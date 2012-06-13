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

import java.util.ArrayList;

public class Table {

	private String status;
	private ArrayList<Tr> trList;
	private String sorttable;

	public Table() {
		trList = new ArrayList<Tr>();
		status = "table";
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;

	}

	/**
	 * @return the trList
	 */
	public ArrayList<Tr> getTrList() {
		return trList;
	}

	/**
	 * @param trList
	 *            the trList to set
	 */
	public void setTrList(ArrayList<Tr> trList) {
		this.trList = trList;
	}

	/**
	 * @return the sorttable
	 */
	public String getSorttable() {
		return sorttable;
	}

	/**
	 * @param sorttable
	 *            the sorttable to set
	 */
	public void setSorttable(String sorttable) {
		this.sorttable = sorttable;

	}

	public void addTr(Tr tr) {
		this.trList.add(tr);
	}
}
