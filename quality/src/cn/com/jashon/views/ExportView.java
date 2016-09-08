package cn.com.jashon.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Files;
import org.nutz.mvc.view.RawView;

/**
 * 自定义文件导出视图，用于清理数据导出时生成的临时文件
 * @author 	dongbolv
 * @date	2014-09-29
 */
public class ExportView extends RawView {

	public ExportView(String contentType) {
		super(contentType);
	}

	@Override
	public void render(HttpServletRequest req, HttpServletResponse resp,
			Object obj) throws Throwable {
		super.render(req, resp, obj);
		
		//文件导出后清理生成的临时文件
		if(obj instanceof java.io.File) {
			java.io.File file = (java.io.File)obj;
			if(file == null || !file.isFile() || !file.exists()) {
				return;
			}
			
			Files.deleteDir(file.getParentFile());
		}
	}

}
