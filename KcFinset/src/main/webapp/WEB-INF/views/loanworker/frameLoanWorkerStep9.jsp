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
    var cd_occupational        ;
    var cd_occupational_detail ;
    var cd_worker_position     ;
    var cd_employee_type       ;
    var jb_dt_join_view        ;
    var amt_year_income        ;
    var isInit = true;
    var isInit2 = true;
    var changedData = new Object();
    /**
     * 처음 호출인지
     */
    var getIsStart = function(id) {
        var r = changedData[id];
        // alert("getIsStart:r="+r);
        var result = true;
        if(r != undefined) {
            result = false;
        }
        return result;
    }
    var setIsStart = function(id) {
        changedData[id]='1';
    }
    $(document).ready(function() {
        <c:if test="${site eq 'LOCAL'}">
        testOnload();
        </c:if>        <%--alert("파라메터 \ncd_goods:${txFcTransmitVO.cd_goods }\nno_person=${txFcTransmitVO.no_person}\nhp1=${txFcTransmitVO.hp1 }\ncd_fc=${txFcTransmitVO.cd_fc }");--%>
		chkValidate();
		/**
		 * 검색화면에서 선택한 업체명 가져오기
		 */
        nm_comp                = getCookie('nm_comp'               );
        no_biz_comp            = getCookie('no_biz_comp'           );
        kiscode                = getCookie('kiscode'               );
        cd_occupational        = getCookie('cd_occupational'       );
        cd_occupational_detail = getCookie('cd_occupational_detail');
        cd_worker_position     = getCookie('cd_worker_position'    );
        cd_employee_type       = getCookie('cd_employee_type'      );
        jb_dt_join_view        = getCookie('jb_dt_join_view'       );
        amt_year_income        = getCookie('amt_year_income'       );
        isInit2                = getCookie('isInit2'               );
        var cookievalue ="";
        cookievalue += 'nm_comp='                  + nm_comp               ;
        cookievalue += '\nno_biz_comp='            + no_biz_comp           ;
        cookievalue += '\ncd_occupational='        + cd_occupational       ;
        cookievalue += '\ncd_occupational_detail=' + cd_occupational_detail;
        cookievalue += '\ncd_worker_position='     + cd_worker_position    ;
        cookievalue += '\ncd_employee_type='       + cd_employee_type      ;
        cookievalue += '\njb_dt_join_view='        + jb_dt_join_view       ;
        cookievalue += '\namt_year_income='        + amt_year_income       ;
        // debug("$(document).ready:\n"+cookievalue);
        $('#nm_comp'               ).val(nm_comp               );
        $('#no_biz_comp'           ).val(no_biz_comp           );
        $('#kiscode'               ).val(kiscode               );
        $('#cd_employee_type'      ).val(cd_employee_type      );
        $('#jb_dt_join_view'       ).val(jb_dt_join_view       );
        if (amt_year_income != '') {
            $('#amt_year_income').val(amt_year_income       );
        }
        nm_comp = nm_comp.trim();

        if(cd_occupational == '') {
            $('#cd_occupational').selectpicker('toggle');
        } else {
            /**
             * 직군 선택
             */
            $('#cd_occupational').val(cd_occupational);
            $('#cd_occupational').selectpicker('refresh');
            cateChgMid();
            if(nm_comp == '' || cd_occupational_detail == '') {
                hide2ndDepth();
            }
            /**
             * 2depth 값이 없을 때
             * 일반이면 사업자 선택화면이 뜨고
             * 나머지이면 두번째 콤보가 열린다.
             */
            /**
             * 일반
             */
            if(cd_occupational == '1' && nm_comp == "") {
                if(isInit2 != "false") {
                    // alert("업체명 찾기 버튼 클릭전");
                    $("#btnNm_comp").click();
                }
            }
            /**
             * 이외
             */
            else if(cd_occupational != '1' && cd_occupational_detail == '') {
                 toggleSelect('cd_occupational_detail');
            }
            /**
             * 2depth 값이 있을 때
             */
            else if(    cd_occupational == '1' && nm_comp != ""
                     || cd_occupational != '1' && cd_occupational_detail != ""
                   ) {
                $('#cd_occupational_detail').val(cd_occupational_detail);
                $('#cd_occupational_detail').selectpicker('refresh');
                chkValidate();
                cateChgLow();
                show2ndDepth();
                if(cd_worker_position == '') {
                    toggleSelect('cd_worker_position');
                } else {
                    $('#cd_worker_position').val(cd_worker_position    );
                    $('#cd_worker_position').selectpicker('refresh');
                }
			}
        }
        isInit = false;
        setCookie('isInit2',"false");

        /**
         * 직군 : 직군에 따라서 직장명을 선택할지 상세직군을 선택할지 처리를 한다.
         */
        $('#cd_occupational').change(function(){
            cd_occupational = $(this).val();
            var isStart = getIsStart("cd_occupational");
            setIsStart("cd_occupational");
            setCookie('cd_occupational',cd_occupational);
            if(cd_occupational =='1') {
                nm_comp = "";
			} else {
                nm_comp = $("#cd_occupational option:checked").text();
                $("#nm_comp").val(nm_comp);
			}
            cd_occupational_detail = "";
            cd_worker_position = "";
            setCookie("nm_comp",nm_comp);
            setCookie("cd_occupational_detail",cd_occupational_detail);
            setCookie('cd_worker_position',cd_worker_position);
            cateChgMid();
            if(cd_occupational !='1' && cd_occupational_detail == ''
            || cd_occupational =='1' && nm_comp == '') {
                hide2ndDepth();
            }
            if(isStart == true) {
                if(cd_occupational != '1') {
                    toggleSelect("cd_occupational_detail");
                } else {
                    $('#btnNm_comp').click();
                }
            }
            chkValidate();
        });
        /**
         * 상세직군찾기 버튼 클릭 : 바뀌면 직위,고용형태,입사년월을 보여주고,직위를 완성시킨후에 연다.
         */
        $('#btnNm_comp').click(function(){
            // alert("업체명 찾기 버튼 클릭");
            searchJob();
        });
        $('#cd_occupational_detail').change(function(){
            chkValidate();
            cd_occupational_detail = $('#cd_occupational_detail').val();
            // debug("changecd_occupational_detail:cd_occupational_detail="+cd_occupational_detail);
            setCookie('cd_occupational_detail',cd_occupational_detail);
            if(  cd_occupational != '1' && cd_occupational_detail !=''
                || cd_occupational == '2' && cd_occupational_detail !='') {
                show2ndDepth();
            } else if(   cd_occupational == '1' && nm_comp == ''
                || cd_occupational == '2' && cd_occupational_detail =='') {
                hide2ndDepth();
            }
            if (cd_occupational_detail != '') {
                cateChgLow();
            }
            var isStart = getIsStart("cd_occupational_detail");
            setIsStart("cd_occupational_detail");
            /**
             * 초기 셋팅하는 것이 아니면 바뀌는 이벤트에 고용형태를 연다
             */
            if(isStart == true && cd_occupational_detail != "" && isInit == false) {
                toggleSelect("cd_worker_position");
            }
        });
        /**
         * 직장명 : 바뀌면 직위,고용형태,입사년월을 보여주고,직위를 완성시킨후에 연다.
         */
        $('#nm_comp').change(function(){
            nm_comp = $(this).val();
            if (nm_comp != '') {
                show2ndDepth();
            } else {
                hide2ndDepth();
            }
            setCookie('nm_comp',nm_comp);
            cateChgLow();
        });
        /**
         * 직위
         */
        $('#cd_worker_position').change(function(){
            cd_worker_position = $(this).val();
            setCookie('cd_worker_position',cd_worker_position);
            chkValidate();
            var isStart = getIsStart("cd_worker_position");
            setIsStart("cd_worker_position");
            if(isStart == true) {
                /**
                 * 고용형태를 연다
                 */
                toggleSelect("cd_employee_type");
            }
        });
        /**
         * 고용형태 : 바뀌면 입사년월로 커서를 옮긴다.
         */
        $('#cd_employee_type').change(function(){
            cd_employee_type = $(this).val();
            setCookie('cd_employee_type',cd_employee_type);
            chkValidate();
            var isStart = getIsStart("cd_employee_type");
            setIsStart("cd_employee_type");

            if (isStart == true && cd_employee_type != '') {
                setTimeout(function(){
                    $('#jb_dt_join_view').click();
                 },100);
            }
        });
        /**
         * 고용형태 : 바뀌면 입사년월로 커서를 옮긴다.
         */
        $('#jb_dt_join_view').change(function(){
            jb_dt_join_view = $(this).val();
            setCookie('jb_dt_join_view',jb_dt_join_view);
            chkValidate();
            var isStart = getIsStart("jb_dt_join_view");
            setIsStart("jb_dt_join_view");
            if(isStart == true) {
                $("#amt_year_income").focus();
            }
        });
        /**
         * 연소득
         */
        $('#amt_year_income').on("keyup",function() {
            amt_year_income = $(this).val();
            setCookie('amt_year_income',amt_year_income);
            chkValidate();
        });
	});
    /**
     * 상세직군 변경시 처리
     */
    function changecd_occupational_detail() {
        // debug("changecd_occupational_detail");

    }

    /*
    * 공인인증서 유무 체크
    */
    function checkExistCert() {
        // debug("checkExistCert:userAgent="+userAgent);
        if(userAgent == "iOS") {
            // debug("checkExistCert1:userAgent="+userAgent);

            //공인인증서 유무 체크 결과 콜백 이벤트
            Jockey.on("resultCheckCert" , function(param) {
            	var iscert = false;
            	if(param.isCert == 1) iscert = true;

            	resultCheckCert(iscert);
            });
            // debug("checkExistCert2:userAgent="+userAgent);

            Jockey.send("checkExistCert");
            // debug("checkExistCert3:userAgent="+userAgent);

        } else if(userAgent == "Android") {
            // debug("checkExistCert.Android.1:userAgent="+userAgent);
            window.Android.checkExistCert();
            // debug("checkExistCert.Android.2:userAgent="+userAgent);
        } else {
            frmStepNextFromMobile();
        }
        // debug("checkExistCert2:end");
    }
    /*
    * 공인인증서 유무 결과 (모바일 에서 호출)
    */
    function resultCheckCert(isCert) {
        if(isCert) {	// 공인인증서가 있을 경우
            frmSimpleDoc();
        } else {		// 공인인증서가 없을 경우
            /**
             * 한도조회실제처리
             */
            frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14.crz'/>";
            /*테스트*/
            <%--frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep20.crz'/>";--%>
            frmloanApplyStep.submit();
        }
    }

    /*
    * 현대 날짜의 YYYY + month 을 반환한다.
    * return YYYYMM
    */
    function combineCurrentDateYM(month) {
        var now = new Date();
        var year = now.getFullYear();
        return year + month;
    }

    /*
	 *스크래핑 실행
	 * custNm, bizLicence, hp, nhisStartRcptYm, nhisEndYm, ntsStartRcptYm,
	 * ntsEndRcptYm, ntsStartTaxYm, ntsEndTaxYm, finYear, type, finYear
	 */
    function frmSimpleDoc() {

        var result = "121222222";
        var noPerson = $('#no_person').val();
        var nmPerson = $('#nm_person').val();
        var ssnPerson = $('#ssn_person').val();
        var bizLicence = "";
        var hp = $('#hp').val();
        var nhisStartRcptYm = minusMonthYM(6);
        var nhisEndYm = getCurrentDateYM();
        var ntsStartTaxYm = combineCurrentDateYM("01");
        var ntsEndTaxYm = combineCurrentDateYM("12");
        var ntsStartRcptYm = minusStartYear(1);
        var ntsEndRcptYm = minusEndYear(1);
        var ntsStartIncomeY = minusYear(1);
        var ntsEndIncomeY = minusYear(1);
        var finYear = minusYear(1);

        if(userAgent == "iOS") {
// 			location.href = "call://checkPasswordCert//" + result + "//" + noPerson + "//" + ssnPerson + "//"
// 			+ bizLicence + "//" +hp + "//" + nhisStartRcptYm + "//" + nhisEndYm + "//" + ntsStartTaxYm + "//" + ntsEndTaxYm + "//" + ntsStartRcptYm + "//"
// 			+ ntsEndRcptYm + "//" + ntsStartIncomeY + "//" + ntsEndIncomeY + "//" + finYear;

            //간편 등록 콜백 함수(완료시 모바일에서 호출하는 이벤트)
            Jockey.on("frmStepNextFromMobile" , function(param) {
                frmStepNextFromMobile();
            });

            //간편 등록 콜백 함수(취소시 모바일에서 호출하는 이벤트)
            Jockey.on("frmStepNextFromMobileCancle" , function() {
                frmStepNextFromMobileCancle();
            });

            //주민등록번호 오류 (모바일에서 호출하는 이벤트)
            Jockey.on("failSsnVerify" , function() {
                failVerify();
            });

            //공인인증 및 건강보험 국세청 스크래핑 실행 모바일 호출
            Jockey.send("checkPasswordCert" , {
                result : result,
                noPerson : noPerson,
                ssnPerson : ssnPerson,
                bizLicence : bizLicence,
                hp : hp,
                nhisStartRcptYm : nhisStartRcptYm,
                nhisEndYm : nhisEndYm,
                ntsStartTaxYm : ntsStartTaxYm,
                ntsEndTaxYm : ntsEndTaxYm,
                ntsStartRcptYm : ntsStartRcptYm,
                ntsEndRcptYm : ntsEndRcptYm,
                ntsStartIncomeY : ntsStartIncomeY,
                ntsEndIncomeY : ntsEndIncomeY,
                finYear : finYear,
                nmPerson : nmPerson
            });

        } else if(userAgent == "Android") {
            window.Android.checkPasswordCert(result
				, noPerson, ssnPerson
				, bizLicence
				, hp
				, nhisStartRcptYm
				, nhisEndYm
				, ntsStartTaxYm
				, ntsEndTaxYm
				, ntsStartRcptYm
				, ntsEndRcptYm
				, ntsStartIncomeY
				, ntsEndIncomeY
				, finYear
				, nmPerson);
        }
    }
    /*
    * 간편 등록 콜백 함수
    * 모바일에서 간편등록이 완료되면 호출 시킨다.
    */
    function frmStepNextFromMobile() {
        frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14pre.crz'/>";
        frmloanApplyStep.submit();
    }
    /*
    * 간편 등록 콜백 함수 (취소시)
    * 모바일에서 간편등록이 완료되면 호출 시킨다.
    */
    function frmStepNextFromMobileCancle(){
        //frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep14.crz'/>";
        //frmloanApplyStep.submit();
    }

    /*
    * 주민번호 틀렸을 시
    */
    function failVerify() {
        frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep6.crz'/>";
        toastMsg('주민등록번호가 정확하지 않습니다.');
        setTimeout(function(){
            frmloanApplyStep.submit();
        }, 2000);
    }

    function searchJob() {
		if(frmloanApplyStep.cd_occupational.value == '' || frmloanApplyStep.cd_occupational.value == null){
			return false;
		}
		if(frmloanApplyStep.amt_year_income.value == '' || frmloanApplyStep.amt_year_income.value == null){
		    $('#amt_year_income').prop('disabled',true);
		}
		frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep10.crz'/>";
        // alert("searchJob:action=/m/loanworker/frameLoanWorkerStep10.crz");

        frmloanApplyStep.submit();
	}
	
	function jiggunChk(){
		
	}
	
	function loanApplyStepStep() {
        // debug("loanApplyStepStep");
		var regNumber = /^[0-9]*$/;

        nm_comp                = $('#nm_comp'               ).val();
        no_biz_comp            = $('#no_biz_comp'           ).val();
        cd_occupational        = $('#cd_occupational'       ).val();
        cd_occupational_detail = $('#cd_occupational_detail').val();
        cd_worker_position     = $('#cd_worker_position'    ).val();
        cd_employee_type       = $('#cd_employee_type'      ).val();
        jb_dt_join_view        = $('#jb_dt_join_view'       ).val();
        amt_year_income        = $('#amt_year_income'       ).val();

        var cookievalue ="";
        cookievalue += 'nm_comp='                  + nm_comp               ;
        cookievalue += '\nno_biz_comp='            + no_biz_comp           ;
        cookievalue += '\ncd_occupational='        + cd_occupational       ;
        cookievalue += '\ncd_occupational_detail=' + cd_occupational_detail;
        cookievalue += '\ncd_worker_position='     + cd_worker_position    ;
        cookievalue += '\ncd_employee_type='       + cd_employee_type      ;
        cookievalue += '\njb_dt_join_view='        + jb_dt_join_view       ;
        cookievalue += '\namt_year_income='        + amt_year_income       ;
        // debug("loanApplyStepStep\n"+cookievalue);


        if(cd_occupational == '' || cd_occupational == null) {
			toastMsg('직군을 선택해주세요.');
			return false;
		} else {
			if(cd_occupational== '1'){
				if(nm_comp == '' || nm_comp == null) {
					toastMsg('직장명을 검색해주세요.');
					return false;
				}
			} else {
				if(cd_occupational_detail == '' || cd_occupational_detail == null) {
					toastMsg('상세직군을 선택해주세요.');
					return false;
				}
			}
		}
        // debug("loanApplyStepStep 1");

        // if ( !frmloanApplyStep.validateForm() ) return false;
        if(cd_worker_position == '' || cd_worker_position == null) {
            toastMsg('직위를 선택해주세요.');
            return false;
        }
        // debug("loanApplyStepStep 2");

        if(cd_employee_type == '' || cd_employee_type == null) {
            toastMsg('고용형태를 선택해주세요.');
            return false;
        }
        // debug("loanApplyStepStep 3");

        if(jb_dt_join_view == '' || jb_dt_join_view == null) {
            toastMsg('입사년월을 선택해주세요.');
            return false;
        }
        // debug("loanApplyStepStep 4");

        var jb_dt_join= jb_dt_join_view.replace(/-/gi, "");
        // debug("loanApplyStepStep :jb_dt_join="+jb_dt_join);
        $("#jb_dt_join").val(jb_dt_join);
        var current = getCurrentDateYM();
        // debug("loanApplyStepStep:current="+current);
        if(jb_dt_join > current) {
            toastMsg('입사년월을 잘못입력했습니다.');
            return false;
        }
        // debug("loanApplyStepStep 5");

        if(amt_year_income == '' || amt_year_income== null) {
			toastMsg('연소득을 입력해주세요.');
			$("#amt_year_income").focus();
			return false;
		}
        // debug("loanApplyStepStep 6");

        if(!regNumber.test(amt_year_income)) {
		    toastMsg('연소득 숫자만 입력해주세요.');
		    $('#amt_year_income').val('');
		    $('#amt_year_income').focus();
		    return false;
		}
        // debug("loanApplyStepStep 7");

        // var data = frmloanApplyStep.ajaxSubmit();
        var data = $("#frmloanApplyStep").serialize();
        // debug("loanApplyStepStep:data=\n"+data);

        if(userAgent == "Android") {
            window.Android.loding('Y');
        }
        else if(userAgent == "iOS") {
            Jockey.send("showLoading");
        }

        $.ajax({
            url : "<c:url value='/m/loanworker/updateTxFc.json'/>",
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
                // debug("loanApplyStepStep:updateTxFc.json:result=\n"+result);
                if(result.result == '00') {
                    frmStepNextFromMobile();
                }
            },
            error : function (e) {
                errMsg(e);
            }
        });

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
    /*
    * 입력한 날짜 -month를 반환한다.
    * return YYYYMM
    */
    function minusMonthYM(minusMonth) {
        var date = new Date();
        date.setMonth(date.getMonth() - minusMonth);
        var year = date.getFullYear();
        var mon = (date.getMonth()+1) > 9 ? '' + (date.getMonth() + 1) : '0' + (date.getMonth() + 1);
        var strYM = year + mon;
        return strYM;
    }
    /*
    * 현재년도 - year 을 반환한다
    * return YYYY
    */
    function minusYear(paramYear) {
        var now = new Date();
        var year = now.getFullYear();
        return (Number(year) - paramYear);
    }
    /*
    * 현재년도 - year + 01 을 반환한다
    * return YYYY01
    */
    function minusStartYear(paramYear) {
        var now = new Date();
        var year = now.getFullYear();
        return (Number(year) - paramYear) + "01";
    }
    /*
    * 현재년도 - year + 12 을 반환한다
    * return YYYY12
    */
    function minusEndYear(paramYear) {
        var now = new Date();
        var year = now.getFullYear();
        return (Number(year) - paramYear) + "12";
    }


    /**
     * 입력이 모두 되면 하단에 확인 버튼을 보여준다.
     */
	function chkValidate() {
        $("#jb_dt_join").val($("#jb_dt_join_view").val());
        // frmloanApplyStep.jb_dt_join.value = frmloanApplyStep.jb_dt_join.value.replace(/-/gi, "");
        var cd_occupational       ;/*직군            */
        var nm_comp               ;/*직장명           */
        var cd_occupational_detail;/*상세직군          */
        var cd_worker_position    ;/*직위            */
        var cd_employee_type      ;/*고용형태          */
        var jb_dt_join_view       ;/*입사년월(- 포함)    */
        var jb_dt_join            ;/*입사년월          */
        var amt_year_income       ;/*연소득           */
        cd_occupational        = $('#cd_occupational'       ).val();
        nm_comp                = $('#nm_comp'               ).val();
        cd_occupational_detail = $('#cd_occupational_detail').val();
        var arId =['cd_worker_position'
                  ,'cd_employee_type'
                  ,'jb_dt_join_view'
                  ,'jb_dt_join'
                  ,'amt_year_income'
        ];
        var isAllInput = true;
        /**
         * 일반
         */
        if(cd_occupational == '1' && nm_comp == '') {
                isAllInput = false;
        }
        /**
         * 이외 기업
         */
        else if(cd_occupational_detail == '') {
            isAllInput = false;
        }
        var val;
        var id;
        if(isAllInput == true) {
            for(var i=0,len = arId.length;i<len;i++) {
                id = arId[i];
                val = $('#'+id).val();
                if(val == '') {
                    isAllInput = false;
                    break;
                }
            }
        }
        if(isAllInput == true) {
            affixBottom('show');
        } else {
            affixBottom('hide');
        }
	}
	
	//중분류 스크립트
	function cateChgMid() {
	    // debug("cateChgMid");
        var val = $('#cd_occupational').val();
        // debug("cateChgMid:cd_occupational:val="+val);
        if(val == '' || val == '1') {
            $('#jobSearch').show();
            $('#jobDetail').hide();
            var is_start = getCookie(IS_START);
            if (nm_comp == '' && is_start == '') {
                $('#nm_comp').click();
                setCookie(IS_START,"1");
            }
        } else {
            $('#jobSearch').hide();
            $('#jobDetail').show();
        }
		var cate_mid_1  =["중분류","일반 기업"];
		var cate_mid_1v =["","111"];
		var cate_mid_2  =["중분류","중앙정부/지자체공무원","기능직","법조/법무","경찰","소방","군인","군무원","교육","별정직","정무의원직"];
		var cate_mid_2v =["","121","122","123","124","125","126","127","128","129","130"];
		var cate_mid_3  =["중분류","법조/법무","의료","세무/회계","기술","부동산","항공"];
		var cate_mid_3v =["","131","132","133","134","135","136"];
		var cate_mid_4  =["중분류","정부투자/산하기관","주한외국관공서/미군군무원","지방자치산하기관","기타 공공기관"];
		var cate_mid_4v =["","141","142","143","144"];
		var cate_mid_5  =["중분류","대학교","전문대학","초/중/고교","기타학교","유치원/유아원/보육원","일반학원"];
		var cate_mid_5v =["","151","152","153","154","155","156"];
		var cate_mid_6  =["중분류","종합/대학병원","일반병원","개인/요양병원"];
		var cate_mid_6v =["","161","162","163"];
		var d = eval("cate_mid_" + val);
		var k = eval("cate_mid_" + val + "v");
		var target = document.getElementById("cd_occupational_detail");
		target.options.length = 0;
		for (x in d) {
			var opt = document.createElement("option");
			opt.value = k[x];
			opt.innerHTML = d[x];
			target.appendChild(opt);
		}
        /**
         * 일반기업
         */
        if(val == '1') {
            cd_occupational_detail = "111";
            $('#cd_occupational_detail').val(cd_occupational_detail);
        }
        $('#cd_occupational_detail').selectpicker('refresh');

        // debug("cateChgMid:cd_occupational_detail="+cd_occupational_detail);
        //
        // if (cd_occupational_detail != '') {
        //     $('#cd_occupational_detail').val(cd_occupational_detail);
        //     $('#cd_occupational_detail').selectpicker('refresh');
        //     changecd_occupational_detail();
        // }
    }
    function cateChgLow() {
        // debug("cateChgLow");
        var val = $('#cd_occupational_detail').val();
        // debug("cateChgLow:val="+val);
// 		debug(e + "XXXXXX");
        var cate_low_111  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","기타"];
        var cate_low_111v =["","11111","11112","11113","11114","11115","11116","11117","11118","11119"];
        var cate_low_121  =["소분류","1급","2급","3급","4급","5급","6급","7급","8급","9급","10급"];
        var cate_low_122  =["소분류","1급","2급","3급","4급","5급","6급","7급","8급","9급","10급"];
        var cate_low_123  =["소분류","대법원장","대법관","고등법원장","일반법관","고시합격자"];
        var cate_low_124  =["소분류","치안총감/치안정감/치안감","경무관","총경","경정","경감","경위","경사","경장","순경"];
        var cate_low_125  =["소분류","소방총감/소방정감","소방감","소방준감","소방정","소방령","소방경","소방위","소방장","소방교","소방사"];
        var cate_low_126  =["소분류","대장/중장/소장/준장","대령/중령/소령","대위","중위","소위","준위","원사","상사","중사","하사"];
        var cate_low_127  =["소분류","1급","2급","3급","4급","5급","6급","7급","8급","9급","10급"];
        var cate_low_128  =["소분류","장학관","연구관","장학사","연구사"];
        var cate_low_129  =["소분류","1급","2급","3급","4급","5급","6급","7급","8급","9급","10급"];
        var cate_low_12A  =["소분류","국회의원","광역단체장","광역의원","기초단체장","기초단체의원"];
        var cate_low_121v =["","12111","12112","12113","12114","12115","12116","12117","12118","12119","1212A"];
        var cate_low_122v =["","12211","12212","12213","12214","12215","12216","12217","12218","12219","1222A"];
        var cate_low_123v =["","12311","12312","12313","12314","12315"];
        var cate_low_124v =["","12411","12412","12413","12414","12415","12416","12417","12418","12419","1242A"];
        var cate_low_125v =["","12511","12512","12513","12514","12515","12516","12517","12518","12519","1252A"];
        var cate_low_126v =["","12611","12612","12613","12614","12615","12616","12617","12618","12619","1262A"];
        var cate_low_127v =["","12711","12712","12713","12714","12715","12716","12717","12718","12719","1272A"];
        var cate_low_128v =["","12811","12812","12813","12814"];
        var cate_low_129v =["","12911","12912","12913","12914","12915","12916","12917","12918","12919","1292A"];
        var cate_low_12Av =["","12A11","12A12","12A13","12A14","12A15"];
        var cate_low_131  =["소분류","변호사","법무사","변리사","공인노무사","손해사정인"];
        var cate_low_132  =["소분류","의사","한의사","수의사","약사"];
        var cate_low_133  =["소분류","공인회계사","세무사","관세사"];
        var cate_low_134  =["소분류","기술사","건축사"];
        var cate_low_135  =["소분류","감정평가사"];
        var cate_low_136  =["소분류","기장","부기장"];
        var cate_low_131v =["","13111","13112","13113","13114","13115"];
        var cate_low_132v =["","13211","13212","13213","13214"];
        var cate_low_133v =["","13311","13312","13313"];
        var cate_low_134v =["","13411","13412"];
        var cate_low_135v =["","13511"];
        var cate_low_136v =["","13611","13612"];
        var cate_low_141  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","임시/파견","계약","특수직해당","임시/파견","계약"];
        var cate_low_142  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","임시/파견","계약","특수직해당","임시/파견","계약"];
        var cate_low_143  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","임시/파견","계약","특수직해당","아마운동선수","예술단원","도서관원","미술관원","공원관리","기타","임시/파견","계약"];
        var cate_low_144  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","임시/파견","계약","특수직해당","임시/파견","계약"];
        var cate_low_141v =["","14111","14112","14113","14114","14115","14116","14117","14118","14119","1411A","14121","14129","1412A"];
        var cate_low_142v =["","14211","14212","14213","14214","14215","14216","14217","14218","14219","1421A","14221","14229","1422A"];
        var cate_low_143v =["","14311","14312","14313","14314","14315","14316","14317","14318","14319","1431A","14321","14322","14323","14324","14325","14326","14327","14329","1432A"];
        var cate_low_144v =["","14411","14412","14413","14414","14415","14416","14417","14418","14419","1441A","14421","14429","1442A"];
        var cate_low_151  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","총장","학장","정교수","부교수","조교수","전임강사","시간강사"];
        var cate_low_152  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","총장","학장","정교수","부교수","조교수","전임강사","시간강사"];
        var cate_low_153  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","교장","교감","교사","기간제교사","보조/실험교사","서무과장","서무과직원"];
        var cate_low_154  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","교장","교감","교사","병설유치원교사","전임강사","시간강사"];
        var cate_low_155  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","원장","부원장","주임강사","강사","보모"];
        var cate_low_156  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","원장","부원장","주임강사","강사","시간강사"];
        var cate_low_151v =["","15111","15112","15113","15114","15115","15116","15117","15118","15121","15122","15123","15124","15125","15126","15127"];
        var cate_low_152v =["","15211","15212","15213","15214","15215","15216","15217","15218","15221","15222","15223","15224","15225","15226","15227"];
        var cate_low_153v =["","15311","15312","15313","15314","15315","15316","15317","15318","15321","15322","15323","15324","15325","15326","15327"];
        var cate_low_154v =["","15411","15412","15413","15414","15415","15416","15417","15418","15421","15422","15423","15424","15425","15426"];
        var cate_low_155v =["","15511","15512","15513","15514","15515","15516","15517","15518","15521","15522","15523","15524","15525"];
        var cate_low_156v =["","15611","15612","15613","15614","15615","15616","15617","15618","15621","15622","15623","15624","15625"];
        var cate_low_161  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","인턴","간호과장","이상","수간호사","간호사","간호조무사","의료기사/물리치료사"];
        var cate_low_162  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","인턴","간호과장","이상","수간호사","간호사","간호조무사","의료기사/물리치료사"];
        var cate_low_163  =["소분류","대표이사","임원","부장","차장","과장","대리","주임","사원","인턴","간호과장","이상","수간호사","간호사","간호조무사","의료기사/물리치료사"];
        var cate_low_161v =["","16111","16112","16113","16114","16116","16116","16117","16118","16121","16131","16132","16133","16134","16141"];
        var cate_low_162v =["","16211","16212","16213","16214","16216","16216","16217","16218","16221","16231","16232","16233","16234","16241"];
        var cate_low_163v =["","16311","16312","16313","16314","16316","16316","16317","16318","16321","16331","16332","16333","16334","16341"];
        var target = document.getElementById("cd_worker_position");
        if(val != '') {
            target.options.length = 0;
            var d = eval("cate_low_" + val);
            var k = eval("cate_low_" + val + "v");
            for (x in d) {
                var opt = document.createElement("option");
                opt.value = k[x];
                //debug(opt.value);
                opt.innerHTML = d[x];
                target.appendChild(opt);
            }
            $('#cd_worker_position').selectpicker('refresh');
        } else {
            target.options.length = 1;
        }
    }
    function show2ndDepth() {
        $('#worker_position').show();
        $('#employee_type').show();
        $('#dt_join_view').show();
    }
    function hide2ndDepth() {
        $('#worker_position').hide();
        $('#employee_type').hide();
        $('#dt_join_view').hide();
    }
    <c:if test="${site eq 'LOCAL'}">
    /**
     * 로컬 개발용 함수
     */
    function testOnload() {
        $("#cd_loan_use").val("01");
        $("#cd_house_type").val("1");
        $("#cd_live_type_home").val("1");
        enableBottom('true');
        affixBottom('show');
        /**
         * 검색화면에서 선택한 업체명 가져오기
         */
        nm_comp                = getCookie('nm_comp'               );
        no_biz_comp            = getCookie('no_biz_comp'           );
        kiscode                = getCookie('kiscode'               );
        cd_occupational        = getCookie('cd_occupational'       );
        cd_occupational_detail = getCookie('cd_occupational_detail');
        cd_worker_position     = getCookie('cd_worker_position'    );
        cd_employee_type       = getCookie('cd_employee_type'      );
        jb_dt_join_view        = getCookie('jb_dt_join_view'       );
        amt_year_income        = getCookie('amt_year_income'       );
        isInit2                = getCookie('isInit2'               );
        if(nm_comp                == '') { nm_comp                = '공무원'; }
        if(no_biz_comp            == '') { no_biz_comp            = '12121212'; }
        if(kiscode                == '') { kiscode                = '1'; }
        if(cd_occupational        == '') { cd_occupational        = '2'; }
        if(cd_occupational_detail == '') { cd_occupational_detail = '121'; }
        if(cd_worker_position     == '') { cd_worker_position     = '12111'; }
        if(cd_employee_type       == '') { cd_employee_type       = '1'; }
        if(jb_dt_join_view        == '') { jb_dt_join_view        = '2002-01'; }
        if(amt_year_income        == '') { amt_year_income        = '6000000'; }

        setCookie('nm_comp'               ,nm_comp                );
        setCookie('no_biz_comp'           ,no_biz_comp            );
        setCookie('kiscode'               ,kiscode                );
        setCookie('cd_occupational'       ,cd_occupational        );
        setCookie('cd_occupational_detail',cd_occupational_detail );
        setCookie('cd_worker_position'    ,cd_worker_position     );
        setCookie('cd_employee_type'      ,cd_employee_type       );
        setCookie('jb_dt_join_view'       ,jb_dt_join_view        );
        setCookie('amt_year_income'       ,amt_year_income        );
        setCookie('isInit2'               ,isInit2                );
    }
    </c:if>
</script>

</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<a type="button" class="ui-nav nav-back" href="#" onclick="history.go(-1);">뒤로가기</a>
			</div>
			<h1>직장/소득정보</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="container">
			<div class="lead">
				<p>직장/소득정보를 입력해주세요.</p>
			</div>	
			<form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
			
			<input type="hidden" name="nm_person" id="nm_person" value="${txFcTransmitVO.nm_person}"/>
			<input type="hidden" name="no_person" id="no_person" value="${txFcTransmitVO.no_person}"/>
			<input type="hidden" name="ssn_person" id="ssn_person" value="${txFcTransmitVO.ssn_person }"/>
			<input type="hidden" name="cd_goods" id="cd_goods" value="${txFcTransmitVO.cd_goods }"/>
			<input type="hidden" name="cd_fc" id="cd_fc" value="${txFcTransmitVO.cd_fc }"/>
			<input type="hidden" name="no_bunch" id="no_bunch" value="${txFcTransmitVO.no_bunch}"/>
			<input type="hidden" name="kcb_di" id="kcb_di" value="${txFcTransmitVO.kcb_di }"/>
			<input type="hidden" name="no_biz_comp" id="no_biz_comp" value=""/><%--사업자번호--%>
			<input type="hidden" name="kiscode" id="kiscode" value=""/>
            <input type="hidden" name="hp" id="hp" value="${txFcTransmitVO.hp1 }"/>
			<div class="form-inline">
				<div class="form-group">
					<label for="cd_occupational">직군</label>
					<select class="selectpicker" data-header="직군선택" name="cd_occupational" id="cd_occupational">
						${ufn:makeOptions("cd_occupational", "직군선택", "")}
					</select>
				</div>
				<div class="form-group has-feedback" id="jobSearch">
					<label for="nm_comp">직장명</label>
					<input type="text" class="form-control" name="nm_comp" id="nm_comp" placeholder="직장명을 검색하세요" onfocus="searchJob();" value="" readonly="readonly">
		            <button type="button" class="sch-btn" id="btnNm_comp"><span class="form-control-feedback btn-search">검색</span></button>
				</div>
				<div class="form-group has-feedback" id="jobDetail" style="display: none">
					<label for="cd_occupational_detail">상세직군</label>
					<select class="selectpicker" data-header="직군상세선택" name="cd_occupational_detail" id="cd_occupational_detail">
					</select>
				</div>
                <div class="form-group" id="worker_position" style="display: none">
                    <label for="cd_worker_position">직위</label>
                    <select class="selectpicker" data-header="직위선택" name="cd_worker_position" id="cd_worker_position" >
                    </select>
                </div>
                <div class="form-group" id="employee_type" style="display: none">
                    <label for="cd_employee_type">고용형태</label>
                    <select class="selectpicker" data-header="고용형태선택" name="cd_employee_type" id="cd_employee_type" >
                        ${ufn:makeOptions("cd_employee_type", "고용형태선택", "")}
                    </select>
                </div>
                <div class="form-group" id="dt_join_view" style="display: none">
                    <label for="jb_dt_join_view">입사년월</label>
                    <input type="month" name="jb_dt_join_view" id="jb_dt_join_view" class="form-control slt-date" placeholder=""/>
                    <input type="hidden" name="jb_dt_join" id="jb_dt_join" class="form-control slt-date" placeholder="" />
                </div>
				<div class="form-group">
					<label for="amt_year_income">연소득</label>
					<input type="number" class="form-control" name="amt_year_income" id="amt_year_income" maxlength="6" autocomplete="off" value=""/>
					<span class="form-control-feedback" aria-hidden="true">만원</span>
				</div>
			</div>
			</form>
		</div>
		<div class="btn-fixed-bottom" id="confirmed_div">
			<a role="button" class="btn btn-lg btn-primary btn-block" onclick="loanApplyStepStep();">확인</a>
		</div>
	</section>
	<!-- //Content -->
</div>

</body>
</html>
