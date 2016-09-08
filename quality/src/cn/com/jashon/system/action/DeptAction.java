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
import cn.com.jashon.core.utils.Constant;
import cn.com.jashon.core.utils.DwzUtil;
import cn.com.jashon.system.domain.SysDept;
import cn.com.jashon.system.service.ISystemService;
import cn.com.jashon.system.utils.Codes;

@IocBean
@At("/system/dept")
@ActionDesc("部门信息")
public class DeptAction extends BaseAction {
	
	private static final Log log = Logs.get();
	
	@Inject
	private ISystemService systemService;
	
	@At("/list")
	@Ok("jsp:/WEB-INF/jspx/system/dept-list.jsp")
	public Object list(HttpServletRequest req) {
		final String jsonString = systemService.getTreeJsonString(SysDept.class);
		req.setAttribute("jsonString", jsonString);
		return jsonString;
	}
	
	@At("/add/?")
	@Ok("jsp:/WEB-INF/jspx/system/dept-edit.jsp")
	public Object add(int level, HttpServletRequest req) {
		SysDept d = systemService.genSysEntryCode(SysDept.class, "", level);
		req.setAttribute("d", d);
		return d;
	}
	
	@At("/edit/?")
	@Ok("jsp:/WEB-INF/jspx/system/dept-edit.jsp")
	public Object edit(String id, HttpServletRequest req) {
		SysDept d = systemService.fetch(SysDept.class, id, true);
		req.setAttribute("d", d);
		return d;
	}
	
	@At("/save")
	public Object save(@Param("::d.") SysDept d, String tid) {
		try {
			if(d == null) throw new Exception("未找到对应的部门信息!");
			
			if(systemService.checkUnique(SysDept.class, "code", d.getCode(), d.getId())) {
				throw new Exception("编码已存在!".intern());
			}

			d = systemService.update(d);
			Codes.update(Constant.SC_CODE_TYPE_DEPT, d.getCode(), d.getName());
			
			return DwzUtil.reloadCurrPage("部门信息保存成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("部门信息保存失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("部门信息保存失败: " + e.getMessage());
		}
	}
	
	@At("/save/next/before/?/?")
	@Ok("jsp:/WEB-INF/jspx/system/dept-edit.jsp")
	public Object saveChildBefore(String pid, int level, String tid, HttpServletRequest req) {
		SysDept d = systemService.genSysEntryCode(SysDept.class, pid, level);
		d.setPid(pid);
		d.setLevel(level + 1);
		
		req.setAttribute("d", d);
		return d;
	}
	
	@At("/save/next")
	public Object saveChild(@Param("::d.") SysDept d, String tid) {
		try {
			systemService.saveNextLevel(SysDept.class, d);
			Codes.update(Constant.SC_CODE_TYPE_DEPT, d.getCode(), d.getName());
			
			return DwzUtil.reloadCurrPage("下级部门信息保存成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.error("下级部门信息保存失败：" + e.getMessage());
			}
			return DwzUtil.stopPageError("下级部门信息保存失败: " + e.getMessage());
		}
	}
	
	@At("/del/?")
	public Object del(String id, String tid) {
		try {
			if(id == null) throw new Exception("未找到对应的部门信息!");
			SysDept d = systemService.fetch(SysDept.class, id, false);
			if(d == null) throw new Exception("未找到对应的部门信息!");
			
			systemService.deleteDept(id);
			
			return DwzUtil.reloadCurrPage("部门信息删除成功", tid, id);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("部门信息删除失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("部门信息删除失败: " + e.getMessage());
		}
	}
	
	@At("/view/?")
	@Ok("jsp:/WEB-INF/jspx/system/dept-view.jsp")
	public Object view(String id, HttpServletRequest req) {
		try {
			if(id == null) throw new Exception("未找到对应的部门信息!");
			SysDept d = systemService.fetch(SysDept.class, id, false);
			if(d == null) throw new Exception("未找到对应的部门信息!");
			req.setAttribute("d", d);
			
			//TODO 获取部门角色列表
			//TODO 获取部门人员列表
			
			return d;
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("部门信息查看失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("部门信息查看失败: " + e.getMessage());
		}
	}
	
}
