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
	$("#chkDataDelYn").click(function(){
		if( $(this).is(":checked") ) {
// 			affixBottom('show');
			enableBottom('true');
		} else {
// 			affixBottom('hide');
			enableBottom('false');
		}
	});
	
	//탈퇴 
	$("#alertMsg .btn-lg").each(function () {
		$(this).on("click", function() {
			if("Y" == $(this).attr("data-val")){
				myPageConfirm();
			}
        });
	});
});

//탈퇴 약관체크
function quitChkYn(){

	//동의체크박스 체크 여부
	if($("input:checkbox[name='chkDataDelYn']").is(":checked") == false ||
			$("input:checkbox[name='checkbox1']").is(":checked") == false || 
			$("input:checkbox[name='checkbox2']").is(":checked") == false || 
			$("input:checkbox[name='checkbox3']").is(":checked") == false|| 
			$("input:checkbox[name='checkbox4']").is(":checked") == false|| 
			$("input:checkbox[name='checkbox5']").is(":checked") == false) {
		toastMsg('필수 약관을 모두 동의해주세요');
		setTimeout(function(){}, 2000);
		return false;
	}
	
	alertMsg('정말로 탈퇴 하시겠습니까?');
	
}

//탈퇴하기
function myPageConfirm() {
	var frm = document.frmQuit;
	
	frm.action="/m/customercenter/frameCustomerQuitComp.crz"; 
	frm.method="post"; 
	frm.submit(); 
	
}

//전체 항목동의
function chkAll(){
	if($('#chkDataDelYn').is(":checked")){
		$("input:checkbox[id='checkbox1']").prop("checked", true);
		$("input:checkbox[id='checkbox2']").prop("checked", true);
		$("input:checkbox[id='checkbox3']").prop("checked", true);
		$("input:checkbox[id='checkbox4']").prop("checked", true);
		$("input:checkbox[id='checkbox5']").prop("checked", true);
		$('#chkDataDelYn').parents(".checkbox").addClass("check");
	} else {
		$("input:checkbox[id='checkbox1']").prop("checked", false);
		$("input:checkbox[id='checkbox2']").prop("checked", false);
		$("input:checkbox[id='checkbox3']").prop("checked", false);
		$("input:checkbox[id='checkbox4']").prop("checked", false);
		$("input:checkbox[id='checkbox5']").prop("checked", false);
		$('#chkDataDelYn').parents(".checkbox").removeClass("check");
	}
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
			<h1>서비스 탈퇴</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<form name="frmQuit" method="post">
			<div class="lead">
				<p>서비스 탈퇴를 위해<br />아래 안내를 읽고 동의해주세요.</p>
			</div>
			<div class="container-fluid inner area-quit">
				<h2 class="h2">서비스 탈퇴 안내</h2>
				<div class="checkbox chk-check"> 
					<input type="checkbox" name="checkbox1" id="checkbox1" disabled />
					<p>서비스를 탈퇴하시면 핀셋에서 제공하는 모든서비스를 사용할 수 없으며, 재가입하셔도 기존 서비스 이용내역을 조회하실 수 없습니다.</p>
				</div>
				<div class="checkbox chk-check">
					<input type="checkbox" name="checkbox2" id="checkbox2" disabled /> 
					<p>서비스 이용시 등록한 회원님의 각종 인증정보는 더 이상 사용하실 수 없으며 재가입시 모든정보를 재등록 하셔야 합니다.</p>
				</div>
				<div class="checkbox chk-check"> 
					<input type="checkbox" name="checkbox3" id="checkbox3" disabled />
					<p>금융거래를 하지 않으신 회원님의 회원정보는 서비스 탈퇴 즉시 삭제됩니다.</p>
				</div>
				<div class="checkbox chk-check"> 
					<input type="checkbox" name="checkbox4" id="checkbox4" disabled />
					<p>금융거래가 진행된 고객님의 개인신용정보는 금융거래 종료 후 5년 이내 다른정보와 분리하여 안전하게 보관됩니다.</p>
				</div>
				<div class="checkbox chk-check"> 
					<p>서비스 탈퇴후 일정기간 재가입이 불가능합니다.</p>
					<input type="checkbox" name="checkbox5" id="checkbox5" disabled />
				</div>
				<div class="check-all">
					<div class="checkbox"><label><input type="checkbox" id="chkDataDelYn" name="chkDataDelYn" onchange="chkAll();"/> 동의합니다.</label>	</div>
				</div>
			</div>
		</form>
		<div class="btn-fixed-bottom affix-bottom" id="my_page_btn_container">
			<button type="button" class="btn btn-lg btn-disabled btn-block" onclick="quitChkYn();">탈퇴하기</button>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
