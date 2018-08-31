<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<link rel="stylesheet" href="<c:url value="/style/autocomplete.css"/>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
   <title>koscom</title>
   <%@ include file="/WEB-INF/include/headComm.jsp"%>
   <%--<script src="<c:url value="/script/temp.js"/>?<%=random%>"></script>--%>
  
    <script type="text/javascript">
        //검색어 자동완성기능
        $(function(){
            $( "#apartment_nm" ).autocomplete({
                source : function( request, response ) {
                    $.ajax({
                        type: 'post',
                        url: '<c:url value="/kbrealestate/listSrchApartmentInfo.json"/>',
                        dataType: "json",
                        data: {BUILDING_TYPE : frmScrap.BUILDING_TYPE.value, APARTMENT : frmScrap.apartment_nm.value, REGION1_CODE : frmScrap.REGION1_CODE.value, REGION2_CODE : frmScrap.REGION2_CODE.value, REGION3_CODE : frmScrap.REGION3_CODE.value },
                        success: function(data) {
                            //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
                            response(
                                $.map(data.data , function(item) {
                                    return {
                                        label: item.auto_com_txt,
                                        code : item.APARTMENT,
                                        value: item.APARTMENT_NAME/*,
                                    APARTMENT_value: item.APARTMENT_NAME*/
                                    }
                                })
                            );
                        }
                    });
                },
                //조회를 위한 최소글자수
                minLength: 1,
                select: function( event, ui ) {
                    goKbMarketPriceList();
                }

            });

            ChangeSelect(document.frmScrap);
			
			//레이어창
			popInfo();
        });
        $(document).ready(function() {
            if(userAgent == "Android") {
                window.Android.setEndApp('Y');
            }
        });
        var aptList = new Object();
        /**
         * 아파트목록 가져오기
         */
        function getAptList() {
            if(userAgent == "Android") {
                window.Android.loding('Y');
            }
            else if(userAgent == "iOS") {
				Jockey.send("showLoading");
			}
            $.ajax({
                type: 'post',
                url: '<c:url value="/kbrealestate/listSrchApartmentInfo.json"/>',
                dataType: "json",
                data: {BUILDING_TYPE : frmScrap.BUILDING_TYPE.value
                    , APARTMENT : frmScrap.apartment_nm.value
                    , REGION1_CODE : frmScrap.REGION1_CODE.value
                    , REGION2_CODE : frmScrap.REGION2_CODE.value
                    , REGION3_CODE : frmScrap.REGION3_CODE.value },
                success: function(data) {
                    if(userAgent == "Android") {
                        window.Android.loding('N');
                    }
                    else if(userAgent == "iOS") {
    					Jockey.send("stopLoading");
    				}
                    //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
                    var frm = document.frmScrap;
                    frm.apartment_nm.options.length=1;
                    var list = data.data;
                    var len = list.length;
                    console.log("len="+len);
                    if(len < 1) {
                        toastMsg("해당지역에는 아파트가 없습니다.");
                    } else {
                        var objOne;
                        var boxOption;
                        var target = frm.apartment_nm;
                        var APARTMENT;
                        var APARTMENT_NAME;
                        var REGION3_CODE;
                        var aptObj;
                        $('#apartment_nm').prop("disabled",false);
                        for(var i=0;i<len;i++) {
                            objOne = list[i];
                            APARTMENT      = objOne.APARTMENT     ;
                            APARTMENT_NAME = objOne.APARTMENT_NAME;
                            REGION3_CODE   = objOne.REGION3_CODE  ;
                            aptList[APARTMENT]=objOne;
                            $('#apartment_nm').append("<option value='"+APARTMENT+"'>"+APARTMENT_NAME+"</option>");
                        }
                        $('#apartment_nm').selectpicker('refresh');
                    }
                },
                error : function (e) {
                    errMsg(e);
                }
            });
        }
        /**
         * 시세및 한도 대출 부분을 clear
         */
        function clearContents() {
            document.frmScrap.FLOOR_PLAN_AREA.options.length=0;
            $('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
            $('#FLOOR_PLAN_AREA').selectpicker('refresh');
            document.frmScrap.apartment_nm.options.length=0
            $('#apartment_nm').append("<option value=''>아파트명</option>");
            $('#apartment_nm').selectpicker('refresh');

            sale_lowest_average.innerHTML = "";
            sale_general_average.innerHTML = "";
            sale_highest_average.innerHTML = "";
            limit_lowest.innerHTML = "";
            limit_general.innerHTML = "";
            limit_highest.innerHTML = "";
            limit_max.innerHTML = "";
        }
        /**
         * 읍면동 코드 가져오는 로직
         * @param APARTMENT
         */
        function getREGION3_CODE(APARTMENT){
            var REGION3_CODE = aptList[APARTMENT].REGION3_CODE;
            console.log("getREGION3_CODE:"+REGION3_CODE);
            return REGION3_CODE;
        }
        function ChangeSelectBox(frm, obj) {
            var url;
            var data;

            var objId = $(obj).attr('id');
            var objVal = $("#"+objId).val();
            var target;
            clearContents();
            if(objId == "BUILDING_TYPE"){ //	빌딩 종류
                target= frm.REGION1_CODE;
                url = "<c:url value='/kbrealestate/listAddrRegion1.json'/>";
                data = {"BUILDING_TYPE":1};
                frm.REGION1_CODE.options.length=0;
                $('#REGION1_CODE').append("<option value=''>시/도</option>");
                $('#REGION1_CODE').selectpicker('refresh');

                frm.REGION2_CODE.options.length=0
                $('#REGION2_CODE').append("<option value=''>시/군/구</option>");
                $('#REGION2_CODE').selectpicker('refresh');

                frm.REGION3_CODE.options.length=0
                $('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
                $('#REGION3_CODE').selectpicker('refresh');


                frm.apartment_nm.options.length=0
                $('#apartment_nm').append("<option value=''>아파트명</option>");
                $('#apartment_nm').selectpicker('refresh');

                // $('#apartment_nm').val('');
            }
            else if(objId == "REGION1_CODE")
            {
                target= frm.REGION2_CODE;
                url = "<c:url value='/kbrealestate/listAddrRegion2.json'/>";
                data = {"REGION1_CODE":objVal};
                frm.REGION2_CODE.options.length=0
                $('#REGION2_CODE').append("<option value=''>시/군/구</option>");
                $('#REGION2_CODE').selectpicker('refresh');

                frm.REGION3_CODE.options.length=0
                $('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
                $('#REGION3_CODE').selectpicker('refresh');

                frm.apartment_nm.options.length=0
                $('#apartment_nm').append("<option value=''>아파트명</option>");
                $('#apartment_nm').selectpicker('refresh');

                frm.FLOOR_PLAN_AREA.options.length=0
                $('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
                $('#FLOOR_PLAN_AREA').selectpicker('refresh');
            }
            else if(objId == "REGION2_CODE")
            {
                target= frm.REGION3_CODE;
                url = "<c:url value='/kbrealestate/listAddrRegion3.json'/>";
                data = {"REGION2_CODE":objVal};

                frm.REGION3_CODE.options.length=0
                $('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
                $('#REGION3_CODE').selectpicker('refresh');

                frm.apartment_nm.options.length=0
                $('#apartment_nm').append("<option value=''>아파트명</option>");
                $('#apartment_nm').selectpicker('refresh');
            }

            if(data == null) return false;

            var boxOption;
            target.options.length=0;
            if(userAgent == "Android") {
                window.Android.loding('Y');
            }
            else if(userAgent == "iOS") {
				Jockey.send("showLoading");
			}
            $.ajax({
                url : url,
                data : data,
                contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                type : "POST",
                dataType : "json",
                success : function(data) {
                    if(userAgent == "Android") {
                        window.Android.loding('N');
                    }
                    else if(userAgent == "iOS") {
    					Jockey.send("stopLoading");
    				}

                    if(objId == "BUILDING_TYPE"){ //	빌딩 종류
                        boxOption = document.createElement("OPTION");
                        boxOption.value = '';
                        boxOption.text = '시/도';
                        target.options.add(boxOption);
                        $.each(data.List, function (index, item) {
                            boxOption = document.createElement("OPTION");
                            boxOption.value = item.code_value;
                            boxOption.text = item.nm_code;
                            target.options.add(boxOption);
                        });
                        $('#REGION1_CODE').selectpicker('refresh');
                        target.options.length = data.List.length + 1;

                    }
                    else if(objId == "REGION1_CODE")// 시/도
                    {
                        boxOption = document.createElement("OPTION");
                        boxOption.value = '';
                        boxOption.text = '시/군/구';
                        target.options.add(boxOption);
                        $.each(data.List, function (index, item) {
                            boxOption = document.createElement("OPTION");
                            boxOption.value = item.code_value;
                            boxOption.text = item.nm_code;
                            target.options.add(boxOption);
                        });
                        $('#REGION2_CODE').selectpicker('refresh');
                        target.options.length = data.List.length + 1;

                    }
                    else if(objId == "REGION2_CODE")// 시/군/구
                    {
                        boxOption = document.createElement("OPTION");
                        boxOption.value = '';
                        boxOption.text = '읍/면/동';
                        target.options.add(boxOption);
                        $.each(data.List, function (index, item) {
                            boxOption = document.createElement("OPTION");
                            boxOption.value = item.code_value;
                            boxOption.text = item.nm_code;
                            target.options.add(boxOption);
                        });
                        $('#REGION3_CODE').selectpicker('refresh');
                        target.options.length = data.List.length + 1;
                        getAptList();
                    }
                },
                error : function (e) {
                    errMsg(e);
                }
            });
        }

        function ChangeSelectBox2(frm, obj){
            clearContents();
            getAptList();
        }

        function ChangeSelect(frm){
            var url;
            var data;

            var target;

            target= frm.REGION1_CODE;
            url = "<c:url value='/kbrealestate/listAddrRegion1.json'/>";
            data = {"BUILDING_TYPE":1};
            frm.REGION1_CODE.options.length=0;
            $('#REGION1_CODE').append("<option value=''>시/도</option>");
            $('#REGION1_CODE').selectpicker('refresh');

            frm.REGION2_CODE.options.length=0
            $('#REGION2_CODE').append("<option value=''>시/군/구</option>");
            $('#REGION2_CODE').selectpicker('refresh');

            frm.REGION3_CODE.options.length=0
            $('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
            $('#REGION3_CODE').selectpicker('refresh');

            frm.FLOOR_PLAN_AREA.options.length=0
            $('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
            $('#FLOOR_PLAN_AREA').selectpicker('refresh');

            $('#APARTMENT').val('');


            if(data == null) return false;

            var boxOption;
            target.options.length=0;
            if(userAgent == "Android") {
                window.Android.loding('Y');
            }
            else if(userAgent == "iOS") {
				Jockey.send("showLoading");
			}
            $.ajax({
                url : url,
                async : false,
                data : data,
                contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                type : "POST",
                dataType : "json",
                success : function(data) {
                    if(userAgent == "Android") {
                        window.Android.loding('N');
                    }
                    else if(userAgent == "iOS") {
    					Jockey.send("stopLoading");
    				}
                    boxOption = document.createElement("OPTION");
                    boxOption.value = '';
                    boxOption.text = '시/도';
                    target.options.add(boxOption);
                    $.each(data.List, function (index, item) {
                        boxOption = document.createElement("OPTION");
                        boxOption.value = item.code_value;
                        boxOption.text = item.nm_code;
                        target.options.add(boxOption);
                    });
                    $('#REGION1_CODE').selectpicker('refresh');
                    target.options.length = data.List.length + 1;
                },
                error : function (e) {
                    errMsg(e);
                }

            });
        }
        var arrKbMarketPricePriceList = new Array();
        function goKbMarketPriceList() {
            var APARTMENT = $("#apartment_nm").val();
            var REGION3_CODE = getREGION3_CODE(APARTMENT);
            console.log('APARTMENT='+APARTMENT);
            $('#REGION3_CODE').val(REGION3_CODE);
            $('#APARTMENT').val('');
            $("#APARTMENT").val(APARTMENT);
// 	if(frmScrap.API_ID.value != "9921"){
// 		console.log("API_ID를 9921:시세검색으로 선택해 주세요");
// 		return;
// 	}
            var target;
            target= document.frmScrap.FLOOR_PLAN_AREA;
            document.frmScrap.FLOOR_PLAN_AREA.options.length=0;
            $('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
            $('#FLOOR_PLAN_AREA').selectpicker('refresh');
            if(!frmScrap.validateForm()) return;

            // var data = frmScrap.ajaxSubmit();
            var data = $("#frmScrap").serialize();
            if(data == null) return false;

            var boxOption;
            target.options.length=0;
            startLoading();
            $.ajax({
                url : "<c:url value='/kbrealestate/scrapKbMarketPriceList.crz'/>",
                data : data,
                contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                type : "POST",
                async : true,
                success : function (data) {
                    stopLoading();
                    boxOption = document.createElement("OPTION");
                    boxOption.value = '';
                    boxOption.text = '공급면적';
                    target.options.add(boxOption);
                    // console.log("data.KbMarketPricePyeongList="+data.KbMarketPricePyeongList);
                    // console.log("data.KbMarketPricePyeongList.length="+data.KbMarketPricePyeongList.length);
                    // console.log("data.KbMarketPricePriceList="+data.KbMarketPricePriceList);
                    // console.log("data.KbMarketPricePriceList.length="+data.KbMarketPricePriceList.length);
                    // if(data.KbMarketPricePyeongList == undefined || data.KbMarketPricePyeongList.length == 0) {
                    //    toastMsg('해당 자료가 존재하지 않습니다.');
                    // }
                    $.each(data.KbMarketPricePyeongList, function (index, item) {
                        // console.log("KbMarketPricePyeongList:" + index);
                        // var value = item.floor_PLAN_AREA_SUPPLY +"/"+item.floor_PLAN_AREA_DEDICATED;
                        // var text = item.floor_PLAN_AREA_SUPPLY +"/"+item.floor_PLAN_AREA_DEDICATED;
                        // console.log("KbMarketPricePyeongList:value =" + value+"\n"+"text="+text);
                        boxOption = document.createElement("OPTION");
                        boxOption.value = item.floor_PLAN_AREA_SUPPLY +"/"+item.floor_PLAN_AREA_DEDICATED;
                        boxOption.text = item.floor_PLAN_AREA_SUPPLY +"("+item.floor_PLAN_AREA_DEDICATED+")";
                        target.options.add(boxOption);
                    });
                    arrKbMarketPricePriceList = new Array();
                    $.each(data.KbMarketPricePriceList, function (index, item) {
                        // console.log("KbMarketPricePriceList:" + index);
                        // console.log("KbMarketPricePriceList:" + item);
                        arrKbMarketPricePriceList[index] = [item.area_SIZE,item.sale_LOWEST_AVERAGE,item.sale_GENERAL_AVERAGE,item.sale_HIGHEST_AVERAGE];
                    });
                    $('#FLOOR_PLAN_AREA').selectpicker('refresh');
                    target.options.length = data.List.length + 1;
                },
                error : function (e) {
                    errMsg(e);
                }
            });
        }
        function chgSqure(){
            var frm = document.frmScrap;
            /**
             * 선택한 아파트 정보에 따른 표시 변경
             */
            if(frm.FLOOR_PLAN_AREA.options[frm.FLOOR_PLAN_AREA.selectedIndex].value == ""){
                var message = (selGubun == 1)? "아파트": "오피스텔";
                message = message + "정보를 입력해 주세요";
                toastMsg(message);
                return;
            }
            //ppt20171001버전
            var speculateArray = ["서울특별시강남구","서울특별시서초구","서울특별시송파구","서울특별시강동구","서울특별시용산구","서울특별시성동구","서울특별시노원구","서울특별시마포구","서울특별시양천구","서울특별시영등포구","서울특별시강서구","세종특별자치시세종특별자치시"];
            var overheatArray = ["서울특별시광진구","서울특별시동작구","서울특별시구로구","서울특별시금천구","서울특별시관악구","서울특별시서대문구","서울특별시은평구","서울특별시종로구","서울특별시동대문구","서울특별시중랑구","서울특별시성북구","서울특별시강북구","서울특별시도봉구","서울특별시중구","경기도과천시"];
            var modifyArray = ["경기도성남시","경기도하남시","경기도고양시","경기도광명시","경기도남양주시","부산광역시해운대구","부산광역시연제구","부산광역시수영구","부산광역시동래구","부산광역시부산진구","부산광역시기장군","부산광역시남구"];
            //"경기도가평군","경기도광주시","경기도구리시","경기도군포시","경기도김포시","경기도동두천시","경기도부천시","경기도수원시","경기도시흥시","경기도안산시","경기도안성시","경기도안양시","경기도양주시","경기도양평군","경기도여주시","경기도연천군","경기도오산시","경기도용인시","경기도의왕시","경기도의정부시","경기도이천시","경기도파주시","경기도평택시","경기도포천시","경기도화성시"
            //"부산광역시북구","부산광역시중구","부산광역시금정구","부산광역시동구","부산광역시사하구","부산광역시영도구","부산광역시서구","부산광역시강서구","부산광역시사상구","
            var region = "0";	//0:그외    1:투기지역 , 2:투기과열지구, 3:조정대상지역
            for(var i=0; i < speculateArray.length; i++){
                if(speculateArray[i] == frm.REGION1_CODE.options[frm.REGION1_CODE.selectedIndex].text + frm.REGION2_CODE.options[frm.REGION2_CODE.selectedIndex].text){
                    //console.log("투기지역:" + speculateArray[i] + "입니다.");
                    region = "1";
                }
            }
            for(var i=0; i < overheatArray.length; i++){
                if(overheatArray[i] == frm.REGION1_CODE.options[frm.REGION1_CODE.selectedIndex].text + frm.REGION2_CODE.options[frm.REGION2_CODE.selectedIndex].text){
                    //console.log("투기과열지구:" + overheatArray[i] + "입니다.");
                    region = "2";
                }
            }
            for(var i=0; i < modifyArray.length; i++){
                if(modifyArray[i] == frm.REGION1_CODE.options[frm.REGION1_CODE.selectedIndex].text + frm.REGION2_CODE.options[frm.REGION2_CODE.selectedIndex].text){
                    //console.log("조정대상지역:" + modifyArray[i] + "입니다.");
                    region = "3";
                }
            }
            var preloan = frm.hid_preloan.value;
            var ordinary = frm.hid_ordinary.value;
            var preloan_speculate = "";	//투기지역주택에 대한 대출1건이상 있는 가구
            sale_lowest_average.innerHTML = "-";
            sale_general_average.innerHTML = "-";
            sale_highest_average.innerHTML = "-";
            limit_lowest.innerHTML = "-";
            limit_general.innerHTML = "-";
            limit_highest.innerHTML = "-";
            limit_max.innerHTML = "-";
            if(arrKbMarketPricePriceList.length <= 0) {
                console.log("선택하신 담보물의 현재 시세정보를 확인할 수 없습니다.");
                toastMsg("선택하신 담보물의 현재 시세정보를 확인할 수 없습니다.");
                return;
            }
            var arrFLOOR_PLAN_AREA0 = splitPyeong(frm.FLOOR_PLAN_AREA.options[frm.FLOOR_PLAN_AREA.selectedIndex].value, 0);
            // console.log("arrFLOOR_PLAN_AREA0="+arrFLOOR_PLAN_AREA0);
            var arrFLOOR_PLAN_AREA1 = splitPyeong(frm.FLOOR_PLAN_AREA.options[frm.FLOOR_PLAN_AREA.selectedIndex].value, 1);
            // console.log("arrFLOOR_PLAN_AREA1="+arrFLOOR_PLAN_AREA1);
            maxLtv = 0;
            for(var i=0; i < arrKbMarketPricePriceList.length; i++){
                var arrMyArray0 = splitPyeong(arrKbMarketPricePriceList[i][0], 0);
                var arrMyArray1 = splitPyeong(arrKbMarketPricePriceList[i][0], 1);
                // console.log("arrMyArray0="+arrMyArray0+",arrMyArray1="+arrMyArray1);
                if(arrFLOOR_PLAN_AREA0 == arrMyArray0 || arrFLOOR_PLAN_AREA1 == arrMyArray1){
                    sale_lowest_average.innerHTML = viewAmt(arrKbMarketPricePriceList[i][1]);
                    sale_general_average.innerHTML = viewAmt(arrKbMarketPricePriceList[i][2]);
                    sale_highest_average.innerHTML = viewAmt(arrKbMarketPricePriceList[i][3]);
                    limit_lowest.innerText  = setLimit(region, arrKbMarketPricePriceList[i][1], preloan, ordinary, preloan_speculate);
                    limit_general.innerText = setLimit(region, arrKbMarketPricePriceList[i][2], preloan, ordinary, preloan_speculate);
                    limit_highest.innerText = setLimit(region, arrKbMarketPricePriceList[i][3], preloan, ordinary, preloan_speculate);
                }
            }
            if(arrKbMarketPricePriceList.length > 0) {
                limit_max.innerHTML = "최대한도 : 시세의 <span class=\"txt-point\">"+maxLtv+"%</span><span class=\"txt-point\"> (LTV"+maxLtv+"%)</span>";
            }

        }
        function splitPyeong(str, idx){
            try {
                if(idx == 0){
                    return str.split("/")[0].split(".")[0];
                }else if(idx == 1){
                    return str.split("/")[1].split(".")[0];
                }
            } catch (e) {
                return "";
            }
        }

        /**
         *
         * @param region 지역
         * @param amt  금액
         * @param preloan 담보대출여부
         * @param ordinary 서비
         * @param preloan_speculate
         * @returns {string}
         */
        function setLimit(region, amt, preloan, ordinary, preloan_speculate){	//var region = "0";	//0:그외    1:투기지역 , 2:투기과열지구, 3:조정대상지역
            console.log("setLimit:region="+region+",amt="+amt);
            var ltv = 0;
            try {
                amt = parseInt(amt);
            } catch (e) {
                return "";
            }
            var result = "";
            if(region == "0") {//0:그외
                if(preloan == "Y"){
                    console.log("그외 가족중기주택담보Y LTV 60%");
                    ltv = 60;
                }else if(preloan == "N"){
                    console.log("그외 가족중기주택담보Y LTV 70%");
                    ltv = 70;
                }
            }
            /**
             * 1:투기지역
             */
            else if(region == "1") {
                /**
                 * 담보대출이 있는 경우
                 */
                if(preloan == "Y"){//투기지역=투기과열지구 가족중기주택담보Y ==> LTV 0%
                    console.log("투기지역=투기과열지구 가족중기주택담보Y LTV 0%");
                    ltv = 0;
                }else if(preloan == "N"){
                    if(ordinary == "Y"){//서민Y
                        if(amt <= 600000000){//6억이하 ==> LTV 50%
                            console.log("투기지역=투기과열지구 가족중기주택담보N 6억이하 LTV 50%");
                            ltv = 50;
                        }else{//6억초과 ==> LTV 40%
                            console.log("투기지역=투기과열지구 가족중기주택담보N 6억초과 LTV 40%");
                            ltv = 40;
                        }
                    }else if(ordinary == "N"){//서민N LTV 40%
                        console.log("투기지역=투기과열지구 가족중기주택담보N 서민N LTV 40%");
                        ltv = 40;
                    }
                }
                if(preloan_speculate == "Y"){

                }else if(preloan_speculate == "N"){

                }
            }
            /**
             * 2:투기과열지구
             */
            else if(region == "2") {//
                /**
                 * 담보대출이 있는경우
                 */
                if(preloan == "Y") {//투기지역=투기과열지구 가족중기주택담보Y ==> LTV 0%
                    console.log("투기지역=투기과열지구 가족중기주택담보Y LTV 0%");
                    ltv = 30;
                }
                /**
                 * 담보대출이 없는 경우
                 */
                else if(preloan == "N") {
                    if(ordinary == "Y"){//서민Y
                        if(amt <= 600000000){//6억이하 ==> LTV 50%
                            console.log("투기지역=투기과열지구 가족중기주택담보N 6억이하 LTV 50%");
                            ltv = 50;
                        }else{//6억초과 ==> LTV 40%
                            console.log("투기지역=투기과열지구 가족중기주택담보N 6억초과 LTV 40%");
                            ltv = 40;
                        }
                    } else if(ordinary == "N"){//서민N LTV 40%
                        console.log("투기지역=투기과열지구 가족중기주택담보N 서민N LTV 40%");
                        ltv = 40;
                    }
                }
                if(preloan_speculate == "Y"){

                }else if(preloan_speculate == "N"){

                }
            }else if(region == "3"){//3:조정대상지역
                if(preloan == "Y"){//조정대상지역 가족중기주택담보Y ==> LTV 50%
                    console.log("조정대상지역   가족중기주택담보Y LTV --50%--");
                    ltv = 50;
                }else if(preloan == "N"){
                    if(ordinary == "Y"){//서민Y
                        if(amt <= 500000000){//5억이하 ==> LTV 70%
                            console.log("조정대상지역 가족중기주택담보N 5억이하 LTV 70%");
                            ltv = 70;
                        }else{//5억초과 ==> LTV 60%
                            console.log("조정대상지역 가족중기주택담보N 5억초과 LTV 60%");
                            ltv = 60;
                        }
                    }else if(ordinary == "N"){//서민N LTV 60%
                        console.log("조정대상지역 가족중기주택담보N 서민N LTV 60%");
                        ltv = 60;
                    }
                }
                if(preloan_speculate == "Y"){

                }else if(preloan_speculate == "N"){

                }
            }
            // var a = "최대한도 : 시세의 <span class=\"txt-point\">ltv%</span><span class=\"txt-point\"> (LTVltv%)</span>";
            // 최대한도 : 시세의 <span class="txt-point">40%</span><span class="txt-point"> (LTV40%)</span>
            console.log("setLimit:ltv="+ltv);
            if(maxLtv < ltv) {
                maxLtv = ltv;
            }
            result = amt * ltv * 0.01;
            console.log("setLimit:result="+result);
            result = Math.round(result/1000)/10;
            console.log("setLimit:만원단위변환후 result="+result);
            result = number_format(""+result);
            console.log("setLimit: ',' 변환후 result="+result);
            return result;
        }
        var maxLtv = 0;
        function setButton(typ, paramYn){
            var frm = document.frmScrap;
            if(typ == "ordinary"){
                $("#ordinary_y").removeClass();
                $("#ordinary_n").removeClass();
                if(paramYn == "Y"){
                    $("#ordinary_y").addClass("btn btn-check active");
                    $("#ordinary_n").addClass("btn btn-check");
                    frm.hid_ordinary.value = "Y";
                }else{
                    $("#ordinary_y").addClass("btn btn-check");
                    $("#ordinary_n").addClass("btn btn-check active");
                    frm.hid_ordinary.value = "N";
                }
            }else if(typ == "preloan"){
                $("#preloan_y").removeClass();
                $("#preloan_n").removeClass();
                if(paramYn == "Y"){
                    $("#preloan_y").addClass("btn btn-check active");
                    $("#preloan_n").addClass("btn btn-check");
                    frm.hid_preloan.value = "Y";
                }else{
                    $("#preloan_y").addClass("btn btn-check");
                    $("#preloan_n").addClass("btn btn-check active");
                    frm.hid_preloan.value = "N";
                }
            }
            chgSqure();
        }
        var selGubun = 1;
        function goReset(gubun) {
            selGubun = gubun;
            if(gubun == '1'){
                $('#house_info').html('아파트 정보');
            } else if(gubun == '2') {
                $('#house_info').html('오피스텔 정보');
            }
            // $("#REGION1_CODE option:eq(0)").prop("selected", true);
            // $('#REGION1_CODE').selectpicker('refresh');

            // frmScrap.REGION2_CODE.options.length=0;
            // $('#REGION2_CODE').append("<option value=''>시/군/구</option>");
            // $('#REGION2_CODE').selectpicker('refresh');

            frmScrap.REGION3_CODE.options.length=0;
            $('#REGION3_CODE').append("<option value=''>읍/면/동</option>");
            $('#REGION3_CODE').selectpicker('refresh');
            // $('#apartment_nm').val('');
            frmScrap.apartment_nm.options.length=0
            $('#apartment_nm').append("<option value=''>아파트명</option>");
            $('#apartment_nm').selectpicker('refresh');
            frmScrap.FLOOR_PLAN_AREA.options.length=0
            $('#FLOOR_PLAN_AREA').append("<option value=''>공급면적/전용면적</option>");
            $('#FLOOR_PLAN_AREA').selectpicker('refresh');

            // $("#ordinary_y").removeClass();
            // $("#ordinary_n").removeClass();
            // $("#preloan_y").removeClass();
            // $("#preloan_n").removeClass();
            // $("#ordinary_n").addClass("btn");
            // $("#ordinary_y").addClass("btn");
            // $("#preloan_n").addClass("btn");
            // $("#preloan_y").addClass("btn");
            frmScrap.hid_ordinary.value = "";
            frmScrap.hid_preloan.value = "";

            sale_lowest_average.innerHTML = "";
            sale_general_average.innerHTML = "";
            sale_highest_average.innerHTML = "";
            limit_lowest.innerHTML = "";
            limit_general.innerHTML = "";
            limit_highest.innerHTML = "";

            frmScrap.BUILDING_TYPE.value = gubun;
        }
    </script>
</head>
<body>
<div id="wrapper">
    <form name="frmScrap" id="frmScrap" method="post" onsubmit="return false;">
        <input type="hidden" name="hid_preloan" id="hid_preloan" value="Y"/>
        <input type="hidden" name="hid_ordinary" id="hid_ordinary" value="Y"/>
        <input type="hidden" name="API_ID" id="API_ID" value="9921"/>
        <input type="hidden" name="BUILDING_TYPE" id="BUILDING_TYPE" value="1"/>
        <!-- Header -->
        <header id="header">
            <div class="input-group">
                <div class="input-group-btn">
                    <button type="button" class="ui-nav nav-back" onclick="history.back();">뒤로가기</button>
                </div>
                <h1>시세/대출한도 조회</h1>
            </div>
        </header>
        <!-- Content -->
        <section id="content">
        	<div class="affix-fixed top-fixed-item">
	            <ul class="nav nav-outline nav-justified tabs">
	                <!-- 			<li class="active"><a href="#tab1">아파트</a></li> -->
	                <li class="active"><a role="button" onclick="goReset('1');">아파트</a></li>
	                <!-- 			<li><a href="#tab2">오피스텔</a></li> -->
	                <li><a role="button" onclick="goReset('2');">오피스텔</a></li>
	            </ul>
			</div>
            <div class="security-limit-sch">
                <div class="tab-content">
                    <div class="tab-pane active" id="tab1">
                        <!-- tab1 -->
                        <h2 class="h2" id="house_info">아파트 정보</h2>
                        <div class="container-fluid">
                            <div class="tab-item">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-xs-4">
										<span class="" aria-hidden="true">
											<select class="selectpicker" data-header="시/도" name="REGION1_CODE" id="REGION1_CODE" onchange="ChangeSelectBox(document.frmScrap, this)">
												<option value="">시/도</option>
											</select>
										</span>
                                        </div>
                                        <div class="col-xs-4">
										<span class="" aria-hidden="true">
											<select class="selectpicker" data-header="시/군/구" name="REGION2_CODE" id="REGION2_CODE" onchange="ChangeSelectBox(document.frmScrap, this)">
												<option value="">시/군/구</option>
											</select>
										</span>
                                        </div>
                                        <div class="col-xs-4">
										<span class="" aria-hidden="true">
											<select class="selectpicker" data-header="읍/면/동" name="REGION3_CODE" id="REGION3_CODE" onchange="ChangeSelectBox2(document.frmScrap, this)">
												<option value="">읍/면/동</option>
											</select>
										</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-xs-6">
										<span class="" aria-hidden="true">
											<input type="hidden" class="form-control " id="APARTMENT" name="APARTMENT" />
											<%--<input type="text" class="form-control " id="apartment_nm" name="apartment_nm"  placeholder="검색해주세요" validate="label:apartment_nm;maxbt:30;" maxlength="30"/>--%>
										    <select class="selectpicker" data-header="아파트명" name="apartment_nm" id="apartment_nm" onchange="goKbMarketPriceList();">
												<option value="">아파트명</option>
											</select>
										</span>
                                        </div>
                                        <div class="col-xs-6">
										<span class="" aria-hidden="true">
											<select class="selectpicker" name="FLOOR_PLAN_AREA" id="FLOOR_PLAN_AREA" data-header="공급면적/전용면적" onchange="chgSqure();">
												<option value="">공급면적/전용면적</option>
											</select>
										</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h2 class="h2">대출자 정보
                            <a role="button" data-toggle="collapse" data-layer="popup" href="#debt-info" class="icon-link"><i class="pop-info ico-alert">정보</i></a>
                        </h2>
                        <div class="container-fluid">
                            <div class="tab-item info">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <h3 class="title">기담보대출</h3>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="pull-right btn-group btn-group-justified">
                                        	<button id="preloan_y" class="btn btn-check" role="button" onclick="setButton('preloan', 'Y');">보유</button>
                                        	<button id="preloan_n" class="btn btn-check active" role="button" onclick="setButton('preloan','N');">미보유</button>
                                            <!-- <div id="preloan_y" class="btn btn-check" role="button" onclick="setButton('preloan', 'Y');">보유</div>
                                            <div id="preloan_n" class="btn btn-check" role="button" onclick="setButton('preloan','N');">미보유</div> -->
                                            
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-6">
                                        <h3 class="title">서민/실소유자</h3>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="pull-right btn-group btn-group-justified">
                                            <button id="ordinary_y" class="btn btn-check" role="button" onclick="setButton('ordinary', 'Y');">해당</button>
                                            <button id="ordinary_n" class="btn btn-check" role="button" onclick="setButton('ordinary', 'N');">미해당</button>
                                            <!-- <div id="ordinary_y" class="btn" role="button" onclick="setButton('ordinary', 'Y');">해당</div>
                                            <div id="ordinary_n" class="btn" role="button" onclick="setButton('ordinary', 'N');">미해당</div> -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h2 class="h2">시세 및 대출한도
                            <span class="time pull-right"><%--<time class="time">기준일: 2017.08.28 (단위:만원)</time>--%></span>
                        </h2>
                        <div class="container-fluid">
                            <div class="tab-item"><%--<span><div id="limit_max"></div>--%><!-- 최대한도 : 시세의 40%(LTV40%) </span>-->
                                <div class="limit-txt" id="limit_max"></div>
                                <p class="txt-sm-left">* 시세 및 조건 변동에  따라 실제 대출 한도는 다를 수 있습니다.</p>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <td></td>
                                        <td>하위평균가</td>
                                        <td>일반평균가</td>
                                        <td>상위평균가</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>시세</td>
                                        <td class="text-right"><div id="sale_lowest_average"></div></td>
                                        <td class="text-right"><div id="sale_general_average"></div></td>
                                        <td class="text-right"><div id="sale_highest_average"></div></td>
                                    </tr>
                                    <tr class="tr-point">
                                        <td>한도</td>
                                        <td class="text-right"><div id="limit_lowest"></div></td>
                                        <td class="text-right"><div id="limit_general"></div></td>
                                        <td class="text-right"><div id="limit_highest"></div></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- //Content -->
    </form>
</div>
<div id="debt-info" class="collapse popup-layer">
    <span class="title">기담보대출</span>
    <p>가족(본인 및 세대원)의 주택담보대출 여부</p>
    <span class="title">서민/실소유자</span>
    <p>무주택세대주이면서 부부합산 연소득 6천만원이하, 생애 최초구입자는 7천만원이하</p>
    <button data-toggle="collapse" data-target="#debt-info" aria-expanded="false" aria-controls="collapseExample" class="btn-close _popup_close"><i class="icon-close">레이어 닫기</i></button>
</div>
</body>
</html>
