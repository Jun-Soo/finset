<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>고객 연계검사</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('.selectpicker').selectpicker();
	window.setupValidateForm( frmPersonRel );

});
	function searchPersonRel() {

		if(!frmPersonRel.validateForm()) return false;
		
		var data = frmPersonRel.ajaxSubmit();
		if (data == null) return false;
		
		vLoad("listPersonRel","<c:url value='/person/listPersonRel.crz'/>",data);
	}
	
	// data 초기화
	function resetRelForm() {
		
		$("form[name=frmPersonRel] input[name=nm_person]").val("");
		$("form[name=frmPersonRel] input[name=ssn_person_tmp]").val("");	
		$("form[name=frmPersonRel] input[name=hp_tmp]").val("");
		$("#hp_tmp_type option:eq(0)").attr("selected", "selected");
		$("#frmPersonRel .selectpicker").selectpicker("refresh"); //selectpicker 초기화
	}
</script>
</head>
<body>
<div class="ui-layout-content" style="height: 100%">
	<div class="panel panel-default toggle-panel">
		<div class="panel-heading">
			<h3 class="h-sec pull-left">고객정보</h3>
			
			<span class="pull-right">
				<button type="button" class="btn btn-primary btn-xs" onclick="resetRelForm();">
					<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 초기화
				</button>
				<button type="button" class="btn btn-default btn-xs" onclick="searchPersonRel();">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 검색
				</button>
			</span>
		</div>
		<form name="frmPersonRel" id="frmPersonRel">
		<input type="hidden" name="no_person" value="${personVO.no_person}" />
			<div class="panel-collapse toggle-cont">
				<table class="table table-classic tbl-info">
					<colgroup>
						<col width="10%">
						<col width="20%">
						<col width="10%">
						<col width="20%">
						<col width="10%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>이름</th>
							<td><input type="text" class="form-control" name="nm_person" value="${personVO.nm_person}" validate="label:이름; keydown-enter:searchPersonRel;"/></td>
							<th>생년월일</th>
							<td>
								<div class="input-group ssn-w">
									<input type="text" class="form-control" name="ssn_person_tmp" value="${personVO.ssn_person_idx1}" validate="label:생년월일; numeric; keyup-next:ssn_person_tmp; keydown-enter:searchPersonRel;" maxlength="6"/>
									<span class="input-group-addon" id="basic-addon1">-</span>
									<input type="text" class="form-control" name="ssn_person_tmp" value="${personVO.ssn_person_idx2}" validate="label:생년월일; numeric; keydown-enter:searchPersonRel;" maxlength="1"/>	
								</div>
							</td>
							<th>전화번호</th>
							<td>
								<c:choose>
				              		<c:when test="${(!empty personVO.no_person) && ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">
				              			${ufn:formatMaskHp(personVO.hp)}
				              		</c:when>
				              		<c:otherwise>
				              			<div class="input-group phone-w">
											<select name="hp_tmp" id="hp_tmp_type" class="selectpicker" validate="label:휴대폰; ">
												${ufn:makeOptions("cd_hp_kind","구분", personVO.hp_idx1)}
											</select>
											<span class="input-group-addon" id="basic-addon1">-</span>
											<input type="text" class="form-control" name="hp_tmp" value="${personVO.hp_idx2}" validate="label:휴대폰;numeric;keyup-next:hp_tmp; keydown-enter:searchPersonRel;" maxlength="4"/>
											<span class="input-group-addon" id="basic-addon1">-</span>
											<input type="text" class="form-control" name="hp_tmp" value="${personVO.hp_idx3}" validate="label:휴대폰;numeric; keydown-enter:searchPersonRel;" maxlength="4"/>	
										</div>
				              		</c:otherwise>
				              	</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<!-- 연계검사 리스트-->
	<div id="listPersonRel">
	 	<%@ include file="/WEB-INF/views/person/listPersonRel.jsp"%>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>