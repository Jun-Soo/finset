<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
function resetForm() {
	if(confirm("신규입력을 위해 모든 입력칸이 비워집니다. 진행하시겠습니까?") == false) return;
	var f = document.frmNiceDefaultBank;
	f.no_nice_default_bank.value = "";
	f.div_data.value = "";
	f.cd_reason.value = "";
	f.div_reason.value = "";
	f.occur_institution.value = "";
	f.dt_occur.value = "";
	f.dt_regist.value = "";
	f.dt_lift.value = "";
	f.div_lift.value = "";
	f.occur_team.value = "";
	f.amt_regist.value = "";
	f.amt_delay.value = "";
}
</script>
</head>
	<form name="frmNiceDefaultBank" id="frmNiceDefaultBank">
	<input type="hidden" name="no_person" id="no_person" value="${no_person}"/>
	<input type="hidden" name="no_nice_default_bank" value="${personNiceDefaultBankVO.no_nice_default_bank}"/>
	<div class="panel panel-primary">
		<div class="panel-heading">NICE채무불이행(은행연합회) 입력값</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody> 
				<tr>
					<th><span>정보구분</span></th>
					<td><input type="text" class="form-control width-120" name="div_data" id="div_data" value="${personNiceDefaultBankVO.div_data}" /></td>
					<th><span>사유코드</span></th>
					<td><input type="text" class="form-control width-120" name="cd_reason" id="cd_reason" value="${personNiceDefaultBankVO.cd_reason}" /></td>
					<th><span>사유구분</span></th>
					<td><input type="text" class="form-control width-120" name="div_reason" id="div_reason" value="${personNiceDefaultBankVO.div_reason}" /></td>
					<th><span>발생기관</span></th>
          			<td><input type="text" class="form-control width-120" name="occur_institution" id="occur_institution" value="${personNiceDefaultBankVO.occur_institution}"  validate="label:발생기관명"/></td>
				</tr>
				<tr>
					<th><span>발생일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_occur" id="dt_occur" value="${personNiceDefaultBankVO.dt_occur}" validate="date; label:발생일" maxlength="10"/>
						</div>
					</td>
					<th><span>등록일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_regist" id="dt_regist" value="${personNiceDefaultBankVO.dt_regist}" validate="date; label:등록일" maxlength="10"/>
						</div>
					</td>
					<th><span>해제일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_lift" id="dt_lift" value="${personNiceDefaultBankVO.dt_lift}" validate="date; label:해제일" maxlength="10"/>
						</div>
					</td>
					<th><span>해제구분</span></th>
					<td><input type="text" class="form-control width-120" name="div_lift" id="div_lift" value="${personNiceDefaultBankVO.div_lift}" /></td>
				</tr>
				<tr>
					<th><span>발생지점</span></th>
					<td><input type="text" class="form-control width-120" name="occur_team" id="occur_team" value="${personNiceDefaultBankVO.occur_team}" /></td>
					<th><span>등록금액</span></th>
					<td><input type="text" class="form-control width-120" name="amt_regist" id="amt_regist" value="${personNiceDefaultBankVO.amt_regist}" validate="label:등록금액;numeric;" maxlength="8"/></td>
					<th><span>연체금액</span></th>
					<td colspan="3"><input type="text" class="form-control width-120" name="amt_delay" id="amt_delay" value="${personNiceDefaultBankVO.amt_delay}" validate="label:연체금액;numeric;" maxlength="8"/></td>
          		</tr>
			</tbody>
		</table>
		</div>
	</div>
	<div class="align-r">
		<a role="button" class="btn btn-default btn-xs" onclick="resetForm();"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>초기화</a>
		
		<a role="button" class="btn btn-default btn-xs" onclick="procPersonNiceDefaultBank();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>NICE채무불이행(은행연합회) 입력값 저장</a>
	</div>
	<br><br><br><br>
	</form>