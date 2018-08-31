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
	});
	
// 	$(function() {
// 	    var charLimit = 1;
// 	    $(".inputs").keydown(function(e) {

// 	        var keys = [8, 9, /*16, 17, 18,*/ 19, 20, 27, 33, 34, 35, 36, 37, 38, 39, 40, 45, 46, 144, 145];

// 	        if (e.which == 8 && this.value.length == 0) {
// 	            $(this).prev('.inputs').focus();
// 	        } else if ($.inArray(e.which, keys) >= 0) {
// 	            return true;
// 	        } else if (this.value.length >= charLimit) {
// 	            $(this).next('.inputs').focus();
// 	            return false;
// 	        } else if (e.shiftKey || e.which <= 48 || e.which >= 58) {
// 	            return false;
// 	        }
// 	    }).keyup (function () {
// 	        if (this.value.length >= charLimit) {
// 	            $(this).next('.inputs').focus();
// 	            return false;
// 	        }
// 	    });
// 	});
	
// 	function successStep() {
// 		var chk = true;
// 		for(var i=1; i<=4; i++){
// 			if($("#pass_number"+i).val() == "undefined" || $("#pass_number"+i).val() == '') {
// 				chk = false;
// 				break;
// 			}
// 		}
// 		if(chk == true){
// 			if (!frmSuccessStep.validateForm()) return false;
// 			frmSuccessStep.submit();
// 		}
// 	}
</script>

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
			// $(".code .mark-group").each(function(){
				// $(this).addClass("active");
			// });
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
		var pass_number = document.getElementsByName("pass_number");
		var pass_person = '';
		for(var i=0; i < pass_number.length; i++){
			pass_person += pass_number[i].value;
		}
		if(frmSuccessStep.pass_person.value != pass_person){
			for(var i=1; i<=4; i++){
				$('#pass_number'+i).val('');
				$(".code-mark").removeClass("active");
			}
			$(".form-shake").addClass("shake-effect").delay(1000).queue(function(){
				$(this).removeClass("shake-effect").dequeue();
			});
			$('#chk_pwd').html('입력하신 비밀번호가 일치하지 않습니다.');
			return false;
		}
		var data = frmSuccessStep.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/m/person/changePwd.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				
				
				if(result.result == '00') {
					toastMsg('비밀번호설정이 완료 되었습니다.');
				} else { //본인인증 값 없을 때 오류 처리
					toastMsg(result.message);
				}
				
				if("${yn_reload}" == 'Y'){
					window.Android.closeWebView();
				} else {
					frmSuccessStep.action = "<c:url value='/m/credit/frameCreditInfoMain.crz'/>";
					setTimeout(function(){
						frmSuccessStep.submit();
					}, 2000);	
				}
				
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
}
//비밀번호 오류시 흔들림
// $(document).ready(function() {
// 	$(".btn").click(function(){
// 		$(".form-shake").addClass("shake-effect").delay(1000).queue(function(){
// 			$(this).removeClass("shake-effect").dequeue();
// 		});
// 	});
// });
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
			<h1>비밀번호 확인</h1>
			<div class="input-group-btn">
				<button class="ui-nav nav-menu" type="button" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<form name="frmSuccessStep" method="post">
	<input type="hidden" name="pass_person" value="${pass_person}" placeholder="" validate="required; label:비밀번호;"/>
	<input type="hidden" name="returnUrl" value="<c:out value="${returnUrl}"/>">
		<section id="content">
			<div class="security-code-wrap">
				<p> 비밀번호를 다시 입력해주세요.
					<!-- <span id="chk_pwd">비밀번호를 다시 입력해주세요.</span> -->
					<span id="chk_pwd"> </span>
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
