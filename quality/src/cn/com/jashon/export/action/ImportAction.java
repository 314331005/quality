package cn.com.jashon.export.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import cn.com.jashon.core.action.BaseAction;
import cn.com.jashon.core.utils.DwzUtil;
import cn.com.jashon.core.utils.JsonUtil;
import cn.com.jashon.export.domain.ImportModel;
import cn.com.jashon.export.service.IExportService;
import cn.com.jashon.export.utils.Tags;

/**
 * 通用数据导入控制器
 * @author 	dongbolv
 * @date	2015-01-05
 */
@IocBean
@At("/imp")
public class ImportAction extends BaseAction {

	private static final Log log = Logs.get();
		
	@Inject
	private IExportService exportService;
	
	/**
	 * Excel数据导入
	 * @param json	json格式字符串，包含数据导入所需的主要配置
	 */
	@At("/xls")
	@AdaptBy(type = UploadAdaptor.class,args = { "/uploadTemp", "8192", "UTF-8", "50" }) 
	public Object xls(String json, @Param("f") TempFile f, String tid,
			HttpServletRequest req, ServletContext context) {
		try {
			ImportModel m = JsonUtil.fromJson(ImportModel.class, json);
			final String tag = m.getTag();
			final Class<?> clazz = Tags.get(Mvcs.getServletContext(), tag);
			
			final String ext = f.getMeta().getFileExtension();
			if(!(".xls".equalsIgnoreCase(ext))) {
				throw new Exception("文件格式不正确，请上传 .xls 文件!");
			}
			
			File origFile = f.getFile();
			if(origFile.length() <= 0) {
				throw new Exception("文件大小不符合规则，请上传大于 0KB 的文件!");
			}
			final String filePath = origFile.getPath();
			int count = exportService.xlsDataImp(filePath, clazz, m);
			
			return DwzUtil.reloadCurrPage("已批量导入 " + count + " 条数据!", "");
		} catch(Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("数据批量导入失败: %s", e.getMessage());
			}
			return DwzUtil.stopPageError("数据批量导入失败!");
		} finally {
			Files.deleteFile(f.getFile());//删除临时文件
		}
	}
	
}
