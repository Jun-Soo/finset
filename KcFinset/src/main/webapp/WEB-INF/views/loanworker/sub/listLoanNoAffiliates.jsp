<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
    var listLength = 0;
    listLength = "${count}";
    totalPage = "${pagedList.pageCount}";
    setListCount(listLength);
    $("#divAllCheck").hide();
</script>
<c:choose>
	<c:when test="${empty pagedList.source}">
		<div class="data-none">
			<p>신청 가능한 상품이 없습니다.</p>
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="List" items="${pagedList.source}" varStatus="status">
		<div class="list-block prd-list">
			<div class="container-fluid prd-loan" id="loan_product">
				<a href="#url">
					<div class="list-heading" onclick="loanGoodsBankDetail('${List.cd_fc}','${List.cd_non_goods}');">
						<li class="bank-title">
							<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>${ufn:getNmFc(List.cd_fc)}
							<c:if test="${List.yn_alliance eq 'Y'}">
								<span class="alliance-logo"  />
							</c:if>
						</li>
						<!-- <li class="bank-title">${ufn:getNmFc(List.cd_fc)}</li> -->
						<input type="hidden" name="cd_fc_each" value="${List.cd_fc}"/>
						<input type="hidden" name="cd_goods_each" value="${List.cd_non_goods}"/>
						<h2 class="prd-title">${List.nm_goods}</h2>
						<p>${List.desc_feature}&nbsp;</p>
						<div class="loan-tag">
							${ufn:makeKeyWordList(List.keyword_list)}
						</div>
					</div>
					<div class="list-info">
						<dl onclick="loanGoodsBankDetail('${List.cd_fc}','${List.cd_non_goods}');">
							<dt>대출금리</dt>
							<dd class="txt-point">
								<c:set var="cd_ratio_type" value="${List.cd_ratio_type}" />
								<c:if test="${List.cd_ratio_type.length() gt '2'}">
									<c:set var="cd_ratio_type" value="변동,고정" />
                                    <label>${cd_ratio_type}</label>
                                </c:if>
								<c:if test="${List.cd_ratio_type.length() eq '2'}">
									<c:set var="cd_ratio_type" value="${ufn:getCodeName('cd_ratio_type', List.cd_ratio_type)}" />
                                    <label>${cd_ratio_type}</label>
								</c:if>
                                <c:set var="rto_interest_from" value="${List.rto_interest_from}" />
                                <c:set var="rto_interest_to"   value="${List.rto_interest_to}" />
                                <c:if test="${rto_interest_from eq null and rto_interest_to eq null}">
                                    -
								</c:if>
                                <c:if test="${List.rto_interest_from ne null and List.rto_interest_to ne null}">
                                    ${List.rto_interest_from}&nbsp;%&nbsp;~&nbsp;${List.rto_interest_to}&nbsp;%
                                </c:if>
                                <c:if test="${List.rto_interest_from ne null and List.rto_interest_to eq null}">
                                    ${List.rto_interest_from}&nbsp;%&nbsp;~
                                </c:if>
                                <c:if test="${List.rto_interest_from eq null and List.rto_interest_to ne null}">
                                    ~&nbsp;${List.rto_interest_to}&nbsp;%
                                </c:if>
							</dd>
						</dl>
						<dl>
							<dt>대출한도</dt>
                            <c:set var="v_desc_max_limit" value="" />
                            <c:set var="desc_max_limit" value="${List.desc_max_limit}" />
							<c:if test="${desc_max_limit eq '0'}">
								<c:set var="v_desc_max_limit" value="-" />
							</c:if>
							<c:if test="${v_desc_max_limit ne '-'}">
								<c:set var="v_desc_max_limit" value="최대 ${ufn:formatNumberPattern(desc_max_limit,'###,###.##')}만원" />
							</c:if>
                            <c:set var="max_loan_term" value="${List.max_loan_term}" />
                            <c:set var="v_max_loan_term" value="" />
                            <c:if test="${max_loan_term eq ''}">
								<c:set var="v_max_loan_term" value="-" />
							</c:if>
							<c:if test="${v_max_loan_term ne '-'}">
								<c:set var="v_max_loan_term" value="${max_loan_term}년" />
							</c:if>
							<dd>${v_desc_max_limit} / ${v_max_loan_term}</dd>
						</dl>
					</div>
					<div class="loan-btn">
						<div class="checkbox ico-loan ico-zzim">
							<input type="checkbox" id="z${List.cd_fc}${List.cd_non_goods}" value="Y"
                                   onchange="loanGoodsChoice('${List.cd_fc}','${List.cd_non_goods}', 'z${List.cd_fc}${List.cd_non_goods}', 'N');"
                                <c:out value="${List.yn_favorite eq 'Y' ? 'checked' : ''}"/> ><label class="" for="z${List.cd_fc}${List.cd_non_goods}"></label>
						</div>
					</div>
				</a>
			</div>
		</div>
		</c:forEach>
	</c:otherwise>
</c:choose>
