package cn.com.jashon.system.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

import cn.com.jashon.core.utils.Constant;
import cn.com.jashon.system.domain.SysCode;
import cn.com.jashon.system.domain.SysDept;

/**
 * 系统编码表初始化操作帮助类
 * @author 	dongbolv
 * @date	2014-09-27
 */
public class Codes {
	
	private static Dao dao;
	
	private static final Map<String, Map<String, String>> codesMap = new LinkedHashMap<String, Map<String, String>>();
	
	/**
	 * 系统初始化时从数据库中加载码表数据
	 * @param sc
	 * @param dao
	 * @return
	 */
	@SuppressWarnings("serial")
	public static final Map<String, Map<String, String>> getSysCodesMap(ServletContext sc, final Dao dao) {
		Codes.dao = dao;
		//系统编码数据
		List<SysCode> codes = dao.query(SysCode.class, Cnd.where("status", "=", 1).asc("type").asc("seqno"));
		if(!Lang.isEmpty(codes)) {
			Map<String, String> detailsMap = null;
			String type = null;
			for(final SysCode c : codes) {
				type = c.getType();
				if(codesMap.containsKey(type)) {
					codesMap.get(type).put(c.getCode(), c.getName());
				} else {
					detailsMap = new LinkedHashMap<String, String>(){{
						put(c.getCode(), c.getName());
					}};
					codesMap.put(type, detailsMap);
				}
			}
		}
		
		sc.setAttribute(Constant.SC_CODE_NAME, codesMap);
		
		//部门编码数据
		List<SysDept> depts = dao.query(SysDept.class, Cnd.where("status", "=", 1).asc("level").asc("seqno"));
		for(SysDept dept : depts) {
			update(Constant.SC_CODE_TYPE_DEPT, dept.getCode(), dept.getName());
		}
		
		return codesMap;
	}
	
	public static final Map<String, String> get(String type) {
		if(Strings.isBlank(type)) {
			return null;
		}
		
		return codesMap.containsKey(type) ? codesMap.get(type) : null;
	}
	
	public static final String get(String type, String code) {
		if(Strings.isBlank(type) || Strings.isBlank(code)) {
			return "".intern();
		}
		
		Map<String, String> values = codesMap.containsKey(type) ? codesMap.get(type) : null;
		return !Lang.isEmpty(values) ? Strings.sBlank(values.get(code), "") : "".intern();
	}
	
	/**
	 * 加载同一类别的码表数据
	 * @param type
	 */
	public static final void reload(String type) {
		if(dao != null && !Strings.isBlank(type)) {
			List<SysCode> codes = dao.query(SysCode.class, 
					Cnd.where("status", "=", 1).and("type", "=", type).asc("seqno"));
			if(!Lang.isEmpty(codes)) {
				Map<String, String> detailsMap = new LinkedHashMap<String, String>();
				for(final SysCode c : codes) {
					detailsMap.put(c.getCode(), c.getName());
				}
				codesMap.put(type, detailsMap);
			}
		}
	}
	
	/**
	 * 修改码表数据后无法判定其顺序和等信息，直接从数据库加载同类别数据
	 * @see method reload
	 * @param type
	 * @param code
	 * @param name
	 */
	@SuppressWarnings("serial")
	public static final void update(String type, final String code, final String name) {
		if(codesMap.containsKey(type)) {
			codesMap.get(type).put(code, name);
		} else {
			Map<String, String> values = new LinkedHashMap<String, String>(){{
				put(code, name);
			}};
			codesMap.put(type, values);
		}
	}
	
	/**
	 * 直接加载同一类别的所有数据
	 * @param type
	 * @param values
	 */
	public static final void update(String type, Map<String, String> values) {
		codesMap.put(type, values);
	}
	
	public static final void delete(String type) {
		if(codesMap.containsKey(type)) {
			codesMap.remove(type);
		}
	}
	
	public static final void delete(String type, String code) {
		if(codesMap.containsKey(type)) {
			Map<String, String> values = codesMap.get(type);
			if(!Lang.isEmpty(values) && values.containsKey(code)) {
				values.remove(code);
			} 
		}
	}
	
}
