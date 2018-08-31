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

	function modifyPersonEmail() {
	
		var frm = document.frmModifyPersonEmail;
		
		var data = frmModifyPersonEmail.ajaxSubmit();
		if(data == null) return false;
	
		$.ajax({
			url: "<c:url value='/m/person/modifyPersonEmail.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					toastMsg('변경내용이 저장되었습니다.');
					//마이페이지 - 내정보로 이동
					setTimeout(function(){
						frm.action = "<c:url value='/m/customercenter/frameCustomerMyInfo.crz'/>";
						frm.submit();
					}, 300);
				}else if(result.result == '01'){
					toastMsg(result.message);
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
	
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
			<h1>이메일 변경</h1>
		</div>
	</header>
	<!-- Content -->
	<form name="frmModifyPersonEmail" id="frmModifyPersonEmail" method="post" onsubmit="return false;">
	<section id="content">
		<div class="container-fluid top-block user-modify">
			<h2 class="h2">이메일 주소</h2>
			<input type="text" id="email" name="email" value="${personInfo.email}" maxlength="30" onkeypress="if(event.key=='Enter'){modifyPersonEmail();}" class="form-control form-control-line" placeholder="이메일주소를 입력해주세요." autocomplete="off">
		</div>
		<div class="btn-fixed-bottom affix-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="modifyPersonEmail();">저장</a>
		</div>
	</section>
	</form>
</div>
</body>
</html>