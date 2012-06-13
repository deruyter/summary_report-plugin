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


import hudson.plugins.summary_report.report.Accordion;
import hudson.plugins.summary_report.report.Field;
import hudson.plugins.summary_report.report.Section;
import hudson.plugins.summary_report.report.Tab;
import hudson.plugins.summary_report.report.Table;
import hudson.plugins.summary_report.report.Tabs;
import hudson.plugins.summary_report.report.Td;
import hudson.plugins.summary_report.report.Tr;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserXml {

    private static Section resultat;
    private static URI xml_path;

    /**
     * @param xml URI Path to the xml file
     */
    public ParserXml(final URI xml) {
        xml_path = xml;
    }

    public String parse(){
        try{
            final SAXParserFactory fabrique = SAXParserFactory.newInstance();
            final SAXParser parseur = fabrique.newSAXParser();
            final DefaultHandler gestionnaire = new Analyse();
            parseur.parse(new File(xml_path), gestionnaire);
        }catch (ParserConfigurationException pce){
            return pce.toString();
        }catch (SAXException sax){
            return sax.toString();
        }catch (IOException ioe){
            return ioe.toString();
        }
        return "";
    }


    public Section result() {
        return resultat;
    }

    static class Analyse extends DefaultHandler {

        private Section section;
        private Tabs tmp_tabs;
        private Tab tmp_tab;
        private Field tmp_field;
        private Table tmp_table;
        private Tr tmp_tr;
        private Td tmp_td;
        private Accordion tmp_accordion;

        /**
         * FLAGS
         */
        private boolean f_section = false;
        private boolean f_tabs = false;
        private boolean f_tab = false;
        private boolean f_field = false;
        private boolean f_table = false;
        private boolean f_tr = false;
        private boolean f_td = false;
        private boolean f_accordion = false;
        


        public Analyse() {
            super();
            section = new Section();
        }

        @Override
        public void characters(final char[] ch, final int start,
                final int length) throws SAXException {

            final String lecture = new String(ch, start, length);

            // if the string doesn't contain only empty charaters
            if(!lecture.matches("^\\s*$")){
                if (f_field && length>1){
                    tmp_field.setCdata(lecture);
                }
                if (f_td && length>1){
                    tmp_td.setCdata(lecture);
                }
            }
       }

        @Override
        public void endDocument() throws SAXException {
            resultat = section;
        }

        @Override
        public void endElement(final String uri, final String localName,
                final String qName) throws SAXException {

            if (qName.equals("section") && f_section) {
                f_section = false;
            } else if (qName.equals("accordion") && f_accordion) {
                f_accordion = false;
                section.addObject(tmp_accordion);
            } else if (qName.equals("field") && f_accordion && f_field) {
                f_field = false;
                tmp_accordion.addObject(tmp_field);
            } else if (qName.equals("table") && f_accordion && f_table) {
                f_table = false;
                tmp_accordion.addObject(tmp_table);
            } else if (qName.equals("tabs")  && f_tabs) {
                f_tabs = false;
                section.addObject(tmp_tabs);
            } else if (qName.equals("tab")  && f_tabs && f_tab) {
                f_tab = false;
                tmp_tabs.addTab(tmp_tab);
            } else if (qName.equals("field") && f_tabs && f_tab && f_field) {
                f_field = false;
                tmp_tab.addObject(tmp_field);
            } else if (qName.equals("table") && f_tabs && f_tab && f_table) {
                f_table = false;
                tmp_tab.addObject(tmp_table);
            } else if (qName.equals("field")  && f_field) {
                f_field = false;
                section.addObject(tmp_field);
            } else if (qName.equals("table")  && f_table) {
                f_table = false;
                section.addObject(tmp_table);
            } else if (qName.equals("tr") && f_tr) {
                f_tr = false;
                tmp_table.addTr(tmp_tr);
            } else if (qName.equals("td") && f_td) {
                f_td = false;
                tmp_tr.addTd(tmp_td);
            }
        }

        @Override
        public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
            if (qName.equals("section") ) {
                f_section = true;
                section.setSectionName(attributes.getValue("name"));
                section.setLine(attributes.getValue("line"));
                section.setColumn(attributes.getValue("column"));
                section.setFontColor(attributes.getValue("fontcolor"));
            } else if (qName.equals("tabs")) {
                f_tabs = true;
                tmp_tabs = new Tabs();
            } else if (qName.equals("tab")) {
                f_tab = true;
                tmp_tab = new Tab();
                tmp_tab.setTabName(attributes.getValue("name"));
            } else if (qName.equals("accordion")) {
                f_accordion = true;
                tmp_accordion = new Accordion();
                tmp_accordion.setAccordionName(attributes.getValue("name"));
            } else if (qName.equals("field")) {
                f_field = true;
                tmp_field = new Field();
                tmp_field.setFieldName(attributes.getValue("name"));
                tmp_field.setFieldValue(attributes.getValue("value"));
                tmp_field.setHref(attributes.getValue("href"));
                tmp_field.setTitleColor(attributes.getValue("titlecolor"));
                tmp_field.setDetailColor(attributes.getValue("detailcolor"));
            } else if (qName.equals("table")) {
                f_table = true;
                tmp_table = new Table();
                tmp_table.setSorttable(attributes.getValue("sorttable"));
            } else if (qName.equals("tr")) {
                f_tr = true;
                tmp_tr = new Tr();
            } else if (qName.equals("td")) {
                f_td = true;
                tmp_td = new Td();
                tmp_td.setTdValue(attributes.getValue("value"));
                tmp_td.setBgColor(attributes.getValue("bgcolor"));
                tmp_td.setFontColor(attributes.getValue("fontcolor"));
                tmp_td.setFontAttribute(attributes.getValue("fontattribute"));
                tmp_td.setHref(attributes.getValue("href"));
                tmp_td.setAlign(attributes.getValue("align"));
                tmp_td.setWidth(attributes.getValue("width"));
            }
        }
    }
}
