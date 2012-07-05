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

import java.lang.String;
import java.io.File;
import java.io.FileWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Class for XML parser
 * @author Raynald Briand
 */
public class ParserXmlTest {

    public ParserXmlTest() {
    }


    /***********************************************************/
    /***********************************************************/
    /**                                                       **/
    /**          Test of wrong xml files (Error)              **/
    /**                                                       **/
    /***********************************************************/
    /***********************************************************/

    /**
     * Test of Error_ParseEmptyFile
     */
    @Test
    public void test_Error_ParseEmptyFile() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_empty_file.xml");

        String xml_path_empty_file = "/tmp/test_empty_file.xml";
        FileWriter writer_empty_file = new FileWriter("/tmp/test_empty_file.xml",true);

        writer_empty_file.write("");
        if (writer_empty_file != null){
            writer_empty_file.close();
        }
        ParserXml instance_empty_file = new ParserXml(xml_path_empty_file);
        assertFalse("The xml files is wrong and the parser say it's right, maybe the parser is incorrect.",instance_empty_file.parse().isEmpty());
    }

    /**
     * Test of Error_ParseTwoSections
     */
    @Test
    public void test_Error_ParseTwoSections() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_two_sections.xml");

        String xml_path_two_sections = "/tmp/test_two_sections.xml";
        FileWriter writer_two_sections = new FileWriter("/tmp/test_two_sections.xml",true);

        writer_two_sections.write("<section name=\"Section Test\">"
                                + "</section>"
                                + "<section name=\"Section2 Test\">"
                                + "</section>");
        if (writer_two_sections != null){
            writer_two_sections.close();
        }

        ParserXml instance_two_sections = new ParserXml(xml_path_two_sections);
        assertFalse("The xml files is wrong and the parser say it's right, maybe the parser is incorrect.",instance_two_sections.parse().isEmpty());
    }

    /**
     * Test of Error_ParseIncompleteSection
     */
    @Test
    public void test_Error_ParseIncompleteSection() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_incomplete_sections.xml");

        String xml_path_incomplete_sections = "/tmp/test_incomplete_sections.xml";
        FileWriter writer_incomplete_sections = new FileWriter("/tmp/test_incomplete_sections.xml",true);

        writer_incomplete_sections.write("<section name=\"Section Test\">"
                                + "</se");
        if (writer_incomplete_sections != null){
            writer_incomplete_sections.close();
        }

        ParserXml instance_incomplete_sections = new ParserXml(xml_path_incomplete_sections);
        assertFalse("The xml files is wrong and the parser say it's right, maybe the parser is incorrect.",instance_incomplete_sections.parse().isEmpty());
    }

     /**
     * Test of Error_ParseWrongField
     */
    @Test
    public void test_Error_ParseWrongField() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_wrong_field.xml");

        String xml_path_wrong_field = "/tmp/test_wrong_field.xml";
        FileWriter writer_wrong_field = new FileWriter("/tmp/test_wrong_field.xml",true);

        writer_wrong_field.write("<section name=\"Section Test\">"
                               + "  <field name=\"TestFie value=\"Test succeeded\"/>"
                               + "</section>");
        
        if (writer_wrong_field != null){
            writer_wrong_field.close();
        }

        ParserXml instance_wrong_field = new ParserXml(xml_path_wrong_field);
        assertFalse("The xml files is wrong and the parser say it's right, maybe the parser is incorrect.",instance_wrong_field.parse().isEmpty());
    }

     /**
     * Test of Error_ParseWrongTable
     */
    @Test
    public void test_Error_ParseWrongTable() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_wrong_table.xml");

        String xml_path_wrong_table = "/tmp/test_wrong_table.xml";
        FileWriter writer_wrong_table = new FileWriter("/tmp/test_wrong_table.xml",true);

        writer_wrong_table.write("<section name=\"Section Test\">"
                               + "  <table>"
                               + "    <tr>"
                               + "      <td value=\"value1\" fontattribute=\"bold\"   width=\"200\" align=\"left\"   />"
                               + "      <td value=\"value2\" fontattribute=\"normal\" width=\"70\"  align=\"center\" />"
                               + "    </tr>"
                               + "    <tr>"
                               + "  </table>"
                               + "</section>");

        if (writer_wrong_table != null){
            writer_wrong_table.close();
        }

        ParserXml instance_wrong_table = new ParserXml(xml_path_wrong_table);
        assertFalse("The xml files is wrong and the parser say it's right, maybe the parser is incorrect.",instance_wrong_table.parse().isEmpty());
    }

    /**
     * Test of Error_ParseWrongAccordion
     */
    @Test
    public void test_Error_ParseWrongAccordion() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_wrong_accordion.xml");

        String xml_path_wrong_accordion = "/tmp/test_wrong_accordion.xml";
        FileWriter writer_wrong_accordion = new FileWriter("/tmp/test_wrong_accordion.xml",true);

        writer_wrong_accordion.write("<section name=\"Section Test\">"
                                   + "  <accordion name=\"TestAccordion\">"
                                   + "    <field name=\"TestField\" value=\"Test succeeded\"/>"
                                   + "  </accor"
                                   + "</section>");

        if (writer_wrong_accordion != null){
            writer_wrong_accordion.close();
        }

        ParserXml instance_wrong_accordion = new ParserXml(xml_path_wrong_accordion);
        assertFalse("The xml files is wrong and the parser say it's right, maybe the parser is incorrect.",instance_wrong_accordion.parse().isEmpty());
    }

     /**
     * Test of Error_ParseWrongTabs
     */
    @Test
    public void test_Error_ParseWrongTabs() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_wrong_tabs.xml");

        String xml_path_wrong_tabs = "/tmp/test_wrong_tabs.xml";
        FileWriter writer_wrong_tabs = new FileWriter("/tmp/test_wrong_tabs.xml",true);

        writer_wrong_tabs.write("<section name=\"Section Test\">"
                              + "  <tabs>"
                              + "    <tab name=\"Tab1\">"
                              + "      <field name=\"TestField\" value=\"Test succeeded\"/>"
                              + "      <table>"
                              + "        <tr>"
                              + "          <td value=\"value1\" fontattribute=\"bold\"   width=\"200\" align=\"left\"   />"
                              + "          <td value=\"value2\" fontattribute=\"normal\" width=\"70\"  align=\"center\" />"
                              + "        </tr>"
                              + "      </table>"
                              +"     </tab>"
                              + "    <tab name=\"Tab2\">"
                              + "  </tabs>"
                              + "</section>");

        if (writer_wrong_tabs != null){
            writer_wrong_tabs.close();
        }

        ParserXml instance_wrong_tabs = new ParserXml(xml_path_wrong_tabs);
        assertFalse("The xml files is wrong and the parser say it's right, maybe the parser is incorrect.",instance_wrong_tabs.parse().isEmpty());
    }


    /***********************************************************/
    /***********************************************************/
    /**                                                       **/
    /**          Test of right xml files (Pass)                **/
    /**                                                       **/
    /***********************************************************/
    /***********************************************************/

    /**
     * Test of parse Pass_ParseField
     */
    @Test
    public void test_Pass_ParseField() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_field.xml");

        String xml_path_field = "/tmp/test_field.xml";
        FileWriter writer_field = new FileWriter("/tmp/test_field.xml",true);

        writer_field.write("<section name=\"Section Test\">"
                         + "  <field name=\"TestField\" value=\"Test succeeded\"/>"
                         + "  <field name=\"TestField2\" value=\"Test2 succeeded\"><![CDATA[+++ !@#$%^&*()_+{}[]|\':;,.<> ]]></field>"
                         + "</section>");
        if (writer_field != null){
            writer_field.close();
        }

        ParserXml instance_field = new ParserXml(xml_path_field);
        assertTrue("The xml files is right and the parser say it's wrong, maybe the parser is incorrect. "+instance_field.parse(),instance_field.parse().isEmpty());
    }
    
    /**
     * Test of parse Pass_ParseTable
     */
    @Test
    public void test_Pass_ParseTable() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_table.xml");

        String xml_path_table = "/tmp/test_table.xml";
        FileWriter writer_table = new FileWriter("/tmp/test_table.xml",true);

        writer_table.write("<section name=\"Section Test\">"
                         + "  <table>"
                         + "    <tr>"
                         + "      <td value=\"value1\" fontattribute=\"bold\"   width=\"200\" align=\"left\"   />"
                         + "      <td value=\"value2\" fontattribute=\"normal\" width=\"70\"  align=\"center\" />"
                         + "    </tr>"
                         + "    <tr>"
                         + "      <td value=\"value3\" fontattribute=\"bold\"   width=\"200\" align=\"left\"   />"
                         + "      <td value=\"value4\" fontattribute=\"normal\" width=\"70\"  align=\"center\" />"
                         + "    </tr>"
                         + "  </table>"
                         + "</section>");
        if (writer_table != null){
            writer_table.close();
        }

        ParserXml instance_table = new ParserXml(xml_path_table);
        assertTrue("The xml files is right and the parser say it's wrong, maybe the parser is incorrect. "+instance_table.parse(),instance_table.parse().isEmpty());
    }

    /**
     * Test of parse Pass_ParseAccordion
     */
    @Test
    public void test_Pass_ParseAccordion() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_accordion.xml");

        String xml_path_accordion = "/tmp/test_accordion.xml";
        FileWriter writer_accordion = new FileWriter("/tmp/test_accordion.xml",true);

        writer_accordion.write("<section name=\"Section Test\">"
                             + "  <accordion name=\"TestAccordion\">"
                             + "    <field name=\"TestField\" value=\"Test succeeded\"/>"
                             + "    <table>"
                             + "      <tr>"
                             + "        <td value=\"value1\" fontattribute=\"bold\"   width=\"200\" align=\"left\"   />"
                             + "        <td value=\"value2\" fontattribute=\"normal\" width=\"70\"  align=\"center\" />"
                             + "      </tr>"
                             + "    </table>"
                             + "  </accordion>"
                             + "</section>");
        if (writer_accordion != null){
            writer_accordion.close();
        }

        ParserXml instance_accordion = new ParserXml(xml_path_accordion);
        assertTrue("The xml files is right and the parser say it's wrong, maybe the parser is incorrect. "+instance_accordion.parse(),instance_accordion.parse().isEmpty());
    }

    /**
     * Test of parse Pass_ParseTabs
     */
    @Test
    public void test_Pass_ParseTabs() throws Exception {

        /* Verification of existing file */
        deleteFiles("/tmp/test_tabs.xml");

        String xml_path_tabs = "/tmp/test_tabs.xml";
        FileWriter writer_tabs = new FileWriter("/tmp/test_tabs.xml",true);

        writer_tabs.write("<section name=\"Section Test\">"
                             + "  <tabs>"
                             + "    <tab name=\"Tab1\">"
                             + "      <field name=\"TestField\" value=\"Test succeeded\"/>"
                             + "      <table>"
                             + "        <tr>"
                             + "          <td value=\"value1\" fontattribute=\"bold\"   width=\"200\" align=\"left\"   />"
                             + "          <td value=\"value2\" fontattribute=\"normal\" width=\"70\"  align=\"center\" />"
                             + "        </tr>"
                             + "      </table>"
                             +"     </tab>"
                             + "    <tab name=\"Tab2\">"
                             + "      <field name=\"TestField\" value=\"Test succeeded\"/>"
                             + "      <table>"
                             + "        <tr>"
                             + "          <td value=\"value1\" fontattribute=\"bold\"   width=\"200\" align=\"left\"   />"
                             + "          <td value=\"value2\" fontattribute=\"normal\" width=\"70\"  align=\"center\" />"
                             + "        </tr>"
                             + "      </table>"
                             +"     </tab>"
                             + "  </tabs>"
                             + "</section>");
        if (writer_tabs != null){
            writer_tabs.close();
        }

        ParserXml instance_tabs = new ParserXml(xml_path_tabs);
        assertTrue("The xml files is right and the parser say it's wrong, maybe the parser is incorrect. "+instance_tabs.parse(),instance_tabs.parse().isEmpty());
    }

    /**
     * Function to delete existing files before creation
     * @param path Path of the file
     */
    public void deleteFiles(String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
        file = null;
    }
}