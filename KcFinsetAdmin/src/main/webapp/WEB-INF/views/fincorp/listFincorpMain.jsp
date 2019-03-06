<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>금융사리스트</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<%@ include file="/WEB-INF/include/postCode.jsp"%>
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
	loadFincorpList();
}

//


function clearCacheGoods(){
	$.ajax({
		url : "<c:url value='/goods/clearCacheGoods.json'/>",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			alert("상품정보 변경이 적용되었습니다.");
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
function loadFincorpList(){
	var data = frmFincorpList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listFincorp","<c:url value='/fincorp/listFincorp.crz'/>",data);
}

/* 금융사 상세 조회/수정 화면 */
function goFincorpForm(cd_fc) {
	
	var openWindow = window.open("<c:url value='/fincorp/formFincorpMain.crz'/>?cd_fc="+cd_fc, "open"+cd_fc, "width=1100, height="+(screen.availHeight-105)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus();
}

/* 금융사 신규등록 화면 */
function goApplyForm() {
	
	//var openWindow = window.open("<c:url value='/fincorp/formFincorpMain2.crz'/>", "width=1950, height="+(screen.availHeight-80)+", scrollbars=yes, resizable=yes", "");
/* 	var openWindow = window.open("<c:url value='/fincorp/formPopFincorpMain.crz'/>","openC", "width=600, height="+(screen.availHeight-730)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus(); */
	var openWindow = window.open("<c:url value='/fincorp/formFincorpMain.crz'/>?cd_fc=", "open", "width=1100, height="+(screen.availHeight-105)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus();
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
			<h1 class="pull-left">금융사리스트</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">금융사관리</a></li>
				<li class="active">금융사리스트</li>
			</ol>
		</div>
		<!--// title -->
		
		<form name="frmFincorpList" id="frmFincorpList" class="navbar-form">
			<input type="text" style="display: none;" /> <!-- 엔터시 자동서브밋 방지  -->
			<input type="hidden" name="tmp_cd_goods" id="tmp_cd_goods" /> 
			<input type="hidden" name="page" id="page" />
			<!-- Search -->
			<div class="srch">
				<div class="form-group" id="sandbox-container">
					<!-- 날짜검색 -->
					<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
						${ufn:makeOptions("cd_fc_date","일자", "")}
		   			</select>
					<div class="input-daterange input-group date-w" id="datepicker">
		   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
					    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind', 'dt_frt');" validate="date; label:시작일자" maxlength="8"/>
					    <span class="input-group-addon">~</span>
					    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind', 'dt_frt');" validate="date; label:종료일자" maxlength="8"/>
		   			</div>
						${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
					<!-- 금융기관업권 -->
					<select name="cd_fin" class="selectpicker" id="cd_fin" data-style="btn-primary">
						${ufn:makeOptions("cd_fin","금융기관업권", "")}
					</select>
					<!-- 상세검색 -->
					<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
						${ufn:makeOptions("cd_fincorp_detail","상세검색", "")}
					</select>
					<input type="text" id="txt_detail" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','nm_fc');" validate="label:상품명;keydown-enter:goSearch;" />
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					<c:if test="${cd_template_group eq '00'}">
							<button type="button" class="btn btn-primary btn-xs" onclick="goApplyForm();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 신규</button>
					</c:if>
					<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmFincorpList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				</div>
			</div>
			<!--// Search -->
			<!--//Alert -->
			
			<div id="listFincorp">
				<%@ include file="/WEB-INF/views/fincorp/listFincorp.jsp"%>
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