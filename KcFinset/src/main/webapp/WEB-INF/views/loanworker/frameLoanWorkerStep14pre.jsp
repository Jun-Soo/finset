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
            if(userAgent == "Android") {
                window.Android.setEndApp('Y');
            }
            <%--alert('no_bunch=${txFcTransmitVO.no_bunch}\n이전화면=${referer}');--%>
            <c:if test="${referer ne null}">
            setTimeout(function() {
                reqFinanceInfo();
            },1000);
            </c:if>
        });
        /**
         * 한도조회
         */
        function reqFinanceInfo() {
            // alert("reqFinanceInfo");
            var data = $("#frmloanApplyStep").serialize();
            <c:if test="${site ne 'REAL'}">
            alert("reqFinanceInfo:data="+data);
            </c:if>
            if(data == null) return false;
            var vTimeout = 5*60*1000;
            $.ajax({
                url : "<c:url value='/m/loanworker/reqFinanceInfo.json'/>",
                data : data,
                contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                type : "POST",
                async : true,
                success : function (result) {
                    // debug("insertTxFc:result.result="+result.result+",result.no_bunch="+result.no_bunch);
                    // alert("result.result="+result.result
                    //     +"\nmessage="+result.message
                    //     +"\nerrorMsg="+result.errorMsg
                    //     +"\nresult.isSuccess="+result.isSuccess);
                    var isSuccess = result.isSuccess;
                    var message   = result.message  ;

                    if(isSuccess == 'false' || message != '') {
                        <c:if test="${site ne 'REAL'}">
                        alert(message);
                        toastMsg(message);
                        </c:if>
                    }
                },
                error : function (e) {
                    errMsg(e);
                    // setTimeout(function() {
                    //     history.back();
                    // },1500);
                }
            });
            <c:if test="${site ne 'REAL'}">
                alert("한도조회 신청하였습니다.");
                toastMsg("한도조회 신청하였습니다.");
            </c:if>
            <c:if test="${site eq 'REAL'}">
                toastMsg("한도조회 신청하였습니다.");
            </c:if>
             setTimeout(function() {
                 location.href = "/m/loanworker/frameLoanWorkerStep1.crz";
             },1500);
        }
    </script>
</head>
<body>
<div class="progress-wrap scraping show">
	<div class="loader">
		<svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="40px" height="40px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">
			<path fill="#2b43ba" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
				<animateTransform attributeType="xml" attributeName="transform" type="rotate" from="0 25 25" to="360 25 25" dur="0.6s" repeatCount="indefinite"/>
			</path>
		</svg>
	</div>
	<div class="container progress-txt">
		<div class="lead">
			<p>선택하신 상품의 금리/한도를<br>조회하고 있습니다.</p>
			<small>(최대 5분 소요)</small>
		</div>
	</div>
    <form name="frmloanApplyStep" id="frmloanApplyStep" method="post" validate="remove_tag;">
        <input type="hidden" name="nm_person"              id="nm_person"               value="${txFcTransmitVO.nm_person}"/>
        <input type="hidden" name="no_person"              id="no_person"               value="${txFcTransmitVO.no_person}"/>
        <input type="hidden" name="ssn_person"             id="ssn_person"              value="${txFcTransmitVO.ssn_person }"/>
        <input type="hidden" name="cd_goods"               id="cd_goods"                value="${txFcTransmitVO.cd_goods }"/>
        <input type="hidden" name="cd_fc"                  id="cd_fc"                   value="${txFcTransmitVO.cd_fc }"/>
        <input type="hidden" name="no_bunch"               id="no_bunch"                value="${txFcTransmitVO.no_bunch}"/>
        <input type="hidden" name="kcb_di"                 id="kcb_di"                  value="${txFcTransmitVO.kcb_di }"/>
        <input type="hidden" name="no_biz_comp"            id="no_biz_comp"             value="${txFcTransmitVO.no_biz_comp }"/><%--사업자번호--%>
        <input type="hidden" name="hp"                     id="hp"                      value="${txFcTransmitVO.hp1 }"/>
        <input type="hidden" name="cd_occupational"        id="cd_occupational"         value="${txFcTransmitVO.cd_occupational }"/>
        <input type="hidden" name="nm_comp"                id="nm_comp"                 value="${txFcTransmitVO.nm_comp }"/>
        <input type="hidden" name="cd_occupational_detail" id="cd_occupational_detail"  value="${txFcTransmitVO.cd_occupational_detail }"/>
        <input type="hidden" name="cd_worker_position"     id="cd_worker_position"      value="${txFcTransmitVO.cd_worker_position }"/>
        <input type="hidden" name="cd_employee_type"       id="cd_employee_type"        value="${txFcTransmitVO.cd_employee_type }"/>
        <input type="hidden" name="jb_dt_join"             id="jb_dt_join"              value="${txFcTransmitVO.jb_dt_join }"/>
        <input type="hidden" name="amt_year_income"        id="amt_year_income"         value="${txFcTransmitVO.amt_year_income }"/>
        <input type="hidden" name="errorMsg"               id="errorMsg"                value=""/>
        <input type="hidden" name="cd_duty_comp"           id="cd_duty_comp"            value="${txFcTransmitVO.cd_duty_comp }"/>
    </form>
</div>
</body>
</html>
