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
		
	});

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
			<h1>사업자명 직접입력</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>사업자명을 직접 입력해 주세요.</p>
				<small>사업자번호는 “-” 없이 입력해 주세요.</small>
			</div>
			<div class="form-inline">
				<div class="form-group">
					<label for="">사업자명</label>
					<input type="text" class="form-control" placeholder="사업자명을 입력해주세요." autocomplete="off"/>
				</div>
				<div class="form-group">
					<label for="">사업자번호</label>
					<input type="number" class="form-control" placeholder="사업자번호를 입력해주세요." autocomplete="off"/>
				</div>
			</div>
		</div>
		<div class="btn-fixed-bottom">
			<a href="#" class="btn btn-lg btn-primary btn-block" disabled="disabled">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
