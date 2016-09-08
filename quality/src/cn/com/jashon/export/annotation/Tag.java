package cn.com.jashon.export.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据导入导出标识注解类
 * 说明：该类声明在需要导入导出数据的实体类上
 * @author 	dongbolv
 * @date	2014-09-29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Tag {

	/**
	 * 作为确定导出哪个实体数据的标识
	 * 说明：为提升系统安全性，避免页面中直接填写类路径
	 */
	String value();
	
}
