package cn.com.jashon.system.domain;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

import cn.com.jashon.core.domain.IdEntry;

/**
 * 系统编码表信息
 * @author 	dongbolv
 * @date 	2014-09-23
 */
@Comment("系统编码表信息")
@Table("SYS_CODE")
public class SysCode extends IdEntry {
	
	@Column("TYPE")
	@Comment("码表所属类别")
	private String type;

	@Column("NAME")
	@Comment("代码")
	private String name;
	
	@Column("CODE")
	@Comment("代码")
	private String code;
	
	@Column("STATUS")
	@Comment("状态 1：已固化，0：未固化")
	private int status = 0;
	
	@Column("SEQNO")
	@Comment("排序号")
	private int seqno = 1;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
}
