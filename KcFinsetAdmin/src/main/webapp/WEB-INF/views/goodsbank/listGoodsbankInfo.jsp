<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {

});

</script>
<div class="panel panel-primary">
	<div class="panel-collapse">
		<!-- List -->
		<table id="tbl_listCont" class="table table-bordered tbl-info" cellspacing="0" width="100%">
			<thead>
		         <tr>
					<th rowspan="2"> 상품코드</th>
		            <th rowspan="2"> 상품명</th>
		            <th rowspan="2"> 은행명</th>
		            <th rowspan="2"> 등록여부</th>
		            <th colspan="2"> 분류</th>
		            <th colspan="2"> 금리</th>
		            <th rowspan="2"> 한도</th>               
		            <th rowspan="2"> 등록일</th>
		        </tr>
		         <tr>
		        	<th>대분류</th>
		        	<th>중분류</th>
		        	<th>최소</th>
		        	<th>최대</th>
		        </tr>
		    </thead>
			<tbody>
		    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="10" height="100" align="center">
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
					<tr class="${class_string}" onclick="setGoodsbankInfoUse('${List.cd_non_goods}');" style="cursor: pointer;">
						  <td>${List.cd_non_goods}</td>   <!-- 상품코드 -->
			              <td style="text-align: left;">${List.nm_goods}</td>   <!-- 상품명 -->
			              <td>${ufn:getNmFc(List.cd_fc)}</td>   <!-- 금융사 -->
			              <td>${List.yn_use}</td>	<!-- 등록여부 -->
			              <td>${ufn:getCodeName('cd_goods_l', List.cd_goods_class_l)}</td>
			              <td>${ufn:getCodeName('cd_goods_m', List.cd_goods_class_m)}</td>
			              <td>${List.rto_interest_from}</td>
			              <td>${List.rto_interest_to}</td>
			              <td>${ufn:formatNumber(List.desc_max_limit)}</td>
			              <td>${List.dt_frt}</td>	
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