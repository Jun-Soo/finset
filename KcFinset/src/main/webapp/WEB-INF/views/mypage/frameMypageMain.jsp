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
// 	popSecurityInfo();

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
						<a href="<c:url value='/m/mypage/frameMypageCustInfo.crz'/>" class="collapsed">
							<h3 class="panel-title">회원정보관리</h3>
							<small>회원기본정보 확인 및 비밀번호 수정<br />
								회원탈퇴 서비스를 이용할 수 있습니다.</small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/past/listPastMain.crz'/>" class="collapsed">
							<h3 class="panel-title">신청상품진행현황</h3>
							<small>대출신청내역조회, 추천상품조회 등 서비스 이용<br />
								내역을 조회할 수 있습니다.</small>
						</a>
					</div>
				</div>
				<!-- 임시 메뉴 생성 start -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#" class="collapsed">
							<h3 class="panel-title">알림</h3>
							<small></small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#" class="collapsed">
							<h3 class="panel-title">개인설정</h3>
							<small></small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/customercenter/frameCustomerGoodsFavorite.crz'/>" class="collapsed">
							<h3 class="panel-title">관심상품</h3>
							<small></small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#" class="collapsed">
							<h3 class="panel-title">인증서관리</h3>
							<small></small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/customercenter/frameCustomerNotice.crz'/>" class="collapsed">
							<h3 class="panel-title">공지</h3>
							<small></small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#" class="collapsed">
							<h3 class="panel-title">자주묻는질문</h3>
							<small></small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#" class="collapsed">
							<h3 class="panel-title">문의하기</h3>
							<small></small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/customercenter/frameCustomerServiceOpPolicy.crz'/>" class="collapsed">
							<h3 class="panel-title">서비스운영방침</h3>
							<small></small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#" class="collapsed">
							<h3 class="panel-title">버전정보</h3>
							<small></small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/customercenter/frameCustomerQuit.crz'/>" class="collapsed">
							<h3 class="panel-title">회원탈퇴</h3>
							<small></small>
						</a>
					</div>
					</div>
				<!-- 임시 메뉴 생성 End -->
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>
