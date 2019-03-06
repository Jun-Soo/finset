<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function () {
		<c:if test="${!empty no_apply}">
			loadApplyMemo(null,"${no_apply}");
		</c:if>
		
		$('[data-toggle="popover"]').popover();
	});
</script>
<!-- //Nav tabs -->
<div class="panel panel-primary">
	<div class="panel-heading">사전접수이력</div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<colgroup>
				<col width="15%">
				<col width="*">
				<col width="*">
				<col width="12%">
				<col width="15%">
				<col width="10%">
				<col width="22%">
			</colgroup>
			<thead>
				<tr>
					<th>접수일</th>
					<th>광고매체</th>
					<th>상품구분</th>
					<th>신청금</th>
					<th>승/접</th>
					<th>승인금</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="List" items="${listPrepareHist}">
<%-- 					<tr onclick="loadApplyHist(this,'${List.no_prepare}');" style="cursor: pointer;" <c:if test="${prepareVO.no_prepare eq List.no_prepare}">class="warning"</c:if>> --%>
										<tr  <c:if test="${prepareVO.no_prepare eq List.no_prepare}"></c:if>>
					<td>${List.ymd_frt_short}</td>
						<td>${ufn:getCodeName('cd_advertisement', List.cd_advertisement)}</td>
						
						<c:choose>
							<c:when test="${fn:length(ufn:getCodeName('cd_goods_type', List.cd_goods_type)) > 8}">
								<td>
									<span data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="${ufn:getCodeName('cd_goods_type', List.cd_goods_type)}">
										${fn:substring(ufn:getCodeName('cd_goods_type', List.cd_goods_type),0,8)}
									</span>
								</td>
							</c:when>
							<c:otherwise>
								<td>${ufn:getCodeName('cd_goods_type', List.cd_goods_type)}</td>
							</c:otherwise>
						</c:choose>
						
						<td>${ufn:formatNumber(List.amt_apply)}</td>
						<td>${List.cnt_approval} / ${List.cnt_apply}</td>
						<td>${ufn:formatNumber(List.amt_approval)}</td>
						<td>${ufn:getCodeName('cd_prepare_doc_box', List.cd_prepare_doc_box)}
							<c:choose>
			              		<c:when test="${List.cd_prepare_doc_box eq '20'}">
				              		(${ufn:getCodeName('cd_prepare_class', List.cd_prepare_class)})
			              		</c:when>
			              		<c:when test="${List.cd_prepare_doc_box eq '50'}">
			              			(${ufn:getCodeName('cd_apply_class', List.cd_prepare_class)})
			              		</c:when>
			              		<c:when test="${List.cd_prepare_doc_box eq '60'}">
			              			(${ufn:getCodeName('cd_reject_cause',List.cd_reject_cause)})
			              		</c:when>
			              	</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!-- 접수이력 -->
<div id="applySummary">
	<%@ include file="/WEB-INF/views/apply/listApplySummary.jsp" %>
</div>

<!-- 금융사 메모 리스트 -->
<div id="listApplyMemo" style="display: none;">
	<%@ include file="/WEB-INF/views/counsel/listApplyMemo.jsp"%>
</div>

<!-- 심사처리 -->
<div id="formModifyApply" style="display: none;">
	<c:if test="${ufn:isApprAuth('102001',sessionScope.STR_APPROVAL)}">
		<%@ include file="/WEB-INF/views/apply/formModifyApply.jsp"%>
	</c:if>
</div>

<!-- 상담메모 폼 -->
<div id="formCounsel">
</div>
	
<!-- 상담메모 리스트 -->
<div id="listCounselInfo">
</div>