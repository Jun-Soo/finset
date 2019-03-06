<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
//초기화
function resetForm() {
	if(confirm("신규입력을 위해 모든 입력칸이 비워집니다. 진행하시겠습니까?") == false) return;
	var f = document.frmNiceCashService;
	f.no_nice_cash_service.value = "";
	f.occur_institution.value = "";
	f.occur_team.value = "";
	f.dt_occur.value = "";
	f.amt_cash_service.value = "";
	f.dt_change.value = "";
}
</script>
</head>
	<form name="frmNiceCashService" id="frmNiceCashService">
	<input type="hidden" name="no_person" id="no_person" value="${no_person}"/>
	<input type="hidden" name="no_nice_cash_service" value="${personNiceCashServiceVO.no_nice_cash_service}"/>
	<div class="panel panel-primary">
		<div class="panel-heading">NICE현금서비스정보(은행연합회) 입력값</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody> 
				<tr>
          			<th><span>발생기관명</span></th>
          			<td><input type="text" class="form-control width-120" name="occur_institution" id="occur_institution" value="${personNiceCashServiceVO.occur_institution}"  validate="label:발생기관명"/></td>
          			<th><span>발생지점명</span></th>
          			<td><input type="text" class="form-control width-120" name="occur_team" id="occur_team" value="${personNiceCashServiceVO.occur_team}"  validate="label:발생지점명"/></td>
          			<th><span>발생일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_occur" id="dt_occur" value="${personNiceCashServiceVO.dt_occur}" validate="date; label:발생일" maxlength="10"/>
						</div>
					</td>
          			<th><span>금액(단위:천원)</span></th>
          			<td><input type="text" class="form-control width-120" name="amt_cash_service" id="amt_cash_service" value="${personNiceCashServiceVO.amt_cash_service}" validate="label:금액;numeric;" maxlength="10"/></td>
          			<th><span>변경일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_change" id="dt_change" value="${personNiceCashServiceVO.dt_change}" validate="date;label:변경일" maxlength="10"/>
						</div>
          			</td>
          		</tr>
			</tbody>
		</table>
		</div>
	</div>
	<div class="align-r">
		<a role="button" class="btn btn-default btn-xs" onclick="resetForm();"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>초기화</a>
		
		<a role="button" class="btn btn-default btn-xs" onclick="procPersonNiceCashService();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>NICE대출 입력값 저장</a>
	</div>
	<br><br><br><br>
	</form>