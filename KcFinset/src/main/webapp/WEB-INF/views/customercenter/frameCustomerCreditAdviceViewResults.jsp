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
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>신용상담신청</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content" class="board-view">
		<h2 class="h2">상담결과</h2>
		<div class="container-fluid">
			<div class="panel-body">
				<p>
					 <c:out value="${counselContents}"/>
				</p>
			</div>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
