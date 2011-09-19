package hudson.plugins.summary_report.report;

public class Td {
    private String tdValue;
    private String bgColor;
    private String fontColor;
    private String fontAttribute;
    private String href;
    private String width;
    private String align;
    private String cdata;
    
    public Td(){
        this.width="100";
        this.align="left";
    }

    /**
     * @return the tdValue
     */
    public String getTdValue() {
        return tdValue;
    }

    /**
     * @param tdValue the tdValue to set
     */
    public void setTdValue(String tdValue) {
        this.tdValue = tdValue;
    }

    /**
     * @return the bgColor
     */
    public String getBgColor() {
        return bgColor;
    }

    /**
     * @param bgColor the bgColor to set
     */
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * @return the fontColor
     */
    public String getFontColor() {
        return fontColor;
    }

    /**
     * @param fontColor the fontColor to set
     */
    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    /**
     * @return the fontAttribute
     */
    public String getFontAttribute() {
        return fontAttribute;
    }

    /**
     * @param fontAttribute the fontAttribute to set
     */
    public void setFontAttribute(String fontAttribute) {
        this.fontAttribute = fontAttribute;
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
     * @return the width
     */
    public String getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(String width) {
        this.width = width;
    }
    /**
     * @return the align
     */
    public String getAlign() {
        return align;
    }

    /**
     * @param align the alignment to set
     */
    public void setAlign(String align) {
        this.align = align;
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
}
