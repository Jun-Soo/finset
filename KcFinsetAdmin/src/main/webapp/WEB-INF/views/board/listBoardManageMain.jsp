<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>게시판관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	
	//Layout
	$('body').layout({
			minSize:				40
		,	east__size:				670 //우측 width
		
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

//리스트 클릭했을때 form에 해당 정보삽입
function getBoardManage(id_board) {

	var data = {"id_board":id_board};
	vLoad("formBoardManage","<c:url value='/board/getBoardManage.crz'/>",data,false);
	
	//listBoardInfo(id_board);
}

//게시판 관리 리스트 조회
function listBoardManage() {
	var data = "";
	vLoad("listBoardManage","<c:url value='/board/listBoardManage.crz'/>",data,false);
}

</script>
</head>
<body>
<%@ include file="/WEB-INF/include/header.jsp"%>

<!-- Layout-East -->

<!--// Layout-East -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">게시판관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">게시판</a></li>
				<li class="active">게시판관리</li>
			</ol>
		</div>
		<!--// title -->
		
		<!-- Alert -->
		<div class="alert alert-success alert-dismissible exclamation-list" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span></button>
			<ul>
				<li>게시판 관리에서 게시판 목록을 확인하고 게시판 등록 및 수정을 할 수 있습니다.</li>
				<li><strong>게시판명</strong>을 누르시면 게시판 정보를 확인 하실 수 있습니다.</li>
				<li>등록 및 변경을 완료 하신 후 <strong>적용</strong>을 누르시면 변경된 정보가 시스템에 반영됩니다.</li>
			</ul>
		</div>
		<!--//Alert -->
		
		<div id="formBoardManage">
			<%@ include file="/WEB-INF/views/board/formBoardManage.jsp"%>
		</div>

		<div id="listBoardManage">
			<%@ include file="/WEB-INF/views/board/listBoardManage.jsp"%>
		</div>
	
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>