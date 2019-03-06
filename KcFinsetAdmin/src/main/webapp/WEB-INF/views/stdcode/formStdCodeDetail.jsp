<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	// SelectPicker
	$('.selectpicker').selectpicker();
</script>
<form name="frmStdCodeDetail">
<table class="table table-classic" cellspacing="0" width="100%">
	<colgroup>
		<col width="80px">
		<col width="*">
		<col width="80px">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th>상세항목ID</th>
			<td>
				<c:choose>
					<c:when test="${empty stdcodeInfo.code_value}">
						<input type="hidden" name="cd_proc_type" value="C">
						<input type="text" class="form-control" name="code_value" validate="required;label:상세항목ID;maxbt:100;" maxlength="100"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" name="code_value" value="${stdcodeInfo.code_value}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
			<th>상세항목명</th>
			<td>
				<input type="text" class="form-control" name="nm_code" value="${stdcodeInfo.nm_code}" validate="required;label:상세항목명;maxbt:2000;" maxlength="2000"/>
			</td>
		</tr>
		<tr>
			<th>정렬순서</th>
			<td>
				<input type="text" class="form-control" name="seq_order" value="${stdcodeInfo.seq_order}" validate="numeric;required;label:정렬순서;maxbt:4;" maxlength="4"/>
			</td>
			<th>사용여부</th>
			<td>
				<select class="selectpicker" name="yn_use" data-width="100%">
					${ufn:makeOptions("yn_use","사용여부", (empty stdcodeInfo.yn_use?'Y':stdcodeInfo.yn_use))}
				</select>
			</td>
		</tr>
		<tr>
			<th>설명</th>
			<td>
				<input type="text" class="form-control" name="etc" value="${stdcodeInfo.etc}" validate="label:표준데이터항목설명;maxbt:200;" maxlength="200"/>
			</td>
			<th>표준데이터항목그룹ID</th>
			<td>
				<input type="text" class="form-control" name="code_group" value="${stdcodeInfo.code_group}" validate="required;label:표준데이터항목그룹ID;maxbt:100;" readonly="readonly"/>
			</td>
		</tr>
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-primary btn-xs" onclick="loadStdCodeDetail('','','${stdcodeInfo.code_group}');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 신규</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="procStdCodeInfo(document.frmStdCodeDetail,'D');"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="delStdCodeInfo(document.frmStdCodeDetail,'D');"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>