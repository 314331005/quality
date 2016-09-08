package cn.com.jashon.export.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 数据导出日期字段格式化类，配合ExportFmt注解使用
 * @author 	dongbolv
 * @date	2014-09-29
 */
@IocBean(name="DateFmt")
public class DateFmt extends Formator {

	private static final long serialVersionUID = -823282091600613023L;
	
	private final Map<String, DateFormat> values = new LinkedHashMap<String, DateFormat>();
	
	@Override
	public String format(Object value, String pattern) {
		DateFormat df = null;
		if(values.containsKey(pattern)) {
			df = values.get(pattern);
		} else {
			df = new SimpleDateFormat(pattern);
			values.put(pattern, df);
		}
		
		return df.format((Date)value);
	}

}
