<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<table id="tbl_debtPersonalRepay" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
         <tr>
            <th> 정보관리번호</th>
            <th> 기준년월</th>
            <th> KCB여부</th>         
            <th> 계좌상태코드</th>
            <th> 계좌상태명</th>
            <th> 상환금액</th>
            <th> 상환원금</th>
            <th> 상환이자</th>
            <th> 중도상환금</th>
            <th> 대출잔액</th>
            <th> 연체금액</th>
            <th> 추정금리</th>
            <th> 은행금리</th>
            <th> 이자계산시작일</th>
            <th> 이자계산종료일</th>
            <th> 이자계산일수</th>
            <th> 금리산정기준코드명</th>
            <th> 결제예정일</th>
            <th> 이자납입일</th>
            <th> 이자계산일자</th>
<%--             <th colspan="${fn:length(ufn:getCodeList('cd_board_type'))}"> 게시판 옵션</th> --%>
        </tr>
<%--         <tr>
	        <c:forEach var="List" items="${ufn:getCodeList('cd_board_type')}">
				<th>${List.nm_code}</th>
			</c:forEach>
		</tr> --%>
    </thead>
       
	<tbody>
    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
		<c:if test="${empty listDebtPersonRepay}">
		<tr>
			<td colspan="20" height="100" align="center">
				검색결과가 없습니다
			</td>
		</tr>
		</c:if>
	  	<c:forEach var="List" items="${listDebtPersonRepay}" varStatus="status">
			<tr style="cursor: pointer;">
				<td>${List.no_manage_info}</td>   <!-- 정보관리번호 -->
				<td>${List.req_yyyymm}</td>	<!-- 기준년월 -->
				<td>${List.yn_kcb}</td>   <!-- KCB여부 -->
				<td>${List.cd_account}</td>   <!-- 계좌상태코드 -->
				<td>${List.nm_account}</td>   <!-- 계좌상태명 -->
				<td>${ufn:formatNumber(List.amt_repay)}</td>   <!-- 상환금액 -->
				<td>${ufn:formatNumber(List.amt_repay_p)}</td>   <!-- 상환원금 -->
				<td>${ufn:formatNumber(List.amt_repay_i)}</td>   <!-- 상환이자 -->
				<td>${ufn:formatNumber(List.amt_mid_repay)}</td>   <!-- 중도상환금 -->
				<td>${ufn:formatNumber(List.amt_remain)}</td>   <!-- 대출잔액 -->
				<td>${ufn:formatNumber(List.amt_delay)}</td>   <!-- 연체금액 -->
				<td>${List.etm_interest}</td>   <!-- 추정금리 -->
				<td>${List.bank_interest}</td>   <!-- 은행금리 -->
				<td>${ufn:formatShortDate(List.interest_strt_ymd)}</td>   <!-- 이자계산시작일 -->
				<td>${ufn:formatShortDate(List.interest_end_ymd)}</td>   <!-- 이자계산종료일 -->
				<td>${List.interest_cal_dcnt}</td>   <!-- 이자계산일수 -->
				<td>${List.etm_basic_nm}</td>   <!-- 금리산정기준코드명 -->
				<td>${ufn:formatShortDate(List.payment_ymd)}</td>   <!-- 결제예정일 -->
				<td>${ufn:formatShortDate(List.interest_ymd)}</td>   <!-- 이자납입일 -->
				<td>${List.interest_day}</td>   <!-- 이자계산일(DD) -->
			</tr>
		</c:forEach> 
	</tbody>
</table>