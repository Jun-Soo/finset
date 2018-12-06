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
$(function(){
	// slick slider
	$('.grade-slider').slick({
		infinite: false,
		arrows: false,
		swipeToSlide: true,
		slidesToShow: 5
	});
	
	var score = $(".guideline-score"),
		grade = $(".guideline-grade"),
		score_current = $(".slick-current").find(".grade-bar").data("score");
		grade_current = $(".slick-current").find(".grade-bar").data("grade")+"등급";	
	
	// 초기값
	score.text(score_current);
	grade.text(grade_current);
	
	// 그래프 셋팅
	$(".grade-bar").each(function(){
		var val = $(this).data("graph");
		$(this).css("height", val + "%");
	});
	
	$('.grade-slider').slick('slickGoTo', 12);
	
	// 스와이프 후 점수, 등급 변경
	$('.grade-slider').on('afterChange', function(event, slick, currentSlide, nextSlide){		
		var active_score = $(slick.$slides[currentSlide+2]).find(".grade-bar").data("score"),
			active_grade = $(slick.$slides[currentSlide+2]).find(".grade-bar").data("grade")+"등급";
		
		score.text(active_score);
		grade.text(active_grade);
		monthLength();
	});
	
	function monthLength() {
		var center_month = $(".slick-center").find(".month span"),
			center_month_length = center_month.text().length;	
		
		if ( center_month_length > 4 ) {
			$(".grade-slider-block").addClass("year-month");
		} else {
			$(".grade-slider-block").removeClass("year-month");
		}
	}
	monthLength();
});

//차트 클릭시 list이동
function listMoveTab(date){   
	var tabNm = $(".tabs > .active > a").attr("data-TabNm");   
	var elem = $("."+tabNm+"-"+date+":first");
	var offset = elem.offset();     
	if ( elem.length > 0 ) {
	// 	$('html, body').animate({scrollTop : offset.top-50},400);  
		   $('html, body').animate({scrollTop : offset.top-50});  
	}

}  
</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goCreditMain();">뒤로가기</button>
			</div>
			<h1>상세정보</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content">
		<!-- 월별추이그래프  -->
		<div class="grade-slider-block">
			<div class="grade-txt">월별추이</div>
			<div class="guideline-grade-active">
				<span class="guideline-score"></span>
				<span class="guideline-grade"></span>
			</div>
			<div class="guideline-group">
				<span class="guideline"><em>0</em></span>
				<span class="guideline"><em>100</em></span>
				<span class="guideline"><em>300</em></span>
				<span class="guideline"><em>500</em></span>
				<span class="guideline"><em>700</em></span>
				<span class="guideline"><em>900</em></span>
			</div>
			<div class="grade-slider">
				<c:forEach var="List" items="${chartList}" varStatus="status">
				<div class="slide">
					<div class="grade-bar-block"><div class="grade-bar" data-graph="${(List.rating_credit/900)*100}" data-grade="${List.grade_credit}" data-score="${List.rating_credit}"></div></div>
					<a href="#" onclick="listMoveTab('${List.chart_list_param}');"><h3 class="month"><span><c:out value="${List.chart_title}"/></span></h3></a>
				</div>
				</c:forEach>
			</div>
			<div class="h-guideline">
				<span class="h-line"></span>
				<span class="h-line"></span>
				<span class="h-line"></span>
				<span class="h-line"></span>
				<span class="h-line"></span>
				<span class="h-line"></span>
			</div>
		</div>
		<!-- //월별추이그래프  -->
		<div>
			<ul class="nav nav-outline nav-justified tabs">
				<li class="<c:if test='${tabNm eq \'tab1\'}'>active</c:if>"><a href="#tab1" data-TabNm='tab1' data-target="#tab1" data-toggle="tab">신용조회정보</a></li>
				<li class="<c:if test='${tabNm eq \'tab2\'}'>active</c:if>"><a href="#tab2" data-TabNm='tab2' data-target="#tab2" data-toggle="tab">대출/카드정보</a></li>
				<li class="<c:if test='${tabNm eq \'tab3\'}'>active</c:if>"><a href="#tab3" data-TabNm='tab3' data-target="#tab3" data-toggle="tab">연체정보</a></li>
			</ul>
		</div>
		<div class="tab-content">
			<!-- tab1 -->
			<div class="tab-pane <c:if test='${tabNm eq \'tab1\'}'>active</c:if>" id="tab1">
				<div class="sum-block sum-credit">
					<div class="sum-block-items">
						<div class="row tab-pane">
							<dl class="col-xs-6">
								<dt>최근 1개월</dt>
								<dd>00<span>건</span></dd>
							</dl>
							<dl class="col-xs-6">
								<dt>최근 1년</dt>
								<dd>00<span>건</span></dd>
							</dl>
						</div>
					</div>
				</div>
				<div class="list-block">
					<c:forEach var="List" items="${inquiryList}" varStatus="status">
						<fmt:parseDate var="dtInfo" value="${List.dt_info}" pattern="yyyy-MM-dd" /> 
						<fmt:formatDate var="tab1Date" value="${dtInfo}" pattern="yyyy-MM"/>
						<div class="container-fluid tab1-${tab1Date}">
							<div class="list-heading">
								<li class="bank-title">
									<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
									<c:out value="${List.nm_fc}"/>
								</li>
							</div>
							<div class="list-info">
								<dl>
									<dt>조회일자</dt>
									<dd><fmt:formatDate value="${dtInfo}" pattern="yyyy.MM.dd" /></dd>
								</dl>
								<%-- <dl>
									<dt>대상기관</dt>
									<dd><c:out value="${List.nm_fc}"/></dd>
								</dl> --%>
								<dl>
									<dt>조회목적</dt>
									<dd><c:out value="${List.change_contents}"/></dd>
								</dl>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- noti -->
				<div class="container">
					<div class="noti-section">
						<ul>
							<li>* 조회정보는 다음 기준에 해당하는 조회가 포함되지 않습니다.</li>
							<li>* 중복정보 : 동일기관에서 30일 이내에 동일 조회목적으로 여러 번 조회한 경우 최초 조회만 포함됩니다.</li>
							<li>* 특정 조회목적 : 서비스 테스트를 위한 조회, 단순 상담을 위한 조회, 민원, 민원상담, 프리워크아웃 관련, 증권계좌 개설, 법원명령, 본인조회 등의 이유로 조회한 경우는 포함되지 않습니다.</li>
							<li>* 비공개조회 : 기업의 대표자에 대하여 조회한 경우, 채권추심을 위해 금융기관 등이 조회한 경우 등 법적으로 개인 동의가 없어도 신용정보조회를 할 수 있는 경우, 포함되지 않습니다.</li>
							<li>* 서민금융종합지원대책의 시행으로 주요조회정보 중 2011년 10월 1일 이후에 조회된 내역은 금융사에 제공되지 않으며, 신용조회는 KCB 신용등급에 영향을 주지 않습니다. 단, 금융사기방지 및 금융미거래자의 신용등급부여 목적으로 신용조회정보가 활용될 수 있습니다.</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- //tab1 -->
			<!-- tab2 -->
			<div class="tab-pane <c:if test='${tabNm eq \'tab2\'}'>active</c:if>" id="tab2">
				<div class="sum-block sum-credit">
					<div class="sum-block-items">
						<div class="row tab-pane">
							<dl class="col-xs-6">
								<dt>최근 1개월</dt>
								<dd>00<span>건</span></dd>
							</dl>
							<dl class="col-xs-6">
								<dt>최근 1년</dt>
								<dd>00<span>건</span></dd>
							</dl>
						</div>
					</div>
				</div>
				<div class="list-block">
					<c:forEach var="List" items="${loanCardList}" varStatus="status">
						<fmt:parseDate var="dtInfo" value="${List.dt_info}" pattern="yyyy-MM-dd" /> 
						<fmt:formatDate var="tab2Date" value="${dtInfo}" pattern="yyyy-MM"/>
						<div class="container-fluid tab2-${tab2Date}">
							<div class="list-heading">
								<li class="bank-title">
									<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
									<c:out value="${List.nm_fc}"/>
								</li>
								<label class="label-type"><c:out value="${List.change_contents}"/></label>
							</div>
							<div class="list-info">
								<dl>
									<dt>정보등록일</dt>
									<dd><fmt:formatDate value="${dtInfo}" pattern="yyyy.MM.dd" /></dd>
								</dl>
								<%-- <dl>
									<dt>대상기관</dt>
									<dd><c:out value="${List.nm_fc}"/></dd>
								</dl> --%>
								<dl>
									<dt>수집처</dt>
									<dd>한국신용정보원</dd>
								</dl>
								<%-- <dl>
									<dt>사유</dt>
									<dd><c:out value="${List.change_contents}"/></dd>
								</dl> --%>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- noti -->
				<div class="container">
					<div class="noti-section">
						<ul>
							<li>* 본 정보는 서비스 가입 이후에만 제공됩니다.</li>
							<li>* 정보변동일은 정보 발생일자 또는 한국신용정보원 등록일자입니다.</li>
							<li>* APP Push를 통한 알람은 정보등록일 익영업일에 제공됩니다.</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- //tab2 -->
			<!-- tab3 --> 
			<div class="tab-pane <c:if test='${tabNm eq \'tab3\'}'>active</c:if>" id="tab3">
				<div class="sum-block sum-credit">
					<div class="sum-block-items">
						<div class="row tab-pane">
							<dl class="col-xs-6">
								<dt>최근 1개월</dt>
								<dd>00<span>건</span></dd>
							</dl>
							<dl class="col-xs-6">
								<dt>최근 1년</dt>
								<dd>00<span>건</span></dd>
							</dl>
						</div>
					</div>
				</div>
				<div class="list-block">
					<c:forEach var="List" items="${overdueList}" varStatus="status">
						<fmt:parseDate var="dtInfo" value="${List.dt_info}" pattern="yyyy-MM-dd" /> 
						<fmt:formatDate var="tab3Date" value="${dtInfo}" pattern="yyyy-MM"/>
						<div class="container-fluid tab3-${tab3Date}"> 
							<div class="list-heading">
								<li class="bank-title">
									<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
									<c:out value="${List.nm_fc}"/>
								</li>
								<label class="label-type"><c:out value="${List.change_contents}"/></label>
							</div>
							<div class="list-info"> 
								<dl>
									<dt>정보등록일</dt>
									<dd><fmt:formatDate value="${dtInfo}" pattern="yyyy.MM.dd" /></dd>
								</dl>
								<dl>
									<dt>수집처</dt>
									<dd>한국신용정보원</dd>
								</dl>
								<%-- <dl>
									<dt>사유</dt>
									<dd><c:out value="${List.change_contents}"/></dd>
								</dl> --%>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- noti -->
				<div class="container">
					<div class="noti-section">
						<ul>
							<li>* 본 정보는 서비스 가입 이후에만 제공됩니다.</li>
							<li>* 정보변동일은 정보 발생일자 또는 한국신용정보원 등록일자입니다.</li>
							<li>* APP Push를 통한 알람은 정보등록일 익영업일에 제공됩니다.</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- //tab3 -->
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
