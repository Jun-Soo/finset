<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
function showLogin(){
	no_person = "${no_person}";
	frmLogin.no_person.value = no_person;
	frmLogin.action = "<c:url value='/loginsocial/loginSocial.crz'/>";
	frmLogin.method = "post";
	frmLogin.submit();
}

//로그아웃
function goLogout(){
	if(!confirm("로그아웃 하시겠습니까?")) return;
	frmLogout.target = "_self";
	frmLogout.action = "<c:url value='/j_spring_security_logout'/>";
	frmLogout.method = "post";
	frmLogout.submit();
}
function formClause(formerLocation){
	no_person = "${no_person}";
	frmLogin.formerLocation.value = formerLocation;
	frmLogin.no_person.value = no_person;
	frmLogin.action = "<c:url value='/loginsocial/formClause.crz'/>";
	frmLogin.method = "post";
	frmLogin.submit();
}

function testLogin(){
	frmLogin.no_person.value = "P000008558";
	frmLogin.action = "<c:url value='/login/test.crz'/>"
	frmLogin.method = "post";
	frmLogin.submit();
}
function testLogout(){
	if(!confirm("로그아웃 하시겠습니까?")) return;
	frmLogout.target = "_self";
	frmLogout.action = "<c:url value='/j_spring_security_logout'/>";
	frmLogout.method = "post";
	frmLogout.submit();
}
//alert("${ynAgreeUsing}");
</script>
<form name="frmLogin" id="frmLogin">
<input type="hidden" name="no_person" value="${no_person}"/>
<input type="hidden" name="cd_personal"/>
<input type="hidden" name="pass_person"/>
<input type="hidden" name="yn_modal" value="Y"/>
<input type="hidden" name="formerLocation" value=""/>
</form>
<form name="frmLogout" method="POST"></form>
<c:set var="ynAgreeUsing" value="${ynAgreeUsing}"/>
<%String[] arrTmp3 = request.getRequestURL().toString().split("/");%>
<c:set var="localhostYn" value="<%=arrTmp3[2].substring(0,2).trim()%>"/>
<c:set var="bgSelect" value="0"/>
<%String[] arrTmp = request.getServletPath().split("/");%>
<c:set var="menu_curpage" value="<%=arrTmp[arrTmp.length-1].trim()%>"/>
<c:choose>
 	<c:when test="${menu_curpage eq 'infoFincook.jsp' || menu_curpage eq 'agreeFincook.jsp' || menu_curpage eq 'formPerson.jsp' ||  menu_curpage eq 'waitFincook.jsp' || menu_curpage eq 'resultFincook.jsp' || menu_curpage eq 'listFincook.jsp' || menu_curpage eq 'successFincook.jsp' || menu_curpage eq 'listStatus.jsp' || menu_curpage eq 'listStatus.jsp' || menu_curpage eq 'listPast.jsp'}">
		<c:set var="menu_fincook" value="active"/>
		<c:set var="menu_consult" value=""/>
		<c:set var="menu_finauction" value=""/>
		<c:set var="menu_customer" value=""/>
		<c:set var="bgSelect" value="1"/>
	</c:when>
 	<c:when test="${menu_curpage eq 'listGrade.jsp' || menu_curpage eq 'viewGrade.jsp' || menu_curpage eq 'listDenyDetailPage.jsp' || menu_curpage eq 'roboMain.jsp'}">
		<c:set var="menu_fincook" value=""/>
		<c:set var="menu_consult" value="active"/>
		<c:set var="menu_finauction" value=""/>
		<c:set var="menu_customer" value=""/>
		<c:set var="bgSelect" value="2"/>
	</c:when>
 	<c:when test="${menu_curpage eq 'infoService.jsp' || menu_curpage eq 'infoStep.jsp' || menu_curpage eq 'infoFincorp.jsp'}">
		<c:set var="menu_fincook" value=""/>
		<c:set var="menu_consult" value=""/>
		<c:set var="menu_finauction" value="active"/>
		<c:set var="menu_customer" value=""/>
		<c:set var="bgSelect" value="3"/>
	</c:when>
 	<c:when test="${menu_curpage eq 'infoFaq.jsp' || menu_curpage eq 'infoQuit.jsp' || menu_curpage eq 'listBoardInfoMain.jsp' || menu_curpage eq 'listQnaInfoMain.jsp'}">
		<c:set var="menu_fincook" value=""/>
		<c:set var="menu_consult" value=""/>
		<c:set var="menu_finauction" value=""/>
		<c:set var="menu_customer" value="active"/>
		<c:set var="bgSelect" value="4"/>
	</c:when>
	<%-- <c:otherwise>
		<c:set var="menu_fincook" value="active"/>
		<c:set var="menu_consult" value=""/>
		<c:set var="menu_finauction" value=""/>
		<c:set var="menu_customer" value=""/>
	</c:otherwise> --%>
</c:choose>
<%-- <c:choose> --%>
<%--  	<c:when test="${empty no_person || no_person eq 'anonymousUser'}"> --%>
		<div id="header" class="navbar navbar-default">
			<div class="header-top">
				<div class="container">
					<div class="navbar-brand comp-name">
						<h1>(주)코스콤 </h1>
<!-- 						<button id="btnWhy" class="btn btn-why" data-toggle="collapse" href="#headerToggle" aria-expanded="false" aria-controls="headerToggle">왜?</button> -->
					</div>
					<ul class="nav navbar-nav navbar-right navbar-menu">
						<c:choose>
							<c:when test="${empty no_person || no_person eq 'anonymousUser'}">
									<li><a role="button" class="nav-login" onclick="showLogin();">로그인</a></li>
							</c:when>
							<c:otherwise>
									<li><a role="button" class="nav-login" onClick="goLogout();">로그아웃</a></li>
							</c:otherwise>
						</c:choose>
						<li><a role="button" class="nav-sitemap">사이트맵</a></li>
						<c:choose>
							<c:when test="${empty no_person || no_person eq 'anonymousUser'}">
								<li><a role="button" class="nav-login" onclick="testLogin();">로그인</a></li>
							</c:when>
							<c:otherwise>
								<li><a role="button" class="nav-login" onclick="testLogout();">로그아웃</a></li>
							</c:otherwise>
						</c:choose>    
					</ul>					
				</div>
			</div>
			<div class="gnb-bar">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebarMenu" aria-expanded="false">
					        <span class="sr-only">Toggle navigation</span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
						</button>
						<a href="/baseIndex.crz" class="navbar-brand logo">
							<img src="../images/logo.png" alt="FinCook" />
						</a>
					</div>
					<div class="collapse navbar-collapse" id="sidebarMenu">
						<ul class="mobile-menu">
							<c:choose>
								<c:when test="${empty no_person || no_person eq 'anonymousUser'}">
										<li><a role="button" class="nav-login" onclick="showLogin();">로그인</a></li>
								</c:when>
								<c:otherwise>
										<li><a role="button" class="nav-login" onClick="goLogout();">로그아웃</a></li>
								</c:otherwise>
							</c:choose>
							<li><a role="button" class="nav-sitemap">사이트맵</a></li>
						</ul>
						<ul id="gnb" class="nav navbar-nav navbar-right">
							<li class="dropdown ${menu_fincook}">
								<c:choose><c:when test="${empty no_person || no_person eq 'anonymousUser'}">
								<a role="button" class="dropdown-toggle" data-toggle="dropdown">My 코스콤</a>
								</c:when><c:otherwise>
								<a role="button" class="dropdown-toggle" data-toggle="dropdown">My 코스콤</a>
								</c:otherwise></c:choose>	
								<ul class="sub-menu">
									<c:choose><c:when test="${empty no_person || no_person eq 'anonymousUser'}">
										<li><a role="button" onClick="showLogin();">코스콤맞춤대출</a></li>
										<li><a role="button" onClick="showLogin();">대출진행상황 조회</a></li>
										<li><a role="button" onClick="showLogin();">과거신청내역</a></li>
									</c:when><c:when test="${empty ynAgreeUsing || ynAgreeUsing ne 'Y'}">
										<li><a role="button" onClick="formClause('/fincook/infoFincook.crz');">코스콤맞춤대출</a></li>
										<li><a role="button" onClick="formClause('/status/listStatus.crz');">대출진행상황 조회</a></li>
										<li><a role="button" onClick="formClause('/past/listPast.crz');">과거신청내역</a></li>
									</c:when><c:otherwise>
										<li><a href="<c:url value="/fincook/infoFincook.crz"/>">코스콤맞춤대출</a></li>
										<li><a href="<c:url value="/status/listStatus.crz"/>">대출진행상황 조회</a></li>
										<li><a href="<c:url value="/past/listPast.crz"/>">과거신청내역</a></li>
									</c:otherwise></c:choose>
								</ul>							
							</li>
							<li class="dropdown ${menu_consult}">
	 							<c:choose><c:when test="${empty no_person || no_person eq 'anonymousUser'}">
	 							<a role="button" class="dropdown-toggle" data-toggle="dropdown">코스콤플랫폼</a>
								</c:when><c:otherwise>
								<a role="button" class="dropdown-toggle" data-toggle="dropdown">코스콤플랫폼</a>
								</c:otherwise></c:choose>
								<ul class="sub-menu">
<%-- 									<c:choose><c:when test="${empty no_person || no_person eq 'anonymousUser'}"> --%>
<!-- 										<li><a role="button" onClick="showLogin();">신용등급 변동표</a></li> -->
<!-- 										<li><a role="button" onClick="showLogin();">거절 내역 및 분석</a></li> -->
<!-- 										<li><a role="button" onClick="showLogin();">로보</a></li> -->
<%-- 									</c:when><c:when test="${empty ynAgreeUsing || ynAgreeUsing ne 'Y'}"> --%>
										<li><a role="button" onClick="formClause('/grade/listGrade.crz');">신용등급 변동표</a></li>
										<li><a role="button" onClick="formClause('/deny/listDenyDetailPage.crz');">거절 내역 및 분석</a></li>
										<li><a role="button" onClick="formClause('/robo/roboMain.crz');">로보</a></li>
<%-- 									</c:when><c:otherwise> --%>
<%-- 										<li><a href="<c:url value="/grade/listGrade.crz"/>">신용등급 변동표</a></li> --%>
<%-- 										<li><a href="<c:url value="/deny/listDenyDetailPage.crz"/>">거절 내역 및 분석</a></li> --%>
<%-- 										<li><a href="<c:url value="/robo/roboMain.crz"/>">로보</a></li> --%>
<%-- 									</c:otherwise></c:choose> --%>
								</ul>						
							</li>
							<li class="dropdown ${menu_finauction}">
								<a role="button" class="dropdown-toggle" data-toggle="dropdown">고객라운지</a>
								<ul class="sub-menu">
									<li><a href="<c:url value="/finauction/infoService.crz"/>">서비스배경</a></li>
									<li><a href="<c:url value="/finauction/infoStep.crz"/>">이용절차</a></li>
									<li><a href="<c:url value="/finauction/infoFincorp.crz"/>">제휴금융사</a></li>
								</ul>						
							</li>
							<li class="dropdown ${menu_customer}">
								<a role="button" class="dropdown-toggle" data-toggle="dropdown">회사소개</a>
								<ul class="sub-menu">
									<li><a href="<c:url value="/customer/infoFaq.crz"/>">FAQ</a></li>
									<li><a href="<c:url value="/board/listBoardInfoMain.crz"/>">공지사항</a></li>
									<li><a href="<c:url value="/board/listQnaInfoMain.crz"/>">Q&A</a></li>
									<c:choose><c:when test="${empty no_person || no_person eq 'anonymousUser'}">
										<li><a role="button" onClick="showLogin();">회원탈퇴</a></li>
									</c:when><c:otherwise>
										<li><a href="<c:url value="/customer/infoQuit.crz"/>">회원탈퇴</a></li>
									</c:otherwise></c:choose>
								</ul>						
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="headerToggle" class="collapse">
			<div class="header-toggle-inner">
				<h1>대부업? 대부 중개업?</h1>
				<div class="header-toggle-body">
					<p>코스콤은 <em>코스콤 소셜대부</em>를 자회사로 두고 있습니다.</p>
					<p><em>"코스콤"</em>은 코스콤 소셜대부에서 운영하는 웹사이트 브랜드 네임입니다. 
					현재 국내에는 P2P 관련 법이 존재하지 않고 있기에 <em>국내 법률 체계에 맞는</em> 회사를 운영하기 위해서 입니다. 
					코스콤 소셜대부는 대부업과 대부중개업을 등록한 회사이지만 실제로 대출은 하지 않습니다.</p>
					<p>저희 코스콤 시스템은 <strong>국내 각 금융회사와 연계하여 불필요한 대출절차를 없애고</strong> 
					고객님들 신용등급에 맞는 <strong>최적의 대출상품을 빠르고 정확하게 찾기</strong> 위함입니다. 
					또한 저희는 건전한 대출 문화를 형성시키고 국민들의 신용 관리에 앞장설 수 있도록
					<em>차별화된 재무컨설팅을 해드리기 위한 핀테크 기업</em>입니다.</p>
					<div class="text-right">
						<button class="btn btn-default" data-toggle="collapse" href="#headerToggle" aria-expanded="false" aria-controls="headerToggle">닫기</button>
					</div>
				</div>
			</div>
		</div>
		<c:choose><c:when test="${bgSelect eq '1' }">
		<div class="sub-visual sub1">
			<div class="container">
				<div class="sub-intro">
					<h1>My 코스콤</h1>
					<p>신용등급에 맞는 최적의 대출상품을 빠르고 정확하게 찾아드립니다.</p>
				</div>
				<ul class="lnb">
					<c:choose><c:when test="${empty no_person || no_person eq 'anonymousUser'}">
						<li><a role="button" onClick="showLogin();">코스콤맞춤대출</a></li>
						<li><a role="button" onClick="showLogin();">대출진행상황 조회</a></li>
						<li><a role="button" onClick="showLogin();">과거신청내역</a></li>
					</c:when><c:when test="${empty ynAgreeUsing || ynAgreeUsing ne 'Y'}">
						<li><a role="button" onClick="formClause('/fincook/infoFincook.crz');">코스콤맞춤대출</a></li>
						<li><a role="button" onClick="formClause('/status/listStatus.crz');">대출진행상황 조회</a></li>
						<li><a role="button" onClick="formClause('/past/listPast.crz');">과거신청내역</a></li>
					</c:when><c:otherwise>
						<li><a href="<c:url value="/fincook/infoFincook.crz"/>">코스콤맞춤대출</a></li>
						<li><a href="<c:url value="/status/listStatus.crz"/>">대출진행상황 조회</a></li>
						<li><a href="<c:url value="/past/listPast.crz"/>">과거신청내역</a></li>
					</c:otherwise></c:choose>
				</ul>	
			</div>
		</div>
		</c:when>
		<c:when test="${bgSelect eq '2' }">
		<div class="sub-visual sub2">
			<div class="container">
				<div class="sub-intro">
					<h1>코스콤플랫폼</h1>
					<p>신용등급에 맞는 최적의 대출상품을 빠르고 정확하게 찾아드립니다.</p>
				</div>
				<ul class="lnb">
					<c:choose><c:when test="${empty no_person || no_person eq 'anonymousUser'}">
						<li><a role="button" onClick="showLogin();">신용등급 변동표</a></li>
						<li><a role="button" onClick="showLogin();">거절 내역 및 분석</a></li>
						<li><a role="button" onClick="showLogin();">로보</a></li>
					</c:when><c:when test="${empty ynAgreeUsing || ynAgreeUsing ne 'Y'}">
						<li><a role="button" onClick="formClause('/grade/listGrade.crz');">신용등급 변동표</a></li>
						<li><a role="button" onClick="formClause('/deny/listDenyDetailPage.crz');">거절 내역 및 분석</a></li>
						<li><a role="button" onClick="formClause('/robo/roboMain.crz');">로보</a></li>
					</c:when><c:otherwise>
						<li><a href="<c:url value="/grade/listGrade.crz"/>">신용등급 변동표</a></li>
						<li><a href="<c:url value="/deny/listDenyDetailPage.crz"/>">거절 내역 및 분석</a></li>
						<li><a href="<c:url value="/robo/roboMain.crz"/>">로보</a></li>
					</c:otherwise></c:choose>
				</ul>
			</div>
		</div>
		</c:when>
		<c:when test="${bgSelect eq '3' }">
		<div class="sub-visual sub3">
			<div class="container">
				<div class="sub-intro">
					<h1>고객라운지</h1>
					<p>유용한 고객라운지 서비스를 안내해 드립니다.</p>
				</div>
				<ul class="lnb">
					<li><a href="<c:url value="/finauction/infoService.crz"/>">서비스배경</a></li>
					<li><a href="<c:url value="/finauction/infoStep.crz"/>">이용절차</a></li>
					<li><a href="<c:url value="/finauction/infoFincorp.crz"/>">제휴금융사</a></li>
				</ul>
			</div>
		</div>
		</c:when>
		<c:when test="${bgSelect eq '4' }">
		<div class="sub-visual sub4">
			<div class="container">
				<div class="sub-intro">
					<h1>회사소개</h1>
					<p>신용등급에 맞는 최적의 대출상품을 빠르고 정확하게</p>
				</div>
				<ul class="lnb">
					<li><a href="<c:url value="/customer/infoFaq.crz"/>">FAQ</a></li>
					<li><a href="<c:url value="/board/listBoardInfoMain.crz"/>">공지사항</a></li>
					<li><a href="<c:url value="/board/listQnaInfoMain.crz"/>">Q&A</a></li>
					<c:choose><c:when test="${empty no_person || no_person eq 'anonymousUser'}">
						<li><a role="button" onClick="showLogin();">회원탈퇴</a></li>
					</c:when><c:otherwise>
						<li><a href="<c:url value="/customer/infoQuit.crz"/>">회원탈퇴</a></li>
					</c:otherwise></c:choose>
				</ul>
			</div>
		</div>
		</c:when>
		<c:otherwise>
		<!-- <div class="sub-visual sub1">
			<div class="container">
			</div>
		</div> -->
		</c:otherwise>
		</c:choose>