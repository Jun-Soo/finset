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
		<%--alert("sex=${sex}\nbirthDay=${birthDay}\ncd_tel=${cd_tel}");--%>
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
            $("#dvCheckAll").addClass("check");
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
		<c:if test="${site ne 'LOCAL'}">
        var ssn2 = $('#encPwd').val();
        if(ssn2.length < 1) {
            toastMsg("주민번호 뒷자리를 입력해주세요.");
            $('#ssn2').click();
            return;
        }
		</c:if>
		<c:if test="${site eq 'LOCAL'}">
        var ssn2 = $('#ssn2').val();
        $('#encPwd').val(ssn2);
        </c:if>
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
// 							$("#confirmed_certify").attr("disabled", false); //확인버튼 활성화
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
                            $('#smsCertNo').click();
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

    //tx_fc_transmit테이블에  신청인 정보 insert 하기 위한 함
    function insertTxFc(){
	    var encPwd = $('#encPwd').val();
	    // alert("insertTxFc:encPwd="+encPwd);
        if ( !frmCertifyStep.validateForm() ) return false;
        $('#telComCd').removeAttr("disabled");
        // alert("insertTxFc:2");
        // var chk = validateionChk();
        // if(chk == false){ return false;}
        //frmloanApplyStep.ssn_person.value = frmloanApplyStep.ssn1.value + frmloanApplyStep.ssn2.value;
        var data = $("#frmCertifyStep").serialize();
        if(data == null) return false;

        $.ajax({
            url : "<c:url value='/m/loanworker/insertTxFc.json'/>",
            data : data,
            contentType : "application/x-www-form-urlencoded; charset=UTF-8",
            type : "POST",
            async : false,
            success : function (result) {
                // debug("insertTxFc:\nresult.result="+result.result+"\nresult.no_bunch="+result.no_bunch);
                if(result.result == '00') {
                    $('#telComCd').removeAttr("disabled");
                    frmCertifyStep.no_bunch.value = result.no_bunch;
                    loanApplyStepStep();
                }
            },
            error : function (e) {
                alert("insertTxFc:encPwd="+encPwd);
                errMsg(e);
            }
        });
    }

    function loanApplyStepStep() {
	    // alert("loanApplyStepStep");
        if ( !frmCertifyStep.validateForm() ) return false;
        // var chk = validateionChk();
        // if(chk == false){ return false;}
        //frmCertifyStep.ssn_person.value = frmCertifyStep.ssn1.value + frmCertifyStep.ssn2.value;

        frmCertifyStep.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep7.crz'/>";
        frmCertifyStep.submit();
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
        // alert("confirmedCertify");

        if(frmCertifyStep.smsCertNo.value == '' || frmCertifyStep.smsCertNo.value == 'undefined') return false;
		var regExp = /^[0-9]+$/;
		if ( !frmAcceptTerms.validateForm() ) return false;
		if ( !frmCertifyStep.validateForm() ) return false;
		var data = frmCertifyStep.ajaxSubmit();
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
			    // alert("본인인증결과 :result.result="+result.result);
				if(result.result == '00') {
					//submit 전 disable 풀어줘야 넘어감
					$('#telComCd').removeAttr("disabled");
					frmCertifyStep.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep7.crz'/>";
					frmCertifyStep.kcb_di.value = result.kcb_di;
					frmCertifyStep.kcb_ci.value = result.kcb_ci;
					frmCertifyStep.kcb_cp.value = result.kcb_cp;
                    insertTxFc();
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
        var val = $("#ssn_birth").val();
        $("#ssn1").val(val);
        if(val.length == 6) {
            $("#ssn1").val(val);
            showSecureKeypad();
            // frmCertifyStep.sex.focus();
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
		
		if(gubun == 'hp1') {
			frmAcceptTerms.action = "<c:url value='/m/credit/frameAcceptTerms1.crz'/>";
			frmAcceptTerms.submit();
		} else if(gubun == 'hp2') {
			frmAcceptTerms.action = "<c:url value='/m/credit/frameAcceptTerms2.crz'/>";
			frmAcceptTerms.submit();
		} else if(gubun == 'hp3') {
			frmAcceptTerms.action = "<c:url value='/m/credit/frameAcceptTerms3.crz'/>";
			frmAcceptTerms.submit();
		} else if(gubun == 'hp4') {
			frmAcceptTerms.action = "<c:url value='/m/credit/frameAcceptTerms4.crz'/>";
			frmAcceptTerms.submit();
		}
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
        } else {
            $("#ssn2").val("");	//
            $("input[name=encPwd]").val("");	//
        }
        if('${cd_tel}' == '') {
            // alert("toggleSelect:telComCd");
            toggleSelect("telComCd");
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
			<h1>본인확인</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content">
		<form name="frmAcceptTerms" method="post">
        <div class="container">
            <div class="check-all">
                <div class="checkbox" id="dvCheckAll"><label for="checkall"><input type="checkbox" id="checkall"> 전체 약관 동의</label></div>
            </div>
        </div>
		<!-- panel -->
		<div class="panel-group agree-panel" id="termsList">
			<div class="panel-collapse collapse in" id="panel1">
				<ul class="list-group">
					<li class="list-group-item">
						<label>개인정보수집 및 이용동의</label>
						<button type="button" class="btn-terms" onclick="popAcceptTerms('hp1');">약관보기</button>
					</li>
					<li class="list-group-item">
						<label>고유식별처리 동의</label>
						<button type="button" class="btn-terms" onclick="popAcceptTerms('hp2');">약관보기</button>
					</li>
					<li class="list-group-item">
						<label>통신사 이용약관 동의</label>
						<button type="button" class="btn-terms" onclick="popAcceptTerms('hp3');">약관보기</button>
					</li>
					<li class="list-group-item">
						<label>서비스 이용약관 동의</label>
						<button type="button" class="btn-terms" onclick="popAcceptTerms('hp4');">약관보기</button>
					</li>
				</ul>
			</div>
		</div>
		</form>
		<!-- //panel -->
		<div class="container">
			<form name="frmCertifyStep" id="frmCertifyStep" method="post" validate="remove_tag;">
			<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
			<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
			<input type="hidden" name="nation" value="1" >
			<input type="hidden" name="kcb_ci" >
			<input type="hidden" name="kcb_di" >
			<input type="hidden" name="kcb_cp" >
            <input type="hidden" name="no_bunch" id="no_bunch" value=""/>
			<input type="hidden" name="no_person" value="${personVO.no_person}"/>
			<input type="hidden" name="svcTxSeqno" >
			<input type="hidden" name="smsReSndYn" id="smsReSndYn">
                <input type="hidden" name="ssn_person" value=""/>
                <input type="hidden" name="bgn"  />
			<div class="form-block form-input-block-confrim">
				<div class="form-group">
					<label for="nm_person" class="sr-only">이름</label>
                    <input type="text" name="nm_person" id="nm_person" maxlength="10" class="form-control" value="${nm_person}"
                           placeholder="이름을 입력하세요." validate="trim;strip_ws;label:이름;restrictChars;" autocomplete="off" onkeyup="chkValidate();"/>
				</div>
				<div class="form-group">
					<label for="" class="sr-only">주민등록번호 앞 7자리</label>
					<div class="row">
						<div class="col-xs-5">
                            <input type="number" name="ssn_birth" id="ssn_birth"
                                   class="form-control xe_required" maxlength="6" value="${birthDay}"
                                   validate=" label:주민등록번호 앞자리;numeric;" autocomplete="off"
                                   placeholder="주민등록번호 앞자리"  onkeyup="ssnBirthChk(); chkValidate();"/>
                            <input type="hidden" name="birthday" id="birthday" class="form-control" maxlength="8"  autocomplete="off" />
						</div>
						<div class="col-xs-1">
							<p class="form-control-static">-</p>
						</div>
						<div class="col-xs-6">
							<label for="ssn2" class="sr-only">주민번호 7번째 자리</label>
                            <input type="password" class="form-control xe_required" name="ssn2" id="ssn2" onclick="showSecureKeypad();"
                                   placeholder="주민번호뒷자리" maxlength="7"  autocomplete="off">
                            <input type="hidden" name="encPwd" id="encPwd" />
                            <input type="hidden" name="sex" id="sex" value="${sex}"/>
                            <input type="hidden" name="ssn1" id="ssn1" value="${birthDay}"/>
						</div>
						<%--<div class="col-xs-3">--%>
							<%--<p class="form-control-static">* * * * * *</p>--%>
						<%--</div>--%>
                    </div>
				</div>
				<div class="form-group">
					<div class="form-input-inline">
						<span class="" aria-hidden="true">
							<select name="telComCd" id="telComCd" validate="select-one;label:통신사" class="selectpicker" data-header="옵션선택">
								<option value="">통신사</option>
								<option value="01" <c:if test="${cd_tel eq '01'}">selected</c:if>>SKT</option>
								<option value="02" <c:if test="${cd_tel eq '02'}">selected</c:if>>KT</option>
								<option value="03" <c:if test="${cd_tel eq '03'}">selected</c:if>>LGU+</option>
								<option value="04" <c:if test="${cd_tel eq '04'}">selected</c:if>>SK알뜰폰</option>
								<option value="05" <c:if test="${cd_tel eq '05'}">selected</c:if>>KT알뜰폰</option>
								<option value="06" <c:if test="${cd_tel eq '06'}">selected</c:if>>LG알뜰폰</option>
						<label for="hp" class="sr-only">휴대폰 번호</label>
						<input type="tel" name="hp" id="hp" class="form-control" maxlength="11" value="${hp}" />
<%-- 						<input type="tel" name="hp" id="hp" class="form-control" maxlength="11" value="${personHp }" readonly="readonly"/> --%>
							</select>
						</span>
						<span class="form-control-feedback" aria-hidden="true"><a role="button" id="req_certification" class="btn btn-primary btn-cert-no" onclick="kcmRequestCertNo();">인증요청</a></span>
					</div>	
				</div>
				<div class="form-group has-feedback" id="cert_no_conteiner">
					<label for="smsCertNo" class="sr-only">인증번호</label>
					<input type="tel" name="smsCertNo" id="smsCertNo" class="form-control" placeholder="인증번호를 입력하세요" autocomplete="off" onkeyup="smsCertNoChk();">
	                <span id="countdown" class="form-control-feedback cert-num" aria-hidden="true">00:00</span>
				</div>
			</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="confirmed_div">
			<button id="confirmed_certify" onclick="confirmedCertify()" class="btn btn-lg btn-primary btn-block" >확인</button>	
		</div>
	</div>
	
	
	<!-- //Content -->
</div>
</body>
</html>
