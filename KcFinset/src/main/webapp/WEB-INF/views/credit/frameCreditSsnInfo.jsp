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
		var scrapCode = $('#scrap_code').val();
		
		switch(scrapCode){
		case "nhis":
			$("#div_select").hide();
		break;
		case "nts":
			$("#div_ssn").hide();
			showButton();
		break;
		case "nps":
			$("#div_select").hide();
		break;
	}

	});

	/* 
	* 공인인증서 유무 체크
	*/
	function checkExistCert() {
		var scrapCode = $('#scrap_code').val();
		
		if ( !frmCreditSsnInfo.validateForm() ) return false;
		// 국세청의 경우 주민번호 사용 안함
		if(scrapCode != "nts")	{
			var chk = validateionChk();
			if(chk == false){ return false;}
		}
		
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
		} else if(frmCreditSsnInfo.ssn2.value.length != 7) {
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
			frmCreditSsnInfo.action = "<c:url value='/m/credit/frameCreditRaise.crz'/>";
			frmCreditSsnInfo.submit();
		}
	}
	
	/* 
		스크래핑 실행
		scrapResult 건강보험 스크래핑 실행 옵션  "121222222" -> 122222222 (납부내역서만)
		nhisStartRcptYm	 전달-2달 YYYYMM
		nhisEndYm	전달 YYYYMM
	*/
	function frmSimpleDoc() {
		var scrapCode = $('#scrap_code').val();
		var noPerson = $('#no_person').val();	
		var nmPerson = $('#nm_person').val();
		var ssnPerson = "";
		var nhisStartRcptYm = "";
		var nhisEndYm = "";
		var certDivision = "";
		var ntsStartIncomeY = "";
		var ntsEndIncomeY = "";
		
		switch(scrapCode){
		case "nhis":
			ssnPerson = $('#ssn1').val() + $('#ssn2').val();
			nhisStartRcptYm = $('#nhis_start_ym').val();
			nhisEndYm = $('#nhis_end_ym').val();	
		break;
		case "nts":
			certDivision = $('#cert_division').val();
			ntsStartIncomeY = $('#inquiry_year').val();
			ntsEndIncomeY = $('#inquiry_year').val();
		break;
		case "nps":
			ssnPerson = $('#ssn1').val() + $('#ssn2').val();
		break;
		}
		
		// 주민번호 뒷자리 복호화 :국세청이 아닌경우 호출 
		// TODO LoginController.getDecodedPassword에 사용자 본인만 접근할 수 있도록 제한해야함
		if ( frmCreditSsnInfo.encPwd != null && scrapCode != "nts") {
			$.ajax({
				url : "<c:url value='/m/login/getDecodedPassword.crz'/>",
				data : {"encPwd": frmCreditSsnInfo.encPwd.value},
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
	
		if(userAgent == "iOS") {

			Jockey.on("resultCreditRatingUpgrade" , function(param) {
				resultCreditRatingUpgrade(param.result, param.scrapCode);
			});
			
			Jockey.send("creditRatingUpgrade" , {
				scrapCode : scrapCode,
				noPerson : noPerson,
				nmPerson : nmPerson,
				ssnPerson : ssnPerson,
				nhisStartRcptYm : nhisStartRcptYm,
				nhisEndYm : nhisEndYm,
				certDivision : certDivision, 
				ntsStartIncomeY : ntsStartIncomeY,
				ntsEndIncomeY : ntsEndIncomeY
			});
		} else if(userAgent == "Android") {
			window.Android.creditRatingUpgrade(scrapCode, noPerson, nmPerson, ssnPerson, nhisStartRcptYm, nhisEndYm, certDivision, ntsStartIncomeY, ntsEndIncomeY);
		}
	}
	
	/*
	* 스크래핑 완료 시 (모바일에서 호출)
	* 정상 : 관련 스크래핑 내역 조회화면으로 이동
	* 비정상  : 홈화면으로 이동(신용평가)
	*/
	function resultCreditRatingUpgrade(result, scrapCode) {
		
		frmCreditSsnInfo.action = "<c:url value='/m/credit/frameCreditInfoMain.crz'/>";
		if(result == "true")	{		
			switch( scrapCode ){
			   case "nhis":
				   frmCreditSsnInfo.action = "<c:url value='/m/credit/frameCreditRaiseNhis.crz'/>";
			     break;
			   case "nts":
				   frmCreditSsnInfo.action = "<c:url value='/m/credit/frameCreditRaiseNts.crz'/>";
				 break;
			   case "nps":
				   frmCreditSsnInfo.action = "<c:url value='/m/credit/frameCreditRaiseNps.crz'/>";
				 break;
		    }
		}
		frmCreditSsnInfo.submit();
	}
	
	function showButton() {
        affixBottom("show");
        enableBottom("true");
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
        showButton();
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
		
			
		<div class="container" id="div_ssn">
			<div class="lead">
				<p>연동을 위해 주민등록번호<br>뒷자리를 입력해주세요.</p>
			</div>
			<form name="frmCreditSsnInfo" id="frmCreditSsnInfo" method="post" validate="remove_tag;">
			<input type="hidden" name="scrap_code" id="scrap_code" value="${scrap_code}"/>
			<input type="hidden" name="no_person" id="no_person" value="${personVO.no_person}"/>				
			<input type="hidden" name="ssn_person" id="ssn_person" value=""/>
			<input type="hidden" name="ssn1" id="ssn1" value="${personVO.ssn_person}"/>
			<input type="hidden" name="nhis_start_ym" id="nhis_start_ym" value="${nhis_start_ym}" />
			<input type="hidden" name="nhis_end_ym" id="nhis_end_ym" value="${nhis_end_ym}" />
				
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
							<input type="password" class="form-control xe_required" name="ssn2" id="ssn2" onclick="showSecureKeypad();" placeholder="주민번호뒷자리" maxlength="7"  autocomplete="off" readonly="readonly">
							<input type="hidden" name="encPwd"/>
						</div>
					</div>
				</div>
			</div>
			</form>
		</div>
		<div class="container" id="div_select">
			<div class="form-inline">
			
				<div class="form-group">
					<label for="category">가입형태</label>
					<select class="selectpicker" data-header="가입형태 " name="cert_division" id="cert_division">
					<option value="1">근로소득자용</option>
					<option value="2">연말정산한 사업소득자용</option>
					<option value="3">종합소득세 신고자용</option>
					</select>
				</div>
				
				<div class="form-group">
					<label for="category">대상기간</label>
					<select class="selectpicker" data-header="대상기간 " name="inquiry_year" id="inquiry_year">
					<c:forEach var="inquiryYear" items="${inquiry_years}" varStatus="status">
					<option value=${inquiryYear}>${inquiryYear}년</option>
					</c:forEach>
					</select>
				</div>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<div class="btn-fixed-bottom" id="next_div">
		<a role="button" class="btn btn-lg btn-block btn-disabled" onclick="checkExistCert();">확인</a>
	</div>
</div>
</body>
</html>
