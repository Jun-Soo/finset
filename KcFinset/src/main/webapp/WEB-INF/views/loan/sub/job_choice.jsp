<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script type="text/javascript">

var arr = [];
var bool = false;
var jobCd = "";
var onCreate = true;

$(document).ready(function() {
	makeJobOptions("1");
	
});




function makeJobOptions(index){
	
	var goods_code = $("#_apply_type").val();
	var nonOwnerCode = ["04", "09", "01", "08", "14", "15"];
	var jobCd;
	var jobNm;
	
	if(index == 1) {
		jobCd = "00000";
		jobNm = "";
	} else{
		jobCd = $("select[name=cd_job_"+(Number(index)-1)+"]").val();
		jobNm = $('#cd_job_'+index).html();
	}
	
	formJob.code.value = jobCd;
	formJob.index.value = index-1;
	arr[index-1] = jobNm;
	bool = false;
	window.setupValidateForm( formJob );
	
	for(var i = 1; i < 6; i++){
		if(i >= index){
			$("#cd_job_" + i).children().remove();
		}
	}
// 	alert(formJob.serialize());
	$("#cd_job_" + index).append("<option value=''>선택</option>");
	$.ajax({
		url : "/m/loan/makeJobOptions.crz",
		type : "post",
		dataType : "json",
		data : formJob.serialize(),
		async : false,
		success : function(data){
// 			alert(data.data.length);
			for(var i = 0; i < data.data.length; i ++){
				if(index == 0 && Number(nonOwnerCode.indexOf(goods_code)) > -1){
					if(data.data[i].job_cd == '20000'){
						continue;
					}
				}
				$("#cd_job_" + index).append("<option value='" + data.data[i].job_cd + "'>" + data.data[i].job_nm + "</option>");
			}
			
			if(onCreate) {
				if(frmloanApplyStep.loan_code.value == '01') {
					//10000 -> 직장인
					$("#cd_job_1").val("10000").prop("selected", true);
					onCreate = false;
					makeJobOptions("2");
				} else if (frmloanApplyStep.loan_code.value == '02'){
					 //20000 -> 자영업
					$("#cd_job_1").val("20000").prop("selected", true);
					onCreate = false;
					makeJobOptions("2");
				} else if (frmloanApplyStep.loan_code.value == '03' ||frmloanApplyStep.loan_code.value == '04'){
					if(frmloanApplyStep.income_info.value == '01') {
						$("#cd_job_1").val("10000").prop("selected", true);
					} else if (frmloanApplyStep.income_info.value == '02'){
						$("#cd_job_1").val("20000").prop("selected", true);
					}
					onCreate = false;
					makeJobOptions("2");
				}
			}
		},
		error:function(request,status,error){
            errMsg(e);
        }

	});
	formJob.code.value = "";
	
}

function selectJob(){
	var jobName = "";
	for(var i=0;i<arr.length;i++){
		
		if(i  == arr.length-1){
			jobName = jobName + arr[i];	
		}
		else{
			jobName = jobName + arr[i] + "<";
		}
	}
	$("#jobCode1").val(jobName);
	
	if($("#cd_job_5").val() ==  "" || $("#cd_job_5").val() ==  null ){
		alert("직업이 선택되지 않았습니다.");
		return;
	}
	
	$("#cd_job_class_l").val($("#cd_job_2").val());
	$("#cd_job_class_m").val($("#cd_job_3").val());
	$("#cd_job_class_s").val($("#cd_job_4").val());
	$("#cd_duty_comp").val($("#cd_job_5").val());
	$("#job").val($("#cd_job_2 option:selected").text()+"/"+$("#cd_job_3 option:selected").text()+"/"+$("#cd_job_4 option:selected").text());
	 $("#popModal").modal("hide");
}
</script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">직업선택</h4>
</div>
<div class="modal-body">
<h2 class="h2">직업/업종 선택</h2>
 	<form id="formJob" name="formJob" onsubmit="return false;">
		 <input type="hidden" name="code" value="" />
	  	<input type="hidden" name="index" value="" />
	  	<input type="hidden" name="apply_type" value="" />
		<div class="form-box">
			<div class="form-horizontal">
				<div class="form-group">
<!-- 				<label class="control-label">구분</label> -->
				<!-- <select class="form-control" id="cd_job_1" name="cd_job_1" onchange="makeJobOptions(2)"> -->
					<select class="form-control" id="cd_job_1" name="cd_job_1" onchange="makeJobOptions(2)" style="display:none"></select>	
				</div>
				<div class="form-group">
					<label class="control-label">분류</label>
					<select class="form-control" id="cd_job_2" name="cd_job_2" onchange="makeJobOptions(3)" ></select>
					<select class="form-control" id="cd_job_3" name="cd_job_3" onchange="makeJobOptions(4)"></select>
					<select class="form-control" id="cd_job_4" name="cd_job_4" onchange="makeJobOptions(5)"></select>
				</div>
				<div class="form-group">
					<label class="control-label">직급</label>
					<select class="form-control" id="cd_job_5" name="cd_job_5" onchange="makeJobOptions(6)"></select>
				</div>
			</div>		
		</div>
	</form>
</div>
<div class="modal-footer">
	<button class="btn btn-default btn-outline btn-lg btn-block" onclick="selectJob()">선택 완료</button>
</div>