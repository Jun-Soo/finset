<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<input type="hidden" name="listLength" value="${fn:length(listStatDailyUseDetail)}" />
<div cellspacing="0" width="100%">
<table class="table table-bordered tbl-info" style="margin:0px;">
	<colgroup>
		<col width="25%">
		<col width="25%">
		<col width="13%">
		<col width="12%">
		<col width="25%">
	</colgroup>
	<thead>
		<tr>
			<th>회원번호</th>
			<th>이름</th>
			<th>신용등급</th>
			<th>부채건수</th>
			<th>부채잔액</th>
		</tr>
	</thead>
</table>
</div> 
<div cellspacing="0" width="100%" style="height: 560px; overflow: scroll;">
<table class="table table-bordered tbl-info" style="overflow:hidden;">
	<colgroup>
		<col width="26%">
		<col width="25%">
		<col width="13%">
		<col width="13%">
		<col width="23%"> 
	</colgroup> 
	<tbody id="listDetailTbody">
	<c:if test="${empty listStatDailyUseDetail}">
		<tr>
			<td colspan="5" height="520px;" align="center">
				검색결과가 없습니다
			</td>
		</tr>
	</c:if>
	<c:forEach var="List" items="${listStatDailyUseDetail}">
		<tr>
			<td>${List.no_person}</td>
			<td>${List.nm_person}</td>
			<td>${List.grade_credit}</td>
			<td class="right">${ufn:formatNumber(List.debt_cnt)}</td>
			<td class="right">${List.debt_amt_remain}만원</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>