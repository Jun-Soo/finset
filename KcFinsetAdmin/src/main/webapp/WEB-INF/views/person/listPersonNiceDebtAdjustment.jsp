<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
var ynShow = "";
function delNiceDebtAdjustment(no_nice_debt_adjustment, no_person) {
	if(confirm("삭제하시겠습니까?") == false) return;
	ynShow = "N";	//버튼을 눌러도 tr이랑 같이 눌러져서 강제로 N 안보겠다고 세팅해야함
	var data = {"no_nice_debt_adjustment":no_nice_debt_adjustment, "no_person":no_person};
	 $.ajax({
		url : "<c:url value='/person/delNiceDebtAdjustment.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDebtAdjustmentList(returnData.returnObj, 'N');	//삭제후엔 안보이기
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
}
</script>
<div class="panel panel-primary">
	<div class="panel-heading">NICE채무조정 및 상환정보 이력</div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>발생기관명</th>
					<th>계좌상태</th>
					<th>개설일</th>
					<th>개설총금액</th>
					<th>잔액</th>
					<th>최초연체발생일</th>
					<th>연체발생일수</th>
					<th>총납입회차</th>
					<th>실납입회차</th>
					<th>연체회차</th>
					<th>연체금액</th>
					<th>채무감면유무</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty listPersonNiceDebtAdjustment}">
				<tr>
					<td colspan="20">NICE채무조정 및 상환정보목록 없음</td>
				</tr>
			</c:if>
			<c:forEach var="listPersonNiceDebtAdjustment" items="${listPersonNiceDebtAdjustment}" varStatus="status">
				<tr class="${tr_class}" onclick="loadNiceLoanForm(this,'${listPersonNiceDebtAdjustment.no_nice_debt_adjustment}','${listPersonNiceDebtAdjustment.no_person}', ynShow);" style="cursor: pointer;">
					<td>${listPersonNiceDebtAdjustment.occur_institution}</td>
					<td>${listPersonNiceDebtAdjustment.status_account}</td>
					<td>${listPersonNiceDebtAdjustment.dt_start}</td>
					<td>${listPersonNiceDebtAdjustment.amt_all_frt}</td>
					<td>${listPersonNiceDebtAdjustment.amt_remain}</td>
					<td>${listPersonNiceDebtAdjustment.dt_frt_delay}</td>
					<td>${listPersonNiceDebtAdjustment.cnt_occur_delay_dt}</td>
					<td>${listPersonNiceDebtAdjustment.cnt_all_pay}</td>
					<td>${listPersonNiceDebtAdjustment.cnt_real_pay}</td>
					<td>${listPersonNiceDebtAdjustment.cnt_delay}</td>
					<td>${listPersonNiceDebtAdjustment.amt_delay}</td>
					<td>${listPersonNiceDebtAdjustment.yn_reduce_debt}</td>
					<td>
						<button type="button" class="btn btn-default btn-xs" onclick="delNiceDebtAdjustment('${listPersonNiceDebtAdjustment.no_nice_debt_adjustment}','${listPersonNiceDebtAdjustment.no_person}');">
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
	<button type="button" class="btn btn-default btn-xs" onclick="loadNiceDebtAdjustmentForm('','','${no_person}','');">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> NICE채무조정 및 상환정보내역 추가
	</button>
</div>
<br>