<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: 시스템 관리자에게 문의해 주시기 바랍니다 ::</title>
</head>
<body>
<table width="100%" height="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td align="center" valign="middle">
		 	<table width="752" height="444" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td style="background:url('<c:url value="/img/login/img_notice_ip.gif"/>') no-repeat; padding:123px 115px; color:#333; font-size:9pt;" align="right" valign="bottom">
					현재 접속 IP : <%=request.getRemoteAddr()%></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>