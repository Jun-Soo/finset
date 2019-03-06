<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#listPersonInfoTable')
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
		<table id="listPersonInfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th> 순번</th>
					<th> 회원번호</th>
					<th> 고객명</th>
					<th> 전화번호</th>
					<th> 성별</th>
					<th> 생년월일</th>
					<th> 최초접속일자</th>
					<th> 최근접속일자</th>
<!-- 					<th> IP</th> --> <!--2017/11/14 IP 주석처리  -->
				</tr>
			</thead>
			<tbody>
			<c:forEach var="List" items="${pagedList.source}" varStatus="status">
								
				<tr>
					<td>${status.index+1+(pagedList.page-1)*pagedList.pageSize}</td>
		
					<td><a href="#" onclick="goPersonForm('${List.no_person}');"><strong>${List.no_person}</strong></a></td>   <!-- 고객번호 -->
					<td>
						<a href="#" onclick="goPersonForm('${List.no_person}');"><strong>${List.nm_person}</strong></a>
					</td>   <!-- 이름 -->
					<td>
						<c:choose>
							<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">${ufn:formatMaskHpType('2',List.hp)}</c:when>
							<c:otherwise>${ufn:formatTelNo(List.hp)}</c:otherwise>
						</c:choose>
					</td>   <!-- 휴대폰 -->
					<td>
						<c:choose>
							<c:when test="${List.c1_gender eq '1'}">
								<span class="glyphicon glyphicon-user male" aria-hidden="true" title="남성"></span>
							</c:when>
							<c:when test="${List.c1_gender eq '2'}">
								<span class="glyphicon glyphicon-user female" aria-hidden="true" title="여성"></span>
							</c:when>
						</c:choose>
					</td>
					<td>${List.ymd_birth}</td>   <!-- 생년월일 -->
					<td>${List.dt_frt}</td>   <!-- 최초접속일자 -->
					<td>${List.dt_lst}</td>   <!-- 최근접속일자 -->
					 <!--<td>192.168.0.xx</td>-->  <!--   IP -->
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

