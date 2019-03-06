<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

</script>

<div class="panel panel-primary">
	<div class="panel-collapse">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">전체 현황</a>
		</h3>
 		<div class="pull-right" style="padding-top: 5px;">
			<c:forEach var="List" items="${pagedList.source}" varStatus="status" begin="0" end="0">
				<a href="#none">비교일 : ${List.dt_frt }</a>
				<a href="#none">기준일 : ${List.dt_lst }</a>
			</c:forEach>
		</div>
	</div>
		<!-- List -->
		<table id="tbl_listCont" class="table table-bordered tbl-info" cellspacing="0" width="100%">
			<thead>
		         <tr>
					<th width="30%">은행</th>
					<th width="20%">대상</th>
					<th width="10%">상품수</th>
					<th width="10%">변경없음</th>
					<th width="10%">신규</th>
					<th width="10%">변경</th>
					<th width="10%">삭제</th>
		        </tr>
		    </thead>
			<tbody>
		    	<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty pagedList.source}">
				<tr>
					<td colspan="27" height="100" align="center">
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
					<tr class="${class_string}" style="cursor: pointer;" >
						<td onclick="searchBankData('${List.cd_fc}','${List.gubun}');">${ufn:getNmFc(List.cd_fc)}</td>
						<td>
							<c:choose>
								<c:when test="${List.gubun eq '1' }">
									신용
								</c:when>
								<c:otherwise>
									담보
								</c:otherwise>
							</c:choose>
						</td>
						<td onclick="searchBankData('${List.cd_fc}','${List.gubun}','');">${List.cnt_goods}</td>
						<td onclick="searchBankData('${List.cd_fc}','${List.gubun}','4');">${List.nochange_goods}</td>
						<td onclick="searchBankData('${List.cd_fc}','${List.gubun}','1');">${List.new_goods}</td>
						<td onclick="searchBankData('${List.cd_fc}','${List.gubun}','2');">${List.change_goods}</td>
						<td onclick="searchBankData('${List.cd_fc}','${List.gubun}','3');">${List.delete_goods}</td>
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
	<tg:paging pagedListHolder="${pagedList}"/>
</div>
<%-- // load our paging tag, pass pagedListHolder and the link --%>


