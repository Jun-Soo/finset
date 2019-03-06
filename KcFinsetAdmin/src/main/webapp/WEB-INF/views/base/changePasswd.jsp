<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Loan Counsel Agency Change Password</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
//로그아웃
function goLogout(){
	
	if(!confirm("로그아웃 하시겠습니까?")) return;
	
	frmMenu.target = "_self";
	frmMenu.action = "<c:url value='/j_spring_security_logout'/>";
	frmMenu.method = "post";
	frmMenu.submit();
}
</script>
</head>
<body>
<form name="frmMenu" id="_frmMenu">
</form>
<div class="ui-layout-content">
	<div class="alert alert-success alert-dismissible exclamation-list alert-lg" role="alert" >
		<ul>
			<li>초기 비밀번호와 현재 비밀번호가 <strong>동일</strong> 합니다. 비밀번호를 변경하세요.</li>
			<li><strong>쉬운 비밀번호</strong> 나 <strong>자주 쓰는 사이트의 비밀번호가 같을 경우</strong>, 도용되기 쉬우므로 <strong>주기적으로 변경하셔서 사용</strong>하는 것이 좋습니다.</li>
			<li>아이디와 주민등록번호,생일,전화번호 등 개인정보와 관련된 숫자, 연속된 숫자, 반복된 문자 등 다른사람이 쉽게 알아 낼 수 있는 비밀번호는 개인정보 유출의 위험이 높으므로 사용을 자제해 주시기 바랍니다.</li>
			<li>비밀번호는 8-32자의 영문 대/소문자,숫자,특수문자를 혼합해서 사용하실 수 있습니다.</li>
		</ul>
	</div>
	<div>
	  <div class="modal-dialog">
	    <div id="pass-modal" class="modal-content">
	    	<%@ include file="/WEB-INF/views/worker/formWorkerUser.jsp" %>
	    </div>
	  </div>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>