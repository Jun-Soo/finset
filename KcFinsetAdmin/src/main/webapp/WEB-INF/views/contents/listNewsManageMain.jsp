<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>뉴스관리</title>
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

//리스트 조회
function goSearch(page) {
	jumpPage(page);
}

// 페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	
	var data = frmNewsManageList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listNewsManage","<c:url value='/contents/listNewsManage.crz'/>",data);
	initIfrmNewsArea();
}

//원문기사 관련 초기화
function initIfrmNewsArea(){
	$("#seq_news").val(''); 
	$("#ifrmNewsArea").hide();
}

//원문기사iframe처리
function viewIfrmNewsContent(seq, newsUrl){
	var link = newsUrl;
	
	$("#seq_news").val(seq);
	
	if(link != null && link != ""){
		ifrmNewsContents.location.href = link;
	}
	
	setTimeout(function(){
		$("#ifrmNewsArea").show();
	}, 2000);
}

/* 기사등록 화면 */
function goNewsManageFormPopup() {
	var seq_news = $("#seq_news").val();
	var openWindow = window.open("<c:url value='/contents/formNewsManage.crz'/>?seq_news="+seq_news, "pop_formNewsManage", "width=800, height="+(screen.availHeight-250)+", scrollbars=yes, resizable=yes", "");
	openWindow.focus();
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
			<h1 class="pull-left"> 뉴스관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 컨텐츠관리</a></li>
				<li class="active"> 뉴스관리</li>
			</ol>
		</div>
		<!--// Title -->

		<form name="frmNewsManageList" id="frmNewsManageList" class="navbar-form">
		<input type="hidden" id="page" name="page" />
		<input type="hidden" name="return_url" value="http://fincook-lca.ccse.co.kr/credit/niceCentCert.crz">
		<input type="hidden" name="safekey" value="" maxlength="13">
		<input type="hidden" name="name" value="">
		<input type="hidden" name="cust_key" value="">
		<input type="hidden" id="seq_news" name="seq_news" value="">
		
		<!-- Search -->
		<div class="srch">
			<div class="form-group" id="sandbox-container">
				
				<!-- 날짜검색 -->
				<div class="input-daterange input-group date-w" id="datepicker">
	   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
				    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" value="${yesterday}" validate="date; label:시작일자;" maxlength="8"/>
				    <span class="input-group-addon">~</span>
				    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" value="${today}" validate="date; label:종료일자;" maxlength="8"/>
	   			</div>
	   			${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
							
				<select name="news_status" class="selectpicker" id="news_status" data-style="btn-primary">
					${ufn:makeOptions("news_status","상태", "")}
				</select>
				<select name="news_search_query" class="selectpicker" id="news_search_query" data-style="btn-primary">
					${ufn:makeOptions("news_search_query","키워드", "")}
				</select>
				<button type="button" class="btn btn-primary btn-xs" onclick="goSearch(1);"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
				
			</div>
		</div>
		<!--// Search -->
		
		<!-- list -->
		<div id="listNewsManage" style="width:57%; float:left;">
			<%@ include file="/WEB-INF/views/contents/listNewsManage.jsp"%>
		</div>
		<!-- //list -->
		<!-- 원문뉴스보기 -->
		<div id="ifrmNewsArea" style="overflow:visible;width:42%; height:700px; float:right; display:none;">
			<span class="pull-right" style="margin-bottom:10px;">
			<button id="newsCreateBtn" type="button" class="btn btn-default btn-xs" onclick="goNewsManageFormPopup();">
				<span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 등록
			</button>
			</span>
			<iframe id="ifrmNewsContents" name="ifrmNewsContents" frameborder="0" style="width:100%; height:100%;"></iframe>	
		</div>	
		<!-- //원문뉴스보기 -->
		
		</form>
	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>