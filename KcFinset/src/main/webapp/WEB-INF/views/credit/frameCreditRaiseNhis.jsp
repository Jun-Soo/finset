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
function send() {
	var data = {"scrap_code":"nhis"};
	$.ajax({
		url : "<c:url value='/kcb/updateKcbReqNonfiInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = "FAILED";
			if(result.returnData == '00') {
				returnData = "SUCCESS"		
			}
			frmCreditRaiseNhis.action = "<c:url value='/m/credit/frameCreditRaiseResult.crz?result="+returnData+"'/>";
			frmCreditRaiseNhis.submit();
		},
		error : function (e) {
			errMsg(e);
		}
	});
}
</script>
</head>
<body class="bg-white">
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>납부확인 및 전송하기</h1>
		</div>
	</header>
	<section id="content">
		<form name="frmCreditRaiseNhis" method="post">
		</form>
		<div class="container ">
			<div class="list-block">
				<div class="list-info">
					<div class="credit_title">
						<p>${name} 님의</p>
						<p>1년간 납입금액은 ${ufn:formatNumber(total_payment)}만원 ( ${payment_size}개월) 이며</p>
						<p>추정소득은 <strong> ${ufn:formatNumber(amt_year_income)}</strong> 만원 입니다.</p>
						<br/>
						<p>해당 정보를 신용평가사로 전송하시겠습니까?</p>
					</div>
				</div>
			</div>
		</div>
		<!-- tab2 -->
		<div class="credit_detail">
			<dl>
				<dt>납부내역</dt>
				<c:forEach var="paymentVO" items="${payment}" varStatus="status">
					<dd>&nbsp;<div class="pull-left">${ufn:formatDateDot(paymentVO.pay_yyyymm)} | ${ufn:formatNumber(paymentVO.amt_nt_health_insu)} 원</div>
						<c:choose>
						<c:when test="${paymentVO.amt_pay_health_insu eq  paymentVO.amt_nt_health_insu}">
							<div class="pull-right">납부</div>
						</c:when>
						<c:otherwise>
							<div class="pull-right">미납</div>
						</c:otherwise>
						</c:choose>
					</dd>
					

				</c:forEach>
			</dl>
		</div>
		<div class="btn-fixed-bottom affix-bottom">
			<a role="button" class="btn btn-lg btn-block btn-primary" onclick="send();">전송하기</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
