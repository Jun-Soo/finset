<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
function procPersonNice() {
	window.setupValidateForm( frmPersonNice );
	if ( !frmPersonNice.validateForm() ) return false;
	
	var data = frmPersonNice.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/person/procPersonNice.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadPersonInfo("${person.no_person}");
		},
		error : function (e) {
			alert(e.responseText);
			hotkey = false;
		}
	});
}

function gradeFocus(){
	if(event.keyCode == 9){
		$("#test2").attr("tabindex", -1).focus();
	}
}
</script>
	<form name="frmPersonNice" id="frmPersonNice">
	<input type="hidden" name="no_person" value="${person.no_person}"/>
	<input type="hidden" name="yn_modal" value="${personForm.yn_modal}"/>
	<div class="panel panel-primary">
		<div class="panel-heading">NICE - CB스코어, 서브프라임 스코어, 채무건전성 스코어</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<colgroup>
				<col width="9%">
				<col width="6%">
				<col width="5">
				<col width="9%">
				<col width="6%">
				<col width="5">
				<col width="9%">
				<col width="6%">
				<col width="5">
				<col width="9%">
				<col width="6%">
				<col width="5">
				<col width="7%">
				<col width="8%">
				<col width="5">
			</colgroup>
			<tbody>
				<tr>
					<th rowspan="2">CB스코어</span></th>
					<th><span class="required">신용등급</span></th>
					<td>
						<input type="text" class="form-control width-45" tabindex="1" name="score_cb" id="score_cb" value="${personNice.score_cb}" validate="required;label:CB스코어(신용등급);numeric;"/>
					</td>
					<th rowspan="2">서브프라임 스코어</span></th>
					<th><span class="required">신용등급</span></th>
					<td>
						<input type="text" class="form-control width-45" tabindex="3" name="score_subprime" id="score_subprime" value="${personNice.score_subprime}" validate="required;label:서브프라임스코어(신용등급);numeric;"/>
					</td>
					<th rowspan="2">채무건전성 스코어</span></th>
					<th><span>신용등급</span></th>
					<td>
						<input type="text" class="form-control width-45" tabindex="5" name="score_bankrupt" id="score_bankrupt" value="${personNice.score_bankrupt}" validate="required;label:채무건전성스코어(신용등급);numeric;"/>	
					</td>
				</tr>
				<tr>
					<th>신용평점</span></th>
					<td>
						<input type="text" class="form-control width-65" tabindex="2" name="avg_credit" id="avg_credit" value="${personNice.avg_credit}" validate="required;label:CB스코어(신용평점);numeric;"/>	
					</td>
					<th><span>신용평점</span></th>
					<td>
						<input type="text" class="form-control width-65" tabindex="4" name="avg_subprime" id="avg_subprime" value="${personNice.avg_subprime}" validate="required;label:서브프라임스코어(신용평점);numeric;"/>
					</td>

					<th><span>신용평점</span></th>
					<td>
						<input type="text" class="form-control width-65" tabindex="6" name="avg_bankrupt" id="avg_bankrupt" value="${personNice.avg_bankrupt}" onkeydown="gradeFocus();" validate="required;label:채무건전성스코어(신용평점);numeric;"/>	
					</td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>		
	<div class="panel panel-primary" id="test2">
		<div class="panel-heading">NICE 채무불이행정보</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<colgroup>
				<col width="15%">
				<col width="10%">
				<col width="15%">
				<col width="10%">
				<col width="15%">
				<col width="10%">
				<col width="15%">
				<col width="10%">
			</colgroup>		
			<tbody>
				<tr>
					<th><span>채무불이행(은행연합회)-건수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="default_bank" id="default_bank" value="${personNice.default_bank}" validate="required;label:채무불이행(은행연합회)-건수;numeric;"/>	
					</td>
					<th><span>공공정보(은행연합회)-건수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="info_public" id="info_public" value="${personNice.info_public}" validate="required;label:공공정보(은행연합회)-건수;numeric;"/>	
					</td>
					<th><span>금융질서문란(은행연합회)-건수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="disorder_fin" id="disorder_fin" value="${personNice.disorder_fin}" validate="required;label:금융질서문란(은행연합회)-건수;numeric;"/>	
					</td>
					<th><span>채무불이행(신용정보사)-건수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="default_credit" id="default_credit" value="${personNice.default_credit}" validate="required;label:채무불이행(신용정보사)-건수;numeric;"/>	
					</td>

				</tr>
			</tbody>
		</table>
		</div>
	</div>		

	<div class="panel panel-primary">
		<div class="panel-heading">NICE 개설정보(은행연합회)</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody>
				<tr>
					<th><span>총대출건수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="cnt_all_loan" id="cnt_all_loan" value="${personNice.cnt_all_loan}" validate="required;label:총대출건수;numeric;"/>
					</td>
					<th><span>대출금액</span></th>
					<td>
						<input type="text" class="form-control width-95" name="amt_loan" id="amt_loan" value="${personNice.amt_loan}" validate="required;label:대출금액;numeric;"/>
					</td>
					<th><span>현금서비스건수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="cnt_cash_service" id="cnt_cash_service" value="${personNice.cnt_cash_service}" validate="required;label:현금서비스건수;numeric;"/>
					</td>
					<th><span>현금서비스금액</span></th>
					<td>
						<input type="text" class="form-control width-95" name="amt_cash_service" id="amt_cash_service" value="${personNice.amt_cash_service}" validate="required;label:현금서비스금액;numeric;"/>
					</td>
					<th><span>보증건수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="cnt_guarantee" id="cnt_guarantee" value="${personNice.cnt_guarantee}" validate="required;label:보증건수;numeric;"/>
					</td>
					<th><span>보증금액</span></th>
					<td>
						<input type="text" class="form-control width-95" name="amt_guarantee" id="amt_guarantee" value="${personNice.amt_guarantee}" validate="required;label:보증금액;numeric;"/>
					</td>
					<th><span>신용카드수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="cnt_creditcard" id="cnt_creditcard" value="${personNice.cnt_creditcard}" validate="required;label:신용카드수;numeric;"/>
					</td>
					<th><span>연체건수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="cnt_delay" id="cnt_delay" value="${personNice.cnt_delay}" validate="required;label:연체건수;numeric;"/>
					</td>
					<th><span>대부사용건수</span></th>
					<td>
						<input type="text" class="form-control width-45" name="cnt_lend" id="cnt_lend" value="${personNice.cnt_lend}" validate="required;label:대부사용건수;numeric;"/>
					</td>
					<th><span>대부사용금액</span></th>
					<td>
						<input type="text" class="form-control width-95" name="amt_lend" id="amt_lend" value="${personNice.amt_lend}" validate="required;label:대부사용금액;numeric;"/>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	</form>
	<div class="align-r">
		<button type="submit" class="btn btn-default btn-xs" onclick="procPersonNice();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>NICE 기본정보 저장</button>
	</div>