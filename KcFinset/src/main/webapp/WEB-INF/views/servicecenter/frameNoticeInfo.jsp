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
			<h1>공지사항</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="lead">
			<p>KOSCOM이 전하는 새로운 소식들 입니다.</p>
		</div>
		<div class="panel-box">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/servicecenter/frameNoticeDetail.crz'/>" class="collapsed">
							<h3 class="panel-title">자율 머신의 안전 확보와 윤리적 가이드라인</h3>
							<small class="date">2017.06.01</small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/servicecenter/frameNoticeDetail.crz'/>" class="collapsed">
							<h3 class="panel-title">투자 문화를 바꾸다 “모바일 증권사가 최종 지향점</h3>
							<small class="date">2017.06.01</small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/servicecenter/frameNoticeDetail.crz'/>" class="collapsed">
							<h3 class="panel-title">한국 경제 성장의 노하우를 해외에 전수하는 IT	</h3>
							<small class="date">2017.06.01</small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/servicecenter/frameNoticeDetail.crz'/>" class="collapsed">
							<h3 class="panel-title">자율 머신의 안전 확보와 윤리적 가이드라인</h3>
							<small class="date">2017.06.01</small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/servicecenter/frameNoticeDetail.crz'/>" class="collapsed">
							<h3 class="panel-title">투자 문화를 바꾸다 “모바일 증권사가 최종 지향점</h3>
							<small class="date">2017.06.01</small>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="btn-more-area">
			<button type="button" class="btn btn-block btn-more">+ 더보기</button>
		</div>
	</section>
	
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>