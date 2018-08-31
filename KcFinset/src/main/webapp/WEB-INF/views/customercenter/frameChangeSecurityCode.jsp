<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko" class="page-full">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	if(userAgent == "Android") {
		window.Android.setEndApp('Y');
	}
	// 코드번호 입력
	$(".keypad [data-value]").each(function(){
		$(this).click(function(){
			var output = $(this).attr("data-value");
			$(".code .code-no").each(function(){
				if( $(this).val().length === 0 ) {
					$(this).val(output);
					$(this).prev(".code-mark").addClass("active");
					successStep();
					return false;					
				}
				
			});
		});
	});
	
	// 코드번호 삭제
	jQuery.fn.reverse = [].reverse;
	$(".btn-backspace").click(function(){
		$(".code .code-no").reverse().each(function(){
			if( $(this).val().length !== 0 ) {
				$(this).val("");
				$(this).prev(".code-mark").removeClass("active");
				return false;					
			}
			
		});
	});
});
function successStep() {
	var chk = true;
	for(var i=1; i<=4; i++){
		if($("#pass_number"+i).val() == "undefined" || $("#pass_number"+i).val() == '') {
			chk = false;
			break;
		}
	}
	if(chk == true){
		if (!frmSuccessStep.validateForm()) return false;
		frmSuccessStep.submit();
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
			<h1>비밀번호 설정</h1>
			<div class="input-group-btn">
				<button class="ui-nav nav-menu" type="button" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<form name="frmSuccessStep" action="<c:url value='/m/customercenter/frameChangeSecurityCodeCertify.crz'/>" method="post">
	<input type="hidden" name="returnUrl" value="<c:out value="${returnUrl}"/>">
		<section id="content">
			<div class="security-code-wrap">
				<p>
					비밀번호를 입력해주세요.
				</p>
				<div class="code-group clearfix">
					<div class="code form-shake">
						<div class="mark-group">
							<label for="" class="code-mark">코드번호</label>
							<input type="password" class="form-control code-no" name="pass_number" id="pass_number1" maxlength="1" value="" placeholder="" validate="required; label:비밀번호;numeric;" onchange="successStep();" autocomplete="off"/>
						</div>
						<div class="mark-group">
							<label for="" class="code-mark">코드번호</label>
							<input type="password" class="form-control code-no" name="pass_number" id="pass_number2" maxlength="1" value="" placeholder="" validate="required; label:비밀번호;numeric;" onchange="successStep();" autocomplete="off"/>
						</div>
						<div class="mark-group">
							<label for="" class="code-mark">코드번호</label>
							<input type="password" class="form-control code-no" name="pass_number" id="pass_number3" maxlength="1" value="" placeholder="" validate="required; label:비밀번호;numeric;" onchange="successStep();" autocomplete="off"/>
						</div>
						<div class="mark-group">
							<label for="" class="code-mark">코드번호</label>
							<input type="password" class="form-control code-no" name="pass_number" id="pass_number4" maxlength="1" value="" placeholder="" validate="required; label:비밀번호;numeric;" onchange="successStep();" autocomplete="off"/>
						</div>
					</div>
				</div>
			</div>
			<ul class="keypad">
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="1">1</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="2">2</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="3">3</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="4">4</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="5">5</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="6">6</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="7">7</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="8">8</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="9">9</button></li>
				<li class="btn-none"><button type="button" class="btn btn-lg btn-block btn-key">&nbsp;</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="0">0</button></li>
				<li><button type="button" class="btn btn-lg btn-block btn-backspace">←</button></li>
			</ul>
		</section>
	</form>
	<!-- //Content -->
</div>
</body>
</html>
