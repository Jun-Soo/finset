<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${personVO.nm_person} 고객정보 확인</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	function modifyHp() {
		
		if ( !frmPerson.validateForm() ) return false;
		
		var data = frmPerson.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/person/modifyHp.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				if (parseInt(returnData.cd_result,10) == 0) {
					self.close();
				} 
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
		
		
	}
</script>
</head>
<body>
<div class="ui-layout-content">
	<div class="alert alert-success alert-dismissible exclamation-list alert-lg" role="alert" >
		<ul>
			<li>시스템 접근권한 및 계정관리 정책에 의거 개인정보 조회시 <strong>조회기록</strong>이 남습니다. 유의하시기 바랍니다.</li>
		</ul>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="h-sec pull-left">열람정보</h3>
			<span class="pull-right">
				<c:choose>
	        		<c:when test="${(!empty personVO.no_person) && ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') eq '04'}">
						<button type="button" class="btn btn-default btn-xs" onclick="modifyHp();">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 변경
						</button>
	        		</c:when>
	        	</c:choose>
			</span>
		</div>
		<div class="panel-collapse toggle-cont">
		<form name="frmPerson">
		<input type="hidden" name="no_person" value="${personVO.no_person}"/>
		<table class="table table-classic">
			<colgroup>
				<col width="30%"/>
				<col width="*"/>
			</colgroup>
		
			<tbody>
				<tr>
					<th>휴대폰 번호</th>
					<td>
						<c:choose>
			        		<c:when test="${(!empty personVO.no_person) && ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') eq '04'}">
								<div class="input-group phone-w">
									<select name="hp_tmp" class="selectpicker" validate="required; label:휴대폰;">
										${ufn:makeOptions("cd_hp_kind","구분", personVO.hp_idx1)}
									</select>
									<span class="input-group-addon" id="basic-addon1">-</span>
									<input type="text" class="form-control" name="hp_tmp" value="${personVO.hp_idx2}" validate="required;label:휴대폰;numeric;keyup-next:hp_tmp" maxlength="4"/>
									<span class="input-group-addon" id="basic-addon1">-</span>
									<input type="text" class="form-control" name="hp_tmp" value="${personVO.hp_idx3}" validate="required;label:휴대폰;numeric;" maxlength="4"/>	
								</div>
			        		</c:when>
			        	</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>