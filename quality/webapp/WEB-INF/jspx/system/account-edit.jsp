<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jashon_str" uri="http://www.jashon.com.cn/jstl/str"%>
<div style="margin:1px 0">
	<form id="saveSysUserForm"
		action="${pageContext.request.contextPath}/system/account/save"
		method="post"
		onsubmit="return validateCallback(this, navTabAjaxDone);"
		class="pageForm required-validate">
		<input type="hidden" name="tid" value="${param.tid}" /> <input
			type="hidden" name="u.id" value="${u.id}" />
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add"><span submitForm="saveSysUserForm">保存用户信息</span></a></li>
			</ul>
		</div>
		<table class="edit">
			<tbody>
				<tr>
					<th width="15%">用户ID</th>
					<td width="35%"><input name="u.memberId" value="${u.memberId}" class="required" style="width:95%" autocomplete="off" /></td>
					<th width="15%">账户等级</th>
					<td width="35%"><input name="u.accountLevelId" value="${u.accountLevelId}" class="required" style="width:95%" autocomplete="off" /></td>
				</tr>
				<tr>
					<th>用户登录名</th>
					<td><input name="u.username" value="${u.username}"  class="required" maxlength="10" style="width:95%" autocomplete="off" /></td>
					<th>用户登录密码</th>
					<td><input name="u.password" value="${u.password}" class="required" maxlength="18" style="width:95%" autocomplete="off" /></td>
				</tr>
				<tr>
					<th>可用积分</th>
					<td><input name="u.integralUsable" value="${u.integralUsable}" class="required" style="width:95%" autocomplete="off" /></td>
					<th>是否内部员工</th>
					<%-- <td><input name="u.isEmployee" value="${u.isEmployee}"  style="width:95%" autocomplete="off"/></td> --%>
					<td><select class="required""  name="u.isEmployee" value="${u.isEmployee}" style="width:20%" autocomplete="off">
							<option value=""></option>
							<option value="是">是</option>
							<option value="否">否</option>
					</select> 
				</tr>
				<tr>
					<th>注册时间</th>
					<td><input name="u.createTime" value="${requestScope.date}"  style="width:95%" autocomplete="off" readonly="readonly"/></td>
					<th>是否启用</th>
					<td><input name="u.isEnable" value="1" class="required" style="width:95%" autocomplete="off" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<%-- <td>
					<c:forEach var="dic" items="${dics['status']}">
						<input type="radio" name="u.status" ${u.status == dic.key ? "checked" : ""} value="${dic.key}"/>${dic.value}&nbsp;&nbsp;
					</c:forEach>
				</td> --%>