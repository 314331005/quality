package cn.com.jashon.export.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.com.jashon.export.format.Formator;

/**
 * 数据导出格式化
 * @author 	dongbolv
 * @date	2014-09-29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface ExportFmt {
	
	Class<? extends Formator> formator(); 

	String pattern(); 
	
}
