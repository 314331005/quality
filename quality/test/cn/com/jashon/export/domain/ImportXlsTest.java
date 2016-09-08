package cn.com.jashon.export.domain;

import org.junit.Test;

import cn.com.jashon.core.unitest.BaseUnitTest;

public class ImportXlsTest extends BaseUnitTest {

	@Test
	public void dataImpTest() throws Exception {
		final String jsonString = "{\"tag\":\"test\",\"type\":1,\"rows\":{\"0\":{\"start\":1,\"end\":-1},\"1\":{\"start\":1,\"end\":-1}},\"cols\":{\"0\":\"code\",\"1\":\"name\",\"2\":\"seqno\",\"3\":\"price\",\"4\":\"createDate\",\"5\":\"createTime\",\"6\":\"dt\"}}";
		
		String result = getResult("http://10.10.20.101:8088/framework/imp/xls?json=" + jsonString);
		System.out.println("result=" + result);
	}
	
}
