package cn.com.jashon.system.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

import cn.com.jashon.core.action.BaseAction;
import cn.com.jashon.core.utils.DwzUtil;
import cn.com.jashon.core.utils.StringUtil;
import cn.com.jashon.system.domain.SysUser;
import cn.com.jashon.system.service.ISystemService;

@IocBean
public class LoginAction extends BaseAction {

	@Inject
	private ISystemService systemService;
	
	@At("/login")
	@Ok("jsp:/login.jsp")
	public Object login(String loginName, String loginPass, String checkCode) {
		String errorMessage = null;
		try {
			if(Strings.isBlank(loginName)) {
				throw new Exception("请输入登录名");
			} 
			if(Strings.isBlank(loginPass)) {
				throw new Exception("请输入登录密码");
			} 

			loginProcess(loginName, loginPass);
			
		} catch(Exception e) {
			errorMessage = Strings.sBlank(e.getMessage(), "未知错误");
		}
		
		if(errorMessage != null) {
			Mvcs.getReq().setAttribute("errorMessage", errorMessage);
			return new JspView("/login.jsp");
		} else {
			return new ServerRedirectView("/default.jsp");
		}
	}
	
	@At("/login/ajax")
	public Object ajaxLogin(String loginName, String loginPass, String checkCode) {
		try {
			if(Strings.isBlank(loginName)) {
				throw new Exception("请输入登录名");
			} 
			if(Strings.isBlank(loginPass)) {
				throw new Exception("请输入登录密码");
			} 
			
			String maxTid = loginProcess(loginName, loginPass);
			
			return DwzUtil.reloadTabAndCloseCurrent("登录成功!", "", maxTid);
		} catch(Exception e) {
			return DwzUtil.stopPageError("登录失败：" + e.getMessage());
		}
	}
	
	@At("/login/after/user")
	@Ok("jsp:/login-after-user.jsp")
	public void ajaxLoginUser() {
	}
	
	@At("/login/after/menu")
	@Ok("jsp:/login-after-menu.jsp")
	public void ajaxLoginMenu() {
	}
	
	@At("/logout")
	public Object logout() {
		removeSessionUser();
		return new ServerRedirectView("/login.jsp");
	}

	private String loginProcess(String loginName, String loginPass) throws Exception {
		SysUser user = systemService.loginUser(loginName, loginPass);
		if(user == null || Strings.isBlank(user.getId())) {
			throw new Exception("登录名和密码不匹配");
		}
		if(user.getStatus() == 0) {
			throw new Exception("您的账号已被锁定");
		}
		
		final String treeHTML = systemService.buildDWZMenuTreeHTML(user, getContextPath());
		setSessionUserMenu(user, treeHTML);
		
		return fetchMaxTid(treeHTML);
	}
	
	private String fetchMaxTid(String str) {
		Pattern p = Pattern.compile("page_[0-9]{1,}");
		Matcher m = p.matcher(str);
		
		String rel = null;
		while(m.find()) {
			rel = m.group(0);
		}
		
		return Strings.isBlank(rel) ? "" : StringUtil.substringAfterLast(rel, "_");
	}
	
}
