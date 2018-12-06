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
        var yn_loan_mount  = getCookie("YN_LOAN_MOUNT");
        var cd_loan_mount  = getCookie("CD_LOAN_MOUNT");
        var cd_type_pay    = getCookie("CD_TYPE_PAY"  );
        var cd_loan_term   = getCookie("CD_LOAN_TERM" );
        $('#yn_loan_mount').val(yn_loan_mount     );
        $('#yn_loan_mount').selectpicker("refresh");
        $('#cd_loan_mount').val(cd_loan_mount     );
        $('#cd_loan_mount').selectpicker("refresh");
        $('#cd_type_pay'  ).val(cd_type_pay       );
        $('#cd_type_pay'  ).selectpicker("refresh");
        $('#cd_loan_term' ).val(cd_loan_term      );
        $('#cd_loan_term' ).selectpicker("refresh");
        if(    yn_loan_mount != ''
            || cd_loan_mount != ''
            || cd_type_pay   != ''
            || cd_loan_term  != ''
        ) {
            viewRepayment();
            ChkButton();
        }
        $('#yn_loan_mount').on("change",function(){
            viewRepayment();
            ChkButton();
        });
        $('#div_cd_loan_mount').on("change",function(){
            viewRepayment();
            ChkButton();
        });
        $('#cd_type_pay').on("change",function(){
            viewRepayment();
            ChkButton();
        });
        $('#cd_loan_term').on("change",function(){
            viewRepayment();
            ChkButton();
        });
    });

	function updateTxFc(){
		if ( !frmloanApplyStep.validateForm() ) return false;
		if($("#yn_loan_mount").val() == '' || $("#yn_loan_mount").val() == null) {
			toastMsg('거치여부를 선택해주세요.');
			return false;
		} else if($("#yn_loan_mount").val() == '1'){
			if($("#cd_loan_mount").val() == '' || $("#cd_loan_mount").val() == null) {
				toastMsg('거치기간을 선택해주세요.');
				return false;
			}
		} else if($("#yn_loan_mount").val() == '2'){
			$('#cd_loan_mount').val('');
		}
		
		if($("#cd_type_pay").val() == '' || $("#cd_type_pay").val() == null) {
			toastMsg('상환방식을 선택해주세요.');
			return false;
		} else if($("#cd_type_pay").val() == '1' || $("#cd_type_pay").val() == '2'){
			if($("#cd_loan_term").val() == '' || $("#cd_loan_term").val() == null) {
				toastMsg('상환기간을 선택해주세요.');
				return false;
			}
		} else if($("#cd_type_pay").val() == '3'){
			$('#cd_loan_term').val('');
		}
		var data = $("#frmloanApplyStep").serialize();
		if(data == null) return false;
        var yn_loan_mount = $("#yn_loan_mount").val();
        var cd_loan_mount = $("#cd_loan_mount").val();
        var cd_type_pay   = $("#cd_type_pay"  ).val();
        var cd_loan_term  = $("#cd_loan_term" ).val();
        setCookie("YN_LOAN_MOUNT",yn_loan_mount);
        setCookie("CD_LOAN_MOUNT",cd_loan_mount);
        setCookie("CD_TYPE_PAY"  ,cd_type_pay  );
        setCookie("CD_LOAN_TERM" ,cd_loan_term );
        $.ajax({
			url : "<c:url value='/m/loanhomemortgage/modifyLoanRERepaymentInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					loanApplyStepStep();
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	function loanApplyStepStep() {
        frmStepNextFromMobile();
		// checkExistCert();
	}
	
	/* 
	* 공인인증서 유무 체크
	*/
	function checkExistCert() {
		if(userAgent == "iOS") {
			
				//공인인증서 유무 체크 결과 콜백 이벤트
				Jockey.on("resultCheckCert" , function(param) {
					var iscert = false;
	            	if(param.isCert == 1) iscert = true;

	            	resultCheckCert(iscert);v
				});
				
				Jockey.send("checkExistCert");
			
		} else if(userAgent == "Android") {
			window.Android.checkExistCert();
		}
	}
	
	/*
	* 간편 등록 콜백 함수 (취소시)
	* 모바일에서 간편등록이 완료되면 호출 시킨다.
	*/
	function frmStepNextFromMobileCancle(){
		frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14.crz'/>";
 		frmloanApplyStep.submit();
	}
	
	/*
	* 간편 등록 콜백 함수
	* 모바일에서 간편등록이 완료되면 호출 시킨다.
	*/
	function frmStepNextFromMobile() {
		frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14pre.crz'/>";
 		frmloanApplyStep.submit();
	}
	/* 
	* 공인인증서 유무 결과 (모바일 에서 호출)
	*/
	function resultCheckCert(isCert) {
		if(isCert) {	// 공인인증서가 있을 경우
			frmSimpleDoc();
		} else {		// 공인인증서가 없을 경우
	 		frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14.crz'/>";
	 		frmloanApplyStep.submit();
		}
	}
	
	/* 
	* 주민번호 틀렸을 시 
	*/
	function failVerify() {
		frmloanApplyStep.action = "<c:url value='/m/loanhomemortgage/frameLoanHomeMortgageStep7.crz'/>";
		toastMsg('주민등록번호가 정확하지 않습니다.');
		setTimeout(function(){
			frmloanApplyStep.submit();
		}, 2000);
	}
	/* 
	*스크래핑 실행
	* custNm, bizLicence, hp, nhisStartRcptYm, nhisEndYm, ntsStartRcptYm, 
	* ntsEndRcptYm, ntsStartTaxYm, ntsEndTaxYm, finYear, type, finYear
	*/
	function frmSimpleDoc() {
		
		var result = "121222222";
		var noPerson = $('#no_person').val();
		var nmPerson = $('#nm_person').val();
		var ssnPerson = $('#ssn_person').val();
		var bizLicence = "";
		var hp = $('#hp').val();
		var nhisStartRcptYm = minusMonthYM(6);
		var nhisEndYm = getCurrentDateYM();
		var ntsStartTaxYm = combineCurrentDateYM("01");
		var ntsEndTaxYm = combineCurrentDateYM("12");
		var ntsStartRcptYm = minusStartYear(1);
		var ntsEndRcptYm = minusEndYear(1);
		var ntsStartIncomeY = minusYear(1);
		var ntsEndIncomeY = minusYear(1);
		var finYear = minusYear(1);
		
		if(userAgent == "iOS") {
			//간편 등록 콜백 함수(완료시 모바일에서 호출하는 이벤트)
			Jockey.on("frmStepNextFromMobile" , function(param) {
				frmStepNextFromMobile();
			});
			
			//간편 등록 콜백 함수(취소시 모바일에서 호출하는 이벤트)
			Jockey.on("frmStepNextFromMobileCancle" , function() {
				frmStepNextFromMobileCancle();
			});
			
			//주민등록번호 오류 (모바일에서 호출하는 이벤트)
			Jockey.on("failSsnVerify" , function() {
				failVerify();
			});
			
			Jockey.send("checkPasswordCert" , {
				result : result,
				noPerson : noPerson,
				ssnPerson : ssnPerson,
				bizLicence : bizLicence,
				hp : hp,
				nhisStartRcptYm : nhisStartRcptYm,
				nhisEndYm : nhisEndYm,
				ntsStartTaxYm : ntsStartTaxYm,
				ntsEndTaxYm : ntsEndTaxYm,
				ntsStartRcptYm : ntsStartRcptYm,
				ntsEndRcptYm : ntsEndRcptYm,
				ntsStartIncomeY : ntsStartIncomeY,
				ntsEndIncomeY : ntsEndIncomeY,
				finYear : finYear,
				nmPerson : nmPerson
			});
		} else if(userAgent == "Android") {
			window.Android.checkPasswordCert(result, noPerson, ssnPerson, bizLicence, hp, nhisStartRcptYm, nhisEndYm, ntsStartTaxYm, ntsEndTaxYm, ntsStartRcptYm, ntsEndRcptYm, ntsStartIncomeY, ntsEndIncomeY, finYear, nmPerson);
		}
	}
	
	/*
	* 현대 날짜의 YYYY + month 을 반환한다.
	* return YYYYMM
	*/
	function combineCurrentDateYM(month) {
		var now = new Date();
        var year = now.getFullYear();
	    return year + month;
	}
	
	/*
	* 현대 날짜의 YYYYMM 을 반환한다.
	* return YYYYMM
	*/
	function getCurrentDateYM() {
		var now = new Date();
        var year= now.getFullYear();
	    var mon = (now.getMonth()+1) > 9 ? '' + (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
	    var strYM = year + mon;
	    return strYM;
	}
	
	/*
	* 입력한 날짜 -month를 반환한다.
	* return YYYYMM
	*/
	function minusMonthYM(minusMonth) {
		var date = new Date();
		date.setMonth(date.getMonth() - minusMonth);
		var year = date.getFullYear();
	    var mon = (date.getMonth()+1) > 9 ? '' + (date.getMonth() + 1) : '0' + (date.getMonth() + 1);
	    var strYM = year + mon;
	    return strYM;
	}
	
	/*
	* 현재년도 - year 을 반환한다
	* return YYYY
	*/
	function minusYear(paramYear) {
		var now = new Date();
        var year = now.getFullYear();
        return (Number(year) - paramYear);
	}
	
	/*
	* 현재년도 - year + 01 을 반환한다
	* return YYYY01
	*/
	function minusStartYear(paramYear) {
		var now = new Date();
        var year = now.getFullYear();
        return (Number(year) - paramYear) + "01";
	}
	/*
	* 현재년도 - year + 12 을 반환한다
	* return YYYY12
	*/
	function minusEndYear(paramYear) {
		var now = new Date();
        var year = now.getFullYear();
        return (Number(year) - paramYear) + "12";
	}
	
	function viewRepayment(){
		if($('#yn_loan_mount').val() == '1'){
			$('#div_cd_loan_mount').show();
		} else {
			$('#div_cd_loan_mount').hide();
		}

		if($('#cd_type_pay').val() == '1' || $('#cd_type_pay').val() == '2') {
			$('#div_cd_loan_term').show();
		} else {
			$('#div_cd_loan_term').hide();
		}
	}
	
	function ChkButton(){
		if($("#yn_loan_mount").val() == '2') {
			$("#cd_loan_mount").val('').selectpicker('refresh');
		} 
		if($("#cd_type_pay").val() == '3') {
			$("#cd_loan_term").val('').selectpicker('refresh');
		}	

		if($("#yn_loan_mount").val() != '' && $("#yn_loan_mount").val() != null) {
			if($("#yn_loan_mount").val() == '1'){
				if($("#cd_loan_mount").val() != '' && $("#cd_loan_mount").val() != null) {
					if($("#cd_type_pay").val() != '' && $("#cd_type_pay").val() != null) {
						if($("#cd_type_pay").val() == '1' || $("#cd_type_pay").val() == '2'){
							if($("#cd_loan_term").val() != '' && $("#cd_loan_term").val() != null) {
								affixBottom('show');
							} else {
								affixBottom('hide');
							}
						} else if($("#cd_type_pay").val() == '3') {
							affixBottom('show');
						}
					} else {
						affixBottom('hide');
					}
				} 
			} else if($("#yn_loan_mount").val() == '2'){
				if($("#cd_type_pay").val() != '' && $("#cd_type_pay").val() != null) {
					if($("#cd_type_pay").val() == '1' || $("#cd_type_pay").val() == '2'){
						if($("#cd_loan_term").val() != '' && $("#cd_loan_term").val() != null) {
							affixBottom('show');
						} else {
							affixBottom('hide');
						}
					} else if($("#cd_type_pay").val() == '3'){
						affixBottom('show');
					}
				} else {
					affixBottom('hide');
				}
// 				}
			}
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
			<h1>상환정보</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>대출 상환정보를 입력해 주세요.</p>
			</div>	
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">	
			<input type="hidden" name="nm_person" id="nm_person" value="${txFcTransmitVO.nm_person}"/>
			<input type="hidden" name="no_person" id="no_person" value="${txFcTransmitVO.no_person}"/>
			<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
			<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
			<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
			<input type="hidden" name="hp" id="hp" value="${txFcTransmitVO.hp1 }"/>
			<input type="hidden" name="ssn_person" id="ssn_person" value="${txFcTransmitVO.ssn_person }"/>
			<input type="hidden" name="loan_code" id="loan_code" value="03"/>
			<input type="hidden" name="cd_duty_comp"   id="cd_duty_comp"   value="${txFcTransmitVO.cd_duty_comp }"/>
				<div class="form-inline">
				<div class="form-group">
					<label for="">거치여부</label>
					<select class="selectpicker" data-header="거치여부" name="yn_loan_mount" id="yn_loan_mount">
						<option value=''>거치여부</option>
						<option value='1'>거치</option>
						<option value='2'>비거치</option>
					</select>
				</div>
				<div class="form-group" style="display: none" id="div_cd_loan_mount">
					<label for="">거치기간</label>
					<select class="selectpicker" data-header="거치기간" name="cd_loan_mount" id="cd_loan_mount">
						<option value=''>거치기간</option>
						<option value='1'>1년</option>
						<option value='2'>2년</option>
						<option value='3'>3년</option>
						<option value='4'>4년</option>
						<option value='5'>5년</option>
					</select>
				</div>
				<div class="form-group">
					<label for="">상환방식</label>
					<select class="selectpicker" data-header="상환방식" name="cd_type_pay" id="cd_type_pay">
						<option value=''>상환방식</option>
						<option value='1'>원리금분할상환</option>
						<option value='2'>원금분할상환</option>
						<option value='3'>만기일시상환</option>
					</select>
				</div>
				<div class="form-group" style="display: none" id="div_cd_loan_term">
					<label for="">상환기간</label>
					<select class="selectpicker" data-header="상환기간" name="cd_loan_term" id="cd_loan_term" >
						<option value=''>상환기간</option>
						<option value='1'>1년</option>
						<option value='2'>2년</option>
						<option value='3'>3년</option>
						<option value='4'>4년</option>
						<option value='5'>5년</option>
					</select>
				</div>
			</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" type="button" class="btn btn-lg btn-primary btn-block" onclick="updateTxFc();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
