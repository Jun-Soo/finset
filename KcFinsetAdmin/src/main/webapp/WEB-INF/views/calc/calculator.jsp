<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>대출 계산기</title>
<%@ include file="/WEB-INF/include/headComm.jsp"%>
<script type="text/javascript" src="<c:url value="/script/util_comm.js"/>"></script>
<script type="text/javascript">
           
	$(document).ready(function(){
		//datepicker
		$('.input-daterange').datepicker({
			format: "yyyy-mm-dd", //dateformat
			todayHighlight: true, //당일 표시
			todayBtn: "linked", //today button link 활성화
			autoclose: true, //달력 자동 닫힘
			beforeShowDay : nationalDays
		});
		
		$(".toggle-panel").each(function(i){
			$(this).find(".h-sec > a").click(function(){
				$(this).toggleClass("closed");
				$(this).closest(".toggle-panel").find(".toggle-cont").toggle("blind", 200);
			});
		});
	
	});

	function pushCash(obj,inputId){
		var u = parseInt($(obj).val());
		var curCash = parseInt($('#'+inputId).val().replace(/\,/g,""));
		var resetData = 0;	
	
		if(obj.id != 'reset'){
			$('#'+inputId).val(formatCurrency(curCash+u));
		} else {
			$('#'+inputId).val(resetData);
		}
	}

	var AddRowCount=0;

	/**
	 * 해당월의 마지막일자 반환
	 * @param year
	 * @param month
	 * @return
	 */
	function getLastDateOfMonth(year, month){
		var d = new Date(year,month,"");
		//d.date-=1;
		return d.getDate();
	}

	/**
	* 10미만의 수치에 앞자리 0추가
	*/
	function addZero(num){
		var result;
		result = (Number(num)<10)?"0"+num.toString():num.toString();
		return result;
	}

	/**
	 * dateString 일자에 addMonth를 더한 경우 실제 존재 하는 날짜 이상인 경우 그 월의 마지막일자를 반환
	 * ex (2007-12-31,2) = 2008-02-29
	 * @param dateString
	 * @param addMonth
	 * @return
	 */
	function getRealDate(dateString,addMonth){

		var result0="";
		var result1="";
		var d="";

		var dateStandard=$("#dateInputField").val().replace(/\-/g,"");	// 날짜

		var yearInput  =dateStandard.substring(0, 4);
		var monthInput =dateStandard.substring(4, 6);
		var dayInput =Number(dateStandard.substring(6,8));

		var dateTo=dateString+"";
		dateTo=dateTo.replace(/\-/g,"");
		var year  =dateTo.substring(0, 4);
		var month =dateTo.substring(4, 6);
		var day =dateTo.substring(6,8);

		var checkLastDay = getLastDateOfMonth(year, Number(month)+Number(addMonth) );
		
		if(checkLastDay <= dayInput)
		{
			d = new Date(year,Number(month)-1+Number(addMonth),1);
			result0 = 	addZero(d.getFullYear())+"-"+
						addZero(d.getMonth()+1)+"-"+
						addZero(getLastDateOfMonth(d.getFullYear(), (d.getMonth()+1)));
		}else
			{
				d = new Date(year,Number(month)+Number(addMonth)-1,1);
				result0 = addZero(d.getFullYear())+"-"+
				addZero(Number(d.getMonth())+1)+"-"+
				addZero(Number(dayInput));
			}

		return result0;
	}

	function goCalc(){

		var cd_payback_type = "00";
		for(var i=0;i<$("input:radio[name='cd_payback_type']").length;i++){
			if($("input:radio[name='cd_payback_type']").eq(i).is(":checked"))
			{
				cd_payback_type = $("input:radio[name='cd_payback_type']").eq(i).val();
			}
		}
		
		// param setting
		var cost=$("#s1_1").val().replace(/\,/g,"");				//대출 금액
	    var term=$("#s1_2").val().replace(/\,/g,"");				//대출 기간
	    var interestRate=$("#s1_3").val().replace(/\,/g,"");		//대출 금리			
	    var repaymentCycle=$('#repaymentCycle').val();				//상환 주기
	    var repaymentType=cd_payback_type;							//상환 방법(01 : 원리금 균등방식, 02 : 원금 균등방식, 03 : 원금 만기일시상환)
		var dateStandard=$("#dateInputField").val().replace(/\-/g,"");	// 날짜
		
		if(cost == "" || cost == 0){
			alert('대출금액을 입력해주세요');
			return;
		}
		else if(term == "" || term == 0){
			alert('대출기간을 입력해주세요');
			return;
		}
		else if(interestRate == "" || interestRate == 0){
			alert('대출금리를 입력해주세요');
			return;
		}
		else if(repaymentType == "" || repaymentType == 0){
			alert('상환방법를 선택해주세요');
			return;
		}
		
		var col="";
		var row = new Object();
		//var row:Object;

		var totalInterest = 0;
		var avgInterest = 0;
		var totalRepayments = 0;
		var avgRepayments = 0;
		var repaymentTypeKor=""

		var rest = cost;			// 잔금
		var principalSumTotal = 0;	// 납입원금합계

		var tmpRepaymentCycle = 0;	// 출력용 상환주기
		//회차 계산 = (Math.floor(대출기간 / 상환주기)==대출기간 / 상환주기)?Math.round(Math.floor(대출기간 / 상환주기)):Math.round(Math.floor(대출기간 / 상환주기))+1;
		var repaymentCnt = (Math.floor(term / repaymentCycle)==term / repaymentCycle)?Math.round(Math.floor(term / repaymentCycle)):Math.round(Math.floor(term / repaymentCycle))+1;

		var nextDate=dateStandard;

		//원리금 균등방식
		if(repaymentType=="01"){
			repaymentTypeKor="원리금균등상환";

			//상환금 = (대출금 * (대출이자율 / 1200) * 상환주기) / (1 - 1 /(1 + (대출이자율 / 1200) * 상환주기) ^ (대출기간 / 상환주기)))
			var repayments = (cost*(interestRate/1200)*repaymentCycle)/(1-1/(Math.pow((1+(interestRate/1200)*repaymentCycle), term/repaymentCycle)));

			for(i = 0 ; i < repaymentCnt; i++){
				row[i] = new Object();
				// 상환금
				row[i]["repayments"] = repayments;
				// 회차
				row[i]["cnt"] = i+1;
				nextDate = getRealDate(nextDate,repaymentCycle);
				//alert("dateStandard"+dateStandard +"nextDate"+nextDate);
				row[i]["repaymentDate"] = nextDate;


				tmpRepaymentCycle += repaymentCycle;

				// 이자 = 잔금 * (대출금리/1200)*상환주기
				if(tmpRepaymentCycle > term){
					//마지막이자  = 잔금 * (대출금리/1200)*(대출기간-(tmpRepaymentCycle - 상환주기));
					row[i]["interest"] = rest * (interestRate/1200)*(term-(tmpRepaymentCycle - repaymentCycle));

					//마지막 누적이자 = 누적이자  + 이자
					totalInterest += row[i]["interest"];

					//마지막 상환금  = 잔금 + 이자
					row[i]["repayments"] = rest + row[i]["interest"];
				}else{
					//이자계산 = 잔금 * (대출금리/1200)*상환주기;
					row[i]["interest"] = rest * (interestRate/1200)*repaymentCycle;

					//이자 누적 = 총이자 + 이자
					totalInterest += row[i]["interest"];
				}
				//납입원금 = 상환금 - 이자
				row[i]["principalSum"] = row[i]["repayments"] - row[i]["interest"];

				//납입원금합계 = 납입원금합계 + 납입원금
				row[i]["principalSumTotal"] = principalSumTotal += row[i]["principalSum"];

				//잔금 = 잔금 - 납입원금
				row[i]["rest"] = rest-=row[i]["principalSum"];

				// 총 상환금
				totalRepayments += row[i]["repayments"];

				//상환일
				//alert("i"+i+" 날짜"+row[i]["repaymentDate"] +" value:"+row[i]["principalSum"]);

			}
		//원금 균등방식
		}else if(repaymentType=="02"){
			repaymentTypeKor="원금균등상환";
 			for(i = 0 ; i < repaymentCnt; i++){
 				row[i] = new Object();
				tmpRepaymentCycle += repaymentCycle;
				row[i]["cnt"] = i+1;
				nextDate = getRealDate(nextDate,repaymentCycle);
				row[i]["repaymentDate"] = nextDate;

				// 이자 = 잔금 * (대출금리/1200)*상환주기
				if(tmpRepaymentCycle > term){
					//마지막이자 = 잔금 * (대출금리/1200)*(대출기간-(tmpRepaymentCycle - 상환주기));
					row[i]["interest"] = rest * (interestRate/1200)*(term-(tmpRepaymentCycle - repaymentCycle));
					//마지막 누적이자 = 총이자 += 이자
					totalInterest += row[i]["interest"];
					//마지막 상환금 = 잔금 + 이자
					row[i]["repayments"] = rest + row[i]["interest"];
				}else{
					//이자계산 = 잔금 * (대출금리/1200)*상환주기;
					row[i]["interest"] = rest * (interestRate/1200)*repaymentCycle;
					//이자 누적 = 총이자 += 이자;
					totalInterest += row[i]["interest"];
				}

				if(repaymentCnt != i+1){
			        //납입원금 = 대출금 / (대출기간 / 상환주기)
			        row[i]["principalSum"] = cost / (term/repaymentCycle);
			    }else{
			    	row[i]["principalSum"] = rest;
			    }

				//상환금 = 납입원금 + 월이자
				row[i]["repayments"] = row[i]["principalSum"] + row[i]["interest"];

				//잔금 = 잔금 - 납입원금
				row[i]["rest"] = rest -= row[i]["principalSum"];

				//납입원금합계 = 납입원금합계 + 납입원금
				row[i]["principalSumTotal"] = principalSumTotal += row[i]["principalSum"];

				//총 상환금
				totalRepayments += row[i]["repayments"];

				//상환일
				
				//result.addItem(row);
			}
		//원금 만기일시상환
		}else if(repaymentType=="03"){
			repaymentTypeKor="원금만기일시상환";

			for(i=0 ; i < repaymentCnt; i++){
				row[i] = new Object();
				row[i]["cnt"] = i+1;
				nextDate = getRealDate(nextDate,repaymentCycle);
				row[i]["repaymentDate"] = nextDate;

				//월이자 = 대출금 * (대출금리/1200)
				row[i]["interest"] = cost * (interestRate/1200)* repaymentCycle;

				//총이자 = 월이자 * 상환기간
				//totalInterest = interest * loanInterestRate;
				totalInterest = row[i]["interest"] * repaymentCnt;
				if(i+1 == repaymentCnt){
					row[i]["principalSum"] = cost;
					//상환금 = 납입원금 + 월이자
					row[i]["repayments"] = Number(row[i]["principalSum"]) + Number(row[i]["interest"]);

				}else{
					row[i]["principalSum"] = 0;
					row[i]["repayments"] = Number(row[i]["principalSum"]) + Number(row[i]["interest"]);
				}
				//잔금 = 잔금 - 납입원금
				row[i]["rest"] = rest -= row[i]["principalSum"];

				//납입원금합계 = 납입원금합계 + 납입원금
				row[i]["principalSumTotal"] = principalSumTotal += row[i]["principalSum"];

				//총 상환금
				totalRepayments += row[i]["repayments"];

				//상환일

				//result.addItem(row);
			}
		}


		avgInterest = Math.abs(Math.round(totalInterest/repaymentCnt));
		totalInterest = Math.abs(Math.round(totalInterest));
		avgRepayments = Math.abs(Math.round(totalRepayments/repaymentCnt));
		totalRepayments = Math.abs(Math.round(totalRepayments));

		if(repaymentCnt>0){
			$("#chartValue li")[0].innerHTML="<span style='font-weight: bold'>월평균 납입금<br></span>"+formatCurrency(avgRepayments)+"원";
			$("#chartValue li")[1].innerHTML="<span style='font-weight: bold'>총이자<br></span>"+formatCurrency(totalInterest)+"원";
			$("#chartValue li")[2].innerHTML="<span style='font-weight: bold'>월평균 이자액<br></span>"+formatCurrency(avgInterest)+"원";
		}

		var table1=$("#table1 td");
		table1[0].innerHTML=formatCurrency(cost)+"원";	// 대출금액
		table1[1].innerHTML=term+"개월";
		table1[2].innerHTML=interestRate+"%";
		table1[3].innerHTML=repaymentCycle+"개월";
		table1[4].innerHTML=repaymentTypeKor;
		if(repaymentType=="01"){
			if(repaymentCnt>0){
				table1[5].innerHTML=formatCurrency(Math.round(row[0]["repayments"]))+"원";
			}
			$("#table1 th")[5].innerHTML="상환금";

		}else if(repaymentType=="02"){
			if(repaymentCnt>0){
				table1[5].innerHTML=formatCurrency(Math.round(row[0]["principalSum"]))+"원";
			}
			$("#table1 th")[5].innerHTML="납입원금";

		}else if(repaymentType=="03"){
			if(repaymentCnt>0){
				table1[5].innerHTML=formatCurrency(Math.round(row[0]["interest"]))+"원";
			}
			$("#table1 th")[5].innerHTML="월이자";

		}
		table1[6].innerHTML=formatCurrency(Math.round(totalInterest))+"원";

		// 값존재시 삭제
		if(repaymentCnt>0){
			$("#tbody").remove();
			$("#trbody").remove();
			$('<tbody id="tbody"></tbody>').appendTo("#table2");
		}

		var innerHtmlString='<tr> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td></td> </tr>';

		for(i = 0 ; i < repaymentCnt; i++){
			$(innerHtmlString).appendTo("#tbody");
		}
		AddRowCount=repaymentCnt;



		var table2=$("#table2 td");
		for(i = 0 ; i < repaymentCnt; i++){
				table2[(i*7)+0].innerHTML=row[i]["cnt"];					//회차
				table2[(i*7)+1].innerHTML=row[i]["repaymentDate"];			//상환일
				table2[(i*7)+2].innerHTML=formatCurrency(Math.round(row[i]["repayments"]))+"원";			//상환금
				table2[(i*7)+3].innerHTML=formatCurrency(Math.round(row[i]["principalSum"]))+"원";		//납입원금
				table2[(i*7)+4].innerHTML=formatCurrency(Math.round(row[i]["interest"]))+"원";			//이자
				table2[(i*7)+5].innerHTML=formatCurrency(Math.round(row[i]["principalSumTotal"]))+"원";	//납입원금 합계
				table2[(i*7)+6].innerHTML=formatCurrency(Math.round(row[i]["rest"]))+"원";				//잔금
		}

		var frm = document.mainForm;

		if(isNaN(totalInterest)) { totalInterest=0; }

		frm.principalSum.value=cost;
		frm.totalInterest.value=totalInterest;
	}
</script>
</head>

<body>
<form name="mainForm" action="">
<input type="hidden" name="principalSum" />
<input type="hidden" name="totalInterest" />
</form>

<div class="ui-layout-content">
	<div class="panel panel-default toggle-panel">
		<div class="panel-heading">
			<h3 class="h-sec pull-left">
				<a href="#none">대출 계산기</a>
			</h3>
		</div>

		<div class="panel-collapse toggle-cont">
			<table class="table table-classic tbl-info">
				<tbody>
					<tr>	
						<th>대출금액</th>					
						<td>
			                <button type="button" class="btn btn-default btn-xs" value="100000" onclick="pushCash(this,'s1_1')" >10만</button>
			               	<button type="button" class="btn btn-default btn-xs" value="1000000" onclick="pushCash(this,'s1_1')">100만</button>
			               	<button type="button" class="btn btn-default btn-xs" value="5000000" onclick="pushCash(this,'s1_1')">500만</button>
			               	<button type="button" class="btn btn-default btn-xs" value="10000000" onclick="pushCash(this,'s1_1')">1000만</button>
			                <button type="button" class="btn btn-primary btn-xs" id="reset" value="0" onclick="pushCash(this,'s1_1')">
			                 	<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> 정정
			                </button>
		                </td>	
		                <td>
		                	 <input type="text" id="s1_1" name="s1_1" value="0" class="form-control width-120 align-r" onkeyup="keyupCurrencyObj(event, this);"  onkeypress="keyNumeric(event);" /> 원
		                </td>	
						<th rowspan="5">계산결과</th>	
						<td rowspan="5" id="chartValue">
							<li><span>월평균 납입금</span></li>
							<li><span>총이자</span></li>
		  					<li><span>월평균 이자액</span></li>	
						</td>				
					</tr>
					<tr>
						<th>대출기간</th>
						<td>
							<button type="button" class="btn btn-default btn-xs" value="12" onclick="pushCash(this,'s1_2')">1년</button>
							<button type="button" class="btn btn-default btn-xs" value="24" onclick="pushCash(this,'s1_2')">2년</button>
							<button type="button" class="btn btn-default btn-xs" value="36" onclick="pushCash(this,'s1_2')">3년</button>
							<button type="button" class="btn btn-default btn-xs" value="48" onclick="pushCash(this,'s1_2')">4년</button>
							<button type="button" class="btn btn-default btn-xs" value="60" onclick="pushCash(this,'s1_2')">5년</button>
							<button type="button" class="btn btn-primary btn-xs" id="reset" value="0" onclick="pushCash(this,'s1_2')">
			                 	<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> 정정
			                </button>
						</td>
						<td>
							<input type="text" id="s1_2" name="s1_2" value="0" class="form-control width-120 align-r" onkeyup="keyupCurrencyObj(event, this);"  onkeypress="keyNumeric(event);" /> 개월
						</td>
					</tr>
					<tr>
						<th>대출금리</th>
						<td colspan="2">
							연 <input type="text" id="s1_3" name="s1_3" value="0" class="form-control width-65 align-r" onkeyup="keyupCurrencyObj(event, this);"  onkeypress="keyNumeric(event);" /> %      
						</td>
					</tr>	
					<tr>
						<th>계약일</th>
						<input type="hidden" name="repaymentCycle" id="repaymentCycle" value="1">
						<td colspan="2">
							<div class="input-daterange input-group date-w" id="datepicker">
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								<input type="text" class="input-sm form-control" name="dateInputField" value="${ufn:getCurrentDate()}" id="dateInputField" validate="date"/>
							</div>			
						</td>			
					</tr>
					<tr>
						<th>상환방법</th>
						<td colspan="2">
							${ufn:makeRadioAndCheckBoxs("cd_payback_type", "cd_payback_type", "radio", "01", 0)}
						</td>
					</tr>
					<tr>
						<td colspan="5">
							<div class="align-c">
								<button type="submit" class="btn btn-primary btn-xs" onclick="goCalc();">
				                 	<span class="glyphicon glyphicon-modal-window" aria-hidden="true"></span> 계 산
				                </button>
		                	 </div> 
						</td>
					</tr>
				</tbody>	
			</table>
		</div>		
	</div>
	
	<div class="panel panel-default toggle-panel">
		<div class="panel-heading">
			<h3 class="h-sec pull-left">
				<a href="#none">계산 결과</a>
			</h3>
		</div>

		<div class="panel-collapse toggle-cont">
			<table class="table table-bordered tbl-info" id="table1">
		        <thead>
			        <tr>
			        	<th>대출금액</th>
			            <th>대출기간</th>
			            <th>대출금리</th>
			            <th>상환주기</th>
			            <th>상환방법</th>
			            <th>상환금</th>
			            <th>총이자</th>
			        </tr>
		        </thead>
		        <tbody>
			        <tr>
			            <td></td>
			            <td></td>
			            <td></td>
			            <td></td>
			            <td></td>
			            <td></td>
			            <td></td>
			        </tr>
		        </tbody>
		    </table>
		
			<table class="table table-bordered tbl-info" id="table2">
				<thead>
					<tr>
						<th>회차</th>
						<th>상환일</th>
						<th>상환금</th>
						<th>납입원금</th>
						<th>이자</th>
						<th>납입원금 합계</th>
						<th>잔금</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<tr id="trbody">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			 </table>
		</div>
	</div> 

</div>
</body>
</html>