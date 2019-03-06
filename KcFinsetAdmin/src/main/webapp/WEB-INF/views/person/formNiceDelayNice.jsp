<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
function resetForm() {
	if(confirm("신규입력을 위해 모든 입력칸이 비워집니다. 진행하시겠습니까?") == false) return;
	var f = document.frmNiceDelayNice;
	f.no_nice_delay_nice.value = "";
	f.div_goods.value = "";
	f.div_delay.value = "";
	f.occur_institution.value = "";
	f.occur_team.value = "";
	f.dt_frt_delay.value = "";
	f.cnt_occur_delay_dt.value = "";
	f.div_regist.value = "";
	f.amt_frt_delay.value = "";
	f.amt_delay.value = "";
	f.amt_remain.value = "";
	f.amt_limit_loan.value = "";
}
</script>
</head>
	<form name="frmNiceDelayNice" id="frmNiceDelayNice">
	<input type="hidden" name="no_person" id="no_person" value="${no_person}"/>
	<input type="hidden" name="no_nice_delay_nice" value="${personNiceDelayNiceVO.no_nice_delay_nice}"/>
	<div class="panel panel-primary">
		<div class="panel-heading">NICE연체정보(NICE) 입력값</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody> 
				<tr>
					<th><span>상품구분</span></th>
					<td><input type="text" class="form-control width-120" name="div_goods" id="div_goods" value="${personNiceDelayNiceVO.div_goods}" /></td>
					<th><span>연체구분</span></th>
					<td><input type="text" class="form-control width-120" name="div_delay" id="div_delay" value="${personNiceDelayNiceVO.div_delay}" /></td>
					<th><span>발생기관명</span></th>
					<td><input type="text" class="form-control width-120" name="occur_institution" id="occur_institution" value="${personNiceDelayNiceVO.occur_institution}"  validate="label:발생기관명"/></td>
					<th><span>발생지점명</span></th>
					<td><input type="text" class="form-control width-120" name="occur_team" id="occur_team" value="${personNiceDelayNiceVO.occur_institution}"  validate="label:발생지점"/></td>
				</tr>
				<tr>
					<th><span>최초연체발생일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_frt_delay" id="dt_frt_delay" value="${personNiceDelayNiceVO.dt_frt_delay}" validate="date;label:최초연체발생일" maxlength="10"/>
						</div>
          			</td>
					<th><span>연체발생일</span></th>
					<td><input type="text" class="form-control width-120" name="cnt_occur_delay_dt" id="cnt_occur_delay_dt" value="${personNiceDelayNiceVO.cnt_occur_delay_dt}" validate="label:연체발생일;numeric;" maxlength="5"/></td>
					<th><span>등록구분</span></th>
					<td><input type="text" class="form-control width-120" name="div_regist" id="div_regist" value="${personNiceDelayNiceVO.div_regist}" /></td>
					<th><span>최초연체금액</span></th>
					<td><input type="text" class="form-control width-120" name="amt_frt_delay" id="amt_frt_delay" value="${personNiceDelayNiceVO.amt_frt_delay}" validate="label:최초연체금액;numeric;" maxlength="8"/></td>
				</tr>
				<tr>
					<th><span>연체금액</span></th>
					<td><input type="text" class="form-control width-120" name="amt_delay" id="amt_delay" value="${personNiceDelayNiceVO.amt_delay}" validate="label:연체금액;numeric;" maxlength="8"/></td>
					<th><span>잔액</span></th>
					<td><input type="text" class="form-control width-120" name="amt_remain" id="amt_remain" value="${personNiceDelayNiceVO.amt_remain}" validate="label:잔액;numeric;" maxlength="8"/></td>
					<th><span>한도</span></th>
					<td colspan="3"><input type="text" class="form-control width-120" name="amt_limit_loan" id="amt_limit_loan" value="${personNiceDelayNiceVO.amt_limit_loan}" validate="label:한도;numeric;" maxlength="8"/></td>
          		</tr>
			</tbody>
		</table>
		</div>
	</div>
	<div class="align-r">
		<a role="button" class="btn btn-default btn-xs" onclick="resetForm();"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>초기화</a>
		
		<a role="button" class="btn btn-default btn-xs" onclick="procPersonNiceDelayNice();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>NICE연체정보(NICE)입력값 저장</a>
	</div>
	<br><br><br><br>
	</form>