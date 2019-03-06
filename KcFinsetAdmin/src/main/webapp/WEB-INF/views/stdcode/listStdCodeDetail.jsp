<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#tbl_listStdCodeDetail')
	.addClass( 'nowrap' )
	.DataTable({
		paging: false,
		responsive: true,
		searching: false, //데이터 테이블 검색 삭제
		columnDefs: [
						{ targets: [-1, -3], className: 'dt-body-right' }
					]
	});
});
</script>
<table id="tbl_listStdCodeDetail" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>순번</th>
			<th>표준데이터항목ID(CODE_VALUE)</th>
			<th>표준데이터항목명(NM_CODE)</th>
			<th>정렬</th>
			<th>사용</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${List}" varStatus="status">
		<tr onclick="loadStdCodeDetail('${List.code_group}','${List.code_value}');" style="cursor: pointer;">
			<td>${status.count} </td>
			<td>${List.code_value}</td>
			<td>${List.nm_code}</td>
			<td>${List.seq_order}</td>
			<td>${List.yn_use}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>