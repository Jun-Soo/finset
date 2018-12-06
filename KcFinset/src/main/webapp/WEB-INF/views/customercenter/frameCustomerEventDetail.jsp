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
<body class="bg-white">
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>이벤트</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content" class="board-view">
		<div class="container-fluid">
			<div class="panel panel-default">
				<div class="panel-heading caption">
					<h3 class="panel-title">신용점수 확인하고 아메리카노 받기 이벤트</h3>
					<small class="date">2017.04.01 ~ 2017.04.30</small>
					<span class="evt-ongoing">진행중</span>
				</div>
				<div class="panel-body">
					<p>
						안녕하세요. 핀셋 서비스 고객 여러분.<br />
						핏셋서비스 오픈을 맞아 신용점수를 확인하신 고객님들에게 추첨을 통해 스타벅스 아메리카노 기프티콘을 드립니다.
						<br /><br />
						<strong>이벤트 참여 방법</strong><br />
						이벤트 기간 : 2017.04.01(토) ~ 2017.04.30(일)
					</p>
				</div>	
			</div>
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
