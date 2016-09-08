package cn.com.jashon.export.utils;

import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import cn.com.jashon.export.domain.ExportTitle;

public class XlsUtil {
	
	private static Log log = Logs.get();
	
	/**
	 * 创建 Excel的sheet的标题头样式
	 * @return
	 */
	public WritableCellFormat getTitleStyle() {
		WritableFont font = new WritableFont(WritableFont.ARIAL, 11);
		WritableCellFormat format = null;
		try {
			font.setBoldStyle(WritableFont.BOLD);
			format = new WritableCellFormat(font);
			format.setAlignment(Alignment.CENTRE);
			format.setVerticalAlignment(VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		} catch (WriteException e) {
			if(log.isErrorEnabled()) {
				log.errorf("标题样式设置错误 ：%s", e.getMessage());
			}
		}
		return format;
	}
	
	/**
	 * 创建 Excel的sheet的单元格样式
	 * @return
	 */
	public WritableCellFormat getCellStyle() {
		WritableFont font = new WritableFont(WritableFont.ARIAL, 11);
		WritableCellFormat format = null;
		try {
			format = new WritableCellFormat(font);
			format.setAlignment(Alignment.LEFT);
			format.setVerticalAlignment(VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			if(log.isErrorEnabled()) {
				log.errorf("标题样式设置错误 ：%s", e.getMessage());
			}
		}
		return format;
	}
	
	/**
	 * 创建无标题的sheet
	 * @param wwb
	 * @param sheetName
	 * @return
	 * @throws WriteException
	 */
	public WritableSheet createSheet(WritableWorkbook wwb, String sheetName) throws WriteException {
		WritableSheet sheet = wwb.createSheet(sheetName, 0);
		sheet.setRowView(0, 300);// 设置行高
		return sheet;
	}
	
	/**
	 * 根据传入的参数向sheet中写入标题信息
	 * @param wwb
	 * @param sheetName	sheet名称
	 * @param titles	标题信息列表
	 * @return
	 * @throws WriteException
	 */
	public WritableSheet createSheet(WritableWorkbook wwb, String sheetName, List<ExportTitle> titles) throws WriteException {
		return createSheet(wwb, 0, sheetName, titles);
	}
	
	/**
	 * 根据传入的参数向sheet中写入标题信息
	 * @param wwb
	 * @param sheetName	sheet名称
	 * @param titles	标题信息列表
	 * @return
	 * @throws WriteException
	 */
	public WritableSheet createSheet(WritableWorkbook wwb, int sheetIndex, String sheetName, List<ExportTitle> titles) throws WriteException {
		WritableSheet sheet = wwb.createSheet(sheetName, Math.max(sheetIndex, 0));
		sheet.setRowView(0, 400);// 设置行高
		Label label = null;
		ExportTitle title = null;
		
		sheet.addCell(new Label(0, 0, "序号", getTitleStyle()));
		sheet.setColumnView(0, 5);//设置列宽
		
		for (int i = 0; i < titles.size(); i++) {
			title = titles.get(i);
			if(title == null) {
				continue;
			}
			label = new Label(i+1, 0, title.getTitle(), getTitleStyle());
			sheet.addCell(label);
			sheet.setColumnView(i+1, title.getWidth());//设置列宽
		}
		return sheet;
	}
	
}
