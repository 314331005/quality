package cn.com.jashon.views;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.View;
import org.nutz.mvc.ViewMaker;

/**
 * 自定义文件导出视图
 * @author 	dongbolv
 * @date	2014-09-29
 */
public class ExportMaker implements ViewMaker {

	@Override
	public View make(Ioc ioc, String type, String value) {
		if("exp".equals(type)) {
			return new ExportView(value);
		}
		return null;
	}

}
