<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎使用<fmt:bundle basename="static"><fmt:message key="app.title"/></fmt:bundle></title>
<link rel="shortcut icon" href="favicon.ico"/>
<link rel="icon" href="favicon.ico"/>
<link rel="stylesheet" type="text/css" href="themes/default/style.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="themes/css/core.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="themes/css/print.css" media="print"/>
<link rel="stylesheet" type="text/css" href="javascript/plugins/ztree/css/zTreeStyle/zTreeStyle.min.css" />
<link rel="stylesheet" type="text/css" href="javascript/plugins/combox/css/jquery.combox.min.css" />
<link rel="stylesheet" type="text/css" href="javascript/plugins/combox/css/jquery.comboxtree.min.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="css/uploadify.css">
       
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="themes/css/ieHack.css" media="screen"/>
<![endif]-->
<!--[if lte IE 9]>
<script type="text/javascript" src="js/speedup.js"></script>
<![endif]-->

<script type="text/javascript" src="javascript/jquery-1.7.2.min.js"></script>	
<script type="text/javascript"  src="javascript/plugins/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="javascript/jquery.livequery.min.js"></script>
<script type="text/javascript" src="javascript/jquery.cookie.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.js"></script>
<script type="text/javascript" src="javascript/jquery.bgiframe.js"></script>
<script type="text/javascript" src="javascript/plugins/xheditor/xheditor-1.2.1.min.js"></script>
<script type="text/javascript" src="javascript/plugins/xheditor/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript" src="javascript/plugins/ztree/js/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript" src="javascript/plugins/ztree/js/jquery.treextend.js"></script>
<script type="text/javascript" src="javascript/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<script type="text/javascript" src="javascript/plugins/dragevent/jquery.event.drag-2.2-min.js"></script>
<script type="text/javascript" src="javascript/plugins/jquery.tablednd.js"></script>
<script type="text/javascript" src="javascript/plugins/combox/js/jquery.combox.min.js"></script>
<script type="text/javascript" src="javascript/plugins/combox/js/jquery.comboxtree.min.js"></script>

<script type="text/javascript" src="javascript/dwz.min.js"></script>
<script type="text/javascript" src="javascript/dwz.regional.zh.js"></script>
<script type="text/javascript" src="javascript/common.js"></script>
</head>
<body scroll="no">
<c:if test="${empty sessionScope._SYS_USER_}">
	<c:redirect url="/logout"/>
</c:if>
<form id="loginAjaxUserForm" silent="true" onsubmit="return divSearch(this, 'header');" action="${pageContext.request.contextPath}/login/after/user" method="post"></form>
<form id="loginAjaxMenuForm" silent="true" onsubmit="return divSearch(this, 'sidebar');" action="${pageContext.request.contextPath}/login/after/menu" method="post"></form>
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo">标志</a>
				<ul class="nav">
					<li style="background:none"><a class="no-underline">您好，${sessionScope._SYS_USER_.loginName}（${sessionScope._SYS_USER_.name}）</a></li>
					<li><a class="no-underline" target="dialog" mask="true" width="500" height="160" href="${pageContext.request.contextPath}/system/user/passwd/before">修改密码</a></li>
					<li><a class="no-underline" onclick="logout('${sessionScope._SYS_USER_.loginName}', '${pageContext.request.contextPath}/logout');" style="cursor:pointer;">退出</a></li>
				</ul>
			</div>
		</div>
		
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2><fmt:bundle basename="static"><fmt:message key="app.title"/></fmt:bundle></h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">${sessionScope._SYS_USER_MENUS_}</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<ul class="navTab-tab">
							<li tabid="main" class="main" title="首页"><a href="javascript:;"><span><span class="home_icon">首页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<div class="tabsRight">right</div>
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">首页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div id="mainContent" class="page unitBox">
						<div class="pageHeader">
							<div class="searchBar"></div>
							<!-- 
							<div>
								<form action="${pageContext.request.contextPath}/imp/xls" method="post" 
									onsubmit="return iframeCallback(this, navTabAjaxDone)" enctype="multipart/form-data">
									<input type="hidden" name="json" value='{"tag":"test","type":1,"rows":{"0":{"start":1,"end":-1},"1":{"start":1,"end":-1}},"cols":{"0":"code","1":"name","2":"seqno","3":"price","4":"createDate","5":"createTime","6":"dt"}}'/>
									<input type="file" name="f" style="width:300px;border:1px solid #e2e2e2"/>
									<input type="submit" value="upload"/>
								</form>
							</div>
							 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="footer">
		<div style="position:absolute;top:0;right:15px;">版本：<fmt:bundle basename="static"><fmt:message key="app.version"/></fmt:bundle></div>
		<fmt:bundle basename="static"><fmt:message key="app.copyright"/></fmt:bundle>
	</div>
</html>