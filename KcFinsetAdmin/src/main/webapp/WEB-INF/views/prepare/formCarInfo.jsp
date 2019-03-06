<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		
		// SelectPicker
		$('.selectpicker').selectpicker();
		
		window.setupValidateForm(frmCarInfo);
		
	});
	
	//차량정보 삽입수정
	function procCarInfo() {
		if(!frmCarInfo.validateForm()) return false;
		var data = frmCarInfo.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/car/procCarInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				$("#modal-lg").modal('hide');
				if(parseInt(returnData.cd_result,10) == 0) {
					loadPrepareForm();
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	//폼초기화
	function reset() {
		document.getElementById("frmCarInfo").reset();
	}
	
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="largeModalLabel">차량담보 등록</h4>
</div>
<div class="modal-body">
	<form name="frmCarInfo" id="frmCarInfo">
	<input type="hidden" name="no_prepare" value="${carForm.no_prepare}" />
	<div class="panel panel-primary">
		<div class="panel-heading">차량정보</div>
		<div class="panel-collapse">
			
				<table class="table table-classic tbl-info">
					<colgroup>
						<col width="10%">
						<col width="23%">
						<col width="10%">
						<col width="23%">
						<col width="10%">
						<col width="23%">
					</colgroup>
					<tbody>
						<tr>
							<th>차량번호</th>
							<td>
								<input type="text" class="form-control width-120" name="no_reg_car" value="${carVO.no_reg_car}" validate="label:차량번호;maxbt:30" maxlength="30"/>
							</td>
							<th>차대번호</th>
							<td>
								<input type="text" class="form-control width-150" name="no_id_car" value="${carVO.no_id_car}" validate="label:차대번호;maxbt:30" maxlength="30"/>
							</td>
							<th>차종</th>
							<td>
								<input type="text" class="form-control width-120" name="nm_kind_car" value="${carVO.nm_kind_car}" validate="label:차종;maxbt:60" maxlength="60"/>
							</td>		
						</tr>
						<tr>
							<th>차명</th>
							<td>
								<input type="text" class="form-control width-120" name="nm_car" value="${carVO.nm_car}" validate="label:차명;maxbt:360" maxlength="60"/>
							</td>
							<th>용도</th>
							<td>
								<input type="text" class="form-control width-120" name="nm_used_car" value="${carVO.nm_used_car}" validate="label:용도;maxbt:100" maxlength="100"/>
							</td>
							<th>연료</th>
							<td>
								<select name="cd_fuel" class="selectpicker" id="cd_fuel" data-style="btn-primary" validate="label:연료종류">
									${ufn:makeOptions("cd_fuel","연료종류", carVO.cd_fuel)}
								</select>
							</td>
									
						</tr>
						<tr>
							<th>제조사명</th>
							<td>
								<input type="text" class="form-control width-120" name="nm_comp_car"  value="${carVO.nm_comp_car}" validate="label:제조사명;maxbt:60" maxlength="60"/>
							</td>
							<th>모델</th>
							<td colspan="3">
								<input type="text" class="form-control width-120" name="nm_model_car"  value="${carVO.nm_model_car}" validate="label:모델;maxbt:60" maxlength="60"/>
								<input type="text" class="form-control width-55 right" name="year_car"  value="${carVO.year_car}" validate="label:연식;maxbt:4" maxlength="4"/> 연식
							</td>
						</tr>
						<tr>
							<th>배기량</th>
							<td>
								<input type="text" class="form-control width-120 right" name="cc_car"  value="${carVO.cc_car}" validate="label:배기량;maxbt:10" maxlength="10"/> cc
							</td>
							<th>주행거리</th>
							<td>
								<input type="text" class="form-control width-120 right" name="mile_car"  value="${carVO.mile_car}" validate="label:주행거리;maxbt:10" maxlength="10"/> km
							</td>
							<th>변속기</th>
							<td>
								${ufn:makeRadioAndCheckBoxs("cd_gear", "cd_gear", "radio", carVO.cd_gear, 0)}
							</td>
						</tr>
						<tr>
							<th>차량색상</th>
							<td>
								<input type="text" class="form-control width-120" name="nm_color_car"  value="${carVO.nm_color_car}" validate="label:차량색상;maxbt:60" maxlength="60"/>
							</td>
							<th>신차가격</th>
							<td>
								<div class="input-group">
									<span class="input-group-addon">￦</span>
									<input type="text" class="form-control right" name="amt_new_car" value="${carVO.amt_new_car}" validate="label:신차가격;maxbt:10;money" maxlength="10"/>
								</div> 만원
							</td>
							<th>중고시세</th>
							<td>
								<div class="input-group">
									<span class="input-group-addon">￦</span>
									<input type="text" class="form-control right" name="amt_now_car" value="${carVO.amt_now_car}" validate="label:중고시세;maxbt:10;money" maxlength="10"/>
								</div> 만원
							</td>
						</tr>
						<tr>
							<th>사고유무</th>
							<td colspan="5">
								${ufn:makeRadioAndCheckBoxs("yn_accident", "yn_accident", "radio", carVO.yn_accident, 0)}
							</td>
						</tr>
						<tr>
							<th>사고내용</th>
							<td colspan="5">
								<input type="text" class="form-control" name="memo_car" value="${carVO.memo_car}"  validate="label:사고내용;maxbt:2000" maxlength="2000"/>
							</td>
						</tr>
						<tr>
							<th>보험회사</th>
							<td>
								<input type="text" class="form-control width-120" name="nm_insu_car" value="${carVO.nm_insu_car}"  validate="label:보험회사;maxbt:100" maxlength="100"/>
							</td>
							<th>보험종류</th>
							<td colspan="3">
								<input type="text" class="form-control width-120" name="nm_sort_insu_car" value="${carVO.nm_sort_insu_car}"  validate="label:보헙종류;maxbt:100" maxlength="100"/>
								<span class="pull-right">
									기간 : <input type="text" class="form-control width-45 right" name="cnt_term_insu_car" value="${carVO.cnt_term_insu_car}" validate="label:보험기간;maxbt:4" maxlength="4"/>개월 / 
									회차 : <input type="text" class="form-control width-45 right" name="cnt_rcpt_insu_car" value="${carVO.cnt_rcpt_insu_car}" validate="label:보험납부회차;maxbt:4" maxlength="4"/>회
								</span>
							</td>
						</tr>
						<tr>
							<th>할부기관</th>
							<td>
								<select name="cd_bank_insu_car" class="selectpicker" id="cd_bank_insu_car" data-style="btn-primary" validate="label:할부기관">
									${ufn:makeOptions("cd_bank_insu_car","할부기관", carVO.cd_bank_insu_car)}
								</select>
							</td>
							<th>총할부금액</th>
							<td colspan="3">
								<div class="input-group">
									<span class="input-group-addon">￦</span>
									<input type="text" class="form-control align-r" name="amt_loan_car" value="${carVO.amt_loan_car}" validate="label:총할부금액;maxbt:10;money" maxlength="10"/>
								</div> 만원
								<span class="pull-right">
									기간 : <input type="text" class="form-control width-45 right" name="cnt_total_loan_car" value="${carVO.cnt_total_loan_car}" validate="label:총할부기간;maxbt:4;money" maxlength="4"/>개월 / 
									회차 : <input type="text" class="form-control width-45 right" name="cnt_rcpt_loan_car" value="${carVO.cnt_rcpt_loan_car}" validate="label:납부회차;maxbt:4;money" maxlength="4"/>회
								</span>
							</td>
						</tr>
						<tr>
							<th>현재총잔액</th>
							<td>
								<div class="input-group">
									<span class="input-group-addon">￦</span>
									<input type="text" class="form-control right" name="amt_bal_loan_car" value="${carVO.amt_bal_loan_car}" validate="label현재총잔액;maxbt:10;money" maxlength="10"/>
								</div> 만원
							</td>
							<th>현재연체액</th>
							<td colspan="3">
								<div class="input-group">
									<span class="input-group-addon">￦</span>
									<input type="text" class="form-control right" name="amt_delay_loan_car" value="${carVO.amt_delay_loan_car}" validate="label:현재연체액;maxbt:10;money" maxlength="10"/>
								</div> 만원
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</div>

<div class="modal-footer">
	<button type="reset" class="btn btn-primary btn-xs" onclick="resetForm('frmCarInfo');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
	<button type="submit" class="btn btn-primary btn-xs" onclick="procCarInfo();"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 차량담보등록</button>
</div>
