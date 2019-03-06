<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	// SelectPicker
	$('.selectpicker').selectpicker();
</script>
<form name="frmConsumeCardMng">
<table class="table table-classic" cellspacing="0" width="100%">
	<colgroup>
		<col width="80px">
		<col width="*">
		<col width="80px">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th>카드사</th>
			<td>
				<c:choose>
					<c:when test="${empty cardInfo.cd_fc}">
						<input type="hidden" id="card_cd_proc_type" name="cd_proc_type" value="C">
						<select class="selectpicker" id="card_cd_fc" name="cd_fc" validate="label:카드사;required;" data-width="100%">
								<option value="">선택</option> 
							<c:forEach var="schCardFcList" items="${schCardFcList}" varStatus="status">
								<option value="${schCardFcList.cd_fc}">${schCardFcList.nm_fc}</option> 
							</c:forEach>
						</select>
					</c:when>
					<c:otherwise>
						<input type="hidden" id="card_cd_fc" name="cd_fc" value="${cardInfo.cd_fc}">
						<select class="selectpicker" id="card_view_cd_fc" name="view_cd_fc" data-width="100%" disabled="disabled">
							<c:forEach var="schCardFcList" items="${schCardFcList}" varStatus="status">
								<option value="${schCardFcList.cd_fc}" <c:if test="${cardInfo.cd_fc eq schCardFcList.cd_fc}">selected="selected"</c:if>>
								${schCardFcList.nm_fc}
								</option> 
							</c:forEach>
						</select>
					</c:otherwise>
				</c:choose>
			</td>
			<th>소비항목코드</th>
			<td>
				<input type="text" class="form-control" id="card_cd_consume_class" name="cd_consume_class" value="${cardInfo.cd_consume_class}" validate="label:소비항목코드;maxbt:5;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<th colspan="1">업종명</th>
			<td colspan="3">
				<c:choose>
					<c:when test="${empty cardInfo.nm_business}">
						<input type="text" class="form-control" id="card_nm_business" name="nm_business" validate="label:업종명;required;maxbt:50;" maxlength="50"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" id="card_nm_business" name="nm_business" value="${cardInfo.nm_business}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
				
			</td>
		</tr>
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-primary btn-xs" onclick="getConsumeCardMng('','');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 신규</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="procConsumeCardMng();"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="delConsumeCardMng();"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>