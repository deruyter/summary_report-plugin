package hudson.plugins.summary_report.report;

import java.util.ArrayList;

public class Section {

    private String sectionName;
    private String line;
    private String column;
    private ArrayList<Object> objectList;
    private String fontColor;

    public Section(){
        objectList = new ArrayList<Object>();
    }

    /**
     * @return the sectionName
     */
    public String getSectionName(){
        return sectionName;
    }

    /**
     * @param sectionName the sectionName to set
     */
    public void setSectionName(String sectionName){
        this.sectionName = sectionName;
    }

    /**
     * @return the line
     */
    public String getLine(){
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(String line){
        this.line = line;

    }

    /**
     * @return the column
     */
    public String getColumn(){
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(String column){
        this.column = column;

    }

    /**
     * @return the objectList
     */
    public ArrayList<Object> getObjectList(){
        return objectList;
    }

    /**
     * @param objectList the objectList to set
     */
    public void setObjectList(ArrayList<Object> objectList){
        this.objectList = objectList;
    }

    public void addObject(Object obj){
        this.objectList.add(obj);
    }

    /**
     * @return the fontColor
     */
    public String getfontColor(){
        return fontColor;
    }

    /**
     * @param fontColor the fontColor to set
     */
    public void setFontColor(String fontColor){
        this.fontColor = fontColor;

    }
}
