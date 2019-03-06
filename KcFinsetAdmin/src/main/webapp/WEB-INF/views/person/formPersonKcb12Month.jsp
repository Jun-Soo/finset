<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<script type="text/javascript">
	$(document).ready(function () {
		<c:if test="${!empty no_apply}">
			loadApplyMemo(null,"${no_apply}");
		</c:if>
		
		$('[data-toggle="popover"]').popover();
	});

	function procPersonKcb12Month() {
		hotkey = true;
		var yn_modal = frmPersonKcb12Month.yn_modal.value;
		
		window.setupValidateForm( frmPersonKcb12Month );
		if ( !frmPersonKcb12Month.validateForm() ) return false;
		
		var data = frmPersonKcb12Month.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/person/procPersonKcb12Month.json'/>",
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
						loadPersonKcb12Month("${person.no_person}");
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
		
		if(IsNull(frmPersonKcb12Month.no_person))
			$("#modal-lg").modal('hide');
	}
</script>
	<div class="align-r">
		<button type="submit" class="btn btn-default btn-xs" onclick="procPersonKcb12Month();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장</button>
	</div>
	<form name="frmPersonKcb12Month" id="frmPersonKcb12Month">
	<input type="hidden" name="no_person" value="${no_person}"/>
	<input type="hidden" name="yn_modal" value="${personForm.yn_modal}"/>
	<div class="panel panel-primary">
		<div class="panel-heading">KCB 최근12개월 CB스코어 이력</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody>
				<tr>
					<th><span class="required">기준월</span></th>
					<c-rt:forEach var="last12Month" items="${strArryLast12Month}" varStatus="status">
						<td>
							<input type="text" class="form-control width-65" name="std_month_${status.index}" id="std_month_${status.index}" value="${last12Month}" validate="label:기준월" readonly/>
						</td>
					</c-rt:forEach> 
				</tr>
				<tr>
					<th><span class="required">평점</span></th>
					<td><input type="text" class="form-control width-65" name="avg_point_0" id="avg_point_0" value="${resultKcb12MonthVO.avg_point_0}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_1" id="avg_point_1" value="${resultKcb12MonthVO.avg_point_1}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_2" id="avg_point_2" value="${resultKcb12MonthVO.avg_point_2}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_3" id="avg_point_3" value="${resultKcb12MonthVO.avg_point_3}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_4" id="avg_point_4" value="${resultKcb12MonthVO.avg_point_4}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_5" id="avg_point_5" value="${resultKcb12MonthVO.avg_point_5}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_6" id="avg_point_6" value="${resultKcb12MonthVO.avg_point_6}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_7" id="avg_point_7" value="${resultKcb12MonthVO.avg_point_7}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_8" id="avg_point_8" value="${resultKcb12MonthVO.avg_point_8}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_9" id="avg_point_9" value="${resultKcb12MonthVO.avg_point_9}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_10" id="avg_point_10" value="${resultKcb12MonthVO.avg_point_10}" validate="label:평점"/></td>
					<td><input type="text" class="form-control width-65" name="avg_point_11" id="avg_point_11" value="${resultKcb12MonthVO.avg_point_11}" validate="label:평점"/></td>
				</tr>
				<tr>
					<th><span class="required">등급</span></th>
					<td><input type="text" class="form-control width-45" name="grade_0" id="grade_0" value="${resultKcb12MonthVO.grade_0}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_1" id="grade_1" value="${resultKcb12MonthVO.grade_1}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_2" id="grade_2" value="${resultKcb12MonthVO.grade_2}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_3" id="grade_3" value="${resultKcb12MonthVO.grade_3}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_4" id="grade_4" value="${resultKcb12MonthVO.grade_4}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_5" id="grade_5" value="${resultKcb12MonthVO.grade_5}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_6" id="grade_6" value="${resultKcb12MonthVO.grade_6}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_7" id="grade_7" value="${resultKcb12MonthVO.grade_7}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_8" id="grade_8" value="${resultKcb12MonthVO.grade_8}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_9" id="grade_9" value="${resultKcb12MonthVO.grade_9}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_10" id="grade_10" value="${resultKcb12MonthVO.grade_10}" validate="label:등급"/></td>
					<td><input type="text" class="form-control width-45" name="grade_11" id="grade_11" value="${resultKcb12MonthVO.grade_11}" validate="label:등급"/></td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	</form>
