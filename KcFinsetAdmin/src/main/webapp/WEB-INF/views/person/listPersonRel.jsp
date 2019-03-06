<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function() { 
		$('[data-toggle="popover"]').popover(); 
	}); 
	
	function goPrepareForm(no_prepare) {
		
		var openWindow = window.open("<c:url value='/prepare/formPrepareMain.crz'/>?no_prepare="+no_prepare, "open"+no_prepare, "width=1240, height="+(screen.availHeight-80)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}
</script>
<c:set var="cnt_all" value="${fn:length(listPerson['NM']) + fn:length(listPerson['BGN']) + fn:length(listPerson['HP'])}"/>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">연계정보</h3>
	</div>
	<div class="panel panel-primary">
		<div class="panel-collapse">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>항목</th>
						<th>이름</th>
						<th>생년월일</th>
						<th>전화번호</th>
						<th>서류함</th>
						<th>신청일</th>
						<th>신청금액</th>
						<th>승인금액</th>
						<th>승/접</th>
						<th>담당자</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${cnt_all eq '0'}">
						<tr>
							<td colspan="10" height="100" align="center">
								검색하여 주세요.
							</td>
						</tr>
					</c:if>
					<c:forEach var="List" items="${listPerson['NM']}" varStatus="status">
						<c:if test="${listPerson['NM'][0] eq null }">
							<tr>
								<th>이름</th>
								<td colspan="9">검색된 데이터가 없습니다.</td>
							</tr>
						</c:if>
						<tr>
							<c:if test="${status.count eq 1}">
								<th rowspan="${fn:length(listPerson['NM'])}">이름</th>
							</c:if>
							
							<c:set var="td_class" value=""/>
							<c:if test="${List.nm_person eq personVO.nm_person}">
								<c:set var="td_class" value="class='danger'"/>
							</c:if>
							<td ${td_class}><a href="#" onclick="goPrepareForm('${List.no_prepare}'); return false;"><strong>${List.nm_person}</strong></a></td>
							<td>${ufn:formatDate(List.ymd_birth)}</td>
							<td>
								<c:choose>
				              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">${ufn:formatMaskHp(List.hp)}</c:when>
				              		<c:otherwise>${List.hp}</c:otherwise>
				              	</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${List.cd_prepare_doc_box eq '20'}">
										<span data-toggle="popover" data-trigger="hover" data-placement="right" data-title="상담구분" data-content="${ufn:getCodeName('cd_prepare_class',List.cd_prepare_class)}">
											${ufn:getCodeName('cd_prepare_doc_box',List.cd_prepare_doc_box)}</span>
									</c:when>
									<c:when test="${List.cd_prepare_doc_box eq '60'}">
										<span data-toggle="popover" data-trigger="hover" data-placement="right" data-title="접수불가 사유" data-content="${ufn:getCodeName('cd_reject_cause',List.cd_reject_cause)}">
											${ufn:getCodeName('cd_prepare_doc_box',List.cd_prepare_doc_box)}</span>
									</c:when>
									<c:otherwise>
										${ufn:getCodeName('cd_prepare_doc_box',List.cd_prepare_doc_box)}
									</c:otherwise>
								</c:choose>
							</td>
							<td>${ufn:formatDate(List.ymd_frt)}</td>
							<td>${ufn:formatNumber(List.amt_apply)}</td>
							<td>${ufn:formatNumber(List.amt_approval)}</td>
							<td>${List.cnt_approval} / ${List.cnt_apply}</td>
							<td>${ufn:getWorkerInfo(List.id_prepare,'NM')}</td>
						</tr>
					</c:forEach>
					<c:forEach var="List" items="${listPerson['BGN']}" varStatus="status">
						<c:if test="${listPerson['BGN'][0] eq null }">
							<tr>
								<th>생년월일</th>
								<td colspan="9">검색된 데이터가 없습니다.</td>
							</tr>
						</c:if>
						<tr>
							<c:if test="${status.count eq 1}">
								<th rowspan="${fn:length(listPerson['BGN'])}">생년월일</th>
							</c:if>
							
							<c:set var="td_class" value=""/>
							<c:if test="${List.ymd_birth eq personVO.ymd_birth}">
								<c:set var="td_class" value="class='danger'"/>
							</c:if>
							<td><a href="#" onclick="goPrepareForm('${List.no_prepare}'); return false;"><strong>${List.nm_person}</strong></a></td>
							<td ${td_class}>${ufn:formatDate(List.ymd_birth)}</td>
							<td>
								<c:choose>
				              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">${ufn:formatMaskHp(List.hp)}</c:when>
				              		<c:otherwise>${List.hp}</c:otherwise>
				              	</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${List.cd_prepare_doc_box eq '20'}">
										<span data-toggle="popover" data-trigger="hover" data-placement="right" data-title="상담구분" data-content="${ufn:getCodeName('cd_prepare_class',List.cd_prepare_class)}">
											${ufn:getCodeName('cd_prepare_doc_box',List.cd_prepare_doc_box)}</span>
									</c:when>
									<c:when test="${List.cd_prepare_doc_box eq '60'}">
										<span data-toggle="popover" data-trigger="hover" data-placement="right" data-title="접수불가 사유" data-content="${ufn:getCodeName('cd_reject_cause',List.cd_reject_cause)}">
											${ufn:getCodeName('cd_prepare_doc_box',List.cd_prepare_doc_box)}</span>
									</c:when>
									<c:otherwise>
										${ufn:getCodeName('cd_prepare_doc_box',List.cd_prepare_doc_box)}
									</c:otherwise>
								</c:choose>
							</td>
							<td>${ufn:formatDate(List.ymd_frt)}</td>
							<td>${ufn:formatNumber(List.amt_apply)}</td>
							<td>${ufn:formatNumber(List.amt_approval)}</td>
							<td>${List.cnt_approval} / ${List.cnt_apply}</td>
							<td>${ufn:getWorkerInfo(List.id_prepare,'NM')}</td>
						</tr>
					</c:forEach>
					<c:forEach var="List" items="${listPerson['HP']}" varStatus="status">
						<c:if test="${listPerson['HP'][0] eq null }">
							<tr>
								<th>휴대폰</th>
								<td colspan="9">검색된 데이터가 없습니다.</td>
							</tr>
						</c:if>
						<tr>
							<c:if test="${status.count eq 1}">
								<th rowspan="${fn:length(listPerson['HP'])}">휴대폰</th>
							</c:if>
							
							<c:set var="td_class" value=""/>
							<c:if test="${List.hp eq personVO.hp}">
								<c:set var="td_class" value="class='danger'"/>
							</c:if>
							<td><a href="#" onclick="goPrepareForm('${List.no_prepare}'); return false;"><strong>${List.nm_person}</strong></a></td>
							<td>${ufn:formatDate(List.ymd_birth)}</td>
							<td ${td_class}>
								<c:choose>
				              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">${ufn:formatMaskHp(List.hp)}</c:when>
				              		<c:otherwise>${List.hp}</c:otherwise>
				              	</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${List.cd_prepare_doc_box eq '20'}">
										<span data-toggle="popover" data-trigger="hover" data-placement="right" data-title="상담구분" data-content="${ufn:getCodeName('cd_prepare_class',List.cd_prepare_class)}">
											${ufn:getCodeName('cd_prepare_doc_box',List.cd_prepare_doc_box)}</span>
									</c:when>
									<c:when test="${List.cd_prepare_doc_box eq '60'}">
										<span data-toggle="popover" data-trigger="hover" data-placement="right" data-title="접수불가 사유" data-content="${ufn:getCodeName('cd_reject_cause',List.cd_reject_cause)}">
											${ufn:getCodeName('cd_prepare_doc_box',List.cd_prepare_doc_box)}</span>
									</c:when>
									<c:otherwise>
										${ufn:getCodeName('cd_prepare_doc_box',List.cd_prepare_doc_box)}
									</c:otherwise>
								</c:choose>
							</td>
							<td>${ufn:formatDate(List.ymd_frt)}</td>
							<td>${ufn:formatNumber(List.amt_apply)}</td>
							<td>${ufn:formatNumber(List.amt_approval)}</td>
							<td>${List.cnt_approval} / ${List.cnt_apply}</td>
							<td>${ufn:getWorkerInfo(List.id_prepare,'NM')}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
