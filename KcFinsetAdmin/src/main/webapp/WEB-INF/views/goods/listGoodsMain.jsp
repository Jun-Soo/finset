<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>제휴 상품관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<script type="text/javascript" src="<c:url value="/script/util_comm.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
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
		var data = {"cd_fc":cd_fc,"page":page,"yn_use":yn_use};
		console.log(data);
		vLoad("listGoods","<c:url value='/goods/listGoods.crz'/>",data);
	}
	
});

//리스트 조회
function goSearch() {
	jumpPage(1);
}
//페이지 이동
function jumpPage(page) {
	$("#page").val(page);
	loadGoodsList();
}
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
// 폼 초기화
function initForm() {
	frmGoodsList.tmp_cd_goods.value = "";
	var data = {"tab_type":frmGoodsList.tab_type.value};
	vLoad("formGoodsTab","<c:url value='/goods/formGoodsInfo.crz'/>", data);
}
function loadGoodsList(){
	var cd_template_group = $("#cd_template_group").val();
	var cd_fc = $("#search_cd_fc").val();
	
	if( cd_template_group == 12 && cd_fc == ''){
		alert("금융사 관리자일 경우 회사명을 등록하셔야 합니다.");
		return false;
	}
	
	var data = frmGoodsList.ajaxSubmit();
	console.log(data);
	if(data == null) return false;
	vLoad("listGoods","<c:url value='/goods/listGoods.crz'/>",data);
}
</script>
</head>
<body>
<!-- Layout-North -->

<!--// Layout-North -->

<!-- Layout-East -->
<div class="ui-layout-east">
	<div class="ui-layout-content">
	<!-- Content -->
		<div id="formGoodsTab">
			<%@ include file="/WEB-INF/views/goods/formGoodsDetail.jsp"%>
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
			<h1 class="pull-left">제휴 상품관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">상품관리(금융사)</a></li>
				<li class="active">제휴 상품관리</li>
			</ol>
		</div>
		<!--// title -->
		
		<form name="frmGoodsList" id="frmGoodsList" class="navbar-form">
			<input type="text" style="display: none;" /> <!-- 엔터시 자동서브밋 방지  -->
			<input type="hidden" name="tmp_cd_fc" id="tmp_cd_fc" />
			<input type="hidden" name="tmp_cd_goods" id="tmp_cd_goods" />
			<input type="hidden" name="page" id="page" />
			<input type="hidden" name="tab_type" id="tab_type" />
			<!-- Search -->
			<div class="srch">
				<div class="form-group" id="sandbox-container">
					<!-- 금융기관명 -->
					<tr>
						<td> 금융기관명</td>
					</tr>
					<c:choose>
						<c:when test="${ workerVO.cd_template_group eq '12'  }">
							<select id="search_cd_fc_temp" name="cd_fc_temp" class="selectpicker" data-style="btn-primary" disabled="disabled">
								${ufn:makeFincorpOptions('금융기관명', workerVO.cd_fc, 'NM', 'Y')}
							</select>
							<input type="hidden" id="search_cd_fc" name="cd_fc" class="form-control" value="${workerVO.cd_fc}"/>
						</c:when>  
						<c:otherwise>
							<select id="search_cd_fc" name="cd_fc" class="selectpicker" data-style="btn-primary">
								${ufn:makeFincorpOptions('금융기관명', goods.cd_fc, 'NM', 'Y')}
							</select>
						</c:otherwise>
					</c:choose>
					<select id="sel_detail" name="sel_detail" class="selectpicker" data-style="btn-primary">
						${ufn:makeOptions("cd_goods_detail","상품상세검색", "")}
					</select>
					<input type="hidden" id="cd_template_group" name="cd_template_group" value="${workerVO.cd_template_group}">
					<input type="text" id="txt_detail" name="txt_detail" class="form-control" onclick="defaultSetting('sel_detail','nm_goods');" validate="label:상품명;keydown-enter:goSearch;"/>
					<select id="search_yn_use" name="yn_use" class="selectpicker" data-style="btn-primary">
						${ufn:makeOptions("yn_use","판매여부", "Y")}
					</select>  
					<button type="button" class="btn btn-primary btn-xs" onclick="goSearch();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
					<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmGoodsList');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				</div>
			</div>
			<!--// Search -->
			<!--//Alert -->
			<div id="listGoods">
				<%@ include file="/WEB-INF/views/goods/listGoods.jsp"%>
			</div>
		</form>
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
</body>
</html>