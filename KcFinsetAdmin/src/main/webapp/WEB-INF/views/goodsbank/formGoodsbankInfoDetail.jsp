<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">상품 상세정보</a>
		</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-primary btn-xs" onclick="initForm();">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			<c:choose>
				<c:when test="${empty cooconGoodsBankInfo.cd_non_goods}">
					<button type="button" class="btn btn-default btn-xs" id="createGoodsbankInfoDetails">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 추가
					</button>
				</c:when>
				<c:otherwise>
					<a role="button" class="btn btn-default btn-xs" id="procGoodsbankInfoDetails">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
					</a>
					<button type="button" class="btn btn-warning btn-xs" id="deleteGoodsbankInfoDetails">
						<span class="glyphicon" aria-hidden="true"></span> 삭제
					</button>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
	<div class="panel-collapse toggle-cont">
		<form name="frmGoodsbankInfoDetails" id="frmGoodsbankInfoDetails">
			<div class="panel panel-primary">
				<table class="table table-classic">
 					<colgroup>
						<col width="16%">
						<col width="16%">
						<col width="16%">
						<col width="16%">
						<col width="16%">
						<col width="16%">
					</colgroup>
					<tbody>
						<tr>
							<th>금융사업권</th>
							<c:choose>
							<c:when test="${ workerVO.cd_template_group eq '12' }">
								<td colspan="2">
									<select id="cd_fin_temp" name="cd_fin_temp" class="selectpicker" validate="label:업권코드;" disabled="disabled">
											${ufn:makeOptions("cd_fin","업권코드", workerVO.cd_fin)}
									</select> 
									<input type="hidden" class="form-control" id="cd_fin" name="cd_fin" validate="required; label:업권코드;" value="${workerVO.cd_fin}"/>
								</td> 
								<th>금융사코드</th>
								<td colspan="2"> 
									<select id="cd_fc_temp" name="cd_fc_temp" class="selectpicker" data-style="btn-primary" disabled="disabled">
										${ufn:makeFincorpOptions('금융기관명', workerVO.cd_fc, 'NM', 'N')}
									</select>
									<input type="hidden" class="form-control" id="cd_fc" name="cd_fc" validate="required; label:금융사코드;" value="${workerVO.cd_fc}"/>
									<input type="hidden" class="form-control" id="nm_fc" name="nm_fc" validate="label:금융사명;" value="${ufn:getNmFc(workerVO.cd_fc)}"/>
								</td>
							</c:when>  
							<c:otherwise>
								<td colspan="2">
									<select id="cd_fin" name="cd_fin" class="selectpicker" validate="required; label:업권코드;">
											${ufn:makeOptions("cd_fin","업권코드", cooconGoodsBankInfo.cd_fin)}
									</select>
								</td> 
								<th>금융사코드</th>
								<td colspan="2">
									<select id="cd_fc" name="cd_fc" class="selectpicker" validate="required; label:금융사코드;" data-style="btn-primary">
										${ufn:makeFincorpOptions('금융기관명', cooconGoodsBankInfo.cd_fc, 'NM', 'N')}
									</select>
									<input type="hidden" class="form-control" id="nm_fc" name="nm_fc" validate="label:금융사명;" value="${ufn:getNmFc(cooconGoodsBankInfo.cd_fc)}"/>
								</td>
							</c:otherwise>
						</c:choose>		
						</tr>
						<tr>
							<th>상품명</th>
							<td colspan="2">
								<input type="text" class="form-control" id="nm_goods" name="nm_goods"  value="${cooconGoodsBankInfo.nm_goods}" validate="required;label:상품명;" maxlength="10" />
							</td>
							<th>상품코드</th>
							<td colspan="2">
								<c:choose>
									<c:when test="${workerVO.cd_template_group eq '12'}">
										<input type="text" class="form-control" id="cd_non_goods_temp" name="cd_non_goods_temp" value="${cooconGoodsBankInfo.cd_non_goods }" validate="label:상품코드;" disabled="disabled"/>
										<input type="hidden" class="form-control" id="cd_non_goods" name="cd_non_goods" value="${cooconGoodsBankInfo.cd_non_goods }"/>
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control" id="cd_non_goods" name="cd_non_goods" value="${cooconGoodsBankInfo.cd_non_goods }" validate="label:상품코드;"/>
									</c:otherwise>
								</c:choose>
								<!-- <button type="button" class="btn btn-success btn-xs" onclick="duplicationChk();">
									<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 중복확인
								</button> -->
							</td>
						</tr>
						<tr>
							<th>표시여부</th>
							<td colspan="2">
								<select id="yn_use" name="yn_use" class="selectpicker" validate="required; label:표시여부;">
								${ufn:makeOptions("yn_use","선택", cooconGoodsBankInfo.yn_use)}
								</select>
							</td>
							<th>금융사링크</th>
							<td colspan="2">
								<input type="text" class="form-control" id="fc_link" name="fc_link" value="${cooconGoodsBankInfo.fc_link}" validate="label:금융사링크;maxbyte:200;" maxlength="200"/>
							</td>
						</tr>
						
						<tr>
							<th>대분류</th>
							<td colspan="2">
								<select id="cd_goods_class_l" name="cd_goods_class_l" class="selectpicker" validate="required; label:대분류;" style="background-color: #FCF7C2;" >
									${ufn:makeOptions("cd_goods_l","선택", cooconGoodsBankInfo.cd_goods_class_l)}
								</select>
							</td>
							<th>중분류</th>
							<td colspan="2">
								<select name="cd_goods_class_m" id="cd_goods_class_m" class="selectpicker" validate="required; label:중분류;" style="background-color: #FCF7C2;">
									${ufn:makeOptions("cd_goods_m","선택",cooconGoodsBankInfo.cd_goods_class_m )}
								</select>
							</td>
							<!-- 
							<th>소분류</th>
							<td>
								<select id="cd_goods_class_s" name="cd_goods_class_s" class="selectpicker" disabled="disabled"  validate="label:소분류;" value="${cooconGoodsBankInfo.cd_goods_class_s}">
									<option value=""> 선택 </option>
								</select>
							</td>
							 -->
						</tr>
						<tr>
							<th><span>최소금리(%)</span></th>
							<td colspan="2">
								<input type="text" class="form-control" id="rto_interest_from" name="rto_interest_from" placeholder="Number" value="${cooconGoodsBankInfo.rto_interest_from}" validate="label:최소금리;" maxlength="10"/>
							</td>
							<th><span>최대금리(%)</span></th>
							<td colspan="2">
								<input type="text" class="form-control" id="rto_interest_to" name="rto_interest_to" placeholder="Number" value="${cooconGoodsBankInfo.rto_interest_to}" validate="label:최대금리;" maxlength="10"/>
							</td>	
						</tr>	
						<tr>
							<th>최대한도(만원)</th>
							<td colspan="2">
								<input type="text" class="form-control" id="desc_max_limit" name="desc_max_limit" placeholder="Number" value="${cooconGoodsBankInfo.desc_max_limit}" validate="label:최대한도;" maxlength="10"/>
							</td>
							<th>최대기간(년)</th>
							<td colspan="2">
								<input type="text" class="form-control" id="max_loan_term" name="max_loan_term" placeholder="Number" value="${cooconGoodsBankInfo.max_loan_term}" validate="label:최대기간;" maxlength="10"/>
							</td> 
							<!-- 
							<th>연장기간(년)</th>
							<td colspan="2">
								<input type="text" class="form-control" id="renew_max_term" name="renew_max_term" placeholder="Number" value="${cooconGoodsBankInfo.renew_max_term}" validate="label:연장기간;" maxlength="10"/>
							</td>
							 -->
						</tr>
						<!-- 
						<tr>
							<th>최소기간(년)</th>
							<td colspan="2">
								<input type="text" class="form-control" id="min_loan_term" name="min_loan_term" placeholder="Number" value="${cooconGoodsBankInfo.min_loan_term}" validate="label:최초기간;" maxlength="10"/>
							</td>
						</tr>
						-->
						<tr>
							<th>중도상환해약금율(%)</th>
							<td colspan="2">
								<input type="text" class="form-control" id="rto_cancel" name="rto_cancel" placeholder="Number" value="${cooconGoodsBankInfo.rto_cancel}" validate="label:중도상환해약금율;" maxlength="10"/>
							</td> 
							<th>수수료적용기간(년)</th>
							<td colspan="2">
								<input type="text" class="form-control" id="year_commission" name="year_commission" placeholder="Number" value="${cooconGoodsBankInfo.year_commission}" validate="label:수수료적용기간;" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>이자부과시기</th>
							<td colspan="5">
								<input type="text" class="form-control" id="time_interest" name="time_interest" value="${cooconGoodsBankInfo.time_interest}" validate="label:이자부과시기;" maxlength="30"/>
							</td> 
						</tr>
						<tr>
							<th>상품특징</th>
							<td colspan="5">
								<textarea class="form-control w100" id="desc_feature" name="desc_feature" validate="required; label:상품특징;" maxlength="2000">${cooconGoodsBankInfo.desc_feature}</textarea>
							</td> 
						</tr>
						<tr>
							<th>키워드</th>
							<td colspan="5">
								<input type="text" class="form-control" id="keyword_list" name="keyword_list" value="${cooconGoodsBankInfo.keyword_list}" validate="required; label:키워드;" maxlength="70"/>
							</td>
						</tr> 
					</tbody>
				</table>
			</div>
			
			<!--상세항목 start  -->
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
								<th>신청방식</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_apply_type", "cd_apply_type", "checkbox", cooconGoodsBankInfo.cd_apply_type, 0)}
								</td>
							</tr>
							<tr>
								<th>상환방식</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_type_pay", "cd_type_pay", "checkbox", cooconGoodsBankInfo.cd_type_pay, 0)}
								</td>
							</tr>
							<tr>
								<th>거래방식</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_trade_type", "cd_trade_type", "checkbox", cooconGoodsBankInfo.cd_trade_type, 0)}
								</td>
							</tr>
							<tr>
								<th>무서류</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("yn_send_docu", "yn_send_docu", "radio", cooconGoodsBankInfo.yn_send_docu, 0)}
								</td>
							</tr>
							<tr>
								<th>무방문</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("yn_visit", "yn_visit", "radio", cooconGoodsBankInfo.yn_visit, 0)}
								</td>
							</tr>
							<tr>
								<th>중도상환 수수료 없음</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("yn_erly_rpay_fee", "yn_erly_rpay_fee", "radio", cooconGoodsBankInfo.yn_erly_rpay_fee, 0)}
								</td>
							</tr>
							<tr>
								<th>대상고객</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_target_cust", "cd_target_cust", "checkbox", cooconGoodsBankInfo.cd_target_cust, 0)}
								</td>
							</tr>
							<tr>
								<th>금리방식</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_ratio_type", "cd_ratio_type", "checkbox", cooconGoodsBankInfo.cd_ratio_type, 0)}
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>	
			<!-- 
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
								<th>신청방식</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_apply_type", "cd_apply_type", "checkbox", cooconGoodsBankInfo.cd_apply_type, 0)}
								</td>
							</tr>
							<tr>
								<th>상환방식</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_type_pay", "cd_type_pay", "checkbox", cooconGoodsBankInfo.cd_type_pay, 0)}
								</td>
							</tr>
							<tr>
								<th>거래방식</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_trade_type", "cd_trade_type", "checkbox", cooconGoodsBankInfo.cd_trade_type, 0)}
								</td>
							</tr>
							<tr>
								<th>무서류</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("yn_send_docu", "yn_send_docu", "radio", cooconGoodsBankInfo.yn_send_docu, 0)}
								</td>
							</tr>
							<tr>
								<th>무방문</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("yn_visit", "yn_visit", "radio", cooconGoodsBankInfo.yn_visit, 0)}
								</td>
							</tr>
							<tr>
								<th>금리/한도 확인가능</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("yn_srch_ratio_limit", "yn_srch_ratio_limit", "radio", cooconGoodsBankInfo.yn_srch_ratio_limit, 0)}
								</td>
							</tr>
							<tr>
								<th>중도상환 수수료 없음</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("yn_erly_rpay_fee", "yn_erly_rpay_fee", "radio", cooconGoodsBankInfo.yn_erly_rpay_fee, 0)}
								</td>
							</tr>
							<tr>
								<th>대상고객</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_target_cust", "cd_target_cust", "checkbox", cooconGoodsBankInfo.cd_target_cust, 0)}
								</td>
							</tr>
							<tr>
								<th>금리방식</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_ratio_type", "cd_ratio_type", "checkbox", cooconGoodsBankInfo.cd_ratio_type, 0)}
								</td>
							</tr>
							<tr>
								<th>주택종류</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_house_type", "cd_collateral_house_type", "checkbox", cooconGoodsBankInfo.cd_collateral_house_type, 0)}
								</td>
							</tr>
							<tr>
								<th>자금용도</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_loan_use", "cd_collateral_loan_use", "checkbox", cooconGoodsBankInfo.cd_collateral_loan_use, 0)}
								</td>
							</tr>
							<tr>
								<th>주택면적(전용면적)</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_area", "cd_area", "checkbox", cooconGoodsBankInfo.cd_area, 0)}
								</td>
							</tr>
							<tr>
								<th>거치방식</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_defer", "cd_defer", "radio", cooconGoodsBankInfo.cd_defer, 0)}
								</td>
							</tr>
							
							<tr>
								<th>대상구분</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_object", "cd_object", "checkbox", cooconGoodsBankInfo.cd_object, 0)}
								</td>
							</tr>
							
							<tr>
								<th>후순위 가능여부</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("yn_post_ranking", "yn_post_ranking", "radio", cooconGoodsBankInfo.yn_post_ranking, 0)}
								</td>
							</tr>
							<tr>
								<th>실행시간</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_time_exec", "cd_time_exec", "radio", cooconGoodsBankInfo.cd_time_exec, 0)}
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>	
			-->
			<!--상세항목  end  -->
			
			<!--상세정보 start  -->
			<div class="panel panel-primary">
				<div class="panel-heading">상세정보</div>
					<div class="panel-collapse">
						<table class="table table-classic">
		 					<colgroup>
								<col width="20%">
								<col width="80%">
							</colgroup>
							<tbody>
							<!-- 
							<tr>
								<th>상품상세</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_goods" name="desc_goods" validate=" label:상품상세;" maxlength="2000">${cooconGoodsBankInfo.desc_goods}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_goods' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr> 
							 -->
							<tr>
								<th>대출대상</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_loan" name="desc_loan" validate=" label:대출대상;" maxlength="2000">${cooconGoodsBankInfo.desc_loan}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_loan' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>대출한도</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_limit" name="desc_limit" validate=" label:대출한도;" maxlength="2000">${cooconGoodsBankInfo.desc_limit}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_limit' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>대출금리</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_ratio" name="desc_ratio" validate=" label:대출금리;" maxlength="2000">${cooconGoodsBankInfo.desc_ratio}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_ratio' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>우대금리</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_prefer_yield" name="desc_prefer_yield" validate=" label:우대금리;" maxlength="2000">${cooconGoodsBankInfo.desc_prefer_yield}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_prefer_yield' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>대출기간</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_term" name="desc_term" validate=" label:대출기간;" maxlength="2000">${cooconGoodsBankInfo.desc_term}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_term' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>상환방식</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_repaymethod" name="desc_repaymethod" validate=" label:상환방식;" maxlength="2000">${cooconGoodsBankInfo.desc_repaymethod}</textarea>
									<script type="text/javascript">
										CKEDITOR.replace( 'desc_repaymethod' ,{
											language : 'ko',
											enterMode:'2',
											height : '200px',  // 입력창의 높이
											toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
										});								
									</script>
								</td>
							</tr>
							<tr>
								<th>부대비용</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_loan_cost" name="desc_loan_cost" validate=" label:부대비용;" maxlength="2000">${cooconGoodsBankInfo.desc_loan_cost}</textarea>
									<script type="text/javascript">
										CKEDITOR.replace( 'desc_loan_cost' ,{
											language : 'ko',
											enterMode:'2',
											height : '200px',  // 입력창의 높이
											toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
										});									
									</script>
								</td>
							</tr>
							<tr>
								<th>구비서류</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_papers" name="desc_papers" validate=" label:구비서류;" maxlength="2000">${cooconGoodsBankInfo.desc_papers}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_papers' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>연체이자율</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_overdue_interest" name="desc_overdue_interest" validate=" label:연체이자율;" maxlength="2000">${cooconGoodsBankInfo.desc_overdue_interest}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_overdue_interest' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>수수료</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_repayment_fee" name="desc_repayment_fee" validate=" label:수수료;" maxlength="2000">${cooconGoodsBankInfo.desc_repayment_fee}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_repayment_fee' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>신청시기</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_loan_period" name="desc_loan_period" validate=" label:신청시기;" maxlength="2000">${cooconGoodsBankInfo.desc_loan_period}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_loan_period' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>지급시기</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_loan_provide" name="desc_loan_provide" validate=" label:지급시기;" maxlength="2000">${cooconGoodsBankInfo.desc_loan_provide}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_loan_provide' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>상품문의</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_contact" name="desc_contact" validate=" label:상품문의;" maxlength="2000">${cooconGoodsBankInfo.desc_contact}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_contact' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>대상담보</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_collateral" name="desc_collateral" validate=" label:대상담보;" maxlength="2000">${cooconGoodsBankInfo.desc_collateral}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_collateral' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>유의사항</th>
								<td colspan="3">
									<textarea class="form-control w100" id="desc_etc" name="desc_etc" validate=" label:유의사항;" maxlength="2000">${cooconGoodsBankInfo.desc_etc}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_etc' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>	
			<!--상세정보 end  -->
		</form>
	</div>
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
		</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-primary btn-xs" onclick="initForm();">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			<c:choose>
				<c:when test="${empty cooconGoodsBankInfo.cd_non_goods}">
					<button type="button" class="btn btn-default btn-xs" id="createGoodsbankInfoDetails">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 추가
					</button>
				</c:when> 
				<c:otherwise>
					<a role="button" class="btn btn-default btn-xs" id="procGoodsbankInfoDetails">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
					</a>
					<button type="button" class="btn btn-warning btn-xs" id="deleteGoodsbankInfoDetails">
						<span class="glyphicon" aria-hidden="true"></span> 삭제
					</button>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
</div>
<script>

	$(document).ready(function(){
		// SelectPicker
		$('.selectpicker').selectpicker();
		
		window.setupValidateForm( frmGoodsbankInfoDetails );
		
	    var create = { 
	        success : function (result) {
	        	alert(result);
	        	var cd_non_goods = $("#cd_non_goods").val();
	        	setGoodsbankInfoUse(cd_non_goods);
	        	
	        	$("#page").val("1");
				loadGoodsbankInfoList();
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
	        url: "<c:url value='/goodsbank/createGoodsbank.json'/>",
	        type: "POST",
	    }; 
	    var proc = { 
	        success : function (result) {
					alert(result);
					var cd_non_goods = $("#cd_non_goods").val();
					setGoodsbankInfoUse(cd_non_goods);
					
					$("#page").val("1");
					loadGoodsbankInfoList();
				},
				error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				},
		        url: "<c:url value='/goodsbank/modifyGoodsbankDetails.json'/>",
		        type: "POST",
	    }; 
	    
	    var del = { 
		        success : function (result) {
		        	alert(result);
		        	initForm();
		        	
		        	$("#page").val("1");
					loadGoodsbankInfoList();
				},
				error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				},
		        url: "<c:url value='/goodsbank/delGoodsbankDetails.json'/>",
		        type: "POST",
		    }; 
	 
	     $("[id='procGoodsbankInfoDetails']").click(function(){
	    	var regExp_Float1 = /^[0-9]+$/;
	     	var regExp_Float2 = /^(\-|\+|\d*)\d+(\.|\d)\d*$/;

	    	if ( !frmGoodsbankInfoDetails.validateForm() ) return false;
	    	
// 	    	frmGoodsbankInfoDetails.desc_goods.value = CKEDITOR.instances.desc_goods.getData();
			frmGoodsbankInfoDetails.desc_loan.value = CKEDITOR.instances.desc_loan.getData();
			frmGoodsbankInfoDetails.desc_limit.value = CKEDITOR.instances.desc_limit.getData();
			frmGoodsbankInfoDetails.desc_ratio.value = CKEDITOR.instances.desc_ratio.getData();
			frmGoodsbankInfoDetails.desc_prefer_yield.value = CKEDITOR.instances.desc_prefer_yield.getData();
			frmGoodsbankInfoDetails.desc_term.value = CKEDITOR.instances.desc_term.getData();
			frmGoodsbankInfoDetails.desc_loan_cost.value = CKEDITOR.instances.desc_loan_cost.getData();
			frmGoodsbankInfoDetails.desc_repaymethod.value = CKEDITOR.instances.desc_repaymethod.getData();
			frmGoodsbankInfoDetails.desc_papers.value = CKEDITOR.instances.desc_papers.getData();
			frmGoodsbankInfoDetails.desc_overdue_interest.value = CKEDITOR.instances.desc_overdue_interest.getData();
			frmGoodsbankInfoDetails.desc_repayment_fee.value = CKEDITOR.instances.desc_repayment_fee.getData();
			frmGoodsbankInfoDetails.desc_loan_period.value = CKEDITOR.instances.desc_loan_period.getData();
			frmGoodsbankInfoDetails.desc_loan_provide.value = CKEDITOR.instances.desc_loan_provide.getData();
			frmGoodsbankInfoDetails.desc_contact.value = CKEDITOR.instances.desc_contact.getData();
			frmGoodsbankInfoDetails.desc_collateral.value = CKEDITOR.instances.desc_collateral.getData();
			frmGoodsbankInfoDetails.desc_etc.value = CKEDITOR.instances.desc_etc.getData();
			
			if(chk_point() == false ){
				return false;
			}else{
				$("#frmGoodsbankInfoDetails").ajaxSubmit(proc);				
			}
			
			return false; 
	    }); 
	    
	    $("[id='createGoodsbankInfoDetails']").click( function(){
	    	var regExp_Float1 = /^[0-9]+$/;
	     	var regExp_Float2 = /^(\-|\+|\d*)\d+(\.|\d)\d*$/;
			
	     	if ( !frmGoodsbankInfoDetails.validateForm() ) return false;
// 	    	frmGoodsbankInfoDetails.desc_goods.value = CKEDITOR.instances.desc_goods.getData();
			frmGoodsbankInfoDetails.desc_loan.value = CKEDITOR.instances.desc_loan.getData();
			frmGoodsbankInfoDetails.desc_limit.value = CKEDITOR.instances.desc_limit.getData();
			frmGoodsbankInfoDetails.desc_ratio.value = CKEDITOR.instances.desc_ratio.getData();
			frmGoodsbankInfoDetails.desc_prefer_yield.value = CKEDITOR.instances.desc_prefer_yield.getData();
			frmGoodsbankInfoDetails.desc_term.value = CKEDITOR.instances.desc_term.getData();
			frmGoodsbankInfoDetails.desc_loan_cost.value = CKEDITOR.instances.desc_loan_cost.getData();
			frmGoodsbankInfoDetails.desc_repaymethod.value = CKEDITOR.instances.desc_repaymethod.getData();
			frmGoodsbankInfoDetails.desc_papers.value = CKEDITOR.instances.desc_papers.getData();
			frmGoodsbankInfoDetails.desc_overdue_interest.value = CKEDITOR.instances.desc_overdue_interest.getData();
			frmGoodsbankInfoDetails.desc_repayment_fee.value = CKEDITOR.instances.desc_repayment_fee.getData();
			frmGoodsbankInfoDetails.desc_loan_period.value = CKEDITOR.instances.desc_loan_period.getData();
			frmGoodsbankInfoDetails.desc_loan_provide.value = CKEDITOR.instances.desc_loan_provide.getData();
			frmGoodsbankInfoDetails.desc_contact.value = CKEDITOR.instances.desc_contact.getData();
			frmGoodsbankInfoDetails.desc_collateral.value = CKEDITOR.instances.desc_collateral.getData();
			frmGoodsbankInfoDetails.desc_etc.value = CKEDITOR.instances.desc_etc.getData();
			
			if(chk_point() == false ){
				return false;
			}else{
				$("#frmGoodsbankInfoDetails").ajaxSubmit(create);				
			}
	    	
	        return false; 
	    }); 
	    
	    $("[id='deleteGoodsbankInfoDetails']").click( function(){
	    	var regExp_Float1 = /^[0-9]+$/;
	     	var regExp_Float2 = /^(\-|\+|\d*)\d+(\.|\d)\d*$/;
	     	
			$("#frmGoodsbankInfoDetails").ajaxSubmit(del);
	    	
	        return false; 
	    }); 

	});

	function chk_point(){
		var rto_interest_to = $("#rto_interest_to").val();
		var rto_interest_from = $("#rto_interest_from").val();
		var chk_to_value = rto_interest_to.substr(rto_interest_to.indexOf(".")+1,rto_interest_to.length).length;
		var chk_from_value = rto_interest_from.substr(rto_interest_from.indexOf(".")+1,rto_interest_from.length).length;
		if( chk_to_value > 3){
			alert("최소 금리는 소수점은 3자리까지만 입력이 가능합니다.");
			return false;
		}else if( chk_from_value > 3){
			alert("최대 금리는 소수점 3자리까지만 입력이 가능합니다.");
			return false;
		}
	}
	

</script>