<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script src="<c:url value="/script/jquery/jquery.tablednd.js"/>"></script>
<style>
.dragRow {
		    background-color: silver;
		}
</style>
<script type="text/javascript">
</script>
<form name="frmTest">
<table id="tbl_listKbMarketPriceOfferingsList" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>매물구분</th>
			<th>거래</th>
			<th>확인일자</th>
			<th>매물명</th>
			<th>면적</th>
			<th>동</th>
			<th>층</th>
			<th>매물가</th>
			<th>연락처</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${KbMarketPriceOfferingsList}" varStatus="status">
			<td>${List.OFFERINGS_TYPE}</td>
			<td>${List.TRANSACTION_METHOD}</td>
			<td>${List.CONFIRMED_DATE}</td>
			<td>${List.OFFERINGS_NAME}</td>
			<td>${List.OFFERINGS_AREA_SIZE}</td>
			<td>${List.BUILDING_DONG}</td>
			<td>${List.BUILDING_FLOOR}</td>
			<td>${List.OFFERINGS_PRICE}</td>
			<td>${List.TELEPHONE}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<table id="tbl_listKbMarketPricePriceList" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>시세갱신일</th>
			<th>면적인덱스</th>
			<th>면적</th>
			<th>단지명</th>
			<th>매매가_하위평균가</th>
			<th>매매가_일반평균가</th>
			<th>매매가_상위평균가</th>
			<th>전세가_하위평균가</th>
			<th>전세가_일반평균가</th>
			<th>전세가_상위평균가</th>
			<th>월세가_보증금</th>
			<th>월세가_월세</th>
			<th>매매가_전주대비변동액</th>
			<th>전세가_전주대비변동액</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${KbMarketPricePriceList}" varStatus="status">
			<td>${List.RENEWAL_DATE}</td>
			<td>${List.AREA_INDEX}</td>
			<td>${List.AREA_SIZE}</td>
			<td>${List.AREA_NAME}</td>
			<td>${List.SALE_LOWEST_AVERAGE}</td>
			<td>${List.SALE_GENERAL_AVERAGE}</td>
			<td>${List.SALE_HIGHEST_AVERAGE}</td>
			<td>${List.RENTAL_LOWEST_AVERAGE}</td>
			<td>${List.RENTAL_GENERAL_AVERAGE}</td>
			<td>${List.RENTAL_HIGHEST_AVERAGE}</td>
			<td>${List.RENTAL_DEPOSIT_AMOUNT}</td>
			<td>${List.RENTAL_PAYMENT_PERIOD}</td>
			<td>${List.SALE_CHANGE_BEFORWEEK_PRICE}</td>
			<td>${List.RENTAL_CHANGE_BEFORWEEK_PRICE}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<table id="tbl_listKbMarketPriceComplexList" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>총세대수</th>
			<th>총동수</th>
			<th>입주년월</th>
			<th>건설업체</th>
			<th>지번주소</th>
			<th>도로명주소</th>
			<th>난방방식</th>
			<th>난방연료</th>
			<th>총주차대수</th>
			<th>세대당주차대수</th>
			<th>용적률</th>
			<th>건폐율</th>
			<th>최고층</th>
			<th>최저층</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${KbMarketPriceComplexList}" varStatus="status">
			<td>${List.TOTAL_HOUSEHOLD}</td>
			<td>${List.TOTAL_BUILDING}</td>
			<td>${List.MOVE_IN_DATE}</td>
			<td>${List.CONSTRUCTION_COMPANY}</td>
			<td>${List.AREA_REGION}</td>
			<td>${List.AREA_STREET_NAME}</td>
			<td>${List.HEATING_METHOD}</td>
			<td>${List.HEATING_FUEL}</td>
			<td>${List.TOTAL_PARKING_NUM}</td>
			<td>${List.HOUSE_PARKING_NUM}</td>
			<td>${List.VOLUME_RATIO}</td>
			<td>${List.COVERAGE_RATIO}</td>
			<td>${List.FLOOR_HIGHEST}</td>
			<td>${List.FLOOR_LOWEST}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<table id="tbl_listKbMarketPricePyeongList" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>면적</th>
			<th>방수</th>
			<th>욕실수</th>
			<th>공급면적</th>
			<th>전용면적</th>
			<th>분양세대수</th>
			<th>현관구조</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="List" items="${KbMarketPricePyeongList}" varStatus="status">
			<td>${List.FLOOR_PLAN_AREA_SIZE}</td>
			<td>${List.FLOOR_PLAN_ROOM_NUM}</td>
			<td>${List.FLOOR_PLAN_BATHROOM_NUM}</td>
			<td>${List.FLOOR_PLAN_AREA_SUPPLY}</td>
			<td>${List.FLOOR_PLAN_AREA_DEDICATED}</td>
			<td>${List.FLOOR_PLAN_SELL_NUM}</td>
			<td>${List.FLOOR_PLAN_ENTRANCE_STRUCTURE}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</form>