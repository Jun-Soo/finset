<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>상품 조회 이력</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<script type="text/javascript" src="<c:url value="/script/util_comm.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
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

//페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	listApplyResultInfo();
}
function listApplyResultInfo(){
	var data = frmApplyResultList.ajaxSubmit();
	console.log(data);
	if(data == null) return false;
	vLoad("listApplyResultInfo","<c:url value='/apply/listApplyResultInfo.crz'/>",data);
}

function initForm() {
	var data = frmApplyResultList.ajaxSubmit();
	vLoad("listApplyResultMain","<c:url value='/apply/listApplyResultMain.crz'/>", data);
}

</script>
</head>
<body>
<!-- //Layout-North -->
<div class="ui-layout-center">
	<div class="ui-layout-content">
		<!-- Title -->
		<div class="h-title">
			<h1 class="pull-left"> 상품 조회 이력</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#"> 상품관리(금융사)</a></li>
				<li class="active"> 상품 조회 이력</li>
			</ol>
		</div>
		<!--// Title -->

		<form name="frmApplyResultList" id="frmApplyResultList" class="navbar-form" method="post">
			<input type="hidden" name="tmp_cd_fc" id="tmp_cd_fc" />
			<input type="hidden" name="tmp_cd_goods" id="tmp_cd_goods" />
			<input type="hidden" name="page" id="page" />
			<input type="hidden" name="tab_type" id="tab_type" />
		<!-- Search -->
		<div class="srch">
			<div class="form-group" id="sandbox-container">
				<!-- 날짜검색 -->
				<select name="sel_dt_kind" class="selectpicker" id="sel_dt_kind" data-style="btn-primary" >
					${ufn:makeOptions("cd_person_date","일자", prepare.cd_goods_type) }
	   			</select>
				<div class="input-daterange input-group date-w" id="datepicker">
	   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
				    <input type="text" class="input-sm form-control" name="txt_dt_from" id="txt_dt_from" validate="date; label:시작일자;" maxlength="8"/>
				    <span class="input-group-addon">~</span>
				    <input type="text" class="input-sm form-control" name="txt_dt_to" id="txt_dt_to" validate="date; label:종료일자;" maxlength="8"/>
	   			</div>
				
<%-- 				${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to', 'P.dt_lst')} --%>
				${ufn:quickYmdFromTo('T,Y,TM,W,LW,M,LM', 'txt_dt_from', 'txt_dt_to')}
			
				<c:choose>
					<c:when test="${ workerVO.cd_template_group eq '12'  }">
						<select name="cd_fc" class="selectpicker" id="cd_fc" data-style="btn-primary" disabled="disabled">
							${ufn:makeFincorpOptions('금융기관명', workerVO.cd_fc, 'NM', 'Y')}
						</select>
						<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
							${ufn:makeOptions("cd_goods_detail","상품상세검색", "")}
						</select>
						<input type="hidden" id="cd_template_group" name="cd_template_group" value="${workerVO.cd_template_group}">
					</c:when>
					<c:otherwise>
						<select name="cd_fc" class="selectpicker" id="cd_fc" data-style="btn-primary">
							${ufn:makeFincorpOptions('금융기관명', goods.cd_fc, 'NM', 'Y')}
						</select>
						<select name="sel_detail" class="selectpicker" id="sel_detail" data-style="btn-primary">
							${ufn:makeOptions("cd_goods_detail","상품상세검색", "")}
						</select>
						<input type="hidden" id="cd_template_group" name="cd_template_group" value="${workerVO.cd_template_group}">
					</c:otherwise>
				</c:choose>
				<input type="text" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','nm_goods');" validate="label:상품명;keydown-enter:goSearch;"/>
				<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
				<button type="button" class="btn btn-primary btn-xs" onclick="initForm('frmApplyResultList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
			</div>
		</div>
		<!--// Search -->
			<div id="listApplyResultInfo">
				<%@ include file="/WEB-INF/views/apply/listApplyResultInfo.jsp"%>
			</div>
		</form>
	</div>
</div>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>