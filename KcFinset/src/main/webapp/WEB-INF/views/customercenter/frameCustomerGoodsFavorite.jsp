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
	$(document).ready(function() {
		var totalPage = "${pagedList.pageCount}";
	    $(window).scroll(function(){
			if($(document).height() <= $(window).scrollTop() + $(window).height()){
					if(totalPage > pageCnt){
						  jumpPage();
					}
				  pageCnt++;
			  }
			});
	
		$("#page").val(1);
		
		$("#allcheck").on("change",function () { 
	        var checked = $(this).is(":checked");
	        $("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
	            var disabled = $(this).find("input:checkbox[name='goods_choice']").prop("disabled");
	            if (disabled != true) {
	                $(this).find("input:checkbox[name='goods_choice']").prop("checked",checked);
	            }
	        });
	        setCheckedHtml();
	        buttonClassRemove();
	    });
	});
	
	function getCheckedCount(){
	    var count = 0;
	    $("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
	        if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
	            count++;
	        }
	    });
	    return count;
	}
	function setCheckedHtml() {
	    var checkedCount =getCheckedCount();
	    var htmlText = "("+checkedCount+"개)";
	    $("#checked_count").html(htmlText);
	}
	function buttonClassRemove(){
	    if($("input:checkbox[name='goods_choice']:checked").length > 0){
	        enableBottom('true');
	    } else {
	        enableBottom('false');
	    }
	}
	
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
			url : "<c:url value='/m/customercenter/listLoanAffiliates.crz'/>",
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
	
	/*
	* name : goodsFavoriteDetail
	* desc : 관심상품 상세 조회
	* param : cd_fc
	* param : cd_goods
	* output :
	*/
	function goodsFavoriteDetail( cd_fc, cd_goods, yn_alliance ){
		frmFavoriteGoodsList.cd_fc.value = cd_fc;
		frmFavoriteGoodsList.cd_goods.value = cd_goods;
		frmFavoriteGoodsList.yn_alliance.value = yn_alliance;
		frmFavoriteGoodsList.action = "<c:url value='/m/customercenter/frameCustomerGoodsFavoriteDetail.crz'/>";
		frmFavoriteGoodsList.submit();
	}
	
	/*
	* name : tabGoodsAlliance
	* desc : 일반/제휴상품 탭 변경 이벤트
	* param :
	* output :
	*/
	function tabGoodsAlliance(cdGoodsAlliance){
		$("#page").val(1);
	
		$("#cd_goods_alliance").val(cdGoodsAlliance);
		
		var data = frmFavoriteGoodsList.ajaxSubmit();
	
	 	if(data == null) return false;
	
		$.ajax({
			url : "<c:url value='/m/customercenter/listLoanAffiliates.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listLoanGoods").empty();
				$("#listLoanGoods").append(result);
				
				setCheckedHtml();
	            buttonClassRemove();
	            $("#allcheck").prop("checked",false);
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	/*
	* name : chgGoodsType
	* desc : 상품구분 변경 이벤트
	* param :
	* output :
	*/
	function chgGoodsType(){
		$("#page").val(1);
		
		var data = frmFavoriteGoodsList.ajaxSubmit();
	
	 	if(data == null) return false;
	
		$.ajax({
			url : "<c:url value='/m/customercenter/listLoanAffiliates.crz'/>",
			data : data,
			async: true,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			success : function (result) {
				$("#listLoanGoods").empty();
				$("#listLoanGoods").append(result);
				
				setCheckedHtml();
	            buttonClassRemove();
	            $("#allcheck").prop("checked",false);
			},
			error : function (e) {
				errMsg(e);
			}
		});
	}
	
	function setPgCnt(value){
		$('#pgCnt').val(value);
		$('#pgCnt').html(value);
	}
	
	//금리/한도조회하기
	function goodsFavoriteInquiry(){
		if($("input:checkbox[name='goods_choice']:checked").length == 0) {
			toastMsg('선택된 대출 상품이 없습니다.');
		} else {
			var cdGoodsClass = "${cd_goods_class}";
			var cd_goods = '';
			var cd_fc = '';
			var frmAction = '';
			
			$("#listLoanGoods").find("div[id=loan_product]").each(function(i, item){
				if($(this).find("input:checkbox[name='goods_choice']").is(":checked")){
					cd_goods += $(this).find("input[name='cd_goods_each']").val()+",";
					cd_fc += $(this).find("input[name='cd_fc_each']").val()+",";
				}
			});
			cd_goods = cd_goods.slice(0, -1);
			cd_fc = cd_fc.slice(0, -1);
			
			frmFavoriteGoodsList.cd_goods.value = cd_goods;
			frmFavoriteGoodsList.cd_fc.value = cd_fc;
			
			if(cdGoodsClass == "02"){
				frmAction = "<c:url value='/m/loanselfemployed/frameLoanSelfEmployedStep4.crz'/>";
			}else if(cdGoodsClass == "03"){
				frmAction = "<c:url value='/m/loanhomemortgage/frameLoanHomeMortgageStep5.crz'/>";
			}else{
				frmAction = "<c:url value='/m/loanworker/frameLoanWorkerStep5.crz'/>";
			}
			frmFavoriteGoodsList.action = frmAction;
			frmFavoriteGoodsList.submit();
		}
	}
	
	//즐겨찾기 추가 / 해제
	function loanGoodsChoice(cd_fc, cd_goods, id, yn_alliance){
		var data = {"cd_fc":cd_fc, "cd_goods":cd_goods, "yn_alliance":yn_alliance}; 
		if(data == null) return false;
		var chkZzim = $('#'+id).is(":checked");
		
	    if(chkZzim == true){
	//     		alert("체크박스 체크");
	    		$.ajax({
					url : "<c:url value='/m/loan/insertLoanGoodsChoice.json'/>",
					data : data,
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					type : "POST",
					async : false,
					success : function (result) {
						var returnData = result.returnData;
	// 					alert(returnData.message);
// 						toastMsg("즐겨찾기 추가되었습니다");
					},
					error : function (e) {
						errMsg(e);
					}
				});
			}else if(chkZzim == false){
	//     		alert("체크박스 체크 해제!");
	    		$.ajax({
					url : "<c:url value='/m/loan/deleteLoanGoodsChoice.json'/>",
					data : data,
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					type : "POST",
					async : false,
					success : function (result) {
						var returnData = result.returnData;
	// 					alert(returnData.message);
// 						toastMsg("즐겨찾기 해제되었습니다");
					},
					error : function (e) {
						errMsg(e);
					}
				});
			}	
		}
	
	//뒤로가기
	function goBack(){
		frmFavoriteGoodsList.action = "/m/customercenter/frameCustomerCenterMain.crz";
		frmFavoriteGoodsList.submit();
	}
	
	//전체선택 show, hide
	function checkAllShow(cdGoodsAlliance){
		if(cdGoodsAlliance == "01"){ //일반상품
			$("#allCheck").hide();
		}else if(cdGoodsAlliance == "02"){ //제휴상품
			$("#allCheck").show();
		}
	}
</script>
</head>
<body>
	<div id="wrapper">
	<form method="post" name="frmFavoriteGoodsList" id="frmFavoriteGoodsList">
		<input type="hidden" name="page" id="page"/>
		<input type="hidden" name="totalPage" id="totalPage" value ="${pagedList.pageCount}" />
		<input type="hidden" name="cd_goods_alliance" id="cd_goods_alliance" value="${cd_goods_alliance}" />
		<!-- 상세view parameter -->
		<input type="hidden" name="cd_goods" id="cd_goods" />
		<input type="hidden" name="cd_fc"    id="cd_fc" />
		<input type="hidden" name="yn_alliance" id="yn_alliance" />
	
		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
				</div>
				<h1>관심상품</h1>
			</div>
		</header>
	
		<!-- Content -->
		<section id="content">
			<div class="affix-fixed top-fixed-item">
				<ul class="nav nav-outline nav-justified tabs">  
					<li class="<c:if test="${cd_goods_alliance eq '01'}">active</c:if>" ><a href="#tab1" onclick="tabGoodsAlliance('01');">일반상품</a></li>
					<li class="<c:if test="${cd_goods_alliance eq '02'}">active</c:if>" ><a href="#tab2" onclick="tabGoodsAlliance('02');">제휴상품</a></li>
				</ul>
			</div>
			<div class="sort-block">
				<div class="ctrl-item">
					<div id="allCheck" class="checkbox chk-square" style="display:none;"><label><input type="checkbox" id="allcheck"> 선택 <em id="checked_count">(0개)</em></label></div>
					<div class="result-item">
						<div class="result-txt">
							<p class="prd-ea">총 <em id="pgCnt" name="pgCnt">0</em>개의 상품</p>
						</div>  
						<select id="cd_goods_class" name="cd_goods_class" class="selectbox select-loan-cate" onchange="chgGoodsType();">
							<option value="01" ${cd_goods_class eq '01' ? 'selected' : ''}>신용대출(직장인)</option>
							<option value="02" ${cd_goods_class eq '02' ? 'selected' : ''}>신용대출(개인사업자)</option>
							<option value="03" ${cd_goods_class eq '03' ? 'selected' : ''}>담보대출</option>
						</select>
					</div>
				</div>
			</div>
			<div id="listLoanGoods">
				<%@ include file="/WEB-INF/views/customercenter/sub/listLoanAffiliates.jsp"%>
			</div>
		</section>
		<!-- //Content -->
	</form>
	</div>
</body>
</html>