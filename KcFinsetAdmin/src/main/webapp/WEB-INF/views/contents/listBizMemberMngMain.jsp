<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>가맹점관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				"50%" //좌측 width
		
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
		,  west__initClosed: 		false
	});

	// toggle panel
	$(".toggle-panel").each(function(i){
		$(this).find(".h-sec > a").click(function(){
			$(this).toggleClass("closed");
			$(this).closest(".toggle-panel").find(".toggle-cont").toggle("blind", 200);
		});
	});
});
// 분류 선택 시 이동 로직
function changeCdClass() {
	var cd_class = $("#biz_member_cd_class").val();
	if((cd_class||"")=="") return false;
	var bizMemberCdType = $("#biz_member_cd_type");
	$("#biz_member_cd_type").empty()
	$("#biz_member_cd_type").append("<option value=''>선택</option>")
    <c:forEach var="cdTypeList" items="${cdTypeMap}" varStatus="status">
		if(cd_class == "${cdTypeList.key}"){
	    	<c:forEach var="cdTypeVO" items="${cdTypeList.value}" varStatus="status">
		    	$("#biz_member_cd_type").append("<option value='${cdTypeVO.cd_type}'>${cdTypeVO.nm_type}</option>")
			</c:forEach>
		}
	</c:forEach>
	$('select[name=cd_type]').selectpicker('refresh');
};

// 카드승인내역 검색
function goSearchApproval() {
	var data = frmSchAprv.ajaxSubmit();
	if(($("#aprv_sel_cd_fc").val() || "") == "") {
		alert("카드사를 입력해 주세요");
		return;
	}
	if(data == null) return false;
	vLoad("listCardAprvMng","<c:url value='/contents/listCardAprvMng.crz'/>",data);
};

// 가맹점명 검색
function goSearchNmBIz() {
	var data = frmSchNmBiz.ajaxSubmit();
	if(data == null) return false;
	vLoad("listNmBizMemberMng","<c:url value='/contents/listNmBizMemberMng.crz'/>", data, false);
};

// 카드 승인내역 정보 조회 - 형태만 맞춰준 것이고 실제로는 화면에 있는 데이터를 사용하기 위함
function getCardAprvMng(nm_member, no_biz_member) {
	frmNmBizMemberMng.nm_member.value = nm_member;
	frmNmBizMemberMng.no_biz_member.value = no_biz_member;
	
	$('select[name=cd_class]').val('');
	$('select[name=cd_type]').empty();
	$('select[name=cd_type]').append('<option value="" selected="selected">선택</option>');
	
	$('select[name=cd_class]').selectpicker('refresh');
	$('select[name=cd_type]').selectpicker('refresh');
	window.setupValidateForm( frmNmBizMemberMng );
};

// 가맹점명 정보 조회
function getNmBizMemberMng(nm_member, no_biz_member) {
	var data = {"nm_member":nm_member, "no_biz_member":no_biz_member};
	vLoad("formNmBizMemberMng","<c:url value='/contents/getNmBizMemberMng.crz'/>", data, false);
	
	window.setupValidateForm( frmNmBizMemberMng );
};

// 가맹점명 등록, 수정
function procNmBizMemberMng() {
	var frm = document.frmNmBizMemberMng;
	if(!frm.validateForm()) return false;
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/contents/procNmBizMemberMng.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			goSearchNmBIz();
			goSearchApproval();
			getNmBizMemberMng('','');
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
};

// 가맹점명 일괄 등록
function getNmBizMemberMulti() {
	
	var multi = $(".multi:checked");
	if((multi||"")==""||multi.length==0) {
		alert("좌측 승인내역 중 선택된 내역이 없습니다.")
		return false;
	}

	if(!document.frmNmBizMemberMng.validateForm()) return false;
	
	if(confirm("좌측에 체크된 모든 항목을 우측 분류, 항목으로 한번에 등록합니다. \n정말 진행하시겠습니까?") == false) {
		return false;
	}
	
	var frm = document.createElement("form");
	
	$(frm).append("<input name='cd_class' value='"+document.frmNmBizMemberMng.biz_member_cd_class.value+"'/>");
	$(frm).append("<input name='cd_type' value='"+document.frmNmBizMemberMng.biz_member_cd_type.value+"'/>");
	for(var i = 0; i<multi.length; i++) {
		$(frm).append("<input name='nm_member' value='"+$(multi[i]).data("nm_member")+"'/>");
		$(frm).append("<input name='no_biz_member' value='"+$(multi[i]).data("no_biz_member")+"'/>");
	}
	window.setupValidateForm( frm );
	var data = frm.ajaxSubmit();
	$.ajax({
		url : "<c:url value='/contents/procNmBizMemberMngMulti.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);

			goSearchNmBIz();
			goSearchApproval();
			getNmBizMemberMng('','');
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
};

// 가맹점명 삭제
function delNmBizMemberMng() {
	var frm = frmNmBizMemberMng;
	frm.validateForm();
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	if(confirm("정말 삭제하시겠습니까?") == false) {
		return false;
	}
	$.ajax({
		url : "<c:url value='/contents/delNmBizMemberMng.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			goSearchNmBIz();
			goSearchApproval();
			getNmBizMemberMng('','');
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

</script>

</head>
<body>
<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!--// Layout-North -->

<!-- Layout-West -->
<div class="ui-layout-west">
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">카드승인내역관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">컨텐츠관리</a></li>
				<li class="active">가맹점관리</li>
			</ol>
		</div>
		<!--// title -->
	<!-- Content -->
		<div class="srch align-l">
			<form name="frmSchAprv" onsubmit="return false;">
			<select class="selectpicker" name="sel_cd_fc" id="aprv_sel_cd_fc" data-style="btn-primary">
				<option value="">카드사</option> 
				<c:forEach var="schCardFcList" items="${schCardFcList}" varStatus="status">
					<option value="${schCardFcList.cd_fc}">${schCardFcList.nm_fc}</option> 
				</c:forEach>
			</select>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchApproval();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			</form>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 카드승인내역관리</a>
				</h3>
			</div>
		</div>
		
		<div id="listCardAprvMng" class="d-table">
			<%@ include file="/WEB-INF/views/contents/listCardAprvMng.jsp"%>
		</div>
	<!-- //Content -->
	</div>
</div>
<!--// Layout-West -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">가맹점명관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">컨텐츠관리</a></li>
				<li class="active">가맹점관리</li>
			</ol>
		</div>
		<!--// title -->
		<div class="srch align-l">
			<form name="frmSchNmBiz">
			<!-- 
			<select class="selectpicker" name="sel_cd_fc" id="sel_cd_fc" data-style="btn-primary">
				<option value="">카드사</option> 
				<c:forEach var="schCardFcList" items="${schCardFcList}" varStatus="status">
					<option value="${schCardFcList.cd_fc}">${schCardFcList.nm_fc}</option>
				</c:forEach>
			</select>
			 -->
			<select class="selectpicker" name="sel_cd_class" id="aprv_sel_cd_class" data-style="btn-primary">
				<option value="">분류</option>
				<c:forEach var="cdClassVO" items="${cdClassList}" varStatus="status">
					<option value="${cdClassVO.cd_class}">${cdClassVO.nm_class}</option>
				</c:forEach>
			</select>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchNmBIz();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			</form>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 가맹점명관리</a>
				</h3>
			</div>
			<div id="formNmBizMemberMng" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/contents/formNmBizMemberMng.jsp"%>
			</div>
		</div>
		
		<div id="listNmBizMemberMng" class="d-table">
			<%@ include file="/WEB-INF/views/contents/listNmBizMemberMng.jsp"%>
		</div>
	<!--// Layout-Content -->
	</div>
</div>
<!--// Layout-Center -->

<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>