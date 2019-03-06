<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
var ynShow = "";
function delNiceDefaultNice(no_nice_default_nice, no_person) {
	if(confirm("삭제하시겠습니까?") == false) return;
	ynShow = "N";	//버튼을 눌러도 tr이랑 같이 눌러져서 강제로 N 안보겠다고 세팅해야함
	var data = {"no_nice_default_nice":no_nice_default_nice, "no_person":no_person};
	 $.ajax({
		url : "<c:url value='/person/delNiceDefaultNice.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDefaultNiceList(returnData.returnObj, 'N');	//삭제후엔 안보이기
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
}
</script>
<div class="panel panel-primary">
	<div class="panel-heading">NICE채무불이행(NICE) 이력</div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>등록사유</th>
					<th>발생기관</th>
					<th>발생지점</th>
					<th>발생일</th>
					<th>제공일</th>
					<th>연체금액</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty listPersonNiceDefaultNice}">
				<tr>
					<td colspan="20">NICE채무불이행(NICE) 이력 없음</td>
				</tr>
			</c:if>
			<c:forEach var="listPersonNiceDefaultNice" items="${listPersonNiceDefaultNice}" varStatus="status">
				<tr class="${tr_class}" onclick="loadNiceDefaultNiceForm(this,'${listPersonNiceDefaultNice.no_nice_default_nice}','${listPersonNiceDefaultNice.no_person}', ynShow);" style="cursor: pointer;">
					<td>${listPersonNiceDefaultNice.regist_reason}</td>
					<td>${listPersonNiceDefaultNice.occur_institution}</td>
					<td>${listPersonNiceDefaultNice.occur_team}</td>
					<td>${listPersonNiceDefaultNice.dt_occur}</td>
					<td>${listPersonNiceDefaultNice.dt_offer}</td>
					<td>${listPersonNiceDefaultNice.amt_delay}</td>
					<td>
						<button type="button" class="btn btn-default btn-xs" onclick="delNiceDefaultNice('${listPersonNiceDefaultNice.no_nice_default_nice}','${listPersonNiceDefaultNice.no_person}');">
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
	<button type="button" class="btn btn-default btn-xs" onclick="loadNiceDefaultNiceForm('','','${no_person}','');">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> NICE채무불이행(NICE)내역 추가
	</button>
</div>
<br>