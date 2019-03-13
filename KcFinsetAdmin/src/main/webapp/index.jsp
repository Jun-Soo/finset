<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Crizen Loan Counsel Agency</title>
<script type="text/javascript">
	<c:if test="${ufn:getCodeName('_CONF_CTI' , 'YN_USE_CTI') eq 'Y'}">
		var id_comp = "${ufn:getNvlCodeName('_CONF_SYSTEM','ID_COMP','crizen')}";
		var openWindow = window.open("<c:url value='/cti/popCtiMain.crz'/>",  "popCtiMain_"+id_comp,"width=550, height=200, scrollbars=no, resizable=no", "");
		openWindow.focus();
	</c:if>
	location.href = "<%= request.getContextPath() %>/baseMain.crz"; 
</script>
</head>
<body>

</body>
</html>