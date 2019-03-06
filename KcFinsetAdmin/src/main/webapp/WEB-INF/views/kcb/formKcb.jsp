<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
</script>
<div class="popup">
	<div class="modal-body">
		<div class="align-r">
			<button type="submit" class="btn btn-default btn-xs" onclick="procKcb();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>저장</button>
		</div>
		<form name="frmKcb" id="frmKcb">
		<div class="panel panel-primary">
			<div class="panel-heading">기본정보</div>
			<div class="panel-collapse">
			<table class="table table-classic">
				<colgroup>
					<col width="12.5%"/>
					<col width="12.5%"/>
					<col width="12.5%"/>
					<col width="12.5%"/>
					<col width="12.5%"/>
					<col width="12.5%"/>
					<col width="12.5%"/>
					<col width="12.5%"/>
				</colgroup>
				<tbody>
				<tr>
					<th colspan="8">
						<span class="required">
							* 600    	01: 발생 신규 고객ID 등록&업데이트,   05 : 고객 ID 삭제</br>
							* 600420	01: 발생(신규 제휴 서비스 신청), 02:수정(제휴 서비스 신청내용 수정), 03:해지, 09:URL조회
						</span>
					</th>
				</tr>
				<tr>
					<th>
						<span class="required">nmIf</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="nmIf" id="nmIf" value="600420" validate="label:nmIf"/>nmIf
					</td>
					<th>
						<span class="required">cd_regist</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="cd_regist" id="cd_regist" value="09" validate="label:cd_regist"/>cd_regist
					</td>
					<th>
						<span class="required">noPerson</span>
					</th>
					<td colspan="3">
						<input type="text" class="form-control width-120" name="noPerson" id="noPerson" value="P000000001" validate="label:noPerson"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="required">bgn</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="bgn" id="bgn" value="197311291" validate="label:bgn"/>bgn
					</td>
					<th>
						<span class="required">nmCust</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="nmCust" id="nmCust" value="이현중" validate="label:nmCust"/>nmCust
					</td>
					<th>
						<span class="required">di</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="di" id="di" value="MC0GCCqGSIb3DQIJAyEA70ZExFokd3gSeDUh8asSa5Z9w2Cmm4GthkkYP2cEADI=" validate="label:di"/>
					</td>
					<th>
						<span class="required">cp</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="cp" id="cp" value="P18760000000" validate="label:cp"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="required">MenuCode</span>
					</th>
					<td>
						<input type="text" class="form-control width-120" name="reqMenuCode" id="reqMenuCode" value="210" validate="label:MenuCode"/>MenuCode
					</td>
					<th>
						<span class="required">ViewCode</span>
					</th>
					<td colspan="5">
						<input type="text" class="form-control width-120" name="reqViewCode" id="reqViewCode" value="s02173986405" validate="label:ViewCode"/>ViewCode
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</div>
		</form>
	</div>
</div>
