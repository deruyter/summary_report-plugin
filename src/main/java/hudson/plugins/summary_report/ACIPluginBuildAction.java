/*
 * The MIT License
 *
 * Copyright (c) 2012, Thomas Deruyter, Raynald Briand
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hudson.plugins.summary_report;

import hudson.model.Action;
import hudson.model.AbstractBuild;
import hudson.plugins.summary_report.report.Report;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

import org.kohsuke.stapler.StaplerProxy;
import org.xml.sax.SAXException;

public class ACIPluginBuildAction implements Action, Serializable, StaplerProxy {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String URL_NAME = "aciResult";
    private AbstractBuild<?, ?> build;
    private String result;
    private Report report;
    private ArrayList< ArrayList<String> > fileError;



    public ACIPluginBuildAction(AbstractBuild<?, ?> build, ArrayList<String> files) throws InterruptedException, ParserConfigurationException, SAXException, URISyntaxException, IOException {
        this.build = build;
        this.report = new Report();
        this.fileError = new ArrayList< ArrayList<String> >();

        for (int i = 0; i < files.size(); i++) {
             
            String current_report = files.get(i);
             
            String path = "file:"+build.getArtifactsDir().getAbsolutePath() +File.separatorChar+ current_report;

            URI xml = new URI(path);


            ParserXml parseur = new ParserXml(xml);

            String resParse = parseur.parse();
            if(resParse.equals("")){
                this.report.addSection(parseur.result());
            }else{
                ArrayList<String> list = new ArrayList<String>();
                list.add("#FF0000");//titleColor
                list.add("Parsing File Error");//fieldName
                list.add("#000000");//detailColor

                String str = build.getArtifactsDir().getName();
                str = str.replace("archive",build.getNumber()+"/artifact");

                list.add(str+File.separatorChar+current_report);//href project page

                str = build.getArtifactsDir().getName();
                str = str.replace("archive","artifact");

                list.add(str+File.separatorChar+current_report);//href build page

                list.add("Cause : "+resParse);//fieldValue
                fileError.add(list);
            }
        }
        
    }
    
    public Report getReport() {
        return report;
    }

     public ArrayList< ArrayList<String> > getFileError() {
        return fileError;
    }

    public String getSummary() {
        StringBuilder builder = new StringBuilder();
        return builder.toString();
    }

    public String getDetails() {
        StringBuilder builder = new StringBuilder();
        return builder.toString();
    }

    public String getResult() {
        return this.result;
    }

    AbstractBuild<?, ?> getBuild() {
        return this.build;
    }

    public Object getTarget() {
        return this.result;
    }

    String getPreviousResult() {
        ACIPluginBuildAction previousAction = this.getPreviousAction();
        String previousResult = null;
        if (previousAction != null) {
            previousResult = previousAction.getResult();
        }
        return previousResult;
    }

    ACIPluginBuildAction getPreviousAction() {
        AbstractBuild<?, ?> previousBuild = this.build.getPreviousBuild();
        if (previousBuild != null) {
            return previousBuild.getAction(ACIPluginBuildAction.class);
        }
        return null;
    }

    public String getProjectName() {
        String str = build.getParent().getName();
        str = str.replace(".", "dot");
        return str;
    }

    public int getBuildNumber() {
        return build.getNumber();
    }
    /**
     * The three functions getIconFileName,getDisplayName,getUrlName
     * create a link to a new page with url : http://{root}/job/{job name}/URL_NAME
     * for the page of the build
     */
    public String getIconFileName() {
        //return "/plugin/summary_report/icons/summary_report.png";
        return null;
    }

    public String getDisplayName() {
        //return "ACIAction";
        return null;
    }

    public String getUrlName() {
        //return URL_NAME;
        return null;
    }
}
