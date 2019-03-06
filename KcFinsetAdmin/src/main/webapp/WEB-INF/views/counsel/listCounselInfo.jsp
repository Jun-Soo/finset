<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#listCounselInfoTable')
	.addClass( 'nowrap' )
	.DataTable({
		info: false,
		paging: false,
		searching: false,
		responsive: true,
		columnDefs: [
						{ targets: [-1, -3], className: 'dt-body-right' }
					]
	});
});


</script>
<!-- <div class="panel panel-primary"> -->
<!-- 	<div class="panel-collapse"> -->
		<input type="hidden" name="listLength"
			value="${fn:length(pagedList.source)}" />
		<!-- List -->
		<table id="listCounselInfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th> 순번</th>
					<th> 상담신청일</th>
					<th> 상담준비일</th>
					<th> 상담완료일</th>
					<th> 회원번호</th>
					<th> 고객명</th>
					<th> 나이</th>
					<th> 상태</th>
					<th> 상담직원명</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="List" items="${pagedList.source}" varStatus="status">
								
				<tr>
					<td onclick="goCounselForm('${List.no_person}','${List.counsel_seq}');">${status.index+1+(pagedList.page-1)*pagedList.pageSize}</td>
					<td onclick="goCounselForm('${List.no_person}','${List.counsel_seq}');">${List.dt_apply}</td>   <!-- 상담신청일시 -->
					<td onclick="goCounselForm('${List.no_person}','${List.counsel_seq}');">${List.dt_pre_counsel}</td> <!-- 상담준비일시 -->
					<td onclick="goCounselForm('${List.no_person}','${List.counsel_seq}');">${List.dt_counsel}</td> <!-- 상담완료일시 -->
		
					<td onclick="goCounselForm('${List.no_person}','${List.counsel_seq}');"><strong>${List.no_person}</strong></td>   <!-- 회원번호 -->
					<td onclick="goCounselForm('${List.no_person}','${List.counsel_seq}');"><strong>${List.nm_person}</strong></td>   <!-- 이름 -->
					<td onclick="goCounselForm('${List.no_person}','${List.counsel_seq}');">${List.age}</td> <!-- 나이 -->
					<td>
						<c:if test="${List.cd_counsel_status eq '1'}">
						<button type="button" class="btn btn-primary btn-xs" onclick="saveCounselStatus('${List.no_person}','${List.counsel_seq}');"> 상담준비중 변경</button>
						</c:if>
						<c:if test="${List.cd_counsel_status ne '1'}">
						<strong>${ufn:getCodeName('cd_counsel_status',List.cd_counsel_status) }</strong>
						</c:if>
					</td>   <!-- 상담상태 -->
					<td onclick="goCounselForm('${List.no_person}','${List.counsel_seq}');">${List.nm_emp}</td>   <!-- 상담직원명 -->
				</tr>
		
			</c:forEach>
			</tbody>
		</table>

<div class="panel-footer">
		<span class="pull-left"> <span class="total-num em"><span></span>총건수
				: <em>${ufn:formatNumber(pagedList.recordCount)} 건</em></span>
		</span>
</div>

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}" />

