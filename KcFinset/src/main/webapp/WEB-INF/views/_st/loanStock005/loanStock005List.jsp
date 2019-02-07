<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ taglib prefix="ct" uri= "/WEB-INF/tlds/ct.tld" %>
<html>
<head>
<meta charset="UTF-8">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>

<script type="text/javascript">
    $(document).ready(function () {
    	
      
    	
    	//다음버튼
    	$("#confirmButton").click(function(){
 	    	//location.href="/m/loanstock/frameLoanStockStep7.crz";
 	    	var form = $("form[name=theForm]");
 	    	form.submit();
 	    });
        if(userAgent == "Android") {
            window.Android.setEndApp('Y');
        }
    });
	</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goCreditMain();">뒤로가기</button>
			</div>
			<h1>계좌번호 입력</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<form name="theForm" method="post" action="/m/loanstock/loanStock006List.json">
		
		<div class="list-block">	
			<div class="container-fluid">
				<div class="list-heading new-acc">
					<ul>
						<li class="bank-title">
							<!-- <span class="thumb-logo" style="background-image:url('/fincorp/getFinCorpIcon.crz?cd_fc=2000016');"></span> -->
							서울보증보험-하드코딩
						</li>
						<li>2,500만원 (+500만원)-하드코딩</li>
					</ul>
				</div>
				<div class="list-info">
					
					<input type="hidden"  name="inVOList[0].mbrCd" value="S063"/>
					
					
				</div>
			</div>
			<!-- 
			<div class="container-fluid">
				<div class="list-heading new-acc">
					<ul>
						<li class="bank-title">
							<span class="thumb-logo" style="background-image:url('/fincorp/getFinCorpIcon.crz?cd_fc=0010633');"></span>
							삼성화재해상보험-하드코딩
						</li>
						<li>2,500만원 (+500만원)-하드코딩</li>
					</ul>
				</div>
				<div class="list-info">
					<input type="hidden"  name="inVOList[1].mbrCd" value="S063"/>
				</div>
			</div>
			 -->
		</div>
		</form>
			<a role="button" id="confirmButton" class="btn btn-lg btn-primary btn-block" style="background-color:#283593 !important; color: #fff;">다음</a>
		<div class="btn-fixed-bottom" >
		</div>
	</section>
	<!-- //Content -->
</div>	
	
</body>
</html>