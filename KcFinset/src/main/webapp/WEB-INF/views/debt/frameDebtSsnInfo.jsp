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
		//alert("<c:out value='${bankCode}'/>");
	});

// 	frmloanApplyStep.ssn_person.value = frmloanApplyStep.ssn1.value + frmloanApplyStep.ssn2.value;

	/* 
	* 공인인증서 유무 체크
	*/
	function checkExistCert() {
		
		if ( !frmloanApplyStep.validateForm() ) return false;
		var chk = validateionChk();
		// alert("chk="+chk);
		if(chk == false){ return false;}
		
		if(userAgent == "iOS") {
			 //공인인증서 유무 체크 결과 콜백 이벤트
            Jockey.on("resultCheckCert" , function(param) {
            	var iscert = false;
            	if(param.isCert == 1) iscert = true;

            	resultCheckCert(iscert);
            });
            // debug("checkExistCert2:userAgent="+userAgent);

            Jockey.send("checkExistCert");
		} else if(userAgent == "Android") {
			window.Android.checkExistCert();
		}
	}
	
	/*
	* 주민번호 체크
	*/
	function validateionChk(){
	    var result = true;
		var regNumber = /^[0-9]*$/;
		if(!regNumber.test($('#ssn2').val())) {
		    toastMsg('주민등록번호 뒷자리 숫자만 입력해주세요.');
		    $('#ssn2').val('');
		    $('#ssn2').focus();
		    result = false;
		} else if(frmloanApplyStep.ssn2.value.length != 7) {
			toastMsg('주민등록번호 뒷자리 길이가 정확하지 않습니다.');
			$('#ssn2').val('');
			$('#ssn2').focus();
			result =false;
		}
		return result;
	}
	
	/* 
	* 공인인증서 유무 결과 (모바일에서 호출)
	*/
	function resultCheckCert(isCert) {
		if(isCert) {	// 공인인증서가 있을 경우
			frmSimpleDoc();
		} else {		// 공인인증서가 없을 경우
			alert('공인인증서가 없습니다.');
	// 		frmloanApplyStep.action = "<c:url value='/m/debt/frameInDebtInfo.crz'/>";
			frmloanApplyStep.action = "<c:url value='/m/debt/frameDebtInfoMain.crz'/>";
			frmloanApplyStep.submit();
		}
	}
	
	/* 
		스크래핑 실행
		lastDate	이후의 거래내역 조회(조회 시작일자) : ''입력시 => 계좌정보O,계좌상새내역X
		bankCode	운영 연계되면 하드코딩 풀것 
		scrapResult 건강보험 스크래핑 실행 옵션  "121222222" -> 122222222 (납부내역서만)
		nhisStartRcptYm	 전달-2달 YYYYMM
		nhisEndYm	전달 YYYYMM
	*/
	function frmSimpleDoc() {
		
		var noPerson = $('#no_person').val();	
//		noPerson = "P000000014";	

		var bankCode = $('#bank_code').val();
		//bankCode = "ibk,shinhan,kbstar,hanabank";		
		//var bankCode = "shinhan";
		//var bankCode = "kdb,ibk,kbstar,hanabank,suhyupbank,nonghyup,jejubank,jbbank,knbank,shinhan,wooribank,standardchartered,citibank,dgb,busanbank,kjbank,kfcc,cu,epostbank";//$('#bankCode').val();
		//var bankCode = "ibk,kbstar,hanabank,nonghyup,jbbank,shinhan,wooribank,standardchartered,citibank,dgb,kfcc";//$('#bankCode').val();
		
		var lastDate = $('#last_date').val();	
		//lastDate = "20180101";  
		
		var nmPerson = $('#nm_person').val();
		var scrapResult = "122222222";		
		
		var ssnPerson = $('#ssn1').val() + $('#ssn2').val();
		// 주민번호 뒷자리 복호화
		// TODO LoginController.getDecodedPassword에 사용자 본인만 접근할 수 있도록 제한해야함
		
		if ( frmloanApplyStep.encPwd != null ) {
			$.ajax({
				url : "<c:url value='/m/login/getDecodedPassword.crz'/>",
				data : {"encPwd": frmloanApplyStep.encPwd.value},
				async: false,
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				type : "POST",
				success : function (result) {
					ssnPerson = $('#ssn1').val() + result.message;
				},
				error : function (e) {
                    errMsg(e);
				}
			});
		}
		
		var nhisStartRcptYm = $('#nhis_start_rcpt_ym').val();
		var nhisEndYm = $('#nhis_end_ym').val();	
	
		if(userAgent == "iOS") {

			Jockey.on("frmStepNextFromMobile" , function(param) {
				frmStepNextFromMobile();
			});
			
			Jockey.send("checkBankPasswordCert" , {
				noPerson : noPerson,
				bankCode : bankCode,
				lastDate : lastDate,
				nmPerson : nmPerson,
				scrapResult : scrapResult,
				ssnPerson : ssnPerson,
				nhisStartRcptYm : nhisStartRcptYm,
				nhisEndYm : nhisEndYm
			});
		} else if(userAgent == "Android") {
			window.Android.checkBankPasswordCert(noPerson, bankCode, lastDate, nmPerson, scrapResult, ssnPerson, nhisStartRcptYm, nhisEndYm);
		}
	}
	
	/*
	* 스크래핑 완료 시 (모바일에서 호출)
	* 모바일에서 간편등록이 완료되면 호출 시킨다.
	*/
	function frmStepNextFromMobile() {
//		alert("콜백콜콜");
		frmloanApplyStep.debt_reg_update_yn.value = 'Y';
		frmloanApplyStep.action = "<c:url value='/m/debt/frameDebtInfoMain.crz'/>";
		frmloanApplyStep.submit();
	}
	
	/*
	* 스크래핑 완료 시 (모바일에서 호출)
	* 모바일에서 간편등록이 완료되면 호출 시킨다.
	*/
	function frmStepNextFromMobile(resultCode) {
//		alert("콜백콜콜");
		frmloanApplyStep.debt_reg_update_yn.value = 'Y';
		frmloanApplyStep.action = "<c:url value='/m/debt/frameDebtInfoMain.crz'/>";
		frmloanApplyStep.submit();
	}
	
	function ssn2Chk() {
        affixBottom("show");
        enableBottom("true");

// 		alert('1');
// 		if(frmloanApplyStep.ssn2.value.length == 7){
// 			$('#next_div').removeClass();
// 		} else {
// 			$('#next_div').addClass("btn-fixed-bottom");
// 		}
	}
	
	function loanApplyStepStep() {
		if ( !frmloanApplyStep.validateForm() ) return false;
		var chk = validateionChk();
		if(chk == false){ return false;}
		//frmloanApplyStep.ssn_person.value = frmloanApplyStep.ssn1.value + frmloanApplyStep.ssn2.value;
		
		frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep8.crz'/>";
		frmloanApplyStep.submit();
	}
	
	/*
	* 보안키패드 노출
	*/
	function showSecureKeypad() {
		if(userAgent == "iOS") {
			Jockey.send("showSecureKeypad",{
				keypadType : "numeric",
				maxInputLength : 7,
				subTitle : "주민등록번호 뒷자리",
				placeholderText : "숫자를 입력하세요."
			});
			//보안키패드 결과값 수신 콜백 이벤
			Jockey.on("resultKeypad" , function(param){
				resultKeypad(param.encPwd);
			});
		} else if(userAgent == "Android") {
			window.Android.showSecureKeypad("numeric", 7, "주민등록번호 뒷자리");
		}
	}
	
	/*
	* 보안키패드 결과값 수령
	*/
	function resultKeypad(encPwd) {
		if ( encPwd != null && encPwd != "" ) {
			$("#ssn2").val("1111111");	// 임의의 숫자 7자 입력
			$("input[name=encPwd]").val(encPwd);	// 암호화값 입력. 서버단에서 복호화가 이뤄져야함
		} else {
			$("#ssn2").val("");	// 
			$("input[name=encPwd]").val("");	// 
		}
        ssn2Chk();
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
			<h1>신청인 정보</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>연동을 위해 주민등록번호<br>뒷자리를 입력해주세요.</p>
			</div>
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
				
				<!-- 제대로 셋팅하기 -->	
 				<input type="hidden" name="no_person" id="no_person" value="${personVO.no_person}"/>				
<!-- 				<input type="hidden" name="no_person" id="no_person" value="${personVO.no_person}"/> -->
				<input type="hidden" name="bank_code" id="bank_code" value="${bankCode}"/>
	
				<input type="hidden" name="last_date" id="last_date" value=""/>
				<input type="hidden" name="ssn_person" id="ssn_person" value=""/>
				<input type="hidden" name="ssn1" id="ssn1" value="${personVO.ssn_person}"/>
					
				<input type="hidden" name="nhis_start_rcpt_ym" id="nhis_start_rcpt_ym" value="${nhis_start_rcpt_ym}" />
				<input type="hidden" name="nhis_end_ym" id="nhis_end_ym" value="${nhis_end_ym}" />
				
				<input type="hidden" name="debt_reg_update_yn" id="debt_reg_update_yn" value="N"/>				

			
			<div class="form-block">
				<div class="form-group">
					<label class="sr-only">고객명</label>
					<input type="text" value="${personVO.nm_person }" class="form-control" name="nm_person" id="nm_person" placeholder="" readonly="readonly">
				</div>
				<div class="form-group">
					<label class="sr-only">생년월일</label>
					<div class="row">
						<div class="col-xs-5">
							<input type="text" value="${personVO.ssn_person }" class="form-control" name="" placeholder="" readonly="readonly">
						</div>
						<div class="col-xs-1">
							<p class="form-control-static">-</p>
						</div>
						<div class="col-xs-6">
							<!-- <input type="password" class="form-control xe_required" name="ssn2" id="ssn2" onkeyup="ssn2Chk();" placeholder="주민번호뒷자리" maxlength="7"  autocomplete="off"> -->
							<input type="password" class="form-control xe_required" name="ssn2" id="ssn2" onclick="showSecureKeypad();" placeholder="주민번호뒷자리" maxlength="7"  autocomplete="off" readonly="readonly">
							<input type="hidden" name="encPwd"/>
						</div>
					</div>
				</div>
			</div>
			</form>
		</div>
	</section>
	<!-- //Content -->
	<div class="btn-fixed-bottom" id="next_div">
<!-- 		<a role="button" class="btn btn-lg btn-primary btn-block" onclick="loanApplyStepStep();">확인</a>  -->
		<a role="button" class="btn btn-lg btn-block btn-disabled" onclick="checkExistCert();">확인</a>
	</div>
<!-- 	<div class="btn-fixed-bottom" id="next_div"> -->
<!-- 		<a href="#" class="btn btn-lg btn-primary btn-block" disabled="disabled">확인</a> -->
<!-- 	</div> -->
</div>
</body>
</html>
