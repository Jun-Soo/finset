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
		//로그아웃 
		$("#alertMsg .btn-lg").each(function () {
			$(this).on("click", function() {
				if("Y" == $(this).attr("data-val")){
					personLogout();
				}
	        });
		});
		
		if(userAgent == "Android" && $('#site').val() != "REAL") {
			$("#user-link").removeClass("div_hidden");
			window.Android.backKeySendUrl("/m/customercenter/frameCustomerCenterMain.crz");
		}
	});
// 	function profileImg() {

// 		var frm = document.frmCutomerInfo;

// 		if(frm.file1.value == ''){
// 			$("#file1Space").html("<input type= 'hidden' name='dumy_file1' />");
// 		}
// 		frm.submit();		
		
// 	}
	
// 		function setEmail(value){
// 			$('#email').val(value);
// 			$('#txtEmail').text(value);
// 		}
		
// 		function changeBox(gubun) {
// 			$('#email').show();
// 			$('#txtEmail').hide();
			
// 			$("#modifyEmail").hide();
// 			$("#saveEmail").show();
// 		}

		function frameModifyEmail(){
			var frm = document.frmCutomerInfo;
			
			frm.action="/m/customercenter/frameModifyPersonEmail.crz"; 
			frm.method="post"; 
			frm.submit(); 
		}
		
		function personLogout() {
			var frm = document.frmCutomerInfo;
			
			$("#yn_logout").val('Y');
			
			frm.action="/m/person/framePersonLogout.crz"; 
			frm.method="post"; 
			frm.submit(); 
			
// 			$.ajax({
// 				url: "<c:url value='/m/person/modifyPersonLogout.json'/>",
// 				data : data,
// 				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
// 				type : "POST",
// 				async : false,
// 				success : function (result) {
// 					if(result.result == '00') {
// 						toastMsg('로그아웃 되었습니다.');						
// 						frmCutomerInfo.action = "<c:url value='/m/login/frameSecurityCodeConfirm.crz'/>";
// 						setTimeout(function(){
// 							frmCutomerInfo.submit();
// 						}, 1000);
// 					}
// 				},
// 				error : function (e) {
// 					errMsg(e);
// 				}
// 			});
			
		}
		function frameDebtSecurityCode() {
			frmCutomerInfo.action = "/m/debt/frameDebtSecurityCode.crz";
			frmCutomerInfo.submit();
		}
		
		function frameFcLinkControl() {
			frmCutomerInfo.action = "/m/scrap/frameFcLinkControl.crz";
			frmCutomerInfo.submit();
		}
		
		function goBack() {
			frmCutomerInfo.action = "/m/customercenter/frameCustomerCenterMain.crz";
			frmCutomerInfo.submit();
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
			<h1>내정보</h1>
			<div class="g-menu" id="src_menu">
				<button type="button" class="btn btn-gmenu" onclick="alertMsg('로그아웃 하시겠습니까?');">로그아웃</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<form name="frmCutomerInfo" id="frmCutomerInfo" method="post">
		<input type="hidden" id="yn_logout" name="yn_logout" value=""/>
		<input type="hidden" id="site" name="site" value="${site}"/>
			<div class="container-fluid top-block user-modify">
				<div>
					<dl>
						<dt>이름</dt>
						<dd><c:out value="${resultPerson.nm_person }"/></dd>
					</dl>
					<dl>
						<dt>휴대폰번호</dt>
						<dd><c:out value="${resultPerson.hp }"/></dd>
					</dl>
					<dl>
						<dt>이메일주소</dt>
						<dd class="user-mail">
							<c:choose>
								<c:when test="${empty resultPerson.email}">
									<p id="txtEmail">등록안됨</p>
									<a role="button" id="modifyEmail" class="btn btn-xs btn-primary" onclick="frameModifyEmail();">수정</a>
								</c:when>
								<c:otherwise>
									<p id="txtEmail">${resultPerson.email }</p>
									<a role="button" id="modifyEmail" class="btn btn-xs btn-primary" onclick="frameModifyEmail();">수정</a>
								</c:otherwise>
							</c:choose>
						</dd>
					</dl>
					<dl>
						<dt>부채정보</dt>
						<dd class="user-debt">등록된 부채가 <c:out value="${debtCount}"/>개 있음.
							<a role="button" id="frameDebtCancelDelete" class="btn btn-xs btn-primary" onclick="frameDebtSecurityCode();">수정</a>
						</dd>
					</dl>
					<dl id="user-link" class="div_hidden">
						<dt>연동정보</dt>
						<dd class="user-link">연동된 금융사가 <c:out value="${linkedFcCount}"/>개 있음.
							<a role="button" id="frameFcLinkControl" class="btn btn-xs btn-primary" onclick="frameFcLinkControl();">수정</a>
						</dd>
					</dl>
				</div>
			</div>
			<p class="link-txt">서비스를 탈퇴하려면 <a href="<c:url value='/m/customercenter/frameCustomerQuit.crz'/>"><u>여기</u></a>를 눌러주세요.</p>
		</form>		
	</section>
	<!-- //Content -->
</div>
</body>
</html>