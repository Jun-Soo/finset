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
	
	 if(userAgent == "Android") {
			window.Android.setEndApp('Y');
	    }
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
		var chk_pwd = Number($("#chk_pwd").val());
		
		// 비밀번호 체크 ajax
		if ( !frmSuccessStep.validateForm() ) return false;
		var data = frmSuccessStep.ajaxSubmit();
		if(data == null) return false;
		$.ajax({
			url : "<c:url value='/m/person/fingerChkCode.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					frmSuccessStep.action = "<c:url value='/m/customercenter/frameCustomerAuthenticationSecurity.crz'/>";
					frmSuccessStep.submit();
				} else {
					//비밀번호 틀린 누적횟수 증가
					chk_pwd += 1;
					
					$("#chk_pwd").val(chk_pwd);
					$(".code-mark").removeClass("active");
					$(".form-shake").addClass("shake-effect").delay(1000).queue(function(){
						$(this).removeClass("shake-effect").dequeue();
					});
					$("#err_message").html(result.message+"("+chk_pwd+"/5)");
					if(chk_pwd == 5) {
						//비밀번호 틀린 누적횟수 초기화 - 비밀번호 5번모두 불일치
						chk_pwd = 0;
						
						frmSuccessStep.action = "<c:url value='/m/customercenter/frameCustomerAuthenticationSecurity.crz'/>";
						frmSuccessStep.submit();
					}
					for(var i=1; i<=4; i++){
						$('#pass_number'+i).val('');
					}
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
}

</script>
</head>
<body>
<div id="wrapper">
	<!-- Content -->
	<form name="frmSuccessStep" method="post">
	<input type="hidden" name="chk_pwd" id="chk_pwd" value="0" />
	<input type="hidden" name="returnUrl" value="<c:out value="${returnUrl}"/>">
		<section id="content">
			<div class="container security-code">
				<div class="security-code-wrap">
					<p>
						비밀번호를 입력해주세요.
						<span id="err_message" ></span>
					</p>
					<div class="code-group clearfix">
						<div class="code form-shake">
							<div class="mark-group">
								<label for="" class="code-mark">코드번호</label>
								<input type="password" class="form-control code-no" name="pass_number" id="pass_number1" maxlength="1" value="" placeholder="" onchange="successStep();" 
								/>
							</div>
							<div class="mark-group">
								<label for="" class="code-mark">코드번호</label>
								<input type="password" class="form-control code-no" name="pass_number" id="pass_number2" maxlength="1" value="" placeholder="" onchange="successStep();" />
							</div>
							<div class="mark-group">
								<label for="" class="code-mark">코드번호</label>
								<input type="password" class="form-control code-no" name="pass_number" id="pass_number3" maxlength="1" value="" placeholder="" onchange="successStep();" />
							</div>
							<div class="mark-group">
								<label for="" class="code-mark">코드번호</label>
								<input type="password" class="form-control code-no" name="pass_number" id="pass_number4" maxlength="1" value="" placeholder="" onchange="successStep();" />
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
					<li class="btn-none"><button type="button" class="btn btn-lg btn-block">&nbsp;</button></li>
					<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="0">0</button></li>
					<li><button type="button" class="btn btn-lg btn-block btn-backspace">←</button></li>
				</ul>
			</div>
		</section>
	</form>
	<!-- //Content -->
</div>
</body>
</html>
