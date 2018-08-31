<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
function goMenu(action) {
	if ( action == "" ) {
		alert("준비중입니다.");
		return;
	}
	
	try{
		frmMenu.action = action;
		frmMenu.viewType.value = "";
		frmMenu.target = "_self";
		frmMenu.method = "post";
		frmMenu.submit();
	}
	catch(e){
		frmMenu.action = action;
		frmMenu.viewType.value = "";
		frmMenu.target = "_self";
		frmMenu.method = "post";
		frmMenu.submit();
	}
}
function goPopup(action,nm_program) {
	if ( action == "" ) {
		alert("준비중입니다.");
		return;
	}
	
	window.open("","pop_"+nm_program,"width=1350, height=910, scrollbars=yes resizable=yes","");
	
	try{
		frmMenu.target_action.value = action;
		frmMenu.nm_program.value = nm_program;
		frmMenu.action = "<c:url value='/popup.crz'/>";
		frmMenu.target = "pop_"+nm_program;
		frmMenu.method = "post";
		frmMenu.submit();
	}
	catch(e){
		frmMenu.target_action.value = action;
		frmMenu.nm_program.value = nm_program;
		frmMenu.action = "<c:url value='/popup.crz'/>";
		frmMenu.target = "pop_"+nm_program;
		frmMenu.method = "post";
		frmMenu.submit();
	}
}

//로그아웃
function goLogout(){
	
	if(!confirm("로그아웃 하시겠습니까?")) return;
	
	frmMenu.target = "_self";
	frmMenu.action = "<c:url value='/j_spring_security_logout'/>";
	frmMenu.method = "post";
	frmMenu.submit();
}

//사용자정보수정
function modifyUserSelf() {
	vLoad("modal-content-md","<c:url value='/agency/modifyAgencyUserSelf.crz'/>",null);
}

</script>

<form name="frmMenu" id="_frmMenu">
<input name="target_action" type="hidden"/>
<input name="nm_program" type="hidden"/>
<input name="json_data" type="hidden"/>
<input name="viewType" type="hidden"/>
<input name="no_cust" type="hidden" value=""/>

<div class="ui-layout-north">
	<nav class="navbar navbar-default color-bar">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#gnb-wrap" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/"/>"><img src="<c:url value="/img/logo/logo_${ufn:getCodeName('_CONF_SYSTEM','NM_LOGO')}.png"/>" alt="" /></a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	            <ul class="nav navbar-nav">
					<%-- <li class="dropdown">
						<a role="button" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true">사전접수관리<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a role="button" onclick="goMenu('<c:url value="/agency/listPrepareMain.crz"/>');">사전접수리스트</a></li>
						</ul>
					</li> --%>
					<li class="dropdown">
						<a role="button" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true">접수관리<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a role="button" onclick="goMenu('<c:url value="/agency/listApplyMain.crz"/>');">접수리스트</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a role="button" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true">공지사항<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a role="button" onclick="goMenu('<c:url value="/board/listBoardInfoMain.crz"/>');">공지사항</a></li>
						</ul>
					</li>
<%-- 					<c:if test="${'01' eq ufn:getAgencyUserInfo(id_emp,'LEVEL')}">
						<li class="dropdown">
							<a role="button" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true">환경관리<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a role="button" onclick="goMenu('<c:url value="/agency/agencyUserMain.crz"/>');">사용자관리</a></li>
							</ul>
						</li>
					</c:if> --%>
	            </ul>
			</div>
<%-- 			<div class="pull-right">
				<span class="user"><strong>${ufn:getAgencyUserInfo(id_emp,'NM')}</strong> 님 / ${ufn:getCurrentDate()} / ${ufn:getDayOfWeek()}요일</span>
				<button type="button" class="btn btn-default h-btn" data-toggle="modal" data-target=".bs-modal" onclick="modifyUserSelf();"><span class="glyphicon glyphicon-wrench" aria-hidden="true"><b>설정</b></span></button>
				<button type="button" class="btn btn-default h-btn" onclick="goLogout();"><span class="glyphicon glyphicon-off" aria-hidden="true"><b>나가기</b></span></button>
			</div> --%>
		</div>
	</nav>
</div>
</form>
<%@ include file="/WEB-INF/views/comm/modal.jsp" %>