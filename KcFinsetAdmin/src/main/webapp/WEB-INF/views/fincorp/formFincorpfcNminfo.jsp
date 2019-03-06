<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
	// SelectPicker
	$('.selectpicker').selectpicker();
	
	function NmSetData(seq, nm_nm_fc, nm_yn_use){
		var seq = seq;
		var nm_nm_fc = nm_nm_fc;
		var nm_yn_use = nm_yn_use;
		$("#nm_nm_fc").val(nm_nm_fc);
		$("#nm_yn_use").val(nm_yn_use);
		$('#nm_yn_use').selectpicker('refresh');	
		$("#seq").val(seq);
	}
	
</script>
<form name="tableFincorpfcNmDetail" id="formFincorpfcNmDetail">
<input type="hidden" value = "${fincorpInfo.cd_fc}" >
<table id="" class="table table-classic" cellspacing="0" width="100%">
	<colgroup>
		<col width="10%">
		<col width="40%">
		<col width="10%">
		<col width="40%">
	</colgroup>
	<tbody>
		<tr>
			<th>은행명</th>
			<td>
				<input type="text" class="form-control" id="nm_nm_fc" name="nm_nm_fc" value="" validate="label:은행명;maxbt:2000;" maxlength="2000"/>
			</td>
			<th>사용여부</th>
			<td>
				<select class="selectpicker" id="nm_yn_use" name="nm_yn_use" data-width="100%">
					${ufn:makeOptions("yn_use","사용여부", "")}
				</select>
			</td>
			<input type="hidden" name="seq" value="" />
		</tr>
	</tbody>
</table>
</form>
<div class="align-r">
	<button type="button" class="btn btn-primary btn-xs" id="createNm_fc"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 추가</button>
	<button type="button" class="btn btn-primary btn-xs" id="updNm_fc"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> 저장</button>
	<button type="button" class="btn btn-primary btn-xs" id="delNm_fc"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 삭제</button>
</div>