package cn.com.jashon.system.utils;

import org.nutz.lang.Lang;

public class PassUtil {
	
	public static String genUserPass(String loginName, String loginPass) {
		return Lang.md5(loginName.trim().concat(loginPass.trim()));
	}

}
