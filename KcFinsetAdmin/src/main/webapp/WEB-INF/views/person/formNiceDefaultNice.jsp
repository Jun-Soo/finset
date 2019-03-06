<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
function resetForm() {
	if(confirm("신규입력을 위해 모든 입력칸이 비워집니다. 진행하시겠습니까?") == false) return;
	var f = document.frmNiceDefaultNice;
	f.no_nice_default_nice.value = "";
	f.regist_reason.value = "";
	f.occur_institution.value = "";
	f.occur_team.value = "";
	f.dt_occur.value = "";
	f.dt_offer.value = "";
	f.amt_delay.value = "";
}
</script>
</head>
	<form name="frmNiceDefaultNice" id="frmNiceDefaultNice">
	<input type="hidden" name="no_person" id="no_person" value="${no_person}"/>
	<input type="hidden" name="no_nice_default_nice" value="${personNiceDefaultNiceVO.no_nice_default_nice}"/>
	<div class="panel panel-primary">
		<div class="panel-heading">NICE대출 입력값</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody> 
				<tr>
					<th><span>등록사유</span></th>
					<td><input type="text" class="form-control width-120" name="regist_reason" id="regist_reason" value="${personNiceDefaultNiceVO.regist_reason}"/></td>
					<th><span>발생기관</span></th>
          			<td><input type="text" class="form-control width-120" name="occur_institution" id="occur_institution" value="${personNiceDefaultNiceVO.occur_institution}"  validate="label:발생기관명"/></td>
					<th><span>발생지점</span></th>
          			<td><input type="text" class="form-control width-120" name="occur_team" id="occur_team" value="${personNiceDefaultNiceVO.occur_institution}"  validate="label:발생지점"/></td>
					<th><span>발생일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_occur" id="dt_occur" value="${personNiceDefaultNiceVO.dt_occur}" validate="date; label:발생일" maxlength="10"/>
						</div>
					</td>
				</tr>
				<tr>
					<th><span>제공일</span></th>
          			<td>
          				<div class="input-daterange input-group date-w" id="datepicker">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
							<input type="text" class="input-sm form-control" name="dt_offer" id="dt_offer" value="${personNiceDefaultNiceVO.dt_offer}" validate="date; label:제공일" maxlength="10"/>
						</div>
					</td>
					<th><span>연체금액</span></th>
					<td colspan="5"><input type="text" class="form-control width-120" name="amt_delay" id="amt_delay" value="${personNiceDefaultNiceVO.amt_delay}" validate="label:연체금액;numeric;" maxlength="8"/></td>
          		</tr>
			</tbody>
		</table>
		</div>
	</div>
	<div class="align-r">
		<a role="button" class="btn btn-default btn-xs" onclick="resetForm();"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>초기화</a>
		
		<a role="button" class="btn btn-default btn-xs" onclick="procPersonNiceDefaultNice();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>NICE채무불이행(NICE) 입력값 저장</a>
	</div>
	<br><br><br><br>
	</form>