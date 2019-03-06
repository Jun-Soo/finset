<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
var hotkey = false;
$(document).ready(function(){
	// SelectPicker
	$('.selectpicker').selectpicker();
	
	viewContact();
	
	window.setupValidateForm( frmLegal );
});

function viewContact() {
	if("01" == frmLegal.cd_collect_path.value) {
		$("#cd_contact_path_01").show();
		$("#cd_contact_path_02").hide();
	} else if("02" == frmLegal.cd_collect_path.value) {
		$("#cd_contact_path_02").show();
		$("#cd_contact_path_01").hide();
	} else {
		$("#cd_contact_path_01").hide();
		$("#cd_contact_path_02").hide();
	}
}

function procLegalInfo() {
	
	if("01" == frmLegal.cd_collect_path.value) {
		frmLegal.cd_contact_path.value = frmLegal.cd_contact_path_01.value
	} else if("02" == frmLegal.cd_collect_path.value) {
		frmLegal.cd_contact_path.value = frmLegal.cd_contact_path_02.value
	}
	
	window.setupValidateForm( frmLegal );
	if ( !frmLegal.validateForm() ) return false;
	
	var data = frmLegal.ajaxSubmit();
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/apply/modifyApplyLegal.json'/>",
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

</script>
<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none">적법수집확인</a>
		</h3>
		<span class="pull-right">
			<button type="submit" class="btn btn-default btn-xs" onclick="procLegalInfo();">
			<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 저장</button>
		</span>
	</div>
	<div class="panel-collapse toggle-cont">
		<form name="frmLegal">
			<input type="hidden" name="no_apply" value="${applyVO.no_apply}"/>
			<input type="hidden" name="cd_contact_path"/>
			<div class="panel-collapse">
				<table class="table table-classic">
					<colgroup>
						<col width="15%">
						<col width="35%">
						<col width="15%">
						<col width="35%">
					</colgroup>
					<tbody>
						<tr>
							<th>최초수집경로</th>
							<td colspan="3">
								<select name="cd_collect_path" class="selectpicker" onchange="viewContact();">
									${ufn:makeOptionsType("cd_collect_path", "최초수집경로", applyVO.cd_collect_path, "ID.NM")}
								</select>
							</td>
						</tr>
						<tr>
							<th>연락처수집경로</th>
							<td colspan="3">
								<span id="cd_contact_path_01" style="display: none;">
								<select name="cd_contact_path_01" class="selectpicker">
									${ufn:makeOptionsType("cd_contact_path_01", "연락처수집경로", applyVO.cd_contact_path, "ID.NM")}
								</select>
								</span>
								<span id="cd_contact_path_02" style="display: none;">
								<select name="cd_contact_path_02" class="selectpicker">
									${ufn:makeOptionsType("cd_contact_path_02", "연락처수집경로", applyVO.cd_contact_path, "ID.NM")}
								</select>
								</span>
							</td>
						</tr>
						<tr>
							<th>고객 연락 방법</th>
							<td colspan="3">
								<select name="cd_collect_method" class="selectpicker">
									${ufn:makeOptionsType("cd_collect_method", "연락방법", applyVO.cd_collect_method, "ID.NM")}
								</select>
							</td>
						</tr>
						<tr>
							<th>기타경로(메모)</th>
							<td colspan="3">
								<input type="text" class="form-control" name="etc_memo" value="${applyVO.etc_memo}"/>
							</td>
						</tr>
						<tr>
							<th>제휴사명</th>
							<td colspan="3">
								<input type="text" class="form-control width-300" name="nm_agency" value="${applyVO.nm_agency}"/>
							</td>
						</tr>
						<tr>
							<th>대표자</th>
							<td>
								<input type="text" class="form-control width-120" name="nm_ceo_agency" value="${applyVO.nm_ceo_agency}"/>
							</td>
							<th>홈페이지</th>
							<td>
								<input type="text" class="form-control width-150" name="url_homepage_agency" id="url_homepage_agency" value="${applyVO.url_homepage_agency}"/>
								<select name="url_homepage_agency_domain" class="selectpicker" onchange="$('#url_homepage_agency').val(this.value)">
									${ufn:makeOptionsType("url_collect_homepage", "직접입력", '',"NM")}
								</select>
							</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>
								<input type="text" class="form-control width-120" name="nm_writer" value="${applyVO.nm_writer}"/>
							</td>
							<th>작성일</th>
							<td>${applyVO.dt_frt}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</div>