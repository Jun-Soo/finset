<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#listSendPersonDetail')
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
					
<h3 class="h-sec pull-left">
	<a href="#none">발송 대상 고객</a>
</h3>
<table id="listSendPersonDetail" class="table table-bordered" cellspacing="0" width="100%">
	<colgroup>
		<col width="50px"/>
		<col width="100px"/>
		<col width="100px"/>
		<col width="*"/>
	</colgroup>
	<thead>
		<tr>
			<th>선택</th>
			<th>회원번호</th>
			<th>이름</th>
			<th>생년월일</th>
		</tr>
	</thead>
	<tbody id="list_tbody">
	</tbody>
</table>