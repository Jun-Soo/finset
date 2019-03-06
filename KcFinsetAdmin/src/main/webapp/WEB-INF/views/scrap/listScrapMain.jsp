<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>COOCON KB부동산시세</title>
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
function loadScrapGroup(seq_scrap) {
	var data = {"seq_scrap":seq_scrap};
	vLoad("formScrapGroup","<c:url value='/scrap/getScrap.crz'/>",data,false);
	window.setupValidateForm( frmScrapGroup );
}
// 엔터검색
function isEnter(keyScrap, type) {
	if( keyScrap == 13 ) {
		goSearchCG();
		frmSchCG.txt_detail.blur();
	}
}
function procScrap() {
	frm = document.frmScrap;
	if(!frm.validateForm()) return false;
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/kbrealestate/scrapKbAddress.crz'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			//	vLoad("listScrap","<c:url value='/scrap/listScrap.crz'/>",data);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
function goCreateKbSiGunGuInfo() {
	if(frmScrap.API_ID.value != "9920"){
		alert("API_ID를 9920:주소검색으로 선택해 주세요");
		return;
	}
	if(!confirm("시 군 구 자료가 삭제되고 쿠콘스크래핑을통해 다시 입력됩니다.\n\n몇분에서 몇십분 소요됩니다.\n\n진행하시겠습니까?")) return;
	if(!frmScrap.validateForm()) return;
	var data = frmScrap.ajaxSubmit();
	if(data == null) return false;
	vLoad("listScrap","<c:url value='/kbrealestate/createKbSiGunGuInfo.crz'/>",data);
}
function goCreateKbDongAptInfo() {
	if(frmScrap.API_ID.value != "9920"){
		alert("API_ID를 9920:주소검색으로 선택해 주세요");
		return;
	}
	if(!confirm("동 아파트목록 자료가 삭제되고 쿠콘스크래핑을통해 다시 입력됩니다.\n\n1시간가량 소요됩니다.\n\n진행하시겠습니까?")) return;
	if(!frmScrap.validateForm()) return;
	var data = frmScrap.ajaxSubmit();
	if(data == null) return false;
	vLoad("listScrap","<c:url value='/kbrealestate/createKbDongAptInfo.crz'/>",data);
}
function goKbMarketPriceList() {
	if(frmScrap.API_ID.value != "9921"){
		alert("API_ID를 9921:시세검색으로 선택해 주세요");
		return;
	}
	if(!frmScrap.validateForm()) return;
	var data = frmScrap.ajaxSubmit();
	if(data == null) return false;
	vLoad("listScrap","<c:url value='/kbrealestate/scrapKbMarketPriceList.crz'/>",data);
}
function goCreateFssCompany() {
	if(!confirm("FSS금융감독원 금융회사 정보가 삭제 후 다시 입력됩니다.\n\n몇분~몇십분가량 소요됩니다.\n\n진행하시겠습니까?")) return;
	var data = frmScrap.ajaxSubmit();
	if(data == null) return false;
	vLoad("listScrap","<c:url value='/fss/createFssCompany.crz'/>",data);
}
function goCreateFssMortgageLoan() {
	if(!confirm("FSS금융감독원 주택담보 금융상품 정보가 삭제 후 다시 입력됩니다.\n\n몇분~몇십분가량 소요됩니다.\n\n진행하시겠습니까?")) return;
	var data = frmScrap.ajaxSubmit();
	if(data == null) return false;
	vLoad("listScrap","<c:url value='/fss/createFssMortgageLoan.crz'/>",data);
}
function goCreateFssRentHouseLoan() {
	if(!confirm("FSS금융감독원 전세자금 금융상품 정보가 삭제 후 다시 입력됩니다.\n\n몇분~몇십분가량 소요됩니다.\n\n진행하시겠습니까?")) return;
	var data = frmScrap.ajaxSubmit();
	if(data == null) return false;
	vLoad("listScrap","<c:url value='/fss/createFssRentHouseLoan.crz'/>",data);
}
function goCreateFssCreditLoan() {
	if(!confirm("FSS금융감독원 개인신용대출 금융상품 정보가 삭제 후 다시 입력됩니다.\n\n몇분~몇십분가량 소요됩니다.\n\n진행하시겠습니까?")) return;
	var data = frmScrap.ajaxSubmit();
	if(data == null) return false;
	vLoad("listScrap","<c:url value='/fss/createFssCreditLoan.crz'/>",data);
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
				<li><a href="#">스크래핑/API관리</a></li>
				<li class="active">COOCON KB부동산시세</li>
			</ol>
		</div>
		<!--// title -->
	<!-- Content -->
		<div class="srch align-l">
			<form name="frmSchCG" onsubmit="return false;">
			<button type="button" class="btn btn-primary btn-xs" onclick="goCreateKbSiGunGuInfo();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> KB 시,군,구 주소입력</button>
			<button type="button" class="btn btn-primary btn-xs" onclick="goCreateKbDongAptInfo();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> KB 동,아파트목록 입력</button>
			<button type="button" class="btn btn-primary btn-xs" onclick="goKbMarketPriceList();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> KB시세조회</button>
			
			</form>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 금융사전문별 항목그룹</a>
				</h3>
			</div>
			<div id="formScrap" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/scrap/formScrap.jsp"%>
			</div>
		</div>
		
		<div id="listScrap" class="d-table">
			<%@ include file="/WEB-INF/views/scrap/listScrap.jsp"%>
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