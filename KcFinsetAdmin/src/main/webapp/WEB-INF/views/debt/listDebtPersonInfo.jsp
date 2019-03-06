<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<table id="tbl_debtPersonalInfo" class="table table-bordered" cellspacing="0" width="100%"  style="height: 230px; overflow: scroll;">
	<thead>
         <tr>
            <th> 고객번호</th>
            <th> 고객명</th>
            <th> 정보관리번호</th>
            <th> 은행</th>
            <th> 금융기관</th>
            <th> 대출일자</th>
            <th> 해지일자</th>
            <th> 만기일자</th>
            <th> 상환방식</th>
            <th> 거래형태코드</th>
            <th> 신용여부</th>
            <th> 담보여부</th>
            <th> 약정금액</th>
            <th> 대출잔액</th>
            <th> 거치기간</th>
            <th> 잔여기간</th>
            <th> 총원리금</th>
            <th> 총원금</th>
            <th> 총이자</th>
            <th> 누적상환원리금</th>
            <th> 누적상환원금</th>
            <th> 누적상환이자</th>
            <th> 잔여원리금</th>
            <th> 잔여원금</th>
            <th> 잔여이자</th>
            <th> 업데이트일자</th>
        </tr>
    </thead>
	<tbody>
    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
		<c:if test="${empty listDebtPersonInfo}">
		<tr>
			<td colspan="25" height="100" align="center">
				검색결과가 없습니다
			</td>
		</tr>
		</c:if>
	  	<c:forEach var="List" items="${listDebtPersonInfo}" varStatus="status">
			<tr onclick="getDebtPersonRepay('${List.no_person}','${List.no_manage_info}');" style="cursor: pointer;">
				<td>${List.no_person}</td>   <!-- 회원관리번호 -->
				<td>${List.nm_person}</td>   <!-- 고객명 -->
				<td>${List.no_manage_info}</td>   <!-- 정보관리번호 -->
				<td>${List.nm_biz_type}</td>	<!-- 은행 -->
				<td>${List.nm_biz}</td>   <!-- 금융기관명 -->
				<td>${ufn:formatShortDate(List.ymd_loan)}</td>   <!-- 대출일자 -->
				<td>${ufn:formatShortDate(List.ymd_cancel)}</td>   <!-- 해지일자 -->
				<td>${ufn:formatShortDate(List.ymd_loanend)}</td>   <!-- 만기일자 -->
				<td>${List.repayment_nm}</td>   <!-- 상환방식 -->
				<td>${List.deal_nm}</td>   <!-- 거래형태코드 -->
				<td>${List.yn_credit}</td>   <!-- 신용여부 -->
				<td>${List.yn_loan}</td>   <!-- 담보여부 -->
				<td>${ufn:formatNumber(List.amt_contract)}</td>   <!-- 약정금액 -->
				<td>${ufn:formatNumber(List.amt_remain)}</td>   <!-- 대출잔액 -->
				<td>${List.loan_mount}</td>   <!-- 거치기간 -->
				<td>${List.duration_load}</td>   <!-- 잔여기간 -->
				<td>${ufn:formatNumber(List.amt_total_pni)}</td>   <!-- 총원리금 -->
				<td>${ufn:formatNumber(List.amt_total_p)}</td>   <!-- 총원금 -->
				<td>${ufn:formatNumber(List.amt_total_i)}</td>   <!-- 총이자 -->
				<td>${ufn:formatNumber(List.amt_repay_pni)}</td>   <!-- 누적상환원리금 -->
				<td>${ufn:formatNumber(List.amt_repay_p)}</td>   <!-- 누적상환원금 -->
				<td>${ufn:formatNumber(List.amt_repay_i)}</td>   <!-- 누적상환이자 -->
				<td>${ufn:formatNumber(List.amt_remain_pni)}</td>   <!-- 잔여원리금 -->
				<td>${ufn:formatNumber(List.amt_remain_p)}</td>   <!-- 잔여원금 -->
				<td>${ufn:formatNumber(List.amt_remain_i)}</td>   <!-- 잔여이자 -->
				<td>${ufn:formatShortDate(List.update_date)}</td>   <!-- 업데이트일자 -->
			</tr>
		</c:forEach> 
	</tbody>
</table>