<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		window.setupValidateForm(frmSendPushEach);
	});
	
	function sendPushMsg() {

		if (!frmSendPushEach.validateForm()) {
			return false;
		}

		var data = frmSendPushEach.ajaxSubmit();

		$.ajax({
			url : "<c:url value='/pusheach/sendPushEachMsg.json'/>",
			data : data,
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function(result) {
				if (result.result == "00") {
					alert("푸시 메시지 전송에 성공 했습니다.");
					goSearch();
					$("#popModal").modal("hide");
				} else if(result.result == "11"){
					alert("알림을 허용하지 않은 고객입니다.");
				} else {
					alert("푸시 메시지 전송에 실패 했습니다.");
				}
			},
			error : function(e) {
				alert(e.responseText);
			}
		});
	}

	function goCustRel(txt_detail, frm_nm, no_person, no_prepare, yn_grt) {
		var data = {"txt_detail":txt_detail,"frm_nm":frm_nm,"no_person":no_person,"no_prepare":no_prepare};
		$("#modal-lg").modal("show");
		vLoad("modal-content-lg","<c:url value='/deny/listCustRelMain.crz'/>",data);
	}
</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">메시지 발송</a>
		</h3>
		<span class="pull-right">
		
			<button type="button" class="btn btn-primary btn-xs" onclick="resetForm('frmSendPushEach');">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			
			<button type="button" class="btn btn-default btn-xs" onclick="sendPushMsg();">
				<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 발송
			</button>
			
		</span>
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
						 	<input type="text" name="no_person" id="no_person" class="form-control width-150" onkeydown="isEnter(event.keyCode)" value="${noPerson }" validate="required;label:이름;keydown-enter:goSearch;" readonly="readonly"/>
			 				<button type="button" class="btn btn-default btn-xs" onclick="goCustRel('no_person', 'frmSendPushEach','${personVO.no_person}','${prepareVO.no_prepare}','N');">
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 고객찾기
							</button>
					 	</td>
						</tr>
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
<!-- 						<tr> -->
<!-- 							<th>예약시간</th> -->
<!-- 							<td> -->
<!-- 								<input type="text" class="form-control" name="dt_reserv" maxlength="100" /> -->
<!-- 							</td> -->
<!-- 						</tr> -->
					</tbody>
				</table>
			</div>
		
		</form>
	</div>
	
</div>