<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
		fcholidays = [];
		var cd_fc = '${fincorpInfo.cd_fc}';
		$.ajax({
			type : "post",
			//url : "<c:url value='/business/listBusinessDay.json'/>",
			url : "<c:url value='/business/listFcBusinessDay.json'/>",
			async : false,
			data : {"cd_fc" : cd_fc},
			dataType : "json",
			success : function(data){
				$.each(data.List, function (index, item) {
					var arr = [item.substring(0, 4), item.substring(4, 6), item.substring(6, 8)];
					fcholidays.push(arr);
				});
				
			}
		});
		
		$('#day').datepicker({
			format: "yyyy-mm-dd",
		    todayHighlight: false,
			beforeShowDay : nationalDays
		}).on('changeDate', dateChanged);
		
		function dateChanged(ev) {
	 		$(strDate($('#day').datepicker('getDates')));
			
	 		$("#date_piker").val(arr_date);	
	 		
	 		var cd_fc = '${fincorpInfo.cd_fc}';
	 		
	 		var data = {"cd_fc":cd_fc, "ymd":arr_date};
	 		//var data = {"ymd":arr_date};
			
			if(data == null){
				return false;
			}
			
			$.ajax({
				type : "post",
				url : "<c:url value='/business/getFcBusinessDayInfo.json'/>",
				async : false,
				dataType : "json",
				data : data,
				success : function(data){
					if(data.result == "success"){
						$("#time_start").val(data.BusinessDay.time_start);
						$("#time_end ").val(data.BusinessDay.time_end );
						if(data.BusinessDay.yn_cutoff == "Y"){
							$('#yn_cutoff').prop('checked', true);
						}
						else{
							$('#yn_cutoff').prop('checked', false);
						}
						$("#cd_reason").val(data.BusinessDay.cd_reason);
						$("#memo 	 ").val(data.BusinessDay.memo     );
						$("#cd_reason").selectpicker('refresh');					
					}
					else{
						$("#time_start").val("");
						$("#time_end ").val("");
						$('#yn_cutoff').prop('checked', false);
						$("#cd_reason").val("");
						$("#memo 	 ").val("");
						$("#cd_reason").selectpicker('refresh');
					}
					
				}
			});
		}
	
		//공휴일 체크
		function nationalDays(date) {
			for (var i = 0; i < fcholidays.length; i++) {
				if (date.getFullYear() == fcholidays[i][0] && date.getMonth() == fcholidays[i][1] - 1
				                    && date.getDate() == fcholidays[i][2] || date.getDay() == 0 || date.getDay() == 6) { 
					return "date-holiday";
				}
			}
			return "";
		}
		
		$("#time_start").timepicker({
			step: 5,            //시간간격 : 5분
			timeFormat: "H:i"    //시간:분 으로표시
		});
		$("#time_end").timepicker({
			step: 5,            //시간간격 : 5분
			show2400 : true,
			timeFormat: "H:i"    //시간:분 으로표시
		});
		
		window.setupValidateForm( formBusiness );
		$('#day').change();
	});
	
</script>	

<div  class="calendar-grid" id="">
	<div class="month" id="day"></div>
</div>

	<form name = "formBusiness" class="navbar-form">
	
	<input type="hidden" name="cd_fc" id="cd_fc">
	<input type="hidden" name="yn_holiday" id="yn_holiday">
	<input type="hidden" name="arr_ymd" id="arr_ymd">
		<div class="panel-collapse" id="left-box">
		<table>
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col width="25%">
			</colgroup>
				<tbody> 
				<tr>
					<th>날짜</th>
					<td>
						<input type="text" class="form-control width-150" id="date_piker" name="date_piker" value="" validate="required; label:날짜;" maxlength="30" readonly="readonly"/>
					</td>
				</tr>
				<tr>	
					<th>시간</th>
					<td>
						<input type="text" class="form-control" name="time_start" value="" placeholder="시작시간"  id="time_start" validate="required; label:시작시간;" size="8" maxlength="5"> ~
						<input type="text" class="form-control" name="time_end" value="" placeholder="종료시간"  id="time_end" validate="required; label:종료시간;" size="8" maxlength="5">
					</td>
				</tr>
				<tr>
					<th><label for="yn_cutoff">차단유무</label></th>
					<td>
						<input type="checkbox" name="yn_cutoff" id="yn_cutoff" value="Y" validate="required; label:차단유무;"/><label for="yn_cutoff"></label>
					</td>
				</tr>
				<tr>
					<th>사유</th>
					<td>
						<select id="cd_reason" name="cd_reason" class="selectpicker" validate="required;select-one;label:사유">
								${ufn:makeOptions("cd_reason","선택", "")}
						</select>
					</td>
				</tr>
				<tr>
					<th></th>
					<td><textarea class="form-control w100 h150" id="memo" name="memo" validate=" label:사유내용; maxbt:2000;" maxlength="2000"></textarea></td>
				</tr>
				</tbody>
			</table>	
		</div>
	</form>
