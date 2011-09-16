package hudson.plugins.summary_report.report;

import java.util.ArrayList;

/**
 * Class who represent one report for one build
 *
 */
public class Report {


    private ArrayList<Section> section;

    public Report() {
        section = new ArrayList<Section>();
    }

    /**
     * @return the section
     */
    public ArrayList<Section> getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(ArrayList<Section> section) {
        this.section = section;
    }

    /**
     * @param section The section to add in the list of sections
     */
    public void addSection(Section section){
        this.section.add(section);
    }
}
