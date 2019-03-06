<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	// SelectPicker
	$('.selectpicker').selectpicker();
	
});


</script>
<c:choose>
	<c:when test="${ufn:getCodeName('_CONF_SYSTEM', 'CD_GOODS_TAB') eq '01'}">
		<c:choose>
			<c:when test="${!empty cd_goods_type}">
				<c:set var="cd_goods_type" value="${cd_goods_type}" />
			</c:when>
			<c:otherwise>
				<c:set var="cd_goods_type" value="01" />
			</c:otherwise>
		</c:choose>
		<c:set var="goods_type_comp" value="${cd_goods_type}" />
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${!empty cd_fc}">
				<c:set var="cd_fc" value="${cd_fc}" />
			</c:when>
			<c:otherwise>
				<c:set var="cd_fc" value="" />
			</c:otherwise>
		</c:choose>
		<c:set var="goods_type_comp" value="${cd_fc}" />
	</c:otherwise>
</c:choose>

<c:forEach items="${cd_goods_arr}" var="cd_goods">
	<input type="hidden" name="cd_goods_arr" value="${cd_goods}" />
</c:forEach>

<div class="panel-collapse toggle-cont">
	<table class="table table-bordered tbl-info">
		${ufn:makeGoodsTypeRadioAndCheckBoxs( ufn:getCodeName('_CONF_SYSTEM', 'CD_GOODS_TAB'), goods_type_comp, "cd_goods_arr", "checkbox", str_cd_goods, 4, "loadInfoSet(this);" )}
	</table>
</div>
