package cn.com.jashon;

import javax.servlet.ServletContext;

import org.nutz.dao.Dao;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import cn.com.jashon.core.enums.Enums;
import cn.com.jashon.core.utils.Dics;
import cn.com.jashon.export.utils.Tags;
import cn.com.jashon.system.utils.Codes;

public class MainSetup implements Setup {
	
	public void init(NutConfig config) {
		final Dao dao = config.getIoc().get(Dao.class);
		
		/* 创建表
		List<Class<?>> domains = Scans.me().scanPackage("cn.com.jashon.core.domain");
		for(Class<?> clazz : domains) {
			if(clazz.getAnnotation(Table.class) != null) {
				dao.create(clazz, false);
			}
		}
		*/
		
		// 初始化数据字典
		ServletContext sc = config.getServletContext();
		// 全局回话域中的变量为：dics
		Dics.loadDirectory(sc);
		// 全局回话域中的变量为：codes
		Codes.getSysCodesMap(sc, dao);
		// 数据导入导出实体检测
		Tags.scan(sc);
		// 枚举码表实体检测
		Enums.scan(sc);
	}

	@Override
	public void destroy(NutConfig config) {
	}

}
