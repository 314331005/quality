package cn.com.jashon;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.com.jashon.core.service.IBaseService;
import cn.com.jashon.core.unitest.BaseUnitTest;
import cn.com.jashon.core.utils.ProcType;

@SuppressWarnings("serial")
public class BaseServiceTest extends BaseUnitTest {

	@Test
	public void callTest() {
		Map<String, Object> params = new HashMap<String, Object>() {{
			put("IN_BIDD_CODE", "项目代码");
			put("IN_USER_CODE", "用户代码");
			put("IN_USER_NAME", "用户名称");
			put("IN_AMOUNT_ATM", 12.3D);//DOUBLE
			put("IN_BIDD_ATM", 123.45D);//DOUBLE
			put("IN_RESIDUE_ATM", 123.45D);//DOUBLE
			put("IN_PAYWAY", "还款方式");
			put("IN_SCHEME_VALUE", 100);//INT
			put("IN_EXPECT_ATM", 1.23D);//DOUBLE
		}};
		
		IBaseService baseService = get(IBaseService.class, "baseService");
		Map<String, Object> values = baseService.call("PROC_BIDD_INFO_UPD", params, new HashMap<String, ProcType>(){{
			put("OUT_RTN_CODE", ProcType.String);
			put("OUT_RTN_MSG", ProcType.String);
		}});
		
		System.out.println(values.get("OUT_RTN_CODE") + " -- " + values.get("OUT_RTN_MSG"));
	}
	
}
