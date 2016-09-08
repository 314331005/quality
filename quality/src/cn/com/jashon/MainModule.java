package cn.com.jashon;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.Views;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import cn.com.jashon.core.filter.LoginFilter;
import cn.com.jashon.core.filter.WordFilter;
import cn.com.jashon.views.ExportMaker;

@Ok("json")
@Fail("json")
@Views({ExportMaker.class})
@Modules(scanPackage = true)
@IocBy(type = ComboIocProvider.class, args = {
		"*org.nutz.ioc.loader.json.JsonLoader", "ioc/",
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
		"cn.com.jashon" })
@Filters({
	@By(type=WordFilter.class, args={"^/(exp|imp)/"}),
	@By(type=LoginFilter.class, args={"^/index/list", "_SYS_USER_", "/login.jsp"})
})
@SetupBy(value = MainSetup.class)
public class MainModule {

}
