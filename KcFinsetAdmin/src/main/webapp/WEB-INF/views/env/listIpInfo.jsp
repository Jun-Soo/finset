<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function () {
		// DataTable (search)
		$('#tbl_sales_cust').DataTable();
		
	}); 
	
	//선택된 ip정보를 가져옴
	function getIpInfo(cd_system, ip) {
		
		if(cd_system == "" || ip == "") {
			alert("IP를 다시 선택하여주세요.(새로고침)");
			return;
		}
	
		var data = {"cd_system":cd_system,"ip":ip};
		vLoad("formIpInfo", "<c:url value='/ip/getIpInfo.crz'/>", data);
	}
</script>
<table id="tbl_sales_cust" class="table table-bordered tbl-info">
	<colgroup>
		<col width="100px"/>
		<col width="300px"/>
		<col width="*"/>
		<col width="150px"/>
		<col width="100px"/>
	</colgroup>
	<thead>
		<tr>
			<th>시스템구분</th>
			<th>IP 범위</th>
			<th>메모</th>
			<th>등록일시</th>
			<th>등록자</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="List" items="${List}" varStatus="status">
			<tr onclick="getIpInfo('${List.cd_system}','${List.ip}')" style="cursor: pointer;">
				<td>${ufn:getCodeName('cd_system', List.cd_system)}</td>
				<td>${List.ip}</td>
				<td>${List.memo}</td>
				<td>${List.dt_frt}</td>
				<td>${ufn:getWorkerInfo(List.id_frt,'NM')}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
