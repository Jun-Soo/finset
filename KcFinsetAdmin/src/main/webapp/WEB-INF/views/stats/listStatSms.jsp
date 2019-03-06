<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<input type="hidden" name="listLength" value="${fn:length(listStatSms)}" />
<table class="table table-bordered tbl-info" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>담당자</th>
			<th>문자타입</th>
			<th>총건수</th>
			<th>전송중</th>
			<th>성공건수</th>
			<th>실패건수</th>
			<th>성공율</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${empty listStatSms}">
		<tr>
			<td colspan="14" height="100" align="center">
				검색결과가 없습니다
			</td>
		</tr>
	</c:if>
	
	<c:set var="sum_cnt_sms" value="0"/>
	<c:set var="sum_cnt_sending" value="0"/>
	<c:set var="sum_cnt_success" value="0"/>
	<c:set var="sum_cnt_fail" value="0"/>
	<c:set var="trCnt" 	value="0" />
	
	<c:set var="all_sum_cnt_sms"	value="0" />
	<c:set var="all_sum_cnt_sending" value="0" />
	<c:set var="all_sum_cnt_success" 	value="0" />
	<c:set var="all_sum_cnt_fail" 	value="0" />
	
	<c:set var="id_emp_tmp" value="" />
	
	<c:forEach var="List" items="${listStatSms}">
	
		<c:set var="rowCnt" value="1" />
		<c:set var="id_emp" value="" />
		<c:forEach var="ListSub" items="${listStatSms}">
			<c:if test="${List.id_emp eq ListSub.id_emp}">
				<c:choose>
					<c:when test="${id_emp eq ListSub.id_emp}">
						<c:set var="rowCnt" value="${rowCnt+1}" />
					</c:when>
					<c:otherwise>
						<c:set var="id_emp" value="${ListSub.id_emp}" />
						<c:set var="rowCnt" value="1" />
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
		
		<c:set var="trCnt" 	value="${trCnt + 1}" />
		<tr>
			<c:if test="${List.id_emp ne id_emp_tmp}">
				<c:set var="id_emp_tmp" value="${List.id_emp}" />
				<td rowspan="${rowCnt}">${ufn:getWorkerInfo(List.id_emp,'NM')}</td>			
			</c:if>
			<td>	
				<c:choose>
					<c:when test="${List.c1_type_msg eq 'M'}">
						MMS
					</c:when>
					<c:when test="${List.c1_type_msg eq 'S'}">
						SMS
					</c:when>
					<c:otherwise>
						${List.c1_type_msg}
					</c:otherwise>
				</c:choose>
			</td>
			<td class="right">${ufn:formatNumber(List.cnt_sms)}</td>
			<td class="right">${ufn:formatNumber(List.cnt_sms_sending)}</td>
			<td class="right">${ufn:formatNumber(List.cnt_sms_success)}</td>
			<td class="right">${ufn:formatNumber(List.cnt_sms_fail)}</td>
			<c:choose>
				<c:when test="${List.cnt_sms eq 0}">
					<td class="right">-</td>
				</c:when>
				<c:otherwise>
					<td class="right"><fmt:formatNumber value="${List.cnt_sms_success/List.cnt_sms}" pattern="0.00%"/></td>
				</c:otherwise>
			</c:choose>
		</tr>

		<c:set var="sum_cnt_sms" value="${sum_cnt_sms + List.cnt_sms}"/>
		<c:set var="sum_cnt_sending" value="${sum_cnt_sending + List.cnt_sms_sending}"/>
		<c:set var="sum_cnt_success" value="${sum_cnt_success + List.cnt_sms_success}"/>
		<c:set var="sum_cnt_fail" value="${sum_cnt_fail + List.cnt_sms_fail}"/>
		
		<c:set var="all_sum_cnt_sms" value="${all_sum_cnt_sms + List.cnt_sms}" />
		<c:set var="all_sum_cnt_sending" value="${all_sum_cnt_sending + List.cnt_sms_sending}" />
		<c:set var="all_sum_cnt_success" value="${all_sum_cnt_success + List.cnt_sms_success}" />
		<c:set var="all_sum_cnt_fail" value="${all_sum_cnt_fail + List.cnt_sms_fail}" />

		<c:if test="${trCnt eq rowCnt}">			
			<tr class="warning borTop">
				<td>소계</td>
				<td></td>
				<td class="right">${ufn:formatNumber(sum_cnt_sms)}</td>
				<td class="right">${ufn:formatNumber(sum_cnt_sending)}</td>
				<td class="right">${ufn:formatNumber(sum_cnt_success)}</td>
				<td class="right">${ufn:formatNumber(sum_cnt_fail)}</td>
				<c:choose>
					<c:when test="${sum_cnt_sms eq 0}">
						<td class="right">-</td>
					</c:when>
					<c:otherwise>
						<td class="right"><fmt:formatNumber value="${sum_cnt_success/sum_cnt_sms}" pattern="0.00%"/></td>
					</c:otherwise>
				</c:choose>	
			</tr>
			
			<c:set var="sum_cnt_sms"	value="0" />
			<c:set var="sum_cnt_sending" value="0" />
			<c:set var="sum_cnt_success" 	value="0" />
			<c:set var="sum_cnt_fail" 	value="0" />
			<c:set var="trCnt" 	value="0" />
		</c:if>
	</c:forEach>
		
	<c:if test="${!empty listStatSms}">
		<tfoot>
		 	<tr class="active borTop">
				<td class="center"><strong>합계</strong></td>
				<td></td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_sms)}</td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_sending)}</td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_success)}</td>
				<td class="right">${ufn:formatNumber(all_sum_cnt_fail)}</td>
				<c:choose>
					<c:when test="${all_sum_cnt_sms eq 0}">
						<td class="right">100.00%</td>
					</c:when>
					<c:otherwise>
						<td class="right"><fmt:formatNumber value="${all_sum_cnt_success / all_sum_cnt_sms}" pattern="0.00%"/></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</tfoot>
	</c:if>
	</tbody>
</table>
