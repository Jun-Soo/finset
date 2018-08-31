<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	var obj, existCert;
	$(document).ready(function() {
		$("#li_bank").click();
		$("#dropdown-menu-control").hide();
		$("#dropdown-menu-link").hide();
		addPseudoBar("_bank");
		
		$(window).scroll(function(){
			$("#dropdown-menu-control").hide();
			$("#dropdown-menu-link").hide();
		});
		
		$("#wrapper").on("click",function(e){
			$("#dropdown-menu-control").hide();
			$("#dropdown-menu-link").hide();
		});
		
		$(".scrap_tab").on("click", function() {
			hideDivs();
			var type = $(this).data("tab");
			switch (type) {
			case "bank":
				$("#div_bank").removeClass("div_hidden");
				break;

			case "card":
				$("#div_card").removeClass("div_hidden");
				break;

			case "etc":
				$("#div_etc").removeClass("div_hidden");
				break;
			}
			checkExistCert();
		});
		
		$(".ico-submenu").on("click", function(event){
			$("#dropdown-menu-link").hide();
			event.stopPropagation();
			obj = $(this);
			var scrollTop = $(window).scrollTop();
			var standard = 74;
			if ( $("#menu-fixed-bottom").is(":visible") === true || $(".btn-fixed-bottom").is(":visible") ){
				standard += 48;
			}
			if(($(window).scrollTop()+$(window).height()-40)-$(this).offset().top<standard) {
				scrollTop += 120;
			}
			$("#hiddenSelectbox_control").css("top",$(this).offset().top-scrollTop);
			$("#dropdown-menu-control").show("blind",null,150,null);

		});
		
		$(".interlock").on("click", function(event){
			$("#dropdown-menu-control").hide();
			//event.preventDefault();
			
			event.stopPropagation();
			obj = $(this);
			var scrollTop = $(window).scrollTop();
			var standard = 74;
			
			if ( $("#menu-fixed-bottom").is(":visible") === true || $(".btn-fixed-bottom").is(":visible") ){
				standard += 48;
			}
			if(($(window).scrollTop()+$(window).height()-40)-$(this).offset().top<standard) {
				scrollTop += 120;
			}
			
			if(existCert) {
				$("#hiddenSelectbox_link").css("top",$(this).offset().top-scrollTop);
// 				$("#dropdown-menu-link").toggle();
				$("#dropdown-menu-link").show("blind",null,150,null);
			}
			else	{
				$(".login").click();
			}
		});
		
		$(".unlink").on("click", function(event){
			var no_person = obj.parents(".pull-right").find("#no_person").val(); 
			var cd_fc = obj.parents(".pull-right").find("#cd_fc").val();
			var data = "no_person="+no_person+"&cd_fc="+cd_fc;
			$.ajax({
				url : "<c:url value='/m/scrap/unlinkScrapFc.json'/>",
				data : data,
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				type : "POST",
				async : false,
				success : function (result) {
					if(result.result == '00') {
						toastMsg('연결해제가 완료되었습니다.');
						setTimeout(function(){
							location.reload();
	 					}, 2000);
					}
				},
				error : function (e) {
	                errMsg(e);
				}
			});
		});
		
		$(".modify").on("click", function(event){
			$("#dropdown-menu-control").hide();
			//event.preventDefault();
			event.stopPropagation();
			var scrollTop = $(window).scrollTop();
			
			if(existCert) {
				$("#hiddenSelectbox_link").css("top",obj.offset().top-scrollTop);
				$("#dropdown-menu-link").toggle();
			}
			else	{
				$(".login").click();
			}
		});
		$(".reset").on("click", function(event){
			alert(obj.parents(".pull-right").find("#nm_code").val());
			
		});
		$(".cert").on("click", function(event){
			var no_person = obj.parents(".pull-right").find("#no_person").val();
			var cd_coocon = obj.parents(".pull-right").find("#cd_coocon").val();
			var nm_code = obj.parents(".pull-right").find("#nm_code").val();
			var nm_person = $('#nm_person').val();
			
			var agency = null;
			if(nm_code == "은행")	{
				agency = "bank";
			}
			else if(nm_code == "카드")	{
				agency = "card";
			}
			else if(nm_code == "기타")	{
				agency = "etc";
			}
			
			if(userAgent == "IOS")	{
				// do nothing
			}
			else if(userAgent == "Android") {
				window.Android.updateAvaliableCertScrapInfo(no_person, agency, cd_coocon, nm_person);
			}
			
		});
		$(".login").on("click", function(event){
			var no_person = obj.parents(".pull-right").find("#no_person").val();
			var cd_coocon = obj.parents(".pull-right").find("#cd_coocon").val();
			var nm_code = obj.parents(".pull-right").find("#nm_code").val();
			
			var agency = null;
			if(nm_code == "은행")	{
				agency = "bank";
			}
			else if(nm_code == "카드")	{
				agency = "card";
			}
			else if(nm_code == "기타")	{
				agency = "etc";
			}
			
			if(userAgent == "IOS")	{
				// do nothing
			}
			else if(userAgent == "Android") {
				window.Android.updateAvaliableLoginScrapInfo(no_person, agency, cd_coocon);
			}
		});
		
		if(userAgent == "Android") {
			window.Android.backKeySendUrl("/m/customercenter/frameCustomerMyInfo.crz");
		}
		checkExistCert();
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
			$('#regist_cert').removeClass('div_hidden');
			existCert = true;
			$("#content").css("padding-bottom","64px");
		} else {		// 공인인증서가 없을 경우
			//alert('공인인증서가 없습니다.');
		}
	}
	
	function updateScrapInfoResult(cd_err, msg_err)	{
		if(cd_err == "00000000")	{
			toastMsg("금융사 연동이 완료되었습니다.");
			setTimeout(function(){
				location.reload();
			}, 2000);
			
		}
		else	{
			toastMsg("금융사 연동이 실패하였습니다. </br>("+ msg_err+")");
		}	
		//obj.parents(".list-block").append("<div class=bg_grey>  오류 </br> "+msg_err+" </div>");
	}
	
	var getCert = function() {
		var noPerson = $('#no_person').val();	
		var bankCode = $('#bank_code').val();
		var cardCode = $('#card_code').val();
		
		if(userAgent == "iOS") {
			Jockey.on("frmFcListNextFromMobile" , function(param) {
				frmFcListNextFromMobile();
			});
 
			Jockey.send("checkAvaliableScrapList" , {
				noPerson : noPerson,
				bankCode : bankCode
			});
		} else if(userAgent == "Android") {
			window.Android.checkAvaliableScrapList(noPerson, bankCode, cardCode);
		}
	}
	
	//자동 스크래핑 등록 완료 시 (모바일에서 호출)
	function frmFcListNextFromMobile() {
		toastMsg('공인인증서 등록이 완료되었습니다.');
		setTimeout(function(){
			location.reload();
		}, 2000);
	}

	var hideDivs = function() {
		$("#div_bank").addClass("div_hidden");
		$("#div_card").addClass("div_hidden");
		$("#div_security").addClass("div_hidden");
		$("#div_etc").addClass("div_hidden");
	}
	
	function goBack() {
		frmFcLinkControl.action = "/m/customercenter/frameCustomerMyInfo.crz";
		frmFcLinkControl.submit();
	}

</script>
</head>
<body>
	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
				</div>
				<h1>금융사 연동</h1>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
		<form name="frmFcLinkControl" id="frmFcLinkControl" method="post">
			<div class="affix-fixed top-fixed-item">
				<ul class="nav nav-outline nav-justified tabs">
					<li class="scrap_tab" data-tab="bank"><a id="li_bank" href="#">은행</a></li>
					<li class="scrap_tab" data-tab="card"><a id="li_card" href="#">카드</a></li>
<!-- 					<li class="scrap_tab" data-tab="security"><a id="li_security" href="#">증권</a></li> -->
					<li class="scrap_tab" data-tab="etc"><a id="li_etc" href="#">기타</a></li>
				</ul>
			</div>
			<div id="div_bank">
				<c:choose>
					<c:when test="${empty bankList}">
					<div class="data-none">
						<p>등록 내역이 없습니다.</p>
					</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="vo" items="${bankList}">
							<div class="list-block">
								<div class="container-fluid">
									<div class="list-info">
										<span class="thumb-logo" style="background-image: url('/fincorp/getFinCorpIcon.crz?cd_fc=${vo.CD_FC}');"></span>
										<span>${vo.NM_FC }</span>
										<div class="pull-right">
											<input type="hidden" name="no_person" id="no_person" value ="${vo.NO_PERSON }" />
											<input type="hidden" name="cd_fc" id="cd_fc" value ="${vo.CD_FC }" />
											<input type="hidden" name="nm_code" id="nm_code" value ="${vo.NM_CODE }" />
											<input type="hidden" name="cd_coocon" id="cd_coocon" value ="${vo.CD_COOCON }" />
											<input type="hidden" name="type_login" id="type_login" value ="${vo.TYPE_LOGIN }" />
											<input type="hidden" name="bank_code" id="bank_code" value="<c:out value="${bank_code}"/>">
											<input type="hidden" name="card_code" id="card_code" value="<c:out value="${card_code}"/>">
											<input type="hidden" name="nm_person" id="nm_person" value="<c:out value="${nm_person}"/>">
											<c:choose>
												<c:when test="${vo.YN_LINK  eq 'Y'}">
													<button type="button" class="ico-submenu ico-submenu-black"></button>
												</c:when>
												<c:otherwise>
													<dl>
														<dt>
															<a class="interlock"><strong>연동하기</strong></a>
															
														</dt>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							<c:choose>
								<c:when test="${vo.CD_LINK_STAT  eq '99' && !empty vo.RSN_LINK_MESSAGE}">
									<div class="bg_grey div_error"> <strong>오류</strong> </br>${vo.RSN_LINK_MESSAGE}</div>
								</c:when>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="div_card" class="div_hidden">
				<c:choose>
					<c:when test="${empty cardList}">
					<div class="data-none">
						<p>등록 내역이 없습니다.</p>
					</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="vo" items="${cardList}">
							<div class="list-block">
								<div class="container-fluid">
									<div class="list-info">
										<span class="thumb-logo" style="background-image: url('/fincorp/getFinCorpIcon.crz?cd_fc=${vo.CD_FC}');"></span>
										<span>${vo.NM_FC }</span>
										<div class="pull-right">
											<input type="hidden" name="no_person" id="no_person" value ="${vo.NO_PERSON }" />
											<input type="hidden" name="cd_fc" id="cd_fc" value ="${vo.CD_FC }" />
											<input type="hidden" name="nm_code" id="nm_code" value ="${vo.NM_CODE }" />
											<input type="hidden" name="cd_coocon" id="cd_coocon" value ="${vo.CD_COOCON }" />
											<input type="hidden" name="type_login" id="type_login" value ="${vo.TYPE_LOGIN }" />
											<c:choose>
												<c:when test="${vo.YN_LINK  eq 'Y'}">
													<button type="button" class="ico-submenu ico-submenu-black"></button>
												</c:when>
												<c:otherwise>
													<dl>
														<dt>
															<a class="interlock"><strong>연동하기</strong></a>
															
														</dt>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							<c:choose>
								<c:when test="${vo.CD_LINK_STAT  eq '99' && !empty vo.RSN_LINK_MESSAGE}">
									<div class="bg_grey div_error"> <strong>오류</strong> </br>${vo.RSN_LINK_MESSAGE}</div>
								</c:when>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="div_etc" class="div_hidden">
				<c:choose>
					<c:when test="${empty etcList}">
					<div class="data-none">
						<p>등록 내역이 없습니다.</p>
					</div>
				</c:when>
					<c:otherwise>
						<c:forEach var="vo" items="${etcList}">
							<div class="list-block">
								<div class="container-fluid">
									<div class="list-info">
										<span class="thumb-logo" style="background-image: url('/fincorp/getFinCorpIcon.crz?cd_fc=${vo.CD_FC}');"></span>
										<span>${vo.NM_FC }</span>
										<div class="pull-right">
											<input type="hidden" name="no_person" id="no_person" value ="${vo.NO_PERSON }" />
											<input type="hidden" name="cd_fc" id="cd_fc" value ="${vo.CD_FC }" />
											<input type="hidden" name="nm_code" id="nm_code" value ="${vo.NM_CODE }" />
											<input type="hidden" name="cd_coocon" id="cd_coocon" value ="${vo.CD_COOCON }" />
											<input type="hidden" name="type_login" id="type_login" value ="${vo.TYPE_LOGIN }" />
											<c:choose>
												<c:when test="${vo.YN_LINK  eq 'Y'}">
													<button type="button" class="ico-submenu ico-submenu-black"></button>
												</c:when>
												<c:otherwise>
													<dl>
														<dt>
															<a class="interlock"><strong>연동하기</strong></a>
															
														</dt>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							<c:choose>
								<c:when test="${vo.CD_LINK_STAT  eq '99' && !empty vo.RSN_LINK_MESSAGE}">
									<div class="bg_grey div_error"> <strong>오류</strong> </br>${vo.RSN_LINK_MESSAGE}</div>
								</c:when>
							</c:choose>
					</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="btn-fixed-bottom affix-bottom div_hidden" id="regist_cert">
				<a role="button" class="btn btn-lg btn-block btn-primary" onclick="getCert();">공인인증서 등록하기</a>
			</div>
		</form>
		</section>
		<!-- //Content -->
		<div id="hiddenSelectbox_control" style="position: fixed; right: 0px">
			<div class="btn-group bootstrap-select selectbox pull-right open">
				<div class="dropdown-menu open" id="dropdown-menu-control"	style="display: block;">
					<ul class="dropdown-menu inner" id="dropdown-inner">
						<li><a class="unlink" id="unlink"><span class="text">연결해제</span></a></li>
						<li><a class="modify" id="modify"><span class="text">연동정보 변경</span></a></li>
					</ul>
				</div>
			</div> 
		</div>
		<!-- //Content -->
		<div id="hiddenSelectbox_link" style="position: fixed; right: 0px">
			<div class="btn-group bootstrap-select selectbox pull-right open">
				<div class="dropdown-menu open" id="dropdown-menu-link"	style="display: block;">
					<ul class="dropdown-menu inner" id="dropdown-inner">
						<li><a class="cert" id="cert"><span class="text">공인인증서</span></a></li>
						<li><a class="login" id="login"><span class="text">아이디/비밀번호</span></a></li>
					</ul>
				</div>
			</div> 
		</div>
	</div>
</body>
</body>
</html>
