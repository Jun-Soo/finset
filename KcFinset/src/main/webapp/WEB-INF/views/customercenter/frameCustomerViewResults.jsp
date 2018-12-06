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
	var searching = true;
	$(document).ready(function() {
        goodsChkInit();
		var totalPage = "${pagedList.pageCount}";
		$(window).scroll(function(){
			if($(document).height() <= $(window).scrollTop() + $(window).height()){
					if(totalPage > pageCnt){
						  jumpPage();
					}
				  pageCnt++;
			  }
			});
        var cnt=0;
		$("#page").val(1);
        $("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
            if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
                cnt++;
            }
        });

        if(cnt > 1){
            $('#next_div').addClass("btn-fixed-bottom");
            alert("대출은 한건만 신청할 수 있습니다.");
            return false;
        }
        setTimeout(function(){
            refreshSearching();
        },1000);
	});

	/*
	* name : goSearch
	* desc : 리스트 조회
	* param :
	* output :
	*/
	function goSearch() {
		jumpPage();
	}

	/*
	* name : jumpPage
	* desc : 페이지 이동
	* param :
	* output : 리스트 추가
	*/
	function jumpPage(){
		var pageIndex = Number($("#page").val());
		$("#page").val(pageIndex + 1);
		var data = frmFavoriteGoodsList.ajaxSubmit();

		if(data == null) return false;

		$.ajax({
			url : "<c:url value='/m/customercenter/listCustomerViewResults.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listLoanGoods").append(result);
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	/**
     * 조회중인 건이 있는 경우 조회실행
     * 없는 경우 플래그를 바꾼다.
     */
	function refreshSearching() {
        var count = getCountSearching();
        if(count > 0) {
            setTimeout(function() {
                refreshSearching();
            },3000);
        }
        /**
         * 조회 완료시 다시 조회
         */
        else {
            refreshPage();
        }
    }
	/*
	* name : jumpPage
	* desc : 페이지 이동
	* param :
	* output : 리스트 추가
	*/
	function refreshPage() {
        var data = $("#frmFavoriteGoodsList").serialize();
		if(data == null) return false;

		$.ajax({
			url : "<c:url value='/m/customercenter/listCustomerViewResults.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listLoanGoods").html(result);
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	/*
	* name : getIsSearching
	* desc : 조회중건수
	* param :
	* output :
	*/
	function getCountSearching() {
        var data = $("#frmFavoriteGoodsList").serialize();
		if(data == null) return false;
		var resultCount = 0;
		$.ajax({
			url : "<c:url value='/m/customercenter/getIsSearching.crz'/>",
			data : data,
			async: false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
                resultCount = result.count;
                <c:if test="${site ne 'REAL'}">
                    toastMsg("조회중건수="+resultCount);
                </c:if>
			},
			error : function (e) {
				errMsg(e);
			}
		});
		return resultCount;
	}

	/*
	* name : goodsFavoriteDetail
	* desc : 관심상품 상세 조회
	* param : cd_fc
	* param : cd_goods
	* output :
	*/
	function goodsFavoriteDetail( cd_fc, cd_goods ){
		frmFavoriteGoodsList.cd_fc.value = cd_fc;
		frmFavoriteGoodsList.cd_goods.value = cd_goods;
		frmFavoriteGoodsList.action = "<c:url value='/m/customercenter/frameCustomerViewResultsDetail.crz'/>";
		frmFavoriteGoodsList.submit();
	}

	function loanApplyStepStep() {
		frmFavoriteGoodsList.action = "<c:url value='/m/loan/frameLoanIncomeSuccessAfter.crz'/>";
		frmFavoriteGoodsList.submit();
	}
    function inCome() {
        var cd_goods;
        var cd_fc;
        var no_prepare;
        var amt_limit;
        var no_bunch;
        var cd_goods_gubun;
        var cnt = 0;

        $("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
            if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
                cnt++;

                cd_goods    = $(this).find("input[name='list_cd_goods']").val();
                cd_fc       = $(this).find("input[name='list_cd_fc']").val();
                no_prepare 	= $(this).find("input[name='list_no_prepare']").val();
                amt_limit 	= $(this).find("input[name='list_amt_limit']").val();
                no_bunch    = $(this).find("input[name='list_no_bunch']").val();
                cd_goods_gubun = $(this).find("input[name='list_cd_goods_gubun']").val();
            }
        });

        if(cnt > 1){
            $('#next_div').addClass("btn-fixed-bottom");
            alert("대출은 한건만 신청할 수 있습니다.");
            return false;
        }

        //상품코드, 금융사 코드 값 체크
        if(cd_goods == null && cd_fc == null) {
            toastMsg('대출상품을 선택해주세요.');
            return false;
        } else {
            frmFavoriteGoodsList.cd_goods.value 	  = cd_goods;
            frmFavoriteGoodsList.cd_fc.value 		  = cd_fc;
            frmFavoriteGoodsList.amt_limit.value 	  = amt_limit;
            frmFavoriteGoodsList.no_prepare.value     = no_prepare;
            frmFavoriteGoodsList.no_bunch.value       = no_bunch;
            frmFavoriteGoodsList.loan_code.value = cd_goods_gubun;
        }
        var data = $("#frmFavoriteGoodsList").serialize();
        <c:if test="${site ne 'REAL'}">
        alert("inCome:data="+data);
        </c:if>
		startLoading();
        if(data == null) return false;
        $.ajax({
            url : "<c:url value='/m/loan/frameLoanIncomeSuccess.json'/>",
            data : data,
            contentType : "application/x-www-form-urlencoded; charset=UTF-8",
            type : "POST",
            async : true,
            success : function (result) {
                stopLoading();
                <c:if test="${site ne 'REAL'}">
                    alert("inCome:result.message="+result.message+"\nresult.isSuccess="+result.isSuccess);
                </c:if>
                // alert("result.result="+result.result+",errorMsg="+result.errorMsg);
                loanApplyStepStep();
            },
            error : function (e) {
                toastMsg("서비스 오류입니다.");
            }
        });
	}
	function goodsChk(e){

		var cd_goods;
		var cd_fc;
		var cnt = 0;

		$("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
			if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
				cnt++;
			}
		});

		if(cnt > 1){
// 			affixBottom('hide');
			enableBottom('false');
			alert("대출은 한건만 신청할 수 있습니다.");
			e.checked = false;
		}else if(cnt == 1){
// 			affixBottom('show');
			enableBottom('true');
		}else {
// 			affixBottom('hide');
			enableBottom('false');
		}
	}
	function goodsChkInit(e){

		var cd_goods;
		var cd_fc;
		var cnt = 0;

		$("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
			if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
				cnt++;
			}
		});

		if(cnt == 1){
// 			affixBottom('show');
			enableBottom('true');
		}else {
// 			affixBottom('hide');
			enableBottom('false');
		}
	}

// 	function chgListGoodsSort(chgSort){

// 		frmFavoriteGoodsList.sel_order_col.value 	= chgSort.value;

// 		$("#page").val(1);

// 		var data = frmFavoriteGoodsList.ajaxSubmit();

// 		if(data == null) return false;

// 		$.ajax({
// 			url : "<c:url value='/m/customercenter/listCustomerViewSortResults.crz'/>",
// 			data : data,
// 			async: true,
// 			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
// 			type : "POST",
// 			success : function (result) {
// 				$("#listLoanGoods").empty();
// 				$("#listLoanGoods").append(result);
// 			},
// 			error : function (e) {
// 			}
// 		});
// 	}

	
	function loadTab(gubun){
		
		$("#page").val(1);
		$("#cd_goods_gubun").val('');
		$("#cd_goods_gubun2").val('');

		if(gubun == 'credit'){
			$('#cd_goods_gubun').val('01');
			$('#cd_goods_gubun2').val('02');
			
		} else if (gubun == 'house') {
			$('#cd_goods_gubun').val('03');
		}
		var data = frmFavoriteGoodsList.ajaxSubmit();

		if(data == null) return false;

		$.ajax({
			url : "<c:url value='/m/customercenter/listCustomerViewResults.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listLoanGoods").empty();
				$("#listLoanGoods").append(result);
			},
			error : function (e) {
			}
		});
	}
	
	function setPgCnt(value){
		$('#pgCnt').val(value);
		$('#pgCnt').html(value);
	}
</script>

</head>
<body>
	<div id="wrapper">
	<form method="post" name="frmFavoriteGoodsList" id="frmFavoriteGoodsList" >
		<input type="hidden" name="page" id="page"/>
		<input type="hidden" name="totalPage" id="totalPage" value ="${pagedList.pageCount}" />
		<input type="hidden" name="loan_code" >
		<input type="hidden" name="cd_goods" id="cd_goods" />
		<input type="hidden" name="cd_fc"    id="cd_fc" />
		<input type="hidden" name="amt_limit"    id="amt_limit" />
		<input type="hidden" name="no_prepare"    id="no_prepare" />
		<input type="hidden" name="no_bunch" id="no_bunch" />
		<input type="hidden" id="sel_order_col"  name="sel_order_col" />
		<input type="hidden" id="sel_order_asc"  name="sel_order_asc" />
		<input type="hidden" id="cd_goods_gubun"  name="cd_goods_gubun" />
		<input type="hidden" id="cd_goods_gubun2"  name="cd_goods_gubun2" />
	</form>

		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
				</div>
				<h1>상품조회결과</h1>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<div class="affix-fixed top-fixed-item">
			<%--	<ul class="nav nav-outline nav-justified tabs">
					<li class="active" ><a href="#tab1" onclick="loadTab('credit');">신용</a></li>
					<li><a href="#tab2" onclick="loadTab('house');">담보</a></li>
				</ul>--%>
				<div class="container-fluid collapse in top-block" id="collapseExample">
					<div class="alert-desc">
						<em>대출 신청은 하나의 상품만 선택해 신청 가능합니다.</em>
						<p>대출신청 후 신용등급 변동, 입력정보 오류 등으로 인해 금리/한도의 변동되거나 대출이 불가능 할  수 있습니다.</p>
						<button data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" class="btn-close"><i class="icon-close">레이어 닫기</i></button>
					</div>
					
				</div>
			</div>
			<!-- 
			<div class="sort-block affix-fixed top-fixed-item">
				<div class="ctrl-item">
					<div class="result-item">
						<div class="result-txt">
							<p class="prd-ea">총 <em id="pgCnt" name="pgCnt">0</em>개의 상품</p>
						</div>
						<select class="form-control" onchange="chgListGoodsSort(this);">
							<option value="01" <c:out value="${sort eq '01' ? 'selected' : ''}"/>>금리낮은순</option>
							<option value="02" <c:out value="${sort eq '02' ? 'selected' : ''}"/>>한도높은순</option>
							<option value="03" <c:out value="${sort eq '03' ? 'selected' : ''}"/>>기간높은순</option>
						</select>
					</div>
				</div>
			</div>
			 -->
			<div id="listLoanGoods">
				<%@ include file="/WEB-INF/views/customercenter/sub/listCustomerViewResults.jsp"%>
			</div>
			<div class="btn-fixed-bottom affix-bottom" id="next_div">
				<a role="button" class="btn btn-lg btn-block btn-disabled" onclick="inCome();">신청하기</a>
			</div>
		</section>
		<!-- //Content -->
	</div>
</body>
</html>
