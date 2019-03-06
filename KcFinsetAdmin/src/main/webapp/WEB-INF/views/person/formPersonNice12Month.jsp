<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<script type="text/javascript">
$(document).ready(function () {
	var avg_point_0 = Number('${resultNice12MonthVO.avg_point_0}');
	var avg_point_1 = Number('${resultNice12MonthVO.avg_point_1}');
	var avg_point_2 = Number('${resultNice12MonthVO.avg_point_2}');
	var avg_point_3 = Number('${resultNice12MonthVO.avg_point_3}');
	var avg_point_4 = Number('${resultNice12MonthVO.avg_point_4}');
	var avg_point_5 = Number('${resultNice12MonthVO.avg_point_5}');
	var avg_point_6 = Number('${resultNice12MonthVO.avg_point_6}');
	var avg_point_7 = Number('${resultNice12MonthVO.avg_point_7}');
	var avg_point_8 = Number('${resultNice12MonthVO.avg_point_8}');
	var avg_point_9 = Number('${resultNice12MonthVO.avg_point_9}');
	var avg_point_10 = Number('${resultNice12MonthVO.avg_point_10}');
	var avg_point_11 = Number('${resultNice12MonthVO.avg_point_11}');

	var grade_0 = Number('${resultNice12MonthVO.grade_0}');
	var grade_1 = Number('${resultNice12MonthVO.grade_1}');
	var grade_2 = Number('${resultNice12MonthVO.grade_2}');
	var grade_3 = Number('${resultNice12MonthVO.grade_3}');
	var grade_4 = Number('${resultNice12MonthVO.grade_4}');
	var grade_5 = Number('${resultNice12MonthVO.grade_5}');
	var grade_6 = Number('${resultNice12MonthVO.grade_6}');
	var grade_7 = Number('${resultNice12MonthVO.grade_7}');
	var grade_8 = Number('${resultNice12MonthVO.grade_8}');
	var grade_9 = Number('${resultNice12MonthVO.grade_9}');
	var grade_10 = Number('${resultNice12MonthVO.grade_10}');
	var grade_11 = Number('${resultNice12MonthVO.grade_11}');
	var avg_12month = ((avg_point_0 + avg_point_1 + avg_point_2 + avg_point_3 + avg_point_4 + avg_point_5 + avg_point_6 + avg_point_7 + avg_point_8 + avg_point_9 + avg_point_10 + avg_point_11) / 12).toFixed(2);
	var grade_12month = ((grade_0 + grade_1 + grade_2 + grade_3 + grade_4 + grade_5 + grade_6 + grade_7 + grade_8 + grade_9 + grade_10 + grade_11) / 12).toFixed(2);
	frmPersonNice12Month.avg_12month.value = avg_12month;
	frmPersonNice12Month.grade_12month.value = grade_12month;
	window.setupValidateForm( frmPersonNice12Month );
});

function procPersonNice12Month() {
	hotkey = true;
	var yn_modal = frmPersonNice12Month.yn_modal.value;
	
	if ( !frmPersonNice12Month.validateForm() ) return false;
	
	var data = frmPersonNice12Month.ajaxSubmit();
	if(data == null) return false;

	$.ajax({
		url : "<c:url value='/person/procPersonNice12Month.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			hotkey = false;
			
			if (parseInt(returnData.cd_result,10) == 0) {
				if ("Y" == yn_modal) {
					$("#modal-lg").modal('hide');
					// 고객 정보수정 후 폼 초기화
					loadPersonNice12Month("${no_person}");
					
					//loadPersonInfo("${person.no_person}");
				 } else {
					self.close();
				}
			} 
		},
		error : function (e) {
			alert(e.responseText);
			hotkey = false;
		}
	});
	if(IsNull(frmPersonNice12Month.no_person))
		$("#modal-lg").modal('hide');
}
function avgmonth(){
	var avg_point_0 = Number(frmPersonNice12Month.avg_point_0.value);
	var avg_point_1 = Number(frmPersonNice12Month.avg_point_1.value);
	var avg_point_2 = Number(frmPersonNice12Month.avg_point_2.value);
	var avg_point_3 = Number(frmPersonNice12Month.avg_point_3.value);
	var avg_point_4 = Number(frmPersonNice12Month.avg_point_4.value);
	var avg_point_5 = Number(frmPersonNice12Month.avg_point_5.value);
	var avg_point_6 = Number(frmPersonNice12Month.avg_point_6.value);
	var avg_point_7 = Number(frmPersonNice12Month.avg_point_7.value);
	var avg_point_8 = Number(frmPersonNice12Month.avg_point_8.value);
	var avg_point_9 = Number(frmPersonNice12Month.avg_point_9.value);
	var avg_point_10 = Number(frmPersonNice12Month.avg_point_10.value);
	var avg_point_11 = Number(frmPersonNice12Month.avg_point_11.value);
	var avg_12month = ((avg_point_0 + avg_point_1 + avg_point_2 + avg_point_3 + avg_point_4 + avg_point_5 + avg_point_6 + avg_point_7 + avg_point_8 + avg_point_9 + avg_point_10 + avg_point_11) / 12).toFixed(2);
	frmPersonNice12Month.avg_12month.value = avg_12month;
}
function grademonth(){
	var grade_0 = Number(frmPersonNice12Month.grade_0.value);
	var grade_1 = Number(frmPersonNice12Month.grade_1.value);
	var grade_2 = Number(frmPersonNice12Month.grade_2.value);
	var grade_3 = Number(frmPersonNice12Month.grade_3.value);
	var grade_4 = Number(frmPersonNice12Month.grade_4.value);
	var grade_5 = Number(frmPersonNice12Month.grade_5.value);
	var grade_6 = Number(frmPersonNice12Month.grade_6.value);
	var grade_7 = Number(frmPersonNice12Month.grade_7.value);
	var grade_8 = Number(frmPersonNice12Month.grade_8.value);
	var grade_9 = Number(frmPersonNice12Month.grade_9.value);
	var grade_10 = Number(frmPersonNice12Month.grade_10.value);
	var grade_11 = Number(frmPersonNice12Month.grade_11.value);
	
	var grade_12month = ((grade_0 + grade_1 + grade_2 + grade_3 + grade_4 + grade_5 + grade_6 + grade_7 + grade_8 + grade_9 + grade_10 + grade_11) / 12).toFixed(2);
	frmPersonNice12Month.grade_12month.value = grade_12month;
}
</script>
	<form name="frmPersonNice12Month" id="frmPersonNice12Month">
	<input type="hidden" name="no_person" value="${no_person}"/>
	<input type="hidden" name="yn_modal" value="${personForm.yn_modal}"/>
	<div class="panel panel-primary">
		<div class="panel-heading">NICE 최근12개월 CB스코어 이력</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody>
				<tr>
					<th><span class="required">기준월</span></th>
					<c:forEach var="last12Month" items="${strArryLast12Month}" varStatus="status">
						<td>
							<input type="text" class="form-control width-65" name="std_month_${status.index}" id="std_month_${status.index}" value="${last12Month}" readonly="readonly"/>
						</td>
					</c:forEach> 
				</tr>
				<tr>
					<th><span class="required">평점</span></th>
					<td><input type="text" class="form-control width-65" name="avg_point_0" id="avg_point_0" value="${resultNice12MonthVO.avg_point_0}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_1" id="avg_point_1" value="${resultNice12MonthVO.avg_point_1}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_2" id="avg_point_2" value="${resultNice12MonthVO.avg_point_2}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_3" id="avg_point_3" value="${resultNice12MonthVO.avg_point_3}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_4" id="avg_point_4" value="${resultNice12MonthVO.avg_point_4}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_5" id="avg_point_5" value="${resultNice12MonthVO.avg_point_5}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_6" id="avg_point_6" value="${resultNice12MonthVO.avg_point_6}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_7" id="avg_point_7" value="${resultNice12MonthVO.avg_point_7}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_8" id="avg_point_8" value="${resultNice12MonthVO.avg_point_8}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_9" id="avg_point_9" value="${resultNice12MonthVO.avg_point_9}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_10" id="avg_point_10" value="${resultNice12MonthVO.avg_point_10}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<td><input type="text" class="form-control width-65" name="avg_point_11" id="avg_point_11" value="${resultNice12MonthVO.avg_point_11}" validate="required;label:평점;numeric;" maxlength="3" onblur="avgmonth();" /></td>
					<th><span>12개월 평균평점</span></th>
					<td>
						<input type="text" class="form-control width-65" name="avg_12month" id="avg_12month" readonly="readonly"/>	
					</td>
				</tr>
				<tr>
					<th><span class="required">등급</span></th>
					<td><input type="text" class="form-control width-45" name="grade_0" id="grade_0" value="${resultNice12MonthVO.grade_0}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_1" id="grade_1" value="${resultNice12MonthVO.grade_1}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_2" id="grade_2" value="${resultNice12MonthVO.grade_2}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_3" id="grade_3" value="${resultNice12MonthVO.grade_3}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_4" id="grade_4" value="${resultNice12MonthVO.grade_4}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_5" id="grade_5" value="${resultNice12MonthVO.grade_5}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_6" id="grade_6" value="${resultNice12MonthVO.grade_6}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_7" id="grade_7" value="${resultNice12MonthVO.grade_7}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_8" id="grade_8" value="${resultNice12MonthVO.grade_8}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_9" id="grade_9" value="${resultNice12MonthVO.grade_9}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_10" id="grade_10" value="${resultNice12MonthVO.grade_10}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<td><input type="text" class="form-control width-45" name="grade_11" id="grade_11" value="${resultNice12MonthVO.grade_11}" validate="required;label:등급;numeric;" maxlength="2" onblur="grademonth();"/></td>
					<th><span>12개월 평균등급</span></th>
					<td>
						<input type="text" class="form-control width-45" name="grade_12month" id="grade_12month" readonly="readonly"/>	
					</td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	</form>
	<div class="align-r">
		<button type="submit" class="btn btn-default btn-xs" onclick="procPersonNice12Month();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>NICE 최근12개월 CB스코어 이력 저장</button>
	</div>
