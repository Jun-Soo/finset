<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>고객통지관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
/* 		,	west__size:				"70%" //좌측 width
		,	east__size:				"30%" //우측 width */
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
		window.setupValidateForm(frmListPushInfo);
// 		goSearch();
	
	
});
	/*
	* 리스트 조회
	*/
	function goSearch() {
		jumpPage(1);
	}
	
	/*
	* 페이지 이동
	*/
	function jumpPage(page) {
		$("#page").val(page);
		
		var data = frmListPushInfo.ajaxSubmit();
		if(data == null) return false;
		
		vLoad("boardInfo", "<c:url value='/push/listPushInfo.crz'/>", data);
		vLoad("formPushTab", "<c:url value='/push/popSendPush.crz'/>", data);
	}
	

	/*
	* 푸시 상세보기
	*/
	function viewPushInfo(push_divcd,seq) {
		var vHeight = 0;
/* 		var v_gubun = "";
		if(push_divcd == "전체") {
			v_gubun = "A";
			vHeight = screen.availHeight-600;
		}
		else {
			v_gubun = "E";
			vHeight = screen.availHeight-300;
		} */
		vHeight = screen.availHeight-300;
		
		var openWindow = window.open("<c:url value='/push/popViewPushInfo.crz'/>?seq_push="+seq+"&push_divcd="+push_divcd,"open"+seq, "width=600, height="+(vHeight)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}


	/*
	* 전체 푸시 보내기
	*/
	function formAllSendPush(){

		var openWindow = window.open("<c:url value='/push/popSendPush.crz'/>","open", "width=600, height="+(screen.availHeight-600)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}
	/*
	* 개별 푸시 보내기
	*/
	function formEachSendPush(){

		var openWindow = window.open("<c:url value='/push/popSendPushEach.crz'/>","open", "width=1200, height="+(screen.availHeight-400)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}
</script>

</head>
<body>
<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!--// Layout-North -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">고객통지관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">고객관리</a></li>
				<li class="active">고객통지관리</li>
			</ol>
		</div>
		<!--// title -->
			<form name="frmListPushInfo" id="frmListPushInfo" class="navbar-form">
						<input type="hidden" name="id_board" id="id_board" value="push">
						<input type="hidden" name="page" id="page" />
							<div class="srch">
								<!-- Search -->
								<div class="form-group" id="sandbox-container">
									
									<div class="input-daterange input-group date-w" id="datepicker">
						   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
									    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind', 'P.dt_frt');" validate="date; label:시작일자;" maxlength="8"/>
									    <span class="input-group-addon">~</span>
									    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind', 'P.dt_frt');" validate="date; label:종료일자;" maxlength="8"/>
						   			</div>
									${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
						   			
						   			<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
										<option name="sel_detail" value="title">제목</option>
									</select>
									
					   				<input type="text" id="txt_detail" name="txt_detail" class="form-control" validate="label:이름;keydown-enter:goSearch;" />
									<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
 									<button type="button" class="btn btn-primary btn-xs" onclick="formAllSendPush();"><span aria-hidden="true"></span>전체 푸시 보내기</button>
 									<button type="button" class="btn btn-primary btn-xs" onclick="formEachSendPush();"><span aria-hidden="true"></span>개별 푸시 보내기</button>
								</div>
								<!--// Search -->
							</div>
							<div id="boardInfo">
								<%@ include file="/WEB-INF/views/push/sub/listPushInfo.jsp"%>
							</div>
			</form>	
		</div>
	</div>
	<!--// Layout-Content -->
<!--// Layout-Center -->
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
</body>
</html>