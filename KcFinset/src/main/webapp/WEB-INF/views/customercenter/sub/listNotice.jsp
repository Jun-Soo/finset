<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">


</script>
<c:choose>
	<c:when test="${empty pagedList.source}">
		<div class="data-none">
			<p>조회한 내역이 없습니다.</p>
		</div>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${boardForm.id_board eq 'notice'}">
			<!-- 공지 -->
				<div class="container-fluid">
					<div class="list-group">
						<c:forEach var="List" items="${pagedList.source}" varStatus="status">
							<a class="list-group-item" onclick="CustomerNoticeDatail('${List.seq}','${List.id_board}');">
								<p class="date">${ufn:formatDate(List.dt_frt)}</p>
								<h3 class="h3">${List.title}</h3>
							</a>
						</c:forEach>
					</div>	
				</div>
			</c:when>
			<c:otherwise>
			<!-- 이벤트ㅡ -->
				<div class="container-fluid">
					<div class="list-group">
						<c:forEach var="List" items="${pagedList.source}" varStatus="status">
							<a class="list-group-item list-evt" onclick="CustomerNoticeDatail('${List.seq}','${List.id_board}');">
								<div class="evt-thumb">
									<img src="/m/board/getBoardImg.crz?id_board=${List.id_board}&seq=${List.seq}&file_type=01">
								</div>
								<div class="caption">
									<h3 class="h3">${List.title}</h3>
									<p class="date">${ufn:formatDate(List.ymd_post_strt)} ~ ${ufn:formatDate(List.ymd_post_end)}</p>
									<c:choose>
										<c:when test="${List.cd_event_proc eq '01'}">
											<span class="label label-status status-green">예정</span>
										</c:when>
										<c:when test="${List.cd_event_proc eq '02'}">
											<span class="label label-status status-blue">진행중</span>
										</c:when>
										<c:otherwise>
											<span class="label label-status status-gray">종료</span>
										</c:otherwise>
									</c:choose>
								</div>
							</a>
						</c:forEach>
					</div>	
				</div>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
