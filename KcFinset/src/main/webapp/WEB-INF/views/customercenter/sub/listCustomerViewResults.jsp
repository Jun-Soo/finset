<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	setPgCnt("${pagedList.recordCount}");
});
</script>
<c:choose>
	<c:when test="${empty pagedList.source}">
		<div class="data-none">
			<p>조회한 내역이 없습니다.</p>
		</div>
	</c:when>
	<c:otherwise>
		<c:set var="dt_receive" value=""/>
		<c:set var="t_dt_receive" value=""/>
		<c:forEach var="List" items="${pagedList.source}" varStatus="status">
			<c:set var="t_dt_receive" value="${List.dt_receive}" />

		<div class="list-block">
			<c:if test="${t_dt_receive ne dt_receive}">
			<h2 class="h2 h-date">
				<fmt:parseDate var="dtReceive" value="${List.dt_receive}" pattern="yyyyMMdd" /> 
				<fmt:formatDate value="${dtReceive}" pattern="yyyy.MM.dd" />
			</h2>
				<c:set var="dt_receive" value="${t_dt_receive}" />
			</c:if>
			<div class="container-fluid prd-loan prd-affiliates" id="loan_product">
				<input type="hidden" name="cd_fc_each" value="${List.cd_fc}"/>
				<input type="hidden" name="cd_goods_each" value="${List.cd_goods}"/>
				<input type="hidden" name = "list_cd_goods"    value="${List.cd_goods}" />
				<input type="hidden" name = "list_cd_fc"           value="${List.cd_fc}" />
				<input type="hidden" name = "list_no_prepare" value="${List.no_prepare}" />
				<input type="hidden" name = "list_amt_limit"     value="${List.amt_limit}" />
				<input type="hidden" name = "list_no_bunch"     value="${List.no_bunch}" />
				<input type="hidden" name = "list_cd_goods_gubun"     value="${List.cd_goods_gubun}" />
				
				<a href="#url">
					<div class="ctrl-item">
						<div class="checkbox chk-square">
							<c:choose>
		   						<c:when test="${List.yn_loan eq 'Y' and List.apply_cnt eq 0}"> <!-- 선택가능(신청가능) -->
									<input type="checkbox" name="goods_choice" id="s${status.count}" onclick="goodsChk(this)"><label class="" for="s${status.count}"></label>
								</c:when>
								<c:otherwise> <!-- 선택불가능 -->
									<input type="checkbox" name="goods_choice" id="s${status.count}" disabled="disabled"><label class="" for="s${status.count}"></label>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="list-heading" onclick="goodsFavoriteDetail('${List.cd_fc}','${List.cd_goods}');">
						<c:choose>
	   						<c:when test="${List.yn_loan eq 'Y' and List.apply_cnt eq 0}">
								<span class="label label-status status-blue">신청가능</span>
							</c:when>
							<c:when test="${List.cd_status ne '04' and List.yn_loan eq 'N' and (List.yn_receive eq 'Y' or List.yn_receive eq null)}">
								<span class="label label-status status-gray">신청불가능</span>
							</c:when>
							<c:when test="${List.yn_loan eq 'Y' and List.apply_cnt gt 0}">
								<span class="label label-status status-green">신청완료</span>
							</c:when>
							<c:when test="${List.cd_status eq '02'}">
								<span class="label label-status status-red">신청불가능</span>
							</c:when>
							<c:when test="${List.cd_status eq '03'}">
								<span class="label label-status status-red">조회실패</span>
							</c:when>
							<c:when test="${List.cd_status eq '04'}">
								<span class="label label-status status-red">조회중</span>
							</c:when>
						</c:choose>
						<li class="bank-title">
                           <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
                           ${ufn:getNmFc(List.cd_fc)}
                        </li>
                        <h2 class=" prd-title">${List.nm_goods}</h2>
					</div>
					<div class="list-info">
						<dl>
							<dt>대출한도</dt>
							<dd>${ufn:formatNumberMan(List.amt_limit)} 만원</dd>
						</dl>
						<dl onclick="goodsFavoriteDetail('${List.cd_fc}','${List.cd_goods}');">
							<dt>대출금리</dt>
							<dd class="txt-point">
							<c:set var="cd_type_interest" value="${List.cd_type_interest}" />
	                        <c:if test="${List.cd_type_interest.length() gt '2'}">
	                            <c:set var="cd_type_interest" value="변동,고정" />
	                            <label>${cd_type_interest}</label>
	                        </c:if>
	                        <c:if test="${List.cd_type_interest.length() eq '2'}">
	                            <c:set var="cd_type_interest" value="${ufn:getCodeName('cd_ratio_type', List.cd_type_interest)}" />
	                            <label>${cd_type_interest}</label>
	                        </c:if>
							${List.rto_loan} %</dd>
						</dl>
						<dl>
							<dt>대출기간</dt>
							<dd>${List.year_term * 12} 개월</dd>
						</dl>
						<dl>
							<dt>상환방식</dt>
							<dd>
								<c:set var="cdTypePay" value="${fn:split(List.cd_type_pay,',')}" />
								<c:forEach var="typePay" items="${cdTypePay}" varStatus="i">
									<c:if test="${i.current > 1}">
									, &nbsp;
									</c:if>
									${ufn:getCodeName("cd_type_pay",typePay)}상환
								</c:forEach> 
							</dd>
						</dl>
					</div>
					<%-- <div class="loan-btn">
						<div class="checkbox ico-loan ico-choice">
							<input type="checkbox" name="goods_choice" id="s${status.count}" onclick="goodsChk(this)"><label class="" for="s${status.count}"></label>
						</div>
					</div> --%>
				</a>
			</div>
		</div>
		</c:forEach>
	</c:otherwise>
</c:choose>
