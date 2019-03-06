<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<div class="panel panel-primary">
	<div class="panel-collapse">
		<table id="tbl_listWorkerAuthHist" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
			<thead>
				<tr>
					<th>순서</th>
					<th>권한변경 ID</th>
					<th>권한구분</th>
					<th>권한상세</th>
					<th>권한부여 ID</th>
					<th>처리구분</th>
					<th>등록일자</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="6" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
				</c:if>
				<c:forEach items="${pagedList.source}" var="List" varStatus="status">
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
						<td>${List.id_emp}</td>
						<td>${ufn:getCodeName('cd_auth', List.cd_auth)}</td>
						<td>
							<c:choose>
								<c:when test="${'01' eq List.cd_auth}">
									${ufn:getApproval(List.cd_detail,'NM')}
								</c:when>
							 	<c:otherwise>
									${ufn:getProgram(List.cd_detail,'NM')}
								</c:otherwise>
							</c:choose>
						</td>
						<td>${List.id_auth}</td>
						<td>${ufn:getCodeName('cd_auth_proc', List.cd_proc)}</td>
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