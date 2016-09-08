package cn.com.jashon;

import java.util.Collection;

import org.junit.Test;

import cn.com.jashon.core.unitest.BaseUnitTest;
import cn.com.jashon.core.utils.JsonUtil;
import cn.com.jashon.system.domain.SysMenu;
import cn.com.jashon.system.service.ISystemService;

public class MenusTest extends BaseUnitTest {
	
	final String json = "[{\"pid\":\"\",\"code\":\"01\",\"name\":\"系统管理\",\"desc\":\"系统管理\",\"link\":\"\",\"level\":1,\"seqno\":6,\"status\":1,\"flag\":1,\"id\":\"eb755d9c-3430-44a1-b761-42347df98f96\"}, {\"pid\":\"\",\"code\":\"02\",\"name\":\"测试顶级\",\"desc\":\"测试顶级\",\"link\":\"\",\"level\":1,\"seqno\":9,\"status\":1,\"flag\":1,\"id\":\"4ae4be3c-f84b-4a32-8919-0060886c6452\"}, {\"pid\":\"4ae4be3c-f84b-4a32-8919-0060886c6452\",\"code\":\"0201\",\"name\":\"测试1\",\"desc\":\"测试1\",\"link\":\"\",\"level\":2,\"seqno\":1,\"status\":1,\"flag\":1,\"id\":\"ec6dca3c-d07d-4112-9849-2a3f1c6fa07c\"}, {\"pid\":\"eb755d9c-3430-44a1-b761-42347df98f96\",\"code\":\"0101\",\"name\":\"菜单管理\",\"desc\":\"菜单管理\",\"link\":\"/system/menu/list\",\"level\":2,\"seqno\":1,\"status\":1,\"flag\":1,\"id\":\"6d0ff035-d4f8-47ae-8ec0-bde93e2ff673\"}, {\"pid\":\"eb755d9c-3430-44a1-b761-42347df98f96\",\"code\":\"0102\",\"name\":\"部门管理\",\"desc\":\"部门管理\",\"link\":\"/system/dept/list\",\"level\":2,\"seqno\":2,\"status\":1,\"flag\":1,\"id\":\"a737a3ff-2d91-408e-b586-d88f4ef479de\"}, {\"pid\":\"4ae4be3c-f84b-4a32-8919-0060886c6452\",\"code\":\"0202\",\"name\":\"测试2\",\"desc\":\"测试2\",\"link\":\"\",\"level\":2,\"seqno\":2,\"status\":1,\"flag\":1,\"id\":\"89d0dee8-ed98-45fe-aa42-eed1be5a09f1\"}, {\"pid\":\"eb755d9c-3430-44a1-b761-42347df98f96\",\"code\":\"0103\",\"name\":\"用户管理\",\"desc\":\"用户管理\",\"link\":\"/system/user/list\",\"level\":2,\"seqno\":3,\"status\":1,\"flag\":1,\"id\":\"c6e4f14c-8b02-4738-a64f-df0228268634\"}, {\"pid\":\"4ae4be3c-f84b-4a32-8919-0060886c6452\",\"code\":\"0203\",\"name\":\"测试3\",\"desc\":\"测试3\",\"link\":\"\",\"level\":2,\"seqno\":3,\"status\":1,\"flag\":1,\"id\":\"35bf9d4c-cecd-4c07-a407-c48bf2ec002a\"}, {\"pid\":\"eb755d9c-3430-44a1-b761-42347df98f96\",\"code\":\"0104\",\"name\":\"编码管理\",\"desc\":\"编码管理\",\"link\":\"/system/code/list\",\"level\":2,\"seqno\":4,\"status\":1,\"flag\":1,\"id\":\"2b97fba6-76a8-4526-a8a1-957f92127aef\"}, {\"pid\":\"35bf9d4c-cecd-4c07-a407-c48bf2ec002a\",\"code\":\"020301\",\"name\":\"测试4\",\"desc\":\"测试4\",\"link\":\"/system/user/list\",\"level\":3,\"seqno\":1,\"status\":1,\"flag\":1,\"id\":\"b5796d0c-c873-4e23-b8d3-6b85bea891f2\"}]";
	
	@Test
	public void menuTest() {
		Collection<SysMenu> menus = JsonUtil.json2List(SysMenu.class, json);
		
		ISystemService systemService = getIoc().get(ISystemService.class, "systemService");
		System.out.println(systemService.buildDWZMenuTreeHTML(menus, "/framework"));
	}
	
}
