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
	    	location.href="/m/st/customercenter020View.crz";
	    });
        if(userAgent == "Android") {
            window.Android.setEndApp('Y');
        }
    });

	</script>
</head>
<body>
<c:choose>
	<c:when test="${empty outVO.gridList }">
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
			<form name="theForm" method="post" action="/m/st/customercenter019List.crz">
				<!-- 증권사코드 하드코딩 -->
				<input type="hidden"  name="mbrCd" value="S063"/>
				<!-- 계좌번호 하드코딩 -->
				계좌번호 : <input type="text" name="AcntNo" value="111122229999" title="계좌번호"><br/>
		
				
		
				<input type="submit" value="전송">
			</form>
			<br/>
	</c:when>
	<c:otherwise>
	<c:choose>
		<c:when test="${errorMessage != '' }">
			<p>${errorMessage }</p>
		</c:when>
		<c:otherwise>
	
			<div id="wrapper">
				<!-- Header -->
				<header id="header">
					<div class="input-group">
						<div class="input-group-btn">
							<button type="button" class="ui-nav nav-back" onclick="goCreditMain();">뒤로가기</button>
						</div>
						<h1>반대매매</h1>
					</div>
				</header>
				<!-- Content -->
				<section id="content">
					<c:forEach var="gridVO" items="${outVO.gridList }" varStatus="GridListStatus">
					<div class="list-block">	
						<div class="container-fluid">
							<div class="list-heading new-acc">
								<ul>
									<li class="bank-title">
										<!-- <span class="thumb-logo" style="background-image:url('/fincorp/getFinCorpIcon.crz?cd_fc=2000016');"></span> -->
										${gridVO.mbrNm}(${inVO.acntNo })
									</li>
									<li class="date">${ufn:formatDateDot(gridVO.cvrgOcrDttm)}</li>
									<%-- <li class="date">${gridVO.cvrgOcrDttm}</li> --%>
								</ul>
							</div>
							<div class="list-info">
								<dl>
									<dt>대상상품</dt>
									<dd>${gridVO.prdtNm}</dd>
								</dl>
							</div>
						</div>
					</div>
					</c:forEach>
				</section>
				<!-- //Content -->
				</div>
			</c:otherwise>
		</c:choose>
			
	</c:otherwise>
</c:choose>


</body>
</html>