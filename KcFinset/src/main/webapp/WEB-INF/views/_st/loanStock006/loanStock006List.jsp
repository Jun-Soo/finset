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
		//affixBottom('show');
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
		if('${errorMessage}' != ""){
			
		    alert('${errorMessage}');
		}
	});
	//초기화면
	function init(orderVal){
		//기본 정렬은 금리적은순
		if(orderVal == undefined) orderVal = "sortLoanIntrstRt";
		//Json으로 parsing 하여 정렬
		var inVOJson = JSON.parse('${inVO}');
		var outVOList = JSON.parse('${outVOList}');
		console.log(outVOList);
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
			    	html+='						<a href="javascript:void(0);" onclick="fnc_goDetail('+"'"+inVOJson.inVOList[parentIdx].mbrCd+"','"+gridListAdd[i].crdtIttCd+"','"+gridListAdd[i].prdtCd+"'"+');">                 '; 
			    	html+='							<div class="list-heading">                                                                                                ';
			    	html+='								<ul>                                                                                                                  ';
			    	html+='									<li class="bank-title">                                                                                           ';
			    	//html+='										<span class="thumb-logo" style="background-image:url("/fincorp/getFinCorpIcon.crz?cd_fc=2000016");"></span>   ';
			    	//html+='										'+outVOList[parentIdx].mbrNm+'&nbsp;/&nbsp;'+gridListAdd[i].crdtIttNm                                                       ;
			    	//html+='										<span class="thumb-logo"><img src="/fincorp/getFinCorpIcon.crz?cd_fc=2000016" alt="이미지 하드코딩"></span>   ';
			    	html+='										'+outVOList[parentIdx].mbrNm+'&nbsp;/&nbsp;' +gridListAdd[i].crdtIttNm                                                       ; 
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
			    	html+='							<dl>                                                                                                                  ';
			    	html+='								<dt>투자유형</dt>                                                                                                     ';
			    	if(gridListAdd[i].invstPlanTp == '0'){
				    	html+='								<dd>단기분산</dd>                  ';
			    	}else if(gridListAdd[i].invstPlanTp == '1'){
			    		html+='								<dd>장기분산</dd>                  ';
			    	}else if(gridListAdd[i].invstPlanTp == '2'){
			    		html+='								<dd>단기집중</dd>                  ';
			    	}else if(gridListAdd[i].invstPlanTp == '3'){
			    		html+='								<dd>장기집중</dd>                  ';
			    	}else{
			    		html+='								<dd>잘못된 데이터입니다.</dd>                  ';
			    	}
			    	
			    	html+='							</dl>                                                                                                                 ';
			    	html+='						</div>                                                                                                                    ';
			    	html+='						<div class="checkbox newloan">                                                                                            ';
			    	html+='							<label>                                                                                                               ';
			    	html+='								<input type="checkbox" name="checkbox1" class="checkb" onclick="fnc_checkClick();" />                                                         ';
			    	html+='							</label>                                                                                                              ';
			    	html+='						</div>                                                                                                                    ';
			    	html+='					</div>                                                                                                                        ';
				}
			}
			
			
			
		} 
		//상품갯수
		if(gridListAdd != null){
			
			gridCnt = gridListAdd.length;
		}
		
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
	function fnc_goDetail(mbrCd,crdtIttCd,prdtCd){
			//alert(mbrCd+"/"+crdtIttCd+"/"+prdtCd)
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

	//체크박스 클릭
    function fnc_checkClick(){
    	if ( $('.checkb').is(':checked') == true ) {
    		$(this).prop('checked',true);
    		affixBottom('show');
    	} else {
    		$(this).prop('checked',false);
    		affixBottom('hide');
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
					<button type="button" class="ui-nav nav-back" onclick="javascript:history.back();">뒤로가기</button>
				</div>
				<h1>주식매입자금(스탁론)</h1>
			</div>
		</header>
		<!-- Content -->
		<section id="content">
			<div class="affix-fixed top-fixed-item">
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
							<!-- <select class="form-control" id="orderby">
								<option value="01">금리낮은순</option>
								<option value="02">한도높은순</option>
								<option value="03">기간높은순</option>
							</select> -->
	                    </div>
					</div>
					<div class="ctrl-item">
						
					</div>
				</div>
			</div>
			<form  id="inputForm" method="post" action="/m/loanstock/loanStock007View.json">
				<input type="hidden" name="mbrCd">
				<input type="hidden" name="crdtIttCd">
				<input type="hidden" name="prdtCd">
			</form>
			
				<div class="list-block">	
					
				</div>
			

			<div class="btn-fixed-bottom" id="next_div">
				<a role="button" id="confirmButton" class="btn btn-lg btn-primary btn-block">조회하기</a>
			</div>
		</section>
		<!-- //Content -->
	</div>		
</body>
</html>