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
					window.Android.setEndApp('Y');
				}
			});
		
			function reLogin(){
				var frm = document.frmLogout;
				
				frm.action="/m/login/frameSecurityCodeConfirm.crz"; 
				frm.method="post"; 
				frm.submit(); 
			}
		</script>
	</head>
	<body>
		<form name="frmLogout" method="post">
			<input type="hidden" name="hp" id="hp" value="${hp}" />
		</form>
		<div id="wrapper">
			<!-- Header -->
			<header id="header">
				<div class="input-group">
					<div class="input-group-btn blind">
						<button type="button" class="ui-nav nav-back" onclick="">뒤로가기</button>
					</div>
					<h1>로그아웃 완료</h1>
				</div>
			</header>
			<section id="content">
				<div class="container">
					<div class="msg-block">
						<span class="msg-img logout-complete"></span>
						<strong>안전하게 로그아웃 되었습니다.</strong>
					</div>
				</div>
				<div class="btn-fixed-bottom affix-bottom" id="">
					<a role="button" class="btn btn-lg btn-block btn-primary" onclick="reLogin();">다시 로그인하기</a>
				</div>
			</section>
		</div>
	</body>
</html>