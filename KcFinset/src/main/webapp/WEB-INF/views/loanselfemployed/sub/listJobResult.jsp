<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	var totalPageNo = "${pagedList.pageCount}";
	var currPageNo = "${pagedList.page}";
	
	showAdd(totalPageNo, currPageNo);
});

</script>

<c:choose>
	<c:when test="${empty pagedList.source}">
		<div class="container-fluid">
			<div class="list-group-item data-none">
				<p>검색한 데이터가 없습니다.</p>
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<div class="container-fluid">
		<div class="list-group">
		<c:forEach var="List" items="${pagedList.source}" varStatus="status">
		<a role="button" class="list-group-item" onclick="jobConfirm('${List.kiscode}', '${List.business}', '${List.korentrnm}')">
			<h4 class="list-group-item-heading">${List.korentrnm}</h4>
			<p class="list-group-item-text text-point">사업자번호 : ${List.business}</p>
			<p class="list-group-item-text">대표자명 : ${List.korreprnm}</p>
			<p class="list-group-item-text">사업자주소 : ${List.nolt_koraddr}</p>
		</a>
		</c:forEach>
		</div>
	</div>
	
	</c:otherwise>
</c:choose>
