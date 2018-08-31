<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	
	$(document).ready(function(){
		var url = window.location.href;
		
		if(url.indexOf("base") >= 0) {
		} else if(url.indexOf("credit") >= 0) {
			$("#footer_credit").addClass("active");
		} else if(url.indexOf("debt") >= 0) {
			$("#footer_debt").addClass("active");			
		} else if(url.indexOf("loan") >= 0) {
			$("#footer_loan").addClass("active");
		} else if(url.indexOf("assets") >= 0) {
			$("#footer_assets").addClass("active");
		} else if(url.indexOf("customercenter") >= 0) {
			$("#footer_mypage").addClass("active");
		}
	});
</script>
<div id="menu-fixed-bottom">
	<ul class="menu-list">
		<li id="footer_credit" class=""><a href="<c:url value='/m/credit/frameCreditInfoMain.crz'/>" class="menu-credit">신용관리</a></li>
		<li id="footer_debt"><a href="<c:url value='/m/debt/frameDebt.crz'/>" class="menu-debt">부채관리</a></li>
		<li id="footer_loan" class=""><a href="<c:url value='/m/loanworker/frameLoanWorkerStep1.crz'/>" class="menu-loan">상품조회</a></li>
<%--    		<li id="footer_cust"><a href="<c:url value='/m/servicecenter/frameServiceCenterInfo.crz'/>" class="menu-cust">고객센터</a></li> --%>
<!-- 	<li id="footer_cust"><a href="#none" class="menu-cust">고객센터</a></li> -->
		<!-- <li id="footer_assets"><a href="<c:url value='/m/assets/frameAssetsMain.crz'/>" class="menu-assets">자산관리</a></li> -->
		<li id="footer_mypage"><a href="<c:url value='/m/customercenter/frameCustomerCenterMain.crz'/>" class="menu-mypage">마이페이지</a></li>
	</ul>			
</div>