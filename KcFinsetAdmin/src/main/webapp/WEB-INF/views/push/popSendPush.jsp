<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>전체 푸시 보내기</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//window.setupValidateForm(frmSendPush);
	});

	function sendPushMsg() {

		if (!frmSendPush.validateForm()) {
			return false;
		}

		var data = frmSendPush.ajaxSubmit();

		$.ajax({
			url : "<c:url value='/push/sendPushMsg.json'/>",
			data : data,
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function(result) {
				if (result.result == "00") {
					alert("푸시 메시지 전송에 성공 했습니다.");
					//goSearch();
					//$("#popModal").modal("hide");
					self.close();
					opener.goSearch();
				} else {
					alert("푸시 메시지 전송에 실패 했습니다.");
				}
			},
			error : function(e) {
				alert(e.responseText);
			}
		});
	}

</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<body>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">메시지 발송</a>
		</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmSendPush');">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			
			<button type="button" class="btn btn-default btn-xs" onclick="sendPushMsg();">
				<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 발송
			</button>
			
		</span>
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
								<input type="text" class="form-control" name="title" validate="required;label:제목;maxbt:100;" maxlength="100" />
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea  class="form-control" rows="10" name="body" validate="required;label:내용;maxbt:1000;" maxlength="1000"></textarea>
							</td>
						</tr>
						<tr>
							<th>URL</th>
							<td>
								<input type="text" class="form-control" name="link_addr" maxlength="100" />
							</td>
						</tr>
						
					</tbody>
				</table>
			</div>
		
		</form>
	</div>
	
</div>
</body>
</html>