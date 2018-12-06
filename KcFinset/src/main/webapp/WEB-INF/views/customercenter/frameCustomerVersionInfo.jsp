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
// 			var version = '${nmCode}';
			var chkVersion = window.Android.checkAppVersion();
			checkAppVersion(chkVersion);
// 			var versionSplit = version.split('.');
// 			var chkVersionSplit = chkVersion.split('.');
// 			for(var i=0; i<versionSplit.length; i++){
// 				if(Number(versionSplit[i]) > Number(chkVersionSplit[i])){
// 					$('#update').show();
// 					break;
// 				} 
// 			}
		}
		else if(userAgent == "iOS")
		{
			//앱버전 조회결과 콜백
			Jockey.on("receiveAppVersion" , function (param) {
				checkAppVersion(param.appVersion);
			});
			//앱버전 조회 네이티브 호출
			Jockey.send("checkAppVersion");
		}	
	});
	
	function checkAppVersion(appVersion) {
		var version = '${nmCode}';
		var versionSplit = version.split('.');
		var chkVersionSplit = appVersion.split('.');
		for(var i=0; i<versionSplit.length; i++){
			if(Number(versionSplit[i]) > Number(chkVersionSplit[i])){
				$('#update').show();
				break;
			} 
		}
	}
	
	function updateApp() {
		if(userAgent == "Android") {
			window.Android.updateApp();
		}
		else if(userAgent == "iOS") {
			Jockey.send("updateApp" , {
				app_id : ""
			});
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
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>버전정보</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content" class="ver-info">
		<div class="msg-block">
			<p>현재 최신 버전 <span> ${nmCode}</span></p>
		</div>
		<div class="container">
			<div class="btn-area" id="update" style="display: none">
				<a href="#" class="btn btn-block btn-outline" onclick="updateApp();">업데이트</a>
			</div>
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
