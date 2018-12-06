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
    $(document).ready(function () {
        if(userAgent == "Android") {
            window.Android.setEndApp('Y');
        }
    });
	function loanApplyStep(gubun){
		if(gubun == 'WORKER'){ //직장인 신용대출
			$('#cd_goods_class_l').val('01');
			$('#cd_goods_class_m').val('01,03,08,09');
			frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep2.crz'/>";
		} else if(gubun == 'EMPLOYED'){ //자영업자 신용대출
			$('#cd_goods_class_l').val('01');
			$('#cd_goods_class_m').val('02,03,08,09');
			frmloanApplyStep.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep1.crz'/>";
		} 
		else if(gubun == 'HOMEMORTGAGE'){ //부동산 담보대출
			$('#cd_goods_class_l').val('02');
			$('#cd_goods_class_m').val('05,08');
			frmloanApplyStep.action = "<c:url value='/m/loanhomemortgage/frameLoanHomeMortgageStep1.crz'/>";
		}
		frmloanApplyStep.submit();
	}

    /**
	 * 상품조회내역
     */
	function goCustomerViewResults() {
	    location.href = "<c:url value='/m/customercenter/frameCustomerViewResults.crz'/>";
	}
    setCookie(LOANWORKER_CUR_TAB,"");
    setCookie(LOANSELF_CUR_TAB,"");
    setCookie(LOAN_MORTGAGE_CUR_TAB,"");

	</script>

</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>상품조회</h1>
			<div class="g-menu">
				<button type="button" class="btn btn-gmenu" onclick="goCustomerViewResults();">상품조회내역</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<div id="content">
		<div class="visual-banner">
			<h2 class="title">
				다양한 금융사의 대출금리와<br />한도를 한눈에
			</h2>		
		</div>
		<div class="container-fluid">
			<div class="list-group list-group-bnr">
				<a href="javascript:loanApplyStep('WORKER')" class="list-group-item">
					<h3 class="h3">신용대출(직장인)</h3>
					<p>소득이있는 직장인</p>
				</a>
				
				<a href="javascript:loanApplyStep('EMPLOYED')" class="list-group-item">
					<h3 class="h3">신용대출(개인사업자)</h3>
					<p>소득이있는  개인사업자</p>
				</a>
				
				<a href="javascript:loanApplyStep('HOMEMORTGAGE')" class="list-group-item">
					<h3 class="h3">주택담보대출</h3>
					<p>아파트, 오피스텔, 일반주택 구입 및 보유자</p>
				</a>
			</div>
		</div>
	</div>

	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
    <form method="post" name="frmloanApplyStep" id="frmloanApplyStep">
        <input type="hidden" name="st" value="1">
        <input type="hidden" name="loan_code">
        <input type="hidden" name="cd_goods_class_l" id="cd_goods_class_l">
        <input type="hidden" name="cd_goods_class_m" id="cd_goods_class_m">
    </form>
</div>
</body>
</html>
