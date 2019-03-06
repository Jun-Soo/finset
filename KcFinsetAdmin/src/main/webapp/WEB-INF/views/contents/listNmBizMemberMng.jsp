<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
});
</script>
<div cellspacing="0" width="100%">
<table class="table table-bordered tbl-info" style="margin:0px;">
	<colgroup>
		<col width="24%">
		<col width="24%">
		<col width="24%">
		<col width="28%">
	</colgroup>
	<thead>
		<tr>
			<th>가맹점명</th>
			<th>사업자번호</th>
			<th>분류</th>
			<th>항목</th>
		</tr>
	</thead>
</table>
</div> 
<div cellspacing="0" width="100%" style="height: 300px; overflow: scroll;">
<table id="tbl_listConsumeCardMng" class="table table-bordered tbl-info" style="overflow:hidden;">
	<colgroup>
		<col width="25%">
		<col width="25%">
		<col width="25%">
		<col width="25%">
	</colgroup>
	<tbody>
	<c:choose>
	<c:when test="${empty nmBizList}">
		<td colspan="4">검색한 데이터가 없습니다</td>
	</c:when>
	<c:otherwise>
		<c:forEach var="List" items="${nmBizList}" varStatus="status">
			<tr onclick="getNmBizMemberMng('${List.nm_member}','${List.no_biz_member}');" style="cursor: pointer;">
				<td>${List.nm_member}</td>
				<td>${List.no_biz_member}</td>
				<td>${List.nm_class}</td>
				<td>${List.nm_type}</td>
			</tr>
		</c:forEach>
	</c:otherwise>
	</c:choose>
	</tbody>
</table>
</div>