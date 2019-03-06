<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>고객 계약정보</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<%@ include file="/WEB-INF/include/postCode.jsp"%>
<script type="text/javascript">
	var layout;
	$(document).ready(function () {
		$('body').layout({
				minSize:				40
			,	east__size:				"45%" //우측 width
			,	north__spacing_open:	0
			,	north__resizable:		false
			
			,	spacing_open:			5
			,	spacing_closed:			5
			,	togglerLength_open:		50
			,	togglerLength_closed:	"100%"
			,	east__initClosed:		false
		});
		
	});
	
	// 사전신청서 오픈시 입력된 접수번호 
	var no_apply_active = "${prepareForm.no_apply}";
	
	function goCustRel(no_person, no_prepare, yn_grt) {
		// 모달창이 켜질 때 기본정보 , 보증인을 구분하기 위함
		frmPrepare.yn_grt.value = yn_grt;

		var data = {"no_person":no_person,"no_prepare":no_prepare,"yn_grt":yn_grt};
		vLoad("modal-content-lg","<c:url value='/person/listCustRelMain.crz'/>",data,false);
	}
	
	function createPrepareInfo() {
		
		window.setupValidateForm( frmPrepare );
		
		if(!IsNull(frmPrepare.no_prepare)) {
			alert("이미 신규작성된 신청서 입니다.");
			return false;
		}
		
		if(IsNull(frmPrepare.no_person)) {
			alert("고객찾기를 진행하여 주세요.");
			return false;
		}
		
		if ( !frmPrepare.validateForm() ) return false;
		var data = frmPrepare.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/prepare/createPrepareInfo.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				frmPrepare.no_prepare.value = result.no_prepare;
				
				if(parseInt(returnData.cd_result,10) == 0) {
					loadPrepareForm();
				} else {
					alert(returnData.message);
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
		
	}
	
	function updateOverlapChk() {
		
		window.setupValidateForm( frmPrepare );
		
		if(IsNull(frmPrepare.no_prepare)) {
			alert("신규작성을 진행하여 주세요.");
			return false;
		}
		
		if(IsNull(frmPrepare.no_person)) {
			alert("고객찾기를 진행하여 주세요.");
			return false;
		}
		
		if ( !frmPrepare.validateForm() ) return false;
		var data = frmPrepare.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/prepare/updateOverlapChk.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				
				if(parseInt(returnData.cd_result,10) == 0) {
					loadPrepareForm();
				} else {
					alert(returnData.message);
				}
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
	}
	
	function loadPrepareForm() {
		window.setupValidateForm( frmPrepare );
		
		var data = frmPrepare.ajaxSubmit();
		if(data == null) return false;
		
		vLoad("formPrepare","<c:url value='/prepare/formPrepare.crz'/>",data);
		
	}
	
	function modifyPrepare() {
		
		
		window.setupValidateForm( frmPrepareForm );
		
		if ( !frmPrepareForm.validateForm() ) return false;
		var data = frmPrepareForm.ajaxSubmit();
		if(data == null) return false;
		
		$.ajax({
			url : "<c:url value='/prepare/modifyPrepare.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
				// 새로고침
				loadPrepareForm();
				loadPrepareTab('HIST');
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
		
	}
	
	function getPerson(no_person) {
		
		var data = {"no_person":no_person, "yn_modal": "Y"};
		vLoad("modal-content-lg","<c:url value='/person/formPerson.crz'/>",data,false);
		
	}
	
	function loadApplyHist(tr,no_prepare) {
		$(tr).siblings().removeClass();
		$(tr).addClass("warning");
		$("#formModifyApply").html("");
		
		var data = {"no_prepare":no_prepare,"no_apply":no_apply_active};
		vLoad("applySummary","<c:url value='/apply/listApplyByPrepare.crz'/>",data);
	}
	
	function loadApplyForm(tr,no_apply) {
		$(tr).siblings().removeClass();
		$(tr).addClass("warning");
		$("#formModifyApply").show();
		
		var data = {"no_apply":no_apply};
		vLoad("formModifyApply","<c:url value='/apply/formModifyApply.crz'/>",data);
	}
	
	function loadApplyMemo(tr,no_apply) {
		$(tr).siblings().removeClass();
		$(tr).addClass("warning");
		$("#listApplyMemo").show();
		
		var no_person = frmPrepare.no_person.value;
		var data = {"no_person":no_person,"no_apply":no_apply};
		vLoad("listApplyMemo","<c:url value='/counsel/listApplyMemo.crz'/>",data);
	}
	
	function loadPrepareTab(type) {
		
		var no_prepare = frmPrepare.no_prepare.value;
		
		frmPrepare.no_apply.value = no_apply_active;
		window.setupValidateForm( frmPrepare );
		
		var data = frmPrepare.ajaxSubmit();
		if(data == null) return false;
		
		if (frmPrepare.no_prepare.value != null && frmPrepare.no_prepare.value != "") {
			if("HIST" == type)
				vLoad("formPrepareTab","<c:url value='/prepare/prepareHist.crz'/>",data);
			else if("SMS" == type)
				vLoad("formPrepareTab","<c:url value='/sms/listFormSms.crz'/>",data);
			else if("ALARM" == type)
				vLoad("formPrepareTab","<c:url value='/alarm/listAlarmMain.crz'/>",data);
		}
	}
	
	// 연계검사 팝업	
	function popPersonRelation() {
		var no_person = frmPrepare.no_person.value;
		var openWindow = window.open("", "popPersonRelation"+no_person, "width=900, height=600, scrollbars=yes, resizable=yes", "");
		
		document.frmPrepare.target = "popPersonRelation"+no_person;
		document.frmPrepare.action = "<c:url value='/person/listPersonRelMain.crz'/>";
		document.frmPrepare.method = "post";
		document.frmPrepare.submit();
		openWindow.focus();
	}
	
	function viewPersonnalInfo(no_person, cd_personal) {
		
	<c:choose>
		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') eq '03'}">
		var data = {"no_person":no_person,"cd_personal":cd_personal};
		
		$.ajax({
			url : "<c:url value='/person/viewPersonnal.json'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				var returnData = result.returnData;
				alert(returnData.message);
			},
			error : function (e) {
				alert(e.responseText);
			}
		});
		</c:when>
		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') eq '04'}">
		var openWindow = window.open("", "popPersonnal"+no_person, "width=410,height=210,scrollbars=no,resizable=no","");
		
		frmPersonnal.no_person.value = no_person;
		frmPersonnal.cd_personal.value = cd_personal;
		frmPersonnal.target = "popPersonnal"+no_person;
		frmPersonnal.action = "<c:url value='/person/viewPersonnal.crz'/>";
		frmPersonnal.method = "post";
		frmPersonnal.submit();
		
		openWindow.focus();
		</c:when>
	</c:choose>
	}
	
	function goCreditInqPop(){
		frmPersonnal.action = "<c:url value='/attach/popFrameFormAttach.crz'/>";
		frmPersonnal.method = "post";
		frmPersonnal.submit();
	}
	
</script>
</head>
<body>
<form name="frmPersonnal">
<input type="hidden" name="no_person" value="${personVO.no_person}"/>
<input type="hidden" name="cd_personal"/>
</form>
	<!-- Layout-North -->
	<div class="ui-layout-north">
		<div class="navbar navbar-default color-bar">
		<div class="ui-layout-content">
			<div class="cust-credit pull-left">
				<div class="top-row">
					<dl class="stat1">
						<dt><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 고객명</dt>
				        <dd>${personVO.nm_person}</dd>
					</dl>
					
					<dl class="stat1">
						<dt><span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span> 생년월일</dt>
				        <dd>${ufn:formatDate(personVO.ymd_birth)}</dd>
					</dl>
					 
					<dl class="stat3">
						<dt><span class="glyphicon glyphicon-list" aria-hidden="true"></span> 서류함</dt>
				        <dd class="selected">
				        	✔ ${ufn:getCodeName('cd_prepare_doc_box', prepareVO.cd_prepare_doc_box)}
				        	<c:choose>
				        		<c:when test="${prepareVO.cd_prepare_doc_box eq '20'}">
				        			(${ufn:getCodeName('cd_prepare_class', prepareVO.cd_prepare_class)})
				        		</c:when>
				        		<c:when test="${prepareVO.cd_prepare_doc_box eq '50'}">
				        			(${ufn:getCodeName('cd_apply_class', prepareVO.cd_prepare_class)})
				        		</c:when>
				        	</c:choose>
				        </dd>
					</dl>
					
					<dl class="stat2">
						<dt><span class="glyphicon glyphicon-bookmark" aria-hidden="true"></span> 상태</dt>
						<c:if test="${prepareVO.cd_prepare_doc_box eq '50'}">
							<c:choose>
								<c:when test="${prepareVO.cd_prepare_class eq '50'}">
							        <dd class="selected">✔ 승인</dd>
							        <dd>✔ 부결</dd>
								</c:when>
								<c:when test="${prepareVO.cd_prepare_class eq '60'}">
							        <dd>✔ 승인</dd>
							        <dd class="selected">✔ 부결</dd>
								</c:when>
								<c:otherwise>
							        <dd>✔ 승인</dd>
							        <dd>✔ 부결</dd>
								</c:otherwise>
							</c:choose>
						</c:if>
					</dl>
				</div>
			</div>
		</div>
		</div>
	</div>
	<!-- //Layout-North -->

	<div class="ui-layout-center">
		<div id="formPrepare" class="ui-layout-content">
			<!-- Content -->
			<%@ include file="/WEB-INF/views/prepare/formPrepare.jsp" %>
			<!-- //Content -->
		</div>
	</div>
	<!-- Layout-West -->
	<div class="ui-layout-east">
		<div class="ui-layout-content">
			<!-- Content -->
			<!-- Nav tabs -->
			<div role="tabpanel">
				<ul class="nav nav-tabs tab-sm" role="tablist">
					<li role="presentation" class="active" onclick="loadPrepareTab('HIST');"><a href="#cont1"	aria-controls="cont1" role="tab" data-toggle="tab">접수이력</a></li>
				</ul>
			</div>
			
			<div id="formPrepareTab">
				<%@ include file="/WEB-INF/views/prepare/formPrepareTab.jsp"%>
			</div>
		</div>
	</div>
<!-- //Layout-West -->
<%@ include file="/WEB-INF/views/comm/modal.jsp"%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
<iframe name="_proc" id="_proc" frameborder="0" width="0" height="0"></iframe>
</body>
</html>