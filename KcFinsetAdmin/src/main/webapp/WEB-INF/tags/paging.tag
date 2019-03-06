<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ attribute name="pagedListHolder" required="true" type="com.koscom.util.Pagination" %>

<c:if test="${pagedList.pageCount > 0}">
<nav id="footer">
	<%-- <span class="total-num pull-left">총건수 : ${pagedList.recordCount}</span> --%>
	<ul class="pagination">
	  <li>
	    <c:choose>
	    	<c:when test="${pagedList.firstLinkedPage-1 > 0}">
				<a href="JAVASCRIPT:jumpPage('${pagedList.firstLinkedPage}')" aria-label="Previous">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				</a>
	    	</c:when>
	    	<c:when test="${!pagedList.firstPage}">
				<a href="JAVASCRIPT:jumpPage('${pagedList.page-1}')" aria-label="Previous">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				</a>
	    	</c:when>
	    	<c:otherwise>
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	    	</c:otherwise>
	    </c:choose>
    </li>
    
    <c:if test="${pagedList.firstLinkedPage > 0}">
        <li><a href="JAVASCRIPT:jumpPage('1')">1</a></li>
    </c:if>
    <c:if test="${pagedList.firstLinkedPage > 1}">
        <li><span>...<span></li>
    </c:if>
    
    <c:forEach begin="${pagedList.firstLinkedPage}" end="${pagedList.lastLinkedPage}" var="i">
        <c:choose>
            <c:when test="${(pagedList.page-1) == i}">
                <li class="active"><a href="#">${i+1}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="JAVASCRIPT:jumpPage('${i+1}')">${i+1}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${pagedList.lastLinkedPage < pagedList.pageCount - 2}">
        <li><span>...</span></li>
    </c:if>
    <c:if test="${pagedList.lastLinkedPage < pagedList.pageCount - 1}">
        <li><a href="JAVASCRIPT:jumpPage('${pagedList.pageCount}')">${pagedList.pageCount}</a></li>
    </c:if>
    
    <li>
	    <c:choose>
	    	<c:when test="${pagedList.lastLinkedPage+1 >= pagedList.pageCount-1 && !pagedList.lastPage}">
				<a href="JAVASCRIPT:jumpPage('${pagedList.page+1}')" aria-label="Next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				</a>
	    	</c:when>
	    	<c:when test="${!pagedList.lastPage}">
				<a href="JAVASCRIPT:jumpPage('${pagedList.lastLinkedPage+2}')" aria-label="Next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				</a>
	    	</c:when>
	    	<c:otherwise>
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    	</c:otherwise>
	    </c:choose>
    </li>
  </ul>
</nav>
</c:if>
