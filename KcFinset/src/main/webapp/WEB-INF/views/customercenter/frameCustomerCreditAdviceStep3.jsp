<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	//toggleSelect
	$('#yn_wedding').change(function(event) {
        toggleSelect("cd_family_cnt");
    });
    $('#cd_family_cnt').on('change', function(event) {
        toggleSelect("cd_living");
    });	
    $('#cd_living').on('change', function(event) {
        toggleSelect("cd_job");
    });	
    $('#cd_job').on('change', function(event) {
        $("#amt_mm_income").focus(); 
    });	
    
    //numberic maxlength체크
    //월소득
    $('#amt_mm_income').on('keyup', function (e) {        
        e.preventDefault();
        $('#amt_mm_income').val(returnData($('#amt_mm_income').val()));    
    });
    //월지출
    $('#amt_mm_expense').on('keyup', function (e) {        
        e.preventDefault();
        $('#amt_mm_expense').val(returnData($('#amt_mm_expense').val()));    
    });
});

//numberic maxlength 체크
function returnData(data) {    
    if (data.length < 11) {        
        return data;    
    } else {        
        //return data.substring(0, data.length - 1); // 익스플로러에서는 제대로 동작안함...;;
        return data.substring(0, 10);
	}
}

//신용상담 신청
function createCreditAdvice(){
	if (!frmCreditAdviceStep3.validateForm()) return false;
	frmCreditAdviceStep3.action = "<c:url value='/m/customercenter/frameCustomerCreditAdviceStep4.crz'/>";
	frmCreditAdviceStep3.submit();
}
</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>신용상담신청</h1>
		</div>
	</header>
	<!-- Content -->
	<form method="post" name="frmCreditAdviceStep3" id="frmCreditAdviceStep3">
	
	<section id="content">
		<div class="container">
			<!-- 추가정보입력 -->
			<div class="form-inline">
				<div class="form-group">
					<label>결혼여부</label>
					<select name="yn_wedding" id="yn_wedding" class="selectpicker" data-header="결혼여부선택" validate="required; label:결혼여부;">
						<option value="">결혼여부선택</option>
						<option value="N">미혼</option>
						<option value="Y">기혼</option>
					</select>
				</div>
				<div class="form-group">
					<label>부양가족</label>
					<select name="cd_family_cnt" id="cd_family_cnt" class="selectpicker" data-header="부양가족선택" validate="required; label:부양가족;">
						${ufn:makeOptions("cd_family_cnt","부양가족선택", "")}
					</select>
				</div>
				<div class="form-group">
					<label>주거형태</label>
					<select name="cd_living" id="cd_living" class="selectpicker" data-header="주거형태선택" validate="required; label:주거형태;">
						${ufn:makeOptions("cd_living","주거형태선택", "")}
					</select>
				</div>
				<div class="form-group">
					<label>직업</label>
					<select name="cd_job" id="cd_job" class="selectpicker" data-header="직군선택" validate="required; label:직군;">
						${ufn:makeOptions("cd_job","직군선택", "")}
					</select>
				</div>
				<div class="form-group">
					<label>월소득</label>
					<input type="number" name="amt_mm_income" id="amt_mm_income" class="form-control" validate="required; label:월소득;numeric;">
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
				<div class="form-group">
					<label>월지출</label>
					<input type="number" name="amt_mm_expense" id="amt_mm_expense" class="form-control" validate="required; label:월지출;numeric;">
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
			</div>   
		</div>
		<div class="btn-fixed-bottom affix-bottom" id="next_div">
			<a onclick="createCreditAdvice();" role="button" class="btn btn-lg btn-primary btn-block">신청하기</a>
		</div>
	</section>
	
	</form>
	<!-- //Content -->
</div>
</body>
</html>
