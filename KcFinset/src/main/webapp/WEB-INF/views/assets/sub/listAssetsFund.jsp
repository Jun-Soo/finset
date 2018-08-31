<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>

<c:choose>
	<c:when test="${empty pagedList.source}">
		<div class="data-none">
			<p>검색결과가 없습니다.</p>
		</div>
	</c:when>
	<c:otherwise>
		<div class="prd-block prd-type item-assets-list">
			<c:forEach var="List" items="${pagedList.source}" varStatus="status">
				<div class="container-fluid">
					<h3 class="prdlist-heading">
						${List.fundname} <%--<span class="label-outline">${List.assettype}</span> --%>
<%-- 						<span class="pull-right">${List.secuno}</span> --%>
						<span class="pull-right">${List.secunm}</span>
					</h3>

					<div class="prdlist-info">
						<dl>
							<dt>손익</dt>
							<c:choose>
								<c:when test="${List.revenue > 0}">
									<dd class="txt-point">${ufn:formatNumberPattern(List.revenue, "###,###")} 원(${ufn:formatNumberPattern(List.rto_revenue, "###.##")}%)</dd>
								</c:when>
								<c:otherwise>
									<dd class="txt-point-minus">${ufn:formatNumberPattern(List.revenue, "###,###")} 원(${ufn:formatNumberPattern(List.rto_revenue, "###.##")}%)</dd>
								</c:otherwise>
							</c:choose>
							
<%-- 							<dd class="txt-point">${ufn:formatNumberPattern(List.revenue, "###,###")} 원(${ufn:formatNumberPattern(List.rto_revenue, "###.##")}%)</dd> --%>
						</dl>
						<dl>
							<dt>평가금액</dt>
							<dd>${ufn:formatNumberPattern(List.valatcur, "###,###")} 원</dd>
						</dl>
						<dl>
							<dt>매수금액</dt>
							<dd>${ufn:formatNumberPattern(List.valattrade, "###,###")} 원 / ${List.qty}주</dd>
						</dl>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:otherwise>
</c:choose>
