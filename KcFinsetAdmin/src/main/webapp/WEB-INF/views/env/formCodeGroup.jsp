<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	// SelectPicker
	$('.selectpicker').selectpicker();
</script>
<form name="frmCodeGroup">
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
			<th>코드그룹ID</th>
			<td>
				<c:choose>
					<c:when test="${empty codeInfo.code_group}">
						<input type="hidden" name="cd_proc_type" value="C">
						<input type="text" class="form-control" name="code_group" validate="required;label:코드그룹ID;maxbt:100;" maxlength="100"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" name="code_group" value="${codeInfo.code_group}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			
			</td>
			<th>코드그룹명</th>
			<td>
				<input type="text" class="form-control" name="nm_code" value="${codeInfo.nm_code}" validate="required;label:코드그룹명;maxbt:2000;" maxlength="2000"/>
			</td>
		</tr>
		<tr>
			<th>사용자구분</th>
			<td>
				<select class="selectpicker" name="yn_system_code" data-width="100%" validate="required; label:사용자구분;">
					${ufn:makeOptions("yn_system_code","구분", codeInfo.yn_system_code)}
				</select>
			</td>
			<th>사용여부</th>
			<td>
				<select class="selectpicker" name="yn_use" data-width="100%">
					${ufn:makeOptions("yn_use","사용여부", (empty codeInfo.yn_use?'Y':codeInfo.yn_use))}
				</select>
			</td>
		</tr>
		<tr>
			<th>설명</th>
			<td colspan="3">
				<input type="text" class="form-control" name="etc" value="${codeInfo.etc}" validate="label:코드그룹설명;maxbt:200;" maxlength="200"/>
			</td>
		</tr>
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-primary btn-xs" onclick="loadCodeGroup('');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 신규</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="procCodeInfo(document.frmCodeGroup,'G');"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="delCodeInfo(document.frmCodeGroup,'G');"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>
