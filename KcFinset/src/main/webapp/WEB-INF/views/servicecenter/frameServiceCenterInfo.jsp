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
			<h1>고객센터</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="primary-block">
			<h2 class="title">무엇을 도와드릴까요?</h2>
			<p>
				고객님들이 더욱 즐겁고 편리하게 서비스를 이용할 수 있도록 최선을 다하겠습니다.
			</p>
		</div>
		<div class="panel-box">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/servicecenter/frameNoticeInfo.crz'/>" class="collapsed">
							<h3 class="panel-title">공지사항</h3>
							<small>KOSCOM이 전하는 새로운 소식들 입니다.</small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/servicecenter/frameQuestionInfo.crz'/>" class="collapsed">
							<h3 class="panel-title">자주하는 질문</h3>
							<small>고객님들이 자주 질문해주시는 사항들을 확인할 수 있습니다.</small>
						</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<c:url value='/m/servicecenter/frameConsultingInfo.crz'/>" class="collapsed">
							<h3 class="panel-title">Q&A</h3>
							<small>서비스 이용문의, 불편사항, 개선사항 등을 전할 수 있습니다.</small>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="btn-fixed-bottom bottom-over">
			<div class="col-xs-12">
				<button type="button" class="btn btn-lg btn-default btn-block">대출신청</button>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>