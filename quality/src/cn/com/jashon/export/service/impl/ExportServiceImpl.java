package cn.com.jashon.export.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Times;

import cn.com.jashon.core.service.impl.BaseServiceImpl;
import cn.com.jashon.core.utils.HtmlUtil;
import cn.com.jashon.core.utils.InvokeUtil;
import cn.com.jashon.core.utils.JsonUtil;
import cn.com.jashon.core.utils.ParseUtil;
import cn.com.jashon.core.utils.PropsUtil;
import cn.com.jashon.export.domain.ExportModel;
import cn.com.jashon.export.domain.ExportTitle;
import cn.com.jashon.export.domain.ImportModel;
import cn.com.jashon.export.domain.ImportRow;
import cn.com.jashon.export.service.IExportService;
import cn.com.jashon.export.utils.XlsUtil;

/**
 * Excel数据导入导出服务层接口
 * @author 	dongbolv
 * @date	2015-01-06
 */
@IocBean(name = "exportService", args = { "refer:dao" })
public class ExportServiceImpl extends BaseServiceImpl implements IExportService {
	
	public ExportServiceImpl(Dao dao){
		this.dao = dao;
	}
	
	/**
	 * Excel数据导入
	 * @param filePath	Excel上传临时文件路径
	 * @param clazz		导入数据对应的实体
	 * @param m			数据模型类实例
	 * @return			已导入的数据总数
	 * @throws Exception
	 */
	public int xlsDataImp(String filePath, Class<?> clazz, ImportModel m) throws Exception {
		int dataImpCount = 0;
		
		Workbook wwb = null;
		Sheet sheet = null;
		try {
			wwb = Workbook.getWorkbook(new File(filePath));
			int sheetNums = wwb.getNumberOfSheets();
			if(sheetNums < 1) {
				throw new RuntimeException("Sheet not found."); 
			}
			
			Map<String, ImportRow> rowsMap = m.getRows();
			Map<String, String> colsMap = m.getCols();
			
			ImportRow ir = null;
			String key = null;
			Object target = null;
			for(int idx=0; idx<sheetNums; idx++) {
				// 读取数据模型实例配置信息，检测当前sheet下是否有需要读取的数据
				key = String.valueOf(idx);
				if(!rowsMap.containsKey(key)) {
					continue;
				}
				ir = rowsMap.get(key);
				if(ir == null) {
					continue;
				}
				
				sheet = wwb.getSheet(idx);
				int colNums = sheet.getColumns();
				// 计算需要读取的数据行的起止索引
				int rowNums = sheet.getRows();
				int startRowNum = Math.max(ir.getStart(), 0);
				int endRowNum = ir.getEnd();
				endRowNum = endRowNum < 0 ? rowNums : Math.min(endRowNum, rowNums);
				int colNum = -1;
				for(; startRowNum<endRowNum; startRowNum++) {
					try {
						target = InvokeUtil.newInstance(clazz);
						for(Map.Entry<String, String> column : colsMap.entrySet()) {
							colNum = ParseUtil.toInt(column.getKey());
							if(colNum >= colNums) {
								continue;
							}
							Cell cell = sheet.getCell(colNum, startRowNum); 
							InvokeUtil.setValue(target, column.getValue(), cell.getContents());
							
						}
						// 导入数据
						update(target);
						dataImpCount++;
						
						if(log.isInfoEnabled()) {
							log.infof("Excel data import success : %s, ", JsonUtil.toJson(target));
						}
					} catch(Exception e) {
						if(log.isErrorEnabled()) {
							log.errorf("Excel data import error : %s", e.getMessage());
						}
					}
				}
			}
		} finally {
			if(wwb != null) {
				wwb.close();
				wwb = null;
			}
		}
		
		return dataImpCount;
	}
	
	/**
	 * 导出数据到临时文件，并创建zip格式的压缩文件将这些临时文件压缩存储后直接提供用户下载
	 * @param downFileDir	导出数据的临时文件的存放路径
	 * @param clazz			导出数据对应的实体
	 * @param fitlerParams	导出数据集检索条件
	 * @param m				数据模型类实例
	 * @return				导出文件存放路径
	 * @throws Exception
	 */
	public String xlsDataExp(String downFileDir, 
			Class<?> clazz, Map<String, String> fitlerParams, ExportModel m) throws Exception {
		final String filePrefix = m.getName();
		final boolean isAddSuffix = m.isSuffix(); 
//		boolean isZip = m.isZip();
		List<ExportTitle> titles = m.getTitles();
		final String[] titleFields = m.getTitleFields();
		//数据集
		List<Map<String, String>> fields = findMapList(clazz, 
				fitlerParams, m.getOrders(), Arrays.asList(m.getTitleFields()));
		
		String tmpFilePath = downFileDir + "/" + System.currentTimeMillis();//并发下载，根据时间点创建临时文件夹
		Files.createDirIfNoExists(downFileDir);
		Files.createDirIfNoExists(tmpFilePath);
		
		String filePath = tmpFilePath + "/" + filePrefix + (isAddSuffix ? Times.format("yyyyMMddHHmmss", Times.now()) : "") + ".xls";
		
		int dataCount = fields.size();//导出的数据总条数
		//依据每个文件中存放的数据条数计算需要创建的文件个数
		final int ROW_COUNT_PER_FILE = PropsUtil.getInteger("exp.sheet.max", 20000);
		int fileCount = dataCount % ROW_COUNT_PER_FILE == 0 ? dataCount / ROW_COUNT_PER_FILE : dataCount / ROW_COUNT_PER_FILE + 1;
		Label label = null;
		WritableWorkbook wwb = null;
		try {
			XlsUtil xls = new XlsUtil();
			
			wwb = Workbook.createWorkbook(new File(filePath));
			//容错处理，数据集为空时创建空sheet后返回
			if(fileCount <= 0) {
				xls.createSheet(wwb, 0, "Sheet1", titles);
				return filePath;
			}
			
			for(int count=0; count<fileCount; count++){
				WritableSheet ws = xls.createSheet(wwb, count, "Sheet" + (count + 1), titles);
				if(!Lang.isEmpty(fields)) {
					Map<String, String> values = null;
					//sheet中第一行（row=0）为标题,故此处定义为1
					for(int row=1; row<=dataCount; row++) {
						values = fields.get(row-1);
						if(values == null || values.size() == 0) continue;
						//遍历每条数据的各个字段，取出各字段对应的数据并放入单元格中
						ws.addCell(new Label(0, row, String.valueOf(row)));
						for (int col=0,len=titleFields.length; col<len; col++) {
							if(values.containsKey(titleFields[col]) && values.get(titleFields[col]) != null) {
								label = new Label(col+1, row, HtmlUtil.unescape(values.get(titleFields[col])));
							} else {
								label = new Label(col+1, row, "");
							}
							ws.addCell(label);
						}
						//
						if(row % ROW_COUNT_PER_FILE == 0) {
							row = 1;
							break;
						}
					}
				}
			}
		} finally {
			if(wwb != null) {
				wwb.write();
				wwb.close();
				wwb = null;
			}
		}
		
		return filePath;
	}

}
