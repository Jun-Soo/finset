<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function () {
		<c:if test="${!empty no_apply}">
			loadApplyMemo(null,"${no_apply}");
		</c:if>
		
		$('[data-toggle="popover"]').popover();
	});

	function procPersonKcb() {
		hotkey = true;
		var yn_modal = frmPersonKcb.yn_modal.value;
		
		window.setupValidateForm( frmPersonKcb );
		if ( !frmPersonKcb.validateForm() ) return false;
		
		var data = frmPersonKcb.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/person/procPersonKcb.json'/>",
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
						loadPersonInfo("${person.no_person}");
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
		
		if(IsNull(frmPersonKcb.no_person))
			$("#modal-lg").modal('hide');
	}

</script>
	<div class="align-r">
		<button type="submit" class="btn btn-default btn-xs" onclick="procPersonKcb();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장</button>
	</div>
	<form name="frmPersonKcb" id="frmPersonKcb">
	<input type="hidden" name="no_person" value="${person.no_person}"/>
	<input type="hidden" name="yn_modal" value="${personForm.yn_modal}"/>
	<div class="panel panel-primary">
		<div class="panel-heading">KCB대출 입력값</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<tbody>
				<tr>
					<th><span class="required">CB스코어</span></th>
					<td>
						<input type="text" class="form-control width-120" name="score_cb" id="score_cb" value="${personKcb.score_cb}" validate="required;label:주민번호;numeric;"/>
					</td>
					<th><span class="required">신용평점</span></th>
					<td>
						<input type="text" class="form-control width-120" name="avg_credit" id="avg_credit" value="${personKcb.avg_credit}" validate="required;label:주민번호;numeric;"/>	
					</td>
					<th><span class="required">평균평점</span></th>
					<td>
						<input type="text" class="form-control width-120" name="avg_avg" id="avg_avg" value="${personKcb.avg_avg}" validate="required;label:주민번호;numeric;"/>
					</td>
					<th><span>평균등급</span></th>
					<td>
						<input type="text" class="form-control width-120" name="grade_avg" id="grade_avg" value="${personKcb.grade_avg}" validate="required;label:주민번호;numeric;"/>
					</td>
				</tr>
				<tr>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	</form>
