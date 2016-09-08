package cn.com.jashon.export.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.resource.Scans;

import cn.com.jashon.MainModule;
import cn.com.jashon.core.utils.Constant;
import cn.com.jashon.core.utils.InvokeUtil;
import cn.com.jashon.export.annotation.Tag;

/**
 * 文件导出辅助类，用于初始化时扫描文件导出标识类
 * @author 	dongbolv
 * @date	2014-09-29
 */
@SuppressWarnings("unchecked")
public class Tags {
	
	/**
	 * 扫描所有标识为导出的实体
	 * 说明：默认扫描 MainModule 类的 IocBy注解的 第4个参数
	 * @param sc
	 */
	public static void scan(ServletContext sc) {
		IocBy iocBy = InvokeUtil.getAnnotation(MainModule.class, IocBy.class);
		String[] args = iocBy.args();
		if(args != null && args.length >= 4) {
			scan(sc, args[3]); 
		}
	}
	
	/**
	 * 扫描所有标识为导出的实体
	 * @param sc
	 * @param scanPackage
	 */
	public static void scan(ServletContext sc, String scanPackage) {
		List<Class<?>> domains = Scans.me().scanPackage(scanPackage);
		
		Map<String, Class<?>> values = new LinkedHashMap<String, Class<?>>();
		
		Tag anno = null;
		for(Class<?> clazz : domains) {
			anno = clazz.getAnnotation(Tag.class);
			if(anno != null) {
				values.put(anno.value(), clazz);
			}
		}
		
		sc.setAttribute(Constant.SC_TAG_NAME, values);
	}
	
	/**
	 * 获取导出标识对应的实体类
	 * @param sc
	 * @param tag	实体导出标识
	 * @return
	 */
	public static Class<?> get(ServletContext sc, String tag) {
		Map<String, Class<?>> values = (Map<String, Class<?>>)sc.getAttribute(Constant.SC_TAG_NAME);
		return values.containsKey(tag) ? values.get(tag) : null;
	}
	
	/**
	 * 添加实体导出标识
	 * @param sc
	 * @param tag	实体导出标识
	 * @param clazz 实体类
	 */
	public static void add(ServletContext sc, String tag, Class<?> clazz) {
		Map<String, Class<?>> values = (Map<String, Class<?>>)sc.getAttribute(Constant.SC_TAG_NAME);
		values.put(tag, clazz);
	} 

}
