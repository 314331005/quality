package cn.com.jashon.system.domain;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

/**
 * 省市县编码（三级联动）
 * @author 	dongbolv
 * @date	2014-12-09
 */
@Comment("省市县编码")
@Table("SYS_PCA")
public class PCACode {
	
	@JsonField(ignore=true)
	@Column("PARENTCODE")
	private String pcode;
	
	@JsonField(value="value")
	@Column("CODE")
	private String code;
	
	@JsonField(value="text")
	@Column("NAME")
	private String name;
	
	public PCACode() {
	}
	
	public PCACode(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
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

}
