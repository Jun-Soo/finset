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
	 	// progress-bar
	 	$(".progress-bar-credit .credit-bar").delay(0).queue(function(){
	 		$(this).css("width", ("${creditSum.sum_amt_contract-creditSum.sum_amt_remain}"/"${creditSum.sum_amt_contract}")*100+"%");
	 	});
	 	
	 	$(".progress-bar-credit .loan-bar").delay(0).queue(function(){
	 		$(this).css("width", ("${loanSum.sum_amt_contract-loanSum.sum_amt_remain}"/"${loanSum.sum_amt_contract}")*100+"%");
	 	});
	 	
	});  
</script>

</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goCreditMain();">뒤로가기</button>
			</div>
			<h1>대출현황</h1>
		</div>
	</header>
	<div class="affix-fixed top-fixed-item">
		<ul class="nav nav-outline nav-justified tabs">
			<li class="active"><a href="#tab1" data-target="#tab1" data-toggle="tab">신용대출</a></li>
			<li><a href="#tab2" data-target="#tab2" data-toggle="tab">담보대출</a></li>
		</ul>
	</div>
	<!-- Content -->
	<div id="content">
		<div class="tab-content">
			<!-- tab1 -->
			<div class="tab-pane active" id="tab1">
			<c:choose>
				<c:when test="${empty creditList}">
					<div class="data-none">
						<p>등록 내역이 없습니다.</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="sum-block sum-credit">
						<div class="sum-block-items">
							<div class="goods-ea">보유 <c:out value="${creditSum.debt_cnt}"/>건	</div>
							<div class="row tab-pane">
								<dl class="col-xs-6">
									<dt>대출잔액</dt>
									<dd><c:out value="${ufn:formatNumberPattern(creditSum.sum_amt_remain/10000,'###,###')}"/><span>만원</span></dd>
								</dl>
								<dl class="col-xs-6">
									<dt>대출금액</dt>
									<dd><c:out value="${ufn:formatNumberPattern(creditSum.sum_amt_contract/10000,'###,###')}"/><span>만원</span></dd>
								</dl>
							</div>
							<div class="progress-group progress-bar-credit">
								<div class="progress">
									<div class="progress-bar credit-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<div class="progress-label">
									<label class="label">대출금 상환비율</label>
									<span>
									<c:choose>
									<c:when test="${creditSum.sum_amt_contract > 0}">
										<c:out value="${ufn:formatNumberPattern(((creditSum.sum_amt_contract-creditSum.sum_amt_remain)/creditSum.sum_amt_contract)*100,'0.0')}"/>%
									</c:when>
									<c:otherwise>
										0.0%
									</c:otherwise>
									</c:choose>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="list-block">
							<c:forEach var="List" items="${creditList}" varStatus="status">
								<div class="container-fluid">
									<div class="list-heading">
										<li class="bank-title">
											<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
											<c:out value="${List.nm_fc}"/>
										</li>
									</div>
									<div class="list-info">
										<dl>
											<dt>개설일자</dt>
											<dd><c:out value="${List.ymd_loan}"/></dd>
										</dl>
										<dl>
											<dt>대출잔액</dt>
											<dd><c:out value="${ufn:formatNumberPattern(List.amt_remain/10000,'###,###')}"/>만원</dd>
										</dl>
										<dl>
											<dt>대출금액</dt>  
											<dd><c:out value="${ufn:formatNumberPattern(List.amt_contract/10000,'###,###')}"/>만원</dd>
										</dl>
									</div>
								</div>
							</c:forEach>
					</div>
					<div class="btn-area">
						<a href="<c:url value='/m/debt/frameDebt.crz'/>" class="btn btn-block btn-outline">자세히 보기</a>
					</div>
					<!-- noti -->
					<div class="container">
						<div class="noti-section">
							<ul>
								<li>대출개설정보는 현재 해지되지 않은 대출정보입니다.</li>
								<li>일부 기관의 대출정보는 “개인신용정보 제공 및 활용동의서” 미제출 경우거나 해당 금융기관의 기준 및 정책에 의해 대출정보가 제공되지 않을 수 있습니다.</li>
								<li>금융사로부터 정보가 등록되지 않은 경우 대출 이용 내역 등 정보가 공란으로 표기될 수 있습니다.</li>
								<li>“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</li>
								<li>금융사로부터 정보가 등록되지 않은 경우 대출 이용 내역 등 정보가 공란으로 표기될 수 있습니다.</li>
							</ul>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			</div>
			<!-- //tab1 -->
			<!-- tab2 -->
			<div class="tab-pane" id="tab2">
			<c:choose>
				<c:when test="${empty loanList}">
					<div class="data-none">
						<p>등록 내역이 없습니다.</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="sum-block sum-credit">
						<div class="sum-block-items">
							<div class="goods-ea">보유 <c:out value="${loanSum.debt_cnt}"/>건</div>
							<div class="row tab-pane">
								<dl class="col-xs-6">
									<dt>대출잔액</dt>
									<dd><c:out value="${ufn:formatNumberPattern(loanSum.sum_amt_remain/10000,'###,###')}"/><span>만원</span></dd>
								</dl>
								<dl class="col-xs-6">
									<dt>대출금액</dt>
									<dd><c:out value="${ufn:formatNumberPattern(loanSum.sum_amt_contract/10000,'###,###')}"/><span>만원</span></dd>
								</dl>
							</div>
							<div class="progress-group progress-bar-credit">
								<div class="progress">
									<div class="progress-bar loan-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<div class="progress-label">
									<label class="label">대출금 상환비율</label>
									<span>
									<c:choose>
									<c:when test="${loanSum.sum_amt_contract > 0}">
										<c:out value="${ufn:formatNumberPattern(((loanSum.sum_amt_contract-loanSum.sum_amt_remain)/loanSum.sum_amt_contract)*100,'0.0')}"/>%
									</c:when>
									<c:otherwise>
										0.0%
									</c:otherwise>
									</c:choose>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="list-block">
						<c:forEach var="List" items="${loanList}" varStatus="status">
							<div class="container-fluid">
								<div class="list-heading">
									<li class="bank-title">
										<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
										<c:out value="${List.nm_fc}"/>
									</li>
								</div>
								<div class="list-info">
									<dl>
										<dt>개설일자</dt>
										<dd><c:out value="${List.ymd_loan}"/></dd>
									</dl>  
									<dl>
										<dt>대출잔액</dt>
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_remain/10000,'###,###')}"/><span>만원</span></dd>
									</dl>
									<dl>
										<dt>대출금액</dt>  
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_contract/10000,'###,###')}"/><span>만원</span></dd>
									</dl>
								</div>
							</div>
						</c:forEach>
					</div>		
					<div class="btn-area">
						<a href="<c:url value='/m/debt/frameDebt.crz'/>" class="btn btn-block btn-outline">자세히 보기</a>
					</div>
					<!-- noti -->
					<div class="container">
						<div class="noti-section">
							<ul>
								<li>대출개설정보는 현재 해지되지 않은 대출정보입니다.</li>
								<li>일부 기관의 대출정보는 “개인신용정보 제공 및 활용동의서” 미제출 경우거나 해당 금융기관의 기준 및 정책에 의해 대출정보가 제공되지 않을 수 있습니다.</li>
								<li>금융사로부터 정보가 등록되지 않은 경우 대출 이용 내역 등 정보가 공란으로 표기될 수 있습니다.</li>
								<li>“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</li>
								<li>금융사로부터 정보가 등록되지 않은 경우 대출 이용 내역 등 정보가 공란으로 표기될 수 있습니다.</li>
							</ul>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			</div>	
			<!-- //tab2 -->
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
