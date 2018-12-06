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
//test
//resultAddress('박준수','01026882453');
});

//사용자 검색
function srcPerson(){
	if(userAgent == "Android") {
		window.Android.getAddressList();
	}
	else if(userAgent == "iOS"){
		//TODO ios 사용자검색 추가
// 		//앱버전 조회결과 콜백
// 		Jockey.on("getAddressList" , function (param) {
// 			resultAddress(param.src_nm_person, param.src_hp);
// 		});
	}
}

//사용자 검색결과 셋팅
function resultAddress(src_nm_person,src_hp){
	$("#offer_nm_person").val(src_nm_person);
	$("#offer_hp").val(src_hp);

}

//validate체크
function validShareInfoNewReq(){
	var creditOfferChk = $("input:checkbox[id='yn_credit_offer']").is(":checked"); //신용등급 및 연체정보 체크여부
	var debtOfferChk = $("input:checkbox[id='yn_debt_offer']").is(":checked"); //대출개설 및 잔고현황 체크여부
	var incomeOfferChk = $("input:checkbox[id='yn_income_offer']").is(":checked"); //소득정보 체크여부
	var assetItgtMngmChk = $("input:checkbox[id='yn_asset_itgt_mngm']").is(":checked"); //자산 체크여부
	var consumeItgtMngmChk = $("input:checkbox[id='yn_consume_itgt_mngm']").is(":checked"); //소비 체크여부
	var debtItgtMngmChk = $("input:checkbox[id='yn_debt_itgt_mngm']").is(":checked"); //부채 체크여부

	if($("#offer_hp").val() == ""){
		toastMsg("사용자를 검색해 주세요.");
		return false;
	}

	if(!creditOfferChk && !debtOfferChk && !incomeOfferChk
			&& !assetItgtMngmChk && !consumeItgtMngmChk && !debtItgtMngmChk){
		toastMsg("항목을 선택해 주세요.");
		return false;
	}

	return true;
}

//공유요청
function createShareInfo(){
	if(!validShareInfoNewReq()) return false;

	var data = frmShareInfoNewRequest.ajaxSubmit();
	if(data == null){
		return false;
	}
	$.ajax({
		url : "<c:url value='/m/customercenter/createPersonShareInfo.json'/>",
		data : data,
		async: false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			toastMsg(result.message);
			if("00" == result.cdResult){
				$("#seq_share").val(result.seq_share);

				sendMsg(result.typeMessage, result.req_nm_person);
			}
		},
		error : function (e) {
            errMsg(e);

		}
	});

}

//sms, push메세지 발송
function sendMsg(typeMessage, req_nm_person){
	if("sms" == typeMessage){ //sms보내기
		var offer_hp = $("#offer_hp").val();
		var msg;

		msg = req_nm_person+"님으로부터 공유요청이 왔습니다. \n"
		msg += "아래의 링크를 선택하여 FINSET을 \n"
		msg += "설치하시기 바랍니다. \n"
		msg += "https://play.google.com/store/apps/details?id=com.app.kc.koscom"

		if(userAgent == "Android") {
			window.Android.sendSms(offer_hp, msg);
		}else if(userAgent == "iOS"){
			//TODO ios sms문자발송 추가
		}
	}else if("push" == typeMessage){
		var data = frmShareInfoNewRequest.ajaxSubmit();
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

		var data = frmShareInfoNewRequest.ajaxSubmit();
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
				if("00" == result.cdResult){
					toastMsg("문자발송에 성공했습니다.");
					goShareInfoMain();
				}else{
					toastMsg("문자발송에 실패했습니다.");
					return false;
				}
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
	$("#type_list").val('offer');

	frmShareInfoNewRequest.action = "/m/customercenter/frameShareInfoMain.crz";
	frmShareInfoNewRequest.submit();
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
			<h1>사용자 선택</h1>
		</div>
	</header>
	<!-- //Header -->
	<!-- Content -->
	<section id="content">
	<form id="frmShareInfoNewRequest" name="frmShareInfoNewRequest" action="post">
	<input type="hidden" id="setting_mode" name="setting_mode" value="00"/>
	<input type="hidden" id="share_status" name="share_status" value="01"/>
	<input type="hidden" id="seq_share" name="seq_share"/>
	<input type="hidden" id="type_list" name="type_list"/>

		<div class="visual-banner middle-banner">
			<p class="msg">공유를 요청할 사용자를 선택해주세요</p>
		</div>
		<div class="container">
				<div class="form-inline">
					<div class="form-group">
						<label for="person_req">사용자</label>
						<input id="offer_nm_person" name="offer_nm_person" class="form-control" type="text" readonly="readonly"/>
						<input id="offer_hp" name="offer_hp" class="form-control" type="hidden" value="" />
						<div class="person_req" onclick="srcPerson();"></div>
					</div>
				</div>
		</div>
		<div class="container mt-3">
			<ul class="ul-share">
				<li class="li-share li-share-head">
					<h2 class="h2">정보 제공 항목</h2>
				</li>
				<li class="li-share li-share-tail">
					<div class="chk-share">
						<input type="checkbox" id="yn_credit_offer" name="yn_credit_offer" value="Y"/> <label for="chk1">신용등급 및 연체 정보</label>
					</div>
					<div class="chk-share">
						<input type="checkbox" id="yn_debt_offer" name="yn_debt_offer" value="Y"/> <label for="chk2">대출개설 및 잔고 현황</label>
					</div>
					<div class="chk-share">
						<input type="checkbox" id="yn_income_offer" name="yn_income_offer" value="Y"/> <label for="chk3">소득정보</label>
					</div>
				</li>
				<li class="li-share li-share-head">
					<h2 class="h2">정보 통합 관리 항목</h2>
				</li>
				<li class="li-share li-share-tail">
					<div class="chk-share">
						<input type="checkbox" id="yn_asset_itgt_mngm" name="yn_asset_itgt_mngm" value="Y"/> <label for="chk4">자산</label>
					</div>
					<div class="chk-share">
						<input type="checkbox" id="yn_consume_itgt_mngm" name="yn_consume_itgt_mngm" value="Y"/> <label for="chk5">소비</label>
					</div>
					<div class="chk-share">
						<input type="checkbox" id="yn_debt_itgt_mngm" name="yn_debt_itgt_mngm" value="Y"/> <label for="chk6">부채</label>
					</div>
				</li>
			</ul>
		</div>
		<div class="btn-fixed-bottom affix-bottom">
			<a role="button" class="btn btn-lg btn-block btn-primary" onclick="createShareInfo();">공유 요청</a>
		</div>
	</form>
	</section>
	<!-- //Content -->
</div>
</body>
</body>
</html>
