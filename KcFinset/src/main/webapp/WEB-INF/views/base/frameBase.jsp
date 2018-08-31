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
<script src="<c:url value="/script/jquery/jquery.animateNumber.min.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if(userAgent == "Android") {
			window.Android.setEndApp('Y');
		}
		// slider
	    $('.slider').slick({
			dots: true,
			infinite: true,
			speed: 300,
			slidesToShow: 1,
			arrows: false,
			autoplay: false
	 	});
		
		if("<c:out value="${securityResult}" />" == 'Y') {
// 			if(userAgent == "iOS") {
//// 				location.href = "call://checkSecurity";
//					Jockey.send("checkSecurity");			
// 			} else if(userAgent == "Android") {
// 				window.Android.checkSecurity();
// 			} else {
// 				popSecurityInfo();
// 			}
		}
		
		// animateNumber
	 	$('.slider').on('afterChange', function(event, slick, currentSlide, nextSlide){
			console.log(currentSlide);
			if ( currentSlide === 0 ) {
				$('#aniNo1,#aniNo2,#aniNo3').text("0");
			} else {
			 	counter();
			}
		});
		
		function counter() {
		 	var comma_separator_number_step = $.animateNumber.numberStepFactories.separator(',')
			$('#aniNo1').animateNumber({
			    number: 18,
			    numberStep: comma_separator_number_step
			});
			$('#aniNo2').animateNumber({
			    number: 10352420,
			    numberStep: comma_separator_number_step
			});
			$('#aniNo3').animateNumber({
			    number: 165230250,
			    numberStep: comma_separator_number_step
			});
		}
		
		progressBar();
		
	});
	
	function progressBar() {
		// progress-bar
	 	$(".progress-bar-koscom .progress-header, .progress-bar-koscom .pointer").delay(0).queue(function(){
	 		$(this).css({"left":"40%","opacity":"1"});
	 	});
	 	$(".progress-bar-koscom .progress-bar").delay(0).queue(function(){
	 		$(this).css("width", "40%");
	 	});
	 	$(".progress-bar-kcb .progress-header, .progress-bar-kcb .pointer").delay(500).queue(function(){
	 		$(this).css({"left":"70%","opacity":"1"});
	 	});
	 	$(".progress-bar-kcb .progress-bar").delay(500).queue(function(){
	 		$(this).css("width", "70%");
	 	});
	 	$(".progress-bar-nice .progress-header, .progress-bar-nice .pointer").delay(1000).queue(function(){
	 		$(this).css({"left":"20%","opacity":"1"});
	 	});
	 	$(".progress-bar-nice .progress-bar").delay(1000).queue(function(){
	 		$(this).css("width", "20%");
	 	});
	}
	
</script>

</head>
<body>
<div id="wrapper" class="full">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				
			</div>
			<h1 class="logo">koscom</h1>
			<div class="input-group-btn">
				<button class="ui-nav nav-menu" type="button" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="credit-mgt-wrapper">
			<div class="slider">
				<!-- slide1 -->
				<div class="credit-mgt-box">
					<h2 class="title">신용 요약 관리</h2>
					<div class="credit-mgt-block">
						<div class="progress-group progress-bar-koscom">
							<div class="progress-header">
								<span class="grade"><em>04</em>/10등급</span>
								<label>KOSCOM SCORE</label>
							</div>
							<div class="progress">
								<i class="pointer"></i>
								<div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="10"></div>
							</div>
						</div>
						<div class="progress-group progress-bar-kcb">
							<div class="progress-header">
								<span class="grade"><em>07</em>/10등급</span>
								<label>KOSCOM SCORE</label>
							</div>
							<div class="progress">
								<i class="pointer"></i>
								<div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="10"></div>
							</div>
						</div>
						<div class="progress-group progress-bar-nice">
							<div class="progress-header">
								<span class="grade"><em>02</em>/10등급</span>
								<label>KOSCOM SCORE</label>
							</div>
							<div class="progress">
								<i class="pointer"></i>
								<div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="10"></div>
							</div>
						</div>
					</div>
					<p>
						위 정보는 신용정보회사에서 제공된 것이며,<br />
						실시간 상태를 반영하지 않습니다.
					</p>
				</div>
				<!-- slide2 -->
				<div class="credit-mgt-box">
					<h2 class="title">대출 요약 관리</h2>
					<div class="loan-mgt-block">
						<ul class="loan-mgt-items">
							<li><div class="item">
									<span class="counter"><em id="aniNo1">0</em></span>
									<label>대출건수(건)</label>
								</div></li>						
							<li><div class="item">
									<span class="counter"><em id="aniNo2">0</em></span>
									<label>대출원금(원)</label>
								</div></li>						
							<li><div class="item">
									<span class="counter"><em id="aniNo3">0</em></span>
									<label>대출잔액(원)</label>
								</div></li>						
						</ul>
					</div>
					<p class="text-right">
						UPDATE: 2017-06-15
					</p>
				</div>
			</div>
		</div>
		<a href="#url" class="bnr-credit-info">
			<h3 class="title"><em>신용정보</em> 상세조회</h3>
			<small>신용등급 및 신용등급 변동 이력 조회</small>
		</a>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>