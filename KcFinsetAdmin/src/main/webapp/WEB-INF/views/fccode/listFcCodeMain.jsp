<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>금융사 전문별 데이터항목관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	//Layout
	$('body').layout({
			minSize:				40
		,	west__size:				"55%" //좌측 width
		
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
	ChangeSelectBox("","")
}
// 금융사전문별 항목그룹 검색
function goSearchCG() {
	if(frmSchCG.cd_fc.value == ""){
		alert("금융사를 선택해주세요");
		$('#cd_fc').data('selectpicker').$button.focus();
// 		frm.cd_fc.focus();
		return;
	}
	if(frmSchCG.no_edoc.value == ""){
		alert("전문번호를 선택해주세요");
		$('#no_edoc').data('selectpicker').$button.focus();
		// 		frm.no_edoc.focus();
		return;
	}
	if(frmSchCG.type_txrx.value == ""){
		alert("송수신구분을 선택해주세요");
		$('#type_txrx').data('selectpicker').$button.focus();
// 		frm.type_txrx.focus();
		return;
	}
	if(!frmSchCG.validateForm()) return;
	var data = frmSchCG.ajaxSubmit();
	if(data == null) return false;
	vLoad("listFcCodeGroup","<c:url value='/fccode/listFcCode.crz'/>",data);
}
function goSearchExcel() {
	if(frmSchCG.cd_fc.value == ""){
		alert("금융사를 선택해주세요");
		$('#cd_fc').data('selectpicker').$button.focus();
// 		frm.cd_fc.focus();
		return;
	}
	if(frmSchCG.no_edoc.value == ""){
		alert("전문번호를 선택해주세요");
		$('#no_edoc').data('selectpicker').$button.focus();
		// 		frm.no_edoc.focus();
		return;
	}
	if(frmSchCG.type_txrx.value == ""){
		alert("송수신구분을 선택해주세요");
		$('#type_txrx').data('selectpicker').$button.focus();
// 		frm.type_txrx.focus();
		return;
	}
	if(!frmSchCG.validateForm()) return;
	var data = frmSchCG.ajaxSubmit();
	if(data == null) return false;
    frmSchCG.action = "/fccode/listFcCodeExcel.crz";
    frmSchCG.submit();
}
// 상세금융사전문별 항목 검색
function goSearchCD() {
	if(!frmSchCD.validateForm()) return;
	var data = frmSchCD.ajaxSubmit();
	if(data == null) return false;
	vLoad("listFcCodeDetail","<c:url value='/fccode/listFcCode.crz'/>",data);
}
// 금융사전문별 항목그룹 정보 조회
function loadFcCodeGroup(cd_fc, no_edoc, code_group, item_tag, type_txrx, obj) {
	defaultSetting('sch_cd_sel','code_group');
	//$("#sch_cd_sel").selectpicker("val", "code_group")
	var data = {"cd_fc":cd_fc,"no_edoc":no_edoc,"code_group":code_group, "type_txrx":type_txrx, "item_tag":item_tag, "yn_code_group":"Y"};
	vLoad("formFcCodeGroup","<c:url value='/fccode/getFcCode.crz'/>",data,false);

	frmSchCD.cd_fc.value = cd_fc;
	frmSchCD.no_edoc.value = no_edoc;
	frmSchCD.txt_detail.value = code_group;
	frmSchCD.item_tag.value = item_tag;
	frmSchCD.type_txrx.value = type_txrx;

 	frmFcCodeDetail.cd_fc.value = cd_fc;
	frmFcCodeDetail.no_edoc.value = no_edoc;
	frmFcCodeDetail.code_group.value = code_group;
	frmFcCodeDetail.type_txrx.value = type_txrx;
	//frmFcCodeDetail.item_tag_value.value = item_tag;
 	// 초기화
 	if(code_group != '') {
		goSearchCD();
		loadFcCodeDetailItemTag(cd_fc, no_edoc, code_group, item_tag, type_txrx);
	}
	window.setupValidateForm( frmFcCodeGroup );
// 	window.setupValidateForm( frmFcCodeDetail );
	//tr 선택시 색상 변경 추가
	var table = document.getElementById("tbl_listFcCodeGroup");
 	var tr = table.getElementsByTagName("tr");
 	for(var i=0; i<tr.length; i++){
 		tr[i].style.background = "white";
 	}
 	obj.style.backgroundColor = "#e0effc";
	//tr 선택시 색상 변경  end
}
function loadFcCodeDetailItemTag(cd_fc, no_edoc, code_group, item_tag, type_txrx) {
	var data = {"cd_fc":cd_fc,"no_edoc":no_edoc,"code_group":code_group,"item_tag":item_tag, "type_txrx":type_txrx};
	vLoad("formFcCodeDetail","<c:url value='/fccode/setFcCode.crz'/>",data,false);
}
// 상세금융사전문별 항목 정보 조회
function loadFcCodeDetail(cd_fc, no_edoc, code_group, code_value, default_group, type_txrx, item_tag) {
	
	var data = {"cd_fc":cd_fc,"no_edoc":no_edoc,"code_group":code_group,"code_value":code_value, "yn_code_group":"N", "type_txrx":type_txrx};
	vLoad("formFcCodeDetail","<c:url value='/fccode/getFcCode.crz'/>",data,false);
	// 초기화
	if(cd_fc == '') {
		frmFcCodeDetail.cd_fc.value = "";
	}
	if(no_edoc == '') {
		frmFcCodeDetail.no_edoc.value = "";
	}
	if(code_group == '') {
		frmFcCodeDetail.code_group.value = default_group;
	}
	window.setupValidateForm( frmFcCodeDetail );
}
// 엔터검색
function isEnter(keyFcCode, type) {
	if( keyFcCode == 13 ) {
		if( type == "code_group" ) {
			goSearchCG();
			//frmSchCG.txt_detail.blur();
		} else if( type == "code_detail" ) {
			goSearchCD();
			frmSchCD.txt_detail.blur();
		}
	}
}
function procFcCodeInfo(frm, type) {

	if(type == 'G') {
		frm.parent_code_group.value = frm.item_repeat_tag.value;
		window.setupValidateForm( frmFcCodeGroup );
	} else if(type == 'D') {
	 	window.setupValidateForm( frmFcCodeDetail );
	}
	
	//frm.parent_code_group.value = frm.item_repeat_tag.value;
	if(!frm.validateForm()) return false;
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/fccode/procFcCodeInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			if(parseInt(returnData.cd_result,10) == 0) {
				if('G' == type) {
					//defaultSetting('sch_cg_sel','code_group');
					//frmSchCG.txt_detail.value = frm.code_group.value;
					goSearchCG();
					//loadFcCodeDetailItemTag(cd_fc, no_edoc, code_group, item_tag);
					loadFcCodeDetailItemTag(frm.cd_fc.value, frm.no_edoc.value, frm.code_group.value, frm.item_tag.value, frm.type_txrx.value);
					goSearchCD();
				} else {
					defaultSetting('sch_cd_sel','code_group');
					frmSchCD.txt_detail.value = frm.code_group.value;
					goSearchCD();
				}
			}
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
function delFcCodeInfo(frm, type) {
	if(confirm("삭제하시겠습니까?") == false) return false;
	if(!frm.validateForm()) return false;
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/fccode/delFcCodeInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			if(parseInt(returnData.cd_result,10) == 0) {
				if('G' == type) {
 					//defaultSetting('sch_cg_sel','code_group');
					//frmSchCG.txt_detail.value = frm.code_group.value;
					goSearchCG();
				} else {
					defaultSetting('sch_cd_sel','code_group');
					frmSchCD.txt_detail.value = frm.code_group.value;
					goSearchCD();
				}
			}
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}
function clearCacheFcCode() {
	$.ajax({
		url : "<c:url value='/fccode/clearCacheFcCode.json'/>",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success :  function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
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
		url : "<c:url value='/fccode/listNmFcEdoc.json'/>",
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

function updateFcCodeSeq() {
	if(frmSchCG.cd_fc.value == ""){
		alert("금융사를 선택해주세요");
		$('#cd_fc').data('selectpicker').$button.focus();
// 		frm.cd_fc.focus();
		return;
	}
	if(frmSchCG.no_edoc.value == ""){
		alert("전문을 선택해주세요");
		$('#no_edoc').data('selectpicker').$button.focus();
// 		frm.no_edoc.focus();
		return;
	}
	if(frmSchCG.type_txrx.value == ""){
		alert("송수신구분을 선택해주세요");
		$('#type_txrx').data('selectpicker').$button.focus();
// 		frm.type_txrx.focus();
		return;
	}
	var rowCount = $('#tbl_listFcCodeGroup tbody tr').length;
	if(rowCount <= 1){
		alert("조회된 리스트가 없습니다.")
		return false;
	}
	
	if(confirm("변경 하시겠습니까?") == false) return false;
	
	var obj = new Object();
	var arr = new Array();

	for(var i = 0 ; i < rowCount; i++){
			obj.cd_fc = $('#tbl_listFcCodeGroup tbody tr').eq(i).find("td").eq(2).html();
			obj.no_edoc = $('#tbl_listFcCodeGroup tbody tr').eq(i).find("td").eq(3).html();
			if($('#tbl_listFcCodeGroup tbody tr').eq(i).find("td").eq(4).html() == "송신"){
				obj.type_txrx = "TX";
			} else{
				obj.type_txrx = "RX";
			}
			obj.code_group = $('#tbl_listFcCodeGroup tbody tr').eq(i).find("td").eq(5).html();
			obj.seq_order = i+1;
			obj.parent_code_group = $('#tbl_listFcCodeGroup tbody tr').eq(i).find("td").eq(10).html();
			arr.push(obj);
			obj = new Object();
	}
			var jsonData = JSON.stringify(arr);
			$.ajax({
				url : "<c:url value='/fccode/updateFcCodeSeq.json'/>",
				data : jsonData,
				dataType : "json",
				contentType : "application/json",
				type : "POST",
				success : function() {
					alert('정상적으로 저장되었습니다.');
					goSearchCG();
				},
				error : function(e) {
					
					alert(e.responseText);
				},
				clearForm: true,
		        resetForm: true
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
			<h1 class="pull-left">금융사 전문별 데이터항목관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">전문관리</a></li>
				<li class="active">금융사 전문별 데이터항목관리</li>
			</ol>
		</div>
		<!--// title -->
	<!-- Content -->
		<div class="srch align-l">
			<form name="frmSchCG" onsubmit="return false;">
			<input type="hidden" name="yn_code_group" value="Y"/>
			<select class="selectpicker" id="cd_fc" name="cd_fc" onchange="ChangeSelectBox(document.frmSchCG, this)" data-style="btn-primary">
				${ufn:makeFincorpOptions('금융사', '', 'type', '')}
			</select>
			<select class="selectpicker" name="no_edoc" id="no_edoc" data-style="btn-primary">
				<option value="">전문번호</option>
			</select>
			<select class="selectpicker"name="type_txrx"  id="type_txrx" data-style="btn-primary">
						${ufn:makeOptions("cd_type_txrx","송수신구분", "")}
			</select>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchExcel();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 엑셀</button>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchCG();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			<button type="button" class="btn btn-primary btn-xs" onclick="resetForm(document.frmSchCG);"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
			<button type="button" class="btn btn-warning btn-xs" onclick="clearCacheFcCode();"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span> 적용</button>
			</form>
		</div>
	
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 금융사전문별 항목그룹</a>
				</h3>
			</div>
			<div id="formFcCodeGroup" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/fccode/formFcCodeGroup.jsp"%>
			</div>
		</div>
		
		<div id="listFcCodeGroup" class="d-table">
			<%@ include file="/WEB-INF/views/fccode/listFcCodeGroup.jsp"%>
		</div>
		
	<!-- //Content -->
	</div>
</div>
<!--// Layout-West -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<!-- Layout-Content -->
	<div class="ui-layout-content">
<!-- 		title -->
		<div class="h-title">
			<h1 class="pull-left">금융사 전문별 데이터항목관리 상세</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">전문관리</a></li>
				<li class="active">금융사 전문별 데이터항목관리 상세</li>
			</ol>
		</div>
<!-- 		// title -->
		<div class="srch align-l">
			<form name="frmSchCD">
			<input type="hidden" name="yn_code_group" value="N"/>
			<input type="hidden" name="item_tag" value=""/>
			<input type="hidden" name="cd_fc" value=""/>
			<input type="hidden" name="no_edoc" value=""/>
			<input type="hidden" name="type_txrx" value=""/>
			<select class="selectpicker" name="sel_detail" id="sch_cd_sel" data-style="btn-primary">
				<option value="">상세검색</option> 
				<option value="cd_fc">금융사명</option> 
				<option value="no_edoc">전문번호</option> 
				<option value="code_group">항목그룹ID</option> 
				<option value="code_value">항목ID</option> 
				<option value="nm_code">항목명</option> 
				<option value="etc">설명</option> 
			</select>
			<input type="text" class="form-control" name="txt_detail" validate="required;label:검색문자열;" onclick="defaultSetting('sch_cd_sel','code_group');" onkeydown="isEnter(event.keyFcCode, 'code_detail');" maxlength="100"/>
			<button type="button" class="btn btn-primary btn-xs" onclick="goSearchCD();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 검색</button>
			<button type="button" class="btn btn-primary btn-xs" onclick="resetForm(document.frmSchCD);"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
			</form>
		</div>

		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">
					<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 금융사전문별 항목상세</a>
				</h3>
			</div>
			<div id="formFcCodeDetail" class="panel-collapse toggle-cont">
				<%@ include file="/WEB-INF/views/fccode/formFcCodeDetail.jsp"%>
			</div>
		</div>
		
		<div id="listFcCodeDetail" class="d-table">
			<%@ include file="/WEB-INF/views/fccode/listFcCodeDetail.jsp"%>
		</div>

	
	</div>
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->

<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>