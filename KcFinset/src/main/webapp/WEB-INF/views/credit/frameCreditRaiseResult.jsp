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
	var result = "<c:out value="${result}" escapeXml="false"/>";
	if(result == "SUCCESS")	{
		setSuccess();	
	}
	else	{
		setFail();
	}
});

function setSuccess() {
	$("#div_fail").hide();
	$("#div_success").show();
}

function setFail() {
	$("#div_success").hide();
	$("#div_fail").show();
}

function countercheck() {
	var data = {"scrap_code":"nhis"};
	$.ajax({
		url : "<c:url value='/kcb/updateKcbReqNonfiInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = "FAILED";
			if(result.returnData == '00') {
				returnData = "SUCCESS"		
			}
			frmCreditRaiseNhis.action = "<c:url value='/m/credit/frameCreditRaiseResult.crz?result="+returnData+"'/>";
			frmCreditRaiseNhis.submit();
		},
		error : function (e) {
			errMsg(e);
		}
	});
}

function goHome() {
	frmCreditRaiseResult.action = "<c:url value='/m/credit/frameCreditRaise.crz'/>";
	frmCreditRaiseResult.submit();
}

</script>
</head>
<body class="bg-white">
<div id="div_success" class="credit_result div_hidden">
	<div class="container">
	<form name="frmCreditRaiseResult" method="post">
	</form>
		<div class="lead">
			<p>정상적으로 접수되었습니다.</p>
			<br/>
			<p>해당 자료를 분석 후 신용평가에 반영될</p>
			<p>예정입니다.</p>
		</div>
	</div>
	<div id="btn_success" class="btn-fixed-bottom affix-bottom">
		<a role="button" class="btn btn-lg btn-block btn-primary" onclick="goHome()">홈으로</a>
	</div>
</div>
<div id="div_fail" class="credit_result div_hidden">
	<div class="container">
		<div class="lead">
			<p>전송에 실패하였습니다</p>
			<p>다시 전송하시려면 재전송 버튼을 눌러주세요.</p>
		</div>
	</div>
	<div id="btn_fail" class="btn-fixed-bottom affix-bottom">
		<div class="col-xs-6">
			<a role="button" class="btn btn-lg btn-block btn-primary" onclick="countercheck()">재전송</a>
		</div>
		<div class="col-xs-6">
			<a role="button" class="btn btn-lg btn-block btn-primary" onclick="goHome()">홈으로</a>
		</div>
	</div>
</div>
</body>
</html>
