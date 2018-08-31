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
        $('#cd_loan_use').change(function(event) {
            toggleSelect("cd_house_type");
        });

        $('#cd_house_type').change(function(event) {
            toggleSelect("cd_live_type_home");
        });

        $('#cd_live_type_home').change(function(event) {
            enableBottom('true');
            affixBottom('show');
        });
        /**
         * 직장 소득정보에서 사용하는 쿠키값 초기화
         */
        initCookieLoanSelfEmployed();

	});
    var initCookieLoanSelfEmployed = function () {
        /**
         * 쿠키값 초기화
         */
        setCookie(IS_START                ,"");
        setCookie('nm_comp'               ,"");
        setCookie('no_biz_comp'           ,"");
        setCookie('cd_occupational'       ,"");
        setCookie('cd_occupational_detail',"");
        setCookie('cd_worker_position'    ,"");
        setCookie('cd_employee_type'      ,"");
        setCookie('jb_dt_join_view'       ,"");
        setCookie('amt_year_income'       ,"");
        setCookie('amt_year_income'       ,"");
        setCookie('isInit2'               ,"");
    };

	function chkValidate(){
		if($("#cd_loan_use").val() != null && $("#cd_loan_use").val() != '' && $("#cd_house_type").val() != null && $("#cd_house_type").val() != ''
			&& $("#cd_live_type_home").val() != null && $("#cd_live_type_home").val() != '') {
			affixBottom('show');
		}else {
			affixBottom('hide');
		}
	}
	
	function modifyLoanAdditional(){
		if ( !frmloanApplyStep.validateForm() ) return false;
		if($("#cd_loan_use").val() == '' || $("#cd_loan_use").val() == null) {
			toastMsg('대출용도를 선택해주세요.');
			return false;
		} else if($("#cd_house_type").val() == '' || $("#cd_house_type").val() == null) {
			toastMsg('주거형태를 선택해주세요.');
			return false;
		} else if($("#cd_live_type_home").val() == '' || $("#cd_live_type_home").val() == null) {
			toastMsg('소유형태를 선택해주세요.');
			return false;
		}
		var data = frmloanApplyStep.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/loanworker/modifyLoanAdditional.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					loanApplyStepStep();
				} else {
					toastMsg(result.message);
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
		
	}
	
	function loanApplyStepStep() {
		
		frmloanApplyStep.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep8.crz'/>";
		frmloanApplyStep.submit();
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
			<h1>부가정보</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>부가 정보를 선택해주세요.</p>
			</div>	
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
                <input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
                <input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
                <input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
                <input type="hidden" name="ssn_person" id="ssn_person" value="${txFcTransmitVO.ssn_person }"/>
                <div class="form-inline">
				<div class="form-group">
					<label for="">대출용도</label>
					<select class="selectpicker" data-header="대출용도선택"
							name="cd_loan_use" id="cd_loan_use" onchange="chkValidate();">
							${ufn:makeOptions("cd_loan_use","EMPTY", '')}
					</select>
				</div>
				<div class="form-group">
					<label for="">주거형태</label>
					<select class="selectpicker" data-header="주거형태선택"
							name="cd_house_type" id="cd_house_type" onchange="chkValidate();">
						${ufn:makeOptions("cd_house_type","EMPTY", '')}
					</select>
				</div>
				<div class="form-group">
					<label for="">소유형태</label>
					<select class="selectpicker" data-header="소유형태선택"
							name="cd_live_type_home" id="cd_live_type_home" onchange="chkValidate();">
						${ufn:makeOptions("cd_live_type_home","EMPTY", '')}
					</select>
				</div>
			</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="modifyLoanAdditional();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
