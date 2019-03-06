<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	//SelectPicker
	$('.selectpicker').selectpicker();
</script>
<!-- List -->
<div>
	<table class="table table-bordered" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>순번</th>
				<th>은행명</th>
				<th>사용</th>
			</tr>
		</thead>
		<tbody>
		<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
			<c:if test="${empty list}">
				<tr>
					<td colspan="3" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List" items="${list}" varStatus="status" >
				<tr onclick="NmSetData('${List.seq}','${List.nm_nm_fc}','${List.nm_yn_use}');" style="cursor: pointer;">
					<td>${List.seq} </td>
					<td>${List.nm_nm_fc} </td>
					<td>${List.nm_yn_use} </td>
					<input type="hidden" id="seq" name="seq" value="">
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
		<!-- //List -->
