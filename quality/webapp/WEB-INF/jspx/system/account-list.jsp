<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);"
		action="${pageContext.request.contextPath}/system/account/list"
		method="post">
		<input type="hidden" name="tid" value="${param.tid}" /> <input
			type="hidden" id="sys_page_pageNo" name="page.pageNo"
			value="${page.pageNo}" /> <input type="hidden"
			id="sys_page_pageSize" name="page.pageSize" value="${page.pageSize}" />
		<div class="searchBar">
			<table class="searchContent">
				<thead>
					<tr>
						<td>登录名:&nbsp;<input name="ft_LIKEA_S_username"
							value="${param.ft_LIKEA_S_username}" size="20" /></td>
						<td>是否内部员工:</td>
						<td><select class="combox"  name="ft_LIKEA_S_isEmployee" value="${param.ft_LIKEA_S_isEmployee}" size="20">
							<option value=""></option>
							<option value="是">是</option>
							<option value="否">否</option>
					</select> 
						<td><div class="buttonActive">
								<span submitForm="pagerForm">查询</span>
							</div></td>
						<td><div class="buttonActive">
								<span clearForm="pagerForm">清空</span>
							</div></td>
					</tr>
				</thead>
			</table>
		</div>
	</form>
</div>
<div class="panelBar">
	<ul class="toolBar">
		<li><a class="add" target="navTab" rel="tab-account-edit"
			href="${pageContext.request.contextPath}/system/account/edit/0?tid=${param.tid}"
			title="用户信息新增"><span>新增</span></a></li>
		<li><a class="delete" target="selectedTodo" rel="wids"
			title="删除后数据不可恢复，确认删除吗?"
			href="${pageContext.request.contextPath}/system/account/del?tid=${param.tid}"><span>批量删除</span></a></li>
	</ul>
</div>
<form id="userBatchForm"
	onsubmit="return validateCallback(this, navTabAjaxDone);" action=""
	method="post">
	<table class="table" style="width:100%;margin-top:2px" layoutH="113">
		<thead>
			<tr>
				<th width="25"><input type="checkbox" class="checkboxCtrl" group="wids" id="allChk" /></th>
				<th width="35">序号</th>
				<th width="50">用户ID</th>
				<th width="120">账户等级</th>
				<th width="120">用户登录名</th>
				<th width="160">用户登录密码</th>
				<th width="120">可用积分</th>
				<th width="120">是否内部员工</th>
				<th width="120">注册时间</th>
				<!--<th width="100">积分级数</th>
				<th width="100">现金账户预留</th>
				 <th width="35">信用等级</th>
				<th width="35">ip地址</th>
				<th width="35">账户来源</th> -->
				<th width="80">是否启用</th>
				<th width="220">操作</th>
			</tr>
		</thead>
		<tbody>
					
			<c:forEach var="u" items="${page.result}" varStatus="st">
				<tr target="uid" rel="${u.id}">
						<td class="center"><input type="checkbox" name="wids" value="${u.id}" /></td>
						<td>${st.count+(page.pageNo-1)*page.pageSize}</td>
						<td>${u.memberId}</td>
						<td>${u.accountLevelId}</td>
						<td>${u.username}</td>
						<td>${u.password}</td>
						<td>${u.integralUsable}</td>
						<td>${u.isEmployee}</td>
						<td>${u.createTime}</td>
						<!-- <td>${u.integralLevels}</td>
						<td>${u.balance}</td>
						<td>${u.creditLevelId}</td>
						<td>${u.ip}</td>
						<td>${u.sourceFrom}</td> -->
						<td>${u.isEnable}</td>
						<td class="center">
							<a target="dialog" mask="true" maxable="false" resizable="false" width="600" height="240" rel="dlg-code-edit" href="${pageContext.request.contextPath}/system/account/details/?memberId=${u.memberId}&&tid=${param.tid}">详细信息</a>&nbsp;&nbsp;
							<c:choose>
								<c:when test="${u.isEnable == 1}">
									<a target="ajaxTodo" title="禁用后该用户将无法登录本系统直至再次启用，请确认?"
										href="${pageContext.request.contextPath}/system/account/enable/${u.id}/0?tid=${param.tid}"
										class="font-red">禁用</a>
								</c:when>
							 </c:choose>
						 </td>
				</tr>
			</c:forEach>
			<c:forEach begin="1" end="${page.pageSize - fn:length(page.result)}">
				<tr target="uid" rel="">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
<div class="panelBar">
	<div class="pages">
		<span>每页显示</span> <span>&nbsp;${page.pageSize}&nbsp;条，共&nbsp;${page.totalCount}&nbsp;条</span>
	</div>
	<div class="pagination" targetType="navTab"
		totalCount="${page.totalCount}" numPerPage="${page.pageSize}"
		pageNumShown="${page.pageShowNum}" currentPage="${page.pageNo}"></div>
</div>

