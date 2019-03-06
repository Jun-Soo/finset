<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	jumpPage(1);
});

// 페이지 이동
function jumpPage(page) {
	
	var yn_personal = "Y";
	var no_person = frmModalPersonInfoHist.no_person.value;
	var data = {"no_person":no_person, "yn_personal":yn_personal, "page":page};
	
	vLoad("listPersonInfoHist","<c:url value='/person/listPersonInfoHist.crz'/>",data);
}
</script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="largeModalLabel">고객정보 수정이력</h4>
</div>
<div class="modal-body">
	<form name="frmModalPersonInfoHist" id="frmModalPersonInfoHist" class="navbar-form">
		<input type="hidden" name="no_person" id="no_person" value="${personForm.no_person}"/>
		<div id="listPersonInfoHist">
			<%@ include file="/WEB-INF/views/person/listPersonInfoHist.jsp"%>
		</div>
	</form>
</div>
<div class="modal-footer">
</div>