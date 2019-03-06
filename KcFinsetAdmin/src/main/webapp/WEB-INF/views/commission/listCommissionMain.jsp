<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>  
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>수수료관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				"80%" //좌측 width
		,	east__size:				"20%" //우측 width
		
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

	// toggle panel
	$(".toggle-panel").each(function(i){
		$(this).find(".h-sec > a").click(function(){
			$(this).toggleClass("closed");
			$(this).closest(".toggle-panel").find(".toggle-cont").toggle("blind", 200);
		});
	});
	
	// SelectPicker
	$('.selectpicker').selectpicker();
	
});


//리스트 조회
function goSearch() {
	jumpPage(1);
}
//페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	loadCommissionList();
}
// 폼 초기화
function initForm() {
// 	var data = {"no_apply":no_apply};
	vLoad("formCommissionDetail","<c:url value='/commission/formCommissionInfo.crz'/>",{});
}
function loadCommissionList(){
	var data = frmCommissionList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listCommission","<c:url value='/commission/listCommission.crz'/>",data);
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
		<div id="formCommissionDetail">
			<%@ include file="/WEB-INF/views/commission/formCommissionDetail.jsp"%>
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
<!-- 			<h1 class="pull-left">수수료관리</h1> -->
			<ol class="breadcrumb pull-left">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">금융사관리</a></li>
				<li class="active">수수료관리</li>
			</ol>
		</div>  
		<!--// title -->
		
		<form name="frmCommissionList" id="frmCommissionList" class="navbar-form">
			<input type="text" style="display: none;" /> <!-- 엔터시 자동서브밋 방지  -->
			<input type="hidden" name="tmp_no_apply" id="tmp_no_apply" />
			<input type="hidden" name="tmp_cd_goods" id="tmp_cd_goods" />
			<input type="hidden" name="page" id="page" />
			<input type="hidden" name="tab_type" id="tab_type" />
			<!-- Search -->
			<div class="srch">
				<div class="form-group" id="sandbox-container">
				<!-- 날짜검색 -->
					<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
						${ufn:makeOptions("cd_commission_date","전체", "")}
		   			</select>
					<div class="input-daterange input-group date-w" id="datepicker">
		   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
					    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind', 'A.YMD_APPROVAL');" validate="date; label:시작일자" maxlength="8"/>
					    <span class="input-group-addon">~</span>
					    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind', 'A.YMD_APPROVAL');" validate="date; label:종료일자" maxlength="8"/>
		   			</div>
					
<%-- 					${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to', 'A.YMD_APPROVAL')} --%>
					${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
					<select name="cd_fc" class="selectpicker" id="cd_fc" data-style="btn-primary">
						${ufn:makeFincorpOptions('금융기관명', goods.cd_fc, 'type', '')}
					</select>
					<select name="yn_exe" class="selectpicker" id="yn_exe" data-style="btn-primary">
						${ufn:makeOptions("yn_yes","집행여부", '')}
					</select>
					
<!-- 					<input type="text" id="txt_detail" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','nm_goods');" validate="label:상품명;keydown-enter:goSearch;" /> -->
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmCommissionList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				</div>
			</div>
			<!--// Search -->
			<!--//Alert -->
			<div id="listCommission">
				<%@ include file="/WEB-INF/views/commission/listCommission.jsp"%>
			</div>
		</form>
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
</body>
</html>