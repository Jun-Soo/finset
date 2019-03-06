<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

function modifyBoardComment(seq) {
	
	var seq_board = frmViewBoard.seq_board.value;
	var content_comment = frmViewBoard.content_comment.value;
	
	var data = {"seq_board":seq_board, "seq":seq, "content_comment":content_comment};
	
	 $.ajax({
		url : "<c:url value='/board/modifyBoardComment.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			viewBoardInfo(returnData.returnObj);
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
}

function delBoardComment(seq) {
	
	if(confirm("삭제하시겠습니까?") == false) return;
	
	var seq_board = frmViewBoard.seq_board.value;
	var data = {"seq_board":seq_board, "seq":seq};
	
	 $.ajax({
		url : "<c:url value='/board/delBoardComment.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			viewBoardInfo(returnData.returnObj);
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
}
</script>

<table id="tbl_listBoardComment" class="table table-comment" cellspacing="0" witdh="100%">
	<colgroup>
		<col width="10%">
		<col width="62%">
		<col width="12%">
		<col width="3%">
		<col width="3%">
	</colgroup>
	<tbody>
		<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
		<c:forEach var="List" items="${listBoardComment}">
			<tr>
				<td>
				<c:choose>
					<c:when test="${!empty List.nm_person}">
						${List.nm_person }
					</c:when>
					<c:when test="${List.id_frt eq 'admin'}">
						${ufn:getWorkerInfo(List.id_frt,'NM')}
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
				</c:choose>	
				</td>
				<td>${List.content_comment}</td>
				<td class="align-r">${List.dt_frt}</td>
				<c:choose>
					<c:when test="${id_emp eq List.id_frt}">
						<td class="align-r"><a href="#" onclick="getBoardComment('${List.seq}');">수정</a></td>
						<td><a href="#" onclick="delBoardComment('${List.seq}');">삭제</a></td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
	        </tr>
		</c:forEach>
	</tbody>
</table>
