package hudson.plugins.summary_report.report;

import java.util.ArrayList;

public class Accordion {

    private String status;
    private String accordionName;
    private ArrayList<Object> objectList;

    public Accordion(){
        objectList = new ArrayList<Object>();
        status = "accordion";
    }

    /**
     * @return the status
     */
    public String getStatus(){
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status){
        this.status = status;

    }
    /**
     * @return the accordionName
     */
    public String getAccordionName(){
        return accordionName;
    }

    /**
     * @param accordionName the accordionName to set
     */
    public void setAccordionName(String accordionName){
        this.accordionName = accordionName;

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

}
