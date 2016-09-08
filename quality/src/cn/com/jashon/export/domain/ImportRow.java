package cn.com.jashon.export.domain;

/**
 * 导入文件行读取范围
 */
public class ImportRow {

	/**
	 * 开始行
	 */
	private int start = 1;
	
	/**
	 * 结束行（-1为文件内容末尾）
	 */
	private int end = -1;
	
	public ImportRow() {
	}
	
	public ImportRow(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
}
