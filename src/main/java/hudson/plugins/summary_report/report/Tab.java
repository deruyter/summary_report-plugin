package hudson.plugins.summary_report.report;

import java.util.ArrayList;

public class Tab {

    private String status;
    private String tabName;
    private ArrayList<Object> objectList;

    
    public Tab(){
        objectList = new ArrayList<Object>();
        status = "tab";
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
     * @return the tabName
     */
    public String getTabName(){
        return tabName;
    }

    /**
     * @param tabName the tabName to set
     */
    public void setTabName(String tabName){
        this.tabName = tabName;

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
