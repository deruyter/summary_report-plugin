package hudson.plugins.summary_report;

import hudson.model.Action;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.plugins.summary_report.report.Report;

import java.io.Serializable;
import java.util.ArrayList;



public class ACIPluginProjectAction implements Action, Serializable {
    
    public static final String URL_NAME = "aciResult";
    public AbstractProject<?,?> project;

    public ACIPluginProjectAction(final AbstractProject<?, ?> project) {
        this.project = project;
    }

    /**
     * The three functions getIconFileName,getDisplayName,getUrlName
     * create a link to a new page with url : http://{root}/job/{job name}/URL_NAME
     * for the page of the project
     */
    public String getIconFileName() {
//        return "/plugin/summary_report/icons/summary_report.png";
        return null;
    }
    public String getDisplayName() {
//        return "ACIProjectAction";
        return null;
    }
    public String getUrlName() {
//        return URL_NAME;
        return null;
    }
       

    /**
     * Returns the last finished build.
     *
     * @return the last finished build or <code>null</code> if there is no such build
     */
    public AbstractBuild<?, ?> getLastFinishedBuild() {
        AbstractBuild<?, ?> lastBuild = project.getLastBuild();
        while (lastBuild != null && (lastBuild.isBuilding() || lastBuild.getAction(ACIPluginBuildAction.class) == null)) {
            lastBuild = lastBuild.getPreviousBuild();
        }
        return lastBuild;
    }

    /**
     * This method get the report of the last finished build
     * @return The last Report
     */
    public Report getReport() {
       AbstractBuild<?, ?> build = getLastFinishedBuild();
       ACIPluginBuildAction resultAction = build.getAction(ACIPluginBuildAction.class);
       return resultAction.getReport();
    }

    /**
     * This method get the files error of the last finished build
     * @return The last FileError
     */
    public ArrayList< ArrayList<String> > getFileError() {
        AbstractBuild<?, ?> build = getLastFinishedBuild();
        ACIPluginBuildAction resultAction = build.getAction(ACIPluginBuildAction.class);
        return resultAction.getFileError();
    }

    public String getProjectName() {
        String str = project.getName();
        str = str.replace(".", "dot");
        return str;
    }
}
