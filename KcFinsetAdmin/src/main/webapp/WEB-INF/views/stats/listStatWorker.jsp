<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<input type="hidden" name="listLength" value="${fn:length(listStatWorker)}" />
<table class="table table-bordered tbl-info" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th rowspan="2">담당자</th>
			<th rowspan="2">상품명</th>
			<th rowspan="2">접수건수</th>
			<th rowspan="2">승인건수</th>
			<th rowspan="2">승인금액</th>
			<th rowspan="2">승인율</th>
			<th colspan="3">구간별승인건수</th>
			<th colspan="3">구간별승인금액</th>
			<th rowspan="2">심사건수</th>
			<th rowspan="2">보류건수</th>
			<th rowspan="2">거절건수</th>
		</tr>
		<tr>
			<th>1-500</th>
			<th>501-1000</th>
			<th>1001-</th>
			<th>1-500</th>
			<th>501-1000</th>
			<th>1001-</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${empty listStatWorker}">
		<tr>
			<td colspan="14" height="100" align="center">
				검색결과가 없습니다
			</td>
		</tr>
	</c:if>
	
	<c:set var="sum_cnt_apply" value="0"/>
	<c:set var="sum_cnt_approval" value="0"/>
	<c:set var="sum_amt_approval" value="0"/>
	<c:set var="sum_cnt_approval_500" value="0"/>
	<c:set var="sum_cnt_approval_1000" value="0"/>
	<c:set var="sum_cnt_approval_1001" value="0"/>
	<c:set var="sum_amt_approval_500" value="0"/>
	<c:set var="sum_amt_approval_1000" value="0"/>
	<c:set var="sum_amt_approval_1001" value="0"/>
	<c:set var="sum_cnt_exam" value="0"/>
	<c:set var="sum_cnt_hold" value="0"/>
	<c:set var="sum_cnt_reject" value="0"/>
	<c:set var="trCnt" 	value="0" />
	
	<c:set var="all_sum_cnt_apply"	value="0" />
	<c:set var="all_sum_cnt_approval" value="0" />
	<c:set var="all_sum_amt_approval" 	value="0" />
	<c:set var="all_sum_cnt_approval_500" 	value="0" />
	<c:set var="all_sum_cnt_approval_1000" value="0" />
	<c:set var="all_sum_cnt_approval_1001" value="0" />
	<c:set var="all_sum_amt_approval_500" 	value="0" />
	<c:set var="all_sum_amt_approval_1000" value="0" />
	<c:set var="all_sum_amt_approval_1001" value="0" />
	<c:set var="all_sum_cnt_exam" 	value="0" />
	<c:set var="all_sum_cnt_hold" 	value="0" />
	<c:set var="all_sum_cnt_reject" 	value="0" />
	
	<c:set var="id_apply_tmp" value="" />
	
	<c:forEach var="List" items="${listStatWorker}">
	
		<c:set var="rowCnt" value="1" />
		<c:set var="id_apply" value="" />
		<c:forEach var="ListSub" items="${listStatWorker}">
			<c:if test="${List.id_apply eq ListSub.id_apply}">
				<c:choose>
					<c:when test="${id_apply eq ListSub.id_apply}">
						<c:set var="rowCnt" value="${rowCnt+1}" />
					</c:when>
					<c:otherwise>
						<c:set var="id_apply" value="${ListSub.id_apply}" />
						<c:set var="rowCnt" value="1" />
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
		
		<c:set var="trCnt" 	value="${trCnt + 1}" />
		<tr>
			<c:if test="${List.id_apply ne id_apply_tmp}">
				<c:set var="id_apply_tmp" value="${List.id_apply}" />
				<td rowspan="${rowCnt}">${ufn:getWorkerInfo(List.id_apply,'NM')}</td>			
			</c:if>
			<td>${ufn:getGoodsDetail(List.cd_goods, '')}</td>
			<td class="right">${ufn:formatNumber(List.cnt_apply)}</td>
			<td class="right">${ufn:formatNumber(List.cnt_approval)}</td>
			<td class="right">${ufn:formatNumber(List.amt_approval)}</td>
			<c:choose>
				<c:when test="${List.cnt_apply eq 0}">
					<td class="right">100.00%</td>
				</c:when>
				<c:otherwise>
					<td class="right"><fmt:formatNumber value="${List.cnt_approval/List.cnt_apply}" pattern="0.00%"/></td>
				</c:otherwise>
			</c:choose>
			<td class="right">${ufn:formatNumber(List.cnt_approval_500)}</td>
			<td class="right">${ufn:formatNumber(List.cnt_approval_1000)}</td>
			<td class="right">${ufn:formatNumber(List.cnt_approval_1001)}</td>
			<td class="right">${ufn:formatNumber(List.amt_approval_500)}</td>
			<td class="right">${ufn:formatNumber(List.amt_approval_1000)}</td>
			<td class="right">${ufn:formatNumber(List.amt_approval_1001)}</td>
			<td class="right">${ufn:formatNumber(List.cnt_exam)}</td>
			<td class="right">${ufn:formatNumber(List.cnt_hold)}</td>
			<td class="right">${ufn:formatNumber(List.cnt_reject)}</td>
		</tr>
			
		<c:set var="sum_cnt_apply" value="${sum_cnt_apply + List.cnt_apply}"/>
		<c:set var="sum_cnt_approval" value="${sum_cnt_approval + List.cnt_approval}"/>
		<c:set var="sum_amt_approval" value="${sum_amt_approval + List.amt_approval}"/>
		<c:set var="sum_cnt_approval_500" value="${sum_cnt_approval_500 + List.cnt_approval_500}"/>
		<c:set var="sum_cnt_approval_1000" value="${sum_cnt_approval_1000 + List.cnt_approval_1000}"/>
		<c:set var="sum_cnt_approval_1001" value="${sum_cnt_approval_1001 + List.cnt_approval_1001}"/>
		<c:set var="sum_amt_approval_500" value="${sum_amt_approval_500 + List.amt_approval_500}"/>
		<c:set var="sum_amt_approval_1000" value="${sum_amt_approval_1000 + List.amt_approval_1000}"/>
		<c:set var="sum_amt_approval_1001" value="${sum_amt_approval_1001 + List.amt_approval_1001}"/>
		<c:set var="sum_cnt_exam" value="${sum_cnt_exam + List.cnt_exam}"/>
		<c:set var="sum_cnt_hold" value="${sum_cnt_hold + List.cnt_hold}"/>
		<c:set var="sum_cnt_reject" value="${sum_cnt_reject + List.cnt_reject}"/>
		
		<c:set var="all_sum_cnt_apply" value="${all_sum_cnt_apply + List.cnt_apply}" />
		<c:set var="all_sum_cnt_approval" value="${all_sum_cnt_approval + List.cnt_approval}" />
		<c:set var="all_sum_amt_approval" value="${all_sum_amt_approval + List.amt_approval}" />
		<c:set var="all_sum_cnt_approval_500" value="${all_sum_cnt_approval_500 + List.cnt_approval_500}" />
		<c:set var="all_sum_cnt_approval_1000" value="${all_sum_cnt_approval_1000 + List.cnt_approval_1000}" />
		<c:set var="all_sum_cnt_approval_1001" value="${all_sum_cnt_approval_1001 + List.cnt_approval_1001}" />	
		<c:set var="all_sum_amt_approval_500" value="${all_sum_amt_approval_500 + List.amt_approval_500}" />
		<c:set var="all_sum_amt_approval_1000" value="${all_sum_amt_approval_1000 + List.amt_approval_1000}" />
		<c:set var="all_sum_amt_approval_1001" value="${all_sum_amt_approval_1001 + List.amt_approval_1001}" />
		<c:set var="all_sum_cnt_exam" value="${all_sum_cnt_exam + List.cnt_exam}" />
		<c:set var="all_sum_cnt_hold" value="${all_sum_cnt_hold + List.cnt_hold}" />
		<c:set var="all_sum_cnt_reject" value="${all_sum_cnt_reject + List.cnt_reject}" />

		<c:if test="${trCnt eq rowCnt}">			
			<tr class="warning borTop">
				<td>소계</td>
				<td></td>
				<td class="right">${ufn:formatNumber(sum_cnt_apply)}</td>
				<td class="right">${ufn:formatNumber(sum_cnt_approval)}</td>
				<td class="right">${ufn:formatNumber(sum_amt_approval)}</td>
				<c:choose>
					<c:when test="${sum_cnt_apply eq 0}">
						<td class="right">100.00%</td>
					</c:when>
					<c:otherwise>
						<td class="right"><fmt:formatNumber value="${sum_cnt_approval / sum_cnt_apply}" pattern="0.00%"/></td>
					</c:otherwise>
				</c:choose>
				<td class="right">${ufn:formatNumber(sum_cnt_approval_500)}</td>	
				<td class="right">${ufn:formatNumber(sum_cnt_approval_1000)}</td>
				<td class="right">${ufn:formatNumber(sum_cnt_approval_1001)}</td>
				<td class="right">${ufn:formatNumber(sum_amt_approval_500)}</td>
				<td class="right">${ufn:formatNumber(sum_amt_approval_1000)}</td>
				<td class="right">${ufn:formatNumber(sum_amt_approval_1001)}</td>
				<td class="right">${ufn:formatNumber(sum_cnt_exam)}</td>
				<td class="right">${ufn:formatNumber(sum_cnt_hold)}</td>
				<td class="right">${ufn:formatNumber(sum_cnt_reject)}</td>		
			</tr>
			
			<c:set var="sum_cnt_apply"	value="0" />
			<c:set var="sum_cnt_approval" value="0" />
			<c:set var="sum_amt_approval" 	value="0" />
			<c:set var="sum_cnt_approval_500" 	value="0" />
			<c:set var="sum_cnt_approval_1000" value="0" />
			<c:set var="sum_cnt_approval_1001" value="0" />
			<c:set var="sum_amt_approval_500" 	value="0" />
			<c:set var="sum_amt_approval_1000" value="0" />
			<c:set var="sum_amt_approval_1001" value="0" />
			<c:set var="sum_cnt_exam" 	value="0" />
			<c:set var="sum_cnt_hold" 	value="0" />
			<c:set var="sum_cnt_reject" 	value="0" />
			<c:set var="trCnt" 	value="0" />			
		</c:if>
	</c:forEach>
		
	<c:if test="${!empty listStatWorker}">
		<tfoot>
		 	<tr class="active borTop">
				<td class="center"><strong>합계</strong></td>
				<td></td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_apply)}</td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_approval)}</td>
				<td class="right">${ufn:formatNumber(all_sum_amt_approval)}</td>
				<c:choose>
					<c:when test="${all_sum_cnt_apply eq 0}">
						<td class="right">100.00%</td>
					</c:when>
					<c:otherwise>
						<td class="right"><fmt:formatNumber value="${all_sum_cnt_approval / all_sum_cnt_apply}" pattern="0.00%"/></td>
					</c:otherwise>
				</c:choose>
				<td class="right">${ufn:formatNumber(all_sum_cnt_approval_500)}</td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_approval_1000)}</td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_approval_1001)}</td>
				<td class="right">${ufn:formatNumber(all_sum_amt_approval_500)}</td>
				<td class="right">${ufn:formatNumber(all_sum_amt_approval_1000)}</td>
				<td class="right">${ufn:formatNumber(all_sum_amt_approval_1001)}</td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_exam)}</td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_hold)}</td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_reject)}</td>
			</tr>
		</tfoot>
	</c:if>
	</tbody>
</table>
