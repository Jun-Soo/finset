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
	$(document).ready(function() {
		affixBottom('show');
	});
	function procConditionhouseInfo() {
		var frm = document.frmConditionhouseInfo;
		if(frm.no_person.value == ""){
			alert("no_person를 선택해해주세요");
			frm.no_person.focus();
			return;
		}
		if(!frm.validateForm()) return false;
		var data = frm.ajaxSubmit();
		if(data == null) return false;
		$.ajax({
			url : "<c:url value='/m/loanhomemortgage/procConditionhouseInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				//alert(returnData.message);
				toastMsg('적용되었습니다.');
                goGoodsList();
			},
			error : function (e) {

			}
		});
	}
	
	function setButton(objId){
		var objName = $(objId).attr('name');
		var hiddenVal = $('input[name='+ objName +']').val();

		if( hiddenVal == "Y" ){
			$(objId).removeClass('active');
			$('input[name='+ objName +']').val("N");
		}
		else{
			$(objId).addClass('active');
			$('input[name='+ objName +']').val("Y");
		}
	}
	var activeIsInit = true;
	function initButton(){
		
		$('#house_type > button'	).each(function(){
		    if(activeIsInit == true) {
                $(this).addClass('active');
			} else {
                $(this).removeClass('active');
			}
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});
		$('#loan_use > button'		).each(function(){
            if(activeIsInit == true) {
                $(this).addClass('active');
            } else {
                $(this).removeClass('active');
            }
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});
		$('#cd_area > button'		).each(function(){
            if(activeIsInit == true) {
                $(this).addClass('active');
            } else {
                $(this).removeClass('active');
            }
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});
		$('#ratio_type > button'	).each(function(){
            if(activeIsInit == true) {
                $(this).addClass('active');
            } else {
                $(this).removeClass('active');
            }
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});
		$('#term_loan > button'	).each(function(){
            if(activeIsInit == true) {
                $(this).addClass('active');
            } else {
                $(this).removeClass('active');
            }
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});
		$('#type_pay > button'		).each(function(){
            if(activeIsInit == true) {
                $(this).addClass('active');
            } else {
                $(this).removeClass('active');
            }
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});
		$('#cd_defer > button'		).each(function(){
            if(activeIsInit == true) {
                $(this).addClass('active');
            } else {
                $(this).removeClass('active');
            }
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});
	}
	
	function goGoodsList() {
        history.back();
	}
</script>

</head>
<body>
<div id="wrapper" class="pop-full">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>조건선택</h1>
			<button class="btn-header-close" onclick="goGoodsList();">
				<span class="ico-close">닫기</span>
			</button>
		</div>
	</header>
<form name="frmConditionhouseInfo" method="post">
	<!-- Content -->
	<section id="content">
		<input type="hidden" class="form-control" name="no_person" value="${no_person}"/>
		<input type="hidden" class="form-control" name="cd_collateral_house_type_01" value="${conditionhouseInfo.cd_collateral_house_type_01}"/>
		<input type="hidden" class="form-control" name="cd_collateral_house_type_02" value="${conditionhouseInfo.cd_collateral_house_type_02}"/>
		<input type="hidden" class="form-control" name="cd_collateral_house_type_03" value="${conditionhouseInfo.cd_collateral_house_type_03}"/>
		<input type="hidden" class="form-control" name="cd_collateral_loan_use_01" value="${conditionhouseInfo.cd_collateral_loan_use_01}"/>
		<input type="hidden" class="form-control" name="cd_collateral_loan_use_02" value="${conditionhouseInfo.cd_collateral_loan_use_02}"/>
		<input type="hidden" class="form-control" name="cd_collateral_loan_use_03" value="${conditionhouseInfo.cd_collateral_loan_use_03}"/>
		<input type="hidden" class="form-control" name="cd_area_01" value="${conditionhouseInfo.cd_area_01}"/>
		<input type="hidden" class="form-control" name="cd_area_02" value="${conditionhouseInfo.cd_area_02}"/>
		<input type="hidden" class="form-control" name="cd_area_03" value="${conditionhouseInfo.cd_area_03}"/>
		<input type="hidden" class="form-control" name="cd_ratio_type_01" value="${conditionhouseInfo.cd_ratio_type_01}"/>
		<input type="hidden" class="form-control" name="cd_ratio_type_02" value="${conditionhouseInfo.cd_ratio_type_02}"/>
		<input type="hidden" class="form-control" name="cd_ratio_type_03" value="${conditionhouseInfo.cd_ratio_type_03}"/>
		<input type="hidden" class="form-control" name="cd_term_loan_01" value="${conditionhouseInfo.cd_term_loan_01}"/>
		<input type="hidden" class="form-control" name="cd_term_loan_02" value="${conditionhouseInfo.cd_term_loan_02}"/>
		<input type="hidden" class="form-control" name="cd_term_loan_03" value="${conditionhouseInfo.cd_term_loan_03}"/>
		<input type="hidden" class="form-control" name="cd_term_loan_04" value="${conditionhouseInfo.cd_term_loan_04}"/>
		<input type="hidden" class="form-control" name="cd_type_pay_01" value="${conditionhouseInfo.cd_type_pay_01}"/>
		<input type="hidden" class="form-control" name="cd_type_pay_02" value="${conditionhouseInfo.cd_type_pay_02}"/>
		<input type="hidden" class="form-control" name="cd_type_pay_03" value="${conditionhouseInfo.cd_type_pay_03}"/>
		<input type="hidden" class="form-control" name="cd_defer_01" value="${conditionhouseInfo.cd_defer_01}"/>
		<input type="hidden" class="form-control" name="cd_defer_02" value="${conditionhouseInfo.cd_defer_02}"/>
		<input type="hidden" name="cd_goods_class_l" id="cd_goods_class_l">
		<input type="hidden" name="cd_goods_class_m" id="cd_goods_class_m">

		<div class="container">
			<h2 class="h2">주택종류</h2>
			<div id="house_type" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_collateral_house_type_01" name="cd_collateral_house_type_01"   class="btn btn-check ${ conditionhouseInfo.cd_collateral_house_type_01 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">아파트</button><!--주택종류	아파트-->
				<button type="button" id="div_cd_collateral_house_type_02" name="cd_collateral_house_type_02"   class="btn btn-check ${ conditionhouseInfo.cd_collateral_house_type_02 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">오피스텔</button>      <!--주택종류 오피스텔-->
				<button type="button" id="div_cd_collateral_house_type_03" name="cd_collateral_house_type_03"   class="btn btn-check ${ conditionhouseInfo.cd_collateral_house_type_03 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">주택/연립/빌라</button><!--주택종류 주택/연립/빌라-->
			</div>
		</div>
		<div class="container">
			<h2 class="h2">자금용도</h2>
			<div id="loan_use" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_collateral_loan_use_01" name="cd_collateral_loan_use_01"   class="btn btn-check ${ conditionhouseInfo.cd_collateral_loan_use_01 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">구입자금</button>  <!--자금용도	신규주택구입-->
				<button type="button" id="div_cd_collateral_loan_use_02" name="cd_collateral_loan_use_02"   class="btn btn-check ${ conditionhouseInfo.cd_collateral_loan_use_02 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">기대출상환</button><!--자금용도 기대출상환-->
				<button type="button" id="div_cd_collateral_loan_use_03" name="cd_collateral_loan_use_03"   class="btn btn-check ${ conditionhouseInfo.cd_collateral_loan_use_03 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">자금조달</button>  <!--자금용도 자금조달-->
			</div>
		</div>
		<div class="container">
			<h2 class="h2">주택면적(전용면적)</h2>
			<div id="cd_area" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_area_01" name="cd_area_01"   class="btn btn-check ${ conditionhouseInfo.cd_area_01 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">~85m2</button>    <!--주택면적(전용면적)	85m2 미만-->
				<button type="button" id="div_cd_area_02" name="cd_area_02"   class="btn btn-check ${ conditionhouseInfo.cd_area_02 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">85~125m2</button> <!--주택면적(전용면적) 85m2~125m2-->
				<button type="button" id="div_cd_area_03" name="cd_area_03"   class="btn btn-check ${ conditionhouseInfo.cd_area_03 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">125m2 ~</button>  <!--주택면적(전용면적) 125m2초과-->
			</div>
		</div>
		<div class="container">
			<h2 class="h2">금리방식</h2>
			<div id="ratio_type" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_ratio_type_01" name="cd_ratio_type_01"   class="btn btn-check ${ conditionhouseInfo.cd_ratio_type_01 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">고정</button><!--금리방식	고정금리-->
				<button type="button" id="div_cd_ratio_type_02" name="cd_ratio_type_02"   class="btn btn-check ${ conditionhouseInfo.cd_ratio_type_02 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">혼합</button><!--금리방식 혼합금리-->
				<button type="button" id="div_cd_ratio_type_03" name="cd_ratio_type_03"   class="btn btn-check ${ conditionhouseInfo.cd_ratio_type_03 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">변동</button><!--금리방식 변동금리-->
			</div>
		</div>
		<div class="container">
			<h2 class="h2">대출기간</h2>
			<div id="term_loan" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_term_loan_01" name="cd_term_loan_01"   class="btn btn-check ${ conditionhouseInfo.cd_term_loan_01 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">~1년</button>   <!--대출기간	~1년-->
				<button type="button" id="div_cd_term_loan_02" name="cd_term_loan_02"   class="btn btn-check ${ conditionhouseInfo.cd_term_loan_02 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">2~15년</button> <!--대출기간 2~10년-->
				<button type="button" id="div_cd_term_loan_03" name="cd_term_loan_03"   class="btn btn-check ${ conditionhouseInfo.cd_term_loan_03 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">16~30년</button><!--대출기간 10~30년-->
				<button type="button" id="div_cd_term_loan_04" name="cd_term_loan_04"   class="btn btn-check ${ conditionhouseInfo.cd_term_loan_04 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">31년~</button>  <!--대출기간 31년~-->
			</div>
		</div>
		<div class="container">
			<h2 class="h2">상환방식</h2>
			<div id="type_pay" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_type_pay_01" name="cd_type_pay_01"   class="btn btn-check ${ conditionhouseInfo.cd_type_pay_01 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">원리금분할상환</button><!--상환방식	원리금분할상환-->
				<button type="button" id="div_cd_type_pay_02" name="cd_type_pay_02"   class="btn btn-check ${ conditionhouseInfo.cd_type_pay_02 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">원금분할상환</button>  <!--상환방식 원금분할상환-->
				<button type="button" id="div_cd_type_pay_03" name="cd_type_pay_03"   class="btn btn-check ${ conditionhouseInfo.cd_type_pay_03 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">만기일시상환</button>  <!--상환방식 만기일시상환-->
			</div>
		</div>
		<div class="container">
			<h2 class="h2">거치방식</h2>
			<div id="cd_defer" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_defer_01" name="cd_defer_01"   class="btn btn-check ${ conditionhouseInfo.cd_defer_01 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">거치형</button>  <!--거치방식	거치형-->
				<button type="button" id="div_cd_defer_02" name="cd_defer_02"   class="btn btn-check ${ conditionhouseInfo.cd_defer_02 == 'Y' ? 'active' : ''}" role="button" onclick="setButton(this);">비거치형</button><!--거치방식 비거치형-->
			</div>
		</div>
 		<div class="btn-fixed-bottom">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="initButton();">초기화</button>
			</div>
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="procConditionhouseInfo();">적용</button>
			</div>
		</div>
</form>	
	</section>
	<!-- //Content -->
</div>
</body>
</html>
