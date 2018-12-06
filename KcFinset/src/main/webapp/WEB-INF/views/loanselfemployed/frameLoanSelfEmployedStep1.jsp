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
        var pageCnt = 1;
        var curTab = '';
        $(window).scroll(function(){
            if($(document).height() <= $(window).scrollTop() + $(window).height()){
                if(totalPage > pageCnt){
                    jumpPage();
                }
                pageCnt++;
            }
        });
        $(document).ready(function() {
            window.setupValidateForm( frmLoanGoodsList );
            var totalPage = "${pagedList.pageCount}";

            $("#page").val(1);

            if(userAgent == "Android") {
                window.Android.backKeySendUrl("/m/loanworker/frameLoanWorkerStep1.crz");
            }
            var cookeyCurTab = getCookeyCurTab();
            addPseudoBar(cookeyCurTab);

            $("#li"+cookeyCurTab).click();
            $("#allcheck").on("change",function () {
                var checked = $(this).is(":checked");
                $("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
                    var disabled = $(this).find("input:checkbox[name='goods_choice']").prop("disabled");
                    if (disabled != true) {
                        $(this).find("input:checkbox[name='goods_choice']").prop("checked",checked);
                    }
                });
                // $("#listLoanGoods").find("input:checkbox[name='goods_choice']").prop("checked",checked);
                setCheckedHtml();
                buttonClassRemove();
            });
            $("#orderby").on("change",function(){
                var curTab = $("#_curTab").val();
                loadGoodsTab(curTab);
            });
        });
        function setListCount(count){
            $("#list_count").html(count);
        }

        var getCookeyCurTab = function() {
            var curTab = getCookie(LOANSELF_CUR_TAB);
            if(curTab == undefined || curTab == '') {
                curTab = "bank";
                setCookie(LOANSELF_CUR_TAB,curTab);
            }
            return curTab;
        }

        //리스트 조회
        function goSearch() {
            jumpPage();
        }

        // 페이지 이동
        function jumpPage(){
            var pageIndex = Number($("#page").val());
            $("#page").val(pageIndex + 1);
            loadGoodsTab(curTab);
        }

        function loanGoodsDetail(cd_fc, cd_goods){
            frmLoanGoodsList.cd_fc.value = cd_fc;
            frmLoanGoodsList.cd_goods.value = cd_goods;
            frmLoanGoodsList.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep2.crz'/>";
            frmLoanGoodsList.submit();
        }

        function loanGoodsBankDetail(cd_fc, cd_non_goods){
            frmLoanGoodsList.cd_fc.value = cd_fc;
            frmLoanGoodsList.cd_goods.value = cd_non_goods;
            frmLoanGoodsList.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep2Bank.crz'/>";
            frmLoanGoodsList.submit();
        }

        function loanGoodsChoice(cd_fc, cd_goods, id, yn_alliance){
            var data = {"cd_fc":cd_fc, "cd_goods":cd_goods, "yn_alliance":yn_alliance};
            if(data == null) return false;
            var chkZzim = $('#'+id).is(":checked");

            if(chkZzim == true){
//         		alert("체크박스 체크");
                $.ajax({
                    url : "<c:url value='/m/loan/insertLoanGoodsChoice.json'/>",
                    data : data,
                    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                    type : "POST",
                    async : false,
                    success : function (result) {
                        var returnData = result.returnData;
//     					alert(returnData.message);
                    },
                    error : function (e) {
                        errMsg(e);
                    }
                });
            }else if(chkZzim == false){
//         		alert("체크박스 체크 해제!");
                $.ajax({
                    url : "<c:url value='/m/loan/deleteLoanGoodsChoice.json'/>",
                    data : data,
                    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                    type : "POST",
                    async : false,
                    success : function (result) {
                        var returnData = result.returnData;
                    },
                    error : function (e) {
                        errMsg(e);
                    }
                });
            }

        }

        function loanWorkerNextStep(){
            if($("input:checkbox[name='goods_choice']:checked").length == 0){
                toastMsg('선택된 대출 상품이 없습니다.');
            } else {
                var cd_goods = '';
                var cd_fc = '';

                $("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
                    if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
                        cd_goods += $(this).find("input[name='cd_goods_each']").val()+",";
                        cd_fc += $(this).find("input[name='cd_fc_each']").val()+",";
                    }
                });
                cd_goods = cd_goods.slice(0, -1);
                cd_fc = cd_fc.slice(0, -1);

                frmLoanGoodsList.cd_goods.value = cd_goods;
                frmLoanGoodsList.cd_fc.value = cd_fc;

                frmLoanGoodsList.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep4.crz'/>";
                frmLoanGoodsList.submit();

            }
        }
        function setCheckedHtml() {
            var checkedCount =getCheckedCount();
            var htmlText = "("+checkedCount+"개)";
            $("#checked_count").html(htmlText);
        }
        function getCheckedCount(){
            var count = 0;
            $("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
                if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
                    count++;
                }
            });
            return count;
        }

        function buttonClassRemove() {
            if($("input:checkbox[name='goods_choice']:checked").length > 0){
                enableBottom('true');
            } else {
                enableBottom('false');
            }
        }

        function goCondition(){
            frmLoanGoodsList.action = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep3.crz'/>?";
            frmLoanGoodsList.submit();
        }

        function loadGoodsTab(type) {
            if(type == undefined || type == '') {
                type = 'bank';
            }
            var curTab = $("#_curTab").val();
            if(curTab != type) {
                curTab = type;
                $("#page").val(1);
                $("#listLoanGoods").html("");
            }
            setCookie(LOANSELF_CUR_TAB,type);
            $("#_curTab").val(type);
            // affixBottom('hide');
            if(curTab == 'affiliates') {
                $("#src_menu").hide();
            } else {
                $("#src_menu").show();
            }
            if("bank" == type){
                $("#cd_fin").val('B');
            } else if("savingBank" == type){
                $("#cd_fin").val('S');
            } else if("capital" == type){
                $("#cd_fin").val('C');
            } else if("card" == type){
                $("#cd_fin").val('D');
            }

            if(curTab == 'affiliates' || curTab == '') {
                affixBottom('show');
            } else {
                affixBottom('hide');
            }

            var data = $("#frmLoanGoodsList").serialize();
            if(data == null) return false;
            if(userAgent == "Android") {
                window.Android.loding('Y');
            }
            else if(userAgent == "iOS") {
                Jockey.send("showLoading");
            }
            var orderby = $("#orderby").val();
            if("affiliates" == type){
                vLoad("listLoanGoods","<c:url value='/m/loanselfemployed/listLoanAffiliates.crz'/>?orderby="+orderby,data);
            } else {
                vLoad("listLoanGoods","<c:url value='/m/loanselfemployed/listLoanNoAffiliates.crz'/>?orderby="+orderby,data);
            }
            var page = $("#page").val();
            if(page == 1) {
                $(document).scrollTop(0);
            }
        }

        // jquery load 재구현(한글깨짐대응)
        function vLoad(id,uri,data) {
            vLoad(id,uri,data,true);
        }
        function vLoad(id,uri,data,sync) {
            $("#divTop").hide();
            $.ajax({
                url : uri,
                data : data,
                async: sync,
                contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                type : "POST",
                success : function (result) {
                    $("#divTop").show();
                    var expr = new RegExp('>[ \t\r\n\v\f]*<', 'g');
                    result = result.replace(expr, '> <');
                    var page = $("#page").val();
                    if(page != 1) {
                        $("#"+id).append(result);
                    } else {
                        $("#"+id).html(result);
                    }
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

        function goGoodsMain(){
            frmLoanGoodsList.action = "<c:url value='/m/loanworker/frameLoanWorkerStep1.crz'/>";
            frmLoanGoodsList.submit();
        }
    </script>

</head>
<body>
<div id="wrapper">
    <form method="post" name="frmLoanGoodsList" id="frmLoanGoodsList">
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="totalPage" id="totalPage" value ="${pagedList.pageCount}" />
        <input type="hidden" name="cd_goods" id="cd_goods"/>
        <input type="hidden" name="cd_fc" id="cd_fc"/>
        <input type="hidden" name="cd_goods_class_l" id="cd_goods_class_l" value="01">
        <input type="hidden" name="cd_goods_class_m" id="cd_goods_class_m" value="02,03,08,09">
        <input type="hidden" name="cd_fin" id="cd_fin" value="">
        <input type="hidden" name="_curTab" id="_curTab" value="${curTab}">

        <!-- Header -->
        <header id="header">
            <div class="input-group">
                <div class="input-group-btn">
                    <button type="button" class="ui-nav nav-back" onclick="history.go(-1);">뒤로가기</button>
                </div>
                <h1>신용대출(개인사업자)</h1>
                <div class="g-menu" id="src_menu">
                    <button type="button" class="btn btn-gmenu" onclick="goCondition();">조건선택</button>
                </div>
            </div>
        </header>
        <!-- Content -->
        <section id="content">
        	<div class="affix-fixed top-fixed-item">
	            <ul class="nav nav-outline nav-justified tabs">
	                <!-- <li onclick="loadGoodsTab('affiliates');"><a id='liaffiliates' href="#">제휴사</a></li> -->
	                <li onclick="loadGoodsTab('bank'      );"><a id='libank'       href="#">은행</a></li>
	                <li onclick="loadGoodsTab('savingBank');"><a id='lisavingBank' href="#">저축은행</a></li>
	                <li onclick="loadGoodsTab('capital'   );"><a id='licapital'    href="#">캐피탈/카드</a></li>
	            </ul>
	            <div class="sort-block" id="divTop">
	                <div class="ctrl-item" >
	                    <div class="checkbox chk-square" id="divAllCheck">
	                        <label><input type="checkbox" id="allcheck"> 선택 <em id="checked_count">(0개)</em></label>
	                    </div>
	                    <div class="result-item">
	                        <div class="result-txt">
	                            <p class="prd-ea">총 <em id="list_count" name="list_count">2</em>개의 상품</p>
	                        </div>
	                        <select class="selectbox" id="orderby">
	                            <option value="01">금리낮은순</option>
	                            <option value="02">한도높은순</option>
	                            <option value="03">기간높은순</option>
	                        </select>
	                    </div>
	                </div>
	            </div>
        	</div>
            <!-- Content -->
            <div id="listLoanGoods">
                <%--<%@ include file="/WEB-INF/views/loanselfemployed/sub/listLoanAffiliates.jsp"%>--%>
            </div>
            <div class="btn-fixed-bottom affix-bottom" id="next_div">
                <a role="button" class="btn btn-lg btn-block btn-disabled" onclick="loanWorkerNextStep();">금리/한도 조회하기</a>
            </div>
        </section>
        <!-- //Content -->
    </form>
</div>
</body>
</html>
