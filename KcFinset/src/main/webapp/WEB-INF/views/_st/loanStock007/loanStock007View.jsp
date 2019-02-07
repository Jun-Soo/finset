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
    	
   		$('.checkb').change(function() {
        	if ( $('.checkb').is(':checked') == true ) {
        		$('.checkb').prop('checked',false);
        		$(this).prop('checked',true);
        		affixBottom('show');
        	} else {
        		affixBottom('hide');
        		$(this).prop('checked',false);
        	}
   		});
    	
   		//다음버튼
   		$("#confirmButton").click(function(){
	    	location.href="/m/loanstock/frameLoanStockStep6.crz";
	    });
        if(userAgent == "Android") {
            window.Android.setEndApp('Y');
        }
    });

	</script>
</head>
<body>
<c:if test="${errorMessage != '' }">
	<script type="text/javascript">alert("${errorMessage }");</script>
</c:if>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="javascript:history.back();">뒤로가기</button>
			</div>
			<h1>상품상세</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="list-block">	
			<div class="container-fluid">
				<div class="list-heading">
					<ul>
						<li class="bank-title">
							<!-- <span class="thumb-logo" style="background-image:url('/fincorp/getFinCorpIcon.crz?cd_fc=2000016');"></span> -->
							${outVO.mbrNm} / ${outVO.crdtIttNm}
						</li>
					</ul>
					<div class="loan">
						<ul>
							<li class="name">${outVO.prdtNm}</li>
						</ul>
					</div>
				</div>
				<div class="list-info">
					<dl>
						<dt>금리</dt>
						<dd class="fs-red"><fmt:formatNumber value="${outVO.loanIntrstRt}" pattern="##0.00"/>%</dd>
					</dl>
					<dl>
						<dt>한도</dt>
						<dd><fmt:formatNumber value="${outVO.loanMaxLmtAmt/10000}" pattern="#,#00"/>만원</dd>
					</dl>
					<dl>
						<dt>기간</dt>
						<dd><fmt:formatNumber value="${outVO.loanTerm}"/>개월</dd>
					</dl>
				</div>
				<div class="loan-view list-info">
					<strong>상환방식</strong>
					<ul>
						<li>${outVO.rfundMthd eq "0" ? "만기일시" : "만기"}</li>
					</ul>
					<strong>한종목 투자한도</strong>
					<dl>
						<dt>동일종목 투자한도</dt>
						<dd>${outVO.isuInvstLmt}%</dd>
					</dl>
					<dl>
						<dt>로스컷비율 (최저담보 유지비율)</dt>
						<dd>${outVO.lscutSetRt}%</dd>
					</dl>
					<dl>
						<dt>현금인출비율</dt>
						<dd>${outVO.mnyOutAblRt}%</dd>
					</dl>
					<dl>
						<dt>만기연장비율</dt>
						<dd>${outVO.expExtndAblRt}%</dd>
					</dl>
					<strong>연체이율</strong>
					<ul>
						<li><fmt:formatNumber value="${outVO.ovdIntrstRt}" pattern="##0.00"/>%</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	</div>	
</body>
</html>