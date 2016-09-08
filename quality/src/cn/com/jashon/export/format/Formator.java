package cn.com.jashon.export.format;

import org.nutz.mvc.Mvcs;

/**
 * 数据导出格式化抽象类，配合ExportFmt注解使用
 * @author 	dongbolv
 * @date	2014-09-29
 */
public abstract class Formator {

	public abstract String format(Object value, String pattern);
	
	public static String format(Class<? extends Formator> clazz, String pattern, Object value) {
		try {
			Formator obj = Mvcs.getIoc().get(clazz, clazz.getSimpleName());
			String fieldValue = obj.format(value, pattern);
			return fieldValue;
		} catch (Exception e) {
			return "".intern();
		}
	}
	
}
