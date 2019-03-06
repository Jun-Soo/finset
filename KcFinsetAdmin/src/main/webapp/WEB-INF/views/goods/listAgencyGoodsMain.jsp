<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>노출상품관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				200 //좌측 width
		,	east__size:				670 //우측 width
		
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
	if(!frmList.cd_goods.value && !frmList.id_agency.value) alert("검색조건을 선택해주세요.");
	else jumpPage(1);
}

//페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	
	loadGoodsList();
}

function loadListTab(type) {
	frmList.tab_type.value = type;
	
	loadGoodsList();
}

function clearCacheGoods(){
	$.ajax({
		url : "<c:url value='/goods/clearCacheGoods.json'/>",
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

function loadGoodsList(){
	var data = frmList.ajaxSubmit();
	if(data == null) return false;
	
	if(frmList.tab_type.value == "AGENCY") vLoad("listOpen","<c:url value='/goods/listOpenAgency.crz'/>",data);
	else vLoad("listOpen","<c:url value='/goods/listOpenGoods.crz'/>",data);
}

</script>
</head>
<body>
<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!--// Layout-North -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">노출상품관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">상품관리</a></li>
				<li class="active">노출상품관리</li>
			</ol>
		</div>
		<!--// title -->
		
		<div role="tabpanel">
			<ul class="nav nav-tabs tab-sm" role="tablist">
				<li role="presentation" class="active" onclick="loadListTab('GOODS');"><a href="#cont1"	aria-controls="cont1" role="tab" data-toggle="tab">상품별</a></li>
				<li role="presentation" onclick="loadListTab('AGENCY');"><a href="#cont2" aria-controls="cont1" role="tab" data-toggle="tab">매체사별</a></li>
			</ul>
		</div>
		
		<form name="frmList" id="frmList" class="navbar-form">
			<input type="text" style="display: none;" /> <!-- 엔터시 자동서브밋 방지  -->
			<input type="hidden" name="tab_type" id="tab_type" />
			<input type="hidden" name="yn_open" id="yn_open" />
			
			<!--//Alert -->
			<div id="listOpen">
				<%@ include file="/WEB-INF/views/goods/listOpenGoods.jsp"%>
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