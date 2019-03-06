<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Error</title>
<script type="text/javascript">
</script>	
</head>
<div class="ui-layout-content">
	<div class="error-wrapper">
		<img src="/img/error_img.png" alt="Error" class="error_img">
		<div id="error-message">
			<h1>일시적으로 서비스를 이용하실 수 없습니다.</h1>
			<p>일시적으로 서비스를 이용하실 수 없습니다. 잠시 후 다시 접속해주세요.</p>
		</div>
		<a class="btn-gohome" href="<c:url value="/"/>">메인화면 바로가기</a>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>