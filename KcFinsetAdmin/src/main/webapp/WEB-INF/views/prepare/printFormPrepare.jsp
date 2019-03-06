<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>고객 신청서</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">신청정보</div>
		<div class="panel-collapse">
			<table class="table table-classic tbl-info">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>광고매체</th>
						<td>
							${ufn:getCodeName("cd_advertisement", prepareVO.cd_advertisement)}
						</td>
						<th>상품구분</th>
						<td>
							${ufn:getCodeName("cd_goods_type", prepareVO.cd_goods_type)}
						</td>
					</tr>
					<tr>
						<th>신청금액</th>
						<td>
							${prepareVO.amt_apply} 만원
						</td>
						<th>신청일시</th>
						<td>${prepareVO.dt_frt}</td>
					</tr>
					<tr>
						<th>사용용도</th>
						<td>
							${ufn:getCodeName("cd_used_apply", prepareVO.cd_used_apply)}
						</td>
						<th>유입키워드</th>
						<td>
							<c:if test="${!empty prepareVO.c6_keyword}">
								${ufn:getCodeName('cd_keyword_comp', fn:substring(prepareVO.c6_keyword,0,1))}-${ufn:getCodeName('cd_keyword', fn:substring(prepareVO.c6_keyword,1,5))}
							</c:if>
						</td>
					</tr>
					<tr>
						<th>서류함</th>
						<td>
							${ufn:getCodeName('cd_prepare_doc_box', prepareVO.cd_prepare_doc_box)}
							<c:if test="'60'.equals(prepareVO.cd_prepare_doc_box)">
								(${ufn:getCodeName("cd_reject_cause", prepareVO.cd_reject_cause)})
							</c:if>
							<c:if test="'20'.equals(prepareVO.cd_prepare_doc_box)">
								(${ufn:getCodeName("cd_prepare_class", prepareVO.cd_prepare_class)})
							</c:if>
						</td>
						<th>담당자</th>
						<td>
							${ufn:getWorkerInfo(prepareVO.id_prepare,'NM')}
						</td>
					</tr>
					<tr>
						<th>접수 시 메모</th>
						<td colspan="3">
							${prepareVO.memo_from_agency}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<c:if test="${!empty prepareVO.etc_prepare_path}">
		<div class="panel panel-primary">
			<div class="panel-heading">접수경로</div>
			<div class="panel-collapse">
				<table class="table table-bordered">
					<thead> 
						<tr>
					        <th>접수순위</th>
							<th>접수일자</th>
							<th>상호</th>
							<th>등록번호</th>
							<th>협회등록번호</th>
							<th>전화번호</th>
						</tr> 
					</thead>
					<tbody>
						<c:forEach var="List" items="${ufn:getApplyPath(prepareVO.etc_prepare_path)}" varStatus="status">
							<tr>
								<c:set var="path_data" value="${fn:split(List,'||')}"/>
								<td>${path_data[0]}</td>
								<td>${ufn:formatDate(path_data[1])}</td>
								<td>${path_data[2]}</td>
								<td>${path_data[3]}</td>
								<td>${path_data[4]}</td>
								<td>${path_data[5]}</td>
							</tr>							
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="${!empty prepareVO.cd_collect_path}">
	<div class="panel panel-primary">
		<div class="panel-heading">적법수집확인</div>
		<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
					<col width="15%">
					<col width="25%">
					<col width="15%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>최초수집경로</th>
						<td>
							${ufn:getCodeName("cd_collect_path", prepareVO.cd_collect_path)}
						</td>
						<th>연락처수집경로</th>
						<td>
							<c:if test="${prepareVO.cd_collect_path eq '01'}">
								${ufn:getCodeName("cd_contact_path_01", prepareVO.cd_contact_path)}
							</c:if>
							<c:if test="${prepareVO.cd_collect_path eq '02'}">
								${ufn:getCodeName("cd_contact_path_02", prepareVO.cd_contact_path)}
							</c:if>
						</td>
					</tr>
					<tr>
						<th>고객 연락 방법</th>
						<td>
							${ufn:getCodeName("cd_collect_method", prepareVO.cd_collect_method)}
						</td>
						<th>기타경로(메모)</th>
						<td>
							${prepareVO.etc_memo}
						</td>
					</tr>
					<tr>
						<th>제휴사명</th>
						<td>
							${prepareVO.nm_agency}
						</td>
						<th>대표자</th>
						<td>
							${prepareVO.nm_ceo_agency}
						</td>
					<tr>
						<th>홈페이지</th>
						<td>
							${prepareVO.url_homepage_agency}
						</td>
						<th>작성자</th>
						<td>
							${prepareVO.nm_writer}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</c:if>
	<div class="panel panel-primary">
		<div class="panel-heading">기본정보</div>
		<div class="panel-collapse">
			<table class="table table-classic tbl-info">
				<colgroup>
					<col width="13%">
					<col width="12%">
					<col width="12%">
					<col width="13%">
					<col width="12%">
					<col width="10%">
					<col width="13%">
					<col width="*%">
				</colgroup>
				<tbody>
					<tr>
						<th>성명</th>
						<td colspan="2">
							${personVO.nm_person}
						</td>
						<th>생년월일</th>
						<td colspan="4">${ufn:formatDate(personVO.ymd_birth)}</td>
					</tr>
					<tr>
						
						<th>휴대전화</th>
						<td colspan="2">
							<c:choose>
			              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">
			              			${ufn:formatMaskHp(personVO.hp)}
			              		</c:when>
			              		<c:otherwise>${personVO.hp}</c:otherwise>
			              	</c:choose>
						</td>
						<th>휴대폰명의</th>
						<td colspan="2">
							${personVO.nm_hp_owner}[${personVO.rel_hp_owner}]
						</td>
						<th>휴대폰상태</th>
						<td>${ufn:getCodeName("cd_ph_status", personVO.cd_status_hp)}</td>
					</tr>
					<tr>
						<th>자택전화</th>
						<td colspan="2">
							${personVO.ph_home}
						</td>
						<th>이메일</th>
						<td colspan="4">
							${personVO.email}
						</td>
					</tr>
					<tr>
						<th>자택주소</th>
						<td colspan="7">
							[${personVO.post6_home},${personVO.post5_home}] 
							${personVO.addr1_home} 
							${personVO.addr2_home} 
						</td>
					</tr>
					<tr>
						<th>등본주소</th>
						<td colspan="7">
							[${personVO.post6_reg},${personVO.post5_reg}] 
							${personVO.addr1_reg} 
							${personVO.addr2_reg} 
						</td>
					</tr>
					<tr>
						<th>주거종류</th>
						<td colspan="2">
							[${ufn:getCodeName('cd_house_type', personVO.cd_house_type_home)}] ${personVO.ym_house_reg_idx1}년 ${personVO.ym_house_reg_idx2}월
						</td>
						<th>주거형태</th>
						<td colspan="4">
							[${ufn:getCodeName('cd_live_type', personVO.cd_live_type_reg)}] 보증금:${personVO.amt_rent_deposit_reg} ,월세:${personVO.amt_rent_monthly_reg}
						</td>
					</tr>
					<tr>
						<th>은행계좌</th>
						<td colspan="7">${ufn:getCodeName('c3_bank', personVO.c3_bank)} ${personVO.account_bank}</td>
					</tr>
					<tr>
						<th>결혼여부</th>
						<td colspan="2">${ufn:getCodeName("cd_marry", personVO.cd_marry)}</td>
						<th>군필여부</th>
						<td colspan="4">${ufn:getCodeName("cd_army", personVO.cd_army)}</td>
					</tr>
					<tr>
						<th>자격구분</th>
						<td colspan="7">${ufn:getCodeName("cd_job_class", personVO.cd_job_class)}</td>
					</tr>
					<tr>
						<th>메모</th>
						<td colspan="7">${personVO.etc_memo}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!--급여, 주부-급여, 자유소득, 기타-->
	<c:if test="${'10' eq personVO.cd_job_class || '30' eq personVO.cd_job_class || '60' eq personVO.cd_job_class || 
					'99' eq personVO.cd_job_class || empty personVO.cd_job_class}">
	<div class="panel panel-primary">
		<div class="panel-heading">직장정보</div>
		<div class="panel-collapse">
			<table class="table table-classic tbl-info">
				<colgroup>
					<col width="12%">
					<col width="13%">
					<col width="12%">
					<col width="13%">
					<col width="12%">
					<col width="13%">
					<col width="12%">
					<col width="13%">
				</colgroup>
				<tbody>
					<tr>
						<th>직장명</th>
						<td>${personVO.nm_comp}</td>
						<th>부서명</th>
						<td>${personVO.nm_part_comp}</td>
						<th>직책</th>
						<td>${personVO.nm_duty_comp}</td>
						<th>고용형태</th>
						<td>${ufn:getCodeName('cd_employ_type', personVO.cd_employ_type)}</td>
					</tr>
					<tr>
						<th>직장전화</th>
						<td colspan="3">
							${personVO.ph_comp}
						</td>
						<th>직장팩스</th>
						<td colspan="3">
							${personVO.fax_comp}
						</td>
					</tr>
					<tr>
						<th>직장주소</th>
						<td colspan="7">
							[${personVO.post6_comp},${personVO.post5_comp}]
							${personVO.addr1_comp}
							${personVO.addr2_comp}
						</td>
					</tr>
					<tr>
						<th>근속연수</th>
						<td colspan="3">
							${ufn:getCodeName('cd_job_term', personVO.cd_job_term)}
						</td>
						<th>연소득</th>
						<td colspan="3">
							${ufn:formatNumber(personVO.amt_year_income)} 천원
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</c:if>
	<!-- 자영업, 주부-자영업 -->
	<c:if test="${'20' eq personVO.cd_job_class || '40' eq personVO.cd_job_class}">
	<div class="panel panel-primary">
		<div class="panel-heading">사업체정보</div>
		<div class="panel-collapse">
			<table class="table table-classic tbl-info">
					<colgroup>
						<col width="12%">
						<col width="13%">
						<col width="12%">
						<col width="13%">
						<col width="12%">
						<col width="13%">
						<col width="12%">
						<col width="13%">
					</colgroup>
					<tbody>
						<tr>
							<th>직장명</th>
							<td colspan="2">${personVO.nm_comp}</td>
							<th>사업자번호</th>
							<td colspan="2">${personVO.no_biz_licence}</td>
							<th>업종</th>
							<td>${ufn:getCodeName('cd_industry', personVO.cd_industry)}</td>
							
						</tr>
						<tr>
							<th>직장전화</th>
							<td colspan="2">
								${personVO.ph_comp}
							</td>
							<th>직장팩스</th>
							<td colspan="2">
								${personVO.fax_comp}
							</td>
							<th>근무인원</th>
							<td>${personVO.cnt_worker} 명</td>
							
						</tr>
						<tr>
							<th>직장주소</th>
							<td colspan="7">
								[${personVO.post6_comp},${personVO.post5_comp}]
								${personVO.addr1_comp}
								${personVO.addr2_comp}
							</td>
						</tr>
						<tr>
							<th>입사년월</th>
							<td colspan="3">
								${personVO.ym_start_comp_idx1}년
								${personVO.ym_start_comp_idx2}월
							</td>
							<th>연/월소득</th>
							<td colspan="3">
								${personVO.amt_year_income} 천원 / ${personVO.amt_monthly_income} 천원
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
	<!-- 대학생 -->
	<c:if test="${'50' eq personVO.cd_job_class}">
	<div class="panel panel-primary">
		<div class="panel-heading">학교정보</div>
		<div class="panel-collapse">
			<table class="table table-classic tbl-info">
				<table class="table table-classic tbl-info">
					<colgroup>
						<col width="12%">
						<col width="13%">
						<col width="12%">
						<col width="13%">
						<col width="12%">
						<col width="13%">
						<col width="12%">
						<col width="13%">
					</colgroup>
					<tbody>
						<tr>
							<th>학교명</th>
							<td colspan="2">${personVO.nm_univ}</td>
							<th>학과</th>
							<td colspan="2">${personVO.nm_part_univ}</td>
							<th>학년</th>
							<td>
								${ufn:getCodeName('cd_grade_univ', personVO.cd_grade_univ)}
							</td>
						</tr>
						<tr>
							<th>학교전화</th>
							<td colspan="2">
								${personVO.ph_univ}
							</td>
							<th>학번</th>
							<td colspan="2">${personVO.no_univ}</td>
							<th>재학상태</th>
							<td>
								${ufn:getCodeName('cd_attend_univ', personVO.cd_attend_univ)}
							</td>
						</tr>
						<tr>
							<th>학교주소</th>
							<td colspan="7">
								[${personVO.post6_univ},${personVO.post5_univ}]
								${personVO.addr1_univ}
								${personVO.addr2_univ}
							</td>
						</tr>
						<tr>
							<th>직장명</th>
							<td colspan="3">${personVO.nm_comp}</td>
							<th>입사년월</th>
							<td>
								${personVO.ym_start_comp_idx1}년
								${personVO.ym_start_comp_idx2}월
							</td>
							<th>월소득</th>
							<td>
								${personVO.amt_monthly_income} 천원
							</td>
						</tr>
						<tr>
							<th>직장전화</th>
							<td colspan="3">
								${personVO.ph_comp}
							</td>
							<th>직장팩스</th>
							<td colspan="3">
								${personVO.fax_comp}
							</td>
						</tr>
						<tr>
							<th>직장주소</th>
							<td colspan="7">
								[${personVO.post6_comp},${personVO.post5_comp}]
								${personVO.addr1_comp}
								${personVO.addr2_comp}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="${!empty grtPersonVO}">
	<div class="panel panel-primary">
		<div class="panel-heading">보증인정보</div>
		<div class="panel-collapse">
			<input type="hidden" id="no_person" value="${grtPersonVO.no_person}"/>
			<table class="table table-classic tbl-info">
				<colgroup>
					<col width="12%">
					<col width="13%">
					<col width="12%">
					<col width="13%">
					<col width="12%">
					<col width="13%">
					<col width="12%">
					<col width="13%">
				</colgroup>
				<tbody>
					<tr>
						<th>보증인</th>
						<td colspan="2">${grtPersonVO.nm_person}</td>
						<th>생년월일</th>
						<td colspan="2">${ufn:formatDate(grtPersonVO.ymd_birth)}</td>
						<th>관계</th>
						<td>
							<div class="input-group">
								${ufn:getCodeName("cd_rel", grtInfoVO.cd_rel_grt)}
							</div>
						</td>
					</tr>
					<tr>
						<th>휴대전화</th>
						<td colspan="3">
							<c:choose>
			              		<c:when test="${ufn:getNvlCodeName('_CONF_SYSTEM', 'CD_DEFCON', '05') lt '05'}">
				              		${ufn:formatMaskHp(grtPersonVO.hp)}
			              		</c:when>
			              		<c:otherwise>${grtPersonVO.hp}</c:otherwise>
			              	</c:choose>
						</td>
						<th>직장명</th>
						<td>${grtPersonVO.nm_comp}</td>
						<th>직책</th>
						<td>${grtPersonVO.nm_duty_comp}</td>
					</tr>
					<tr>
						<th>자택전화</th>
						<td colspan="3">${grtPersonVO.ph_home}</td>
						<th>근속연수</th>
						<td colspan="3">${ufn:getCodeName('cd_job_term', grtPersonVO.cd_job_term)}</td>
					</tr>
					<tr>
						<th>직장전화</th>
						<td colspan="3">${grtPersonVO.ph_comp}</td>
						<th>연소득</th>
						<td colspan="3">${ufn:formatNumber(grtPersonVO.amt_year_income)} 천원</td>
					</tr>
					<tr>
						<th>자택주소</th>
						<td colspan="7">
							[${grtPersonVO.post6_home},${grtPersonVO.post5_home}] 
							${grtPersonVO.addr1_home} 
							${grtPersonVO.addr1_home} 
						</td>
					</tr>
					<tr>
						<th>등본주소</th>
						<td colspan="7">
							[${grtPersonVO.post6_reg},${grtPersonVO.post5_reg}] 
							${grtPersonVO.addr1_reg} 
							${grtPersonVO.addr2_reg} 
						</td>
					</tr>
					<tr>
						<th>직장주소</th>
						<td colspan="7">
							[${grtPersonVO.post6_comp},${grtPersonVO.post5_comp}] 
							${grtPersonVO.addr1_comp} 
							${grtPersonVO.addr2_comp} 
						</td>
					</tr>
					<tr>
						<th>메모</th>
						<td colspan="7">${grtPersonVO.etc_memo}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</c:if>
	<c:if test="${!empty carVO}">	
	<div class="panel panel-primary">
		<div class="panel-heading">차량담보현황</div>
		<div class="panel-collapse">
			<table class="table table-classic tbl-info">
				<colgroup>
					<col width="12%">
					<col width="21%">
					<col width="12%">
					<col width="21%">
					<col width="12%">
					<col width="21%">
				</colgroup>
				<tbody>
					<tr>
						<th>차량번호</th>
						<td>${carVO.no_reg_car}</td>
						<th>차대번호</th>
						<td>${carVO.no_id_car}</td>
						<th>차종</th>
						<td>${carVO.nm_kind_car}</td>
					</tr>
					<tr>
						<th>차명</th>
						<td>${carVO.nm_car}</td>
						<th>용도</th>
						<td>${carVO.nm_used_car}</td>
						<th>연료</th>
						<td>${ufn:getCodeName("cd_fuel", carVO.cd_fuel)}</td>
					</tr>
					<tr>
						<th>제조사명</th>
						<td>${carVO.nm_comp_car}</td>
						<th>모델</th>
						<td colspan="3">${carVO.nm_model_car} ${carVO.year_car} 연식</td>
					</tr>
					<tr>
						<th>배기량</th>
						<td>${ufn:formatNumber(carVO.cc_car)} cc</td>
						<th>주행거리</th>
						<td>${ufn:formatNumber(carVO.mile_car)} km</td>
						<th>변속기</th>
						<td>${ufn:getCodeName("cd_gear", carVO.cd_gear)}</td>
					</tr>
					<tr>
						<th>차량색상</th>
						<td>${carVO.nm_color_car}</td>
						<th>신차가격</th>
						<td>${ufn:formatNumber(carVO.amt_new_car)} 만원</td>
						<th>중고시세</th>
						<td>${ufn:formatNumber(carVO.amt_now_car)} 만원</td>
					</tr>
					<tr>
						<th>사고유무</th>
						<td>${ufn:getCodeName("yn_accident", carVO.yn_accident)}</td>
						<th>사고내용</th>
						<td colspan="3">${carVO.memo_car}</td>
					</tr>
					<tr>
						<th>보험회사</th>
						<td>${carVO.nm_insu_car}</td>
						<th>보험종류</th>
						<td colspan="3">${carVO.nm_sort_insu_car} 기간 : ${carVO.cnt_term_insu_car}개월 / 회차 : ${carVO.cnt_rcpt_insu_car}회</td>
						
					</tr>
					<tr>
						<th>할부기관</th>
						<td>${ufn:getCodeName("cd_bank_insu_car", carVO.cd_bank_insu_car)}</td>
						<th>총할부금액</th>
						<td colspan="3">${ufn:formatNumber(carVO.amt_loan_car)}만원 기간 : ${carVO.cnt_total_loan_car}개월 / 회차 : ${carVO.cnt_rcpt_loan_car}회</td>
					</tr>
					<tr>
						<th>현재총잔액</th>
						<td>${ufn:formatNumber(carVO.amt_bal_loan_car)} 만원</td>
						<th>현재연체액</th>
						<td colspan="3">${ufn:formatNumber(carVO.amt_delay_loan_car)} 만원</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</c:if>
</body>
</html>
