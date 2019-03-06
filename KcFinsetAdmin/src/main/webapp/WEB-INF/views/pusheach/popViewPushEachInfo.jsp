<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
					<form name="frmSendPushEach" id="frmSendPushEach">
						<div class=" panel-primary">
							<table class="table table-classic">
			 					<colgroup>
									<col width="20%">
									<col width="80%">
								</colgroup>
								<tbody>
									<tr>
										<th>고객번호</th>
										<td>
											<input type="text" class="form-control" name="title" value="<c:out value="${resultData.no_person}"/>" readonly="readonly"/>
										</td>
									</tr>
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
<!-- 									<tr> -->
<!-- 										<th>보낸시간</th> -->
<!-- 										<td> -->
<%-- 											<input type="text" class="form-control" name="dt_reserv" value="<c:out value="${resultData.dt_reserv}"/>" readonly="readonly"/> --%>
<!-- 										</td> -->
<!-- 									</tr> -->
								</tbody>
							</table>
						</div>
					
					</form>
				</div>
			</div>
</body>
</html>