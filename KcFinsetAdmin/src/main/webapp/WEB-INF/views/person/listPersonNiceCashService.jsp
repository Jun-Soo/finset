<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
var ynShow = "";
function delNiceCashService(no_nice_cash_service, no_person) {
	if(confirm("삭제하시겠습니까?") == false) return;
	ynShow = "N";	//버튼을 눌러도 tr이랑 같이 눌러져서 강제로 N 안보겠다고 세팅해야함
	var data = {"no_nice_cash_service":no_nice_cash_service, "no_person":no_person};
	 $.ajax({
		url : "<c:url value='/person/delNiceCashService.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceCashServiceList(returnData.returnObj, 'N');	//삭제후엔 안보이기
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
}
</script>
<div class="panel panel-primary">
	<div class="panel-heading">NICE현금서비스정보(은행연합회) 이력</div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>발생기관명</th>
					<th>발생지점명</th>
					<th>발생일</th>
					<th>금액</th>
					<th>변경일</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty listPersonNiceCashService}">
				<tr>
					<td colspan="20">현금서비스정보(은행연합회)목록 없음</td>
				</tr>
			</c:if>
			<c:forEach var="listPersonNiceCashService" items="${listPersonNiceCashService}" varStatus="status">
				<tr class="${tr_class}" onclick="loadNiceCashServiceForm(this,'${listPersonNiceCashService.no_nice_cash_service}','${listPersonNiceCashService.no_person}', ynShow);" style="cursor: pointer;">
					<td>${listPersonNiceCashService.occur_institution}</td>
					<td>${listPersonNiceCashService.occur_team}</td>
					<td>${listPersonNiceCashService.dt_occur}</td>
					<td>${listPersonNiceCashService.amt_cash_service}</td>
					<td>${listPersonNiceCashService.dt_change}</td>
					<td>
						<button type="button" class="btn btn-default btn-xs" onclick="delNiceCashService('${listPersonNiceCashService.no_nice_cash_service}','${listPersonNiceCashService.no_person}');">
						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span> 삭제
						</button>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="align-c mg-bt10">
	<button type="button" class="btn btn-default btn-xs" onclick="loadNiceCashServiceForm('','','${no_person}','');">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 현금서비스정보(은행연합회) 추가
	</button>
</div>
<br>