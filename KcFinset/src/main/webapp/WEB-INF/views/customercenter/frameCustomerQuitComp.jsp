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
function appQuit(){
	var frm = document.frmQuitComp;
	window.Android.exitApp(); //앱종료
}
</script>
</head>
<body>
<form name="frmQuitComp" method="post">

</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="goGoodsMain();">뒤로가기</button>
			</div>
			<h1>서비스 탈퇴 완료</h1>
		</div>
	</header>
	<section id="content">
		<div class="container">
			<div class="msg-block">
				<span class="msg-img quit-complete"></span>
				<strong>서비스 탈퇴가 완료되었습니다.</strong>
				<p>이용해주셔서 감사합니다.</p>
			</div>
		</div>
		<div class="btn-fixed-bottom affix-bottom" id="">
			<a role="button" class="btn btn-lg btn-block btn-primary" onclick="appQuit();">앱 종료하기</a>
		</div>
	</section>
</div>
</body>
</html>