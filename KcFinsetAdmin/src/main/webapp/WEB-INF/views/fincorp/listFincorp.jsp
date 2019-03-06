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
		            <th> 금융기업권</th>
		            <th> 금융기관코드</th>
		            <th> 금융사명</th>
		            <th> 사용여부</th>
		            <th> 제휴여부</th>
		            <th> 스크래핑</th>
		            <th> 사업자번호</th>
		            <th> 상품담당자</th>
		            <th> 등록일자</th>
		        </tr>
		    </thead>
			<tbody>
		    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="8" height="100" align="center">
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
					<tr class="${class_string}" onclick="goFincorpForm('${List.cd_fc}')" style="cursor: pointer;">
		              <td>${ufn:getCodeName('cd_fin', List.cd_fin)}</td>   <!-- 상품분류코드 -->
		              <td>${List.cd_fc}</td>   			<!-- 금융기관코드 -->
		              <td>${List.nm_fc}</td>   			<!-- 금융사명 -->
		              <td>${List.yn_use}</td>   		<!-- 사용여부 -->
		              <td>${List.yn_alliance}</td>  	<!-- 연계여부 -->
		              <td>${List.yn_scrap}</td>
		              <td>${List.no_biz_comp}</td>  	<!-- 사업자번호 -->
		              <td>${List.nm_staff_goods}</td>   <!-- 상품 담당자 -->
		              <td>${List.dt_frt}</td>   		<!-- 등록일자 -->
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