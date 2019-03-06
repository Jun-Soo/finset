<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#tbl_listEdocGroup')
	.addClass( 'nowrap' )
	.DataTable({
		paging: false,
		searching: false, //데이터 테이블 검색 삭제
		responsive: true,
		columnDefs: [
						{ targets: [-1, -3], className: 'dt-body-right' }
					]
	});
});
</script>
<table id="tbl_listEdocGroup" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>순번</th>
			<th>금융사명</th>
			<th>금융사코드</th>
			<th>전문번호</th>
			<th>전문명</th>
			<th>전문URL</th>
			<th>송수신구분</th>
			<th>전문타입</th>
			<th>사용</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${List}" varStatus="status">
		<tr onclick="loadEdocGroup('${List.seq_edoc}');" style="cursor: pointer;">
			<td>${status.count}</td>
			<td>${ufn:getNmFc(List.cd_fc)}</td>
			<td>${List.cd_fc}</td>
			<td>${List.no_edoc}</td>
			<td>${List.nm_edoc}</td>
			<td>${List.url_edoc}</td>
			<td>${ufn:getCodeName('cd_type_txrx', List.type_txrx)}</td>
			<td>${ufn:getCodeName('cd_type_flex', List.type_flex)}</td>
			<td>${List.yn_use}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>