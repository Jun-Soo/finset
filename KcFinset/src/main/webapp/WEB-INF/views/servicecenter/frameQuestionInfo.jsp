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
			<h1>자주하는 질문</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="top-block">
			<div class="alert-desc">
				<ul>
					<li>자주 찾는 질문의 답변을 제공하고 있습니다.</li>
					<li>궁금하신 내용의 답변이 없는 경우 상담신청을 이용해주시기 바랍니다.</li>
				</ul>
			</div>
			<div class="input-group">
				<input type="text" class="form-control" placeholder="검색어를 입력하세요">
				<span class="input-group-btn">
					<button class="btn btn-default btn-search" type="button">검색</button>
				</span>
		    </div>
			<div class="form-block">
				<div class="form-group">
					<label for="" class="sr-only">검색어</label>
					<div class="input-group">						
						<input type="text" class="form-control" placeholder="" />
						<div class="input-group-btn">
							<button class="btn btn-inverse">검색</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-box">
			<div class="panel-group" id="faq">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#faq1" class="collapsed" data-toggle="collapse" data-parent="#faq">
							<h3 class="panel-title">휴대폰이 본인 명의가 아닌 경우에도 신청이 가능한가요?</h3>
						</a>
					</div>
					<div class="panel-collapse collapse" id="faq1">
						<div class="panel-body">
							<div class="panel-answer">
								타인명의 휴대폰 사용 시에도 신청은 가능합니다. 본인인증의 경우 휴대폰인증, 신용카드인증, 공인인증서인증 총 3가지 방법이 있기 때문에 가능하신 인증방법을 이용해주시면 됩니다.
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#faq2" class="collapsed" data-toggle="collapse" data-parent="#faq">
							<h3 class="panel-title">휴대폰이 본인 명의가 아닌 경우에도 신청이 가능한가요?</h3>
						</a>
					</div>
					<div class="panel-collapse collapse" id="faq2">
						<div class="panel-body">
							<div class="panel-answer">
								타인명의 휴대폰 사용 시에도 신청은 가능합니다. 본인인증의 경우 휴대폰인증, 신용카드인증, 공인인증서인증 총 3가지 방법이 있기 때문에 가능하신 인증방법을 이용해주시면 됩니다.
							</div>
						</div>
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