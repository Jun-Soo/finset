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
});

//업데이트 요청하기
var reqUpdateYn = "N";
function reqUpdateShareInfo(){
	if("Y" == reqUpdateYn){
		toastMsg("이미 업데이트 요청 하셨습니다.");
		return false;
	}
	
	var data = frmShareInfoDetail.ajaxSubmit();
	if(data == null){
		return false;
	}
	$.ajax({
		url : "<c:url value='/m/customercenter/sendPersonShareInfoPush.json'/>",
		data : data,
		async: false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			if("00" == result.cdResult){
				toastMsg("업데이트 요청되었습니다.");
				
				reqUpdateYn = "Y";
			}else if("01" == result.cdResult){
				toastMsg("하루에 한번 업데이트 요청 가능합니다.");
				
				reqUpdateYn = "Y";
			}else{
				toastMsg("업데이트 요청 실패했습니다.");
				return false;
			}
		},
		error : function (e) {
            errMsg(e);

		}
	});
	
}

</script>
</head>
<body>
<div id="wrapper">
	<!-- Header -->
	<header id="header">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
			</div>
			<h1>공유 정보(<c:out value="${shareInfo.offer_nm_person}"/>)</h1>
		</div>
	</header>
	<!-- //Header -->
	<!-- Content -->
	<section id="content" class="bg-white">
	<form action="post" id="frmShareInfoDetail" name="frmShareInfoDetail">
		<input type="hidden" id="type_list" name="type_list" value="offer"/>
		<input type="hidden" id="setting_mode" name="setting_mode" value="04" />
		<input type="hidden" id="seq_share" name="seq_share" value="${shareInfo.seq_share}"/>
	</form>
		<div class="container">
			<div class="row">
				<div class="col-xs-6 share_head">
					<dl>
						<dt>정보 제공 항목</dt>
						<c:if test="${shareInfo.yn_credit_offer eq 'Y'}"><dd>-신용등급 및 연체 정보</dd></c:if>
						<c:if test="${shareInfo.yn_debt_offer eq 'Y'}"><dd>-대출개설 및 잔고 현황</dd></c:if>
						<c:if test="${shareInfo.yn_income_offer eq 'Y'}"><dd>-소득정보</dd></c:if>
					</dl>
				</div>
				<div class="col-xs-6 share_head">
					<dl>
						<dt>정보 통합 관리 항목</dt>
						<c:if test="${shareInfo.yn_asset_itgt_mngm eq 'Y'}"><dd>-자산</dd></c:if>
						<c:if test="${shareInfo.yn_consume_itgt_mngm eq 'Y'}"><dd>-소비</dd></c:if>
						<c:if test="${shareInfo.yn_debt_itgt_mngm eq 'Y'}"><dd>-부채</dd></c:if>
					</dl>
				</div>
			</div>
		</div>
		<table class="table tbl_item">
			<thead>
				<tr class="tr_head">
					<th colspan="3">신용정보</th>
				</tr>
			</thead>
			<tbody>
				<tr class="tr_two">
					<td>등급</td><td></td><td>${baseInfo.grade_credit}등급</td>
				</tr>
				<tr class="tr_two">
					<td>점수</td><td></td><td>${baseInfo.rating_credit}</td>
				</tr>
				<tr class="tr_item_blank"><td colspan="3"></td></tr>
				<tr class="tr_item_bold tr_two">
					<td>연체현황</td><td></td>
					<td>
					<c:choose>
						<c:when test="${!empty overdueCnt}">
							<c:out value="${overdueCnt}"/>건
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<c:forEach var="overdueList" items="${overdueListData}" varStatus="status">
				<tr class="tr_one">
					<td colspan="3"><c:out value="${overdueList.change_contents}"/></td>
				</tr>
				<tr class="tr_three">
					<fmt:parseDate var="dtInfo" value="${overdueList.dt_info}" pattern="yyyy-MM-dd" /> 
					<td></td><td>정보등록일:</td><td><fmt:formatDate value="${dtInfo}" pattern="yyyy.MM.dd" /></td>
				</tr>
				<tr class="tr_three">
					<td></td><td>수집처:</td><td><c:out value="${overdueList.collector}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<table class="table tbl_item">
			<thead>
				<tr class="tr_head">
					<th colspan="3">소득정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-left">정보수집기관</td><td></td><td class="text-right"><c:out value="${etmInfo.nm_etm_basic}"/></td>
				</tr>
				<tr>
					<td class="text-left">소득기준년도</td><td></td><td class="text-right"><c:out value="${etmInfo.std_year}"/>년</td>
				</tr>
				<tr>
					<td class="text-left">연소득</td><td></td><td class="text-right"><c:out value="${etmInfo.amt_etm_income}"/>만원</td>
				</tr>
			</tbody>
		</table>
		<table class="table tbl_item">
			<thead>
				<tr class="tr_head">
					<th colspan="3">부채정보</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="debtList" items="${debtListData}" varStatus="status">
				<tr class="tr_item_bold tr_one">
					<td colspan="3">
					<span class="thumb-logo" style="background-image:url('<c:url value='/fincorp/getFinCorpIcon.crz'/>?cd_fc=${debtList.cd_fc}');"></span>
					${debtList.nm_fc}
					</td>
				</tr>
				<tr class="tr_three">
					<td>계약정보</td><td>대출형태:</td><td>${debtList.debt_type}</td>
				</tr>
				<!-- 
				<tr class="tr_three">
					<td></td><td></td><td>분할상환</td>
				</tr>
				 -->
				<tr class="tr_three">
					<td></td><td>개설일자:</td><td>${ufn:formatDateDot(debtList.ymd_loan)}</td>
				</tr>
				<tr class="tr_three">
					<td></td><td>만기일자:</td><td>${ufn:formatDateDot(debtList.ymd_loanend)}</td>
				</tr>
				<tr class="tr_three">
					<td></td>
					<td>
						<c:if test="${debtList.cd_type_deal ne '3' and debtList.cd_type_deal ne '6'}">대출원금:</c:if>
	                    <c:if test="${debtList.cd_type_deal eq '3' or debtList.cd_type_deal eq '6'}">대출한도:</c:if>
					</td>
					<td>
						<c:set var="amt_contract" value="${debtList.amt_contract}" />
						<c:if test="${amt_contract ne '-'}">
							<c:set var="amt_contract" value="${ufn:formatNumberPattern(debtList.amt_contract,'###,###.##')}&nbsp;만원" />
						</c:if>
						${amt_contract}
					</td>
				</tr>
				<!-- 
				<tr class="tr_three">
					<td></td><td>구 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 분:</td><td>담보</td>
				</tr>
				-->
				<tr class="tr_three">
					<td>이용현황</td>
					<td>잔 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 액:</td>
					<td>
						<c:set var="amt_remain" value="${debtList.amt_remain}" />
						<c:if test="${amt_remain ne '-'}">
							<c:set var="amt_remain" value="${ufn:formatNumberPattern(debtList.amt_remain,'###,###.##')}&nbsp;만원" />
						</c:if>
						${amt_remain}
					</td>
				</tr>
				<tr class="tr_three">
					<td></td><td>금 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 리:</td><td>${debtList.ever_interest}%</td>
				</tr>
				<tr class="tr_three">
					<td></td>
					<td>월상환액:</td>
					<td>
						<c:if test="${debtList.cd_type_deal ne '3' and debtList.cd_type_deal ne '6'}">
			                <c:set var="amt_repay" value="${debtList.amt_repay}" />
							<c:if test="${amt_repay ne '-'}">
								<c:set var="amt_repay" value="${ufn:formatNumberPattern(debtList.amt_repay,'###,###.##')}&nbsp;만원" />
							</c:if>
							${amt_repay}
						</c:if>
		                <c:if test="${debtList.cd_type_deal eq '3' or debtList.cd_type_deal eq '6'}"><dd>-</dd></c:if>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<c:if test="${shareInfo.yn_itgt_mngm eq 'Y'}"> <!-- 정보통합관리항목이 있는경우 -->
			<div class="btn-fixed-bottom affix-bottom">
				<a role="button" class="btn btn-lg btn-block btn-primary" onclick="reqUpdateShareInfo();">업데이트 요청하기</a>
			</div>
		</c:if>
	</section>
	<!-- //Content -->
</div>
</body>
</body>
</html>
