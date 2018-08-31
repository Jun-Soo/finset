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
		
	});
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
			<h1>이용약관 및 정책</h1>
		</div>
	</header>
	<!-- Content -->
	<div id="content">
		<div class="container-fluid">
			<div class="list-group">
				<a href="<c:url value='/m/customercenter/frameCustomerFinsetTerms.crz'/>" class="list-group-item">
					<h3 class="h3">서비스 이용약관</h3>
				</a>
				<a href="<c:url value='/m/customercenter/frameCustomerPrivacyPolicy.crz'/>" class="list-group-item">
					<h3 class="h3">개인정보 처리방침</h3>
				</a>
			</div>		
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
