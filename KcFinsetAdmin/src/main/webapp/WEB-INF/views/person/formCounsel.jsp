<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		$('.selectpicker').selectpicker();
		//datepicker
		$('.input-daterange').datepicker({
			format: "yyyymmdd", //dateformat
			todayHighlight: true, //당일 표시
			todayBtn: "linked", //today button link 활성화
			autoclose: true, //달력 자동 닫힘
			beforeShowDay : nationalDays
		});
		
		window.setupValidateForm( frmCounselInfo );
		
		//상담메모 수정시 알람사용 disabled
		if('${counselVO.seq_counsel}' != ""){
			$("input[id=chk_alarm]").attr("disabled",true);
		}
	});
	
	function listCounselHist(no_person, no_prepare) {
		var data = {"no_person":no_person, "no_prepare":no_prepare};
		vLoad("counselHist", "<c:url value='/counsel/listCounselHist.crz'/>", data, false);
		
		window.setupValidateForm( frmCounselInfo );  
	} 
	
	function procCounselHist() {
		
		alert("준비중입니다.");
// TO-DO 2017/08/31 상담톡때문에 우선 주석 처리 		
// 		var frm = document.frmCounselInfo;
		
// 	    if(!frm.validateForm()) return false;
		
// 	    if($('input:checkbox[id="chk_alarm"]').is(":checked")){
// 	    	if(frm.ymd_send.value == "" || frm.hh_send.value == "" || frm.mm_send.value == ""){
// 	    		alert("알람일시를 정확히 입력해주세요.");
// 	    		return false;
// 	    	} 
// 		}
	    
// 		var data = frm.ajaxSubmit();
// 		if(data == null) return false; 
		
// 		$.ajax({
// 			url : "<c:url value='/counsel/procCounselHist.json'/>",
// 			data : data,
// 			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
// 			type : "POST",
// 			async : false,
// 			success : function (result) {
// 				var returnDataCounsel = result.returnDataCounsel;
// 				if($('input:checkbox[id="chk_alarm"]').is(":checked")){
// 					var returnDataAlarm = result.returnDataAlarm;
// 					alert(returnDataCounsel.message +"\n\n"+returnDataAlarm.message);
					
// 					if(parseInt(returnDataCounsel.cd_result,10) == 0 && parseInt(returnDataAlarm.cd_result,10) == 0) {
// 						initCounselForm(frm.no_person.value);
// 						listCounselHist(frm.no_person.value);
// 					} 
// 				}else{
// 					alert(returnDataCounsel.message);
					
// 					if(parseInt(returnDataCounsel.cd_result,10) == 0) {
// 						initCounselForm(frm.no_person.value);
// 						listCounselHist(frm.no_person.value);
// 					} 
// 				}
// 			},
// 			error : function (e) {
// 				alert(e.responseText);
// 			}
// 		});
	}
	
	function delCounselHist() {
		alert("준비중입니다.");
// TO-DO 2017/08/31 상담톡때문에 우선 주석 처리 			
// 		var frm = document.frmCounselInfo;
		
// 	    if(!frm.validateForm()) return false;
		
// 		var data = frm.ajaxSubmit();
// 		if(data == null) return false; 
		
// 		$.ajax({
// 			url : "<c:url value='/counsel/delCounselHist.json'/>",
// 			data : data,
// 			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
// 			type : "POST",
// 			async : false,
// 			success : function (result) {
// 				var returnData = result.returnData;
// 				alert(returnData.message);
				
// 				if(parseInt(returnData.cd_result,10) == 0) {
// 					listCounselHist(frm.no_person.value);
// 				} 
// 				initCounselForm(frm.no_person.value);
// 			},
// 			error : function (e) {
// 				alert(e.responseText);
// 			}
// 		});
// 	}
	
// 	function initCounselForm(no_person, no_prepare) {
// 		var data = {"no_person":no_person, "no_prepare":no_prepare}
// 		vLoad("formCounsel","<c:url value='/counsel/initCounselForm2.crz'/>",data);
// 	}
	
// 	function loadAlarmData() {
// 		if($('input:checkbox[id="chk_alarm"]').is(":checked")){
// 			$("#alarmData").show();
// 		}else {
// 			$("#alarmData").hide();
// 			frmCounselInfo.ymd_send.value = "";
			
// 		}
	}
</script>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">상담메모</h3>
		
		<span class="pull-right">
			<button type="button" class="btn btn-default btn-xs" onclick="procCounselHist();">등록</button>
			<button type="button" class="btn btn-default btn-xs" onclick="procCounselHist();">수정</button>
			<button type="button" class="btn btn-default btn-xs" onclick="delCounselHist();">삭제</button>
		</span>
	</div>
	<form id="frmCounselInfo" name="frmCounselInfo">
		<input type="hidden" name="no_person" value="${!empty counselVO.no_person ? counselVO.no_person:no_person}">
		<input type="hidden" name="seq_counsel" value="${counselVO.seq_counsel}">
		<input type="hidden" name="no_apply" value="${counselVO.no_apply}">
		<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
					<col width="15%">
					<col width="85%">
				</colgroup>
				<tbody>
					<tr>
						<th>구분</th>
						<td>
							<select class="selectpicker" name="cd_counsel_class" validate="required; label:구분;">
								${ufn:makeOptions("cd_counsel_class","상담구분", (empty counselVO.cd_counsel_class? ufn:getNvlCodeName('_CONF_SYSTEM','CD_DEFAULT_COUNSEL','10') :counselVO.cd_counsel_class))}
							</select>
						</td>
					</tr>
					<tr>
						<th>상담내용</th>
						<td colspan="5"><textarea class="form-control w100 h50" name="etc_counsel" validate="required; label:상담내용; maxbt:4000;" maxlength="4000">${counselVO.etc_counsel}</textarea></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>