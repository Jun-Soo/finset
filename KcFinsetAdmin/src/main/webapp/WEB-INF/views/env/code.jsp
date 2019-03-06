<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/ufn.tld" prefix="ufn" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Code Main</title>
</head>
<body>
<P>  code_info ${code}. </P>

<P>  ---------------- </P>
 ${ufn:getCodeName('cd_job_class', '10')}		

</body>
</html>