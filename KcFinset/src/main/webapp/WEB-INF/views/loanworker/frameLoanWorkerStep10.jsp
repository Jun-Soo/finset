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
	//var pageCnt = 1;
	$(document).ready(function() {
		$('#nmCompInput').hide();
		$(window).scroll(function(){
			if($(document).height() <= $(window).scrollTop() + $(window).height()){
				var totalPage = $('#totalPage').val();
		 		var currPage = $('#currPage').val();
				if(totalPage > 1){
					if(totalPage != currPage){
						  jumpPage();
					}
				}
			  }
			});
	
		$("#page").val(1);
        $("#txt_detail").focus();
	});
	
	function showAdd(totalPage, currPage){
		
		$('#totalPage').val(totalPage);
		$('#currPage').val(currPage);
	}
	
	//리스트 조회
	function goSearch() {
		jumpPage();
	}
	
	// 페이지 이동
	function jumpPage(){
		var pageIndex = Number($("#page").val());
		$("#page").val(pageIndex + 1);
		var data = {"page":$("#page").val(), "txt_detail":$('#txt_detail_confirm').val()};
		
		if(data == null){
			return false;
		}
		if(userAgent == "Android") {
			window.Android.loding('Y');
		}
		else if(userAgent == "iOS") {
			Jockey.send("showLoading");
		}
		$.ajax({
			url : "<c:url value='/m/loanworker/listJobResult.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listJob").append(result);
				if(userAgent == "Android") {
					window.Android.loding('N');
				}
				else if(userAgent == "iOS") {
					Jockey.send("stopLoading");
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	function searchJob(){
		if($("#txt_detail").val() == '' || $("#txt_detail").val() == null) {
			toastMsg('직장명을 입력해주세요.');
			$("#txt_detail").focus();
			return false;
		}
		$("#txt_detail_confirm").val($("#txt_detail").val());
		if(userAgent == "Android") {
			window.Android.loding('Y');
		}
		else if(userAgent == "iOS") {
			Jockey.send("showLoading");
		}
		$.ajax({
			url : "<c:url value='/m/loanworker/listJobResult.crz'/>",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : {"txt_detail":$('#txt_detail').val()},
			type : "POST",
			success : function (result) {
				var expr = new RegExp('>[ \t\r\n\v\f]*<', 'g');
				result = result.replace(expr, '> <');
				$("#listJob").html(result);
				$('#nmCompInput').show();
				if(userAgent == "Android") {
					window.Android.loding('N');
				}
				else if(userAgent == "iOS") {
					Jockey.send("stopLoading");
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
		
	}
    var nm_comp     ='';
    var no_biz_comp ='';
    var kiscode     ='';

    function jobConfirm(kiscode, p_no_biz_comp, p_nm_comp){
        $('#kiscode').val(kiscode);
        $('#business').val(p_no_biz_comp);
        $('#nm_comp').val(p_nm_comp);
        nm_comp     = p_nm_comp;
        no_biz_comp = p_no_biz_comp;
        kiscode = kiscode;

		affixBottom('show');
	}
	
	function loanApplyStepStep() {
        setCookie('nm_comp'    ,nm_comp    );
        setCookie('no_biz_comp',no_biz_comp);
        setCookie('kiscode'    ,kiscode);
		<%--frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep9.crz'/>";--%>
		// frmloanApplyStep.submit();
		history.back();
	}

    /**
	 * 입력한 값을 쿠키에 넣고 history.back을 한다.
     */
	function goBack(){

	}
	
	function procKisCompanyOutline() {
		if ( !frmloanApplyStep.validateForm() ) return false;
		if($("#kiscode").val() == '' || $("#kiscode").val() == null) {
			toastMsg('직장을 선택해주세요.');
			return false;
		}
		var data = $("#frmloanApplyStep").serialize();
		if(data == null) return false;
        if(userAgent == "Android") {
            window.Android.loding('Y');
        }
        else if(userAgent == "iOS") {
            Jockey.send("showLoading");
        }

		$.ajax({
			url : "<c:url value='/m/loanworker/procKisCompanyOutline.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : true,
			success : function (result) {
                if(userAgent == "Android") {
                    window.Android.loding('N');
                }
                else if(userAgent == "iOS") {
                    Jockey.send("stopLoading");
                }
				if(result.result == '00') {
					loanApplyStepStep();
				} else {
					toastMsg('다시 시도해주세요.');
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	function nmCompInput(){
		frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep11.crz'/>";
		frmloanApplyStep.submit();
	}

</script>

</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;" onsubmit="return false;">
	<input type="hidden" name="kiscode" id="kiscode" >
	<input type="hidden" name="business" id="business" >
	<input type="hidden" name="nm_comp" id="nm_comp" >
	<input type="hidden" name="txt_detail_confirm" id="txt_detail_confirm" >
	
	<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
	<input type="hidden" name="cd_occupational" id="cd_occupational" value="${txFcTransmitVO.cd_occupational}"/>
	<input type="hidden" name="amt_year_income" id="amt_year_income" value="${txFcTransmitVO.amt_year_income}"/>
	<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
	<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
	
	<input type="hidden" name="page" id="page"/>
	<input type="hidden" name="totalPage" id="totalPage"/>
	<input type="hidden" name="currPage" id="currPage"/>
	<input type="hidden" name="kcb_di" id="kcb_di" value="${txFcTransmitVO.kcb_di }"/>
	
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>직장명 검색</h1>
		</div>
	</header>
	<div class="search-block affix-fixed">
		<div class="form-group has-feedback">	
			<label for="" class="sr-only">직장명 검색</label>			
			<input type="text" class="form-control" name="txt_detail" id="txt_detail" onkeypress="if(event.key=='Enter'){searchJob();}" placeholder="직장명" autocomplete="off">
			<button type="button" class="sch-btn" onclick="searchJob();"><span class="form-control-feedback btn-search">검색</span></button>
		</div>
	</div>
	</form>
	<!-- Content -->
	<section id="content">
		<div id="listJob">
			<%@ include file="/WEB-INF/views/loanworker/sub/listJobFind.jsp"%>
		</div>
		<div class="help-block" id="nmCompInput">
			<p>직장명이 없을경우 직접입력 가능합니다.</p>
			<a role="button" class="btn btn-block btn-outline" onclick="nmCompInput();">직장명 직접입력</a>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="procKisCompanyOutline();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
