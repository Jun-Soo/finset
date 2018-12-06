<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {

	});
// 	var curTab = '${curTab}';
// 	function goBack(){
// 	    location.href = "<c:url value='/m/loanworker/frameLoanWorkerStep2.crz'/>?curTab="+curTab;
// 	}
</script>

</head>
<body>
	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
				</div>
				<h1>신용대출</h1>
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
				</div>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<div class="list-block">
				<div class="container-fluid prd-loan">
					<div class="list-heading">
                        <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${goodsVO.cd_fc}');"></span>
                        ${ufn:getNmFc(goodsVO.cd_fc)}
						<h2 class="prd-title">${goodsVO.nm_goods}</h2>
                        <div class="scroll-toggle">
                            <p>${goodsVO.desc_feature}&nbsp;</p>
                            <div class="loan-tag">${ufn:makeKeyWordList(goodsVO.keyword_list)}</div>
                        </div>
					</div>
					<div class="list-info">
						<p>${goodsVO.desc_feature}</p>
                        <dt>대출금리</dt>
                        <dd class="txt-point">
                            <c:set var="cd_ratio_type" value="${goodsVO.cd_ratio_type}" />
                            <c:if test="${goodsVO.cd_ratio_type.length() gt '2'}">
                                <c:set var="cd_ratio_type" value="변동,고정" />
                                <label>${cd_ratio_type}</label>
                            </c:if>
                            <c:if test="${goodsVO.cd_ratio_type.length() eq '2'}">
                                <c:set var="cd_ratio_type" value="${ufn:getCodeName('cd_ratio_type', goodsVO.cd_ratio_type)}" />
                                <label>${cd_ratio_type}</label>
                            </c:if>
                            <c:set var="rto_interest_from" value="${goodsVO.rto_interest_from}" />
                            <c:set var="rto_interest_to"   value="${goodsVO.rto_interest_to}" />
                            <c:if test="${rto_interest_from eq null and rto_interest_to eq null}">
                                -
                            </c:if>
                            <c:if test="${goodsVO.rto_interest_from ne null and goodsVO.rto_interest_to ne null}">
                                ${goodsVO.rto_interest_from}%&nbsp;~&nbsp;${goodsVO.rto_interest_to}%
                            </c:if>
                            <c:if test="${goodsVO.rto_interest_from ne null and goodsVO.rto_interest_to eq null}">
                                ${goodsVO.rto_interest_from}%&nbsp;~
                            </c:if>
                            <c:if test="${goodsVO.rto_interest_from eq null and goodsVO.rto_interest_to ne null}">
                                ~&nbsp;${goodsVO.rto_interest_to}%
                            </c:if>
                        </dd>
						<dl>
                            <dt>대출한도</dt>
                            <c:set var="v_amt_limit" value="" />
                            <c:set var="amt_limit" value="${goodsVO.amt_limit}" />
                            <c:if test="${amt_limit eq '0'}">
                                <c:set var="v_amt_limit" value="-" />
                            </c:if>
                            <c:if test="${v_amt_limit ne '0'}">
                                <c:set var="v_amt_limit" value="최대 ${ufn:formatNumberPattern(amt_limit,'###,###.##')}만원" />
                            </c:if>
                            <c:set var="v_cd_loan_term" value="" />
                            <c:set var="cd_loan_term" value="${goodsVO.cd_loan_term}" />
                            <c:if test="${cd_loan_term eq ''}">
                                <c:set var="v_cd_loan_term" value="-" />
                            </c:if>
                            <c:if test="${v_cd_loan_term ne '-'}">
                                <c:set var="v_cd_loan_term" value="${cd_loan_term}년" />
                            </c:if>
                            <dd>${v_amt_limit} / ${v_cd_loan_term}</dd>
						</dl>
						<div class="loan-tag">
							<%-- <span class="label">할인이벤트</span>
							<span class="label">모바일</span>
							<span class="label">직장인</span> --%>
						</div>
					</div>
				</div>
			</div>
                <c:if test="${    goodsVO.desc_loan        ne '' and goodsVO.desc_loan        ne null
                              and goodsVO.desc_limit       ne '' and goodsVO.desc_limit       ne null
                              and goodsVO.desc_paymethod   ne '' and goodsVO.desc_paymethod   ne null
                              and goodsVO.desc_repaymethod ne '' and goodsVO.desc_repaymethod ne null
                              and goodsVO.desc_commission  ne '' and goodsVO.desc_commission  ne null
                              and goodsVO.content_interest ne '' and goodsVO.content_interest ne null
                              and goodsVO.prefer_interest  ne '' and goodsVO.prefer_interest  ne null}">
                <div class="tab-content temp-item">
				<ul class="nav nav-outline nav-justified tabs">
                    <c:if test="${goodsVO.desc_loan        ne '' and goodsVO.desc_loan        ne null
                              and goodsVO.desc_limit       ne '' and goodsVO.desc_limit       ne null
                              and goodsVO.desc_paymethod   ne '' and goodsVO.desc_paymethod   ne null
                              and goodsVO.desc_repaymethod ne '' and goodsVO.desc_repaymethod ne null
                              and goodsVO.desc_commission  ne '' and goodsVO.desc_commission  ne null}">
					<li class="active"><a href="#tab1">상세정보</a></li>
                    </c:if>
                    <c:if test="${goodsVO.content_interest ne '' and goodsVO.content_interest ne null
                              and goodsVO.prefer_interest  ne '' and goodsVO.prefer_interest  ne null}">
                    <li><a href="#tab2">금리정보</a></li>
                    </c:if>
				</ul>
				<div class="tab-pane active" id="tab1">
					<!-- tab1 -->
					<div class="container-fluid">
						<c:if test="${goodsVO.desc_loan ne '' and goodsVO.desc_loan ne null}">
						<div class="tab-item">
							<h2 class="h2">대상고객</h2>
							<ul>
								<li>
									${goodsVO.desc_loan}
								</li>
							</ul>
						</div>
						</c:if>
                        <c:if test="${goodsVO.desc_limit ne '' and goodsVO.desc_limit ne null}">
						<div class="tab-item">
							<h2 class="h2">대출한도</h2>
							<ul>
								<li>
									${goodsVO.desc_limit}

								</li>
							</ul>
						</div>
                        </c:if>
                        <c:if test="${goodsVO.desc_paymethod ne '' and goodsVO.desc_paymethod ne null}">
                        <div class="tab-item">
							<h2 class="h2">대출기간</h2>
								<li>
								${goodsVO.desc_paymethod}

								</li>
						</div>
                        </c:if>
                        <c:if test="${goodsVO.desc_repaymethod ne '' and goodsVO.desc_repaymethod ne null}">
						<div class="tab-item">
							<h2 class="h2">상환방식</h2>
							<ul>
								<li>${goodsVO.desc_repaymethod}</li>
							</ul>
						</div>
                        </c:if>
                        <c:if test="${goodsVO.desc_commission ne '' and goodsVO.desc_commission ne null}">
						<div class="tab-item">
							<h2 class="h2">중도상환수수료</h2>
							<ul>
								<li>${goodsVO.desc_commission}</li>
							</ul>
						</div>
                        </c:if>
					</div>
					<!-- //tab1 -->
				</div>
				<div class="tab-pane" id="tab2">
					<!-- tab2 -->
					<div class="container-fluid">
                        <c:if test="${goodsVO.content_interest ne '' and goodsVO.content_interest ne null}">
						<div class="tab-item">
							<h2 class="h2">대출금리</h2>
							<ul>
								<li>${goodsVO.content_interest}</li>
							</ul>
						</div>
                        </c:if>
                        <c:if test="${goodsVO.prefer_interest ne '' and goodsVO.prefer_interest ne null}">
						<div class="tab-item">
							<h2 class="h2">우대금리</h2>
							<ul>
								<li>${goodsVO.prefer_interest}</li>
							</ul>
						</div>
                        </c:if>
                    </div>
					<!-- //tab2 -->
				</div>
                </c:if>
			</div>
		</section>
		<!-- //Content -->
	</div>
</body>
</html>
