package cn.com.jashon;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;

import cn.com.jashon.core.unitest.BaseUnitTest;
import cn.com.jashon.system.domain.SysCode;

public class MultiDataSourceTest extends BaseUnitTest {

	@Test
	public void test() {
		Dao sqlserver = getIoc().get(Dao.class, "dao_sqlserver");
		Dao mysql = getIoc().get(Dao.class, "dao");
		
		List<Record> list = sqlserver.query("TB_SYMBOL", Cnd.orderBy().desc("CODE"));
		for(Record r : list) {
			System.out.println(r.get("CODE") + ", " + r.get("NAME") + ", " + r.get("ABBRNAME"));
//			mysql.insert("sm_code_002", Chain.make("CODE", r.get("CODE")).add("NAME", r.get("NAME")).add("ABBRNAME", r.get("ABBRNAME")));
		}
		
		/*
		List<Record> list = mysql.query("SM_CODE_002", Cnd.orderBy().desc("code"));
		SysCode code = null;
		int index = 1;
		for(Record r : list) {
			code = new SysCode();
			code.setId(UUID.randomUUID().toString());
			code.setCode(r.get("CODE").toString());
			code.setName(r.get("NAME").toString());
			code.setType("PRODUCT_CODE");
			code.setStatus(1);
			code.setSeqno(index++);
			mysql.insert(code);
			System.out.println(r.get("CODE") + ", " + r.get("NAME") + ", " + r.get("ABBRNAME"));
		}
		*/
	}
	
}
