<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
var ynShow = "";
function delNiceDebtGuarantee(no_nice_debt_guarantee, no_person) {
	if(confirm("삭제하시겠습니까?") == false) return;
	ynShow = "N";	//버튼을 눌러도 tr이랑 같이 눌러져서 강제로 N 안보겠다고 세팅해야함
	var data = {"no_nice_debt_guarantee":no_nice_debt_guarantee, "no_person":no_person};
	 $.ajax({
		url : "<c:url value='/person/delNiceDebtGuarantee.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDebtGuaranteeList(returnData.returnObj, 'N');	//삭제후엔 안보이기
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
}
</script>
<div class="panel panel-primary">
	<div class="panel-heading">NICE채무보증정보(은행연합회) 이력</div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>발생기관명</th>
					<th>발생일</th>
					<th>보증금액</th>
					<th>변경일</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty listPersonNiceDebtGuarantee}">
				<tr>
					<td colspan="20">NICE채무보증정보(은행연합회)목록 없음</td>
				</tr>
			</c:if>
			<c:forEach var="listPersonNiceDebtGuarantee" items="${listPersonNiceDebtGuarantee}" varStatus="status">
				<tr class="${tr_class}" onclick="loadNiceDebtGuaranteeForm(this,'${listPersonNiceDebtGuarantee.no_nice_debt_guarantee}','${listPersonNiceDebtGuarantee.no_person}', ynShow);" style="cursor: pointer;">
					<td>${listPersonNiceDebtGuarantee.occur_institution}</td>
					<td>${listPersonNiceDebtGuarantee.dt_occur}</td>
					<td>${listPersonNiceDebtGuarantee.amt_guarantee}</td>
					<td>${listPersonNiceDebtGuarantee.dt_change}</td>
					<td>
						<button type="button" class="btn btn-default btn-xs" onclick="delNiceDebtGuarantee('${listPersonNiceDebtGuarantee.no_nice_debt_guarantee}','${listPersonNiceDebtGuarantee.no_person}');">
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
	<button type="button" class="btn btn-default btn-xs" onclick="loadNiceDebtGuaranteeForm('','','${no_person}','');">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> NICE채무보증정보(은행연합회)내역 추가
	</button>
</div>
<br>