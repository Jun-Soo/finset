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
});

//tx_fc_transmit테이블에  직장/소득 정보 update 하기 위한 함
function updateTxFc(){
	if ( !frmloanApplyStep.validateForm() ) return false;
	if($("#jb_dt_join_view").val() == '' || $("#jb_dt_join_view").val() == null) {
		toastMsg('사업개시(월)을 선택해주세요.');
		return false;
	} else {
		$("#jb_dt_join").val($("#jb_dt_join_view").val());
		frmloanApplyStep.jb_dt_join.value = frmloanApplyStep.jb_dt_join.value.replace(/-/gi, "");
		var current = getCurrentDateYM();
		if(Number(frmloanApplyStep.jb_dt_join.value) > Number(current)){
			toastMsg('사업개시(월)을 잘못입력했습니다.');
			return false;
		}
	}
	var data = frmloanApplyStep.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/m/loanselfemployed/updateTxFc.json'/>",
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
	checkExistCert();
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

        	resultCheckCert(iscert);
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
	frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14.crz'/>";
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
	frmloanApplyStep.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep6.crz'/>";
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

function chkValidate(){
	if($('#jb_dt_join_view').val() != '' && $('#jb_dt_join_view').val() != null){
		affixBottom('show');
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
			<h1>사업자명/소득 정보</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>사업자명/매출 정보를 입력해주세요.</p>
			</div>
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
			<input type="hidden" name="nm_person" id="nm_person" value="${txFcTransmitVO.nm_person}"/>
			<input type="hidden" name="no_person" id="no_person" value="${txFcTransmitVO.no_person}"/>
			<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
			<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
			<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
			<input type="hidden" name="hp" id="hp" value="${txFcTransmitVO.hp1 }"/>
			<input type="hidden" name="ssn_person" id="ssn_person" value="${txFcTransmitVO.ssn_person }"/>	
			<input type="hidden" name="amt_year_sale" id="amt_year_sale" value="${txFcTransmitVO.amt_year_sale }">
			<div class="form-inline">
				<div class="form-group has-feedback">
					<label for="">사업자명</label>					
					<input type="text" class="form-control" name="nm_comp" id="nm_comp" value="${txFcTransmitVO.nm_comp }" readonly="readonly" autocomplete="off">
				</div>
				<div class="form-group">
					<label for="">사업개시<small>(월)</small></label>
					<input type="month" name="jb_dt_join_view" id="jb_dt_join_view" class="form-control slt-date"
						   placeholder="" onchange="chkValidate();" />
					<input type="hidden" name="jb_dt_join" id="jb_dt_join" class="form-control slt-date" placeholder="" />
				</div>
			</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="updateTxFc();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
