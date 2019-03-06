<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>조회 이력</title>
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
function goList() {
	var page = "1";
	if(frmTxFcReceive.page.value != ""){
		page = frmTxFcReceive.page.value;
	}
	jumpPage(page);
}
// 페이지 이동
function jumpPage(page) {
	if(IsNull(frmTxFcReceive.no_person)) {
		alert("고객번호를 입력해 주세요");
		return;
	}
	$("#page").val(page);
	
	var data = frmTxFcReceive.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listTxFcReceive","<c:url value='/txfcreceive/listTxFcReceiveDetailPage.crz'/>",data);
}

function isEnter(keyCode){
	if(keyCode == 13){
		goSearch();
	}
}
function goListTxFcReceiveDetail(no_person, no_prepare){
	var page = frmTxFcReceive.page.value;
	frmTxFcReceive.no_person.value = no_person;
	frmTxFcReceive.no_prepare.value = no_prepare;
	$("#page").val(page);
	var data = frmTxFcReceive.ajaxSubmit();
	if(data == null) return false;
	vLoad("listTxFcReceive","<c:url value='/txfcreceive/listTxFcReceiveDetail.crz'/>",data);
}

function goListTxFcReceiveDetailPage(no_person, no_prepare){
	var page = frmTxFcReceive.page.value;
	frmTxFcReceive.no_person.value = no_person;
	frmTxFcReceive.no_prepare.value = no_prepare;
	$("#page").val(page);
	var data = frmTxFcReceive.ajaxSubmit();
	if(data == null) return false;
	vLoad("listTxFcReceive","<c:url value='/txfcreceive/listTxFcReceiveDetailPage.crz'/>",data);
}
function goCustRel(txt_detail, frm_nm, no_person, no_prepare, yn_grt) {
	var data = {"txt_detail":txt_detail,"frm_nm":frm_nm,"no_person":no_person,"no_prepare":no_prepare};
	$("#modal-lg").modal("show");
	vLoad("modal-content-lg","<c:url value='/deny/listCustRelMain.crz'/>",data);
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
			<h1 class="pull-left"> 조회 이력</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 조회및실행이력</a></li>
				<li class="active"> 조회 이력</li>
			</ol>
		</div>
		<!--// Title -->

		<form name="frmTxFcReceive" id="frmTxFcReceive" class="navbar-form">
		<input type="hidden" name="page" id="page" />
		<input type="hidden" name="no_prepare" id="no_prepare" />
		<input type="hidden" name="yn_grt" value="" />
		<!-- Search -->
		<div class="srch">
			<div class="form-group" id="sandbox-container">
				
				<!-- 날짜검색 -->
				<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
					${ufn:makeOptions("cd_dt_frt","날짜종류", "")}
	   			</select>
				<div class="input-daterange input-group date-w" id="datepicker">
	   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
				    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind', 'DT_FRT');" validate="date; label:시작일자;" maxlength="8"/>
				    <span class="input-group-addon">~</span>
				    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind', 'DT_FRT');" validate="date; label:종료일자;" maxlength="8"/>
	   			</div>
				<!-- 고객검색 -->
				${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
				고객번호 <input type="text" name="no_person" id="no_person" class="form-control" onkeydown="isEnter(event.keyCode)" value="${noPerson }" validate="required;label:이름;keydown-enter:goSearch;" readonly="readonly"/>
				
				<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
				<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmTxFcReceive');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				<button type="button" class="btn btn-default btn-xs" onclick="goCustRel('no_person', 'frmTxFcReceive','${personVO.no_person}','${prepareVO.no_prepare}','N');">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 고객찾기
				</button> 
			</div>
		</div>
		<!--// Search -->
		<div id="listTxFcReceive">
			<%@ include file="/WEB-INF/views/past/listTxFcReceiveDetailPage.jsp"%>
		</div>
		</form>
	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<%@ include file="/WEB-INF/views/comm/modal.jsp"%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>