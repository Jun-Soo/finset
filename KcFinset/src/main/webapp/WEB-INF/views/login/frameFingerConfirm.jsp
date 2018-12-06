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
		window.Android.initFingerPrint();
	}
	else if(userAgent == "iOS") {
		//지문인식 결과 콜백 이벤트
		Jockey.on("resultFingerPrint" , function(param) {
			var result = false;
			if(param.result == 1) result = true;
			resultFingerPrint(result);
		});
		
		Jockey.send("initFingerPrint");
	}
	
	$("#chk_pwd").val("${cnt_fail_finger}");
	var chk_pwd = Number($("#chk_pwd").val());
	if(chk_pwd > 0) {
		$("#err_message").html("지문인증을 "+chk_pwd+"회 실패한 이력이 있습니다.");	
	}
});

//비밀번호 틀린횟수 변경
function modifyPwdFailCnt(cnt_fail){
	var data = {"cnt_fail_mode":"finger", "cnt_fail":cnt_fail}; 
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
	if($("#chk_pwd").val() ==  "undefined" || $("#chk_pwd").val() == '') {
		$("#chk_pwd").val('0');
	}
	var chk_pwd = Number($("#chk_pwd").val());
	//result 값이 1인경우는  ios 결과값
	if(result == true){ //지문인식 성공
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
		chk_pwd += 1;
		modifyPwdFailCnt(chk_pwd);
		$("#chk_pwd").val(chk_pwd);
		
		if(chk_pwd <5){
			$("#err_message").html("다시 시도해 주세요. "+"("+chk_pwd+"/5)");
			
		}

		if(chk_pwd == 5) { //지문인식 5번 모두 틀린 경우
			$("#err_message").html("지문이 비활성화 됩니다.");
			$("#yn_fingerprint").val("N");
			var data = frmSuccessStep.ajaxSubmit();
			if(data == null) return false;
			setTimeout(function(){
				$.ajax({
					url : "<c:url value='/m/person/modifyFingerPrint.json'/>",
					data : data,
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					type : "POST",
					async : false,
					success : function (result) {
						if(result.result == '00') {
							frmSuccessStep.action = "<c:url value='/m/login/frameSecurityCodeConfirm.crz'/>";
							frmSuccessStep.submit();
						} else {
						}
					},
					error : function (e) {
                        errMsg(e);

					}
				});
				
			}, 300);
		}
		return false;
	}
}
</script>


</head>
<body>
<form name="frmSuccessStep" method="post">
<input type="hidden" name="chk_pwd" id="chk_pwd" value="${cnt_fail_finger}"/>
<input type="hidden" name="yn_fingerprint" id="yn_fingerprint" />
	
</form>
<form name="frmLogin" action="<c:url value='/j_spring_security_check'/>" method="POST">
	<input type="hidden" id="j_username" name="j_username" class="form-control" value="${SPRING_SECURITY_LAST_USERNAME}"/>
	<input type="hidden" id="j_password" name="j_password" class="form-control" value="" autocomplete="off"/>
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>지문인증</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container security-code">
			<div class="security-code-wrap security-finger">
				<p>
					지문을 입력해 주세요.
					<span id="err_message"></span>
				</p>
				<div class="fingerprt-cert"></div>
			</div>
			<p class="link-txt"><a href="/m/login/frameCodeConfirm.crz"><u>비밀번호를 입력 하시겠습니까?</u></p>
			<%-- <div class="btn-area">
				<a href="<c:url value='/m/person/frameFindPwdStep1.crz'/>">비밀번호를 입력 하시겠습니까?</a>
			</div> --%>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
