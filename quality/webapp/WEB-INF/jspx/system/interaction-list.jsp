<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<div class="pageHeader">
  <form id="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/system/interaction/list" method="post">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<input type="hidden" id="sys_page_pageNo" name="page.pageNo" value="${page.pageNo}"/>
	<input type="hidden" id="sys_page_pageSize" name="page.pageSize" value="${page.pageSize}"/>
	<div class="searchBar">
	<table class="searchContent">
	<thead><tr>
	  <td>账户ID:&nbsp;<input name="ft_LIKEA_S_accountId" value="${param.ft_LIKEA_S_accountId}" size="20"/></td>
	  <td><div class="buttonActive"><span submitForm="pagerForm">查询</span></div></td>
	  <td><div class="buttonActive"><span clearForm="pagerForm">清空</span></div></td>
	  <td><div class="buttonActive"><a target="dialog" mask="true" maxable="false" resizable="false" width="800" height="530" rel="dlg-code-edit" href="${pageContext.request.contextPath}/system/interaction/edit/0?tid=${param.tid}" title="互动详细添加"><span>添加互动详细</span></a></div></td>
	</tr></thead>
	</table>
	</div>
  </form>
</div>
<table class="table" style="width:100%;margin-top:2px" layoutH="86">
	<thead>
		<tr>
			<th width="25">序号</th>
			<th width="50">账户ID</th>
			<th width="50">分类ID</th>
			<th width="300">内容</th>
			<th width="35">父ID</th>
			<th width="50">有用数</th>
			<th width="50">没用数</th>
			<th width="100">添加时间</th>
			<th width="60">审核人</th>
			<th width="100">审核时间</th>
			<th width="50">审核状态</th>
			<th width="100">审核结果说明</th>
			<th width="50">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="c" items="${page.result}" varStatus="st">
			<tr>
				<td>${st.count+(page.pageNo-1)*page.pageSize}</td>
				<td>${c.accountId}</td>
				<td>${c.interactionTypeId}</td>
				<td>${c.content}</td>
				<td>${c.parentId}</td>
				<td>${c.useful}</td>
				<td>${c.useless}</td>
				<td>${c.addTime}</td>
				<td>${c.adminId}</td>
				<td>${c.reviewTime}</td>
				<td>${c.reviewStatus}</td>
				<td>${c.reviewResult}</td>
				<td>&nbsp;
					<a target="dialog" mask="true" maxable="false" resizable="false" width="800" height="530" rel="dlg-code-edit" href="${pageContext.request.contextPath}/system/interaction/edit/${c.id}?tid=${param.tid}" title="编辑" class="font-blue">编辑</a>&nbsp;&nbsp;
					<a target="ajaxTodo" title="删除后将不可恢复，请确认?" href="${pageContext.request.contextPath}/system/interaction/del/${c.id}?tid=${param.tid}" class="font-red">删除</a>&nbsp;&nbsp;
				</td>
			</tr>
		</c:forEach>
		<c:forEach begin="1" end="${page.pageSize - fn:length(page.result)}">
			<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
		</c:forEach>
	</tbody>
</table>
<div class="panelBar">
	<div class="pages">
		<span>每页显示</span>
		<span>&nbsp;${page.pageSize}&nbsp;条，共&nbsp;${page.totalCount}&nbsp;条</span>
	</div>
	<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize}" pageNumShown="${page.pageShowNum}" currentPage="${page.pageNo}"></div>
</div>