package cn.com.jashon.export.domain;

import org.junit.Test;

import cn.com.jashon.core.utils.JsonUtil;
import cn.com.jashon.export.domain.ImportModel;
import cn.com.jashon.export.domain.ImportRow;

public class ImportModelTest {
	
	@Test
	public void testImport() {
		ImportModel m = new ImportModel();
		m.setTag("test");
		m.addRow(0, new ImportRow(1, -1));
		m.addColumn(0, "code");
		m.addColumn(1, "name");
		m.addColumn(2, "seqno");
		m.addColumn(3, "price");
		m.addColumn(4, "createDate");
		m.addColumn(5, "createTime");
		m.addColumn(6, "dt");
		final String jsonString = JsonUtil.toJson(m);
		//{"tag":"user","type":1,"rows":{0:{"start":1,"end":-1}},"cols":{0:"code","1":"name","2":"age","3":"idcard"}}
		System.out.println(jsonString);
		
		ImportModel target = JsonUtil.fromJson(ImportModel.class, jsonString);
		System.out.println("target.tag=" + target.getTag());
	}

}
