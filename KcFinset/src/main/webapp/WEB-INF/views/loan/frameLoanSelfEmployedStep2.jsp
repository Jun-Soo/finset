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
		checkExistCert();
	});
	
	/* 
	* 공인인증서 유무 체크
	*/
	function checkExistCert() {
		if(userAgent == "iOS") {
			
				//공인인증서 유무 체크 결과 콜백 이벤트
				Jockey.on("resultCheckCert" , function(param) {
					var iscert = false;
	            	if(param.isCert == 1) iscert = true;

	            	resultCheckCert(iscert);
				});
				
				Jockey.send("checkExistCert");
			
		} else if(userAgent == "Android") {
			window.Android.checkExistCert();
		}
	}
	
	/* 
	* 공인인증서 유무 결과 (모바일 에서 호출)
	*/
	function resultCheckCert(isCert) {
		
		$("#income_simple").removeClass();
		$("#income_self").removeClass();
		
		if(isCert) {	// 공인인증서가 있을 경우
			$("#income_simple").addClass("btn btn-lg btn-primary status-on");
			$("#income_self").addClass("btn btn-lg btn-default status-off");
			frmloanApplyStep.status_change.value = 'simple';
		} else {		// 공인인증서가 없을 경우
			$("#income_simple").addClass("btn btn-lg btn-default status-off");
			$("#income_self").addClass("btn btn-lg btn-primary status-on");
			$('#income_simple').attr('disabled', true);  //버튼 비활성화 
			frmloanApplyStep.status_change.value = 'self';
		}
	}
	
	/* 
	*공인인증서 가져오기
	*/
	function importCert() {
		if(userAgent == "iOS") {
			Jockey.send("importCert");
		} else if(userAgent == "Android") {
			window.Android.importCert();
		}
	}
	
	/* 
	*스크래핑 실행
	* custNm, bizLicence, hp, nhisStartRcptYm, nhisEndYm, ntsStartRcptYm, 
	* ntsEndRcptYm, ntsStartTaxYm, ntsEndTaxYm, finYear, type, finYear
	*/
	function frmSimpleDoc() {
		
		var result = "<c:out value='${scrapResult}' />";
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
				loanApplyCancel();
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
	    var mon = (now.getMonth()) > 9 ? '' + (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
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
	    var mon = (date.getMonth()) > 9 ? '' + (date.getMonth() + 1) : '0' + (date.getMonth() + 1);
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
	* 직접등록 선택 시  status_change 값 변경
	*/
	function statusChangeSelf(){
		$("#income_simple").removeClass();
		$("#income_self").removeClass();
		$("#income_simple").addClass("btn btn-lg btn-default status-off");
		$("#income_self").addClass("btn btn-lg btn-primary status-on");
		frmloanApplyStep.status_change.value = 'self';
	}
	
	/*
	* 간편등록 선택 시  status_change 값 변경
	*/
	function statusChangeSimple(){
		$("#income_simple").removeClass();
		$("#income_self").removeClass();
		$("#income_simple").addClass("btn btn-lg btn-default status-on");
		$("#income_self").addClass("btn btn-lg btn-primary status-off");
		$('#income_simple').attr('disabled', false);
		frmloanApplyStep.status_change.value = 'simple';
	}
	
	/*
	* 간편등록 선택 시  status_change 값으로 분기 다음버튼 클릭 액션
	*/
	function frmStepNext() {
		if(frmloanApplyStep.status_change.value == '') {
			
		} else if(frmloanApplyStep.status_change.value == 'simple') {
			frmSimpleDoc();
		} else if(frmloanApplyStep.status_change.value == 'self') {
			frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanSelfEmployedStep3.crz'/>";
			frmloanApplyStep.submit();
		}
	}
	
	/*
	* 간편 등록 콜백 함수
	* 모바일에서 간편등록이 완료되면 호출 시킨다.
	*/
	function frmStepNextFromMobile() {
		frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanSelfEmployedStep3.crz'/>";
		frmloanApplyStep.submit();
	}
	
	//취소버튼 클릭시 대출 초기 페이지로
	function loanApplyCancel(){
		if(!confirm("취소를 하시겠습니까?")) return false;
		frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanApply.crz'/>"
		frmloanApplyStep.submit();
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
</script>

</head>
<body>
<form name="frmloanApplyStep" id="frmloanApplyStep" method="post">
<input type="hidden" name="no_person" id="no_person" value="${personVO.no_person}"/>
<input type="hidden" name="nm_person" id="nm_person" value="${personVO.nm_person}"/>
<input type="hidden" name="ssn_person" id="ssn_person" value="${personVO.ssn_person}"/>
<input type="hidden" name="hp" id="hp" value="${personVO.hp}"/>
<input type="hidden" name="no_bunch" id="no_bunch" value="${personVO.no_bunch}"/>
<input type="hidden" name="loan_code" id="loan_code" value="${personVO.loan_code}"/>
<input type="hidden" name="status_change" id="status_change" value=""/>
<input type="hidden" name="job" id="job" value="${personVO.job}"/>
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>개인금융</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<ul class="step">
			<li><span>1</span></li>
			<li><span>2</span></li>
			<li class="active"><span>3. 소득정보등록</span></li>
			<li><span>4</span></li>
		</ul>
		<h2 class="h2">소득정보 입력</h2>
		<div class="container">	
			<p class="help-block">
				맞춤금융상품 조회에 사용될 중요한 정보입니다.<br />
				정확하게 내용을 입력해여 주시기 바랍니다.
			</p>
		</div>
		<div class="btn-cert" id="income_btn_conteiner"> 
			<button id="income_self" class="btn btn-lg btn-default btn-block" onclick="statusChangeSelf();">소득정보 직접등록</button>
			<button id="income_simple" class="btn btn-lg btn-default btn-block" onclick="statusChangeSimple()" class="">소득정보 간편등록</button>
		</div>
		<div class="cert-guide">
			<ul class="text-list">
				<li>국민연금, 의료보험 납부 내역을 조회하여 별도의 등록과정 없이 소득 정보를 등록할 수 있습니다.</li>
				<li>간편등록 서비스를 이용하기 위해서는 공인인증서가 단말기에 저장되어 있어야 합니다.</li>
				<li>아이폰 이용고객은 공인인증서 등록 과정을 진행해야 합니다.</li>
			</ul>
			<div class="row">
				<div class="col-xs-6">
					<a role="button" class="btn btn-arr">공인인증서 신규발급</a>
				</div>
				<div class="col-xs-6">
					<a role="button" onclick="importCert()" class="btn btn-arr">공인인증서 복사하기</a>
				</div>
			</div>
		</div>
		<div class="btn-fixed-bottom bottom-over">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="loanApplyCancel()">취소</button>
			</div>
			<div class="col-xs-6">
				<a role="button" class="btn btn-lg btn-primary btn-block" onclick="frmStepNext()">다음</a>				
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>
