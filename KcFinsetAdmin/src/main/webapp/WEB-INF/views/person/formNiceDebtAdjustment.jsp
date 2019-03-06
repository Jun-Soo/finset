<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
function resetForm() {
	if(confirm("신규입력을 위해 모든 입력칸이 비워집니다. 진행하시겠습니까?") == false) return;
	var f = document.frmNiceDebtAdjustment;
	f.no_nice_debt_adjustment.value = "";
	f.occur_institution.value = "";
	f.status_account.value = "";
	f.dt_start.value = "";
	f.amt_all_frt.value = "";
	f.amt_remain.value = "";
	f.dt_frt_delay.value = "";
	f.cnt_occur_delay_dt.value = "";
	f.cnt_all_pay.value = "";
	f.cnt_real_pay.value = "";
	f.cnt_delay.value = "";
	f.amt_delay.value = "";
	f.yn_reduce_debt.value = "";
}
</script>
</head>
	<form name="frmNiceDebtAdjustment" id="frmNiceDebtAdjustment">
	<input type="hidden" name="no_person" id="no_person" value="${no_person}"/>
	<input type="hidden" name="no_nice_debt_adjustment" value="${listPersonNiceDebtAdjustment.no_nice_debt_adjustment}"/>
	<div class="panel panel-primary">
		<div class="panel-heading">NICE채무조정 및 상환정보 입력값</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody> 
				<tr>
					<th><span>발생기관명</span></th>
					<td><input type="text" class="form-control width-120" name="occur_institution" id="occur_institution" value="${listPersonNiceDebtAdjustment.occur_institution}"  validate="label:발생기관명"/></td>
					<th><span>계좌상태</span></th>
					<td><input type="text" class="form-control width-120" name="status_account" id="status_account" value="${listPersonNiceDebtAdjustment.status_account}"/></td>
					<th><span>개설일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_start" id="dt_start" value="${listPersonNiceDebtAdjustment.dt_start}" validate="date; label:개설일" maxlength="10"/>
						</div>
					</td>
				</tr>
				<tr>
					<th><span>개설총금액(단위:천원)</span></th>
					<td><input type="text" class="form-control width-120" name="amt_all_frt" id="amt_all_frt" value="${listPersonNiceDebtAdjustment.amt_all_frt}" validate="label:개설총금액;numeric;" maxlength="10"/></td>
					<th><span>잔액(단위:천원)</span></th>
					<td><input type="text" class="form-control width-120" name="amt_remain" id="amt_remain" value="${listPersonNiceDebtAdjustment.amt_remain}" validate="label:잔액;numeric;" maxlength="10"/></td>
					<th><span>최초연체발생일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_frt_delay" id="dt_frt_delay" value="${listPersonNiceDebtAdjustment.dt_frt_delay}" validate="date; label:최초연체발생일" maxlength="10"/>
						</div>
					</td>
				</tr>
				<tr>
					<th><span>연체발생일</span></th>
					<td><input type="text" class="form-control width-120" name="cnt_occur_delay_dt" id="cnt_occur_delay_dt" value="${listPersonNiceDebtAdjustment.cnt_occur_delay_dt}" validate="label:연체발생일;numeric;" maxlength="5"/></td>
					<th><span>총납입회차</span></th>
					<td><input type="text" class="form-control width-120" name="cnt_all_pay" id="cnt_all_pay" value="${listPersonNiceDebtAdjustment.cnt_all_pay}"/></td>
					<th><span>실납입회차</span></th>
					<td><input type="text" class="form-control width-120" name="cnt_real_pay" id="cnt_real_pay" value="${listPersonNiceDebtAdjustment.cnt_real_pay}"/></td>
				</tr>
				<tr>
					<th><span>연체회차</span></th>
					<td><input type="text" class="form-control width-120" name="cnt_delay" id="cnt_delay" value="${listPersonNiceDebtAdjustment.cnt_delay}"/></td>
					<th><span>연체금액(단위:천원)</span></th>
					<td><input type="text" class="form-control width-120" name="amt_delay" id="amt_delay" value="${listPersonNiceDebtAdjustment.amt_delay}" validate="label:연체금액;numeric;" maxlength="10"/></td>
					<th><span>채무감면유무</span></th>				
					<td><input type="text" class="form-control width-120" name="yn_reduce_debt" id="yn_reduce_debt" value="${listPersonNiceDebtAdjustment.yn_reduce_debt}"/></td>
          		</tr>
			</tbody>
		</table>
		</div>
	</div>
	<div class="align-r">
		<a role="button" class="btn btn-default btn-xs" onclick="resetForm();"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>초기화</a>
		
		<a role="button" class="btn btn-default btn-xs" onclick="procPersonNiceDebtAdjustment();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>NICE채무조정 및 상환정보 저장</a>
	</div>
	<br><br><br><br>
	</form>