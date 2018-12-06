<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
		$("#alertMsg .btn-lg").each(function () {
			$(this).on("click", function() {
				if("Y" == $(this).attr("data-val")){
					callFc();
				}
	        });
		});
		
		var totalPageNo = "${pagedListPastHis.pageCount}";
		var currPageNo = "${pagedListPastHis.page}";

		if( totalPageNo == 1 ){
 			$("#list_add").hide();
 		}else{
 			showAdd(totalPageNo, currPageNo);
 		}
	});
	
	//금융사 번호 저장
	function saveFcTelno(telno){
		$("#fc_telno").val(telno);
	}
	
	//금융사 연결
	function callFc(){
		
		if(userAgent == "iOS") {
			
				Jockey.send("phoneCall",{
					phNum : $("#fc_telno").val()
				});
								
		} else if(userAgent == "Android") {
			window.Android.phoneCall($("#fc_telno").val());
		} 
			
	}
</script>

<input type="hidden" name="fc_telno" id="fc_telno" value=""/>

<div class="list-group-block status-list" id="List">
<!-- 데이터가 존재하지 않을 때의 이미지 처리 -->
<c:if test="${empty pagedListPastHis.source}">
	<div class="data-none">
		<p>신청한 내역이 없습니다.</p>
	</div>
</c:if>
<c:forEach var="List" items="${pagedListPastHis.source}" varStatus="status">
	<div class="status-group-item panel container-fluid">
		<dl class="apply-label">
			<dt>신청번호</dt>
			<dd><b>${fn:substring(List.no_fc_req,0,8)}</b>${fn:substring(List.no_fc_req,8,14)}</dd>
		</dl>
			<c:choose>
			    <c:when test="${List.cd_apply_doc_box eq '10'}">
			    	<a href="#list${pagedListPastHis.page}${status.count}" class="panel-heading status-request" data-toggle="collapse">
						<div class="status-txt-area">
							<strong>${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</strong>
					    	<p>대출 신청이 완료되었습니다.</p>
						</div>
					</a>
			    </c:when>
			    <c:when test="${List.cd_apply_doc_box eq '20'}">
			    	<a href="#list${pagedListPastHis.page}${status.count}" class="panel-heading status-ing" data-toggle="collapse">
						<div class="status-txt-area">
							<strong>${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</strong>
					    	<p>대출 심사 진행중입니다.</p>
						</div>
					</a>
			    </c:when>
			    <c:when test="${List.cd_apply_doc_box eq '50'}">
			    	<a href="#list${pagedListPastHis.page}${status.count}" class="panel-heading status-end" data-toggle="collapse">
						<div class="status-txt-area">
							<strong>${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</strong>
					    	<p>대출 심사가 완료되었습니다.</p>
						</div>
					</a>
			    </c:when>
			    <c:when test="${List.cd_apply_doc_box eq '60'}">
			    	<a href="#list${pagedListPastHis.page}${status.count}" class="panel-heading status-pay" data-toggle="collapse">
						<div class="status-txt-area">
							<strong>${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</strong>
					    	<p>
					    		<c:choose>
					    		<c:when test="${not empty List.ymd_approval}">
						    		<c:choose>
									    <c:when test="${fn:substring(List.ymd_approval,4,5) eq '0'}">
									    	${fn:substring(List.ymd_approval,5,6)}월 ${fn:substring(List.ymd_approval,6,8)}일 대출금 지급이 완료되었습니다.
									    </c:when>
									    <c:otherwise>
									    	${fn:substring(List.ymd_approval,4,6)}월 ${fn:substring(List.ymd_approval,6,8)}일 대출금 지급이 완료되었습니다.
									    </c:otherwise>
								    </c:choose>
							    </c:when>
							    <c:otherwise>
							    	대출금 지급이 완료되었습니다.
							    </c:otherwise>
							    </c:choose>
					    	</p>
						</div>
					</a>
			    </c:when>
			    <c:when test="${List.cd_apply_doc_box eq '70'}">
			    	<a href="#list${pagedListPastHis.page}${status.count}" class="panel-heading status-reject" data-toggle="collapse">
						<div class="status-txt-area">
							<strong>${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</strong>
					    	<p>대출 심사가 부결(거절)되었습니다.</p>
						</div>
					</a>
			    </c:when>
			    <c:when test="${List.cd_apply_doc_box eq '99'}">
			    	<a href="#list${pagedListPastHis.page}${status.count}" class="panel-heading status-cancel" data-toggle="collapse">
						<div class="status-txt-area">
							<strong>${ufn:getCodeName('cd_apply_doc_box', List.cd_apply_doc_box)}</strong>
					    	<p>대출 신청이 취소되었습니다.</p>
						</div>
					</a>
			    </c:when>
			</c:choose>
		<div class="collapse" data-parent="#List" id="list${pagedListPastHis.page}${status.count}">
			<div class="list-body">
				<div class="step-group">
					<ul>
						<li class="status-request ${ List.cd_apply_doc_box == '10' ? 'active':''}">신청</li>
						<li class="status-ing ${ List.cd_apply_doc_box == '20' ? 'active':''}">심사중</li>
						<li class="status-end ${ List.cd_apply_doc_box == '50' ? 'active':''}">심사완료</li>
					<c:choose>
				    	<c:when test="${List.cd_apply_doc_box eq '60'}">
							<li class="status-pay active">지급</li>
				    	</c:when>
				    	<c:when test="${List.cd_apply_doc_box eq '70'}">
							<li class="status-reject active">거절</li>
				    	</c:when>
				    	<c:when test="${List.cd_apply_doc_box eq '99'}">
							<li class="status-cancel active">취소</li>
				    	</c:when>
				    	<c:otherwise>
							<li class="status-pay">지급</li>
				    	</c:otherwise>
				    </c:choose>
					</ul>
				</div>
				<%--
				<dl class="dl-list">
					<dt>2017.10.16  11:25</dt>
					<dd>대출금 입금</dd>
					<dt>2017.10.16  11:25</dt>
					<dd>대출심사</dd>
					<dt>2017.10.16  11:25</dt>
					<dd>접수및 상담</dd>
					<dt>2017.10.16  11:25</dt>
					<dd>대출신청</dd>
				</dl>
				 --%>
			</div>
		</div>
		<!-- 금융사 정보 -->
		<div class="list-block">
			<div class="container-fluid prd-loan">
				<div class="list-heading">
					<div class="bank-title">
						<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?file_name=${List.path_file1}');"></span>
						${ufn:getNmFc(List.cd_fc)}
					</div>
					<h2 class=" prd-title">${List.nm_goods}</h2>
<%-- 					<h2 class="prd-title">${ufn:getGoodsDetail(List.cd_goods, '')}</h2> --%>
					<%-- <p>${ufn:getGoodsDetail(List.cd_goods, '')}</p> --%>
					<a href="#" class="btn-call" onclick="saveFcTelno('${List.fc_telno}'); alertMsg('해당금융사의 고객센터로 연결됩니다. 연결하시겠습니까?');" >문의하기</a>
					<!-- <button type="button" class="btn-call" data-toggle="modal" data-target="#popModal" data-whatever="문의하기" data-url="/popup/linkTell.html">문의하기</button> -->
				</div>
				<div class="list-info">
					<dl>
						<dt>대출한도</dt>
						<dd>${ufn:formatNumberMan(List.amt_apply)}만원</dd>
					</dl>
					<dl>
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
						${List.rto_loan}%</dd>
					</dl>
					<dl>
						<dt>대출기간</dt>
						<dd><c:out value="${List.cd_loan_term}"/>년</dd>
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
			</div>	
		</div>
		<!-- //금융사 정보 -->
	</div>
</c:forEach>
</div>