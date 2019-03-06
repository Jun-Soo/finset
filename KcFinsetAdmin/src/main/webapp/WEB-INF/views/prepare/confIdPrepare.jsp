<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>분배설정</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	var layout;
	$(document).ready(function () {
		
		$("input[name=yn_auto_prepare_div]:checked").each(function() {
			var yn_auto = $(this).val();
			if(yn_auto == "Y") $('#display_addtion_DivInfo').show();
			else $('#display_addtion_DivInfo').hide();
		});	
		
	});
	
	function changePrepare(id,name) {
		
		if(!IsNull(frmConfig.div_not_in)) {
			var div_not_in = frmConfig.div_not_in.value;
			if(div_not_in.split(',').length > 50) {
				alert("분배제외 대상자는 최대 50명입니다.\n인사관리에서 상담사권한을 변경하세요.");
				return;
			}
		}
		
		var on_prepare = false;
		if("btn btn-default" == $("#"+id).attr("class")) {
			$("#"+id).attr("class","btn btn-link");
			on_prepare = false;
			alert("["+name+"]님을 분배대상자에서 제외 합니다.");
		} else {
			$("#"+id).attr("class","btn btn-default");
			on_prepare = true;
			//alert("["+name+"]님을 분배대상자로 설정 합니다.");
		}
		
		if(on_prepare) {
			frmConfig.div_not_in.value = frmConfig.div_not_in.value.replace(id+",","");
		} else {
			frmConfig.div_not_in.value += id+",";
		}
	}
	
	function setPrepareConfig() {
		
		if(!confirm("선택된 분배담당자로 변경 하시겠습니까?")){
			return false;
		}
		
		var data = frmConfig.ajaxSubmit();
		
		$.ajax({
			url : "<c:url value='/prepare/confIdPrepareDiv.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
		
	}
	
	function showDivAddtionInfo() {
		
		$("input[name=yn_auto_prepare_div]:checked").each(function() {
			var yn_auto = $(this).val();
			if(yn_auto == "Y") $('#display_addtion_DivInfo').show();
			else $('#display_addtion_DivInfo').hide();
		});	
		
		
	}
</script>
</head>
<body>
	<div class="ui-layout-content">
	
		<!-- Alert -->
		<div class="alert alert-success alert-dismissible exclamation-list" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span></button>
			<ul>
				<li>상담사를 선택,해제 하여 분배대상자를 선택하고 <strong>적용</strong>을 눌러주세요.</li>
			</ul>
		</div>
		<!--//Alert -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="h-sec pull-left">분배설정</h3>
				<span class="pull-right">
					<button type="button" class="btn btn-primary btn-xs" onclick="setPrepareConfig();">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> 적용
					</button>
				</span>
			</div>
			<div class="panel-collapse toggle-cont">
				<form name="frmConfig">
				<input name="div_not_in" type="hidden" value="${ufn:getCodeName('_CONF_PREPARE_DIV','DIV_NOT_IN')}"/>
				<table class="table table-classic tbl-info">
					<colgroup>
						<col width="20%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>작성자 상담여부</th>
							<td>
								${ufn:makeRadioAndCheckBoxs("yn_use", "yn_id_prepare", "RADIO", ufn:getCodeName('_CONF_PREPARE_DIV','YN_ID_PREPARE'), 2)}
							</td>
						</tr>
						<tr>
							<th>분배 사용여부</th>
							<td>
								${ufn:makeRadioAndCheckBoxsEvent("yn_use", "yn_auto_prepare_div", "RADIO", ufn:getCodeName('_CONF_PREPARE_DIV','YN_AUTO_PREPARE_DIV'), 2, "showDivAddtionInfo();")}
							</td>
						</tr>
							<tr id="display_addtion_DivInfo">
								<th>기존고객 분배설정</th>
								<td>
									${ufn:makeRadioAndCheckBoxs("cd_exist_div", "cd_exist_div", "RADIO", ufn:getCodeName('_CONF_PREPARE_DIV','CD_EXIST_DIV'), 3)}
								</td>
							</tr>
						<tr>
							<th>상담자 목록</th>
							<td>
								<c:forEach var="List" items="${listPrepare}" varStatus="status">
									<c:set var="b_class" value="btn btn-default" />
									<c:forEach var="id_emp" items="${listPrepareNot}">
										<c:if test="${List.id_emp eq id_emp}"><c:set var="b_class" value="btn btn-link"/></c:if>
									</c:forEach>
									<button type="button" id="${List.id_emp}" class="${b_class}" onclick="changePrepare('${List.id_emp}','${List.nm_emp}');this.blur();">
										<span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${List.nm_emp}(${List.id_emp})
									</button>
									<c:if test="${status.count % 4 eq '0'}"><br/></c:if>
								</c:forEach>
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