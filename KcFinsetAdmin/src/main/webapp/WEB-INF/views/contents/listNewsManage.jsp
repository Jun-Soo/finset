<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#listNewsManageTable')
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
		<table id="listNewsManageTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th> 날짜</th>
					<th> 작성일</th>
					<th> 뉴스원</th>
					<th> 키워드</th>
					<th> 제목</th>
					<th> 상태</th>
					<th> 사용</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="List" items="${pagedList.source}" varStatus="status">
				<tr onclick="viewIfrmNewsContent('${List.seq_news}', '${List.link}');" style="cursor:pointer;">
					<td>${List.dt_frt}</td>   <!-- 날짜 -->
					<td>${List.pub_date}</td> <!-- 작성일 -->
					<td>${List.news_company}</td> <!-- 뉴스원 -->
					<td>${List.search_query}</td>   <!-- 키워드 -->
					<td style="text-align: left;">${List.title}</td>   <!-- 제목 -->
					<td>${ufn:getCodeName('news_status', List.news_status)}</td> <!-- 상태 -->
					<td>${ufn:getCodeName('yn_use', List.yn_use)}</td>   <!-- 사용 -->
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

