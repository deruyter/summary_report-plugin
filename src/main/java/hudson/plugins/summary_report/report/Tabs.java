package hudson.plugins.summary_report.report;

import java.util.ArrayList;

public class Tabs {

    private String status;
    private ArrayList<Tab> tabList;


    public Tabs(){
        tabList = new ArrayList<Tab>();
        status = "tabs";
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
     * @return the tabList
     */
    public ArrayList<Tab> getTabList(){
        return tabList;
    }

    /**
     * @param tabList the tabList to set
     */
    public void setTabList(ArrayList<Tab> tabList){
        this.tabList = tabList;
    }

    public void addTab(Tab obj){
        this.tabList.add(obj);
    }

}
