<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<div class="panel panel-primary">
	<div class="panel-collapse">
		<table id="tbl_listWorkerLoginHist" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
			<thead>
				<tr>
					<th>순서</th>
					<!-- <th>시스템 구분</th> -->
					<th>소셜업체</th>
					<th>소셜ID</th>
					<th>소셜이름</th>
					<th>코스콤이름</th>
					<th>고유번호</th>
					<!-- <th>로그인결과</th> -->
					<th>WAS IP</th>
					<th>Client IP</th>
					<th>접속일시</th>
				</tr>
			</thead>
			<tbody>
				<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="8" height="100" align="center">
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
						<td>${status.index+1+(pagedList.page-1)*pagedList.pageSize}</td>
						<%-- <td>${ufn:getCodeName('cd_system', List.cd_system)}</td> --%>
						<td>${List.comp_social}</td>
						<td>${List.id_social}</td>
						<td>${List.nm_social}</td>
						<td>${List.nm_person}</td>
						<td>${List.no_person}</td>
						<%-- <td>${ufn:getCodeName('cd_login_result', List.cd_result)}</td> --%>
						<td>${List.ip_server}</td>
						<td>${List.ip_client}</td>
						<td>${List.dt_frt}</td>
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