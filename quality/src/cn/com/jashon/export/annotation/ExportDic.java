package cn.com.jashon.export.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface ExportDic {
	
	/**
	 * 默认取配置文件中的编码信息
	 */
	String sc() default "dics";
	
	/**
	 * 编码分组类别
	 */
	String type();

}
