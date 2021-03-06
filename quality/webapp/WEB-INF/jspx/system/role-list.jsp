<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jashon_map" uri="http://www.jashon.com.cn/jstl/map"%>
<div class="pageHeader">
  <form id="pagerForm" onsubmit="return divSearch(this, 'roleBox');" action="${pageContext.request.contextPath}/system/role/list/${deptId}" method="post">
	<input type="hidden" name="tid" value="${param.tid}"/>
	<input type="hidden" id="sys_page_pageNo" name="page.pageNo" value="${page.pageNo}"/>
	<input type="hidden" id="sys_page_pageSize" name="page.pageSize" value="${page.pageSize}"/>
	<div class="searchBar">
	<table class="searchContent">
	<thead><tr>
	  <td>角色编码:&nbsp;<input name="ft_LIKEA_S_code" value="${param.ft_LIKEA_S_code}" size="20"/></td>
	  <td>角色名称:&nbsp;<input name="ft_LIKEA_S_name" value="${param.ft_LIKEA_S_name}" size="20"/></td>
	  <td>状态:</td>
	  <td><select id="ft_EQ_I_status" name="ft_EQ_I_status" selectedValue="${param.ft_EQ_I_status}" class="combox">
	  		<option value="">全部</option>
	  		<c:forEach var="dic" items="${dics['status']}">
	  			<option value="${dic.key}">${dic.value}</option>
	  		</c:forEach>
	  </select></td>
	  <td><div class="buttonActive"><span id="btnQueryRole" submitForm="pagerForm">查询</span></div></td>
	  <td><div class="buttonActive"><span clearForm="pagerForm">清空</span></div></td>
	  <td><div class="buttonActive"><a target="dialog" mask="true" maxable="false" resizable="false" width="800" height="410" rel="dlg-role-edit" href="${pageContext.request.contextPath}/system/role/edit/0/${deptId}" title="角色添加"><span>添加角色</span></a></div></td>
	</tr></thead>
	</table>
	</div>
  </form>
</div>
<form id="roleBatchForm" onsubmit="return validateCallback(this, navTabAjaxDone);" action="" method="post">
<table class="table" rel="roleBox" style="width:100%;margin-top:2px" layoutH="204">
	<thead>
		<tr>
			<th width="35">序号</th>
			<th width="150">角色名称</th>
			<th width="150">角色编码</th>
			<th>角色描述</th>
			<th width="90">状态</th>
			<th width="120">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="r" items="${page.result}" varStatus="st">
			<tr target="rid" rel="${r.id}">
				<td>${st.count+(page.pageNo-1)*page.pageSize}</td>
				<td>${r.name}</td>
				<td>${r.code}</td>
				<td>${r.desc}</td>
				<td>${jashon_map:get(dics['status'], r.status)}</td>
				<td>&nbsp;
					<a target="dialog" mask="true" maxable="false" resizable="false" width="800" height="400" rel="dlg-role-edit" href="${pageContext.request.contextPath}/system/role/edit/${r.id}/${deptId}" title="角色编辑" class="font-blue">编辑</a>&nbsp;&nbsp;
					<a target="ajaxTodo" callback="afterSysRoleAjaxDone" title="删除后用户将永久失去相关角色权限，请确认?" href="${pageContext.request.contextPath}/system/role/del/${r.id}" class="font-red">删除</a>&nbsp;&nbsp;
					<c:choose>
						<c:when test="${r.status == 1}">
							<a target="ajaxTodo" callback="afterSysRoleAjaxDone" title="禁用后该角色将暂时失效以致用户暂时失去相关权限直至再次启用，请确认?" href="${pageContext.request.contextPath}/system/role/enable/${r.id}/0" class="font-red">禁用</a>
						</c:when>
						<c:otherwise>
							<a target="ajaxTodo" callback="afterSysRoleAjaxDone" title="启用后用户将恢复相关角色权限，请确认?" href="${pageContext.request.contextPath}/system/role/enable/${r.id}/1" class="font-blue">启用</a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		<c:forEach begin="1" end="${page.pageSize - fn:length(page.result)}">
			<tr target="rid"><td></td><td></td><td></td><td></td><td></td><td></td></tr>
		</c:forEach>
	</tbody>
</table>
</form>
<div class="panelBar">
	<div class="pages">
		<span>每页显示</span>
		<span>&nbsp;${page.pageSize}&nbsp;条，共&nbsp;${page.totalCount}&nbsp;条</span>
	</div>
	<div class="pagination" targetType="navTab" rel="roleBox" totalCount="${page.totalCount}" numPerPage="${page.pageSize}" pageNumShown="${page.pageShowNum}" currentPage="${page.pageNo}"></div>
</div>

<script type="text/javascript">
	function afterSysRoleAjaxDone(json){
		if (json.statusCode == DWZ.statusCode.ok){
			alertMsg.info(json.message);
			if($.pdialog && json.callbackType) {
				$.pdialog.closeCurrent();
			}
			$("#btnQueryRole").click();
		}else{
			alertMsg.error(json.message);
		}
	}
</script>