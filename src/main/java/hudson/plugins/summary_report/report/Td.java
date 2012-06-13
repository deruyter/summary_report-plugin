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
