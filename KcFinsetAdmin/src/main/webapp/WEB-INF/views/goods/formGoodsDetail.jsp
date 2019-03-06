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
				<c:when test="${empty goodsInfo.cd_goods}">
					<button type="button" class="btn btn-default btn-xs" id="createGoodsDetails">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 추가
					</button>
				</c:when>
				<c:otherwise>
					<a role="button" class="btn btn-default btn-xs" id="procGoodsDetails">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
					</a>
					<button type="button" class="btn btn-warning btn-xs" onclick="deleteGoods('${goodsInfo.cd_fc}', '${goodsInfo.cd_goods}');">
						<span class="glyphicon" aria-hidden="true"></span> 삭제
					</button>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
	<div class="panel-collapse toggle-cont">
		<form name="frmGoodsDetails" id="frmGoodsDetails">
			<input type="hidden" name="fc" id="fc" />
			<input type="hidden" name="goods" id="goods" />
			<input type="hidden" name="goods_class_l" id="goods_class_l" />
			<input type="hidden" name="goods_class_m" id="goods_class_m" />
			<input type="hidden" name="goods_class_s" id="goods_class_s" />
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
							<th>상품명</th>
							<td colspan="2">
								<input type="text" class="form-control width-200" name="nm_goods"  value="${goodsInfo.nm_goods}" validate="required;label:상품명;" maxlength="30"/>
							</td>
							<c:choose>
							<c:when test="${empty goodsInfo.cd_goods}">
								<th>상품코드</th>
								<td>
									<input type="text" class="form-control width-100" name="cd_goods" value="${goodsInfo.cd_goods}" validate="required; label:상품코드;" maxlength="10"/>
								</td>
								<td>	
									<button type="button" class="btn btn-success btn-xs" onclick="duplicationChk();">
										<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 중복확인
									</button>
								</td>
								</c:when>
								<c:otherwise>
									<input type="hidden" name="cd_goods" value="${goodsInfo.cd_goods}"/>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th>금융기관명</th>
							<td colspan="5">
								<c:choose>
									<c:when test="${workerVO.cd_template_group eq '12'}">
										<input type="hidden" name ="cd_fc" value="${workerVO.cd_fc }" readonly="readonly"/>
										${workerVO.nm_fc }
									</c:when>
									<c:otherwise>
										<select name="cd_fc" class="selectpicker" validate="required;label:금융기관명;">
											${ufn:makeFincorpOptions('금융기관명', goodsInfo.cd_fc, 'type', 'Y')}
										</select>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>판매여부</th> 
							<td colspan="2">
								<c:choose>
									<c:when test="${empty goodsInfo.yn_use }">
										<select name="yn_use" class="selectpicker" validate="required;label:판매여부;">
										${ufn:makeOptions("yn_use","선택", "Y")}
										</select>
									</c:when>
									<c:otherwise>
										<select name="yn_use" class="selectpicker" validate="required;label:판매여부;">
										${ufn:makeOptions("yn_use","선택", goodsInfo.yn_use)}
										</select>
									</c:otherwise>
								</c:choose>
							</td>
							<th>제로금리표시여부</th> 
							<td colspan="2">
								<c:choose>
									<c:when test="${empty goodsInfo.yn_zero_ratio }">
										<select name="yn_zero_ratio" class="selectpicker" validate="required;label:제로금리표시여부;">
										${ufn:makeOptions("yn_use","선택", "")}
										</select>
									</c:when>
									<c:otherwise>
										<select name="yn_zero_ratio" class="selectpicker" validate="required;label:제로금리표시여부;">
										${ufn:makeOptions("yn_use","선택", goodsInfo.yn_zero_ratio)}
										</select>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>대분류</th>
							<td colspan="2">
								<select name="cd_goods_class_l" id="cd_goods_class_l" class="selectpicker" onchange="ChangeSelectBox('cd_goods_m', this)" value="${goodsInfo.cd_goods_class_l}" validate="required;label:대분류;">
									${ufn:makeOptions("cd_goods_l","선택","" )}
								</select>
							</td>
							<th>중분류</th>
							<td colspan="2"> 
								<select name="cd_goods_class_m" id="cd_goods_m" class="selectpicker" onchange="ChangeSelectBox('cd_goods_s', this)" validate="required;label:중분류;" >
									<option value=""> 선택 </option>
								</select>
							</td>
						</tr>
						
						<tr>
							<th><span>상품종류</span></th>
							<td colspan="5">
								<c:choose>
									<c:when test="${empty goodsInfo.cd_goods_type }">
										${ufn:makeRadioAndCheckBoxs("cd_goods_type", "cd_goods_type", "checkbox", "01", 0)}
									</c:when>
									<c:otherwise>
										${ufn:makeRadioAndCheckBoxs("cd_goods_type", "cd_goods_type", "checkbox", goodsInfo.cd_goods_type, 0)}	
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						
						<tr>
							<th>대출기간</th>
							<td colspan="5">
								<input type="text" class="form-control width-100" name="cd_loan_term" value="${goodsInfo.cd_loan_term}" validate=" label:대출기간;" maxlength="3" onkeypress="keyNumeric(event);"/> 년
							</td>
							<!-- 
							<th>금리적용방식</th>
							<td colspan="2">
								${ufn:makeRadioAndCheckBoxs("cd_type_interest","cd_type_interest","checkbox", goodsInfo.cd_type_interest, 0)}
							</td>
							 -->
						</tr>
						
						<tr>
							<th><span>대출금리</span></th>
							<td colspan="2">
								<input type="text" class="form-control width-85"  name="rto_interest_from" value="${goodsInfo.rto_interest_from}" validate=" label:대출금리;" maxlength="4"/> %
								<input type="text" class="form-control width-85"  name="rto_interest_to" value="${goodsInfo.rto_interest_to}" validate=" label:대출금리;" maxlength="4"/> %
							</td>
							<th>한도</th>
							<td colspan="2">
								<input type="text" class="form-control width-100" name="amt_limit" value="${goodsInfo.amt_limit}" validate=" label:한도;" maxlength="9" onkeypress="keyNumeric(event);"/> 만원
							</td>
						</tr>
						
						
<!-- 						<tr> -->
<!-- 							<th><span>상환방식</span></th> -->
<!-- 							<td colspan="5"> -->
<%-- 								${ufn:makeRadioAndCheckBoxs("cd_type_pay", "cd_type_pay", "checkbox", goodsInfo.cd_type_pay, 0)} --%>
<!-- 							</td> -->
<!-- 						</tr> -->
					<!-- 
						<tr>
							<th><span>이벤트</span></th>
							<td colspan="5">
								${ufn:makeRadioAndCheckBoxs("cd_event", "cd_event", "checkbox", goodsInfo.cd_event, 0)}
							</td>
						</tr>
						
						<tr>
							<th>비대면여부</th>
							<td colspan="5">
								<select name="yn_online"  data-width="20%" class="selectpicker" validate=" label:비대면여부;">
									${ufn:makeOptions("yn_online","선택", goodsInfo.yn_online)}
								</select>
							</td>
						</tr>

						<tr>
							<th>무서류 진행여부</th>
							<td colspan="5">
								<select name="yn_proc_nopapers"  data-width="20%" class="selectpicker" validate=" label:무서류 진행여부;">
									${ufn:makeOptions("yn_proc_nopapers","선택", goodsInfo.yn_proc_nopapers)}
								</select>
							</td>
						</tr>
						 -->
						<tr>
							<th>상품특징</th>
							<td colspan="5">
								<input type="text" class="form-control width-400" name="desc_feature" value="${goodsInfo.desc_feature}" validate="required; label:상품특징;" maxlength="70"/>
							</td>
						</tr>
						<tr>
							<th>키워드</th>
							<td colspan="5">
								<input type="text" class="form-control width-400" name="keyword_list" value="${goodsInfo.keyword_list}" validate="required; label:키워드;" maxlength="70"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div id="Condition">
				<%@ include file="/WEB-INF/views/goods/formGoodsConditionCredit.jsp"%>
			</div>
						
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
								<th>개요</th>
								<td colspan="3">
									<textarea class="form-control w100" name="desc_trade_clause" validate=" label:금융사 거래약관; maxbt:2000;" maxlength="2000">${goodsInfo.desc_trade_clause}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_trade_clause' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>상세</th>
								<td colspan="3">
									<textarea class="form-control w100" name="desc_goods" validate=" label:상세; maxbt:2000;" maxlength="2000">${goodsInfo.desc_goods}</textarea>
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
									<textarea class="form-control w100" name="desc_loan" validate=" label:대출대상상세; maxbt:2000;" maxlength="2000">${goodsInfo.desc_loan}</textarea>
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
									<textarea class="form-control w100" name="desc_limit" validate=" label:대출한도상세; maxbt:2000;" maxlength="2000">${goodsInfo.desc_limit}</textarea>
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
								<th>대출기간</th>
								<td colspan="3">
									<textarea class="form-control w100" name="desc_paymethod" validate=" label:대출기간; maxbt:2000;" maxlength="2000">${goodsInfo.desc_paymethod}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_paymethod' ,{
												language : 'ko',
												enterMode:'2',
												height : '200px',  // 입력창의 높이
												toolbar :[["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select", "Button", "ImageButton", "HiddenField"],"/",["Bold","Italic","Underline","Strike","-","Subscript","Superscript"],["NumberedList","BulletedList","-","Outdent","Indent","Blockquote"],["JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"],["Link","Unlink"],["Table","HorizontalRule","Smiley","SpecialChar","PageBreak"],["Styles","Format","Font","FontSize"],["TextColor","BGColor"],["Maximize", "ShowBlocks","-","About"]]									
											});								
										</script>
								</td>
							</tr>
							<tr>
								<th>상환방법</th>
								<td colspan="3">
									<textarea class="form-control w100" name="desc_repaymethod" validate=" label:상환방법; maxbt:2000;" maxlength="2000">${goodsInfo.desc_repaymethod}</textarea>
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
								<th>중도상환수수료</th>
								<td colspan="3">
									<textarea class="form-control w100" name="desc_commission" validate=" label:중도상환수수료; maxbt:2000;" maxlength="2000">${goodsInfo.desc_commission}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_commission' ,{
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
									<textarea class="form-control w100" name="desc_papers" validate=" label:구비서류; maxbt:2000;" maxlength="2000">${goodsInfo.desc_papers}</textarea>
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
								<th>상품문의</th>
								<td colspan="3">
									<textarea class="form-control w100" name="desc_inquiry" validate=" label:상품문의; maxbt:2000;" maxlength="2000">${goodsInfo.desc_inquiry}</textarea>
									<script type="text/javascript">
											CKEDITOR.replace( 'desc_inquiry' ,{
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
									<textarea class="form-control w100" name="content_interest" id="content_interest">${goodsInfo.content_interest}</textarea>
									<script type="text/javascript">
										CKEDITOR.replace( 'content_interest' ,{
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
									<textarea class="form-control w100" name="prefer_interest" id="prefer_interest">${goodsInfo.prefer_interest}</textarea>
									<script type="text/javascript">
										CKEDITOR.replace( 'prefer_interest' ,{
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
			<div id="CfsCheck">
				<%@ include file="/WEB-INF/views/goods/formGoodsCFSCredit.jsp"%>
			</div>
			<div id="RtoCommission">
				<%@ include file="/WEB-INF/views/goods/formGoodsRtoCommission.jsp"%>
			</div>
		</form>
	</div>
	
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none"></a>
		</h3>
		<span class="pull-right">
			<button type="button" class="btn btn-primary btn-xs" onclick="initForm();">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
			</button>
			<c:choose>
				<c:when test="${empty goodsInfo.cd_goods}">
					<button type="button" class="btn btn-default btn-xs" id="createGoodsDetails">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 추가
					</button>
				</c:when>
				<c:otherwise>
					<a role="button" class="btn btn-default btn-xs" id="procGoodsDetails">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장
					</a>
					<button type="button" class="btn btn-warning btn-xs" onclick="deleteGoods('${goodsInfo.cd_fc}', '${goodsInfo.cd_goods}');">
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
		
		window.setupValidateForm( frmGoodsDetails );

	    var proc = { 
	        success : function (result) {
				if(result == "00"){
					alert("수정에 성공하였습니다.");
// 					initForm();
					loadGoodsList();
				}else if(result == "01"){
					alert("수정에 실패하였습니다.");
				}
				
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
	        url: "<c:url value='/goods/modifyGoodsDetails.json'/>",
	        type: "POST"
	    }; 
	    
	    var create = { 
	        success : function (result) {
				if(result == "00"){
					alert("등록에 성공하였습니다.");
					initForm();
					goSearch();
				}else if(result == "01"){
					alert("등록에 실패하였습니다.");
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
	        url: "<c:url value='/goods/createGoods.json'/>",
	        type: "POST"
	    }; 
	 
	     $("[id='procGoodsDetails']").click(function(){
	    	var regExp_Float1 = /^[0-9]+$/;
	     	var regExp_Float2 = /^(\-|\+|\d*)\d+(\.|\d)\d*$/;
	     	

	    	if ( !frmGoodsDetails.validateForm() ) return false;
    		
    		/* if(!regExp_Float1.test($("[name='rto_interest_from']").val()) && !regExp_Float2.test($("[name='rto_interest_from']").val())){
 				alert("대출 금리 최소  값이 잘못 입력되었습니다.");
 				$("[name='rto_interest_from']").focus();
 				return false;
			}
			if(!regExp_Float1.test($("[name='rto_interest_to']").val()) && !regExp_Float2.test($("[name='rto_interest_to']").val())){
				alert("대출 금리 최대  값이 잘못 입력되었습니다.");
				$("[name='rto_interest_to']").focus();
				return false;
			}
			
			if(!regExp_Float1.test($("[name='age_loan_from']").val()) && !regExp_Float2.test($("[name='age_loan_from']").val())){
				alert("대출 가능 연령 최소 값이 잘못 입력되었습니다.");
				$("[name='age_loan_from']").focus();
				return false;
			}
			if(!regExp_Float1.test($("[name='age_loan_to']").val()) && !regExp_Float2.test($("[name='age_loan_to']").val())){
				alert("대출 가능 연령 최대 값이 잘못 입력되었습니다.");
				$("[name='age_loan_to']").focus();
				return false;
			}
			
			if(!regExp_Float1.test($("[name='amt_apply_from']").val()) && !regExp_Float2.test($("[name='amt_apply_from']").val())){
				alert("신청금액 최소 값이 잘못 입력되었습니다.");
				$("[name='amt_apply_from']").focus();
				return false;
			}
			if(!regExp_Float1.test($("[name='amt_apply_to']").val()) && !regExp_Float2.test($("[name='amt_apply_to']").val())){
				alert("신청금액 최대 값이 잘못 입력되었습니다.");
				$("[name='amt_apply_to']").focus();
				return false;
			}
			
			if(!regExp_Float1.test($("[name='no_month_apply_from']").val()) && !regExp_Float2.test($("[name='no_month_apply_from']").val())){
				alert("신청기간 최소 값이 잘못 입력되었습니다.");
				$("[name='no_month_apply_from']").focus();
				return false;
			}
			if(!regExp_Float1.test($("[name='no_month_apply_to']").val()) && !regExp_Float2.test($("[name='no_month_apply_to']").val())){
				alert("신청기간 최대 값이 잘못 입력되었습니다.");
				$("[name='no_month_apply_to']").focus();
				return false;
			}
			
			if(Number($("[name='rto_interest_from']").val()) > Number($("[name='rto_interest_to']").val())){
 				alert("대출 금리 최소 값이 잘못 입력되었습니다.");
 				$("[name='rto_interest_from']").focus();
 				return false;
 			}
    		if(Number($("[name='age_loan_from']").val()) > Number($("[name='age_loan_to']").val())){
 				alert("대출 가능 연령 최소 값이 잘못 입력되었습니다.");
 				$("[name='age_loan_from']").focus();
 				return false;
 			}
    		if(Number($("[name='amt_apply_from']").val()) > Number($("[name='amt_apply_to']").val())){
 				alert("신청금액 최소 값이 잘못 입력되었습니다.");
 				$("[name='amt_apply_from']").focus();
 				return false;
 			}
    		if(Number($("[name='no_month_apply_from']").val()) > Number($("[name='no_month_apply_to']").val())){
 				alert("신청기간 최소 값이 잘못 입력되었습니다.");
 				$("[name='no_month_apply_from']").focus();
 				return false;
 			}

			/* for (var i = 1; i <= st_r_cnt; i++) {
 	    		for (var j = 1; j <= st_c_cnt; j++) {
 	    			if($("[name='amt_limit_min_" + i + "']").val() == ''){
 	    				alert(i+"행의  최저한도를 입력해 주세요");
 	    				$("[name='amt_limit_min_" + i + "']").focus();
 	    				return false;
 	    			}else if($("[name='amt_limit_max_" + i + "']").val() == ''){
 	    				alert(i+"행의  최대한도를 입력해 주세요");
 	    				$("[name='amt_limit_max_" + i + "']").focus();
 	    				return false;
					}else if($("[name='rto_interest_min_" + i + "']").val() == ''){
						alert(i+"행의  최저금리를 입력해 주세요");
						$("[name='rto_interest_min_" + i + "']").focus();
						return false;
					}else if($("[name='rto_interest_max_" + i + "']").val() == ''){
 	    				alert(i+"행의  최대금리를 입력해 주세요");
 	    				$("[name='rto_interest_max_" + i + "']").focus();
 	    				return false;
 	    			}else if($("[name='rto_commission_" + i + "']").val() == ''){
 	    				alert(i+"행의 수수료율을 입력해 주세요");
 	    				$("[name='rto_commission_" + i + "']").focus();
 	    				return false;
 	    			}else if(!regExp_Float1.test($("[name='amt_limit_min_" + i + "']").val())){
 	    				alert(i+"행의  최저한도 값이 잘못 입력되었습니다.");
 	    				$("[name='amt_limit_min_" + i + "']").focus();
 	    				return false;
 	    			}else if(!regExp_Float1.test($("[name='amt_limit_max_" + i + "']").val())){
 	    				alert(i+"행의  최대한도 값이 잘못 입력되었습니다.")
 	    				$("[name='amt_limit_max_" + i + "']").focus();
 	    				return false;
					}else if(!regExp_Float1.test($("[name='rto_interest_min_" + i + "']").val()) &&!regExp_Float2.test($("[name='rto_interest_min_" + i + "']").val())){
 	    				alert(i+"행의  최저금리 값이 잘못 입력되었습니다.");
 	    				$("[name='rto_interest_min_" + i + "']").focus();
 	    				return false;
 	    			}else if(!regExp_Float1.test($("[name='rto_interest_max_" + i + "']").val()) &&!regExp_Float2.test($("[name='rto_interest_max_" + i + "']").val())){
 	    				alert(i+"행의  최대금리 값이 잘못 입력되었습니다.")
 	    				$("[name='rto_interest_max_" + i + "']").focus();
 	    				return false;
 	    			}else if(!regExp_Float1.test($("[name='rto_commission_" + i + "']").val()) && !regExp_Float2.test($("[name='rto_commission_" + i + "']").val())){
 	    				alert(i+"행의 수수료율 값이 잘못 입력되었습니다.");
 	    				$("[name='rto_commission_" + i + "']").focus();
 	    				return false;
 	    			}
 	    		}
 	    	} */
// 			frmGoodsDetails.desc_trade_clause.value = CKEDITOR.instances.desc_trade_clause.getData();
// 			frmGoodsDetails.desc_goods.value = CKEDITOR.instances.desc_goods.getData();
			frmGoodsDetails.desc_loan.value = CKEDITOR.instances.desc_loan.getData();
			frmGoodsDetails.desc_limit.value = CKEDITOR.instances.desc_limit.getData();
			frmGoodsDetails.desc_paymethod.value = CKEDITOR.instances.desc_paymethod.getData();
			frmGoodsDetails.desc_repaymethod.value = CKEDITOR.instances.desc_repaymethod.getData();
			frmGoodsDetails.desc_commission.value = CKEDITOR.instances.desc_commission.getData();
			frmGoodsDetails.desc_papers.value = CKEDITOR.instances.desc_papers.getData();
			frmGoodsDetails.desc_inquiry.value = CKEDITOR.instances.desc_inquiry.getData();
			frmGoodsDetails.content_interest.value = CKEDITOR.instances.content_interest.getData();
			frmGoodsDetails.prefer_interest.value = CKEDITOR.instances.prefer_interest.getData();
			if( chk_chk() == false){
				return false;
			}else{
				$("#frmGoodsDetails").ajaxSubmit(proc);	
			}
			return false; 
	    }); 
	    
	    $("[id='createGoodsDetails']").click( function(){
	    	var regExp_Float1 = /^[0-9]+$/;
	     	var regExp_Float2 = /^(\-|\+|\d*)\d+(\.|\d)\d*$/;

	     	if ( !frmGoodsDetails.validateForm() ) return false;
	     	/*
	     	if(!regExp_Float1.test($("[name='rto_interest_from']").val()) && !regExp_Float2.test($("[name='rto_interest_from']").val())){
 				alert("대출 금리 최소  값이 잘못 입력되었습니다.");
 				$("[name='rto_interest_from']").focus();
 				return false;
			}
			if(!regExp_Float1.test($("[name='rto_interest_to']").val()) && !regExp_Float2.test($("[name='rto_interest_to']").val())){
				alert("대출 금리 최대  값이 잘못 입력되었습니다.");
				$("[name='rto_interest_to']").focus();
				return false;
			}
			
			if(!regExp_Float1.test($("[name='age_loan_from']").val()) && !regExp_Float2.test($("[name='age_loan_from']").val())){
				alert("대출 가능 연령 최소 값이 잘못 입력되었습니다.");
				$("[name='age_loan_from']").focus();
				return false;
			}
			if(!regExp_Float1.test($("[name='age_loan_to']").val()) && !regExp_Float2.test($("[name='age_loan_to']").val())){
				alert("대출 가능 연령 최대 값이 잘못 입력되었습니다.");
				$("[name='age_loan_to']").focus();
				return false;
			}
			
			if(!regExp_Float1.test($("[name='amt_apply_from']").val()) && !regExp_Float2.test($("[name='amt_apply_from']").val())){
				alert("신청금액 최소 값이 잘못 입력되었습니다.");
				$("[name='amt_apply_from']").focus();
				return false;
			}
			if(!regExp_Float1.test($("[name='amt_apply_to']").val()) && !regExp_Float2.test($("[name='amt_apply_to']").val())){
				alert("신청금액 최대 값이 잘못 입력되었습니다.");
				$("[name='amt_apply_to']").focus();
				return false;
			}
			
			if(!regExp_Float1.test($("[name='no_month_apply_from']").val()) && !regExp_Float2.test($("[name='no_month_apply_from']").val())){
				alert("신청기간 최소 값이 잘못 입력되었습니다.");
				$("[name='no_month_apply_from']").focus();
				return false;
			}
			if(!regExp_Float1.test($("[name='no_month_apply_to']").val()) && !regExp_Float2.test($("[name='no_month_apply_to']").val())){
				alert("신청기간 최대 값이 잘못 입력되었습니다.");
				$("[name='no_month_apply_to']").focus();
				return false;
			}	
	    	
			if(Number($("[name='rto_interest_from']").val()) > Number($("[name='rto_interest_to']").val())){
 				alert("대출 금리 최소 값이 잘못 입력되었습니다.");
 				$("[name='rto_interest_from']").focus();
 				return false;
 			}
    		if(Number($("[name='age_loan_from']").val()) > Number($("[name='age_loan_to']").val())){
 				alert("대출 가능 연령 최소 값이 잘못 입력되었습니다.");
 				$("[name='age_loan_from']").focus();
 				return false;
 			}
    		if(Number($("[name='amt_apply_from']").val()) > Number($("[name='amt_apply_to']").val())){
 				alert("신청금액 최소 값이 잘못 입력되었습니다.");
 				$("[name='amt_apply_from']").focus();
 				return false;
 			}
    		if(Number($("[name='no_month_apply_from']").val()) > Number($("[name='no_month_apply_to']").val())){
 				alert("신청기간 최소 값이 잘못 입력되었습니다.");
 				$("[name='no_month_apply_from']").focus();
 				return false;
 			}
    	 
			for (var i = 1; i <= st_r_cnt; i++) {
 	    		for (var j = 1; j <= st_c_cnt; j++) {
 	    			if($("[name='amt_limit_min_" + i + "']").val() == ''){
 	    				alert(i+"행의  최저한도를 입력해 주세요");
 	    				$("[name='amt_limit_min_" + i + "']").focus();
 	    				return false;
 	    			}else if($("[name='amt_limit_max_" + i + "']").val() == ''){
 	    				alert(i+"행의  최대한도를 입력해 주세요");
 	    				$("[name='amt_limit_max_" + i + "']").focus();
 	    				return false;
					}else if($("[name='rto_interest_min_" + i + "']").val() == ''){
						alert(i+"행의  최저금리를 입력해 주세요");
						$("[name='rto_interest_min_" + i + "']").focus();
						return false;
					}else if($("[name='rto_interest_max_" + i + "']").val() == ''){
 	    				alert(i+"행의  최대금리를 입력해 주세요");
 	    				$("[name='rto_interest_max_" + i + "']").focus();
 	    				return false;
 	    			}else if($("[name='rto_commission_" + i + "']").val() == ''){
 	    				alert(i+"행의 수수료율을 입력해 주세요");
 	    				$("[name='rto_commission_" + i + "']").focus();
 	    				return false;
 	    			}else if(!regExp_Float1.test($("[name='amt_limit_min_" + i + "']").val())){
 	    				alert(i+"행의  최저한도 값이 잘못 입력되었습니다.");
 	    				$("[name='amt_limit_min_" + i + "']").focus();
 	    				return false;
 	    			}else if(!regExp_Float1.test($("[name='amt_limit_max_" + i + "']").val())){
 	    				alert(i+"행의  최대한도 값이 잘못 입력되었습니다.")
 	    				$("[name='amt_limit_max_" + i + "']").focus();
 	    				return false;
					}else if(!regExp_Float1.test($("[name='rto_interest_min_" + i + "']").val()) &&!regExp_Float2.test($("[name='rto_interest_min_" + i + "']").val())){
 	    				alert(i+"행의  최저금리 값이 잘못 입력되었습니다.");
 	    				$("[name='rto_interest_min_" + i + "']").focus();
 	    				return false;
 	    			}else if(!regExp_Float1.test($("[name='rto_interest_max_" + i + "']").val()) &&!regExp_Float2.test($("[name='rto_interest_max_" + i + "']").val())){
 	    				alert(i+"행의  최대금리 값이 잘못 입력되었습니다.")
 	    				$("[name='rto_interest_max_" + i + "']").focus();
 	    				return false;
 	    			}else if(!regExp_Float1.test($("[name='rto_commission_" + i + "']").val()) && !regExp_Float2.test($("[name='rto_commission_" + i + "']").val())){
 	    				alert(i+"행의 수수료율 값이 잘못 입력되었습니다.");
 	    				$("[name='rto_commission_" + i + "']").focus();
 	    				return false;
 	    			}
 	    		}
 	    	} */
// 			frmGoodsDetails.desc_trade_clause.value = CKEDITOR.instances.desc_trade_clause.getData();
// 			frmGoodsDetails.desc_goods.value = CKEDITOR.instances.desc_goods.getData();
			frmGoodsDetails.desc_loan.value = CKEDITOR.instances.desc_loan.getData();
			frmGoodsDetails.desc_limit.value = CKEDITOR.instances.desc_limit.getData();
			frmGoodsDetails.desc_paymethod.value = CKEDITOR.instances.desc_paymethod.getData();
			frmGoodsDetails.desc_repaymethod.value = CKEDITOR.instances.desc_repaymethod.getData();
			frmGoodsDetails.desc_commission.value = CKEDITOR.instances.desc_commission.getData();
			frmGoodsDetails.desc_papers.value = CKEDITOR.instances.desc_papers.getData();
			frmGoodsDetails.desc_inquiry.value = CKEDITOR.instances.desc_inquiry.getData();
			frmGoodsDetails.content_interest.value = CKEDITOR.instances.content_interest.getData();
			frmGoodsDetails.prefer_interest.value = CKEDITOR.instances.prefer_interest.getData();
			if( chk_chk() == false){
				return false;
			}else{
				$("#frmGoodsDetails").ajaxSubmit(create);	
			}
	        return false; 
	    }); 

	    //대분류 초기화
		$("select[id^='cd_goods_class_l']").change();
		
		var goods_l = "${goodsInfo.cd_goods_class_l}";
	 	var goods_m = "${goodsInfo.cd_goods_class_m}";
	 	var goods_s = "${goodsInfo.cd_goods_class_s}";
		
		$("#goods_class_l").val(goods_l);
		$("#goods_class_m").val(goods_m);
		$("#goods_class_s").val(goods_s);
		
		$("#fc").val("${goodsInfo.cd_fc}");
		$("#goods").val("${goodsInfo.cd_goods}");
		
		if(goods_l){
	 		$('#cd_goods_class_l option[value='+goods_l+']').attr('selected', 'selected');
	 		$('#cd_goods_class_l').selectpicker('refresh');
	 		$("select[id^='cd_goods_class_l']").change();
		}
	});

	function duplicationChk() {
		var cd_goods =  frmGoodsDetails.cd_goods.value;
		if(cd_goods == "") return false;
		
		var data = {"cd_goods":cd_goods};
		 $.ajax({
			url : "<c:url value='/goods/duplicationChk.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				if(result.result == "Y") alert("사용가능한 상품코드 입니다.");
				else {
					alert("중복된 상품코드 입니다.");
					frmGoodsDetails.cd_goods.value = "";
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		}); 
		
	}
	

	function deleteGoods(cd_fc,cd_goods) {
		
		if(confirm("삭제 하시겠습니까?") == false) return false;
		
		var data = {
			"cd_fc" : cd_fc,
			"cd_goods" : cd_goods
		};
		if (data == null)
			return false;

		$.ajax({
			url : "<c:url value='/goods/deleteGoods.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function(result) {
				var returnData = result.returnData;
				alert(returnData.message);

				initForm();
				goSearch();
			},
			error : function(e) {
				alert(e.responseText);
			}
		});
	}
	
	var cfsCheckInitData = "N";
	var conditionInitData = "N";
	function ChangeSelectBox(codeGroup, obj) {
		var etc, target;
		
		if(!obj.value) {
// 			document.frmGoodsDetails.cd_goods_m.options.length=0;
			$("#cd_goods_m").empty();
			$('#cd_goods_m').append("<option value=''>선택</option>");
			$('#cd_goods_m').selectpicker('refresh');
			
			/*
			document.frmGoodsDetails.cd_goods_s.options.length=0;
			$('#cd_goods_s').append("<option value=''>선택</option>");
			$('#cd_goods_s').selectpicker('refresh');
			return;
			*/
		}
		
		 if(codeGroup == 'cd_goods_m') {
			 etc = obj.value;
			 $('#s_value').val(etc);
// 			 target= document.frmGoodsDetails.cd_goods_m;
			 target = $("#cd_goods_m");
		 } else if(codeGroup == 'cd_goods_s'){
			 etc =  $('#s_value').val();
// 			 target= document.frmGoodsDetails.cd_goods_s;
			 target = $("#cd_goods_s");
		 }
		 
// 		target.options.length=0;
		target.empty();
		
	 	var data = {
				"code_group":codeGroup,
				"etc":etc,
			   };

		if(data == null) return false;
		 
		$.ajax({
			url : "<c:url value='/goods/ChangeSelectBoxLMS.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			dataType : "json",
			success : function(data) {

				 	boxOption = document.createElement("OPTION");
			        boxOption.value ='';    
			        boxOption.text = '선택';		
// 			        target.options.add(boxOption);
					target.append(boxOption);
					
				$.each(data.returnData, function (index, item) {
			        boxOption = document.createElement("OPTION");
			        boxOption.value = item.code_value;    
			        boxOption.text = item.nm_code;		
// 			        target.options.add(boxOption);
			        target.append(boxOption);
					
				});	
			
		        $('#' + codeGroup).selectpicker('refresh');
// 				target.options.length = data.returnData.length+1;
				
				if(codeGroup == 'cd_goods_m') {
					var goods_gubun = $('#goods_class_m').val();
					if(goods_gubun){
						$('#cd_goods_m option[value='+goods_gubun+']').attr('selected', 'selected');
						$('#cd_goods_m').selectpicker('refresh');
						$("select[id^='cd_goods_s']").change();
					}
				 }else if(codeGroup == 'cd_goods_s') {
					var goods_gubun = $('#goods_class_s').val();
					
					if(goods_gubun){
						$('#cd_goods_s option[value='+goods_gubun+']').attr('selected', 'selected');
						$('#cd_goods_s').selectpicker('refresh');
					}
				 }
			}
		}); 
		
		if(frmGoodsDetails.cd_goods_class_l.value == '01'){ //신용
			if(!$("#fc").val()  || !$("#goods").val()){
				vLoad("CfsCheck","<c:url value='/goods/formGoodsCFSCredit.crz'/>",{});
			} else {
				if(cfsCheckInitData == "Y"){
					var data = {"cd_fc": $("#fc").val(),"cd_goods": $("#goods").val()};
					if(data == null) return false;
					vLoad("CfsCheck","<c:url value='/goods/formGoodsCFSCredit.crz'/>",data);
					cfsCheckInitData = "N";
				}else if(cfsCheckInitData == "N"){
					vLoad("CfsCheck","<c:url value='/goods/formGoodsCFSCredit.crz'/>",{});
				}
			}
	 	} else { //담보
	 		if(frmGoodsDetails.cd_goods_class_m.value == '05') { //주택담보
				if(!$("#fc").val()  || !$("#goods").val()){
		 			vLoad("CfsCheck","<c:url value='/goods/formGoodsCFSHousing.crz'/>",{});
				} else {
					if(cfsCheckInitData == "Y"){
						var data = {"cd_fc": $("#fc").val(),"cd_goods": $("#goods").val()};
			 			if(data == null) return false;
			 			vLoad("CfsCheck","<c:url value='/goods/formGoodsCFSHousing.crz'/>",data);
			 			cfsCheckInitData = "N";
					}else if(cfsCheckInitData == "N"){
						vLoad("CfsCheck","<c:url value='/goods/formGoodsCFSHousing.crz'/>",{});
					}
				}
	 		} else if(frmGoodsDetails.cd_goods_class_m.value == '06') { //자동차담보  
		 		if(!$("#fc").val()  || !$("#goods").val()){
		 			vLoad("CfsCheck","<c:url value='/goods/formGoodsCFSCar.crz'/>",{});
				} else {
					if(cfsCheckInitData == "Y"){
						var data = {"cd_fc": $("#fc").val(),"cd_goods": $("#goods").val()};
			 			if(data == null) return false;
			 			vLoad("CfsCheck","<c:url value='/goods/formGoodsCFSCar.crz'/>",data);
			 			cfsCheckInitData = "N";
					}else if(cfsCheckInitData == "N"){
						vLoad("CfsCheck","<c:url value='/goods/formGoodsCFSCar.crz'/>",{});
					}
				}
	 		}
	 	}
		
		// 조건검색 페이지 로드
		if(frmGoodsDetails.cd_goods_class_l.value == '01'){ //신용
				if(!$("#fc").val()  || !$("#goods").val()){  
					vLoad("Condition","<c:url value='/goods/formGoodsConditionCredit.crz'/>",{});
				} else {
					if(conditionInitData == "Y"){
						var data = {"cd_fc": $("#fc").val(),"cd_goods": $("#goods").val()};
						if(data == null) return false;  
						vLoad("Condition","<c:url value='/goods/formGoodsConditionCredit.crz'/>",data);
						conditionInitData = "N";
					}else if(conditionInitData == "N"){
						vLoad("Condition","<c:url value='/goods/formGoodsConditionCredit.crz'/>",{});
					}
				}
		} else { //담보
	 		if(frmGoodsDetails.cd_goods_class_m.value == '05') { //주택
		 		if(!$("#fc").val()  || !$("#goods").val()){
		 			vLoad("Condition","<c:url value='/goods/formGoodsConditionGuarantee.crz'/>",{});
				} else {
					if(conditionInitData == "Y"){
						var data = {"cd_fc": $("#fc").val(),"cd_goods": $("#goods").val()};
			 			if(data == null) return false;
						vLoad("Condition","<c:url value='/goods/formGoodsConditionGuarantee.crz'/>",data);
						conditionInitData = "N";
					}else if(conditionInitData == "N"){
						vLoad("Condition","<c:url value='/goods/formGoodsConditionGuarantee.crz'/>",{});
					}
				}
		 	} else if(frmGoodsDetails.cd_goods_class_m.value == '06') { //자동차
		 		$("#Condition").empty();
		 	}		 
		}
	}
	
	function chk_chk(){
		if( $("#chk_age_loan").is(":checked") == true){
			$("#chk_age_loan").val("Y");
			if( ($("#age_loan_from").val() == "") || ($("#age_loan_to").val() == "")){
				alert("CFS신용 대출가능연령 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_age_loan").val("N");
		}
		
		if( $("#chk_amt_apply").is(":checked") == true){
			$("#chk_amt_apply").val("Y");
			if( ($("#amt_apply_from").val() == "") && ($("#amt_apply_to").val() == "")){
				alert("CFS신용 신청금액 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_amt_apply").val("N");
		}
		
		if( $("#chk_no_month_apply").is(":checked") == true){
			$("#chk_no_month_apply").val("Y");
			if( ($("#no_month_apply_from").val() == "") || ($("#no_month_apply_to").val() == "")){
				alert("CFS신용 신청기간 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_no_month_apply").val("N");
		}
		
		if( $("#chk_cd_type_income").is(":checked") == true){
			$("#chk_cd_type_income").val("Y");
			var chk = 0;
			for(var i=1; i<5; i++){
				if($('#cd_type_income'+i).is(":checked") == true){
					chk = chk+i;
					continue;
				}
			}
			if(chk ==0){
				alert("CFS신용 소득형태 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_cd_type_income").val("N");
		}
		
		if( $("#chk_cd_employ_type").is(":checked") == true){
			$("#chk_cd_employ_type").val("Y");
			var chk = 0;
			for(var i=1; i<10; i++){
				if($('#cd_employ_type'+i).is(":checked") == true){
					chk = chk+i;
					continue;
				}
			}
			if(chk ==0){
				alert("CFS신용 고용형태 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_cd_employ_type").val("N");
		}
		
		
		if( $("#chk_cd_loan_use").is(":checked") == true){
			$("#chk_cd_loan_use").val("Y");
			var chk = 0;
			for(var i=1; i<8; i++){
				if($('#cd_loan_use'+i).is(":checked") == true){
					chk = chk+i;
					continue;
				}
			}
			if(chk ==0){
				alert("CFS신용 자금용도 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_cd_loan_use").val("N");
		}
		
		if( $("#chk_amt_year_income").is(":checked") == true){
			$("#chk_amt_year_income").val("Y");
			if( ($("#amt_year_income").val() =="") || ($("#ou_year_income option:selected").val() == "")){
				alert("CFS신용 연소득 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_amt_year_income").val("N");
		}
		
 		if( $("#chk_amt_year_sale").is(":checked") == true){
			if( ($("#amt_year_sale").val() =="") || ($("#ou_sale_income option:selected").val() == "")){
				alert("CFS신용 연매출액(사업자)를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_amt_year_sale").val("N");
		}
 		
 		
 		if( $("#chk_ymd_start_comp").is(":checked") == true){
 			$("#chk_ymd_start_comp").val("Y");
			if( $("#ymd_start_comp").val() =="" ){
				alert("CFS신용 입사일자 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_ymd_start_comp").val("N");
		}
 		
		 if( $("#chk_no_job_year").is(":checked") == true){
			 $("#chk_no_job_year").val("Y");
			if( $("#no_job_year").val() =="" ){
				alert("CFS신용 근속연수 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_no_job_year").val("N");
		}
 		
 		if( $("#chk_cd_live_type").is(":checked") == true){
 			$("#chk_cd_live_type").val("Y");
			var chk = "";
			for(var i=1; i<8; i++){
				chk += $('#cd_live_type'+i+':checked').val();
			}
			if( chk == null ){
				alert("CFS신용 주거소유형태 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_cd_live_type").val("N");
		}
 		
 		if( $("#chk_cd_house_type").is(":checked") == true){
 			$("#chk_cd_house_type").val("Y");
			var chk = 0;
			for(var i=1; i<10; i++){
				if($('#cd_house_type'+i).is(":checked") == true){
					chk = chk+i;
					continue;
				}
			}
			if(chk == 0){
				alert("CFS신용 주거소유형태 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_cd_house_type").val("N");
		}
 		
 		if( $("#chk_yn_proof_income").is(":checked") == true){
 			$("#chk_yn_proof_income").val("Y");
			var chk = "";
			for(var i=1; i<10; i++){
 				chk += $('#yn_proof_income'+i+':checked').val();
			}
			if( chk == null ){
				alert("CFS신용 소득증빙여부 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_yn_proof_income").val("N");
		}
 		
 		if( $("#chk_day_delay_6month").is(":checked") == true){
 			$("#chk_day_delay_6month").val("Y");
 			if( $("#day_delay_6month").val() =="" ){
				alert("CFS신용 연체여부(최근 6개월) 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_day_delay_6month").val("N");
		}
 		
 		if( $("#chk_day_delay_12month").is(":checked") == true){
 			$("#chk_day_delay_12month").val("Y");
 			if( $("#day_delay_12month").val() =="" ){
				alert("CFS신용 연체여부(최근 1년) 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_day_delay_12month").val("N");
		}
 		
 		if( $("#chk_grade_kcb").is(":checked") == true){
 			$("#chk_grade_kcb").val("Y");
 			if( $("#grade_kcb").val() =="" ){
				alert("CFS신용 신용등급(KCB) 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_grade_kcb").val("N");
		}
 		
 		if( $("#chk_grade_nice").is(":checked") == true){
 			$("#chk_grade_nice").val("Y");
 			if( $("#grade_nice").val() =="" ){
				alert("CFS신용 신용등급(NICE) 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
		}else{
			$("#chk_grade_nice").val("N");
		}
     	
 		if( $("#chk_dti_grade").is(":checked") == true){
 			$("#chk_dti_grade").val("Y");
 			var chk = "";
 			for(var i=1; i<11; i++){
				chk = chk+ $('#dti_grade'+i).val();
			}
			if( chk == "" ){
				alert("CFS신용 DTI 체크를 풀어주시거나 값을 입력해주세요.");
				return false;
			}
 		}else{
 			$("#chk_dti_grade").val("N");
 		} 
	}
	
</script>