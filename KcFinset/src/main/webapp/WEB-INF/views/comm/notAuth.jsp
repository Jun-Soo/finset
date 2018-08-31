<%@ page import="com.koscom.util.StringUtil" %>
<%@ page import="com.koscom.util.LogUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	});
	function goHome() {
        location.href = "/";
	}
</script>
</head>
<body style="background-color: white">
<div class="data-none">
	<em>잘못된 접근입니다. </em>
	<p>다시 접속해주세요.</p>
	<div class="btn-area">
		<a href="javascript:goHome();" class="btn btn-block btn-outline">홈으로 가기</a>
	</div>
</div>
</body>
</html>
