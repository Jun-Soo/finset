<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
function delNiceLoan(no_niceloan, no_person) {
	if(confirm("삭제하시겠습니까?") == false) return;
	var data = {"no_niceloan":no_niceloan, "no_person":no_person};
	 $.ajax({
		url : "<c:url value='/person/delNiceLoan.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceLoanList(returnData.returnObj);
		},
		error : function (e) {
			alert(e.responseText);
		}
	}); 
}
</script>
<div class="panel panel-primary">
	<div class="panel-heading">NICE대출 이력</div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<!-- <th>seq</th>
					<th>소셜ID</th> -->
					<th>업권</th>
					<th>발생기관명</th>
					<th>개설일</th>
					<th>대출금액(단위:천원)</th>
					<th>대출구분</th>
					<th>변경일</th>
					<th>만기일</th>
					<th>대출종류</th>
					<th>최초개설금액(단위:천원)</th>
					<th>평균예상금리</th>
					<th>대표업권</th>
					<th>대출구분2</th>
					<th>잔존예상</th>
					<th>사용개월수</th>
					<th>총예상대출개월</th>
					<th>월불입금(단위:천원)</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty listPersonNiceLoan}">
				<tr>
					<td colspan="20">대출목록 없음</td>
				</tr>
			</c:if>
			<c:forEach var="listPersonNiceLoan" items="${listPersonNiceLoan}" varStatus="status">
				<tr class="${tr_class}" onclick="loadNiceLoanForm(this,'${listPersonNiceLoan.no_niceloan}','${listPersonNiceLoan.no_person}');" style="cursor: pointer;">
					<td>${ufn:getCodeName('cd_fin', listPersonNiceLoan.cd_fin)}</td>
					<td>${listPersonNiceLoan.occur_institution}</td>
					<td>${listPersonNiceLoan.dt_start}</td>
					<td>${listPersonNiceLoan.amt_loan}</td>
					<td>${ufn:getCodeName('div_loan', listPersonNiceLoan.div_loan)}</td>
					<td>${listPersonNiceLoan.dt_change}</td>
					<td>${listPersonNiceLoan.dt_max}</td>
					<td>${ufn:getCodeName('type_loan', listPersonNiceLoan.type_loan)}</td>
					<td>${listPersonNiceLoan.amt_frt}</td>
					<td>${listPersonNiceLoan.rto_avg_interest}</td>
					<td>${ufn:getCodeName('represent_fin', listPersonNiceLoan.represent_fin)}</td>
					<td>${ufn:getCodeName('div_loan2', listPersonNiceLoan.div_loan2)}</td>
					<td>${listPersonNiceLoan.est_remain}</td>
					<td>${listPersonNiceLoan.cnt_use_month}</td>
					<td>${listPersonNiceLoan.total_predict_loan_month}</td>
					<td>${listPersonNiceLoan.amt_pay_month}</td>
					<td><a href="#" onclick="delNiceLoan('${listPersonNiceLoan.no_niceloan}','${listPersonNiceLoan.no_person}');">삭제</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="panel panel-primary">
	<div class="panel-heading"> 신용, 담보, 대부  합계 CD_FIN </div>
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>업권</th>
					<th>대출구분</th>
					<th>금액(단위:천원)</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty listPersonNiceLoanAmtSumByCdFin}">
				<tr>
					<td colspan="20">대출목록 없음</td>
				</tr>
			</c:if>
			<c:forEach var="listPersonNiceLoanAmtSumByCdFin" items="${listPersonNiceLoanAmtSumByCdFin}" varStatus="status">
				<tr class="${tr_class}">
					<td>${ufn:getCodeName('cd_fin', listPersonNiceLoanAmtSumByCdFin.cd_fin)}</td>
					<td>${ufn:getCodeName('div_loan', listPersonNiceLoanAmtSumByCdFin.div_loan)}</td>
					<td>${listPersonNiceLoanAmtSumByCdFin.amt_loan}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="panel panel-primary">
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>종류</th>
					<th>대출기관</th>
					<th>건수</th>
					<th>보유 대출금액(단위:천원)</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty personNiceLoanAnalysis}">
				<tr>
					<td colspan="20">타대출상환비용 없음</td>
				</tr>
			</c:if>
				<tr class="${tr_class}">
					<td rowspan="2">주택담보대출</td>
					<td>1금융</td>
					<td>${personNiceLoanAnalysis.cnt_h_1} </td>
					<td>${personNiceLoanAnalysis.h_1} </td>
				</tr>
				<tr class="${tr_class}">
					<td>1금융외</td>
					<td>${personNiceLoanAnalysis.cnt_h_n1} </td>
					<td>${personNiceLoanAnalysis.h_n1} </td>
				</tr>
				<tr class="${tr_class}">
					<td rowspan="2">주택담보외 담보대출</td>
					<td>1금융</td>
					<td>${personNiceLoanAnalysis.cnt_nh_1} </td>
					<td>${personNiceLoanAnalysis.nh_1} </td>
				</tr>
				<tr class="${tr_class}">
					<td>1금융외</td>
					<td>${personNiceLoanAnalysis.cnt_nh_n1} </td>
					<td>${personNiceLoanAnalysis.nh_n1} </td>
				</tr>
				<tr class="${tr_class}">
					<td rowspan="3">신용대출</td>
					<td>1금융</td>
					<td>${personNiceLoanAnalysis.cnt_c_1} </td>
					<td>${personNiceLoanAnalysis.c_1} </td>
				</tr>
				<tr class="${tr_class}">
					<td>1금융외</td>
					<td>${personNiceLoanAnalysis.cnt_c_n1} </td>
					<td>${personNiceLoanAnalysis.c_n1} </td>
				</tr>
				<tr class="${tr_class}">
					<td>기타대출</td>
					<td>${personNiceLoanAnalysis.cnt_ec} </td>
					<td>${personNiceLoanAnalysis.ec} </td>
				</tr>
				<tr class="${tr_class}">
					<td rowspan="2">대부업</td>
					<td>대부업 담보</td>
					<td>${personNiceLoanAnalysis.cnt_d_d} </td>
					<td>${personNiceLoanAnalysis.d_d} </td>
				</tr>
				<tr class="${tr_class}">
					<td>대부업 신용</td>
					<td>${personNiceLoanAnalysis.cnt_d_c} </td>
					<td>${personNiceLoanAnalysis.d_c} </td>
				</tr>
				<tr class="${tr_class}">
					<td>현금서비스</td>
					<td></td>
					<td>${personNice.cnt_cash_service} </td>
					<td>${personNice.amt_cash_service} </td>
				</tr>
				<tr class="${tr_class}">
					<td>보증</td>
					<td></td>
					<td>${personNice.cnt_guarantee} </td>
					<td>${personNice.amt_guarantee} </td>
				</tr>
				<tr class="${tr_class}">
					<td>총합계</td>
					<td></td>
					<td></td>
					<td>${ADDED_AMT_ALL}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="panel panel-primary">
	<div class="panel-collapse">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>총합계(단위:천원)<!--  K82 --></th>
					<th>월소득(단위:천원)<!--  K89 --></th>
					<th>연소득(단위:천원)<!--  E94 --></th>
					<th>타대출상환비용(월불입금합)(단위:천원)<!-- K90 --></th>
					<th>최저생계비 (단위:천원)<!-- K91 --></th>
					<th>RTI(월상환액/월급여)(단위:천원)<!-- =K90/K89*100%  --></th>
					<th>LTI(총부채/연소득(단위:천원))<!-- =K82/E94*100% --> </th>
					<th>월가처분소득(단위:천원)<!-- =K89-K90-K91 --> </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty personNiceAmtPayMonthSum}">
				<tr>
					<td colspan="20">타대출상환비용 없음</td>
				</tr>
			</c:if>
				<tr class="${tr_class}">
					<td>${personNiceLoanAmtSum.amt_loan} </td>
					<td>${person.amt_monthly_income} </td>
					<td>${person.amt_year_income} </td>
					<td>${-personNiceAmtPayMonthSum.amt_pay_month} </td>
					<td>${person.amt_min_living_cost} </td>
					<td>${RTI}%</td>
					<td>${LTI}%</td>
					<td>${MRL}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="align-c mg-bt10">
	<button type="button" class="btn btn-default btn-xs" onclick="loadNiceLoanForm('','','${no_person}');">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 대출내역 추가
	</button>
</div>
<br>