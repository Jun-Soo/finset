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
	// 공인인증서 유무 체크
	function checkExistCert() {
		if(userAgent == "iOS") {
			 //공인인증서 유무 체크 결과 콜백 이벤트
            Jockey.on("resultCheckCert" , function(param) {
            	var iscert = false;
            	if(param.isCert == 1) iscert = true;
            	resultCheckCert(iscert);
            });
            Jockey.send("checkExistCert");
		}
		else if(userAgent == "Android") {
			window.Android.checkExistCert();
		}
	}
 
	//공인인증서 유무 결과 (모바일에서 호출)
	function resultCheckCert(isCert) {
		if(isCert) {	// 공인인증서가 있을 경우
			frmFcCertList();
		} else {		// 공인인증서가 없을 경우
			alert('공인인증서가 없습니다.');
			frmLogin.submit();
		}
	}

	//자동스크래핑 가능 금융사 조회
	function frmFcCertList() {
		var noPerson = $('#j_username').val();
		var nmPerson = $('#nm_person').val();
		var bankCode = $('#bank_code').val();
		var cardCode = $('#card_code').val();

		if(userAgent == "iOS") {
			/* Jockey.on("frmFcListNextFromMobile" , function(param) {
				frmFcListNextFromMobile();
			});
 
			Jockey.send("checkAvaliableScrapList" , {
				noPerson : noPerson,
				bankCode : bankCode
			}); */
			//do nothing
		} else if(userAgent == "Android") {
			window.Android.checkAvaliableScrapList(noPerson, bankCode, cardCode, nmPerson);
		}
	}
	
	//자동 스크래핑 등록 완료 시 (모바일에서 호출)
	function frmFcListNextFromMobile() {
		frmLogin.submit();
	}
</script>

<script type="text/javascript">
$(document).ready(function() {
	if(userAgent == "Android") {
		window.Android.checkFingerPrint();
	}
	else if(userAgent == "iOS") {
		//지문인식 가능여부 체크 결과 콜백 이벤트
		Jockey.on("resultCheckFingerPrint", function(param) {
			resultCheckFingerPrint(param.result);
		});
		
		Jockey.send("checkFingerPrint");
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
					if($('#chkFingerPrint').val() == 'Y'){
						frmSuccessStep.action= "<c:url value='/m/base/frameFingerCheck.crz'/>";
						setTimeout(function(){
	 						frmSuccessStep.submit();
	 					}, 2000);
					} else {
						$("#j_username").val(result.no_person);
						$("#j_password").val(pass_person);
						if(userAgent == "Android" && $('#site').val() != "REAL") {
							checkExistCert();
						}
						else	{
							frmLogin.submit();
						}
					}
					toastMsg('비밀번호설정이 완료 되었습니다.');
					
 					
				}
			},
			error : function (e) {
                errMsg(e);

			}
		});
	}
}

function resultCheckFingerPrint(result){
	 if(result == true || result == 1){
		 $('#chkFingerPrint').val('Y');
	 } else {
		 $('#chkFingerPrint').val('N');
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
	<input type="hidden" name="chkFingerPrint" id="chkFingerPrint" value=""/>
	<input type="hidden" name="bank_code" id="bank_code" value="${bank_code}"/>
	<input type="hidden" name="card_code" id="card_code" value="${card_code}"/>
	<input type="hidden" id="site" name="site" value="${site}"/>
	<input type="hidden" id="nm_person" name="nm_person" value="${nm_person}"/>
		<!--  -->
		<section id="content">
			<div class="container">
				<div class="security-code-wrap security-passw">
					<p>
						비밀번호를 다시 입력해주세요.
<!-- 						<span id="chk_pwd">비밀번호를 입력해주세요 </span> -->
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
				</div>
			</div>
		</section>
	</form>
	<form name="frmLogin" action="<c:url value='/j_spring_security_check'/>" method="POST">
		<input type="hidden" id="j_username" name="j_username" class="form-control"/>
		<input type="hidden" id="j_password" name="j_password" class="form-control"  autocomplete="off"/>
	</form>
	<!-- //Content -->
</div>
</body>
</html>
