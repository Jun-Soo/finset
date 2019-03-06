<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	<c:if test="${!empty templateList}">
		$('#tbl_listTemplate')
		.addClass( 'nowrap' )
		.DataTable({
			paging: false,
			responsive: true
		});
	</c:if>
});
</script>

<table id="tbl_listTemplate" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>권한그룹 이름</th>
			<th>설명</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${empty templateList}">
		<tr>
			<td colspan="2" height="100" align="center">
				등록된 권한그룹이 없습니다
			</td>
		</tr>
	</c:if>
	<c:forEach var="List" items="${templateList}" varStatus="status">
		<tr onclick="setTemplate('${List.code_value}');" style="cursor: pointer;">
			<td><strong>${List.nm_code}</strong></td>
			<td>${List.etc}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
