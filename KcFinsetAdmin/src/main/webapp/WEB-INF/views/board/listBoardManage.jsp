<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	
	 	/* $('#tbl_boardManage')
		.addClass( 'nowrap' )
		.DataTable({
			paging: false,
			responsive: true,
			columnDefs: [
							{ targets: [-1, -3], className: 'dt-body-right' }
						]
		});  */
});

</script>

<table id="tbl_boardManage" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
         <tr>
            <th rowspan="2"> 게시판명</th>
            <th rowspan="2"> 게시판ID</th>
            <th rowspan="2"> 특정게시판 오픈</th>             
            <th colspan="${fn:length(ufn:getCodeList('cd_board_type'))}"> 게시판 옵션</th>
            <th rowspan="2"> 사용여부</th>
            <th rowspan="2"> 등록일</th>
        </tr>
        <tr>
	        <c:forEach var="List" items="${ufn:getCodeList('cd_board_type')}">
				<th>${List.nm_code}</th>
			</c:forEach>
		</tr>
    </thead>
       
	<tbody>
    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
		<c:if test="${empty listBoardManage}">
		<tr>
			<td colspan="8" height="100" align="center">
				검색결과가 없습니다
			</td>
		</tr>
		</c:if>
	  	<c:forEach var="List" items="${listBoardManage}" varStatus="status">
			<tr onclick="getBoardManage('${List.id_board}');" style="cursor: pointer;">
				<td>${List.nm_board}</td>   <!-- 게시판명 -->
				<td>${List.id_board}</td>   <!-- 게시판id -->
				<td>${List.id_open_agency}</td>	<!-- 특정게시판용 -->
				<c:forEach var="ListSub" items="${ufn:getCodeList('cd_board_type')}">
					<td>
						<c:choose>
							<c:when test="${fn:indexOf(List.cd50_board_type, ListSub.code_value) gt '-1'}">Y</c:when>
							<c:otherwise>N</c:otherwise>
						</c:choose>
					</td>
				</c:forEach>
				<td>${List.yn_use}</td>   <!-- 사용어부 -->
				<td>${ufn:formatShortDate(List.dt_frt)}</td>   <!-- 등록일 -->
			</tr>
		</c:forEach> 
	</tbody>
</table>