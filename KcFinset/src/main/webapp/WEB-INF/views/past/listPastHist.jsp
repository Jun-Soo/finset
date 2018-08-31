<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
		var totalPageNo = "${pagedListPastHis.pageCount}";
		var currPageNo = "${pagedListPastHis.page}";

		if( totalPageNo == 1 ){
 			$("#list_add").hide();
 		}else{
 			showAdd(totalPageNo, currPageNo);
 		}
	});

</script>
<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
<c:if test="${empty pagedListPastHis.source}">
		<p>검색결과가 없습니다.</p>
	</c:if>
	<c:forEach var="List" items="${pagedListPastHis.source}" varStatus="status">
		<div class="panel panel-default">
			<div class="panel-heading">
				<a href="#loan1" class="collapsed clearfix">
					<div class="pull-left">
						<h3 class="panel-title">[${ufn:getCodeName('cd_fc', List.cd_fc)}] ${ufn:getGoodsDetail(List.cd_goods, '')}</h3>
						<small class="">${List.rto_interest_from} ~ ${List.rto_interest_to} | ${ufn:formatNumber(List.amt_apply)}</small>
					</div>
					<div class="label-group pull-right">
						<div class="label label-default">${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</div>
						<span class="date">${ufn:formatDate(List.dt_frt)}</span>
					</div>
				</a>
			</div>
		</div>
</c:forEach>
