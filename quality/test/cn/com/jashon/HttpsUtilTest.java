package cn.com.jashon;

import org.junit.Test;

import cn.com.jashon.core.utils.HttpUtil;

public class HttpsUtilTest {
	
	private final String URL = "https://172.16.17.1:8553/dts_if/servlet/IfRevSubServlet";
	
	private final String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<DataExchangeReq>" +
		    "<NodeId>220122001</NodeId>" +
		    "<RequestTime>20150603145201692</RequestTime>" +
		    "<VerifyCode>5aaac6b7beeb78702894f0f4f6143168</VerifyCode>" +
		    "<Index>A01002</Index>" +
		    "<Count>1</Count>" +
		    "<DataDate>20150603</DataDate>" +
		    "<Address>" +
		        "<manager_base_info>" +
		            "<butcher_fac_id>220122001</butcher_fac_id>" +
		            "<butcher_fac_name>长春众品食品有限公司</butcher_fac_name>" +
		            "<id>2201220010000</id>" +
		            "<name>长春众品食品有限公司</name>" +
		            "<reg_id>22020145698756325</reg_id>" +
		            "<property>企业</property>" +
		            "<type>2002</type>" +
		            "<record_date>20150518152310</record_date>" +
		            "<legal_represent>王小强</legal_represent>" +
		            "<tel>13000000000</tel>" +
		            "<info_update_date>20150603145200</info_update_date>" +
		        "</manager_base_info>" +
		    "</Address>" +
		"</DataExchangeReq>";
	
	@Test
	public void httpsTest() throws Exception {
		String result = HttpUtil.send(URL, "POST", content, 7000);
		System.out.println("result=" + result);
	}
}
