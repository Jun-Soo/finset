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
			<h1>사업자명/소득정보 확인</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>사업자명/소득정보 확인을 위해<br>공인인증을 해주세요.</p>
			</div>	
			<div class="form-inline">
				<div class="form-group">
					<label for="">인증서 선택</label>
					<select class="selectpicker" data-header="옵션선택">
						<option>인증서 리스트에서 선택</option>
						<option>옵션 1</option>
						<option>옵션 2</option>
						<option>옵션 3</option>
					</select>
				</div>
				<div class="form-group has-feedback">
					<label for="">발급기간</label>					
					<input type="text" class="form-control" name="" id="" placeholder="">
				</div>
				<div class="form-group">
					<label for="">종류</label>
					<input type="text" class="form-control" name="" id="" placeholder="">
				</div>
				<div class="form-group">
					<label for="">만료일</label>
					<input type="text" class="form-control" name="" id="" placeholder="">
				</div>
			</div>
			<div class="btn-area mar_T40">
				<a href="#none" class="btn btn-xs btn-link" data-toggle="modal" data-target="#popModal" data-whatever="공인인증 건너뛰기" data-url="../popup/sert_pass.html">공인인증 건너뛰기</a>
			</div>
			<div class="btn-fixed-bottom">
				<a href="#" class="btn btn-lg btn-primary btn-block" disabled="disabled">확인</a>
			</div>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
