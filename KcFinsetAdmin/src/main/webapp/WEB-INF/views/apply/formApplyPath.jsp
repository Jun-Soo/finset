<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	//접수경로 수정
	function getApplyPath(no_apply){
		var data = {"no_apply":no_apply};
		vLoad("modal-content-lg","<c:url value='/prepare/getApplyPath.crz'/>",data,false);
	}
	
	//접수경로 조회
	function loadApplyPath(no_apply){
		var data = {"no_apply":no_apply}
		vLoad("formApplyPath","<c:url value='/apply/popApplyPath.crz'/>",data);
	}
</script>

<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">접수 경로</a>
		</h3>
		<div class="pull-right">
			<button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target=".bs-modal-lg" onclick="getApplyPath('${applyVO.no_apply}');">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 수정
			</button>
		</div>
	</div>
	<div class="panel-collapse toggle-cont">
		<table class="table table-bordered">
			<thead> 
				<tr>
			        <th>접수순위</th>
					<th>접수일자</th>
					<th>상호</th>
					<th>등록번호</th>
					<th>협회등록번호</th>
					<th>전화번호</th>
				</tr> 
			</thead>
			<tbody>
				<c:forEach var="List" items="${ufn:getApplyPath(applyVO.etc_apply_path)}" varStatus="status">
					<tr>
						<c:set var="path_data" value="${fn:split(List,'||')}"/>
						<td>${path_data[0]}</td>
						<td>${ufn:formatDate(path_data[1])}</td>
						<td>${path_data[2]}</td>
						<td>${path_data[3]}</td>
						<td>${path_data[4]}</td>
						<td>${path_data[5]}</td>
					</tr>							
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>