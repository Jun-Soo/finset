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
function getOppfApi() {
var frm = document.frmloanApplyStep;

	var noPerson = frm.noPerson.value;

 	var Ci = "QciuDFKLcwalCKtWALuNWic9eGm7WNdauW+A+n+mpfhif24c3msHdzVjoZK0ntkXZ1+nA6LX47nyKmIq1JoHhg==";
 	var nmPerson = "박환덕";
 	var fromDate = "20170101";
 	var toDate = "20171020";
    window.Android.sendOppf(noPerson, Ci, nmPerson, fromDate, toDate);

 	//TEST
 	//frmloanApplyStep.action = "<c:url value='/m/assets/frameAssetsManagementMain.crz'/>";
 	//frmloanApplyStep.submit();
}

function goMain(){
 	frmloanApplyStep.action = "<c:url value='/m/credit/frameCreditInfoMain.crz'/>";
 	frmloanApplyStep.submit();
}
//안드로이드 응답 콜백 //00 : 성공 , 99 : 인증실패, 10 : 회원가입실패, 20 : 취소
function resultOppf(code){
	if(code == "00"){
		var frm = document.frmloanApplyStep;
		frm.action = "<c:url value='/m/assets/frameAssetsManagementMain.crz'/>";
		frm.submit();
	}else if(code == "99"){
		alert("증권관련 인증실패 했습니다.");
	}else if(code == "10"){
		alert("증권관련 회원가입실패");
	}else if(code == "20"){
		alert("증권관련 자료 취소");
	}
}
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
			<h1>자산관리</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
				<input type="hidden" name="noPerson" id="noPerson" value="${noPerson}"/>
				<input type="hidden" name="Ci" id="Ci" value="${Ci}"/>
				<input type="hidden" name="nmPerson" id="nmPerson" value="${nmPerson}"/>
				<input type="hidden" name="fromDate" id="fromDate" value="${fromDate}"/>
				<input type="hidden" name="toDate" id="toDate" value="${toDate}"/>
			<div class="msg-block">
				<p>
					<em>서비스 이용을 위해<br />증권 정보연동을 하시겠습니까?</em>
					회원등록 및 연계 과정이 필요합니다.
				</p>
			</div>
			<div class="btn-area">
				<a href="#" onclick="getOppfApi();" class="btn btn-lg btn-outline btn-block">예</a>
				<a href="#" class="btn btn-lg btn-outline btn-block" onclick="goMain();">아니오</a>
			</div>
			</form>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
