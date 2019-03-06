<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script>
function getCounselHist(no_person, seq_counsel) {
	
	if(no_person == "" || seq_counsel == "") {
		alert("상담메모를 다시 선택하여주세요.(새로고침)");
		return;
	}
	
	var data = {"no_person":no_person,"seq_counsel":seq_counsel};
	vLoad("formCounsel","<c:url value='/counsel/getCounselHist.crz'/>",data);	
}
</script>
	<div id="counselHist">
		<div class="panel panel-primary">
		<div class="panel-heading">상담이력</div>
			<div class="panel-collapse">
				<form id="frmcounselHist" name="frmcounselHist">
				<input type="hidden" name="page" id="page">
				<input type="hidden" name="no_person" id="no_person" value="${person.no_person}">
				</form>
				<table id="tbl_listWorkerLoginHist" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
					<thead>
						<tr>
<!-- 								<th>순서</th> -->
							<th>상담유형</th>
							<th>상담내용</th>
							<th>일시</th>
						</tr>
					</thead>
					<tbody>
						<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
						<c:if test="${empty pagedList5.source}">
						<tr>
							<td colspan="8" height="100" align="center">
								상담 이력이 없습니다.
							</td>
						</tr>
						</c:if>
						<c:forEach var="List" items="${pagedList5.source}" varStatus="status">
							<c:choose>
								<c:when test="${status.count%2==0}">
									<c:set var="class_string" value="active"/>
								</c:when>
								<c:otherwise>
									<c:set var="class_string" value=""/>
								</c:otherwise>
							</c:choose>
							<tr class="${class_string}" onclick="getCounselHist('${List.no_person}','${List.seq_counsel}');" style="cursor: pointer;">
								<td>${ufn:getCodeName('cd_counsel_class', List.cd_counsel_class)}</td>
								<td>${List.etc_counsel}</td>
								<td>${List.dt_frt}</td>
					        </tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
