<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	// SelectPicker
	$('.selectpicker').selectpicker();
	
	window.setupValidateForm( frmWorkerUser );
});

function procWorkerUser() {
	
	if ( !frmWorkerUser.validateForm() ) return false;
	
	//전화번호 체크
	var extEmpDirectTmp1 = $("#ext_emp_direct_tmp1").val();
	var extEmpDirectTmp2 = $("#ext_emp_direct_tmp2").val();
	var extEmpDirectTmp3 = $("#ext_emp_direct_tmp3").val();
	var extEmpDirectTmp = extEmpDirectTmp1 + "-" + extEmpDirectTmp2 + "-" + extEmpDirectTmp3;
	var regTel = /^\d{2,3}-\d{3,4}-\d{4}$/;
	
	if(extEmpDirectTmp1 != "" || extEmpDirectTmp2 != "" || extEmpDirectTmp3 != ""){
		if(!(regTel.test(extEmpDirectTmp))){
			alert("전화번호 형식이 올바르지 않습니다.");
			return false;
		}
	}
	
	//휴대폰번호 체크
	var hpTmp1 = $("#hp_tmp1").val();
	var hpTmp2 = $("#hp_tmp2").val();
	var hpTmp3 = $("#hp_tmp3").val();
	var hpTmp = hpTmp1 + "-" + hpTmp2 + "-" + hpTmp3;
	var regHp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	
	if(hpTmp1 != "" || hpTmp2 != "" || hpTmp3 != ""){
		if(!(regHp.test(hpTmp))){
			alert("휴대폰번호 형식이 올바르지 않습니다.");
			return false;
		}
	}
	
	var data = frmWorkerUser.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/worker/modifyWorkerUser.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			if(parseInt(returnData.cd_result,10) == 0) {
			
			<c:choose>
				<c:when test="${idxChange eq 'Y'}">
				// 변경 후 메인으로 이동
				location.href = "<%= request.getContextPath() %>/baseMain.crz"; 
				</c:when>
				<c:otherwise>
				// 변경 후 모달 닫음
				$("#modal-mid").modal('hide');
				</c:otherwise>
			</c:choose>
				
			} else {
				alert(returnData.message);
			}
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="mediumModalLabel">사용자 정보수정</h4>
</div>
<div class="modal-body">
	<form name="frmWorkerUser">
	<div class="panel panel-primary">
		<div class="panel-heading"> 정보수정</div>
		<div class="panel-collapse">
		<table class="table table-classic">
			<colgroup>
				<col width="17%"/>
				<col width="30%"/>
				<col width="20%"/>
				<col width="33%"/>
			</colgroup>
		
			<tbody>
				<tr>
					<th class="required">현재 비밀번호</th>
					<td colspan="3">
						<input type="password" class="form-control width-120" name="pass_old" validate="required; label:현재비밀번호;maxbyte:30;" maxlength="30"/>
					</td>
				</tr>
				<tr>
					<th>새 비밀번호</th>
					<td>
						<input type="password" class="form-control width-120" name="pass_new" validate="label:새비밀번호;maxbyte:30;" maxlength="30"/>
					</td>
					<th>새 비밀번호 확인</th>
					<td>
						<input type="password" class="form-control width-120" name="pass_re_new" validate="label:새비밀번호확인;maxbyte:30;" maxlength="30"/>
					</td>
				</tr>
				<tr>
					<th>내선번호</th>
					<td>
						<input type="text" class="form-control width-120" name="ext_emp" id="ext_emp" value="${worker.ext_emp}" validate="label:내선번호;maxbyte:4;numeric" maxlength="4"/>
					</td>
					<th>직통번호</th>
					<td>
						<div class="input-group phone-w">
							<select id="ext_emp_direct_tmp1" name="ext_emp_direct_tmp" class="selectpicker" data-width="20px">
								${ufn:makeOptionsType("cd_ph_local", "지역번호", worker.ext_direct_idx1,"")}
							</select>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" id="ext_emp_direct_tmp2" name="ext_emp_direct_tmp" value="${worker.ext_direct_idx2}" validate="numeric;keyup-next:ext_emp_direct_tmp" maxlength="4"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" id="ext_emp_direct_tmp3" name="ext_emp_direct_tmp" value="${worker.ext_direct_idx3}" validate="numeric;" maxlength="4"/>	
						</div>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>
						<div class="input-group phone-w">
							<select id="hp_tmp1" name="hp_tmp" class="selectpicker" data-width="20px">
								${ufn:makeOptionsType("cd_hp_kind", "구분", worker.hp_idx1, "")}
							</select>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" id="hp_tmp2" name="hp_tmp" value="${worker.hp_idx2}" validate="numeric;keyup-next:hp_tmp" maxlength="4"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" id="hp_tmp3" name="hp_tmp" value="${worker.hp_idx3}" validate="numeric;" maxlength="4"/>	
						</div>
					</td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	</form>
	<table class="table table-bordered tbl-info" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>접속일시</th>
				<th>접속결과</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="List" items="${loginList}" varStatus="status">
			<tr>
				<td>
					${List.dt_frt}
				</td>
				<td>
					${ufn:getCodeName('cd_login_result', List.cd_result)}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="modal-footer">
	<c:choose>
		<c:when test="${idxChange eq 'Y'}">
			<!-- 강제 비밀번호 변경 화면시 버튼 -->
			<button type="submit" class="btn btn-default btn-xs" onclick="procWorkerUser()"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span> 저장</button>
			<button type="submit" class="btn btn-default btn-xs" onclick="goLogout()"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> 로그아웃</button>
		</c:when>
		<c:otherwise>
			<button type="submit" class="btn btn-default btn-xs" onclick="procWorkerUser()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장</button>
		</c:otherwise>
	</c:choose>
</div>