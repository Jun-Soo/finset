<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {

});
//페이지 이동
function jumpPage4(page) {
	$("#page").val(page);
	var data = frmlookupHist.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("lookupHist","<c:url value='/person/listPastMain.crz'/>",data);
}
function jumpPage5(page) {
	$("#page").val(page);
	var data = frmcounselHist.ajaxSubmit();
	if(data == null) return false;
	
	vLoad("counselHist","<c:url value='/counsel/listCounselHist.crz'/>",data);
}
</script>

		<!-- 실행이력 -->
		<div id="excuteHist">
				<%@ include file="/WEB-INF/views/person/listExcuteHist.jsp"%>
		</div>	
		
		<!-- 조회이력 -->
		<div id="lookupHist">
				<%@ include file="/WEB-INF/views/person/listLookupHist.jsp"%>
		</div>
		
		<!--상담이력  -->
		<div id="counselHist">
				<%@ include file="/WEB-INF/views/person/listCounselHist.jsp"%>
		</div>
			
		<!-- 상담메모 폼 -->
		<div id="formCounsel">
		 	<%@ include file="/WEB-INF/views/person/formCounsel.jsp"%>
		</div>	