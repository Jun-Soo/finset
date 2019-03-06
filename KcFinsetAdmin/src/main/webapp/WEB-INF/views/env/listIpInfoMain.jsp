<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>허용 IP 관리</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
</head>
<body>
<!-- Layout-North -->
<%@ include file="/WEB-INF/include/header.jsp"%>
<!--// Layout-North -->

<!-- Layout-Center -->
<div class="ui-layout-center">
	<div class="ui-layout-content">
		<!-- title -->
		<div class="h-title">
			<h1 class="pull-left">허용 IP 관리</h1>
			<ol class="breadcrumb pull-right">
				<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
				<li><a href="#">환경관리</a></li>
				<li class="active">허용 IP 관리</li>
			</ol>
		</div>
		<!--// title -->
		
		<!-- Alert -->
		<div class="alert alert-success alert-dismissible exclamation-list" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span></button>
			<ul>
				<li>IP범위 등록 예 : 192.168.0.1, 192.168.0.1,2,3, 192.168.0.1-25, 192.168.0.*
			</ul>
		</div>
	
		<!-- IP Form -->
		<div id="formIpInfo">
		 	<%@ include file="/WEB-INF/views/env/formIpInfo.jsp"%>
		</div>
		
		<!-- IP Table List -->
		<div id="listIpInfo" class="d-table">
		 	<%@ include file="/WEB-INF/views/env/listIpInfo.jsp"%>
		</div>
	</div>	
	<!--// Layout-Content -->
</div>
<!--// Layout-Center -->
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>