<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
		
	});

	function popSecurityGuide() {
		
		$("#popModal").modal("show");
	
		$.ajax({
			url : "<c:url value='/m/base/popSecurityCodeGuide.crz'/>",
			data : {},
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function(result) {
				$("#modal-content").html(result);
			},
			error : function(e) {
                errMsg(e);

			}
		});
	}

	function popSecurityCode() {
		
		$("#popModal").modal("show");
		
		$.ajax({
			url : "<c:url value='/m/base/popSecurityCode.crz'/>",
			data : {},
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function(result) {
				$("#modal-content").html(result);
			},
			error : function(e) {
                errMsg(e);

			}
		});
	}
	
	function findPwd(){
		frmSuccessLogin.action = "<c:url value='/m/person/frameFindPwdStep1.crz'/>";
		frmSuccessLogin.submit();
	}
</script>
<form name="frmSuccessLogin" method="post">
<div class="modal-body">
	<div class="security-code-block">
		<h2 class="title">비밀번호 입력</h2>
		<p>비밀번호를 입력하세요</p>
		<div class="code-group clearfix">
			<div class="code" autofocus onclick="popSecurityCode()">
				<input type="tel" class="form-control code-no" maxlength="1" value="" placeholder="" autocomplete="off" readonly="readonly"/>
				<input type="tel" class="form-control code-no" maxlength="1" value="" placeholder="" autocomplete="off" readonly="readonly"/>
				<input type="tel" class="form-control code-no" maxlength="1" value="" placeholder="" autocomplete="off" readonly="readonly"/>
				<input type="tel" class="form-control code-no" maxlength="1" value="" placeholder="" autocomplete="off" readonly="readonly"/>
			</div>
		</div>
		<div class="btn-area">
			<a role="button" class="btn btn-xs btn-link" onclick="popSecurityGuide()">비밀번호 이용안내</a>
			<a role="button" class="btn btn-xs btn-link" onclick="findPwd();">비밀번호 찾기</a>
		</div>
	</div>
</div>
</form>
