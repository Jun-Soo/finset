<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
var ynShow = "";
function delNiceDelayNice(no_nice_delay_nice, no_person) {
	if(confirm("삭제하시겠습니까?") == false) return;
	ynShow = "N";	//버튼을 눌러도 tr이랑 같이 눌러져서 강제로 N 안보겠다고 세팅해야함
	var data = {"no_nice_delay_nice":no_nice_delay_nice, "no_person":no_person};
	 $.ajax({
		url : "<c:url value='/person/delNiceDelayNice.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDelayNiceList(returnData.returnObj, 'N');	//삭제후엔 안보이기
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
}
</script>
<div class="panel panel-primary">
	<div class="panel-heading">NICE연체정보(NICE) 이력</div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>상품구분</th>
					<th>연체구분</th>
					<th>발생기관명</th>
					<th>발생지점명</th>
					<th>최초연체발생일</th>
					<th>연체발생일수</th>
					<th>등록구분</th>
					<th>최초연체금액</th>
					<th>연체금액</th>
					<th>잔액</th>
					<th>한도</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty listPersonNiceDelayNice}">
				<tr>
					<td colspan="20">NICE연체정보(NICE)목록 없음</td>
				</tr>
			</c:if>
			<c:forEach var="listPersonNiceDelayNice" items="${listPersonNiceDelayNice}" varStatus="status">
				<tr class="${tr_class}" onclick="loadNiceDelayNiceForm(this,'${listPersonNiceDelayNice.no_nice_delay_nice}','${listPersonNiceDelayNice.no_person}', ynShow);" style="cursor: pointer;">
					<td>${listPersonNiceDelayNice.div_goods}</td>
					<td>${listPersonNiceDelayNice.div_delay}</td>
					<td>${listPersonNiceDelayNice.occur_institution}</td>
					<td>${listPersonNiceDelayNice.occur_team}</td>
					<td>${listPersonNiceDelayNice.dt_frt_delay}</td>
					<td>${listPersonNiceDelayNice.cnt_occur_delay_dt}</td>
					<td>${listPersonNiceDelayNice.div_regist}</td>
					<td>${listPersonNiceDelayNice.amt_frt_delay}</td>
					<td>${listPersonNiceDelayNice.amt_delay}</td>
					<td>${listPersonNiceDelayNice.amt_remain}</td>
					<td>${listPersonNiceDelayNice.amt_limit_loan}</td>
					<td>
						<button type="button" class="btn btn-default btn-xs" onclick="delNiceDelayNice('${listPersonNiceDelayNice.no_nice_delay_nice}','${listPersonNiceDelayNice.no_person}');">
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
	<button type="button" class="btn btn-default btn-xs" onclick="loadNiceDelayNiceForm('','','${no_person}','');">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> NICE연체정보(NICE)내역 추가
	</button>
</div>
<br>