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
	
	function goHome() {
		frmCreditRaiseEmpty.action = "<c:url value='/m/credit/frameCreditRaise.crz'/>";
		frmCreditRaiseEmpty.submit();
	}

</script>

</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>조회결과</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<form name="frmCreditRaiseEmpty" method="post">
		</form>
		<div class="container">
			<div class="msg-block">
				<span class="msg-img result-none"></span>
				<strong>조회내역이 없습니다.</strong>
				<p>금융기관에 납부내역이 없거나<br />납부내역을 불러올 수 없습니다.</p>
			</div>
		</div>
		<div id="btn_fail" class="btn-fixed-bottom affix-bottom">
		<div class="col-xs-6">
			<a role="button" class="btn btn-lg btn-block btn-primary" onclick="goHome()">홈으로</a>
		</div>
	</div>
	</section>
</div>
</body>
</html>
