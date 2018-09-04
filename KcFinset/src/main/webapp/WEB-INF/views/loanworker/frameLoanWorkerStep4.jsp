<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko" class="pop-full">
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
    function procConditioncreditInfo() {
		var frm = document.frmConditioncreditInfo;
		if(frm.no_person.value == ""){
			toastMsg("no_person를 선택해해주세요");
			frm.no_person.focus();
			return;
		}
		if(!frm.validateForm()) return false;
		// var data = $("#frmConditioncreditInfo").ajaxSubmit();
		var data = $("#frmConditioncreditInfo").serialize();
		if(data == null) return false;
		$.ajax({
			url : "<c:url value='/m/loanworker/procConditioncreditInfo.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				toastMsg('적용되었습니다.');
                setTimeout(function(){
                    goGoodsList();
                },500);
			},
			error : function (e) {
				errMsg(e);
			}
		});

	}
	/*신청방식*/
	var ar_cd_apply_type = ["cd_apply_type_01"
                           ,"cd_apply_type_02"
                           ,"cd_apply_type_03"
                           ,"cd_apply_type_04"];
    /*거래방식*/
	var ar_cd_trade_type = ["cd_trade_type_01"
                           ,"cd_trade_type_02"];
    /*금리방식*/
	var ar_cd_ratio_type = ["cd_ratio_type_01"
                           ,"cd_ratio_type_02"];
    /*상환방식*/
	var ar_cd_type_pay   = ["cd_type_pay_01"
                           ,"cd_type_pay_02"
                           ,"cd_type_pay_03"];
    /*상환기간*/
	var ar_cd_term_loan  = ["cd_term_loan_01"
                           ,"cd_term_loan_02"
                           ,"cd_term_loan_03"
                           ,"cd_term_loan_04"];
    /*기타*/
	var ar_etc           = ["yn_send_docu"
                           ,"yn_visit"
                           ,"yn_srch_ratio_limit"
                           ,"yn_erly_rpay_fee"   ];
    var ar_type_name = [ ar_cd_apply_type
                        ,ar_cd_trade_type
                        ,ar_cd_ratio_type
                        ,ar_cd_type_pay
                        ,ar_cd_term_loan
                        ,ar_etc
                       ];
    /**
     * 선택한 버튼의 타입
     * 0 : 신청방식
     * 1 : 거래방식
     * 2 : 금리방식
     * 3 : 상환방식
     * 4 : 상환기간
     * 5 : 기타
     * @param name
     * @returns {string}
     */
	function getBtnTypeIndex(name) {
	    var arType = [ar_cd_apply_type
                     ,ar_cd_trade_type
                     ,ar_cd_ratio_type
                     ,ar_cd_type_pay
                     ,ar_cd_term_loan
                     ,ar_etc
        ];
	    var resultIndex = -1;
	    var tempAr;
	    for(var i=0;i<arType.length;i++) {
	        tempAr = arType[i];
	        for(var j=0;j<tempAr.length;j++) {
	            if(name == tempAr[j]){
                    resultIndex = i;
	                break;
                }
            }
            if(resultIndex > -1) {
	            break;
            }
        }
        if(resultIndex == -1) {
	        alert('이메세지는 나오면 안되는데');
        }
        return resultIndex;
    }
    /**
     * 선택한 버튼의 리스트
     */
    function getBtnTypeList(name) {
	    var index = getBtnTypeIndex(name);
	    var resultList = ar_type_name[index];
	    return resultList;
    }
	function setButton(obj){
		var objName = $(obj).attr('name');
		var hiddenVal = $('input[name='+ objName +']').val();

		if( hiddenVal == "Y" ) {
			$(obj).removeClass('active');
			$('input[name='+ objName +']').val("N");
		}
		else{

			$(obj).addClass('active');
			$('input[name='+ objName +']').val("Y");
            // /**
            //  * 자기자신이 아닌 버튼은 active 를 없애고 N으로 바꾼다.
            //  */
            // var btnType = getBtnTypeIndex(objName);
            // var arBtnName = getBtnTypeList(objName);
            // var tempName;
            // switch(btnType) {
            //     /**
            //      * 기타는 모두 선택시 전체 해제를 한다.
            //      */
            //     case 5:
            //         var isAllSelected = true;
            //         for(var i=0;i<arBtnName.length;i++) {
            //             tempName = arBtnName[i];
            //             var hiddenVal = $('input[name='+ tempName +']').val();
            //             if(hiddenVal != 'Y') {
            //                 isAllSelected = false;
            //                 break;
            //             }
            //         }
            //         /**
            //          * 전부선택되었으면 전부해제한다.
            //          */
            //         if(isAllSelected == true) {
            //             for(var i=0;i<arBtnName.length;i++) {
            //                 tempName = arBtnName[i];
            //                 $("#div_"+tempName).removeClass('active');
            //                 $('input[name='+ tempName +']').val("N");
            //             }
            //         }
            //         break;
            //     /**
            //      * 기타 이외에 경우 선택한 버튼이 아니 버튼은 active 제거하고 N으로 셋팅한다.
            //      */
            //     default:
            //         for(var i=0;i<arBtnName.length;i++) {
            //             tempName = arBtnName[i];
            //             if(tempName != objName) {
            //                 $("#div_"+tempName).removeClass('active');
            //                 $('input[name='+ tempName +']').val("N");
            //             }
            //         }
            // }
		}
	}

	//버튼 초기화
	function initButton(){

		$('#apply_type > button'		).each(function(){
			$(this).removeClass('active');//$(this).addClass('active');
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});

		$('#trade_type > button'		).each(function(){
			$(this).removeClass('active');//$(this).addClass('active');
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});

		$('#ratio_type > button'		).each(function(){
			$(this).removeClass('active');//$(this).addClass('active');
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});

		$('#pay_type > button'			).each(function(){
			$(this).removeClass('active');//$(this).addClass('active');
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});

		$('#term_type > button'		).each(function(){
			$(this).removeClass('active');//$(this).addClass('active');
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});

		$('#etc_type_01 > button'	).each(function(){
			$(this).removeClass('active');//$(this).addClass('active');
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});

		$('#etc_type_02 > button'	).each(function(){
			$(this).removeClass('active');//$(this).addClass('active');
			$('input[name='+ $(this).attr('name') +']').val("N");//$('input[name='+ $(this).attr('name') +']').val("Y");
		});
        procConditioncreditInfo();
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
				<button type="button" class="ui-nav nav-back" onclick="goGoodsList();">뒤로가기</button>
			</div>
			<h1>조건선택</h1>
			<button class="btn-header-close" onclick="goGoodsList();">
				<span class="ico-close">닫기</span>
			</button>
		</div>
	</header>
	<!-- Content -->
<form name="frmConditioncreditInfo" id="frmConditioncreditInfo" method="post">
	<section id="content">
		<input type="hidden" class="form-control" name="no_person" value="${no_person}"/>
		<input type="hidden" class="form-control" name="cd_apply_type_01" value="${conditioncreditInfo.cd_apply_type_01}"/>
		<input type="hidden" class="form-control" name="cd_apply_type_02" value="${conditioncreditInfo.cd_apply_type_02}"/>
		<input type="hidden" class="form-control" name="cd_apply_type_03" value="${conditioncreditInfo.cd_apply_type_03}"/>
		<input type="hidden" class="form-control" name="cd_apply_type_04" value="${conditioncreditInfo.cd_apply_type_04}"/>
		<input type="hidden" class="form-control" name="cd_trade_type_01" value="${conditioncreditInfo.cd_trade_type_01}"/>
		<input type="hidden" class="form-control" name="cd_trade_type_02" value="${conditioncreditInfo.cd_trade_type_02}"/>
		<input type="hidden" class="form-control" name="cd_type_pay_01" value="${conditioncreditInfo.cd_type_pay_01}"/>
		<input type="hidden" class="form-control" name="cd_type_pay_02" value="${conditioncreditInfo.cd_type_pay_02}"/>
		<input type="hidden" class="form-control" name="cd_type_pay_03" value="${conditioncreditInfo.cd_type_pay_03}"/>
		<input type="hidden" class="form-control" name="cd_term_loan_01" value="${conditioncreditInfo.cd_term_loan_01}"/>
		<input type="hidden" class="form-control" name="cd_term_loan_02" value="${conditioncreditInfo.cd_term_loan_02}"/>
		<input type="hidden" class="form-control" name="cd_term_loan_03" value="${conditioncreditInfo.cd_term_loan_03}"/>
		<input type="hidden" class="form-control" name="cd_term_loan_04" value="${conditioncreditInfo.cd_term_loan_04}"/>
		<input type="hidden" class="form-control" name="cd_time_exec_01" value="${conditioncreditInfo.cd_time_exec_01}"/>
		<input type="hidden" class="form-control" name="cd_time_exec_02" value="${conditioncreditInfo.cd_time_exec_02}"/>
		<input type="hidden" class="form-control" name="yn_send_docu" value="${conditioncreditInfo.yn_send_docu}"/>
		<input type="hidden" class="form-control" name="yn_visit" value="${conditioncreditInfo.yn_visit}"/>
		<input type="hidden" class="form-control" name="yn_erly_rpay_fee" value="${conditioncreditInfo.yn_erly_rpay_fee}"/>
		<input type="hidden" class="form-control" name="yn_srch_ratio_limit" value="${conditioncreditInfo.yn_srch_ratio_limit}"/>
		<input type="hidden" class="form-control" name="cd_ratio_type_01" value="${conditioncreditInfo.cd_ratio_type_01}"/>
		<input type="hidden" class="form-control" name="cd_ratio_type_02" value="${conditioncreditInfo.cd_ratio_type_02}"/>
		<input type="hidden" class="form-control" name="id_lst" value="${conditioncreditInfo.id_lst}"/>
		<input type="hidden" name="cd_goods_class_l" id="cd_goods_class_l">
		<input type="hidden" name="cd_goods_class_m" id="cd_goods_class_m">
		<div class="container">
			<h2 class="h2">신청방식</h2>
			<div id="apply_type" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_apply_type_01" name="cd_apply_type_01"   class="btn btn-check ${ conditioncreditInfo.cd_apply_type_01 == 'Y' ? 'active':''}" onclick="setButton(this);">모바일</button>
				<button type="button" id="div_cd_apply_type_02" name="cd_apply_type_02"   class="btn btn-check ${ conditioncreditInfo.cd_apply_type_02 == 'Y' ? 'active':''}" onclick="setButton(this);">인터넷</button>
				<button type="button" id="div_cd_apply_type_03" name="cd_apply_type_03"   class="btn btn-check ${ conditioncreditInfo.cd_apply_type_03 == 'Y' ? 'active':''}" onclick="setButton(this);">콜센터</button>
				<button type="button" id="div_cd_apply_type_04" name="cd_apply_type_04"   class="btn btn-check ${ conditioncreditInfo.cd_apply_type_04 == 'Y' ? 'active':''}" onclick="setButton(this);">영업점</button>
			</div>
		</div>
		<div class="container">
			<h2 class="h2">거래방식</h2>
			<div id="trade_type" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_trade_type_01" name="cd_trade_type_01"   class="btn btn-check ${ conditioncreditInfo.cd_trade_type_01     == 'Y' ? 'active':''}" onclick="setButton(this);">건별대출</button>
				<button type="button" id="div_cd_trade_type_02" name="cd_trade_type_02"   class="btn btn-check ${ conditioncreditInfo.cd_trade_type_02     == 'Y' ? 'active':''}" onclick="setButton(this);">(마이너스)한도대출</button>
			</div>
		</div>
		<div class="container">
			<h2 class="h2">금리방식</h2>
			<div id="ratio_type" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_ratio_type_01" name="cd_ratio_type_01"   class="btn btn-check ${ conditioncreditInfo.cd_ratio_type_01     == 'Y' ? 'active':''}" onclick="setButton(this);">고정금리</button>
				<button type="button" id="div_cd_ratio_type_02" name="cd_ratio_type_02"   class="btn btn-check ${ conditioncreditInfo.cd_ratio_type_02     == 'Y' ? 'active':''}" onclick="setButton(this);">변동금리</button>
			</div>
		</div>
		<div class="container">
			<h2 class="h2">상환방식</h2>
			<div id="pay_type" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_type_pay_01" name="cd_type_pay_01"     class="btn btn-check ${ conditioncreditInfo.cd_type_pay_01       == 'Y' ? 'active':''}" onclick="setButton(this);">원리금균등상환</button>
				<button type="button" id="div_cd_type_pay_02" name="cd_type_pay_02"     class="btn btn-check ${ conditioncreditInfo.cd_type_pay_02       == 'Y' ? 'active':''}" onclick="setButton(this);">원금균등상환</button>
				<button type="button" id="div_cd_type_pay_03" name="cd_type_pay_03"     class="btn btn-check ${ conditioncreditInfo.cd_type_pay_03       == 'Y' ? 'active':''}" onclick="setButton(this);">만기일시상환</button>
			</div>
		</div>
		<div class="container">
			<h2 class="h2">상환기간</h2>
			<div id="term_type" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_cd_term_loan_01" name="cd_term_loan_01"    class="btn btn-check ${ conditioncreditInfo.cd_term_loan_01      == 'Y' ? 'active':''}" onclick="setButton(this);">~1년</button>
				<button type="button" id="div_cd_term_loan_02" name="cd_term_loan_02"    class="btn btn-check ${ conditioncreditInfo.cd_term_loan_02      == 'Y' ? 'active':''}" onclick="setButton(this);">2년~3년</button>
				<button type="button" id="div_cd_term_loan_03" name="cd_term_loan_03"    class="btn btn-check ${ conditioncreditInfo.cd_term_loan_03      == 'Y' ? 'active':''}" onclick="setButton(this);">3년~5년</button>
				<button type="button" id="div_cd_term_loan_04" name="cd_term_loan_04"    class="btn btn-check ${ conditioncreditInfo.cd_term_loan_04      == 'Y' ? 'active':''}" onclick="setButton(this);">5년~</button>
			</div>
		</div>
		<div class="container">
			<h2 class="h2">기타</h2>
			<div id="etc_type">
			<div  id="etc_type_01" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<%--<button type="button" id="div_cd_time_exec_01" name="cd_time_exec_01"    class="btn btn-check ${ conditioncreditInfo.cd_time_exec_01      == 'Y' ? 'active':''}"	onclick="setButton(this   );">즉시</button>--%>
				<%--<button type="button" id="div_cd_time_exec_02" name="cd_time_exec_02"    class="btn btn-check ${ conditioncreditInfo.cd_time_exec_02      == 'Y' ? 'active':''}"	onclick="setButton(this   );">당일</button>--%>
				<button type="button" id="div_yn_send_docu"        name="yn_send_docu"        class="btn btn-check ${ conditioncreditInfo.yn_send_docu         == 'Y' ? 'active':''}"	onclick="setButton(this);">무서류</button>
				<button type="button" id="div_yn_visit"            name="yn_visit"            class="btn btn-check ${ conditioncreditInfo.yn_visit             == 'Y' ? 'active':''}"	onclick="setButton(this);">무방문</button>
			</div>
			<div  id="etc_type_02" class="btn-group btn-group-justified" role="group" aria-label="Justified button group">
				<button type="button" id="div_yn_srch_ratio_limit" name="yn_srch_ratio_limit" class="btn btn-check ${ conditioncreditInfo.yn_srch_ratio_limit  == 'Y' ? 'active':''}"	onclick="setButton(this);">금리/한도 확인가능</button>
				<button type="button" id="div_yn_erly_rpay_fee"    name="yn_erly_rpay_fee"    class="btn btn-check ${ conditioncreditInfo.yn_erly_rpay_fee     == 'Y' ? 'active':''}"	onclick="setButton(this);">중도상환수수료 없음</button>
			</div>
			</div>
		</div>
 		<div class="btn-fixed-bottom">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="initButton();">초기화</button>
			</div>
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="procConditioncreditInfo();">적용</button>
			</div>
		</div>
	</section>
</form>
	<!-- //Content -->
</div>
</body>
</html>
