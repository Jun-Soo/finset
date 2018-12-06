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
	$('.selectpicker').selectpicker({});
    /**
     * 다음화면에서 사용하는 쿠키 제거
     */
    setCookie("YN_LOAN_MOUNT","");
    setCookie("CD_LOAN_MOUNT","");
    setCookie("CD_TYPE_PAY"  ,"");
    setCookie("CD_LOAN_TERM" ,"");
    var cd_job_class_l  = getCookie('CD_JOB_CLASS_L' );
    var amt_year_income = getCookie('AMT_YEAR_INCOME');
    var amt_year_sale   = getCookie('AMT_YEAR_SALE'  );
    $('#cd_job_class_l' ).val(cd_job_class_l    );
    $('#amt_year_income').val(amt_year_income   );
    $('#amt_year_sale'  ).val(amt_year_sale     );
    $('#cd_job_class_l' ).selectpicker("refresh");

    if(cd_job_class_l != undefined && cd_job_class_l == '1') {
        $('#worker').show();
        $('#employed').hide();
        $('#amt_year_income').focus();
    } else if(cd_job_class_l != undefined && cd_job_class_l == '2'){
        $('#worker').hide();
        $('#employed').show();
        $('#amt_year_sale').focus();
    }
    ChkButton();
    toggleSelect("cd_job_class_l");
    $('#amt_year_income').on("keyup",function(){
        var val = $('#amt_year_income').val();
        val = parseInt(val);
        if(val > 1000000) {
            toastMsg("연소득은 100억원까지 입력가능합니다.");
            $('#amt_year_income').val(1000000);
        }
    });
    $('#amt_year_sale').on("keyup",function(){
        var val = $('#amt_year_sale').val();
        if(val.length > 8) {
            toastMsg("연매출은 9999억원까지 입력가능합니다.");
            $('#amt_year_sale').val(99990000);
        }
    });
});

function updateTxFc(){
	if ( !frmloanApplyStep.validateForm() ) return false;
	var regNumber = /^[0-9]*$/;
	if($("#cd_job_class_l").val() == '' || $("#cd_job_class_l").val() == null) {
		toastMsg('직업구분을 선택해주세요.');
		return false;
	} else if($("#cd_job_class_l").val() == '1'){
		if($("#amt_year_income").val() == '' || $("#amt_year_income").val() == null) {
			toastMsg('연소득을 입력해주세요.');
			$("#amt_year_income").focus();
			return false;
		} else if(!regNumber.test($('#amt_year_income').val())) {
		    toastMsg('연소득 숫자만 입력해주세요.');
		    $('#amt_year_income').val('');
		    $('#amt_year_income').focus();
		    return false;
		}
		$('#amt_year_sale').val('0');
	} else if($("#cd_job_class_l").val() == '2'){
		if($("#amt_year_sale").val() == '' || $("#amt_year_sale").val() == null) {
			toastMsg('연매출을 입력해주세요.');
			$("#amt_year_sale").focus();
			return false;
		} else if(!regNumber.test($('#amt_year_sale').val())) {
		    toastMsg('연매출 숫자만 입력해주세요.');
		    $('#amt_year_sale').val('');
		    $('#amt_year_sale').focus();
		    return false;
		}
		// $('#amt_year_income').val('0');
	}
	// var data = frmloanApplyStep.ajaxSubmit();
	var data = $("#frmloanApplyStep").serialize();
	if(data == null) return false;
    var cd_job_class_l  = $('#cd_job_class_l' ).val();
    var amt_year_income = $('#amt_year_income').val();
    var amt_year_sale   = $('#amt_year_sale'  ).val();

	setCookie('CD_JOB_CLASS_L' ,cd_job_class_l );
	setCookie('AMT_YEAR_INCOME',amt_year_income);
	setCookie('AMT_YEAR_SALE'  ,amt_year_sale  );
    if(userAgent == "Android") {
        window.Android.loding('Y');
    }
    else if(userAgent == "iOS") {
        Jockey.send("showLoading");
    }

	$.ajax({
		url : "<c:url value='/m/loanhomemortgage/modifyLoanREIncomeInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : true,
		success : function (result) {
            if(userAgent == "Android") {
                window.Android.loding('N');
            }
            else if(userAgent == "iOS") {
                Jockey.send("stopLoading");
            }
			if(result.result == '00') {
				loanApplyStepStep();
			}
		},
		error : function (e) {
			errMsg(e);
		}
	});
}

function viewIncome(){
	if($('#cd_job_class_l').val() == ''){
		$('#worker').hide();
		$('#employed').hide();
	} else if($('#cd_job_class_l').val() == '1'){
		$('#worker').show();
		$('#employed').hide();
        $('#amt_year_income').val("");
        $('#amt_year_income').focus();
	} else if($('#cd_job_class_l').val() == '2'){
		$('#worker').hide();
		$('#employed').show();
        $('#amt_year_sale').val("");
        $('#amt_year_sale').focus();
    }
}

function loanApplyStepStep() {
	frmloanApplyStep.action = "<c:url value='/m/loanhomemortgage/frameLoanHomeMortgageStep11.crz'/>";
	frmloanApplyStep.submit();
}

function ChkButton(){
	if($("#cd_job_class_l").val() != '' && $("#cd_job_class_l").val() != null) {
		if($("#cd_job_class_l").val() == '1'){
			if($("#amt_year_income").val() != '' && $("#amt_year_income").val() != null) {
				affixBottom('show');
			} else {
				affixBottom('hide');
			}
		} else if($("#cd_job_class_l").val() == '2'){
			if($("#amt_year_sale").val() != '' && $("#amt_year_sale").val() != null) {
				affixBottom('show');
			} else {
				affixBottom('hide');
			}
		}
	} else {
		affixBottom('hide');
	}
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
			<h1>소득정보</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
		<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
		<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
		<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
		<input type="hidden" name="ssn_person" id="ssn_person" value="${txFcTransmitVO.ssn_person}"/>
		<div class="container">
			<div class="lead">
				<p>소득정보를 입력해주세요.</p>
			</div>	
			<div class="form-inline">
				<div class="form-group">
					<label for="">구분</label>
					<select class="selectpicker" data-header="직업구분" name="cd_job_class_l" id="cd_job_class_l" onchange="viewIncome(); ChkButton();">
						<option value=''>직업구분</option>
						<option value='1'>직장인</option>
						<option value='2'>개인사업자</option>
					</select>
				</div>
				<div class="form-group has-feedback" id="worker" style="display: none">
					<label for="">연소득</label>
					<input type="number" class="form-control" name="amt_year_income" id="amt_year_income" onkeyup="ChkButton();" autocomplete="off"/>
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
				<div class="form-group has-feedback" id="employed" style="display: none" >
					<label for="">연매출</label>
					<input type="number" class="form-control" name="amt_year_sale" id="amt_year_sale" onkeyup="ChkButton();" autocomplete="off"/>
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
			</div>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" type="button" class="btn btn-lg btn-primary btn-block" onclick="updateTxFc();">확인</a>
		</div>
		</form>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
