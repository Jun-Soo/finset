<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
		/*
		var totalPageNo = "${pagedList.pageCount}";
		var currPageNo = "${pagedList.page}";

		if( totalPageNo == 1 ){
 			$("#tab1_add").hide();
 		}else{
 			showAdd(totalPageNo, currPageNo);
 		}
		*/
	});

</script>
<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
<%-- <c:if test="${empty applyVO}"> --%>
<!-- 	<p>검색결과가 없습니다.</p> -->
<%-- </c:if> --%>
<!-- <div class="panel panel-default"> -->
<!-- 	<div class="panel-heading"> -->
<!-- 		<a href="#loan1" class="collapsed clearfix"> -->
<!-- 			<div class="pull-left"> -->
<%-- 				<h3 class="panel-title">[${ufn:getCodeName('cd_fc', applyVO.cd_fc)}] ${ufn:getGoodsDetail(applyVO.cd_goods, '')}</h3> --%>
<%-- 				<small class="">${applyVO.rto_interest_from} ~ ${applyVO.rto_interest_to} | ${ufn:formatNumber(applyVO.amt_apply)}</small> --%>
<!-- 			</div> -->
<!-- 			<div class="label-group pull-right"> -->
<%-- 				<div class="label label-default">${ufn:getCodeName('cd_apply_doc_box', applyVO.cd_apply_doc_box)}</div> --%>
<%-- 				<span class="date">${ufn:formatDate(applyVO.dt_frt)}</span> --%>
<!-- 			</div> -->
<!-- 		</a> -->
<!-- 	</div> -->
<!-- </div> -->

<c:choose>
	<c:when test="${empty applyVO}">
		<p>검색결과가 없습니다.</p>
	</c:when>
	<c:otherwise>
		<div class="panel panel-default">
			<div class="panel-heading">
				<a href="#loan1" class="collapsed clearfix"  data-toggle="collapse" data-parent="#faq">
					<div class="pull-left">
						<h3 class="panel-title">[${ufn:getCodeName('cd_fc', applyVO.cd_fc)}] ${ufn:getGoodsDetail(applyVO.cd_goods, '')}</h3>
						<small class="">${applyVO.rto_interest_from} ~ ${applyVO.rto_interest_to} | ${ufn:formatNumber(applyVO.amt_apply)}</small>
					</div>
					<div class="label-group pull-right">
						<div class="label label-default">${ufn:getCodeName('cd_apply_doc_box', applyVO.cd_apply_doc_box)}</div>
						<span class="date">${ufn:formatDate(applyVO.dt_frt)}</span>
					</div>
				</a>
			</div>
			<div class="panel-collapse collapse" id="loan1">
				<div class="panel-body">
					<div class="panel-answer">
						타인명의 휴대폰 사용 시에도 신청은 가능합니다. 본인인증의 경우 휴대폰인증, 신용카드인증, 공인인증서인증 총 3가지 방법이 있기 때문에 가능하신 인증방법을 이용해주시면 됩니다.
					</div>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>
