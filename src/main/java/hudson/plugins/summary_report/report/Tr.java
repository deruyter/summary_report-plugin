package hudson.plugins.summary_report.report;

import java.util.ArrayList;


public class Tr {
    private ArrayList<Td> tdList;
    
    public Tr(){
        tdList = new ArrayList<Td>();
    }

    /**
     * @return the tdList
     */
    public ArrayList<Td> getTdList(){
        return tdList;
    }

    /**
     * @param tdList the tdList to set
     */
    public void setTdList(ArrayList<Td> tdList){
        this.tdList = tdList;
    }

    public void addTd(Td td) {
        this.tdList.add(td);
    }
}
