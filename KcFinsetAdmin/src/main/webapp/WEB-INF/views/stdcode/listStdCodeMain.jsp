<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>표준데이터항목관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				"50%" //좌측 width
		
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

// 표준데이터항목그룹 검색
function goSearchCG() {
	if(!frmSchCG.validateForm()) return;
	
	var data = frmSchCG.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listStdCodeGroup","<c:url value='/stdcode/listStdCode.crz'/>",data);
}

// 상세표준데이터항목 검색
function goSearchCD() {
	if(!frmSchCD.validateForm()) return;
	
	var data = frmSchCD.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listStdCodeDetail","<c:url value='/stdcode/listStdCode.crz'/>",data);
}

// 표준데이터항목그룹 정보 조회
function loadStdCodeGroup(code_group) {
	defaultSetting('sch_cd_sel','code_group');
	//$("#sch_cd_sel").selectpicker("val", "code_group")
	
	var data = {"code_group":code_group,"yn_code_group":"Y"};
	vLoad("formStdCodeGroup","<c:url value='/stdcode/getStdCode.crz'/>",data,false);
	
	frmSchCD.txt_detail.value = code_group;
	frmStdCodeDetail.code_group.value = code_group;
	
	// 초기화
	if(code_group != '') {
		goSearchCD();
	}
	window.setupValidateForm( frmStdCodeGroup );
}

// 상세표준데이터항목 정보 조회
function loadStdCodeDetail(code_group,code_value,default_group) {
	var data = {"code_group":code_group,"code_value":code_value};
	vLoad("formStdCodeDetail","<c:url value='/stdcode/getStdCode.crz'/>",data,false);
	
	// 초기화
	if(code_group == '') {
		frmStdCodeDetail.code_group.value = default_group;
	}
	window.setupValidateForm( frmStdCodeDetail );
}

// 엔터검색
function isEnter(keyStdCode, type) {
	if( keyStdCode == 13 ) {
		if( type == "code_group" ) {
			goSearchCG();
			frmSchCG.txt_detail.blur();
		} else if( type == "code_detail" ) {
			goSearchCD();
			frmSchCD.txt_detail.blur();
		}
	}
}

function procStdCodeInfo(frm, type) {
	
	if(!frm.validateForm()) return false;
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/stdcode/procStdCodeInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			if(parseInt(returnData.cd_result,10) == 0) {
				if('G' == type) {
					defaultSetting('sch_cg_sel','code_group');
					frmSchCG.txt_detail.value = frm.code_group.value;
					goSearchCG();
				} else {
					defaultSetting('sch_cd_sel','code_group');
					frmSchCD.txt_detail.value = frm.code_group.value;
					goSearchCD();
				}
			}
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

function delStdCodeInfo(frm, type) {
	
	if(!frm.validateForm()) return false;
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/stdcode/delStdCodeInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			if(parseInt(returnData.cd_result,10) == 0) {
				if('G' == type) {
					defaultSetting('sch_cg_sel','code_group');
					frmSchCG.txt_detail.value = frm.code_group.value;
					goSearchCG();
				} else {
					defaultSetting('sch_cd_sel','code_group');
					frmSchCD.txt_detail.value = frm.code_group.value;
					goSearchCD();
				}
			}
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

function clearCacheStdCode() {
	
	$.ajax({
		url : "<c:url value='/stdcode/clearCacheStdCode.json'/>",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
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
			<h1 class="pull-left">표준데이터항목관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">전문관리</a></li>
				<li class="active">표준데이터항목관리</li>
			</ol>
		</div>
		<!--// title -->
	<!-- Content -->
		<div class="srch align-l">
			<form name="frmSchCG" onsubmit="return false;">
			<input type="hidden" name="yn_code_group" value="Y"/>
			<select class="selectpicker" name="sel_detail" id="sch_cg_sel" data-style="btn-primary">
				<option value="">상세검색</option> 
				<option value="code_group">표준데이터항목그룹ID</option> 
				<option value="nm_code">표준데이터항목그룹명</option> 
				<option value="etc">설명</option> 
			</select>
			<input type="text" class="form-control" name="txt_detail" validate="label:검색문자열;" onclick="defaultSetting('sch_cg_sel','code_group');" onkeydown="isEnter(event.keyStdCode, 'code_group');" maxlength="100"/>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchCG();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			<button type="button" class="btn btn-primary btn-xs" onclick="resetForm(document.frmSchCG);"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
			<button type="button" class="btn btn-warning btn-xs" onclick="clearCacheStdCode();"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span> 적용</button>
			</form>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 표준데이터항목그룹</a>
				</h3>
			</div>
			<div id="formStdCodeGroup" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/stdcode/formStdCodeGroup.jsp"%>
			</div>
		</div>
		
		<div id="listStdCodeGroup" class="d-table">
			<%@ include file="/WEB-INF/views/stdcode/listStdCodeGroup.jsp"%>
		</div>
	<!-- //Content -->
	</div>
</div>
<!--// Layout-West -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">표준데이터항목관리 상세</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">전문관리</a></li>
				<li class="active">표준데이터항목관리 상세</li>
			</ol>
		</div>
		<!--// title -->
		<div class="srch align-l">
			<form name="frmSchCD">
			<input type="hidden" name="yn_code_group" value="N"/>
			<select class="selectpicker" name="sel_detail" id="sch_cd_sel" data-style="btn-primary">
				<option value="">상세검색</option> 
				<option value="code_group">표준데이터항목그룹ID</option> 
				<option value="code_value">표준데이터항목ID</option> 
				<option value="nm_code">표준데이터항목명</option> 
				<option value="etc">설명</option> 
			</select>
			<input type="text" class="form-control" name="txt_detail" validate="required;label:검색문자열;" onclick="defaultSetting('sch_cd_sel','code_group');" onkeydown="isEnter(event.keyStdCode, 'code_detail');" maxlength="100"/>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchCD();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			<button type="button" class="btn btn-primary btn-xs" onclick="resetForm(document.frmSchCD);"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
			</form>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 표준데이터항목상세</a>
				</h3>
			</div>
			<div id="formStdCodeDetail" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/stdcode/formStdCodeDetail.jsp"%>
			</div>
		</div>
		
		<div id="listStdCodeDetail" class="d-table">
			<%@ include file="/WEB-INF/views/stdcode/listStdCodeDetail.jsp"%>
		</div>
	
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>