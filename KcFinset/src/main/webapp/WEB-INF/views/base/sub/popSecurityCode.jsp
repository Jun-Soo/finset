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
	
	function checkSecurityCode() {
		if($("#chk_pwd").val() ==  "undefined" || $("#chk_pwd").val() == '') {
			$("#chk_pwd").val('0');
		}
		var chk_pwd = Number($("#chk_pwd").val());
		// 비밀번호 체크 ajax
		if ( !frmCheckSecurityCode.validateForm() ) return false;
		var data = frmCheckSecurityCode.ajaxSubmit();
		if(data == null) return false;
		$.ajax({
			url : "<c:url value='/m/person/loginChkCode.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == '00') {
					$("#popModal").modal("hide");
					securityCodeSuccess();
				} else {
					chk_pwd += 1;
					$("#chk_pwd").val(chk_pwd);
// 					alert(result.message+"("+chk_pwd+"/5)");
					setTimeout(function(){
						$("#err_message").css('color', 'red');
						$("#err_message").html(result.message+"("+chk_pwd+"/5)");
					}, 300);
// 					sleep(2000);

					if(chk_pwd == 5) {
						setTimeout(function(){
							frmCheckSecurityCode.action = "<c:url value='/m/person/frameFindPwdStep1.crz'/>";
							frmCheckSecurityCode.submit();
						}, 300);
					}
					for(var i=1; i<=6; i++){
						$('#pass_number'+i).val('');
					}
				}
			},
			error : function (e) {
                errMsg(e);

			}
		});
	}
	
	function securityCodeSuccess() {
		if(userAgent == "iOS") {
// 			location.href = "call://securityCodeSuccess";
			Jockey.send("securityCodeSuccess");
		} else if(userAgent == "Android") {
			window.Android.securityCodeSuccess();
		}
	}
	
	function popSecurityCodeInfo() {
		
		$("#popModal").modal("show");
		
		$.ajax({
			url : "<c:url value='/m/base/popSecurityCodeInfo.crz'/>",
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
</script>
<form name="frmCheckSecurityCode" id="frmCheckSecurityCode" method="post">
	<input type="hidden" name="chk_pwd" id="chk_pwd" />
	<div class="modal-body">
		<div class="security-code-block">
			<h2 class="title">비밀번호 입력</h2>
			<p id="err_message" >비밀번호를 입력하세요</p>
			<div class="code-group clearfix">
				<div class="code">
					<input type="password" readonly="readonly" class="form-control code-no" name="pass_number" id="pass_number1" maxlength="1" value="" validate="required; label:보안코드;numeric;" autocomplete="off"/>
					<input type="password" readonly="readonly" class="form-control code-no" name="pass_number" id="pass_number2" maxlength="1" value="" validate="required; label:보안코드;numeric;" autocomplete="off"/>
					<input type="password" readonly="readonly" class="form-control code-no" name="pass_number" id="pass_number3" maxlength="1" value="" validate="required; label:보안코드;numeric;" autocomplete="off"/>
					<input type="password" readonly="readonly" class="form-control code-no" name="pass_number" id="pass_number4" maxlength="1" value="" validate="required; label:보안코드;numeric;" autocomplete="off"/>
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
	<li class="state"><button type="button" class="btn btn-lg btn-block" onclick="popSecurityCodeInfo();">닫기</button></li>
</ul>
<a role="button" class="btn btn-lg btn-primary btn-block" onclick="checkSecurityCode()">확인</a>
