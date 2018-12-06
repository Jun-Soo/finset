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
		//
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
		
		if(userAgent == "Android") {
			window.Android.backKeySendUrl("/m/customercenter/frameCustomerCenterMain.crz");
		}
	});

	function resultCheckFingerPrint(result){
		 if(result == true || result == 1){
			$('#finger_setting').show();
		 } else {
			$('#finger_setting').hide();
		 }
	}
	
	function chkFinger(){
		if($('#fingerPrint').is(":checked")){
			frmSuccessStep.action = "<c:url value='/m/person/frameSecurityCodeFingerSet.crz'/>";
			frmSuccessStep.submit();
		} else {
			$('#yn_fingerprint').val('N');
		
			var data = frmSuccessStep.ajaxSubmit();
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
	}
	
	function goCustomerMain(){
		frmSuccessStep.action = "<c:url value='/m/customercenter/frameCustomerCenterMain.crz'/>";
		frmSuccessStep.submit();
	}
	
	function goChangePwd(){
		frmSuccessStep.action = "<c:url value='/m/customercenter/frameChnagePwdCert.crz'/>";
		frmSuccessStep.submit();
	}
	
	// 인증서 가져오기 실행
	function importCert() {
		if ( userAgent == "Android" ) {
			window.Android.importCert();
		} else if ( userAgent == "iOS") {
			Jockey.send("getCertVCEasy");
		} else {
			//
		}
	}
	
	// 인증서 가져오기 완료 콜백
	function statusChangeSimple() {
		
	}
</script>

</head>
<body>
<form id="frmSuccessStep" name="frmSuccessStep" method="post">
<input type="hidden" id="yn_fingerprint" name="yn_fingerprint" value=""/>
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goCustomerMain();">뒤로가기</button>
			</div>
			<h1>인증/보안</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content">
		<div class="container-fluid">
			<div class="list-group">			
				<a onclick="importCert();" class="list-group-item">
					<h3 class="h3">인증서 가져오기</h3>
					<p>PC -> 휴대폰 인증서 복사</p>
				</a>
				<a onclick="goChangePwd();" class="list-group-item">
					<h3 class="h3">비밀번호 변경</h3>
					<p>휴대폰 본인확인 필요</p>
				</a>
<!-- 				<div class="list-group-item" id="finger_setting" style="display: none"> -->
				<div id="finger_setting" class="list-group-item">
					<h3 class="h3">지문인증 사용</h3>
					<p>안전하고 쉬운 서비스 이용을 위해<br />지문인증을 활성화</p>
					<div class="ui-switch">
						<label data-form-control="toggle" class="pull-right">
							<input type="checkbox" id="fingerPrint" onchange="chkFinger();" <c:out value="${personVO.yn_fingerprint eq 'Y' ? 'checked' : ''}" />>
							<span data-form-decorator="before"><span data-form-decorator="after"></span></span>
						</label>
					</div>
				</div>				
			</div>
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
