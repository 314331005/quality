package cn.com.jashon;

import org.junit.Test;

import cn.com.jashon.core.utils.QRCodes;

public class QRCodesTest {

	@Test
	public void encodeTest() {
		final String content = "测试ABCdef123";
		QRCodes.encode(content, "F:/qrcode.png", null);
		QRCodes.encode(content, "F:/qrcode.png", "F:/images/Koala.jpg");
	}
	
	@Test
	public void decodeTest() {
		final String content = QRCodes.decode("F:/qrcode.png");
		System.out.println("content=" + content);
	}
	
}
