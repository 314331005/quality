package cn.com.jashon.export.format;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 数据导出数值字段格式化类，配合ExportFmt注解使用
 * @author 	dongbolv
 * @date	2014-09-29
 */
@IocBean(name="NumberFmt")
public class NumberFmt extends Formator {
	
	private static final long serialVersionUID = -3440568799290300339L;
	
	private final Map<String, NumberFormat> values = new LinkedHashMap<String, NumberFormat>();

	@Override
	public String format(Object value, String pattern) {
		NumberFormat df = null;
		if(values.containsKey(pattern)) {
			df = values.get(pattern);
		} else {
			df = new DecimalFormat(pattern);
			values.put(pattern, df);
		}
		
		return df.format(value);
	}

}
