<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>고객통지상세조회</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">

</script>
</head>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<body>
	<div class="panel panel-default toggle-panel">
		<div class="panel-heading">
			<h3 class="h-sec pull-left">
				<a href="#none">메시지 상세</a>
			</h3>
		</div>
		<div class="panel-collapse toggle-cont">
			<form name="frmSendPush" id="frmSendPush">
				<div class=" panel-primary">
					<table class="table table-classic">
	 					<colgroup>
							<col width="10%">
							<col width="90%">
						</colgroup>
						<tbody>
							<tr>
								<th>제목</th>
								<td>
									<input type="text" class="form-control" name="title" value="<c:out value="${resultData.title}"/>" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<textarea class="form-control" rows="10" name="body" readonly="readonly"><c:out value="${resultData.body}"/></textarea>
								</td>
							</tr>
							<tr>
								<th>URL</th>
								<td>
									<input type="text" class="form-control" name="link_addr" value="<c:out value="${resultData.link_addr}"/>" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th>보낸시간</th>
								<td>
									<input type="text" class="form-control" name="link_addr" value="<c:out value="${resultData.dt_frt}"/>" readonly="readonly"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			
			<div id="divListSendPerson" class="d-table">
				<h3 class="h-sec pull-left">
					<a href="#none">발송 고객</a>
				</h3>
				<table id="listSendPersonDetail" class="table table-bordered" cellspacing="0" width="100%">
					<colgroup>
						<col width="80px"/>
						<col width="100px"/>
						<col width="*"/>
						<col width="80px"/>
						<col width="80px"/>
					</colgroup>
					<thead>
						<tr>
							<th>회원번호</th>
							<th>이름</th>
							<th>휴대폰</th>
							<th>OS</th>
							<th>푸시허용여부</th>
						</tr>
					</thead>
					<tbody id="list_tbody">
					<c:if test="${empty resultPersonList}">
						<tr>
							<td colspan="4" height="100" align="center">
								검색결과가 없습니다
							</td>
						</tr>
					</c:if>
					<c:forEach var="List" items="${resultPersonList}">
						<tr>
							<td>${List.no_person}</td>
							<td>${List.nm_person}</td>
							<td>
								<c:choose>
				              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">${ufn:formatMaskHp(List.hp)}</c:when>
				              		<c:otherwise>${List.hp}</c:otherwise>
				              	</c:choose>
				            </td>
							<td>${List.yn_os}</td>
							<td>${List.yn_push}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>