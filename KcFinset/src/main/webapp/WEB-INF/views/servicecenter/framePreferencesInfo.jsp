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
			<h1>설정</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="primary-block">
			<h2 class="title">Koscom은 당신의 내일에 대한 꿈을 꿉니다.</h2>
			<p>
				금융서비스의 새로운 패러다임<br />고객맞춤형 금융서비스
			</p>
		</div>
		<ul class="app-ver">
			<li>
				설치된 버전
				<span>0.0.0</span>
			</li>
			<li class="ver-new">
				최신 버전
				<span>0.0.0</span>
			</li>
		</ul>
		<div class="app-ver-msg">
			<p>최신버전을 사용중입니다.</p>
			<p class="ver-update">최신 버전으로 업데이트를 할 수 있습니다.</p>
			<div class="btn-area">
				<button type="button" class="btn btn-lg btn-primary btn-block">업데이트</button>
			</div>
		</div>
		<div class="msg-alram">
			<div class="inner">
				<span>Push 메세지 수신설정</span>
				<!-- <label class="ui-switch ui-switch-md info m-t-xs"><input type="checkbox" checked="checked"> <i></i></label> -->
				<div class="ui-switch">
					<input type="checkbox" id="s1"><label class="checkbox-inline" for="s1"></label>
				</div>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<div id="menu-fixed-bottom">
		<ul class="menu-list">
			<li><a href="#none" class="menu-credit">신용관리</a></li>
			<li><a href="#none" class="menu-debt">부채관리</a></li>
			<li><a href="#none" class="menu-loan">맞춤대출</a></li>
			<li><a href="#none" class="menu-cust">고객센터</a></li>
			<li><a href="16.html" class="menu-mypage">마이페이지</a></li>
		</ul>			
	</div>
</div>
</body>
</html>