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
    	
   		
        if(userAgent == "Android") {
            window.Android.setEndApp('Y');
        }
    });

	</script>
</head>
<body>
<br>
<br>
<br>
<br>
<br>
<br>
	<form name="theForm" method="post" action="/m/st/customercenter020View.crz">
		계좌번호 : <input type="text" name="AcntNo" value="계좌번호" title="계좌번호"><br/>


		<input type="submit" value="전송">
	</form>
	<br/>
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
					<h1>상세이력</h1>
				</div>
			</header>
			<!-- Content -->
			<section id="content">
				<div class="list-block">	
					
					<div class="container-fluid">
						<div class="list-heading new-acc">
							<ul>
								<li class="bank-title">
									${outVO.shtnNm }
								</li>
								<%-- <li class="date"><fmt:formatNumber value="${outVO.loanMaxLmtAmt/10000}" pattern="#,#00"/>만원</li> --%>
							</ul>
						</div>
						<div class="list-info">
							<dl>
								<dt>${outVO.cvrgDttm }</dt>
								<dd>${outVO.cvrgQty }주</dd>
							</dl>
						</div>
					</div>
					
				</div>
			</section>
			<!-- //Content -->
		</div>
	</c:otherwise>
</c:choose>
	
</body>
</html>