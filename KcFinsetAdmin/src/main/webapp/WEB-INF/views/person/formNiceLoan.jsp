<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$('.selectpicker').selectpicker();
</script>
</head>
	<form name="frmNiceLoan" id="frmNiceLoan">
	<input type="hidden" name="cd_goods_type"/>
	<input type="hidden" name="cd_fc"/>
	<input type="hidden" name="path_common_file" value="${attach.path_save_file}"/>
	<input type="hidden" name="url_common_file" value="${attach.url_attach}"/>
	<input type="hidden" name="nm_common_file" value="${attach.nm_save_file}"/>
	<input type="hidden" name="dis_goods"/>
	<input type="hidden" name="no_person" id="no_person" value="${no_person}"/>
	<input type="hidden" name="no_niceloan" value="${personNiceLoanVO.no_niceloan}"/>
	<input type="hidden" name="yn_modal" value="Y"/>
	<div class="panel panel-primary">
		<div class="panel-heading">NICE대출 입력값</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody> 
				<tr>
          			<th><span>업권</span></th>
          			<td>
          				<select name="cd_fin" id="cd_fin" class="selectpicker">
							${ufn:makeOptions("cd_fin", "업권", personNiceLoanVO.cd_fin)}
						</select>
					</td>
          			<th><span>발생기관명</span></th>
          			<td><input type="text" class="form-control width-120" name="occur_institution" id="occur_institution" value="${personNiceLoanVO.occur_institution}"  validate="required;label:발생기관명"/></td>
          			<th><span>개설일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_start" id="dt_start" value="${personNiceLoanVO.dt_start}" validate="required;date; label:개설일" maxlength="10"/>
						</div>
					</td>
          			<th><span>대출금액(단위:천원)</span></th>
          			<td><input type="text" class="form-control width-120" name="amt_loan" id="amt_loan" value="${personNiceLoanVO.amt_loan}" validate="required;label:대출금액;numeric;" maxlength="10"/></td>
          			<th><span>대출구분</span></th>
          			<td>
          				<select name="div_loan" id="div_loan" class="selectpicker">
							${ufn:makeOptions("div_loan", "대출구분", personNiceLoanVO.div_loan)}
						</select>
          			</td>
          			<th><span>변경일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_change" id="dt_change" value="${personNiceLoanVO.dt_change}" validate="date;label:변경일" maxlength="10"/>
						</div>
          			</td>
          		</tr>
          		<tr>
          			<th><span>만기일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_max" id="dt_max" value="${personNiceLoanVO.dt_max}" validate="required;date; label:만기일" maxlength="10"/>
						</div>
          			</td>
          			<th><span>대출종류</span></th>
          			<td>
          			<%-- <input type="text" class="form-control width-120" name="type_loan" id="type_loan" value="${personNiceLoanVO.type_loan}"/> --%>
          				<select name="type_loan" id="type_loan" class="selectpicker">
							${ufn:makeOptions("type_loan", "대출종류", personNiceLoanVO.type_loan)}
						</select>          			
          			</td>
          			<th><span>최초개설금액</span></th>
          			<td><input type="text" class="form-control width-120" name="amt_frt" id="amt_frt" value="${personNiceLoanVO.amt_frt}" validate="required;label:최초개설금액;numeric;" maxlength="8"/></td>
          			<th><span>대표업권</span></th>
          			<td>
          				<select name="represent_fin" id="represent_fin" class="selectpicker">
							${ufn:makeOptions("represent_fin", "대표업권", personNiceLoanVO.represent_fin)}
						</select>  
          			</td>
          			<th><span>대출구분2</span></th>
          			<td colspan="3">
          				<select name="div_loan2" id="div_loan2" class="selectpicker">
							${ufn:makeOptions("div_loan2", "대출구분2", personNiceLoanVO.div_loan2)}
						</select>  
          			</td>
          		</tr>
          		<tr>
          			<th><span>잔존예상개월수</span></th>
          			<td><input type="text" class="form-control width-120" name="est_remain" id="est_remain" value="${personNiceLoanVO.est_remain}" readonly="readonly"/></td>
          			<th><span>사용개월수</span></th>
          			<td><input type="text" class="form-control width-120" name="cnt_use_month" id="cnt_use_month" value="${personNiceLoanVO.cnt_use_month}" readonly="readonly"/></td>
          			<th><span>총예상대출개월</span></th>
          			<td><input type="text" class="form-control width-120" name="total_predict_loan_month" id="total_predict_loan_month" value="${personNiceLoanVO.total_predict_loan_month}" readonly="readonly"/></td>
          			<th><span>평균예상금리</span></th>
          			<td><input type="text" class="form-control width-120" name="rto_avg_interest" id="rto_avg_interest" value="${personNiceLoanVO.rto_avg_interest}" readonly="readonly"/></td>
          			<th><span>월불입금</span></th>
          			<td colspan="3"><input type="text" class="form-control width-120" name="amt_pay_month" id="amt_pay_month" value="${personNiceLoanVO.amt_pay_month}" readonly="readonly"/></td>
          		</tr>
			</tbody>
		</table>
		</div>
	</div>
	<div class="align-r">
		<a role="button" class="btn btn-default btn-xs" onclick="preCalPersonNiceLoan();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>월불입금 미리계산</a>
		<a role="button" class="btn btn-default btn-xs" onclick="procPersonNiceLoan();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>NICE대출 입력값 저장</a>
	</div>
	<br><br><br><br>
	</form>