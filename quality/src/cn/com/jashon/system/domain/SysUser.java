package cn.com.jashon.system.domain;

import java.util.Collection;
import java.util.LinkedList;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

import cn.com.jashon.core.domain.IdEntry;

/**
 * 用户信息
 * @author 	dongbolv
 * @date 	2014-09-16
 */
@Comment("用户信息")
@Table("SYS_USER")
@View("VIEW_SYS_USER")
public class SysUser extends IdEntry {
	
	@Column("LOGIN_NAME")
	@Comment("登录名")
	private String loginName;
	
	@Column("LOGIN_PASS")
	@Comment("登录密码")
	private String loginPass;
	
	@Column("USER_NAME")
	@Comment("真实姓名")
	private String name;
	
	@Column("IDCARD")
	@Comment("身份证号码")
	private String idcard;
	
	@Column("TYPE")
	@Comment("用户类型（1：系统管理员，2：部门管理员，3：普通用户）")
	private int type = 3;
	
	@Column("MOBILE_PHONE")
	@Comment("联系电话")
	private String phone;
	
	@Column("EMAIL")
	@Comment("电子邮箱")
	private String email;
	
	@Column("STATUS")
	@Comment("状态：是否激活(1：激活，0：无效)")
	private int status = 1;

	@Column("INDEX_OPER_PRIV")
	@Comment("首页操作权限：是否启用(1：启用，0：禁用)")
	private int indexPriv = 0;
	
	@Column("DEPT_ID")
	@Comment("部门代码")
	private String deptId;

	@Readonly
	@Column("DEPT_CODE")
	@Comment("部门代码")
	private String deptCode;

	@Readonly
	@Column("DEPT_NAME")
	@Comment("部门名称")
	private String deptName;
	
	@Readonly
	@Column("DEPT_STATUS")
	@Comment("部门状态")
	private int deptStatus;

	@Readonly
	@Column("DEPT_REMARK1")
	@Comment("部门预留字段1")
	private String deptRemark1;

	@Readonly
	@Column("DEPT_REMARK2")
	@Comment("部门预留字段2")
	private String deptRemark2;
	
	@ManyMany(target=SysRole.class, relation="SYS_USER_ROLE", from="uid", to="rid")
	private Collection<SysRole> roles = new LinkedList<SysRole>();

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Collection<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<SysRole> roles) {
		this.roles = roles;
	}

	public int getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(int deptStatus) {
		this.deptStatus = deptStatus;
	}

	public int getIndexPriv() {
		return indexPriv;
	}

	public void setIndexPriv(int indexPriv) {
		this.indexPriv = indexPriv;
	}

	public String getDeptRemark1() {
		return deptRemark1;
	}

	public void setDeptRemark1(String deptRemark1) {
		this.deptRemark1 = deptRemark1;
	}

	public String getDeptRemark2() {
		return deptRemark2;
	}

	public void setDeptRemark2(String deptRemark2) {
		this.deptRemark2 = deptRemark2;
	}

}
