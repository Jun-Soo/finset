<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>접수 리스트</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>

<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>

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

//리스트 조회
function goSearch() {
	jumpPage(1);
}

// 페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	var data = frmApplyList.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listApply","<c:url value='/apply/listApply.crz'/>",data);
}

function goPrepareForm(no_prepare, no_apply) {
	var openWindow = window.open("<c:url value='/prepare/formPrepareMain.crz'/>?no_prepare="+no_prepare+"&no_apply="+no_apply, "open"+no_prepare, "width=1240, height="+(screen.availHeight-80)+", scrollbars=yes, resizable=yes", "");
	
	openWindow.focus();
	
}
</script>
</head>
<body>
<!-- //Layout-North -->
<div class="ui-layout-center">
	<div class="ui-layout-content">
		
		<!-- Title -->
		<div class="h-title">
			<h1 class="pull-left"> 대출 진행 사항 조회</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 코스콤</a></li>
				<li class="active"> 대출 진행 사항 리스트</li>
			</ol>
		</div>
		<!--// Title -->
		
		<form name="frmApplyList" id="frmApplyList" class="navbar-form">
			<input type="hidden" name="cd_apply_doc" id="cd_apply_doc" />
			<input type="hidden" name="page" id="page" />
			
			<!-- Search -->
			<div class="srch">
				<div class="form-group" id="sandbox-container">
					<!-- 상세검색 -->
					<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
						${ufn:makeOptions("cd_apply_detail","고객번호","PE.no_person") }
					</select>
					<input type="text" id="txt_detail" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','PE.no_person')" validate="label:고객번호;keydown-enter:goSearch;" value="P000000124" readonly="readonly"/>
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true" value="P000000124"></span> 검색</button>
					<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmApplyList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				</div>
			</div>
			<!--// Search -->
			
			<div class="panel panel-primary">
				<!-- 요약정보 -->
				<div class="panel-heading">
					<div class="cust-credit pull-left">
						<dl class="stat1">
							<dt><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 전월접수</dt>
							<dt>(${ufn:formatNumber(summary['CNT_LAST_MONTH_APPLY'])}건/${ufn:formatNumber(summary['AMT_LAST_MONTH_APPLY'])}천원)</dt>
							<dt><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 전월승인</dt>
							<dt>(${ufn:formatNumber(summary['CNT_LAST_MONTH_APPROVAL'])}건/${ufn:formatNumber(summary['AMT_LAST_MONTH_APPROVAL'])}천원)</dt>
						</dl>
						<dl class="stat1">
							<dt><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 당월접수</dt>
							<dt>(${ufn:formatNumber(summary['CNT_MONTH_APPLY'])}건/${ufn:formatNumber(summary['AMT_MONTH_APPLY'])}천원)</dt>
							<dt><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 당월승인</dt>
							<dt>(${ufn:formatNumber(summary['CNT_MONTH_APPROVAL'])}건/${ufn:formatNumber(summary['AMT_MONTH_APPROVAL'])}천원)</dt>
						</dl>
					</div>
				</div>
				<div id="listApply">
		<%-- 			<c:choose>
						<c:when test="${ufn:getNvlCodeName('_CONF_LIST', 'apply', '') eq 'brother'}">
							<%@ include file="/WEB-INF/views/apply/listApply_brother.jsp" %>
						</c:when>
						<c:otherwise>   --%>
							<%@ include file="/WEB-INF/views/apply/listApply.jsp"%>
						 <%--  </c:otherwise>
					</c:choose>  --%>
				</div>
			</div>
		</form>
	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>