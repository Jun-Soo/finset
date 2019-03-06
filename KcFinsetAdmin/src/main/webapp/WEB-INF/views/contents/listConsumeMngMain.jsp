<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>소비분류관리</title>
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
	
	listConsumeSchCdClass();
});

//검색조건 - 분류
function listConsumeSchCdClass(){
	
	$("select[name=sel_cd_class]").empty();
	
	$("select[name=sel_cd_class]").append("<option value=''>분류</option>");
	$("#card_sel_cd_class").append("<option value='none'>미지정</option>");
		
	$.ajax({
		url : "<c:url value='/contents/listConsumeSchCdClass.crz'/>",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		dataType : "json",
		success : function(data) {
			$.each(data.schCdClassList, function (index, item) {
				$("select[name=sel_cd_class]").append("<option value='"+item.cd_class+"'>"+item.nm_class+"</option>");
			});	
			$('select[name=sel_cd_class]').selectpicker('refresh');			
		}
			
	});
}

// 지출항목관리 검색
function goSearchSpend() {
	
	var data = frmSchSpend.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listConsumeSpendMng","<c:url value='/contents/listConsumeSpendMng.crz'/>",data);
}

// 카드업종관리 검색
function goSearchCard() {
	
	var data = frmSchCard.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("listConsumeCardMng","<c:url value='/contents/listConsumeCardMng.crz'/>",data);
}

// 지출항목관리 정보 조회
function getConsumeSpendMng(cd_consume_class) {
	
	var data = {"cd_consume_class":cd_consume_class};
	vLoad("formConsumeSpendMng","<c:url value='/contents/getConsumeSpendMng.crz'/>",data,false);
	
	frmConsumeCardMng.cd_consume_class.value = cd_consume_class;
	
	window.setupValidateForm( frmConsumeSpendMng );
}

// 카드업종관리 정보 조회
   function getConsumeCardMng(cd_fc,nm_business) { 
	var data = {"cd_fc":cd_fc,"nm_business":nm_business};
	vLoad("formConsumeCardMng","<c:url value='/contents/getConsumeCardMng.crz'/>",data,false);
	
	window.setupValidateForm( frmConsumeCardMng );
}

   
//지출항목관리 등록/수정
function procConsumeSpendMng() {
	var frm = document.frmConsumeSpendMng;
	if(!frm.validateForm()) return false;
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/contents/procConsumeSpendMng.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			goSearchSpend();
			goSearchCard();
			listConsumeSchCdClass();
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

//지출항목관리 삭제
function delConsumeSpendMng() {
	var frm = frmConsumeSpendMng;
	
	if($("#spend_cd_class").val() == "" || $("#spend_cd_type").val() == ""){
		alert("지출항목을 선택해주세요.");
		return false;
	}
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/contents/delConsumeSpendMng.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			getConsumeSpendMng('');
			getConsumeCardMng('','');
			goSearchSpend();
			goSearchCard();
			listConsumeSchCdClass();
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

//카드업종관리 등록/수정
function procConsumeCardMng() {
	var frm = document.frmConsumeCardMng;
	if(!frm.validateForm()) return false;
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/contents/procConsumeCardMng.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			goSearchCard();
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

//카드업종관리 삭제
function delConsumeCardMng() {
	var frm = frmConsumeCardMng;
	
	if($("#card_cd_fc").val() == "" || $("#card_cd_business").val() == ""){
		alert("카드업종을 선택해주세요.");
		return false;
	}
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/contents/delConsumeCardMng.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			getConsumeCardMng('');
			goSearchCard();
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
			<h1 class="pull-left">지출항목관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">컨텐츠관리</a></li>
				<li class="active">소비분류관리</li>
			</ol>
		</div>
		<!--// title -->
	<!-- Content -->
		<div class="srch align-l">
			<form name="frmSchSpend" onsubmit="return false;">
			<select class="selectpicker" name="sel_cd_class" id="spend_sel_cd_class" data-style="btn-primary">
				<option value="">분류</option>
			</select>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchSpend();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			</form>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 지출항목관리</a>
				</h3>
			</div>
			<div id="formConsumeSpendMng" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/contents/formConsumeSpendMng.jsp"%>
			</div>
		</div>
		
		<div id="listConsumeSpendMng" class="d-table">
			<%@ include file="/WEB-INF/views/contents/listConsumeSpendMng.jsp"%>
		</div>
	<!-- //Content -->
	</div>
</div>
<!--// Layout-West -->

<!-- Layout-Center -->
<div class="ui-layout-center">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">카드업종관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">컨텐츠관리</a></li>
				<li class="active">소비분류관리</li>
			</ol>
		</div>
		<!--// title -->
	<!-- Layout-Content -->
	<div class="ui-layout-content">
		<div class="srch align-l">
			<form name="frmSchCard">
			<select class="selectpicker" name="sel_cd_fc" id="sel_cd_fc" data-style="btn-primary">
				<option value="">카드사</option> 
				<c:forEach var="schCardFcList" items="${schCardFcList}" varStatus="status">
					<option value="${schCardFcList.cd_fc}">${schCardFcList.nm_fc}</option> 
				</c:forEach>
			</select>
			<select class="selectpicker" name="sel_cd_class" id="card_sel_cd_class" data-style="btn-primary">
				<option value="">분류</option>
				<option value="none">미지정</option>
			</select>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchCard();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			</form>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 카드업종관리</a>
				</h3>
			</div>
			<div id="formConsumeCardMng" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/contents/formConsumeCardMng.jsp"%>
			</div>
		</div>
		
		<div id="listConsumeCardMng" class="d-table">
			<%@ include file="/WEB-INF/views/contents/listConsumeCardMng.jsp"%>
		</div>
	
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>