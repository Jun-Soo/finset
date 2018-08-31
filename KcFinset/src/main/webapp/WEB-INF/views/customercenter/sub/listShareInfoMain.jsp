<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

</script>
<c:choose>
	<c:when test="${empty pagedList.source}">
		<div class="data-none">
			<p>조회한 내역이 없습니다.</p>
		</div>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${type_list eq 'offer'}"> <!-- 요청list -->
				<c:forEach var="List" items="${pagedList.source}" varStatus="status">
				<div class="container-fluid <c:if test='${List.share_status eq \'02\'}'>div-require</c:if>" 
					onclick="
					<c:choose>
					<c:when test="${List.share_status eq '01'}"> <!-- 재요청 화면으로 이동 -->
						goSetting('01',${List.seq_share});
					</c:when>
					<c:when test="${List.share_status eq '02'}"> <!-- 상세화면으로 이동 -->
						<c:if test="${List.yn_offer eq 'Y' and List.yn_itgt_mngm eq 'N'}"> <!-- 정보제공항목만 있는경우 -->
							<c:if test="${List.dt_end_offer ge currentDate}"> <!-- 정보제공종료일이 현재날짜보다 크거나 같을때만 열수 있음 -->
								viewDetail(${List.seq_share});
							</c:if>
						</c:if>
						<c:if test="${List.yn_itgt_mngm eq 'Y'}"> <!-- 정보통합항목이 있는경우 -->
							viewDetail(${List.seq_share});
						</c:if>
					</c:when>
					</c:choose>
					"
				>
					<div class="require-title">
						<div class="list-heading">
							<li>${List.offer_nm_person}</li>
							<div class="label-right">
							<c:if test="${List.yn_offer eq 'Y'}"><label class="label-type">정보공유</label></c:if>
							<c:if test="${List.yn_itgt_mngm eq 'Y'}"><label class="label-type">통합관리</label></c:if>
							</div>
						</div>
						<div class="list-info">
							<dl>
								<dt>
								${List.offer_hp} | 
								<c:choose>
								<c:when test="${List.share_status eq '01'}"> <!-- 요청중 -->
									${fn:substring(List.dt_frt,2,4)}년&nbsp;${fn:substring(List.dt_frt,4,6)}월&nbsp;${fn:substring(List.dt_frt,6,8)}일
									|
								</c:when>
								<c:when test="${List.share_status eq '02'}"> <!-- 승인 -->
									${fn:substring(List.dt_stt_offer,2,4)}년&nbsp;${fn:substring(List.dt_stt_offer,4,6)}월&nbsp;${fn:substring(List.dt_stt_offer,6,8)}일
									<c:if test="${List.yn_offer eq 'Y' and List.yn_itgt_mngm eq 'N'}"> <!-- 정보제공항목만 있는경우 종료일 입력 -->  
									&nbsp;~&nbsp;${fn:substring(List.dt_end_offer,2,4)}년&nbsp;${fn:substring(List.dt_end_offer,4,6)}월&nbsp;${fn:substring(List.dt_end_offer,6,8)}일
									</c:if>
									 |
								</c:when>
								</c:choose>
								<c:choose>
								<c:when test="${List.share_status eq '01'}">
									요청중
								</c:when>
								<c:when test="${List.share_status eq '02'}">
									승인
								</c:when>
								<c:when test="${List.share_status eq '03'}">
									거절
								</c:when>
								</c:choose>
								</dt>
							</dl>
						</div>
					</div>
				</div>
				</c:forEach>
			</c:when>
			<c:otherwise> <!-- 허용list -->
				<c:set var="offerApprovCnt" value="0" />
				<c:forEach var="List" items="${pagedList.source}" varStatus="status">
				<div class="container-fluid <c:if test='${List.share_status eq \'02\'}'>div-require</c:if>"
					onclick="
					<c:choose>
					<c:when test="${List.share_status eq '01'}"> <!-- 허용/거절 화면으로 이동 -->
						goSetting('02',${List.seq_share}); 
					</c:when>
					<c:when test="${List.share_status eq '02'}"> <!-- 변경/해지 화면으로 이동 -->
						<c:if test="${List.yn_offer eq 'Y' and List.yn_itgt_mngm eq 'N'}"> <!-- 정보제공항목만 있는경우 -->
							<c:if test="${List.dt_end_offer >= currentDate}"> <!-- 정보제공종료일이 현재날짜보다 크거나 같을때만 열수 있음 -->
								goSetting('03',${List.seq_share});
							</c:if>
						</c:if>
						<c:if test="${List.yn_itgt_mngm eq 'Y'}"> <!-- 정보통합항목이 있는경우 -->
							goSetting('03',${List.seq_share});
						</c:if>
					</c:when>
					</c:choose>
					"
				>
					<div class="require-title">
						<div class="list-heading">
							<li>${List.req_nm_person}</li>
							<div class="label-right">
							<c:if test="${List.yn_offer eq 'Y'}"><label class="label-type">정보공유</label></c:if>
							<c:if test="${List.yn_itgt_mngm eq 'Y'}"><label class="label-type">통합관리</label></c:if>
							</div>
						</div>
						<div class="list-info">
							<dl>
								<dt>
								${List.req_hp} | 
								<c:choose>
								<c:when test="${List.share_status eq '01'}"> <!-- 요청중 -->
									${fn:substring(List.dt_frt,2,4)}년&nbsp;${fn:substring(List.dt_frt,4,6)}월&nbsp;${fn:substring(List.dt_frt,6,8)}일
									|
								</c:when>
								<c:when test="${List.share_status eq '02'}"> <!-- 승인 -->
									${fn:substring(List.dt_stt_offer,2,4)}년&nbsp;${fn:substring(List.dt_stt_offer,4,6)}월&nbsp;${fn:substring(List.dt_stt_offer,6,8)}일
									<c:if test="${List.yn_offer eq 'Y' and List.yn_itgt_mngm eq 'N'}"> <!-- 정보제공항목만 있는경우 종료일 입력 -->  
									&nbsp;~&nbsp;${fn:substring(List.dt_end_offer,2,4)}년&nbsp;${fn:substring(List.dt_end_offer,4,6)}월&nbsp;${fn:substring(List.dt_end_offer,6,8)}일
									</c:if>
									|
								</c:when>
								</c:choose>
								<c:choose>
								<c:when test="${List.share_status eq '01'}">
									요청중
								</c:when>
								<c:when test="${List.share_status eq '02'}">
									공유중
								</c:when>
								<c:when test="${List.share_status eq '03'}">
									거절
								</c:when>
								</c:choose>
								</dt>
							</dl>
						</div>
					</div>
				</div>
				<c:if test="${List.share_status eq '02'}">
					<c:set var="offerApprovCnt" value="${offerApprovCnt+1}"/>
				</c:if>
				</c:forEach>
				<c:if test="${offerApprovCnt > 0}">
					<div class="btn-fixed-bottom affix-bottom">
						<a role="button" class="btn btn-lg btn-block btn-primary" id="btnAllCancel" onclick="cancelAllItems();">모두 해지하기</a>
					</div>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
<c:if test="${type_list eq 'offer'}"> 
	<div class="btn-fixed-bottom affix-bottom">
		<a role="button" class="btn btn-lg btn-block btn-primary" id="btnNewRequest" onclick="createNewRequest();">신규 요청하기</a>
	</div>
</c:if>
