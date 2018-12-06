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
			<h1>연대보증현황</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content">
	<c:choose>
		<c:when test="${empty guaranteeList}">
			<div class="data-none">
				<p>등록 내역이 없습니다.</p>
			</div>
		</c:when>
		<c:otherwise>
			<div class="sum-block sum-credit">
				<div class="sum-block-items">
					<div class="row">
						<dl class="col-xs-6">
							<dt>보증건수</dt>
							<dd><c:out value="${guaranteeCnt}"/><span>건</span></dd>
						</dl>
						<dl class="col-xs-6">
							<dt>보증금액</dt>  
							<dd><c:out value="${ufn:formatNumberPattern(amtGuarantee/10000,'###,###')}"/><span>만원</span></dd>
						</dl>
					</div>
				</div>
			</div>
			<div class="list-block">
					<c:forEach var="List" items="${guaranteeList}" varStatus="status">
						<div class="container-fluid">
							<div class="list-heading">
								<li class="bank-title">
									<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
									<c:out value="${List.nm_fc}"/>
								</li>
							</div>
							<div class="list-info">
								<dl>
									<dt>보증약정일자</dt>
									<fmt:parseDate var="dtGuarAgree" value="${List.dt_guar_agree}" pattern="yyyyMMdd" /> 
									<dd><fmt:formatDate value="${dtGuarAgree}" pattern="yyyy.MM.dd" /></dd>
								</dl>
								<dl>
									<dt>보증금액</dt>   
									<dd><c:out value="${ufn:formatNumberPattern(List.amt_guar_object/10000,'###,###')}"/>만원</dd>
								</dl>
							</div>
						</div>
					</c:forEach>
			</div>	
			<!-- noti -->
			<div class="container">
				<div class="noti-section">
					<ul>
						<li>연대보증 정보는 미해지 기준으로 제공되기 때문에 일부 연대보증 정보는 만기일이 지났어도 제공될 수 있습니다.</li>
					</ul>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
