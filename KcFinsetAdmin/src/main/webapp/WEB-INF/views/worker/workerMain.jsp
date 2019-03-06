<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>사용자및권한그룹관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				200 //좌측 width
		,	east__size:				640 //우측 width
		
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
	
	// SelectPicker
	$('.selectpicker').selectpicker();
	
});

//초기화
function resetForm(frm) {
	frm.reset();
	
	$('.selectpicker').selectpicker('refresh');//selectpicker 초기화
}

function listWorker() {
	$("#listWorker").load(
			"<c:url value='/worker/listWorker.crz'/>",
			function(){
				window.setupValidateForms(this.getElementsByTagName("form"));
			}
		);
}

function setWorkerInfo(id) {
	$("#formWorker").load(
			"<c:url value='/worker/getWorkerForm.crz'/>",
			{
				id_emp:id,
			},
			function(){
				window.setupValidateForms(this.getElementsByTagName("form"));
			}
		);
	
	$("#workerAuth").load(
			"<c:url value='/worker/getWorkerAuth.crz'/>",
			{
				id_emp:id,
			},
			function(){
				window.setupValidateForms(this.getElementsByTagName("form"));
			}
		);
	
	$("#formWorker").show();
}


function createWorker() {
	
	var frm = document.frmWorker;
	if(!frm.validateForm()) return false;
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/worker/createWorker.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			setWorkerInfo('');
			listWorker();
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

function modifyWorker() {
	
	var frm = document.frmWorker;
	if(!frm.validateForm()) return false;
	
	//전화번호 체크
	var extEmpDirectTmp1 = $("#ext_emp_direct_tmp1").val();
	var extEmpDirectTmp2 = $("#ext_emp_direct_tmp2").val();
	var extEmpDirectTmp3 = $("#ext_emp_direct_tmp3").val();
	var extEmpDirectTmp = extEmpDirectTmp1 + "-" + extEmpDirectTmp2 + "-" + extEmpDirectTmp3;
	var regTel = /^\d{2,3}-\d{3,4}-\d{4}$/;
	
	if(extEmpDirectTmp1 != "" || extEmpDirectTmp2 != "" || extEmpDirectTmp3 != ""){
		if(!(regTel.test(extEmpDirectTmp))){
			alert("전화번호 형식이 올바르지 않습니다.");
			return false;
		}
	}
	
	//휴대폰번호 체크
	var hpTmp1 = $("#hp_tmp1").val();
	var hpTmp2 = $("#hp_tmp2").val();
	var hpTmp3 = $("#hp_tmp3").val();
	var hpTmp = hpTmp1 + "-" + hpTmp2 + "-" + hpTmp3;
	var regHp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	
	if(hpTmp1 != "" || hpTmp2 != "" || hpTmp3 != ""){
		if(!(regHp.test(hpTmp))){
			alert("휴대폰번호 형식이 올바르지 않습니다.");
			return false;
		}
	}
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({ 
		url : "<c:url value='/worker/modifyWorker.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			setWorkerInfo('');
			listWorker();
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

/**
 * fnName : deleteWorker
 * DESC : 사용자 삭제 
 * @param : id_emp
 */
function deleteWorker(id_emp) {
	
	var currUserId = $('#userId').val();
	//alert(currUserId);

	// 현재 사용자의 ID와 비교
	if( id_emp == currUserId ){
		if( confirm("현재 로그인한 사용자입니다. 삭제 시 로그아웃 됩니다. 삭제하시겠습니까?") == false ){
			return ;
		}
	}
	else if( id_emp != currUserId ){
		if ( confirm("삭제하시겠습니까?") == false ){    //확인
			return ;
		}
	}
	
	var data = {"id_emp":id_emp}; 
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/worker/deleteWorker.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			
			//로그아웃 여부 확인
			if(result.logout == 'N') {
				alert(returnData.message);
				setWorkerInfo('');
				listWorker();
			} else{
				frmWorkerProgramAuth.action = "<c:url value='/j_spring_security_logout'/>";
				frmWorkerProgramAuth.submit();
			}
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
function movePm(value) {

	if ( IsNull(frmWorkerProgramAuth.id_emp) ) {
		alert("사용자를 먼저 선택하여 주세요.");
		return;
	}
	
	var action = "";
	
	if( value == 'ADD' ){
		frmWorkerProgramAuth.id_program_list.value = frmWorkerProgramAuth.id_program_add.value;

		action = "<c:url value='/worker/createWorkerProgramAuth.json'/>";
	}
	if( value == 'SUB' ){
		frmWorkerProgramAuth.id_program_list.value = frmWorkerProgramAuth.id_program_sub.value;

		action = "<c:url value='/worker/deleteWorkerProgramAuth.json'/>";
	}

	if ( IsNull(frmWorkerProgramAuth.id_program_list) ) {
		alert("선택된 항목이 없습니다.");
		return;
	}
	
	var data = frmWorkerProgramAuth.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : action,
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			if(parseInt(returnData.cd_result,10) > 0)
				alert(returnData.message);
			
			setWorkerInfo(frmWorkerProgramAuth.id_emp.value);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

function moveAm(value) {

	if ( IsNull(frmWorkerApprovalAuth.id_emp) ) {
		alert("사용자를 먼저 선택하여 주세요.");
		return;
	}
	
	var action = "";
	
	if( value == 'ADD' ){
		frmWorkerApprovalAuth.id_appr_list.value = frmWorkerApprovalAuth.id_appr_add.value;

		action = "<c:url value='/worker/createWorkerApprovalAuth.json'/>";
	}
	if( value == 'SUB' ){
		frmWorkerApprovalAuth.id_appr_list.value = frmWorkerApprovalAuth.id_appr_sub.value;

		action = "<c:url value='/worker/deleteWorkerApprovalAuth.json'/>";
	}

	if ( IsNull(frmWorkerApprovalAuth.id_appr_list) ) {
		alert("선택된 항목이 없습니다.");
		return;
	}
	
	var data = frmWorkerApprovalAuth.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : action,
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			if(parseInt(returnData.cd_result,10) > 0)
				alert(returnData.message);
			
			setWorkerInfo(frmWorkerApprovalAuth.id_emp.value);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

function clearCacheWorker() {
	
	$.ajax({
		url : "<c:url value='/worker/clearCacheWorker.json'/>",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			alert("사용자정보 및 권한이 적용되었습니다.");
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

function setTemplate(id) {
	$("#formWorker").hide();
	
	$("#workerAuth").load(
			"<c:url value='/worker/getWorkerTemplate.crz'/>",
			{
				code_value:id,
			},
			function(){
				window.setupValidateForms(this.getElementsByTagName("form"));
			}
		);
}

function movePmTemplate(value) {

	if ( IsNull(frmProgramTemplate.cd_template_group) ) {
		alert("권한그룹을 먼저 선택하여 주세요.");
		return;
	}
	
	var action = "";
	
	if( value == 'ADD' ){
		frmProgramTemplate.id_program_list.value = frmProgramTemplate.id_program_add.value;

		action = "<c:url value='/worker/createProgramTemplate.json'/>";
	}
	if( value == 'SUB' ){
		frmProgramTemplate.id_program_list.value = frmProgramTemplate.id_program_sub.value;

		action = "<c:url value='/worker/deleteProgramTemplate.json'/>";
	}

	if ( IsNull(frmProgramTemplate.id_program_list) ) {
		alert("선택된 항목이 없습니다.");
		return;
	}
	
	var data = frmProgramTemplate.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : action,
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			if(parseInt(returnData.cd_result,10) > 0)
				alert(returnData.message);
			
			setTemplate(frmProgramTemplate.cd_template_group.value);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

function moveAmTemplate(value) {

	if ( IsNull(frmApprovalTemplate.cd_template_group) ) {
		alert("권한그룹을 먼저 선택하여 주세요.");
		return;
	}
	
	var action = "";
	
	if( value == 'ADD' ){
		frmApprovalTemplate.id_appr_list.value = frmApprovalTemplate.id_appr_add.value;

		action = "<c:url value='/worker/createApprovalTemplate.json'/>";
	}
	if( value == 'SUB' ){
		frmApprovalTemplate.id_appr_list.value = frmApprovalTemplate.id_appr_sub.value;

		action = "<c:url value='/worker/deleteApprovalTemplate.json'/>";
	}

	if ( IsNull(frmApprovalTemplate.id_appr_list) ) {
		alert("선택된 항목이 없습니다.");
		return;
	}
	
	var data = frmApprovalTemplate.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : action,
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			if(parseInt(returnData.cd_result,10) > 0)
				alert(returnData.message);
			
			setTemplate(frmProgramTemplate.cd_template_group.value);
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

<!-- Layout-East -->
<div class="ui-layout-east">
	<div class="ui-layout-content">
	<!-- Content -->
		<div class="panel-heading">
			<span class="pull-right">
				<button type="button" class="btn btn-primary btn-xs" onclick="setWorkerInfo('');"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 신규 사용자등록</button>
				<button type="button" class="btn btn-warning btn-xs" onclick="clearCacheWorker();"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span> 적용</button>
			</span>
		</div>
		
		<input type="hidden" id="userId" name="userId" value="${userId}"/>
		<div id="formWorker">
			<%@ include file="/WEB-INF/views/worker/formWorker.jsp"%>
		</div>
	
		<div id="workerAuth">
			<%@ include file="/WEB-INF/views/worker/workerAuth.jsp"%>
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
			<h1 class="pull-left">사용자및권한그룹관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">시스템관리</a></li>
				<li class="active">사용자및권한그룹관리</li>
			</ol>
		</div>
		<!--// title -->
		
		<!-- Alert -->
		<div class="alert alert-success alert-dismissible exclamation-list" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span></button>
			<ul>
				<li>사용자 관리에서 사용자 목록을 확인하고 사용자 등록 및 변경을 할 수 있습니다.</li>
				<li><strong>사용자 이름</strong>을 누르시면 사용자 정보와 권한을 확인 하실 수 있습니다.</li>
				<li>등록 및 변경을 완료 하신 후 <strong>적용</strong>을 누르시면 변경된 정보가 시스템에 반영됩니다.</li>
			</ul>
		</div>
		<!--//Alert -->
		<div id="listWorker" class="d-table">
			<%@ include file="/WEB-INF/views/worker/listWorker.jsp"%>
		</div>
		
		<div id="listTemplate" class="d-table">
			<%@ include file="/WEB-INF/views/worker/listWorkerTemplate.jsp"%>
		</div>
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>