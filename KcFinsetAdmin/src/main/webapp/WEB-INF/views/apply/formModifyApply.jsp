<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		//datepicker
		$('.input-daterange').datepicker({
			format: "yyyymmdd", //dateformat
			todayHighlight: true, //당일 표시
			todayBtn: "linked", //today button link 활성화
			autoclose: true, //달력 자동 닫힘
			beforeShowDay : nationalDays
		});
		
		$('.selectpicker').selectpicker();
		
		window.setupValidateForm( frmApplyJudge );
	});
	
	function modifyApplyJudge(){
		
		if ( !frmApplyJudge.validateForm() ) return false;
		
		var data = frmApplyJudge.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/apply/modifyApplyJudge.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				loadPrepareForm();
				loadPrepareTab('HIST');
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	function showDivApproval(){
		if(frmApplyJudge.yn_update_approval.checked) {
			$("#display_ymd_approval").show();
			
		} else {
			$("#display_ymd_approval").hide();
			
		}
	}
	
</script>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="h-sec">심사처리</h3>
	</div>
	<form id="frmApplyJudge" name="frmApplyJudge">
		<input type="hidden" name="no_apply" value="${List.no_apply}">
		<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
					<col width="90px">
					<col width="*">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>상품명</th>
						<td colspan="2">
							<select name="cd_goods" class="selectpicker">
								${ufn:makeGoodsOptions("상품명", List.cd_goods, "NM")}
							</select>
						</td>
					</tr>
					<tr>
						<th>상태</th>
						<td colspan="2">
							${ufn:makeRadioAndCheckBoxs("cd_apply_doc_box", "cd_apply_doc_box", "radio", List.cd_apply_doc_box, 0)}
						</td>
					</tr>
					<tr>
						<th>승인일변경</th>
						<td>
				   			<input type="checkbox" name="yn_update_approval" id="yn_update_approval" onclick="showDivApproval();"/><label for="yn_update_approval" class="checkbox-inline"> 승인일 변경 </label>
						</td>
						<td>
				   			<div id="display_ymd_approval" style="display:none; ">
								<div class="input-daterange input-group date-w">
					   				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
								    <input type="text" class="input-sm form-control" name="ymd_approval" id="ymd_approval" value="${List.ymd_approval}"/>
					   			</div>
				   			</div>
						</td>
					</tr>
					<tr>
						<th>승인금액</th>
						<td colspan="2">
							<div class="input-group">
								<span class="input-group-addon">￦</span>
								<input type="text" class="form-control width-120" name="amt_approval" value="${List.amt_approval}" validate="label:승인금액;money;"/>
							</div> 만원
						</td>
					</tr>
					<tr>
						<th>사유</th>
						<td colspan="2">
							<textarea class="form-control w100 h50" name="memo_apply" validate="label:사유; maxbt:4000;" maxlength="4000">${List.memo_from_apply}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="align-c mg-bt10">
	<button type="button" class="btn btn-default btn-xs" onclick="modifyApplyJudge();">수정</button>
</div>