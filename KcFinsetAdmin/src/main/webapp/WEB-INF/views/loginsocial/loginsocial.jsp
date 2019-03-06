<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>로그인</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
var hotkey = false;
$(document).ready(function(){
	$(".alert").hide();
<c:if test="${!empty msg}">
	$("#msg").html("${msg}");
	$(".alert").fadeTo(5000, 100).slideUp(1000, function(){
		 $(".alert").alert('close');
	});
	${action}
</c:if>
	window.setupValidateForm( frmLoginSocial );
});
function isEnter(keyCode){
	if(keyCode == 13){
		goLogin();
	}
}
function procLoginSocial() {
	hotkey = true;
	var yn_modal = frmLoginSocial.yn_modal.value;
	
	window.setupValidateForm( frmLoginSocial );
	var data = frmLoginSocial.ajaxSubmit();
	if(data == null) return false;

	$.ajax({
		url : "<c:url value='/loginsocial/procLoginSocial.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			hotkey = false;
			
			if (parseInt(returnData.cd_result,10) == 0) {
				if ("Y" == yn_modal) {
					$("#modal-lg").modal('hide');
					// 고객 정보수정 후 폼 초기화
					//loadPrepareForm();
					alert('${returnData.comp_social}');
//					loadPersonInfo('${person.no_person}','${personForm.yn_modal}');
				} else {
					self.close();
				}
			} 
		},
		error : function (e) {
			alert(e.responseText);
			hotkey = false;
		}
	});
	
	if(IsNull(frmLoginSocial.no_person))
		$("#modal-lg").modal('hide');
}
function loadPersonInfo(no_person, yn_modal) {
	var data = {"no_person":no_person, "yn_modal":yn_modal};
	vLoad("personInfo","<c:url value='/person/formPerson.crz'/>",data,false);
}	
//로긴 액션 후 처리
function statusChangeCallback(response) {
  alert("here response.status:" + response.status);
   if (response.status === 'connected') {
    
	frmLoginSocial.comp_social.value = "FB";
	frmLoginSocial.id_social.value = response.authResponse.userID;
	frmLoginSocial.token_social.value = response.authResponse.accessToken;
	frmLoginSocial.dt_agree_social.value = "Y"; 
    
	procLoginSocial();
  } else if (response.status === 'not_authorized') {
  } else {
	alert("언노운?" + response.status)
	frmLoginSocial.comp_social.value = "";
	frmLoginSocial.id_social.value = "";
	frmLoginSocial.token_social.value = "";
	frmLoginSocial.dt_agree_social.value = ""; 
  }
}
function checkLoginState() {
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });
}
window.fbAsyncInit = function() {
  FB.init({
    appId      : '1211299452216251',
    cookie     : true,  // enable cookies to allow the server to access the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.6' // use version 2.2
  });
 
	FB.getLoginStatus(function(response) {
	  if (response.status === 'connected') {
			alert("response.authResponse.accessToken:" + response.authResponse.accessToken);

			frmLoginSocial.comp_social.value = "FB";
			frmLoginSocial.id_social.value = response.authResponse.userID;
			frmLoginSocial.token_social.value = response.authResponse.accessToken;
			frmLoginSocial.dt_agree_social.value = "Y"; 
			alert("이미 로긴된 채로 넘어온 경우 요기 페이스북 바로~ 성공시 HOOKING");
			procLoginSocial();
	  }else{
	  	//alert("not login~");
	  } 
	} );

	FB.getLoginStatus(function(response) {
	  statusChangeCallback(response);
	});
};
(function(d, s, id){
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) {return;}
	js = d.createElement(s); js.id = id;
//	js.src = "//connect.facebook.net/en_US/sdk.js";
	js.src = "//connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v2.6&appId=1211299452216251";//en_US
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function testAPI() {
	alert("testAPI");
  console.log('Welcome!  Fetching your information.... ');
  FB.api('/me', function(response) {
    console.log('Successful login for: ' + response.name);
  });
}
//카카오톡 시작
Kakao.init('c73cd64980c85138afc67ba0ed90df02');

function logout_kakao(){
	Kakao.Auth.logout();
}
function loginWithKakao() {
	Kakao.Auth.login({
		success: function(authObj) {
		alert("요기 카카오 성공시 HOOKING");
			//goLogin();
			
			alert(JSON.stringify(authObj));
alert("authObj.access_token:" + authObj.access_token);
alert("authObj.token_type:" + authObj.token_type);
alert("authObj.refresh_token:" + authObj.refresh_token);
alert("authObj.scope:" + authObj.scope);

alert("authObj.id:" + authObj.id);
alert("authObj.nickname:" + authObj.nickname);
		},
		fail: function(err) {
			alert(JSON.stringify(err));
		}
	});
};

Kakao.Auth.createLoginButton({
  container: '#kakao-login-btn',
  lang : 'kr', //'kr'
  size : 'medium',
  success: function(authObj) {
    Kakao.API.request({
      url: '/v1/user/me',
      success: function(res) {
alert("res.id:" + res.id);
alert("res.properties.nickname:" + res.properties.nickname);
      },
      fail: function(error) {
        alert(JSON.stringify(error));
      }
    });
    
  },
  fail: function(err) {
     alert(JSON.stringify(err));
  }//,
});
</script>
</head>
<body>
<%
//session.setAttribute("id_emp", "aaaa");
%>
<%-- 		<form name="frmLoginSocial" action="<c:url value='/j_spring_security_check'/>" method="POST"> --%>
<div class="login-bg">
	<form name="frmLoginSocial">
	<div class="alert alert-success alert-dismissible exclamation-list alert-lg" role="alert" >
		<ul>
			<li id="msg"></li>
		</ul>
	</div>
	<div class="login-box">
		<div class="login-header">
			<a class="navbar-brand" href="<c:url value="/"/>"><img src="<c:url value="/img/logo/logo_crizen.png"/>" alt="" /></a>
		</div>
		<div class="login-body">
			<div class="fb-login-button" data-max-rows="1" data-size="xlarge" data-show-faces="true" data-auto-logout-link="true" data-default-audience="everyone" onlogin="checkLoginState();"></div>
	<%-- <span class="user"><strong>${ufn:getWorkerInfo(id_emp, 'NM')} - ${ufn:getCodeName('c3_branch', ufn:getWorkerInfo(id_emp, 'C3_BRANCH') )} - ${ufn:getCodeName('cd_fc', ufn:getWorkerInfo(id_emp, 'AT'))}   </strong> 님 / ${ufn:getCurrentDate()} / ${ufn:getDayOfWeek()}요일</span> --%>
			<a id="custom-login-btn" href="javascript:loginWithKakao()"><img src="http://mud-kage.kakao.co.kr/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/><a id="kakao-login-btn"></a><a href="javascript:logout_kakao()">logout_kakao</a>
			<div class="login-body">
					<%-- <dl>
						<dt>user id</dt>
						<dd><input type="text" name="j_username" class="form-control" value="${SPRING_SECURITY_LAST_USERNAME}"/></dd>
						<dt>password</dt>
						<dd><input type="1password" name="j_password" class="form-control" onkeydown="isEnter(event.keyCode);"/></dd>
					</dl> --%>
				<div class="btn-sec"><button class="login-btn" onclick="javascript:procLoginSocial();">procLoginSocial</button></div>
			</div>
		</div>
	</div>
	<div class="align-r">
		<button type="submit" class="btn btn-default btn-xs" onclick="procLoginSocial();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장</button>
	</div>
	<input type="1hidden" name="comp_social" value="FB"/>
	<input type="1hidden" name="id_social" value="vvv"/>
	<input type="1hidden" name="token_social" value="abcdefg"/>
	<input type="1hidden" name="dt_agree_social" value=""/>
	
	<input type="hidden" name="no_person" value=""/>
	<input type="hidden" name="cd_personal"/>
	<input type="hidden" name="pass_person"/>
	<input type="hidden" name="yn_modal" value="Y"/>
	<div class="panel panel-primary">
		<div class="panel-heading">기본정보</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<colgroup>
				<col width="10%"/>
				<col width="40%"/>
				<col width="10%"/>
				<col width="40%"/>
			</colgroup>
		
			<tbody>
				<tr>
					<th><span class="required">이름</span></th>
					<td>
								<input type="text" class="form-control width-120" name="nm_person" id="nm_person" value="<%-- ${person.nm_person} --%>" label:이름"/>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	</form>
	<div class="modal-footer">
		<button type="submit" class="btn btn-default btn-xs" onclick="procLoginSocial();">aaaaaa<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장</button>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>