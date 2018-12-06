//jQuery(function($){
//    $(document).ajaxStart(function() {
//    	window.Android.loding('Y');
//    }).ajaxStop(function() {
//    	window.Android.loding('N');
//    });
//});
//
//
/**
 * 금액표기
 */
function number_format(data){
    // alert("number_format:data="+data);
    data = ""+data;
    var tmp = '';
    var number = '';
    var cutlen = 3;
    var comma = ',';
    var i;
    var sign = data.match(/^[\+\-]/);
    // alert("number_format:sign="+sign);
    if(sign) {
        data = data.replace(/^[\+\-]/, "");
        // alert("number_format:insign,data="+sign);

    }
    var len = data.length;
    // alert("number_format:len="+len);
    var mod = (len % cutlen);
    // alert("number_format:mod="+mod);
    var k = cutlen - mod;
    // alert("number_format:k="+k);

    for (i=0; i<data.length; i++) {
        number = number + data.charAt(i);

        if (i < data.length - 1)
        {
            k++;
            if ((k % cutlen) == 0)
            {
                number = number + comma;
                k = 0;
            }
        }
    }
    // alert("number_format:number="+number);
    // alert("number_format:sign="+sign);
    if(sign != null) {
        number = sign+number;
    }
    // alert("number_format:result : number="+number);
    return number;
}

/**
 * 금액표기 만원단위로
 * @param pAmt
 * @returns {number}
 */
function viewAmt(pAmt) {
    // alert("viewAmt:pAmt="+pAmt);
    var amt = parseFloat(pAmt);
    var result = amt;
    result = Math.round(result/1000)/10;
    result = number_format(result);
    // alert("viewAmt:result="+result);
    return result;
}

function goCreditMain() {
	location.href = '/m/credit/frameCreditInfoMain.crz';
}

function goDebtMain() {
	location.href = '/m/debt/frameDebtInfoMain.crz';
}

function goLoanMain() {
	location.href = '/m/loanworker/frameLoanWorkerStep1.crz';
}

function goCustomerMain() {
	location.href = '/m/customercenter/frameCustomerCenterMain.crz';
}