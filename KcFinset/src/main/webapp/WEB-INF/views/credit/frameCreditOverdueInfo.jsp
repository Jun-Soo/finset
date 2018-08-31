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
			<h1>연체현황</h1>
		</div>
	</header>
	<div class="affix-fixed top-fixed-item">
		<ul class="nav nav-outline nav-justified tabs">
			<li class="active"><a href="#tab1" data-target="#tab1" data-toggle="tab">연체</a></li>
			<li><a href="#tab2" data-target="#tab2" data-toggle="tab">기타</a></li>
		</ul>
	</div>
	<!-- Content -->
	<div id="content">
		<div class="tab-content">
			<!-- tab1 -->
			<div class="tab-pane active" id="tab1">
			<c:choose>
				<c:when test="${empty overdueList && empty steadList}">
					<div class="data-none">
						<p>등록 내역이 없습니다.</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="sum-block sum-credit">
						<div class="sum-block-items">
							<div class="row tab-pane">
								<dl class="col-xs-6">
									<dt>연체잔액</dt>
									<dd><c:out value="${ufn:formatNumberPattern(overAmtDelay,'###,###')}"/><span>만원</span></dd>
								</dl>
								<dl class="col-xs-6">
									<dt>대지급잔액</dt>
									<dd><c:out value="${ufn:formatNumberPattern(steadAmtDelay,'###,###')}"/><span>만원</span></dd>
								</dl>
							</div>
						</div>
					</div>
					<div class="list-block">
						<!-- 연체 -->
						<c:forEach var="List" items="${overdueList}" varStatus="status">
							<div class="container-fluid">
								<div class="list-heading">
									<li class="bank-title">
										<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
										<c:out value="${List.nm_fc}"/>
									</li>
									<label class="label-type">연체</label>
								</div>
								<div class="list-info">
									<dl>
										<dt>죄초연체기산일</dt>
										<fmt:parseDate var="ymdDelay" value="${List.ymd_delay}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${ymdDelay}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>최초연체금액</dt>
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_frt_delay/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>상환금액</dt>
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_repay/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>연체잔액</dt>
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_delay/10000,'###,###')}"/>만원</dd>
									</dl>
								</div>
							</div>
						</c:forEach>
						<!-- //연체 -->
						<!-- 대지급  -->
						<c:forEach var="List" items="${steadList}" varStatus="status">
							<div class="container-fluid">
								<div class="list-heading">
									<li class="bank-title">
										<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
										<c:out value="${List.nm_fc}"/>
									</li>
									<label class="label-type">대지급</label>
								</div>
								<div class="list-info">
									<dl>
										<dt>대지급발생일자</dt>
										<fmt:parseDate var="ymdDelay" value="${List.ymd_delay}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${ymdDelay}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>대지급상환일자</dt>
										<fmt:parseDate var="amtFrtDelay" value="${List.amt_frt_delay}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${amtFrtDelay}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>상환금액</dt>    
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_repay/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>대지급잔액</dt>    
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_delay/10000,'###,###')}"/>만원</dd>
									</dl>
								</div>
							</div>
						</c:forEach>
						<!-- //대지급 -->
					</div>
					<!-- noti -->
					<div class="container">
						<div class="noti-section">
							<ul>
								<li>대지급 정보는 보증보험 증권을 담보로 한 금융거래(지급보증) 후 계약을 이행하지 않아 해당 보증보험사에서 대위 변제한 내역에 대한 정보입니다.</li>
								<li>연체/대지급 정보의 제공 기준은 10만원 이상 5영업일 이상 연체 시에 제공됩니다.</li>
								<li>한국장학재단 학자금연체정보는 상환 후 1년까지만 신용평가에 활용됩니다.</li>
								<li>본 정보에서 제공하는 연체 금액은 금융기관에서 관리하는 금액으로, 실제로 상황해야 할 연체금액과는 차이가 있사오니 상환 시에는 필히 해당 금융기관으로 확인하시기 바랍니다.</li>
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
				<c:when test="${empty etcDefaultList && empty etcPublicList && empty etcFinDisorderList}">
					<div class="data-none">
						<p>등록 내역이 없습니다.</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="sum-block sum-credit">
						<div class="sum-block-items">
							<div class="row tab-pane">
								<dl class="col-xs-4">
									<dt>채무불이행</dt>
									<dd><c:out value="${etcCntDefault}"/><span>건</span></dd>
								</dl>
								<dl class="col-xs-4">
									<dt>공공정보</dt>
									<dd><c:out value="${etcCntPublic}"/><span>건</span></dd>
								</dl>
								<dl class="col-xs-4">
									<dt>금융질서문란</dt>
									<dd><c:out value="${etcCntFinDisorder}"/><span>건</span></dd>
								</dl>
							</div>
						</div>
					</div>
					<div class="list-block">
						<!-- 채무불이행 -->
						<c:forEach var="List" items="${etcDefaultList}" varStatus="status">
							<div class="container-fluid">
								<div class="list-heading">
									<li class="bank-title">
										<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
										<c:out value="${List.nm_fc}"/>
									</li>
									<label class="label-type">채무불이행</label>
								</div>
								<div class="list-info">
									<dl>
										<dt>발생일자</dt>
										<fmt:parseDate var="ymdDelay" value="${List.ymd_delay}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${ymdDelay}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>연체금액</dt>    
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_delay/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>상환일자</dt>    
										<fmt:parseDate var="ymdRepay" value="${List.ymd_repay}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${ymdRepay}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>상환금액</dt>    
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_repay/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>연체잔액</dt>    
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_orderdue/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>등록사유</dt>
										<dd><c:out value="${List.cd_default}"/></dd>
									</dl>
								</div>
							</div>
						</c:forEach>
						<!-- //채무불이행 -->
						<!-- 공공정보  -->
						<c:forEach var="List" items="${etcPublicList}" varStatus="status">	
							<div class="container-fluid">
								<div class="list-heading">
									<li class="bank-title">
										<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
										<c:out value="${List.nm_fc}"/>
									</li>
									<label class="label-type">공공정보</label>
								</div>
								<div class="list-info">
									<dl>
										<dt>관리점명</dt>
										<dd><c:out value="${List.nm_agency}"/></dd>
									</dl>
									<dl>
										<dt>연체등록일자</dt>    
										<fmt:parseDate var="ymdDelay" value="${List.ymd_delay}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${ymdDelay}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>등록금액</dt>    
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_delay/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>사건번호</dt>    
										<dd><c:out value="${List.no_case}"/></dd>
									</dl>
									<dl>
										<dt>등록사유</dt>
										<dd><c:out value="${List.cd_default}"/></dd>
									</dl>
								</div>
							</div>
						</c:forEach>
						<!-- //공공정보 -->
						<!-- 금융질서정보문란  -->
						<c:forEach var="List" items="${etcFinDisorderList}" varStatus="status">	
							<div class="container-fluid">
								<div class="list-heading">
									<li class="bank-title">
										<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
										<c:out value="${List.nm_fc}"/>
									</li>
									<label class="label-type">금융질서정보문란</label>
								</div>
								<div class="list-info">
									<dl>
										<dt>관리점명</dt>
										<dd><c:out value="${List.nm_agency}"/></dd>
									</dl>
									<dl>
										<dt>발생일자</dt>    
										<fmt:parseDate var="ymdDefault" value="${List.ymd_default}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${ymdDefault}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>등록금액</dt>    
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_delay/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>연체금액</dt>    
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_delay/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>상환일자</dt>    
										<fmt:parseDate var="ymdRepay" value="${List.ymd_repay}" pattern="yyyyMMdd" /> 
										<dd><fmt:formatDate value="${ymdRepay}" pattern="yyyy.MM.dd" /></dd>
									</dl>
									<dl>
										<dt>상환금액</dt>    
										<dd><c:out value="${ufn:formatNumberPattern(List.amt_repay/10000,'###,###')}"/>만원</dd>
									</dl>
									<dl>
										<dt>등록사유</dt>
										<dd><c:out value="${List.cd_default}"/></dd>
									</dl>
								</div>
							</div>
						</c:forEach>
						<!-- //금융질서정보문란 -->
					</div>
					<!-- noti -->
					<div class="container">
						<div class="noti-section">
							<ul>
								<li>채무불이행(신용정보사)는 신용회복지원을 받다가 연체로 인해 중도 탈락된 건도 포함됩니다.</li>
								<li>채무불이행(신용정보사) 정보는 연체 상환 시 해제반영되어 화면상 조회되지 않더라도 신용평가에는 5년 이내에 활용됩니다.</li>
								<li>공공정보는 국세, 지방세, 관세를 500만원 이상 체납한 경우와 법원의 판결에 의하여 채무불이행자로 결정된 경우, 신용회복지원 등의 이유로 등록되는 정보입니다.</li>
								<li>세금체납정보는 상환 시 해제반영되어 화면상 조회되지 않더라도 신용평가에는 3년동안 활용됩니다.</li>
								<li>금융질서문란정보는 금융사기 등과 관련하여 법원으로부터 판결을 받거나 사기 및 결탁 등 부정한 방법으로 대출을 받거나 거래 약정을 체결한 경우 등의 사유로 등록되는 정보입니다.</li>
								<li>금융질서문란정보는 신용정보관리 규약에 따라 등록 사유가 해제된 이후에도 5년간 기록이 보존됩니다.</li>
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
