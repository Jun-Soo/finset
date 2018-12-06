<%@ page import="com.koscom.util.StringUtil" %>
<%@ page import="com.koscom.util.LogUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
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
			window.Android.setEndApp('Y');
		}
	});
	function goBack(){
        var historySize = history.length;
        if(historySize == undefined || historySize == 0) {
            if(userAgent == "Android") {
                window.Android.exitApp();
            }
        } else {
            history.back();
        }
	}
</script>
</head>
<body class="bg-white">
<div class="data-none">
	<em>죄송합니다.</em>
	<p>일시적으로 서비스를 이용하실 수 없습니다.<br>잠시 후 다시 접속해주세요.</p>
	<div class="btn-area">
		<a href="javascript:goBack();" class="btn btn-outline">뒤로가기</a>
	</div>
</div>
<%
	String error = null;

	if(exception != null) {
		error = LogUtil.errorToStr(exception);
		error = StringUtil.replaceAll(error,"\n","<br/>");
	    exception.printStackTrace();	
	}
%>
	<%--=error--%>
</body>
</html>
