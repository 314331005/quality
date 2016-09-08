package cn.com.jashon;

import cn.com.jashon.core.service.IBaseService;
import cn.com.jashon.core.unitest.BaseUnitTest;

public class ConcurrentTest extends BaseUnitTest {
	
	private IBaseService getBaseService() {
		return getIoc().get(IBaseService.class, "baseService");
	}
	
	private long getNextVal() {
		final String key = "220103001";
		long value1 = getBaseService().setNextVal(key);
		long value2 = getBaseService().getNextVal(key, -1L);
		System.out.println("thread=" + Thread.currentThread().getName() + ", v1=" + value1 + ", v2=" + value2);
		return value2;
	}
	
	public void getNextValTest(int count) {
		for(int i=0; i<count; i++) {
			new Thread(new Runnable() {
				public void run() {
					getNextVal();
				}
			}).start();
		}
	}
	
	public static void main(String[] args) {
		final ConcurrentTest test = new ConcurrentTest();
		test.getNextValTest(1000);
	}
	
}
