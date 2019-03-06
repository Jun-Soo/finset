<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script>

	$(document).ready(function(){
		// SelectPicker
		
		$('.selectpicker').selectpicker();
		
		window.setupValidateForm( frmCommissionDetails );

	    var proc = { 
	        success : function (result) {
				if(result.result == "00"){
					initForm();
					loadCommissionList();
				}else if(result.result == "01"){
					alert("수정에 실패하였습니다.")
				}
				
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
	        url: "<c:url value='/commission/procCommissionDetails.json'/>",
	        type: "POST"
	    }; 
	 
	    $("#procCommissionDetails").click(function(){
	    	if ( !frmCommissionDetails.validateForm() ) return false;
	    	
			$("#frmCommissionDetails").ajaxSubmit(proc); 
			return false; 
	    }); 
	    
	});
	
	function delCommissionDetails() {
		if(confirm("삭제하시겠습니까?") == false) return false;
	 	if (!frmCommissionDetails.validateForm()) return false;
		var data = {
			"no_apply" : $('#no_apply').val()
		};
		if (data == null)
			return false;

		$.ajax({
			url : "<c:url value='/commission/delCommissionDetails.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function(result) {
				var returnData = result.returnData;
				alert(returnData.message);
				initForm();
				loadCommissionList();
			},
			error : function(e) {
				alert(e.responseText);
			}
		});
	}
</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">수수료 상세정보</a>
		</h3>
		<c:if test="${!empty commissionInfo.no_apply || !empty no_apply}">
		<span class="pull-right">
			<button type="button" class="btn btn-primary btn-xs" onclick="initForm();">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			
			<button type="button" class="btn btn-default btn-xs" id="procCommissionDetails">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
			</button>
			
			<button type="button" class="btn btn-warning btn-xs" onclick="delCommissionDetails();">
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제
			</button>
		</span>
		</c:if>
	</div>
	<div class="panel-collapse toggle-cont">
		<form name="frmCommissionDetails" id="frmCommissionDetails" enctype="multipart/form-data">
			<c:choose>
				<c:when test="${empty no_apply }">
					<input type="hidden" name="no_apply" id="no_apply" value="${commissionInfo.no_apply}">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="no_apply" id="no_apply" value="${no_apply}">
				</c:otherwise>
			</c:choose>
			
			<div class="panel-collapse">
				<table class="table table-classic">
					<colgroup>
						<col width="25%">
						<col width="25%">
						<col width="25%">
						<col width="25%">
					</colgroup>
					<tbody>
					 	<tr>
					 		<th>접수번호</th>
					 		<td colspan="3">
					 			<c:choose>
									<c:when test="${empty no_apply }">
										${commissionInfo.no_apply}
									</c:when>
									<c:otherwise>
										${no_apply}
									</c:otherwise>
								</c:choose>
					 		</td>
					 	</tr>
						<tr>	
							<th>예상수수료율</th>
							<td>
								<input type="text" class="form-control" name="rto_exp_loan" value="${commissionInfo.rto_exp_loan}"   id="time_start" validate="required; label:예상수수료율;"  maxlength="5">
							</td>
							<th>예상수수료</th>
							<td>
								<input type="text" class="form-control" name="amt_exp_commission" value="${commissionInfo.amt_exp_commission}"   id="time_start" validate="required; label:예상수수료;"  maxlength="8">
							</td>
						</tr>
						<tr>	
							<th>지급수수료율</th>
							<td>
								<input type="text" class="form-control" name="rto_real_loan" value="${commissionInfo.rto_real_loan}"   id="time_start" validate="required; label:지급수수료율;"  maxlength="5">
							</td>
							<th>지급수수료</th>
							<td>
								<input type="text" class="form-control" name="amt_real_commission" value="${commissionInfo.amt_real_commission}"   id="time_start" validate="required; label:지급수수료;" maxlength="8">
							</td>
						</tr>
						<tr>
							<th>집행여부</th>
							<td colspan="3">
								<select id="yn_exe" name="yn_exe" class="selectpicker" validate="required;select-one;label:집행여부">
										${ufn:makeOptions("yn_yes","선택", commissionInfo.yn_exe)}
								</select>
							</td>
						</tr>
						<tr>
							<th>메모</th>
							<td colspan="3"><textarea class="form-control w100 h150" id="memo" name="memo" validate=" label:내용; maxbt:2000;" maxlength="2000">${commissionInfo.memo}</textarea></td>
						</tr>
				</tbody>
			</table>	
			</div>
		</form>
	</div>
</div>
