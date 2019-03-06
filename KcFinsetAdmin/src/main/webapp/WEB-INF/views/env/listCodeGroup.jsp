<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#tbl_listCodeGroup')
	.addClass( 'nowrap' )
	.DataTable({
		paging: false,
		responsive: true,
		searching: false,
		columnDefs: [
						{ targets: [-1, -3], className: 'dt-body-right' }
					]
	});
});
</script>
<table id="tbl_listCodeGroup" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>순번</th>
			<th>코드그룹ID(CODE_GROUP)</th>
			<th>코드그룹명(NM_CODE)</th>
			<th>사용</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${List}" varStatus="status">
		<tr onclick="loadCodeGroup('${List.code_group}');" style="cursor: pointer;">
			<td>${status.count}</td>
			<td>${List.code_group}</td>
			<td>${List.nm_code}</td>
			<td>${List.yn_use}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>