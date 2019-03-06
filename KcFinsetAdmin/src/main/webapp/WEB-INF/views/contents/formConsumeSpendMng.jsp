<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	// SelectPicker
	$('.selectpicker').selectpicker();
</script>
<form name="frmConsumeSpendMng">
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
			<th>분류코드</th>
			<td>
				<c:choose>
					<c:when test="${empty spendInfo.cd_consume_class}">
						<input type="hidden" id="spend_cd_proc_type" name="cd_proc_type" value="C">
						<input type="text" class="form-control" id="spend_cd_class" name="cd_class" validate="label:분류코드;required;numeric;numSize:2;" maxlength="2"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" id="spend_cd_class" name="cd_class" value="${spendInfo.cd_class}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
			<th>분류명</th>
			<td>
				<input type="text" class="form-control" id="spend_nm_class" name="nm_class" value="${spendInfo.nm_class}" validate="label:분류명;required;maxbt:50;" maxlength="50"/>
			</td>
		</tr>
		<tr>
			<th>항목코드</th>
			<td>
				<c:choose>
					<c:when test="${empty spendInfo.cd_consume_class}">
						<input type="text" class="form-control" id="spend_cd_type" name="cd_type" validate="label:항목코드;required;numeric;numSize:3;" maxlength="3"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" id="spend_cd_type" name="cd_type" value="${spendInfo.cd_type}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
			<th>항목명</th>
			<td>
				<input type="text" class="form-control" id="spend_nm_type" name="nm_type" value="${spendInfo.nm_type}" validate="label:항목명;maxbt:50;" maxlength="50"/>
			</td>
		</tr>
		<tr>
			<th>사용여부</th>
			<td>
				<select class="selectpicker" id="spend_yn_use" name="yn_use" data-width="43%">
					${ufn:makeOptions("yn_use","사용여부", (empty spendInfo.yn_use?'Y':spendInfo.yn_use))}
				</select>
			</td>
			<th>수입지출구분</th>
			<td>
				<c:choose>
					<c:when test="${ empty spendInfo.type_in_out }">
						<select class="selectpicker" id="spend_type_in_out" name="type_in_out" validate="required; label:수입지출구분;">
							${ufn:makeOptions("type_in_out","수입지출구분", '01')}
						</select>
					</c:when>
					<c:otherwise>
						<select class="selectpicker" id="spend_type_in_out" name="type_in_out" disabled="disabled">
							${ufn:makeOptions("type_in_out","수입지출구분", spendInfo.type_in_out)}
						</select>
						<input type="hidden" class="form-control" id="type_in_out" name="type_in_out" validate="required; label:수입지출구분;" value="${spendInfo.type_in_out}"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-primary btn-xs" onclick="getConsumeSpendMng('');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 신규</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="procConsumeSpendMng();"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="delConsumeSpendMng();"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>
