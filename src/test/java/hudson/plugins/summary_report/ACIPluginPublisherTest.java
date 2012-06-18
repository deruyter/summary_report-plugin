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


import hudson.model.*;
import hudson.tasks.ArtifactArchiver;
import hudson.tasks.Shell;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.jvnet.hudson.test.HudsonTestCase;

/**
 *
 * @author Raynald Briand
 * @author Thomas Deruyter
 */
public class ACIPluginPublisherTest  extends HudsonTestCase{
    
    public ACIPluginPublisherTest() {
    }


    /**
     * Build test 
     */
    public void testBuild() throws Exception {
    	
    	final boolean debug_info = false;

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
            System.out.println(build.getArtifactsDir()+"/../build.xml");
            try {
                String line;
                boolean xmlGenerated = false;
                while ((line = buff.readLine()) != null) {
                	System.out.println(line);
                    if(line.contains("<hudson.plugins.summary__report.ACIPluginBuildAction>")) {
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<build class=\"build\" reference=\"../../..\"/>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<report>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<reportSection>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<hudson.plugins.summary__report.report.Section>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<sectionName>Session1</sectionName>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<objectList>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<hudson.plugins.summary__report.report.Field>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<status>field</status>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<fieldName>Field1</fieldName>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<fieldValue>Field1 succeeded</fieldValue>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("</hudson.plugins.summary__report.report.Field>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("</objectList>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("</hudson.plugins.summary__report.report.Section>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("</reportSection>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("</report>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("<fileError/>"));
                    	line = buff.readLine(); 
                    	if (debug_info) System.out.println(line);
                        assertTrue(line.contains("</hudson.plugins.summary__report.ACIPluginBuildAction>"));
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