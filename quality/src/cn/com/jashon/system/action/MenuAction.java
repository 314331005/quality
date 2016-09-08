package cn.com.jashon.system.action;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.com.jashon.core.action.BaseAction;
import cn.com.jashon.core.annotation.ActionDesc;
import cn.com.jashon.core.utils.DwzUtil;
import cn.com.jashon.system.domain.SysMenu;
import cn.com.jashon.system.domain.SysUser;
import cn.com.jashon.system.service.ISystemService;

@IocBean
@At("/system/menu")
@ActionDesc("菜单信息")
public class MenuAction extends BaseAction {
	
	private static final Log log = Logs.get();
	
	@Inject
	private ISystemService systemService;
	
	@At("/list")
	@Ok("jsp:/WEB-INF/jspx/system/menu-list.jsp")
	public Object list(HttpServletRequest req) {
		final String jsonString = systemService.getTreeJsonString(SysMenu.class);
		req.setAttribute("jsonString", jsonString);
		return jsonString;
	}
	
	@At("/add/?")
	@Ok("jsp:/WEB-INF/jspx/system/menu-edit.jsp")
	public Object add(int level, HttpServletRequest req) {
		SysMenu m = systemService.genSysEntryCode(SysMenu.class, "",level);
		req.setAttribute("m", m);
		return m;
	}
	
	@At("/edit/?")
	@Ok("jsp:/WEB-INF/jspx/system/menu-edit.jsp")
	public Object edit(String id, HttpServletRequest req) {
		SysMenu m = systemService.fetch(SysMenu.class, id, true);
		req.setAttribute("m", m);
		return m;
	}
	
	@At("/save")
	public Object save(@Param("::m.") SysMenu m, String tid) {
		try {
			if(m == null) throw new Exception("未找到对应的菜单信息!");
			
			if(systemService.checkUnique(SysMenu.class, "code", m.getCode(), m.getId())) {
				throw new Exception("编码已存在!".intern());
			}

			m = systemService.update(m);
			
			SysUser user = getSessionSysUser();
			final String treeHTML = systemService.buildDWZMenuTreeHTML(user, getContextPath());
			setSessionUserMenu(user, treeHTML);
			
			return DwzUtil.reloadCurrPage("菜单信息保存成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("菜单信息保存失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("菜单信息保存失败: " + e.getMessage());
		}
	}
	
	@At("/save/next/before/?/?")
	@Ok("jsp:/WEB-INF/jspx/system/menu-edit.jsp")
	public Object saveChildBefore(String pid, int level, String tid, HttpServletRequest req) {
		SysMenu m = systemService.genSysEntryCode(SysMenu.class, pid, level);
		m.setPid(pid);
		m.setLevel(level + 1);
		
		req.setAttribute("m", m);
		return m;
	}
	
	@At("/save/next")
	public Object saveChild(@Param("::m.") SysMenu m, String tid) {
		try {
			if(m != null && m.getLevel() >= 2) {
				throw new RuntimeException("暂时只支持添加两级菜单!");
			}
			systemService.saveNextLevel(SysMenu.class, m);
			
			SysUser user = getSessionSysUser();
			final String treeHTML = systemService.buildDWZMenuTreeHTML(user, getContextPath());
			setSessionUserMenu(user, treeHTML);
			
			return DwzUtil.reloadCurrPage("下级菜单信息保存成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("下级菜单信息保存失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("下级菜单信息保存失败: " + e.getMessage());
		}
	}
	
	@At("/del/?")
	public Object del(String id, String tid) {
		try {
			if(id == null) throw new Exception("未找到对应的菜单信息!");
			SysMenu d = systemService.fetch(SysMenu.class, id, false);
			if(d == null) throw new Exception("未找到对应的菜单信息!");
			
			systemService.deleteSelfAndChilds(SysMenu.class, id);
			
			SysUser user = getSessionSysUser();
			final String treeHTML = systemService.buildDWZMenuTreeHTML(user, getContextPath());
			setSessionUserMenu(user, treeHTML);
			
			return DwzUtil.reloadCurrPage("菜单信息删除成功", tid, id);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("菜单信息删除失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("菜单信息删除失败: " + e.getMessage());
		}
	}
	
}
