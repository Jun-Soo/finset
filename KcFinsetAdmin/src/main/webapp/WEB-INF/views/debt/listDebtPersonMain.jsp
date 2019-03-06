<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>KCB 스크래핑결과 부채조회</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('body').layout({
			minSize:				40
		,	west__size:				200 //좌측 width
		,	east__size:				200 //우측 width
		
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
	});
	
	// SelectPicker
	$('.selectpicker').selectpicker();
	
	// 확장검색조건 보이기
	$("#btn-expand").click(function(){
		$(this).toggleClass("open");
		$("#divExpandSearch").toggle("blind", 200);
	});
	
});

//리스트 조회
function goSearch() {
	jumpPage(1);
}
// 페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	var no_person = $("#txt_no_person").val();
	$("#no_person").val(no_person);
	//var data = {"no_person":no_person};
	
	var data = frmDebtList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listDebtPersonInfo","<c:url value='/debt/listDebtPersonInfo.crz'/>",data);
}

//엑셀 다운로드
function getExcel(){
	if(frmDebtList.listLength.value == 0 ){
		alert("검색 후 진행하여 주세요.");
		return false;
	}
	
	frmDebtList.action = "<c:url value='/person/listDebtPersonInfo_excel.crz'/>";
	frmDebtList.target = "_proc";
	frmDebtList.method = "POST";
	frmDebtList.submit();
}

function getDebtPersonRepay(v_no_person,v_no_manage_info){

	$("#no_person").val(v_no_person);
	$("#no_manage_info").val(v_no_manage_info);
	var data = frmDebtList.ajaxSubmit();
	if(data == null) return false;
	vLoad("listDebtPersonRepay","<c:url value='/debt/listDebtPersonRepay.crz'/>",data);
}
</script>
</head>
<body>

<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!--// Layout-North -->

<!-- //Layout-North -->
<div class="ui-layout-center">
	<div class="ui-layout-content">

		<!-- Title -->
		<div class="h-title">
			<h1 class="pull-left"> KCB 스크래핑결과 부채조회</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 스크래핑/API관리</a></li>
				<li class="active"> KCB 스크래핑결과 부채조회</li>
			</ol> 
		</div>
		<!--// Title -->

		<form name="frmDebtList" id="frmDebtList" class="navbar-form">
		<input type="hidden" name="page" id="page" />
		<input type="hidden" name="no_person" id="no_person" />
		<input type="hidden" name="no_manage_info" id="no_manage_info" />
		
		
		<!-- Search -->
		<div class="srch">
			<div class="form-group" id="sandbox-container">
				
				<label>고객번호</label>
				<input type="text" id="txt_no_person" name="txt_no_person" class="form-control" validate="keydown-enter:goSearch;" />
				
				<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
				<!-- 엑셀 다운로드 버튼(권한필요) -->
			<%-- 	<c:if test="${ufn:isApprAuth('402003',sessionScope.STR_APPROVAL)}">
					<button type="button" class="btn btn-primary btn-xs" onclick="getExcel();"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> Excel다운</button>
				</c:if> --%>
				<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmDebtList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				
			</div>
		</div>
		<!--// Search -->

			<div id="listDebtPersonInfo" style="height: 250px; overflow: scroll; margin-bottom: 10px;">
				<%@ include file="/WEB-INF/views/debt/listDebtPersonInfo.jsp"%>
			</div>
			<div id="listDebtPersonRepay">
				<%@ include file="/WEB-INF/views/debt/listDebtPersonRepay.jsp"%>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
</body>
</html>