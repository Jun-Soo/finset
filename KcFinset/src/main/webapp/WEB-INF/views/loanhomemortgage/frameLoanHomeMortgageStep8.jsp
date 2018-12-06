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
        var y_user_end     = getCookie("y_user_end"    );
        var n_user_end     = getCookie("n_user_end"    );
        var y_loan_already = getCookie("y_loan_already");
        var n_loan_already = getCookie("n_loan_already");
		if(y_user_end     == "true") { $("#checkbox1").click();}
		if(n_user_end     == "true") { $("#checkbox2").click();}
		if(y_loan_already == "true") { $("#checkbox3").click();}
		if(n_loan_already == "true") { $("#checkbox4").click();}
        /**
         * 다음화면에서 셋팅할 쿠키 초기화
         */
        setCookie('BUILDING_TYPE'      ,"");
        setCookie('REGION1_CODE'       ,"");
        setCookie('REGION2_CODE'       ,"");
        setCookie('REGION3_CODE'       ,"");
        setCookie('apartment_nm'       ,"");
        setCookie('FLOOR_PLAN_AREA'    ,"");
        setCookie('FLOOR_HIGHEST'      ,"");

    });
	
	function loanChk(yn) {
		if(yn == 'Y'){
			$('#checkbox1').prop('checked', true);
			$('#checkbox2').prop('checked', false);
		} else if(yn == 'N'){
			$('#checkbox1').prop('checked', false);
			$('#checkbox2').prop('checked', true);
		}
		
		if($("input:checkbox[id='checkbox1']").is(":checked") == false){
			if($("input:checkbox[id='checkbox2']").is(":checked") == false){
				return false;
			}
		}		
		if($("input:checkbox[id='checkbox3']").is(":checked") == false){
			if($("input:checkbox[id='checkbox4']").is(":checked") == false) {
				return false;
			}
		}
		affixBottom('show');
		
	}
	
	function lowIncomeChk(yn) {
		if(yn == 'Y'){
			$('#checkbox3').prop('checked', true);
			$('#checkbox4').prop('checked', false);
		} else if(yn == 'N'){
			$('#checkbox3').prop('checked', false);
			$('#checkbox4').prop('checked', true);
		}
		
		if($("input:checkbox[id='checkbox1']").is(":checked") == false){
			if($("input:checkbox[id='checkbox2']").is(":checked") == false){
				return false;
			}
		}		
		if($("input:checkbox[id='checkbox3']").is(":checked") == false){
			if($("input:checkbox[id='checkbox4']").is(":checked") == false){
				return false;
			}
		}
		
		affixBottom('show');
	}
	
	function loanApplyStepStep() {
		if ( !frmloanApplyStep.validateForm() ) return false;
		if($("input:checkbox[id='checkbox1']").is(":checked") == false){
			if($("input:checkbox[id='checkbox2']").is(":checked") == false){
				toastMsg('주택담보대출여부를 선택해주세요.');
				return false;
			}
		}		
		if($("input:checkbox[id='checkbox3']").is(":checked") == false){
			if($("input:checkbox[id='checkbox4']").is(":checked") == false){
				toastMsg('서민/실수요자여부를 선택해주세요.');
				return false;
			}
		}		
		frmloanApplyStep.action = "<c:url value='/m/loanhomemortgage/frameLoanHomeMortgageStep9.crz'/>";
		frmloanApplyStep.submit();
	}
	
	//tx_fc_transmit테이블에  신청인 정보 insert 하기 위한 함
	function updateTxFc(){
		if ( !frmloanApplyStep.validateForm() ) return false;
		if($("input:checkbox[id='checkbox1']").is(":checked") == false){
			if($("input:checkbox[id='checkbox2']").is(":checked") == false){
				toastMsg('주택담보대출여부를 선택해주세요.');
				return false;
			}
		}		
		if($("input:checkbox[id='checkbox3']").is(":checked") == false){
			if($("input:checkbox[id='checkbox4']").is(":checked") == false){
				toastMsg('서민/실수요자여부를 선택해주세요.');
				return false;
			}
		}
		var y_user_end     = $("#checkbox1").is(":checked");
		var n_user_end     = $("#checkbox2").is(":checked");
		var y_loan_already = $("#checkbox3").is(":checked");
		var n_loan_already = $("#checkbox4").is(":checked");

		setCookie("y_user_end"    ,y_user_end    );
		setCookie("n_user_end"    ,n_user_end    );
		setCookie("y_loan_already",y_loan_already);
		setCookie("n_loan_already",n_loan_already);
		var data = frmloanApplyStep.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/loanhomemortgage/modifyLoanREConditionInfo.json'/>",
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
			<h1>신청인 조건</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content" class="loan-quest">
	<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
		<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
		<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
		<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
		<input type="hidden" name="ssn_person" id="ssn_person" value="${txFcTransmitVO.ssn_person}"/>
		<div class="container-fluid bg-white">
			<p>
				가족(본인 및 세대원) 중에<br /> 주택담보대출이 있나요?
			</p>
			<ul class="list-group">
				<li class="list-group-item">
					<label>예</label>
					<div class="checkbox"><input type="checkbox" name="yn_loan_already" id="checkbox1" value="Y" onchange="loanChk('Y');"/></div>
				</li>
				<li class="list-group-item">
					<label>아니오</label>
					<div class="checkbox"><input type="checkbox" name="yn_loan_already" id="checkbox2" value="N" onchange="loanChk('N');"/></div>
				</li>
			</ul>
		</div>
		<div class="container-fluid bg-white">
			<p>
				서민/실수요자 인가요?
			</p>
			<ul>
				<li>- 무주택 세대주</li>
				<li>- 부부합산 연소득 6천만원<br />(생애최초구입자는 7천만원) 이하</li>
			</ul>
			<ul class="list-group">
				<li class="list-group-item">
					<label>예</label>
					<div class="checkbox"><input type="checkbox" name="yn_user_end" value="Y" id="checkbox3" onchange="lowIncomeChk('Y');"/></div>
				</li>
				<li class="list-group-item">
					<label>아니오</label>
					<div class="checkbox"><input type="checkbox" name="yn_user_end" value="N" id="checkbox4" onchange="lowIncomeChk('N');"/></div>
				</li>
			</ul>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" type="button" class="btn btn-lg btn-primary btn-block" onclick="updateTxFc();">확인</a>
		</div>
	</form>
	</section>
	<!-- //Content -->

</div>
</body>
</html>
