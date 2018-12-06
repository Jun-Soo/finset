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
	
	//직장인 신용대출
	function loanApplyStep(gubun) {
		if(gubun == 'WORKER'){ //직장인 신용대출
			frmloanApplyStep.loan_code.value = '01';
		} else if(gubun == 'SELFEMPLOYED') { //자영업자 신용대출
			frmloanApplyStep.loan_code.value = '02';
		} else if(gubun == 'REALESTATE') {	//부동산 담보대출
			frmloanApplyStep.loan_code.value = '03';
		} else if(gubun == 'CAR') {	//자동차 담보대출
			frmloanApplyStep.loan_code.value = '04';
		}
		frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanAcceptTerms.crz'/>";
		frmloanApplyStep.submit();
	}
	
	
</script>

</head>
<body>
<form name="frmloanApplyStep" id="frmloanApplyStep" method="post">
<input type="hidden" name="loan_code">
<input type="hidden" name="title">
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn"></div>
			<h1 class="logo">koscom</h1>
			<div class="input-group-btn">
				<button class="ui-nav nav-menu" type="button" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
<!-- 		<div class="visual-banner"> -->
<!-- 			<h2 class="title"> -->
<!-- 				더 낮게, 더 많이,<br />더 쉽게, 더 빠르게<br /> -->
<!-- 				<small>Loan Made Simple</small> -->
<!-- 			</h2>		 -->
<!-- 		</div> -->
		<div class="section">
<!-- 			<a href="#url" class="bnr-service-guide"> -->
<!-- 				<p>쉽고 빠른 금융 서비스를 경험하세요</p> -->
<!-- 				<h3 class="title">이용안내</h3> -->
<!-- 			</a> -->
			<div class="panel-box">
				<div class="panel-group" id="banner">				
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#loan1" class="collapsed bnr-credit" data-toggle="collapse" data-parent="#banner">
								<h3 class="panel-title">신용대출</h3>
								<small>최적의 솔루션을 제공</small>
							</a>
						</div>
						<div class="panel-collapse collapse" id="loan1">
							<ul class="list-group">
								<li class="list-group-item">
									<h4 class="tit">직장인 대출신청</h4>
									<p>근로소득으로 소득 증빙이 가능한 분이라면 신청가능!<br />
										보다 높은 한도와 낮은 금리를 안내해드립니다.</p>
									<a role="button" onclick="loanApplyStep('WORKER')" class="btn btn-xs btn-default btn-outline">신청</a>
								</li>
								<li class="list-group-item">
									<h4 class="tit">자영업자 대출신청</h4>
									<p>자영업소득으로 소득 증빙이 가능한 분이라면 신청가능!<br />
									필요자금을 더 빠르게 안내해드립니다.</p>
									<a role="button" onclick="loanApplyStep('SELFEMPLOYED')" class="btn btn-xs btn-default btn-outline">신청</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#loan2" class="collapsed bnr-mortgage" data-toggle="collapse" data-parent="#banner">
								<h3 class="panel-title">담보대출</h3>
								<small>금융사별 금융상품을 한눈에</small>
							</a>
						</div>
						<div class="panel-collapse collapse" id="loan2">
							<ul class="list-group">
								<li class="list-group-item">
									<h4 class="tit">아파트 담보대출</h4>
									<p>부동산을 담보를 통한 필요자금 대출 <br />
										인터넷으로 쉽고 간편하게 최적 상품 조회.</p>
									<a role="button" onclick="loanApplyStep('REALESTATE')" class="btn btn-xs btn-default btn-outline">신청</a>
								</li>
								<li class="list-group-item">
									<h4 class="tit">자동차 담보대출</h4>
									<p>자동차를 담보로 빠른 목돈 마련<br />
										최고 한도의 상품을 찾아보세요!</p>
									<a role="button" onclick="loanApplyStep('CAR')" class="btn btn-xs btn-default btn-outline">신청</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>
