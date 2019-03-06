<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	
});

</script>
<!-- <div class="panel panel-primary"> -->
<!-- 	<div class="panel-collapse"> -->
		<!-- List -->
		<table id="listApplyResultInfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th> 순번 </th>
					<th> 은행명</th>
					<th> 상품명 </th>
					<th> 조회일시 </th>
					<th> 이름 </th>
					<th> 금리 </th>
					<th> 한도(만원) </th>
					<th> 조회결과 </th>
					<th> 상태 </th>
				</tr>
			</thead>
			<tbody>
				<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="9" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
				</c:if>
				<c:forEach var="List" items="${pagedList.source}" varStatus="status">
				<tr>
					<td>${List.rnum }</td>
					<td>${ufn:getNmFc(List.cd_fc)}</td> 
					<td>${List.nm_goods }</td>
					<td>${List.dt_receive }</td>
					<td>${List.nm_person }</td>
					<td>${List.rto_loan }%</td>
					<td>${List.amt_limit }</td>
					<td>
					<c:choose>
   						<c:when test="${List.yn_loan eq 'Y' and List.apply_cnt eq 0}">
							신청가능
						</c:when>
						<c:when test="${List.yn_loan eq 'N' and List.yn_receive eq 'Y'}">
							신청불가능
						</c:when>
						<c:when test="${List.yn_loan eq 'Y' and List.apply_cnt gt 0}">
							신청완료
						</c:when>
						<c:when test="${empty List.yn_loan}">
							조회오류
						</c:when>
					</c:choose>
					</td>
					<td>${ufn:getCodeName('cd_apply_doc_box',List.cd_apply_doc_box) }</td>
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

