<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>접수 리스트</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>

<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>

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
	
});

//리스트 조회
function goSearch() {
	jumpPage(1);
}

// 페이지 이동
function jumpPage(page) {
	if(IsNull(frmStatusList.txt_detail)) {
		alert("고객번호를 입력해 주세요");
		return;
	}
	$("#page").val(page);
	
	var data = frmStatusList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listStatus","<c:url value='/status/listStatusPg.crz'/>",data);
}

function goPrepareForm(no_prepare, no_apply) {
	var openWindow = window.open("<c:url value='/prepare/formPrepareMain.crz'/>?no_prepare="+no_prepare+"&no_apply="+no_apply, "open"+no_prepare, "width=1240, height="+(screen.availHeight-80)+", scrollbars=yes, resizable=yes", "");
	
	openWindow.focus();
	
}

//엑셀 다운로드
function getExcel(){
	
	if(frmStatusList.listLength.value == 0 ){
		alert("검색 후 진행하여 주세요.");
		return false;
	}
	
	frmStatusList.txt_dt_from.value = frmStatusList.txt_dt_from.value.replace( /^\$|-/g, "");
	frmStatusList.txt_dt_to.value = frmStatusList.txt_dt_to.value.replace( /^\$|-/g, "");
	
	frmStatusList.action = "<c:url value='/status/listStatus_excel.crz'/>";
	frmStatusList.target = "_proc";
	frmStatusList.method = "POST";
	frmStatusList.submit();
}

function goCustRel(txt_detail, frm_nm, no_person, no_prepare, yn_grt) {
	var data = {"txt_detail":txt_detail,"frm_nm":frm_nm,"no_person":no_person,"no_prepare":no_prepare};
	$("#modal-lg").modal("show");
	vLoad("modal-content-lg","<c:url value='/deny/listCustRelMain.crz'/>",data);
}
function loadPrepareForm() {
	window.setupValidateForm( frmStatusList );
	
	var data = frmStatusList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("formPrepare","<c:url value='/prepare/formPrepare.crz'/>",data);
	
}

function getPerson(no_person) {
	
	var data = {"no_person":no_person, "yn_modal": "Y"};
	vLoad("modal-content-lg","<c:url value='/person/formPerson.crz'/>",data,false);
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
			<h1 class="pull-left"> 대출신청 상황조회 내역 리스트</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 대출신청 관리</a></li>
				<li class="active"> 대출신청 상황조회 내역 리스트</li>
			</ol>
		</div>
		<!--// Title -->
		
		<form name="frmStatusList" id="frmStatusList" class="navbar-form">
		<input type="hidden" name="page" id="page" />
		<!-- Search -->
		<div class="srch">
				<div class="form-group" id="sandbox-container">
					
					<!-- 날짜검색 -->
					<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
						${ufn:makeOptions("cd_apply_date","일자", "")}
		   			</select>
					<div class="input-daterange input-group date-w" id="datepicker">
		   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
					    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind', 'AP.dt_frt');" validate="date; label:시작일자" maxlength="8"/>
					    <span class="input-group-addon">~</span>
					    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind', 'AP.dt_frt');" validate="date; label:종료일자" maxlength="8"/>
		   			</div>
					
					${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
					
					<!-- 상품명 -->
					<select name="cd_goods" class="selectpicker" id="cd_goods" data-style="btn-primary">
						${ufn:makeGoodsOptions("상품명", "", "NM")}
					</select>
					<!-- 금융사 -->
					<select name="cd_fc" class="selectpicker" id="cd_fc" data-style="btn-primary">
						${ufn:makeOptions("cd_fc","금융사","")}
					</select>
					<!-- 담당자 -->
					<select name="id_prepare_srch" class="selectpicker" id="id_prepare_srch" data-style="btn-primary">
						${ufn:makeIdOptions("502001", "담당자", "")}
					</select>
					
					<input type="hidden" id="sel_detail" name="sel_detail" value="AP.no_person" class="form-control"/>
					고객번호 <input type="text" id="txt_detail" name="txt_detail" value="${personVO.no_person}" class="form-control" onclick="defaultSetting('sel_detail','PE.nm_person')" validate="label:이름;keydown-enter:goSearch;" readonly="readonly"/>
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmStatusList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
					<button type="button" class="btn btn-default btn-xs" onclick="goCustRel('txt_detail', 'frmStatusList','${personVO.no_person}','${prepareVO.no_prepare}','N');">
						<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 고객찾기
					</button> 
					
				</div>
			</div>
		<!--// Search -->
		<div id="listStatus">
			<%@ include file="/WEB-INF/views/status/listStatus.jsp"%>
		</div>
		</form>
	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<%@ include file="/WEB-INF/views/comm/modal.jsp"%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>