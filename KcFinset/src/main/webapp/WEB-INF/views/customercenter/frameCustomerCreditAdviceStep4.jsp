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
			<h1>신용상담신청</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="msg-block">
			<span class="msg-img msg-complete"></span>
			<strong>신청이 완료되었습니다.</strong>
		</div>
		<div class="btn-area">
			<a href="<c:url value='/m/customercenter/frameCustomerCreditAdviceMain.crz'/>" class="btn btn-block btn-outline">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
