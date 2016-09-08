<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<div class="pageHeader">
  <form id="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/system/code/list" method="post">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<input type="hidden" id="sys_page_pageNo" name="page.pageNo" value="${page.pageNo}"/>
	<input type="hidden" id="sys_page_pageSize" name="page.pageSize" value="${page.pageSize}"/>
	<div class="searchBar">
	<table class="searchContent">
	<thead><tr>
	  <td>类别:</td>
	  <td><select id="code_type" name="ft_EQ_S_type" selectedValue="${param.ft_EQ_S_type}" class="combox">
	  		<option value="">全部</option>
	  		<c:forEach var="dic" items="${dics['syscode']}">
	  			<option value="${dic.key}">${dic.value}</option>
	  		</c:forEach>
	  </select></td>
	  <td>编码:&nbsp;<input name="ft_LIKEA_S_code" value="${param.ft_LIKEA_S_code}" size="20"/></td>
	  <td>名称:&nbsp;<input name="ft_LIKEA_S_name" value="${param.ft_LIKEA_S_name}" size="20"/></td>
	  <td>状态:</td>
	  <td><select id="ft_EQ_I_status" name="ft_EQ_I_status" selectedValue="${param.ft_EQ_I_status}" class="combox">
	  		<option value="">全部</option>
	  		<c:forEach var="dic" items="${dics['codeStatus']}">
	  			<option value="${dic.key}">${dic.value}</option>
	  		</c:forEach>
	  </select></td>
	  <td><div class="buttonActive"><span submitForm="pagerForm">查询</span></div></td>
	  <td><div class="buttonActive"><span clearForm="pagerForm">清空</span></div></td>
	  <td><div class="buttonActive"><a target="dialog" mask="true" maxable="false" resizable="false" width="500" height="260" rel="dlg-code-edit" href="${pageContext.request.contextPath}/system/code/edit/0?tid=${param.tid}" title="系统编码添加"><span>添加系统编码</span></a></div></td>
	</tr></thead>
	</table>
	</div>
  </form>
</div>
<table class="table" style="width:100%;margin-top:2px" layoutH="86">
	<thead>
		<tr>
			<th width="35">序号</th>
			<th width="250">类别</th>
			<th width="250">编码</th>
			<th width="600">名称</th>
			<th width="90">状态</th>
			<th width="140">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="c" items="${page.result}" varStatus="st">
			<tr>
				<td>${st.count+(page.pageNo-1)*page.pageSize}</td>
				<td>${jashon_map:get(dics['syscode'], c.type)}</td>
				<td>${c.code}</td>
				<td>${c.name}</td>
				<td class="center">${jashon_map:get(dics['codeStatus'], c.status)}</td>
				<td>&nbsp;
					<c:if test="${not empty c.type}">
						<c:choose>
							<c:when test="${c.status == 0}">
								<a target="dialog" mask="true" maxable="false" resizable="false" width="500" height="260" rel="dlg-code-edit" href="${pageContext.request.contextPath}/system/code/edit/${c.id}?tid=${param.tid}" title="系统编码编辑" class="font-blue">编辑</a>&nbsp;&nbsp;
								<a target="ajaxTodo" title="删除后将不可恢复，请确认?" href="${pageContext.request.contextPath}/system/code/del/${c.id}?tid=${param.tid}" class="font-red">删除</a>&nbsp;&nbsp;
								<a target="ajaxTodo" title="固化后该编码将不能再编辑或删除，请确认?" href="${pageContext.request.contextPath}/system/code/fix/${c.id}?tid=${param.tid}" class="font-red">固化</a>
							</c:when>
							<c:otherwise>
								<a class="font-gray">编辑</a>&nbsp;&nbsp;<a class="font-gray">删除</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<c:forEach begin="1" end="${page.pageSize - fn:length(page.result)}">
			<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>
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