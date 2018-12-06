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
		cateChgLow("${txFcTransmitVO.cd_occupational_detail}");
	});

	//tx_fc_transmit테이블에  직장/소득 정보 update 하기 위한 함
	function updateTxFc(){
		// if ( !frmloanApplyStep.validateForm() ) return false;
		if($("#cd_worker_position").val() == '' || $("#cd_worker_position").val() == null) {
			toastMsg('직위를 선택해주세요.');
			return false;
		} else if($("#cd_employee_type").val() == '' || $("#cd_employee_type").val() == null) {
			toastMsg('고용형태를 선택해주세요.');
			return false;
		} else if($("#jb_dt_join_view").val() == '' || $("#jb_dt_join_view").val() == null) {
			toastMsg('입사년월을 선택해주세요.');
			return false;
		} else {
			$("#jb_dt_join").val($("#jb_dt_join_view").val());
			frmloanApplyStep.jb_dt_join.value = frmloanApplyStep.jb_dt_join.value.replace(/-/gi, "");
			var current = getCurrentDateYM();
			if(Number(frmloanApplyStep.jb_dt_join.value) > Number(current)){
				toastMsg('입사년월을 잘못입력했습니다.');
				return false;
			}
		}
		// var data = frmloanApplyStep.ajaxSubmit();
        var data = $("#frmloanApplyStep").serialize();

        if(data == null) return false;
		$.ajax({
			url : "<c:url value='/m/loanworker/updateTxFc.json'/>",
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
	
	function cateChgLow(e) {
// 		alert(e + "XXXXXX");
		var cate_low_111  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","기타"];
		var cate_low_111v =["","11111","11112","11113","11114","11115","11116","11117","11118","11119"];
		var cate_low_121  =["소분류","1급","2급","3급","4급","5급","6급","7급","8급","9급","10급"];
		var cate_low_122  =["소분류","1급","2급","3급","4급","5급","6급","7급","8급","9급","10급"];
		var cate_low_123  =["소분류","대법원장","대법관","고등법원장","일반법관","고시합격자"];
		var cate_low_124  =["소분류","치안총감/치안정감/치안감","경무관","총경","경정","경감","경위","경사","경장","순경"];
		var cate_low_125  =["소분류","소방총감/소방정감","소방감","소방준감","소방정","소방령","소방경","소방위","소방장","소방교","소방사"];
		var cate_low_126  =["소분류","대장/중장/소장/준장","대령/중령/소령","대위","중위","소위","준위","원사","상사","중사","하사"];
		var cate_low_127  =["소분류","1급","2급","3급","4급","5급","6급","7급","8급","9급","10급"];
		var cate_low_128  =["소분류","장학관","연구관","장학사","연구사"];
		var cate_low_129  =["소분류","1급","2급","3급","4급","5급","6급","7급","8급","9급","10급"];
		var cate_low_12A  =["소분류","국회의원","광역단체장","광역의원","기초단체장","기초단체의원"];
		var cate_low_121v =["","12111","12112","12113","12114","12115","12116","12117","12118","12119","1212A"];
		var cate_low_122v =["","12211","12212","12213","12214","12215","12216","12217","12218","12219","1222A"];
		var cate_low_123v =["","12311","12312","12313","12314","12315"];
		var cate_low_124v =["","12411","12412","12413","12414","12415","12416","12417","12418","12419","1242A"];
		var cate_low_125v =["","12511","12512","12513","12514","12515","12516","12517","12518","12519","1252A"];
		var cate_low_126v =["","12611","12612","12613","12614","12615","12616","12617","12618","12619","1262A"];
		var cate_low_127v =["","12711","12712","12713","12714","12715","12716","12717","12718","12719","1272A"];
		var cate_low_128v =["","12811","12812","12813","12814"];
		var cate_low_129v =["","12911","12912","12913","12914","12915","12916","12917","12918","12919","1292A"];
		var cate_low_12Av =["","12A11","12A12","12A13","12A14","12A15"];
		var cate_low_131  =["소분류","변호사","법무사","변리사","공인노무사","손해사정인"];
		var cate_low_132  =["소분류","의사","한의사","수의사","약사"];
		var cate_low_133  =["소분류","공인회계사","세무사","관세사"];
		var cate_low_134  =["소분류","기술사","건축사"];
		var cate_low_135  =["소분류","감정평가사"];
		var cate_low_136  =["소분류","기장","부기장"];
		var cate_low_131v =["","13111","13112","13113","13114","13115"];
		var cate_low_132v =["","13211","13212","13213","13214"];
		var cate_low_133v =["","13311","13312","13313"];
		var cate_low_134v =["","13411","13412"];
		var cate_low_135v =["","13511"];
		var cate_low_136v =["","13611","13612"];
		var cate_low_141  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","임시/파견","계약","특수직해당","임시/파견","계약"];
		var cate_low_142  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","임시/파견","계약","특수직해당","임시/파견","계약"];
		var cate_low_143  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","임시/파견","계약","특수직해당","아마운동선수","예술단원","도서관원","미술관원","공원관리","기타","임시/파견","계약"];
		var cate_low_144  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","임시/파견","계약","특수직해당","임시/파견","계약"];
		var cate_low_141v =["","14111","14112","14113","14114","14115","14116","14117","14118","14119","1411A","14121","14129","1412A"];
		var cate_low_142v =["","14211","14212","14213","14214","14215","14216","14217","14218","14219","1421A","14221","14229","1422A"];
		var cate_low_143v =["","14311","14312","14313","14314","14315","14316","14317","14318","14319","1431A","14321","14322","14323","14324","14325","14326","14327","14329","1432A"];
		var cate_low_144v =["","14411","14412","14413","14414","14415","14416","14417","14418","14419","1441A","14421","14429","1442A"];
		var cate_low_151  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","총장","학장","정교수","부교수","조교수","전임강사","시간강사"];
		var cate_low_152  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","총장","학장","정교수","부교수","조교수","전임강사","시간강사"];
		var cate_low_153  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","교장","교감","교사","기간제교사","보조/실험교사","서무과장","서무과직원"];
		var cate_low_154  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","교장","교감","교사","병설유치원교사","전임강사","시간강사"];
		var cate_low_155  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","원장","부원장","주임강사","강사","보모"];
		var cate_low_156  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","원장","부원장","주임강사","강사","시간강사"];
		var cate_low_151v =["","15111","15112","15113","15114","15115","15116","15117","15118","15121","15122","15123","15124","15125","15126","15127"];
		var cate_low_152v =["","15211","15212","15213","15214","15215","15216","15217","15218","15221","15222","15223","15224","15225","15226","15227"];
		var cate_low_153v =["","15311","15312","15313","15314","15315","15316","15317","15318","15321","15322","15323","15324","15325","15326","15327"];
		var cate_low_154v =["","15411","15412","15413","15414","15415","15416","15417","15418","15421","15422","15423","15424","15425","15426"];
		var cate_low_155v =["","15511","15512","15513","15514","15515","15516","15517","15518","15521","15522","15523","15524","15525"];
		var cate_low_156v =["","15611","15612","15613","15614","15615","15616","15617","15618","15621","15622","15623","15624","15625"];
		var cate_low_161  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","인턴","간호과장","이상","수간호사","간호사","간호조무사","의료기사/물리치료사"];
		var cate_low_162  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","인턴","간호과장","이상","수간호사","간호사","간호조무사","의료기사/물리치료사"];
		var cate_low_163  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","인턴","간호과장","이상","수간호사","간호사","간호조무사","의료기사/물리치료사"];
		var cate_low_161v =["","16111","16112","16113","16114","16116","16116","16117","16118","16121","16131","16132","16133","16134","16141"];
		var cate_low_162v =["","16211","16212","16213","16214","16216","16216","16217","16218","16221","16231","16232","16233","16234","16241"];
		var cate_low_163v =["","16311","16312","16313","16314","16316","16316","16317","16318","16321","16331","16332","16333","16334","16341"];
		var d = eval("cate_low_" + e);
		var k = eval("cate_low_" + e + "v");
		var target = document.getElementById("cd_worker_position");
		target.options.length = 0;
		for (x in d) {
			var opt = document.createElement("option");
			opt.value = k[x];
			//alert(opt.value);
			opt.innerHTML = d[x];
			target.appendChild(opt);
			$('#cd_worker_position').selectpicker('refresh');
		}
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
		} else {
            frmStepNextFromMobile();
        }
	}
	
	/*
	* 간편 등록 콜백 함수 (취소시)
	* 모바일에서 간편등록이 완료되면 호출 시킨다.
	*/
	function frmStepNextFromMobileCancle(){
		//frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14.crz'/>";
 		//frmloanApplyStep.submit();
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
		frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep7.crz'/>";
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
// 			location.href = "call://checkPasswordCert//" + result + "//" + noPerson + "//" + ssnPerson + "//" 
// 			+ bizLicence + "//" +hp + "//" + nhisStartRcptYm + "//" + nhisEndYm + "//" + ntsStartTaxYm + "//" + ntsEndTaxYm + "//" + ntsStartRcptYm + "//"
// 			+ ntsEndRcptYm + "//" + ntsStartIncomeY + "//" + ntsEndIncomeY + "//" + finYear;
			
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
			
			//공인인증 및 건강보험 국세청 스크래핑 실행 모바일 호출
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
        var cd_worker_position = $('#cd_worker_position').val();
        var cd_employee_type = $('#cd_employee_type').val();
        var jb_dt_join_view = $('#jb_dt_join_view').val();
		if(cd_worker_position != '' && cd_worker_position != null && cd_employee_type != '' && cd_employee_type != null && jb_dt_join_view != '' && jb_dt_join_view != null){
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
			<h1>직장/소득정보</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>직장/소득정보를 입력해주세요.</p>
			</div>
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">	
			<input type="hidden" name="nm_person" id="nm_person" value="${txFcTransmitVO.nm_person}"/>
			<input type="hidden" name="no_person" id="no_person" value="${txFcTransmitVO.no_person}"/>
			<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
			<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
			<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
			<input type="hidden" name="hp" id="hp" value="${txFcTransmitVO.hp1 }"/>
			<input type="hidden" name="ssn_person" id="ssn_person" value="${txFcTransmitVO.ssn_person }"/>
			<input type="hidden" name="loan_code" id="loan_code" value="01"/>
			<input type="hidden" name="kcb_di" id="kcb_di" value="${txFcTransmitVO.kcb_di }"/>
			<div class="form-inline">
				<div class="form-group">
					<label for="cd_occupational_name">직군</label>
					<input type="text" class="form-control" name="cd_occupational_name" id="cd_occupational_name" value="${ufn:getCodeName('cd_occupational', txFcTransmitVO.cd_occupational)}" readonly="readonly">
					<input type="hidden" class="form-control" name="cd_occupational" id="cd_occupational" value="${txFcTransmitVO.cd_occupational}">
				</div>
				<c:if test="${txFcTransmitVO.cd_occupational eq '1'}">
				<div class="form-group has-feedback">
					<label for="nm_comp">직장명</label>
					<input type="text" class="form-control" name="nm_comp" id="nm_comp" value="${txFcTransmitVO.nm_comp }" readonly="readonly">
<!-- 		            <button type="button" class="sch-btn"><span class="form-control-feedback btn-search">검색</span></button> -->
				</div>
				</c:if>
				<div class="form-group">
					<label for="cd_worker_position">직위</label>
					<select class="selectpicker" data-header="직위선택" name="cd_worker_position" id="cd_worker_position" onchange="chkValidate();">
					</select>
				</div>
				<div class="form-group">
					<label for="cd_employee_type">고용형태</label>
					<select class="selectpicker" data-header="고용형태선택" name="cd_employee_type" id="cd_employee_type" onchange="chkValidate();">
						${ufn:makeOptions("cd_employee_type", "고용형태선택", txFcTransmitVO.cd_employee_type)}
					</select>
				</div>
				<div class="form-group">
					<label for="jb_dt_join_view">입사년월</label>
					<input type="month" name="jb_dt_join_view" id="jb_dt_join_view" class="form-control slt-date" placeholder="" onchange="chkValidate();"/>
					<input type="hidden" name="jb_dt_join" id="jb_dt_join" class="form-control slt-date" placeholder="" />
				</div>
				<div class="form-group has-feedback">
					<label for="amt_year_income">연소득</label>
					<input type="tel" class="form-control" name="amt_year_income" id="amt_year_income" value="${txFcTransmitVO.amt_year_income }" readonly="readonly"/>
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
			</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="confirmed_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="updateTxFc();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
