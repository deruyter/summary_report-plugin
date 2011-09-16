package hudson.plugins.summary_report;

import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.BuildListener;


import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Recorder;
import java.io.File;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.Project;

import org.kohsuke.stapler.DataBoundConstructor;
import org.xml.sax.SAXException;

public class ACIPluginPublisher extends Recorder {

    /* The list name of the files to parse */
    private String name;

    @DataBoundConstructor
    public ACIPluginPublisher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Action getProjectAction(AbstractProject<?, ?> project) {
        return new ACIPluginProjectAction(project);
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {

        /**
         * Define if we must parse multiple file by searching for , in the name var
         */
        String[] files = name.split(",");

        ArrayList<String> filesToParse = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            FileSet fileSet = new FileSet();
            File workspace = build.getArtifactsDir();
            fileSet.setDir(workspace);
            fileSet.setIncludes(files[i].trim());
            Project antProject = new Project();
            fileSet.setProject(antProject);
            String[] tmp_files = fileSet.getDirectoryScanner(antProject).getIncludedFiles();

            for (int j = 0; j < tmp_files.length; j++) {
                
                filesToParse.add(tmp_files[j]);
            }
           
        }

        try {       
            ACIPluginBuildAction buildAction;
            buildAction = new ACIPluginBuildAction(build, filesToParse);        
            build.addAction(buildAction);
        } catch (ParserConfigurationException ex) {
        } catch (SAXException ex) {
        } catch (URISyntaxException ex) {
        }

        return true;

    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }
}
