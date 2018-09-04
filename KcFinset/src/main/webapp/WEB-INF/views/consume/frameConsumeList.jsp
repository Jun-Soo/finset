<%@page import="com.koscom.util.JSTLFunction"%>
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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
var standardDt;
$(document).ready(function() {
	$(".g-menu #setting").show();
	$(".g-menu #delete").hide();
	$(".g-menu #save").hide();
	var scrollTop = 0;
	standardDt = getStandardDt();
	$("#ym").val(formatHead((getTodayYm())+""));
	setTimeout(function(){
		makeBtnInquire();
	},500);
	
	$("#setting").on("click",function(){
		$("#ym").attr("disabled",true);
		frmConsumeList.action = "<c:url value='/m/consume/frameConsumeSetting.crz'/>";
		frmConsumeList.submit();
	});
	
	$(document).on("click",".consume_list",function(){
		scrollTop = $(window).scrollTop();
		
		$("#consume_list").hide();
		var means_consume = $(this).find(".means_consume").val();
		var contents = $(this).find(".contents").text();
		var dt_trd = formatDate($(this).find(".full_dt_trd").val(),true);
// 		var nm_card = $(this).find(".nm_card").text();
// 		var amt_in_out = formatAmt($(this).find(".amt").text().replace(/[^0-9]/g,""),false);
		var amt_in_out = $(this).find(".amt_in_out").text().replace(/[^0-9]/g,"");
		var cd_class = $(this).find(".cd_class").val();
		var nm_class = $(this).find(".subcontent_left").text();
		var memo = $(this).find(".memo").text();
		
		$("#consume_detail").find("#means_consume").selectpicker("val",means_consume);
		$("#consume_detail").find("#contents").val(contents);
		$("#consume_detail").find("#dt_trd").val(dt_trd);
		$("#consume_detail").find("#amt_in_out").val(amt_in_out);
// 		$("#consume_detail").find("#category").val(cd_class).attr("selected",true);
		$("#consume_detail").find("#category").selectpicker("val",cd_class);
		$("#consume_detail").find("#nm_class").val(nm_class);
		$("#consume_detail").find("#memo").val(memo);
		
		$(".g-menu #setting").hide();
		$(".g-menu #delete").show();
		$(".g-menu #save").show();
		$("#consume_detail").show();
	});
	
	$("#header").click(function(){
		$("#consume_detail").hide();
		$(".g-menu #setting").show();
		$(".g-menu #delete").hide();
		$(".g-menu #save").hide();
		$("#consume_list").show();
		$(window).scrollTop(scrollTop);
	});

	$(".btn-check").off("click").on("click",function(){
		if($(this).attr("class").indexOf("active")>-1){
			$(this).removeClass("active");
		} else {
			$(this).addClass("active");
		}
		chkCondition();
	});
	$("#prevMM").on("click",function(){
		setPrevMM();
	});
	$("#nextMM").on("click",function(){
		setNextMM();
	});
	
	$(document).on("click",".btn_inquire",function(event){
		event.preventDefault();
		event.stopPropagation();
		listPersonTransDetail();
		$(".label_means_consume").next().removeClass("open");
		$(".trans_detail").addClass("open");
	});
	
	$(document).on("click",".swipe",function(){
		if($(".trans_detail").attr("class").indexOf("open")>-1) {
			$(".trans_detail").removeClass("open");
			$("button[data-id='means_consume']").attr("aria-expanded",false);
			flag();
		}
	});
	
	$(document).on("click",".trans_detail .consume_list", function(){
		var an = $(this).find(".subcontent_right").text();
		var contents = $(this).find(".contents").text();
		var dt_trd = formatDate($(this).find(".full_dt_trd").val(),true);
		var amt_in_out = $(this).find(".amt_in_out").text().replace(/[^0-9]/g,"");
		var cd_class = "99";
		var nm_class = $(this).find(".subcontent_left").text();
		
		$("#consume_detail").find("#means_consume").selectpicker("hide");
		$(".label_means_consume").text("계좌");
		$("#consume_detail").find("#an").attr("type","text");
		
		$("#consume_detail").find("#an").val(an);
		$("#consume_detail").find("#contents").val(contents);
		$("#consume_detail").find("#dt_trd").val(dt_trd);
		$("#consume_detail").find("#amt_in_out").val(amt_in_out);
		$("#consume_detail").find("#category").selectpicker("val",cd_class);
		$("#consume_detail").find("#nm_class").val(nm_class);
	});
});

var listConsumeInfo = function(){
	//frmConsumeList.serialize()는 화면이 뜨자마자 적용이 안됨
	var data = $("#frmConsumeList").serialize();
	data = data.replace(".","");
	$.ajax({
		url : "<c:url value='/m/consume/listConsumeInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function(result) {
			makeProgressBar(result);
			makeConsumeInfoList(result);
		},
		error : function(e) {
			toastMsg('정보 조회 중 오류가 발생했습니다. 관리자에게 연락 바랍니다.');
		}
	});
}

var listPersonTransDetail = function(){
	//frmConsumeList.serialize()는 화면이 뜨자마자 적용이 안됨
	var data = $("#frmConsumeList").serialize();
	data = data.replace(".","");
	$.ajax({
		url : "<c:url value='/m/consume/listPersonTransDetail.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function(result) {
			makePersonTransDetailList(result);
		},
		error : function(e) {
			toastMsg('정보 조회 중 오류가 발생했습니다. 관리자에게 연락 바랍니다.');
		}
	});
}

var getTodayYm = function() {
	return getYm(new Date());
}

var getYm = function(date) {
	return date.getFullYear()+(((date.getMonth()+1)+"").length==1?"0":"")+(date.getMonth()+1);
}

var formatHead = function(dateStr) {
	return dateStr.substr(0,4)+"."+dateStr.substr(4,6);
}

//마지막 날, 첫 날 등의 변수에 따라 한 달의 기준이 달라질 수 있기 때문에 15로 고정
var getStandardDt = function() {
	var date = new Date();
	var strDate = date.getFullYear()+"/"+(((date.getMonth()+1)+"").length==1?"0":"")+(date.getMonth()+1)+"/15";
	return new Date(strDate);
}

var setPrevMM = function() {
	standardDt.setMonth(standardDt.getMonth()-1);
	$("#ym").val(formatHead(getYm(standardDt)));
	listConsumeInfo();
}

var setNextMM = function() {
	standardDt.setMonth(standardDt.getMonth()+1);
	$("#ym").val(formatHead(getYm(standardDt)));
	listConsumeInfo();
}


var makeProgressBar = function(result) {
	var income = parseFloat(result.income);
	var consume = parseFloat(result.consume);
	
	$("#text_income").text(formatAmt(income,false)+" 원");
	$("#text_consume").text(formatAmt(consume,false)+" 원");
	//수정 필요
	$(".progress-bar").css("width","20%");
}

var makeConsumeInfoList = function(result) {
	$(".list-group").html("");
	var list = result.listConsumeInfo;
	for(var i=0;i<list.length;i++){
		var amt_in_out = list[i].type_in_out=="01"?formatAmt(list[i].amt_in_out,false):formatAmt(list[i].amt_in_out,true);
		var tags =
			"<div class='list-group-item consume_list "+list[i].type_in_out+"'>"+
				"<input type='hidden' class='means_consume' value='"+list[i].means_consume+"'/>"+
				"<input type='hidden' class='tm_trd' value='"+list[i].tm_trd+"'/>"+
				"<input type='hidden' class='nm_type' value='"+list[i].nm_type+"'/>"+
				"<input type='hidden' class='full_dt_trd' value='"+list[i].dt_trd+"'/>"+
				"<input type='hidden' class='cd_class' value='"+list[i].cd_class+"'/>"+
				"<input type='hidden' class='memo' value='"+list[i].memo+"'/>"+
				"<input type='hidden' class='grade' value='"+list[i].grade+"'/>"+
				"<div class='consume_head'>"+
					"<span class='dt_trd'>"+formatDate(list[i].dt_trd, false)+"</span>"+
					"<span class='contents'>"+list[i].contents+"</span>"+
					"<span class='amt_in_out'>"+amt_in_out+" 원</span>"+
				"</div>"+
				"<div class='consume_tail'>"+
					"<span class='subcontent_left'>"+list[i].nm_class+"</span>"+
					"<span class='subcontent_right'>"+list[i].nm_card+"</span>"+
				"</div>"+
			"</div>"
			;
		$(".list-group").append(tags);
	}
	chkCondition();
}

var formatDate = function(date, isFull){
	var yyyy = date.substring(0,4);
	var mm = date.substring(4,6);
	var dd = date.substring(6,8);
	if(isFull) {
		return yyyy+"-"+mm+"-"+dd;
	} else {
		return mm+"-"+dd;
	}
}

var formatTime = function(time) {
	var hh = time.substring(0,2);
	var mm = time.substring(2,4);
	var ss = time.substring(4,6);
	return hh+":"+mm+":"+ss;
}

var formatAmt = function(amt, isMinus) {
	if(isMinus) {
		return "-"+Number(amt).toLocaleString("en").split(".")[0];
	} else {
		return Number(amt).toLocaleString("en").split(".")[0];
	}
}

var chkCondition = function() {
	var list = $(".list-group");
	if(($("#btn_income").attr("class")).indexOf("active")>-1){
		$(".01").removeClass("div_hidden");
	} else {
		$(".01").addClass("div_hidden");
	}
	if(($("#btn_consume").attr("class")).indexOf("active")>-1){
		$(".02").removeClass("div_hidden");
	} else {
		$(".02").addClass("div_hidden");
	}
}

var makePersonTransDetailList = function(result){
	$(".trans_detail .inner").html("");
	var list = result.listPersonTransDetail;
	for(var i=0;i<list.length;i++) {
		var tags = 
			"<li>"+
				"<div class='list-group-item consume_list'>"+
					"<input type='hidden' class='full_dt_trd' value='"+list[i].dt_trd+"'/>"+
					"<input type='hidden' class='tm_trd' value='"+list[i].tm_trd+"'/>"+
					"<input type='hidden' class='cd_fc' value='"+list[i].cd_fc+"'/>"+
					"<div class='consume_head'>"+
					"<span class='dt_trd'>"+formatDate(list[i].dt_trd, false)+"</span>";
			tags += (list[i].doc1||"")==""?
					"<span class='contents'>"+list[i].doc2+"</span>":
					"<span class='contents'>"+list[i].doc1+"</span>";
			tags += ((list[i].amt_wdrl||"")=="")||list[i].amt_wdrl==0?
					"<span class='amt_in_out'>"+formatAmt(list[i].amt_dep,false)+" 원</span>":
					"<span class='amt_in_out'>"+formatAmt(list[i].amt_wdrl,true)+" 원</span>";
			tags +=
				"</div>"+
				"<div class='consume_tail'>"+
					"<span class='subcontent_left'>"+list[i].nm_fc+"</span>"+
					"<span class='subcontent_right'>"+list[i].an+"</span>"+
				"</div>"+
			"</li>";
		$(".trans_detail .inner").append(tags);
	}
}

var makeBtnInquire = function() {
	var a = $(".label_means_consume").next().find("li[data-original-index='0']").children().first();
	a.append("<button class='btn btn_inquire'>계좌조회</button>");
}

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
			<div class="g-menu">
				<button id="setting" type="button" style="color: white;">설정</button>
				<button id="delete" type="button" style="color: white;">삭제</button>
				<button id="save" type="button" style="color: white;">저장</button>
			</div>
			<h1>지출관리</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div id="consume_list">
				<div class="sum-block consume_title">
				<div class="calendar_head">
					<form id="frmConsumeList" name="frmConsumeList" method="POST">
						<span id="prevMM">&lt;</span><input type="text" id="ym" name="ym" readonly="readonly"/><span id="nextMM">&gt;</span>
					</form>
				</div>
				<div class="sum-block-items">
					<div class="row">
						<dl class="col-xs-6">
							<dt>수입</dt>
							<dd id="text_income">${ufn:formatNumber(income)} 원</dd>
						</dl>
						<dl class="col-xs-6">
							<dt>지출</dt>
							<dd id="text_consume">${ufn:formatNumber(consume)} 원</dd>
						</dl>
					</div>
					<div class="progress-group progress-bar-sum">
						<div class="progress">
							<div class="progress-bar" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"></div>
						</div>
						<div class="progress-label">
							<label class="label">예산대비 소비율</label>
							<span>20%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="container consume_condition">
<!-- 				<h2 class="h2">거래방식</h2> -->
				<div id="trade_type" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
					<button type="button" id="btn_income" name="btn_income" class="btn btn-check active">수입</button>
					<button type="button" id="btn_consume" name="btn_consume" class="btn btn-check active">지출</button>
				</div>
			</div>
			<div class="list-group">
				<c:forEach var="vo" items="${listConsumeInfo}">
					<div class="list-group-item consume_list ${vo.type_in_out}">
						<input type="hidden" class="means_consume" value="${vo.means_consume}"/>
						<input type="hidden" class="tm_trd" value="${vo.tm_trd}"/>
						<input type="hidden" class="nm_type" value="${vo.nm_type}"/>
						<input type="hidden" class="full_dt_trd" value="${vo.dt_trd}"/>
						<input type="hidden" class="cd_class" value="${vo.cd_class}"/>
						<input type="hidden" class="memo" value="${vo.memo}"/>
						<input type="hidden" class="grade" value="${vo.grade}"/>
						<div class="consume_head">
							<span class="dt_trd">${ufn:formatDate(vo.dt_trd+"01")}</span>
							<span class="contents">${vo.contents}</span>
							<span class="amt_in_out">${vo.amt_in_out}</span>
						</div>
						<div class="consume_tail">
							<span class="subcontent_left">${vo.nm_class}</span>
							<span class="subcontent_right">${vo.nm_card}</span>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="consume_detail" class="div_hidden">
			<div class="container">
				<div class="form-inline">
	                <div class="form-group">
	                    <label for="means_consume" class="label_means_consume">결제 수단</label>
	                    <select class="selectpicker" data-header="결제 수단" name="means_consume" id="means_consume">
	                    	<option value="03">입출금 계좌</option>
	                    	<option value="01">카드</option>
	                    	<option value="02">현금</option>
	                    </select>
	                    <input type="hidden" class="form-control" name="an" id="an" autocomplete="off"/>
	                </div>
	                <div class="form-group">
	                    <label for="amt_in_out">금액</label>
	                    <input type="number" class="form-control" name="amt_in_out" id="amt_in_out" autocomplete="off"/>
	                    <span class="form-control-feedback" aria-hidden="true">원</span>
	                </div>
	                <div class="form-group">
	                    <label for="contents">결제처</label>
	                    <input type="text" class="form-control" name="contents" id="contents" autocomplete="off"/>
	                </div>
	                <div class="form-group">
	                    <label for="category">카테고리</label>
	                    <select class="selectpicker" data-header="카테고리" name="category" id="category">
							<c:forEach var="subList" items="${listPersonConsumeClassInfo}">
								<c:forEach var="vo" items="${subList }" varStatus="myIndex">
									<c:if test="${myIndex.index eq 0}">
										<option value="${vo.cd_class}">${vo.nm_class}</option>
									</c:if>
								</c:forEach>
								</div>
							</c:forEach>
	                    </select>
	                </div>
  	                <div class="form-group">
	                    <label for="dt_trd">날짜</label>
	                    <input type="text" class="form-control" name="dt_trd" id="dt_trd" autocomplete="off"/>
	                </div>
  	                <div class="form-group">
	                    <label for="memo">메모</label>
	                    <input type="text" class="form-control" name="memo" id="memo" autocomplete="off"/>
	                </div>
				</div>
<!-- 				<div class="btn-group bootstrap-select open trans_detail"> -->
				<div class="btn-group bootstrap-select trans_detail">
					<div class="dropdown-menu open">
						<div class="popover-title">
							<button type="button" class="close" aria-hidden="true">x</button>
							입출금 내역
						</div>
						<ul class="dropdown-menu inner">
						</ul>
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
