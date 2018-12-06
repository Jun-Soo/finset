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
		
		
		
		if(userAgent == "iOS") {
			//Jockey.send("browserCall",{
			//	url : "${goodsInfo.fc_link}"
			//});
		} else if(userAgent == "Android") {
			
    		//window.Android.browserCall("${goodsInfo.fc_link}");
		}
	});
</script>
</head>
<body>
<div class="splash">
	<div class="splash-body">
		
	</div>
</div>
</body>
</html>
