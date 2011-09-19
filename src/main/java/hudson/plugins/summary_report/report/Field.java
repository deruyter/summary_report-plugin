package hudson.plugins.summary_report.report;

public class Field {

    private String status;
    private String fieldName;
    private String fieldValue;
    private String cdata;
    private String href;
    private String titleColor;
    private String detailColor;

    public Field() {
        status = "field";
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
     * @return the fieldName
     */
    public String getFieldName(){
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName){
        this.fieldName = fieldName;

    }

    /**
     * @return the fieldValue
     */
    public String getFieldValue(){
        return fieldValue;
    }

    /**
     * @param fieldValue the fieldValue to set
     */
    public void setFieldValue(String fieldValue){
        this.fieldValue = fieldValue;

    }

    /**
     * @return the cdata
     */
    public String getCdata(){
            return cdata;
    }

    /**
     * @param cdata the cdata to set
     */
    public void setCdata(String cdata){    
        if (this.cdata == null) this.cdata = cdata;
        else this.cdata = this.cdata + cdata;
    }

    /**
     * @return the ref
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href the ref to set
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return the titleColor
     */
    public String getTitleColor() {
        return titleColor;
    }

    /**
     * @param titleColor the titleColor to set
     */
    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    /**
     * @return the detailColor
     */
    public String getDetailColor() {
        return detailColor;
    }

    /**
     * @param detailColor the detailColor to set
     */
    public void setDetailColor(String detailColor) {
        this.detailColor = detailColor;
    }
}
