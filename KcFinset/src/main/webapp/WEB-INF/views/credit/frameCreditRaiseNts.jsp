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
	var data = {"scrap_code":"nts"};
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
			frmCreditRaiseNts.action = "<c:url value='/m/credit/frameCreditRaiseResult.crz?result="+returnData+"'/>";
			frmCreditRaiseNts.submit();
		},
		error : function (e) {
			errMsg(e);
		}
	});
}
</script>
</head>
<body>
<div id="wrapper" class="bg-white">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
			</div>
			<h1>납부확인 및 전송하기</h1>
		</div>
	</header>
	<section id="content">
		<form name="frmCreditRaiseNts" method="post">
		</form>
		<div class="container ">
			<div class="list-block">
				<div class="list-info">
					<div class="credit_title">
						<p>${name}님의</p>
						<p>${year}년도 소득금액은 ${ufn:formatNumber(income_div)}만원 입니다.</p>
						<br/>
						<p>해당 정보를 전송하시겠습니까?</p>
					</div>
				</div>
			</div>
		</div>
		<!-- tab2 -->
		<div class="credit_detail">
			<dl>
				<dt>소득금액증명</dt>
				<dd>&nbsp;<div class="pull-left">${income_division} | ${corp_name}</div></dd>
				<dd>&nbsp;<div class="pull-left">${ufn:formatNumber(income)}원</div></dd>
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
