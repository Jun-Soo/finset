<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>COOCON 금융사스크래핑관리</title>
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
	var data = $("#page").val(page);
	loadCooconAPIList();
}

function loadCooconAPIList(){
	
	var data = frmCooconAPIList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listCooconAPIinfo","<c:url value='/coocon/cooconAPIList.crz'/>",data);
	
}

function reciveAPI(){
	var data = null;
// 	vLoad("","/coocon/cooconAPI.crz",data);
	$.ajax({
		url : "<c:url value='/coocon/cooconAPI.crz'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			console.log(result);
// 			var returnMsg = result.message;
// 			alert(returnMsg);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}


function SetCooconAPIinfo(gubun, org_type, cd_org, cd_fc, yn_use){
	
	$("#gubun").val("0"+gubun);
	$("#org_type").val(org_type);
	$('#org_type').selectpicker('refresh');
	$("#cd_org").val(cd_org);
	$("#cd_fc").val(cd_fc);
	$("#yn_use").val(yn_use);
	$('#gubun').selectpicker('refresh');
	$('#cd_fc').selectpicker('refresh');
	$('#yn_use').selectpicker('refresh');
	
}

function initForm() {
	$("#gubun").val("");
	$('#gubun').selectpicker('refresh');
	$("#org_type").val("");
	$('#org_type').selectpicker('refresh');
	$("#cd_org").val("");
	$("#cd_fc").val("");
	$("#yn_use").val("");
	$('#gubun').selectpicker('refresh');
	$('#cd_fc').selectpicker('refresh');
	$('#yn_use').selectpicker('refresh');
	
}

</script>
</head>
<body>
<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!-- Layout-East -->
<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">COOCON 금융사스크래핑관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">스크래핑/API관리</a></li>
				<li class="active">COOCON 금융사스크래핑관리</li>
			</ol>
		</div>
		<!--// title -->
		
		<form name="frmCooconAPIList" id="frmCooconAPIList" class="navbar-form">
			<input type="hidden" name="page" id="page" />
			<!-- Search -->
			<div class="srch">
				<div class="form-group" id="sandbox-container">
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
				</div>
				<button type="button" class="btn btn-primary btn-xs" onclick="reciveAPI();">
					<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> API
				</button>
			</div>
			<!--// Search -->
			<!--//Alert -->
			
			<div id="formcooconGoodsDetail">
				<%@ include file="/WEB-INF/views/coocon/formcooconGoodsDetail.jsp"%>
			</div>
			<div id="listCooconAPIinfo">
				<%@ include file="/WEB-INF/views/coocon/listcooconGoods.jsp"%>
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