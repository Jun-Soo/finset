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
/*		var errorMsg = "${errorMsg}";
		if(errorMsg != undefined && errorMsg != ""){
			toastMsg(errorMsg);
			setTimeout(function(){
				goMain();
			}, 2000);

		}
*/
		if(userAgent == "Android") {
			window.Android.setBackKeyUse('N');
		}

	});

	function loanApplyStepStep() {

		frmloanApplyStep.action = "<c:url value='/m/loan/frameLoanIncomeSuccessAfter.crz'/>";
		frmloanApplyStep.submit();
	}
    function inCome() {
        var cd_goods;
        var cd_fc;
        var no_prepare;
        var amt_limit;
        var cnt = 0;
        $("#listGoods").find("div[id=loan_product]").each(function(i, item){
            if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
                cnt++;

                cd_goods    = $(this).find("input[name='list_cd_goods']").val();
                cd_fc       = $(this).find("input[name='list_cd_fc']").val();
                no_prepare 	= $(this).find("input[name='list_no_prepare']").val();
                amt_limit 	= $(this).find("input[name='list_amt_limit']").val();
            }
        });

        if(cnt > 1){
            $('#next_div').addClass("btn-fixed-bottom");
            toastMsg("대출은 한건만 신청할 수 있습니다.");
            return false;
        }

        //상품코드, 금융사 코드 값 체크
        if(cd_goods == null && cd_fc == null) {
            toastMsg('대출상품을 선택해주세요.');
            return false;
        } else {
            frmloanApplyStep.cd_goods.value 	= cd_goods;
            frmloanApplyStep.cd_fc.value 			= cd_fc;
            frmloanApplyStep.amt_limit.value 	= amt_limit;
            frmloanApplyStep.no_prepare.value = no_prepare;
        }
        var data = $("#frmloanApplyStep").serialize();
        <c:if test="${site ne 'REAL'}">
        alert("inCome:data="+data);
        </c:if>
        if(data == null) return false;
        $.ajax({
            url : "<c:url value='/m/loan/frameLoanIncomeSuccess.json'/>",
            data : data,
            contentType : "application/x-www-form-urlencoded; charset=UTF-8",
            type : "POST",
            async : true,
            success : function (result) {
                <c:if test="${site ne 'REAL'}">
                    alert("inCome:result.message="+result.message+"\nresult.isSuccess="+result.isSuccess);
                </c:if>
                loanApplyStepStep();
            },
            error : function (e) {
                toastMsg("서비스 오류입니다.");
                setTimeout(function() {
                    history.back();
                },1500);
            }
        });
    }
	function goMain(){
		frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanWorkerStep1.crz'/>";
		frmloanApplyStep.submit();
	}
	function goodsChk(e){
        var cd_goods;
		var cd_fc;
		var cnt = 0;

		$("#listGoods").find("div[id=loan_product]").each(function(i, item){
			if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
				cnt++;
			}
		});

		if(cnt > 1){
			$('#next_div').addClass("btn-fixed-bottom");
			toastMsg("대출은 한건만 신청할 수 있습니다.");
			if(e != undefined) {
			    e.checked = false;
            }
		}
	}

	function chgListGoodsSort(chgSort){

		//alert(chgSort.value);
		frmloanApplyStep.sel_order_col.value 	= chgSort.value;
// 		if( chgSort.value == "01" ){
// 			frmloanApplyStep.sel_order_col.value 	= "RTO_LOAN";
// 			frmloanApplyStep.sel_order_asc.value 	= "DESC";
// 		}else if( chgSort.value == "02" ){
// 			frmloanApplyStep.sel_order_col.value 	= "AMT_LIMIT";
// 			frmloanApplyStep.sel_order_asc.value 	= "DESC";
// 		}

		frmloanApplyStep.action = "<c:url value='/m/loanworker/frameLoanGoodsSortList.crz'/>"
		frmloanApplyStep.submit();
	}

	function chgYearToMonth(year){
		if(year  == 1){
			return "12";
		}else if(e.value  == 2){
			return "24";
		}else if(e.value  == 3){
			return "36";
		}else if(e.value  == 4){
			return "48";
		}else if(e.value  == 5){
			return "60";
		}
	}
    /*
    * name : goodsFavoriteDetail
    * desc : 관심상품 상세 조회
    * param : cd_fc
    * param : cd_goods
    * output :
    */
    function goodsFavoriteDetail( cd_fc, cd_goods ) {
        frmloanApplyStep.cd_fc.value = cd_fc;
        frmloanApplyStep.cd_goods.value = cd_goods;
        frmloanApplyStep.action = "<c:url value='/m/customercenter/frameCustomerViewResultsDetail.crz'/>";
        frmloanApplyStep.submit();
    }

	function goGoodsMain(){
		frmloanApplyStep.action = "<c:url value='/m/credit/frameCreditInfoMain.crz'/>";
		frmloanApplyStep.submit();
	}
</script>

</head>
<body>
<form name="frmloanApplyStep" id="frmloanApplyStep"  method="post">
<input type="hidden" name="loan_code" value="${loan_code }">
<input type="hidden" name="no_bunch" id="no_bunch" value="${no_bunch}"/>
<input type="hidden" name="title" id="title"    />
<input type="hidden" id="cd_goods"   name="cd_goods"  />
<input type="hidden" id="cd_fc"      name="cd_fc"     />
<input type="hidden" id="no_prepare" name="no_prepare"/>
<input type="hidden" id="amt_limit"  name="amt_limit" />
<input type="hidden" id="sel_order_col"  name="sel_order_col" />
<input type="hidden" id="sel_order_asc"  name="sel_order_asc" />
</form>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn blind">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>조회결과</h1>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
	 	<div class="affix-fixed top-fixed-item">
	 		<div class="container-fluid collapse in top-block" id="collapseExample">
				<div class="alert-desc">
					<em>대출 신청은 하나의 상품만 선택해 신청 가능합니다.</em>
					<p>대출신청 후 신용등급 변동, 입력정보 오류 등으로 인해 금리/한도의 변동되거나 대출이 불가능 할  수 있습니다.</p>
					<button data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" class="btn-close"><i class="icon-close">레이어 닫기</i></button>
				</div>
			</div>
			<div class="sort-block">
				<div class="ctrl-item" >
					<div class="result-item">
						<div class="result-txt">
							<p class="prd-ea">총 <em>${count}</em>개의 상품</p>
						</div>
						<%--<select class="selectbox" id="orderby">--%>
							<%--<option value="01" <c:out value="${sort eq '01' ? 'selected' : ''}"/>>금리낮은순</option>--%>
							<%--<option value="02" <c:out value="${sort eq '02' ? 'selected' : ''}"/>>한도높은순</option>--%>
							<%--<option value="03">기간높은순</option>--%>
						<%--</select>--%>
					</div>
				</div>
			</div>
	 	</div>
		<c:choose>
			<c:when test="${count eq '0' }">
				<div class="data-none">
					<p>대출 조회 내역이 없습니다.</p>
				</div>
			</c:when>
            <c:otherwise>
                <div id="listGoods">
                    <c:forEach var="List" items="${list}" varStatus="status">

                        <div class="list-block">
                            <h2 class="h2 h-date">
                                <fmt:parseDate var="dtReceive" value="${List.dt_receive}" pattern="yyyyMMdd" />
                                <fmt:formatDate value="${dtReceive}" pattern="yyyy.MM.dd" />
                            </h2>
                            <div class="container-fluid prd-loan prd-affiliates" id="loan_product">

                                <input type="hidden" name="cd_fc_each" value="${List.cd_fc}"/>
                                <input type="hidden" name="cd_goods_each" value="${List.cd_goods}"/>
                                <input type="hidden" name = "list_cd_goods"    value="${List.cd_goods}" />
                                <input type="hidden" name = "list_cd_fc"           value="${List.cd_fc}" />
                                <input type="hidden" name = "list_no_prepare" value="${List.no_prepare}" />
                                <input type="hidden" name = "list_amt_limit"     value="${List.amt_limit}" />
                                <input type="hidden" name = "list_no_bunch"     value="${List.no_bunch}" />
                                <input type="hidden" name = "list_cd_goods_gubun"     value="${List.cd_goods_gubun}" />

                                <a href="#url">
                                    <div class="ctrl-item">
                                        <div class="checkbox chk-square">
                                            <c:choose>
                                                <c:when test="${List.yn_loan eq 'Y' and List.apply_cnt eq 0}"> <!-- 선택가능(신청가능) -->
                                                    <input type="checkbox" name="goods_choice" id="s${status.count}" onclick="goodsChk(this)"><label class="" for="s${status.count}"></label>
                                                </c:when>
                                                <c:otherwise> <!-- 선택불가능 -->
                                                    <input type="checkbox" name="goods_choice" id="s${status.count}" disabled="disabled"><label class="" for="s${status.count}"></label>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="list-heading" onclick="goodsFavoriteDetail('${List.cd_fc}','${List.cd_goods}');">
                                        <c:choose>
                                            <c:when test="${List.yn_loan eq 'Y' and List.apply_cnt eq 0}">
                                                <span class="label label-status status-blue">신청가능</span>
                                            </c:when>
                                            <c:when test="${List.yn_loan eq 'N' and List.yn_receive eq 'Y'}">
                                                <span class="label label-status status-gray">신청불가능</span>
                                            </c:when>
                                            <c:when test="${List.yn_loan eq 'Y' and List.apply_cnt gt 0}">
                                                <span class="label label-status status-green">신청완료</span>
                                            </c:when>
                                            <c:when test="${empty List.yn_loan}">
                                                <span class="label label-status status-red">조회오류</span>
                                            </c:when>
                                        </c:choose>
                                        <li class="bank-title">
                                            <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${List.cd_fc}');"></span>
                                                ${ufn:getNmFc(List.cd_fc)}
                                        </li>
                                        <h2 class=" prd-title">${List.nm_goods}</h2>
                                    </div>
                                    <div class="list-info">
                                        <dl>
                                            <dt>대출한도</dt>
                                            <dd>${ufn:formatNumberMan(List.amt_limit)} 만원</dd>
                                        </dl>
                                        <dl onclick="goodsFavoriteDetail('${List.cd_fc}','${List.cd_goods}');">
                                            <dt>대출금리</dt>
                                            <dd class="txt-point">
                                                <c:set var="cd_type_interest" value="${List.cd_type_interest}" />
                                                <c:if test="${List.cd_type_interest.length() gt '2'}">
                                                    <c:set var="cd_type_interest" value="변동,고정" />
                                                    <label>${cd_type_interest}</label>
                                                </c:if>
                                                <c:if test="${List.cd_type_interest.length() eq '2'}">
                                                    <c:set var="cd_type_interest" value="${ufn:getCodeName('cd_ratio_type', List.cd_type_interest)}" />
                                                    <label>${cd_type_interest}</label>
                                                </c:if>
                                                    ${List.rto_loan} %</dd>
                                        </dl>
                                        <dl>
                                            <dt>대출기간</dt>
                                            <dd>${List.year_term * 12} 개월</dd>
                                        </dl>
                                        <dl>
                                            <dt>상환방식</dt>
                                            <dd>
                                                <c:set var="cdTypePay" value="${fn:split(List.cd_type_pay,',')}" />
                                                <c:forEach var="typePay" items="${cdTypePay}" varStatus="i">
                                                    <c:if test="${i.current > 1}">
                                                        , &nbsp;
                                                    </c:if>
                                                    ${ufn:getCodeName("cd_type_pay",typePay)}상환
                                                </c:forEach>
                                            </dd>
                                        </dl>
                                    </div>
                                        <%-- <div class="loan-btn">
                                            <div class="checkbox ico-loan ico-choice">
                                                <input type="checkbox" name="goods_choice" id="s${status.count}" onclick="goodsChk(this)"><label class="" for="s${status.count}"></label>
                                            </div>
                                        </div> --%>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div> <!-- div listGoods -->
            </c:otherwise>
		</c:choose>
		<div class="btn-fixed-bottom  affix-bottom" id="next_div">
			<div class="col-xs-6">
				<button type="button" class="btn btn-lg btn-default btn-block" onclick="goMain();">홈으로</button>
			</div>
            <c:if test="${count ne '0' }">
                <div class="col-xs-6">
                    <a role="button" class="btn btn-lg btn-primary btn-block" onclick="inCome();">신청하기</a>
                </div>
            </c:if>
            <c:if test="${count eq '0' }">
                <div class="col-xs-6">
                    <a role="button" class="btn btn-lg btn-disabled btn-block">신청하기</a>
                </div>
            </c:if>
		</div>
	</section>
	<!-- //Content -->
</div>
</body>
</html>
