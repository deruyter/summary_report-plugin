package hudson.plugins.summary_report;


import hudson.model.*;
import hudson.tasks.ArtifactArchiver;
import hudson.tasks.Shell;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.jvnet.hudson.test.HudsonTestCase;

/**
 *
 * @author br80
 */
public class ACIPluginPublisherTest  extends HudsonTestCase{
    
    public ACIPluginPublisherTest() {
    }


    /**
     * Build test 
     */
    public void testBuild() throws Exception{

        /* Project creation */
        FreeStyleProject project = createFreeStyleProject("ProjectTestBuild");
        /* Verification if ProjectTestBuild is created */
        assertNotNull(project.getAllJobs());
        assertEquals(1,  project.getAllJobs().toArray().length);

        /* Add ArtifactArchiver publisher */
        ArtifactArchiver archiver = new ArtifactArchiver("*.xml", "", true);
        project.getPublishersList().add(archiver);
        
        /* Add ACIPluginPublisher publisher */
        ACIPluginPublisher publisher = new ACIPluginPublisher("*.xml");
        project.getPublishersList().add(publisher);

        /* After add ACIPluginPublisher and ArtifactArchiver */
        assertEquals(2, project.getPublishersList().size());

        /* Create files to archive */
        project.getBuildersList().add(
                new Shell("rm -rf *;"
                        + "echo  \"  <section name=\\\"Session1\\\">\n"
                        + "             <field name=\\\"Field1\\\" value=\\\"Field1 succeeded\\\"/>\n"
                        + "          </section>"
                        + "\" >> junit1.xml;"));

        /* Build the ProjectTestBuild */
        FreeStyleBuild build = project.scheduleBuild2(0).get();
        assertBuildStatusSuccess(build);

        /* Verification of Artifacts */
        assertTrue(build.getHasArtifacts());
        assertEquals(1,build.getArtifacts().size());

        /* Verification of output xml generate by the plugin */
        /* Methodology: - Browse line per line until summary_report tag */
        /*              - Compare lines with expected lines */
        try{
            
            BufferedReader buff = new BufferedReader(
                    new FileReader(build.getArtifactsDir()+"/../build.xml"));
            try {
                String line;
                boolean xmlGenerated = false;
                while ((line = buff.readLine()) != null) {
                    
                    if(line.contains("<hudson.plugins.summary__report.ACIPluginBuildAction>")){
                        assertTrue(buff.readLine().contains("<build class=\"build\" reference=\"../../..\"/>"));
                        assertTrue(buff.readLine().contains("<report>"));
                        assertTrue(buff.readLine().contains("<section>"));
                        assertTrue(buff.readLine().contains("<hudson.plugins.summary__report.report.Section>"));
                        assertTrue(buff.readLine().contains("<sectionName>Session1</sectionName>"));
                        assertTrue(buff.readLine().contains("<objectList>"));
                        assertTrue(buff.readLine().contains("<hudson.plugins.summary__report.report.Field>"));
                        assertTrue(buff.readLine().contains("<status>field</status>"));
                        assertTrue(buff.readLine().contains("<fieldName>Field1</fieldName>"));
                        assertTrue(buff.readLine().contains("<fieldValue>Field1 succeeded</fieldValue>"));
                        assertTrue(buff.readLine().contains("</hudson.plugins.summary__report.report.Field>"));
                        assertTrue(buff.readLine().contains("</objectList>"));
                        assertTrue(buff.readLine().contains("</hudson.plugins.summary__report.report.Section>"));
                        assertTrue(buff.readLine().contains("</section>"));
                        assertTrue(buff.readLine().contains("</report>"));
                        assertTrue(buff.readLine().contains("<fileError/>"));
                        assertTrue(buff.readLine().contains("</hudson.plugins.summary__report.ACIPluginBuildAction>"));
                        xmlGenerated = true;
                    }
                }
                assertTrue("The summary_report plugin doesn't create xml displaying information", xmlGenerated);
            } finally {
                buff.close();
            }
        } catch (IOException ioe) {
            assertFalse("Erreur --" + ioe.toString(), true);
        }

    }

}