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
<div class="panel panel-default toggle-panel">
<input type="hidden" id="s_value">
	<div class="panel-collapse toggle-cont">
			<input type="hidden" name="nm_request_form" id="nm_request_form" value="${productInfo.nm_request_form}">
			<input type="hidden" name="url_request" id="url_request" value="${productInfo.url_request}">
			<input type="hidden" name="id_request" id="id_request" value="${productInfo.id_request}">
			
			<input type="hidden" name="cd_trade_type" id="cd_trade_type" value="">
			<input type="hidden" name="cd_term_loan" id="cd_term_loan" value="">
			<input type="hidden" name="cd_time_exec" id="cd_time_exec" value="">
			<input type="hidden" name="yn_send_docu" id="yn_send_docu" value="">
			<input type="hidden" name="yn_visit" id="yn_visit" value="">
			<input type="hidden" name="yn_erly_rpay_fee" id="yn_erly_rpay_fee" value="">
			<input type="hidden" name="yn_srch_ratio_limit" id="yn_srch_ratio_limit" value="">
			<input type="hidden" name="cd_category_goods" id="cd_category_goods" value="">
			
			
			
			<!--조건검색 주택담보 start  -->
			<div class="panel panel-primary">
				<div class="panel-heading">조건검색 담보항목</div>		
				<div class="panel-collapse">
					<table class="table table-classic">
		 					<colgroup>
								<col width="30%">
								<col width="70%">
							</colgroup>
							<tbody> 
								<tr>
									<th>신청방식</th>
									<td>
										<c:choose>
											<c:when test="${empty goodsInfo.cd_apply_type }">
												${ufn:makeRadioAndCheckBoxs("cd_apply_type", "cd_apply_type", "checkbox", 1, 0)}
											</c:when>
											<c:otherwise>
												${ufn:makeRadioAndCheckBoxs("cd_apply_type", "cd_apply_type", "checkbox", goodsInfo.cd_apply_type, 0)}	
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								
								<tr>
									<th>금리방식</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_ratio_type", "cd_ratio_type", "checkbox", goodsInfo.cd_ratio_type, 0)}
									</td>
								</tr>
								
<!-- 								<tr> -->
<!-- 									<th>대출기간</th> -->
<!-- 									<td> -->
<%-- 										${ufn:makeRadioAndCheckBoxs("cd_term_loan", "cd_term_loan", "checkbox", goodsInfo.cd_term_loan, 0)} --%>
<!-- 									</td> -->
<!-- 								</tr> -->
								
								<tr>
									<th>상환방식</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_type_pay", "cd_type_pay", "checkbox", goodsInfo.cd_type_pay, 0)}
									</td>
								</tr>
								
								<tr>
									<th>주택종류</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_collateral_house_type", "cd_collateral_house_type", "checkbox", goodsInfo.cd_collateral_house_type, 0)}
									</td>
								</tr>
								
								<tr>
									<th>자금용도</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_collateral_loan_use", "cd_collateral_loan_use", "checkbox", goodsInfo.cd_collateral_loan_use, 0)}
									</td>
								</tr>
								
								<tr>
									<th>주택면적(전용면적)</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_area", "cd_area", "checkbox", goodsInfo.cd_area, 0)}
									</td>
								</tr>
								
								<tr>
									<th>거치방식</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_defer", "cd_defer", "radio", goodsInfo.cd_defer, 0)}
									</td>
								</tr>
								
								<tr>
									<th>대상구분</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_object", "cd_object", "checkbox", goodsInfo.cd_object, 0)}
									</td>
								</tr>
								
								<tr>
									<th>후순위가능여부</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("yn_post_ranking", "yn_post_ranking", "radio", goodsInfo.yn_post_ranking, 0)}
									</td>
								</tr>
							</tbody>
						</table>
				</div>
			</div>	
			<!--조건검색 주택담보 end  -->
	</div>
</div>

