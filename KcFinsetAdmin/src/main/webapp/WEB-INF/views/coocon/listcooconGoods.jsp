<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {

});

</script>
<div id="listCooconAPIinfo" class="panel panel-primary">
	<div class="panel-collapse">
		<!-- List -->
		<table id="tbl_listCont" class="table table-bordered tbl-info" cellspacing="0" width="100%">
			<thead>
		         <tr>
		         	<th> 은행명</th>
		            <th> 조회구분코드</th>
		            <th> 금융기관구분코드</th>
		            <th> 쿠콘은행코드</th>
		            <th> 금융사코드</th>
		            <th> 사용여부</th>               
		        </tr>
		    </thead>
			<tbody>
		    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="27" height="100" align="center">
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
					<tr class="${class_string}" style="cursor: pointer;" onclick="SetCooconAPIinfo('${List.gubun }','${List.org_type }','${List.cd_org}','${List.cd_fc}','${List.yn_use}');">
						<td>${ufn:getNmFc(List.cd_fc)}</td>
						<td>
							<c:choose>
								<c:when test="${List.gubun eq '1' }">
									신용
								</c:when>
								<c:otherwise>
									담보
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${List.org_type eq '1' }">
									은행
								</c:when>
								<c:when test="${List.org_type eq '2' }">
									저축은행
								</c:when>
								<c:when test="${List.org_type eq '3' }">
									보험사
								</c:when>
								<c:otherwise>
									캐피탈
								</c:otherwise>
							</c:choose>
						</td>
						<td>${List.cd_org}</td>
						<td>${List.cd_fc}</td>
						<td>${List.yn_use}</td>
		           	</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- //List -->
	</div>
	<div class="panel-footer">
		<span class="pull-left">
			<span class="total-num em"><span></span>총건수 : <em>${ufn:formatNumber(pagedList.recordCount)} 건</em></span>
		</span>
	</div>
</div>
<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}"/>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
