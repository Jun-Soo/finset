<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	// SelectPicker
	$('.selectpicker').selectpicker();
});

</script>

	
	<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">상품 상세정보</a>
		</h3>
		
	</div>
	<div class="panel-collapse toggle-cont">
		
			<input type="hidden" name="nm_request_form" id="nm_request_form" value="${goodsInfo.nm_request_form}">
			<input type="hidden" name="url_request" id="url_request" value="${goodsInfo.url_request}">
			<input type="hidden" name="id_request" id="id_request" value="${goodsInfo.id_request}">
			<div class="panel-collapse">
				<table class="table table-classic">
 					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody> 
						<c:choose>
							<c:when test="${empty goodsInfo.cd_goods}">
								<tr>
									<th>상품코드</th>
									<td colspan="3">
										${goodsInfo.cd_goods}
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="cd_goods" value="${goodsInfo.cd_goods}"/>
							</c:otherwise>
						</c:choose>
						<tr>
							<th>상품명</th>
							<td>
								${goodsInfo.nm_goods}
							</td>
							<th>변동요인</th> <!--getCodeName  -->
							<td>
								${ufn:getCodeName("cd_chg_factor",goodsInfo.cd_chg_factor)} 
							</td>
						</tr>
						<tr>
							<th>상품구분</th>
							<td>
								${ufn:getCodeName("cd_goods_type",goodsInfo.cd_goods_type)}
							</td>
							<th>금융사</th>
							<td>
									${ufn:getCodeName("cd_fc",goodsInfo.cd_fc)}
							</td>
						</tr>
						<tr>
							<th>요약설명</th>
							<td colspan="3">
								${goodsInfo.summary}
							</td>
						</tr>
 						<tr>
							<th>CI금융사 로고 파일</th>
							<td colspan="3">
								<c:if test="${!empty goodsInfo.path_fileCi}">
									<img src="<c:url value='/fincorp/getFinCorpIcon.crz'/>?file_name=${goodsInfo.path_fileCi}" alt="로고" />
								</c:if>
							</td>
							<th></th>
							<td>
							</td>
						</tr>
						<tr>
							<th>상세설명</th>
							<td colspan="3">
								${goodsInfo.content}
							</td>
						</tr>
						<tr>
							<th>사용 여부</th>
							<td colspan="3">
								${ufn:getCodeName("yn_yes", goodsInfo.yn_use)}
							</td>
						</tr>
						<tr>
							<th>MUE(단위:천원)</th>
							<td colspan="3">
								<table id="ctable" class="table-striped">
									<c:set var="grade" value="1" />
									<c:set var="tmp" value="0" />
									<c:set var="gradeMue" value="1" />
									<c:set var="doneLoop" value="false" />
								    <!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
									<c:if test="${empty mueList}">
									<tr>
										<td>데이터가 존재하지 않습니다.</td>	
									</tr>
									</c:if>
									<c:forEach var="mueList" items="${mueList}">
										<c:choose>
											<c:when test="${grade == mueList.grade_year_income && tmp == 0}">
												<tr>
													<c:set var="gradeMue" value="${mueList.grade_mue}" />
													<td><input type="hidden" name="grade_year_income_${grade}" value="${mueList.grade_year_income}" size="7" class="form-control"/></td>	
													<td><p>min</p><input type="text" name="min_${grade}" value="${mueList.min_year_income}" size="7" class="form-control" readonly="readonly"/></td>
													<td><p>max</p><input type="text" name="max_${grade}" value="${mueList.max_year_income}" size="7" class="form-control" readonly="readonly"/></td>
													<td><input type="hidden" name="grade_mue_${gradeMue}" value="${mueList.grade_mue}" size="7" class="form-control"/>
														<p>${gradeMue}등급</p><input type="text" name="mue_${grade}_${gradeMue}" value="${mueList.mue}" size="7" class="form-control" readonly="readonly"/>
													</td>		
													<c:set var="tmp" value="1" />
											</c:when>
											<c:when test="${grade == mueList.grade_year_income && tmp == 1}">
													<c:set var="gradeMue" value="${mueList.grade_mue}" /> 
													<td><input type="hidden" name="grade_mue_${gradeMue}" value="${mueList.grade_mue}" size="7" class="form-control"/>
														<p>${gradeMue}등급</p><input type="text" name="mue_${grade}_${gradeMue}" value="${mueList.mue}" size="7" class="form-control" readonly="readonly"/>
													</td>	
											</c:when>
											<c:when test="${grade < mueList.grade_year_income}">
												</tr>
												<tr>
													<c:set var="gradeMue" value="${mueList.grade_mue}" />
													<c:set var="grade" value="${mueList.grade_year_income}" />
													<td><input type="hidden" name="grade_year_income_${grade}" value="${mueList.grade_year_income}" size="7" class="form-control"/></td>
													<td><input type="text" name="min_${grade}" value="${mueList.min_year_income}" size="7" class="form-control" readonly="readonly"/></td>
													<td><input type="text" name="max_${grade}" value="${mueList.max_year_income}" size="7" class="form-control" readonly="readonly"/></td>
													<td><input type="hidden" name="grade_mue_${gradeMue}" value="${mueList.grade_mue}" size="7" class="form-control"/>
														<input type="text" name="mue_${grade}_${gradeMue}" value="${mueList.mue}" size="7" class="form-control" readonly="readonly"/>
													</td>	
													<c:set var="tmp" value="2" />
											</c:when>
											<c:when test="${grade == mueList.grade_year_income && tmp == 2}">
													<c:set var="gradeMue" value="${mueList.grade_mue}" /> 
													<td><input type="hidden" name="grade_mue_${gradeMue}" value="${mueList.grade_mue}" size="7" class="form-control"/>
														<input type="text" name="mue_${grade}_${gradeMue}" value="${mueList.mue}" size="7" class="form-control" readonly="readonly"/>
													</td>	
											</c:when>
										</c:choose>
									</c:forEach>
									
								</table>
							</td>
						</tr>
						<tr>
							<th>신용</th>
							<td colspan="3">
								<table class="table table-classic">
									<colgroup>
										<col width="25%">
										<col width="25%">
										<col width="25%">
										<col width="25%">
									</colgroup>
									<tbody>
										<tr>
											<th>신용등급</th>
											<th>PI등급 (채무건전성등급)</th>
											<th>SP등급 (서브프라임 등급/대부등급)</th>
											<th>DEP등급 (채무건전성 등급)</th>
										</tr>
										<tr>
											<td>${goodsInfo.score_cb}</td>
											<td>${goodsInfo.grade_pi}</td>
											<td>${goodsInfo.grade_sp}</td>
											<td>${goodsInfo.grade_dep}</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th>대출금리(예:26.0)</th>
							<td colspan="3">
								<table class="table table-classic">
									<colgroup>
										<col width="10%">
										<col width="10%">
										<col width="10%">
										<col width="10%">
										<col width="10%">
										<col width="10%">
										<col width="10%">
										<col width="10%">
										<col width="10%">
										<col width="10%">
									</colgroup>
									<tbody>
										<tr>
											<th>1등급</th>
											<th>2등급</th>
											<th>3등급</th>
											<th>4등급</th>
											<th>5등급</th>
											<th>6등급</th>
											<th>7등급</th>
											<th>8등급</th>
											<th>9등급</th>
											<th>10등급</th>
										</tr>
										<tr>
											<td>${goodsRtoInfo.rto_loan1}%</td>
											<td>${goodsRtoInfo.rto_loan2}%</td>
											<td>${goodsRtoInfo.rto_loan3}%</td>
											<td>${goodsRtoInfo.rto_loan4}%</td>
											<td>${goodsRtoInfo.rto_loan5}%</td>
											<td>${goodsRtoInfo.rto_loan6}%</td>
											<td>${goodsRtoInfo.rto_loan7}%</td>
											<td>${goodsRtoInfo.rto_loan8}%</td>
											<td>${goodsRtoInfo.rto_loan9}%</td>
											<td>${goodsRtoInfo.rto_loan10}%</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th>상환방식</th>
							<td>
								${goodsInfo.method_repay}
							</td>
							<th>중도상환수수료</th>
							<td>
								${goodsInfo.method_mid_repay}
							</td>
						</tr>
						<tr>
							<th>나이(제한없으면:0)</th>
							<td>
								<table class="table table-classic">
									<colgroup>
										<col width="10%">
										<col width="10%">
										<col width="80%">
									</colgroup>
									<tbody>
										<tr>
											<th>이상</th>
											<th>이하</th>
											<th>설명</th>
										</tr>
										<tr>					
											<td>${goodsInfo.age_min}</td>
											<td>${goodsInfo.age_max}</td>
											<td>${goodsInfo.age_desc}</td>
										</tr>
									</tbody>
								</table>
							</td>
							<th>대출한도(단위:원)</th>
							<td>
								<table class="table table-classic">
									<colgroup>
										<col width="50%">
										<col width="50%">
									</colgroup>
									<tbody>
										<tr>
											<th>MIN</th>
											<th>MAX</th>
										</tr>
										<tr>					
											<td>${goodsInfo.amt_limit_loan_min}</td>
											<td>${goodsInfo.amt_limit_loan_max}</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th>재직기간(개월수)</th>
							<td>
								${goodsInfo.job_term_month}
							</td>
							<th>납입일</th>
							<td>
								${goodsInfo.day_payment}
							</td>
						</tr>
						<tr>
							<th>소득제한(단위: 천원)</th>
							<td>
								${goodsInfo.amt_limit_income}
							</td>
							<th>연소득채무비율</th>
							<td>
								${goodsInfo.rto_year_income_debt}
							</td>
						</tr>
						<tr>
							<th><span>고용형태 (*허용된 고용형태만 체크)</span></th>
							<td colspan="3">
								${ufn:makeRadioAndCheckBoxs("cd_employ_type", "cd_employ_type", "checkbox", goodsInfo.cd_employ_type, 0)}
							</td>
						</tr>
						<tr>
							<th>직군선택 (*허용된 직군만 체크)</th>
							<td colspan="3">
								${ufn:makeRadioAndCheckBoxs("cd_job_class", "cd50_goods_job", "checkbox", goodsInfo.cd50_goods_job, 0)}
							</td>
						</tr>
						<tr>
							<th>CUTOFF DTI (예:26.0)</th>
							<td colspan="3">
								${goodsInfo.rto_cutoff_dti}
							</td>
						</tr>
						<tr>
							<th>기대출 과다자 (2금융권)</th>
							<td>
								${goodsInfo.cnt_exceed_2nd}
							</td>
							<th>기대출 과다자 (총대출건수)</th>
							<td>
								${goodsInfo.cnt_exceed_all_cnt_loan}
							</td>
						</tr>
						<tr>
							<th>기대출 과다자 (저축은행건수)</th>
							<td>
								${goodsInfo.cnt_exceed_saving_bank}
							</td>
							<th>최근 6개월 신용등급 하락</th>
							<td>
								${goodsInfo.grade_last6month}
							</td>
						</tr>
						<tr>
							<th>현금서비스 건수</th>
							<td>
								${goodsInfo.cnt_cash_service}
							</td>
							<th>현금서비스 금액</th>
							<td>
								${goodsInfo.amt_cash_service}
							</td>
						</tr>
						<tr>
							<th>등록자</th>
							<td>
								${ufn:getWorkerInfo(goodsInfo.id_frt,'NM')}
							</td>
							<th>등록일</th>
							<td>${goodsInfo.dt_frt}</td>
						</tr>
					</tbody>
				</table>
			</div>
		
	</div>
</div>

<!-- <div class="modal-footer">
</div> -->