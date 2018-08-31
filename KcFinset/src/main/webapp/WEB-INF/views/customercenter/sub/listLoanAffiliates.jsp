<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	setPgCnt("${pagedList.recordCount}");

	checkAllShow("${cd_goods_alliance}");
});


</script>
<c:choose>
	<c:when test="${empty pagedList.source}">
		<div class="data-none">
			<p>등록 상품이 없습니다.</p>
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="List" items="${pagedList.source}" varStatus="status">
		<div class="list-block">
			<div class="container-fluid prd-loan <c:if test="${cd_goods_alliance ne '01'}">prd-affiliates</c:if>" id="loan_product"> <!-- 일반상품에서는 체크박스 안보여줌 -->
				<a href="#url">
					<c:if test="${cd_goods_alliance ne '01'}"> <!-- 일반상품에서는 체크박스 안보여줌 -->
						<div class="checkbox chk-square"><input type="checkbox" id="s${List.cd_fc}${List.cd_goods}" name="goods_choice"
	                                <c:if test="${v_amt_limit eq '-'}">
	                                    disabled
	                                </c:if>
	                                       onchange="setCheckedHtml();buttonClassRemove();"></div>
                    </c:if>	
					<div class="list-heading" onclick="goodsFavoriteDetail('${List.cd_fc}','${List.cd_goods}','${List.yn_alliance}');">
						<li class="bank-title">
							<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>${ufn:getNmFc(List.cd_fc)}
						</li>
						<input type="hidden" name="cd_fc_each" value="${List.cd_fc}"/>
						<input type="hidden" name="cd_goods_each" value="${List.cd_goods}"/>
						<h2 class=" prd-title">${List.nm_goods}</h2>
						<p>${List.desc_feature}&nbsp;</p> 
						<div class="loan-tag">
							${ufn:makeKeyWordList(List.keyword_list)}
						</div>
					</div>
					<div class="list-info">
						<dl onclick="goodsFavoriteDetail('${List.cd_fc}','${List.cd_goods}','${List.yn_alliance}');">
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
                            <c:set var="rto_interest_from" value="${List.rto_interest_from}" />
                            <c:set var="rto_interest_to"   value="${List.rto_interest_to}" />
                            <c:if test="${rto_interest_from eq null and rto_interest_to eq null}">
                                -
                            </c:if>
                            <c:if test="${List.rto_interest_from ne null and List.rto_interest_to ne null}">
                                ${List.rto_interest_from}%&nbsp;~&nbsp;${List.rto_interest_to}%
                            </c:if>
                            <c:if test="${List.rto_interest_from ne null and List.rto_interest_to eq null}">
                                ${List.rto_interest_from}&nbsp;%&nbsp;~
                            </c:if>
                            <c:if test="${List.rto_interest_from eq null and List.rto_interest_to ne null}">
                                ~&nbsp;${List.rto_interest_to}%
                            </c:if>
                            </dd>
						</dl>
						<dl>
							<dt>대출한도</dt>
							<c:choose>
							<c:when test="${List.yn_alliance eq 'Y'}">
							<dd>최대 ${ufn:formatNumberMan(List.amt_limit)}만원 / ${List.cd_loan_term}년</dd>
							</c:when>
							<c:otherwise>
							<dd>최대 ${ufn:formatNumberPattern(List.amt_limit, "###,###")}만원 / ${List.cd_loan_term}년</dd>
							</c:otherwise>
							</c:choose>
						</dl>
						<div class="loan-tag">
							<%-- <span class="label">할인이벤트</span>
							<span class="label">모바일</span>
							<span class="label">직장인</span> --%>
						</div>						
						<div class="loan-btn">
							<div class="checkbox ico-loan ico-zzim">
								<input type="checkbox" id="z${List.cd_fc}${List.cd_goods}" value="Y" onchange="loanGoodsChoice('${List.cd_fc}','${List.cd_goods}', 'z${List.cd_fc}${List.cd_goods}', '${List.yn_alliance}');" <c:out value="${List.yn_favorite eq 'Y' ? 'checked' : ''}"/> ><label class="" for="z${List.cd_fc}${List.cd_goods}"></label>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div>
		</c:forEach>
		<c:if test="${cd_goods_alliance eq '02'}">
			<div class="btn-fixed-bottom affix-bottom" id="next_div">
			    <a role="button" class="btn btn-lg btn-block btn-disabled" onclick="goodsFavoriteInquiry();">금리/한도 조회하기</a>
			</div>
		</c:if>
	</c:otherwise>
</c:choose>
