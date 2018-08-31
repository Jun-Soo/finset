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
	 	//신용
	 	$(".progress-bar-credit .credit-bar").delay(0).queue(function(){
	 		$(this).css("width", ("${creditAmtTotal}"/"${creditLimitTotal}")*100+"%");
	 	});
	 	
	 	//체크
	 	$(".progress-bar-credit .check-bar").delay(0).queue(function(){
			$(this).css("width", ("${checkAmtTotal}"/"${(checkAmtTotal+creditAmtTotal)}")*100+"%");
		});
	 	

		//이용월 셋팅
		setUseMonth();
		 	
	});
	
	
	function setUseMonth(){
		var date = new Date();
		
		if(date.getDate() < 15){
			date.setMonth(date.getMonth() - 1);	 
		}
		
		if(date.getMonth() == 0){
			$(".useMonth").empty().html("12");
		}else{
			$(".useMonth").empty().html(date.getMonth());
		}
	}
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
			<h1>카드현황</h1>
		</div>
	</header>	
	<div class="affix-fixed top-fixed-item">
		<ul class="nav nav-outline nav-justified tabs">
			<li class="active"><a href="#tab1" data-target="#tab1" data-toggle="tab">신용카드</a></li>
			<li><a href="#tab2" data-target="#tab2" data-toggle="tab">체크카드</a></li>
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
							<div class="goods-ea">보유 <em><c:out value="${creditCnt}"/></em>개	</div>
							<div class="row tab-pane">
								<dl class="col-xs-6">
									<dt><span><em class="useMonth"></em>월</span> 이용금액</dt>
									<dd><c:out value="${ufn:formatNumberPattern(creditAmtTotal,'###,###')}"/><span>만원</span></dd>
								</dl>
								<dl class="col-xs-6">
									<dt>총한도</dt>
									<dd><c:out value="${ufn:formatNumberPattern(creditLimitTotal,'###,###')}"/><span>만원</span></dd>
								</dl>
							</div>
							<div class="progress-group progress-bar-credit">
								<div class="progress">
									<div class="progress-bar credit-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<div class="progress-label">
									<label class="label"><strong><em class="useMonth"></em>월</strong> 한도이용율</label>
									<span>
									<c:choose>
									<c:when test="${creditLimitTotal > 0}">
										<c:out value="${ufn:formatNumberPattern((creditAmtTotal/creditLimitTotal)*100,'0.0')}"/>%
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
					<div class="list-block credit-item-card">
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
										<fmt:parseDate var="ymdOpen" value="${List.ymd_open}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${ymdOpen}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>총한도</dt>
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_limit/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>단기대출(현금서비스)한도</dt>
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_ca_limit/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>이용금액</dt>  
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_total/10000,'###,###')}"/>만원</dd>
									</dl>
								</div>
								<a href="#card${status.index}" class="collapsed list-more" data-toggle="collapse" data-parent="#card${status.index}">이용금액상세보기</a>
								<div class="panel-collapse collapse" id="card${status.index}">
									<div class="panel-body">
										<div class="list-info">
											<dl>
												<dt>일시불</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_lump_sum/10000,'###,###')}"/>만원</dd>
											</dl>
											<dl>
												<dt>할부</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_installment/10000,'###,###')}"/>만원</dd>
											</dl>
											<dl>
												<dt>단기대출(현금서비스)</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_short_card_loan/10000,'###,###')}"/>만원</dd>
											</dl>
											<dl>
												<dt>체크카드</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_check/10000,'###,###')}"/>만원</dd>
											</dl>
											<dl>
												<dt>연체금액</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_delay/10000,'###,###')}"/>만원</dd>
											</dl>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>	
					</div>
					<!-- noti -->
					<div class="container">
						<div class="noti-section">
							<ul>
								<li>카드 개설 정보는 현재 해지되지 않은 카드 정보입니다. 일부 금융 기관의 경우에는 고객별로 카드 정보를 합산하여 제공하므로 이러한 경우에는 복수개의 카드라도 한 개의 카드정보로 제공됩니다.</li>
								<li>총 한도는 단기카드대출(현금서비스) 한도를 포함하고 있습니다.</li>
								<li>“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</li>
								<li>총 한도는 단기카드대출(현금서비스)한도를 포함하고 있습니다.</li>
								<li>총 이용금액은 단기카드대출(현금서비스), 체크카드 이용금액을 포함하고 있습니다.</li>
								<li>“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</li>
								<li>카드이용금액은 해당월 1일부터 말일까지의 이용금액정보 입니다.</li>
								<li>금융기관에서 등록하지 않은 카드 정보 및 이용금액은 표시되지 않습니다.</li>
								<li>한도이용률은 해당월 카드이용금만을 반영한 값으로 현재시점의 한도이용률과 차이가 있을 수 있습니다.</li>
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
				<c:when test="${empty checkList}">
					<div class="data-none">
						<p>등록 내역이 없습니다.</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="sum-block sum-credit">
						<div class="sum-block-items">
							<div class="goods-ea">보유 <em><c:out value="${checkCnt}"/></em>개</div>
							<div class="row tab-pane">
								<dl class="col-xs-6">
									<dt><span><em class="useMonth"></em>월</span> 이용금액</dt>
									<dd><c:out value="${ufn:formatNumberPattern(checkAmtTotal,'###,###')}"/><span>만원</span></dd>
								</dl>
								<dl class="col-xs-6">
									<dt>총한도</dt>
									<dd><c:out value="${ufn:formatNumberPattern(checkLimitTotal,'###,###')}"/><span>만원</span></dd>
								</dl>
							</div>
							<div class="progress-group progress-bar-credit">
								<div class="progress">
									<div class="progress-bar check-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<div class="progress-label">
									<label class="label"><strong><em class="useMonth"></em>월</strong> 체크카드이용율</label>
									<span>
									<c:choose>
									<c:when test="${(checkAmtTotal+creditAmtTotal) > 0}">
										<c:out value="${ufn:formatNumberPattern((checkAmtTotal/(checkAmtTotal+creditAmtTotal))*100,'0.0')}"/>%
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
						<c:forEach var="List" items="${checkList}" varStatus="status">	
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
										<fmt:parseDate var="ymdOpen" value="${List.ymd_open}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${ymdOpen}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>총한도</dt>
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_limit/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>단기대출(현금서비스)한도</dt>
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_ca_limit/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>이용금액</dt>
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_total/10000,'###,###')}"/>만원</dd>
									</dl>
								</div>
								<a href="#check${status.index}" class="collapsed list-more" data-toggle="collapse" data-parent="check${status.index}">이용금액상세보기</a>
								<div class="panel-collapse collapse" id="check${status.index}">
									<div class="panel-body">
										<div class="list-info">
											<dl>
												<dt>일시불</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_lump_sum/10000,'###,###')}"/>만원</dd>
											</dl>
											<dl>
												<dt>할부</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_installment/10000,'###,###')}"/>만원</dd>
											</dl>
											<dl>
												<dt>단기대출(현금서비스)</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_short_card_loan/10000,'###,###')}"/>만원</dd>
											</dl>
											<dl>
												<dt>체크카드</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_check/10000,'###,###')}"/>만원</dd>
											</dl>
											<dl>
												<dt>연체금액</dt>
												<dd><c:out value="${ufn:formatNumberPattern(List.amt_delay/10000,'###,###')}"/>만원</dd>
											</dl>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- noti -->
					<div class="container">
						<div class="noti-section">
							<ul>
								<li>카드 개설 정보는 현재 해지되지 않은 카드 정보입니다. 일부 금융 기관의 경우에는 고객별로 카드 정보를 합산하여 제공하므로 이러한 경우에는 복수개의 카드라도 한 개의 카드정보로 제공됩니다.</li>
								<li>총 한도는 단기카드대출(현금서비스) 한도를 포함하고 있습니다.</li>
								<li>“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</li>
								<li>총 한도는 단기카드대출(현금서비스)한도를 포함하고 있습니다.</li>
								<li>총 이용금액은 단기카드대출(현금서비스), 체크카드 이용금액을 포함하고 있습니다.</li>
								<li>“개인신용정보 제공 및 활용동의서” 미제출한 경우거나 해당 금융 기관의 기준 및 정책에 의해 일부 카드 정보가 제공되지 않을 수 있습니다.</li>
								<li>카드이용금액은 해당월 1일부터 말일까지의 이용금액정보 입니다.</li>
								<li>카드이용금액은 매월 15일에 갱신됩니다.</li>
								<li>금융기관에서 등록하지 않은 카드 정보 및 이용금액은 표시되지 않습니다.</li>
								<li>체크카드이용비중은 전체 카드사용액(신용카드 포함)중 체크카드 사용액의 비중을 말합니다.</li>
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
