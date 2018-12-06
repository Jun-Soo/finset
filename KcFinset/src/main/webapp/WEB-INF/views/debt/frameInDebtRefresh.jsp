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

/*
 * 부채관리 메인
 */
function goDebtInfoMain(){
//	location.href="/m/debt/frameDebtInfoMain.crz";
	frmloanApplyStep.action = "<c:url value='/m/debt/frameDebtInfoMain.crz'/>";
	frmloanApplyStep.submit();
}

/*
 * GO-> 부채 등록  step1
 */
function goDebtSsnInfo() {
//	location.href="/m/debt/frameDebtSsnInfo.crz";
	frmloanApplyStep.action = "<c:url value='/m/debt/frameDebtSsnInfo.crz'/>";
	frmloanApplyStep.submit();
}

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
			<h1>부채관리</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
				<div class="area-total">
					<%--등록된 부채가 없으면 신규--%>
					<c:if test="${ debt_count == '0'}">
						<span>금융사 등록 부채가 없습니다.</span>
					</c:if>
					<%--등록된 부채가 있으면 신규가 아님--%>
					<c:if test="${ debt_count ne '0'}">
						<span>금융사 대출이 총 <em class="num">${debt_count}건</em> 있습니다.</span>
					</c:if>
				</div>
				<div class="msg-block">
					<strong>서비스 이용을 위해<br>금융사 정보연동을 하시겠습니까?</strong>
					<p>금융사에 등록된 공인인증서가 필요합니다.</p>
				</div>
				<div class="btn-area">
					<!--<a href="javascript:checkExistCert();" class="btn btn-lg btn-line btn-block">예</a>-->
					<a href="javascript:goDebtSsnInfo();" class="btn btn-lg btn-outline btn-block">예</a>
					<a href="javascript:goDebtInfoMain();" class="btn btn-lg btn-outline btn-block">아니오</a>
				</div>
			</form>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>	
	
</div>
</body>
</body>
</html>
