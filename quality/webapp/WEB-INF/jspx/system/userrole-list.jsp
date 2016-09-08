<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jashon_str" uri="http://www.jashon.com.cn/jstl/str"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<div class="pageHeader" style="margin-left:207px;">
  <form id="userRoleForm" onsubmit="return divSearch(this, 'userRoleBox');" action="${pageContext.request.contextPath}/system/role/user/list/${deptId}" method="post">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<input type="hidden" name="urids" value="${urids}"/>
	<div class="searchBar">
	<table class="searchContent">
	<thead><tr>
	  <td>角色编码:&nbsp;<input name="ft_LIKEA_S_code" value="${param.ft_LIKEA_S_code}" size="20"/></td>
	  <td>角色名称:&nbsp;<input name="ft_LIKEA_S_name" value="${param.ft_LIKEA_S_name}" size="20"/></td>
	  <td>状态:</td>
	  <td><select class="combox" id="userrole_status" name="ft_EQ_I_status" selectedValue="${param.ft_EQ_I_status}">
	  		<option value="">全部</option>
	  		<c:forEach var="dic" items="${dics['status']}">
	  			<option value="${dic.key}" <c:if test="${dic.key == param.ft_EQ_I_status}">selected="selected"</c:if> >${dic.value}</option>
	  		</c:forEach>
	  </select></td>
	  <td><div class="buttonActive"><span submitForm="userRoleForm">查询</span></div></td>
	  <td><div class="buttonActive"><span clearForm="userRoleForm">清空</span></div></td>
	</tr></thead>
	</table>
	</div>
  </form>
</div>
<div class="pageContent">
<table class="list" style="width:100%;margin-top:2px" layoutH="213">
	<thead>
		<tr>
			<th width="25"><input id="groupUserRoleId" type="checkbox" class="checkboxCtrl" group="urids" style="margin-left:-2px;"/></th>
			<th width="35">序号</th>
			<th width="150">角色名称</th>
			<th width="150">角色编码</th>
			<th>角色描述</th>
			<th width="90">状态</th>
		</tr>
	</thead>
	<tbody checkboxEvent groupId="groupUserRoleId" chkName="urids">
		<c:forEach var="r" items="${roles}" varStatus="st">
			<tr>
				<td><input type="checkbox" name="urids" value="${r.id}" <c:if test="${jashon_str:contains(urids, ',', r.id)}">checked="checked"</c:if> /></td>
				<td>${st.count}</td>
				<td>${r.name}</td>
				<td>${r.code}</td>
				<td>${r.desc}</td>
				<td class="center">${jashon_map:get(dics['status'], r.status)}</td>
			</tr>
		</c:forEach>
		<c:forEach begin="1" end="${fn:length(roles) <= 15 ? 15 - fn:length(roles) : 0}">
			<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>
		</c:forEach>
	</tbody>
</table>
</div>