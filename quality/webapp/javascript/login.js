function addFavroite(url, title) {
	try {
		window.external.addFavorite(url, title);
	} catch(e) {
		try {
			window.sidebar.addPanel(title, url);
		} catch(e) {
			alert("抱歉，加入收藏失败，请使用Ctrl + D进行添加。");
		}
	}
}

function setHome(obj, url) {
	try {
		obj.style.behavior='url(#default#homepage)';
		obj.setHomePage(url);
	} catch(e) {
		if(window.netscape) {
			try {
				window.netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			} catch(e) {
				alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入‘about:config’并回车\n然后将[signed.applets.codebase_principal_support]的值设置为‘true’，双击即可。");
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;'].getService(Components.interfaces.nslPrefBranch);
			prefs.setCharPref('browser.startup.homepage',url);
		} else {
			alert("抱歉，您的浏览器不支持此项操作。");
		}
	}
}