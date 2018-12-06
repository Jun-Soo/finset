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
		if(userAgent == "Android") {
			window.Android.setEndApp('Y');
		}
		//신용컨설팅 클릭안되게
		$('#cre').bind('click', false);
		
		//신용컨설팅 클릭 안되게 막음 20180612 김휘경
		$('.ico-credit-consult').bind('click', false);
		  
		//로그아웃 
		$("#alertMsg .btn-lg").each(function () {
			$(this).on("click", function() {
				if("Y" == $(this).attr("data-val")){
					personLogout();
				}
	        });
		});

	});
	
	function goNoti() { 
		document.frmCustomerMain.action="/m/customercenter/frameCustomerNotification.crz"; 
		document.frmCustomerMain.method="post"; 
		document.frmCustomerMain.submit(); 
	}

	function personLogout() {
		var frm = document.frmCustomerMain;
		
		$("#yn_logout").val('Y');
		
		frm.action="/m/person/framePersonLogout.crz"; 
		frm.method="post"; 
		frm.submit(); 
		
	}
	
	function goMain(){
		frmCustomerMain.action = "<c:url value='/m/credit/frameCreditInfoMain.crz'/>";
		frmCustomerMain.submit();
	}
</script>

</head>
<body>
	<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="goMain();">뒤로가기</button>
			</div>
			<h1>마이페이지</h1>
			<div class="g-menu">
				<button type="button" class="ico ico-notilist" onclick="goNoti();">알림내역</button>
				<button type="button" class="btn btn-gmenu" onclick="alertMsg('로그아웃 하시겠습니까?');">로그아웃</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<form name="frmCustomerMain" id="frmCustomerMain" method="post">
	<input type="hidden" id="yn_logout" name="yn_logout" value="">
	<section id="content" class="my-info">
		<a href="<c:url value='/m/customercenter/frameCustomerMyInfo.crz'/>">
			<div class="container-fluid my-info-heading">
				<div class="user-info">
					<p class="name">${personVO.nm_person }</p>
					<p class="contact">최근 접속 <span>${personVO.dt_lst }</span></p>
				</div>
			</div>
		</a>
		<div class="container-fluid">
			<div class="cube-group-item">
				<ul>
					<li>
						<a href="<c:url value='/m/customercenter/frameCustomerNotice.crz'/>" class="ico-noti-evt">공지/이벤트</a>
					</li>
					<li>
						<a href="<c:url value='/m/customercenter/frameCustomerNotificationSetting.crz'/>" class="ico-noti-sett">알림설정</a>
					</li>
					<li>
						<a href="<c:url value='/m/customercenter/frameCustomerAuthenticationSecurity.crz'/>" class="ico-security">인증/보안</a>
					</li>
					<li>
						<a href="<c:url value='/m/customercenter/frameCustomerGoodsFavorite.crz'/>" class="ico-goods-favo">관심상품</a>
					</li>
					<li>
						<a href="<c:url value='/m/customercenter/frameCustomerViewResults.crz'/>" class="ico-goods-result">상품조회결과</a>
					</li>
					<li>
						<a href="<c:url value='/m/customercenter/frameCustomerGoodsStatus.crz'/>" class="ico-goods-status">신청진행현황</a>
					</li>
					<li>
						<a href="<c:url value='/m/customercenter/frameCustomerFAQ.crz'/>" class="ico-faq">자주묻는질문</a>
					</li>
					<li>
						<a href="<c:url value='/m/customercenter/frameCustomerServiceCenter.crz'/>" class="ico-serv-center">고객센터</a>
					</li>
					<li class="preparing">
						<a href="<c:url value='/m/customercenter/frameCustomerCreditAdviceMain.crz'/>" class="ico-credit-consult">신용상담<span>(준비중)</span></a>
						<%-- 임시주석: 삭제금지 <a href="<c:url value='/m/customercenter/frameCustomerCreditAdviceMain.crz'/>" class="ico-credit-consult">신용상담</a> --%>
					</li>
					<li>
						<a href="<c:url value='/m/credit/frameCreditRaise.crz'/>" class="ico-serv-center">신용등급올리기</a>
					</li>
				</ul>
			</div>
		</div>
	</section>
</form>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>
