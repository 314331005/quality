package cn.com.jashon.export.action;

import java.io.File;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.com.jashon.core.action.BaseAction;
import cn.com.jashon.core.utils.JsonUtil;
import cn.com.jashon.export.domain.ExportModel;
import cn.com.jashon.export.service.IExportService;
import cn.com.jashon.export.utils.Tags;

/**
 * 通用数据导出控制器
 * @author 	dongbolv
 * @date	2014-09-29
 */
@IocBean
@At("/exp")
public class ExportAction extends BaseAction {
	
	private static final Log log = Logs.get();
	
	@Inject
	private IExportService exportService;
	
	/**
	 * 导出Excel文件
	 * @param json	json格式字符串，包含数据导出所需的主要配置
	 */
	@At("/xls")
	@Ok("exp:stream")
	public Object xls(String json) {
		try {
			ExportModel m = JsonUtil.fromJson(ExportModel.class, json);
			final String downFileDir = Mvcs.getServletContext().getRealPath("/export");
			
			final String tag = m.getTag();
			final Class<?> clazz = Tags.get(Mvcs.getServletContext(), tag);
			String downFileName = exportService.xlsDataExp(downFileDir, clazz, getRequestParams(), m);
			return new File(downFileName);
			
		} catch(Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("数据导出失败：%s", e.getMessage());
			}
			return null;
		}
	}
	
}
