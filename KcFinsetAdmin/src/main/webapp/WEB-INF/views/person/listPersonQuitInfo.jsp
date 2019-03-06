<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<div class="panel panel-primary">
	<div class="panel-collapse">
		<input type="hidden" name="listLength"
			value="${fn:length(pagedList.source)}" />
		<!-- List -->
		<table id="listPersonQuitInfo" class="table table-bordered tbl-info"
			cellspacing="0" width="100%">

			<thead>
				<tr>
					<th>순번</th>
					<th>회원번호</th>
					<th>고객명</th>
					<th>탈퇴일</th>
					<th>핸드폰번호</th>
				</tr>
			</thead>

			<tbody>
				<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
					<tr>
						<td colspan="8" height="100" align="center">검색결과가 없습니다</td>
					</tr>
				</c:if>
				<c:forEach var="List" items="${pagedList.source}" varStatus="status">
					<c:choose>
						<c:when test="${status.count%2==0}">
							<c:set var="class_string" value="active" />
						</c:when>
						<c:otherwise>
							<c:set var="class_string" value="" />
						</c:otherwise>
					</c:choose>

					<tr class="${class_string}">
						<!-- 순번 -->
						<td>${status.index+1+(pagedList.page-1)*pagedList.pageSize}</td>
						<!-- 번호 -->
						<td>${List.no_person}</td>
						<!-- 이름 -->
						<td>${List.nm_person}</td>
						<!-- 탈퇴일 -->
						<td>${List.dt_quit}</td>
						<!-- 핸드폰번호-->
						<td>${List.hp}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- //List -->
	</div>
	<div class="panel-footer">
		<span class="pull-left"> <span class="total-num em"><span></span>총건수
				: <em>${ufn:formatNumber(pagedList.recordCount)} 건</em></span>
		</span>
	</div>
</div>

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}" />

