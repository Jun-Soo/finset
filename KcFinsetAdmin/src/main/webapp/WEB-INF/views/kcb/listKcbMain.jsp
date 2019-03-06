<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>KCB 전문조회</title>
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
function goSearch() {
	frm = document.frmKcbList;
	if(!frm.validateForm()) return false;
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/kcb/procKcbCb.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			if(returnData){
				
				console.log(JSON.stringify(returnData.returnObj));
				alert(returnData.message);
				alert(returnData.cd_result);
				alert(JSON.stringify(returnData.returnObj));
			}else{
				vLoad("listKcb","<c:url value='/kcb/procKcbCb.json'/>",data);
			}
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
function procKcb() {
	frm = document.frmKcb;
	if(!frm.validateForm()) return false;
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/kcb/procKcbCb600.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			console.log(JSON.stringify(returnData.returnObj));
			alert(returnData.message);
			alert(returnData.cd_result);
			alert(JSON.stringify(returnData.returnObj));
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

<!-- //Layout-North -->
<div class="ui-layout-center">
	<div class="ui-layout-content">

		<!-- Title -->
		<div class="h-title">
			<h1 class="pull-left"> KCB 전문조회</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 스크래핑/API관리</a></li>
				<li class="active"> KCB 전문조회</li>
			</ol>
		</div>
		<!--// Title -->
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 금융사전문별 항목그룹</a>
				</h3>
			</div>
			<div id="formKcb" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/kcb/formKcb.jsp"%>
			</div>
		</div>
		<form name="frmKcbList" id="frmKcbList" class="navbar-form">
		<input type="hidden" name="page" id="page" />
		<!-- Search -->
		<div class="srch">
			<div class="form-group" id="sandbox-container">
				<input type="text" id="nmIf" name="nmIf" class="form-control" value="300" validate="label:nmIf  ;keydown-enter:goSearch;" />
				<input type="text" id="noPerson" name="noPerson" class="form-control" value="P000000026" validate="label:noPerson  ;keydown-enter:goSearch;" />13자리(뒤에공백으로)채워야함
				<input type="text" id="nmCust" name="nmCust" class="form-control" value="홍길동" validate="label:nmCust  ;keydown-enter:goSearch;" />
				<input type="text" id="cd_per_corp" name="cd_per_corp" class="form-control" value="4" validate="label:cd_per_corp  ;keydown-enter:goSearch;" />
				<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
				<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmKcbList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
			</div>
		</div>

		<div id="listKcb" class="d-table">
			<%@ include file="/WEB-INF/views/kcb/listKcb.jsp"%>
		</div>
		</form>
	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<%@ include file="/WEB-INF/views/comm/modal.jsp"%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>