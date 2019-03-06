<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>연동 상품관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript" src="<c:url value="/script/util_comm.js"/>"></script>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				"50%" //좌측 width
		,	east__size:				"50%" //우측 width
		
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
	
	if($("#search_cd_fc").val() != ''){
		var page = 1;
		var cd_fc = $("#search_cd_fc").val();
		var yn_use = $("#search_yn_use").val();
		var data = {"cd_fc":cd_fc, "page":page,"yn_use":yn_use};
		console.log(data);
		vLoad("listGoodsbankInfo","<c:url value='/goodsbank/listGoodsbankInfo.crz'/>", data);
	}
});

//리스트 조회
function goSearch() {
	jumpPage(1);
}
//페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	loadGoodsbankInfoList();
}

// 폼 초기화
function initForm() {
	var data = null;
	vLoad("formGoodsbankInfoDetail","<c:url value='/goodsbank/setGoodsbankInfoUse.crz'/>",data);
}
function loadGoodsbankInfoList(){
	
	var cd_template_group = $("#cd_template_group").val();
	var cd_fc = $("#search_cd_fc").val();
	
	if( cd_template_group == 12 && cd_fc == ''){
		alert("금융사 관리자일 경우 회사명을 등록하셔야 합니다.");
		return false;
	}
	
	var data = frmGoodsbankInfoList.serialize();
	if(data == null) return false;
	console.log(data);
	vLoad("listGoodsbankInfo","<c:url value='/goodsbank/listGoodsbankInfo.crz'/>",data);
	
}

function setGoodsbankInfoUse(cd_non_goods){
	var data = {"cd_non_goods":cd_non_goods};
	vLoad("formGoodsbankInfoDetail","<c:url value='/goodsbank/setGoodsbankInfoUse.crz'/>",data);
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
		<div id="formGoodsbankInfoDetail">
			<%@ include file="/WEB-INF/views/goodsbank/formGoodsbankInfoDetail.jsp"%>
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
			<h1 class="pull-left">연동 상품관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">금융사관리</a></li>
				<li class="active">연동 상품관리</li>
			</ol>
		</div>
		<!--// title -->
		
		<form name="frmGoodsbankInfoList" id="frmGoodsbankInfoList" class="navbar-form">
			<input type="text" style="display: none;" /> <!-- 엔터시 자동서브밋 방지  -->
			<input type="hidden" name="page" id="page" />
			<!-- Search -->
			<div class="srch">
				<div class="form-group" id="sandbox-container">
					금융기관명
					<c:choose>
						<c:when test="${ workerVO.cd_template_group eq '12' }">
							<select id="search_cd_fc_temp" name="cd_fc_temp" class="selectpicker" data-style="btn-primary" disabled="disabled">
								${ufn:makeFincorpOptions('금융기관명', workerVO.cd_fc, 'NM', 'N')}
							</select>
							<input type="hidden" id="search_cd_fc" name="cd_fc" class="form-control" value="${workerVO.cd_fc}"/>
						</c:when>  
						<c:otherwise>
							<select id="search_cd_fc" name="cd_fc" class="selectpicker" data-style="btn-primary">
								${ufn:makeFincorpOptions('금융기관명', goods.cd_fc, 'NM', 'N')}
							</select>
						</c:otherwise>
					</c:choose>
					<input type="hidden" id="cd_template_group" name="cd_template_group" value="${workerVO.cd_template_group}">
					<span style="margin-left:10px;">대분류</span>
					<select id="search_cd_goods_class_l" name="cd_goods_class_l" class="selectpicker">
						${ufn:makeOptions("cd_goods_l","전체", "")}
					</select>
					<span style="margin-left:10px;">중분류</span>
					<select id="search_cd_goods_m" name="cd_goods_class_m" class="selectpicker">
						${ufn:makeOptions("cd_goods_m","전체", "")}
					</select>
					<span style="margin-left:10px;">표시여부</span>
					<select id="search_yn_use" name="yn_use" class="selectpicker">
						${ufn:makeOptions("yn_use","전체", "Y")}
					</select>
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					<button type="button" class="btn btn-primary btn-xs" onclick="initForm;"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				</div>
			</div>
			<!--// Search -->
			<!--//Alert -->
			<div id="listGoodsbankInfo">
				<%@ include file="/WEB-INF/views/goodsbank/listGoodsbankInfo.jsp"%>
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