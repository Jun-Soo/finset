<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
</head>
<body>
	<h5 align="center">FAQ페이지</h5>

	<div class="faqHeader">
		<span>번호</span><span>제목</span>
	</div>
	<dl>

		<dt>
			<span class="num">1</span><a href="javascript:readFaq(&#39;0&#39;);"
				class="subject">받는 분 개인통관고유부호는 왜 기재해야 하나요?</a>
		</dt>
		<dd>
			<div class="faqCont" style="display: none;" id="divFaqCont0">
				<span class="faqContTxt">답변내용</span>
				<p>
					물품을 받으실 분의 개인통관고유부호는 수입신고 시에 세관에서 요청하는 자료로, 받는 분의 개인통관고유부호가 기재되어야
					수입신고가 가능합니다. <br> <br>일반통관 진행 시 개인통관고유부호가 없는 경우 화물 입항 후
					한진 인천공항 사무소에서 별도로 확인 절차를 거치게 되므로 통관/배송 진행이 지연될 수 있습니다. <br> <br>배송이
					완료된 시점에는 이하넥스 시스템에서 자동 삭제되므로, 개인정보 유출 등 우려하지 않으셔도 됩니다. <br> <br>보다
					자세한 사항은 이하넥스(eHANEX)의 해외배송 관련 이용안내를 참고하시기 바랍니다.( <a
						href="http://www.ehanex.com/easyguide/FO_Event_Num.jsp"> 바로가기
						click☞</a>) <br>
				</p>
				<a href="javascript:readFaq(&#39;0&#39;);" class="btn btn-default">닫기</a>
			</div>
		</dd>

		<dt>
			<span class="num">2</span><a href="javascript:readFaq(&#39;1&#39;);"
				class="subject">신청서를 작성을 깜빡했어요. (상품이 입고된 후 신청서 작성한 경우)</a>
		</dt>
		<dd>
			<div class="faqCont" style="display: none;" id="divFaqCont1">
				<span class="faqContTxt">답변내용</span>
				<p>
					이하넥스 물류센터에 상품이 입고되었을 때 신청서가 반드시 작성되어 있어야만 신속하고 정확한 입고확인이 가능합니다. <br>
					<br>만약, 실수로 신청서를 늦게 작성하셨거나 입고된 후에도 신청서가 확인되지 않는 상품에 대해서는
					물류센터에서 신청서 미 작성 화물로 분류하여 별도의 장소에 보관하게 되며, 장기보관으로 인한 상품훼손 및 보관료 대상이
					될 수 있습니다. <br> <br>보관료 부과 기준은 아래와 같습니다. <br> <br>신청서
					없이 상품 먼저 입고된 상태로 10일, 운송료 결제요청 후 10일까지는 무상 <br>11일째 $3.00 최초
					부과 후 12일째부터 +$1.00/day 씩 추가 <br> <br> - 신청서 없이 상품 먼저 입고된
					상태로 10일, 운송료 결제요청 후 10일까지는 무상 <br>이하넥스에서는 물류센터에 상품 도착할 경우 혹은
					배송비 결제를 요청하는 SMS를 보내드립니다. 이는 신청서 작성 안내 및 빠른 운송료 결제를 할 수 있도록 안내 드리는
					것이니 문자/메일 받으신 즉시 신청서 작성 및 운송료 결제를 해주시기를 당부 드립니다. 또한 스팸 문자함으로 빠지거나
					올바르지 않은 휴대폰 정보로 수신하지 못할 수 있음을 참고 바랍니다.
				</p>
				<a href="javascript:readFaq(&#39;1&#39;);" class="btn btn-default">닫기</a>
			</div>
		</dd>

		<dt>
			<span class="num">3</span><a href="javascript:readFaq(&#39;2&#39;);"
				class="subject">상품이 여러 개일 경우 어떻게 작성하면 되나요?</a>
		</dt>
		<dd>
			<div class="faqCont" style="display: none;" id="divFaqCont2">
				<span class="faqContTxt">답변내용</span>
				<p>
					상이한 상품에 대해서는 신청서 상품정보 기재란 에서 ‘상품추가’ 버튼을 통해 각각 작성하셔야 합니다. <br>
					<br>이하넥스 물류센터에서는 입출고 자동화기기를 통해 신청서 상 정보와 입고된 상품을 하나씩 스캔하며 매치작업
					하기 때문에 하나의 상품정보에 여러 상품들을 다 기재하실 경우 입고확인이 불가합니다. <br> <br>정확한
					상품 확인을 위하여 각기 상이한 상품에 대해서는 상품정보를 각각 등록해 주시기를 부탁 드립니다. <br> <br>상품명
					난에 ‘등등.’ ‘Etc... .’ 와 같이 기재하실 경우 기재되지 않은 상품에 대해서는 입고 확인이 불가하며 입고
					진행이 늦어지니 이점 유의하여 주시기 바랍니다.
				</p>
				<a href="javascript:readFaq(&#39;2&#39;);" class="btn btn-default">닫기</a>
			</div>
		</dd>

		<dt>
			<span class="num">4</span><a href="javascript:readFaq(&#39;3&#39;);"
				class="subject">신청서 작성시, 물류센터를 잘못 선택했어요.</a>
		</dt>
		<dd>
			<div class="faqCont" style="display: none;" id="divFaqCont3">
				<span class="faqContTxt">답변내용</span>
				<p>
					물류센터를 잘못 지정하시고 신청하신 경우, 입고확인이 불가하며 화물 입고여부에 따라 아래와 같이 하시기 바랍니다. <br>
					<br>신청화물 물류센터 입고 전 : 기존 신청서 삭제 후 올바른 물류센터를 지정하여 신청서 재작성 <br>신청화물
					물류센터 입고 후 : 신청서 재작성 후 해당사항을 정리하여 1:1 문의
				</p>
				<a href="javascript:readFaq(&#39;3&#39;);" class="btn btn-default">닫기</a>
			</div>
		</dd>

		<dt class="last">
			<span class="num">5</span><a href="javascript:readFaq(&#39;4&#39;);"
				class="subject">신청서를 왜 작성해야 하나요?</a>
		</dt>
		<dd class="last">
			<div class="faqCont" style="display: none;" id="divFaqCont4">
				<span class="faqContTxt">답변내용</span>
				<p>
					쇼핑몰에서 주문하신 상품정보(쇼핑몰 주소, 상품명, 상품가격 등) 와 받으시는 분의 정보는 물류센터 입고확인, 한국도착
					후 수입통관/배송 진행에 중요한 자료로 활용됩니다. <br> <br>부정확한 정보 기재 시에는
					입고확인/통관/배송 진행이 원활하지 않을 수 있으니, 신청서 페이지 내 모든 정보에 대해 정확하게 작성해 주시기를 부탁
					드립니다. <br>
				</p>
				<a href="javascript:readFaq(&#39;4&#39;);" class="btn btn-default">닫기</a>
			</div>
		</dd>
		
		<dt class="last">
			<span class="num">6</span><a href="javascript:readFaq(&#39;5&#39;);"
				class="subject">6번입니다</a>
		</dt>
		<dd class="last">
			<div class="faqCont" style="display: none;" id="divFaqCont5">
				<span class="faqContTxt">답변내용</span>
				<p>
					쇼핑몰에서 주문하신 상품정보(쇼핑몰 주소, 상품명, 상품가격 등) 와 받으시는 분의 정보는 물류센터 입고확인, 한국도착
					후 수입통관/배송 진행에 중요한 자료로 활용됩니다. <br> <br>부정확한 정보 기재 시에는
					입고확인/통관/배송 진행이 원활하지 않을 수 있으니, 신청서 페이지 내 모든 정보에 대해 정확하게 작성해 주시기를 부탁
					드립니다. <br>
				</p>
				<a href="javascript:readFaq(&#39;5&#39;);" class="btn btn-default">닫기</a>
			</div>
		</dd>

	</dl>
	<!-- e:faq -->

<script type="text/javascript">
function readFaq(idx){

	var obj  = eval("document.getElementById('divFaqCont"+idx+"')");
	
	if(obj.style.display == ""){
	  obj.style.display = "none";
	}else{
	  obj.style.display = "";
	}
}
</script>
</body>
</html>
