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
	
	FastClick.attach(document.body);
	
	var msg = "${msg}";
	if(msg != null && msg != '') {
		toastMsg(msg);
	}
	
	if(userAgent == "Android") {
		window.Android.settingPush("${yn_push}");
		window.Android.settingPushType("${cd_push}");
		if("${yn_fingerprint}" == 'Y'){
			window.Android.initFingerPrint();
		}
	}
	else if(userAgent == "iOS") {
		//앱 푸쉬 설정
		Jockey.send("settingPush" , {
			yn_push : "${yn_push}"
		});
		//앱 알림 설정
// 		Jockey.send("settingPushType" , {
// 			cd_push : "${cd_push}"
// 		});
		
		//지문인식 결과 콜백 이벤트
		Jockey.on("resultFingerPrint",function(param) {
			resultFingerPrint(param.result);
		});
		
		if("${yn_fingerprint}" == 'Y'){
			Jockey.send("initFingerPrint" );
		}
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
	
	$("#chk_pwd").val("${cnt_fail_pwd}");
	var chk_pwd = Number($("#chk_pwd").val());
	if(chk_pwd > 0) {
		$("#err_message").html("비밀번호를 "+chk_pwd+"회 실패한 이력이 있습니다.");	
	}
	
	if(userAgent == "Android") {
		window.Android.setEndApp('Y');
	}
});

//비밀번호 틀린횟수 변경
function modifyPwdFailCnt(mode, cnt_fail){
	var data = {"cnt_fail_mode":mode, "cnt_fail":cnt_fail}; 
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/m/person/modifyPwdFailCnt.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			
		},
		error : function (e) {
            errMsg(e);
		}
	});
}

function resultFingerPrint(result){
	if($("#chk_finger").val() ==  "undefined" || $("#chk_finger").val() == '') {
		$("#chk_finger").val('0');
	}
	var chk_finger = Number($("#chk_finger").val());
	if(result == true || result == 1){ //지문인식 성공
		if(userAgent == "Android") {
			window.Android.closeFingerPrint();
			
			if("${yn_reload}" == 'Y'){
				window.Android.closeWebView();
				return false;
			}
		}
		
 		$("#j_username").val("${no_person}");
		$("#j_password").val("${no_token}");
		frmLogin.submit();
		
	} else {
		//지문 틀린 누적횟수 증가
		chk_finger += 1;
		modifyPwdFailCnt("finger", chk_finger);
		
		$("#chk_finger").val(chk_finger);
		if(chk_finger <5){
			$("#err_message").html("다시 시도해 주세요. "+"("+chk_finger+"/5)");
		}
		if(chk_finger == 5) { //지문인식 5번 모두 틀린 경우
			$("#err_message").html("지문이 비활성화 됩니다.");
			setTimeout(function(){
				$("#err_message").html("비밀번호를 입력하세요.");
			}, 1000);
			if(userAgent == "Android") {
				window.Android.closeFingerPrint();
			}
			$("#yn_fingerprint").val("N");
			var data = {"yn_fingerprint" : $("#yn_fingerprint").val()};
// 			if(data == null) return false;
				$.ajax({
					url : "<c:url value='/m/person/modifyFingerPrint.json'/>",
					data : data,
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					type : "POST",
					async : false,
					success : function (result) {
						
					},
					error : function (e) {
						errMsg(e);
					}
				});
		}
		return false;
	}
}

function successStep() {
	
	var chk = true;
	var pass = '';
	for(var i=1; i<=4; i++){
		if($("#pass_number"+i).val() == "undefined" || $("#pass_number"+i).val() == '') {
			chk = false;
			break;
		}
		pass += $("#pass_number"+i).val();
	}
	if(chk == true){
		if($("#chk_pwd").val() ==  "undefined" || $("#chk_pwd").val() == '') {
			$("#chk_pwd").val('0');
		}
		var chk_pwd = Number($("#chk_pwd").val());
		
		// 비밀번호 체크 ajax
		if ( !frmSuccessStep.validateForm() ) return false;
		var data = frmSuccessStep.ajaxSubmit();
		if(data == null) return false;
		$.ajax({
			url : "<c:url value='/m/person/loginChkCode.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') { //비밀번호 일치
					if(userAgent == "Android") {
						// 스플래시 ON
						//TODO 앱 정상배포시 try-catch 제거하고 스크립트만 호출해야함
						try {
							window.Android.splash("Y");
							
							if("${yn_reload}" == 'Y'){
								window.Android.closeWebView();
								return false;
							}
						} catch(e) {
							console.log(e);
						}
					}
					else if(userAgent == "iOS")
					{
						Jockey.send("splashView",{
							yn_splash : "Y"
						});
					}	
					
					//frmSuccessStep.action = "<c:url value='/m/login/frameKcbCrawling.crz'/>";
					//frmSuccessStep.submit();
					$("#j_username").val(result.no_person);
					$("#j_password").val(pass);
					frmLogin.submit();
					
				} else {
					//비밀번호 틀린 누적횟수 증가
					chk_pwd += 1;
					modifyPwdFailCnt("pwd", chk_pwd);
					
					$("#chk_pwd").val(chk_pwd);
					$(".code-mark").removeClass("active");
					$(".form-shake").addClass("shake-effect").delay(1000).queue(function(){
						$(this).removeClass("shake-effect").dequeue();
					});
					$("#err_message").html(result.message+" ("+chk_pwd+"/5)");
					
					if(chk_pwd == 5) { //비밀번호 5번 모두 틀린 경우
						setTimeout(function(){
							frmSuccessStep.action = "<c:url value='/m/person/frameFindPwdStep1.crz'/>";
							frmSuccessStep.submit();
						}, 300);
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

function fingerConfirm(){
	frmSuccessStep.action = "<c:url value='/m/login/frameFingerConfirm.crz'/>";
	frmSuccessStep.submit();
}
</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>비밀번호 입력</h1>
		</div>
	</header>
	<!-- Content -->
	<form name="frmSuccessStep" action="<c:url value='/m/credit/frameCreditInfoMain.crz'/>" method="post">
	<input type="hidden" name="chk_pwd" id="chk_pwd" value="${cnt_fail_pwd}" />
	<input type="hidden" name="chk_finger" id="chk_finger" value="${cnt_fail_finger}" />
	<input type="hidden" name="returnUrl" value="<c:out value="${returnUrl}"/>">
	<input type="hidden" name="yn_fingerprint" id="yn_fingerprint" />
	
		<section id="content">
			<div class="container security-code">
				<div class="security-code-wrap security-passw">
					<p>
						비밀번호를 입력해주세요.
						<span id="err_message" ></span>
					</p>
					<div class="code-group clearfix">
						<div class="code form-shake">
							<div class="mark-group">
								<label for="" class="code-mark">코드번호</label>
								<input type="password" class="form-control code-no" name="pass_number" id="pass_number1" maxlength="1" value="" placeholder="" onchange="successStep();" />
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
						<c:choose>
							<c:when test="${yn_fingerprint eq 'Y' }">
								<li><button type="button" class="btn btn-lg btn-block btn-fingerfrt" onclick="fingerConfirm();">&nbsp;</button></li>
							</c:when>
							<c:otherwise>
								<li class="btn-none"><button type="button" class="btn btn-lg btn-block">&nbsp;</button></li>
							</c:otherwise>
						</c:choose>
						<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="0">0</button></li>
						<li><button type="button" class="btn btn-lg btn-block btn-backspace">←</button></li>
					</ul>
				</div>
				<p class="link-txt"><a href="/m/person/frameFindPwdStep1.crz"><u>비밀번호를 재설정 하시겠습니까?</u></p>
			</div>
		</section>
	</form>
	<form name="frmLogin" action="<c:url value='/j_spring_security_check'/>" method="POST">
		<input type="hidden" id="j_username" name="j_username" class="form-control" value="${SPRING_SECURITY_LAST_USERNAME}"/>
		<input type="hidden" id="j_password" name="j_password" class="form-control" value="" autocomplete="off"/>
	</form>
	<!-- //Content -->
</div>
</body>
</html>
