package hudson.plugins.summary_report;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;

@Extension
public class ACIPluginDescriptor extends BuildStepDescriptor<Publisher> {

    public ACIPluginDescriptor(){
        super(ACIPluginPublisher.class);
    }

    public boolean isApplicable(Class<? extends AbstractProject> jobType) {
    	return true;
    }

    @Override
    public String getDisplayName() {
        return "Publish ACI reports";
    }
}
