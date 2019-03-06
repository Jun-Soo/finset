<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#tbl_listFcCodeDetail')
	.addClass( 'nowrap' )
	.DataTable({
		paging: false,
		responsive: true,
		searching: false, //데이터 테이블 검색 삭제
		"bSort" : false,//소팅기능 삭제
		columnDefs: [
						{ targets: [-1, -3], className: 'dt-body-right' }
					]
	});
});
</script>
<table id="tbl_listFcCodeDetail" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>정렬</th>
			<th style="display: none;">금융사</th>
			<th style="display: none;">전문번호</th>
			<th>항목명</th>
			<th>전문항목값</th>
			<th>사용</th>
			<th>핀셋표준항목코드</th>
			<th>핀셋표준항목명</th>
			<th>핀셋표준항목코드</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${List}" varStatus="status">
		<tr onclick="loadFcCodeDetail('${List.cd_fc}','${List.no_edoc}','${List.code_group}','${List.code_value}','', '${List.type_txrx }');" style="cursor: pointer;">
			<td>${List.seq_order}</td>
			<td style="display: none;">${ufn:getNmFc(List.cd_fc)}</td>
			<td style="display: none;">${List.no_edoc}</td>
			<td>${List.nm_code}</td>
			<td>${List.code_value}</td>
			<td>${List.yn_use}</td>
			<td>${List.item_tag}</td>
			<td>${ufn:getStdCodeName(List.item_tag, List.item_tag_value)}</td>
			<td>${List.item_tag_value}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>