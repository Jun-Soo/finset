/**
 * 공통사용 js 
 */

//숫자포맷관련 prototype (소수점지정,'.',',') ex) 123.34.formatMoney(0) => 12,334
Number.prototype.formatMoney = function(c, d, t){
var n = this, 
    c = isNaN(c = Math.abs(c)) ? 2 : c, 
    d = d == undefined ? "." : d, 
    t = t == undefined ? "," : t, 
    s = n < 0 ? "-" : "", 
    i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", 
    j = (j = i.length) > 3 ? j % 3 : 0;
   return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
 };

//날짜포맷관련 prototype ex) new Data().format('yyyy-MM-dd') => 2015-04-17 
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

//loading 처리
function showLoading(){
	loading = $('<img alt="loading" src="../img/ajax-loader.gif" />').appendTo(document.body).show();
	loading.css({  top: $(document).scrollTop()+ ($(window).height() )/2 + 'px',
      left: ($(window).width() )/2 + 'px',
      'position':'absolute'});
		var height = $(document).scrollTop() + $(window).height();
	 	$div = $('<div id="progressDiv" style="position:absolute; top:0; left:0; width:100%; height:' + height + 'px; background:#000; opacity:.5; filter:alpha(opacity=20); z-index:1000;"></div>');
	 $('body').append($div);
}
function showStop() {
	$("#progressDiv").remove();
	if(loading != undefined){
		// loading.hide();
		loading.remove();
	}
}
jQuery(function($){
    $(document).ajaxStart(function() {
   	 showLoading();
    }).ajaxStop(function() {
    	showStop();
    });
});

//암전처리(loading 이미지없음)
function showDark(){
	var height = $(document).scrollTop() + $(window).height();
 	$div = $('<div id="darkDiv" style="position:absolute; top:0; left:0; width:100%; height:' + height + 'px; background:#000; opacity:.5; filter:alpha(opacity=20); z-index:1000;"></div>');
	 $('body').append($div);
}
function stopDark() {
	$("#darkDiv").remove();
}

//상세검색내용 클릭시 상세검색 디폴트값 설정
function defaultSetting(id, val) {
	var selVal = document.getElementById(id).value; 
	if(selVal == ""){
		$("#"+id).val(val).attr("selected", "selected");
		$("#"+id).selectpicker("refresh"); //selectpicker 초기화
	}
}

// 
function getDateYMD(date,format) {
	if (date == null || date == '' || date == 'undefined')
		return '';
	
	var yyyy = date.getFullYear();
	var mm = '';
	var tmp_mm = parseInt(date.getMonth()) + 1;
	
	if (tmp_mm < 10)
		mm = '0' + tmp_mm;
	
	var dd = date.getDate();
	
	if(dd < 10)
		dd = '0' + dd;
	
	if (format == "yyyy-mm-dd")
		return yyyy + '-' + mm + '-' + dd;
	
}

// 입력값이 NULL인지 체크
function IsNull( obj ) {
    if ( obj.value == null || obj.value == "" || obj.value == undefined) {
        return true ;
    }
    return false ;
}


// 한 그룹의 체크박스의 값을 문자열을 기준으로 해당되는 값과 일치하면 체크를 해준다.
function setCheckBoxValue(obj, checkedValue) {
	for ( ii=0; ii<obj.length; ++ii ) {
		if ( obj[ii].value == checkedValue )
			obj[ii].checked = true;
		else obj[ii].checked = false;
	}
}

// 라디오, 체크박스 선택된값 가저오기
function getRadioCheckedVal(objRadio){
    var i;
    var checkedval = null;
    
    if ( objRadio[0] == 'undefined' || objRadio[0] == null ) {
		if ( objRadio.checked==true ) checkedval = objRadio.value;
	}
    else {
    	for(i=0;i<objRadio.length;i++){
    		if(objRadio[i].checked==true){
    			checkedval = objRadio[i].value;
    			break;
    		}
    	}
    }
    return checkedval ;
} 

// 폼 초기화(form id)
function resetForm(frmId) {
	var frm = document.getElementById(frmId);
	frm.reset();
	
	$('#'+frmId+' .selectpicker').selectpicker('refresh');//selectpicker 초기화
}

/*Ajax Post 요청처리 함수*/
function ajaxPostReq(url , paramData , callBack) {
	if(url == null || url == '' || url == undefined) 
	{
		alert('[AJAX 요청실패]URL없음');
		return false;
	}
	
	$.ajax({
		url : url,
		data : paramData,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			//처리후 콜백함수 호출
			if(callBack != null)
				eval('var data = result; ' +callBack+'(data);');	
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
	
}

// jquery load 재구현(한글깨짐대응)
function vLoad(id,uri,data) {
	vLoad(id,uri,data,true);
}
function vLoad(id,uri,data,sync) {
	$.ajax({
		url : uri,
		data : data,
		async: sync,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "POST",
		success : function (result) {
			var expr = new RegExp('>[ \t\r\n\v\f]*<', 'g');
			result = result.replace(expr, '> <');
			$("#"+id).html(result);
		},
		error : function (e) {
			alert(e.responseText);
		}
	});
}

function countCheckBox(obj) {
	var checkcount=0;
	if ( obj[0] == 'undefined' || obj[0] == null ) {
		if ( obj.checked ) checkcount++;
	}
	else {
		for(var icount=0; icount < obj.length; icount++){
			if(obj[icount].checked)checkcount++;
		}
	}
	return checkcount;
}

/* Combo 에서 선택된 값 가져오기 */
function getRadioOption( opt ) {
	if(typeof(opt)!=null && typeof(opt.length)=='number'){
		var aSelValue = new Array();
		var iarrayCnt=0;
		
		for( i = 0; i < opt.length; i++ ) {
			if( opt[i].checked ) {
				aSelValue[iarrayCnt++] = opt[i].value;
			}
		}

		return aSelValue;
	}else{
		if(opt.checked){
			return opt.value
		}else{
			return null;
		}
	}
}

/* 체크박스 전체 선택 */
function checkBoxAllCheck(obj, name) {
	if(obj.checked)
		$("input:checkbox[name='"+name+"']").prop("checked", true);
	else
		$("input:checkbox[name='"+name+"']").prop("checked", false);
}

/* obj change display */
function changeObjDisplay(objId, isDisplay) {	
		
	if(document.getElementById(objId) == undefined )
		return;
	
	if ( isDisplay ) {
		document.getElementById(objId).style.display = "";
	}
	else {
		document.getElementById(objId).style.display = "none";
	}
}

/*오브젝트 null 체크*/
function isNullObj(obj) {
 	if (typeof obj == 'undefined') 
 		return true;
 	else
 		return false;
 }