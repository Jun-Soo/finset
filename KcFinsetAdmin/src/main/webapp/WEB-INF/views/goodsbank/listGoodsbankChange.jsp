<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<style>
</style>
<script type="text/javascript">
$(document).ready(function () {
	$('td:contains("Y")').css('color','red');
	
});
</script>
<div id="listGoodsbankChange" class="panel panel-primary">
	<div class="panel-collapse">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">상품별 현황</a>
		</h3>
	</div>
		<!-- List -->
		<table id="wrap" class="table table-bordered tbl-info" cellspacing="0" width="100%" >
			<thead>
		         <tr>
					<th width="24%">상품명</th>
					<th width="3%">등록</th>
					<th width="3%">상태</th>
					<th width="3%">개요</th>
					<th width="3%">고객</th>
					<th width="3%">담보</th>
					<th width="3%">한도</th>
					<th width="3%">금리</th>
					<th width="3%">우대</th>
					<th width="3%">기간</th>
					<th width="3%">상환</th>
					<th width="3%">수수료</th>
					<th width="3%">서류</th>
					<th width="3%">연체</th>
					<th width="3%">중도</th>
					<th width="3%">비용</th>
					<th width="3%">신청</th>
					<th width="3%">지급</th>
					<th width="3%">문의</th>
					<th width="3%">기타</th>
		        </tr>
		    </thead>
			<tbody>
		    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty listcooconChangeInfo}">
				<tr>
					<td colspan="21" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
				</c:if>
			    <c:forEach var="List" items="${listcooconChangeInfo}" varStatus="status">
					<tr id="chkYN" class="${class_string}" style="cursor: pointer;" onclick="SetData('${List.cd_coocon_goods}','','${List.nm_coocon_goods}','${List.cd_fc}' );">
						<td style="text-align: left; font-size: 10px;"; width="18%" >${List.nm_goods}</td>
						<td width="4%" >${List.yn_reg}</td>
						<td width="4%" >
							<c:choose>
								<c:when test="${List.status eq 1}">
									신규
								</c:when>
								<c:when test="${List.status eq 2}">
									변경
								</c:when>
								<c:when test="${List.status eq 4}">
									변경없음
								</c:when>
								<c:otherwise>
									삭제
								</c:otherwise>
							</c:choose>
						</td>
						<td width="4%" onclick="BringData(event,'desc_goods', '${List.cd_fc}', '${List.yn_goods}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_goods}</td>
						<td width="4%" onclick="BringData(event,'desc_target_cust', '${List.cd_fc}', '${List.yn_target_cust}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_target_cust}</td>
						<td width="4%" onclick="BringData(event,'desc_collateral', '${List.cd_fc}', '${List.yn_collateral}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_collateral}</td>
						<td width="4%" onclick="BringData(event,'desc_limit', '${List.cd_fc}', '${List.yn_limit}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_limit}</td>
						<td width="4%" onclick="BringData(event,'desc_yield_rate', '${List.cd_fc}', '${List.yn_yield_rate}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_yield_rate}</td>
						<td width="4%" onclick="BringData(event,'desc_prefer_yield', '${List.cd_fc}', '${List.yn_prefer_yield}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_prefer_yield}</td>
						<td width="4%" onclick="BringData(event,'desc_term', '${List.cd_fc}', '${List.yn_term}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_term}</td>
						<td width="4%" onclick="BringData(event,'desc_repaymethod', '${List.cd_fc}', '${List.yn_repaymethod}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_repaymethod}</td>
						<td width="4%" onclick="BringData(event,'desc_contractfee', '${List.cd_fc}', '${List.yn_contractfee}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_contractfee}</td>
						<td width="4%" onclick="BringData(event,'desc_papers', '${List.cd_fc}', '${List.yn_paper}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_paper}</td>
						<td width="4%" onclick="BringData(event,'desc_overdue_interest', '${List.cd_fc}', '${List.yn_overdue}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_overdue}</td>
						<td width="4%" onclick="BringData(event,'desc_repayment_fee', '${List.cd_fc}', '${List.yn_repayment_fee}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_repayment_fee}</td>
						<td width="4%" onclick="BringData(event,'desc_loan_cost', '${List.cd_fc}', '${List.yn_loan_cost}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_loan_cost}</td>
						<td width="4%" onclick="BringData(event,'desc_loan_period', '${List.cd_fc}', '${List.yn_loan_period}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_loan_period}</td>
						<td width="4%" onclick="BringData(event,'desc_loan_provide', '${List.cd_fc}', '${List.yn_loan_provide}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_loan_provide}</td>
						<td width="4%" onclick="BringData(event,'desc_goods_inquiry', '${List.cd_fc}', '${List.yn_goods_inquiry}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_goods_inquiry}</td>
						<td width="4%" onclick="BringData(event,'desc_etc', '${List.cd_fc}', '${List.yn_etc}','${List.cd_coocon_goods}','${List.nm_coocon_goods}','${List.cd_type_req }');">${List.yn_etc}</td>
		           	</tr>
				</c:forEach>
			</tbody>
		</table>
	<!-- //List -->
	<div class="panel-footer">
		<span class="pull-left">
			<span class="total-num em"><span></span>총건수 : <em>${listcooconChangeInfoCount } 건</em></span>
		</span>
	</div>
	</div>
</div>
<%-- // load our paging tag, pass pagedListHolder and the link --%>


