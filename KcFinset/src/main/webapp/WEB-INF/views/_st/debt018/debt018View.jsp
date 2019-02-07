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
    	//console.log('${outVOJson}');
    	
        if(userAgent == "Android") {
            window.Android.setEndApp('Y');
        }
    });

	</script>
</head>
<body>


<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="goDebtMain();">뒤로가기</button>
			</div>
			<h1>부채상세정보</h1>
			
			<form name="frmDebtUpdate" id="frmDebtUpdate" method="post" validate="remove_tag;">
				<input type="hidden" name="no_manage_info" value="${debtData.no_manage_info }"/>
				<input type="hidden" name="interest" value="${debtData.ever_interest}"/>
			</form>
			
			
<!-- 		gmenu 기초 -->
			<div class="rightDiv" id="debt_rightDiv">
				<button type="button" id="submenu" class="ico-submenu"></button>
			</div>
<!-- 		frameLoanWorkerStep2의 selectbox기초해서 bootstrap class에 영향을 받게 수정 -->
			<div id="hiddenSelectbox" style="position: fixed; right: 0px">
				<div class="btn-group bootstrap-select selectbox pull-right open">
					<div class="dropdown-menu open" id="dropdown-menu">
						<ul class="dropdown-menu inner" id="dropdown-inner">
							<li><a id="debt-update"><span class="text">수정</span></a></li>
							<li><a id="modalConfirm"><span class="text">삭제</span></a></li>
							<li><a href="../memo/frameListMemo.crz"><span class="text">메모</span></a></li>
<!-- 							<li><a><span class="text">출금계좌연결</span></a></li> -->
							<c:choose>
								<c:when test="${empty debtData.tel}">
									<li><a href="tel:0000"><span class="text">전화걸기</span></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="tel:${debtData.tel}"><span class="text">전화걸기</span></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
            </div>
		</div>
	</header>
	<!-- Content -->
	<section id="content">
		<div class="list-block">
			<div class="container-fluid prd-debt">
				<div class="list-heading">
					<li class="bank-title">
						<%-- <span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${debtData.cd_fc}');"></span> --%>${outVO.prdtNm}(${outVO.mbrNm} / ${outVO.crdtIttNm})
					</li>
					<label class="label-type">${debtData.debt_type}</label>
				</div>
				<div class="list-info">
					<dl>
						<dt>대출금액</dt>
						<dd><fmt:formatNumber value="${outVO.loanAmt/10000}" pattern="#,#00"/>만원</dd>
					</dl>
					<dl>
						<dt>평가금액</dt>
						<dd><fmt:formatNumber value="${outVO.totEvalAmt/10000}" pattern="#,#00"/>만원</dd>
					</dl>
					<dl>
						<dt>인출가능금액</dt>
						<dd><fmt:formatNumber value="${outVO.mnyoutAbleAmt/10000}" pattern="#,#00"/>만원</dd>
					</dl>
					<div class="progress-group progress-bar-sum">
						<div class="progress">
							<div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
						</div>
						<div class="progress-label">
							 <label class="label">담보평가비율</label>
							<span><fmt:formatNumber value="${outVO.loanAmt / outVO.totEvalAmt * 100}" pattern="##0.00"/>%</span>
						</div>
					</div>
				</div>
				<%-- 
				<div class="list-info">
					
					<dl>
						<dt>총평가금</dt>
						<dd><fmt:formatNumber value="${outVO.totEvalAmt/10000}" pattern="#,#00"/>만원</dd>
					</dl>
					<dl>
						<dt>주식평가금</dt>
						<dd><fmt:formatNumber value="${outVO.assetEvalAmt/10000}" pattern="#,#00"/>만원</dd>
					</dl>
					<dl>
						<dt>예수금</dt>
						<dd><fmt:formatNumber value="${outVO.dps/10000}" pattern="#,#00"/>만원</dd>
					</dl>
					<div class="progress-group progress-bar-sum">
						<div class="progress">
							<!--<i class="pointer"></i>-->
							<div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
						</div>
						<div class="progress-label">
							 <label class="label">수익률</label>
							<span><fmt:formatNumber value="${outVO.ernrat}" pattern="##0.00"/>%</span>
						</div>
					</div>
				</div>
				 --%>
			</div>
		</div>
		<div class="tab-content">
			<ul class="nav nav-outline nav-justified tabs">
				<li class="active"><a href="#tab2">계약정보</a></li>
				<li><a href="#tab1">상환정보</a></li>
				<li><a href="#tab3">종목정보</a></li>
			</ul>
			<div class="tab-pane active" id="tab2">
				<!-- tab2 -->
				<div class="container-fluid">
					<div class="cont-cube">
						<ul>
							<li>
								<dl>
									<dt>${outVO.mbrNm }</dt>
									<dd>${outVO.prdtNm }</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>금리</dt>
									<%-- <c:set var="ever_interest" value="${outVO.loanIntrstRt}" />
			                        <c:if test="${ever_interest eq '-'}">
			                            <c:set var="ever_interest" value="${debtData.ever_interest}" />
			                        </c:if>
			                        <c:if test="${ever_interest ne '-'}">
			                            <c:set var="ever_interest" value="${debtData.ever_interest}%" />
			                        </c:if> --%>
									<dd><fmt:formatNumber value="${outVO.loanIntrstRt}" pattern="##0.00"/>%</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>대출금</dt>
									
                                    <dd><fmt:formatNumber value="${outVO.loanAmt/10000}" pattern="#,#00"/>만원</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>개설일자</dt>
									<dd>${ufn:formatDateDot(outVO.loanSrtDt)}</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>만기일자</dt>
									<dd>${ufn:formatDateDot(outVO.loadEndDt)}</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>상환방식</dt>
									<dd><c:choose>
											<c:when test="${outVO.rfundMthd eq '0'}">
												만기일시
											</c:when>
											<c:when test="${outVO.rfundMthd eq '1'}">
												만기
											</c:when>
											<c:otherwise>
											-
											</c:otherwise>
										</c:choose>
                                    </dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>잔여기간</dt>
									<dd><c:if test="${empty outVO.termDays}">-</c:if>
                                        <c:if test="${!empty outVO.termDays}"><fmt:formatNumber value="${outVO.termDays}"/>개월</c:if></dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>동일종목투자</dt>
									<dd><fmt:formatNumber value="${outVO.isuInvstLmt}"/>%</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>로스컷</dt>
									<dd><fmt:formatNumber value="${outVO.lscutSetRt}"/>%</dd>
								</dl>
							</li>
							<!-- li>
								<dl>
									<dt>대출구분</dt>
									<dd>${debtData.type_deal}</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>대출기간</dt>
									<dd><c:if test="${debtData.term_all eq '-'}">-</c:if>
                                        <c:if test="${debtData.term_all ne '-'}">${debtData.term_all}개월</c:if></dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt>대출잔액</dt>
                                    <dd>${ufn:formatNumberPattern(debtData.amt_remain,'###,###.##')}만원</dd>
								</dl>
							</li -->
						</ul>
					</div>
				</div>
				<div class="container">
					<p>* 금리는 핀셋에서 추정한 값으로 실제와 차이가 발생할 수 있습니다.</p>
				</div>
				<!-- //tab2 -->
			</div>
			<div class="tab-pane" id="tab1">
				<!-- tab1 -->
				<div class="container-fluid">
						<div class="repay-status">
							<span class="label label-info">정상</span>
<!-- 							<span class="label label-warning">중도상환</span>
							<span class="label label-success">단기연체</span> -->
							<span class="label label-danger">연체</span>
							<span class="label label-default">기타</span>
						</div>
						<div class="repay-date-area">
                            <c:set var="i" value="0" />
                            <%--<c:set var="req_yyyymm" value="" />--%>
                            <%--<c:if test="${list12BizDayData.size() > '0'}">--%>
                                <%--<c:set var="req_yyyymm" value="${list12BizDayData[0].req_yyyymm}" />--%>
                            <%--</c:if>--%>
                            <c:forEach var="oneMonth" items="${list12BizDayData}" varStatus="status">
							<%--<c:forEach var="i" begin="0" end="11" step="1">--%>
								<c:if test="${i == 0 || i == 4 || i == 8 }">
								<div class="row">
								</c:if>
									<div id="collapseExample_${i}" class="collapse popup-layer">
										<div class="popup-layer-header"><span>${oneMonth.req_yyyymm}</span></div>
										<span class="title">원리금</span>
                                        <c:set var="amt_repay" value="${oneMonth.amt_repay}" />
                                        <c:if test="${amt_repay ne '-'}">
                                            <c:set var="amt_repay" value="${ufn:formatNumberPattern(oneMonth.amt_repay,'###,###.##')}만원" />
                                        </c:if>
                                        <p>${amt_repay}</p>
                                        <c:set var="amt_repay_p" value="${oneMonth.amt_repay_p}" />
                                        <c:if test="${amt_repay_p ne '-'}">
                                            <c:set var="amt_repay_p" value="${ufn:formatNumberPattern(oneMonth.amt_repay_p,'###,###.##')}만원" />
                                        </c:if>
										<p><span><strong>원금</strong> : ${amt_repay_p }</span>
                                            <c:set var="amt_repay_i" value="${oneMonth.amt_repay_i}" />
                                            <c:if test="${amt_repay_i ne '-'}">
                                                <c:set var="amt_repay_i" value="${ufn:formatNumberPattern(oneMonth.amt_repay_i,'###,###.##')}만원" />
                                            </c:if>
											/ <span><strong>이자</strong> : ${amt_repay_i }</span></p>
										<button data-toggle="collapse" data-target="#collapseExample_${i}" aria-expanded="false" aria-controls="collapseExample_${i}" class="btn-close"><i class="icon-close">레이어 닫기</i></button>
									</div>
									<!-- 계좌상태코드 : 0-정상, 1-중도상환, 2-단기연체,3-장기연체,4-기타, 5-알수없음 -->
									<div class="col-xs-3">
										<c:if test="${oneMonth.cd_state == '0'}">
											<c:set var="status_color" value="repay-date-info" />
										</c:if>
										<c:if test="${oneMonth.cd_state == '1'}">
											<c:set var="status_color" value="repay-date-warning" />
										</c:if>
										<c:if test="${oneMonth.cd_state == '2'}">
											<c:set var="status_color" value="repay-date-success" />
										</c:if>
										<c:if test="${oneMonth.cd_state == '3'}">
											<c:set var="status_color" value="repay-date-danger" />
										</c:if>
										<c:if test="${oneMonth.cd_state == '4'}">
											<c:set var="status_color" value="repay-date-default" />
										</c:if>
										<c:if test="${oneMonth.cd_state == '5'}">
											<c:set var="status_color" value="repay-date-default" />
										</c:if>
										<div data-toggle="collapse" href="#collapseExample_${i}" aria-expanded="false" aria-controls="collapseExample_${i}" class="repay-date ${status_color}" data-layer="popupMonth">
											<span>${oneMonth.req_yyyymm}</span>
										</div>
									</div>
								<c:if test="${i == 3 || i == 7 || i == 11 }">
								</div>
								</c:if>
                                <c:set var="i" value="${i + 1}" />
                            </c:forEach>
					</div>
					<%--<h2 class="h2">전체/상환/잔여 <small> 단위:만원</small></h2>--%>
					<%--<table class="table">--%>
						<%--<thead>--%>
							<%--<tr>--%>
								<%--<td></td>--%>
								<%--<td>원리금</td>--%>
								<%--<td>이자</td>--%>
								<%--<td>원금</td>--%>
							<%--</tr>--%>
						<%--</thead>--%>
						<%--<tbody>--%>
							<%--<tr>--%>
								<%--<th>전체</th>--%>
								<%--<td class="text-right">${ufn:formatNumberPattern(debtData.all_amt_repay  ,"###,###.##")}</td> <!-- 원리금 전체 -->--%>
								<%--<td class="text-right">${ufn:formatNumberPattern(debtData.all_amt_repay_i,"###,###.##")}</td> <!-- 이자 전체 -->--%>
								<%--<td class="text-right">${ufn:formatNumberPattern(debtData.all_amt_repay_p,"###,###.##")}</td> <!-- 원금 전체 -->--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th>상환</th>--%>
								<%--<td class="text-right">${ufn:formatNumberPattern(debtData.rep_amt_repay  ,"###,###.##")}</td> <!-- 원리금 상환 -->--%>
								<%--<td class="text-right">${ufn:formatNumberPattern(debtData.rep_amt_repay_i,"###,###.##")}</td> <!-- 이자 상환 -->--%>
								<%--<td class="text-right">${ufn:formatNumberPattern(debtData.rep_amt_repay_p,"###,###.##")}</td> <!-- 원금 상환 -->--%>
							<%--</tr>--%>
							<%--<tr class="tr-point">--%>
								<%--<th>잔여</th>--%>
								<%--<td class="text-right">${ufn:formatNumberPattern(debtData.rem_amt_repay  ,"###,###.##")}</td> <!-- 원리금 잔여 -->--%>
								<%--<td class="text-right">${ufn:formatNumberPattern(debtData.rem_amt_repay_i,"###,###.##")}</td> <!-- 이자 잔여 -->--%>
								<%--<td class="text-right">${ufn:formatNumberPattern(debtData.rem_amt_repay_p,"###,###.##")}</td> <!-- 원금 잔여 -->--%>
							<%--</tr>--%>
						<%--</tbody>--%>
					<%--</table>--%>
				</div>
				<!-- //tab1 -->
				
			</div>
			<div class="tab-pane" id="tab3">
				<div class="container-fluid">
<%-- 
					<c:forEach items="${outVO.gridList }" var="isuList" varStatus="status">
						종목코드:	${isuList.isuNo}<br>
						종목명:	${isuList.shtnIsuNm}<br>
						평가금액:	${isuList.evalAmt}<br>
						현재가:	${isuList.curPrc}<br>
						등락률:	${isuList.updnRat}<br>
						평단가:	${isuList.buyPrc}<br>
						보유수량:	${isuList.hldQty}<br>
						평가손익:	${isuList.evalPnl}<br>
						평가손익률:	${isuList.evalRat}<br>
					</c:forEach>
					 --%>
				</div>
			</div>
		</div>
	</section>
	<!-- //Content -->
</div>
<div id="help-sub-sum" class="collapse popup-layer">
	<span class="title">원금상환비율</span>
	<p>(대출원금-대출잔액)/대출원금*100</p>
	<button data-toggle="collapse" data-target="#help-sub-sum" class="btn-close _popup_close"><i class="icon-close">레이어 닫기</i></button>
</div>
<!-- 삭제를 확인하는 modal -->
<div id="delConfirmModal" class="modal">
		<div class="alert-content">
			<div class="alert-body">
			    <div id="body-header" class="alertText">부채를 삭제 할까요?</div>
			    <div id="body-sub">선택하신 대출 정보는 모두 삭제가 되어 이후 조회가 불가능 합니다.</div>
		    </div>
		    <div class="alert-footer"> 
		    	<button type="button" class="btn btn-lg btn-default" data-dismiss="modal">아니오</button>
				<button type="button" id="btn-delete" class="btn btn-lg btn-default" data-dismiss="modal">네, 진행할게요</button>
		    </div>
		</div>
	</div>

	
</body>
</html>