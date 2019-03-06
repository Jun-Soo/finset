<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	// SelectPicker
	$('.selectpicker').selectpicker();
	
});

function initWorkerPass(id_emp) {
	
	if(!confirm("초기비밀번호는 사용자ID 입니다. 초기화를 하시겠습니까?")) return;
	
	var data = {"id_emp":id_emp};
	$.ajax({
		url : "<c:url value='/worker/initWorkerPass.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

/**
 * Name  : viewFin
 * Desc  : 업권 선택에 따라서 회사명 조회
 * Param : 
**/
function viewFin(){
	
	var selFinVal = $('#cd_fin').val();
	alert(selFinVal);
	
	var data = {"cd_fin":selFinVal};
	
	if(data == null) return false;
	var target= $('#cd_fc');
	//cd_fc
	$.ajax({
		url : "<c:url value='/worker/getListFc.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (data) {
			boxOption = document.createElement("OPTION");
	        boxOption.value = '';     
	        boxOption.text = '전문번호';
	        target.options.add(boxOption);
			$.each(data.List, function (index, item) {
		        boxOption = document.createElement("OPTION");
		        boxOption.value = item;     
		        boxOption.text = item;
		        target.options.add(boxOption);
			});	
	        $('#cd_fc').selectpicker('refresh');
			target.options.length = data.List.length + 1;
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

</script>
<div class="panel panel-default panel-sub toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 사용자 정보</a>
		</h3>
		<span class="pull-right">
			<c:choose>
				<c:when test="${empty worker.id_emp}">
					<button type="button" class="btn btn-primary btn-xs" onclick="createWorker();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 등록</button>
					<button type="button" class="btn btn-default btn-xs" onclick="resetForm(document.frmWorker);"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-primary btn-xs" onclick="modifyWorker();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 변경</button>
					<button type="button" class="btn btn-warning btn-xs" onclick="deleteWorker('${worker.id_emp}');"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 삭제</button>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
	<div class="panel-collapse toggle-cont">
		<form name="frmWorker" id="frmWorker">
		<table class="table table-classic">
			<colgroup>
				<col width="12%"/>
				<col width="18%"/>
				<col width="12%"/>
				<col width="18%"/>
				<col width="12%"/>
				<col width="*"/>
			</colgroup>
			<tbody>
				<tr>
					<th><span class="required">사용자ID</span></th>
					<td>
						<c:choose>
							<c:when test="${empty worker.id_emp}">
								<input type="text" class="form-control" name="id_emp" id="id_emp" value="${worker.id_emp}" validate="required;label:사용자ID;maxbyte:20;alpha_numeric" maxlength="20"/>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="id_emp" id="id_emp" value="${worker.id_emp}" />
								${worker.id_emp}
							</c:otherwise>
						</c:choose>
					</td>
					<th>비밀번호</th>
					<td>
						<c:choose>
							<c:when test="${empty worker.id_emp}">
								ID 와 동일
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-default btn-xs" onclick="initWorkerPass('${worker.id_emp}')"><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> 초기화</button>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><span class="required">사용자 이름</span></th>
					<td>
						<input type="text" class="form-control" name="nm_emp" id="nm_emp" value="${worker.nm_emp}" validate="required;label:사용자이름;maxbyte:30" maxlength="10"/>
					</td>
					<th><span class="required">사용여부</span></th>
					<td>
						<select class="selectpicker" name="cd_status_emp" id="cd_status_emp" title="사용여부" validate="">
							${ufn:makeOptions('cd_status_emp','',worker.cd_status_emp)} 
						</select>
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<div class="input-group phone-w">
							<select id="ext_emp_direct_tmp1" name="ext_emp_direct_tmp" class="selectpicker">
								${ufn:makeOptionsType("cd_ph_local", "지역번호", worker.ext_direct_idx1,"")}
							</select>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" id="ext_emp_direct_tmp2" name="ext_emp_direct_tmp" value="${worker.ext_direct_idx2}" validate="numeric;keyup-next:ext_emp_direct_tmp" maxlength="4"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" id="ext_emp_direct_tmp3" name="ext_emp_direct_tmp" value="${worker.ext_direct_idx3}" validate="numeric;" maxlength="4"/>	
						</div>
					</td>
					<th>휴대폰</th>
					<td>
						<div class="input-group phone-w">
							<select id="hp_tmp1" name="hp_tmp" class="selectpicker" data-width="20px">
								${ufn:makeOptions("cd_hp_kind", "구분", worker.hp_idx1)}
							</select>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" id="hp_tmp2" name="hp_tmp" value="${worker.hp_idx2}" validate="numeric;keyup-next:hp_tmp" maxlength="4"/>
							<span class="input-group-addon" id="basic-addon1">-</span>
							<input type="text" class="form-control" id="hp_tmp3" name="hp_tmp" value="${worker.hp_idx3}" validate="numeric;" maxlength="4"/>	
						</div>
					</td>

				</tr>
				<tr>
					<th>권한그룹</th>
					<td>
						<c:choose>
							<c:when test="${worker.authority eq 'ADMIN'}">
								<select class="selectpicker" name="cd_template_group" id="cd_template_group"  title="권한그룹">
									${ufn:makeOptions('cd_template_group','권한그룹구분',worker.cd_template_group)} 
								</select>
							</c:when>
							<c:otherwise>
								<select class="selectpicker" name="cd_template_group" id="cd_template_group"  validate="required;label:탬플릿구분;" title="권한그룹">
									${ufn:makeOptions('cd_template_group','권한그룹구분',worker.cd_template_group)}
								</select>
							</c:otherwise>
						</c:choose>
					</td>
					<th>업권</th>
					<td>
						<select class="selectpicker" name="cd_fin" id="cd_fin"  title="업권" >
							${ufn:makeOptions('cd_fin','',worker.cd_fin)} 
						</select>
					</td>
				</tr>
				<tr>
					<th>회사명</th>
					<td>
						<select class="selectpicker" name="cd_fc" id="cd_fc" data-width="80%">
							${ufn:makeFincorpOptions('회사명', worker.cd_fc, 'type', '')}
						</select>
					</td>
					<th>접속초기화</th>
					<td>
						<button type="button" class="btn btn-primary btn-xs" onclick="resetCon();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 접속초기화</button>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
</div>