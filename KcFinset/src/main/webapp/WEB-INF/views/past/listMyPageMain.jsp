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
			<h1>마이페이지</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="primary-block">
			<h2 class="title">회원정보 관리</h2>
			<p>
				회원기본정보 관리 및 서비스 이용 내역의 조회를 할 수 있습니다.
			</p>
		</div>
		<div class="panel-box">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#url" class="collapsed">
							<h3 class="panel-title">회원정보관리</h3>
							<small>회원기본정보 확인 및 보안코드 수정<br />
								회원탈퇴 서비스를 이용할 수 있습니다.</small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/past/listPastMain.crz'/>" class="collapsed">
							<h3 class="panel-title">이용내역조회</h3>
							<small>대출신청내역조회, 추천상품조회 등 서비스 이용<br />
								내역을 조회할 수 있습니다.</small>
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>
