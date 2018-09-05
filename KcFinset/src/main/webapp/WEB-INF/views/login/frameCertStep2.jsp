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
<!-- <link rel="stylesheet" href="//rawgit.com/Soldier-B/jquery.toast/master/jquery.toast/jquery.toast.min.css" /> -->
<!-- <script src="//rawgit.com/Soldier-B/jquery.toast/master/jquery.toast/jquery.toast.min.js"></script> -->
<script src="<c:url value="/script/selectize.js"/>"></script>
<script type="text/javascript">

	var timeId = "";

	$(document).ready(function() {
		// 안드로이드 SMS 퍼미션
		if(userAgent == "Android") {
			window.Android.reqSMSPermission();
		}
    });
	
	function setRequestPhoneNumber(phoneNumber){
	     $('#hp').val(phoneNumber);
	}
	
	/**
	* 타이머
	**/
	function countdown(elementName, minutes, seconds) {
	    var element, endTime, hours, mins, msLeft, time;

	    function twoDigits(n) {
	        return (n <= 9 ? "0" + n : n);
	    }	

	    function updateTimer() {
	        msLeft = endTime - (+new Date);
	        if ( msLeft < 1000 ) {
	            element.innerHTML = "0:00";
	        } else {
	            time = new Date( msLeft );
	            hours = time.getUTCHours();
	            mins = time.getUTCMinutes();
	            element.innerHTML = (hours ? hours + ':' + twoDigits( mins ) : mins) + ':' + twoDigits( time.getUTCSeconds() );
	            timeId = setTimeout( updateTimer, time.getUTCMilliseconds() + 500 );
	        }
	    }

	    element = document.getElementById( elementName );
	    endTime = (+new Date) + 1000 * (60*minutes + seconds) + 500;
	    updateTimer();
	}
	
	/*
	* 인증 번호 요청
	*/
	function kcmRequestCertNo() {
		$('#telComCd').removeAttr("disabled");
		if ( !frmCertifyStep.validateForm() ) return false;
		var chk = chkCert();
		if(chk == false){ return false;}
		if($("#sex").val() == '1' || $("#sex").val() == '2') {
			$('#birthday').val("19"+$('#ssn_birth').val());
		} else if($("#sex").val() == '3' || $("#sex").val() == '4') {
			$('#birthday').val("20"+$('#ssn_birth').val());
		}
		
		//var data = frmCertifyStep.ajaxSubmit();
		var data = $("#frmCertifyStep").serialize();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/credit/kcmRequestCertNo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				
				         
				if(result.result == "00") {
					$('#smsCertNo').val('');
					toastMsg(result.message);
					if(timeId != "")
						clearTimeout(timeId);
					else 
						frmCertifyStep.smsReSndYn.value = "Y";
					//이름 주민번호 앞 뒤 통신사선택  disable
					$('#telComCd').attr("disabled", "disabled");
					frmCertifyStep.nm_person.readOnly = true;
					frmCertifyStep.ssn_birth.readOnly = true;
					frmCertifyStep.sex.readOnly = true;
					
					countdown("countdown", 3, 00);
					$("#req_certification").html("인증번호 재전송");	
					frmCertifyStep.svcTxSeqno.value = result.svcTxSeqno;
					frmCertifyStep.smsCertNo.readOnly = false;
                    $('#smsCertNo').focus();

                } else if(result.result == "11") {
					
					alert(result.message);
// 					if(userAgent == "Android") {
// 						window.Android.exitApp();
// 					} else {
						frmCertifyStep.action = "<c:url value='/m/login/frameCertStep1.crz'/>";
						frmCertifyStep.submit();
						return false;
// 					}
				} else if(result.result == "01") {
					toastMsg(result.message);
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	/*
	* 인증번호 
	*/
	function setCertNumber(number) {
		number=number+"";
		if(frmCertifyStep.smsCertNo.value.length == 0){
			$("#smsCertNo").val(number);
			smsCertNoChk();
		}
	}
	
	/*
	* 인증 번호 확인
	*/
	function confirmedCertify() {
		if(frmCertifyStep.smsCertNo.value == '' || frmCertifyStep.smsCertNo.value == 'undefined') return false;
		
		var regExp = /^[0-9]+$/;
		if ( !frmCertifyStep.validateForm() ) return false;
		// var data = frmCertifyStep.ajaxSubmit();
        var data = $("#frmCertifyStep").serialize();
        if(data == null) return false;
		if(!regExp.test(frmCertifyStep.smsCertNo.value)){
			toastMsg('숫자만 입력해주세요.');
			$('#smsCertNo').val('');
			return false;
		}
		$.ajax({
			url : "<c:url value='/m/credit/kcmCertify.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
// 				if(result.result == '13'){
// 					if(!confirm(result.message)){
// 						frmCertifyStep.action = "<c:url value='/m/login/frameCertStep1.crz'/>";
// 						frmCertifyStep.submit();
// 						return false;
// 					}
// 				}
// 				if(result.result == '00' || result.result == '13') {
				if(result.result == '00') {
					frmCertifyStep.kcb_ci.value = result.kcb_ci;
					frmCertifyStep.kcb_di.value = result.kcb_di;
					frmCertifyStep.kcb_cp.value = result.kcb_cp;
					insertPerson();
				}else{
// 						alert(result.message);
					toastMsg(result.message);
					$('#smsCertNo').val('');
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
// 		}
	}
	
	/*
	* 회원가입
	*/
	function insertPerson() {
		
		frmCertifyStep.bgn.value =  frmCertifyStep.birthday.value + $("#sex").val();
		
		var data = frmCertifyStep.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/login/insertPerson.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					joinSuccess(result.returnData);
				} else if(result.result == '11') {
					loginSuccess(result.returnData);
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	//로그아웃 변수 변경
	function modifyPersonLogout() {
		
		$("#yn_logout").val('N');
		$('#yn_use').val("Y");
		var data = frmCertifyStep.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url: "<c:url value='/m/person/modifyYnUseAndLogout.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
		
	}
	/*
	* 회원가입
	*/
	function joinSuccess(noPerson) {
		if(userAgent == "iOS") {
// 			location.href = "call://setNoPerson//"+noPerson;
			Jockey.send("setNoPerson",{
				noPerson : noPerson,
				phNum : frmCertifyStep.hp.value
			});
		} else if(userAgent == "Android") {
			window.Android.setNoPerson(noPerson, frmCertifyStep.hp.value);
		}
		
		//submit 전 disable 풀어줘야 넘어감
		$('#telComCd').removeAttr("disabled");

		frmCertifyStep.action = "<c:url value='/m/base/frameSecurityCode.crz'/>";
		frmCertifyStep.submit();
	}
	
	/*
	* 로그인
	*/
	function loginSuccess(noPerson) {
		
		modifyPersonLogout();
		
		if(userAgent == "iOS") {
// 			location.href = "call://setNoPerson//"+noPerson;
			Jockey.send("setNoPerson",{
				noPerson : noPerson,
				phNum : frmCertifyStep.hp.value
			});
		} else if(userAgent == "Android") {
			window.Android.setNoPerson(noPerson, frmCertifyStep.hp.value);
		}
		
		location.href = "<c:url value='/m/login/frameSecurityCodeConfirm.crz?hp="+frmCertifyStep.hp.value+"'/>";
	}
	
	function ssnBirthChk(){
		if(frmCertifyStep.ssn_birth.value.length == 6){
            $('#sex').focus();
		}
	}

	function sexChk(){
        var val = $('#sex').val();
        var len = val.length;
        if(len > 1) {
            val = val.substr(0,1);
            $('#sex').val(val);
        }
        if(len > 0){
            $('#telComCd').selectpicker('toggle');
        }
	}
	function chkValidate(){
		if($("#nm_person").val() != null && $("#nm_person").val() != '' && $("#ssn_birth").val() != null && $("#ssn_birth").val() != ''
				&& $("#sex").val() != null && $("#sex").val() != '' && $("#hp").val() != null && $("#hp").val() != '' && $("select[name=telComCd]").val() != null
				&& $("select[name=telComCd]").val() != '') {
		}else {
		}
	}
	
	function smsCertNoChk(){
		if(frmCertifyStep.smsCertNo.value.length >= 1){
			affixBottom('show');
		} else {
			affixBottom('hide');
		} 
	}
	
	function chkCert(){

		var regNumber = /^[0-9]*$/;
		
		if($("#nm_person").val() == '' || $("#nm_person").val() == null) {
			toastMsg('이름을 입력해주세요.');
			$("#nm_person").focus();
			return false;
		} 
		if($("#ssn_birth").val() == '' || $("#ssn_birth").val() == null) {
			toastMsg('주민등록번호 앞자리를 입력해주세요.');
			$("#ssn_birth").focus();
			return false;
		} else if(!regNumber.test($('#ssn_birth').val())) {
		    toastMsg('주민등록번호 앞자리를 숫자만 입력해주세요.');
		    $('#ssn_birth').val('');
		    $('#ssn_birth').focus();
		    return false;
		}
		if($("#sex").val() == '' || $("#sex").val() == null) {
			toastMsg('주민등록번호 뒷자리를 입력해주세요.');
			$("#sex").focus();
			return false;
		} else if(!regNumber.test($('#sex').val())) {
		    toastMsg('주민등록번호 뒷자리를 숫자만 입력해주세요.');
		    $('#sex').val('');
		    $('#sex').focus();
		    return false;
		}
		if($("#telComCd").val() == '' || $("#telComCd").val() == null) {
			toastMsg('통신사를 선택해주세요.');
			return false;
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
				<button class="ui-nav nav-back" type="button" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>본인인증</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container"> 
			<form name="frmCertifyStep" id="frmCertifyStep" method="post" validate="remove_tag;">
			<input type="hidden" name="nation" value="1" >
			<input type="hidden" name="kcb_ci" >
			<input type="hidden" name="kcb_di" >
			<input type="hidden" name="kcb_cp" >
			<input type="hidden" name="svcTxSeqno" >
			<input type="hidden" name="smsReSndYn" id="smsReSndYn">
			<input type="hidden" name="bgn"  />
			<input type="hidden" name="yn_logout" id="yn_logout"  />
			<input type="hidden" name="yn_use" id="yn_use"  />
			<input type="hidden" name="yn_eventPush" id="yn_eventPush" value="${yn_eventPush}"/>
			
				<div class="form-block form-input-block-confrim">
					<div class="form-group">
						<label for="" class="sr-only">이름</label>
						<input type="text" name="nm_person" id="nm_person" class="form-control" maxlength="8" placeholder="이름을 입력하세요." validate="trim;strip_ws;label:이름;" autocomplete="off" onkeyup="chkValidate();"/>
					</div>
					<div class="form-group">
						<label for="" class="sr-only">주민등록번호 앞 7자리</label>
						<div class="row">
							<div class="col-xs-5">
								<input type="number" name="ssn_birth" id="ssn_birth" class="form-control xe_required" maxlength="6" validate=" label:주민등록번호 앞자리;numeric;" autocomplete="off" placeholder="주민등록번호 앞자리"  onkeyup="ssnBirthChk(); chkValidate();"/>						
								<input type="hidden" name="birthday" id="birthday" class="form-control" maxlength="8"  autocomplete="off" />						
							</div>
							<div class="col-xs-1">
								<p class="form-control-static">-</p>
							</div>
							<div class="col-xs-2">
								<label for="" class="sr-only">주민번호 7번째 자리</label>
								<input type="number" pattern="[0-9]*" inputmode="numeric" style="-webkit-text-security:disc" maxlength="1" class="form-control" name="sex" id="sex" placeholder="" validate=" label:주민등록번호 뒷자리;numeric;" autocomplete="off" onkeyup="sexChk(); chkValidate();"/>					
							</div>
							<div class="col-xs-4">
								<p class="form-control-static">* * * * * *</p>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-input-inline">
							<span class="" aria-hidden="true">
								<select name="telComCd" id="telComCd" validate="select-one;label:통신사" class="selectpicker" data-header="옵션선택" >
									<option value="">통신사</option>
									<option value="01">SKT</option>
									<option value="02">KT</option>
									<option value="03">LGU+</option>
									<option value="04">SK알뜰폰</option>
									<option value="05">KT알뜰폰</option>
									<option value="06">LG알뜰폰</option>
								</select>
								<label for="hp" class="sr-only">휴대폰 번호</label>
								<input type="tel" name="hp" id="hp" class="form-control" maxlength="11" />
							</span>
							<span class="form-control-feedback" aria-hidden="true"><a role="button" id="req_certification" class="btn btn-primary btn-cert-no" onclick="kcmRequestCertNo();">인증요청</a></span>
						</div>
					</div>
					<div class="form-group has-feedback" id="cert_no_conteiner">
						<label for="smsCertNo" class="sr-only">인증번호</label>
						<input type="number" name="smsCertNo" id="smsCertNo" class="form-control" placeholder="인증번호를 입력하세요" autocomplete="off" readonly="readonly" onkeyup="smsCertNoChk();">
		                <span id="countdown" class="form-control-feedback cert-num" aria-hidden="true">00:00</span>
					</div>	
				</div>
			</form>
		</div>
	</section>
	<div class="btn-fixed-bottom" id="confirmed_div">
		<button id="confirmed_certify" onclick="confirmedCertify()" class="btn btn-lg btn-primary btn-block" >확인</button>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
