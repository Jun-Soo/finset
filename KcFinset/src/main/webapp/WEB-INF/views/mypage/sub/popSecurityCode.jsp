<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%-- <%@ include file="/WEB-INF/include/headComm.jsp"%> --%>
<script type="text/javascript">
	$(document).ready(function() {
		
		// 코드번호 입력
		$(".keypad [data-value]").each(function(){
			$(this).click(function(){
				var output = $(this).attr("data-value");
				$(".code .code-no").each(function(){
					if( $(this).val().length === 0 ) {
						$(this).val(output);
						return false;					
					}
				});
			});
		});
		
		// 코드번호 삭제
		jQuery.fn.reverse = [].reverse;
		$(".btn-backspace").click(function(){
			$(".code .code-no").reverse().each(function(){
				if( $(this).val().length !== 0 ) {
					$(this).val("");
					return false;					
				}
			});
		});
		
		window.setupValidateForm( frmCheckSecurityCode );
	});
	
	function confirmSecurityCode() {
		var securityCode = '';
		$("input[name='pass_number']").each(function (i) {
			 
			securityCode +=$("input[name='pass_number']").eq(i).val();

        });
		if(gubun == 'current') {
			$('#currentPwd').val(securityCode);
			
			
		} else if(gubun == 'change') {
			$('#changePwd').val(securityCode);
			
			
		} else if(gubun == 'confirm') {
			$('#ChangePwdConfirm').val(securityCode);
			
		}
		$("#popModal").modal("hide");
	}
	
	function securityCodeSuccess() {
		if(userAgent == "iOS") {
// 			location.href = "call://securityCodeSuccess";
			Jockey.send("securityCodeSuccess");
		} else if(userAgent == "Android") {
			window.Android.securityCodeSuccess();
		}
	}
	
	function closeSecurityCode() {
		
		$("#popModal").modal("hide");
		
	}

</script>
<form name="frmCheckSecurityCode" id="frmCheckSecurityCode" method="post">
	<div class="modal-body">
		<div class="security-code-block">
			<h2 class="title">비밀번호 입력</h2>
			<p>비밀번호를 입력하세요</p>
			<div class="code-group clearfix">
				<div class="code">
					<input type="password" readonly="readonly" class="form-control code-no" name="pass_number" maxlength="1" value="" validate="required; label:비밀번호;" autocomplete="off"/>
					<input type="password" readonly="readonly" class="form-control code-no" name="pass_number" maxlength="1" value="" validate="required; label:비밀번호;" autocomplete="off"/>
					<input type="password" readonly="readonly" class="form-control code-no" name="pass_number" maxlength="1" value="" validate="required; label:비밀번호;" autocomplete="off"/>
					<input type="password" readonly="readonly" class="form-control code-no" name="pass_number" maxlength="1" value="" validate="required; label:비밀번호;" autocomplete="off"/>
				</div>
			</div>
		</div>
	</div>
</form>
<ul class="keypad">
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="1">1</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="3">3</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="5">5</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="2">2</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="6">6</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="4">4</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="7">7</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="8">8</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="9">9</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key">&nbsp;</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key" data-value="0">0</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-key">&nbsp;</button></li>
	<li class="state"><button type="button" class="btn btn-lg btn-block">보안 키패드 작동 중</button></li>
	<li><button type="button" class="btn btn-lg btn-block btn-backspace">←</button></li>
	<li class="state"><button type="button" class="btn btn-lg btn-block" onclick="closeSecurityCode();">닫기</button></li>
</ul>
<a role="button" class="btn btn-lg btn-primary btn-block" onclick="confirmSecurityCode()">확인</a>
