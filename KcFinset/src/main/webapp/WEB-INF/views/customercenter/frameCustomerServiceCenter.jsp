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
		
		if(userAgent == "Android") {

			var chkVersion = window.Android.checkAppVersion();
			checkAppVersion(chkVersion);
			
		} else if(userAgent == "iOS") {
			
			//앱버전 조회결과 콜백
			Jockey.on("receiveAppVersion" , function (param) {
				checkAppVersion(param.appVersion);
			});
			//앱버전 조회 네이티브 호출
			Jockey.send("checkAppVersion");
		}	
		
	});
	
	function sendEmail(code , addr) {
		if(userAgent == "iOS") {
			
			Jockey.send("sendEmail",{
				code : code,
				addr : addr
			});
		
		} else if(userAgent == "Android") {
			window.Android.sendEmail(code, addr);
		}
	}
	
	function checkAppVersion(appVersion) {
		
		$('#app_version').html(appVersion);
		
		var version = '${newest_version}';
		var versionSplit = version.split('.');			//최신버전
		var chkVersionSplit = appVersion.split('.');	//현재버전
		for(var i=0; i<versionSplit.length; i++){
			if(Number(versionSplit[i]) > Number(chkVersionSplit[i])){
				$('#update').show();
				break;
			} 
		}
	}
	
	function updateApp() {
		if(userAgent == 'Android') {
			window.Android.updateApp();
		}
		else if(userAgent == 'iOS' ) {
			Jockey.send("updateApp" , {
				app_id : ""
			});
		}
	}

	//뒤로가기
	function goBack(){
		frmCustomerServiceCenter.action = "/m/customercenter/frameCustomerCenterMain.crz";
		frmCustomerServiceCenter.submit();
	}
</script>

</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
			</div>
			<h1>고객센터</h1>
		</div>
	</header>
	<!-- Content -->
	<form method="post" name="frmCustomerServiceCenter" id="frmCustomerServiceCenter">
	
	<div id="content">
		<div class="lead">
			<p>서비스 이용관련 문의<br>및 의견을 보내주세요.</p>
		</div>
		<div class="container-fluid">
			<div class="row inquiry-item">
				<div class="col-xs-6">
					<a href="https://goto.kakao.com/@신용부채관리핀셋" target="_blank" class="inquiry-kakao">
						카카오톡 문의
					</a>
				</div>
				<div class="col-xs-6">
					<a href="#" class="inquiry-email" onclick="sendEmail('00', 'finset@koscom.co.kr');">
						이메일 문의
					</a>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="list-group">
				<a href="<c:url value='/m/customercenter/frameCustomerFAQ.crz'/>" class="list-group-item">
					<h3 class="h3">자주묻는 질문</h3>
				</a>
				<a href="<c:url value='/m/customercenter/frameCustomerTerms.crz'/>" class="list-group-item">
					<h3 class="h3">이용약관 및 정책</h3>
				</a>
				<!-- 
				<a href="<c:url value='/m/customercenter/frameCustomerVersionInfo.crz'/>" class="list-group-item">
					<h3 class="h3">버전 정보<span class="ver-num">(${nmCode})</span></h3>
				</a>
				 -->
				 <div class="list-group-item">
					<h3 class="pull-left h3">버전정보</h3>
					<span class="pull-right" id="app_version">
						<div class="pull-right" id="update" style="display: none">
							<a href="#" class="btn-xs btn-underline" onclick="updateApp();">업데이트</a>
						</div>
					</span>
				</div>
				<div class="list-group-item">
					<h3 class="pull-left h3">제휴문의</h3>
					<span class="pull-right mail-add">finset@koscom.co.kr</span>
				</div>
			</div>	
		</div>
		<div class="btn-fixed-bottom">
			<a href="#" class="btn btn-lg btn-primary btn-block" disabled="disabled">신청취소</a>
		</div>
	</div>
	
	</form>
	<!-- //Content -->
</div>
</body>
</html>
