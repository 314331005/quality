package cn.com.jashon.export.domain;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import cn.com.jashon.core.query.OrderType;
import cn.com.jashon.export.annotation.Tag;

/**
 * 通用数据导出模型
 * @author 	dongbolv
 * @date	2014-09-29
 */
public class ExportModel {

	/**
	 * 根据此标识找到对应的实体
	 * @see Tag.java
	 */
	private String tag;

	/**
	 * 文件名前缀
	 */
	private String name;

	/**
	 * 是否在文件名前缀后附加时间戳
	 */
	private boolean suffix = true;

	/**
	 * 数据导出类型，暂时只支持xls导出 
	 * 说明：1:xls, 2:txt, 3:rtf, 4:pdf, 5:xml
	 */
	private int type = 1;

	/**
	 * 是否以ZIP压缩文件形式导出
	 */
	private boolean zip = false;
	
	/**
	 * 数据按此规则排序后导出
	 */
	private LinkedHashMap<String, OrderType> orders = new LinkedHashMap<String, OrderType>();

	/**
	 * 导出文件内容标题栏
	 */
	private List<ExportTitle> titles = new LinkedList<ExportTitle>();

	public void addOrder(String orderBy, OrderType orderType) {
		orders.put(orderBy, orderType);
	}
	
	public void addTitle(ExportTitle title) {
		titles.add(title);
	}

	public void addTitle(String title, int width, String field) {
		addTitle(new ExportTitle(title, width, field));
	}

	public String[] getTitleFields() {
		String[] titleFields = new String[titles.size()];
		int idx = 0;
		for (ExportTitle title : titles) {
			titleFields[idx++] = title.getField();
		}
		return titleFields;
	}

	public boolean isZip() {
		return zip;
	}

	public void setZip(boolean zip) {
		this.zip = zip;
	}

	public LinkedHashMap<String, OrderType> getOrders() {
		return orders;
	}

	public void setOrders(LinkedHashMap<String, OrderType> orders) {
		this.orders = orders;
	}

	public List<ExportTitle> getTitles() {
		return titles;
	}

	public void setTitles(List<ExportTitle> titles) {
		this.titles = titles;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isSuffix() {
		return suffix;
	}

	public void setSuffix(boolean suffix) {
		this.suffix = suffix;
	}

}
