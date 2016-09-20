<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<div class="pageHeader">
  <form id="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/system/user/list" method="post">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<input type="hidden" id="sys_page_pageNo" name="page.pageNo" value="${page.pageNo}"/>
	<input type="hidden" id="sys_page_pageSize" name="page.pageSize" value="${page.pageSize}"/>
	<div class="searchBar">
	<table class="searchContent">
	<thead><tr>
	  <td>登录名:&nbsp;<input name="ft_LIKEA_S_loginName" value="${param.ft_LIKEA_S_loginName}" size="20"/></td>
	  <td>所属部门:&nbsp;<input name="ft_LIKEA_S_deptName" value="${param.ft_LIKEA_S_deptName}" size="20"/></td>
	  <td>状态:</td>
	  <td><select class="combox" prompt="全部" id="user_status" name="ft_EQ_I_status" selectedValue="${param.ft_EQ_I_status}">
	  		<c:forEach var="dic" items="${dics['status']}">
	  			<option value="${dic.key}">${dic.value}</option>
	  		</c:forEach>
	  </select></td>
	  <td><div class="buttonActive"><span submitForm="pagerForm">查询</span></div></td>
	  <td><div class="buttonActive"><span clearForm="pagerForm">清空</span></div></td>
	</tr></thead>
	</table>
	</div>
  </form>
</div>
<div class="panelBar">
	<ul class="toolBar">
		<li><a class="add" target="navTab" rel="tab-user-edit" href="${pageContext.request.contextPath}/system/user/edit/0?tid=${param.tid}" title="用户信息新增"><span>新增</span></a></li>
		<li><a class="edit" target="navTab" rel="tab-user-edit" href="${pageContext.request.contextPath}/system/user/edit/{uid}?tid=${param.tid}" title="用户信息编辑"><span>编辑</span></a></li>
		<li><a class="delete" target="ajaxTodo" title="删除后数据不可恢复，确认删除吗?" href="${pageContext.request.contextPath}/system/user/del/{uid}?tid=${param.tid}"><span>删除</span></a></li>
		<li><a class="delete" target="form" rel="userBatchForm" title="删除后数据不可恢复，确认删除吗?" href="${pageContext.request.contextPath}/system/user/del?tid=${param.tid}"><span>批量删除</span></a></li>
		<li><a class="icon" target="ajaxTodo" title="启用后用户将系统登录及相关角色权限，请确认?" href="${pageContext.request.contextPath}/system/user/enable/{uid}/1?tid=${param.tid}"><span>启用</span></a></li>
		<li><a class="icon" target="ajaxTodo" title="禁用后该用户将无法登录本系统直至再次启用，请确认?" href="${pageContext.request.contextPath}/system/user/enable/{uid}/0?tid=${param.tid}"><span>禁用</span></a></li>
		<li><a class="icon" target="ajaxTodo" title="用户密码初始化操作，请确认?" href="${pageContext.request.contextPath}/system/user/passwd/init/{uid}?tid=${param.tid}"><span>密码初始化</span></a></li>
	</ul>
</div>
<form id="userBatchForm" onsubmit="return validateCallback(this, navTabAjaxDone);" action="" method="post">
<table class="table" style="width:100%;margin-top:2px" layoutH="113">
	<thead>
		<tr>
			<th width="25"><input type="checkbox" class="checkboxCtrl" group="mids" /></th>
			<th width="35">序号</th>
			<th width="130">登录名</th>
			<th width="120">姓名</th>
			<th width="120">所属部门</th>
			<th width="160">身份证号</th>
			<th width="160">联系电话</th>
			<th width="250">电子邮箱</th>
			<th width="80">状态</th>
			<th width="220">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="u" items="${page.result}" varStatus="st">
			<tr target="uid" rel="${u.id}">
				<td class="center"><input type="checkbox" name="uids" value="${u.id}"/></td>
				<td>${st.count+(page.pageNo-1)*page.pageSize}</td>
				<td>${u.loginName}</td>
				<td>${u.name}</td>
				<td>${u.deptName}</td>
				<td>${u.idcard}</td>
				<td>${u.phone}</td>
				<td>${u.email}</td>
				<td class="center">${jashon_map:get(dics['status'], u.status)}</td>
				<td class="center">
					<a target="navTab" rel="tab-user-edit" href="${pageContext.request.contextPath}/system/user/edit/${u.id}?tid=${param.tid}" title="用户信息编辑" class="font-blue">编辑</a>&nbsp;&nbsp;
					<a target="ajaxTodo" title="删除后用户将不可恢复，请确认?" href="${pageContext.request.contextPath}/system/user/del/${u.id}?tid=${param.tid}" class="font-red">删除</a>&nbsp;&nbsp;
					<c:choose>
						<c:when test="${u.status == 1}">
							<a target="ajaxTodo" title="禁用后该用户将无法登录本系统直至再次启用，请确认?" href="${pageContext.request.contextPath}/system/user/enable/${u.id}/0?tid=${param.tid}" class="font-red">禁用</a>
						</c:when>
						<c:otherwise>
							<a target="ajaxTodo" title="启用后用户将系统登录及相关角色权限，请确认?" href="${pageContext.request.contextPath}/system/user/enable/${u.id}/1?tid=${param.tid}" class="font-blue">启用</a>
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;<a target="ajaxTodo" title="用户密码初始化操作，请确认?" href="${pageContext.request.contextPath}/system/user/passwd/init/${u.id}?tid=${param.tid}" class="font-blue">密码初始化</a>
				</td>
			</tr>
		</c:forEach>
		<c:forEach begin="1" end="${page.pageSize - fn:length(page.result)}">
			<tr target="uid" rel=""><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
		</c:forEach>
	</tbody>
</table>
</form>
<div class="panelBar">
	<div class="pages">
		<span>每页显示</span>
		<span>&nbsp;${page.pageSize}&nbsp;条，共&nbsp;${page.totalCount}&nbsp;条</span>
	</div>
	<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize}" pageNumShown="${page.pageShowNum}" currentPage="${page.pageNo}"></div>
</div>