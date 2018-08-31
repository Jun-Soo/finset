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
	<div class="container progress-txt">
		<!-- <p class="lead">선택하신 상품의 금리/한도를<br>조회하고 있습니다. <small>(최대 5분 소요)</small></p> -->
		<div class="lead"><p>올바른 금융습관 FINSET</p></div>
	</div>
</div>
<form name="frmStep" method="post" action="" /> 
<% String linkUrl = request.getParameter("linkUrl"); %>


</body>
<script type="text/javascript">
$(window).load(function() {
	
	var linkUrl = '<%=linkUrl%>';
	frmStep.action = linkUrl;
	
	$.ajax({
		url : "<c:url value='/m/login/getKcbCrawling.json'/>",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			
			if(result.cd_result == '00') {
				
				frmStep.submit();
			} else {
				toastMsg("최신 정보 업데이트에 실패하였습니다. 재로그인시 다시 시도됩니다.");
				setTimeout(function(){
					frmStep.submit();
				}, 2000);
			}
			
		},
		error : function (e) {
			toastMsg("최신 정보 업데이트에 실패하였습니다. 재로그인시 다시 시도됩니다.");
            setTimeout(function(){
            	frmStep.submit();
			}, 2000);
		}
	});
});
</script>
</html>

