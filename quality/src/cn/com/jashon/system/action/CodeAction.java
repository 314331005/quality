package cn.com.jashon.system.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import cn.com.jashon.system.domain.SysCode;
import cn.com.jashon.system.service.ISystemService;
import cn.com.jashon.system.utils.Codes;

@IocBean
@At("/system/code")
@ActionDesc("系统编码信息")
public class CodeAction extends BaseAction {
	
	private static final Log log = Logs.get();
	
	@Inject
	private ISystemService systemService;
	
	@At("/list")
	@Ok("jsp:/WEB-INF/jspx/system/code-list.jsp")
	public Object list(String type, HttpServletRequest req) {
		Page<SysCode> page = getPage("page.");
		page.addOrder("type", OrderType.ASC);
		page.addOrder("seqno", OrderType.ASC);
		
		page = systemService.findPage(SysCode.class, page, null, getRequestParams());
		req.setAttribute("page", page);
		
		return page;
	}
	
	@At("/edit/?")
	@Ok("jsp:/WEB-INF/jspx/system/code-edit.jsp")
	public Object edit(String id, HttpServletRequest req) {
		SysCode c = systemService.fetch(SysCode.class, id, true);
		req.setAttribute("c", c);
		return c;
	}
	
	@At("/save")
	public Object save(@Param("::c.") SysCode c, String tid) {
		try {
			if(c == null) throw new Exception("未找到对应的编码信息!");
			if(!Strings.isBlank(c.getId()) && c.getStatus() == 1) {
				throw new Exception("当前编码已为固化状态，不能修改!");
			}
			
			if(systemService.checkCodeUnique(SysCode.class, c.getType(), c.getCode(), c.getId())) {
				throw new Exception("编码已存在!".intern());
			}

			c = systemService.update(c);
			return DwzUtil.reloadTabAndCloseCurrent("编码信息保存成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("编码信息保存失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("编码信息保存失败: " + e.getMessage());
		}
	}
	
	@At("/fix/?")
	public Object fix(String id, String tid) {
		try {
			if(id == null) throw new Exception("未找到对应的编码信息!");
			SysCode c = systemService.fetch(SysCode.class, id, false);
			if(c == null) throw new Exception("未找到对应的编码信息!");
			
			systemService.updateStatus(SysCode.class, id, 1);
			Codes.reload(c.getType());//重新加载系统编码表信息
			
			return DwzUtil.reloadCurrPage("编码信息固化操作成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("编码信息固化操作失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("编码信息固化操作失败: " + e.getMessage());
		}
	}
	
	@At("/fix")
	public Object fixAll(HttpServletRequest req, String[] cids, String tid) {
		int i = 0;
		try {
			if(Lang.isEmptyArray(cids)) {
				throw new Exception("请至少勾选一条记录!");
			}
			
			Map<String, Integer> typesMap = systemService.updateCodeStatus(cids, 1);
			for(Map.Entry<String, Integer> m : typesMap.entrySet()) {
				i += m.getValue();
				Codes.reload(m.getKey());//重新加载系统编码表信息
			}
			
			return DwzUtil.reloadCurrPage("编码信息批量固化操作，成功处理 "+i+"/"+cids.length+" 条!", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("编码信息批量固化失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("编码信息批量固化操作，成功处理 "+i+"/"+cids.length+" 条!" + e.getMessage());
		}
	}
	
	@At("/del/?")
	public Object del(String id, String tid) {
		try {
			if(Strings.isBlank(id)) throw new Exception("未找到对应的编码信息!");
			
			SysCode c = systemService.fetch(SysCode.class, id, false);
			if(c == null) throw new Exception("未找到对应的编码信息!");
			
			if(c.getStatus() == 1) {
				throw new Exception("当前编码已为固化状态，不能删除!");
			}
			
			systemService.delete(c);
			return DwzUtil.reloadCurrPage("编码信息删除成功", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("编码信息删除失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("编码信息删除失败: " + e.getMessage());
		}
	}
	
	@At("/del")
	public Object deleteAll(HttpServletRequest req, String[] cids, String tid) {
		int i = 0;
		try {
			if(Lang.isEmptyArray(cids)) {
				throw new Exception("请至少勾选一条记录!");
			}
			
			i = systemService.deleteCodes(cids);
			return DwzUtil.reloadCurrPage("编码信息批量删除操作，成功处理 "+i+"/"+cids.length+" 条!", tid);
		} catch (Exception e) {
			if(log.isErrorEnabled()) {
				log.errorf("编码信息批量删除失败：%s", e.getMessage());
			}
			return DwzUtil.stopPageError("编码信息批量删除操作，成功处理 "+i+"/"+cids.length+" 条!" + e.getMessage());
		}
	}
	
}
