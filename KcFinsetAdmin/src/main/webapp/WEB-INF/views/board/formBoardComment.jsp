<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

</script>

<table id="tbl_formBoardComment" class="table table-comment" cellspacing="0" witdh="100%">
	<colgroup>
		<col width="95%">
		<col width="5%">
	</colgroup>
	<tbody>
		<tr>
			<td>
				<input type="hidden" name="" value="${boardComment.seq}">
				<textArea name="content_comment" id="content_comment" class="form-control h50" validate="label:댓글; maxbt:2000;" maxlength="2000">${boardComment.content_comment}</textArea>
			</td>
			<td>
				<c:choose>
					<c:when test="${empty boardComment}">
						<button type="button" class="btn btn-primary btn-xs" onclick="createBoardComment();">
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 등록
						</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-primary btn-xs" onclick="modifyBoardComment('${boardComment.seq}');">
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 수정
						</button>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</tbody>
</table>
