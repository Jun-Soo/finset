<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%@ taglib prefix="ct" uri= "/WEB-INF/tlds/ct.tld" %>
<html>
<head>
<meta charset="UTF-8">
<title>koscom</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>

<script type="text/javascript">
	$(document).ready(function () {
		affixBottom('show');
		init();
		
		//정렬 
		$("#orderby").change(function(){
			orderVal = $(this).val();
			$(".list-block").children().remove();
			init(orderVal);
		});
		
	
		//완료버튼
		$("#confirmButton").click(function(){
				
	    	location.href="/m/credit/frameCreditInfoMain.crz";
	    });
			
	    if(userAgent == "Android") {
	        window.Android.setEndApp('Y');
	    }
		 
	});
	//초기화면
	function init(orderVal){
		//기본 정렬은 금리적은순
		if(orderVal == undefined) orderVal = "sortLoanIntrstRt";
		//Json으로 parsing 하여 정렬
		var inVOJson = JSON.parse('${inVO}');
		var outVOList = JSON.parse('${outVOList}');
		var gridArrStr = "";
		var html = "";
		var gridCnt = 0;
		var gridVOJson = null;
		var gridListAdd = null;
		var outVOJson = null;
		for(var j=0; j<outVOList.length; j++){
			outVOJson = outVOList[j];
			//부보키를 세팅해준다
			for(var k=0; k < outVOJson.gridList.length; k++){
				
				outVOJson.gridList[k].parentIdx = j;
			}
			//console.log(outVOJson);
			//상품리스트를 하나의 json으로 합침
			if(gridListAdd == null){
				gridListAdd = outVOList[j].gridList;
			}else{
				gridListAdd = $.merge(gridListAdd,outVOList[j].gridList);
			}
			console.log(gridListAdd);
			//다 합쳐지면 화면에 뿌림
			if(j == outVOList.length -1){
				//정렬
				if (orderVal == 'sortLoanIntrstRt'){
					gridListAdd.sort(sortLoanIntrstRt); 
				}else{
					gridListAdd.sort(sortLoanMaxLmtAmt); 
				}
				//정렬된 data 화면에 뿌려줌
				for(var i=0; i < gridListAdd.length; i++){
			    	//부모리스트 index
					var parentIdx = gridListAdd[i].parentIdx;
					html+='					<div class="container-fluid">                                                                                                 ';
			    	html+='						<a href="javascript:void(0);" onclick="goDetail('+"'"+inVOJson.sndIttCdList[parentIdx]+"','"+outVOList[parentIdx].crdtIttCd+"','"+gridListAdd[i].prdtCd+"'"+');">                 ';
			    	html+='							<div class="list-heading">                                                                                                ';
			    	html+='								<ul>                                                                                                                  ';
			    	html+='									<li class="bank-title">                                                                                           ';
			    	//html+='										<span class="thumb-logo" style="background-image:url("/fincorp/getFinCorpIcon.crz?cd_fc=2000016");"></span>   ';
			    	//html+='										'+inVOJson.mbrNm+'&nbsp;/&nbsp;'+outVOJson.crdtLttNm                                                       ;
			    	html+='										<span class="thumb-logo"><img src="/fincorp/getFinCorpIcon.crz?cd_fc=2000016" alt="이미지 하드코딩"></span>   ';
			    	html+='										'+inVOJson.sndIttCdList[parentIdx]+'(증권사코드)&nbsp;/&nbsp;' +outVOList[parentIdx].crdtIttCd+'(여신사코드)'                                                      ;
			    	html+='									</li>                                                                                                             ';
			    	html+='								</ul>                                                                                                                 ';
			    	html+='								<div class="loan">                                                                                                    ';
			    	html+='									<ul>                                                                                                              ';
			    	html+='										<li class="name">'+gridListAdd[i].prdtNm+'</li>                                                              ';
			    	html+='									</ul>                                                                                                             '; 
			    	html+='								</div>                                                                                                                ';
			    	html+='							</div>                                                                                                                    ';
			    	html+='						</a>                                                                                                                                  ';
			    	html+='						<div class="list-info">                                                                                                   ';
			    	html+='							<dl>                                                                                                                  ';
			    	html+='								<dt>금리</dt>                                                                                                     ';
			    	html+='								<dd class="fs-red">'+parseFloat(gridListAdd[i].loanIntrstRt)+'%</dd>            ';
			    	html+='							</dl>                                                                                                                 ';
			    	html+='							<dl>                                                                                                                  ';
			    	html+='								<dt>한도</dt>                                                                                                     ';
			    	html+='								<dd>'+numberWithCommas(parseInt(gridListAdd[i].loanMaxLmtAmt)/10000)+'만원</dd>                  ';
			    	html+='							</dl>                                                                                                                 ';
			    	html+='							<dl>                                                                                                                 ';
			    	html+='								<dt>기간</dt>                                                                                                                 ';
			    	html+='								<dd>'+gridListAdd[i].loanTerm+'</dd>                                                                                                                 ';
			    	html+='							</dl>                                                                                                                 ';
			    	html+='						</div>                                                                                                                    ';
			    	// if(parseInt(gridVOJson[i].loanMaxLmtAmt) > 0){
			    		
				   // 	html+='						<div class="newloan">                                                                                            ';
				    //	html+='							<span>가능</span>                                                                                                               ';
				    //	html+='						</div>                                                        ';
			    	//} 
			    	
			    	html+='						<div class="newloan">                                                                                            ';
			    	html+='							<span>가능(하드코딩)</span>                                                                                                               ';
			    	html+='						</div>                                                        ';
	
			    	html+='					</div>                                                                                                                        ';
				}
			}
			
			
			
		} 
		//상품갯수
		gridCnt = gridListAdd.length;
		$("#list_count").text(gridCnt);
		//html 만들기
		$(".list-block").append(html);
		 
	}
	//대출금높은순 정렬
	function sortLoanMaxLmtAmt(a, b){
		if(a.loanMaxLmtAmt == b.loanMaxLmtAmt) return 0;
		return parseInt(a.loanMaxLmtAmt) < parseInt(b.loanMaxLmtAmt) ? 1 : -1;
	}
	//이율낮은순 정렬
	function sortLoanIntrstRt(a, b){
		if(a.loanIntrstRt == b.loanIntrstRt) return 0;
		return parseFloat(a.loanIntrstRt) > parseFloat(b.loanIntrstRt) ? 1 : -1;
	}
	//상세보기
	function goDetail(mbrCd,crdtIttCd,prdtCd){
			console.log(mbrCd+"/"+crdtIttCd+"/"+prdtCd)
		var form = $("#inputForm");
		$("input[name=mbrCd]").val(mbrCd);
		$("input[name=crdtIttCd]").val(crdtIttCd);
		$("input[name=prdtCd]").val(prdtCd);
		form.submit();
	}
	//숫자 3자리마다 , 찍기
	function numberWithCommas(x){
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}


</script>
</head>
<body>
<c:if test="${errorMessage != '' }">
	<script type="text/javascript">alert("${errorMessage }");</script>
</c:if>
<c:choose>
	<%-- <c:when test="${errorMessage != '' }">
		<p>${errorMessage }</p>
		<div id="next_div">
		<a href="#" class="btn btn-lg btn-primary btn-block2 btn-rev">재조회</a>
		<a href="#" class="btn btn-lg btn-primary btn-block2 btn-cancle">취소</a>
		</div>
	</c:when> --%>
	<c:when test="${empty outVOList }">
	</c:when>
	<c:otherwise>

		<div id="wrapper">
			<!-- Header -->
			<header id="header">
				<div class="input-group">
					<div class="input-group-btn">
						<button type="button" class="ui-nav nav-back">뒤로가기</button>
					</div>
					<h1>조회결과</h1>
				</div>
			</header>
			<!-- Content -->
			<section id="content">
				<div class="visual-banner new-vb">
					<p>아래의 정보를 확인하신 후 </p>
					<p>HTS에 접속하여 조회하신 상품을 </p>
					<p>신청하여 주세요</p>
				</div>
				<div class="affix-fixed top-fixed-item new-aff">
				    <div class="sort-block" id="divTop">
						<div class="ctrl-item">
							<div class="result-item">
		                        <div class="result-txt">
									<p class="prd-ea">총 <em id="list_count"></em>개의 상품</p>
								</div>
								<label for="" class="sr-only">select</label>
								<select class="selectbox pull-right" id="orderby">
									<option value="sortLoanIntrstRt">금리낮은순</option>
									<option value="sortLoanMaxLmtAmt">한도높은순</option>
								</select>
		                    </div>
						</div>
						<div class="ctrl-item">
						</div>
					</div>
				</div>
				<form  id="inputForm" method="post" action="/m/loanstock/loanStock013View.json">
					<input type="hidden" name="mbrCd">
					<input type="hidden" name="crdtIttCd">
					<input type="hidden" name="prdtCd">
					<%-- 
					<c:if test="${not empty outVO and outVO.nextKey != '' }">
						<input type="hidden" id="NextKeyOut" value="${outVO.nextKey }" title="nextkey">
					</c:if>
					 --%>
				</form>
				<div class="list-block" style="min-height:615px;">	
					
				</div>
				<div id="next_div">
					<a role="button" id="confirmButton" class="btn btn-lg btn-primary btn-block" style="background-color:#283593 !important; color: #fff;">완료</a>
				</div>
			</section>
			<!-- //Content -->
		</div>	
	</c:otherwise>
</c:choose>
	
</body>
</html>