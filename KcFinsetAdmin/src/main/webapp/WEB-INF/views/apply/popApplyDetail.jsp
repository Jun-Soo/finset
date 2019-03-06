<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	$(document).ready(function () {
		// toggle panel
		$(".toggle-panel").each(function(i){
			$(this).find(".h-sec > a").click(function(){
				$(this).toggleClass("closed");
				$(this).closest(".toggle-panel").find(".toggle-cont").toggle("blind", 200);
			});
		});
	});
	
	function modifyApplyMemo() {
		
	    var data = frmApplyMemo.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/apply/modifyApplyMemo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	function delApplyInfo() {
		
		var data = frmApplyMemo.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/apply/delApplyInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				
				// 팝업이 닫혀야함, 접수이력 갱신되야함
				if(parseInt(returnData.cd_result,10) == 0) {
					opener.loadPrepareTab('HIST');
					opener.loadPrepareForm();
					self.close();
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
</script>
</head>
<body>
	<div class="ui-layout-content">
		<c:if test="${applyVO.cd_apply_doc_box eq '01' }">
			<div>
				<div class="panel-heading">
					<span class="pull-right">
						<button type="button" class="btn btn-warning btn-xs" onclick="delApplyInfo();">
							<span class="glyphicon glyphicon-floppy-remove" aria-hidden="true"></span> 접수 삭제
						</button>
					</span>
				</div>
			</div>
		</c:if>
		<!-- 접수메모 -->
		<div>
			<div class="panel panel-default toggle-panel">
				<div class="panel-heading">
					<h3 class="h-sec pull-left">
						<a href="#none">접수 시 메모</a>
					</h3>
					<span class="pull-right">
						<button type="button" class="btn btn-default btn-xs" onclick="modifyApplyMemo();">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 수정</button>
					</span>
				</div>
				<form id="frmApplyMemo" name="frmApplyMemo">
					<div class="panel-collapse toggle-cont">
					<input type="hidden" name="no_apply" value="${applyVO.no_apply}" />
					<input type="hidden" name="no_prepare" value="${applyVO.no_prepare}" />
						<table class="table table-classic">
							<colgroup>
								<col width="10%">
								<col width="*">
							</colgroup>
							<tr>
								<th>메모</th>
								<td><textarea class="form-control w100 h50" name="memo_to_apply" validate="label:상담내용; maxbt:1000;" maxlength="330">${applyVO.memo_to_apply}</textarea></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
		
		<!-- 파일폼 -->
		<div id="formAttachInfo">
			<%@ include file="/WEB-INF/views/attach/formAttachInfo.jsp" %>
		</div>
	
		<!-- 파일리스트 -->
		<div id="listAttachInfo">
			<%@ include file="/WEB-INF/views/attach/listAttachInfo.jsp" %>
		</div>
		
		<!-- 접수 경로 -->
		<div id="formApplyPath">
			<%@ include file="/WEB-INF/views/apply/formApplyPath.jsp" %>
		</div>
		
		<!-- 적법수집 -->
		<div id="formLegalInfo">
			<%@ include file="/WEB-INF/views/apply/formApplyLegal.jsp" %>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/comm/modal.jsp"%>
	<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>