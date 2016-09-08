<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jashon_str" uri="http://www.jashon.com.cn/jstl/str"%>
<div style="margin:1px 0">
<form id="saveSysUserForm" action="${pageContext.request.contextPath}/system/account/save" method="post" 
	onsubmit="return validateCallback(this, navTabAjaxDone);" class="pageForm required-validate">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"><span submitForm="saveSysUserForm">保存用户信息</span></a></li>
		</ul>
	</div>
	<table class="edit">
		<tbody>
		<%-- <c:forEach var="u" items="${page.result}" varStatus="st">
		</c:forEach> --%>
			<tr>
				<th width="15%">用户ID</th>
				<td width="35%"><input name="c.memberId" value="${c.memberId}" class="required"  style="width:95%" autocomplete="off"/></td>
				<th width="15%">联系电话</th>
				<td width="35%"><input name="ad.mobile" value="${ad.mobile}"  class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>用户登录名</th>
				<td><input name="ad.name" value="${ad.name}" class="required"  style="width:95%" autocomplete="off"/></td>
				<th>家庭住址</th>
				<td><input name="ad.address" value="${ad.address}" class="required" maxlength="10" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>用户登录密码</th>
				<td><input name="c.password" value="${c.password}" class="required" maxlength="10" style="width:95%" autocomplete="off"/></td>
				<th>业余爱好</th>
				<td><input name="ad.hobby" value="${ad.hobby}" class="required" maxlength="18" style="width:95%" autocomplete="off"/></td>
			</tr>
		</tbody>
	</table>
</form>
</div>
