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

	var timeId = "";

	$(document).ready(function() {
		
		$('#checkall').change(function() {
 			if ($('#checkall').is(':checked')) {
 				$(this).parents(".checkbox").addClass("check");
 				$(':checkbox').parents(".panel-heading").addClass("check");
		        $('#panel1').removeClass();
		        $('#panel1').addClass("panel-collapse collapse");
 			} else {
 				$(':checkbox').prop('checked', false);
 				$(this).parents(".checkbox").removeClass("check");
 				$(':checkbox').parents(".panel-heading").removeClass("check");
				$('#panel1').removeClass();
		        $('#panel1').addClass("panel-collapse collapse in");
 			}
 		});
        if ($('#checkall').is(':checked')) {
            $(this).parents(".checkbox").addClass("check");
            $(':checkbox').parents(".panel-heading").addClass("check");
            $('#panel1').removeClass();
            $('#panel1').addClass("panel-collapse collapse");
        } else {
            $(':checkbox').prop('checked', false);
            $(this).parents(".checkbox").removeClass("check");
            $(':checkbox').parents(".panel-heading").removeClass("check");
            $('#panel1').removeClass();
            $('#panel1').addClass("panel-collapse collapse in");
        }
		// 안드로이드 SMS 퍼미션
		if(userAgent == "Android") {
			window.Android.reqSMSPermission();
			window.Android.setEndApp('Y');
		}
	});
    /**
	* 타이머
	**/
	function countdown( elementName, minutes, seconds ) {
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
		if ( !frmAcceptTerms.validateForm() ) return false;
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
        // alert("인증요청: kcmRequestCertNo:data =\n"+data);
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/person/personCertify.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '01'){
					toastMsg(result.message);
					return false;
				}
				$.ajax({
					url : "<c:url value='/m/credit/kcmRequestCertNo.json'/>",
					data : data,
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					type : "POST",
					async : false,
					success : function (result) {
						
						toastMsg(result.message);
						if(result.result == "00") {
							$('#smsCertNo').val('');
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
						}
					},
					error : function (e) {
						errMsg(e);
					}
				});
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
		if ( !frmAcceptTerms.validateForm() ) return false;
		if ( !frmCertifyStep.validateForm() ) return false;
		var data = $("#frmCertifyStep").serialize();
		// alert("comfirmdata=\n"+data);
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
				if(result.result == '00') {
					//submit 전 disable 풀어줘야 넘어감
					$('#telComCd').removeAttr("disabled");
					frmCertifyStep.action = "<c:url value='/m/person/frameSecurityCode.crz'/>";
					frmCertifyStep.kcb_di.value = result.kcb_di;
					frmCertifyStep.kcb_ci.value = result.kcb_ci;
					frmCertifyStep.kcb_cp.value = result.kcb_cp;
					frmCertifyStep.submit();
				}else{
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
	
	function ssnBirthChk(){
		if(frmCertifyStep.ssn_birth.value.length == 6){
			frmCertifyStep.sex.focus();
		}
	}
	var isSexChange = false;
	function sexChk() {
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
	
	//본인인증관련 약관 1
	function popAcceptTerms(gubun) {
		
		if(gubun == 'hp4') {
			frmAcceptTerms.action = "<c:url value='/m/credit/frameAcceptTerms4.crz'/>";
			frmAcceptTerms.submit();
		} else if(gubun == 'hp8') {
			frmAcceptTerms.action = "<c:url value='/m/credit/frameAcceptTerms8.crz'/>";
			frmAcceptTerms.submit();
		} else if(gubun == 'hp9') {
			frmAcceptTerms.action = "<c:url value='/m/credit/frameAcceptTerms9.crz'/>";
			frmAcceptTerms.submit();
		} else if(gubun == 'hp1') {
			frmAcceptTerms.action = "<c:url value='/m/credit/frameAcceptTerms1.crz'/>";
			frmAcceptTerms.submit();
		}
	}
</script>

</head>
<body class="bg_white">
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
		<form name="frmAcceptTerms" method="post">
		<div class="container">
			<div class="check-all">
				<div class="checkbox"><label><input type="checkbox" id="checkall" name="checkall" validate="checkbox-required:checkall;label:이용약관;"> 전체 약관 동의</label></div>			
			</div>
		</div>
		<!-- panel -->
		<div class="panel-group agree-panel" id="termsList">
			<div class="panel panel-default"> 
				<div class="panel-collapse collapse in" id="panel1">
					<div class="list-group">
						<li>
							<a class="list-group-item" onclick="popAcceptTerms('hp4');">
								<label>개인정보수집 및 이용동의</label>
								<button type="button" class="btn-terms">약관보기</button>
							</a>
						</li>
						<li>
							<a class="list-group-item" onclick="popAcceptTerms('hp8');">
								<label>고유식별처리 동의</label>
								<button type="button" class="btn-terms">약관보기</button>
							</a>
						</li>
						<li>
							<a class="list-group-item" onclick="popAcceptTerms('hp9');">
								<label>통신사 본인확인 이용약관 동의</label>
								<button type="button" class="btn-terms">약관보기</button>
							</a>
						</li>
						<li>
							<a class="list-group-item" onclick="popAcceptTerms('hp1');">
								<label>서비스 이용약관 동의</label>
								<button type="button" class="btn-terms">약관보기</button>
							</a>
						</li>
					</div>
				</div>
			</div>
		</div>
		</form>
		<!-- //panel -->
		<div class="container">
			<form name="frmCertifyStep" id="frmCertifyStep" method="post" validate="remove_tag;">
			<input type="hidden" name="nation" value="1" >
			<input type="hidden" name="kcb_ci" >
			<input type="hidden" name="kcb_di" >
			<input type="hidden" name="kcb_cp" >
			<input type="hidden" name="svcTxSeqno" >
			<input type="hidden" name="smsReSndYn" id="smsReSndYn">
			<input type="hidden" name="bgn"  />
			<div class="form-block form-input-block-confrim">
				<div class="form-group">
					<label for="" class="sr-only">이름</label>
					<input type="text" name="nm_person" id="nm_person" maxlength="10" class="form-control" placeholder="이름을 입력하세요." validate="trim;strip_ws;label:이름;restrictChars;" autocomplete="off" onkeyup="chkValidate();"/>
				</div>
				<div class="form-group">
					<label for="" class="sr-only">주민등록번호 앞 7자리</label>
					<div class="row">
						<div class="col-xs-5">
							<input type="number" name="ssn_birth" id="ssn_birth" class="form-control xe_required" maxlength="6" validate="label:주민등록번호 앞자리;numeric;" autocomplete="off" placeholder="주민등록번호 앞자리"  onkeyup="ssnBirthChk(); chkValidate();"/>						
							<input type="hidden" name="birthday" id="birthday" class="form-control" maxlength="8"  autocomplete="off" />						
						</div>
						<div class="col-xs-1">
							<p class="form-control-static">-</p>
						</div>
						<div class="col-xs-2">
							<label for="" class="sr-only">주민번호 7번째 자리</label>
							<input type="number" pattern="[0-9]*" inputmode="numeric" style="-webkit-text-security:disc" class="form-control" name="sex" id="sex"
								   maxlength="1" placeholder="" validate="label:주민등록번호 뒷자리;numeric;" autocomplete="off" onkeyup="sexChk(); chkValidate();"/>
						</div>
						<div class="col-xs-4">
							<p class="form-control-static">* * * * * *</p>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="form-input-inline">
						<span class="" aria-hidden="true">
							<select name="telComCd" id="telComCd" validate="select-one;label:통신사" class="selectpicker" data-header="옵션선택">
								<option value="">통신사</option>
								<option value="01">SKT</option>
								<option value="02">KT</option>
								<option value="03">LGU+</option>
								<option value="04">SK알뜰폰</option>
								<option value="05">KT알뜰폰</option>
								<option value="06">LG알뜰폰</option>
								<label for="" class="sr-only">휴대폰 번호</label>
								<input type="number" pattern="[0-9]*" name="hp" id="hp" class="form-control" maxlength="11" value="${personHp }"/>
<%-- 						<input type="tel" name="hp" id="hp" class="form-control" maxlength="11" value="${personHp }" readonly="readonly"/> --%>
							</select>
						</span>
						<span class="form-control-feedback" aria-hidden="true"><a role="button" id="req_certification" class="btn btn-primary btn-cert-no" onclick="kcmRequestCertNo();">인증요청</a></span>
					</div>	
				</div>
				<div class="form-group has-feedback" id="cert_no_conteiner">
					<label for="" class="sr-only">인증번호</label>						
					<input type="tel" name="smsCertNo" id="smsCertNo" class="form-control" placeholder="인증번호를 입력하세요" autocomplete="off" readonly="readonly" onkeyup="smsCertNoChk();">
	                <span id="countdown" class="form-control-feedback cert-num" aria-hidden="true">00:00</span>
				</div>
			</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="confirmed_div">
			<button id="confirmed_certify" onclick="confirmedCertify()" class="btn btn-lg btn-primary btn-block" >확인</button>	
		</div>
	</section>
	
	
	<!-- //Content -->
</div>
</body>
</html>
