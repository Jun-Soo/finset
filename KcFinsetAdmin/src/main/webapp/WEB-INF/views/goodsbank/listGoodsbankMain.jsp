<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>COOCON 금융사상품관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>

<script type="text/javascript" src="<c:url value="/script/util_comm.js"/>"></script>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				"50%" //좌측 width
		,	east__size:				"50%" //우측 width
		
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
		,  east__initClosed: 		false
	});

	// toggle panel
	$(".toggle-panel").each(function(i){
		$(this).find(".h-sec > a").click(function(){
			$(this).toggleClass("closed");
			$(this).closest(".toggle-panel").find(".toggle-cont").toggle("blind", 200);
		});
	});
	
	// SelectPicker
	$('.selectpicker').selectpicker();
	
});


//리스트 조회
function goSearch() {
	jumpPage(1);
}
//페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	loadGoodsbankList();
}
function clearCacheGoodsbank(){
	$.ajax({
		url : "<c:url value='/goodsbank/clearCacheGoodsbank.json'/>",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			alert("상품정보 변경이 적용되었습니다.");
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
// 폼 초기화  
function initForm() {
	var data = null;
	vLoad("formGoodsbankTab","<c:url value='/goodsbank/formGoodsbankInfo.crz'/>", data);
}
function initForm2() {
	var data = null;
	vLoad("listGoodsbankChange","<c:url value='/goodsbank/listGoodsbankChange.crz'/>",data);
	$("#pastData").html("");
	$("#currentData").html("");
}
function loadGoodsbankList(){
	initForm();
	var data = frmGoodsbankList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listGoodsbank","<c:url value='/goodsbank/listGoodsbank.crz'/>",data);
	/* vLoad("listGoodsbankChange","<c:url value='/goodsbank/listGoodsbankChange.crz'/>",data); */
	
}
function searchBankData(cd_fc, gubun, status){
	var cd_fc = cd_fc;
	var gubun = gubun;   
	var status = status;
	var page = $("#page").val();
	var data = {"page":page,"cd_fc":cd_fc,"gubun":gubun,"status":status};
	
	initForm2(); //상세내용 항목 초기화
	
	vLoad("listGoodsbankChange","<c:url value='/goodsbank/listGoodsbankChange.crz'/>",data);
}

function BringData(event,column_name,cd_fc,yn_value,cd_coocon_goods,nm_coocon_goods,gubun){
	event.stopImmediatePropagation();
	
	var data = {"column_name":column_name,"cd_fc":cd_fc,"cd_coocon_goods":cd_coocon_goods,"nm_coocon_goods":nm_coocon_goods,"gubun":gubun};
	vLoad("GoodsbankChangeData","<c:url value='/goodsbank/ChangeData.crz'/>",data); 

}

function SetData(cd_coocon_goods, cd_non_goods, nm_coocon_goods, cd_fc){
	var cd_coocon_goods = cd_coocon_goods;
	var cd_non_goods = cd_non_goods;
	var nm_coocon_goods = nm_coocon_goods;
	var cd_fc = cd_fc;
	
	var data = {"cd_coocon_goods":cd_coocon_goods,"cd_non_goods":cd_non_goods,"nm_coocon_goods":nm_coocon_goods,"cd_fc":cd_fc};
	vLoad("GoodsbankInfoSetData","<c:url value='/goodsbank/SetData.crz'/>",data);
}


</script>
</head>
<body>
<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!--// Layout-North -->

<!-- Layout-East -->
<div class="ui-layout-east">
	<div class="ui-layout-content">
	<!-- Content -->
		<div id="formGoodsbankTab">
			<%@ include file="/WEB-INF/views/goodsbank/formGoodsbankDetail.jsp"%>
		</div>
	<!-- //Content -->
	</div>
</div>
<!--// Layout-East -->
<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">COOCON 금융사상품관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">스크래핑/API관리</a></li>
				<li class="active">COOCON 금융사상품관리</li>
			</ol>
		</div>
		<!--// title -->
		
		<!-- Alert -->
		
		<form name="frmGoodsbankList" id="frmGoodsbankList" class="navbar-form">
			<input type="text" style="display: none;" /> <!-- 엔터시 자동서브밋 방지  -->
			<input type="hidden" name="tmp_cd_fc" id="tmp_cd_fc" />
			<input type="hidden" name="tmp_cd_goods" id="tmp_cd_goods" />
			<input type="hidden" name="page" id="page" />
			<input type="hidden" name="tab_type" id="tab_type" />
			<!-- Search -->
			<div class="srch">
				<div class="form-group" id="sandbox-container">
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					<button type="button" class="btn btn-primary btn-xs" onclick="initForm2();"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				</div>
			</div>
			<!--// Search -->
			<!--//Alert -->
			<div id="listGoodsbank">
				<%@ include file="/WEB-INF/views/goodsbank/listGoodsbank.jsp"%>
			</div>
			<div id="listGoodsbankChange">
				<%@ include file="/WEB-INF/views/goodsbank/listGoodsbankChange.jsp"%>
			</div>
			<div id="GoodsbankChangeData">
				<%@ include file="/WEB-INF/views/goodsbank/GoodsbankChangeData.jsp"%>
			</div>
		</form>
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
</body>
</html>