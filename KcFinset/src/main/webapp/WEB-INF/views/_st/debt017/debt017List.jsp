<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ taglib prefix="ct" uri= "/WEB-INF/tlds/ct.tld" %>
<html>
<head>
<meta charset="UTF-8">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>

<script type="text/javascript">

</script>
</head>
<body>
<br>
<br>
<br>
<br>
<br>
<br>
	<form name="theForm" method="post" action="/m/st/debt018View.crz">
		<input type="hidden"  name="mbrCd" value="S063"/>
		계좌번호 : <input type="text" name="AcntNo" value="30110100001" title="계좌번호"><br/>
		연계계좌번호 : <input type="text" name="LnkAcntNo" value="30120100001" title="연계계좌번호"><br/>

		<input type="submit" value="전송">
	</form>
	<br/>

	
</body>
</html>