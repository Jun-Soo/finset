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

//신용등급상세로 이동
function onClick(code){
	var button, status, url;
	frmCreditRaise.action = "";
	
	
	switch(code){
	   case "nhis":
		   staus = "<c:out value="${nhis_status}" escapeXml="false"/>";
		   button = "<c:out value="${nhis_button}" escapeXml="false"/>";
		   url = "<c:url value='/m/credit/frameCreditRaiseNhis.crz'/>";
	     break;
	   case "nts":
		   staus = "<c:out value="${nts_status}" escapeXml="false"/>";
		   button = "<c:out value="${nts_button}" escapeXml="false"/>";
		   url = "<c:url value='/m/credit/frameCreditRaiseNts.crz'/>";
		 break;
	   case "nps":
		   staus = "<c:out value="${nps_status}" escapeXml="false"/>";
		   button = "<c:out value="${nps_button}" escapeXml="false"/>";
		   url = "<c:url value='/m/credit/frameCreditRaiseNps.crz'/>";
		 break;
 	}
	if(staus == '대기')	{
		frmCreditRaise.action = url;
		frmCreditRaise.submit();
	}
	
	if(button == 'false')	{
		//do nothing
		return;
	}
	
	frmCreditRaise.action = "<c:url value='/m/credit/frameCreditSsnInfo.crz?scrap_code="+code+"'/>";
	frmCreditRaise.submit();
}
function onClickDetail(){
	frmCreditRaise.action = "<c:url value='/m/credit/frameCreditRaiseDetail.crz'/>";
	frmCreditRaise.submit();
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
			<h1>신용등급 올리기</h1>
		</div>
	</header>
	<!-- //Header -->
	<!-- Content -->
	<section id="content">
		<form name="frmCreditRaise" method="post">
		</form>
		<div class="visual-banner middle-banner">
			<p class="msg text-title">몇 번의 클릭으로 신용점수를 Up 하세요</p>
			<br/>
			<p class="msg">지금까지 150명이 신청하신 후 최대 20 점이</p>
			<p class="msg">상승하였습니다</p>
			<br/>
			<p class="msg pull-right msg-last" onclick="onClickDetail();">자세히 </p>
		</div>
		<div class="list-block">
			<div class="container-fluid" onclick="onClick('nhis');">
				<div class="require-title">
					<div class="list-heading">
						<div>
							<span class="thumb-logo pull-left" style="background-image:url('/fincorp/getFinCorpIcon.crz?cd_fc=0010471');"></span>
							<li>국민건강보험</li>
						</div>
						<c:if test="${!empty nhis_status}">
						<div class="label-right">
							<label class="label-type">${nhis_status} | ${ufn:formatDateDot(nhis_date)}</label>
						</div>
						</c:if>

					</div>
					<div class="list-info">
						<dl>
							<dt>최근 1년간 건강보험료 납부 내역 등록</dt>
						</dl>
					</div>
				</div>
			</div>
			<div class="container-fluid" onclick="onClick('nps');">
				<div class="require-title">
					<div class="list-heading">
						<div>
							<span class="thumb-logo pull-left" style="background-image:url('/fincorp/getFinCorpIcon.crz?cd_fc=0010471');"></span>
							<li>국민연금</li>
						</div>
						<c:if test="${!empty nps_status}">
						<div class="label-right">
							<label class="label-type">${nps_status} | ${ufn:formatDateDot(nps_date)}</label>
						</div>
						</c:if>
					</div>
					<div class="list-info">
						<dl>
							<dt>최근 1년간 국민연금 납부 내역 등록</dt>
						</dl>
					</div>
				</div>
			</div>
			<div class="container-fluid" onclick="onClick('nts');">
				<div class="require-title">
					<div class="list-heading">
						<div>
							<span class="thumb-logo pull-left" style="background-image:url('/fincorp/getFinCorpIcon.crz?cd_fc=0010471');"></span>
							<li>국세청 HomeTax</li>
						</div>
						<c:if test="${!empty nts_status}">
						<div class="label-right">
							<label class="label-type">${nts_status} |${ufn:formatDateDot(nts_date)}</label>
						</div>
						</c:if>
					</div>
					<div class="list-info">
						<dl>
							<dt>직전 귀속년도 소득 등록</dt>
						</dl>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</body>
</html>
