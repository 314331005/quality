package cn.com.jashon.system.action;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.com.jashon.core.action.BaseAction;
import cn.com.jashon.core.annotation.ActionDesc;
import cn.com.jashon.core.query.OrderType;
import cn.com.jashon.core.query.Page;
import cn.com.jashon.core.utils.DwzUtil;
import cn.com.jashon.core.utils.Idcard;
import cn.com.jashon.core.utils.PropsUtil;
import cn.com.jashon.system.domain.SysDept;
import cn.com.jashon.system.domain.SysUser;
import cn.com.jashon.system.service.ISystemService;
import cn.com.jashon.system.utils.PassUtil;

@IocBean
@At("/system/user")
@ActionDesc("用户信息")
@SuppressWarnings("serial")
public class UserAction extends BaseAction {

	private static final Log log = Logs.get();
	
	@Inject
	private ISystemService systemService;
	
	@At("/list")
	@Ok("jsp:/WEB-INF/jspx/system/user-list.jsp")
	public Object list(HttpServletRequest req) {
		Page<SysUser> page = getPage("page.", "id", OrderType.DESC);
		Map<String, String> params = getRequestParams();
		params.put("ft_LIKER_S_deptCode", getSessionUDeptCode());
		page = systemService.findPage(SysUser.class, page, Cnd.where("type", "<>", 1), params);
		req.setAttribute("page", page);
		
		return page;
	}
	
	@At("/edit/?")
	@Ok("jsp:/WEB-INF/jspx/system/user-edit.jsp")
	public Object edit(String id, HttpServletRequest req) {
		SysUser u = systemService.findBaseAndLinks(SysUser.class, id, "roles", "id");
		
		Map<String, String> values = new LinkedHashMap<String, String>(){{
			put("ft_EQ_I_status", "1");
		}};
		String jsonString = systemService.getTreeJsonString(SysDept.class, values);
		req.setAttribute("u", u != null ? u : new SysUser());
		req.setAttribute("jsonString", jsonString);
		return u;
	}
	
	@At("/save")
	public Object save(@Param("::u.") SysUser u, String oldPass, String roleIds, String tid) {
		try {
			if(u == null) throw new Exception("未找到对应的用户信息!");
			if(Strings.isBlank(u.getDeptId())) {
				throw new Exception("未找到用户的所属部门信息!");
			}
			
			if(systemService.checkUnique(SysUser.class, "loginName", u.getLoginName(), u.getId())) {
				throw new Exception("当前登录名已存在!".intern());
			}
			
			final String idcard = u.getIdcard();
			if(!Strings.isBlank(idcard)) {
				if(!Idcard.isValidatedAllIdcard(idcard)) {
					throw new Exception("无效的身份证");
				}
			}

			systemService.updateUserAndRoles(u, oldPass, roleIds);
			
			return DwzUtil.reloadTabAndCloseCurrent("用户信息保存成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("用户信息保存失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("用户信息保存失败: " + e.getMessage());
		}
	}
	
	@At("/enable/?/?")
	public Object enable(String id, int status, String tid) {
		try {
			if(id == null) throw new Exception("未找到对应的用户信息!");
			SysUser u = systemService.fetch(SysUser.class, id, false);
			if(u == null) throw new Exception("未找到对应的用户信息!");
			
			systemService.updateStatus(SysUser.class, id, status);
			
			return DwzUtil.reloadCurrPage("用户信息" + (status == 0 ? "禁用" : "启用") + "操作成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("用户信息" + (status == 0 ? "禁用" : "启用") + "操作失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("用户信息" + (status == 0 ? "禁用" : "启用") + "操作失败: " + e.getMessage());
		}
	}
	
	@At("/passwd/init/?")
	public Object initLoginPass(String id, String tid) {
		try {
			SysUser user = getSessionSysUser();
			if(user == null) throw new Exception("请先登录本系统!");
			
			SysUser target = systemService.fetch(SysUser.class, id, false);
			if(target == null) {// || !user.getId().equals(target.getId())
				throw new Exception("未找到对应的用户信息!");
			}
			
			target.setLoginPass(PropsUtil.getActiveString("passwd.init"));
			target = systemService.initPass(target);
			
			return DwzUtil.reloadCurrPage("密码初始化成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("密码初始化失败：%s",  e.getMessage());
			}
			return DwzUtil.stopPageError("密码初始化失败: " + e.getMessage());
		}
	}
	
	@At("/passwd/before")
	@Ok("jsp:/WEB-INF/jspx/system/user-passwd.jsp")
	public Object updateLoginPassBefore() {
		return null;
	}
	
	@At("/passwd/update")
	public Object updateLoginPass(String oldPass, String loginPass, String confirmPass) {
		try {
			SysUser user = getSessionSysUser();
			if(user == null) throw new Exception("请先登录本系统!");
			
			if(Strings.isBlank(oldPass) 
					|| Strings.isBlank(loginPass) 
					|| Strings.isBlank(confirmPass)) {
				throw new Exception("请填写原密码、新密码和确认密码!");
			}
			
			String realOldPass = PassUtil.genUserPass(user.getLoginName(), oldPass);
			if(!realOldPass.equals(user.getLoginPass())) {
				throw new Exception("原密码不正确!");
			}
			
			if(!loginPass.equals(confirmPass)) {
				throw new Exception("两次输入的密码不一致!");
			}

			user.setLoginPass(loginPass);
			user = systemService.initPass(user);
			if(user != null) { //移除Session用户信息
				removeSessionUser();
			}
			
			return DwzUtil.reloadTabAndCloseCurrent("密码修改成功，请重新登录!", "");
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("密码初始化失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("密码初始化失败: " + e.getMessage());
		}
	}
	
	@At("/del/?")
	public Object del(String id, String tid) {
		try {
			if(id == null) throw new Exception("未找到对应的用户信息!");
			SysUser d = systemService.fetch(SysUser.class, id, false);
			if(d == null) throw new Exception("未找到对应的用户信息!");
			
			systemService.deleteBaseAndLinks(d, "roles");
			
			return DwzUtil.reloadCurrPage("用户信息删除成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("用户信息删除失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("用户信息删除失败: " + e.getMessage());
		}
	}
	
	@At("/del")
	public Object deleteAll(HttpServletRequest req, String[] dids, String tid) {
		int i = 0;
		try {
			if(Lang.isEmptyArray(dids)) 
				throw new Exception("请至少勾选一条记录!");
			
			for(String id : dids) {
				if(Strings.isBlank(id)) {
					continue;
				}
				i += systemService.deleteBaseAndLinks(SysUser.class, id, "roles");
			}
			return DwzUtil.reloadCurrPage("用户信息批量删除操作，成功处理 "+i+"/"+dids.length+" 条!", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("用户信息批量删除失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("用户信息批量删除操作，成功处理 "+i+"/"+dids.length+" 条!" + e.getMessage());
		}
	}
	
}
