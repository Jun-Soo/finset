<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	// SelectPicker
	$('.selectpicker').selectpicker();
</script>
<form name="frmStdCodeGroup">
<input type="hidden" name="yn_code_group" value="Y">
<table class="table table-classic" cellspacing="0" width="100%">
	<colgroup>
		<col width="80px">
		<col width="*">
		<col width="80px">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th>항목그룹코드</th>
			<td>
				<c:choose>
					<c:when test="${empty stdcodeInfo.code_group}">
						<input type="hidden" name="cd_proc_type" value="C">
						<input type="text" class="form-control" name="code_group" validate="required;label:항목그룹코드;maxbt:100;" maxlength="100"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" name="code_group" value="${stdcodeInfo.code_group}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			
			</td>
			<th>항목그룹명</th>
			<td>
				<input type="text" class="form-control" name="nm_code" value="${stdcodeInfo.nm_code}" validate="required;label:항목그룹명;maxbt:2000;" maxlength="2000"/>
			</td>
		</tr>
		<tr>
			<th>설명</th>
			<td colspan="3">
				<input type="text" class="form-control" name="etc" value="${stdcodeInfo.etc}" validate="label:표준데이터항목그룹설명;maxbt:200;" maxlength="200"/>
			</td>
		</tr>
		<tr>
			<th>입력유형</th>
			<td>
				${ufn:makeRadioAndCheckBoxs("cd_type_data", "type_data", "radio", stdcodeInfo.type_data, 0)}
			</td>
			<th>사용여부</th>
			<td colspan="3">
				${ufn:makeRadioAndCheckBoxs("yn_yes", "yn_use", "radio", stdcodeInfo.yn_use, 0)}
			</td>
		</tr>
		<tr>
			<th>DB필드</th>
			<td colspan="3">
				<input type="text" class="form-control" name="nm_col" value="${stdcodeInfo.nm_col}" validate="label:DB필드;maxbt:200;" maxlength="200"/>
			</td>
		</tr>
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-primary btn-xs" onclick="loadStdCodeGroup('');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 신규</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="procStdCodeInfo(document.frmStdCodeGroup,'G');"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="delStdCodeInfo(document.frmStdCodeGroup,'G');"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>
