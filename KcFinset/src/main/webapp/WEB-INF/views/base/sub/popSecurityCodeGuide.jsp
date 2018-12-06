<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<div class="modal-body">
	<div class="help-block">
		서비스를 이용하시는 고객님의 개인정보 및 
		금융정보의 보호를 위한 보호장치 입니다. 
		앱 실행, 서비스 이용 단계 중 비밀번호를 요구하며,
		00회 이상 입력 오류 발생 시 재인증을 통하여 
		비밀번호를 재설정 해야합니다. 
		마이페이지 – 비밀번호 재설정 페이지에서 
		코드를 재설정할 수 있으니 안전한 서비스 이용을 위해
		주기적으로 비밀번호를 변경해주시기 바랍니다.
	</div>
</div>
<a role="button" class="btn btn-lg btn-primary btn-block" onclick="popSecurityInfo()">확인</a>
