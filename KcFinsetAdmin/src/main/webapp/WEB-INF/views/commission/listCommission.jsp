<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {

});
function setGoodsInfo(no_apply){
// 	frmGoodsList.tmp_no_apply.value = no_apply;
	var data = {"no_apply":no_apply};
	vLoad("formCommissionDetail","<c:url value='/commission/formCommissionInfo.crz'/>",data);
	console.log(data);
}
</script>
<div class="panel panel-primary">
	<div class="panel-collapse">
		<!-- List -->
		<table id="tbl_listCont" class="table table-bordered tbl-info" cellspacing="0" width="100%">
			<thead>
		         <tr>
		            <th> 접수번호</th>
		            <th> 금융사</th>
		            <th> 상품</th>
		            <th> 금리</th>
		            <th> 승인금액</th>
		            <th> 승인일</th>
		            <th> 예상수수료율</th>               
		            <th> 예상수수료</th>               
		            <th> 지급수수료율</th>
		            <th> 지급수수료</th>
		            <th> 집행여부</th>
		            <th> 집행일</th>
		        </tr>
		    </thead>
			<tbody>
		    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="12" height="100" align="center">
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
					<tr class="${class_string}" onclick="setGoodsInfo('${List.no_apply}')" style="cursor: pointer;">
					
			              <td>${List.no_apply}</td>   <!-- 접수번호 -->
			              <td>${ufn:getNmFc(List.cd_fc)}</td>   <!-- 금융사 -->
			              <td>${ufn:getGoodsDetail(List.cd_goods, '')}</td>   <!-- 상품명 -->
						  <td>${List.rto_loan}</td>   
						  <td>${List.amt_approval}</td>   
						  <td>${List.ymd_approval}</td>  
			              <td>${List.rto_exp_loan}</td>
			              <td>${List.amt_exp_commission}</td>
			              <td>${List.rto_real_loan}</td>
			              <td>${List.amt_real_commission}</td>
			              <td>${List.yn_exe}</td>
			              <td>${List.dt_exe}</td>	
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