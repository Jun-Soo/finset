<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>탈퇴 관리</title>
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
		
});

//리스트 조회
function goSearch() {
	jumpPage(1);
}

// 페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	
	var data = frmPersonQuitList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listPersonQuitInfo","<c:url value='/person/listPersonQuitInfo.crz'/>",data);
}

function isEnter(keyCode){
	if(keyCode == 13){
		goSearch();
	}
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
			<h1 class="pull-left"> 탈퇴 관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 고객관리</a></li>
				<li class="active"> 탈퇴 관리</li>
			</ol>
		</div>
		<!--// Title -->

		<form name="frmPersonQuitList" id="frmPersonQuitList" class="navbar-form" onsubmit="return false;">
		<input type="hidden" name="page" id="page" />
		
		<!-- Search -->
		<div class="srch">
			<div class="form-group" id="sandbox-container">
				<!-- 상세검색 -->
			<select class="selectpicker" name="sel_detail" id="sel_detail" data-style="btn-primary">
				${ufn:makeOptions("cd_person_quit_detail","상세검색", '')}
			</select>
				<input type="text" name="txt_detail" onkeydown="isEnter(event.keyCode)" onclick="defaultSetting('sel_detail','PQ.nm_person')"/>
				<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			</div>
		</div>
		<!--// Search -->

			<div id="listPersonQuitInfo">
				<%@ include file="/WEB-INF/views/person/listPersonQuitInfo.jsp"%>
			</div>
		</form>
	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<%@ include file="/WEB-INF/views/comm/modal.jsp"%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>