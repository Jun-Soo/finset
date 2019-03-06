<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script>
</script>
	<div id="excuteHist">
		<div class="panel panel-primary">
		<div class="panel-heading" onclick="goListExcute();" style="cursor: pointer;">실행이력</div>
			<div class="panel-collapse">
				<form id="frmexcuteHist" name="frmexcuteHist">
				<input type="hidden" name="page" id="page">
				<input type="hidden" name="no_person" id="no_person" value="${person.no_person}">
				</form>
				<table id="tbl_listWorkerLoginHist" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
					<thead>
						<tr>
							<th>날짜/시간</th>
							<th>분류</th>
							<th>금융사</th>
							<th>상품</th>
							<th>거치기간</th>
							<th>금리</th>
							<th>금액</th>
							<th>진행상태</th>
						</tr>
					</thead>
					<tbody>
						<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
						<c:if test="${empty pagedList3.source}">
						<tr>
							<td colspan="8" height="100" align="center">
								실행 이력이 없습니다.
							</td>
						</tr>
						</c:if>
						<c:forEach var="List" items="${pagedList3.source}" varStatus="status">
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
								<td> </td>
								<td>${ufn:getNmFc(List.cd_fc)}</td>
								<td>${ufn:getGoodsDetail(List.cd_goods, '')}</td>
								<td> </td>
								<td>${List.rto_loan}</td>
								<td>${ufn:formatNumber(List.amt_apply)}</td>
								<td>${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</td>
								
					        </tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
