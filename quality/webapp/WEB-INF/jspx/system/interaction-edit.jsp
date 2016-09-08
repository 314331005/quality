<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<form id="saveInteractionForm" action="${pageContext.request.contextPath}/system/interaction/save" method="post" 
	onsubmit="return validateCallback(this, dialogAjaxDone);" class="pageForm required-validate">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<input type="hidden" name="c.id" value="${c.id}"/>
	<div class="panelBar">
		<ul class="toolBar">
			<li style="float:right;"><a class="add"><span submitForm="saveInteractionForm">保存</span></a></li>
		</ul>
	</div>
	<table class="edit" layoutH="0">
		<tbody>
			<tr>
				<th width="20%" nowrap="nowrap">账户ID</th>
				<td><input name="c.accountId" value="${c.accountId}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>分类ID</th>
				<td><input name="c.interactionTypeId" value="${c.interactionTypeId}" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>内容</th>
				<td><textarea name="c.content" cols="45" rows="10" style="width:95%"></textarea>${c.content}</td>
			</tr>
			<tr>
				<th>父ID</th>
				<td><input name="c.parentId" value="${c.parentId}" class="required"style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>有用数</th>
				<td><input name="c.useful" value="${c.useful}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>没用数</th>
				<td><input name="c.useless" value="${c.useless}" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>添加时间</th>
				<td><input type="text" name="c.addTime" value="${c.addTime}" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" style="width:95%"/> </td>
			</tr>
			<tr>
				<th>审核人</th>
				<td><input name="c.adminId" value="${c.adminId}" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>审核时间</th>
				<td><input type="text" name="c.reviewTime" value="${c.reviewTime}" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" style="width:95%"/> </td>
			</tr>
			<tr>
				<th>审核状态</th>
	  			<td width="80%">
					<select id="c_reviewStatus" name="c.reviewStatus" selectedValue="${c.reviewStatus}" class="combox required">
						  <c:forEach var="dic" items="${dics['reviewStatus']}">
						  	<option value="${dic.key}">${dic.value}</option>
						  </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>审核结果说明</th>
				<td><textarea name="c.reviewResult" cols="45" rows="10" style="width:95%">${c.reviewResult}</textarea></td>
			</tr>
		</tbody>
	</table>
</form>