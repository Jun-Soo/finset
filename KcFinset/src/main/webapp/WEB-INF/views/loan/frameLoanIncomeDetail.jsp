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
	
	function loanApplyStepWorker() {
		frmloanApplyStep.loan_code.value = '01';
		frmloanApplyStep.submit();
	}
	
	function loanApplyStepSelfEmployed() {
		frmloanApplyStep.loan_code.value = '02';
		frmloanApplyStep.submit();
	}
	
</script>

</head>
<body>
<form name="frmloanApplyStep" id="frmloanApplyStep" action="<c:url value='/m/loan/frameLoanStep1.crz'/>" method="post">
<input type="hidden" name="loan_code">
<input type="hidden" name="title">
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>상품상세</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<h2 class="page-header">대출상품명 <span class="text-primary">(기업은행)</span></h2>
		<div class="tab-content">
			<ul class="nav nav-outline nav-justified tabs">
				<li class="active"><a href="#tab1">상품안내</a></li>
				<li><a href="#tab2">금리&이율 및 수수료</a></li>
				<li><a href="#tab3">유의사항</a></li>
			</ul>
			<div class="tab-pane active" id="tab1">
				<!-- tab1 -->
				<h2 class="h2">상품정보</h2>
				<div class="container">
					<p class="help-block">
						주식매입자금이 필요한 주식투자자에게 증권계좌의 유가증권 및 예수금을 담보로 실행하는 대출 상품
					</p>
				</div>
				<br />
				<h2 class="h2">대출신청자격</h2>
				<div class="container">
					<ul class="text-list">
						<li>만 19세 이상 개인(신용관리대상자 및 법인, 외국인 대출신청 불가능)</li>
						<li>증권계좌 평가금액이 100만원 이상인 계좌를 보유한 고객</li>
					</ul>
				</div>
				<!-- //tab1 -->
			</div>
			<div class="tab-pane" id="tab2">
				<!-- tab2 -->
				<h2 class="h2">금리&이율 및 수수료</h2>
				<div class="container">
					<p class="help-block">
						주식매입자금이 필요한 주식투자자에게 증권계좌의 유가증권 및 예수금을 담보로 실행하는 대출 상품
					</p>
				</div>
				<!-- //tab2 -->
			</div>
			<div class="tab-pane" id="tab3">
				<!-- tab3 -->
				<h2 class="h2">유의사항</h2>
				<div class="container">
					<p class="help-block">
						주식매입자금이 필요한 주식투자자에게 증권계좌의 유가증권 및 예수금을 담보로 실행하는 대출 상품
					</p>
				</div>
				<!-- //tab3 -->
			</div>
		</div>
		<div class="btn-fixed-bottom bottom-over">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block">닫기</button>
			</div>
			<div class="col-xs-6">
				<a href="11.html" class="btn btn-lg btn-primary btn-block">상품선택</a>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
	
</div>
</body>
</html>
