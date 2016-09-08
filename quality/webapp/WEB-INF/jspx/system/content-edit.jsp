<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<form id="saveContentForm" action="${pageContext.request.contextPath}/system/content/save" method="post" 
	onsubmit="return validateCallback(this, dialogAjaxDone);" class="pageForm required-validate">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<input type="hidden" name="c.id" value="${c.id}"/>
	<div class="panelBar">
		<ul class="toolBar">
			<li style="float:right;"><a class="add"><span submitForm="saveContentForm">保存</span></a></li>
		</ul>
	</div>
	<table class="edit" layoutH="0">
		<tbody>
			<tr>
				<th width="20%" nowrap="nowrap">主标题</th>
				<td><input name="c.firstTitle" value="${c.firstTitle}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>副标题</th>
				<td><input name="c.secondTitle" value="${c.secondTitle}" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>排序</th>
				<td><input name="c.orderBy" value="${c.orderBy}" class="required"style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>攥写人</th>
				<td><input name="c.addedBy" value="${c.addedBy}" class="required" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>攥写人职位</th>
				<td><input name="c.position" value="${c.position}" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>攥写人单位</th>
				<td><input name="c.employer" value="${c.employer}" style="width:95%" autocomplete="off"/></td>
			</tr>
			<tr>
				<th>添加时间</th>
				<td><input type="text" name="c.addTime" value="${c.addTime}" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" style="width:95%"/> </td>
			</tr>
			<tr>
				<th>是否启用</th>
	  			<td width="80%">
					<select id="c_isEnable" name="c.isEnable" selectedValue="${c.isEnable}" class="combox required">
						  <c:forEach var="dic" items="${dics['indexPriv']}">
						  	<option value="${dic.key}">${dic.value}</option>
						  </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>内容</th>
				<td><textarea name="c.content" cols="45" rows="15" style="width:95%">${c.content}</textarea></td>
			</tr>
		</tbody>
	</table>
</form>