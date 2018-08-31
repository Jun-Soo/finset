<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
        <c:if test="${site eq 'LOCAL'}">
        testOnload();
        </c:if>        <%--alert("파라메터 \ncd_goods:${txFcTransmitVO.cd_goods }\nno_person=${txFcTransmitVO.no_person}\nhp1=${txFcTransmitVO.hp1 }\ncd_fc=${txFcTransmitVO.cd_fc }");--%>

	    //최상단 체크박스 클릭
		$('#checkall').change(function() {
 			if ($('#checkall').is(':checked')) {
 				$(':checkbox').prop('checked', true);
 				$(this).parents(".checkbox").addClass("check");
 				$(':checkbox').parents(".panel-heading").addClass("check");
// 				for(var i=1; i<=2; i++){
//			        $('#panel'+i).removeClass();
//			        $('#panel'+i).addClass("panel-collapse collapse");
// 				}
 				affixBottom('show');
 				
  			} else {
 				$(':checkbox').prop('checked', false);
 				$(this).parents(".checkbox").removeClass("check");
 				$(':checkbox').parents(".panel-heading").removeClass("check");
// 				for(var i=1; i<=2; i++){
// 					$('#panel'+i).removeClass();
//			        $('#panel'+i).addClass("panel-collapse collapse in");
// 				}
 				affixBottom('hide');
 			}
 		});
        if ($('#checkall').is(':checked')) {
            $(':checkbox').prop('checked', true);
            $("#dvCheckAll").addClass("check");
//            for(var i=1; i<=2; i++){
//                $('#panel'+i).removeClass();
//                $('#panel'+i).addClass("panel-collapse collapse");
//            }
            affixBottom('show');
        } else {
            $(':checkbox').prop('checked', false);
            $(this).parents(".checkbox").removeClass("check");
            $(':checkbox').parents(".panel-heading").removeClass("check");
//            for(var i=1; i<=2; i++){
//                $('#panel'+i).removeClass();
//                $('#panel'+i).addClass("panel-collapse collapse in");
//           }
            affixBottom('hide');
        }

        $(':checkbox').change(function() {
        	//약관 전체동의 체크
	    	 if($('#checkbox1').is(":checked") && $('#checkbox2').is(":checked") ){
	    		$('#checkall').prop('checked', true);
	    		$('#checkall').parents(".checkbox").addClass("check");
	    	 } else {
	    		$('#checkall').prop('checked', false);
		    	$('#checkall').parents(".checkbox").removeClass("check");
	    	 }
	    	 
        	$(this).parents(".panel-heading").toggleClass("check");
            //다음버튼 show
            if($('#checkbox1').is(":checked") && $('#checkbox2').is(":checked")){
                affixBottom('show');
            } else {
//		    		 for(var i=1; i<=2; i++){
//						$('#panel'+i).removeClass();
//				        $('#panel'+i).addClass("panel-collapse collapse in");
//				}
                affixBottom('hide');
            }
	    });
	});
	
	//본인인증관련 약관 1
	function popAcceptTerms(gubun) {
		
		if(gubun == 'hp1') {
			frmTermsStep.action = "<c:url value='/m/credit/frameAcceptTerms1.crz'/>";
			frmTermsStep.submit();
		} else if(gubun == 'hp6') {
			frmTermsStep.action = "<c:url value='/m/credit/frameAcceptTerms6.crz'/>";
			frmTermsStep.submit();
		} else if(gubun == 'hp7') {
			frmTermsStep.action = "<c:url value='/m/credit/frameAcceptTerms7.crz'/>";
			frmTermsStep.submit();
		} else if(gubun == 'hp8') {
			frmTermsStep.action = "<c:url value='/m/credit/frameAcceptTerms8.crz'/>";
			frmTermsStep.submit();
		} else if(gubun == 'hp9') {
			frmTermsStep.action = "<c:url value='/m/credit/frameAcceptTerms9.crz'/>";
			frmTermsStep.submit();
		}
		
	}
	
	function confirmedTerms() {
		var chk = chkAcceptTerm();
		if(chk == false){ return false;}
		frmTermsStep.submit();
	}
	
	function chkAcceptTerm(){
		if($("input:checkbox[name='checkbox1']").is(":checked") == false || $("input:checkbox[name='checkbox2']").is(":checked") == false || $("input:checkbox[name='checkbox2']").is(":checked") == false) {
			toastMsg('필수 약관을 모두 동의해주세요.');
			return false;
		}
	}
    <c:if test="${site eq 'LOCAL'}">
    /**
     * 로컬 개발용 함수
     */
    function testOnload() {
        $("#checkall").click();
    }
    </c:if>

</script>

</head>
<body class="bg_white">
<form name="frmTermsStep" id="frmTermsStep" action="<c:url value='/m/loanworker/frameLoanWorkerStep6.crz'/>" method="post">
<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>약관동의</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="check-all">
				<div class="checkbox" id="dvCheckAll"><label for="checkall"><input type="checkbox" id="checkall"> 전체 약관 동의</label></div>
			</div>
		</div>
		<!-- panel -->
		<div class="panel-group agree-panel" id="termsList">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="checkbox"> <label><input type="checkbox" name="checkbox1" id="checkbox1" validate="checkbox-required:checkbox1;label:개인(신용)정보 동의;" />
						<a href="" class="collapsed" role="button" data-toggle="collapse"><em>[필수]</em> 개인(신용)정보 동의</a></label></div>
				</div>
				<div class="panel-collapse collapse in" id="panel1">
					<div class="list-group">
						<a class="list-group-item" onclick="popAcceptTerms('hp1');">
							<label>개인(신용)정보 수집·이용 동의</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
						<a class="list-group-item" onclick="popAcceptTerms('hp2');">
							<label>고유식별정보 수집·이용 동의</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
						<a class="list-group-item" onclick="popAcceptTerms('hp3');">
							<label>개인(신용)정보 조회 동의</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
						<a class="list-group-item" onclick="popAcceptTerms('hp4');">
							<label>고유식별정보 조회동의</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
						<a class="list-group-item" onclick="popAcceptTerms('hp5');">
							<label>개인(신용)정보 제공 동의</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
						<a class="list-group-item" onclick="popAcceptTerms('hp6');">
							<label>고유식별정보 제공 동의</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="checkbox"> <label><input type="checkbox" name="checkbox2" id="checkbox2" validate="checkbox-required:checkbox2;label:휴대전화 본인인증 동의;"/><a href="" class="collapsed" role="button" data-toggle="collapse"><em>[필수]</em> 휴대전화 본인인증 동의</a></label></div>
				</div>
				<div class="panel-collapse collapse in" id="panel2">
					<div class="list-group">
						<a role="button" class="list-group-item" onclick="popAcceptTerms('hp6');">
							<label>본인확인서비스 이용약관</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
						<a role="button" class="list-group-item" onclick="popAcceptTerms('hp7');">
							<label>개인정보 수정·이용/취급위탁 동의</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
						<a role="button" class="list-group-item" onclick="popAcceptTerms('hp8');">
							<label>고유식별정보처리 동의</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
						<a role="button" class="list-group-item" onclick="popAcceptTerms('hp9');">
							<label>통신사 본인확인 이용약관</label>
							<button type="button" class="btn-terms">약관보기</button>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- //panel -->
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" onclick="confirmedTerms()" id="confirmButton" class="btn btn-lg btn-primary btn-block">다음</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</form>
</body>
</html>
