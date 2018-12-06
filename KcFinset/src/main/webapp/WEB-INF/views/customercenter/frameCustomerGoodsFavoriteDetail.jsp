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
		 $("#alertMsg .btn-lg").each(function () {
			$(this).on("click", function() {
				if("Y" == $(this).attr("data-val")){
					callFc();
				}
			});
		});
	});
	
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

	function goBack(){
		var ynAlliance = "${yn_alliance}";
		
		if("Y" == ynAlliance){ //제휴상품
			$("#cd_goods_alliance").val("02");
		}else{ //일반상품
			$("#cd_goods_alliance").val("01");
		}
		
		frmFavoriteGoodsDetail.action = "<c:url value='/m/customercenter/frameCustomerGoodsFavorite.crz'/>";
		frmFavoriteGoodsDetail.submit();
    }
	
	//비제휴상품_금리알아보기/전화문의하기
	//금리알아보기
    function searchInterestRate(cd_non_goods){
		
    	var data = {"cd_non_goods" : cd_non_goods}; 
		if(data == null) return false;
		
    	$.ajax({
			url : "<c:url value='/m/goodsbank/addLinkCount.json'/>",
			data : data,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			type : "POST",
			async : false,
			success : function (result) {
				var returnData = result.returnData;
				
				if(userAgent == "iOS") {
					Jockey.send("browserCall",{
						url : "${goodsInfo.fc_link}"
					});
				} else if(userAgent == "Android") {
		    		window.Android.browserCall("${goodsInfo.fc_link}");
				}
			},
			error : function (e) {
				errMsg(e);
			}
		});
    }
	
  	//전화문의하기
	function callFc(){
		if(userAgent == "iOS") {
			Jockey.send("phoneCall",{
				phNum : "${goodsInfo.desc_contact}"
			});		
		} else if(userAgent == "Android") {
			window.Android.phoneCall("${goodsInfo.desc_contact}");
		} 	
	}
  	
</script>
</head>
<body>
	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-back" onclick="goBack();">뒤로가기</button>
				</div>
				<h1>관심상품</h1>
				<div class="input-group-btn">
					<button type="button" class="ui-nav nav-menu" data-toggle="btn-trigger">메뉴</button>
				</div>
			</div>
		</header>
		<form method="post" name="frmFavoriteGoodsDetail" id="frmFavoriteGoodsDetail">
		<input type="hidden" id="cd_goods_alliance" name="cd_goods_alliance" value="01"/>
		
		<!-- Content -->
		<section id="content">
			<div class="list-block">
				<div class="container-fluid prd-loan">
					<a href="#url">
						<div class="list-heading">
							<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${goodsInfo.cd_fc}');"></span>${ufn:getNmFc(goodsInfo.cd_fc)}
							<h2 class="prd-title">${goodsInfo.nm_goods}</h2>
							<p>${goodsInfo.desc_feature}&nbsp;</p>
							<div class="loan-tag">
								${ufn:makeKeyWordList(goodsInfo.keyword_list)}
							</div>
						</div>
						<div class="list-info">
							<dl>
								<dt>대출금리</dt>
								<dd class="txt-point">
								<c:set var="cd_ratio_type" value="${goodsInfo.cd_ratio_type}" />
	                            <c:if test="${goodsInfo.cd_ratio_type.length() gt '2'}">
	                                <c:set var="cd_ratio_type" value="변동,고정" />
	                                <label>${cd_ratio_type}</label>
	                            </c:if>
	                            <c:if test="${goodsInfo.cd_ratio_type.length() eq '2'}">
	                                <c:set var="cd_ratio_type" value="${ufn:getCodeName('cd_ratio_type', goodsInfo.cd_ratio_type)}" />
	                                <label>${cd_ratio_type}</label>
	                            </c:if>
	                            <c:set var="rto_interest_from" value="${goodsInfo.rto_interest_from}" />
	                            <c:set var="rto_interest_to"   value="${goodsInfo.rto_interest_to}" />
	                            <c:if test="${rto_interest_from eq null and rto_interest_to eq null}">
	                                -
	                            </c:if>
	                            <c:if test="${goodsInfo.rto_interest_from ne null and goodsInfo.rto_interest_to ne null}">
	                                ${goodsInfo.rto_interest_from}&nbsp;%&nbsp;~&nbsp;${goodsInfo.rto_interest_to}&nbsp;%
	                            </c:if>
	                            <c:if test="${goodsInfo.rto_interest_from ne null and goodsInfo.rto_interest_to eq null}">
	                                ${goodsInfo.rto_interest_from}&nbsp;%&nbsp;~
	                            </c:if>
	                            <c:if test="${goodsInfo.rto_interest_from eq null and goodsInfo.rto_interest_to ne null}">
	                                ~&nbsp;${goodsInfo.rto_interest_to}&nbsp;%
	                            </c:if>
                        		</dd>
							</dl>
							<dl>
								<dt>대출한도</dt>
								<c:choose>
								<c:when test="${yn_alliance eq 'Y'}">
								<dd>최대 ${ufn:formatNumberMan(goodsInfo.amt_limit)}만원 / ${goodsInfo.cd_loan_term}년</dd>
								</c:when>
								<c:otherwise> 
								<dd>최대 ${ufn:formatNumberPattern(goodsInfo.amt_limit, "###,###")}만원 / ${goodsInfo.cd_loan_term}년</dd>
								</c:otherwise>
								</c:choose>
							</dl>
							<div class="loan-tag">
								<%-- <span class="label">할인이벤트</span>
								<span class="label">모바일</span>
								<span class="label">직장인</span> --%>
							</div>
							<div class="loan-btn">
								<div class="checkbox ico-loan ico-zzim">
									<input type="checkbox" id="z${goodsInfo.cd_fc}${goodsInfo.cd_goods}" value="Y" onchange="loanGoodsChoice('${goodsInfo.cd_fc}','${goodsInfo.cd_goods}', 'z${goodsInfo.cd_fc}${goodsInfo.cd_goods}', '${yn_alliance}');" <c:out value="${goodsInfo.yn_favorite eq 'Y' ? 'checked' : ''}"/> ><label class="" for="z${goodsInfo.cd_fc}${goodsInfo.cd_goods}"></label>
								</div>
							</div>
						</div>
					</a>
				</div>
			</div>
			<div class="tab-content">
				<ul class="nav nav-outline nav-justified tabs">
					<li class="active"><a href="#tab1">상세정보</a></li>
					<li><a href="#tab2">금리정보</a></li>
				</ul>
				<div class="tab-pane active" id="tab1">
					<!-- tab1 -->
					<div class="container-fluid">
						<div class="tab-item">
							<h2 class="h2">대상고객</h2>
							<div>
									${goodsInfo.desc_loan}
							</div>
						</div>
						<div class="tab-item">
							<h2 class="h2">대출한도</h2>
							<ul>
								<li>
									${goodsInfo.desc_limit}

								</li>
							</ul>
						</div>
						<div class="tab-item">
							<h2 class="h2">대출기간</h2>
								<div class="import-tb">
									${goodsInfo.desc_paymethod}
								</div>
						</div>
						<div class="tab-item">
							<h2 class="h2">상환방법</h2>
							<ul>
								<li>${goodsInfo.desc_repaymethod}</li>
							</ul>
						</div>
						<div class="tab-item">
							<h2 class="h2">수수료</h2>
							<ul>
								<li>${goodsInfo.desc_commission}</li>
							</ul>
						</div>
						<c:if test="${not empty goodsInfo.desc_overdue_interest}">
							<div class="tab-item">
								<h2 class="h2">연체이자율</h2>
								<ul>
									<li>${goodsInfo.desc_overdue_interest}</li>
								</ul>
							</div>
						</c:if>
						<c:if test="${not empty goodsInfo.desc_loan_cost}">
							<div class="tab-item">
								<h2 class="h2">부대비용</h2>
								<ul>
									<li>${goodsInfo.desc_loan_cost}</li>
								</ul>
							</div>
						</c:if>
						<c:if test="${not empty goodsInfo.time_interest}">
							<div class="tab-item">
								<h2 class="h2">이자부과시기</h2>
								<ul>
									<li>${goodsInfo.time_interest}</li>
								</ul>
							</div>
						</c:if>
					</div>
					<!-- //tab1 -->
				</div>
				<div class="tab-pane" id="tab2">
					<!-- tab2 -->
					<div class="container-fluid">
						<div class="tab-item">
							<h2 class="h2">대출금리</h2>
							<ul>
								<li>${goodsInfo.content_interest}</li>
							</ul>
						</div>
						<c:if test="${not empty goodsInfo.prefer_interest}">
						<div class="tab-item">
							<h2 class="h2">우대금리</h2>
								${goodsInfo.prefer_interest}
						</div>
						</c:if>
					</div>
					<!-- //tab2 -->
				</div>
			</div>
			<!-- 페퍼일경우 아래 항목 추가 -->
			<c:if test="${not empty goodsInfo.desc_etc}">
			<div class="container">
				<h3 style="color: #999 !important; margin-left:-5px; margin-bottom:10px;">ⓘ  유의사항</h3>
				<div class="noti-section">
					<ul>
						${goodsInfo.desc_etc}
					</ul>
				</div>
			</div>
			</c:if>
			
			<!-- 비제휴 상품인경우 금리알아보기 / 전화문의하기 버튼 나타남 -->
			<c:if test="${goodsInfo.yn_alliance eq 'Y'}">
				<div class="btn-fixed-bottom affix-bottom">
<!-- 					<div class="col-xs-6"> -->
					<div class="col-xs-12">
						<c:choose>
						<c:when test="${empty goodsInfo.fc_link}">
							<button type="button" class="btn btn-lg btn-default btn-block" disabled="disabled">신청하기</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-lg btn-primary btn-block" onclick="searchInterestRate('${goodsInfo.cd_non_goods}');">신청하기</button>
						</c:otherwise>
						</c:choose>
					</div>
					<!-- 
					<div class="col-xs-6">
						<c:choose>
						<c:when test="${empty goodsInfo.desc_contact}">
							<button type="button" class="btn btn-lg btn-default btn-block" disabled="disabled">전화문의하기</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-lg btn-primary btn-block" onclick="alertMsg('해당금융사의 고객센터로 연결됩니다. 연결하시겠습니까?');">전화문의하기</button>
						</c:otherwise>
						</c:choose>
					</div>
					 -->
				</div>
			</c:if>
		</section>
		<!-- //Content -->
		</form>
	</div>
</body>
</html>
