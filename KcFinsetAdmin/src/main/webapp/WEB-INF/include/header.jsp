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
	
	//Sms 팝업
	/* function goSmsPopup(yn_sms_tab) {
		var openWindow = window.open("<c:url value='/sms/popFormSms.crz'/>?yn_sms_tab="+yn_sms_tab, "open"+yn_sms_tab, "width=650, height="+(screen.availHeight-450)+", scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	} */
	
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
	
	// 사용자정보수정
	function modifyWorkerUser() {
		vLoad("modal-content-md","<c:url value='/worker/modifyWorkerUser.crz'/>",null);
	}
	// 금융계산기
	function goCalculator() {
		var openWindow = window.open("<c:url value='/calculator.crz'/>", "","width=770, height=600, scrollbars=yes, resizable=yes", "");
		openWindow.focus();
	}
	
	// 즐겨찾기 팝업
	function goBookmark() {
		var openWindow = window.open("<c:url value='/popBookmark.crz'/>","popBookmark","width=770, height=600, scrollbars=yes, resizable=no", "");
		openWindow.focus();
	}
	
	function goAlarmPop() {
		var openWindow = window.open("<c:url value='/alarm/popAlarmMain.crz'/>", "","width=450, height=450, scrollbars=yes, resizable=no", "");
		openWindow.focus();
	}
	
	function goCallbackPop() {
		var openWindow = window.open("<c:url value='/cti/listCallbackMain.crz'/>", "","width=700, height=450, scrollbars=yes, resizable=no", "");
		openWindow.focus();
	}
</script>

<form name="frmMenu" id="_frmMenu">
<input name="target_action" type="hidden"/>
<input name="nm_program" type="hidden"/>
<input name="json_data" type="hidden"/>
<input name="viewType" type="hidden"/>
<input name="seq_alarm" type="hidden"/>
<input name="no_cust" type="hidden" value=""/>

<div class="ui-layout-north">
	<nav class="navbar navbar-default color-bar">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/"/>"><img src="<c:url value="/img/logo/logo_${ufn:getCodeName('_CONF_SYSTEM','NM_LOGO')}.png"/>" alt="" /></a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	            <ul class="nav navbar-nav">
					<!-- 상위메뉴        ${ufn:isProgram('id_program_target','cd_system','cd_work','cd_program_group')} -->
	            	<c:forEach var="main_program" items="${sessionScope.ARR_PROGRAM}">
						<c:if test="${ufn:isProgram(main_program,'60','','10')}">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true">${ufn:getProgram(main_program,'NM')}<span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
								<!-- 하위메뉴  -->
									<c:forEach var="sub_program" items="${sessionScope.ARR_PROGRAM}">
										<c:if test="${ufn:isProgram(sub_program,'60',ufn:getProgram(main_program,'WORK'),'20')}">
											<li><a href="#" onclick="goMenu('<c:url value="${ufn:getProgram(sub_program,'ACTION')}"/>');">${ufn:getProgram(sub_program,'NM')}</a></li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>
	            </ul>
			</div>
			<div class="pull-right">
				<span class="user"><strong>${ufn:getWorkerInfo(id_emp, 'NM')} - ${ufn:getCodeName('c3_branch', ufn:getWorkerInfo(id_emp, 'C3_BRANCH') )} - ${ufn:getCodeName('cd_fc', ufn:getWorkerInfo(id_emp, 'AT'))}   </strong> 님 / ${ufn:getCurrentDate()} / ${ufn:getDayOfWeek()}요일</span>
				<button type="button" class="btn btn-default h-btn" onclick="goCalculator();"><span class="glyphicon glyphicon-modal-window" aria-hidden="true"><b>계산기</b></span></button>
				<button type="button" class="btn btn-default h-btn" data-toggle="modal" data-target=".bs-modal" onclick="modifyWorkerUser(); "><span class="glyphicon glyphicon-wrench" aria-hidden="true"><b>설정</b></span></button>
				<button type="button" class="btn btn-default h-btn" onclick="goLogout();"><span class="glyphicon glyphicon-off" aria-hidden="true"><b>나가기</b></span></button>
			</div>
		</div>
	</nav>
</div>
</form>
<%-- <c:if test="${ufn:getNvlCodeName('_CONF_ALARM','YN_USE','') eq 'Y' && ufn:getNvlCodeName('_CONF_ALARM','CD_CONFIRM','') eq '02'}"> --%>
<!-- Layout-North -->
<%-- <%@ include file="/WEB-INF/views/alarm/layerAlarm.jsp"%> --%>
<!--// Layout-North -->
<script type="text/javascript">

// function layerConfirmAlarm(seq){
// 	Alarm.arrive(seq);
// }
</script>
<%-- </c:if> --%>

<%@ include file="/WEB-INF/views/comm/modal.jsp"%>