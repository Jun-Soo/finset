<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko" class="page-full">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
});

// 공인인증서 유무 체크
function checkExistCert() {
	if(userAgent == "iOS") {
		 //공인인증서 유무 체크 결과 콜백 이벤트
        Jockey.on("resultCheckCert" , function(param) {
        	var iscert = false;
        	if(param.isCert == 1) iscert = true;
        	resultCheckCert(iscert);
        });
        Jockey.send("checkExistCert");
	}
	else if(userAgent == "Android") {
		window.Android.checkExistCert();
	}
}

//공인인증서 유무 결과 (모바일에서 호출)
function resultCheckCert(isCert) {
	if(isCert) {	// 공인인증서가 있을 경우
		frmFcCertList();
	} else {		// 공인인증서가 없을 경우
		alert('공인인증서가 없습니다.');
		frmLogin.submit();
	}
}

//자동스크래핑 가능 금융사 조회
function frmFcCertList() {
	var noPerson = $('#j_username').val();	
	var bankCode = $('#bank_code').val();
	var cardCode = $('#card_code').val();
	var nmPerson = $('#nm_person').val();
	
	if(userAgent == "iOS") {
		/*Jockey.on("frmFcListNextFromMobile" , function(param) {
			frmFcListNextFromMobile();
		});

		Jockey.send("checkAvaliableScrapList" , {
			noPerson : noPerson,
			bankCode : bankCode,
			cardCode : cardCode
		});*/
		// do nothing
	} else if(userAgent == "Android") {
		window.Android.checkAvaliableScrapList(noPerson, bankCode, cardCode, nmPerson);
	}
}

//자동 스크래핑 등록 완료 시 (모바일에서 호출)
function frmFcListNextFromMobile() {
	frmLogin.submit();
}

function chkFinger(gubun){
	if(gubun == 'Y'){
		$('#yn_fingerprint').val('Y');
		if(userAgent == "Android") {
			window.Android.initFingerPrint();
		}else if(userAgent == "iOS") {
			//지문인식 결과 콜백 이벤트
			Jockey.on("resultFingerPrint",function(param) {
				resultFingerPrint(param.result);
			});
			
			Jockey.send("initFingerPrint" );
		}
	} else if(gubun == 'N') {
		$('#yn_fingerprint').val('N');
	}
	
	var data = frmLogin.ajaxSubmit();
	$.ajax({
		url : "<c:url value='/m/person/modifyFingerPrint.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			if(result.result == '00') {
				toastMsg('지문 설정이 완료 되었습니다.');
// 				frmSuccessStep.action= "<c:url value='/m/credit/frameCreditInfoMain.crz'/>";
//				frmSuccessStep.action = "<c:url value='/m/login/frameKcbCrawling.crz'/>";

				if(userAgent == "Android" && $('#site').val() != "REAL") {
					checkExistCert();
				}
				else	{
					setTimeout(function(){
						frmLogin.submit();
					}, 2000);
				}
			} else {
				
			}
		},
		error : function (e) {
            errMsg(e);
		}
	});
}

//지문인증 callback
function resultFingerPrint(result){
	if(result == true || result == 1){
		if(userAgent == "Android") {
			window.Android.closeFingerPrint();
		}
	} else {
		return false;
	}
}
</script>

</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>지문인증 설정</h1>
		</div>
	</header>
	<!-- Content -->
	<form name="frmLogin" action="<c:url value='/j_spring_security_check'/>" method="POST">
		<input type="hidden" id="j_username" name="j_username" class="form-control" value="${no_person}"/>
		<input type="hidden" id="j_password" name="j_password" class="form-control" value="${pass_person}" autocomplete="off"/>
		<input type="hidden" name="yn_fingerprint" id="yn_fingerprint" value=""/>
		<input type="hidden" name="bank_code" id="bank_code" value="${bank_code}"/>
		<input type="hidden" name="card_code" id="card_code" value="${card_code}"/>
		<input type="hidden" id="site" name="site" value="${site}"/>
		<input type="hidden" id="nm_person" name="nm_person" value="${nm_person}"/>
	</form>
	<section id="content">
		<div class="container security-code">
			<div class="security-code-wrap security-finger">
				<div class="fingerprt-cert"></div>
				<p>
					지문인증을 사용하시겠습니까?
				</p>
			</div>
			<div class="btn-fixed-bottom affix-bottom">
				<div class="col-xs-6">
					<button type="button" class="btn btn-lg btn-default btn-block" onclick="chkFinger('N');">아니오</button>
				</div>
				<div class="col-xs-6">
					<button type="button" class="btn btn-lg btn-primary btn-block" onclick="chkFinger('Y');">예</button>
				</div>
			</div>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
