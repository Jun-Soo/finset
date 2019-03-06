<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>즐겨찾기</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
</head>
<body>
	<div class="ui-layout-content">
		<div class="panel panel-default toggle-panel">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">즐겨찾기 목록</h3>
			</div>
			<table id="tbl_listCode" class="table table-classic tbl-info" >
				<colgroup>
					<col width="40%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>이름</th>
						<th>주소</th>
					<tr>
				</thead>
				<tbody>
					<c:forEach var="List" items="${listBookmark}">
						<tr>
							<td class="align-c">${List.etc}</td>
							<td><a href="${List.nm_code}" target='_blank'><strong>${List.nm_code}</strong></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>