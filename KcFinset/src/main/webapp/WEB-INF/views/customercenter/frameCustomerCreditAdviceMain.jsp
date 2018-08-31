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

//뒤로가기
function goBack(){
	frmCustomerCreditAdviceMain.action = "/m/customercenter/frameCustomerCenterMain.crz";
	frmCustomerCreditAdviceMain.submit();
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
			<h1>신용상담</h1>
		</div>
	</header>
	<!-- Content -->
	<form method="post" name="frmCustomerCreditAdviceMain" id="frmCustomerCreditAdviceMain">
	
	<div id="content">
		<div class="visual-banner">
			<div class="title">
				신용등급을 올리고 싶으세요?
				<p>지금 FINSET의 상담서비스를 만나보세요.</p>	
			</div>
		</div>
		<div class="container-fluid">
			<div class="list-group list-group-bnr">
				<a href="/m/customercenter/frameCustomerCreditAdviceStep1.crz" class="list-group-item">
					<h3 class="h3">신용상담 신청하기</h3>
				</a>
				<a href="/m/customercenter/frameCustomerCreditAdviceStatus.crz" class="list-group-item">
					<h3 class="h3">신용상담  신청현황보기</h3>
				</a>
			</div>
			<div class="panel-box">
				<div class="panel-group" id="banner">				
					<div class="panel panel-default" >
						<div class="panel-heading">
							<a href="/m/customercenter/frameCustomerCreditAdviceStep1.crz">
								<h3 class="panel-title">신용상담 신청하기</h3>
							</a>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="/m/customercenter/frameCustomerCreditAdviceStatus.crz">
								<h3 class="panel-title">신용상담  신청현황보기</h3>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	</form>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>
