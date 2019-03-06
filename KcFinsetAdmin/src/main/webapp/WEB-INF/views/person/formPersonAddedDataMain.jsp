<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<br><br>
<c:choose>
	<c:when test="${empty person.no_person}">
		<title>신규고객등록</title>
	</c:when>
	<c:otherwise>
		<title>${person.nm_person} 정보수정</title>
	</c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<%@ include file="/WEB-INF/include/postCode.jsp"%>
<script type="text/javascript">
var showCashService = 0;
function loadNiceCashServiceList(no_person, ynShow) {
	//alert(showCashService + "loadNiceCashServiceList");
	if(ynShow != '') showCashService = ynShow;
	if(showCashService == 0) $("#formNiceCashService").show(100, function(){showCashService = 1;});
	else $("#formNiceCashService").hide(100, function(){showCashService = 0;});
	var data = {"no_person":no_person};
	vLoad("listPersonNiceCashService","<c:url value='/person/listPersonNiceCashService.crz'/>",data);
}
var showDebtGuarantee = 0;
function loadNiceDebtGuaranteeList(no_person, ynShow) { 
	$("#formNiceDebtGuarantee").hide();
	if(ynShow != '') showDebtGuarantee = ynShow;
	if(showDebtGuarantee == 0) $("#formNiceCashService").show(100, function(){showDebtGuarantee = 1;});
	else $("#formNiceCashService").hide(100, function(){showDebtGuarantee = 0;});
	var data = {"no_person":no_person};
	vLoad("listPersonNiceDebtGuarantee","<c:url value='/person/listPersonNiceDebtGuarantee.crz'/>",data);
}
var showDebtAdjustment = 0;
function loadNiceDebtAdjustmentList(no_person, ynShow) {
	$("#formNiceDebtAdjustment").hide();
	var data = {"no_person":no_person};
	vLoad("listPersonNiceDebtAdjustment","<c:url value='/person/listPersonNiceDebtAdjustment.crz'/>",data);
}
var showDefaultBank = 0;
function loadNiceDefaultBankList(no_person, ynShow) {
	$("#formNiceDefaultBank").hide();
	var data = {"no_person":no_person};
	vLoad("listPersonNiceDefaultBank","<c:url value='/person/listPersonNiceDefaultBank.crz'/>",data);
}
var showDefaultNice = 0;
function loadNiceDefaultNiceList(no_person, ynShow) {
	$("#formNiceDefaultNice").hide();
	var data = {"no_person":no_person};
	vLoad("listPersonNiceDefaultNice","<c:url value='/person/listPersonNiceDefaultNice.crz'/>",data);
}
var showDelayNice = 0;
function loadNiceDelayNiceList(no_person, ynShow) {     
	$("#formNiceDelayNice").hide();
	var data = {"no_person":no_person};
	vLoad("listPersonNiceDelayNice","<c:url value='/person/listPersonNiceDelayNice.crz'/>",data);
}
function loadNiceCashServiceForm(tr, no_nice_cash_service, no_person, ynShow){
	//alert(showCashService + ynShow);
	$(tr).siblings().removeClass();
	$(tr).addClass("warning");
	if(ynShow == 'Y'){
		$("#formNiceCashService").show(100, function(){showCashService = 1;});
	}else if(ynShow == 'N'){
		$("#formNiceCashService").hide(100, function(){showCashService = 0;});
	}else{
		//0이면 show =====> 후에 다음을 위해 1셋팅
		if(showCashService == 0) $("#formNiceCashService").show(100, function(){showCashService = 1;});
		else $("#formNiceCashService").hide(100, function(){showCashService = 0;});
	}
	var data={"no_nice_cash_service":no_nice_cash_service, "no_person":no_person};
	vLoad("formNiceCashService", "<c:url value='/person/formNiceCashService.crz'/>", data);
}
function loadNiceDebtGuaranteeForm(tr, no_nice_debt_guarantee, no_person, ynShow){
	//alert("loadNiceDebtGuaranteeForm:"+ tr + ":" + no_nice_debt_guarantee + ":" + no_person + ":"+ showDebtGuarantee);
	$(tr).siblings().removeClass();
	$(tr).addClass("warning");
	if(ynShow == 'Y'){
		$("#formNiceDebtGuarantee").show(100, function(){showDebtGuarantee = 1;});
	}else if(ynShow == 'N'){
		$("#formNiceDebtGuarantee").hide(100, function(){showDebtGuarantee = 0;});
	}else{
		if(showDebtGuarantee == 0) $("#formNiceDebtGuarantee").show(100, function(){showDebtGuarantee = 1;});
		else $("#formNiceDebtGuarantee").hide(100, function(){showDebtGuarantee = 0;});
	}
	var data={"no_nice_debt_guarantee":no_nice_debt_guarantee, "no_person":no_person};
	vLoad("formNiceDebtGuarantee", "<c:url value='/person/formNiceDebtGuarantee.crz'/>", data);
}
function loadNiceDebtAdjustmentForm(tr, no_nice_debt_adjustment, no_person, ynShow){
	//alert(tr + ":" + no_nice_debt_adjustment + ":" + no_person + ":"+ showDebtAdjustment);
	$(tr).siblings().removeClass();
	$(tr).addClass("warning");
	if(ynShow == 'Y'){
		$("#formNiceDebtAdjustment").show(100, function(){showDebtAdjustment = 1;});
	}else if(ynShow == 'N'){
		$("#formNiceDebtAdjustment").hide(100, function(){showDebtAdjustment = 0;});
	}else{
		if(showDebtAdjustment == 0) $("#formNiceDebtAdjustment").show(100, function(){showDebtAdjustment = 1;});
		else $("#formNiceDebtAdjustment").hide(100, function(){showDebtAdjustment = 0;});
	}
	var data={"no_nice_debt_adjustment":no_nice_debt_adjustment, "no_person":no_person};
	vLoad("formNiceDebtAdjustment", "<c:url value='/person/formNiceDebtAdjustment.crz'/>", data);
}
function loadNiceDefaultBankForm(tr, no_nice_default_bank, no_person, ynShow){
	//alert(tr + ":" + no_nice_default_bank + ":" + no_person + ":"+ showDefaultBank);
	$(tr).siblings().removeClass();
	$(tr).addClass("warning");
	if(ynShow == 'Y'){
		$("#formNiceDefaultBank").show(100, function(){showDefaultBank = 1;});
	}else if(ynShow == 'N'){
		$("#formNiceDefaultBank").hide(100, function(){showDefaultBank = 0;});
	}else{
		if(showDefaultBank == 0) $("#formNiceDefaultBank").show(100, function(){showDefaultBank = 1;});
		else $("#formNiceDefaultBank").hide(100, function(){showDefaultBank = 0;});
	}
	var data={"no_nice_default_bank":no_nice_default_bank, "no_person":no_person};
	vLoad("formNiceDefaultBank", "<c:url value='/person/formNiceDefaultBank.crz'/>", data);
}

function loadNiceDefaultNiceForm(tr, no_nice_default_nice, no_person, ynShow){
	//alert(tr + ":" + no_nice_default_nice + ":" + no_person + ":"+ showDefaultNice);
	$(tr).siblings().removeClass();
	$(tr).addClass("warning");
	if(ynShow == 'Y'){
		$("#formNiceDefaultNice").show(100, function(){showDefaultNice = 1;});
	}else if(ynShow == 'N'){
		$("#formNiceDefaultNice").hide(100, function(){showDefaultNice = 0;});
	}else{
		if(showDefaultNice == 0) $("#formNiceDefaultNice").show(100, function(){showDefaultNice = 1;});
		else $("#formNiceDefaultNice").hide(100, function(){showDefaultNice = 0;});
	}
	var data={"no_nice_default_nice":no_nice_default_nice, "no_person":no_person};
	vLoad("formNiceDefaultNice", "<c:url value='/person/formNiceDefaultNice.crz'/>", data);
}
function loadNiceDelayNiceForm(tr, no_nice_delay_nice, no_person, ynShow){
	//alert(tr + ":" + no_nice_delay_nice + ":" + no_person + ":"+ showDelayNice);
	$(tr).siblings().removeClass();
	$(tr).addClass("warning");
	if(ynShow == 'Y'){
		$("#formNiceDelayNice").show(100, function(){showDelayNice = 1;});
	}else if(ynShow == 'N'){
		$("#formNiceDelayNice").hide(100, function(){showDelayNice = 0;});
	}else{
		if(showDelayNice == 0) $("#formNiceDelayNice").show(100, function(){showDelayNice = 1;});
		else $("#formNiceDelayNice").hide(100, function(){showDelayNice = 0;});
	}
	var data={"no_nice_delay_nice":no_nice_delay_nice, "no_person":no_person};
	vLoad("formNiceDelayNice", "<c:url value='/person/formNiceDelayNice.crz'/>", data);
}
function procPersonNiceCashService() {
	showCashService = 0;
	window.setupValidateForm( frmNiceCashService );
	if ( !frmNiceCashService.validateForm() ) return false;
	var data = frmNiceCashService.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/person/procPersonNiceCashService.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceCashServiceList("${no_person}", "N");
		},
		error : function (e) {
			alert(e.responseText);
			hotkey = false;
		}
	});
}
function procPersonNiceDebtGuarantee() {
	showDebtGuarantee = 0;
	window.setupValidateForm( frmNiceDebtGuarantee );
	if ( !frmNiceDebtGuarantee.validateForm() ) return false;
	var data = frmNiceDebtGuarantee.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/person/procPersonNiceDebtGuarantee.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDebtGuaranteeList("${no_person}", "N");
		},
		error : function (e) {
			alert(e.responseText);
			hotkey = false;
		}
	});
}
function procPersonNiceDebtAdjustment() {
	showDebtAdjustment = 0;
	window.setupValidateForm( frmNiceDebtAdjustment );
	if ( !frmNiceDebtAdjustment.validateForm() ) return false;
	var data = frmNiceDebtAdjustment.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/person/procPersonNiceDebtAdjustment.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDebtAdjustmentList("${no_person}", "N");
		},
		error : function (e) {
			alert(e.responseText);
			hotkey = false;
		}
	});
}
function procPersonNiceDefaultBank() {
	showDefaultBank = 0;
	window.setupValidateForm( frmNiceDefaultBank );
	if ( !frmNiceDefaultBank.validateForm() ) return false;
	var data = frmNiceDefaultBank.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/person/procPersonNiceDefaultBank.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDefaultBankList("${no_person}", "N");
		},
		error : function (e) {
			alert(e.responseText);
			hotkey = false;
		}
	});
}
function procPersonNiceDefaultNice() {
	showDefaultNice = 0;
	window.setupValidateForm( frmNiceDefaultNice );
	if ( !frmNiceDefaultNice.validateForm() ) return false;
	var data = frmNiceDefaultNice.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/person/procPersonNiceDefaultNice.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDefaultNiceList("${no_person}", "N");
		},
		error : function (e) {
			alert(e.responseText);
			hotkey = false;
		}
	});
}
function procPersonNiceDelayNice() {
	showDelayNice = 0;
	window.setupValidateForm( frmNiceDelayNice );
	if ( !frmNiceDelayNice.validateForm() ) return false;
	var data = frmNiceDelayNice.ajaxSubmit();
	if(data == null) return false;
	$.ajax({
		url : "<c:url value='/person/procPersonNiceDelayNice.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			loadNiceDelayNiceList("${no_person}", "N");
		},
		error : function (e) {
			alert(e.responseText);
			hotkey = false;
		}
	});
}
</script>
</head>
<body>
	<!-- Layout-North -->
	<div class="ui-layout-north">
		<div class="navbar navbar-default color-bar">
		<div class="ui-layout-content">
			<div class="cust-credit pull-left">
				<div class="top-row">
				<c:if test="${personForm.yn_modal eq 'Y'}">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				</c:if>
				<c:choose>
					<c:when test="${empty person.nm_person}">
						<dt><h4 class="modal-title" id="largeModalLabel"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 고객등록</dt>
					</c:when>
					<c:otherwise>
						<dt><h4 class="modal-title" id="largeModalLabel"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 고객명 ${person.nm_person} 정보수정</dt>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
		</div>
	</div>
	<!-- //Layout-North -->
	<!-- //ui-layout-center -->
	<%-- <div class="ui-layout-center">
		<div class="ui-layout-content">
	
			<div id="personInfo">
				<%@ include file="/WEB-INF/views/person/formPerson.jsp"%>
			</div>
		</div>
	</div> --%>
	<!-- ui-layout-east -->
	<div class="ui-layout-east">
		<div class="ui-layout-content">
			<%-- <div id="formPersonNice"><%@ include file="/WEB-INF/views/person/formPersonNice.jsp"%></div>
			<div id="formPersonKcb"><%@ include file="/WEB-INF/views/person/formPersonKcb.jsp"%></div>
			<div id="formPersonNice12Month"><%@ include file="/WEB-INF/views/person/formPersonNice12Month.jsp"%></div>
			<div id="formPersonKcb12Month"><%@ include file="/WEB-INF/views/person/formPersonKcb12Month.jsp"%></div> --%>
			<%-- <div id="listPersonNiceLoan"><%@ include file="/WEB-INF/views/person/listPersonNiceLoan.jsp"%></div>
			<div id="formNiceLoan" style="display:none;"></div> --%>

<div id="listPersonNiceCashService"><%@include file="/WEB-INF/views/person/listPersonNiceCashService.jsp"%></div>
<div id="formNiceCashService" style="display:none;"></div>

<div id="listPersonNiceDebtGuarantee"><%@include file="/WEB-INF/views/person/listPersonNiceDebtGuarantee.jsp"%></div>
<div id="formNiceDebtGuarantee" style="display:none;"></div>

<div id="listPersonNiceDebtAdjustment"><%@include file="/WEB-INF/views/person/listPersonNiceDebtAdjustment.jsp"%></div>
<div id="formNiceDebtAdjustment" style="display:none;"></div>

<div id="listPersonNiceDefaultBank"><%@include file="/WEB-INF/views/person/listPersonNiceDefaultBank.jsp"%></div>
<div id="formNiceDefaultBank" style="display:none;"></div>

<div id="listPersonNiceDefaultNice"><%@include file="/WEB-INF/views/person/listPersonNiceDefaultNice.jsp"%></div>
<div id="formNiceDefaultNice" style="display:none;"></div>

<div id="listPersonNiceDelayNice"><%@include file="/WEB-INF/views/person/listPersonNiceDelayNice.jsp"%></div>
<div id="formNiceDelayNice" style="display:none;"></div>

			<%-- <div id="formNiceLoan"><%@ include file="/WEB-INF/views/person/formNiceLoan.jsp"%></div> --%>
			<%-- <div id="listPersonKcbLoan"><%@ include file="/WEB-INF/views/person/listPersonKcbLoan.jsp"%></div> --%>
			<%-- <div id="formKcbLoan" style="display: none;"><%@ include file="/WEB-INF/views/person/formKcbLoan.jsp"%></div> --%>
		</div>
	</div>
<!-- //Layout-West -->
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>