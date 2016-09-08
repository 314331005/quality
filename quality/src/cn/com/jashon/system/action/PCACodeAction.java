package cn.com.jashon.system.action;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.com.jashon.core.action.BaseAction;
import cn.com.jashon.core.annotation.ActionDesc;
import cn.com.jashon.core.utils.Constant;
import cn.com.jashon.system.utils.Codes;

@IocBean
@At("/system/code")
@ActionDesc("省市县编码信息")
public class PCACodeAction extends BaseAction {
	
	/**
	 * 省市县三级联动
	 */
	@At("/pca/p")
	@Ok("raw")
	public Object province() {
		final String json = Codes.get(Constant.SC_CODE_PCA_P, Constant.SC_CODE_PCA_P60);
		return Strings.isBlank(json) ? "[]".intern() : json;
	}
	
	/**
	 * 省市县三级联动（市）
	 */
	@At("/pca/c/?")
	@Ok("raw")
	public Object city(String pcode) {
		final String json = Codes.get(Constant.SC_CODE_PCA_PC, pcode);
		return Strings.isBlank(json) ? "[]".intern() : json;
	}
	
	@At("/pca/c")
	@Ok("raw")
	public Object city2() {
		return city(null);
	}
	
	/**
	 * 省市县三级联动（区县）
	 */
	@At("/pca/a/?")
	@Ok("raw")
	public Object area(String pcode) {
		final String json = Codes.get(Constant.SC_CODE_PCA_CA, pcode);
		return Strings.isBlank(json) ? "[]".intern() : json;
	}
	
	@At("/pca/a")
	@Ok("raw")
	public Object area2() {
		return area(null);
	}
	
}