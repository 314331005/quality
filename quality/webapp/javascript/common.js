$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login_dialog.jsp", loginTitle:"系统登录",
		statusCode:{ok:200, error:300, timeout:301},
		pageInfo:{pageNum:"page.pageNo", numPerPage:"page.pageSize", orderField:"page.orderBy", orderDirection:"page.order"},
		keys: {statusCode:"statusCode", message:"message"},
		debug:false,
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
			setTimeout(function(){
				$("li.main").click();
			}, 500);
		}
	});
	
	loadFormValidate();
});

function loginDialogAjaxDone(json) {
	DWZ.ajaxDone(json);
	if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
		$("#loginAjaxUserForm").submit();
		$("#loginAjaxMenuForm").submit();
		$.pdialog.closeCurrent();
	}
	return false;
}

function logout(name, url) {
	alertMsg.confirm("您好, " + name + ", 确认要退出登录吗？", {
		okCall: function(){
			window.location.href = url;
		}
	});
}

function loadFormValidate() {
	$.validator.addMethod("login", function(value, element) {
		return this.optional(element) || /^[a-zA-Z0-9]{6,16}$/i.test(value);
	}, "长度在6至16之间的英文字母或数字"); 
	$.validator.addMethod("passwd", function(value, element) {
		return this.optional(element) || /^[a-zA-Z0-9]{6,32}$/i.test(value);
	}, "长度在6至32之间的英文字母或数字"); 
	$.validator.addMethod("normal", function(value, element) {
		return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5]+$/i.test(value);
	}, "禁止输入特殊字符"); 
	$.validator.addMethod("tel", function(value, element) {
		return this.optional(element) || /^[1]{1}[3578]{1}[0-9]{9}$/i.test(value);
	}, "手机号码格式不正确"); 
	$.validator.addMethod("mail", function(value, element) {
		return this.optional(element) || /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.(com|com.cn|net|org)$/i.test(value);
	}, "电子邮箱格式不正确");
	$.validator.addMethod("vehicleNum", function(value, element) {
		return this.optional(element) || /^[\u4e00-\u9fa5A-Z]{1}[A-Z0-9]{5}[A-Z0-9学警]{1}$/i.test(value);
	}, "车牌号码格式不正确");
	$.validator.addMethod("float", function(value, element) {
		return this.optional(element) || /^(\d{1,8}|\d{1,8}\.\d{1})$/.test(value);
	}, "长度为8位的数值，最多1位小数"); 
	$.validator.addMethod("double2", function(value, element) {
		return this.optional(element) || /^(\d{1,8}|\d{1,8}\.\d{1,2})$/.test(value);
	}, "长度为8位的数值，最多2位小数");
	$.validator.addMethod("double3", function(value, element) {
		return this.optional(element) || /^(\d{1,6}|\d{1,6}\.\d{1,3})$/.test(value);
	}, "长度为6位的数值，最多3位小数");
	$.validator.addMethod("double4", function(value, element) {
		return this.optional(element) || /^(\d{1,8}|\d{1,8}\.\d{1,4})$/.test(value);
	}, "长度为8位的数值，最多4位小数");
}

function disableKeys() {
	$(document).on("contextmenu", function(e) {
		return false;
	});
	$(document).on("keydown", function(e) {
		var code = e.keyCode;
		//禁用退格键、Alt + <- 和 Alt + ->组合键
		if(code == 37 || code == 39) {
			return false;
		}
	});
}

function loadMainTabAjaxDone(json) {
	if(json == null) return false;
	if (json.statusCode == DWZ.statusCode.ok) {
		alertMsg.correct(json.message);
		$("#mainForm").submit();
	}else{
		alertMsg.error(json.message);
	}
}

function loadMainDialogAjaxDone(json) {
	if(json == null) return false;
	if (json.statusCode == DWZ.statusCode.ok) {
		alertMsg.correct(json.message);
		if($.pdialog) {
			$.pdialog.closeCurrent();
		}
		$("#mainForm").submit();
	}else{
		alertMsg.error(json.message);
	}
}

function treeAfterSaveCallback(json) {
	if(json == null) return false;
	
	if (json.statusCode == DWZ.statusCode.ok) {
		alertMsg.correct(json.message);
		navTab.reloadFlag(json.navTabId);
	} else {
		alertMsg.error(json.message);
	}
}


function deleteTreeNode(json, treeId){
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var treeNode = zTree.getNodeByParam("id", json.extra);
	if(treeNode != null) {
		var pNode = treeNode.getParentNode();
		zTree.removeNode(treeNode);
		/*
		if(pNode && pNode != null) {
			zTree.selectNode(pNode);
		} else {
			navTab.reloadFlag(json.navTabId);
		}*/
		navTab.reloadFlag(json.navTabId);
		return pNode;
	}
	return null;
}

function showInfoMsg(message) {
	if (alertMsg) {
		alertMsg.info(message);
	}
}

function showWarnMsg(message) {
	if (alertMsg) {
		alertMsg.warn(message);
	}
}

function showErrMsg(message) {
	if (alertMsg) {
		alertMsg.error(message);
	}
}

function getObjectId() {
	return "objSample";
}
