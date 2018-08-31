<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko" class="page-full">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	// slider
    $('.slider').slick({
		dots: true,
		infinite: true,
		speed: 300,
		slidesToShow: 1,
		arrows: false,
		autoplay: false
 	});
});
</script>
</head>
<body>
<div id="wrapper" class="service-intro">
	<!-- Content -->
	<section id="content">
		<div class="slide-item">
			<div class="slider">
				<!-- slide1 -->
				<div class="intro-item intro-01">
					<p class="intro-copy">금융거래에 활용되는<br><strong>신용등급과 신용평점을 확인</strong>하세요.</p>
					<img src="/images/intro_phone_1.png" alt="" title="">
				</div>
				<!-- slide2 -->
				<div class="intro-item intro-02">
					<p class="intro-copy">수많은 <strong>대출정보를<br>한곳에서</strong> 관리하세요.</p>
					<img src="/images/intro_phone_2.png" alt="" title="">
				</div>
				<!-- slide3 -->
				<div class="intro-item intro-03">
					<p class="intro-copy">다양한 금융기관의 <strong>대출금리와<br>한도를 확인</strong>해보세요.</p>
					<img src="/images/intro_phone_3.png" alt="" title="">
				</div>
			</div>
		</div>
		<div class="btn-fixed-bottom affix-bottom">
			<a href="/m/login/frameSecurityCodeConfirm.crz?hp=${hp}&yn_grt=${pass}"role="button" class="btn btn-lg btn-block btn-primary">시작하기</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>