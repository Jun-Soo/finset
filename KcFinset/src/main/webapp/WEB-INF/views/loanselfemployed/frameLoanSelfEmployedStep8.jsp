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
    var nm_comp                ;
    var kiscode                ;
    var no_biz_comp            ;
	$(document).ready(function() {
		chkValidate();
        nm_comp                = getCookie('nm_comp'               );
        no_biz_comp            = getCookie('no_biz_comp'           );
        kiscode                = getCookie('kiscode'               );
        $('#nm_comp'    ).val(nm_comp     );
        $('#no_biz_comp').val(no_biz_comp );
        $('#kiscode'    ).val(kiscode     );
        /**
         * 사업자명 찾기 버튼 클릭
         */
        $('#btnNm_comp').click(function(){
            // alert("업체명 찾기 버튼 클릭");
            searchJob();
        });
        /**
         * 사업자명 입력 클릭
         */
        $('#nm_comp').click(function(){
            // alert("업체명 찾기 버튼 클릭");
            searchJob();
        });
        $('#amt_year_sale').on("keyup",function(){
            var val = $('#amt_year_sale').val();
            if(val.length > 8) {
                toastMsg("연매출은 9999억원까지 입력가능합니다.");
                val = val.substr(0,8);
                $('#amt_year_sale').val(val);
            }
        });
	});


	function searchJob(){
		// if(frmloanApplyStep.amt_year_sale.value == '' || frmloanApplyStep.amt_year_sale.value == null){
		// $('#amt_year_sale').val('0');
		// }
		frmloanApplyStep.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep9.crz'/>";
		frmloanApplyStep.submit();
	}
	
	function loanApplyStepStep() {
		var regNumber = /^[0-9]*$/;
		if($("#nm_comp").val() == '' || $("#nm_comp").val() == null) {
			toastMsg('사업자명을 검색해주세요.');
			return false;
		}
		if($("#amt_year_sale").val() == '' || $("#amt_year_sale").val() == null) {
			toastMsg('연매출을 입력해주세요.');
			$("#amt_year_sale").focus();
			return false;
		} else if(!regNumber.test($('#amt_year_sale').val())) {
		    toastMsg('연매출 숫자만 입력해주세요.');
		    $('#amt_year_sale').val('');
		    $('#amt_year_sale').focus();
		    return false;
		}

        frmStepNextFromMobile();
	}
    /*
    * 현대 날짜의 YYYYMM 을 반환한다.
    * return YYYYMM
    */
    function getCurrentDateYM() {
        var now = new Date();
        var year= now.getFullYear();
        var mon = (now.getMonth()+1) > 9 ? '' + (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
        var strYM = year + mon;
        return strYM;
    }

    //tx_fc_transmit테이블에  직장/소득 정보 update 하기 위한 함
    function updateTxFc(){
	    // alert("updateTxFc");
        if ( !frmloanApplyStep.validateForm() ) return false;
        if($("#jb_dt_join_view").val() == '' || $("#jb_dt_join_view").val() == null) {
            toastMsg('사업개시(월)을 선택해주세요.');
            return false;
        } else {
            $("#jb_dt_join").val($("#jb_dt_join_view").val());
            frmloanApplyStep.jb_dt_join.value = frmloanApplyStep.jb_dt_join.value.replace(/-/gi, "");
            var current = getCurrentDateYM();
            if(Number(frmloanApplyStep.jb_dt_join.value) > Number(current)){
                toastMsg('사업개시(월)을 잘못입력했습니다.');
                return false;
            }
        }
        var data = $("#frmloanApplyStep").serialize();
        // var data = frmloanApplyStep.ajaxSubmit();
        // alert("updateTxFc:data="+data);
        if(data == null) return false;
        $.ajax({
            url : "<c:url value='/m/loanselfemployed/updateTxFc.json'/>",
            data : data,
            contentType : "application/x-www-form-urlencoded; charset=UTF-8",
            type : "POST",
            async : false,
            success : function (result) {
                // alert("updateTxFc:success:\nresult="+result+"\nresult.result="+result.result);
                if(result.result == '00') {
                    loanApplyStepStep();
                }
            },
            error : function (e) {
                // alert("updateTxFc:error:\nresult="+result+"\nresult.result="+result.result);
                errMsg(e);
            }
        });
    }

	function chkValidate(){
		if($('#nm_comp').val() != null
            && $('#nm_comp').val() != ''
            && $('#amt_year_sale').val() != null
            && $('#amt_year_sale').val() != ''
            && $('#jb_dt_join_view').val() != null
            && $('#jb_dt_join_view').val() != ''){
			affixBottom('show');
		} else {
			affixBottom('hide');
		}
	}
    /*
* 간편 등록 콜백 함수
* 모바일에서 간편등록이 완료되면 호출 시킨다.
*/
    function frmStepNextFromMobile() {
        frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14pre.crz'/>";
        <%--frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14.crz'/>";--%>
        <%--frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14.crz'/>";--%>
        frmloanApplyStep.submit();
    }
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
			<h1>사업자/소득 정보</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>사업자명/소득정보를 입력해주세요.</p>
			</div>	
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
				<input type="hidden" name="nm_person" id="nm_person" value="${txFcTransmitVO.nm_person}"/>
				<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
				<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
				<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
				<input type="hidden" name="no_person" id="no_person" value="${personVO.no_person}"/>
				<input type="hidden" name="hp" id="hp" value="${txFcTransmitVO.hp1 }"/>
                <input type="hidden" name="no_biz_comp" id="no_biz_comp" value=""/><%--사업자번호--%>
                <input type="hidden" name="kiscode" id="kiscode" value=""/>
                <input type="hidden" name="ssn_person" id="ssn_person" value="${txFcTransmitVO.ssn_person }"/>
				<div class="form-inline">
				<div class="form-group has-feedback">
					<label for="nm_comp">사업자명</label>
					<input type="text" class="form-control" name="nm_comp" id="nm_comp" placeholder="사업자명을 검색하세요" value="${txFcTransmitVO.nm_comp }" readonly="readonly" autocomplete="off">
		            <button type="button" class="sch-btn" id="btnNm_comp"><span class="form-control-feedback btn-search">검색</span></button>
				</div>
				<div class="form-group has-feedback">
					<label for="amt_year_sale">연매출</label>
					<input type="number" class="form-control" name="amt_year_sale" id="amt_year_sale" maxlength="8" value="<c:out value="${txFcTransmitVO.amt_year_sale eq '0' ? '' : txFcTransmitVO.amt_year_sale}" />" onkeyup="chkValidate();" autocomplete="off"/>
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
				<div class="form-group">
					<label for="jb_dt_join_view">사업개시<small>(월)</small></label>
					<input type="month" name="jb_dt_join_view" id="jb_dt_join_view" class="form-control slt-date"
                           placeholder="" onchange="chkValidate();" />
					<input type="hidden" name="jb_dt_join" id="jb_dt_join" class="form-control slt-date" placeholder="" />
				</div>
			</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="next_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="updateTxFc();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
