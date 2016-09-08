package cn.com.jashon.export.domain;

public class ExportTitle {
	
	private String title;
	
	private int width = 20;
	
	private String field;
	
	public ExportTitle() {
	}

	public ExportTitle(String title, int width, String field) {
		this.title = title;
		this.width = width;
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
