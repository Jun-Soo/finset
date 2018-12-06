<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	var totalPageNo = "${pagedList.pageCount}";
	setTotalPageCnt(totalPageNo);
});
</script>
<c:choose>
	<c:when test="${empty pagedList.source}">
		<div class="data-none">
			<p>검색 내역이 없습니다.</p>
		</div>
	</c:when>
	<c:otherwise>
		<div class="prd-block prd-type">
			<div class="panel-box">
				<div class="panel-group" id="faq">
					<c:forEach var="List" items="${pagedList.source}" varStatus="status">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a href="#faq${List.board_idx}" class="collapsed" data-toggle="collapse" data-parent="#faq">
									<h3 class="panel-title"><p>${List.title}</p></h3>
								</a>
							</div>
							<div class="panel-collapse collapse" id="faq${List.board_idx}">
								<div class="panel-body">
									<div class="panel-answer">
										${List.content}
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>		
