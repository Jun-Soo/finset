<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>금융사전문별 항목 관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				"100%" //좌측 width
		
		,	north__spacing_open:	0
		,	north__resizable:		false
		,	south__spacing_open:	0
		,	south__resizable:		false
		
		,	spacing_open:			5
		,	spacing_closed:			5
		,   togglerLength_open:     50
		,   togglerLength_closed:   "100%"
		
		,  initClosed:       		true
		,  north__initClosed: 		false
		,  south__initClosed: 		false
		,  west__initClosed: 		false
	});

	// toggle panel
	$(".toggle-panel").each(function(i){
		$(this).find(".h-sec > a").click(function(){
			$(this).toggleClass("closed");
			$(this).closest(".toggle-panel").find(".toggle-cont").toggle("blind", 200);
		});
	});
	
});

//초기화
function resetForm(frm) {
	frm.reset();
	$('.selectpicker').selectpicker('refresh');//selectpicker 초기화
}

// 금융사전문별 항목그룹 검색
function goSearchCG() {
	if(!frmSchCG.validateForm()) return;
	var data = frmSchCG.ajaxSubmit();
	if(data == null) return false;
	vLoad("listKbmarketpriceGroup","<c:url value='/kbmarketprice/listKbmarketprice.crz'/>",data);
}
function loadKbmarketpriceGroup(seq_kbmarketprice) {
	var data = {"seq_kbmarketprice":seq_kbmarketprice};
	vLoad("formKbmarketpriceGroup","<c:url value='/kbmarketprice/getKbmarketprice.crz'/>",data,false);
	window.setupValidateForm( frmKbmarketpriceGroup );
}
// 엔터검색
function isEnter(keyKbmarketprice, type) {
	if( keyKbmarketprice == 13 ) {
		goSearchCG();
		frmSchCG.txt_detail.blur();
	}
}
function procKbMarketPrice() {
	frm = document.frmKbmarketprice;
	if(!frm.validateForm()) return false;
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/kbrealestate/scrapKbMarketPrice.crz'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			//	vLoad("listKbMarketPrice","<c:url value='/kbMarketPrice/listKbMarketPrice.crz'/>",data);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
</script>
</head>
<body>
<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!--// Layout-North -->

<!-- Layout-West -->
<div class="ui-layout-west">
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
<!-- 			<h1 class="pull-left">금융사 전문관리</h1> -->
			<ol class="breadcrumb pull-left">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">환경관리</a></li>
				<li class="active">금융사 전문관리</li>
			</ol>
		</div>
		<!--// title -->
	<!-- Content -->
		<div class="srch align-l">
			<form name="frmSchCG" onsubmit="return false;">
			<select class="selectpicker" name="cd_fc">
				${ufn:makeFincorpOptions('전체', '', 'type', '')}
			</select>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchCG();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			</form>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 금융사전문별 항목그룹</a>
				</h3>
			</div>
			<div id="formKbMarketPrice" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/kbmarketprice/formKbmarketprice.jsp"%>
			</div>
		</div>
		
		<div id="listKbmarketprice" class="d-table">
			<%@ include file="/WEB-INF/views/kbmarketprice/listKbmarketprice.jsp"%>
		</div>


	<!-- //Content -->
	</div>
</div>
<!--// Layout-West -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
		
		
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>