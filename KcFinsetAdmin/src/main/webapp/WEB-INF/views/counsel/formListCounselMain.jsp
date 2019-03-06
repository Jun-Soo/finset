<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>상담 접수 및 현황</title>
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
	
	var data = frmCounselList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listCounselInfo","<c:url value='/counsel/listCounselInfo.crz'/>",data);
}

function getPersonModal(no_person) {
	$("#modal-lg").modal('hide');
	var data = {"no_person":no_person};
	vLoad("modal-content-lg","<c:url value='/counsel/formPerson.crz'/>",data);
	
	setTimeout(function() {
		$("#modal-lg").modal('show');
	}, 1000);
	
}

function goPersonAddedDataForm(no_person) {
	var openWindow = window.open("<c:url value='/counsel/formPersonAddedDataMain.crz'/>?no_person="+no_person, "popPersonAdded"+no_person, "width=950, height="+(screen.availHeight-130)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus();
}

function goCounselForm(no_person,seq) {
	var openWindow = window.open("<c:url value='/counsel/formCounselProc.crz'/>?no_person="+no_person+"&counsel_seq="+seq, "popPerson"+no_person, "width=1250, height="+(screen.availHeight-150)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus();
}

//엑셀 다운로드
function getExcel(){
	if(frmCounselList.listLength.value == 0 ){
		alert("검색 후 진행하여 주세요.");
		return false;
	}
	
 	frmCounselList.txt_dt_from.value = frmCounselList.txt_dt_from.value.replace( /^\$|-/g, "");
	frmCounselList.txt_dt_to.value = frmCounselList.txt_dt_to.value.replace( /^\$|-/g, "");
	
	frmCounselList.action = "<c:url value='/counsel/listCounselInfo_excel.crz'/>";
	frmCounselList.target = "_proc";
	frmCounselList.method = "POST";
	frmCounselList.submit();
}

//상담 처리중으로 변경
function saveCounselStatus(no_person,seq){
// 	frmCounselInfo.no_person.value = no_person;
// 	frmCounselInfo.counsel_seq.value = seq;
	
//     var data = frmCounselInfo.ajaxSubmit();

    var data = {"no_person":no_person, "counsel_seq":seq};
    
	$.ajax({
		url : "<c:url value='/counsel/saveCounselStatus.json'/>",
		data : data,
		async : true,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function(result) {
			goSearch();
		},
		error : function(e) {
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

<!-- //Layout-North -->
<div class="ui-layout-center">
	<div class="ui-layout-content">

		<!-- Title -->
		<div class="h-title">
			<h1 class="pull-left"> 상담 접수 및 현황</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 신용상담</a></li>
				<li class="active"> 상담 접수 및 현황</li>
			</ol>
		</div>
		<!--// Title -->

		<form name="frmCounselList" id="frmCounselList" class="navbar-form">
		<input type="hidden" name="page" id="page" />
		<input type="hidden" name="return_url" value="http://fincook-lca.ccse.co.kr/credit/niceCentCert.crz">
		<input type="hidden" name="safekey" value="" maxlength="13">
		<input type="hidden" name="name" value="">
		<input type="hidden" name="cust_key" value="">
		
		
		<!-- Search -->
		<div class="srch">
			<div class="form-group" id="sandbox-container">
				
				<!-- 날짜검색 -->
				<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
					${ufn:makeOptions("cd_counsel_date","일자", "") }
	   			</select>
				<div class="input-daterange input-group date-w" id="datepicker">
	   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
				    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind', 'DT_APPLY');" validate="date; label:시작일자;" maxlength="8"/>
				    <span class="input-group-addon">~</span>
				    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind', 'DT_APPLY');" validate="date; label:종료일자;" maxlength="8"/>
	   			</div>
				
					${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
				
				<!-- 상세검색 -->
				<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
					${ufn:makeOptions("cd_person_detail","상세검색", '')}
				</select>
				<input type="text" id="txt_detail" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','nm_person')" validate="label:이름;keydown-enter:goSearch;" />
				
				<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
				<!-- 엑셀 다운로드 버튼(권한필요) -->
				<c:if test="${ufn:isApprAuth('402003',sessionScope.STR_APPROVAL)}">
					<button type="button" class="btn btn-primary btn-xs" onclick="getExcel();"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> Excel다운</button>
				</c:if>
				<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmCounselList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				
			</div>
		</div>
		<!--// Search -->

			<div id="listCounselInfo">
				<%@ include file="/WEB-INF/views/counsel/listCounselInfo.jsp"%>
			</div>
		</form>
	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<%@ include file="/WEB-INF/views/comm/modal.jsp"%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>