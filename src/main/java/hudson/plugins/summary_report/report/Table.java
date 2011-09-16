package hudson.plugins.summary_report.report;

import java.util.ArrayList;

public class Table {
    
    private String status;
    private ArrayList<Tr> trList;
    private String sorttable;
    
    public Table(){
        trList = new ArrayList<Tr>();
        status = "table";
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
     * @return the trList
     */
    public ArrayList<Tr> getTrList(){
        return trList;
    }

    /**
     * @param trList the trList to set
     */
    public void setTrList(ArrayList<Tr> trList){
        this.trList = trList;
    }

    /**
     * @return the sorttable
     */
    public String getSorttable(){
        return sorttable;
    }

    /**
     * @param sorttable the sorttable to set
     */
    public void setSorttable(String sorttable){
        this.sorttable = sorttable;

    }

    public void addTr(Tr tr) {
        this.trList.add(tr);
    }
}
