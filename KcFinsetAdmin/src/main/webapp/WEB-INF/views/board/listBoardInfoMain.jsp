<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>공지사항</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	
	$('body').layout({
			minSize:				40
		
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
	
	window.setupValidateForm( frmListBoardInfo );
	
	$('.selectpicker').selectpicker();
	
	goSearch();
});

//리스트 조회
function goSearch() {
	jumpPage(1);
}

// 페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	
	var data = frmListBoardInfo.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("boardInfo","<c:url value='/board/listBoardInfo.crz'/>",data);
}

//게시판 조회
function listBoardInfo(id_board) {
	
	if(id_board == "") return false;
	frmListBoardInfo.id_board.value = id_board;
	
	var page = frmListBoardInfo.page.value;
	if(page == "") page = "1";
	
	jumpPage(page);
}

//글쓰기폼 로드
function formBoardInfo(id_board, seq) {
	//var id_board = '${boardForm.id_board}';

	var data= {"id_board":id_board, "seq":seq}
	vLoad("boardInfo","<c:url value='/board/formBoardInfo.crz'/>",data,false);
}

//글보기
function viewBoardInfo(seq) {
	
	if(seq == "") return false;
	
	var data= {"seq":seq}
	vLoad("boardInfo","<c:url value='/board/viewBoardInfo.crz'/>",data,false);
}
</script>
</head>
<body>
<%@ include file="/WEB-INF/include/header.jsp"%>
	<div class="ui-layout-center">
		<div class="ui-layout-content">
			<!-- Title -->
			<div class="h-title">
				<h1 class="pull-left"> 게시판</h1>
				<ol class="breadcrumb pull-right">
					<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
					<li> 게시판</li>
					<li class="active"> 게시판</li>
				</ol>
			</div>
			<div role="tabpanel">
 				<ul class="nav nav-tabs tab-sm" role="tablist">
					<c:forEach var="List" items="${ufn:getBoardList()}" varStatus="status">
						<c:choose>
							<c:when test="${status.count eq 1}">
								<li class="active" role="presentation" onclick="javascript:frmListBoardInfo.id_board.value='${List.id_board}'; goSearch();"><a href="#none" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> ${List.nm_board}</a></li>
							</c:when>
							<c:otherwise>
								<li role="presentation" onclick="javascript:frmListBoardInfo.id_board.value='${List.id_board}'; goSearch();"><a href="#none" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> ${List.nm_board}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
			<div class="w90">
				<form name="frmListBoardInfo" id="frmListBoardInfo" enctype="multipart/form" class="navbar-form">
					<input type="hidden" name="id_board" id="id_board" value="${boardForm.id_board}">
					<input type="hidden" name="page" id="page" />
					<!-- Search -->
					
					<div class="srch">
						<div class="form-group" id="sandbox-container">
				
							<!-- 날짜검색 -->
							<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
								${ufn:makeOptions("cd_board_date","일자", "")}
				   			</select>
							<div class="input-daterange input-group date-w" id="datepicker">
				   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind', 'dt_frt')" validate="date; lable:시작일자;" maxlength="8"/>
							    <span class="input-group-addon">~</span>
							    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind', 'dt_frt')" validate="date; lable:종료일자;" maxlength="8"/>
				   			</div>
				
							${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
							
							<!-- 상세검색 -->
							<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
								${ufn:makeOptions("cd_board_detail","상세검색", "")}
							</select>
							<input type="text" id="txt_detail" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','PIH.no_person')" validate="label:이름;keydown-enter:goSearch;" />
							
							<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
							<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmListBoardInfo');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
						</div>
					</div>
					<!--// Search -->
				</form>	
				<div id="boardInfo">
					<%@ include file="/WEB-INF/views/board/listBoardInfo.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>