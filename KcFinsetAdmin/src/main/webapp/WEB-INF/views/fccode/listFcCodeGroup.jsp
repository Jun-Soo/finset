<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script src="<c:url value="/script/jquery/jquery.tablednd.js"/>"></script>
<style>
.dragRow {
		    background-color: silver;
		}
</style>
<script type="text/javascript">
$(document).ready(function () {
	$('#tbl_listFcCodeGroup')
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
	// $("table").tableDnD();
	 
	 $("#tbl_listFcCodeGroup").tableDnD({
	        onDragClass: "dragRow"
	    });



});


</script>
<form name="frmTest">
<table id="tbl_listFcCodeGroup" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>순번</th>
			<th>금융사</th>
			<th style="display: none;">금융사코드</th>
			<th>전문번호</th>
			<th>송수신구분</th>
			<th>항목그룹ID</th>
			<th>항목그룹명</th>
			<th>핀셋표준항목</th>
			<th>속성</th>
			<th>길이</th>
			<th>사용</th>
			<th style="display: none;">부모코드</th>
		</tr>
	</thead>
	<tbody>
	
	<c:forEach var="List" items="${List}" varStatus="status">
			<c:if test="${status.count == 1 }">
				<input type="text" name="search_cd_fc" value="${List.cd_fc}" style="display: none">
				<input type="text" name="search_no_edoc" value="${List.no_edoc}" style="display: none">
				<input type="text" name="search_type_txrx"value="${List.type_txrx}" style="display: none">
			</c:if>
		
		<tr onclick="loadFcCodeGroup('${List.cd_fc}','${List.no_edoc}','${List.code_group}','${List.item_tag}','${List.type_txrx}', this);" style="cursor: pointer;">
			<td>${List.seq_order}<%-- ${status.count} --%></td>
			<td>${ufn:getNmFc(List.cd_fc)}</td>
			<td style="display: none;">${List.cd_fc}</td>
			<td>${List.no_edoc}</td>
			<td>${ufn:getCodeName('cd_type_txrx', List.type_txrx)}</td>
			<td>${List.code_group}</td>
			<td>${List.nm_code}</td>
			<td>${List.item_tag}</td>
			<td>${List.type_attr}</td>
			<td>${List.field_length}</td>
			<td>${List.yn_use}</td>
			<td style="display: none;">${List.parent_code_group}</td>
		</tr>
	</c:forEach>
	
	</tbody>
</table>
</form>