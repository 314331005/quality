<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<form id="saveCommentProductForm" action="${pageContext.request.contextPath}/system/commentproduct/save" method="post" 
	onsubmit="return validateCallback(this, dialogAjaxDone);" class="pageForm required-validate">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<input type="hidden" name="c.id" value="${c.id}"/>
	<div class="panelBar">
		<ul class="toolBar">
			<li style="float:right;"><a class="add"><span submitForm="saveCommentProductForm">保存</span></a></li>
		</ul>
	</div>
	<table class="edit" layoutH="0">
		<tbody>
			<tr>
				<th width="20%" nowrap="nowrap">商品ID</th>
				<td><input name="c.productId" value="${c.productId}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>评论级别ID</th>
				<td><input name="c.commentLevelId" value="${c.commentLevelId}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>评论数</th>
				<td><input name="c.times" value="${c.times}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>占比</th>
				<td><input name="c.rate" value="${c.rate}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
		</tbody>
	</table>
</form>