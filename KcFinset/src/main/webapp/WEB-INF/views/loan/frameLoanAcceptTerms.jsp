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
	
	function loanApplyStepStep() {
		if ( !frmloanApplyStep.validateForm() ) return false;
		var cd_agree = '';
	    $('input:checkbox[id=chkBox]').each(function() {
	        if($(this).is(':checked')) {
	        	if(cd_agree == null || cd_agree == '') {
	        		 if($(this).val() !=  "undefined" || $(this).val() != '') {
		        		 cd_agree += ($(this).val());	 
	        		 }
	        	 } else {
	        		 if($(this).val() !=  "undefined" || $(this).val() != '') {
			        	 cd_agree += ","+($(this).val());
	        		 }
	        	 } 
	         }
	    });
	    frmloanApplyStep.cd_agree.value = cd_agree;
	    if(frmloanApplyStep.loan_code.value == '01'){
	    	frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanWorkerStep1.crz'/>"
      		frmloanApplyStep.submit();
	    } else if (frmloanApplyStep.loan_code.value == '02') {
	    	frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanSelfEmployedStep1.crz'/>"
      		frmloanApplyStep.submit();
	    } else if (frmloanApplyStep.loan_code.value == '03') {
	    	frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanRealEstateStep1.crz'/>"
      		frmloanApplyStep.submit();
	    } else if (frmloanApplyStep.loan_code.value == '04') {
	    	frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanCarStep1.crz'/>"
      		frmloanApplyStep.submit();
	    }
// 	    frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanStep2.crz'/>"
//   		frmloanApplyStep.submit();
	}
	
	//취소버튼 클릭시 대출 초기 페이지로
	function loanApplyCancel(){
		if(!confirm("취소를 하시겠습니까?")) return false;
		frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanApply.crz'/>"
		frmloanApplyStep.submit();
	}
</script>

</head>
<body>
<form name="frmloanApplyStep" id="frmloanApplyStep"  method="post">
<input type="hidden" name="loan_code" value="${loan_code }">
<input type="hidden" name="title">
<input type="hidden" name="cd_agree">

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
			<li class="active"><span>1. 약관동의</span></li>
			<li><span>2</span></li>
			<li><span>3</span></li>
			<li><span>4</span></li>
		</ul>
		<div class="alert-desc">
			<ul>
				<li>하단의 동의서 내용을 반드시 확인하시고 필수항목은 반드시 동의하셔야 맞춤금융상품 안내가 가능합니다.</li>
				<li>신용정보 조회 동의 후, 취소는 불가능합니다.</li>
				<li>본 서비스는 고객님의 신용/소득정보로 조회한 결과만 안내되며, 각 금융사의 정책에 따라 최종 결과는 다를 수 있습니다.</li>
			</ul>				
		</div>
		<h2 class="h2">약관동의</h2>
		<div class="container">			
			<p class="help-block">아래 약관 내용을 확인 및 동의해주시기 바랍니다.</p>
		</div>
		<div class="agree-block">
			<ul class="list-group">
				<li class="list-group-item">
					<div class="checkbox pull-left">
						<label><input type="checkbox" id="chkBox" value="01" name="cd_agree1" validate="checkbox-required:cd_agree1;label:개인(신용)정보 조회 및 수집·이용·제공동의;"/> [필수] 개인(신용)정보 조회 및 수집·이용·제공동의</label>
						<button type="button" class="btn-terms" onclick="popAcceptTerms1();">약관보기</button>
					</div>			
				</li>
				<li class="list-group-item">
					<div class="checkbox pull-left">
						<label><input type="checkbox" id="chkBox" value="02" name="cd_agree2" validate="checkbox-required:cd_agree2;label:개인(신용)정보 수집·이용·제공동의;"/> [필수] 개인(신용)정보 수집·이용·제공동의</label>
						<button type="button" class="btn-terms" onclick="popAcceptTerms1();">약관보기</button>
					</div>				
				</li>
				<li class="list-group-item">
					<div class="checkbox pull-left">
						<label><input type="checkbox" id="chkBox" value="03" name="cd_agree3" validate="checkbox-required:cd_agree3;label:간편등록 조회 및 수집·이용·제공동의;"/> [필수] 간편등록 조회 및 수집·이용·제공동의</label>
						<button type="button" class="btn-terms" onclick="popAcceptTerms1();">약관보기</button>
					</div>			
				</li>
				<li class="list-group-item">
					<div class="checkbox pull-left">
						<label><input type="checkbox" id="chkBox" value="04" name="cd_agree4" /> [선택] 개인(신용)정보 수집·이용 동의</label>
						<button type="button" class="btn-terms" onclick="popAcceptTerms1();">약관보기</button>
					</div>			
				</li>
			</ul>
			<ul>
				<li class="checkbox-total">
					<div class="checkbox">
						<label>
							<input type="checkbox" name="checkbox5" validate="checkbox-required:checkbox5;label:약관동의;"/> 본인은 위 내용을 충분히 이해하고 확인하였습니다.
						</label>
					</div>
				</li>
			</ul>
		</div>
		<div class="bottom-over">
			<div class="col-xs-6">
				<button type="button" onclick="loanApplyCancel()" class="btn btn-lg btn-default btn-block">취소</button>
			</div>
			<div class="col-xs-6"> 
				<a role="button" onclick="loanApplyStepStep()" class="btn btn-lg btn-primary btn-block">다음</a>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</form>
</body>
</html>
