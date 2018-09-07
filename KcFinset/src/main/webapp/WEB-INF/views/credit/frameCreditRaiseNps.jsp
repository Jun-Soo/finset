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
	if(userAgent == "Android") {
		window.Android.backKeySendUrl("/m/credit/frameCreditRaise.crz");
	}
});
function send() {
	var data = {"scrap_code":"nps"};
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
			frmCreditRaiseNps.action = "<c:url value='/m/credit/frameCreditRaiseResult.crz?result="+returnData+"'/>";
			frmCreditRaiseNps.submit();
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
		<div class="container ">
		<form name="frmCreditRaiseNps" method="post">
		</form>
			<div class="list-block">
				<div class="list-info">
					<div class="credit_title">
						<p>${name} 님의</p>
						<p>총 납입금액은 ${ufn:formatNumber(amt_pay)}만원(${cnt_month_pay}개월)입니다</p>
						<p>${start_year}년 ${start_month}월부터 매월 ${amt_est_pns_month}만원(현재기준)</p>
						<p>지급 예상됩니다.</p>
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
					<dd>&nbsp;<div class="pull-left">${ufn:formatDateDot(paymentVO.start_yyyymm)} ~ ${ufn:formatDateDot(paymentVO.end_yyyymm)}</div><div class="pull-right">${paymentVO.etc}</div>
					<dd>&nbsp;<div class="pull-left">납부  | </div><div class="pull-right">${ufn:formatNumber(paymentVO.amt_pay)} 원(${paymentVO.cnt_month_pay}개월)</div>
					<dd>&nbsp;<div class="pull-left">미납  | </div><div class="pull-right">${ufn:formatNumber(paymentVO.amt_not_pay)} 원(${paymentVO.cnt_month_not_pay}개월)</div>
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
