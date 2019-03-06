<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script>
</script>
	<div id="activityHist">
		<div class="panel panel-primary">
		<div class="panel-heading">활동로그</div>
			<div class="panel-collapse">
				<form id="frmactivityHist" name="frmactivityHist">
				<input type="hidden" name="page" id="page">
				<input type="hidden" name="no_person" id="no_person" value="${person.no_person}">
				</form>
				<table id="tbl_listWorkerLoginHist" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
					<thead>
						<tr>
<!-- 								<th>순서</th> -->
							<th>날짜/시간</th>
							<th>URL</th>
							<!-- <th>화면명</th> -->
						</tr>
					</thead>
					<tbody>
						<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
						<c:if test="${empty pagedList2.source}">
						<tr>
							<td colspan="8" height="100" align="center">
								활동 이력이 없습니다.
							</td>
						</tr>
						</c:if>
						<c:forEach var="List" items="${pagedList2.source}" varStatus="status">
							<c:choose>
								<c:when test="${status.count%2==0}">
									<c:set var="class_string" value="active"/>
								</c:when>
								<c:otherwise>
									<c:set var="class_string" value=""/>
								</c:otherwise>
							</c:choose>
						
							<tr class="${class_string}">
								<td>${ufn:formatDate(List.dt_frt)}</td>
								<td style="text-align: left;">${List.nm_dir}</td>
								<%-- <td>${List.nm_page}</td> --%>
					        </tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
