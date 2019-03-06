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
			
			<input type="hidden" name="cd_collateral_house_type" id="cd_collateral_house_type" value="">
			<input type="hidden" name="cd_collateral_loan_use" id="cd_collateral_loan_use" value="">
			<input type="hidden" name="cd_area" id="cd_area" value="">
			<input type="hidden" name="cd_defer" id="cd_defer" value="">
			<input type="hidden" name="cd_object" id="cd_object" value="">
			<input type="hidden" name="yn_post_ranking" id="yn_post_ranking" value="">
			
			<!--신용 start  -->
				<div class="panel panel-primary">
					<div class="panel-heading">조건검색 신용항목</div>	
					<div class="panel-collapse">
						<table class="table table-classic">
		 					<colgroup>
<!-- 								<col width="20%"> -->
								<col width="30%">
								<col width="70%">
							</colgroup>
							<tbody> 
								<tr>
									<th>신청방식</th>
									<td>
										<c:choose>
											<c:when test="${empty goodsInfo.cd_apply_type }">
												${ufn:makeRadioAndCheckBoxs("cd_apply_type", "cd_apply_type", "checkbox", "01,02,03,04", 0)}
											</c:when>
											<c:otherwise>
												${ufn:makeRadioAndCheckBoxs("cd_apply_type", "cd_apply_type", "checkbox", goodsInfo.cd_apply_type, 0)}	
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								
								<tr>
									<th>거래방식</th>
									<td>
										<c:choose>
											<c:when test="${empty goodsInfo.cd_trade_type }">
												${ufn:makeRadioAndCheckBoxs("cd_trade_type", "cd_trade_type", "checkbox", "01", 0)}
											</c:when>
											<c:otherwise>
												${ufn:makeRadioAndCheckBoxs("cd_trade_type", "cd_trade_type", "checkbox", goodsInfo.cd_trade_type, 0)}
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
									
								<tr>
									<th>금리방식</th>
									<td>
										<c:choose>
											<c:when test="${empty goodsInfo.cd_ratio_type }">
												${ufn:makeRadioAndCheckBoxs("cd_ratio_type", "cd_ratio_type", "checkbox", "01,02", 0)}
											</c:when>
											<c:otherwise>
												${ufn:makeRadioAndCheckBoxs("cd_ratio_type", "cd_ratio_type", "checkbox", goodsInfo.cd_ratio_type, 0)}
											</c:otherwise>
										</c:choose>
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
								
								<%-- <tr>
									<th>지급방식</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_time_exec", "cd_time_exec", "checkbox", goodsInfo.cd_time_exec, 0)}
									</td>
								</tr> --%>
								
								<tr>
									<th>무서류</th>
									<td>
										<c:choose>
											<c:when test="${empty goodsInfo.yn_send_docu }">
												${ufn:makeRadioAndCheckBoxs("yn_send_docu", "yn_send_docu", "radio", "01", 0)}
											</c:when>
											<c:otherwise>
												${ufn:makeRadioAndCheckBoxs("yn_send_docu", "yn_send_docu", "radio", goodsInfo.yn_send_docu, 0)}
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								
								<tr>
									<th>무방문</th>
									<td>
										<c:choose>
											<c:when test="${empty goodsInfo.yn_visit }">
												${ufn:makeRadioAndCheckBoxs("yn_visit", "yn_visit", "radio", "01", 0)}
											</c:when>
											<c:otherwise>
												${ufn:makeRadioAndCheckBoxs("yn_visit", "yn_visit", "radio", goodsInfo.yn_visit, 0)}
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								
								<tr>
									<th>중도상환수수료없음</th>
									<td>
										<c:choose>
											<c:when test="${empty goodsInfo.yn_erly_rpay_fee }">
												${ufn:makeRadioAndCheckBoxs("yn_erly_rpay_fee", "yn_erly_rpay_fee", "radio", "02", 0)}
											</c:when>
											<c:otherwise>
												${ufn:makeRadioAndCheckBoxs("yn_erly_rpay_fee", "yn_erly_rpay_fee", "radio", goodsInfo.yn_erly_rpay_fee, 0)}
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								
								<%-- <tr>
									<th>금리/한도 확인가능</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("yn_srch_ratio_limit", "yn_srch_ratio_limit", "radio", goodsInfo.yn_srch_ratio_limit, 0)}
									</td>
								</tr>
								
								<tr>
									<th>상품군 분류</th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_category_goods", "cd_category_goods", "checkbox", goodsInfo.cd_category_goods, 0)}
									</td>
								</tr> --%>
							</tbody>
						</table>
					</div>
				</div>	
		<!--신용 end  -->
	</div>
</div>