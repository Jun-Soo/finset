<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">

</script>

<div class="panel panel-default toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span><a href="#none"> 고객 정보 입력란</a>
		</h3>
	</div>		
	<div id="formFinanceInfo">
		<div class="panel-collapse toggle-cont">
			<form name="frmMangeFinanceInfo" id="frmMangeFinanceInfo">
				<table id="manageFinanceInfo" class="table table-bordered" cellspacing="0" width="100%">
					<colgroup>
						<col width="20%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>대출진행상태</th>
							<th>대출지급금액</th>
							<th>대출금리</th>
							<th>대출일자</th>
							<th>사유</th>
						</tr>
					</thead>	
					<tbody>
						<tr>
							<td>
								<input type="text" class="form-control" name="lonProgressStatus" id="lonProgressStatus" value="" validate="required;label:대출진행상태; maxbt:1;" maxlength="1"/> <!-- value="${agencyVO.id_agency}" -->
							</td>
							<td>
								<input type="text" class="form-control" name="lonProgressStatus" id="lonProgressStatus" value="" validate="required;label:대출지급금액; maxbt:13;" maxlength="13"/> <!-- value="${agencyVO.id_agency}" -->
							</td>
							<td>
								<input type="text" class="form-control" name="lonRate" id="lonRate" value="" validate="required;label:대출금리; maxbt:5;" maxlength="5"/> <!-- value="${agencyVO.id_agency}" -->
							</td>
							<td>
								<input type="text" class="form-control" name="lonDate" id="lonDate" value="" validate="required;label:대출일자; maxbt:8;" maxlength="8"/> <!-- value="${agencyVO.id_agency}" -->
							</td>
							<td>
								<input type="text" class="form-control" name="lonReson" id="lonReson" value="" validate="required;label:사유; maxbt:127;" maxlength="127"/> <!-- value="${agencyVO.id_agency}" -->
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>
	
<%-- // load our paging tag, pass pagedListHolder and the link --%>
<tg:paging pagedListHolder="${pagedList}"/>