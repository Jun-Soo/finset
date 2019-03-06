<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%-- <input type="hidden" name="listLength" value="${fn:length(listStatDailyUse)}" /> 엑셀 --%>
<table class="table table-bordered tbl-info" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>날짜</th>
			<th>가입자수</th>
			<th>접속자수</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${empty pagedList.source}">
		<tr>
			<td colspan="3" height="565px;" align="center">
				검색결과가 없습니다
			</td>
		</tr>
	</c:if>
	<c:forEach var="List" items="${pagedList.source}" varStatus="status">
		<tr>
			<td>${List.dt_frt}</td>
			<td class="right">
			<c:choose>
			<c:when test="${List.join_person_cnt ne 0}">
			<a onclick="viewDetail('${List.dt_frt}','ji')" style="cursor: pointer;">
			${ufn:formatNumber(List.join_person_cnt)}
			</a>
			</c:when>
			<c:otherwise>
			${ufn:formatNumber(List.join_person_cnt)}
			</c:otherwise>
			</c:choose>
			</td>
			<td class="right">
			<c:choose>
			<c:when test="${List.login_person_cnt ne 0}">
			<a onclick="viewDetail('${List.dt_frt}','cn')" style="cursor: pointer;">
			${ufn:formatNumber(List.login_person_cnt)}
			</a>
			</c:when>
			<c:otherwise>
			${ufn:formatNumber(List.login_person_cnt)}
			</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<tg:paging pagedListHolder="${pagedList}"/>
