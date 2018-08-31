<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

function moveDetailPage(link_addr){
	location.href = "<%= request.getContextPath() %>"+link_addr;
}

</script>

<div class="list-group thumb-item">
<c:choose>
	<c:when test="${empty pagedList.source}">
			<div class="data-none">
				<p>알림 내역이 없습니다.</p>
			</div>
	</c:when>
	<c:otherwise>
		<!-- 
			<c:set var="dt_init" value="init"/>
				<c:forEach var="List" items="${pagedList.source}" varStatus="status">
					<c:if test="${dt_init eq List.dt_frt}">
					</c:if>
					<c:if test="${dt_init ne List.dt_frt}">
						<c:set var="dt_init" value="${List.dt_frt}"/>
						<h2 class="h2 h-date">${ufn:formatDateDot(List.dt_frt)}</h2>
					</c:if>
					<a href="#faq${List.rnum}" class="list-group-item label-general-noti">
						<strong>${List.title}</strong>
					</a>
				</c:forEach>
		 -->
			 <c:forEach var="List" items="${pagedList.source}" varStatus="status">
			 	<h2 class="h2 h-date">${ufn:formatDateDot(List.dt_frt)}</h2> 
			 	<c:choose>
			 		<c:when test="${!empty List.link_addr}">
			 			<a onclick="moveDetailPage('${List.link_addr}');" class="list-group-item label-credit label-credit-search">
			 		</c:when>
			 		<c:otherwise>
			 			<a class="list-group-item label-credit label-credit-search"> <!-- link 없는경우 -->
			 		</c:otherwise>
			 	</c:choose>
					<strong><c:out value="${List.title}"/></strong>
	<!-- 				<p>신한은행</p> -->
				</a>
			</c:forEach>
	</c:otherwise>
</c:choose>
</div>