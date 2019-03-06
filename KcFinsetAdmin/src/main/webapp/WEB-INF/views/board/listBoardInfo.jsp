<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

</script>

<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">${ufn:getBoardInfo(boardForm.id_board, 'NM')}</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-default btn-xs" onclick="formBoardInfo('${boardForm.id_board}','');">
				<span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 글쓰기
			</button>
		</span>
	</div>
	<div class="panel panel-primary">
		<div class="panel-collapse">
			<table id="tbl_listBoardInfo" class="table table-bordered" cellspacing="0" witdh="100%">
				<colgroup>
					<col width="10%">
					<col width="35%">
					<c:if test="${fn:contains(ufn:getBoardInfo(boardForm.id_board, 'OPTION'), '10')}">
						<col width="5%">
					</c:if>
					<col width="5%">
					<col width="10%">
					<col width="10%">
					<col width="5%">
					<c:if test="${boardForm.id_board eq 'event' }">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					</c:if>
				</colgroup>
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<c:if test="${fn:contains(ufn:getBoardInfo(boardForm.id_board, 'OPTION'), '10')}">
							<th>첨부</th>
						</c:if>
						<th>팝업</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회</th>
						<c:if test="${boardForm.id_board eq 'event' }">
						<th>시작일자</th>
						<th>종료일자</th>
						<th>진행여부</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
						<c:if test="${empty pagedList.source}">
						<tr>
							<td colspan="5" height="100" align="center">
								작성한 글이 없습니다
							</td>
						</tr>
					</c:if>
					<c:forEach var="List" items="${pagedList.source}" varStatus="status">
						<c:choose>
							<c:when test="${status.count%2==0}">
								<c:set var="class_string" value="active"/>
							</c:when>
							<c:otherwise>
								<c:set var="class_string" value=""/>
							</c:otherwise>
						</c:choose>
					
						<tr class="${class_string}">
							<td>${pagedList.recordCount-(((pagedList.page - 1) * 10 ) + status.count)+1}</td>
							<c:choose>
								<c:when test="${List.id_board eq 'event'}">
									<td class="align-l"><img src="/board/getBoardImg.crz?id_board=${List.id_board}&seq=${List.seq}&file_type=01" height="70px" width="52px"><a href="#" onclick="viewBoardInfo('${List.seq}');" >${List.title}</a></td>
								</c:when>
								<c:otherwise>
									<td class="align-l"><a href="#" onclick="viewBoardInfo('${List.seq}');">${List.title}</a></td>	
								</c:otherwise>
							</c:choose>
							<c:if test="${fn:contains(ufn:getBoardInfo(boardForm.id_board, 'OPTION'), '10')}">
								<td>
									<c:if test="${!empty List.url_files1}">
										<a class="btn btn-xs circle" href="<c:url value='/attach/getFile.crz'/>?file_name=${List.url_files1}"><span class="glyphicon glyphicon-save"></span></a>
									</c:if>
									<c:if test="${!empty List.url_files2}">
										<a class="btn btn-xs circle" href="<c:url value='/attach/getFile.crz'/>?file_name=${List.url_files2}"><span class="glyphicon glyphicon-save"></span></a>
									</c:if>
								</td>
							</c:if>
							<td>${List.yn_popup}</td>
							<td>
							<c:choose>
							<c:when test="${!empty List.nm_person}">
								${List.nm_person }
							</c:when>
							<c:otherwise>
								${ufn:getWorkerInfo(List.id_frt,'NM')}
							</c:otherwise>
							</c:choose>
							</td>
							<td>${ufn:formatDate(List.dt_frt)}</td>
<%-- 							<td>${ufn:getFormattedTime("yyyy-MM-dd HH:mm:ss","yyyy-MM-dd",List.dt_frt)}</td> --%>
							<td>${List.hit}</td>
							<c:if test="${boardForm.id_board eq 'event' }">
								<td>${ufn:formatDate(List.ymd_post_strt)}</td>
								<td>${ufn:formatDate(List.ymd_post_end) }</td>
								<td>${ufn:getCodeName('cd_event_proc', List.cd_event_proc) }</td>
							</c:if>
				        </tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panel-footer">
			<span class="pull-left">
				<span class="total-num em"><span></span>총건수 : <em>${ufn:formatNumber(pagedList.recordCount)} 건</em></span>
			</span>
		</div>
	</div>
</div>
	
<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}"/>