<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<c:choose>
	<c:when test="${empty person.no_person}">
		<title>신규고객등록</title>
	</c:when>
	<c:otherwise>
		<title>${person.nm_person} 정보</title>
	</c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<%@ include file="/WEB-INF/include/postCode.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('body').layout({
		minSize:				40
		,	west__size:				"550" //좌측 width
		//,	east__size:				"60%" //우측 width
 		,	north__spacing_open:	0
		,	north__resizable:		false
		,	west__resizable:		false
		,	spacing_open:			5
		,	spacing_closed:			5
		,	togglerLength_open:		50
		,	togglerLength_closed:	"100%"
		,	east__initClosed:		false
	});
});

function goListLookup(){
	
	window.opener.name = "parentPage"
	frmlookupHist.page.value = "1";
	document.frmlookupHist.target = "parentPage";
	document.frmlookupHist.method = "POST";
	document.frmlookupHist.action = "<c:url value='/past/listLookupHistMain.crz'/>";
	document.frmlookupHist.submit();
}

function goListExcute(){
	
	window.opener.name = "parentPage"
	frmlookupHist.page.value = "1";
	document.frmlookupHist.target = "parentPage";
	document.frmlookupHist.method = "POST";
	document.frmlookupHist.action = "<c:url value='/past/listExcuteHistMain.crz'/>";
	document.frmlookupHist.submit();
}
</script>
</head>
<body>
	<!-- Layout-North -->
	<div class="ui-layout-north">
		<div class="navbar navbar-default color-bar">
		<div class="ui-layout-content">
			<div class="cust-credit pull-left">
				<div class="top-row">
				<c:choose>
					<c:when test="${empty person.nm_person}">
						<dt><h4 class="modal-title" id="largeModalLabel"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 고객등록</dt>
					</c:when>
					<c:otherwise>
						<dt><h4 class="modal-title" id="largeModalLabel"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${person.nm_person} 정보</dt>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
		</div>
	</div>
	<!-- //Layout-North -->
	<!-- ui-layout-west -->
	<div class="ui-layout-west">
		<div class="ui-layout-content">
			 <%@ include file="/WEB-INF/views/person/formPerson.jsp"%>
		</div>
	</div>
	<!-- //ui-layout-center -->
	<div class="ui-layout-center">
	
		<div class="ui-layout-content">
			<div id="personInfo" scroll="yes" height="10">
				<%@ include file="/WEB-INF/views/person/formPersonLoan.jsp"%>
				
			</div>
		</div>
	</div>
<!-- //Layout-West -->
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>