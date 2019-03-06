<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#tbl_listWorker')
	.addClass( 'nowrap' )
	.DataTable({
		paging: false,
		responsive: true,
		columnDefs: [
						{ targets: [-1, -3], className: 'dt-body-right' }
					]
	});
});
</script>

<table id="tbl_listWorker" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>권한그룹</th>
			<th>업권</th>
			<th>금융사명</th>
			<th>ID</th>
			<th>사용자명</th>
			<th>사용여부</th>
			<th>등록일자</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${List}" varStatus="status">
		<c:choose>
			<c:when test="${List.id_emp eq 'admin'}">
				<!-- admin 은 ADMIN 만 -->
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<tr onclick="setWorkerInfo('${List.id_emp}')" style="cursor: pointer;">
						<td>${ufn:getCodeName('cd_template_group', List.cd_template_group)}</td>
						<td>${ufn:getCodeName('cd_fin', List.cd_fin)}</td>
						<td>${ufn:getNmFc(List.cd_fc)}</td>
						<td>${List.id_emp}</td>
						<td><strong>${List.nm_emp}</strong></td>
						<td>${ufn:getCodeName('cd_status_emp', List.cd_status_emp)}</td>
						<td>${List.dt_frt}</td>
					</tr>
				</sec:authorize>
			</c:when>
			<c:otherwise>
				<tr onclick="setWorkerInfo('${List.id_emp}')" style="cursor: pointer;">
						<td>${ufn:getCodeName('cd_template_group', List.cd_template_group)}</td>
						<td>${ufn:getCodeName('cd_fin', List.cd_fin)}</td>
						<td>${ufn:getNmFc(List.cd_fc)}</td>
						<td>${List.id_emp}</td>
						<td><strong>${List.nm_emp}</strong></td>
						<td>${ufn:getCodeName('cd_status_emp', List.cd_status_emp)}</td>
						<td>${List.dt_frt}</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	</tbody>
</table>