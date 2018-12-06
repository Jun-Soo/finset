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
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="history.go(-1);">뒤로가기</button>
			</div>
			<h1>	<c:if test="${isSuccess eq 'true'}">신청완료</c:if>
					<c:if test="${isSuccess eq 'false'}">신청불가</c:if></h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead"><p><c:if test="${message eq 'success'}">대출신청이  완료 되었습니다.</c:if>
                <c:if test="${message ne 'success'}">${message}</c:if></p></div>
		</div>
		<div class="list-block">
			<c:if test="${message eq 'success'}">
			<div class="container-fluid prd-loan">
				<div class="list-heading">
					<%--<span class="label label-status status-blue">신청가능</span>--%>
                    <%--<c:if test="${txFcReceiveVO.sts_loan eq '70'}">--%>
					    <%--<span class="label label-status status-red">신청불가능</span>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${txFcReceiveVO.sts_loan eq '70'}">--%>
                    <span class="label label-status status-green">신청완료</span>
                    <%--</c:if>--%>
					<%--<span class="label label-status status-red">조회오류</span>--%>
					<div class="bank-title">
						<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${fincorpVO.cd_fc}');"></span>${fincorpVO.nm_fc}
					</div>
					<h2 class="prd-title">${goodsVO.nm_goods}</h2>
				</div>
				<div class="list-info">
					<dl>
						<dt>대출금리</dt>
						<dd class="txt-point">
							<c:set var="cd_type_interest" value="${txFcReceiveVO.cd_type_interest}" />
							<c:if test="${txFcReceiveVO.cd_type_interest.length() gt '2'}">
								<c:set var="cd_type_interest" value="변동,고정" />
								<label>${cd_type_interest}</label>
							</c:if>
							<c:if test="${txFcReceiveVO.cd_type_interest.length() eq '2'}">
								<c:set var="cd_type_interest" value="${ufn:getCodeName('cd_ratio_type', txFcReceiveVO.cd_type_interest)}" />
								<label>${cd_type_interest}</label>
							</c:if>
							${txFcReceiveVO.rto_loan} %</dd>
					</dl>
					<dl>
						<dt>대출한도</dt>
						<dd>${ufn:formatNumberMan(txFcReceiveVO.amt_limit)} 만원</dd>
					</dl>
					<dl>
						<dt>대출기간</dt>
						<dd>${goodsVO.cd_loan_term * 12} 개월</dd>
					</dl>
					<dl>
						<dt>상환방식</dt>
						<dd><c:set var="cdTypePay" value="${fn:split(List.cd_type_pay,',')}" />
							<c:forEach var="typePay" items="${cdTypePay}" varStatus="i">
								<c:if test="${i.current > 1}">
									, &nbsp;
								</c:if>
								${ufn:getCodeName("cd_type_pay",typePay)}상환
							</c:forEach></dd>
					</dl>
				</div>
			</div>
			</c:if>
		</div>
		<div class="container noti-section">
			<ul>
				<li>대출신청 후 신용등급 변동, 입력정보 오류 등으로 인해 금리/한도가 변동되거나 대출이 불가능 할  수 있습니다.</li>
				<li>신청 이후 진행사항은 마이페이지(상품신청현황)에서 확인가능합니다.</li>
			</ul>
		</div>
		<div class="btn-fixed-bottom affix-bottom" id="next_div">
			<a href="<c:url value='/m/loanworker/frameLoanWorkerStep1.crz'/>" role="button" class="btn btn-lg btn-block btn-primary">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
