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
		$("#txt_detail").keydown(function (key) {
	        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
	        	goSearch();
	        }
	    });
	});
	
	//리스트 조회
	function goSearch() {
		$('#page').val(1);
		frmFaq.action = "<c:url value='/m/customercenter/frameCustomerFAQSearch.crz'/>";
		frmFaq.submit();
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
			<h1>자주묻는질문</h1>
		</div>
	</header>
	<div class="search-block affix-fixed">
		<div class="form-group has-search">	
			<label for="" class="sr-only">자주묻는 질문 검색</label>			
			<form method="post" name="frmFaq" id="frmFaq">	
				<input type="search" class="form-control" maxlength="255" name="txt_detail" id="txt_detail" placeholder="자주묻는 질문 검색" autocomplete="off">
				<input type="hidden" name="page" id="page"/>
			</form>
		</div>
	</div>
	<!-- Content -->
	<div id="content">
		<div class="container-fluid">
				<div class="list-group">
					<a href="<c:url value='/m/customercenter/frameCustomerFAQDetail.crz'/>?id_board=credit" class="list-group-item">
						<h3 class="h3">신용관리</h3>
					</a>

					<a href="<c:url value='/m/customercenter/frameCustomerFAQDetail.crz'/>?id_board=debt" class="list-group-item">
						<h3 class="h3">부채관리</h3>
					</a>

					<a href="<c:url value='/m/customercenter/frameCustomerFAQDetail.crz'/>?id_board=goods" class="list-group-item">
						<h3 class="h3">상품조회</h3>
					</a>

					<a href="<c:url value='/m/customercenter/frameCustomerFAQDetail.crz'/>?id_board=security" class="list-group-item">
						<h3 class="h3">보안</h3>
					</a>

					<a href="<c:url value='/m/customercenter/frameCustomerFAQDetail.crz'/>?id_board=etc" class="list-group-item">
						<h3 class="h3">기타</h3>
					</a>
				</div>	
			</div>
		</div>
	</div>
	<!-- //Content -->
</div>
</body>
</html>
