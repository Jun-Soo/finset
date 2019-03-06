<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	// SelectPicker
	
	$('.selectpicker').selectpicker();
	
// 	window.setupValidateForm( frmGoodsDetails );
});

</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<div class="panel-default toggle-panel">
<input type="hidden" id="s_value">
	<div class="panel-collapse toggle-cont">
			<input type="hidden" name="nm_request_form" id="nm_request_form" value="${productInfo.nm_request_form}">
			<input type="hidden" name="url_request" id="url_request" value="${productInfo.url_request}">
			<input type="hidden" name="id_request" id="id_request" value="${productInfo.id_request}">
			
			<!--신용 start  -->
				<div class="panel panel-primary">
					<div class="panel-heading">상품 상세항목</div>	
					<div class="panel-collapse">
						<table class="table table-classic">
		 					<colgroup>
								<col width="30%">
								<col width="70%">
							</colgroup>
							<tbody>
								<tr>
									<th>키워드</th>
									<td>
										<input type="text" class="form-control" id="keyword_list" name="keyword_list" value="${goodsbankInfo.keyword_list}" validate="required; label:키워드;" maxlength="70"/>
									</td>
								</tr> 
								<tr>
									<th>신청방식</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_apply_type", "cd_apply_type", "checkbox", goodsbankInfo.cd_apply_type, 0)}
									</td>
								</tr>
								<tr>
									<th>상환방식</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_type_pay", "cd_type_pay", "checkbox", goodsbankInfo.cd_type_pay, 0)}
									</td>
								</tr>
								<tr>
									<th>거래방식</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_trade_type", "cd_trade_type", "checkbox", goodsbankInfo.cd_trade_type, 0)}
									</td>
								</tr>
								<tr>
									<th>무서류</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("yn_send_docu", "yn_send_docu", "radio", goodsbankInfo.yn_send_docu, 0)}
									</td>
								</tr>
								<tr>
									<th>무방문</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("yn_visit", "yn_visit", "radio", goodsbankInfo.yn_visit, 0)}
									</td>
								</tr>
								<tr>
									<th>금리/한도 확인가능</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("yn_srch_ratio_limit", "yn_srch_ratio_limit", "radio", goodsbankInfo.yn_srch_ratio_limit, 0)}
									</td>
								</tr>
								<tr>
									<th>중도상환 수수료 없음</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("yn_erly_rpay_fee", "yn_erly_rpay_fee", "radio", goodsbankInfo.yn_erly_rpay_fee, 0)}
									</td>
								</tr>
								<tr>
									<th>대상고객</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_target_cust", "cd_target_cust", "checkbox", goodsbankInfo.cd_target_cust, 0)}
									</td>
								</tr>
								<tr>
									<th>금리방식</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_ratio_type", "cd_ratio_type", "checkbox", goodsbankInfo.cd_ratio_type, 0)}
									</td>
								</tr>
								<tr>
									<th>주택종류</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_house_type", "cd_collateral_house_type", "checkbox", goodsbankInfo.cd_collateral_house_type, 0)}
									</td>
								</tr>
								<tr>
									<th>자금용도</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_loan_use", "cd_collateral_loan_use", "checkbox", goodsbankInfo.cd_collateral_loan_use, 0)}
									</td>
								</tr>
								<tr>
									<th>주택면적(전용면적)</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_area", "cd_area", "checkbox", goodsbankInfo.cd_area, 0)}
									</td>
								</tr>
								<tr>
									<th>거치방식</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_defer", "cd_defer", "radio", goodsbankInfo.cd_defer, 0)}
									</td>
								</tr>
								
								<tr>
									<th>대상구분</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_object", "cd_object", "checkbox", goodsbankInfo.cd_object, 0)}
									</td>
								</tr>
								
								<tr>
									<th>후순위 가능여부</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("yn_post_ranking", "yn_post_ranking", "radio", goodsbankInfo.yn_post_ranking, 0)}
									</td>
								</tr>
								<tr>
									<th>실행시간</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_time_exec", "cd_time_exec", "radio", goodsbankInfo.cd_time_exec, 0)}
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>	
		<!--신용 end  -->
	</div>
</div>