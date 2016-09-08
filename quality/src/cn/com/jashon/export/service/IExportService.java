package cn.com.jashon.export.service;

import java.util.Map;

import cn.com.jashon.core.service.IBaseService;
import cn.com.jashon.export.domain.ExportModel;
import cn.com.jashon.export.domain.ImportModel;

/**
 * Excel数据导入导出服务层接口
 * @author 	dongbolv
 * @date	2015-01-06
 */
public interface IExportService extends IBaseService {

	/**
	 * Excel数据导入
	 * @param filePath	Excel上传临时文件路径
	 * @param clazz		导入数据对应的实体
	 * @param m			数据模型类实例
	 * @return			已导入的数据总数
	 * @throws Exception
	 */
	public int xlsDataImp(String filePath, Class<?> clazz, ImportModel m) throws Exception;
	
	/**
	 * 导出数据到临时文件，并创建zip格式的压缩文件将这些临时文件压缩存储后直接提供用户下载
	 * @param downFileDir	导出数据的临时文件的存放路径
	 * @param clazz			导出数据对应的实体
	 * @param fitlerParams	导出数据集检索条件
	 * @param m				数据模型类实例
	 * @return				导出文件存放路径
	 * @throws Exception
	 */
	public String xlsDataExp(String filePath, 
			Class<?> clazz, Map<String, String> fitlerParams, ExportModel m) throws Exception;
	
}
