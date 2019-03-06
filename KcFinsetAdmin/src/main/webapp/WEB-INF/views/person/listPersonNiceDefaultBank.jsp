<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
var ynShow = "";
function delNiceDefaultBank(no_nice_default_bank, no_person) {
	if(confirm("삭제하시겠습니까?") == false) return;
	ynShow = "N";	//버튼을 눌러도 tr이랑 같이 눌러져서 강제로 N 안보겠다고 세팅해야함
	var data = {"no_nice_default_bank":no_nice_default_bank, "no_person":no_person};
	 $.ajax({
		url : "<c:url value='/person/delNiceDefaultBank.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDefaultBankList(returnData.returnObj, 'N');	//삭제후엔 안보이기
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
}
</script>
<div class="panel panel-primary">
	<div class="panel-heading">NICE채무불이행(은행연합회) 이력</div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>정보구분</th>
					<th>사유코드</th>
					<th>사유구분</th>
					<th>발생기관</th>
					<th>발생일</th>
					<th>등록일</th>
					<th>해제일</th>
					<th>해제구분</th>
					<th>발생지점</th>
					<th>등록금액</th>
					<th>연체금액</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty listPersonNiceDefaultBank}">
				<tr>
					<td colspan="20">NICE채무불이행(은행연합회)목록 없음</td>
				</tr>
			</c:if>
			<c:forEach var="listPersonNiceDefaultBank" items="${listPersonNiceDefaultBank}" varStatus="status">
				<tr class="${tr_class}" onclick="loadNiceDefaultBankForm(this,'${listPersonNiceDefaultBank.no_nice_default_bank}','${listPersonNiceDefaultBank.no_person}', ynShow);" style="cursor: pointer;">
					<td>${listPersonNiceDefaultBank.div_data}</td>
					<td>${listPersonNiceDefaultBank.cd_reason}</td>
					<td>${listPersonNiceDefaultBank.div_reason}</td>
					<td>${listPersonNiceDefaultBank.occur_institution}</td>
					<td>${listPersonNiceDefaultBank.dt_occur}</td>
					<td>${listPersonNiceDefaultBank.dt_regist}</td>
					<td>${listPersonNiceDefaultBank.dt_lift}</td>
					<td>${listPersonNiceDefaultBank.div_lift}</td>
					<td>${listPersonNiceDefaultBank.occur_team}</td>
					<td>${listPersonNiceDefaultBank.amt_regist}</td>
					<td>${listPersonNiceDefaultBank.amt_delay}</td>
					<td>
						<button type="button" class="btn btn-default btn-xs" onclick="delNiceDefaultBank('${listPersonNiceDefaultBank.no_nice_default_bank}','${listPersonNiceDefaultBank.no_person}');">
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
	<button type="button" class="btn btn-default btn-xs" onclick="loadNiceDefaultBankForm('','','${no_person}','');">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> NICE채무불이행(은행연합회)내역 추가
	</button>
</div>
<br>