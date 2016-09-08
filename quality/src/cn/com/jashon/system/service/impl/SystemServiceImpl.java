package cn.com.jashon.system.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

import cn.com.jashon.core.domain.TreeNode;
import cn.com.jashon.core.query.OrderType;
import cn.com.jashon.core.service.impl.BaseServiceImpl;
import cn.com.jashon.core.utils.InvokeUtil;
import cn.com.jashon.core.utils.PropsUtil;
import cn.com.jashon.system.domain.SysCode;
import cn.com.jashon.system.domain.SysDept;
import cn.com.jashon.system.domain.SysMenu;
import cn.com.jashon.system.domain.SysRole;
import cn.com.jashon.system.domain.SysUser;
import cn.com.jashon.system.service.ISystemService;
import cn.com.jashon.system.utils.PassUtil;

@SuppressWarnings("serial")
@IocBean(name = "systemService", args = { "refer:dao" })
public class SystemServiceImpl extends BaseServiceImpl implements ISystemService {

	public SystemServiceImpl(Dao dao){
		this.dao = dao;
	}
	
	/**
	 * 用户登录校验
	 * @param loginName
	 * @param loginPass
	 * @return
	 */
	public SysUser loginUser(String loginName, String loginPass) {
		if(Strings.isBlank(loginName) 
				|| Strings.isBlank(loginPass)) {
			return null;
		}
		
		Criteria cri = Cnd.cri();
		cri.where().andEquals("loginName", loginName)
				.andEquals("loginPass", PassUtil.genUserPass(loginName, loginPass));
		SysUser user = fetch(SysUser.class, cri, false);
		return user;
	}
	
	/**
	 * 用户密码修改或初始化
	 * @param loginName
	 * @param loginPass
	 * @return
	 */
	public SysUser initPass(SysUser user) {
		final String newPass = PassUtil.genUserPass(user.getLoginName(), user.getLoginPass());
		user.setLoginPass(newPass);
		user = update(user);
		return user;
	}
	
	public Collection<SysMenu> getUserMenus(SysUser user) {
		Collection<SysMenu> menus = new LinkedHashSet<SysMenu>();
		
		if(user.getType() == 1) {
			Map<String, OrderType> ordersMap = new LinkedHashMap<String, OrderType>(){{
				put("level", OrderType.ASC);
				put("seqno", OrderType.ASC);
			}};
			List<SysMenu> allMenus = findList(SysMenu.class, null, ordersMap);
			if(!Lang.isEmpty(allMenus)) {
				menus.addAll(allMenus);
			}
			return menus;
		} else {
			Collection<SysRole> roles = findLinks(user, "roles", "id").getRoles();
			for(SysRole r : roles) {
				if(r.getStatus() == 1) {
					menus.addAll(findLinks(r, "menus", "id").getMenus());
				}
			}
			//菜单重排序
			List<SysMenu> menuList = new LinkedList<SysMenu>();
			menuList.addAll(menus);
			Collections.sort(menuList, new Comparator<SysMenu>(){
				@Override
				public int compare(SysMenu m1, SysMenu m2) {
					return m1.getLevel() == m2.getLevel() ? (m1.getSeqno() - m2.getSeqno()) : (m1.getLevel() - m2.getLevel());
				}
			});
			return menuList;
		}
	}
	
	public <T> T saveNextLevel(Class<T> clazz, T obj) throws Exception {
		if(obj == null) {
			throw new Exception("未找到对应的信息!");
		}
		
		String id = (String)InvokeUtil.getValue(obj, "id");
		if(Strings.isBlank(id)) {
			throw new RuntimeException("主键值不存在，无法添加下级信息!");
		}
		
		if(checkUnique(clazz, "code", (String)InvokeUtil.getValue(obj, "code"), null)) {
			throw new RuntimeException("编码已存在!".intern());
		}
		
		InvokeUtil.setValue(obj, "id", null);
		InvokeUtil.setValue(obj, "pid", id);
		return update(obj);
	}
	
	public <T> int updateStatus(Class<T> clazz, String id, int status) {
		// 状态修正检测并更新，只有有效或无效两种状态
		return dao.update(clazz, Chain.make("status", status == 0 ? 0 : 1), Cnd.where("id", "=", id));
	}
	
	public <T> int updateCodeStatus(Class<T> clazz, String type, String code, int status) {
		// 状态修正检测并更新，只有有效或无效两种状态
		return dao.update(clazz, Chain.make("status", status == 0 ? 0 : 1), 
					Cnd.where("type", "=", type).and("code", "=", code));
	}
	
	public Map<String, Integer> updateCodeStatus(String[] cids, int status) {
		status = status == 0 ? 0 : 1;
		dao.update(SysCode.class, 
				Chain.make("status", status), 
				Cnd.where("id", "in", cids));
		
		List<SysCode> codes = query(SysCode.class, 
				Cnd.where("id", "=", cids).and("status", "=", status));
		
		Map<String, Integer> typesMap = new LinkedHashMap<String, Integer>();
		String type = null;
		for(SysCode c : codes) {
			type = c.getType();
			if(Strings.isBlank(type)) {
				continue;
			}
			
			if(typesMap.containsKey(type)) {
				typesMap.put(type, typesMap.get(type) + 1);
			} else {
				typesMap.put(type, 1);
			}
		}
		return typesMap;
	}
	
	public int deleteCodes(String[] cids) {
		return delete(SysCode.class, 
				Cnd.where("id", "in", cids).and("status", "=", 1));
	}
	
	public SysUser updateUserAndRoles(SysUser u, String oldPass, String roleIds) {
		if(!Strings.isBlank(roleIds)) {
			String[] rids = roleIds.indexOf(",") != -1 ? Strings.splitIgnoreBlank(roleIds, ",") : new String[]{roleIds};
			List<SysRole> roles = query(SysRole.class, Cnd.where("id", "in", rids));
			u.setRoles(roles);
		} else {
			u.setRoles(null);
		}
		
		//用户密码有变更则重新加密
		if(!u.getLoginPass().equals(oldPass)) {
			u.setLoginPass(PassUtil.genUserPass(u.getLoginName(), u.getLoginPass()));
		}
		
		return updateBaseAndLinks(u, "roles");
	}
	
	public SysRole updateRoleAndMenus(SysRole r, String menuIds) {
		if(!Strings.isBlank(menuIds)) {
			String[] mids = menuIds.indexOf(",") != -1 ? Strings.splitIgnoreBlank(menuIds, ",") : new String[]{menuIds};
			List<SysMenu> menus = query(SysMenu.class, Cnd.where("id", "in", mids));
			r.setMenus(menus);
		} else {
			r.setMenus(null);
		}
		
		return updateBaseAndLinks(r, "menus");
	}
	
	public void deleteDept(final String id) {
		List<String> deptIds = new LinkedList<String>(){{
			add(id);
		}};
		//删除部门及其子部门
		findChildIdsByPid(SysDept.class, deptIds, id);
		//删除部门下的角色及角色对应的菜单
		if(!Lang.isEmpty(deptIds)) {
			for(String deptId : deptIds) {
				List<String> roleIds = findIds(SysRole.class, "deptId", deptId);
				deleteBaseAndLinks(SysRole.class, roleIds, "menus");
			}
		}
		delete(SysDept.class, deptIds);
	}
	
	public <T> List<String> deleteSelfAndChilds(Class<T> clazz, final String id) {
		List<String> ids = new LinkedList<String>(){{
			add(id);
		}};
		findChildIdsByPid(clazz, ids, id);
		int count = delete(clazz, ids);
		
		if(log.isInfoEnabled()) {
			log.infof("Total delete count is [%d]", count);
		}
		return ids;
	}
	
	private <T> void findChildIdsByPid(Class<T> clazz, List<String> ids, String pid) {
		List<String> cids = findIds(clazz, "pid", pid);
		if (!Lang.isEmpty(cids)) {
			ids.addAll(cids);
			for (String cid : cids) {
				findChildIdsByPid(clazz, ids, cid);
			}
		}
	}
	
	/**
	 * 树结构同级数据排序, 根据seqno
	 * @param sortedIds 重新排序后id，多个以逗号分隔
	 */
	@SuppressWarnings("unchecked")
	public void updateTreeSameLevelSeqno(Class<?> clazz, String sortedIds) throws Exception {
		//数量为1个无需排序，直接返回
		if(Strings.isBlank(sortedIds) || sortedIds.indexOf(",") == -1) {
			return;
		}
		
		String[] ids = Strings.splitIgnoreBlank(sortedIds.trim(), ",");
		Object object = null;
		for (int i = 0, len = ids.length; i < len; i++) {
			object = fetch(clazz, ids[i], false);
			if (object != null) {
				InvokeUtil.setValue(object, "seqno", i + 1);
				update(object);
			}
		}

	}
	
	/**
	 * 实体初始化时自动生成编码
	 * 说明：用于系统管理中部门和菜单的编码生成
	 * @author 	dongbolv
	 * @date	2014-12-05
	 */
	public <T> T genSysEntryCode(Class<T> clazz, String pid, int level) {
		String code = "";
		int codeLen = Math.max(PropsUtil.getInteger("sys.code.len"), 2);
		String codePad = Strings.sBlank(PropsUtil.getString("sys.code.pad"), "0");
		
		Criteria cri = Cnd.cri();
		cri.where().andEquals("pid", pid);
		int maxCode = dao.func(clazz, "max", "code", cri);
		
		if(maxCode == 0) {
			if(level == 0) {
				code = Strings.alignRight(maxCode + 1, (level + 1) * codeLen, codePad.charAt(0));
			} else {
				T obj = fetch(clazz, pid, false);
				if(obj == null) {
					throw new RuntimeException("上级信息不存在!");
				}
				String num = Strings.alignRight('1', codeLen, codePad.charAt(0));
				code = InvokeUtil.getValue(obj, "code") + num;
			}
		} else {
			code = Strings.alignRight(maxCode + 1, (level + 1) * codeLen, codePad.charAt(0));
		}
		
		T target = InvokeUtil.newInstance(clazz);
		InvokeUtil.setValue(target, "code", code);
		InvokeUtil.setValue(target, "pid", pid);
		return target;
	}
	
	/**
	 * 递归构造无限层级树形菜单HTML
	 */
	public String buildDWZMenuTreeHTML(SysUser user, String contextPath) {
		Collection<SysMenu> menus = getUserMenus(user);
		return buildDWZMenuTreeHTML(menus, contextPath);
	}
	
	/**
	 * 递归构造无限层级树形菜单HTML
	 */
	public String buildDWZMenuTreeHTML(Collection<SysMenu> menus, String contextPath) {
		Collection<TreeNode> nodes = buildTreeStructure(SysMenu.class, menus);
		
		StringBuffer html = new StringBuffer();
		AtomicInteger tidSafeIndex = new AtomicInteger(0);
		//判断首页左侧菜单是否设置为Accordion模式
		final boolean isAccordionMode = PropsUtil.getBoolean("sys.menu.accordion");
		if(isAccordionMode) {
			generateAccordionTreeHTML(nodes, 1, contextPath, html, tidSafeIndex);
			return html.toString();
		} else {
			generateSimpleTreeHTML(nodes, 1, contextPath, html, tidSafeIndex);
			
			StringBuffer wrapHTML = new StringBuffer();
			return wrapHTML.append("<div class=\"accordionHeader\"><h2><span>Folder</span>系统菜单</h2></div>")
					.append("<div class=\"accordionContent\"><ul class=\"tree treeFolder\">") 
					.append(html.toString())
					.append("</ul></div>")
					.toString();
		}
	}
	
	/**
	 *  根据菜单集合生成首页树形菜单的HTML代码片段(Accordion模式)
	 */
	private void generateAccordionTreeHTML(Collection<TreeNode> nodes, int level, 
			String contextPath, StringBuffer html, AtomicInteger index) {
		if(Lang.isEmpty(nodes) || level < 1) return;
		
		for(TreeNode node : nodes) {
			if(!node.isValid()) {
				continue;//忽略掉无效菜单及其子菜单
			}
			
			List<TreeNode> childs = node.getChilds();
			//缩进
			html.append("\n").append(Strings.dup(' ', (level - 1) * 8));
			//顶层accordion判断
			if(level == 1) {
				html.append("<div class=\"accordionHeader\"><h2><span>Folder</span>")
					.append(node.getText())
					.append("</h2></div>")
					.append("<div class=\"accordionContent\"><ul class=\"tree treeFolder\">");
			} else {
				html.append("<li>")
				    .append("<a");
				//定义菜单URL链接地址和点击行为
				final String url = node.getLink();
				if(!Strings.isBlank(url)) {
					html.append(" target=\"navTab\" rel=\"page_")
						.append(index.incrementAndGet())
						.append("\" href=\"")
						.append(contextPath)
						.append(url)
						.append(url.indexOf("?") != -1 ? "&tid=" : "?tid=")
						.append(index.get())
						.append("\"");
				}
				//菜单名称
				html.append(">").append(node.getText()).append("</a>");
				
				if(level > 1 && Lang.isEmpty(childs)) {
					html.append("</li>");
				}
			}
			
			if(!Lang.isEmpty(childs)) {
				//缩进
				html.append("\n").append(Strings.dup(' ', (level) * 4));
				//构造非顶层元素
				if(level > 1) {
					html.append("<ul>");
				}
				//递归调用
				generateAccordionTreeHTML(node.getChilds(), level + 1, contextPath, html, index);
				
				//缩进
				html.append("\n").append(Strings.dup(' ', (level) * 4));
				//构造非顶层元素
				if(level > 1) {
					html.append("</ul>")
					    .append("</li>");
				}
			}
			
			//顶层accordion判断
			if(level == 1) {
				html.append("\n</ul>")
		    		.append("</div>");
			}
		}
	}
	
	/**
	 *  根据菜单集合生成首页树形菜单的HTML代码片段(非Accordion模式)
	 *  
	 *  <li><a>系统管理</a>
			<ul>
				<li><a>菜单管理</a></li>
				<li><a>部门管理</a></li>
				<li><a>用户管理</a></li>
				<li><a>编码管理</a></li>
			</ul>
		</li>
		<li><a>测试顶级</a>
			<ul>
				<li><a>测试1</a></li>
				<li><a>测试2</a></li>
				<li><a>测试3</a>
					<ul>
						<li><a>测试4</a></li>
					</ul>
				</li>
			</ul>
		</li>
	 * @param nodes
	 * @param level
	 */
	private void generateSimpleTreeHTML(Collection<TreeNode> nodes, int level, 
			String contextPath, StringBuffer html, AtomicInteger index) {
		if(Lang.isEmpty(nodes) || level < 1) return;
		
		for(TreeNode node : nodes) {
			if(!node.isValid()) {
				continue;//忽略掉无效菜单及其子菜单
			}
			
			List<TreeNode> childs = node.getChilds();
			//缩进
			html.append("\n").append(Strings.dup(' ', (level - 1) * 8));
			
			html.append("<li>")
			    .append("<a");
			//定义菜单URL链接地址和点击行为
			final String url = node.getLink();
			if(!Strings.isBlank(url)) {
				html.append(" target=\"navTab\" rel=\"page_")
					.append(index.incrementAndGet())
					.append("\" href=\"")
					.append(contextPath)
					.append(url)
					.append(url.indexOf("?") != -1 ? "&tid=" : "?tid=")
					.append(index.get())
					.append("\"");
			}
			//菜单名称
			html.append(">").append(node.getText()).append("</a>");
			
			if(level > 1 && Lang.isEmpty(childs)) {
				html.append("</li>");
			}
			
			if(!Lang.isEmpty(childs)) {
				//缩进
				html.append("\n").append(Strings.dup(' ', (level) * 4));
				
				html.append("<ul>");
				//递归调用
				generateSimpleTreeHTML(node.getChilds(), level + 1, contextPath, html, index);
				
				//缩进
				html.append("\n").append(Strings.dup(' ', (level) * 4));
				
				html.append("</ul>")
				    .append("</li>");
			}
		}
	}

}
