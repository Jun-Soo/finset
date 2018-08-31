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

	var selectedTab; // 0, 1, 2
	var gubun;
	$(document).ready(function() {
		$("#my_page_tabs").tabs({
			activate: function(event, ui) {
				selectedTab = $("#my_page_tabs").tabs('option', 'active');
				if(selectedTab == '0') {
					$('#my_page_btn_container').hide();
				} else if(selectedTab == '1') {
					$('#my_page_btn_container').show();
				} else if(selectedTab == '2') {
					$('#my_page_btn_container').show();
				}
			} 
		});
	});
	
	function myPageConfirm() {
		//selectedTab 1일경우 보안코드변경 2일경우 회원탈퇴
		if(selectedTab == '1') {
			if ( !frmChangePwd.validateForm() ) return false;
			var data = frmChangePwd.ajaxSubmit();
			if(data == null) return false;
			
			$.ajax({
				url : "<c:url value='/m/mypage/modifyPassword.json'/>",
				data : data,
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				type : "POST",
				async : false,
				success : function (result) {
					alert(result.message);
					
					if(result.result == "00") {
						securityCodeFailed();
						frmChangePwd.action = "<c:url value='/m/credit/frameCreditInfoMain.crz'/>"
						frmChangePwd.submit();
					} else {
						$('#currentPwd').val('');
						$('#changePwd').val('');
						$('#ChangePwdConfirm').val('');
					}
				},
				error : function (e) {
					errMsg(e);
				}
			});
			
		} else if(selectedTab == '2') {
			if(!confirm("탈퇴 하시겠습니까?")) return false;
			$.ajax({
				url : "<c:url value='/m/mypage/procPersonQuit.json'/>",
				data : data,
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				type : "POST",
				async : false,
				success : function (result) {
					
					if(result.result == "00") {
						alert(result.message);
						frmChangePwd.action = "<c:url value='/logOut'/>"
						frmChangePwd.submit();
					} else {
						alert('탈퇴 실패하였습니다.');
					}
				},
				error : function (e) {
					errMsg(e);
				}
			});
		}
	}

	function popSecurityCode(pwdConfirm) {
		if(pwdConfirm == 'current') {
			gubun = 'current';
		} else if(pwdConfirm == 'change') {
			gubun = 'change';
		} else if(pwdConfirm == 'confirm') {
			gubun = 'confirm';
		}
		$("#popModal").modal("show");
		
		$.ajax({
			url : "<c:url value='/m/mypage/popSecurityCode.crz'/>",
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
	
	/*
	* 메인 페이지에 보안 코드 팝업을  띄우기 위한 function
	*/
	function securityCodeFailed() {
		if(userAgent == "iOS") {
// 			location.href = "call://securityCodeFailed";
			Jockey.send("securityCodeFailed");
		} else if(userAgent == "Android") {
			window.Android.securityCodeFailed();
		}
	}
	
	function myPageCancle(){
		if(!confirm("메인페이지로 이동하시겠습니까?")) return false;
		frmChangePwd.action = "<c:url value='/m/credit/frameCreditInfoMain.crz'/>"
		frmChangePwd.submit();
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
			<h1>회원정보</h1>
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
			</div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div id="my_page_tabs">
			<ul class="nav nav-outline nav-justified tabs">
				<li class="active"><a href="#tab1">기본정보</a></li>
				<li><a href="#tab2">보안코드변경</a></li>
				<li><a href="#tab3">회원탈퇴</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab1">
					<!-- tab1 -->
					<h2 class="h2">기본정보</h2>
					<div class="container">
						<p class="help-block">
							고객님께서 가입 시 등록하신 이름, 생년월일, 성별 정보에 대해서만<br />
							확인만 가능하며, 수정이 불가능합니다.
						</p>
						<div class="form-info form-horizontal">
							<div class="form-group">
<!-- 								<label class="col-xs-4 control-label">고객명</label> -->
								<div class="col-xs-8">
									<p class="form-control-static"><c:out value="${resultPerson.nm_person }"/></p>
								</div>
							</div>
							<div class="form-group">
<!-- 								<label class="col-xs-4 control-label">생년월일</label> -->
								<div class="col-xs-8">
									<p class="form-control-static"><c:out value="${resultPerson.birthday }"/></p>
								</div>
							</div>
							<div class="form-group">
<!-- 								<label class="col-xs-4 control-label">성별</label> -->
								<div class="col-xs-8">
									<p class="form-control-static"><c:out value="${resultPerson.sex eq '1' ? '남' : '여'}"/></p>
								</div>
							</div>
							<div class="form-group">
<!-- 								<label class="col-xs-4 control-label">가입일</label> -->
								<div class="col-xs-8">
									<p class="form-control-static">${resultPerson.dt_frt}</p>
								</div>
							</div>
							<div class="form-group">
<!-- 								<label class="col-xs-4 control-label">최근로그인</label> -->
								<div class="col-xs-8">
									<p class="form-control-static">${resultPerson.dt_lst }</p>
								</div>
							</div>
						</div>
					</div>
					<!-- //tab1 -->
				</div>
				<div class="tab-pane" id="tab2">
					<!-- tab2 -->
					<h2 class="h2">보안코드 변경</h2>
					<div class="container">
						<p class="help-block">
							보안코드를 변경할 수 있습니다.<br />
							개인정보 보호를 위해 보안코드를 주기적으로 변경해주세요
						</p>
						<form name="frmChangePwd" validate="remove_tag;" method="post">
						<div class="form-block">
							<div class="form-group">
<!-- 								<label for="">보안코드</label> -->
								<input type="password" class="form-control" name="currentPwd" id="currentPwd" placeholder="현재 보안코드를 입력하세요" validate="required; label:현재보안코드;minlength:4;" maxlength="6" onclick="popSecurityCode('current')" onFocus="this.blur()"/>
							</div>
							<div class="form-group">
<!-- 								<label for="">코드 입력</label> -->
								<input type="password" class="form-control" name="changePwd" id="changePwd" placeholder="변경할 코드를 입력하세요" validate="required; label:보안코드;minlength:4;" maxlength="6" onclick="popSecurityCode('change')" onFocus="this.blur()"/>
							</div>
							<div class="form-group">
<!-- 								<label for="">코드 확인</label> -->
								<input type="password" class="form-control" name="ChangePwdConfirm" id="ChangePwdConfirm" placeholder="코드를 다시 한번 입력하세요" validate="required; label:보안코드;minlength:4;" maxlength="6" onclick="popSecurityCode('confirm')" onFocus="this.blur()"/>
							</div>
						</div>
						</form>
					</div>
					<!-- //tab2 -->
				</div>
				<div class="tab-pane" id="tab3">
					<!-- tab3 -->
					<h2 class="h2">회원 탈퇴</h2>
					<div class="container">
						<p class="help-block">
							회원 탈퇴 전 다음의 사항을 꼭 확인하시기 바랍니다.
						</p>
						<div class="terms-body">
							<h2>제1조(개인정보의 처리목적)</h2>
							<p>㈜코스콤은 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다</p>
							<h2>제2조(개인정보의 처리 및 보유기간)</h2>
							<p>① ㈜코스콤은 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집시에 동의 받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다.<br />
							② 각각의 개인정보 처리 및 보유기간은 다음과 같습니다
							<h2>제3조(개인정보의 제3자 제공)</h2>
							<p>① ㈜코스콤은 정보주체의 개인정보를 제1조(개인정보의 처리 목적)에서 명시한 범위 내에서만 처리하며, 정보주체의 동의, 법률의 특별한 규정 등 개인정보 보호법 제17조 및 제18조 에 해당하는 경우에만 개인정보를 제3자에게 제공합니다.<br />
							② ㈜코스콤은 다음과 같이 개인정보를 제3자에게 제공하고 있습니다.</p>
						</div>
						<div class="checkbox">
							<label><input type="checkbox"> 위 내용을 모두 확인하였으며, 내용에 동의합니다.</label>
						</div>
					</div>
					<!-- //tab3 -->
				</div>
			</div>
		</div>
		<div class="btn-fixed-bottom bottom-over" id="my_page_btn_container" style="display:none;">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="myPageCancle()">취소</button>
			</div>
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="myPageConfirm()">확인</button>
			</div>
		</div>
	</section>
	<!-- //Content -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</div>
</body>
</html>