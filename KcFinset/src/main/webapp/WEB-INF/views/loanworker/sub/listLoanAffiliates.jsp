<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
    var listLength = 0;
    listLength = "${count}";
    totalPage = "${pagedList.pageCount}";
    setListCount(listLength);
    $("#divAllCheck").show();
</script>
<c:choose>
    <c:when test="${empty pagedList.source}">
        <div class="data-none">
            <p>신청 가능한 상품이 없습니다.</p>
        </div>
    </c:when>
    <c:otherwise>
        <c:forEach var="List" items="${pagedList.source}" varStatus="status">
            <%--대출한도--%>
            <c:set var="v_amt_limit" value="" />
            <c:set var="amt_limit" value="${List.amt_limit}" />
            <c:if test="${amt_limit eq '0' or amt_limit eq null}">
                <c:set var="v_amt_limit" value="-" />
            </c:if>
            <c:if test="${v_amt_limit ne '-'}">
                <c:set var="v_amt_limit" value="최대 ${ufn:formatNumberPattern(amt_limit,'###,###.##')}만원" />
            </c:if>
            <div class="list-block">
            	<div class="container-fluid prd-loan prd-affiliates" id="loan_product">
                    <a href="#url">
	                    <div class="ctrl-item">
							<div class="checkbox chk-square">
                                <input type="checkbox" id="s${List.cd_fc}${List.cd_goods}" name="goods_choice"
                                <c:if test="${v_amt_limit eq '-'}">
                                    disabled
                                </c:if>
                                       onchange="setCheckedHtml();buttonClassRemove();">
                            </div>
						</div>
                        <div class="list-heading" onclick="loanGoodsDetail('${List.cd_fc}','${List.cd_goods}');">
                            <li class="bank-title">
                                <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>${ufn:getNmFc(List.cd_fc)}
                            </li>
                            <input type="hidden" name="cd_fc_each" value="${List.cd_fc}"/>
                            <input type="hidden" name="cd_goods_each" value="${List.cd_goods}"/>
                            <h2 class="prd-title">${List.nm_goods}</h2>
                            <p>${List.desc_feature}&nbsp;</p>
                            <div class="loan-tag">
                                    ${ufn:makeKeyWordList(List.keyword_list)}
                            </div>
                        </div>
                        <div class="list-info">
                            <dl onclick="loanGoodsDetail('${List.cd_fc}','${List.cd_goods}');">
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
                                        ${List.rto_interest_from}%&nbsp;~&nbsp;${List.rto_interest_to}%
                                    </c:if>
                                    <c:if test="${List.rto_interest_from ne null and List.rto_interest_to eq null}">
                                        ${List.rto_interest_from}%&nbsp;~
                                    </c:if>
                                    <c:if test="${List.rto_interest_from eq null and List.rto_interest_to ne null}">
                                        ~&nbsp;${List.rto_interest_to}%
                                    </c:if>
                                </dd>
                            </dl>
                            <dl>
                                <dt>대출한도</dt>

                                <c:set var="v_cd_loan_term" value="" />
                                <c:set var="cd_loan_term" value="${List.cd_loan_term}" />
                                <c:if test="${cd_loan_term eq '' or cd_loan_term eq null}">
                                    <c:set var="v_cd_loan_term" value="-" />
                                </c:if>
                                <c:if test="${v_cd_loan_term ne '-'}">
                                    <c:set var="v_cd_loan_term" value="${cd_loan_term}년" />
                                </c:if>
                                <dd>${v_amt_limit} / ${v_cd_loan_term}</dd>
                            </dl>
                        </div>
                        <div class="loan-btn">
                            <%-- <div class="checkbox ico-loan ico-choice">
                                <input type="checkbox" id="s${List.cd_fc}${List.cd_goods}" name="goods_choice" onchange="buttonClassRemove();">
                                <label for="s${List.cd_fc}${List.cd_goods}"></label> <!--  $(this).val() -->
                            </div> --%>
                            <div class="checkbox ico-loan ico-zzim">
                                <input type="checkbox" id="z${List.cd_fc}${List.cd_goods}" value="Y"
                                       onchange="loanGoodsChoice('${List.cd_fc}','${List.cd_goods}', 'z${List.cd_fc}${List.cd_goods}', 'Y');" <c:out value="${List.yn_favorite eq 'Y' ? 'checked' : ''}"/> >
                                <label for="z${List.cd_fc}${List.cd_goods}"></label>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>
