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
	
	function listCounselInfo(no_person, no_prepare) {
		var data = {"no_person":no_person, "no_prepare":no_prepare};
		vLoad("listCounselInfo", "<c:url value='/counsel/listCounselInfo.crz'/>", data, false);
		
		window.setupValidateForm( frmCounselInfo );  
	} 
	
	function procCounselInfo() {
		
		var frm = document.frmCounselInfo;
		
	    if(!frm.validateForm()) return false;
		
	    if($('input:checkbox[id="chk_alarm"]').is(":checked")){
	    	if(frm.ymd_send.value == "" || frm.hh_send.value == "" || frm.mm_send.value == ""){
	    		alert("알람일시를 정확히 입력해주세요.");
	    		return false;
	    	} 
		}
	    
		var data = frm.ajaxSubmit();
		if(data == null) return false; 
		
		$.ajax({
			url : "<c:url value='/counsel/procCounselInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnDataCounsel = result.returnDataCounsel;
				
				if($('input:checkbox[id="chk_alarm"]').is(":checked")){
					var returnDataAlarm = result.returnDataAlarm;
					alert(returnDataCounsel.message +"\n\n"+returnDataAlarm.message);
					
					if(parseInt(returnDataCounsel.cd_result,10) == 0 && parseInt(returnDataAlarm.cd_result,10) == 0) {
						initCounselForm(frm.no_person.value, frm.no_prepare.value);
						listCounselInfo(frm.no_person.value, frm.no_prepare.value);
					} 
				}else{
					alert(returnDataCounsel.message);
					
					if(parseInt(returnDataCounsel.cd_result,10) == 0) {
						initCounselForm(frm.no_person.value, frm.no_prepare.value);
						listCounselInfo(frm.no_person.value, frm.no_prepare.value);
					} 
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	function delCounselInfo() {
		
		var frm = document.frmCounselInfo;
		
	    if(!frm.validateForm()) return false;
		
		var data = frm.ajaxSubmit();
		if(data == null) return false; 
		
		$.ajax({
			url : "<c:url value='/counsel/delCounselInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				
				if(parseInt(returnData.cd_result,10) == 0) {
					listCounselInfo(frm.no_person.value, frm.no_prepare.value);
				} 
				initCounselForm(frm.no_person.value, frm.no_prepare.value);
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	function initCounselForm(no_person, no_prepare) {
		var data = {"no_person":no_person, "no_prepare":no_prepare}
		vLoad("formCounsel","<c:url value='/counsel/initCounselForm.crz'/>",data);
	}
	
	function loadAlarmData() {
		if($('input:checkbox[id="chk_alarm"]').is(":checked")){
			$("#alarmData").show();
		}else {
			$("#alarmData").hide();
			frmCounselInfo.ymd_send.value = "";
			
		}
	}
</script>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">상담메모</h3>
		
		<span class="pull-right">
			<button type="button" class="btn btn-default btn-xs" onclick="procCounselInfo();">등록</button>
			<button type="button" class="btn btn-default btn-xs" onclick="procCounselInfo();">수정</button>
			<button type="button" class="btn btn-default btn-xs" onclick="delCounselInfo();">삭제</button>
		</span>
	</div>
	<form id="frmCounselInfo" name="frmCounselInfo">
		<input type="hidden" name="no_person" value="${!empty prepareVO.no_person ? prepareVO.no_person:counselVO.no_person}">
		<input type="hidden" name="seq_counsel" value="${counselVO.seq_counsel}">
		<input type="hidden" name="no_apply" value="${counselVO.no_apply}">
		<input type="hidden" name="no_prepare" value="${!empty prepareVO.no_prepare ? prepareVO.no_prepare:counselVO.no_prepare}">
		<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
					<col width="13%">
					<col width="24%">
					<col width="10%">
					<col width="22%">
					<col width="13%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>구분</th>
						<td>
							<select class="selectpicker" name="cd_counsel_class" validate="required; label:구분;">
								${ufn:makeOptions("cd_counsel_class","상담구분", (empty counselVO.cd_counsel_class? ufn:getNvlCodeName('_CONF_SYSTEM','CD_DEFAULT_COUNSEL','10') :counselVO.cd_counsel_class))}
							</select>
						</td>
						<th>대면</th>
						<td>${ufn:makeRadioAndCheckBoxs("yn_meet", "yn_meet", "radio", counselVO.yn_meet, 0)}</td>
						<th>바운딩</th>
						<td>${ufn:makeRadioAndCheckBoxs("cd_contact", "cd_contact", "radio", counselVO.cd_contact, 0)}</td>
					</tr>
					<%-- <tr>
						<th>대면여부</th>
						<td>${ufn:makeRadioAndCheckBoxs("yn_meet", "yn_meet", "radio", counselVO.yn_meet, 0)}</td>
					</tr>
					<tr>
						<th>바운딩</th>
						<td>${ufn:makeRadioAndCheckBoxs("cd_contact", "cd_contact", "radio", counselVO.cd_contact, 0)}</td>
					</tr> --%>
					<tr>
						<c:choose>
							<c:when test="${ufn:getCodeName('_CONF_ALARM', 'YN_USE') ne 'Y'}">
								<th>접촉방법</th>
								<td colspan="5">
									${ufn:makeRadioAndCheckBoxs("cd_counsel_method", "cd_counsel_method", "radio", counselVO.cd_counsel_method, 0)}
								</td>
							</c:when>
							<c:otherwise>
								<th>접촉방법</th>
								<td colspan="3">
									${ufn:makeRadioAndCheckBoxs("cd_counsel_method", "cd_counsel_method", "radio", counselVO.cd_counsel_method, 0)}
								</td>
								<th>알람사용</th>
								<td>
									<input type="checkbox" name="chk_alarm" id="chk_alarm" onclick="loadAlarmData();"><label for="chk_alarm">사용</label>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr id="alarmData" name="alarmData" style="display: none;">
						<th>알람일시</th>
						<td>
							<div class="input-daterange input-group date-w">
				   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							    <input type="text" class="input-sm form-control" name="ymd_send" id="ymd_send" value="${alarmVO.ymd_send}" validate="date; label:알람일자;"/>
				   			</div>
				   		</td>
				   		<td>
						    <select name="hh_send" id="hh_send" class="selectpicker" validate="label:알람시;" >
								<option value="">시</option>
								<option value="08">08</option>
								<option value="09">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
							</select>
						</td>
						<td colspan="3">
						    <select name="mm_send" id="mm_send" class="selectpicker" validate="label:알람분;">
								<option value="">분</option>
								<option value="00">00</option>
								<option value="00">05</option>
								<option value="10">10</option>
								<option value="10">15</option>
								<option value="20">20</option>
								<option value="20">25</option>
								<option value="30">30</option>
								<option value="30">35</option>
								<option value="40">40</option>
								<option value="40">45</option>
								<option value="50">50</option>
								<option value="50">55</option>
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