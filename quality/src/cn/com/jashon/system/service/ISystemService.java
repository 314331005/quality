package cn.com.jashon.system.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.com.jashon.core.service.IBaseService;
import cn.com.jashon.system.domain.SysMenu;
import cn.com.jashon.system.domain.SysRole;
import cn.com.jashon.system.domain.SysUser;

public interface ISystemService extends IBaseService {
	
	/**
	 * 用户登录校验
	 * @param loginName
	 * @param loginPass
	 * @return
	 */
	public SysUser loginUser(String loginName, String loginPass);
	
	/**
	 * 用户密码修改或初始化
	 * @param loginName
	 * @param loginPass
	 * @return
	 */
	public SysUser initPass(SysUser user);
	
	public Collection<SysMenu> getUserMenus(SysUser user);
	
	public <T> int updateStatus(Class<T> clazz, String id, int status);
	
	
//	public <T> int updateCodeStatus(Class<T> clazz, String type, String code, int status);
	
	public Map<String, Integer> updateCodeStatus(String[] cids, int status);
	
	public int deleteCodes(String[] cids);
	
	public SysUser updateUserAndRoles(SysUser u, String oldPass, String roleIds);
	
	public SysRole updateRoleAndMenus(SysRole r, String menuIds);
	
	public <T> T saveNextLevel(Class<T> clazz, T obj) throws Exception;
	
	public void deleteDept(String id);
	
	public <T> List<String> deleteSelfAndChilds(Class<T> clazz, String id);
	
	/**
	 * 树结构同级数据排序, 根据seqno
	 * @param sortedIds 重新排序后id，多个以逗号分隔
	 */
	public void updateTreeSameLevelSeqno(Class<?> clazz, String sortedIds) throws Exception;
	
	/**
	 * 实体初始化时自动生成编码
	 * 说明：用于系统管理中部门和菜单的编码生成
	 * @author 	dongbolv
	 * @date	2014-12-05
	 */
	public <T> T genSysEntryCode(Class<T> clazz, String pid, int level);
	
	public String buildDWZMenuTreeHTML(SysUser user, String contextPath);
	
	public String buildDWZMenuTreeHTML(Collection<SysMenu> menus, String contextPath);
}
