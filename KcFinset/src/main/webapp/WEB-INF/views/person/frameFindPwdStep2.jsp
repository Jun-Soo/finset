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
		// 안드로이드 SMS 퍼미션
		if(userAgent == "Android") {
			window.Android.reqSMSPermission();
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
		if ( !frmCertifyStep.validateForm() ) return false;
		if($("#sex").val() == '1' || $("#sex").val() == '2') {
			$('#birthday').val("19"+$('#ssn_birth').val());
		} else if($("#sex").val() == '3' || $("#sex").val() == '4') {
			$('#birthday').val("20"+$('#ssn_birth').val());
		}
		//var data = frmCertifyStep.ajaxSubmit();
		var data = $("#frmCertifyStep").serialize();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/person/personCertify.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '01'){
					alert(result.message);
					return false;
				}
				$.ajax({
					url : "<c:url value='/m/credit/kcmRequestCertNo.json'/>",
					data : data,
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					type : "POST",
					async : false,
					success : function (result) {
						
						alert(result.message);
						if(result.result == "00") {
							$("#confirmed_certify").attr("disabled", false); //확인버튼 활성화
							if(timeId != "")
								clearTimeout(timeId);
							else 
								frmCertifyStep.smsReSndYn.value = "Y";
							
							countdown("countdown", 3, 00);
							$("#req_certification").html("인증번호 재전송");	
							frmCertifyStep.svcTxSeqno.value = result.svcTxSeqno;
							frmCertifyStep.smsCertNo.readOnly = false;
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
// 		if($("#smsCertNo").val() == null || $("#smsCertNo").val() == ''){
		number=number+"";
		if(frmCertifyStep.smsCertNo.value.length == 0){
			$("#smsCertNo").val(number);
// 			confirmedCertify();
			smsCertNoChk();
		}
// 		$("#smsCertNo").val(number);
// 		}
	}
	
	/*
	* 인증 번호 확인
	*/
	function confirmedCertify() {
		var regExp = /^[0-9]+$/;
		if ( !frmCertifyStep.validateForm() ) return false;
		var data = frmCertifyStep.ajaxSubmit();
		if(data == null) return false;
		if(frmCertifyStep.smsCertNo.value.length == 6){
			if(!regExp.test(frmCertifyStep.smsCertNo.value)){
				alert('숫자만 입력해주세요.');
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
						frmCertifyStep.action = "<c:url value='/m/person/frameSecurityCode.crz'/>";
						frmCertifyStep.submit();
					}else{
						alert(result.message);
					}
				},
				error : function (e) {
					errMsg(e);
				}
			});
		}
	}
	
	function ssnBirthChk(){
		if(frmCertifyStep.ssn_birth.value.length == 6){
			frmCertifyStep.sex.focus();
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
		$("#req_certification").removeClass();
		if($("#nm_person").val() != null && $("#nm_person").val() != '' && $("#ssn_birth").val() != null && $("#ssn_birth").val() != ''
				&& $("#sex").val() != null && $("#sex").val() != '' && $("#hp").val() != null && $("#hp").val() != '' && $("select[name=telComCd]").val() != null
				&& $("select[name=telComCd]").val() != '') {
			$("#req_certification").addClass("btn btn-primary btn-block btn-cert-no");
		}else {
			$("#req_certification").addClass("btn btn-primary btn-block btn-cert-no disabled");
		}
	}
</script>

</head>
<body >
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button class="ui-nav nav-back" type="button" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>본인인증</h1>
			<div class="input-group-btn">
				<button class="ui-nav nav-menu" type="button" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
<!-- 		<p class="top-desc">서비스 이용을 위해<br />휴대전화 명의를 통한 본인인증을 진행합니다.</p> -->
		<div class="container"> 
			<form name="frmCertifyStep" id="frmCertifyStep" method="post" validate="remove_tag;">
			<input type="hidden" name="nation" value="1" >
			<input type="hidden" name="svcTxSeqno" >
			<input type="hidden" name="smsReSndYn" id="smsReSndYn">
			<input type="hidden" name="bgn"  />
				<div class="form-block form-input-block-confrim">
					<div class="form-group">
						<label for="">이름</label>
						<input type="text" name="nm_person" id="nm_person" class="form-control" placeholder="이름" validate="required;trim;strip_ws;label:이름;restrictChars;" autocomplete="off" onkeyup="chkValidate();"/>
					</div>
					<!-- <div class="form-group">
						<div class="row">    
							<div class="col-xs-8">
								<label for="" class="sr-only">생년월일</label>
								<input type="tel" name="birthday" class="form-control" placeholder="생년월일(숫자만 입력 ex.20170405)" maxlength="8" validate="required; label:생년월일;numeric;" autocomplete="off"/>					
							</div>
							<div class="col-xs-4">
								<label for="" class="sr-only">성별</label>
								<select name="sex" validate="required;select-one;label:성별" class="form-control">
									<option>성별</option>
									<option value="1">남자</option>
									<option value="2">여자</option>
								</select>					
							</div>
						</div>
					</div> -->
					<div class="form-group">
						<label for="">주민등록번호 앞지라</label>
						<div class="row">
							<div class="col-xs-5">
								<input type="number" name="ssn_birth" id="ssn_birth" class="form-control" maxlength="6" validate="required; label:주민등록번호 앞자리;numeric;" autocomplete="off" placeholder="주민등록번호 앞자리"  onkeyup="ssnBirthChk(); chkValidate();"/>						
								<input type="hidden" name="birthday" id="birthday" class="form-control" maxlength="8"  autocomplete="off" />						
							</div>
							<div class="col-xs-1">
								<p class="form-control-static">-</p>
							</div>
							<div class="col-xs-2">
								<label for="" class="sr-only">주민번호 7번째 자리</label>
								<input type="number" pattern="[0-9]*" inputmode="numeric" style="-webkit-text-security:disc" class="form-control" name="sex" id="sex" maxlength="1" placeholder="" validate="required; label:주민등록번호 뒷자리;numeric;" autocomplete="off" onkeyup="sexChk(); chkValidate();"/>					
							</div>
							<div class="col-xs-4">
								<p class="form-control-static">* * * * * *</p>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="">휴대폰 번호</label>
						<div class="row">
							<div class="col-xs-4">
								<label for="" class="sr-only">휴대폰 번호</label>
								<select name="telComCd" id="telComCd" validate="required;select-one;label:통신사" class="form-control" onchange="chkValidate();">
									<option value=''>통신사</option>
									<option value="01">SKT</option>
									<option value="02">KT</option>
									<option value="03">LGU+</option>
									<option value="04">SK알뜰폰</option>
									<option value="05">KT알뜰폰</option>
									<option value="06">LG알뜰폰</option>
								</select>
							</div>
							<div class="col-xs-8">
								<label for="" class="sr-only">휴대폰 번호</label>
								<input type="number" name="hp" id="hp" class="form-control" placeholder="휴대폰번호(숫자만 입력 ex.01012345678)" maxlength="11" validate="required; label:휴대폰번호;minlength:10;numeric;" autocomplete="off" onkeyup="chkValidate();"/>
							</div>
						</div>
					</div>
					<!-- <div class="form-group">
						<div class="row">
							<div class="col-xs-4">
								<label for="">휴대폰 번호</label>
								<select name="telComCd" validate="required;select-one;label:통신사" class="form-control">
									<option>통신사</option>
									<option value="01">SKT</option>
									<option value="02">KT</option>
									<option value="03">LGU+</option>
									<option value="04">SK알뜰폰</option>
									<option value="05">KT알뜰폰</option>
									<option value="06">LG알뜰폰</option>
								</select>
							</div>
							<div class="col-xs-8">
								<label for="" class="sr-only">휴대폰</label>
								<input type="tel" name="hp" class="form-control" placeholder="휴대폰번호 (ex.01012345678)" maxlength="11" validate="required; label:휴대폰번호;minlength:10;numeric;" autocomplete="off"/>			
							</div>
						</div>
					</div> -->
					<a role="button" id="req_certification" class="btn btn-primary btn-block btn-cert-no disabled" onclick="kcmRequestCertNo();">인증번호 전송</a>
					<div class="form-group has-feedback" id="cert_no_conteiner">
						<label for="" class="sr-only">인증번호</label>						
						<input type="tel" name="smsCertNo" id="smsCertNo" class="form-control" placeholder="인증번호를 입력하세요" autocomplete="off" readonly="readonly" onkeyup="confirmedCertify();">
		                <span id="countdown" class="form-control-feedback cert-num" aria-hidden="true">00:00</span>
					</div>			
				</div>
			</form>
		</div>
	</section>
	<div class="btn-fixed-bottom">
		<button id="confirmed_certify" onclick="confirmedCertify()" class="btn btn-lg btn-primary btn-block" disabled="disabled" >확인</button>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
