package cn.com.jashon.system.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.com.jashon.core.action.BaseAction;
import cn.com.jashon.core.query.OrderType;
import cn.com.jashon.core.utils.DwzUtil;
import cn.com.jashon.core.utils.InvokeUtil;
import cn.com.jashon.core.utils.StringUtil;
import cn.com.jashon.system.service.ISystemService;

/**
 * 功能：同级别排序
 * @author 	dongbolv
 * @date	2014-09-17
 */
@IocBean
@At("/system/sort")
@SuppressWarnings("serial")
public class SortAction extends BaseAction {
	
	private final Log log = Logs.getLog(this.getClass());
	
	@Inject
	private ISystemService systemService;
	
	private static final String PACKAGE_PREFIX = "cn.com.jashon.system.domain.";
	
	/**
	 * 同级排序(DWZ弹出dialog方式)
	 * @param className
	 * @param pPropName
	 * @param id
	 * @param otherPropParams	其它过滤条件，例：&otherPropParams=Name:zhangsan,appId:lisi
	 * @return
	 * @throws Exception
	 */
	@At("/before/?/?/?")
	@Ok("jsp:/WEB-INF/jspx/system/sort-before.jsp")
	public Object treeSameLevelSortBefore(String className, final String pPropName, String id, String otherPropParams) throws Exception {
		List<?> objs = null;
		try {
			if(!Strings.isBlank(id)) {
				final String classFullName = PACKAGE_PREFIX.concat(className);
				Class<?> clazz = Class.forName(classFullName);
				Object object = systemService.fetch(clazz, id, false);
				if(object == null) {
					throw new Exception("未获取到数据,实体：".concat(className).concat(",ID：").concat(id));
				}
				
				final String pPropValue = (String)InvokeUtil.getValue(object, pPropName);
				
				Map<String, String> params = new LinkedHashMap<String, String>(){{
					put("ft_EQ_S_".concat(pPropName), pPropValue);
				}};
				
				if(!Strings.isBlank(otherPropParams) && otherPropParams.indexOf(":") != -1) {
					String[] otherPropsArr = { otherPropParams };
					if(otherPropParams.indexOf(",") != -1) {
						otherPropsArr = Strings.splitIgnoreBlank(otherPropParams.trim(), ",");
					}
					
					String propName = null;	//实体属性
					String propValue = null;//实体属性值
					for(String otherProp : otherPropsArr) {
						if(otherProp == null || otherProp.indexOf(":") == -1) {
							continue;
						}
						propName = StringUtil.substringBefore(otherProp, ":").trim();
						propValue = StringUtil.substringAfterLast(otherProp, ":").trim();
						if(!Strings.isBlank(propName) && InvokeUtil.containsProperty(object, propName)) {
							params.put("ft_EQ_S_".concat(propName), propValue);
						}
					}
				}
				
				objs = systemService.findList(clazz, params, "seqno", OrderType.ASC);
			}
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("同级项排序失败：%s", e.getMessage());
			}
		}

		getRequest().setAttribute("objs", objs);
		getRequest().setAttribute("className", className);
		return objs;
	}

	@At("/update/?")
	public Object treeSameLevelSort(String className, String sortedIds, String tid) throws Exception {
		try{
			if(Strings.isBlank(sortedIds)) {
				throw new Exception("未找到需要排序的数据");
			}
			final String classFullName = PACKAGE_PREFIX.concat(className);
			Class<?> clazz = Class.forName(classFullName);
			systemService.updateTreeSameLevelSeqno(clazz, sortedIds);
			return DwzUtil.reloadTabAndCloseCurrent("同级项排序成功", tid);
		}catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("同级项排序失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("同级项排序失败: " + e.getMessage());
		}
	}
	
}