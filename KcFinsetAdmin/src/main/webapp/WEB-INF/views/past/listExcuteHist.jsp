<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<div class="panel panel-primary">
	<div class="panel-collapse">
		<input type="hidden" name="listLength"
			value="${fn:length(pagedList.source)}" />
		<!-- List -->
		<table id="listPersonQuitInfo" class="table table-bordered tbl-info"
			cellspacing="0" width="100%">

			<thead>
				<tr>
					<th>고객번호</th>
					<th>날짜/시간</th>
					<th>분류</th>
					<th>금융사</th>
					<th>상품</th>
					<th>거치기간</th>
					<th>금리</th>
					<th>금액</th>
					<th>진행상태</th>
				</tr>
			</thead>

			<tbody>
				<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
					<tr>
						<td colspan="8" height="100" align="center">검색결과가 없습니다</td>
					</tr>
				</c:if>
				<c:forEach var="List" items="${pagedList.source}" varStatus="status">
					<c:choose>
						<c:when test="${status.count%2==0}">
							<c:set var="class_string" value="active"/>
						</c:when>
						<c:otherwise>
							<c:set var="class_string" value=""/>
						</c:otherwise>
					</c:choose>
				
					<tr class="${class_string}">
						<td>${List.no_person }</td>
						<td>${ufn:formatDate(List.dt_frt)}</td>
						<td> </td>
						<td>${ufn:getCodeName('cd_fc', List.cd_fc)}</td>
						<td>${ufn:getGoodsDetail(List.cd_goods, '')}</td>
						<td> </td>
						<td>${List.rto_loan}</td>
						<td>${ufn:formatNumber(List.amt_apply)}</td>
						<td>${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- //List -->
	</div>
	<div class="panel-footer">
		<span class="pull-left"> <span class="total-num em"><span></span>총건수
				: <em>${ufn:formatNumber(pagedList.recordCount)} 건</em></span>
		</span>
	</div>
</div>

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}" />

