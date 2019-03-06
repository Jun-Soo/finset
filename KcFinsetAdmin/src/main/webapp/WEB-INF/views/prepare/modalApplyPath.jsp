<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		// SelectPicker
		$('.selectpicker').selectpicker();
		
		window.setupValidateForm(frmApplyPath);
	});
	
	//접수경로 삽입수정
	function procApplyPath() {
		
		var path = "";
		var gubun = "\|\|"
		
		for(var i=0;i<$("input[name='level']").length; i++){
			var level = document.getElementsByName("level")[i].value;
			var date = document.getElementsByName("date")[i].value;
			var name = document.getElementsByName("name")[i].value;
			var ssn = document.getElementsByName("ssn")[i].value;
			var assn = document.getElementsByName("assn")[i].value;
			var ph = document.getElementsByName("ph")[i].value;
			
			if(level == "" || date == "" || name == "" || ssn == "" || assn == "" || ph == ""){
				alert("접수경로가 비어있습니다."); return false;
			}
			
			path += level+gubun+date+gubun+name+gubun+ssn+gubun+assn+gubun+ph;
			
			if(i < $("input[name='level']").length-1){
				path += "@@";
			}
		}
		
		var no_prepare = frmApplyPath.no_prepare.value;
		var no_apply = frmApplyPath.no_apply.value;
		
		if(no_prepare != ""){
			var data = {"etc_prepare_path":path, "no_prepare":no_prepare};
		}else if(no_apply != ""){
			var data = {"etc_apply_path":path, "no_apply":no_apply};
		} 
		
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/prepare/procApplyPath.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				$("#modal-lg").modal('hide');
				
				if(parseInt(returnData.cd_result,10) == 0) {
					if(no_prepare != "") loadPrepareForm();
					else loadApplyPath(no_apply);
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	//접수경로 행 삭제
	function delApplyPath(obj) {
		$(obj).parent().parent().remove();
	}
	
	//접수경로 행 추가
	function addApplyPath() {
		var addData = $("#addData");
		$("#tbl_apply_path tbody:last").append($(addData).val());
	}
	
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="largeModalLabel">접수경로 수정</h4>
</div>
<div class="modal-body">
	<form name="frmApplyPath" id="frmApplyPath">
		<input type="hidden" name="no_prepare" value="${prepareVO.no_prepare}" />
		<input type="hidden" name="no_apply" value="${applyVO.no_apply}" />
		<div class="panel panel-primary">
			<div class="panel-heading">접수경로
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-xs" onclick="resetForm('frmApplyPath');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
					<button type="button" class="btn btn-default btn-xs" onclick="addApplyPath();"><span class="glyphicon glyphicon-plus"></span> 추가</button>
				</div>
			</div>
			<div class="panel-collapse">
				<table class="table table-bordered" id="tbl_apply_path">
					<thead> 
						<tr>
					        <th>접수순위</th>
							<th>접수일자</th>
							<th>상호</th>
							<th>등록번호</th>
							<th>협회등록번호</th>
							<th>전화번호</th>
							<th>삭제</th>
						</tr> 
					</thead>
					<tbody>
						<c:set var="path" value="${!empty prepareVO ? prepareVO.etc_prepare_path:applyVO.etc_apply_path}"></c:set>
						<c:forEach var="List" items="${ufn:getApplyPath(path)}" varStatus="status">
							<tr>
								<c:set var="path_data" value="${fn:split(List,'||')}"/>
								<td><input class="form-control" type="text" name="level" value="${path_data[0]}"/></td>
								<td><input class="form-control" type="text" name="date" value="${path_data[1]}" maxlength="8"/></td>
								<td><input class="form-control" type="text" name="name" value="${path_data[2]}" /></td>
								<td><input class="form-control" type="text" name="ssn" value="${path_data[3]}" /></td>
								<td><input class="form-control" type="text" name="assn" value="${path_data[4]}" /></td>
								<td><input class="form-control" type="text" name="ph" value="${path_data[5]}" /></td>
								<td><button type="button" class="btn btn-danger btn-xs" onclick="delApplyPath(this);">삭제</button></td>
							</tr>							
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<textarea id="addData" style="display: none;">
			<tr>
				<td><input class="form-control" type="text" name="level" /></td>
				<td><input class="form-control" type="text" name="date" maxlength="8"/></td>
				<td><input class="form-control" type="text" name="name" /></td>
				<td><input class="form-control" type="text" name="ssn" /></td>
				<td><input class="form-control" type="text" name="assn" /></td>
				<td><input class="form-control" type="text" name="ph" /></td>
				<td><button type="button" class="btn btn-danger btn-xs" onclick="delApplyPath(this);">삭제</button></td>
			</tr>
		</textarea>
	</form>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary btn-xs" onclick="procApplyPath();"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 저장</button>
</div>