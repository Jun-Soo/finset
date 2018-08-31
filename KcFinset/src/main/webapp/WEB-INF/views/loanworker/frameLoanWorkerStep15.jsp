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
</head>
<body>
<form name="frmloanApplyStep" id="frmloanApplyStep" action="<c:url value='/m/loan/frameLoanStep1.crz'/>" method="post">
	<input type="hidden" name="loan_code">
	<input type="hidden" name="title">
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="goGoodsMain();">뒤로가기</button>
			</div>
			<h1>신청완료</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>대출신청이  완료 되었습니다.</p>
			</div>
		</div>
		<div class="list-block">
			<div class="container-fluid prd-loan">
				<div class="list-heading">
					<span class="label label-status status-blue">신청가능</span>
					<span class="label label-status status-gray">신청불가능</span>
					<span class="label label-status status-green">신청완료</span>
					<span class="label label-status status-red">조회오류</span>
					<div class="bank-title">
						<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?file_name=${List.path_file1}');"></span>${ufn:getNmFc(List.cd_fc)}
					</div>
					<h2 class="prd-title">대출상품명</h2>
				</div>
				<div class="list-info">
					<dl>
						<dt>대출금리</dt>
						<dd></dd>
					</dl>
					<dl>
						<dt>대출한도</dt>
						<dd></dd>
					</dl>
					<dl>
						<dt>대출기간</dt>
						<dd></dd>
					</dl>
					<dl>
						<dt>상환방식</dt>
						<dd></dd>
					</dl>
				</div>
			</div>	
		</div>
		<div class="container noti-section">
			<ul>
				<li>대출신청 후 신용등급 변동, 입력정보 오류 등으로 인해 금리/한도가 변동되거나 대출이 불가능 할  수 있습니다.</li>
				<li>신청 이후 진행사항은 마이페이지(상품신청현황)에서 확인가능합니다.</li>
			</ul>
		</div>
		<div class="btn-fixed-bottom affix-bottom" id="next_div">
			<a href="<c:url value='/m/loanworker/frameLoanWorkerStep1.crz'/>" role="button" class="btn btn-lg btn-block btn-primary">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
