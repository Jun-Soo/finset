<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<link rel="stylesheet" href="<c:url value="/style/autocomplete.css"/>"/>
<script type="text/javascript">
// SelectPicker
$('.selectpicker').selectpicker();
</script>
<form name="frmEdocGroup">
<input type="hidden" name="seq_edoc" value="${edocInfo.seq_edoc}">
<table class="table table-classic" cellspacing="0" width="100%">
	<colgroup>
		<col width="80px">
		<col width="*">
		<col width="80px">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th>금융사코드</th>
			<td>
				<select class="selectpicker" name="cd_fc" data-width="80%">
					${ufn:makeFincorpOptions('금융사코드', edocInfo.cd_fc, 'type', '')}
				</select>
			</td>
			<th>사용여부</th>
			<td>
				${ufn:makeRadioAndCheckBoxs("yn_yes", "yn_use", "radio", edocInfo.yn_use, 0)}
			</td>
		</tr>
		<tr>
			<th>전문번호</th>
			<td>
				<input type="text" class="form-control" name="no_edoc" value="${edocInfo.no_edoc}" validate="required;label:전문번호;maxbt:100;" maxlength="30"/>
			</td>
			<th>전문명</th>
			<td>
				<input type="text" class="form-control" name="nm_edoc" value="${edocInfo.nm_edoc}" validate="required;label:전문명;maxbt:200;" maxlength="200"/>
			</td>
		</tr>
		<tr>
			<th>전문URL</th>
			<td>
				<input type="text" class="form-control" name="url_edoc" value="${edocInfo.url_edoc}" validate="required;label:전문URL;maxbt:200;" maxlength="200"/>
			</td>
			<th>설명</th>
			<td>
				<input type="text" class="form-control" name="etc" value="${edocInfo.etc}" validate="label:표준데이터항목설명;maxbt:200;" maxlength="200"/>
			</td>
		</tr>
		<tr>
			<th>송수신구분</th>
			<td>
				${ufn:makeRadioAndCheckBoxs("cd_type_txrx", "type_txrx", "radio", edocInfo.type_txrx, 0)}
			</td>
			<th>전문타입</th>
			<td>
				${ufn:makeRadioAndCheckBoxs("cd_type_flex", "type_flex", "radio", edocInfo.type_flex, 0)}
			</td>
		</tr>
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-primary btn-xs" onclick="loadEdocGroup('','','');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 신규</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="procEdocInfo(document.frmEdocGroup,'G');"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="delEdocInfo(document.frmEdocGroup,'G');"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>
