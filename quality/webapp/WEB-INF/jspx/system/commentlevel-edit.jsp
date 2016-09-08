<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<form id="saveCommentLevelForm" action="${pageContext.request.contextPath}/system/commentlevel/save" method="post" 
	onsubmit="return validateCallback(this, dialogAjaxDone);" class="pageForm required-validate">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<input type="hidden" name="c.id" value="${c.id}"/>
	<div class="panelBar">
		<ul class="toolBar">
			<li style="float:right;"><a class="add"><span submitForm="saveCommentLevelForm">保存</span></a></li>
		</ul>
	</div>
	<table class="edit" layoutH="0">
		<tbody>
			<tr>
				<th width="20%" nowrap="nowrap">名称</th>
				<td><input name="c.name" value="${c.name}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>从多少分</th>
				<td><input name="c.starFrom" value="${c.starFrom}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>到多少分</th>
				<td><input name="c.starTo" value="${c.starTo}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
		</tbody>
	</table>
</form>