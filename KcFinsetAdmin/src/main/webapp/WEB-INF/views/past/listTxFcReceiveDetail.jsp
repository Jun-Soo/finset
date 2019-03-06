<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<div class="panel panel-primary">
	<div class="panel-collapse">
		<!-- List -->
		<table id="tbl_listCont" class="table table-bordered tbl-info" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>날짜</th>
					<th>금융사</th>
					<th>금융사코드</th>
					<th>상품코드</th>
					<th>상품명</th>
					<th>금리 한도
						<table id="tbl_listCont" class="table table-bordered tbl-info" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>대출기간</th>
									<th>월불입금</th>
									<th>적용금리</th>
									<th>대출금액 지수</th>
									<th>DTI</th>
									<th>대출한도</th>
								</tr>
							</thead>
						</table>
					</th>
				</tr>
			</thead>
			<tbody>
				<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
				<c:if test="${empty linkedMap}">
					<tr>
						<td colspan="6" height="100" align="center">검색결과가 없습니다</td>
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
							<c:set var="error" value="" />

							<tr class="${class_string}"><!-- style="cursor: pointer;">
								<!-- 날짜 -->
								<td>${List.key.dt_frt}</td>
								<!-- 금융사 -->
								<td>${ufn:getCodeName('cd_apply_comp', List.key.cd_apply_comp)}</td>
								<!-- 금융사코드 -->
								<td>${List.key.cd_apply_comp}</td>
								<!-- 상품코드 -->
								<td>${List.key.cd_goods}</td>
								<!-- 상품명 -->
								<td>${ufn:getGoodsDetail(List.key.cd_goods, '')}</td>						   
								<td>
									<table id="tbl_listCont" class="table table-bordered tbl-info"
										cellspacing="0" width="100%">
										<tbody>
											<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
											<c:if test="${empty List.value}">
												<tr>
													<td colspan="2" align="center">검색결과가 없습니다.1</td>
												</tr>
											</c:if>
											<c:forEach var="listItem" items="${List.value}"
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
													<c:when test="${not empty listItem.deny_desc}">
														<tr class="${class_string}">
															<td>${listItem.deny_desc}</td>
														</tr>
													</c:when>
												</c:choose>
											</c:forEach>
										</tbody>
									</table>

								</td>
							</tr>
					<c:set var="cd_apply_comp" value="${List.key.cd_apply_comp}" />
					<c:set var="cd_goods" value="${List.key.cd_goods}" />
				</c:forEach>
			</tbody>
		</table>
		<!-- //List -->
	</div>
	<div class="panel-footer">
		<span class="pull-left"> </span>
	</div>
</div>