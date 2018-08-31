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

//validate
function validShareInfoSet(){
	var creditOfferChk = $("input:checkbox[id='yn_credit_offer']").is(":checked"); //신용등급 및 연체정보 체크여부
	var debtOfferChk = $("input:checkbox[id='yn_debt_offer']").is(":checked"); //대출개설 및 잔고현황 체크여부
	var incomeOfferChk = $("input:checkbox[id='yn_income_offer']").is(":checked"); //소득정보 체크여부
	var assetItgtMngmChk = $("input:checkbox[id='yn_asset_itgt_mngm']").is(":checked"); //자산 체크여부
	var consumeItgtMngmChk = $("input:checkbox[id='yn_consume_itgt_mngm']").is(":checked"); //소비 체크여부
	var debtItgtMngmChk = $("input:checkbox[id='yn_debt_itgt_mngm']").is(":checked"); //부채 체크여부
	
	if(!creditOfferChk && !debtOfferChk && !incomeOfferChk
			&& !assetItgtMngmChk && !consumeItgtMngmChk && !debtItgtMngmChk){
		toastMsg("항목을 선택해 주세요.");
		return false;
	}
	
	return true;
}

//공유재요청
function updateShareInfoSet01(){
	$("#share_status").val('');
	
	if(!validShareInfoSet()) return false;
	
	//update
	var data = frmShareInfoSetting.ajaxSubmit();
	if(data == null){
		return false;
	}
	$.ajax({
		url : "<c:url value='/m/customercenter/updatePersonShareInfoSet01.json'/>",
		data : data,
		async: false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			if("N" == result.chkMessageTerm){
				toastMsg("하루에 한번 재요청 가능합니다.");
				return false;
			}else{
				toastMsg(result.message);
				if("00" == result.cdResult){
					sendMsg(result.typeMessage);
				}
			}
		},
		error : function (e) {
            errMsg(e);

		}
	});
}

//허용 / 거절
function updateShareInfoSet02(share_status){
	
	$("#share_status").val(share_status);
	
	var creditOfferChk = $("input:checkbox[id='yn_credit_offer']").is(":checked"); //신용등급 및 연체정보 체크여부
	var debtOfferChk = $("input:checkbox[id='yn_debt_offer']").is(":checked"); //대출개설 및 잔고현황 체크여부
	var incomeOfferChk = $("input:checkbox[id='yn_income_offer']").is(":checked"); //소득정보 체크여부
	
	//허용시 정보제공항목 존재여부 확인
	if(share_status == "02"){
		if(creditOfferChk || debtOfferChk || incomeOfferChk){
			$("#yn_offer").val("Y");
		}
	}
	
	if(!validShareInfoSet()) return false;
	
	//update
	var data = frmShareInfoSetting.ajaxSubmit();
	if(data == null){
		return false;
	}
	$.ajax({
		url : "<c:url value='/m/customercenter/updatePersonShareInfoSet02.json'/>",
		data : data,
		async: false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			toastMsg(result.message);
			if("00" == result.cdResult){
				sendMsg("push");
			}
		},
		error : function (e) {
            errMsg(e);

		}
	});
}

//변경 / 해지
function updateShareInfoSet03(share_status){
	$("#share_status").val(share_status);
	
	if("04" != share_status){ //해지가 아닌경우
		if(!validShareInfoSet()) return false;
	}
	
	//update
	var data = frmShareInfoSetting.ajaxSubmit();
	if(data == null){
		return false;
	}
	$.ajax({
		url : "<c:url value='/m/customercenter/updatePersonShareInfoSet03.json'/>",
		data : data,
		async: false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			toastMsg("test");
			toastMsg(result.message);
			if("00" == result.cdResult){
				sendMsg("push");
			}
		},
		error : function (e) {
            errMsg(e);

		}
	});
	
}

function sendMsg(typeMessage){
	if("sms" == typeMessage){ //sms보내기
		var msg;
		
		if('${setting_mode}' == '01') { //공유재요청
			msg = "${shareInfo.req_nm_person}님으로부터 공유요청이 왔습니다. \n"
			msg += "아래의 링크를 선택하여 FINSET을 \n"
			msg += "설치하시기 바랍니다. \n"
			msg += "https://play.google.com/store/apps/details?id=com.app.kc.koscom"
		}	
		
		if(userAgent == "Android") {
			window.Android.sendSms('${shareInfo.offer_hp}',msg);
		}else if(userAgent == "iOS"){
			//TODO ios sms문자발송 추가
		}
	}else if("push" == typeMessage){
		var data = frmShareInfoSetting.ajaxSubmit();
		if(data == null){
			return false;
		}
		$.ajax({
			url : "<c:url value='/m/customercenter/sendPersonShareInfoPush.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				if("00" == result.cdResult){
					toastMsg("푸시발송에 성공했습니다.");
					goShareInfoMain();
				}else if("01" == result.cdResult){
					toastMsg("하루에 한번 재요청 가능합니다.");
					reqUpdateYn = "Y";
				}else{
					toastMsg("푸시발송에 실패했습니다.");
					return false;
				}
			},
			error : function (e) {
	            errMsg(e);

			}
		});
	}
}

function resultSendSms(resultVal){
	if("00000000" == resultVal){
		
		toastMsg("문자발송에 성공했습니다.");
		
		var data = frmShareInfoSetting.ajaxSubmit();
		if(data == null){
			return false;
		}
		$.ajax({
			url : "<c:url value='/m/customercenter/createPersonShareInfoSms.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
// 				if("00" == result.cdResult){
// 					toastMsg("문자발송에 성공했습니다.");
// 					goShareInfoMain();
// 				}else{
// 					toastMsg("문자발송에 실패했습니다.");
// 					return false;
// 				}
			},
			error : function (e) {
	            errMsg(e);

			}
		});
		
		goShareInfoMain();
		
	}else{
		toastMsg("문자발송에 실패했습니다.");
		return false;
	}
}

//공유관리 메인으로 이동
function goShareInfoMain(){
	if('${setting_mode}' == '01'){ //공유재요청시 요청탭으로 이동
		$("#type_list").val('offer');
	}else{ //허용 / 거절 / 변경 / 해지시 허용탭으로 이동
		$("#type_list").val('req');
	}
	frmShareInfoSetting.action = "/m/customercenter/frameShareInfoMain.crz";
	frmShareInfoSetting.submit();
}
</script>
</head>
<body>
<div id="wrapper" class="bg-white">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>공유 설정</h1>
		</div>
	</header>
	<!-- //Header -->
	<!-- Content -->
	<section id="content">
		<c:choose>
		<c:when test="${setting_mode eq '01'}">
			<div class="visual-banner middle-banner">
				<p class="msg">공유를 선택하시면 아래의 정보가 공유됩니다.</p>
				<br/>
				<p class="msg text-info">신용등급: 신용등급(점수 포함)과 연체정보</p>
				<p class="msg text-info">대출정보: 개설한 대출 현황 정보 및 잔액</p>
				<p class="msg text-info">은행계좌정보: 은행에 개설한 입출금, 적금계좌정보 및 잔액</p>
				<p class="msg text-info">카드정보: 보유한 카드 정보 및 사욯내역</p>
			</div>
		</c:when>
		<c:when test="${setting_mode eq '02'}">
			<div class="visual-banner middle-banner">
				<p class="msg">허용하기를 선택하시면 아래의 정보가 공유됩니다.</p>
				<br/>
				<p class="msg text-info">신용등급: 신용등급(점수 포함)과 연체정보</p>
				<p class="msg text-info">대출정보: 개설한 대출 현황 정보 및 잔액</p>
				<p class="msg text-info">소득정보</p>
				<br/>
				<p class="msg"> * 체크되어 있는 항목만 공유됩니다.</p>
			</div>
		</c:when>
		<c:when test="${setting_mode eq '03'}">
			<div class="visual-banner middle-banner">
				<p class="msg">아래의 정보가 ${shareInfo.req_nm_person}님에게 공유되고 있습니다.</p>
				<br/>
				<p class="msg text-info">신용등급: 신용등급(점수 포함)과 연체정보</p>
				<p class="msg text-info">대출정보: 개설한 대출 현황 정보 및 잔액</p>
				<p class="msg text-info">소득정보</p>
				<br/>
				<p class="msg"> * 체크되어 있는 항목만 공유됩니다.</p>
			</div>
		</c:when>
		</c:choose>
		<form name="frmShareInfoSetting" id="frmShareInfoSetting" method="post">
		<input type="hidden" id="setting_mode" name="setting_mode" value="${setting_mode}"/>
		<input type="hidden" id="seq_share" name="seq_share" value="${shareInfo.seq_share}"/>
		<input type="hidden" id="share_status" name="share_status"/>
		<input type="hidden" id="yn_offer" name="yn_offer" value="N"/>
		<input type="hidden" id="offer_hp" name="offer_hp" value="${shareInfo.offer_hp}"/>
		<input type="hidden" id="offer_no_person" name="offer_no_person" value="${shareInfo.offer_no_person}"/>
		<input type="hidden" id="offer_nm_person" name="offer_nm_person" value="${shareInfo.offer_nm_person}"/>
		<input type="hidden" id="req_hp" name="req_hp" value="${shareInfo.req_hp}"/>
		<input type="hidden" id="req_no_person" name="req_no_person" value="${shareInfo.req_no_person}"/>
		<input type="hidden" id="req_nm_person" name="req_nm_person" value="${shareInfo.req_nm_person}"/>
		<input type="hidden" id="type_list" name="type_list"/>
		<div class="container mt-3">
			<ul class="ul-share">
				<li class="li-share li-share-head">
					<h2 class="h2">정보 제공 항목</h2>
				</li>
				<li class="li-share li-share-tail"> 
					<div class="chk-share">  
						<input type="checkbox" id="yn_credit_offer" name="yn_credit_offer" value="Y"
						<c:if test="${shareInfo.yn_credit_offer eq 'Y'}"> checked="checked" </c:if>
						<c:if test="${setting_mode eq '02'}"> disabled="disabled"</c:if>
						/> <label for="chk1">신용등급 및 연체 정보</label>
					</div>
					<div class="chk-share">
						<input type="checkbox" id="yn_debt_offer" name="yn_debt_offer" value="Y"
						<c:if test="${shareInfo.yn_debt_offer eq 'Y'}"> checked="checked" </c:if>
						<c:if test="${setting_mode eq '02'}"> disabled="disabled" </c:if>
						/> <label for="chk2">대출개설 및 잔고 현황</label>
					</div>
					<div class="chk-share">
						<input type="checkbox" id="yn_income_offer" name="yn_income_offer" value="Y"
						<c:if test="${shareInfo.yn_income_offer eq 'Y'}"> checked="checked" </c:if>
						<c:if test="${setting_mode eq '02'}"> disabled="disabled" </c:if>
						/> <label for="chk3">소득정보</label>
					</div>
				</li>
				<li class="li-share li-share-head">
					<h2 class="h2">정보 통합 관리 항목</h2>
				</li>
				<li class="li-share li-share-tail">
					<div class="chk-share">
						<input type="checkbox" id="yn_asset_itgt_mngm" name="yn_asset_itgt_mngm" value="Y"
						<c:if test="${shareInfo.yn_asset_itgt_mngm eq 'Y'}"> checked="checked" </c:if>
						<c:if test="${setting_mode eq '02'}"> disabled="disabled" </c:if>
						/> <label for="chk4">자산</label>
					</div>
					<div class="chk-share">
						<input type="checkbox" id="yn_consume_itgt_mngm" name="yn_consume_itgt_mngm" value="Y"
						<c:if test="${shareInfo.yn_consume_itgt_mngm eq 'Y'}"> checked="checked" </c:if>
						<c:if test="${setting_mode eq '02'}"> disabled="disabled" </c:if>
						/> <label for="chk5">소비</label>
					</div>
					<div class="chk-share"> 
						<input type="checkbox" id="yn_debt_itgt_mngm" name="yn_debt_itgt_mngm" value="Y"
						<c:if test="${shareInfo.yn_debt_itgt_mngm eq 'Y'}"> checked="checked" </c:if> 
						<c:if test="${setting_mode eq '02'}"> disabled="disabled" </c:if>
						/> <label for="chk6">부채</label>
					</div>
				</li>
			</ul>
		</div>
		</form>
		<div class="btn-fixed-bottom affix-bottom">
			<c:choose>
			<c:when test="${setting_mode eq '01'}"> 
				<a role="button" class="btn btn-lg btn-block btn-primary" onclick="updateShareInfoSet01('');">공유 재요청</a>
			</c:when>
			<c:when test="${setting_mode eq '02'}">
				<div class="col-xs-6">
					<a role="button" class="btn btn-lg btn-block btn-primary" onclick="updateShareInfoSet02('02');">허용하기</a>
				</div>
				<div class="col-xs-6">
					<a role="button" class="btn btn-lg btn-block btn-default" onclick="updateShareInfoSet02('03');">거절하기</a>
				</div>
			</c:when>
			<c:when test="${setting_mode eq '03'}">
				<div class="col-xs-6">
					<a role="button" class="btn btn-lg btn-block btn-primary" onclick="updateShareInfoSet03('02');">변경하기</a>
				</div>
				<div class="col-xs-6">
					<a role="button" class="btn btn-lg btn-block btn-default" onclick="updateShareInfoSet03('04');">해지하기</a>
				</div>
			</c:when>
			</c:choose>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</body>
</html>
