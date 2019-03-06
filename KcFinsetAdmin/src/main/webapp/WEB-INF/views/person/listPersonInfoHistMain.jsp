<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>개인정보 수정이력</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
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
		
		$('.selectpicker').selectpicker();
	});
	
	//리스트 조회
	function goSearch() {
		jumpPage(1);
	}

	// 페이지 이동
	function jumpPage(page) {
		$("#page").val(page);
		
		var data = frmListPersonInfoHist.ajaxSubmit();
		if(data == null) return false;
		
		vLoad("listPersonInfoHist","<c:url value='/person/listPersonInfoHist.crz'/>",data);
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/include/header.jsp"%>
	<div class="ui-layout-center">
		<div class="ui-layout-content">
			<!-- Title -->
			<div class="h-title">
				<h1 class="pull-left"> 개인정보 수정이력</h1>
				<ol class="breadcrumb pull-right">
					<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
					<li> 내역관리</li>
					<li class="active"> 개인정보 수정이력</li>
				</ol>
			</div>
			
			<form name="frmListPersonInfoHist" id="frmListPersonInfoHist" class="navbar-form">
				<input type="hidden" name="page" id="page"/>
				<!-- Search -->
				<div class="srch">
					<div class="form-group" id="sandbox-container">
		
						<!-- 날짜검색 -->
						<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
							${ufn:makeOptions("cd_person_info_date","일자", "")}
			   			</select>
						<div class="input-daterange input-group date-w" id="datepicker">
			   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
						    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind', 'PIH.dt_frt')" validate="date; lable:시작일자;" maxlength="8"/>
						    <span class="input-group-addon">~</span>
						    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind', 'PIH.dt_frt')" validate="date; lable:종료일자;" maxlength="8"/>
			   			</div>
		
						${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
						
						<!-- 상세검색 -->
						<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
							${ufn:makeOptions("cd_person_info_detail","상세검색", "")}
						</select>
						<input type="text" id="txt_detail" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','PIH.no_person')" validate="label:이름;keydown-enter:goSearch;" />
						
						<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
						<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmListPersonInfoHist');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
						
					</div>
				</div>
				<!--// Search -->
				
				<div id="listPersonInfoHist">
					<%@ include file="/WEB-INF/views/person/listPersonInfoHist.jsp"%>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>