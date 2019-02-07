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
    	$('.accountnumber').keyup(function (e){
            var content2 = $(this).val().length;
       		
       		if ( content2 > 8 && content2 <= 20 ) {
       			affixBottom('show');
       			return false;
       		} else if(content2 > 20){
       			alert("계좌번호는 20자리를 넘을 수 없습니다.");
       			affixBottom('hide');
       			return false;
       		}else {
       			affixBottom('hide');
       			return false;
       		}
        });
    	
    	//다음버튼
    	$("#confirmButton").click(function(){
 	    	//location.href="/m/loanstock/frameLoanStockStep7.crz";
 	    	var form = $("form[name=theForm]");
 	    	console.log(form);
 	    	form.submit();
 	    });
        if(userAgent == "Android") {
            window.Android.setEndApp('Y');
        }
		
        if('${errorMessage}' != ""){
			
		    alert('${errorMessage}');
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
		<!-- <form name="theForm" method="post" action="/m/st/loanStock012List.crz"> -->
		<form name="theForm" method="post" action="/m/loanstock/loanStock012List.json">
		
		<!-- <input type="hidden"  name="crdtIttCd" value="C001"/>
		<input type="hidden"  name="loanSrtDt" value="20180801"/>
		<input type="hidden"  name="loadEndDt" value="20180930"/>
		<input type="hidden"  name="nextKey" value="0"/>
		 -->
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
					<label for="accnum01" class="sound_only">계좌번호 입력</label>
					<input type="number" id="accnum01" name="inVOList[0].acntNo" class="accountnumber" value="30110100001" placeholder="계좌번호를 입력해 주세요." />
					<!-- 증권사코드 하드코딩 -->
					<input type="hidden"  name="inVOList[0].mbrCd" value="S063"/>
					
					
				</div>
			</div>
			<!-- input List Test
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
					<label for="accnum02" class="sound_only">계좌번호 입력</label>
					<input type="number" id="accnum02" name="inVOList[1].acntNo" class="accountnumber" placeholder="계좌번호를 입력해 주세요." />
					<input type="hidden"  name="inVOList[1].mbrCd" value="S063"/>
				</div>
			</div>
			 -->
		</div>
		</form>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" id="confirmButton" class="btn btn-lg btn-primary btn-block">다음</a>
		</div>
	</section>
	<!-- //Content -->
</div>	
	
</body>
</html>