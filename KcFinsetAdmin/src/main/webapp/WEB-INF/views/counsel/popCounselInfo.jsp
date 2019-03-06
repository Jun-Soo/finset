<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>메모 팝업</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		$('.selectpicker').selectpicker();
		
		<c:choose>
			<c:when test="${empty counselVO.no_apply}">
				<c:set var="counselMemo" value="class='active'"/>
				listCounselInfo('${counselVO.no_person}','${counselVO.no_prepare}');
				//$('#formCounsel').show();
			</c:when>
			<c:otherwise>
				<c:set var="applyMemo" value="class='active'"/>
				loadApplyMemo('${counselVO.no_person}','${counselVO.no_prepare}','${counselVO.no_apply}');
				//$('#formCounsel').hide();
			</c:otherwise>
		</c:choose>
		
		window.setupValidateForm( frmCounselInfo );
	});
	
	function loadApplyMemo(no_person, no_prepare, no_apply) {
		var data = {"no_person":no_person,"no_apply":no_apply};
		vLoad("listCounselInfo","<c:url value='/counsel/listApplyMemo.crz'/>",data);
	}
</script>
</head>
<body>
	<div class="ui-layout-east">
		<div class="ui-layout-content">
			<div role="tabpanel">
				<ul class="nav nav-tabs tab-sm" role="tablist">
					<li role="presentation" ${counselMemo} onclick="listCounselInfo('${counselVO.no_person}','${counselVO.no_prepare}');"><a href="#cont1" aria-controls="cont1" role="tab" data-toggle="tab">상담메모</a></li>
					<li role="presentation" ${applyMemo} onclick="loadApplyMemo('${counselVO.no_person}','${counselVO.no_prepare}','${counselVO.no_apply}');"><a href="#cont2" aria-controls="cont1" role="tab" data-toggle="tab">금융사 메모</a></li>
				</ul>
			</div>
			
			<!-- 상담메모 폼 -->
			<div id="formCounsel" class="panel-collapse toggle-cont">
			 	<%@ include file="/WEB-INF/views/counsel/formCounsel.jsp"%>
			</div>
				
			<!-- 상담메모 리스트 -->
			<div id="listCounselInfo" class="panel-collapse toggle-cont">
			 	<%@ include file="/WEB-INF/views/counsel/listCounselInfo.jsp"%>
			</div> 
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>