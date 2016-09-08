package cn.com.jashon;

import org.junit.Test;

import cn.com.jashon.core.unitest.BaseUnitTest;

public class ActionRequestTest extends BaseUnitTest {
	
	@Test
	public void loginTest() {
		final String content = getResult("http://10.10.20.101:8089/p2p/mobile/account.do?method=login&json=");
		System.out.println("content=" + content);
	}
	
	@Test
	public void queryTest() {
		final String content = getResult("http://10.10.20.101:8089/p2p/mobile/invest.do?method=query&id=-1&fetch=10");
		System.out.println("content=" + content);
	}
	
	@Test
	public void queryMeTest() {
//		String content = getResult("http://localhost:8089/p2p/mobile/account.do?method=login&json={\"login\":\"dongbolvmain\",\"passwd\":\"dongbolvmain\"}");
//		System.out.println("content=" + content);
		final String content = getResult("http://localhost:8089/p2p/mobile/invest.do?method=queryMe&ucode=b57f2f09-5363-11e4-a01d-40167e219894&state=2&id=-1&fetch=10");
		System.out.println("content=" + content);
	}

}
