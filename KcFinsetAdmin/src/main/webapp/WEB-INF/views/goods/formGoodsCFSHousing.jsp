<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	// SelectPicker
	
	$('.selectpicker').selectpicker();
	
	window.setupValidateForm( frmGoodsDetails );
	
});

</script>
<script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
<div class="panel panel-default toggle-panel">
<input type="hidden" id="s_value">
	<div class="panel-collapse toggle-cont">
			<input type="hidden" name="nm_request_form" id="nm_request_form" value="${productInfo.nm_request_form}">
			<input type="hidden" name="url_request" id="url_request" value="${productInfo.url_request}">
			<input type="hidden" name="id_request" id="id_request" value="${productInfo.id_request}">
			
			<input type="hidden" name="chk_dti_grade" id="chk_dti_grade" value="">
			<input type="hidden" name="dti_grade1" id="dti_grade1" value="">
			<input type="hidden" name="dti_grade2" id="dti_grade2" value="">
			<input type="hidden" name="dti_grade3" id="dti_grade3" value="">
			<input type="hidden" name="dti_grade4" id="dti_grade4" value="">
			<input type="hidden" name="dti_grade5" id="dti_grade5" value="">
			<input type="hidden" name="dti_grade6" id="dti_grade6" value="">
			<input type="hidden" name="dti_grade7" id="dti_grade7" value="">
			<input type="hidden" name="dti_grade8" id="dti_grade8" value="">
			<input type="hidden" name="dti_grade9" id="dti_grade9" value="">
			<input type="hidden" name="dti_grade10" id="dti_grade10" value="">
			<input type="hidden" name="chk_grade_nice" id="chk_grade_nice" value="">
			<input type="hidden" name="grade_nice" id="grade_nice" value="">
			
			<!--CFS주택담보 start  -->
			<div class="panel panel-primary">
				<div class="panel-heading">CFS주택담보</div>		
				<div class="panel-collapse">
					<table class="table table-classic">
		 					<colgroup>
								<col width="30%">
								<col width="70%">
							</colgroup>
							<tbody> 
								<tr>
									<th><input type="checkbox" name="chk_age_loan" id="chk_age_loan" value="Y" <c:out value="${goodsInfo.chk_age_loan eq 'Y' ? 'checked' : ''}" />  /><label for="chk_age_loan">대출 가능 연령</label></th>
									<td>
										<input type="text" class="form-control width-100" name="age_loan_from" value="${goodsInfo.age_loan_from}" validate=" label:대출가능연령;" maxlength="2" onkeypress="keyNumeric(event);"/> ~
								
										<input type="text" class="form-control width-100" name="age_loan_to" value="${goodsInfo.age_loan_to}" validate=" label:대출가능연령;" maxlength="2" onkeypress="keyNumeric(event);"/>
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_amt_apply" id="chk_amt_apply" value="Y" <c:out value="${goodsInfo.chk_amt_apply eq 'Y' ? 'checked' : ''}" />/><label for="chk_amt_apply">신청금액(만원)</label></th>
									<td>
										<input type="text" class="form-control width-100" name="amt_apply_from" value="${goodsInfo.amt_apply_from}" validate=" label:신청금액;" maxlength="6" onkeypress="keyNumeric(event);"/> ~
								
										<input type="text" class="form-control width-100" name="amt_apply_to" value="${goodsInfo.amt_apply_to}" validate=" label:신청금액;" maxlength="6" onkeypress="keyNumeric(event);"/>
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_no_month_apply" id="chk_no_month_apply" value="Y" <c:out value="${goodsInfo.chk_no_month_apply eq 'Y' ? 'checked' : ''}" />/><label for="chk_no_month_apply">신청기간(개월)</label></th>
									<td>
										<input type="text" class="form-control width-100" name="no_month_apply_from" value="${goodsInfo.no_month_apply_from}" validate=" label:신청기간;" maxlength="2" onkeypress="keyNumeric(event);"/> ~
								
										<input type="text" class="form-control width-100" name="no_month_apply_to" value="${goodsInfo.no_month_apply_to}" validate=" label:신청기간;" maxlength="5" onkeypress="keyNumeric(event);"/>
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_cd_type_income" id="chk_cd_type_income" value="Y" <c:out value="${goodsInfo.chk_cd_type_income eq 'Y' ? 'checked' : ''}" />/><label for="chk_cd_type_income">소득 형태</label></th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_type_income", "cd_type_income", "checkbox", goodsInfo.cd_type_income, 0)}
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_cd_employ_type" id="chk_cd_employ_type" value="Y" <c:out value="${goodsInfo.chk_cd_employ_type eq 'Y' ? 'checked' : ''}" />/><label for="chk_cd_employ_type">고용 형태</label></th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_employ_type", "cd_employ_type", "checkbox", goodsInfo.cd_employ_type, 0)}
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_cd_loan_use" id="chk_cd_loan_use" value="Y" <c:out value="${goodsInfo.chk_cd_loan_use eq 'Y' ? 'checked' : ''}" />/><label for="chk_cd_loan_use">자금 용도</label></th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_loan_use", "cd_loan_use", "checkbox", goodsInfo.cd_loan_use, 0)}
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_amt_year_income" id="chk_amt_year_income" value="Y" <c:out value="${goodsInfo.chk_amt_year_income eq 'Y' ? 'checked' : ''}" />/><label for="chk_amt_year_income">연소득</label></th>
									<td>
										<input type="text" class="form-control width-100" name="amt_year_income" value="${goodsInfo.amt_year_income}" validate=" label:연소득;" maxlength="5" onkeypress="keyNumeric(event);"/> 만원
		
										<select name="ou_year_income"  data-width="20%" class="selectpicker" validate=" label:연소득 이상/이하;">
											${ufn:makeOptions("cd_ou","이상/이하", goodsInfo.ou_year_income)}
										</select>
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_amt_year_sale" id="chk_amt_year_sale" value="Y" <c:out value="${goodsInfo.chk_amt_year_sale eq 'Y' ? 'checked' : ''}" />/><label for="chk_amt_year_sale">연매출액(사업자)</label></th>
									<td>
										<input type="text" class="form-control width-100" name="amt_year_sale" value="${goodsInfo.amt_year_sale}" validate=" label:연매출액(사업자);" maxlength="6" onkeypress="keyNumeric(event);"/> 만원
										<select name="ou_sale_income"  data-width="20%" class="selectpicker" validate=" label:연매출 이상/이하;">
											${ufn:makeOptions("cd_ou","이상/이하", goodsInfo.ou_sale_income)}
										</select>
									</td>
								</tr>
								
								<tr >
									<th><input type="checkbox" name="chk_ymd_start_comp" id="chk_ymd_start_comp" value="Y" <c:out value="${goodsInfo.chk_ymd_start_comp eq 'Y' ? 'checked' : ''}" />/><label for="chk_ymd_start_comp">입사일자</label></th>
									<td colspan="2">
											<input type="text" class="form-control width-100" name="ymd_start_comp" value="${goodsInfo.ymd_start_comp}" validate="date; label:입사일자" maxlength="8"/> 자영업의 경우 사업개시일
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_no_job_year" id="chk_no_job_year" value="Y" <c:out value="${goodsInfo.chk_no_job_year eq 'Y' ? 'checked' : ''}" />/><label for="chk_no_job_year">근속 연수</label></th>
									<td>
										<input type="text" class="form-control width-100" name="no_job_year" value="${goodsInfo.no_job_year}" maxlength="5" onkeypress="keyNumeric(event);"/>개월 이상 (자영업자의 경우 영업기간)
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_cd_live_type" id="chk_cd_live_type" value="Y" <c:out value="${goodsInfo.chk_cd_live_type eq 'Y' ? 'checked' : ''}" />/><label for="chk_cd_live_type">주거소유형태</label></th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_live_type", "cd_live_type", "checkbox", goodsInfo.cd_live_type, 0)}
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_cd_house_type" id="chk_cd_house_type" value="Y" <c:out value="${goodsInfo.chk_cd_house_type eq 'Y' ? 'checked' : ''}" />/><label for="chk_cd_house_type">주거종류</label></th>
									<td>
										${ufn:makeRadioAndCheckBoxs("cd_house_type", "cd_house_type", "checkbox", goodsInfo.cd_house_type, 0)}
									</td>
								</tr>
								
								<tr>
								<th><input type="checkbox" name="chk_yn_proof_income" id="chk_yn_proof_income" value="Y" <c:out value="${goodsInfo.chk_yn_proof_income eq 'Y' ? 'checked' : ''}" />/><label for="chk_yn_proof_income">소득증빙여부</label></th>
									<td>
										${ufn:makeRadioAndCheckBoxs("yn_proof_income", "yn_proof_income", "radio", goodsInfo.yn_proof_income, 0)}
									</td>
								</tr>
								
								<tr>
									<th colspan="2"><input type="checkbox" name="chk_yn_4insu" id="chk_yn_4insu" value="Y" <c:out value="${goodsInfo.chk_yn_4insu eq 'Y' ? 'checked' : ''}" />/><label for="chk_yn_4insu">4대 보험 가엽여부(3개월 이상 납입)</label></th>
								</tr>
								
								<tr>
									<th colspan="2"><input type="checkbox" name="chk_yn_bad_credit" id="chk_yn_bad_credit" value="Y" <c:out value="${goodsInfo.chk_yn_bad_credit eq 'Y' ? 'checked' : ''}" />/><label for="chk_yn_bad_credit">은행연합회 신용관리정보(구 신용불량정보) 등재자</label></th>
								</tr>
								
								<tr>
									<th colspan="2"><input type="checkbox" name="chk_yn_delay_current" id="chk_yn_delay_current" value="Y" <c:out value="${goodsInfo.chk_yn_delay_current eq 'Y' ? 'checked' : ''}" />/><label for="chk_yn_delay_current">연체 여부(현재)</label></th>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_day_delay_6month" id="chk_day_delay_6month" value="Y" <c:out value="${goodsInfo.chk_day_delay_6month eq 'Y' ? 'checked' : ''}"  />/><label for="chk_day_delay_6month">연체 여부(최근 6개월)</label></th>
									<td colspan="3">
										<input type="text" class="form-control width-100" name="day_delay_6month" value="${goodsInfo.day_delay_6month}" maxlength="3" onkeypress="keyNumeric(event);"/>일 이상
									</td>
								</tr>

								<tr>
									<th><input type="checkbox" name="chk_day_delay_12month" id="chk_day_delay_12month" value="Y" <c:out value="${goodsInfo.chk_day_delay_12month eq 'Y' ? 'checked' : ''}"  />/><label for="chk_day_delay_12month">연체 여부(최근 1년)</label></th>
									<td colspan="3">
										<input type="text" class="form-control width-100" name="day_delay_12month" value="${goodsInfo.day_delay_12month}" maxlength="3" onkeypress="keyNumeric(event);"/>일 이상
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_grade_kcb" id="chk_grade_kcb" value="Y" <c:out value="${goodsInfo.chk_grade_kcb eq 'Y' ? 'checked' : ''}"  />/><label for="chk_grade_kcb">신용등급(KCB)</label></th>
									<td colspan="3">
										<input type="text" class="form-control width-100" name="grade_kcb" value="${goodsInfo.grade_kcb}" maxlength="3" onkeypress="keyNumeric(event);"/>이상
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_grade_ltv" id="chk_grade_ltv" value="Y" <c:out value="${goodsInfo.chk_grade_ltv eq 'Y' ? 'checked' : ''}"  />/><label for="chk_grade_ltv">LTV</label></th>
									<td colspan="3">
										<input type="text" class="form-control width-100" name="grade_ltv" value="${goodsInfo.grade_ltv}" maxlength="3" onkeypress="keyNumeric(event);"/>이하
									</td>
								</tr>
								
								<tr>
									<th><input type="checkbox" name="chk_grade_dti" id="chk_grade_dti" value="Y" <c:out value="${goodsInfo.chk_grade_dti eq 'Y' ? 'checked' : ''}"  />/><label for="chk_grade_dti">DTI</label></th>
									<td colspan="3">
										<input type="text" class="form-control width-100" name="grade_dti" value="${goodsInfo.grade_dti}" maxlength="3" onkeypress="keyNumeric(event);"/>이하
									</td>
								</tr>
								
							</tbody>
						</table>
				</div>
			</div>	
			<!--CFS주택담보 end  -->
<!-- 		</form> -->
	</div>
</div>

