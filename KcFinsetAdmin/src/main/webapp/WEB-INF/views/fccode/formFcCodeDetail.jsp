<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	// SelectPicker
	$('.selectpicker').selectpicker();
</script>
<form name="frmFcCodeDetail">
<input type="hidden" name="type_txrx" id="type_txrx" value="${fcCodeInfo.type_txrx}"/>
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
				<input type="text" class="form-control" name="nm_fc" value="${ufn:getNmFc(fcCodeInfo.cd_fc)}" validate="required;label:금융사명;maxbt:100;" readonly="readonly"/>
				<input type="text" class="form-control" name="cd_fc" value="${fcCodeInfo.cd_fc}" validate="required;label:금융사코드;maxbt:100;" readonly="readonly"/>
			</td>
			<th>전문번호</th>
			<td>
				<input type="text" class="form-control" name="nm_edoc" value="${ufn:getNmEdoc(fcCodeInfo.cd_fc, fcCodeInfo.no_edoc)}" validate="required;label:전문명;maxbt:100;" readonly="readonly"/>
				<input type="text" class="form-control" name="no_edoc" value="${fcCodeInfo.no_edoc}" validate="required;label:전문번호;maxbt:100;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<th>상세항목ID</th>
			<td>
				<c:choose>
					<c:when test="${empty fcCodeInfo.code_value}">
						<input type="hidden" name="cd_proc_type" value="C">
						<input type="text" class="form-control" name="code_value" validate="required;label:상세항목ID;maxbt:100;" maxlength="100"/>
					</c:when>
					<c:otherwise>
						<input type="text" class="form-control" name="code_value" value="${fcCodeInfo.code_value}">
					</c:otherwise>
				</c:choose>
			</td>
			<th>상세항목명</th>
			<td>
				<input type="text" class="form-control" name="nm_code" value="${fcCodeInfo.nm_code}" validate="required;label:상세항목명;maxbt:2000;" maxlength="2000"/>
			</td>
		</tr>
		<tr>
			<th>정렬순서</th>
			<td>
				<input type="text" class="form-control" name="seq_order" value="${fcCodeInfo.seq_order}" validate="numeric;required;label:정렬순서;maxbt:4;" maxlength="4"/>
			</td>
			<th>사용여부</th>
			<td>
				<select class="selectpicker" name="yn_use" data-width="100%">
					${ufn:makeOptions("yn_use","사용여부", (empty fcCodeInfo.yn_use?'Y':fcCodeInfo.yn_use))}
				</select>
			</td>
		</tr>
		<tr>
			<th>설명</th>
			<td>
				<input type="text" class="form-control" name="etc" value="${fcCodeInfo.etc}" validate="label:금융사전문별 항목설명;maxbt:200;" maxlength="200"/>
			</td>
			<th>금융사전문별 항목그룹ID</th>
			<td>
				<input type="text" class="form-control" name="code_group" value="${fcCodeInfo.code_group}" validate="required;label:금융사전문별 항목그룹ID;maxbt:100;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<th colspan="2">핀셋표준항목코드</th>
			<td colspan="2">
				<input type="hidden" class="form-control" name="item_tag" value="${fcCodeInfo.item_tag}"/>
				<c:choose>
					<c:when test="${empty fcCodeInfo.item_tag}">
					<!-- <input type="text" class="form-control" name="item_tag_value" validate="required;label:상세항목ID;maxbt:100;" maxlength="100"/> -->
					</c:when>
					<c:otherwise>
						<%-- <input type="text" class="form-control" name="item_tag_value" value="${fcCodeInfo.code_value}" readonly="readonly"/> --%>
						<select class="selectpicker" name="item_tag_value" data-width="80%">
							${ufn:makeStdCodeOptions(ufn:getStdCodeName(fcCodeInfo.item_tag, ' '), fcCodeInfo.item_tag, fcCodeInfo.item_tag_value, 'ID.NM')}
						</select>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>		
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-primary btn-xs" onclick="loadFcCodeDetailItemTag('${fcCodeInfo.cd_fc}','${fcCodeInfo.no_edoc}','${fcCodeInfo.code_group}','${fcCodeInfo.item_tag}','${fcCodeInfo.type_txrx}');"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 신규</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="procFcCodeInfo(document.frmFcCodeDetail,'D');"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" onclick="delFcCodeInfo(document.frmFcCodeDetail,'D');"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>