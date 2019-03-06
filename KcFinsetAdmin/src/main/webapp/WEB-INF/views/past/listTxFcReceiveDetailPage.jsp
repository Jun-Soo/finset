<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<div class="panel panel-primary">
	<div class="panel-collapse">
		<input type="hidden" name="listLength" value="${fn:length(pagedList.source)}" />
		<!-- List -->
		<table id="tbl_listAll" class="table table-bordered tbl-info" cellspacing="0" width="100%">
			<colgroup>
				<col width="*">
				<col width="*">
				<col width="*">
				<col width="1000px">
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>조회고유코드</th>
					<th>최초조회시간</th>
					<th width="1000px">
						<table id="tbl_listTitle" class="table table-bordered tbl-info" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th width="600">상품정보
									<table class="table table-bordered tbl-info" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th width="150">금융사</th>
												<th width="150">금융사코드</th>
												<th width="150">상품코드</th>
												<th width="150">상품명</th>
											</tr>
										</thead>
									</table>
									</th>
									<th width="400">금리 한도
										<table class="table table-bordered tbl-info" cellspacing="0" width="100%">
											<thead>
												<tr>
													<th width="100">대출기간</th>
													<th width="150">적용금리</th>
													<th width="150">대출한도</th>
												</tr>
											</thead>
										</table>
									</th>
								</tr>
							</thead>
						</table>
					</th>
					<!-- <th>버튼</th> -->
				</tr>
			</thead>
			<tbody>
				<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty linkedMap}">
					<tr>
						<td colspan="7" height="100" align="center">검색결과가 없습니다</td>
					</tr>
				</c:if>
				<c:forEach var="List" items="${linkedMap}" varStatus="status">
					<c:choose>
						<c:when test="${status.count%2==0}">
							<c:set var="class_string" value="active" />
						</c:when>
						<c:otherwise>
							<c:set var="class_string" value="" />
						</c:otherwise>
					</c:choose>
					<tr class="${class_string}"><!-- style="cursor: pointer;"-->
						<td>${status.index+1+(pagedList.page-1)*pagedList.pageSize}</td>   <!-- 순번 -->
						<td>${List.key.no_bunch}</td><!-- 다발코드 -->
						<td>${List.key.min_dt_frt}</td><!-- 최초조회시간 -->
						<td>
							<table id="tbl_listGoods" class="table table-bordered tbl-info" cellspacing="0" width="100%">
								<tbody>
									<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
									<c:if test="${empty List.value}">
										<tr>
											<td colspan="2" align="center">검색결과가 없습니다.1</td>
										</tr>
									</c:if>
									<c:forEach var="listGoods" items="${List.value}"
										varStatus="status">
										<c:choose>
											<c:when test="${status.count%2==0}">
												<c:set var="class_string" value="active" />
											</c:when>
											<c:otherwise>
												<c:set var="class_string" value="" />
											</c:otherwise>
										</c:choose>
										<c:choose>
										<c:when test="${!empty listGoods.key}">
											<tr class="${class_string}">
												<td width="150">${ufn:getNmFc(listGoods.key.cd_fc)}</td><!-- 금융사 -->
												<td width="150">${listGoods.key.cd_fc}</td><!-- 금융사코드 -->
												<td width="150">${listGoods.key.cd_goods}</td><!-- 상품코드 -->
												<td width="150">${ufn:getGoodsDetail(listGoods.key.cd_goods, '')}</td><!-- 상품명 -->
												<td width="400">
													<table id="tbl_listGoodsAmt" class="table table-bordered tbl-info" cellspacing="0" width="100%">
														<tbody>
															<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
															<c:if test="${empty listGoods.value}">
																<tr>
																	<td colspan="2" align="center">검색결과가 없습니다.2</td>
																</tr>
															</c:if>

															<c:forEach var="listGoodsAmt" items="${listGoods.value}" varStatus="status">
																<c:choose>
																	<c:when test="${status.count%2==0}">
																		<c:set var="class_string" value="active" />
																	</c:when>
																	<c:otherwise>
																		<c:set var="class_string" value="" />
																	</c:otherwise>
																</c:choose>
																<c:choose>
																<c:when test="${!empty listGoodsAmt.year_term}">
																	<tr class="${class_string}">
																		<td width="100">${listGoodsAmt.year_term}년</td>   <!-- 대출기간 -->
																		<td width="150">${listGoodsAmt.rto_loan}%</td>   <!-- 적용금리 -->
																		<td width="150">${ufn:formatNumber(listGoodsAmt.amt_limit)}원</td>   <!-- 대출한도 -->
																	</tr>
																</c:when>
																</c:choose>
															</c:forEach>
														</tbody>
													</table>
												</td>
											</tr>
										</c:when>
										</c:choose>
									</c:forEach>
								</tbody>
							</table>
						</td>
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

