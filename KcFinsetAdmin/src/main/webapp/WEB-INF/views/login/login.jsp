<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>로그인</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">

	$(document).ready(function(){
		$(".alert").hide();
	<c:if test="${!empty msg}">
		$("#msg").html("${msg}");
		$(".alert").fadeTo(5000, 100).slideUp(1000, function(){
			//$(".alert").alert('close');
		});
		${action}
	</c:if>
		
	});

	function goLogin() {
		frmLogin.submit();
	}	
	function isEnter(keyCode){
		if(keyCode == 13){
			goLogin();
		}
	}
</script>
</head>
<body>
<div class="login-bg">
	<div class="alert alert-success alert-dismissible exclamation-list alert-lg" role="alert" >
		<ul>
			<li id="msg"></li>
		</ul>
	</div>
	<div class="login-box">
		<div class="login-header">
			<h1><img src="<c:url value="/img/logo/logo_${ufn:getCodeName('_CONF_SYSTEM','NM_LOGO')}.png"/>" alt="" /></h1>
		</div>
		<form name="frmLogin" action="<c:url value='/j_spring_security_check'/>" method="POST">
			<div class="login-body">
				<dl>
					<dt>user id</dt>
					<dd><input type="text" name="j_username" class="form-control" value="${SPRING_SECURITY_LAST_USERNAME}"/></dd>
					<dt>password</dt>
					<dd><input type="password" name="j_password" class="form-control" onkeydown="isEnter(event.keyCode);" value="" autocomplete="off"/></dd>
				</dl>
				<div class="btn-sec"><button class="login-btn" onclick="javascript:goLogin();">login</button></div>
			</div>
		</form>
	</div>
</div>
</body>
</html>