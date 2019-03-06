<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<div class="panel panel-primary">
	<div class="panel-collapse">
		<table id="tbl_listBoardInfo" class="table table-bordered table-fixed2">
			<colgroup>
				<col width="5%">
				<col width="5%">
				<col width="35%">
				<col width="25%">
				<col width="15%">
				<col width="5%">
				<col width="15%">
			</colgroup>
			<thead>
				<tr>
					<th>NO</th>
					<th>분류</th>
					<th>제목</th>
					<th>내용</th>
					<th>URL</th>
					<th>작성자</th>
					<th>발송일</th>
				</tr>
			</thead>
			<tbody>
				<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
					<tr>
						<td colspan="7" height="100" align="center">
							검색결과가 없습니다
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
					<tr class="${class_string}" onclick="viewPushInfo('<c:out value="${List.push_divcd}"/>',<c:out value="${List.seq_push}"/>);">
						<td ><c:out value="${List.rnum}"/></td>
						<td><c:out value="${ufn:getCodeName('push_divcd',List.push_divcd)}"/></td>
						<td class="txt-left"><c:out value="${List.title}"/></td>
						<td class="txt-left"><c:out value="${List.body}"/></td>
						<td class="txt-left"><c:out value="${List.link_addr}"/></td>
						<td><c:out value="${List.id_frt}"/></td>
						<td><c:out value="${List.dt_frt}"/></td>
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
	
<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}"/>