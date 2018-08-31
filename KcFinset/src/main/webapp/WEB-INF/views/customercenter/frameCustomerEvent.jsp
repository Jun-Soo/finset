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

</script>

</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>공지/이벤트</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content" class="page-event">
		<ul class="nav nav-outline nav-justified tabs affix-fixed">
			<li><a href="#tab1">공지</a></li>
			<li class="active"><a href="#tab2">이벤트</a></li>
		</ul>
		<!-- tab2 -->
		<div class="container-fluid">
			<div class="list-group">
				<a href="#" class="list-group-item">
					<div class="row">
						<div class="col-xs-4">
							<img src="/images/evt_thumb.png" alt="...">
						</div>
						<div class="col-xs-8">
							<div class="caption">
								<h3>신용점수 확인하고 아메리카노 받기 이벤트</h3>
								<p class="date">2017.04.01 ~ 2017.04.30</p>
								<span class="evt-ongoing">진행중</span>
							</div>
						</div>
					</div>
				</a>
				<a href="#" class="list-group-item">
					<div class="row">
						<div class="col-xs-4">
							<img src="/images/evt_thumb.png" alt="...">
						</div>
						<div class="col-xs-8">
							<div class="caption">
								<h3>핀셋 런칭 기념 출석체크 이벤트</h3>
								<p class="date">2017.04.01 ~ 2017.04.30</p>
								<span class="evt-close">종료</span>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div>
		<!-- //tab2 -->
	</div>
</div>
</body>
</html>
