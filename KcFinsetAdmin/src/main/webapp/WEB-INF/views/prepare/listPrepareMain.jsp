<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>사전접수 리스트</title>
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
	
});

//리스트 조회
function goSearch() {
	jumpPage(1);
}

// 페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	
	var data = frmPrepareList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listPrepare","<c:url value='/prepare/listPrepare.crz'/>",data);
}

function goPrepareForm(no_prepare) {
	
	var openWindow = window.open("<c:url value='/prepare/formPrepareMain.crz'/>?no_prepare="+no_prepare, "open"+no_prepare, "width=1240, height="+(screen.availHeight-80)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus();
	
}

function goModifyPrepareDoc() {
	if ( countCheckBox(frmPrepareList.no_prepare_list) == 0 ) {
		alert("선택된 대상이 없습니다.");
		return false;
	}
	if ( countCheckBox(frmPrepareList.no_prepare_list) != 0 && frmPrepareSub.cd_prepare_doc_box.value == '' ) {
		frmPrepareSub.cd_prepare_doc_box.focus();
		alert("서류함을 선택하세요.");
		return false;
	}
	if ( countCheckBox(frmPrepareList.no_prepare_list) != 0 && frmPrepareSub.cd_prepare_doc_box.value == '60' && frmPrepareSub.cd_reject_cause.value == '' ) {
		frmPrepareSub.cd_reject_cause.focus();
		alert("접수불가 사유를 선택하세요.");
		return false;
	}

	if ( !confirm("선택한 신청서를 [" + frmPrepareSub.cd_prepare_doc_box[frmPrepareSub.cd_prepare_doc_box.selectedIndex].text + "] 서류함으로 이동하시겠습니까?") ) { return false; }

	frmPrepareSub.no_prepare.value = getRadioOption(frmPrepareList.no_prepare_list);
	
	var data = frmPrepareSub.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/prepare/modifyPrepareDoc.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			var searchData = frmPrepareList.ajaxSubmit();
			alert(returnData.message);
			vLoad("listPrepare","<c:url value='/prepare/listPrepare.crz'/>",searchData);
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

function goModifyPrepareId(){
	if ( countCheckBox(frmPrepareList.no_prepare_list) == 0 ) {
		alert("선택된 대상이 없습니다.");
		return false;
	}
	if ( countCheckBox(frmPrepareList.no_prepare_list) != 0 && frmPrepareSub.id_prepare.value == '' ) {
		frmPrepareSub.id_prepare.focus();
		alert("담당자을 선택하세요.");
		return false;
	}

	frmPrepareSub.no_prepare.value = getRadioOption(frmPrepareList.no_prepare_list);
	
	var data = frmPrepareSub.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/prepare/modifyPrepareId.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			var searchData = frmPrepareList.ajaxSubmit();
			alert(returnData.message);
			vLoad("listPrepare","<c:url value='/prepare/listPrepare.crz'/>",searchData);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

function goModifyPrepareBatch(){
	if ( countCheckBox(frmPrepareList.no_prepare_list) == 0 ) {
		alert("선택된 대상이 없습니다.");
		return false;
	}
	if ( countCheckBox(frmPrepareList.no_prepare_list) != 0 && frmPrepareSub.id_prepare.value == '' ) {
		frmPrepareSub.id_prepare.focus();
		alert("담당자을 선택하세요.");
		return false;
	}
	if ( countCheckBox(frmPrepareList.no_prepare_list) != 0 && frmPrepareSub.cd_prepare_doc_box.value == '' ) {
		frmPrepareSub.cd_prepare_doc_box.focus();
		alert("서류함을 선택하세요.");
		return false;
	}
	if ( countCheckBox(frmPrepareList.no_prepare_list) != 0 && frmPrepareSub.cd_prepare_doc_box.value == '60' && frmPrepareSub.cd_reject_cause.value == '' ) {
		frmPrepareSub.cd_reject_cause.focus();
		alert("접수불가 사유를 선택하세요.");
		return false;
	}

	frmPrepareSub.no_prepare.value = getRadioOption(frmPrepareList.no_prepare_list);
	
	var data = frmPrepareSub.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/prepare/modifyPrepareBatch.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			var searchData = frmPrepareList.ajaxSubmit();
			alert(returnData.message);
			vLoad("listPrepare","<c:url value='/prepare/listPrepare.crz'/>",searchData);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

// SMS 전송
function goSendSms() {
	
	if (isNullObj(frmPrepareList.no_prepare_list)) {
 		alert("검색 후 다시 시도해주시기 바랍니다..");
 		return false;
 	}
	
	if ( countCheckBox(frmPrepareList.no_prepare_list) == 0 ) {
		alert("선택된 대상이 없습니다.");
		return false;
	}
	
	var page = frmPrepareList.page.value;
	
	window.open("","formSms","width=500,height=500,resizable=yes,","");

	frmPrepareSub.no_prepare.value = getRadioOption(frmPrepareList.no_prepare_list);
	frmPrepareSub.target = "formSms";
	frmPrepareSub.action = "<c:url value='/sms/popFormSms.crz'/>?page="+page;
	frmPrepareSub.method = "post";
	frmPrepareSub.submit();
}

function showDocCause(cd_prepare_doc){
	if(cd_prepare_doc == "60"){
		$("#div_reject_cause").show();
		$("#div_prepare_class_20").hide();
	}else if(cd_prepare_doc == "20"){
		$("#div_reject_cause").hide();
		$("#div_prepare_class_20").show();
	}else{
		$("#div_reject_cause").hide();
		$("#div_prepare_class_20").hide();
	}
}

//연계검사 팝업	
function popPersonRelation() {
	var openWindow = window.open("<c:url value='/person/listPersonRelMain.crz'/>", "popPersonRelation", "width=900, height=600, scrollbars=yes, resizable=yes", "");
	openWindow.focus();
}

//엑셀 다운로드
function getExcel(){
	
	if(frmPrepareList.listLength.value == 0 ){
		alert("검색 후 진행하여 주세요.");
		return false;
	}
	
	frmPrepareList.txt_dt_from.value = frmPrepareList.txt_dt_from.value.replace( /^\$|-/g, "");
	frmPrepareList.txt_dt_to.value = frmPrepareList.txt_dt_to.value.replace( /^\$|-/g, "");
	
	frmPrepareList.action = "<c:url value='/prepare/listPrepare_excel.crz'/>";
	frmPrepareList.target = "_proc";
	frmPrepareList.method = "POST";
	frmPrepareList.submit();
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
			<h1 class="pull-left"> 사전접수 리스트</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 사전접수 관리</a></li>
				<li class="active"> 사전접수 리스트</li>
			</ol>
		</div>
		<!--// Title -->

	<form name="frmPrepareList" id="frmPrepareList" class="navbar-form">
		<input type="hidden" name="cd_prepare_doc" id="cd_cont_doc" />
		<input type="hidden" name="page" id="page" />
		<!-- 서류함  -->
		<div role="tabpanel">
		<ul class="nav nav-pills" role="tablist">
			<li class="active" role="presentation" onclick="javascript:frmPrepareList.cd_prepare_doc.value=''; frmPrepareList.cd_prepare_class.value=''; goSearch();"><a href="#none" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 전체</a></li>
			<c:forEach var="List" items="${ufn:getCodeList('cd_prepare_doc_box')}">
				<c:choose>
					<c:when test="${List.code_value eq '10' || List.code_value eq '20'}">
						<li role="presentation" onclick="javascript:frmPrepareList.cd_prepare_doc.value='${List.code_value}'; frmPrepareList.cd_prepare_class.value=''; goSearch();"><a href="#none" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span> ${List.nm_code} (<label id="PREPARE_${List.code_value}">${ufn:formatNumber(cntPrepareDoc[List.code_value])}</label>)</a></li>
					</c:when>
					<c:otherwise>
						<li role="presentation" onclick="javascript:frmPrepareList.cd_prepare_doc.value='${List.code_value}'; frmPrepareList.cd_prepare_class.value=''; goSearch();"><a href="#none" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span> ${List.nm_code}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
		</div>
		<!-- //서류함 -->

		
		<!-- Search -->
		<div class="srch">
				<div class="form-group" id="sandbox-container">
					
					<!-- 날짜검색 -->
					<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
						${ufn:makeOptions("cd_prepare_date","일자", "")}
		   			</select>
					<div class="input-daterange input-group date-w" id="datepicker">
		   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
					    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind','PR.dt_frt');" validate="date;label:시작일자;" maxlength="8"/>
					    <span class="input-group-addon">~</span>
					    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind','PR.dt_frt');" validate="date;label:종료일자;" maxlength="8"/>
		   			</div>
		   			
		   			${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
					
					<!-- 광고매체 -->
					<select name="cd_advertisement" class="selectpicker" id="cd_advertisement" data-style="btn-primary">
						${ufn:makeOptions("cd_advertisement","광고매체", "")}
					</select>
					
					<!-- 상품구분 -->
					<select name="cd_goods_type" class="selectpicker" id="cd_goods_type" data-style="btn-primary">
						${ufn:makeOptions("cd_goods_type","상품구분", "")}
					</select>
					
					<!-- 담당자 -->
					<select name="id_prepare_srch" class="selectpicker" id="id_prepare_srch" data-style="btn-primary">
						${ufn:makeIdOptions('502001', '담당자', "")}
					</select>
					
					<!-- 상세검색 -->
					<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
						${ufn:makeOptions("cd_prepare_detail","상세검색", "")}
					</select>
					<input type="text" id="txt_detail" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','PE.nm_person');" validate="label:이름;keydown-enter:goSearch;" />
					
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					
					<!-- 엑셀 다운로드 버튼(권한필요) -->
					<c:if test="${ufn:isApprAuth('402001',sessionScope.STR_APPROVAL)}">
						<button type="button" class="btn btn-primary btn-xs" onclick="getExcel();"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> Excel다운</button>
					</c:if>
					<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmPrepareList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
					
					<button type="button" class="btn btn-success btn-xs" onclick="popPersonRelation();">
						<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 연계검사
					</button>
					
					<button type="button" class="btn btn-danger btn-xs" onclick="goPrepareForm('');"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> 신규신청서</button>
					
					<!-- 확장검색 영역 시작 -->
					<div id="divExpandSearch" style="display: none; margin-top: 5px;">
					
						<!-- 서류함 -->
						<select name="cd_prepare_doc_box" class="selectpicker" id="cd_prepare_doc_box" data-style="btn-primary">
							${ufn:makeOptions("cd_prepare_doc_box", "서류함", "")}
						</select>
						
						<!-- 상담사유 -->
						<select name="cd_prepare_class_srch_20" class="selectpicker" id="cd_prepare_class_srch_20" data-style="btn-primary">
							${ufn:makeOptions("cd_prepare_class", "상담구분", "")}
						</select>
						
						<!-- 접수불가-->
						<select name="cd_reject_cause" class="selectpicker" id="cd_reject_cause" data-style="btn-primary">
							${ufn:makeOptions("cd_reject_cause","접수불가사유", "")}
						</select>
						
						<!-- 휴대폰상태-->
						<select name="cd_ph_status" class="selectpicker" id="cd_ph_status" data-style="btn-primary">
							${ufn:makeOptions("cd_ph_status","휴대폰상태", "")}
						</select>
						
						<!-- 접수상태-->
						<select name="cd_apply_class" class="selectpicker" id="cd_apply_class" data-style="btn-primary">
							${ufn:makeOptions("cd_apply_class","접수상태", "")}
						</select>
					
					</div>
					<!-- 확장검색 보이기/숨기기 -->
				    <button id="btn-expand" type="button"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></button>
					
				</div>
			</div>
			<!--// Search -->

			<div id="listPrepare">
				<c:choose>
					<c:when test="${ufn:getNvlCodeName('_CONF_LIST', 'prepare', '') eq 'loanmade'}">
						<%@ include file="/WEB-INF/views/prepare/listPrepare_loanmade.jsp" %>
					</c:when>
					<c:otherwise>
						<%@ include file="/WEB-INF/views/prepare/listPrepare.jsp"%>
					</c:otherwise>
				</c:choose>
			</div>
		</form>
		
		<!-- 일괄 처리  -->
		<div class="pull-right">
			<form name="frmPrepareSub">
				<input type="hidden" name="no_prepare"/>
				<div class="form-group pull-right" id="sandbox-container">				
					<!-- 서류함 -->
					<span id="div_prepare_doc">
					<select class="selectpicker" name="cd_prepare_doc_box" id="prepare_doc" data-style="btn-primary" onChange="showDocCause(this.value);" >
						${ufn:makeOptions("cd_prepare_doc_modify", "서류함", "")}
					</select>
					</span>				
					<!-- 접수불가 사유 -->				
					<div id="div_reject_cause" style="float:left;display:none;">
						<select class="selectpicker" name="cd_reject_cause" id="reject_cause" data-style="btn-primary">
							${ufn:makeOptions("cd_reject_cause", "접수불가 사유", "")}
						</select>
					</div>			
					<!-- 상담 사유 -->				
					<div id="div_prepare_class_20" style="float:left;display:none;">
						<select class="selectpicker" name="cd_prepare_class" id="prepare_class" data-style="btn-primary">
							${ufn:makeOptions("cd_prepare_class", "상담구분", "")}
						</select>
					</div>									
					<button type="button" class="btn btn-primary btn-xs" onclick="goModifyPrepareDoc();"><span class="glyphicon" aria-hidden="true"></span>상태변경</button>
					
					<c:if test="${ufn:isApprAuth('102002',sessionScope.STR_APPROVAL)}">
						<!--  담당자 -->
						<select name="id_prepare" class="selectpicker" id="id_prepare" data-style="btn-primary">
							${ufn:makeIdOptions('502001', '담당자', '')}
						</select>
						<button type="button" class="btn btn-primary btn-xs" onclick="goModifyPrepareId();">담당자변경</button>
						<button type="button" class="btn btn-success btn-xs" onclick="goModifyPrepareBatch();"><span class="glyphicon glyphicon glyphicon-repeat" aria-hidden="true"></span> 일괄변경</button>	
						
						<button type="button" class="btn btn-default btn-xs" onclick="window.open('<c:url value="/prepare/confIdPrepareDiv.crz"/>','','width=640, height=370, scrollbars=yes','');"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 분배설정</button>
					</c:if>
					
					<!-- SMS -->
					<button type="button" class="btn btn-default btn-xs" onclick="goSendSms();"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> SMS</button>
				</div>
			</form>
		</div>	
		<!-- //일괄 처리  -->

	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>