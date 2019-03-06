<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	
	window.setupValidateForm( frmBoard );
	
	// SelectPicker
	$('.selectpicker').selectpicker();
	
});

//게시판 삽입,수정
function procBoardManage() {
	
	if(!frmBoard.validateForm()) return false;
	
	var data = frmBoard.ajaxSubmit();
	if(data == null) return false;

	$.ajax({
		url : "<c:url value='/board/procBoardManage.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			getBoardManage("");
			listBoardManage();
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

//게시판 삭제
function delBoardManage(id_board) {
	
	var data = {"id_board":id_board}
	if(data == null) return false;

	$.ajax({
		url : "<c:url value='/board/delBoardManage.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			getBoardManage("");
			listBoardManage();
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

</script>

<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">게시판 상세정보</a>
		</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-primary btn-xs" onclick="getBoardManage('');">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			<c:choose>
				<c:when test="${empty boardManage.id_board}">
					<button type="button" class="btn btn-default btn-xs" onclick="procBoardManage();">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 추가
					</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-default btn-xs" onclick="procBoardManage();">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
					</button>
				</c:otherwise>
			</c:choose>
			
		</span>
	</div>
	<div class="panel-collapse toggle-cont">
		<form name="frmBoard" id="frmBoard">
			<div class="panel-collapse">
				<table class="table table-classic">
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr>
							<th>게시판 이름</th>
							<td>
								<input type="text" class="form-control width-200" name="nm_board" value="${boardManage.nm_board}" validate="required; label:게시판이름; maxbt:50;" maxlength="50"/>
							</td>
							<c:choose>
								<c:when test="${empty boardManage.id_board}">
									<th>게시판ID</th>
									<td>
										<input type="text" class="form-control width-200" name="id_board" validate="required; label:게시판id; maxbt:20;" maxlength="20"/>
									</td>
								</c:when>
								<c:otherwise>
									<th>게시판ID</th>
									<td>${boardManage.id_board}</td>
									<input type="hidden" name="id_board" value="${boardManage.id_board}"/>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th>등록일</th>
							<td>
								${boardManage.dt_frt}
							</td>
						</tr>
						<tr>
							<th>게시판타입</th>
							<td>
								${ufn:makeRadioAndCheckBoxs("cd_board_type", "cd50_board_type", "checkbox", boardManage.cd50_board_type, 0)}
							</td>
							<th>사용여부</th>
							<td>
								<c:choose>
									<c:when test="${boardManage.id_board eq 'event'}">사용</c:when>
									<c:otherwise>${ufn:makeRadioAndCheckBoxs("yn_use", "yn_use", "radio", boardManage.yn_use, 0)}</c:otherwise>
								</c:choose>
								
							</td>
						</tr>
						<tr>
							<th>게시판 소개</th>
							<td colspan="3">
								<textarea class="form-control h50" name="etc_board" validate="label:게시판소개; maxbt:200;" maxlength="200">${boardManage.etc_board}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<div id="boardInfo">
		<%@ include file="/WEB-INF/views/board/listBoardInfo.jsp"%>
	</div>
</div>