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

	
	function ssn2Chk(){
		if(frmloanApplyStep.ssn2.value.length == 7){
			affixBottom('show');
		} else {
			affixBottom('hide');
		}
	}
	
	//tx_fc_transmit테이블에  신청인 정보 insert 하기 위한 함
	function insertTxFc(){
		if ( !frmloanApplyStep.validateForm() ) return false;
		var chk = validateionChk();
		if(chk == false){ return false;}
		//frmloanApplyStep.ssn_person.value = frmloanApplyStep.ssn1.value + frmloanApplyStep.ssn2.value;
		var data = frmloanApplyStep.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/loanworker/insertTxFc.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					frmloanApplyStep.no_bunch.value = result.no_bunch;
					loanApplyStepStep();
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	function loanApplyStepStep() {
		if ( !frmloanApplyStep.validateForm() ) return false;
		var chk = validateionChk();
		if(chk == false){ return false;}
		//frmloanApplyStep.ssn_person.value = frmloanApplyStep.ssn1.value + frmloanApplyStep.ssn2.value;
		
		frmloanApplyStep.action = "<c:url value='/m/loanhomemortgage/frameLoanHomeMortgageStep8.crz'/>";
		frmloanApplyStep.submit();
	}
	
	function validateionChk(){
		var regNumber = /^[0-9]*$/;
		if(!regNumber.test($('#ssn2').val())) {
		    toastMsg('주민등록번호 뒷자리 숫자만 입력해주세요.');
		    $('#ssn2').val('');
		    $('#ssn2').focus();
		    return false;
		} else if(frmloanApplyStep.ssn2.value.length != 7) {
			toastMsg('주민등록번호 뒷자리 길이가 정확하지 않습니다.');
			$('#ssn2').val('');
			$('#ssn2').focus();
			return false;
		}
		
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
			ssn2Chk();
		} else {
			$("#ssn2").val("");	// 
			$("input[name=encPwd]").val("");	// 
			ssn2Chk();
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
				<p>실명확인을 위해 주민등록번호<br>뒷자리를 입력해주세요.</p>
			</div>
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
			<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
			<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
			<input type="hidden" name="ssn1" id="ssn1" value="${personVO.ssn_person }"/>
			<input type="hidden" name="ssn_person" value=""/>
			<input type="hidden" name="no_person" value="${personVO.no_person}"/>
			<input type="hidden" name="no_bunch" id="no_bunch" value=""/>
			<div class="form-block">
				<div class="form-group">
					<label class="sr-only">고객명</label>
					<input type="text" value="${personVO.nm_person }" class="form-control" name="nm_person" placeholder="" readonly="readonly">
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
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="insertTxFc();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
