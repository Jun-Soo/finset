<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<form name="frmCustList">
<table class="table table-bordered">
	<colgroup>
		<col width="50px"/>
		<col width="100px"/>
		<col width="100px"/>
		<col width="120px"/>
		<col width="50px"/>
		<col width="*"/>
		<col width="100px"/>
	</colgroup>
	<thead>
		<tr>
			<th>선택</th>
			<th>회원번호</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>성별</th>
			<th>휴대폰</th>
			<th>푸시허용여부</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="cnt" value="0"/>
		<c:forEach var="List" items="${listPerson}">
			<c:if test="${personForm.no_person ne List.no_person}">
			<tr>
				<td>
					<c:if test="${List.yn_push eq 'Y'}">
					<button type="button" class="btn btn-primary btn-xs" onclick="setNoPerson('${List.no_person}','${List.nm_person}','${ufn:formatDate(List.ymd_birth)}');"> 선택</button>
					</c:if>
				</td>
				<td>${List.no_person}</td>
				<td>
					<strong>${List.nm_person}</strong>
				</td>
				<td>${ufn:formatDate(List.ymd_birth)}</td>
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
				<td>
					<c:choose>
	              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">${ufn:formatMaskHp(List.hp)}</c:when>
	              		<c:otherwise>${List.hp}</c:otherwise>
	              	</c:choose>
				</td>
				<td>${List.yn_push}</td>
			</tr>
			<c:set var="cnt" value="${cnt+1}"/>
			</c:if>
		</c:forEach>
		<c:if test="${empty listPerson || cnt eq '0'}">
			<tr>
				<td colspan="8" height="100">
					검색결과가 없습니다.
				</td>
			</tr>
		</c:if>
	</tbody>
</table>
</form>