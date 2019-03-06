/*******************************************************************************
* js(명)   : 콘텐츠 유틸리티 JavaScript
* 설명     : 콘텐츠에서 사용하는 Util관련 자바스크립트 함수
* 파일번호 : 2659
* 파일명   : /com/js/cbh/common.js
* 작성자   : 정찬주
* 작성일   : 2008-07-05
* 변경로그 :
*******************************************************************************/

var CBH_RESTRICT_CHARS = "%'&?\\"; // 입력값 제약문자
var DATE_DELIMITER     = "-"; // 일자 구분자
var STRING_DELIMITER   = ";"; // 문자열 구분자
var LINE_DELIMITER     = "|"; // 라인 구분자
//****************************************************************************
// Key 입력관련 관련 함수
//****************************************************************************

/*-------------------------------------------------------------------------
    Spec      : key Event 중지하는 함수.
    Parameter : 이벤트Object
    Return    : Boolean
    Ex        : stopKeyEvent(evt)
    Ref       : 0-48(96), 9-57(105)
-------------------------------------------------------------------------*/
function stopKeyEvent(evt)
{
     if(window.event) {
         window.event.keyCode = 0;
         window.event.cancelBubble = true;
         window.event.returnValue = false;
     } else {
         evt.stopPropagation();
         evt.preventDefault();
         evt.initEvent;
     }

     return false;
}

/*-------------------------------------------------------------------------
    Spec      : Keypress시 input에 입력 key 값이 숫자만입력 할수 있도록 하는 함수
    Parameter : 해당사항 없음
    Return    : Boolean
    Ex        : onKeypress='getEventKeyCode(event);'
    Ref       : 0-48(96), 9-57(105)
-------------------------------------------------------------------------*/
function getEventKeyCode(e) {
    var keyCode = 0;
    try {
        if(e.which) {
            keyCode = e.which;
        } else {
            keyCode = window.event.keyCode;
        }
    } catch (e) {}

    return keyCode;
}

/*-------------------------------------------------------------------------
    Spec      : Keypress시 input에 입력 key 값이 숫자만입력 할수 있도록 하는 함수
    Parameter : 해당사항 없음
    Return    : Boolean
    Ex        : onKeypress='keyNumeric(event);'
    Ref       : 0->48(96), 9->57(105)
-------------------------------------------------------------------------*/
function keyNumeric(e) {

    var keyCode = getEventKeyCode(e);
    if((keyCode >= 48 && keyCode <= 57) ||
       (keyCode == 8 ||  // backspace
        keyCode == 9 || // tab
        keyCode == 13 || // enter
        keyCode == 16 || // shift
        keyCode == 20 || // CapsLock
        keyCode == 35 || // end
        keyCode == 36 || // home
        keyCode == 37 || // right
        keyCode == 39 || // left
        keyCode == 45 || // insert
        keyCode == 46    //  '.'
       ))
    {
       return true;
    } else{
       return stopKeyEvent(e);
    }
}

/*-------------------------------------------------------------------------
    Spec      : Keypress시 input에 입력 key 값이 숫자, '-' 만입력 할수 있도록 하는 함수
    Parameter : 해당사항 없음
    Return    : Boolean
    Ex        : onKeypress='keyNumericDash(event);'
    Ref       : 0->48(96), 9->57(105) '-'->45
-------------------------------------------------------------------------*/
function keyNumericDash(e)
{
    var keyCode = getEventKeyCode(e);
    if((keyCode >= 48 && keyCode <= 57) ||
       (keyCode == 8 ||  // backspace
        keyCode == 9 || // tab
        keyCode == 13 || // enter
        keyCode == 16 || // shift
        keyCode == 20 || // CapsLock
        keyCode == 35 || // end
        keyCode == 36 || // home
        keyCode == 37 || // right
        keyCode == 39 || // left
        keyCode == 45    // '-'
      )) {
        return true;
    } else {
        return stopKeyEvent(e);
    }
}

/*-------------------------------------------------------------------------
    Spec      : Keypress시 input에 입력 key 값이 숫자, '.'만입력 할수 있도록 하는 함수
    Parameter : 해당사항 없음
    Return    : Boolean
    Ex        : onKeypress='keyDouble(event);'
    Ref       : 0-48(96), 9-57(105) '.':46
-------------------------------------------------------------------------*/
function keyDouble(e)
{
    var keyCode = getEventKeyCode(e);

    if((keyCode >= 48 && keyCode <= 57) ||
       (keyCode == 8  || // backspace
        keyCode == 9  || // tab
        keyCode == 13 || // enter
        keyCode == 16 || // shift
        keyCode == 20 || // CapsLock
        keyCode == 35 || // end
        keyCode == 36 || // home
        keyCode == 37 || // right
        keyCode == 39 || // left
        keyCode == 45 || // '-'
        keyCode == 46    // '.'
      )) {
        return true;
    } else {
        return stopKeyEvent(e);
    }
}

/*-------------------------------------------------------------------------
    Spec      :input에 입력 값에 대해서 ", ', &, ?, \ 문자를 입력하지 못하게 함.
    Parameter : 해당 Object Field
    Return    :
    Ex        : onKeyPress="javascript:keySpecialChar();"
-------------------------------------------------------------------------*/
function keySpecialChar(e) {
    var keyCode = getEventKeyCode(e);

    if(keyCode==34 || // "
       keyCode==38 || // &
       keyCode==39 || // '
       keyCode==63 || // ?
       keyCode==92) { // \
        return stopKeyEvent(e);
    } else {
        return true;
    }
}

/*-------------------------------------------------------------------------
    Notes     : 금액 데이터를 금액 포맺으로 변경하는 함수
    Parameter : 금액
    Return    : 시간
    use       : onKeyup="keyupCurrencyObj(event, this);"
-------------------------------------------------------------------------*/
function keyupCurrencyObj(e, obj){
    var keyCode = getEventKeyCode(e);

    if((keyCode >= 48 && keyCode <= 57)  ||
       (keyCode >= 96 && keyCode <= 105) ||
       (keyCode == 8  || // backspace
        keyCode == 13 || // enter
        keyCode == 20 || // CapsLock
        keyCode == 35 || // end
        keyCode == 36 || // home
        keyCode == 37 || // right
        keyCode == 39 || // left
        keyCode == 45    // '-'
     )) {
        obj.value = formatCurrency(obj.value);
    }
}

//****************************************************************************
// 입력값 체크 관련 함수
//****************************************************************************

/*-------------------------------------------------------------------------
    Spec      : 입력값에 특정 문자(chars)가 있는지 체크
    Parameter : 해당 값
    Return    : Boolean
    Ex        : if (!restrictDefaultChars(form.name.value)) {
                       alert("이름 필드에는 특수 문자를 사용할 수 없습니다.");
                   }
-------------------------------------------------------------------------*/
function restrictDefaultChars(str) {
    return restrictChars(str, CBH_RESTRICT_CHARS);
}

/*-------------------------------------------------------------------------
    Spec      : 입력값에 특정 문자(chars)가 있는지 체크
    Parameter : 해당 값
    Return    : Boolean
    Ex        : if (!restrictChars(form.name.value,"!,*&^%$#@~;")) {
                       alert("이름 필드에는 특수 문자를 사용할 수 없습니다.");
                    }
-------------------------------------------------------------------------*/
function restrictChars(str, chars) {
    if(str != null & str.length > 0){
        for (var i=0; i<chars.length; i++) {
           if (str.indexOf(chars.charAt(i)) >= 0) return true;
        }
    }
    return false;
}

/*-------------------------------------------------------------------------
    Notes     : 값이 있는지 없는지를 판단하느 함수
    Parameter : value
    Return    : Boolean
    Use       : isEmptyValue(val)
-------------------------------------------------------------------------*/
function isEmptyValue(val) {
    if(val == null || val == "" || val.length <= 0) return true;
    return false;
}

/*-------------------------------------------------------------------------
    Notes     : 값이 있는지 없는지를 판단하느 함수
    Parameter : value
    Return    : Boolean
    Use       : isEmptyObjectValue(obj)
-------------------------------------------------------------------------*/
function isEmptyObjectValue(obj) {
    return isEmptyValue(getHtmlObjectValue(obj));
}

/*-------------------------------------------------------------------------
    Spec      : 해당값이 숫자인지를 검사하는 함수
    Parameter : 해당 값, 추가하고자 하는 문자
    Return    : Boolean
    Ex        : isNumeric(this.value);
-------------------------------------------------------------------------*/
function isNumeric(str, add){
    var bRet = true;

    if(str != null && str != "") {
        var chars = "0123456789";

        var args = isNumeric.arguments;
        var len = args.length;

        bRet = restrictChars(str, chars);
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : 해당데이터가 금액형식이 맞는지를 판단하는 함수.
    Parameter : 금액
    Return    : boolean
    Use       : isCorrectCurrency(val);
-------------------------------------------------------------------------*/
function isCorrectCurrency(val){
    var str = replace(val, ',',  '');

    if(str.length > 0){
        var i = 0;
        var strLen = str.length;
        var ch = str.charAt(i);

        if(str == "-"){
            return false;
        }

        if(ch == '-') i++;
        while(i < strLen){
            ch = str.charAt(i);
            if (ch < '0' || ch > '9'){
                return false;
            }
            i++;
        }
    }
    return true;
}

/*-------------------------------------------------------------------------
    Notes     : 날짜를 비교하는 함수
    Parameter : 기준일자, 비교일자
    Return    : (srcDate>compDate) = 1, (srcDate==compDate)= 0, (srcDate<compDate) = -1, error(date자료가 아님) = -2
    Use       : compareDate('20050101', "20000507");
-------------------------------------------------------------------------*/
function compareDate(srcDate, compDate){

    var strSrcDate  = replace(srcDate ,DATE_DELIMITER,"");
    var strCompDate = replace(compDate,DATE_DELIMITER,"");

    if(!(isDate(strSrcDate) && isDate(strCompDate)))
        return -2;

    if(strSrcDate > strCompDate)
        return 1;
    else if (strSrcDate == strCompDate)
        return 0;
    else
        return -1;
}

 /*-------------------------------------------------------------------------
    Notes     : 입력값이 이메일 형식인지를 체크하는 함수
    Parameter : Email문자열
    Return    : void
    Use       : if(checkEmailAddress(strEmail))
-------------------------------------------------------------------------*/
 function checkEmailAddress(strEmail) {
    return strEmail.search(/^\s*[\w\~\-\.]+\@[\w\~\-]+(\.[\w\~\-]+)+\s*$/g)>=0;
 }

 /*-------------------------------------------------------------------------
    Notes     : 입력값이 이메일 형식인지를 체크하는 함수
    Parameter : Email Object
    Return    : void
    Use       : if(checkEmailAddress(strEmail))
-------------------------------------------------------------------------*/
 function checkEmailAddressOjbect(objEmail) {
    var bRet = true;
    var strEmail = getHtmlObjectValue(objEmail);

    if(strEmail != null && strEmail != "") {
        // 이메일 체크
        if(!checkEmailAddress(strEmail)) {
            alert("메일주소가 올바르지 않습니다.");
            setObjectFocus(objEmail);
            bRet = false;
        } else {
            // 제한 E-mail
            var arrayRsrctEmail = [];
            var len = arrayRsrctEmail.length;
            for(var i=0; i<len; i++) {
                if(strEmail.lastIndexOf(arrayRsrctEmail[i]) >= 0) {
                    alert(arrayRsrctEmail[i] + " 메일 계정은 사용하실 수 없습니다.");
                    setObjectFocus(objEmail);
                    bRet = false;
                }
            }
        }
    }

    return bRet;
 }

 /*-------------------------------------------------------------------------
    Notes     : 입력값이 URL 형식인지를 체크하는 함수
    Parameter : Url문자열
    Return    : void
    Use       : if(checkUrl(strEmail))
-------------------------------------------------------------------------*/
function checkUrl(strUrl){
    return strUrl.search(/^\s*['http://']+[\w\~\-\.]+\.[\w\~\-]+(\.[\w\~\-]+)+\s*$/g)>=0;
}

/*-------------------------------------------------------------------------
    Notes     : Html object 숫자인지를 판단하는 함수
    Parameter : obj, 메시지 타이틀
    Return    : void
    Use       : if(!checkNumericObject(form.화면출력여부, 'Display여부를')) return false;
-------------------------------------------------------------------------*/
function checkNumericObject(obj, title) {
    var bRet = false;
    // 공백제거
    var chkVal = getHtmlObjectValue(obj);
    if(!isNumeric(chkVal, "")) {
        alert(title + "에 입력하신 숫자는 유효한 숫자가 아닙니다.");
        setObjectFocus(obj);
        bRet = false;
    } else {
        bRet = true;
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : Html object 필수입력체크 함수
    Parameter : obj, 메시지 타이틀
    Return    : void
    Use       : if(!checkNcsrObject(form.화면출력여부, 'Display여부를')) return false;
-------------------------------------------------------------------------*/
function checkNcsrObject(obj, title) {
    var bRet = false;

    // 공백제거
    var chkVal = getTrimStr(getHtmlObjectValue(obj));
    if(isEmptyValue(chkVal)) {

        var objType = getHtmlObjectType(obj);

        if("text"      == objType ||
            "textarea" == objType ||
            "password" == objType) {
            setHtmlObjectValue(obj, chkVal);
            alert(title + " 입력하여 주십시오.");
            setObjectFocus(obj);
            bRet = false;
        } else if("select-one" == objType || 
                  "file"       == objType ||
                  "radio"      == objType) {
            alert(title + " 선택하여 주십시오.");
            setObjectFocus(obj);
            bRet = false;
        }
    } else {
        bRet = true;
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : Html object 금액입력체크 함수
    Parameter : obj, 메시지 타이틀
    Return    : void
    Use       : if(!checkCurrencyObject(form.화면출력여부, 'Display여부를')) return false;
-------------------------------------------------------------------------*/
function checkCurrencyObject(obj, title) {
    var bRet = true;

    var chkVal = getHtmlObjectValue(obj);
    if(chkVal != "") {
        if(!isCorrectCurrency(chkVal)) {

            var args = checkCurrencyObject.arguments;
            var len = args.length;

            var objType = getHtmlObjectType(obj);
            if("text" == objType) {
                if(args == 2) {
                    alert(title + "에 입력하신 금액[ " + chkVal + " ]은 유효하지 않은 금액입니다.");
                } else {
                    alert("입력하신 금액[ " + chkVal + " ]은 유효하지 않은 금액입니다.");
                }
                setObjectFocus(obj);
                bRet = false;
            }
        } else {
            bRet = true;
        }
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : Html object 특수문자 입력체크 함수
    Parameter : obj, 메시지 타이틀
    Return    : void
    Use       : if(!checkCurrencyObject(form.화면출력여부, 'Display여부를')) return false;
-------------------------------------------------------------------------*/
function checkSpecialCharacter(obj, title) {
    var bRet = true;

    var chkVal = getHtmlObjectValue(obj);

    if(chkVal != "") {
        if(restrictDefaultChars(chkVal)) {

            var args = checkSpecialCharacter.arguments;
            var len = args.length;

            var objType = getHtmlObjectType(obj);
            if("text"     == objType ||
               "textarea" == objType ||
               "password" == objType) {
                if(args == 2) {
                    alert(title + "에 입력하신 문자중 특수문자[ " + CBH_RESTRICT_CHARS + " ]는 입력할 수 없습니다.");
                } else {
                    alert("입력하신 문자중 특수문자[ " + CBH_RESTRICT_CHARS + " ]는 입력할 수 없습니다.");
                }
                setObjectFocus(obj);
                bRet = false;
            }
        } else {
            bRet = true;
        }
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : 소수점 들어가는 금액 자릿수 체크하는 함수.
    Parameter : val-체크할 값, constantCnt-정수부분 자리수, decimalCnt-소수부분 자리수, title-타이틀
    Return    : Boolean
    Use       : checkDecimalObj('100.11', 5, 2, '미결금액');
-------------------------------------------------------------------------*/
function checkDecimalObj(obj, constantCnt, decimalCnt, title){

    var objType = getHtmlObjectType(obj);
    var chkVal = getTrimStr(getHtmlObjectValue(obj));
    if(objType == "text" && chkVal != ""){
        var chkVal = replace(chkVal, ',', '');
        var preCnt = 0; // 정수부분 자리수
        var posCnt = 0; // 소수부분 자리수
        var dotPos = 0; // 소수점 위치
        var totalLen = 0;
        var chkConstantCnt = 0;

        var args = checkDecimalObj.arguments;
        var len = args.length;

        if(len == 3) title = "금액";

        if(isNaN(chkVal)){
            alert("\"" + obj.value + "\""+"은(는) 올바른 " + title + "형식 아닙니다.");
            setObjectFocus(obj);
            return false;
        } else {
            dotPos = chkVal.indexOf(".");
            // '-'부호 입력시
            if(chkVal.substring(0,1) == '-')
                chkConstantCnt = constantCnt + 1;
            else chkConstantCnt = constantCnt;

            if(dotPos == -1){
                preCnt = chkVal.length;
                if(preCnt > chkConstantCnt){
                    alert(title + "의 정수부분은 " + constantCnt + "자리이하로 입력하십시오.");
                    setObjectFocus(obj);
                    return false;
                }
            } else if(dotPos > 0) {
                preCnt = dotPos;
                totalLen = chkVal.length;

                if(totalLen == ++dotPos){
                    alert("\"" + chkVal + "\""+"은(는) 올바른 " + title + "형식 아닙니다.");
                    setObjectFocus(obj);
                    return false;
                } else {
                    posCnt = chkVal.substring(preCnt+1).length;
                }

                if(preCnt > chkConstantCnt){
                    alert(title + "의 정수부분은 " + constantCnt + "자리이하로 입력하십시오.");
                    setObjectFocus(obj);
                    return false;
                }

                if(posCnt > decimalCnt){
                    alert(title + "의 소수부분은 " + decimalCnt + "자리이하로 입력하십시오.");
                    setObjectFocus(obj);
                    return false;
                }
            }
        }
    }

    return true;
}

/*-------------------------------------------------------------------------
    Spec      : Text Obj Length Check
    Parameter : obj, maxLen
    Return    : boolean
-------------------------------------------------------------------------*/
function checkObjectLength(obj, msgTitle, maxLen){
    var nbytes = getByteLength(getHtmlObjectValue(obj));

    var args = checkObjectLength.arguments;
    var len = args.length;

    switch (len) {
        case 1:
            msgTitle = "";
            maxLen = obj.maxLength;
            break;
        case 2:
            maxLen = obj.maxLength;
            break;
        default:
            break;
    }

    if(typeof(maxLen) != "undefined") {
        if(nbytes > maxLen){
            alert(msgTitle + " 총 영문기준 "+ maxLen + "자, 한글기준 "+(maxLen/2)+"자까지 입력 할 수 있습니다.(현재 영문기준 " + nbytes + "자를 입력하셨습니다.)");
            obj.focus();
            return false;
        }
    }

    return true;
}

/*-------------------------------------------------------------------------
    Notes     : Html object 숫자인지를 판단하는 함수
    Parameter : obj, 메시지 타이틀
    Return    : void
    Use       : if(!checkNumericObject(form.화면출력여부, 'Display여부를')) return false;
-------------------------------------------------------------------------*/
function checkNumericObject(obj, title) {
    var bRet = false;
    // 공백제거
    var chkVal = getTrimStr(getHtmlObjectValue(obj));
    if(!isNumeric(chkVal, "")) {
        alert(title + " 에 입력하신 숫자는 유효한 숫자가 아닙니다.");
        setObjectFocus(obj);
        bRet = false;
    } else {
        bRet = true;
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : Html object 올바른 E-Mail주소인지를 판단하는 함수
    Parameter : obj, 메시지 타이틀
    Return    : void
    Use       : if(!checkEmailAddrObject(form.E_Mail, 'Display여부를')) return false;
-------------------------------------------------------------------------*/
function checkEmailAddrObject(obj, title) {
    var bRet = false;
    // 공백제거
    var chkVal = getTrimStr(getHtmlObjectValue(obj));
    if(chkVal != "" && !checkEmailAddress(chkVal)) {

        var args = checkEmailAddrObject.arguments;
        var len = args.length;
        if(len == 2) {
            alert(title + "에 입력하신 E-Mail 주소는 올바른 E-Mail주소가 아닙니다.");
        } else {
            alert("올바른 E-Mail주소가 아닙니다.");
        }

        setObjectFocus(obj);
        bRet = false;
    } else {
        bRet = true;
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : Html object 올바른 URL인지를 판단하는 함수
    Parameter : obj, 메시지 타이틀
    Return    : void
    Use       : if(!checkUrlObject(form.url, 'URL')) return false;
-------------------------------------------------------------------------*/
function checkUrlObject(obj, title) {
    var bRet = false;
    // 공백제거
    var chkVal = getTrimStr(getHtmlObjectValue(obj));
    if(chkVal != "" && !checkUrl(chkVal)) {

        var args = checkUrlObject.arguments;
        var len = args.length;
        if(len == 2) {
            alert(title + "에 입력하신 URL은 올바른 URL이 아닙니다.");
        } else {
            alert("올바른 URL이 아닙니다.");
        }

        setObjectFocus(obj);
        bRet = false;
    } else {
        bRet = true;
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : 첨부파일의 확장자를 체크하는 함수.
    Parameter : 파일경로, 확장자("ppt")
    Return    : boolean
    Use       : var bRet = isFileExtension(form.testFile, "ppt");
-------------------------------------------------------------------------*/
function checkAttachFile(objFile, strExtension){
    return checkAttachFileByExtensions(objFile, [strExtension]);
}

/*-------------------------------------------------------------------------
    Notes     : 첨부파일의 확장자를 체크하는 함수.
    Parameter : 파일경로, 확장자("ppt")
    Return    : boolean
    Use       : var bRet = isFileExtension(form.testFile, ["exe", "ppt", "xls"]);
-------------------------------------------------------------------------*/
function checkAttachFileByExtensions(objFile, arrayExtensions){
    var bRet = true;

    var strFilePath = objFile.value;
    if(strFilePath != null && strFilePath != "") {
        if(!checkFileByExtensions(strFilePath, arrayExtensions)) {
            alert("첨부파일이 올바르지 않습니다. [" + arrayExtensions + "] 파일만 첨부가 가능합니다.");
            setObjectFocus(objFile);
            bRet = false;
        }
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : 이미지파일의 폭과 높이를 체크하는 함수.
    Parameter : 파일경로
    Return    : String
    Use       : if(checkImageFileWidthHeight(objFile, 100, 100)) return false;
-------------------------------------------------------------------------*/
function checkImageFileWidthHeight(objFile, nWidth, nHeight){
    var bRet = true;

    var imgFilePath = objFile.value;
    if(imgFilePath != "") {
        var fileExtension = getFileExtension(imgFilePath).toLowerCase();
        // 이미지파일이 Gif인 경우 체크함.
        if("gif" == fileExtension) {
            var img = new Image();
            img.src = imgFilePath;

            if(nWidth < img.width) {
                bRet = false;
            } else {
                if(nHeight < img.height) {
                    bRet = false;
                }
            }
            
            // 메시지 처리
            if(!bRet) {
                alert("이미지 파일("+ fileExtension +")은 폭(Width)이 " + nWidth + ", 높이(Height)가 " + nHeight + " 인 경우만 등록이 가능합니다.\n현재 입력하신 이미지 파일의 사이즈는 폭(Width)이" + img.width +", 높이(Height)가 " + img.height +"입니다.");
                setObjectFocus(objFile);
            }
        }
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : 파일의 확장자를 체크하는 함수.
    Parameter : 파일경로, 확장자("ppt")
    Return    : boolean
    Use       : var bRet = isFileExtension(form.testFile.value, "ppt");
-------------------------------------------------------------------------*/
function isFileExtension(strFilePath, strExtension){
    return checkFileByExtensions(strFilePath, [strExtension]);
}

/*-------------------------------------------------------------------------
    Notes     : 파일의 확장자를 체크하는 함수.
    Parameter : 파일경로, 확장자(["exe", "ppt", "xls"])
    Return    : boolean
    Use       : var bRet = checkFileExtension(form.testFile.value, ["exe", "ppt", "xls"]);
-------------------------------------------------------------------------*/
function checkFileByExtensions(strFilePath, arrayExtensions){
    var bRet = false;

    var strExtension = getFileExtension(strFilePath).toLowerCase();

    if(strExtension != "") {
        var len = 0;
        if(arrayExtensions != null) len = arrayExtensions.length;
        for(var i=0; i<len; i++) {
            if(strExtension == arrayExtensions[i].toLowerCase()) {

                bRet = true;
                i = len;
            }
        }
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : 파일객체의 확장자를 구하는 함수.
    Parameter : 파일경로
    Return    : String
    Use       : var strExtension = getFileExtension(strFilePath);
-------------------------------------------------------------------------*/
function getFileExtension(strFilePath){
    var strExtension = "";

    if(strFilePath != null && strFilePath != "") {
        var lastIndex = strFilePath.lastIndexOf(".") + 1;

        if(lastIndex > 0) {
            if(strFilePath.length > lastIndex) {
                strExtension = strFilePath.substring(lastIndex);
            }
        }
    }

    return strExtension;
}

/*-------------------------------------------------------------------------
    Notes     : 파일경로 정보에서 파일명을 구하는 함수.
    Parameter : 파일경로
    Return    : String
    Use       : var strFileName = getFileName('파일명');
-------------------------------------------------------------------------*/
function getFileName(strFilePathInfo, strFileSeparator) {
    var strFileName = "";

    if(strFilePathInfo != null && strFilePathInfo != "") {
        if(typeof(strFileSeparator) == "undefined") strFileSeparator = "/";

        var lastIndex = strFilePathInfo.lastIndexOf(strFileSeparator)+1;

        if(lastIndex > 0) {
            if(strFilePathInfo.length > lastIndex) {
                strFileName = strFilePathInfo.substring(lastIndex);
            } else {
                strFileName = strFilePathInfo;
            }
        } else {
            strFileName = strFilePathInfo;
        }
    }

    return strFileName;
}

//****************************************************************************
// Object 관련 함수
//****************************************************************************
/*-------------------------------------------------------------------------
    Notes     : button object에서 버튼을 활성화 하는 함수
    Parameter : 버튼 obj
    Return    : void
    Use       : setEnableButton(mainForm.btnPost, "goUpdate");
-------------------------------------------------------------------------*/
function enableImgButton(btnId, funcName){
    var btnObj = document.getElementById(btnId);
    if(btnObj != null) {
        btnObj.style.cursor = "pointer";
        btnObj.onclick = new Function(funcName);
    }
}

/*-------------------------------------------------------------------------
    Notes     : button object에서 버튼을 비활성화 하는 함수
    Parameter : 버튼 obj
    Return    : void
    Use       : setDisableButton(mainForm.btnPost);
-------------------------------------------------------------------------*/
function disableImgButton(btnId){
    var btnObj = document.getElementById(btnId);
    if(btnObj != null) {
        btnObj.style.cursor = "";
        btnObj.onclick = new Function("return true;");
    }
}

/*-------------------------------------------------------------------------
    Notes     : Object명을 가지고 HTML Object를 생성하는 함수.
    Parameter : strObj
    Return    : Html Object
    Use       : getElementById("특수지역분류코드");
-------------------------------------------------------------------------*/
function getElementById(strObj){
    return document.getElementById(strObj);
}

/*-------------------------------------------------------------------------
    Notes     : Object Id를 가지고 Value를 구하는 함수.(text, select, radio, password, textarea)
    Parameter : 해당 Object Id
    Return    : void
    Use       : var rdoVal = getObjectValueById("TEST");
-------------------------------------------------------------------------*/
function getObjectValueById(strObjId){
    return getHtmlObjectValue(getElementById(strObjId));
}

/*-------------------------------------------------------------------------
    Notes     : Object Id를 가지고 Value를 구하는 함수.(text, select, radio, password, textarea)
    Parameter : 해당 Object Id
    Return    : void
    Use       : var rdoVal = getObjectValueById("TEST");
-------------------------------------------------------------------------*/
function setObjectValueById(strObjId, val){
    return setHtmlObjectValue(getElementById(strObjId), val);
}

/*-------------------------------------------------------------------------
    Notes     : Object Id를 가지고 Focus 설정
    Parameter : 해당 Object Id
    Return    : void
    Use       : setObjectFocusById("txtText");
-------------------------------------------------------------------------*/
function setObjectFocusById(strObjId) {
    setObjectFocus(getElementById(strObj));
}

/*-------------------------------------------------------------------------
    Notes     : Radio Button Object의 값을 구하는 함수.
    Parameter : 해당 Object Field
    Return    : String
    Use       : var rdoVal = getRadioButton(rdoObj);
-------------------------------------------------------------------------*/
function getRadioButtonValue(rdoObj){
    var retVal = "";
    if(rdoObj != null) {
        if("radio" == getHtmlObjectType(rdoObj)) {
            if(rdoObj.length > 0) {
                for(var i=0; i<rdoObj.length; i++){
                    if (rdoObj[i].checked){
                        retVal = rdoObj[i].value;
                        i=rdoObj.length;
                    }
                }
            } else {
                retVal = rdoObj.value;
            }
        }
    }

    return retVal;
}

/*-------------------------------------------------------------------------
    Notes     : Radio Button Object의 값을 설정하는 함수.
    Parameter : 해당 Object Field, 해당 값
    Return    : void
    Use       : var rdoVal = setRadioButton(rdoObj);
-------------------------------------------------------------------------*/
function setRadioButtonValue(rdoObj, val){
    if(rdoObj != null) {
        if("radio" == getHtmlObjectType(rdoObj)) {
            for(var i=0; i<rdoObj.length; i++){
                if(val == rdoObj[i].value){
                    rdoObj[i].checked = true;
                    i=rdoObj.length;
                }
            }
        }
    }
}

/*-------------------------------------------------------------------------
    Notes     : Select Box Object의 값을 구하하는 함수.
    Parameter : 해당 Object Field
    Return    : String
    Use       : var rdoVal = getListBoxValue(cmbObj);
-------------------------------------------------------------------------*/
function getSelectBoxValue(slcObj){
    return getSelectedValueOfSelectBox(slcObj);
}

/*-------------------------------------------------------------------------
    Notes     : Select Box Object의 값을 구하하는 함수.
    Parameter : 해당 Object Field
    Return    : String
    Use       : var rdoVal = getListBoxValue(cmbObj);
-------------------------------------------------------------------------*/
function getSelectedValueOfSelectBox(slcObj){
    var retValue = "";

    if(slcObj != null) {
        var selectedIndex = slcObj.selectedIndex;
        if(selectedIndex != -1) {
            retValue = slcObj.options[selectedIndex].value;
        }
    }

    return retValue;
}

/*-------------------------------------------------------------------------
    Notes     : Select Box Object의 값을 설정하는 함수.
    Parameter : 해당 Object Field, 해당 값
    Return    : void
    Use       : var rdoVal = setListBoxValue(cmbObj, "1");
-------------------------------------------------------------------------*/
function setSelectBoxValue(slcObj, val){
    if(slcObj != null) {
        if("select-one" == getHtmlObjectType(slcObj)) {
            var len = slcObj.options.length;
            for (var i=0; i<len; i++) {
                if ( val ==  slcObj.options[i].value ){
                    slcObj.options[i].selected = true;
                    i = len;
                }
            }
        }
    }
}

/*-------------------------------------------------------------------------
    Notes     : Select Box의 Text값을 변경하는 함수.
    Parameter : Select Box Object, 기준값, 변경할 Text
    Return    : void
    Use       : changeSelecteText(document.mainForm.캠퍼스스타지역구분코드, '00', '전체');
-------------------------------------------------------------------------*/
function changeSelectBoxText(slcObj, val, txtVal){
    var len = slcObj.options.length;

    for(var i=0; i<len; i++) {
        if (val == slcObj.options[i].value) {
            slcObj.options[i].text = txtVal;
            i = len;
        }
    }
}

/*-------------------------------------------------------------------------
    Notes     : Select Box의 값에 해당하는 option를 제거하는 함수.
    Parameter : Select Box Object, 기준값
    Return    : void
    Use       : removeOptionSelectBoxByValue(document.mainForm.캠퍼스스타지역구분코드, '00');
-------------------------------------------------------------------------*/
function removeOptionSelectBoxByValue(slcObj, val){
    var len = slcObj.options.length;

    for(var i=0; i<len; i++) {
        if (val == slcObj.options[i].value) {
            slcObj.options[i] = null;
            i = len;
        }
    }
}

/*-------------------------------------------------------------------------
    Notes     : Text Object에 입력한 값을 가지고 Select Box의 Text값을 찾는하는 함수.
    Parameter : Text Object, 해당 Select Object Field
    Return    : void
    Use       : onKeyup="javascript:searchTextOfSelect(this, document.mainForm.slcTest);"
-------------------------------------------------------------------------*/
function searchTextOfSelect(txtObj, slcObj){
    var txtVal = txtObj.value;

    if(txtVal == "") {
        slcObj.options[0].selected = true;
    } else {
        var len = slcObj.options.length;
        for(var i=0; i<len; i++) {
            if(slcObj.options[i].text.indexOf(txtVal) >= 0) {
                slcObj.options[i].selected = true;
                i = len;
            }
        }
    }
}

/*-------------------------------------------------------------------------
    Notes     : Select Object에서 선택된 값을 위로 올리는 함수.
    Parameter : Select Object Field, fixedIndex(고정할Row번호)
    Return    : void
    Use       : upMultipleSelectData(document.mainForm.slcTest, 1);
-------------------------------------------------------------------------*/
function upMultipleSelectData(slcObj, fixedIndex){
    if(slcObj != null) {
        var args = upMultipleSelectData.arguments;
        var lenArgs = args.length;
        if(lenArgs < 2) fixedIndex = 0;

        var len = slcObj.options.length;
        if(len > 0) {
            var selectedIndex = slcObj.selectedIndex;
            if(selectedIndex > fixedIndex && selectedIndex > 0) {
                swapSelectObjectData(slcObj, selectedIndex, (selectedIndex-1));
             }
        }
    }
}

/*-------------------------------------------------------------------------
    Notes     : Select Object에서 선택된 값을 아래로 내리는 함수.
    Parameter : Select Object Field, fixedIndex(고정할Row번호)
    Return    : void
    Use       : downMultipleSelectData(document.mainForm.slcTest, 1);
-------------------------------------------------------------------------*/
function downMultipleSelectData(slcObj, fixedIndex){
    if(slcObj != null) {
        var args = downMultipleSelectData.arguments;
        var lenArgs = args.length;
        if(lenArgs < 2) fixedIndex = 0;

        var len = slcObj.options.length;
        if(len > 0) {
            var limitMax = len - 1;
            var selectedIndex = slcObj.selectedIndex;
            if(selectedIndex >= fixedIndex && selectedIndex < limitMax) {
                swapSelectObjectData(slcObj, selectedIndex, (selectedIndex+1));
            }
        }
    }
}

/*-------------------------------------------------------------------------
    Notes     : Html Object Value를 구하는 함수.(text, select, radio, password, textarea)
    Parameter : 해당 Object Field
    Return    : void
    Use       : var rdoVal = swapSelectObjectData(obj);
-------------------------------------------------------------------------*/
function swapSelectObjectData(slcObj, fromIndex, toIndex){
    var tempText    = slcObj.options[fromIndex].text;
    var tempValue   = slcObj.options[fromIndex].value;
    var tempColor   = slcObj.options[fromIndex].style.color;
    var tempBgColor = slcObj.options[fromIndex].style.backgroundColor;

    slcObj.options[fromIndex].text     = slcObj.options[toIndex].text;
    slcObj.options[fromIndex].value    = slcObj.options[toIndex].value;
    slcObj.options[fromIndex].style.color           = slcObj.options[toIndex].style.color;
    slcObj.options[fromIndex].style.backgroundColor = slcObj.options[toIndex].style.backgroundColor;
    slcObj.options[fromIndex].selected = false;

    slcObj.options[toIndex].text     = tempText;
    slcObj.options[toIndex].value    = tempValue;
    slcObj.options[toIndex].style.color = tempColor;
    slcObj.options[toIndex].style.backgroundColor = tempBgColor;
    slcObj.options[toIndex].selected = true;
}

/*-------------------------------------------------------------------------
    Notes     : Html Object Value를 구하는 함수.(text, select, radio, password, textarea)
    Parameter : 해당 Object Field
    Return    : void
    Use       : var rdoVal = getHtmlObjectType(obj);
-------------------------------------------------------------------------*/
function getHtmlObjectType(obj){
    var objType = "";

    if(obj != null && "object" == typeof(obj)) {
        objType = obj.type;
        if(undefined == objType) {
            var len = obj.length;
            if(len > 0) {
                objType = obj[0].type;
            } else {
                objType = "";
            }
        }
    }

    return objType;
}

/*-------------------------------------------------------------------------
    Notes     : Html Object Value를 구하는 함수.(text, select, radio, password, textarea)
    Parameter : 해당 Object Field
    Return    : void
    Use       : var rdoVal = getHtmlObjectValue(obj);
-------------------------------------------------------------------------*/
function getHtmlObjectValue(obj){
    var retVal = "";

    var objType = getHtmlObjectType(obj);
    if(objType != null && objType != "") {
        if("text"      == objType ||
            "textarea" == objType ||
            "file"     == objType ||
            "password" == objType ||
            "hidden"   == objType) {
            retVal = obj.value;
        } else if("select-one" == objType) {
            retVal = getSelectedValueOfSelectBox(obj);
        } else if("radio" == objType) {
            retVal = getRadioButtonValue(obj);
        }
    }

    return retVal;
}

function cbh_getHtmlObjectValue(obj){
    return getHtmlObjectValue(obj);
}

/*-------------------------------------------------------------------------
    Notes     : Html Object Value를 설정하는 함수.(text, select, radio, password, textarea)
    Parameter : 해당 Object Field, 해당 값
    Return    : void
    Use       : var rdoVal = setHtmlObjectValue(cmbObj, '3');
-------------------------------------------------------------------------*/
function setHtmlObjectValue(obj, val){
    var bRet = true;
    if(obj != null) {
        var objType = getHtmlObjectType(obj);

        if("text"       == objType ||
            "textarea"  == objType ||
            "file"      == objType ||
            "password"  == objType ||
            "hidden"    == objType) {
            obj.value = val;
        } else if(objType == "select-one") {
            setSelectBoxValue(obj, val);
        } else if(objType == "radio") {
            setRadioButtonValue(obj, val);
        } else {
            alert("설정하고자 하는 Object Type("+ obj.name + ":" + objType +")은 지원하지 않습니다.");
            bRet = false;
        }
    } else {
        bRet = false;
    }

    return bRet;
}

function cbh_setHtmlObjectValue(obj, val){
    return setHtmlObjectValue(obj, val);
}

/*-------------------------------------------------------------------------
    Notes     : Object Focus 설정
    Parameter : Form Object
    Return    : void
    Use       : setObjectFocus(url);
-------------------------------------------------------------------------*/
function setObjectFocus(obj) {
    if(null != obj) {
        var objType = getHtmlObjectType(obj);

        if("text"     == objType ||
           "textarea" == objType ||
           "file"     == objType ||
           "password" == objType) {
            if(!obj.readOnly && !obj.disabled) {
                obj.focus();
                obj.select();
            }
        } else if("select-one" == objType ||
                  "radio"      == objType) {
            if(!obj.disabled) {
                obj.focus();
            }
        }
    }
}

/*-------------------------------------------------------------------------
    Spec         : input에 입력 값을 특정 문자로 왼쪽 채우는 함수
    Parameter    : 해당 Object Field, 길이, 문자
    Return       : Boolean
    Ex           : onBlur = setLPadObj(this, 3, '0')
-------------------------------------------------------------------------*/
function setLPadObj(obj, len, ch) {

    var objType = getHtmlObjectType(obj);

    if(objType == "text" && !obj.readOnly && !obj.disabled){
        obj.value = getLpadVal(obj.value, len, ch);
    }
}

/*-------------------------------------------------------------------------
    Spec         : input에 입력 값을 특정 문자로 오른쪽을 채우는 함수
    Parameter    : 해당 Object Field, 길이, 문자
    Return       : Boolean
    Ex           : onBlur = setRPadObj(this, 3, '0')
-------------------------------------------------------------------------*/
function setRPadObj(obj, len, ch) {
    var objType = getHtmlObjectType(obj);

    if(objType == "text" && !obj.readOnly && !obj.disabled){
        obj.value = getRpadVal(obj.value, len, ch);
    }
}

/*-------------------------------------------------------------------------
    Spec         : Form에 첫번째 입력 Object에 Focus를 설정하는 함수.
    Parameter    : 해당 Form Object
    Return       : void
    Ex           : <script>setFirstObjectFocus(document.mainForm);</script>
-------------------------------------------------------------------------*/
function setFirstObjectFocus(frmObj){
    var arrayElements = frmObj.elements;

    var nObjCnt = 0;
    if(arrayElements != null) nObjCnt = arrayElements.length;
    for(var i=0; i<nObjCnt; i++) {
        if(arrayElements[i].type == "text"     ||
           arrayElements[i].type == "password" ||
           arrayElements[i].type == "file"     ||
           arrayElements[i].type == "textarea") {
           if(!arrayElements[i].readOnly && !arrayElements[i].disabled) {
                try {
                    setObjectFocus(arrayElements[i]);
                } catch (e){}
                i = nObjCnt;
            }
        } else if(arrayElements[i].type == "select-one"      ||
                  arrayElements[i].type == "checkbox"        ||
                  arrayElements[i].type == "select-multiple") {
           if(!arrayElements[i].disabled) {
                try {
                    setObjectFocus(arrayElements[i]);
                } catch (e){}
                i = nObjCnt;
            }
        }
    }
}

/*-------------------------------------------------------------------------
    Spec         : 입력길이가 maxLength가 되면 다음 Object로 이동함.
    Parameter    : 체크 Object Field, 다음 Object Field
    Return       : Boolean
    Ex           : onKeyup="javascript:nextObjByMaxLen(event, this, document.mainForm.금액);"
-------------------------------------------------------------------------*/
function nextObjByMaxLen(e, chkObj, nextObj) {
    var keyCode = getEventKeyCode(e);
    if(keyCode != 8 &&  // backspace
       keyCode != 9 && // tab
       keyCode != 13 && // enter
       keyCode != 16 && // shift
       keyCode != 17 && // Ctrl
       keyCode != 20 && // CapsLock
       keyCode != 35 && // end
       keyCode != 36 && // home
       keyCode != 37 && // right
       keyCode != 38 && // up
       keyCode != 39 && // right
       keyCode != 40 && // down
       keyCode != 45 && // insert
       keyCode != 46 && // delete
       keyCode != 144 ) // Number Lock
    {

        if(chkObj.type == "text" && !chkObj.readOnly){
            if(chkObj.value.length == chkObj.maxLength)
                setObjectFocus(nextObj);
        }
    }
}

/*-------------------------------------------------------------------------
    Spec         : 화면에서 Enter를 입력하면 해당 이미지버튼을 클릭한 효과를 줌.
    Parameter    : Key Event, 버튼ID
    Return       : Boolean
    Ex           : onKeypress="javascript:enterKeypress(event, 'btnUpdate');"
-------------------------------------------------------------------------*/
var srcEventType = "";
var srcEventObj  = null;
function excuteIdByEnterKey(e, strBtnId) {
    var keyCode = getEventKeyCode(e);
    if(keyCode == 13) { // Enter
        srcEventObj = null;
        if(e.srcElement) {
            srcEventObj = e.srcElement;
        } else {
            srcEventObj = e.target;
        }
        if(srcEventObj != null) srcEventType = srcEventObj.type;

        if(srcEventType != "" && srcEventType != "textarea") {
            document.getElementById(strBtnId).onclick();
        }
    }
}

/*-------------------------------------------------------------------------
    Spec         : 화면에서 Enter를 입력하면 해당 함수를 실행함.
    Parameter    : Key Event, Function명
    Return       : void
    Ex           : onKeypress="javascript:excuteFuncByEnterKey(event, \"goUpdate('btnUpdate')\");"
-------------------------------------------------------------------------*/
function excuteFuncByEnterKey(e, strFuncName) {
    var keyCode = getEventKeyCode(e);
    if(keyCode == 13) { // Enter
        srcEventObj = null;
        if(e.srcElement) {
            srcEventObj = e.srcElement;
        } else {
            srcEventObj = e.target;
        }
        if(srcEventObj != null) srcEventType = srcEventObj.type;

        if(srcEventType != "" && srcEventType != "textarea") {
            eval(strFuncName);
        }
    }
}

//****************************************************************************
// 문자열 관련 함수
//****************************************************************************
/*-------------------------------------------------------------------------
    Notes     : 해당 문자열의 앞뒤공백을 제거하는 함수.
    Parameter : 문자열
    Return    : String
    Use       : getTrimStr(val);
-------------------------------------------------------------------------*/
function getTrimStr(val) {
    return val.replace(/^\s+|\s+$/g,"");
}

/*-------------------------------------------------------------------------
    Notes     : 해당 문자열의 앞공백을 제거하는 함수.
    Parameter : 문자열
    Return    : String
    Use       : getLTrimStr(val);
-------------------------------------------------------------------------*/
function getLTrimStr(val) {
    return val.replace(/^\s+/,"");
}

/*-------------------------------------------------------------------------
    Notes     : 해당 문자열의 뒤공백을 제거하는 함수.
    Parameter : 문자열
    Return    : String
    Use       : getRTrimStr(val);
-------------------------------------------------------------------------*/
function getRTrimStr(val) {
    return val.replace(/\s+$/,"");
}

/*-------------------------------------------------------------------------
    Spec      : 특정 문자로 왼쪽을 채우는 물자열을 구하는 함수.
    Parameter : value, 길이, 문자
    Return    : Boolean
    Ex        : var lpad = getLpadVal(this, 3, '0')
-------------------------------------------------------------------------*/
function getLpadVal(val, len, ch) {
    var retVal = "";

    if(val == "" || val == null) {
        retVal = "";
    } else {
        var fillVal = "";

        var chSize = len - val.length;
        for(var i=0; i<chSize; i++){
            fillVal += ch;
        }
        retVal = fillVal +  val;
    }

    return retVal;
}


/*-------------------------------------------------------------------------
    Spec      : 특정 문자로 오른쪽을 채우는 물자열을 구하는 함수.
    Parameter : value, 길이, 문자
    Return    : Boolean
    Ex        : var rpad = getRpadVal(this, 3, '0')
-------------------------------------------------------------------------*/
function getRpadVal(val, len, ch) {
    var retVal = "";

    if(val == "" || val == null) {
        retVal = "";
    } else {
        var fillVal = "";
        var chSize = len - val.length;
        for(var i=0; i<chSize; i++){
            fillVal += ch;
        }
        retVal = val + fillVal;
    }

    return retVal;
}

/*-------------------------------------------------------------------------
    Notes     : 해당데이터가 금액형식으로 변환하는 함수.
    Parameter : 금액
    Return    : boolean
    Use       : formatCurrency(num);
-------------------------------------------------------------------------*/
function formatCurrency(num) {
// Function courtesy of:  Cyanide_7
    var numData="";
    var startRealNum=0;
    var sign="";//양수[],음수[-]
    numData=num;

    numData = numData.toString().replace(/\$|\,/g,'');

    if(isNaN(numData)) {
        numData = num;
        return "";
    }
    if(numData.substring(0,1)=="-"){
        sign="-";
        numData=numData.substring(1);
    }

    //소숫점 및 "000.." 제거
    for(var i=0;i<numData.length;i++){
        if(numData.charAt(i)!='0'){
            break;
        }
        startRealNum++;
    }

    if(numData.length!=1&&startRealNum>0){
        if(numData.charAt(startRealNum)=='.'){
            numData = numData.substring(startRealNum-1);
        }else{
            numData = numData.substring(startRealNum);
        }
    }

    //소숫점 제거
    if(numData.charAt(0)=="."){
        numData="0."+numData.substring(1);
    }

    tmpNum=numData.split('.');
    if(tmpNum.length==1){
        numData=tmpNum[0];
        cents="";
    }else if(tmpNum.length==2){
        numData =tmpNum[0];
        cents   =tmpNum[1];
    }else{
        return "";
    }

    for (var i = 0; i < Math.floor((numData.length-(1+i))/3); i++)
        numData = numData.substring(0,numData.length-(4*i+3))+','+numData.substring(numData.length-(4*i+3));

    if(cents==""){
        return sign+numData;
    }else{
        return sign+(numData + "." + cents);
    }
}

/*-------------------------------------------------------------------------
    Notes     : 입력값의 바이트 길이를 구하는 함수.
    Parameter : 금액
    Return    : boolean
    Use       : formatCurrency(val);
-------------------------------------------------------------------------*/
function getByteLength(str){
    var byteLength= 0;

    for(var inx=0; inx < str.length; inx++)
    {
        var oneChar = escape(str.charAt(inx));
        if( oneChar.length == 1 )
            byteLength ++;
        else if(oneChar.indexOf("%u") != -1)
            byteLength += 2;
        else if(oneChar.indexOf("%") != -1)
            byteLength += oneChar.length/3;
    }

    return byteLength;
}
/*-------------------------------------------------------------------------
Notes     : 현재 년월일 구하기
Return    : 일자YYYYMMDD
-------------------------------------------------------------------------*/
function getDate(format){
	var date = new Date();
	var fullYear = date.getFullYear().toString();
	var month = (date.getMonth() + 1) < 10 ? "0"+ (date.getMonth() + 1) : "" + (date.getMonth() + 1);
	var day = date.getDate() < 10 ? "0"+ date.getDate() : "" + date.getDate();
	
	if("yyyy-mm-dd" == format)
	{
		return fullYear+'-'+month+'-'+day;
	}	
	
	return fullYear+month+day;

}
/*-------------------------------------------------------------------------
Notes     : 현재 시간 구하기
Return    : 시간 HHMMSS
-------------------------------------------------------------------------*/
function getHIS(format){
	var time = new Date();
	var hours = time.getHours() < 10 ? "0"+ time.getHours() : "" + time.getHours();
	var minutes = time.getMinutes() < 10 ? "0"+ time.getMinutes() : "" + time.getMinutes();
	var seconds = time.getSeconds() < 10 ? "0"+ time.getSeconds() : "" + time.getSeconds();
	
	if("hh:mm:ss" == format)
	{
		return hours+':'+minutes+':'+seconds;
	}	
	
	return hours+minutes+seconds;

}

/*-------------------------------------------------------------------------
    Notes     : 기준일자에서 가감 일자를 구하는 함수.
    Parameter : 기준일자, 가감일
    Return    : 일자
    Use       : getAddDate(thisDay, 1);
-------------------------------------------------------------------------*/
function getAddYearhDate(stdDate, addYear){
    return getAddDate(stdDate, addYear, 0, 0);
}

/*-------------------------------------------------------------------------
    Notes     : 기준일자에서 가감 일자를 구하는 함수.
    Parameter : 기준일자, 가감일
    Return    : 일자
    Use       : getAddDate(thisDay, 1);
-------------------------------------------------------------------------*/
function getAddMonthDate(stdDate, addMonth){
    return getAddDate(stdDate, 0, addMonth, 0);
}

/*-------------------------------------------------------------------------
    Notes     : 기준일자에서 가감 일자를 구하는 함수.
    Parameter : 기준일자, 가감일
    Return    : 일자
    Use       : getAddDate(thisDay, 1);
-------------------------------------------------------------------------*/
function getAddDayDate(stdDate, addDay){
    return getAddDate(stdDate, 0, 0, addDay);
}

/*-------------------------------------------------------------------------
    Notes     : 기준일자에서 가감 일자를 구하는 함수.
    Parameter : 기준일자, 가감년도, 가감월, 가감일
    Return    : 일자
    Use       : getAddDate(thisDay, 0, 1, 0);
-------------------------------------------------------------------------*/
function getAddDate(stdDate, addYear, addMonth, addDay){
    var addDate = "";
    if(stdDate.length == 8){
        var year  = parseInt(stdDate.substring(0, 4), 10) + addYear;
        var month = parseInt(stdDate.substring(4, 6), 10) + addMonth;
        var day   = parseInt(stdDate.substring(6)   , 10) + addDay;

        var dateObj = new Date(year, (month-1), day);

        var strYear  = dateObj.getYear().toString();
        var strMonth = (dateObj.getMonth()+1).toString();
        var strDate  = dateObj.getDate().toString();

        addDate  = (strYear.length  < 4 ? ("19" + strYear) : strYear) +
                   (strMonth.length < 2 ? ("0" + strMonth) : strMonth) +
                   (strDate.length  < 2 ? ("0" + strDate) : strDate);
    }

    return addDate;
}

/*-------------------------------------------------------------------------
    Notes     : 해당년도의 주의 시작일자를 구하는 함수.
    Parameter : 기준년도(1900년도이상), 해당주(1~52)
    Return    : 일자
    Use       : getFirstDateOfWeekByYear("2008", 1));
-------------------------------------------------------------------------*/
function getFirstDateOfWeekByYear(strYear, nWeek) {
    var retDate = "";
    var nYear = parseInt(strYear, 10);

    if(nYear > 1900) {
        if(nWeek >= 1 && nWeek <= 52) {
            var dateObj = new Date(nYear, 0, 1);
            var dayOfWeek = dateObj.getDay();
            var startDay = 0;

            if(dayOfWeek > 0) {
                while(dayOfWeek <= 6) {
                    dayOfWeek++;
                    startDay++;
                }
            }

            var addDay = (nWeek-1) * 7 + startDay;
            retDate = getAddDate((strYear + "0101"), 0, 0, addDay);
        }
    }

    return retDate;
}

/*-------------------------------------------------------------------------
    Notes     : 해당년도의 주의 마지막일자를 구하는 함수.
    Parameter : 기준년도(1900년도이상), 해당주(1~52)
    Return    : 일자
    Use       : getLastDateOfWeekByYear("2008", 1));
-------------------------------------------------------------------------*/
function getLastDateOfWeekByYear(strYear, nWeek) {
    return getAddDate(getFirstDateOfWeekByYear(strYear, nWeek), 0, 0, 6)
}

/*-------------------------------------------------------------------------
    Notes     : 해당년도의 주의 시작일자와 마지막일자를 구하는 함수.
    Parameter : 기준년도(1900년도이상), 해당주(1~52)
    Return    : Array
    Use       : getDateOfWeekByYear("2008", 1));
-------------------------------------------------------------------------*/
function getDateOfWeekByYear(strYear, nWeek) {
    var arrayDate = new Array(2);
    arrayDate[0] = getFirstDateOfWeekByYear(strYear, nWeek);
    arrayDate[1] = getAddDate(arrayDate[0], 0, 0, 6);

    return arrayDate;
}

/*-------------------------------------------------------------------------
    Notes     : 기준일자가 속한 주의 첫번째(일요일기준) 일자를 구하는함수.
    Parameter : 기준일자
    Return    : 일자
    Use       : getDateOfWeek('20080801');
-------------------------------------------------------------------------*/
function getDateOfWeek(strDate) {
    var retDate = "";

    if(strDate != null && strDate.length == 8) {
        var year  = parseInt(strDate.substring(0, 4), 10);
        var month = parseInt(strDate.substring(4, 6), 10);
        var day   = parseInt(strDate.substring(6)   , 10);

        var dateObj = new Date(year, (month-1), day);
        var dayOfWeek = dateObj.getDay();

        retDate = getAddDate(strDate, 0, 0, -(dayOfWeek));
    }

    return retDate;
}

/*-------------------------------------------------------------------------
    Notes     : 기준일자의 주차를 구하는 함수.
    Parameter : 기준일자
    Return    : 주차
    Use       : getWeekOfDate('20080726');
-------------------------------------------------------------------------*/
function getWeekOfDate(strDate){
    var retWeek = -1;
    if(strDate.length == 8){
        var year  = parseInt(strDate.substring(0, 4), 10);
        var month = parseInt(strDate.substring(4, 6), 10);
        var day   = parseInt(strDate.substring(6)   , 10);

        var initDate = new Date(year, 0, 1);
        var dayOfWeek = initDate.getDay();
        var startDay = 0;

        if(dayOfWeek > 0) {
            while(dayOfWeek <= 6) {
                dayOfWeek++;
                startDay++;
            }
        }

        var fromDate = new Date(year, 0, startDay);
        var toDate   = new Date(year, (month-1), day);

        var termDay = (toDate.getTime() - fromDate.getTime())/(1000*60*60*24);

        retWeek = Math.ceil(termDay/7);
    }

    return retWeek;
}

/*-------------------------------------------------------------------------
    Notes     : 특정년월의 주의 시작일자와 종료일자를 구하는 함수.
    Parameter : 기준일자
    Return    : 주차
    Use       : getArrayKorDateOfWeek('20080726', 1);
-------------------------------------------------------------------------*/
function getArrayKorDateOfWeek(strStdDate, week){
	var arrayDate = new Array(2);

    var stdDate = new Date( parseInt(strStdDate.substring(0,4), 10), parseInt(strStdDate.substring(4,6), 10)-1, "" );
	var lastDate = stdDate.getDate(); // 월의 마지막일자를 구함.

	var tempWeek = -1;
	for(var i=1; i<lastDate; i++) {
		tempWeek = Math.ceil(i/7);

		if(week == tempWeek && arrayDate[0] == null) {
			// 같은주가 되면 시작일자를 구함.
			arrayDate[0] = strStdDate.substring(0,4) + strStdDate.substring(4,6) + (i<10 ? "0"+i.toString() : i.toString()); 
		} else if(week < tempWeek) {
			// 구하고자 하는 주보다 큰경우 종료일자를 구함.
			var lastDay = i-1;
			arrayDate[1] = strStdDate.substring(0,4) + strStdDate.substring(4,6) + (lastDay <10 ? "0"+lastDay.toString() : lastDay.toString());
			i = lastDate; // Roof 종료
		}
	}

	// 마지막일자가 없는 경우 월의 마지막일자를 설정함.
	if(arrayDate[1] == null) {
		arrayDate[1] = strStdDate.substring(0,4) + strStdDate.substring(4,6) + lastDate.toString();
	}
	
	return arrayDate;
}


/*-------------------------------------------------------------------------
    Notes     : 주민번호를 체크하는 함수.
    Parameter : 주민번호1, 주민번호2
    Return    : boolean
    Use       : checkJuminNo(val);
-------------------------------------------------------------------------*/
function checkJuminNo2(strJuminNo1, strJuminNo2) {
    return checkJuminNo(strJuminNo1+strJuminNo2);
}

/*-------------------------------------------------------------------------
    Notes     : 주민번호를 체크하는 함수.
    Parameter : 주민번호
    Return    : boolean
    Use       : checkJuminNo(val);
-------------------------------------------------------------------------*/
function checkJuminNo(strJuminNo) {
    var bRet = false;

    if(strJuminNo.length != 13) bRet = false;
    if(!bRet) {
        // 주민번호를 숫자배열로 변환
        var arrayJuminNo = new Array(13);
        for(var i=0; i<arrayJuminNo.length; i++)
            arrayJuminNo[i] = parseInt(strJuminNo.charAt(i), 10);

        var arrayMultipliers = [2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5];
        var len = arrayMultipliers.length;

        var sum = 0;
        for(var i=0; i<len; i++) {
            sum += arrayJuminNo[i] * arrayMultipliers[i];
        }

        var modulus = sum % 11;
        var endNum  = 11 - modulus;

        if(endNum >= 10) endNum -= 10;

        // 체크Bit와 비교
        if(arrayJuminNo[12] == endNum) bRet = true;
        else bRet = false;
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : 주민번호에서 성별을 추출하는 함수.
    Parameter : 주민번호 앞자리, 주민번호 뒷자리
    Return    : m:남, f:여
    Use       : getSexByJuminNo2(strJuminNo1, strJuminNo2);
-------------------------------------------------------------------------*/
function getSexByJuminNo2(strJuminNo1, strJuminNo2){
    return getSexByJuminNo(strJuminNo1+strJuminNo2);
}

/*-------------------------------------------------------------------------
    Notes     : 주민번호에서 성별을 추출하는 함수.
    Parameter : 주민번호
    Return    : m:남, f:여
    Use       : getSexByJuminNo(strSsno);
-------------------------------------------------------------------------*/
function getSexByJuminNo(strSsno){
    var sex = "";
    var len = strSsno.length;

    if(len == 13){
        var iSex   = parseInt(strSsno.substring(6,7), 10);

        if(!isNaN(iSex)){
            if(iSex == 1 || iSex == 3 || iSex == 5 || iSex == 7){
                sex = "m";
            } else if(iSex == 2 || iSex == 4 || iSex == 6 || iSex == 8){
                sex = "f";
            } else {
                sex = "";
            }
        }
    }

    return sex;
}

/*--------------------------------------------------------------------------
    Spec      : 생년월일를 입력받아 현재시간 기준으로 만나이를 리턴한다.
    Parameter : stdDay 기준일자, birthday  생년월일
    Return    : 만나이
--------------------------------------------------------------------------*/
function getAgeOfBirthday(stdDay, birthday){
    var age = -1;

    if(birthday != null && birthday.length == 8){
        var addAge = 0; // 생일에 따른 가감을 위한 변수

        var currentYear  = parseInt(stdDay.substring(0,4), 10);  // 현재년
        var currentMonth = parseInt(stdDay.substring(4,6), 10);  // 현재월
        var currentDay   = parseInt(stdDay.substring(6,8), 10);    // 현재일
        var year         = parseInt(birthday.substring(0,4), 10); // 주민번호-년
        var month        = parseInt(birthday.substring(4,6), 10); // 주민번호-월
        var day          = parseInt(birthday.substring(6), 10);   // 주민번호-일

        // 월일 비교
        if(currentMonth < month){
            addAge = -1;
        } else if(currentMonth == month) {
            if(currentDay < day){
                addAge = -1;
            } else {
                addAge = 0;
            }
        } else {
            addAge = 0;
        }

        age = currentYear - year + addAge;
    }

    return age;
}

/*-------------------------------------------------------------------------
    Notes     : 주민번호에서 생년월일을 추출하는 함수.
    Parameter : 주민번호
    Return    : 생년월일
    Use       : getBirthdayByJuminNo(strJuminNo, delimiter);
-------------------------------------------------------------------------*/
function getBirthdayByJuminNo(strJuminNo, delimiter){
    var birthday = ""
    var len = strJuminNo.length;

    if((typeof(delimiter) == "undefined")) delimiter = "";

    if(len == 13){
        var iBirth = parseFloat(strJuminNo.substring(0,6));
        var iSex   = parseInt(strJuminNo.substring(6,7), 10);

        if(!isNaN(iBirth) && !isNaN(iSex)){
            var strPreYear = "";
            if(iSex == 1 || iSex == 2 || iSex == 5 || iSex == 6){
                strPreYear = "19";
            } else if(iSex == 3 || iSex == 4 || iSex == 7 || iSex == 8){
                strPreYear = "20";
            } else {
                strPreYear = "18";
            }

            birthday = strPreYear + strJuminNo.substring(0,2) + delimiter + strJuminNo.substring(2,4) + delimiter + strJuminNo.substring(4,6)
        }

    }

    return birthday;
}

/*-------------------------------------------------------------------------
    Notes     : 외국인 등록번호를 체크하는 함수.
    Parameter : 주민번호
    Return    : boolean
    Use       : checkForeignerRegiNo(val);
-------------------------------------------------------------------------*/
function checkForeignerRegiNo(strRegiNo) {
    var bRet = false;

    if(strRegiNo.length != 13) bRet = false;
    if(bRet) {
        // 주민번호를 숫자배열로 변환
        var arrayRegiNo = new Array(13);
        for(var i=0; i<arrayJuminNo.length; i++)
            arrayRegiNo[i] = parseInt(strRegiNo.charAt(i), 10);

        var odd = arrayRegiNo[7]*10 + arrayRegiNo[8];
        if(odd%2 != 0) bRet = false;

        if(bRet) {
            if((arrayRegiNo[11] != 6) &&
               (arrayRegiNo[11] != 7) &&
               (arrayRegiNo[11] != 8) &&
               (arrayRegiNo[11] != 9))
                bRet = false;
        } else {
            var arrayMultipliers = [2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5]; // 2,3,4,5,6,7,8,9,2,3,4,5
            var len = arrayMultipliers.length;

            var sum = 0;
            for(var i=0; i<len; i++) {
                sum += arrayJuminNo[i] * arrayMultipliers[i];
            }

            sum = 11- (sum % 11);
            if(sum >= 10){
                sum-=10;
            }

            sum += 2;
            if(sum>=10) sum-=10;

            if( sum == buf[12]) bRet = true;
            else  bRet = false;
        }
    }

    return bRet;
}

//****************************************************************************
// Table 관련 함수
//****************************************************************************
//var ON_MOUSE_OVER_CLASS = "ctrow1w";
//var ON_MOUSE_OUT_CLASS  = "ctrow1";
var ON_MOUSE_OVER_BGCOLOR = "#FFFF99";
var ON_MOUSE_OUT_BGCOLOR  = "#FFFFFF";
/*-------------------------------------------------------------------------
    Notes     : Table에서 Row onMouseOver시 호출하는 함수.
    Parameter : trObj
    Return    : void
-------------------------------------------------------------------------*/
function tableRowOnMouseOver(trObj){
    //trObj.className = ON_MOUSE_OVER_CLASS;
    trObj.style.backgroundColor = ON_MOUSE_OVER_BGCOLOR;
}

/*-------------------------------------------------------------------------
    Notes     : Table에서 Row onMouseOut시 호출하는 함수.
    Parameter : trObj
    Return    : void
-------------------------------------------------------------------------*/
function tableRowOnMouseOut(trObj){
    //trObj.className = ON_MOUSE_OUT_CLASS;
    trObj.style.backgroundColor = ON_MOUSE_OUT_BGCOLOR;
}

/*-------------------------------------------------------------------------
    Notes     : HTML Table Row를 추가한다
    Parameter : [필수] tableID
                [필수] arrayCellText
                [선택] countRowTitle
                [선택] countRowLimited
                [선택] trBgColor
                [선택] arrayCellClassName
                [선택] arrayCellAlign
                [선택] arrayCellWidth
                [선택] insertRowIndex
    Return    :
    use       : addTableRow('tabMember', arrayCellText, 0, 5);
-------------------------------------------------------------------------*/
function addTableRow(tableID, arrayCellText, countRowTitle, countRowLimited, trBgColor, arrayCellClassName, arrayCellAlign, arrayCellWidth, insertRowIndex) {
    // Get a reference to the table
    var tableRef = document.getElementById(tableID);

    // Row Length
    var tableRows = tableRef.rows.length;

    if(typeof(insertRowIndex) == "undefined") insertRowIndex = tableRows;

    // Cell Length
    var cellLen = 0;
    if(arrayCellText == null) {
        alert("추가할 Cell정보가 없습니다.");
        return false;
    } else {
        cellLen = arrayCellText.length;
    }

    // row Limited
    if(typeof(countRowTitle) == "number" && typeof(countRowLimited) == "number") {
        if(countRowLimited <= (tableRows - countRowTitle)) {
            alert(countRowLimited + "개 까지만 추가할 수 있습니다.");
            return false;
        }
    }

    // background colour of the row
    if(typeof(trBgColor) == "undefined") trBgColor = "#FFFFFF";

    // class attribute of an element
    if(typeof(arrayCellClassName) == "undefined" || arrayCellClassName == "") {
        arrayCellClassName = new Array(cellLen);
        for(var i=0; i<cellLen; i++) arrayCellClassName[i] = "";
    }

    // horizontal alignment of data within a table cell
    if(typeof(arrayCellAlign) == "undefined" || arrayCellAlign == "") {
        arrayCellAlign = new Array(cellLen);
        for(var i=0; i<cellLen; i++) arrayCellAlign[i] = "";
    }

    //  width of a table cell
    if(typeof(arrayCellWidth) == "undefined" || arrayCellWidth == "") {
        arrayCellWidth = new Array(cellLen);
        for(var i=0; i<cellLen; i++) arrayCellWidth[i] = "";
    }

    // Insert a row in the table at row index
    var newRow = tableRef.insertRow(insertRowIndex);
    if(newRow != null) {
        var addCell = null;
        for(var i=0; i<cellLen; i++) {
            addCell = newRow.insertCell(i);

            addCell.className = arrayCellClassName[i];
            addCell.align     = arrayCellAlign[i];
            addCell.width     = arrayCellWidth[i];
            addCell.innerHTML = arrayCellText[i];
        }

        if(trBgColor != "") newRow.style.backgroundColor = trBgColor;
        addCell.focus();
    }

    return true;
}

/*-------------------------------------------------------------------------
    Notes     : HTML Table Row를 삭제한다
    Parameter : [필수] tableID
                [필수] object
    Return    :
    use       : onClick="javascript:deleteTableRow('tableID', this)";
-------------------------------------------------------------------------*/
function deleteTableRow(tableID, srcElement) {
    var tableRef = document.getElementById(tableID);
    tableRef.deleteRow(srcElement.parentNode.parentNode.rowIndex);
}

/*-------------------------------------------------------------------------
    Notes     : HTML Table의 마지막 Row를 삭제한다
    Parameter : [필수] tableID
                [선택] countRowLimited
    Return    :
    use       : onClick="javascript:deleteTableRow('tableID', 1)";
-------------------------------------------------------------------------*/
function deleteTableLastRow(tableID, countRowLimited) {
    var tableRef = document.getElementById(tableID);
    var rowIndex = tableRef.rows.length-1;

    if(typeof(countRowLimited) == "undefined") countRowLimited = 0;

    if(countRowLimited <= rowIndex) {
        tableRef.deleteRow(rowIndex);
    }
}

//****************************************************************************
// Popup 관련 함수
//****************************************************************************
/*-------------------------------------------------------------------------
    Notes     : 창의 크기가 고정된 팝업을 중앙에 띄우는 함수.
    Parameter : p_url, p_winName, p_width, p_height
    Return    : window
-------------------------------------------------------------------------*/
function openFixedPopupWindow(p_url, p_winName, p_width, p_height){
    return openPopupWindow(p_url, p_winName, p_width, p_height, "0", "0", "0", "0");
}

/*-------------------------------------------------------------------------
    Notes     : 창의 크기가 고정된 팝업을 중앙에 띄우는 함수.
    Parameter : p_url, p_winName, p_width, p_height
    Return    : window
-------------------------------------------------------------------------*/
function openStatusPopupWindow(p_url, p_winName, p_width, p_height){
    return openPopupWindow(p_url, p_winName, p_width, p_height, "0", "1", "0", "0");
}


/*-------------------------------------------------------------------------
    Notes     : Scroll이 가능하고 크기가 고정된 팝업을 중앙에 띄우는 함수.
    Parameter : p_url, p_winName, p_width, p_height
    Return    : window
-------------------------------------------------------------------------*/
function openScrollbarsPopupWindow(p_url, p_winName, p_width, p_height){
    return openPopupWindow(p_url, p_winName, p_width, p_height, "0", "1", "1", "0");
}

/*-------------------------------------------------------------------------
    Notes     : 팝업을 중앙에 띄우는 함수.
    Parameter : p_url, p_winName, p_width, p_height, p_menubar, p_status, p_scrollbars, p_resizable
    Return    : window
-------------------------------------------------------------------------*/
function openPopupWindow(p_url, p_winName, p_width, p_height, p_menubar, p_status, p_scrollbars, p_resizable){

    var left = (screen.width - p_width) / 2;
    var top  = (screen.height - p_height) / 2;

    var opt = "";
        opt += "toolbar=0,";
        opt += "location=0,";
        opt += "directories=0,";
        opt += "status="     + p_status + ",";
        opt += "menubar="    + p_menubar + ",";
        opt += "scrollbars=" + p_scrollbars + ",";
        opt += "resizable="  + p_resizable + ",";
        opt += "top="        + top + ",";
        opt += "left="       + left + ",";
        opt += "width="      + p_width + ",";
        opt += "height="     + p_height;

    var popup = window.open(p_url, p_winName, opt);
    /*
    popup.blur();

    if(parseInt(navigator.appVersion, 10) >= 4){
        popup.window.focus()
    } else {
        popup.focus()
    }
    */

    return popup;
}

//****************************************************************************
// 업무관련 함수.
//****************************************************************************
/*-------------------------------------------------------------------------
    Notes     : Date Select Object에 값을 Text Object에 설정하는 함수.
                인자수에따라 년월, 년월일 설정가능.
    Parameter : Text Object, Year Object, Month Object, Date Object
    Return    : void
    Use       : setDateObject(form.date, form.slcYear, form.slcMonth, form.slcDate)
-------------------------------------------------------------------------*/
function setDateObject(txtObj, yearObj, monthObj, dateObj){
    var ymd = "";

    if(txtObj != null) {
        var args = setDateObject.arguments;
        var len  = args.length;
        switch(len) {
            case 3:
                ymd = getHtmlObjectValue(yearObj) + getHtmlObjectValue(monthObj);
                break;
            case 4:
                ymd = getHtmlObjectValue(yearObj) + getHtmlObjectValue(monthObj) + getHtmlObjectValue(dateObj);
                break;
            default:
                break;
        }
        setHtmlObjectValue(txtObj, ymd);
    }
}

/*-------------------------------------------------------------------------
    Notes     : 색상을 찾는 팝업을 호출함.
    Parameter : Form Object Name, Object Name, Month Object, Date Object
    Return    : void
    Use       : onClick="javascript:openSelectColorPopup('mainForm', '항목배경색상내용');"
-------------------------------------------------------------------------*/
function openSelectColorPopup(strFormName, strObjName){
    var strColorVal = eval("document." + strFormName + "." + strObjName + ".value");

    var url  = "/quics?USER_TYPE=10&page=B007980&cc=b000001:b004354";
        url += "&formName="  + strFormName;
        url += "&objName="   + strObjName;
        url += "&colorVal="  + strColorVal;

    var winSelectColor = openFixedPopupWindow(url, "winColorSelection", 350, 240);
}

/*-------------------------------------------------------------------------
    Notes     : 업로드된 파일을 다운로드 받는 함수.
                >>> 확인사항
                    1. 해당 컴포넌트의 보안플래그가 SOFO로 설정되어 있어야 합니다.
                    2. 해당페이지의 TP에 " [b002907] [ XecureWebFileAccessObject ]" 추가되어 있어야 합니다.
    Parameter : [필수] strRealFileFullName - 파일전체경로
                [선택] strDisplayFileName - 화면에 보여지는 파일명
                [선택] nOption - 열기 및 저장 옵션 보이기
    Return    : boolean
    Use       : onClick="javascript:goFileDownload('파일경로명', '파일명');"
-------------------------------------------------------------------------*/
function goFileDownload(strRealFileFullName, strDisplayFileName, nOption){
    var bRet = true;
    if(strRealFileFullName != null && strRealFileFullName != "") {
        var strURL = "/quics?USER_TYPE=10&asfilecode=11268&";
        var strParam = "fileFullName=" + strRealFileFullName;

        var args = goFileDownload.arguments;
        var len  = args.length;

        if(len == 1) {
            strDisplayFileName = getFileName(strRealFileFullName);
            nOption = 1;
        } else if(len == 2){
            nOption = 1;
        }

        FileDownloadURL(strDisplayFileName, strURL, strParam, nOption);
    } else {
        alert("파일정보가 올바르지 않습니다.");
        bRet = false;
    }

    return bRet;
}

/*-------------------------------------------------------------------------
    Notes     : 채용 비밀번호 찾기 클릭시 지원자의 비밀번호를 이메일 발송.
    Parameter : Form Object Name, Object Name, Month Object, Date Object
    Return    : void
    Use       : onClick="javascript:openSelectColorPopup('mainForm', '항목배경색상내용');"
-------------------------------------------------------------------------*/
function openSendMailApplicantPwdRegFrm(formObj){
    var winName = "winSendMailApplicantPwdRegFrm";
    //var popWin = openFixedPopupWindow('', winName, 720, 350);
    var popWin = openFixedPopupWindow('', winName, 660, 410);
    formObj.action = "/quics?USER_TYPE=10&page=C019085&cc=b028364:b032306&QSL=F";
    formObj.target = winName;
    formObj.submit();
}

/*-------------------------------------------------------------------------
    Notes     : 입력값 특수문자 제거
    Parameter : Object
    Return    : char
    Use       : onblur="javascript:this.value = inputKeywordCheck(this);"
-------------------------------------------------------------------------*/
function inputKeywordCheck(obj){
	var str = obj.value;
	var s = [ "[" , "'" , "\\" , '"' , "#" , ";" , "=" , "+" , "]" ,"<" ,">" ] ;
	for( var i=0 ; i<s.length ; i++ ){
		str =  str.split(s[i]).join("")
	}
	return str;
}


 function updateChar(length_limit, strId)  
		  {
			 var strObj = $("#"+strId);
		     var length = calculate_msglen(strObj.val());  
		     textlimit.innerText = length;  
		     if (length > length_limit) {  
		         alert("최대 " + length_limit + "byte이므로 초과된 글자수는 자동으로 삭제됩니다.");  
		         strObj.val(strObj.val().replace(/\r\n$/, ""));
		         strObj.val(assert_msglen(strObj.val(), length_limit));
		     }  
		  }  

		     
		  function calculate_msglen(message)  
		  {  
		     var nbytes = 0;  

		     for (i=0; i<message.length; i++) {  
		         var ch = message.charAt(i);  

		         if(escape(ch).length > 4) {  

		             nbytes += 2;  

		         } else if (ch == '\n') {  

		             if (message.charAt(i-1) != '\r') {  

		                 nbytes += 1;  

		             }  

		         } else if (ch == '<' || ch == '>') {  

		             nbytes += 4;  

		         } else {  

		             nbytes += 1;  

		         }  

		     }  
		     return nbytes;  
		  }  
    

		  function assert_msglen(message, maximum)  
		  {  

		     var inc = 0;  
		     var nbytes = 0;  
		     var msg = "";  
		     var msglen = message.length;  

		     for (i=0; i<msglen; i++) {  

		    	 var ch = message.charAt(i);  
		         
		    	 if (escape(ch).length > 4) {  
		             inc = 2;  
		         } else if (ch == '\n') {  

		             if (message.charAt(i-1) != '\r') {  
		                 inc = 1;  
		             }  

		         } else if (ch == '<' || ch == '>') {  
		             inc = 4;  
		         } else {  
		             inc = 1;  
		         }  

		         if ((nbytes + inc) > maximum) {  
		             break;  
		         }  

		         nbytes += inc;  
		         msg += ch;  
		     }  

		     textlimit.innerText = nbytes;  
		     return msg;  
		  }  



function nextMax(obj){
	var $this = $(obj);
	var currentLength = $this.val().length;
	var maximumLength = $this.attr('maxlength');
	if(currentLength == maximumLength){
	  $this.next().focus();
	}
}


/**
Fire fox에서 한글에 대한 키이벤트를 정상적으로 해결하기 위함 함수.
사용법 : new Observe(document.getElementById('keyword'));
keyword 라는 아이디를 가진 객체에 한글 키이벤트가 발생시 firefox에서 
키 이벤트가 안올라는 것을 잡아서 강제적으로 키이벤트를 발생시킵니다.
*/
var Observe = function(oEl) { 
		this._o = oEl;
		this._value = oEl.value;
		this._bindEvents();
	};
 
	Observe.prototype._bindEvents = function() {
		var self = this;
		var bind = function(oEl, sEvent, pHandler) {
			if (oEl.attachEvent) 
				oEl.attachEvent('on' + sEvent, pHandler);
			else 
				oEl.addEventListener(sEvent, pHandler, false);
		};
 
		bind(this._o, 'focus', function() {
			if (self._timer) 
				clearInterval(self._timer);
			self._timer = setInterval(function() {
				if (self._value != self._o.value) {
					self._value = self._o.value;
					self._fireEvent();
				}
			}, 50);
		});
      
		bind(this._o, 'blur', function() {
			if (self._timer) clearInterval(self._timer);
			self._timer = null;
		});
    };
     
	Observe.prototype._fireEvent = function() {
		if (document.createEvent) {
			var e;
			if (window.KeyEvent) {
				e = document.createEvent('KeyEvents');
				e.initKeyEvent('keyup', true, true, window, false, false, false, false, 65, 0);
	        } else {
			  e = document.createEvent('UIEvents');
			  e.initUIEvent('keyup', true, true, window, 1);
			  e.keyCode = 65;
			}
			this._o.dispatchEvent(e);
		} else {
			var e = document.createEventObject();
			e.keyCode = 65;
			this._o.fireEvent('onkeyup', e);        
		}
    };

/**
* 입력받을 수 있는 값을 필터링한다.
* ex : <input type="text" ..... onkeypress="filterKey('[0-9]')"> ; 숫자만 키입력이 가능한 text filed
* ex : <input type="text" ..... onkeypress="filterKey('[0-9a-zA-Z]')"> ; 영문,숫자만 키입력이 가능한 text filed
* @param filter : 필터링할 정규표현식 ex) '[0-9]':0~9의 값만 허용, '[a-zA-Z]':알파벳만 허용
*/
var filterInputData = function(filter, e) {
        if (filter) {
               if(e.keyCode) {
                       var sKey = String.fromCharCode(e.keyCode);
                       var re = new RegExp(filter);
                       if (!re.test(sKey)) {
                               e.returnValue = false;
                       }
               }else if(e.which) {
                       var sKey = String.fromCharCode(e.which);
                       var re = new RegExp(filter);
                       if (!re.test(sKey)) {
                               e.preventDefault();
                       }
               }
               
               return false;
        }
};

/**
* 서식물리파일명으로 파일 다운받기
* strPageCode			= 현재 페이지
* strBaseComponentCode	= 베이스 컴포넌트(빈컴포넌트 걸어주면 됩니다.)
* param					= formDocNo=파일일련번호
*/
function OpenStreamDownload_omoney(strPageCode,strBaseComponentCode,param) {
	var fileNo = "";

	if(param.indexOf("formDocNo") > -1){
		if(param.indexOf("&") > -1)		param = param.split("&")[0];

		fileNo = param.substring(param.indexOf("=")+1);
	}else{
		alert("파일이 존재하지 않습니다.");
		return;
	}

	//S - download Form 없을 시 생성
	var downLoadFrm = "";
	if(typeof(document.downLoadForm) == "undefined" && document.downLoadForm == null){
		downLoadFrm = document.createElement("form");
		downLoadFrm.setAttribute("name",	"downLoadForm");
		downLoadFrm.setAttribute("method", "");
		downLoadFrm.setAttribute("action", "");
		document.body.appendChild(downLoadFrm);

		var tmpInput = document.createElement("input");
		tmpInput.setAttribute("name",	"서식일련번호");
		tmpInput.setAttribute("type",	"hidden");
		tmpInput.setAttribute("value",	fileNo);
		downLoadFrm.appendChild(tmpInput);
	}else{
		downLoadFrm = document.downLoadForm;
		document.downLoadForm.서식일련번호.value = fileNo;
	}
	//E - download Form 없을 시 생성

	downLoadFrm.action = "/quics?page="+strPageCode+"&cc="+strBaseComponentCode+":b040303";
	doAjaxCC(downLoadFrm);
}


/*-------------------------------------------------------------------------
    Spec      : Text Obj Length Check
    Parameter : obj, maxLen
    Return    : 
    Date       : 2012.06.26
-------------------------------------------------------------------------*/
function checkStrByte(message, maxLength) {

	var strObj = $("#"+message.id);
	var nbytes = calculate_msglen2(strObj.val());

	//전체 길이를 초과하면
	if(nbytes > maxLength) {
		alert("최대 " + maxLength + "byte이므로 초과된 글자수는 자동으로 삭제됩니다.");
		strObj.val(strObj.val().replace(/\r\n$/, ""));
		strObj.val(assert_msglen2(strObj.val(), maxLength));
	}
	
	message.focus();
}

function calculate_msglen2(message)  
{  
   var nbytes = 0;  

   for (i=0; i<message.length; i++) {  
       var ch = message.charAt(i);  

       if(escape(ch).length > 4) {  
           nbytes += 2;  

       } else if (ch == '\n') {  

           if (message.charAt(i-1) != '\r') {  
               nbytes += 1;  
           }  

       } else if (ch == '<' || ch == '>') {  
          nbytes += 4;  

       } else if (ch == '&') {  
          nbytes += 5;  

       } else if (ch == '\'') {  
          nbytes += 6;  

       } else {  
           nbytes += 1;  

       }  

   }  
   return nbytes;  
}


function assert_msglen2(message, maximum)  
{  

 var inc = 0;  
 var nbytes = 0;  
 var msg = "";  
 var msglen = message.length;  

 for (i=0; i<msglen; i++) {  

	 var ch = message.charAt(i);  

	 if (escape(ch).length > 4) {  
	    inc = 2;  
	 } else if (ch == '\n') {  

	    if (message.charAt(i-1) != '\r') {  
	        inc = 1;  
	    }  

	 } else if (ch == '<' || ch == '>') {  
	    inc = 4;  

	 } else if (ch == '&') {  
      inc = 5;  

   } else if (ch == '\'') {  
      inc = 6;  

   } else {  
	    inc = 1;  
	 }  

	 if ((nbytes + inc) > maximum) {  
	    break;  
	 }  

	 nbytes += inc;  
	 msg += ch;
	 
 }  
 
 return msg;
}

