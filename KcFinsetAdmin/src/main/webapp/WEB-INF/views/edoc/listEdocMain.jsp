<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>전문관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				"100%" //좌측 width
		
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

//초기화
function resetForm(frm) {
	frm.reset();
	$('.selectpicker').selectpicker('refresh');//selectpicker 초기화
	ChangeSelectBox("","");
}

// 금융사전문별 항목그룹 검색
function goSearchCG() {
	if(!frmSchCG.validateForm()) return;
	var data = frmSchCG.ajaxSubmit();
	if(data == null) return false;
	vLoad("listEdocGroup","<c:url value='/edoc/listEdoc.crz'/>",data);
}
function loadEdocGroup(seq_edoc) {
	var data = {"seq_edoc":seq_edoc};
	vLoad("formEdocGroup","<c:url value='/edoc/getEdoc.crz'/>",data,false);
	window.setupValidateForm( frmEdocGroup );
}
// 엔터검색
function isEnter(keyEdoc, type) {
	if( keyEdoc == 13 ) {
		goSearchCG();
		frmSchCG.txt_detail.blur();
	}
}
function procEdocInfo(frm, type) {
	if(frm.cd_fc.value == ""){
		alert("금융사를 선택해해주세요");
		frm.cd_fc.focus();
		return;
	}
	if(frm.no_edoc.value == ""){
		alert("전문을 선택해주세요");
		frm.no_edoc.focus();
		return;
	}
	if(!frm.validateForm()) return false;
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/edoc/procEdocInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			alert(result.message);
			if(parseInt(result.cd_result,10) == 0) {
				goSearchCG();
			}
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

function delEdocInfo(frm, type) {
	if(!frm.validateForm()) return false;
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/edoc/delEdocInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			if(parseInt(returnData.cd_result,10) == 0) {
				goSearchCG();
			}
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
function ChangeSelectBox(frm, obj){
	
	if(!obj.value) {
		frm.no_edoc.options.length=0;
		$('#no_edoc').append("<option value=''>전문번호</option>");
		$('#no_edoc').selectpicker('refresh');
		return;
	}
	var data = {"cd_fc":obj.value};
	if(data == null) return false;
//	if(!frm.validateForm()) return false;
	 var target= frm.no_edoc;
	 var boxOption; 
	 target.options.length=0;
	$.ajax({
		url : "<c:url value='/edoc/listNmEdoc.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		dataType : "json",
		success : function(data) {
			
			boxOption = document.createElement("OPTION");
	        boxOption.value = '';     
	        boxOption.text = '전문번호';
	        target.options.add(boxOption);
	        
			$.each(data.List, function (index, item) {
		        boxOption = document.createElement("OPTION");
		        boxOption.value = item;     
		        boxOption.text = item;
		        target.options.add(boxOption);
			});	
		
	        $('#no_edoc').selectpicker('refresh');
			target.options.length = data.List.length + 1;
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
<!-- 			<h1 class="pull-left">전문관리</h1> -->
			<ol class="breadcrumb pull-left">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">전문관리</a></li>
				<li class="active">전문관리</li>
			</ol>
		</div>
		<!--// title -->
	<!-- Content -->
		<div class="srch align-l">
			<form name="frmSchCG" onsubmit="return false;">
			<select class="selectpicker" name="cd_fc" data-style="btn-primary">
				${ufn:makeFincorpOptions('전체', '', 'type', '')}
			</select>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchCG();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			</form>
		</div>
		
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 금융사전문별 항목그룹</a>
				</h3>
			</div>
			<div id="formEdocGroup" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/edoc/formEdocGroup.jsp"%>
			</div>
		</div>
		
		<div id="listEdocGroup" class="d-table">
			<%@ include file="/WEB-INF/views/edoc/listEdocGroup.jsp"%>
		</div>


	<!-- //Content -->
	</div>
</div>
<!--// Layout-West -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
		
		
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>