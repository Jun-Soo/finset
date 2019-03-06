<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$('#listPersonInfoTable')
	.addClass( 'nowrap' )
	.DataTable({
		info: false,
		paging: false,
		searching: false,
		responsive: true,
		columnDefs: [
						{ targets: [-1, -3], className: 'dt-body-right' }
					]
	});
});

</script>
<!-- <div class="panel panel-primary"> -->
<!-- 	<div class="panel-collapse"> -->
		<table id="list029InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>자택주소등록일자</th>
<th>자택우편번호 </th>
<th>자택주소상</th>
<th>자택주소하</th>
<th>자택전화번호 </th>
<th>실거주지주소등록일자</th>
<th>실거주지우편번호</th>
<th>실거주지주소상</th>
<th>실거주지주소하</th>
<th>실거주지전화번호</th>
<th>직장주소등록일자</th>
<th>직장우편번호 </th>
<th>직장주소상</th>
<th>직장주소하</th>
<th>직장전화번호 </th>
<th>직장전화내선번호</th>
<th>직장명 </th>
<th>부서명 </th>
<th>현직장입사년월</th>
<th>연소득등록일자</th>
<th>연소득 </th>
<th>재산세등록일자</th>
<th>재산세 </th>
<th>휴대폰번호등록일자 </th>
<th>휴대폰번호</th>
<th>자택주소구분 </th>
<th>실거주지주소구분</th>
<th>직장주소구분 </th>
<th>이메일주소등록일자 </th>
<th>이메일주소</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty Kcb_Segment029}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
				</c:if>
			<c:forEach var="List_Kcb_Segment029" items="${Kcb_Segment029}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment029.segID}</td>
<td>${List_Kcb_Segment029.dt_regist_home}</td>
<td>${List_Kcb_Segment029.post_home}</td>
<td>${List_Kcb_Segment029.addr1_home}</td>
<td>${List_Kcb_Segment029.addr2_home}</td>
<td>${List_Kcb_Segment029.tel_home}</td>
<td>${List_Kcb_Segment029.dt_regist_real}</td>
<td>${List_Kcb_Segment029.post_real}</td>
<td>${List_Kcb_Segment029.addr1_real}</td>
<td>${List_Kcb_Segment029.addr2_real}</td>
<td>${List_Kcb_Segment029.tel_real}</td>
<td>${List_Kcb_Segment029.dt_regist_comp}</td>
<td>${List_Kcb_Segment029.post_comp}</td>
<td>${List_Kcb_Segment029.addr1_comp}</td>
<td>${List_Kcb_Segment029.addr2_comp}</td>
<td>${List_Kcb_Segment029.tel_comp}</td>
<td>${List_Kcb_Segment029.tel_ext_comp}</td>
<td>${List_Kcb_Segment029.nm_comp}</td>
<td>${List_Kcb_Segment029.nm_dept}</td>
<td>${List_Kcb_Segment029.ym_start_comp}</td>
<td>${List_Kcb_Segment029.ymd_year_income}</td>
<td>${List_Kcb_Segment029.amt_year_income}</td>
<td>${List_Kcb_Segment029.ymd_property_tax}</td>
<td>${List_Kcb_Segment029.amt_property_tax}</td>
<td>${List_Kcb_Segment029.ymd_hp}</td>
<td>${List_Kcb_Segment029.hp}</td>
<td>${List_Kcb_Segment029.cd_addr_home}</td>
<td>${List_Kcb_Segment029.cd_real_home}</td>
<td>${List_Kcb_Segment029.cd_addr_comp}</td>
<td>${List_Kcb_Segment029.ymd_email}</td>
<td>${List_Kcb_Segment029.email}</td>
<td>${List_Kcb_Segment029.filler}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
 		<table id="list030InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>정보관리번호 </th>
<th>업종명 </th>
<th>거래형태코드 </th>
<th>자금용도코드 </th>
<th>신용여부</th>
<th>담보여부</th>
<th>보증인여부</th>
<th>약정금액</th>
<th>대출일자</th>
<th>해지일자</th>
<th>해지사유코드 </th>
<th>집단대출대납구분코드</th>
<th>연체대환대출여부</th>
<th>신용회복지원여부</th>
<th>대출잔액</th>
<th>1개월전연체금액 </th>
<th>2개월전연체금액 </th>
<th>3개월전연체금액 </th>
<th>4개월전연체금액 </th>
<th>5개월전연체금액 </th>
<th>6개월전연체금액 </th>
<th>7개월전연체금액 </th>
<th>8개월전연체금액 </th>
<th>9개월전연체금액 </th>
<th>10개월전연체금액</th>
<th>11개월전연체금액</th>
<th>12개월전연체금액</th>
<th>1개월전계좌상태코드</th>
<th>2개월전계좌상태코드</th>
<th>3개월전계좌상태코드</th>
<th>4개월전계좌상태코드</th>
<th>5개월전계좌상태코드</th>
<th>6개월전계좌상태코드</th>
<th>7개월전계좌상태코드</th>
<th>8개월전계좌상태코드</th>
<th>9개월전계좌상태코드</th>
<th>10개월전계좌상태코드</th>
<th>11개월전계좌상태코드</th>
<th>12개월전계좌상태코드</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment030}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment030" items="${Kcb_Segment030}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment030.segId}</td>
<td>${List_Kcb_Segment030.no_manage_info}</td>
<td>${List_Kcb_Segment030.nm_biz_type}</td>
<td>${List_Kcb_Segment030.cd_type_deal}</td>
<td>${List_Kcb_Segment030.cd_use_fund}</td>
<td>${List_Kcb_Segment030.yn_credit}</td>
<td>${List_Kcb_Segment030.yn_loan}</td>
<td>${List_Kcb_Segment030.yn_guarantor}</td>
<td>${List_Kcb_Segment030.amt_contract}</td>
<td>${List_Kcb_Segment030.ymd_loan}</td>
<td>${List_Kcb_Segment030.ymd_cancel}</td>
<td>${List_Kcb_Segment030.cd_cancel}</td>
<td>${List_Kcb_Segment030.cd_group_loans_proxy}</td>
<td>${List_Kcb_Segment030.yn_delay_turn_loan}</td>
<td>${List_Kcb_Segment030.yn_recovery}</td>
<td>${List_Kcb_Segment030.amt_remain}</td>
<td>${List_Kcb_Segment030.amt_month_before1}</td>
<td>${List_Kcb_Segment030.amt_month_before2}</td>
<td>${List_Kcb_Segment030.amt_month_before3}</td>
<td>${List_Kcb_Segment030.amt_month_before4}</td>
<td>${List_Kcb_Segment030.amt_month_before5}</td>
<td>${List_Kcb_Segment030.amt_month_before6}</td>
<td>${List_Kcb_Segment030.amt_month_before7}</td>
<td>${List_Kcb_Segment030.amt_month_before8}</td>
<td>${List_Kcb_Segment030.amt_month_before9}</td>
<td>${List_Kcb_Segment030.amt_month_before10}</td>
<td>${List_Kcb_Segment030.amt_month_before11}</td>
<td>${List_Kcb_Segment030.amt_month_before12}</td>
<td>${List_Kcb_Segment030.cd_account_before1}</td>
<td>${List_Kcb_Segment030.cd_account_before2}</td>
<td>${List_Kcb_Segment030.cd_account_before3}</td>
<td>${List_Kcb_Segment030.cd_account_before4}</td>
<td>${List_Kcb_Segment030.cd_account_before5}</td>
<td>${List_Kcb_Segment030.cd_account_before6}</td>
<td>${List_Kcb_Segment030.cd_account_before7}</td>
<td>${List_Kcb_Segment030.cd_account_before8}</td>
<td>${List_Kcb_Segment030.cd_account_before9}</td>
<td>${List_Kcb_Segment030.cd_account_before10}</td>
<td>${List_Kcb_Segment030.cd_account_before11}</td>
<td>${List_Kcb_Segment030.cd_account_before12}</td>
<td>${List_Kcb_Segment030.filler}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list034InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>1개월전대출건수 </th>
<th>2개월전대출건수 </th>
<th>3개월전대출건수 </th>
<th>4개월전대출건수 </th>
<th>5개월전대출건수 </th>
<th>6개월전대출건수 </th>
<th>7개월전대출건수 </th>
<th>8개월전대출건수 </th>
<th>9개월전대출건수 </th>
<th>10개월전대출건수</th>
<th>11개월전대출건수</th>
<th>12개월전대출건수</th>
<th>1개월전대출금액 </th>
<th>2개월전대출금액 </th>
<th>3개월전대출금액 </th>
<th>4개월전대출금액 </th>
<th>5개월전대출금액 </th>
<th>6개월전대출금액 </th>
<th>7개월전대출금액 </th>
<th>8개월전대출금액 </th>
<th>9개월전대출금액 </th>
<th>10개월전대출금액</th>
<th>11개월전대출금액</th>
<th>12개월전대출금액</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment034}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment034" items="${Kcb_Segment034}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment034.segID}</td>
<td>${List_Kcb_Segment034.cnt_loan_before1}</td>
<td>${List_Kcb_Segment034.cnt_loan_before2}</td>
<td>${List_Kcb_Segment034.cnt_loan_before3}</td>
<td>${List_Kcb_Segment034.cnt_loan_before4}</td>
<td>${List_Kcb_Segment034.cnt_loan_before5}</td>
<td>${List_Kcb_Segment034.cnt_loan_before6}</td>
<td>${List_Kcb_Segment034.cnt_loan_before7}</td>
<td>${List_Kcb_Segment034.cnt_loan_before8}</td>
<td>${List_Kcb_Segment034.cnt_loan_before9}</td>
<td>${List_Kcb_Segment034.cnt_loan_before10}</td>
<td>${List_Kcb_Segment034.cnt_loan_before11}</td>
<td>${List_Kcb_Segment034.cnt_loan_before12}</td>
<td>${List_Kcb_Segment034.amt_loan_before1}</td>
<td>${List_Kcb_Segment034.amt_loan_before2}</td>
<td>${List_Kcb_Segment034.amt_loan_before3}</td>
<td>${List_Kcb_Segment034.amt_loan_before4}</td>
<td>${List_Kcb_Segment034.amt_loan_before5}</td>
<td>${List_Kcb_Segment034.amt_loan_before6}</td>
<td>${List_Kcb_Segment034.amt_loan_before7}</td>
<td>${List_Kcb_Segment034.amt_loan_before8}</td>
<td>${List_Kcb_Segment034.amt_loan_before9}</td>
<td>${List_Kcb_Segment034.amt_loan_before10}</td>
<td>${List_Kcb_Segment034.amt_loan_before11}</td>
<td>${List_Kcb_Segment034.amt_loan_before12}</td>
<td>${List_Kcb_Segment034.filler}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list035InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>1개월전연체건수 </th>
<th>2개월전연체건수 </th>
<th>3개월전연체건수 </th>
<th>4개월전연체건수 </th>
<th>5개월전연체건수 </th>
<th>6개월전연체건수 </th>
<th>7개월전연체건수 </th>
<th>8개월전연체건수 </th>
<th>9개월전연체건수 </th>
<th>10개월전연체건수</th>
<th>11개월전연체건수</th>
<th>12개월전연체건수</th>
<th>1개월전연체금액 </th>
<th>2개월전연체금액 </th>
<th>3개월전연체금액 </th>
<th>4개월전연체금액 </th>
<th>5개월전연체금액 </th>
<th>6개월전연체금액 </th>
<th>7개월전연체금액 </th>
<th>8개월전연체금액 </th>
<th>9개월전연체금액 </th>
<th>10개월전연체금액</th>
<th>11개월전연체금액</th>
<th>12개월전연체금액</th>
<th>1개월전최장연체일수</th>
<th>2개월전최장연체일수</th>
<th>3개월전최장연체일수</th>
<th>4개월전최장연체일수</th>
<th>5개월전최장연체일수</th>
<th>6개월전최장연체일수</th>
<th>7개월전최장연체일수</th>
<th>8개월전최장연체일수</th>
<th>9개월전최장연체일수</th>
<th>10개월전최장연체일수</th>
<th>11개월전최장연체일수</th>
<th>12개월전최장연체일수</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment035}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment035" items="${Kcb_Segment035}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment035.segID}</td>
<td>${List_Kcb_Segment035.cnt_delay_before1}</td>
<td>${List_Kcb_Segment035.cnt_delay_before2}</td>
<td>${List_Kcb_Segment035.cnt_delay_before3}</td>
<td>${List_Kcb_Segment035.cnt_delay_before4}</td>
<td>${List_Kcb_Segment035.cnt_delay_before5}</td>
<td>${List_Kcb_Segment035.cnt_delay_before6}</td>
<td>${List_Kcb_Segment035.cnt_delay_before7}</td>
<td>${List_Kcb_Segment035.cnt_delay_before8}</td>
<td>${List_Kcb_Segment035.cnt_delay_before9}</td>
<td>${List_Kcb_Segment035.cnt_delay_before10}</td>
<td>${List_Kcb_Segment035.cnt_delay_before11}</td>
<td>${List_Kcb_Segment035.cnt_delay_before12}</td>
<td>${List_Kcb_Segment035.amt_delay_before1}</td>
<td>${List_Kcb_Segment035.amt_delay_before2}</td>
<td>${List_Kcb_Segment035.amt_delay_before3}</td>
<td>${List_Kcb_Segment035.amt_delay_before4}</td>
<td>${List_Kcb_Segment035.amt_delay_before5}</td>
<td>${List_Kcb_Segment035.amt_delay_before6}</td>
<td>${List_Kcb_Segment035.amt_delay_before7}</td>
<td>${List_Kcb_Segment035.amt_delay_before8}</td>
<td>${List_Kcb_Segment035.amt_delay_before9}</td>
<td>${List_Kcb_Segment035.amt_delay_before10}</td>
<td>${List_Kcb_Segment035.amt_delay_before11}</td>
<td>${List_Kcb_Segment035.amt_delay_before12}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before1}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before2}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before3}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before4}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before5}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before6}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before7}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before8}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before9}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before10}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before11}</td>
<td>${List_Kcb_Segment035.cnt_longest_day_before12}</td>
<td>${List_Kcb_Segment035.filler}</td>

				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list041InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>정보관리번호 </th>
<th>업종명 </th>
<th>거래형태코드 </th>
<th>신용여부</th>
<th>담보여부</th>
<th>보증인여부</th>
<th>일부결제금액이월약정(리볼빙)여부</th>
<th>개설일자</th>
<th>해지일자</th>
<th>해지사유코드 </th>
<th>거래정지여부 </th>
<th>회원상태코드 </th>
<th>가족카드발급여부</th>
<th>신용회복지원여부</th>
<th>1개월전연체금액 </th>
<th>2개월전연체금액 </th>
<th>3개월전연체금액 </th>
<th>4개월전연체금액 </th>
<th>5개월전연체금액 </th>
<th>6개월전연체금액 </th>
<th>7개월전연체금액 </th>
<th>8개월전연체금액 </th>
<th>9개월전연체금액 </th>
<th>10개월전연체금액</th>
<th>11개월전연체금액</th>
<th>12개월전연체금액</th>
<th>1개월전거래상태코드</th>
<th>2개월전거래상태코드</th>
<th>3개월전거래상태코드</th>
<th>4개월전거래상태코드</th>
<th>5개월전거래상태코드</th>
<th>6개월전거래상태코드</th>
<th>7개월전거래상태코드</th>
<th>8개월전거래상태코드</th>
<th>9개월전거래상태코드</th>
<th>10개월전거래상태코드</th>
<th>11개월전거래상태코드</th>
<th>12개월전거래상태코드</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment041}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment041" items="${Kcb_Segment041}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment041.segId}</td>
<td>${List_Kcb_Segment041.no_manage_info}</td>
<td>${List_Kcb_Segment041.nm_biz_type}</td>
<td>${List_Kcb_Segment041.cd_type_deal}</td>
<td>${List_Kcb_Segment041.yn_credit}</td>
<td>${List_Kcb_Segment041.yn_loan}</td>
<td>${List_Kcb_Segment041.yn_guarantor}</td>
<td>${List_Kcb_Segment041.yn_revolving}</td>
<td>${List_Kcb_Segment041.ymd_open}</td>
<td>${List_Kcb_Segment041.ymd_cancel}</td>
<td>${List_Kcb_Segment041.cd_cancel}</td>
<td>${List_Kcb_Segment041.yn_stop}</td>
<td>${List_Kcb_Segment041.cd_status_member}</td>
<td>${List_Kcb_Segment041.yn_family_card}</td>
<td>${List_Kcb_Segment041.yn_recovery}</td>
<td>${List_Kcb_Segment041.amt_delay_before1}</td>
<td>${List_Kcb_Segment041.amt_delay_before2}</td>
<td>${List_Kcb_Segment041.amt_delay_before3}</td>
<td>${List_Kcb_Segment041.amt_delay_before4}</td>
<td>${List_Kcb_Segment041.amt_delay_before5}</td>
<td>${List_Kcb_Segment041.amt_delay_before6}</td>
<td>${List_Kcb_Segment041.amt_delay_before7}</td>
<td>${List_Kcb_Segment041.amt_delay_before8}</td>
<td>${List_Kcb_Segment041.amt_delay_before9}</td>
<td>${List_Kcb_Segment041.amt_delay_before10}</td>
<td>${List_Kcb_Segment041.amt_delay_before11}</td>
<td>${List_Kcb_Segment041.amt_delay_before12}</td>
<td>${List_Kcb_Segment041.cd_trade_before1}</td>
<td>${List_Kcb_Segment041.cd_trade_before2}</td>
<td>${List_Kcb_Segment041.cd_trade_before3}</td>
<td>${List_Kcb_Segment041.cd_trade_before4}</td>
<td>${List_Kcb_Segment041.cd_trade_before5}</td>
<td>${List_Kcb_Segment041.cd_trade_before6}</td>
<td>${List_Kcb_Segment041.cd_trade_before7}</td>
<td>${List_Kcb_Segment041.cd_trade_before8}</td>
<td>${List_Kcb_Segment041.cd_trade_before9}</td>
<td>${List_Kcb_Segment041.cd_trade_before10}</td>
<td>${List_Kcb_Segment041.cd_trade_before11}</td>
<td>${List_Kcb_Segment041.cd_trade_before12}</td>
<td>${List_Kcb_Segment041.filler}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list045InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>1개월전신용카드수</th>
<th>2개월전신용카드수</th>
<th>3개월전신용카드수</th>
<th>4개월전신용카드수</th>
<th>5개월전신용카드수</th>
<th>6개월전신용카드수</th>
<th>7개월전신용카드수</th>
<th>8개월전신용카드수</th>
<th>9개월전신용카드수</th>
<th>10개월전신용카드수 </th>
<th>11개월전신용카드수 </th>
<th>12개월전신용카드수 </th>
<th>1개월전총한도합계금액 </th>
<th>2개월전총한도합계금액 </th>
<th>3개월전총한도합계금액 </th>
<th>4개월전총한도합계금액 </th>
<th>5개월전총한도합계금액 </th>
<th>6개월전총한도합계금액 </th>
<th>7개월전총한도합계금액 </th>
<th>8개월전총한도합계금액 </th>
<th>9개월전총한도합계금액 </th>
<th>10개월전총한도합계금액</th>
<th>11개월전총한도합계금액</th>
<th>12개월전총한도합계금액</th>
<th>1개월전단기카드대출(CA)한도합계금액</th>
<th>2개월전단기카드대출(CA)한도합계금액</th>
<th>3개월전단기카드대출(CA)한도합계금액</th>
<th>4개월전단기카드대출(CA)한도합계금액</th>
<th>5개월전단기카드대출(CA)한도합계금액</th>
<th>6개월전단기카드대출(CA)한도합계금액</th>
<th>7개월전단기카드대출(CA)한도합계금액</th>
<th>8개월전단기카드대출(CA)한도합계금액</th>
<th>9개월전단기카드대출(CA)한도합계금액</th>
<th>10개월전단기카드대출(CA)한도합계금액 </th>
<th>11개월전단기카드대출(CA)한도합계금액 </th>
<th>12개월전단기카드대출(CA)한도합계금액 </th>
<th>1개월전이용카드수</th>
<th>2개월전이용카드수</th>
<th>3개월전이용카드수</th>
<th>4개월전이용카드수</th>
<th>5개월전이용카드수</th>
<th>6개월전이용카드수</th>
<th>7개월전이용카드수</th>
<th>8개월전이용카드수</th>
<th>9개월전이용카드수</th>
<th>10개월전이용카드수 </th>
<th>11개월전이용카드수 </th>
<th>12개월전이용카드수 </th>
<th>1개월전총이용금액</th>
<th>2개월전총이용금액</th>
<th>3개월전총이용금액</th>
<th>4개월전총이용금액</th>
<th>5개월전총이용금액</th>
<th>6개월전총이용금액</th>
<th>7개월전총이용금액</th>
<th>8개월전총이용금액</th>
<th>9개월전총이용금액</th>
<th>10개월전총이용금액 </th>
<th>11개월전총이용금액 </th>
<th>12개월전총이용금액 </th>
<th>1개월전단기카드대출(CA)이용카드수 </th>
<th>2개월전단기카드대출(CA)이용카드수 </th>
<th>3개월전단기카드대출(CA)이용카드수 </th>
<th>4개월전단기카드대출(CA)이용카드수 </th>
<th>5개월전단기카드대출(CA)이용카드수 </th>
<th>6개월전단기카드대출(CA)이용카드수 </th>
<th>7개월전단기카드대출(CA)이용카드수 </th>
<th>8개월전단기카드대출(CA)이용카드수 </th>
<th>9개월전단기카드대출(CA)이용카드수 </th>
<th>10개월전단기카드대출(CA)이용카드수</th>
<th>11개월전단기카드대출(CA)이용카드수</th>
<th>12개월전단기카드대출(CA)이용카드수</th>
<th>1개월전단기카드대출(CA)이용금액</th>
<th>2개월전단기카드대출(CA)이용금액</th>
<th>3개월전단기카드대출(CA)이용금액</th>
<th>4개월전단기카드대출(CA)이용금액</th>
<th>5개월전단기카드대출(CA)이용금액</th>
<th>6개월전단기카드대출(CA)이용금액</th>
<th>7개월전단기카드대출(CA)이용금액</th>
<th>8개월전단기카드대출(CA)이용금액</th>
<th>9개월전단기카드대출(CA)이용금액</th>
<th>10개월전단기카드대출(CA)이용금액</th>
<th>11개월전단기카드대출(CA)이용금액</th>
<th>12개월전단기카드대출(CA)이용금액</th>
<th>1개월전할부이용카드수 </th>
<th>2개월전할부이용카드수 </th>
<th>3개월전할부이용카드수 </th>
<th>4개월전할부이용카드수 </th>
<th>5개월전할부이용카드수 </th>
<th>6개월전할부이용카드수 </th>
<th>7개월전할부이용카드수 </th>
<th>8개월전할부이용카드수 </th>
<th>9개월전할부이용카드수 </th>
<th>10개월전할부이용카드수</th>
<th>11개월전할부이용카드수</th>
<th>12개월전할부이용카드수</th>
<th>1개월전할부이용금액</th>
<th>2개월전할부이용금액</th>
<th>3개월전할부이용금액</th>
<th>4개월전할부이용금액</th>
<th>5개월전할부이용금액</th>
<th>6개월전할부이용금액</th>
<th>7개월전할부이용금액</th>
<th>8개월전할부이용금액</th>
<th>9개월전할부이용금액</th>
<th>10개월전할부이용금액</th>
<th>11개월전할부이용금액</th>
<th>12개월전할부이용금액</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment045}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment045" items="${Kcb_Segment045}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment045.segID}</td>
<td>${List_Kcb_Segment045.callSegIdByMesg}</td>
<td>${List_Kcb_Segment045.cnt_card_before1}</td>
<td>${List_Kcb_Segment045.cnt_card_before2}</td>
<td>${List_Kcb_Segment045.cnt_card_before3}</td>
<td>${List_Kcb_Segment045.cnt_card_before4}</td>
<td>${List_Kcb_Segment045.cnt_card_before5}</td>
<td>${List_Kcb_Segment045.cnt_card_before6}</td>
<td>${List_Kcb_Segment045.cnt_card_before7}</td>
<td>${List_Kcb_Segment045.cnt_card_before8}</td>
<td>${List_Kcb_Segment045.cnt_card_before9}</td>
<td>${List_Kcb_Segment045.cnt_card_before10}</td>
<td>${List_Kcb_Segment045.cnt_card_before11}</td>
<td>${List_Kcb_Segment045.cnt_card_before12}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before1}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before2}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before3}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before4}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before5}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before6}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before7}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before8}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before9}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before10}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before11}</td>
<td>${List_Kcb_Segment045.amt_limit_sum_before12}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before1}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before2}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before3}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before4}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before5}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before6}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before7}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before8}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before9}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before10}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before11}</td>
<td>${List_Kcb_Segment045.amt_ca_sum_before12}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before1}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before2}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before3}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before4}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before5}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before6}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before7}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before8}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before9}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before10}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before11}</td>
<td>${List_Kcb_Segment045.cnt_use_card_before12}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before1}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before2}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before3}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before4}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before5}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before6}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before7}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before8}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before9}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before10}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before11}</td>
<td>${List_Kcb_Segment045.amt_all_sum_before12}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before1}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before2}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before3}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before4}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before5}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before6}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before7}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before8}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before9}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before10}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before11}</td>
<td>${List_Kcb_Segment045.cnt_ca_use_card_before12}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before1}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before2}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before3}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before4}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before5}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before6}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before7}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before8}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before9}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before10}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before11}</td>
<td>${List_Kcb_Segment045.amt_ca_use_card_before12}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before1}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before2}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before3}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before4}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before5}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before6}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before7}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before8}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before9}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before10}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before11}</td>
<td>${List_Kcb_Segment045.cnt_install_use_card_before12}</td>
<td>${List_Kcb_Segment045.amt_install_use_before1}</td>
<td>${List_Kcb_Segment045.amt_install_use_before2}</td>
<td>${List_Kcb_Segment045.amt_install_use_before3}</td>
<td>${List_Kcb_Segment045.amt_install_use_before4}</td>
<td>${List_Kcb_Segment045.amt_install_use_before5}</td>
<td>${List_Kcb_Segment045.amt_install_use_before6}</td>
<td>${List_Kcb_Segment045.amt_install_use_before7}</td>
<td>${List_Kcb_Segment045.amt_install_use_before8}</td>
<td>${List_Kcb_Segment045.amt_install_use_before9}</td>
<td>${List_Kcb_Segment045.amt_install_use_before10}</td>
<td>${List_Kcb_Segment045.amt_install_use_before11}</td>
<td>${List_Kcb_Segment045.amt_install_use_before12}</td>

				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list046InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>1개월전연체건수 </th>
<th>2개월전연체건수 </th>
<th>3개월전연체건수 </th>
<th>4개월전연체건수 </th>
<th>5개월전연체건수 </th>
<th>6개월전연체건수 </th>
<th>7개월전연체건수 </th>
<th>8개월전연체건수 </th>
<th>9개월전연체건수 </th>
<th>10개월전연체건수</th>
<th>11개월전연체건수</th>
<th>12개월전연체건수</th>
<th>1개월전연체금액 </th>
<th>2개월전연체금액 </th>
<th>3개월전연체금액 </th>
<th>4개월전연체금액 </th>
<th>5개월전연체금액 </th>
<th>6개월전연체금액 </th>
<th>7개월전연체금액 </th>
<th>8개월전연체금액 </th>
<th>9개월전연체금액 </th>
<th>10개월전연체금액</th>
<th>11개월전연체금액</th>
<th>12개월전연체금액</th>
<th>1개월전최장연체일수</th>
<th>2개월전최장연체일수</th>
<th>3개월전최장연체일수</th>
<th>4개월전최장연체일수</th>
<th>5개월전최장연체일수</th>
<th>6개월전최장연체일수</th>
<th>7개월전최장연체일수</th>
<th>8개월전최장연체일수</th>
<th>9개월전최장연체일수</th>
<th>10개월전최장연체일수</th>
<th>11개월전최장연체일수</th>
<th>12개월전최장연체일수</th>
<th>FILLER</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment046}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment046" items="${Kcb_Segment046}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment046.segId}</td>
<td>${List_Kcb_Segment046.cnt_delay_before1}</td>
<td>${List_Kcb_Segment046.cnt_delay_before2}</td>
<td>${List_Kcb_Segment046.cnt_delay_before3}</td>
<td>${List_Kcb_Segment046.cnt_delay_before4}</td>
<td>${List_Kcb_Segment046.cnt_delay_before5}</td>
<td>${List_Kcb_Segment046.cnt_delay_before6}</td>
<td>${List_Kcb_Segment046.cnt_delay_before7}</td>
<td>${List_Kcb_Segment046.cnt_delay_before8}</td>
<td>${List_Kcb_Segment046.cnt_delay_before9}</td>
<td>${List_Kcb_Segment046.cnt_delay_before10}</td>
<td>${List_Kcb_Segment046.cnt_delay_before11}</td>
<td>${List_Kcb_Segment046.cnt_delay_before12}</td>
<td>${List_Kcb_Segment046.amt_delay_before1}</td>
<td>${List_Kcb_Segment046.amt_delay_before2}</td>
<td>${List_Kcb_Segment046.amt_delay_before3}</td>
<td>${List_Kcb_Segment046.amt_delay_before4}</td>
<td>${List_Kcb_Segment046.amt_delay_before5}</td>
<td>${List_Kcb_Segment046.amt_delay_before6}</td>
<td>${List_Kcb_Segment046.amt_delay_before7}</td>
<td>${List_Kcb_Segment046.amt_delay_before8}</td>
<td>${List_Kcb_Segment046.amt_delay_before9}</td>
<td>${List_Kcb_Segment046.amt_delay_before10}</td>
<td>${List_Kcb_Segment046.amt_delay_before11}</td>
<td>${List_Kcb_Segment046.amt_delay_before12}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before1}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before2}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before3}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before4}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before5}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before6}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before7}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before8}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before9}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before10}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before11}</td>
<td>${List_Kcb_Segment046.cnt_longest_day_before12}</td>
<td>${List_Kcb_Segment046.FILLER}</td>

				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list061InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>업종명 </th>
<th>거래기관명</th>
<th>관리점명</th>
<th>거래형태코드 </th>
<th>최초연체기산일자</th>
<th>연체기산일자 </th>
<th>최초연체금액 </th>
<th>연체금액</th>
<th>만기후연체여부</th>
<th>기한이익상실코드</th>
<th>대출잔액/미도래잔액</th>
<th>연체상환일자 </th>
<th>상환금액</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment061}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment061" items="${Kcb_Segment061}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment061.segId}</td>
<td>${List_Kcb_Segment061.nm_biz_type}</td>
<td>${List_Kcb_Segment061.nm_trade}</td>
<td>${List_Kcb_Segment061.nm_agency}</td>
<td>${List_Kcb_Segment061.cd_type_deal}</td>
<td>${List_Kcb_Segment061.ymd_frt_delay}</td>
<td>${List_Kcb_Segment061.ymd_delay}</td>
<td>${List_Kcb_Segment061.amt_frt_delay}</td>
<td>${List_Kcb_Segment061.amt_delay}</td>
<td>${List_Kcb_Segment061.yn_mat_delay}</td>
<td>${List_Kcb_Segment061.cd_profit_loss}</td>
<td>${List_Kcb_Segment061.amt_remain}</td>
<td>${List_Kcb_Segment061.ymd_repay}</td>
<td>${List_Kcb_Segment061.amt_repay}</td>
<td>${List_Kcb_Segment061.filler}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list062InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>업종명 </th>
<th>거래기관명</th>
<th>관리점명</th>
<th>대지급발생일자</th>
<th>대지급금액</th>
<th>대지급상환일자</th>
<th>상환금액</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment062}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment062" items="${Kcb_Segment062}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment062.segId}</td>
<td>${List_Kcb_Segment062.nm_biz_type}</td>
<td>${List_Kcb_Segment062.nm_trade}</td>
<td>${List_Kcb_Segment062.nm_agency}</td>
<td>${List_Kcb_Segment062.ymd_frt_stead_pay}</td>
<td>${List_Kcb_Segment062.amt_stead_pay}</td>
<td>${List_Kcb_Segment062.ymd_stead_repay}</td>
<td>${List_Kcb_Segment062.amt_repay}</td>
<td>${List_Kcb_Segment062.filler}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list065InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>거래기관명</th>
<th>관리점명</th>
<th>채무불이행(신용정보사)등록사유코드</th>
<th>채무불이행(신용정보사)발생일자 </th>
<th>등록금액</th>
<th>연체금액</th>
<th>상환일자</th>
<th>상환금액</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment065}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment065" items="${Kcb_Segment065}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment065.segId}</td>
<td>${List_Kcb_Segment065.nm_trade}</td>
<td>${List_Kcb_Segment065.nm_agency}</td>
<td>${List_Kcb_Segment065.cd_default}</td>
<td>${List_Kcb_Segment065.ymd_default}</td>
<td>${List_Kcb_Segment065.amt_regist}</td>
<td>${List_Kcb_Segment065.amt_delay}</td>
<td>${List_Kcb_Segment065.ymd_repay}</td>
<td>${List_Kcb_Segment065.amt_repay}</td>
<td>${List_Kcb_Segment065.filler}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list103InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>프로파일 코드</th>
<th>프로파일 결과값 </th>

				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment103}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment103" items="${Kcb_Segment103}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment103.segID}</td>
<td>${List_Kcb_Segment103.cd_profile}</td>
<td>${List_Kcb_Segment103.result_profile}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table id="list105InfoTable" class="table table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
<th>Segment ID</th>
<th>스코어구분코드</th>
<th>1개월전 신용평점</th>
<th>2개월전 신용평점</th>
<th>3개월전 신용평점</th>
<th>4개월전 신용평점</th>
<th>5개월전 신용평점</th>
<th>6개월전 신용평점</th>
<th>7개월전 신용평점</th>
<th>8개월전 신용평점</th>
<th>9개월전 신용평점</th>
<th>10개월전 신용평점</th>
<th>11개월전 신용평점</th>
<th>12개월전 신용평점</th>
<th>1개월전 신용등급</th>
<th>2개월전 신용등급</th>
<th>3개월전 신용등급</th>
<th>4개월전 신용등급</th>
<th>5개월전 신용등급</th>
<th>6개월전 신용등급</th>
<th>7개월전 신용등급</th>
<th>8개월전 신용등급</th>
<th>9개월전 신용등급</th>
<th>10개월전 신용등급</th>
<th>11개월전 신용등급</th>
<th>12개월전 신용등급</th>
<th>FILLER </th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty Kcb_Segment105}">
				<tr>
					<td colspan="10" height="100" align="center">
						검색결과가 없습니다
					</td>
				</tr>
			</c:if>
			<c:forEach var="List_Kcb_Segment105" items="${Kcb_Segment105}" varStatus="status">
				<tr>
<td>${List_Kcb_Segment105.segID}</td>
<td>${List_Kcb_Segment105.cd_score}</td>
<td>${List_Kcb_Segment105.score_credit_before1}</td>
<td>${List_Kcb_Segment105.score_credit_before2}</td>
<td>${List_Kcb_Segment105.score_credit_before3}</td>
<td>${List_Kcb_Segment105.score_credit_before4}</td>
<td>${List_Kcb_Segment105.score_credit_before5}</td>
<td>${List_Kcb_Segment105.score_credit_before6}</td>
<td>${List_Kcb_Segment105.score_credit_before7}</td>
<td>${List_Kcb_Segment105.score_credit_before8}</td>
<td>${List_Kcb_Segment105.score_credit_before9}</td>
<td>${List_Kcb_Segment105.score_credit_before10}</td>
<td>${List_Kcb_Segment105.score_credit_before11}</td>
<td>${List_Kcb_Segment105.score_credit_before12}</td>
<td>${List_Kcb_Segment105.grade_credit_before1}</td>
<td>${List_Kcb_Segment105.grade_credit_before2}</td>
<td>${List_Kcb_Segment105.grade_credit_before3}</td>
<td>${List_Kcb_Segment105.grade_credit_before4}</td>
<td>${List_Kcb_Segment105.grade_credit_before5}</td>
<td>${List_Kcb_Segment105.grade_credit_before6}</td>
<td>${List_Kcb_Segment105.grade_credit_before7}</td>
<td>${List_Kcb_Segment105.grade_credit_before8}</td>
<td>${List_Kcb_Segment105.grade_credit_before9}</td>
<td>${List_Kcb_Segment105.grade_credit_before10}</td>
<td>${List_Kcb_Segment105.grade_credit_before11}</td>
<td>${List_Kcb_Segment105.grade_credit_before12}</td>
<td>${List_Kcb_Segment105.filler}</td>			
				</tr>
			</c:forEach>
			</tbody>
		</table>
