package com.koscom.util;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.env.model.ApprovalManage;
import com.koscom.env.model.ProgramManage;

public class Constant {

	private static final Logger logger = LoggerFactory.getLogger(Constant.class);

	public final static String FCM_SERVER_KEY = "key=AAAAl-PTgOk:APA91bEBPEbDpz-eaOwZB5Zjh3zm7C0HBl4_dwcYeDK2wEmzznOh19251dTpEEf1Deoh8Ktv_W-qsjykrUeZ7TxR99U8xwxsmDaHnlebQds12c9crmksOlyMhJrbTs1fIFCV3M_d_tIn";
	public final static String SUCCESS = "00"; 	// 작업결과 성공
	public final static String FAILED = "01"; 	// 작업결과 실패
	public final static String NOT_AGREE_USING = "03"; // 이용약관 미동의

	public final static String FCM_TOPIC_ANDROID = "/topics/koscom";
	public final static String FCM_TOPIC_IOS = "/topics/koscom_ios";

	public final static long CD_LOAN 		= 10;
	public final static long CD_RECEIPT 	= 20;

	public final static String BM = "10";
	public final static String AI = "20";
	public static final String AG = "60";

	// 10 : 성공, 21 : ID오류, 22 : PASSWORD 오류, 91 : 접근불가
	public final static String LOGIN_SUCCESS 		= "10";
	public final static String LOGIN_ID_ERR 		= "21";
	public final static String LOGIN_PASS_ERR 		= "22";
	public final static String LOGIN_ACCESS_ERR 	= "91";

	// 시스템 마감 여부
	public static String IS_SYSTEM_CLOSED ="N";

	public final static LinkedHashMap<String,ProgramManage> PROGRAM = new LinkedHashMap<String,ProgramManage>();

	public final static LinkedHashMap<String,ApprovalManage> APPROVAL = new LinkedHashMap<String,ApprovalManage>();

	public final static String SECURITY_ROLE = "ROLE_ADMIN";

	public final static boolean getIsAllowedIpAddress(String ipAddr,boolean pIsAllowedIpAddress){
	    boolean isAllowedIpAddress = pIsAllowedIpAddress;
        String[] CRZ_IP_ADDRESS = {"127.0.0.1","0:0:0:0:0:0:0:1","192.168.0.1"
                ,"119.192.135.103", "119.192.135.85","119.192.135.85"
                ,"10.30.40.40","10.30.30.30","211.255.206.81","10.139.105.124"
                ,"10.30.50.11" //DEV_WEB
                ,"10.0.31.11" //REAL_WEB1
                ,"10.0.31.12" //REAL_WEB2
                ,"10.0.31.13" //REAL_TEST
                };
        for (String crzIpAddr : CRZ_IP_ADDRESS) {
            if(crzIpAddr.equals(ipAddr)) {
                isAllowedIpAddress = true;
                break;
            }
        }
        return isAllowedIpAddress;
    }
	public void init() {

		logger.debug("load program list..");
		PROGRAM.put("60000001", new ProgramManage("코스콤", "60", "00", "00", "", 1));//그룹이 00이라 상위메뉴에 안뜸

		PROGRAM.put("60101001", new ProgramManage("고객관리",								"60", "10", "10", "", 1));
		PROGRAM.put("60102001", new ProgramManage("회원리스트",								"60", "10", "20", "/person/listPersonMain.crz", 					1));
		PROGRAM.put("60102002", new ProgramManage("탈퇴관리",								"60", "10", "20", "/person/listPersonQuitMain.crz", 				2));
		PROGRAM.put("60102003", new ProgramManage("고객통지리스트",							"60", "10", "20", "/push/frameSendPush.crz", 						3));
		PROGRAM.put("60102004", new ProgramManage("개별고객통지리스트",						"60", "10", "20", "/pusheach/frameSendPushEach.crz", 				4));
		PROGRAM.put("60102005", new ProgramManage("관리자전산 접속이력관리",				"60", "10", "20", "/worker/listWorkerLoginHistMain.crz", 			5));
		PROGRAM.put("60102006", new ProgramManage("회원 접속이력관리",						"60", "10", "20", "/person/listPersonLoginHistMain.crz", 			6));
//		PROGRAM.put("60102003", new ProgramManage("고객통지리스트",							"60", "10", "20", "/findesign/listFindesignMain.crz", 				3));

		PROGRAM.put("60201001", new ProgramManage("조회및실행이력",							"60", "20", "10", "", 1));
		PROGRAM.put("60202001", new ProgramManage("대출신청관리(서류함)",					"60", "20", "20", "/apply/listApplyMain.crz",						1));
//		PROGRAM.put("60202002", new ProgramManage("실행이력",								"60", "20", "20", "/past/listExcuteHistMain.crz", 					2));
//		PROGRAM.put("60202003", new ProgramManage("조회이력",								"60", "20", "20", "/past/listLookupHistMain.crz", 					3));
//		PROGRAM.put("60202004", new ProgramManage("조건검색사업자",							"60", "20", "20", "/conditionbiz/listConditionbizMain.crz", 		4));
//		PROGRAM.put("60202005", new ProgramManage("조건검색개인",							"60", "20", "20", "/conditioncredit/listConditioncreditMain.crz", 	5));
//		PROGRAM.put("60202006", new ProgramManage("조건검색집",								"60", "20", "20", "/conditionhouse/listConditionhouseMain.crz", 	6));
//		PROGRAM.put("60202004", new ProgramManage("과거신청내역 리스트",					"60", "20", "20", "/past/listPastMain.crz", 						4));
//		PROGRAM.put("60202005", new ProgramManage("마이컨설턴트-신용등급변동표",			"60", "20", "20", "/grade/listGradeMain.crz", 						5));
//		PROGRAM.put("60202006", new ProgramManage("마이컨설턴트-거절 내역 및 분석",			"60", "20", "20", "/deny/listDenyMain.crz", 						6));

		PROGRAM.put("60301001", new ProgramManage("통계",									"60", "30", "10", "", 												1));
		PROGRAM.put("60302001", new ProgramManage("제휴금융사별 통계",						"60", "30", "20", "/stats/viewStatApplyComp.crz", 					1));
		PROGRAM.put("60302002", new ProgramManage("홈페이지 접속 통계",						"60", "30", "20", "/stats/viewStatWorker.crz",						2));
//		PROGRAM.put("60302001", new ProgramManage("기표별  통계",							"60", "30", "20", "/stats/viewStatAdvertisement.crz", 				1));

		PROGRAM.put("60401001", new ProgramManage("상품관리(금융사)",								"60", "40", "10", "", 												1));
		PROGRAM.put("60402001", new ProgramManage("상품관리",								"60", "40", "20", "/goods/listGoodsMain.crz", 						1));
		PROGRAM.put("60402002", new ProgramManage("신청이력",								"60", "40", "20", "/apply/listApplyMain.crz", 						2));
		PROGRAM.put("60402003", new ProgramManage("상품 조회 이력",								"60", "40", "20", "/apply/listApplyResultMain.crz", 						3));
//		PROGRAM.put("60402001", new ProgramManage("금융사리스트",							"60", "40", "20", "/fincorp/listFincorpMain.crz", 					1));
//		PROGRAM.put("60402003", new ProgramManage("수수료관리",								"60", "40", "20", "/commission/listCommissionMain.crz", 			3));
//		PROGRAM.put("60402004", new ProgramManage("coocon스크래핑상품관리",					"60", "40", "20", "/coocon/cooconMain.crz", 						4));
//		PROGRAM.put("60402005", new ProgramManage("은행스크래핑상품관리",					"60", "40", "20", "/goodsbank/listGoodsbankMain.crz", 				5));

		PROGRAM.put("60501001", new ProgramManage("금융사관리",								"60", "50", "10", "", 												1));
		PROGRAM.put("60502001", new ProgramManage("금융사리스트",							"60", "50", "20", "/fincorp/listFincorpMain.crz", 					1));
		PROGRAM.put("60502002", new ProgramManage("상품관리",								"60", "50", "20", "/goods/listGoodsMain.crz", 						2));
		PROGRAM.put("60502003", new ProgramManage("수수료관리",								"60", "50", "20", "/commission/listCommissionMain.crz", 			3));
//		PROGRAM.put("60502004", new ProgramManage("coocon스크래핑상품관리",					"60", "50", "20", "/coocon/cooconMain.crz", 						4));
//		PROGRAM.put("60502005", new ProgramManage("은행스크래핑상품관리",					"60", "50", "20", "/goodsbank/listGoodsbankMain.crz", 				5));

		PROGRAM.put("60601001", new ProgramManage("게시판",									"60", "60", "10", "", 												1));
		PROGRAM.put("60602001", new ProgramManage("게시판",									"60", "60", "20", "/board/listBoardInfoMain.crz",					1));
		PROGRAM.put("60602002", new ProgramManage("게시판관리",								"60", "60", "20", "/board/listBoardManageMain.crz", 				2));
//		PROGRAM.put("60502002", new ProgramManage("제휴사게시판",							"60", "50", "20", "/agency/agencyMain.crz", 						2));
//		PROGRAM.put("60502003", new ProgramManage("웹고객게시판",							"60", "50", "20", "/agency/agencyMain.crz", 						3));

		PROGRAM.put("60701001", new ProgramManage("스크래핑/API관리",						"60", "70", "10", "", 												1));
		PROGRAM.put("60702001", new ProgramManage("(COOCON)금융사스크래핑관리",				"60", "70", "20", "/coocon/cooconMain.crz", 						1));
		PROGRAM.put("60702002", new ProgramManage("(COOCON)금융사상품관리",					"60", "70", "20", "/goodsbank/listGoodsbankMain.crz", 				2));
		PROGRAM.put("60702003", new ProgramManage("(COOCON)금융사리스트",					"60", "70", "20", "/goodsbank/listGoodsbankInfoMain.crz", 			3));
		PROGRAM.put("60702004", new ProgramManage("(COOCON)KB부동산시세",					"60", "70", "20", "/scrap/listScrapMain.crz", 						4));
		PROGRAM.put("60702005", new ProgramManage("FSS",									"60", "70", "20", "/scrap/listScrapMain2.crz", 						5));
		PROGRAM.put("60702006", new ProgramManage("(KCB)전문조회",							"60", "70", "20", "/kcb/listKcbMain.crz", 							6));
		PROGRAM.put("60702007", new ProgramManage("(KCB)스크래핑결과 부채조회",				"60", "70", "20", "/debt/listDebtPersonMain.crz", 					7));

		PROGRAM.put("60801001", new ProgramManage("전문관리",								"60", "80", "10", "", 												1));
		PROGRAM.put("60802001", new ProgramManage("표준데이터항목관리",						"60", "80", "20", "/stdcode/listStdCodeMain.crz", 					1));
		PROGRAM.put("60802002", new ProgramManage("금융사 전문별 데이터항목관리",			"60", "80", "20", "/fccode/listFcCodeMain.crz", 					2));
		PROGRAM.put("60802003", new ProgramManage("전문관리",								"60", "80", "20", "/edoc/listEdocMain.crz", 						3));

		PROGRAM.put("60851001", new ProgramManage("신용상담",								"60", "85", "10", "", 												1));
		PROGRAM.put("60852001", new ProgramManage("상담 접수 및 현황",					    "60", "85", "20", "/counsel/formListCounselMain.crz", 				1));


		PROGRAM.put("60901090", new ProgramManage("시스템관리",								"60", "90", "10", "", 90));
		PROGRAM.put("60902001", new ProgramManage("사용자및권한그룹관리",					"60", "90", "20", "/worker/workerMain.crz",							1));
//		PROGRAM.put("60902002", new ProgramManage("로그관리",								"60", "90", "20", "/worker/listWorkerLoginHistMain.crz",			2));
//		PROGRAM.put("60902007", new ProgramManage("게시판관리",								"60", "90", "20", "/board/listBoardManageMain.crz", 				7));
		PROGRAM.put("60902015", new ProgramManage("권한이력",								"60", "90", "20", "/worker/listWorkerAuthHistMain.crz", 			15));
//		PROGRAM.put("60902016", new ProgramManage("개인정보수정이력",						"60", "90", "20", "/person/listPersonInfoHistMain.crz", 			16));
//		PROGRAM.put("60902089", new ProgramManage("표준데이터항목관리",						"60", "90", "20", "/stdcode/listStdCodeMain.crz", 					89));
//		PROGRAM.put("60902090", new ProgramManage("금융사 전문별 데이터항목관리",			"60", "90", "20", "/fccode/listFcCodeMain.crz", 					90));
//		PROGRAM.put("60902091", new ProgramManage("전문관리",								"60", "90", "20", "/edoc/listEdocMain.crz", 						91));
		PROGRAM.put("60902099", new ProgramManage("코드관리",								"60", "90", "20", "/env/listCodeMain.crz", 							99));
//		PROGRAM.put("60902080", new ProgramManage("스크래핑관리",							"60", "90", "20", "/scrap/listScrapMain.crz", 						80));
//		PROGRAM.put("60902081", new ProgramManage("KIS업체관리",							"60", "90", "20", "/kisline/listKisSrchByNameMain.crz", 			81));
//		PROGRAM.put("60902082", new ProgramManage("KCB관리",								"60", "90", "20", "/kcb/listKcbMain.crz", 							82));
//		PROGRAM.put("60902083", new ProgramManage("부채관리",								"60", "90", "20", "/debt/listDebtMain.crz", 						83));
		logger.debug("load approval list..");
	}
}
