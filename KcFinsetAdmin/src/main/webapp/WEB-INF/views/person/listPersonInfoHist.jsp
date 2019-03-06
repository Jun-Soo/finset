<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<div class="panel panel-primary">
	<div class="panel-collapse">
		<table id="tbl_listPersonInfoHist" class="table table-bordered tbl-info" cellspacing="0" witdh="100%">
			<thead>
				<tr>
					<th>순서</th>
					<th>고객번호</th>
					<th>이름</th>
					<th>변경항목</th>
					<th>변경전 정보</th>
					<th>변경후 정보</th>	
					<th>변경자</th>
					<th>변경일시</th>
				</tr>
			</thead>
			<tbody>
				<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
					<tr>
						<td colspan="8" height="100" align="center">
							검색결과가 없습니다
						</td>
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
						<td>${status.index+1+(pagedList.page-1)*pagedList.pageSize}</td>
						<td>${List.no_person}</td>
						<td>${List.nm_person}</td>
						<td>${ufn:getCodeEtc('cd_info', List.cd_info)}</td>
						<c:choose>
							<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05' && '03' eq List.cd_info}">
								<td>${ufn:formatMaskHp(List.before)}</td>
								<td>${ufn:formatMaskHp(List.after)}</td>
							</c:when>
							<c:otherwise>
								<td>${List.before}</td>
								<td>${List.after}</td>
							</c:otherwise>
						</c:choose>
						<td>${ufn:getWorkerInfo(List.id_frt, 'NM')}</td>
						<td>${List.dt_frt}</td>
			        </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="panel-footer">
		<span class="pull-left">
			<span class="total-num em"><span></span>총건수 : <em>${ufn:formatNumber(pagedList.recordCount)} 건</em></span>
		</span>
	</div>
</div>

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}"/>