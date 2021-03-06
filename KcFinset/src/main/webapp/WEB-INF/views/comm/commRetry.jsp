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
	//확인용 스크립트
});

function countercheck() {
	if(userAgent == "iOS") {
		Jockey.send("creditRatingUpgradeRetry");
	} else if(userAgent == "Android") {
		window.Android.creditRatingUpgradeRetry();
	}
}

function goHome() {
	if(userAgent == "iOS") {
		Jockey.send("closeWebView");
	} else if(userAgent == "Android") {
		window.Android.closeWebView();
	}
	
}

</script>
<style type="text/css">
.progress-wrap{
	text-align:center;
	position:fixed;
	top:0;left:0;right:0;bottom:0;
	/*z-index:999999;*/
    opacity:0;
	display:-webkit-flex;
    display:flex;
    -webkit-justify-content:center;
    justify-content:center;
    -webkit-align-items:center;
    align-items:center;
    /*background-color:rgba(0,0,0,.5);*/
    -webkit-transition: all 0.3s ease;
    transition: all 0.3s ease;
}
.progress-wrap.scraping{
    background-color: #f2f3f7;
}
.progress-wrap.show{
	opacity:1;
}
.loader{
  margin: 0 0 2em;
  height: 100px;
  width: 20%;
  text-align: center;
  padding: 1em;
  margin: 0 auto 1em;
  display: inline-block;
  vertical-align: top;
  position: absolute;
}
/*
  Set the color of the icon
*/
svg path,
svg rect{
  fill: #2b43ba;
}
.progress-txt{
    margin-top: 50px;
}
.progress-txt .lead{
	color: #777;
	font-size: 16px;
	font-weight: 600;
	text-align: center;
	line-height: 20px;
}
</style>
</head>
<body>
<div class="progress-wrap scraping show">
	<div class="loader">
		<svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="40px" height="40px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">
			<path fill="#000" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
				<animateTransform attributeType="xml" attributeName="transform" type="rotate" from="0 25 25" to="360 25 25" dur="0.6s" repeatCount="indefinite"/>
			</path>
		</svg>
	</div>
	<div class="container progress-txt">
		<form name="frmCommRetry" id="frmCommRetry" method="post">
		</form>
		<p class="lead">
			<c:if test="${!empty msg1}">
				<c:forEach var="split_msg1" items="${fn:split(msg1, '||')}" varStatus="status">
					<c:if test="${!status.first}"><br/></c:if><c:out value="${split_msg1}"/>
				</c:forEach>
			</c:if>
			<small>
				<c:if test="${!empty msg2}">
					<c:forEach var="split_msg2" items="${fn:split(msg2, '||')}" varStatus="status">
						<c:if test="${!status.first}"><br/></c:if><c:out value="${split_msg2}"/>
					</c:forEach>
				</c:if>
			</small>
		</p>
	</div>
	<div class="btn-fixed-bottom affix-bottom">
		<div class="col-xs-6">
			<a role="button" class="btn btn-lg btn-block btn-primary" onclick="countercheck()">재조회</a>
		</div>
		<div class="col-xs-6">
			<a role="button" class="btn btn-lg btn-block btn-primary" onclick="goHome()">홈으로</a>
		</div>
	</div>
</div>
</body>

</html>
