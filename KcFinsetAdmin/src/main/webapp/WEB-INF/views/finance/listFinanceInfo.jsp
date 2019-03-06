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
						<col width="16%">
						<col width="16%">
						<col width="16%">
						<col width="16%">
						<col width="16%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>test01</th>
							<th>test02</th>
							<th>test03</th>
							<th>test04</th>
							<th>test05</th>
							<th>test06</th>
						</tr>
					</thead>	
					<tbody>
						<tr>
							<td>
								<input type="text" class="form-control" name="test01_value" id="test01_value" value="${returnData}" /> <!-- value="${agencyVO.id_agency}" -->
							</td>
							<td>
								<input type="text" class="form-control" name="test02_value" id="test02_value" value="" validate="required;label:test02; maxbt:20;" maxlength="20"/> <!-- value="${agencyVO.id_agency}" -->
							</td>
							<td>
								<input type="text" class="form-control" name="test03_value" id="test03_value" value="" validate="required;label:test03; maxbt:20;" maxlength="20"/> <!-- value="${agencyVO.id_agency}" -->
							</td>
							<td>
								<input type="text" class="form-control" name="test04_value" id="test04_value" value="" validate="required;label:test04; maxbt:20;" maxlength="20"/> <!-- value="${agencyVO.id_agency}" -->
							</td>
							<td>
								<input type="text" class="form-control" name="test05_value" id="test05_value" value="" validate="required;label:test05; maxbt:20;" maxlength="20"/> <!-- value="${agencyVO.id_agency}" -->
							</td>
							<td>
								<input type="text" class="form-control" name="test06_value" id="test06_value" value="" validate="required;label:test06; maxbt:20;" maxlength="20"/> <!-- value="${agencyVO.id_agency}" -->
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