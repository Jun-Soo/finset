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
	function signUpSuccess(){
		frmSuccessStep.action =  "<c:url value='/m/credit/frameCreditInfoMain.crz'/>";
		frmSuccessStep.submit();
	}
</script>

</head>
<body>
<form name="frmSuccessStep" method="post">
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button class="ui-nav nav-back" type="button" onclick="history.back();">뒤로가기</button>
			</div>
			<h1 class="logo">koscom</h1>
			<div class="input-group-btn">
				<button class="ui-nav nav-menu" type="button" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="msg-block">
			<h2 class="title">보안코드 변경이 완료되었습니다.</h2>
			<p>고객맞춤형 금융서비스를 이용할 수 있습니다.</p>
		</div>
		<div class="btn-fixed-bottom">
			<button class="btn btn-lg btn-default btn-block btn-point" onclick="signUpSuccess();">시작하기</button>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
