package cn.com.jashon.export.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.com.jashon.export.annotation.Tag;

public class ImportModel {

	/**
	 * 根据此标识找到对应的实体
	 * @see Tag.java
	 */
	private String tag;
	
	/**
	 * 数据导入类型，暂时只支持xls导入 
	 * 说明：1:xls, 2:txt, 3:rtf, 4:pdf, 5:xml
	 */
	private int type = 1;
	
	/**
	 * 导入文件行范围
	 */
	private Map<String, ImportRow> rows = new LinkedHashMap<String, ImportRow>();

	/**
	 * 导入文件列范围
	 */
	private Map<String, String> cols = new LinkedHashMap<String, String>();
	
	public void addRow(int sheetIndex, ImportRow row) {
		rows.put(String.valueOf(sheetIndex), row);
	}
	
	public void addColumn(int colNum, String fieldName) {
		cols.put(String.valueOf(colNum), fieldName);
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Map<String, ImportRow> getRows() {
		return rows;
	}

	public void setRows(Map<String, ImportRow> rows) {
		this.rows = rows;
	}

	public Map<String, String> getCols() {
		return cols;
	}

	public void setCols(Map<String, String> cols) {
		this.cols = cols;
	}

}