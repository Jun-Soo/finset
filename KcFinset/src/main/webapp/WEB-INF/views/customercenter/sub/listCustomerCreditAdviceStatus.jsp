<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">


</script>
<c:choose>
	<c:when test="${empty pagedList.source}">
		<div class="data-none">
			<p>신청한 내역이 없습니다.</p>
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="List" items="${pagedList.source}" varStatus="status">
				
			<c:choose>
			<c:when test="${List.cd_counsel_status eq '1'}"> <!-- 상담신청접수 -->
				<div class="container-fluid status-group-item">
					<div class="panel-heading status-request">
						<div class="status-txt-area">   
							<strong><c:out value="${ufn:getCodeName('cd_counsel_status', List.cd_counsel_status)}"/></strong>
							<fmt:parseDate var="dtApply" value="${List.dt_apply}" pattern="yyyy-mm-dd" />
					    	<p><fmt:formatDate value="${dtApply}" pattern="mm월 dd일" />&nbsp;<c:out value="${List.nm_person}"/>님의 신용상담 신청이 접수되었습니다.</p>
						</div>
					</div>
				</div>
			</c:when>
			<c:when test="${List.cd_counsel_status eq '2'}"> <!-- 상담준비중 -->
				<div class="container-fluid status-group-item">
					<div class="panel-heading status-ing">
						<div class="status-txt-area">
							<strong><c:out value="${ufn:getCodeName('cd_counsel_status', List.cd_counsel_status)}"/></strong>
							<fmt:parseDate var="dtPreCounsel" value="${List.dt_pre_counsel}" pattern="yyyy-mm-dd" />
					    	<p><fmt:formatDate value="${dtPreCounsel}" pattern="mm월 dd일" />&nbsp;<c:out value="${List.nm_person}"/>님의 신용상담을  준비중입니다.</p>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise> <!-- 상담완료 -->
				<div class="container-fluid status-group-item">
					<div class="panel-heading status-end">  
						<div class="status-txt-area">
							<strong><c:out value="${ufn:getCodeName('cd_counsel_status', List.cd_counsel_status)}"/></strong>
							<fmt:parseDate var="dtCounsel" value="${List.dt_counsel}" pattern="yyyy-mm-dd" />
					    	<p><fmt:formatDate value="${dtCounsel}" pattern="mm월 dd일" />&nbsp;<c:out value="${List.nm_person}"/>님의 신용상담이 완료되었습니다.</p>
						</div>  
						<a onclick="creditAdviceDetail('${List.counsel_seq}');" class="btn btn-outline">상담결과보기</a>
					</div>
				</div>
			</c:otherwise>
			</c:choose>
		
		</c:forEach>
	</c:otherwise>
</c:choose>
