<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<style>
/*  .table-fixed > thead,   */
/*  .table-fixed > tbody > tr, */
/*  .table-fixed2 > thead,   */
/*  .table-fixed2 > tbody > tr {  */
/*      display: table;  */
/*      width: 100%;  */
/*      table-layout: fixed;  */
/*      margin-top: -1px;  */
/*  }  */
/*  .table-fixed > thead {  */
/*      width: calc( 100% - 17px );  */
/*  }  */
/*  @supports (-ms-ime-align:auto) {  */
/*      .table-fixed > thead {  */
/*          width: calc( 100% - 12px );  */
/*      }  */
/* }  */
/*  .table-fixed > tbody, */
/*  .table-fixed2 > tbody {  */
/*      display: block;  */
/*      height: 200px; */
/*       overflow: auto;  */
/* }  */
</style>
<div class="panel panel-primary">
	<div class="panel-collapse">
		<table id="tbl_listBoardInfo" class="table table-bordered table-fixed2">
			<colgroup>
				<col width="5%">
				<col width="10%">
				<col width="35%">
				<col width="25%">
<!-- 				<col width="10%"> -->
				<col width="10%">
				<col width="15%">
			</colgroup>
			<thead>
				<tr>
					<th>글번호</th>
					<th>고객번호</th>
					<th>제목</th>
					<th>연결주소</th>
<!-- 					<th>예약시간</th> -->
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
					<tr class="${class_string}">
						<td><c:out value="${List.seq_push}"/></td>
						<td><c:out value="${List.no_person}"/></td>
						<td onclick="viewPushInfo(<c:out value="${List.seq_push}"/>);"><c:out value="${List.title}"/></td>
						<td><c:out value="${List.link_addr}"/></td>
<%-- 						<td><c:out value="${List.dt_reserv}"/></td> --%>
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