<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>담당자별 통계</title>
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

function goSearch() {
	
	var dt_from = frmStats.txt_dt_from.value;
	var dt_to = frmStats.txt_dt_to.value;
	
	if(dt_from == "" || dt_to == "") {
		alert("날짜를 선택해주세요.");
		return false;
	}
	
	var data = frmStats.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listStats","<c:url value='/stats/listStatSms.crz'/>",data);
	
}

//엑셀 다운로드
function getExcel(){
	if(frmStats.listLength.value == 0 ){
		alert("검색 후 진행하여 주세요.");
		return false;
	}
	
	frmStats.action = "<c:url value='/stats/listStatSmsExcel.crz'/>";
	frmStats.target = "_proc";
	frmStats.method = "POST";
	frmStats.fireSubmit();
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
			<h1 class="pull-left"> SMS발송 통계</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 통계</a></li>
				<li class="active"> SMS발송 통계</li>
			</ol>
		</div>
		<!--// Title -->

	<form name="frmStats" id="frmStats" class="navbar-form">
		
		<!-- Search -->
		<div class="srch">
				<div class="form-group" id="sandbox-container">
					
					<!-- 날짜검색 -->
					검색일 :
					<div class="input-daterange input-group date-w" id="datepicker">
		   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
					    <input type="text" class="input-sm form-control" name="txt_dt_from" validate="date;label:시작일자;" maxlength="8"/>
					    <span class="input-group-addon">~</span>
					    <input type="text" class="input-sm form-control" name="txt_dt_to" validate="date;label:종료일자;" maxlength="8"/>
		   			</div>
					
					${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
					
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					<c:if test="${ufn:isApprAuth('402005',sessionScope.STR_APPROVAL)}">
						<button type="button" class="btn btn-success btn-xs" onclick="getExcel();"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> Excel다운</button>
					</c:if>
					
				</div>
			</div>
			<!--// Search -->

			<div id="listStats">
				<%@ include file="/WEB-INF/views/stats/listStatSms.jsp"%>
			</div>
		</form>

	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>