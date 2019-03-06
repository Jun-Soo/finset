<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>대출신청관리(서류함)</title>
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
	console.log(data);
	vLoad("listApply","<c:url value='/apply/listApply.crz'/>",data);
}

function goPrepareForm(no_prepare, no_apply) {
	var openWindow = window.open("<c:url value='/prepare/formPrepareMain.crz'/>?no_prepare="+no_prepare+"&no_apply="+no_apply, "open"+no_prepare, "width=1240, height="+(screen.availHeight-80)+", scrollbars=yes, resizable=yes", "");
	
	openWindow.focus();
	
}

//엑셀 다운로드
function getExcel(){
	
	if(frmApplyList.listLength.value == 0 ){
		alert("검색 후 진행하여 주세요.");
		return false;
	}
	
	frmApplyList.txt_dt_from.value = frmApplyList.txt_dt_from.value.replace( /^\$|-/g, "");
	frmApplyList.txt_dt_to.value = frmApplyList.txt_dt_to.value.replace( /^\$|-/g, "");
	
	frmApplyList.action = "<c:url value='/apply/listApply_excel.crz'/>";
	frmApplyList.target = "_proc";
	frmApplyList.method = "POST";
	frmApplyList.submit();
}
function goGoodsDetailsPop(cd_fc, cd_goods){
	var openWindow = window.open("<c:url value='/goods/viewGoodsDetailsPop.crz'/>?cd_fc="+cd_fc+"&cd_goods="+cd_goods, "cd_goods"+cd_goods, "width=1240, height="+(screen.availHeight-80)+", scrollbars=yes, resizable=yes", "");
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
			<h1 class="pull-left"> 대출신청관리(서류함)</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 조회및실행이력</a></li>
				<li class="active"> 대출신청관리(서류함)</li>
			</ol>
		</div>
		<!--// Title -->
		
		<form name="frmApplyList" id="frmApplyList" class="navbar-form">
			<input type="hidden" name="cd_apply_doc" id="cd_apply_doc" />
			<input type="hidden" name="page" id="page" />
		<!-- 서류함  -->
		<div role="tabpanel">
		<ul class="nav nav-pills" role="tablist">
			<li class="active" role="presentation" onclick="javascript:frmApplyList.cd_apply_doc.value=''; goSearch();"><a href="#none" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 전체</a></li>
			<c:forEach var="List" items="${ufn:getCodeList('cd_apply_doc_box')}">
				<c:choose>
					<c:when test="${List.code_value eq '50' || List.code_value eq '60'}">
						<li role="presentation" onclick="javascript:frmApplyList.cd_apply_doc.value=${List.code_value}; goSearch();"><a href="#none" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span> ${List.nm_code}</a></li>
					</c:when>
					<c:otherwise>
						<li role="presentation" onclick="javascript:frmApplyList.cd_apply_doc.value=${List.code_value}; goSearch();"><a href="#none" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span> ${List.nm_code} <%-- (<label id="APPLY_${List.code_value}">${ufn:formatNumber(cntApplyDoc[List.code_value])}</label>) --%></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
		</div>
		<!-- //서류함 -->
			
			<!-- Search -->
			<div class="srch">
				<div class="form-group" id="sandbox-container">
					
					<!-- 날짜검색 -->
					<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
						${ufn:makeOptions("cd_apply_date","일자", "")}
		   			</select>
					<div class="input-daterange input-group date-w" id="datepicker">
		   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
					    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" onclick="defaultSetting('sel_dt_kind', 'AP.dt_frt');" validate="date; label:시작일자" maxlength="8"/>
					    <span class="input-group-addon">~</span>
					    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" onclick="defaultSetting('sel_dt_kind', 'AP.dt_frt');" validate="date; label:종료일자" maxlength="8"/>
		   			</div>
					
					${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
					<!-- 금융사 -->
					<c:choose>
						<c:when test="${ workerVO.cd_template_group eq '12'  }">
							<select name="cd_fc" class="selectpicker" id="cd_fc" data-style="btn-primary" disabled="disabled">
								${ufn:makeFincorpOptions('금융기관명', workerVO.cd_fc, 'type', 'Y')}
							</select>
							<input type="hidden" name="cd_fc" id="cd_fc" value="${workerVO.cd_fc }"/>
						</c:when>
						<c:otherwise>
							<select name="cd_fc" class="selectpicker" id="cd_fc" data-style="btn-primary">
								${ufn:makeFincorpOptions('금융기관명', '', 'type', 'Y')}
							</select>
						</c:otherwise>
					</c:choose>
					
					<!-- 상세검색 -->
					<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
						${ufn:makeOptions("cd_apply_detail","상세검색","") }
					</select>
					<input type="text" id="txt_detail" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','PE.nm_person')" validate="label:이름;keydown-enter:goSearch;" />
					
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					<!-- 엑셀 다운로드 버튼(권한필요) -->
					<c:if test="${ufn:isApprAuth('402002',sessionScope.STR_APPROVAL)}">
						<button type="button" class="btn btn-primary btn-xs" onclick="getExcel();"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> Excel다운</button>
					</c:if>
					<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmApplyList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
					
					
				</div>
			</div>
			<!--// Search -->
			
			<div class="panel panel-primary">
				<div id="listApply">
					<c:choose>
						<c:when test="${ufn:getNvlCodeName('_CONF_LIST', 'apply', '') eq 'brother'}">
							<%@ include file="/WEB-INF/views/apply/listApply_brother.jsp" %>
						</c:when>
						<c:otherwise>
							<%@ include file="/WEB-INF/views/apply/listApply.jsp"%>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form>
	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>