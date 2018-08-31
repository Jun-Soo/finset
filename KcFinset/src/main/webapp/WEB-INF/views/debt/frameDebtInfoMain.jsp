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
		if(userAgent == "Android") {
			window.Android.setEndApp('Y');
		}
        var nextDay = "${debtSummaryData.max_ymd_loanend}";
        //setTime(nextDay);
	});   
</script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
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
	
    //레이어창
    popInfo();
    
	// animateNumber
 	$('.slider').on('afterChange', function(event, slick, currentSlide, nextSlide){
		console.log(currentSlide);
		if ( currentSlide === 0 ) {
			$('#aniNo1,#aniNo2,#aniNo3,#aniNo4').text("0");
		} else {
		 	counter();
		}
	});
	
	function counter() {
	 	var comma_separator_number_step = $.animateNumber.numberStepFactories.separator(',')
		$('#aniNo1').animateNumber({
		    number: 02042,
		    numberStep: comma_separator_number_step
		});
		$('#aniNo2').animateNumber({
		    number: 06,
		    numberStep: comma_separator_number_step
		});
		$('#aniNo3').animateNumber({
		    number: 16,
		    numberStep: comma_separator_number_step
		});
		$('#aniNo4').animateNumber({
		    number: 32,
		    numberStep: comma_separator_number_step
		});
	}
    // progress-bar
    /*월 상환금액*/
    $(".progress-bar-sum .progress-header, .progress-bar-sum .pointer").delay(0).queue(function(){
        $(this).css({"left":"40%","opacity":"1"});
    });
    $(".progress-bar-sum .progress-bar").delay(0).queue(function(){
        $(this).css("width", "${debtSummaryData.rate_amt_contract }%");
    });
    /*만기상환까지 잔여기간*/
    $(".progress-bar-period .progress-header, .progress-bar-period .pointer").delay(500).queue(function(){
        $(this).css({"left":"70%","opacity":"1"});
    });
    $(".progress-bar-period .progress-bar").delay(500).queue(function(){
        $(this).css("width", "${debtSummaryData.dutation_now_rate }%");
    });
    /*누적이자금액*/
    $(".progress-bar-interest .progress-header, .progress-bar-interest .pointer").delay(1000).queue(function(){
        $(this).css({"left":"20%","opacity":"1"});
    });
    $(".progress-bar-interest .progress-bar").delay(1000).queue(function(){
        $(this).css("width", "${debtSummaryData.rate_repay }%");/*이자상환비율(%)*/
    });
    /*원리금 상환*/
    $(".progress-bar-repay .progress-header, .progress-bar-repay .pointer").delay(500).queue(function(){
        $(this).css({"left":"70%","opacity":"1"});
    });
    $(".progress-bar-repay .progress-bar").delay(500).queue(function(){
        <c:set var="t_repay_pni_per_income" value="${debtSummaryData.repay_pni_per_income}" />
        <c:if test="${ debtSummaryData.repay_pni_per_income gt 100.0}">
        <c:set var="t_repay_pni_per_income" value="100" />
        </c:if>
        $(this).css("width", "${t_repay_pni_per_income}%");
    });
});

function goUpdate(){
	location.href = "<c:url value='/m/debt/frameInDebtRefresh.crz'/>";
};
function goCalendar(){
	location.href = "<c:url value='/m/debt/frameDebtCalendar.crz'/>";
}
/* 
function setTime(nextDay) {
	
	if(!nextDay) return;
    var year = nextDay.substr(0,4);
    var month = nextDay.substr(4,2);
    var day    = nextDay.substr(6,2);
    var dateStr = month+"/"+day+"/"+year;
    var nextDateObj = new Date(dateStr);
    var nextTime = nextDateObj.getTime();

    // Update the count down every 1 second
    var x = setInterval(function() {

        // Get todays date and time
        var now = new Date().getTime();

        // Find the distance between now an the count down date
        var distance = nextTime - now;

        // Time calculations for days, hours, minutes and seconds
        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        // Output the result in an element with id="demo"
        //							    document.getElementById("demo").innerHTML = days + "d " + hours + "h "+ minutes + "m " + seconds + "s ";
        document.getElementById("demo").innerHTML = days + "일 "+hours + "시간 "+ minutes + "분 " + seconds + "초 ";


        // If the count down is over, write some text
        if (distance < 0) {
            clearInterval(x);
            document.getElementById("demo").innerHTML = "-";
        }
    }, 1000);
}
 */
</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>부채관리</h1>
			<div class="g-menu">
				<button type="button" class="ico ico-calendar" onclick="goCalendar();">달력</button>
<!-- 				<button type="button" class="ico ico-update" onclick="goUpdate();">업데이트</button> -->
			</div>
			
		</div>
	</header>
	<!-- Content -->
	<section id="content" class="page-debt-info">
		<div class="slide-item">
			<div class="slider">
				<!-- slide1 -->
				<div class="debt-info-item">
					<div class="sum-block sum-debt">
						<div class="goods-ea">보유 <em>${debtSummaryData.cnt}</em>건</div>
						<div class="sum-block-items">
							<div class="row">
								<dl class="col-xs-6">
									<dt>상환원금(당월)<a role="button" data-toggle="collapse" data-layer="popup" href="#help1" class="icon-link"><i class="pop-info ico-alert-w">정보</i></a></dt>
									<c:set var="cur_mon_mid_rpy_p" value="${debtSummaryData.cur_mon_mid_rpy_p}" />
									<c:if test="${cur_mon_mid_rpy_p ne '-'}">
										<c:set var="cur_mon_mid_rpy_p" value="${ufn:formatNumberPattern(cur_mon_mid_rpy_p,'###,###.##')}<span>만원</span>" />
									</c:if>
									<dd>${cur_mon_mid_rpy_p}</dd>
								</dl>
								<dl class="col-xs-6">
									<dt>대출잔액</dt>
									<c:set var="amt_remain" value="${debtSummaryData.amt_remain}" />
									<c:if test="${amt_remain ne '-'}">
										<c:set var="amt_remain" value="${ufn:formatNumberPattern(amt_remain,'###,###.##')}<span>만원</span>" />
									</c:if>
									<dd>${amt_remain}</dd>
								</dl>
							</div>
							<div class="progress-group progress-bar-sum">
								<div class="progress">
									<div class="progress-bar" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<div class="progress-label">
									<label class="label">원금상환비율<a role="button" data-toggle="collapse" data-layer="popup" href="#help2" class="icon-link"><i class="pop-info ico-alert-w">정보</i></a></label>
									<span>${debtSummaryData.rate_amt_contract }%</span>
								</div>
							</div>
						</div>
					</div>
					<div class="list-block">
						<c:forEach var="oneDebt" items="${debtListData}" varStatus="status">
							<div class="container-fluid">
                                <a href="<c:url value='/m/debt/frameInDebtDetail.crz'/>?no_manage_info=${oneDebt.no_manage_info}">
									<div class="list-heading">
										<li class="bank-title">
											<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${oneDebt.cd_fc}');"></span>${oneDebt.nm_fc}
										</li>
										<label class="label-type">${oneDebt.debt_type}</label><%--/* 신용대출 or 담보대출 표기  */--%>
									</div>
									<div class="list-info">
										<dl>
                                            <dt>상환금액(당월)</dt>
                                            <c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6'}">
	                                            <c:set var="amt_repay" value="${oneDebt.amt_repay}" />
												<c:if test="${amt_repay ne '-'}">
													<c:set var="amt_repay" value="${ufn:formatNumberPattern(oneDebt.amt_repay,'###,###.##')}만원" />
												</c:if>
												<dd>${amt_repay}</dd><%--월 상환금액 (완료or예정)--%>
											</c:if>
                                            <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}"><dd>-</dd></c:if>
										</dl>
										<dl>
											<dt>대출잔액</dt>
											<c:set var="amt_remain" value="${oneDebt.amt_remain}" />
											<c:if test="${amt_remain ne '-'}">
												<c:set var="amt_remain" value="${ufn:formatNumberPattern(oneDebt.amt_remain,'###,###.##')}만원" />
											</c:if>
											<dd>${amt_remain}</dd><%--대출잔액--%>
										</dl>
										<dl>
											<dt><c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6'}">대출원금</c:if>
                                                <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}">대출한도</c:if></dt>
											<c:set var="amt_contract" value="${oneDebt.amt_contract}" />
											<c:if test="${amt_contract ne '-'}">
												<c:set var="amt_contract" value="${ufn:formatNumberPattern(oneDebt.amt_contract,'###,###.##')}만원" />
											</c:if>
											<dd>${amt_contract}</dd><%--대출원금--%>
										</dl>
										<dl>
											<dt><c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6'}">원금상환비율</c:if>
                                                <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}">잔여한도비율</c:if></dt>
											<dd>${oneDebt.rate_repay}%</dd><%--원금상환비율--%>
										</dl>
									</div>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- slide2 -->
                <div class="debt-info-item">
                    <div class="sum-block sum-debt">
                    	<div class="goods-ea">보유 <em>${debtSummaryData.cnt}</em>건 </div>
                        <div class="sum-block-items">
                            <div class="row">
                                <dl class="col-xs-6">
                                    <dt>이자금액<span>(당월)</span><a role="button" data-toggle="collapse" data-layer="popup" href="#collapseExample2" class="icon-link"><i class="pop-info ico-alert-w">정보</i></a></dt>
                                    <c:set var="cur_mon_mid_rpy_i" value="${debtSummaryData.cur_mon_mid_rpy_i}" />
                                    <c:if test="${cur_mon_mid_rpy_i ne '-'}">
                                        <c:set var="cur_mon_mid_rpy_i" value="${ufn:formatNumberPattern(cur_mon_mid_rpy_i,'###,###.##')}<span>만원</span>" />
                                    </c:if>
                                    <dd>${cur_mon_mid_rpy_i}</dd>
                                </dl>                                
                                <dl class="col-xs-6">
                                    <dt>금리(평균)<a role="button" data-toggle="collapse" data-layer="popup" href="#collapseExample3" class="icon-link"><i class="pop-info ico-alert-w">정보</i></a></dt>
                                    <dd>${debtSummaryData.ever_interest }<span>%</span></dd>
                                </dl>                               
                            </div>
                            <div class="progress-group progress-bar-interest">
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <div class="progress-label">
                                    <label class="label">이자상환비율<a role="button" data-toggle="collapse" data-layer="popup" href="#help5" class="icon-link"><i class="pop-info ico-alert-w">정보</i></a></label>
                                    <span>${debtSummaryData.rate_repay }%</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="list-block">
                        <c:forEach var="oneDebt" items="${debtListData}" varStatus="status">
                            <div class="container-fluid">

                                <a href="<c:url value='/m/debt/frameInDebtDetail.crz'/>?no_manage_info=${oneDebt.no_manage_info}">
                                    <div class="list-heading">
                                        <li class="bank-title">
                                            <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${oneDebt.cd_fc}');"></span>${oneDebt.nm_fc}
                                        </li>
                                        <label class="label-type">${oneDebt.debt_type}</label>
                                    </div>
                                    <div class="list-info">
                                        <dl>
                                            <dt>이자금액<span>(월)</span></dt>
                                            <c:set var="o_amt_repay_i" value="${oneDebt.amt_repay_i}"/>
                                            <c:set var="f_amt_repay_i" value="${ufn:formatNumberPattern(oneDebt.amt_repay_i,'###,###.##')}" />
                                            <c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6' and oneDebt.amt_repay_i > '0'}">
                                                <dd>${f_amt_repay_i}만원</dd>
                                            </c:if>
                                            <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}">
                                                <dd>-</dd>
                                            </c:if>
                                            <c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6' and oneDebt.amt_repay_i eq '0'}">
                                                <dd>${o_amt_repay_i}만원</dd>
                                            </c:if>
                                        </dl>
                                        <dl>
                                            <dt>이자상환비율(당월)</dt>
                                            <dd><c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6'}">${oneDebt.rate_mm_repay_i}%</c:if>
                                                <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}">-</c:if></dd>
                                        </dl>
                                        <dl>
                                            <dt>잔여이자금액</dt>
                                            <c:set var="o_remain_amt_repay_i" value="${oneDebt.remain_amt_repay_i}"/>
                                            <c:set var="f_remain_amt_repay_i" value="${ufn:formatNumberPattern(oneDebt.remain_amt_repay_i,'###,###.##')}" />
                                            <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}">
                                                <dd>-</dd>
                                            </c:if>
                                            <c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6' and oneDebt.remain_amt_repay_i > '0'}">
                                                <dd>${f_remain_amt_repay_i}만원</dd>
                                            </c:if>
                                            <c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6' and oneDebt.remain_amt_repay_i eq '0'}">
                                                <dd>${o_remain_amt_repay_i}만원</dd>
                                            </c:if>
                                        </dl>
                                        <dl>
                                            <dt>대출금리</dt>
                                            <dd>${oneDebt.ever_interest}%</dd>
                                        </dl>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
				<!-- slide3 -->
				<div class="debt-info-item">
					<div class="sum-block sum-debt">
						<div class="goods-ea">보유 <em>${debtSummaryData.cnt}</em>건</div>
						<div class="sum-block-items">
							<div class="row">
								<dl class="col-xs-6">
									<dt>원리금 상환(당월)<a role="button" data-toggle="collapse" data-layer="popup" href="#help6" class="icon-link"><i class="pop-info ico-alert-w">정보</i></a></dt>
                                    <c:set var="cur_mon_mid_rpy" value="${debtSummaryData.cur_mon_mid_rpy}" />
                                    <c:if test="${cur_mon_mid_rpy ne '-'}">
                                        <c:set var="cur_mon_mid_rpy" value="${ufn:formatNumberPattern(cur_mon_mid_rpy,'###,###.##')}<span>만원</span>" />
                                    </c:if>

									<dd>${cur_mon_mid_rpy}</dd>
								</dl>
								<dl class="col-xs-6">
									<dt>소득(월평균)</dt>
									<dd>${ufn:formatNumberPattern(debtSummaryData.amt_etm_income,"###,###.##") }<span>만원</span></dd><%--소득(년)--%>
								</dl>
							</div>
							<div class="progress-group progress-bar-repay">
								<div class="progress">
									<div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<div class="progress-label">
									<label class="label">원리금상환/소득<a role="button" data-toggle="collapse" data-layer="popup" href="#help7" class="icon-link"><i class="pop-info ico-alert-w">정보</i></a></label>
									<span>${debtSummaryData.repay_pni_per_income }%</span>
								</div>
							</div>
						</div>
					</div>
					<div class="list-block">
						<c:forEach var="oneDebt" items="${debtListData}" varStatus="status">
							<div class="container-fluid">
                                <a href="<c:url value='/m/debt/frameInDebtDetail.crz'/>?no_manage_info=${oneDebt.no_manage_info}">
									<div class="list-heading">
                                        <li class="bank-title">
                                            <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${oneDebt.cd_fc}');"></span>${oneDebt.nm_fc}
                                        </li>
                                        <label class="label-type">${oneDebt.debt_type}</label>
									</div>
									<div class="list-info">
										<dl>
											<dt>원금 상환(당월)</dt>
											<dd>
                                                <c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6'}">${ufn:formatNumberPattern(oneDebt.amt_repay_p,"###,###.##")}만원</c:if>
                                                <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}">-</c:if>
                                            </dd>
										</dl>
										<dl>
											<dt>이자 상환(당월)</dt>
											<dd><c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6'}">${ufn:formatNumberPattern(oneDebt.amt_repay_i,"###,###.##")}만원</c:if>
                                                <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}">-</c:if></dd>
										</dl>
										<dl>
											<dt>원리금 상환(당월)</dt>
											<dd><c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6'}">${ufn:formatNumberPattern(oneDebt.amt_repay,"###,###.##")}만원</c:if>
                                                <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}">-</c:if></dd>
										</dl>
										<dl>
											<dt>원리금 상환/소득</dt>
											<dd><c:if test="${oneDebt.cd_type_deal ne '3' and oneDebt.cd_type_deal ne '6'}">${oneDebt.repay_per_income}%</c:if>
                                                <c:if test="${oneDebt.cd_type_deal eq '3' or oneDebt.cd_type_deal eq '6'}">-</c:if></dd>
										</dl>
									</div>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- slide4 -->
                <div class="debt-info-item">
                    <div class="sum-block sum-debt">
                    	<div class="goods-ea">보유 <em>${debtSummaryData.cnt}</em>건 </div>
                        <div class="sum-block-items">
                            <div class="row">
                                <dl class="col-xs-6">
                                    <dt>잔여 기간</dt>
                                    <dd>${debtSummaryData.rest_term}<span>개월</span></dd>
                                </dl>                                
                                <dl class="col-xs-6">
                                    <dt>대출 기간</dt>
                                    <dd>${debtSummaryData.loan_term}<span>개월</span></dd>
                                </dl>
                                
                            </div>
                        </div>
                        <div class="progress-group progress-bar-period">
                            <div class="progress">
                                <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="progress-label">
                                <span class="pull-left">${ufn:formatDateDot(debtSummaryData.min_ymd_loan) }</span>
                                <span>${ufn:formatDateDot(debtSummaryData.max_ymd_loanend) }</span>
                            </div>
                        </div>
                        <!--
						<p class="update-txt">
							<em>${debtSummaryData.cnt}건</em><%-- / 기준일자 : ${ufn:formatDateDot(debtSummaryData.dt_std) }--%>
						</p>
						 -->
                    </div>
                    <div class="list-block">
                        <c:forEach var="oneDebt" items="${debtListData}" varStatus="status">
                            <div class="container-fluid">
                                <a href="<c:url value='/m/debt/frameInDebtDetail.crz'/>?no_manage_info=${oneDebt.no_manage_info}">
                                    <div class="list-heading">
                                        <li class="bank-title">
                                            <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${oneDebt.cd_fc}');"></span>${oneDebt.nm_fc}
                                        </li>
                                        <label class="label-type">${oneDebt.debt_type}</label><%--신용대출 or 담보대출 표기--%>
                                    </div>
                                    <div class="list-info">
                                        <dl>
                                            <dt>개설일자</dt>
                                            <dd>${ufn:formatDateDot(oneDebt.ymd_loan)}</dd>
                                        </dl>
                                        <dl>
                                            <dt>만기일자</dt>
                                            <dd>${ufn:formatDateDot(oneDebt.ymd_loanend)}</dd>
                                        </dl>
                                        <dl>
                                            <dt>총대출기간</dt>
                                            <c:set var="term_all" value="${oneDebt.term_all}" />
                                            <c:if test="${term_all ne '-'}">
                                                <c:set var="term_all" value="${ufn:formatNumberPattern(oneDebt.term_all,'###,###')}개월" />
                                            </c:if>
                                            <dd>${term_all}</dd>
                                        </dl>
                                        <dl>
                                            <dt>잔여기간</dt>
                                            <c:set var="term_remain" value="${oneDebt.term_remain}" />
                                            <c:if test="${term_remain ne '-'}">
                                                <c:set var="term_remain" value="${ufn:formatNumberPattern(term_remain,'###,###')}개월" />
                                            </c:if>
                                            <c:if test="${term_remain < '0'}">
                                                <c:set var="term_remain" value="-"/>
                                            </c:if>
                                            <dd>${term_remain}</dd>
                                        </dl>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>	
</div>
<div id="help1" class="collapse popup-layer">
	<span class="title">당월 상환(예정/완료)인 대출원금의 합</span>
	<p>한도대출(마이너스통장) 제외</p>
	<p>과거 대출 상환금액을 기준으로 추정한 값으로 실제 발생 금액과 차이가 발생할 수 있습니다.</p>
	<button data-toggle="collapse" data-target="#help1" class="btn-close _popup_close"><i class="icon-close">레이어 닫기</i></button>
</div>
<div id="help2" class="collapse popup-layer">
	<span class="title">전체 대출원금 중 상환된 원금의 비율((대출원금-대출잔액)/대출원금*100)</span>
	<p>한도대출(마이너스통장)은 대출잔액만 포함</p>
	<button data-toggle="collapse" data-target="#help2" class="btn-close _popup_close"><i class="icon-close">레이어 닫기</i></button>
</div>
<div id="collapseExample2" class="collapse popup-layer">
	<span class="title">당월 상환(예정/완료)인 대출이자의 합</span>
	<p>한도대출(마이너스통장) 제외</p>
	<p>과거 대출 상환금액을 기준으로 추정한 값으로 실제 발생 금액과 차이가 발생할 수 있습니다.</p>
	<button data-toggle="collapse" data-target="#collapseExample2" class="btn-close _popup_close"><i class="icon-close">레이어 닫기</i></button>
</div>
 <div id="collapseExample3" class="collapse popup-layer">
	<span class="title">보유 대출의 잔액을 기준으로 계산한 금리의 평균값(가중평균)</span>
	<p>한도대출(마이너스통장) 제외</p>
	<p>과거 대출 상환금액을 기준으로 추정한 값으로 실제 발생 금액과 차이가 발생할 수 있습니다.</p>
	<button data-toggle="collapse" data-target="#collapseExample3" class="btn-close _popup_close"><i class="icon-close">레이어 닫기</i></button>
</div>
<div id="help5" class="collapse popup-layer">
	<span class="title">당월 상환(예정/완료)인 전체 대출 원리금중 이자의 비율</span>
	<p>한도대출(마이너스통장) 제외</p>
	<p>과거 대출 상환금액을 기준으로 추정한 값으로 실제 발생 금액과 차이가 발생할 수 있습니다.</p>
	<button data-toggle="collapse" data-target="#help5" class="btn-close _popup_close"><i class="icon-close">레이어 닫기</i></button>
</div>
<div id="help6" class="collapse popup-layer">
	<span class="title">당월 상환(예정/완료)인 대출 원리금(원금+이자)의 합계</span>
	<p>한도대출(마이너스통장) 제외</p>
	<p>과거 대출 상환금액을 기준으로 추정한 값으로 실제 발생 금액과 차이가 발생할 수 있습니다.</p>
	<button data-toggle="collapse" data-target="#help6" class="btn-close _popup_close"><i class="icon-close">레이어 닫기</i></button>
</div>
<div id="help7" class="collapse popup-layer">
	<span class="title">원리금 상환 금액 및 소득을 기준으로 추정한 소득대비 대출 원리금 상환 금액의 비율</span>
	<p>한도대출(마이너스통장) 제외</p>
	<p>과거 대출 상환금액 및 소득을 기준으로 추정한 값으로 실제 발생 금액과 차이가 발생할 수 있습니다.</p>
	<button data-toggle="collapse" data-target="#help7" class="btn-close _popup_close"><i class="icon-close">레이어 닫기</i></button>
</div>
</body>
</html>
