<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<c:choose>
	<c:when test="${empty fincorpInfo.cd_fc}">
		<title>신규고객등록</title>
	</c:when>
	<c:otherwise>
		<title>${fincorpInfo.nm_fc} 정보수정</title>
	</c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<%@ include file="/WEB-INF/include/postCode.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('body').layout({
		minSize:				40
		,	east__size:				"30%"//우측 width
 		,	north__spacing_open:	0
		,	north__resizable:		false
		
		,	spacing_open:			5
		,	spacing_closed:			5
		,	togglerLength_open:		50
		,	togglerLength_closed:	"100%"
		,	east__initClosed:		false
	});
	
		
	$("#createNm_fc").click(function(){
		var nm_nm_fc = $("#nm_nm_fc").val();
		var nm_yn_use = $("#nm_yn_use").val();
		var cd_fc = $("#cd_fc").val();
		var seq= $("#seq").val();
		
		if(cd_fc == ""){
			alert("금융기관명에서 검색하여 금융기관코드를 입력해주세요.");
			return false;
		}
		
		if(nm_nm_fc == ""){
			alert("은행명을 입력해주세요.");
			return false;
		}
		
		$.ajax({
	 		url: "<c:url value='/fincorp/createFincorpfcNminfo.json'/>",
	 		type: "POST",
	 		data : {"nm_nm_fc":nm_nm_fc,"nm_yn_use":nm_yn_use, "cd_fc":cd_fc, "seq":seq},
	 		success : function (result) {
				alert(result.result);
				loadnmfcinfo(cd_fc);
				$("#nm_nm_fc").val("");
				$("#nm_yn_use").val("");
				$('#nm_yn_use').selectpicker('refresh');
			},
			error : function (e) {
				alert(e.responseText);
			}
	 	});

	});
	
	$("#updNm_fc").click(function(){
		var nm_nm_fc = $("#nm_nm_fc").val();
		var nm_yn_use = $("#nm_yn_use").val();
		var cd_fc = $("#cd_fc").val();
		var seq= $("#seq").val();
		
		if(cd_fc == ""){
			alert("금융기관명에서 검색하여 금융기관코드를 입력해주세요.");
			return false;
		}
		
		if(nm_nm_fc == ""){
			alert("은행명을 입력해주세요.");
			return false;
		}
		
		$.ajax({
	 		url: "<c:url value='/fincorp/updFincorpfcNminfo.json'/>",
	 		type: "POST",
	 		data : {"nm_nm_fc":nm_nm_fc,"nm_yn_use":nm_yn_use, "cd_fc":cd_fc, "seq":seq},
	 		success : function (result) {
				alert(result.result);
				loadnmfcinfo(cd_fc);
				$("#nm_nm_fc").val("");
				$("#nm_yn_use").val("");
				$('#nm_yn_use').selectpicker('refresh');
			},
			error : function (e) {
				alert(e.responseText);
			}
	 	});

	});
	
	$("#delNm_fc").click(function(){
		var nm_nm_fc = $("#nm_nm_fc").val();
		var nm_yn_use = $("#nm_yn_use").val();
		var cd_fc = $("#cd_fc").val();
		var seq= $("#seq").val();
		
		$.ajax({
	 		url: "<c:url value='/fincorp/delFincorpfcNminfo.json'/>",
	 		type: "POST",
	 		data : {"nm_nm_fc":nm_nm_fc,"nm_yn_use":nm_yn_use, "cd_fc":cd_fc, "seq":seq},
	 		success : function (result) {
				alert(result.result);
				loadnmfcinfo(cd_fc);
				$("#nm_nm_fc").val("");
				$("#nm_yn_use").val("");
				$('#nm_yn_use').selectpicker('refresh');	
			},
			error : function (e) {
				alert(e.responseText);
			}
	 	});

	});
		
});


/*신규 저장*/
function createFincorp() {

	var frm = document.frmFincorpInfo;
	if(!frm.validateForm()) return false;
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$("#email_con").val($("#email_con_tmp").val()+"@"+$("#email_con_tmp_2").val());
	$("#email_adj").val($("#email_adj_tmp").val()+"@"+$("#email_adj_tmp_2").val());
	
	if(!frmFincorpInfo.file1.value){
		$("#file1Space").html("<input type=\"hidden\" name=\"dumy_file1\" />");
	}

	$("#frmFincorpInfo").ajaxSubmit({
 		url: "<c:url value='/fincorp/createFincorp.json'/>",
 		type: "POST",
 		//data : data,
 		success : function (result) {
			alert(result.message);
			
			// 팝업이 닫혀야함, 금융사리스트 갱신되야함
			if(parseInt(result.cd_result,10) == 0) {
				opener.goSearch();
				self.close();
			} else{
				window.location.reload(true);
			}
		},
		error : function (e) {
			alert(e.responseText);
		}
 	});
			
	
	
}


/*저장*/
function procFincorpInfo() {

	var frm = document.frmFincorpInfo;
	if(!frm.validateForm()) return false;
	
	var data = frm.ajaxSubmit();
	if(data == null) return false;
	
	$("#email_con").val($("#email_con_tmp").val()+"@"+$("#email_con_tmp_2").val());
	$("#email_adj").val($("#email_adj_tmp").val()+"@"+$("#email_adj_tmp_2").val());
	
	if(!frmFincorpInfo.file1.value){
		$("#file1Space").html("<input type=\"hidden\" name=\"dumy_file1\" />");
	}

	$("#frmFincorpInfo").ajaxSubmit({
 		url: "<c:url value='/fincorp/procFincorpInfo.json'/>",
 		type: "POST",
 		data : data,
 		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			// 팝업이 닫혀야함, 금융사리스트 갱신되야함
			if(parseInt(returnData.cd_result,10) == 0) {
				opener.goSearch();
				self.close();
			}
		},
		error : function (e) {
			alert(e.responseText);
		}
 	});

	
}

function delFincorpInfo(cd_fc) {

	var result = confirm('삭제 하시겠습니까?'); 

	if (!result) {
		return false;
	}
	
	var data = {"cd_fc":cd_fc}; 
	if(data == null) return false;
	
	$.ajax({
		url : "<c:url value='/fincorp/delFincorpInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			var returnData = result.returnData;
			alert(returnData.message);
			
			// 팝업이 닫혀야함, 금융사리스트 갱신되야함
				if(parseInt(returnData.cd_result,10) == 0) {
					opener.goSearch();
					self.close();
				}
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}
function loadnmfcinfo(cd_fc){
	var data = {"cd_fc":cd_fc};
	vLoad("listFincorpfcNminfo","<c:url value='/fincorp/listFincorpfcNminfo.crz'/>",data);
}

</script>
</head>
<body>
<c:if test="${cd_template_group eq '12'}">
<%@ include file="/WEB-INF/include/header.jsp"%>
</c:if>
	<!-- Layout-North -->
<%-- 	<c:choose>
	<c:when test="${empty fincorpInfo.cd_fc}">
		<div class="ui-layout-center">
			<div class="ui-layout-content">
				<!-- 기본정보 -->
				<div id="fincorpInfo">
					<%@ include file="/WEB-INF/views/fincorp/formPopFincorpInfo.jsp"%>
				</div>
				<!--기본정보  -->
			</div>
		</div>
	</c:when>
	<c:otherwise> --%>
		<!-- //Layout-North -->
		<!-- //ui-layout-center -->
		<div class="ui-layout-center">
			<div class="ui-layout-content">
				<!-- 기본정보 -->
				<div id="fincorpInfo">
					<%@ include file="/WEB-INF/views/fincorp/formFincorpInfo.jsp"%>
				</div>
				<!--기본정보  -->
			</div>
		</div>
		<!-- ui-layout-east -->
		<div class="ui-layout-east">
			<div class="ui-layout-content">
			<!-- tab -->
				
				<div id="formBusinessDay">
					<%@ include file="/WEB-INF/views/fincorp/businessDayMain.jsp"%>
				</div>
			</div>
		</div>
	<!-- //Layout-West -->
<%-- 	</c:otherwise>
</c:choose> --%>
<script type="text/javascript" src="<c:url value="/script/XE_validate.js"/>"></script>
</body>
</html>