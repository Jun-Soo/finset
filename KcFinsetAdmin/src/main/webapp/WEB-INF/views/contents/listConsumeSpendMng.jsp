<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
// 	$('#tbl_listConsumeSpendMng')
// 	.addClass( 'nowrap' )
// 	.DataTable({
// 		paging: false,
// 		responsive: true,
// 		searching: false,
// 		columnDefs: [
// 						{ targets: [-1, -3], className: 'dt-body-right' }
// 					]
// 	});
});
</script>
<div cellspacing="0" width="100%">
<table class="table table-bordered tbl-info" style="margin:0px;">
	<colgroup>
		<col width="25%">
		<col width="25%">
		<col width="25%">
		<col width="25%">
	</colgroup>
	<thead>
		<tr>
			<th>소비항목코드</th>
			<th>분류명</th>
			<th>항목명</th>
			<th>사용여부</th>
		</tr>
	</thead>
</table>
</div> 
<div cellspacing="0" width="100%" style="height: 300px; overflow: scroll;">
<table id="tbl_listConsumeSpendMng" class="table table-bordered tbl-info" style="overflow:hidden;">
	<colgroup>
		<col width="26%">
		<col width="25%">
		<col width="26%">
		<col width="23%">
	</colgroup>
	<tbody>
	<c:choose>
	<c:when test="${empty spendList}">
		<td colspan="4">검색한 데이터가 없습니다</td>
	</c:when>
	<c:otherwise>
		<c:forEach var="List" items="${spendList}" varStatus="status">
			<tr onclick="getConsumeSpendMng('${List.cd_consume_class}');" style="cursor: pointer;">
				<td>${List.cd_consume_class}</td>
				<td>${List.nm_class}</td>
				<td>${List.nm_type}</td>
				<td>${List.yn_use}</td>
			</tr>
		</c:forEach>
	</c:otherwise>
	</c:choose>
	</tbody>
</table>
</div>