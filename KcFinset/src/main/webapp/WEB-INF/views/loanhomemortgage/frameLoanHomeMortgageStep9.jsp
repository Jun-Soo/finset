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
<link rel="stylesheet" href="<c:url value="/style/autocomplete.css"/>"/>
<script type="text/javascript">
//검색어 자동완성기능
var isStart = false;
$(function(){
	ChangeSelect(document.frmloanApplyStep);
    $("#BUILDING_TYPE").change(function(){
        ChangeSelectBox(document.frmloanApplyStep, this);
        chkValidate();
        toggleSelect("REGION1_CODE");
    });
    $("#REGION1_CODE").change(function(){
        ChangeSelectBox(document.frmloanApplyStep, this);
        chkValidate();
        toggleSelect("REGION2_CODE");
    });
    $("#REGION2_CODE").change(function(){
        ChangeSelectBox(document.frmloanApplyStep, this);
        chkValidate();
        toggleSelect("REGION3_CODE");
    });
    $("#REGION3_CODE").change(function(){
        getAptList();
        chkValidate();
    });
    $("#apartment_nm").change(function(){
        goKbMarketPriceList();
        chkValidate();
    });
    $("#FLOOR_PLAN_AREA").change(function(){
        toggleSelect("FLOOR_HIGHEST");
    });
    /**
     * 다음화면 쿠키 초기화
     */
    setCookie('CD_JOB_CLASS_L' ,"");
    setCookie('AMT_YEAR_INCOME',"");
    setCookie('AMT_YEAR_SALE'  ,"");

    /**
     * 쿠키에 등록되어있는 값 화면에 셋팅
     */
    var BUILDING_TYPE   = getCookie('BUILDING_TYPE'   );
    var REGION2_CODE    = getCookie('REGION2_CODE'    );
    var REGION3_CODE    = getCookie('REGION3_CODE'    );
    var apartment_nm    = getCookie('apartment_nm'    );
    var FLOOR_PLAN_AREA = getCookie('FLOOR_PLAN_AREA' );
    var FLOOR_HIGHEST   = getCookie('FLOOR_HIGHEST'   );
    if(BUILDING_TYPE   != "") {
        $('#BUILDING_TYPE'  ).val(BUILDING_TYPE  );
        isStart = true;
        var obj = $('#BUILDING_TYPE'  );
        ChangeSelectBox(document.frmloanApplyStep, obj);
        chkValidate();
    } else {
        isStart = false;
        toggleSelect("BUILDING_TYPE");
    }
});
var aptList = new Object();

/**
 * 아파트목록 가져오기
 */
function getAptList() {
    var frm = document.frmloanApplyStep;
    if(userAgent == "Android") {
        window.Android.loding('Y');
    }
    else if(userAgent == "iOS") {
        Jockey.send("showLoading");
    }
    frm.FLOOR_PLAN_AREA.options.length=0;
    $('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
    $('#FLOOR_PLAN_AREA').selectpicker('refresh');

    frm.FLOOR_HIGHEST.options.length=0;
    $('#FLOOR_HIGHEST').append("<option value=''>층수</option>");
    $('#FLOOR_HIGHEST').selectpicker('refresh');
    $('#apartment_nm').val('');
    aptList = new Object();
    $.ajax({
        type: 'post',
        url: '<c:url value="/kbrealestate/listSrchApartmentInfo.json"/>',
        dataType: "json",
        async : true,
        data: {BUILDING_TYPE : frmloanApplyStep.BUILDING_TYPE.value
            , APARTMENT : frmloanApplyStep.apartment_nm.value
            , REGION1_CODE : frmloanApplyStep.REGION1_CODE.value
            , REGION2_CODE : frmloanApplyStep.REGION2_CODE.value
            , REGION3_CODE : frmloanApplyStep.REGION3_CODE.value },
        success: function(data) {
            if(userAgent == "Android") {
                window.Android.loding('N');
            }
            else if(userAgent == "iOS") {
                Jockey.send("stopLoading");
            }
            //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
            var frm = document.frmloanApplyStep;
            frm.apartment_nm.options.length=1;
            var list = data.data;
            var len = list.length;
            console.log("len="+len);
            if(len < 1) {
                toastMsg("해당지역에는 아파트가 없습니다.");
                $('#apartment_nm').selectpicker('refresh');
                $('#apartment_nm').prop("disabled",true);
            } else {
                var objOne;
                var APARTMENT;
                var APARTMENT_NAME;
                var REGION3_CODE;
                var aptObj;
                $('#apartment_nm').prop("disabled",false);
                for(var i=0;i<len;i++) {
                    objOne = list[i];
                    APARTMENT      = objOne.APARTMENT     ;
                    APARTMENT_NAME = objOne.APARTMENT_NAME;
                    REGION3_CODE   = objOne.REGION3_CODE  ;
                    aptList[APARTMENT]=objOne;
                    $('#apartment_nm').append("<option value='"+APARTMENT+"'>"+APARTMENT_NAME+"</option>");
                }
                $('#apartment_nm').selectpicker('refresh');
                if(isStart == true) {
                    var apartment_nm    = getCookie('apartment_nm'    );
                    if(apartment_nm != ""){
                        $('#apartment_nm'  ).val(apartment_nm  );
                        $('#apartment_nm').selectpicker('refresh');
                        goKbMarketPriceList();
                        chkValidate();
                        isStart = true;
                    } else {
                        toggleSelect("apartment_nm");
                        isStart = false;
                    }
                }
                if(isStart == false) {
                    toggleSelect("apartment_nm");
                }
            }
        },
        error : function (e) {
            errMsg(e);
        }
    });
}
function ChangeSelectBox(frm, obj){
	var url;
	var data;
	
	var objId = $(obj).attr('id');
	var objVal = $("#"+objId).val();
	var target;
	if(objId == "BUILDING_TYPE"){ //	빌딩 종류
		target= frm.REGION1_CODE;
		url = "<c:url value='/kbrealestate/listAddrRegion1.json'/>";
		data = {"BUILDING_TYPE":1};
		frm.REGION1_CODE.options.length=0;
		$('#REGION1_CODE').append("<option value=''>시/도</option>");
		$('#REGION1_CODE').selectpicker('refresh');
		
		frm.REGION2_CODE.options.length=0;
		$('#REGION2_CODE').append("<option value=''>시/군/구</option>");
		$('#REGION2_CODE').selectpicker('refresh');
		
		frm.REGION3_CODE.options.length=0;
		$('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
		$('#REGION3_CODE').selectpicker('refresh');
		
		frm.FLOOR_PLAN_AREA.options.length=0;
		$('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
		$('#FLOOR_PLAN_AREA').selectpicker('refresh');
		
		frm.FLOOR_HIGHEST.options.length=0;
		$('#FLOOR_HIGHEST').append("<option value=''>층수</option>");
		$('#FLOOR_HIGHEST').selectpicker('refresh');
		$('#apartment_nm').val('');
	}
		else if(objId == "REGION1_CODE")
	{
		target= frm.REGION2_CODE;
		url = "<c:url value='/kbrealestate/listAddrRegion2.json'/>";
		data = {"REGION1_CODE":objVal};
		frm.REGION2_CODE.options.length=0
		$('#REGION2_CODE').append("<option value=''>시/군/구</option>");
		$('#REGION2_CODE').selectpicker('refresh');
		
		frm.REGION3_CODE.options.length=0
		$('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
		$('#REGION3_CODE').selectpicker('refresh');
		
		$('#apartment_nm').val('');
		
		frm.FLOOR_PLAN_AREA.options.length=0
		$('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
		$('#FLOOR_PLAN_AREA').selectpicker('refresh');
		
		frm.FLOOR_HIGHEST.options.length=0
		$('#FLOOR_HIGHEST').append("<option value=''>층수</option>");
		$('#FLOOR_HIGHEST').selectpicker('refresh');
	}
		else if(objId == "REGION2_CODE")
	{
		target= frm.REGION3_CODE;
		url = "<c:url value='/kbrealestate/listAddrRegion3.json'/>";
		data = {"REGION2_CODE":objVal};
		
		frm.REGION3_CODE.options.length=0
		$('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
		$('#REGION3_CODE').selectpicker('refresh');
		
		$('#apartment_nm').val('');
		
		frm.FLOOR_PLAN_AREA.options.length=0
		$('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
		$('#FLOOR_PLAN_AREA').selectpicker('refresh');
		
		frm.FLOOR_HIGHEST.options.length=0
		$('#FLOOR_HIGHEST').append("<option value=''>층수</option>");
		$('#FLOOR_HIGHEST').selectpicker('refresh');
	}
	
	if(data == null) return false;

	 var boxOption; 
	 target.options.length=0;
	$.ajax({
		url : url,
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		dataType : "json",
		success : function(data) {
		
			if(objId == "BUILDING_TYPE"){ //	빌딩 종류
				boxOption = document.createElement("OPTION");
		        boxOption.value = '';     
		        boxOption.text = '시/도';
		        target.options.add(boxOption);
				$.each(data.List, function (index, item) {
			        boxOption = document.createElement("OPTION");
			        boxOption.value = item.code_value;     
			        boxOption.text = item.nm_code;
			        target.options.add(boxOption);
				});	
		        $('#REGION1_CODE').selectpicker('refresh');
				target.options.length = data.List.length + 1;
				if(isStart == true) {
                    var REGION1_CODE    = getCookie('REGION1_CODE'    );
                    if(REGION1_CODE != '') {
						if(REGION1_CODE    != "") {$('#REGION1_CODE'   ).val(REGION1_CODE   );}
						$('#REGION1_CODE').selectpicker('refresh');
						var obj = $('#REGION1_CODE'  );
						ChangeSelectBox(document.frmloanApplyStep, obj);
						chkValidate();
					} else {
                        isStart = false;
					}
                }
			}
				else if(objId == "REGION1_CODE")// 시/도
			{
				boxOption = document.createElement("OPTION");
		        boxOption.value = '';     
		        boxOption.text = '시/군/구';
		        target.options.add(boxOption);
				$.each(data.List, function (index, item) {
			        boxOption = document.createElement("OPTION");
			        boxOption.value = item.code_value;     
			        boxOption.text = item.nm_code;
			        target.options.add(boxOption);
				});	
		        $('#REGION2_CODE').selectpicker('refresh');
				target.options.length = data.List.length + 1;
                if(isStart == true) {
                    var REGION2_CODE    = getCookie('REGION2_CODE'    );
                    if(REGION2_CODE    != "") {
                        $('#REGION2_CODE'   ).val(REGION2_CODE   );
                        $('#REGION2_CODE').selectpicker('refresh');
                        var obj = $('#REGION2_CODE'  );
                        ChangeSelectBox(document.frmloanApplyStep,obj);
                        chkValidate();
                    } else {
                        isStart = false;
                    }
                }
            }
				else if(objId == "REGION2_CODE")// 시/군/구
			{
                boxOption = document.createElement("OPTION");
                boxOption.value = '';
                boxOption.text = '읍/면/동';
                target.options.add(boxOption);
                $.each(data.List, function (index, item) {
                    boxOption = document.createElement("OPTION");
                    boxOption.value = item.code_value;
                    boxOption.text = item.nm_code;
                    target.options.add(boxOption);
                });
                $('#REGION3_CODE').selectpicker('refresh');
                target.options.length = data.List.length + 1;
                if(isStart == true) {
                    var REGION3_CODE    = getCookie('REGION3_CODE'    );
                    if(REGION3_CODE    != "") {
                        $('#REGION3_CODE'   ).val(REGION3_CODE   );
                        $('#REGION3_CODE').selectpicker('refresh');
                        getAptList();
                        chkValidate();
                    } else {
                        isStart = false;
                    }
                }
			}
		}
	});
}

function ChangeSelectBox2(frm, obj){
    getAptList();
}

function ChangeSelect(frm){
	var url;
	var data;
	
	var target;

    target= frm.REGION1_CODE;
    url = "<c:url value='/kbrealestate/listAddrRegion1.json'/>";
    data = {"BUILDING_TYPE":1};
    frm.REGION1_CODE.options.length=0;
    $('#REGION1_CODE').append("<option value=''>시/도</option>");
    $('#REGION1_CODE').selectpicker('refresh');

    frm.REGION2_CODE.options.length=0
    $('#REGION2_CODE').append("<option value=''>시/군/구</option>");
    $('#REGION2_CODE').selectpicker('refresh');

    frm.REGION3_CODE.options.length=0
    $('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
    $('#REGION3_CODE').selectpicker('refresh');

    frm.FLOOR_PLAN_AREA.options.length=0
    $('#FLOOR_PLAN_AREA').append("<option value=''>공급면적</option>");
    $('#FLOOR_PLAN_AREA').selectpicker('refresh');

    frm.FLOOR_HIGHEST.options.length=0
    $('#FLOOR_HIGHEST').append("<option value=''>층수</option>");
    $('#FLOOR_HIGHEST').selectpicker('refresh');
    $('#APARTMENT').val('');

	
	if(data == null) return false;

	 var boxOption; 
	 target.options.length=0;
	$.ajax({
		url : url,
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		dataType : "json",
		success : function(data) {
		
				boxOption = document.createElement("OPTION");
		        boxOption.value = '';     
		        boxOption.text = '시/도';
		        target.options.add(boxOption);
				$.each(data.List, function (index, item) {
			        boxOption = document.createElement("OPTION");
			        boxOption.value = item.code_value;     
			        boxOption.text = item.nm_code;
			        target.options.add(boxOption);
				});	
		        $('#REGION1_CODE').selectpicker('refresh');
				target.options.length = data.List.length + 1;				
		}
			
	});
}
/**
 * 읍면동 코드 가져오는 로직
 * @param APARTMENT
 */
function getREGION3_CODE(APARTMENT){
    var obj = aptList[APARTMENT];
    var REGION3_CODE = (obj != undefined) ? aptList[APARTMENT].REGION3_CODE: "";
    console.log("getREGION3_CODE:"+REGION3_CODE);
    return REGION3_CODE;
}

function goKbMarketPriceList() {
// 	if(frmloanApplyStep.API_ID.value != "9921"){
// 		alert("API_ID를 9921:시세검색으로 선택해 주세요");
// 		return;
// 	}
	var target;
	var target2;
    var APARTMENT = $("#apartment_nm").val();
    var REGION3_CODE = getREGION3_CODE(APARTMENT);
    console.log('APARTMENT='+APARTMENT);
    $('#REGION3_CODE').val(REGION3_CODE);
    $('#APARTMENT').val('');
    $("#APARTMENT").val(APARTMENT);
	target= document.frmloanApplyStep.FLOOR_PLAN_AREA;
	target2= document.frmloanApplyStep.FLOOR_HIGHEST;
	document.frmloanApplyStep.FLOOR_PLAN_AREA.options.length=0;
	document.frmloanApplyStep.FLOOR_HIGHEST.options.length=0;
	$('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
	$('#FLOOR_PLAN_AREA').selectpicker('refresh');
	$('#FLOOR_HIGHEST').append("<option value=''>층수</option>");
	$('#FLOOR_HIGHEST').selectpicker('refresh');
	if(!frmloanApplyStep.validateForm()) return;
	// var data = frmloanApplyStep.ajaxSubmit();
	var data = $("#frmloanApplyStep").serialize();
	if(data == null) return false;
	
	var boxOption; 
	var boxOption2; 
	target.options.length=0;
	target2.options.length=0;
	if(userAgent == "Android") {
		window.Android.loding('Y');
	}
	else if(userAgent == "iOS") {
		Jockey.send("showLoading");
	}
	$.ajax({
		url : "<c:url value='/kbrealestate/scrapKbMarketPriceList.crz'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (data) {
			if(userAgent == "Android") {
				window.Android.loding('N');
			}
			else if(userAgent == "iOS") {
				Jockey.send("stopLoading");
			}
			boxOption = document.createElement("OPTION");
	        boxOption.value = '';     
	        boxOption.text = '공급면적';
	        target.options.add(boxOption);
            if(data.List != undefined) {
                $("#FLOOR_PLAN_AREA").prop("disabled",false);
                $.each(data.List, function (index, item) {
                    boxOption = document.createElement("OPTION");
                    boxOption.value = item.floor_PLAN_AREA_SUPPLY;
                    boxOption.text = item.floor_PLAN_AREA_SUPPLY;
                    target.options.add(boxOption);
                });
                if(isStart == true) {
                    var FLOOR_PLAN_AREA = getCookie('FLOOR_PLAN_AREA' );
                    if(FLOOR_PLAN_AREA != '') {
                        $("#FLOOR_PLAN_AREA").val(FLOOR_PLAN_AREA);
                        $('#FLOOR_PLAN_AREA').selectpicker('refresh');
                    }
                }
            } else {
                $("#FLOOR_PLAN_AREA").prop("disabled",true);
            }
			boxOption2 = document.createElement("OPTION");
	        boxOption2.value = '';     
	        boxOption2.text = '층수';
	        target2.options.add(boxOption2);
            var floor_highest = 0;
	        if(data.KbMarketPriceComplexList != undefined) {
                $("#FLOOR_HIGHEST").prop("disabled",false);
                floor_highest = data.KbMarketPriceComplexList[0].floor_HIGHEST.slice(0, -1);
                for(var i = 1; i <= Number(floor_highest); i++){
                     boxOption2 = document.createElement("OPTION");
                     boxOption2.value = i;
                     boxOption2.text = i+"층";
                     target2.options.add(boxOption2);
                }
                if(isStart == true) {
                    var FLOOR_HIGHEST = getCookie('FLOOR_HIGHEST' );
                    if(FLOOR_HIGHEST != '') {
                        $("#FLOOR_HIGHEST").val(FLOOR_HIGHEST);
                    }
                }
            } else {
                $("#FLOOR_HIGHEST").prop("disabled",true);
            }
	        target.options.add(boxOption);
	        target2.options.add(boxOption2);
	        $('#FLOOR_PLAN_AREA').selectpicker('refresh');
	        $('#FLOOR_HIGHEST').selectpicker('refresh');
			target.options.length = (data.List != undefined)? data.List.length + 1:1;
			target2.options.length = Number(floor_highest) +1;
            if(isStart == true) {
                var FLOOR_PLAN_AREA = getCookie('FLOOR_PLAN_AREA' );
                var FLOOR_HIGHEST = getCookie('FLOOR_HIGHEST' );
                if(FLOOR_PLAN_AREA == '') {
                    toggleSelect("FLOOR_PLAN_AREA");
                } else
                if(FLOOR_PLAN_AREA != '' && FLOOR_HIGHEST == '') {
                    toggleSelect("FLOOR_HIGHEST");
                }
            } else {
                toggleSelect("FLOOR_PLAN_AREA");
            }
            isStart = false
		},
		error : function (e) {
			if(userAgent == "Android") {
				window.Android.loding('N');
			}
			else if(userAgent == "iOS") {
				Jockey.send("stopLoading");
			}
			errMsg(e);
		}
	});
}
function loanApplyStepStep() {
	frmloanApplyStep.action = "<c:url value='/m/loanhomemortgage/frameLoanHomeMortgageStep10.crz'/>";
	frmloanApplyStep.submit();
}

function chkValidate(){
	if($('#BUILDING_TYPE').val() != '' && $('#BUILDING_TYPE').val() != null
		&& $('#REGION1_CODE').val() != '' && $('#REGION1_CODE').val() != null		
		&& $('#REGION2_CODE').val() != '' && $('#REGION2_CODE').val() != null		
		&& $('#REGION3_CODE').val() != '' && $('#REGION3_CODE').val() != null		
		&& $('#apartment_nm').val() != '' && $('#apartment_nm').val() != null		
		&& $('#FLOOR_PLAN_AREA').val() != '' && $('#FLOOR_PLAN_AREA').val() != null		
		&& $('#FLOOR_HIGHEST').val() != '' && $('#FLOOR_HIGHEST').val() != null		
	){
		affixBottom('show');
	} else {
		affixBottom('hide');
	}
	
}

function updateTxFc(){
	if ( !frmloanApplyStep.validateForm() ) return false;

	if($('#BUILDING_TYPE').val() == '' || $('#BUILDING_TYPE').val() == null){
		toastMsg('주택종류를 선택해주세요.');
		return false;
	}
	if($('#REGION1_CODE').val() == '' || $('#REGION1_CODE').val() == null){
		toastMsg('시/도를 선택해주세요.');
		return false;
	}
	if($('#REGION2_CODE').val() == '' || $('#REGION2_CODE').val() == null){
		toastMsg('시/군/구를 선택해주세요.');
		return false;
	}
	if($('#REGION3_CODE').val() == '' || $('#REGION3_CODE').val() == null){
		toastMsg('읍/면/동을 선택해주세요.');
		return false;
	}
	if($('#apartment_nm').val() == '' || $('#apartment_nm').val() == null){
		toastMsg('단지를 입력해주세요.');
		$('#apartment_nm').focus();
		return false;
	}
	if($('#FLOOR_PLAN_AREA').val() == '' || $('#FLOOR_PLAN_AREA').val() == null){
		toastMsg('면적을 선택해주세요.');
		return false;
	}
	if($('#FLOOR_HIGHEST').val() == '' || $('#FLOOR_HIGHEST').val() == null){
		toastMsg('층수를 선택해주세요.');
		return false;
	}
    var BUILDING_TYPE  = $('#BUILDING_TYPE'  ).val();
    var REGION1_CODE   = $('#REGION1_CODE'   ).val();
    var REGION2_CODE   = $('#REGION2_CODE'   ).val();
    var REGION3_CODE   = $('#REGION3_CODE'   ).val();
    var apartment_nm   = $('#apartment_nm'   ).val();
    var FLOOR_PLAN_AREA= $('#FLOOR_PLAN_AREA').val();
    var FLOOR_HIGHEST  = $('#FLOOR_HIGHEST'  ).val();
    setCookie('BUILDING_TYPE'      ,BUILDING_TYPE  );
    setCookie('REGION1_CODE'       ,REGION1_CODE   );
    setCookie('REGION2_CODE'       ,REGION2_CODE   );
    setCookie('REGION3_CODE'       ,REGION3_CODE   );
    setCookie('apartment_nm'       ,apartment_nm   );
    setCookie('FLOOR_PLAN_AREA'    ,FLOOR_PLAN_AREA);
    setCookie('FLOOR_HIGHEST'      ,FLOOR_HIGHEST  );

    $('#cd_house_type').val($('#BUILDING_TYPE').val());
	$('#region_1').val($('#REGION1_CODE').val());
	$('#region_2').val($('#REGION2_CODE').val());
	$('#region_3').val($('#REGION3_CODE').val());
	$('#security_addr').val($('#apartment_nm').val());
	// alert("$('#FLOOR_PLAN_AREA').val(="+$('#FLOOR_PLAN_AREA').val()+")");
	$('#security_exclusive_area').val($('#FLOOR_PLAN_AREA').val());
	$('#security_floor').val($('#FLOOR_HIGHEST').val());
	var data = frmloanApplyStep.ajaxSubmit();
	if(data == null) return false;
	
	
	$.ajax({
		url : "<c:url value='/m/loanhomemortgage/modifyLoanREHomeInfo.json'/>",
		data : data,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		async : false,
		success : function (result) {
			if(result.result == '00') {
				loanApplyStepStep();
			}
		},
		error : function (e) {
			errMsg(e);
		}
	});
}

</script>
<script type="text/javascript">
$(document).ready(function() {
	$('.selectpicker').selectpicker({});

});
</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>주택정보</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
	<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
	<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
	<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
	<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
	<input type="hidden" name="cd_house_type" id="cd_house_type" />
	<input type="hidden" name="region_1" id="region_1" />
	<input type="hidden" name="region_2" id="region_2" />
	<input type="hidden" name="region_3" id="region_3" />
	<input type="hidden" name="security_addr" id="security_addr" />
	<input type="hidden" name="security_exclusive_area" id="security_exclusive_area" />
	<input type="hidden" name="ssn_person" id="ssn_person" value="${txFcTransmitVO.ssn_person}"/>
	<input type="hidden" name="security_floor" id="security_floor" />
		<div class="container">
			<div class="lead">
				<p>주택정보를 입력해 주세요.</p>
			</div>	
			<div class="form-inline">
				<div class="form-group">
					<label for="">주택종류</label>
					<select class="selectpicker" data-header="주택종류선택" name="BUILDING_TYPE" id="BUILDING_TYPE">
						<option value=''>주택종류</option>
						<option value='1'>아파트</option>
						<option value='2'>오피스텔</option>
					</select>
				</div>
				<div class="form-group">
					<label for="REGION1_CODE">시/도</label>
					<select class="selectpicker" data-header="시/도" name="REGION1_CODE" id="REGION1_CODE"
                            onchange="">
						<option value="">시/도</option>
					</select>
				</div>
				<div class="form-group">
					<label for="REGION2_CODE">시/군/구</label>
					<select class="selectpicker" data-header="시/군/구" name="REGION2_CODE" id="REGION2_CODE">
						<option value="">시/군/구</option>
					</select>
<!-- 					<input type="1hidden" naem="REGION_2" value="REGION2_CODE의 name"> -->
				</div>
				<div class="form-group">
					<label for="REGION3_CODE">읍/면/동</label>
					<select class="selectpicker" data-header="읍/면/동" name="REGION3_CODE" id="REGION3_CODE">
						<option value="">읍/면/동</option>
					</select>
				</div>
				<div class="form-group">
					<label for="apartment_nm">단지</label>
					<input type="hidden" class="form-control " id="APARTMENT" name="APARTMENT" />
					<select class="selectpicker" data-header="읍/면/동" name="apartment_nm" id="apartment_nm">
						<option value="">단지</option>
					</select>
				</div>
				<div class="form-group">
					<label for="">면적</label>
					<select class="selectpicker" data-header="공급면적/전용면적" name="FLOOR_PLAN_AREA" id="FLOOR_PLAN_AREA" onchange="chkValidate();">
						<option value="">공급면적/전용면적</option>
					</select>
				</div>
				<div class="form-group">
					<label for="FLOOR_HIGHEST">층수</label>
					<select class="selectpicker" data-header="층수" name="FLOOR_HIGHEST" id="FLOOR_HIGHEST" onchange="chkValidate();">
						<option value="">층수</option>
					</select>
				</div>
			</div>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" type="button" class="btn btn-lg btn-primary btn-block" onclick="updateTxFc();">확인</a>
		</div>
	</form>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
