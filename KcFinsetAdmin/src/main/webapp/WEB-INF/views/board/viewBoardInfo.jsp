<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

function delBoardInfo(id_board, seq, id_frt) {
	
	if(seq == "") return false;
	
	if(confirm("삭제하시겠습니까?") == false) return;
	
	var data = {"id_board":id_board, "seq":seq, "id_frt":id_frt};
	 $.ajax({
		url : "<c:url value='/board/delBoardInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			listBoardInfo(returnData.returnObj);
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
	
}

function createBoardComment() {
	
	var seq_board = frmViewBoard.seq_board.value;
	var content_comment = frmViewBoard.content_comment.value;
	
	if(content_comment == ""){
		alert("댓글을 입력해주세요.");
		return;
	}
	
	var data = {"seq_board":seq_board, "content_comment":content_comment};
	
	 $.ajax({
		url : "<c:url value='/board/createBoardComment.json'/>",
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

function getBoardComment(seq) {
	
	var data = {"seq":seq};
	vLoad("formBoardComment","<c:url value='/board/getBoardComment.crz'/>",data,false);
}
</script>

<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">${ufn:getBoardInfo(boardVO.id_board, 'NM')}</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-success btn-xs" onclick="listBoardInfo('${boardVO.id_board}');">
				<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 목록
			</button>
			<c:if test="${id_emp eq boardVO.id_frt}">
				<button type="button" class="btn btn-default btn-xs" onclick="formBoardInfo('${boardVO.id_board}', '${boardVO.seq}');">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 수정
				</button>
				<button type="button" class="btn btn-danger btn-xs" onclick="delBoardInfo('${boardVO.id_board}', '${boardVO.seq}', '${boardVO.id_frt}');">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 삭제
				</button>
			</c:if>
		</span>
	</div>
	<form name="frmViewBoard" id="frmViewBoard">
		<input type="hidden" name="seq_board" id="seq_board" value="${boardVO.seq}">
		<div class="panel-collapse">
			<table id="tbl_listBoardInfo" class="table table-bordered" cellspacing="0" witdh="100%">
				<colgroup>
					<col width="10%">
					<col width="25%">
					<c:if test="${fn:contains(ufn:getBoardInfo(boardVO.id_board, 'OPTION'), '10')}">
						<col width="5%">
					</c:if>
					<col width="5%">
					<col width="10%">
					<col width="15%">
					<col width="5%">
					<c:if test="${boardVO.id_board eq 'event' }">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					</c:if>
				</colgroup>
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<c:if test="${fn:contains(ufn:getBoardInfo(boardVO.id_board, 'OPTION'), '10')}">
							<th>첨부</th>
						</c:if>
						<th>팝업</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>조회</th>
						<c:if test="${boardVO.id_board eq 'event' }">
						<th>시작일자</th>
						<th>종료일자</th>
						<th>진행여부</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${pagedList.recordCount-(((pagedList.page - 1) * 10 ) + status.count)+1}</td>
						<td class="align-l">${boardVO.title}</td>
						<c:if test="${fn:contains(ufn:getBoardInfo(boardVO.id_board, 'OPTION'), '10')}">
							<td>
								<c:if test="${!empty boardVO.url_files1}">
									<a class="btn btn-xs circle" href="<c:url value='/attach/getFile.crz'/>?file_name=${boardVO.url_files1}"><span class="glyphicon glyphicon-save"></span></a>
								</c:if>
								<c:if test="${!empty boardVO.url_files2}">
									<a class="btn btn-xs circle" href="<c:url value='/attach/getFile.crz'/>?file_name=${boardVO.url_files2}"><span class="glyphicon glyphicon-save"></span></a>
								</c:if>
							</td>
						</c:if>
						<td>${boardVO.yn_popup}</td>
						<td>${ufn:getWorkerInfo(boardVO.id_frt,'NM')}</td>
						<td>${boardVO.dt_frt}</td>
						<td>${boardVO.hit}</td>
						<c:if test="${boardVO.id_board eq 'event' }">
						<td>${ufn:formatDate(boardVO.ymd_post_strt)}</td>
						<td>${ufn:formatDate(boardVO.ymd_post_end) }</td>
						<td>${ufn:getCodeName('cd_event_proc', boardVO.cd_event_proc) }</td>
						</c:if>
					</tr>
					<c:if test="${boardVO.id_board eq 'event' }">
						<c:if test="${!empty boardImgInfo }">
							<tr class="borTop">
								<td colspan="9">
									<div>
										<img src="/board/getBoardImg.crz?id_board=${boardImgInfo.id_board}&seq=${boardImgInfo.seq}&file_type=02">
									</div>
								</td>
							</tr>
						</c:if>
					</c:if>
					<tr class="borTop">
						<td colspan="9" class="h150 align-l">
							${boardVO.content }
						</td>
					</tr>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${fn:contains(ufn:getBoardInfo(boardVO.id_board, 'OPTION'), '30')}">
					<div class="comment-content">
						<div id="listboardComment" class="mg-bt10">
							<%@ include file="/WEB-INF/views/board/listBoardComment.jsp"%>
						</div>
						
						<div id="formBoardComment"  class="mg-bt10">
							<%@ include file="/WEB-INF/views/board/formBoardComment.jsp"%>
						</div>
						
						<div class="align-c">
							<button type="button" class="btn btn-success btn-xs" onclick="listBoardInfo('${boardVO.id_board}');">
								<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 목록
							</button>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="align-c">
						<button type="button" class="btn btn-success btn-xs" onclick="listBoardInfo('${boardVO.id_board}');">
							<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 목록
						</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</form>
</div>
