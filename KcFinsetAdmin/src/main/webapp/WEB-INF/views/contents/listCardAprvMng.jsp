<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	var table = $('#tbl_listCardAprvMng')
	.addClass( 'nowrap' )
	.DataTable({
		info: false,
		paging: false,
		searching: false,
		responsive: true,
		columnDefs: [
						{ targets: [-1, -3], className: 'dt-body-right' }
					]
	});
});
</script>
<div cellspacing="0" width="100%" style="height: 500px; overflow-x: hidden; overflow-y: auto;">
<table id="tbl_listCardAprvMng" class="table table-bordered tbl-info">
	<colgroup>
		<col width="4%">
		<col width="17%">
		<col width="17%">
		<col width="26%">
		<col width="18%">
		<col width="28%">
	</colgroup>
	<thead>
		<tr>
			<th> </th>
			<th>승인일자</th>
			<th>카드사</th>
			<th>가맹점명</th>
			<th>사업자번호</th>
			<th>업종</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="List" items="${cardAprvList}" varStatus="status">
			<tr
				style="cursor: pointer;" onclick="getCardAprvMng('${List.nm_member}','${List.no_biz_member}');"
			>
				<td>
					<input
						type="checkbox"
						id="chk${status.index}"
						name="multi"
						class="multi"
						data-nm_member="${List.nm_member}"
						data-no_biz_member="${List.no_biz_member}"
					/>
					<label for="chk${status.index}"></label>
				</td>
				<td>${List.dt_approval}</td>
				<td>${List.nm_fc}</td>
				<td>${List.nm_member}</td>
				<td>${List.no_biz_member}</td>
				<td>${List.type_biz_member}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>