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
<%-- response.setHeader("P3P","CP='CAO PSA CONi OTR OUR DEM ONL'"); --%>
<script type="text/javascript">
	
	$(document).ready(function() {
		if(userAgent == "Android") {
			window.Android.setEndApp('Y');
		}
	});
</script>
</head>
<body>
<form name="frmStep" method="post"> 
</form>
<div style="height: 100%">
	<iframe id="ifrContents" name="ifrContents" width="100%" height="100%" scrolling="yes" frameborder="0"></iframe>	
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>

</body>

<script type="text/javascript">
	
	var kcbURI = "${kcbURI}";
	ifrContents.location.href = kcbURI;
					 
</script>
</html>
