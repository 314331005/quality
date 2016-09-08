package cn.com.jashon.system.domain;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

import cn.com.jashon.core.annotation.TreeDefine;
import cn.com.jashon.core.domain.IdEntry;

/**
 * 菜单信息
 * @author 	dongbolv
 * @date 	2014-09-16
 */
@Comment("菜单信息")
@Table("SYS_MENU")
@View("VIEW_SYS_MENU")
@TreeDefine(text="name", url="link", valid="status")
public class SysMenu extends IdEntry {
	
	@Column("PID")
	@Comment("上级菜单ID")
	private String pid;
	
	@Column("MENU_CODE")
	@Comment("菜单编码")
	private String code;
	
	@Column("MENU_NAME")
	@Comment("菜单名称")
	private String name;
	
	@Column("MENU_DESC")
	@Comment("菜单描述")
	private String desc;
	
	@Column("MENU_LINK")
	private String link;
	
	@Column("LEVE")
	@Comment("层级（level为关键字）")
	private int level = 1;
	
	@Column("SEQNO")
	@Comment("排序号")
	private int seqno = 1;
	
	@Column("STATUS")
	@Comment("是否有效：1-有效， 0-无效")
	private int status = 1;
	
	@Readonly
	@Column("FLAG")
	@Comment("是否是菜单：1-是，0-否")
	private int flag = 1;
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
