<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>Finset</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<link rel="stylesheet" href="<c:url value="/style/animate.css"/>"/>
<script type="text/javascript">
$(document).ready(function () {
	
	// main
    $('.main-txt1').addClass('animated fadeIn').css("animation-delay","0.5s");
    $('.main-txt2').addClass('animated fadeIn').css("animation-delay","1.0s");
    $('.btn-more').addClass('animated fadeIn').css("animation-delay","1.2s");
    $('.ghost1').addClass('animated flipInX').css("animation-delay","1.5s");
    $('.ghost2').addClass('animated flipInX').css("animation-delay","2.0s");
    $('.ghost3').addClass('animated flipInX').css("animation-delay","2.5s");
    $('.ghost4').addClass('animated flipInX').css("animation-delay","3.0s");
	
 	// slick
    $('#main-slider').slick({
 	  dots: false,
 	  infinite: true,
 	  speed: 300,
 	  slidesToShow: 1,
 	  fade: true,
 	  arrows: false,
 	  asNavFor: '.ghost-group',
 	  autoplay: false
 	});
 	$('.ghost-group').slick({
 	  slidesToShow: 4,
 	  asNavFor: '.main-slider',
 	  dots: false,
 	  focusOnSelect: true,
 	  autoplay: false
 	});
 	$('.ghost-group .ghost-item').on('mouseover', function() {
	    $('#main-slider').slick('goTo', $(this).index());
	});
 	$('.slide-prev').click(function(){
 	    $("#main-slider").slick('slickPrev');
 	});
 	$('.slide-next').click(function(){
 	    $("#main-slider").slick('slickNext');
 	});
 	
 	//layer popup
 	function setCookie ( name, value, expiredays ) {
 		var todayDate = new Date();
 		todayDate.setDate( todayDate.getDate() + expiredays );
 		document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
 	}

 	$(function(){
 		var cookiedata = document.cookie;
 		
 		$(".pop-close").each(function(){
 			$(this).click(function(){
 				var chkbox = $(this).siblings("label").find("input"),
 					chkid = chkbox.attr("id");
 				
 				if ( chkbox.is(":checked") === true ) {
 					//하루닫기
 					setCookie(chkid, "checked", 1);
 					$(this).parents(".layerpop").hide();
 				} else {
 					$(this).parents(".layerpop").hide();
 				}
 			});
 		});
 		
 		$("input[name='chkpop']").each(function(){
 			var chkid = $(this).attr("id");

 			if ( cookiedata.indexOf( chkid+"=checked" ) < 0 ){
 				$(this).parents(".layerpop").show();
 			} else { 
 				$(this).parents(".layerpop").hide();
 			}
 		});
 	});

    $(".layerpop").draggable();
	
    // fullpage
    /*$('#wrapper').fullpage({
		anchors: ['fullpage1','fullpage2'],
		menu: '#gnb',
		navigation: false,
		navigationPosition: 'right',
		verticalCentered: true,
		afterLoad: function(anchorLink, index){
			if(index === 1){
				
			}
		},
		onLeave: function(index, nextIndex, direction){
			if(index === 1){
			}
		},
		scrollingSpeed: 1700
	});*/	
	
});
function showLogin(){
	location.href = "<c:url value='/loginsocial/loginSocial.crz'/>";
}
</script>
</head>
<body id="main">
<!-- Layer Popup -->
<div class="layerpop" style="top:100px;left:100px;">
    <a href="http://phishing-keeper.fss.or.kr/fss/vstop/main.jsp" target="_blank"><img src="/images/popup/pop_sample.png" alt="" /></a>
    <div class="pop-close-wrap">
		<label><input type="checkbox" name="chkpop" id="chkpop2" />
		오늘은 더이상 창 띄우지 않기</label>
		<a role="button" class="btn pop-close">닫기</a>
	</div>
</div>
<!-- Header -->
<%@ include file="/WEB-INF/include/gnb.jsp"%>
<!-- //Header -->
<div id="wrapper">
	<!-- slider -->
	<div class="main-slider-wrap">
		<button type="button" class="slide-ctrl slide-prev"></button>
		<button type="button" class="slide-ctrl slide-next"></button>
		<div class="main-slider" id="main-slider">
			<div class="main-slide slide1">
				<div class="container">
					<div class="slide-block">
						<h1 class="main-txt1">나한테 가장 유리한 <em>맞춤대출신청</em></h1>
						<p class="main-txt2">
							본인의 신용에 맞는 <em>맞춤대출정보</em>를 <em>쉽고, 빠르고, 안전</em>하게 <em>무료로 제공</em>합니다.<br />
							여러 금융사의 대출상품을 <strong>한눈에 비교</strong>하고 가장 <strong>유리한 대출상품</strong>을 <br />
							신청하세요!
						</p>
						<c:choose>
						 	<c:when test="${no_person eq '' || no_person eq 'anonymousUser'}">
								<a role="button" onclick="showLogin();" class="btn btn-more">자세히보기</a>
							</c:when>
							<c:otherwise>
								<a href="<c:url value="/fincook/infoFincook.crz"/>" class="btn btn-more">자세히보기</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="main-slide slide2">
				<div class="container">
					<div class="slide-block">
						<h1 class="main-txt1">쉽고 <em>간단한 절차</em>로 비교하고 이용하세요</h1>
						<p class="main-txt2">
							<em>대출신청 금액 입력, 간단한 본인확인, 금융사별 최적</em>의 대출 상품 안내 모두 끝났습니다. <br />
							쉽고, 간단하게 고객님께 꼭 <em>맞춤 대출 상품을 비교</em>하고<br />
							가장 유리한 상품으로 신청하세요!
						</p>
						<a href="<c:url value="/finauction/infoStep.crz"/>" class="btn btn-more">자세히보기</a>
					</div>
				</div>
			</div>
			<div class="main-slide slide3">
				<div class="container">
					<div class="slide-block">
						<h1 class="main-txt1"><em>아직도</em> 어려우신가요?</h1>
						<p class="main-txt2">
							<em>서비스 이용</em>이 어려우신가요? 저희의 정체가 궁금하신가요?<br />
							고객 여러분들이 가장 <em>자주 묻는 질문</em>들에 대한<br />
							저희의 답변은 다음과 같습니다
						</p>
						<a href="<c:url value="/customer/infoFaq.crz"/>" class="btn btn-more">자세히보기</a>
					</div>
				</div>
			</div>
			<div class="main-slide slide4">
				<div class="container">
					<div class="slide-block">
						<h1 class="main-txt1"><em>고객님께 도움</em>을 드릴수 있을까요?</h1>
						<p class="main-txt2">
							저희 <em>코스콤 최신 소식</em>을 전해드리겠습니다.<br />
							최고의 대출 상품 안내, 대출과 관련된 어려운 내용을 설명해드리고<br />
							신용 등급관리를 위한 <em>최고의 노하우들을 공유</em>해 드리고 있습니다
						</p>
						<a href="<c:url value="/board/listBoardInfoMain.crz"/>" class="btn btn-more">자세히보기</a>
					</div>
				</div>
			</div>
		</div>
		<div class="ghost-group-wrap">
			<div class="container">
				<div class="ghost-group">
					<c:choose>
					 	<c:when test="${no_person eq '' || no_person eq 'anonymousUser'}">
						<div class="ghost-item"><a rolo="button" onclick="showLogin();" class="btn-ghost ghost1"><span class="tit">코스콤맞춤대출</span></a></div>
						</c:when>
						<c:otherwise>
						<div class="ghost-item"><a href="<c:url value="/fincook/infoFincook.crz"/>" class="btn-ghost ghost1"><span class="tit">코스콤맞춤대출</span></a></div>
						</c:otherwise>
					</c:choose>
					<div class="ghost-item"><a href="<c:url value="/finauction/infoStep.crz"/>" class="btn-ghost ghost2"><span class="tit">이용절차</span></a></div>
					<div class="ghost-item"><a href="<c:url value="/customer/infoFaq.crz"/>" class="btn-ghost ghost3"><span class="tit">FAQ</span></a></div>
					<div class="ghost-item"><a href="<c:url value="/board/listBoardInfoMain.crz"/>" class="btn-ghost ghost4"><span class="tit">공지사항</span></a></div>					
				</div>
			</div>
		</div>
	</div>
	<!-- //slider -->
	<div class="consulting-link">
		<ul>
			<li><a href="#">목돈만들기</a></li>
			<li><a href="#">결혼자금만들기</a></li>
			<li><a href="#">은퇴자금만들기</a></li>
			<li><a href="#">주택자금만들기</a></li>
			<li><a href="#">직장인제테크</a></li>
			<li><a href="#">맞벌이부부재테크</a></li>
			<li><a href="#">자영업자제테크</a></li>
			<li><a href="#">전문직제테크</a></li>
		</ul>
	</div>
	<div class="consulting-block">
		<div class="container">
			<div class="consulting-list thumb-cate1">
				<a href="#none" class="inner">
					<span class="cate c1">재무컨설팅</span>
					<h3>투자설계</h3>
					<p>재무설계사가 고객이 원하는 재무목표 달성방안을 수립하면, 고객은 저축이나  투자를 통해 그 방안을 실행하게 되는데 이 실행 단계가 바로 투자설계입니다.</p>
				</a>
			</div>
			<div class="consulting-list thumb-cate2">
				<a href="#none" class="inner">
					<span class="cate c2">연령별재무</span>
					<h3>30대 재무설계</h3>
					<p>30대에는 내 집 마련, 결혼 출산 등으로 큰 규모의 자금이 들어갑니다. 따라서 30대 재무설계는 고수익 자산과 안전 자산에 골고루 투자하는 포트폴리오를 짜야 합니다.</p>
				</a>
			</div>
			<div class="consulting-list thumb-cate3">
				<a href="#none" class="inner">
					<span class="cate c1">재무컨설팅</span>
					<h3>부동산설계</h3>
					<p>부동산은 이제 투기의 대상이 아니라 최적의 포트폴리오를 구성하기 위한 투자 자산의 하나로 고객의 재무목표를 달성하기 위한 수단으로 이용 되어야 합니다.</p>
				</a>
			</div>
			<div class="consulting-list thumb-cate4">
				<a href="#none" class="inner">
					<span class="cate c3">테마별재무</span>
					<h3>직장인 재테크</h3>
					<p>직장인은 봉급생활자로서 급여를 받고 일하는 사람을 말합니다. 개인사업자나 전문직에 비해 부를 축적하는 속도가 더딘 것이 현실이므로 이런 상황을 피하기 위한 준비가 필요합니다.</p>
				</a>
			</div>
		</div>
	</div>
	<!-- Footer -->
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	<!-- //Footer -->
</div>
</body>
</html>