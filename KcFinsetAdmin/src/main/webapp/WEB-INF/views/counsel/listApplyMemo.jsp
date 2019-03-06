<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
//toggle panel
$(document).ready(function(){
	$(".toggle-panel-memo").each(function(i){
		$(this).find(".h-sec > a").click(function(){
			$(this).toggleClass("closed");
			$(this).closest(".toggle-panel-memo").find(".toggle-cont-memo").toggle("blind", 200);
		});
	});
});
</script>

<div class="panel panel-default toggle-panel-memo">
	<div class="panel-heading">
		<h3 class="h-sec"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span><a href="#"> 금융사메모 목록</a></h3>
	</div>
	<div class="panel-collapse toggle-cont-memo">
		<div class="list-group">
			<c:if test="${empty listCounsel}">
				<li class="list-group-item">
					<p class="list-group-item-text">
						<span class="list-group-h align-c">금융사메모가 없습니다.</span>
					</p>
				</li>
			</c:if>
			<c:forEach var="List" items="${listCounsel}" varStatus="status">
				<c:if test="${List.cd_counsel_class eq '20' || List.cd_counsel_class eq '30'}">
				<li class="list-group-item">
					<h4 class="list-group-item-heading blue">${ufn:getCodeName('cd_counsel_class', List.cd_counsel_class)}</h4> <strong>${List.dt_frt}</strong>
					<p class="list-group-item-text">
						${List.etc_counsel}
					</p>
				</li>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>