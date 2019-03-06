<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>메시지 전송</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				"70%" //좌측 width
		,	east__size:				"30%" //우측 width
		
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
		window.setupValidateForm(frmListPushEachInfo);
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
		
		var data = frmListPushEachInfo.ajaxSubmit();
		if(data == null) return false;
		
		vLoad("boardInfo", "<c:url value='/pusheach/listPushEachInfo.crz'/>", data);
		vLoad("formPushTab", "<c:url value='/pusheach/popSendPushEach.crz'/>", data);
	}
	

	/*
	* 푸시 상세보기
	*/
	function viewPushInfo(seq) {
		
		var openWindow = window.open("<c:url value='/pusheach/popViewPushEachInfo.crz'/>?seq_push="+seq,"open"+seq, "width=600, height="+(screen.availHeight-600)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}
//모달 팝업 내용보기	
// 	function viewPushInfo(seq) {
// 		$("#popModal").modal("show");

// 		var data = {"seq_push" : seq};

// 		$.ajax({
// 			url : "<c:url value='/push/popViewPushInfo.crz'/>",
// 			data : data,
// 			async : true,
// 			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
// 			type : "POST",
// 			success : function(result) {
// 				$("#modal-content").html(result);
// 			},
// 			error : function(e) {
// 				alert(e.responseText);
// 			}
// 		});
// 	}

	function goCustRel(txt_detail, frm_nm, no_person, no_prepare, yn_grt) {
		var data = {"txt_detail":txt_detail,"frm_nm":frm_nm,"no_person":no_person,"no_prepare":no_prepare};
		$("#modal-lg").modal("show");
		vLoad("modal-content-lg","<c:url value='/deny/listCustRelMain.crz'/>",data);
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
		<div id="formPushEachTab">
			<%@ include file="/WEB-INF/views/pusheach/popSendPushEach.jsp"%>
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
			<h1 class="pull-left">개별 푸시 메시지 전송</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">고객관리</a></li>
				<li class="active">개별고객통지리스트</li>
			</ol>
		</div>
		<!--// title -->
			<form name="frmListPushEachInfo" id="frmListPushEachInfo" class="navbar-form">
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
						   			
						   			<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
										<option name="sel_detail" value="no_person">고객번호</option>
										<option name="sel_detail" value="title">제목</option>
									</select>
									
					   				<input type="text" id="txt_detail" name="txt_detail" class="form-control" validate="label:이름;keydown-enter:goSearch;" />
									<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
<%-- 						   	    고객번호 <input type="text" name="no_person" id="no_person" class="form-control" onkeydown="isEnter(event.keyCode)" value="${noPerson }" validate="required;label:이름;keydown-enter:goSearch;" readonly="readonly"/> --%>
									<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmListPushEachInfo');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
									<button type="button" class="btn btn-default btn-xs" onclick="goCustRel('txt_detail','frmListPushEachInfo','${personVO.no_person}','${prepareVO.no_prepare}','N');">
										<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 고객찾기
									</button> 
								</div>
								<!--// Search -->
							</div>
							<div id="boardInfo">
								<%@ include file="/WEB-INF/views/pusheach/sub/listPushEachInfo.jsp"%>
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