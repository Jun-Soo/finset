package com.koscom.goods.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.koscom.domain.FinsetInfo;
import com.koscom.domain.GoodsInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.finset.model.FinsetDenyVO;
import com.koscom.finset.service.FinsetManager;
import com.koscom.goods.dao.GoodsMapper;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.util.DateUtil;
import com.koscom.util.NumberUtil;
import com.koscom.util.SpringApplicationContext;
import com.koscom.util.StringUtil;

@Service("goodsManager")
public class GoodsManagerImpl implements GoodsManager {
	private static final Logger logger = LoggerFactory.getLogger(GoodsManagerImpl.class);
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CodeManager codeManager;
	
	@Autowired
	private FinsetManager finsetManager;
	
	@Override
	public List<GoodsVO> listGoodsAllianceAll(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceAll(goodsForm);
	}
		
	@Override
	public List<GoodsVO> listGoodsAllianceCredit(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceCredit(goodsForm);
	}
	
	@Override
	public int listGoodsAllianceCreditCount(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceCreditCount(goodsForm);
	}
	
	@Override
	public List<GoodsVO> listGoodsAllianceHouse(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceHouse(goodsForm);
	}
	
	@Override
	public int listGoodsAllianceHouseCount(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceHouseCount(goodsForm);
	}
	
	@Override
	public List<GoodsVO> listGoodsAllianceBiz(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceBiz(goodsForm);
	}

	@Override
	public int listGoodsAllianceBizCount(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceBizCount(goodsForm);
	}
	
	@Override
	public GoodsVO getGoodsFavorite(GoodsVO goodsVO) {
		return goodsMapper.getGoodsFavorite(goodsVO);
	}
	
	@Override
	@Cacheable(value="CacheGoods" , key="#goodsInfo.cd_goods")
	public GoodsVO getGoodsInfo(GoodsInfo goodsInfo) {

		if(goodsInfo == null || StringUtil.isEmpty(goodsInfo.getCd_goods()))
			return null;

		return goodsMapper.getGoodsInfo(goodsInfo);
	}
	
	@Override
	public GoodsVO getGoodsInfo(String cd_goods) {
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setCd_goods(cd_goods);
		return getGoodsInfo(goodsInfo);
	}
	@Override
	public GoodsVO getGoodsInfo(String cd_fc, String cd_goods) {
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setCd_fc(cd_fc);
		//goodsInfo.setCd_apply_comp(cd_fc);
		goodsInfo.setCd_goods(cd_goods);
		return goodsMapper.getGoodsInfo(goodsInfo);
	}
	
	@Override
	public String getGoodsDetail(String cd_goods, String type) {
		GoodsVO goodsVO = getGoodsInfo(cd_goods);
		if(goodsVO == null) return cd_goods;

		String result = goodsVO.getNm_goods();

		// 금융사명
		if ( "NM_COMP".equals(type) ) {
			result = codeManager.getCodeName("cd_fc", goodsVO.getCd_fc());
		}
		// 금융사 상품코드
		else if ( "C20_GOODS".equals(type) ) {
			result = goodsVO.getC20_goods_comp();
		}
		// 접수 매체ID
		else if ( "ID".equals(type) ) {
			result = goodsVO.getId_request();
		}
		// 접수 URL
		else if ( "URL".equals(type) ) {
			result = goodsVO.getUrl_request();
		}
		// 접수 모델 폼
		else if ( "NM_FORM".equals(type) ) {
			result = goodsVO.getNm_request_form();
		}
		// 요약설명
		else if ( "SUMMARY".equals(type) ) {
			result = goodsVO.getSummary();
		}
		// 상품코드 -> 상품명
		else if ("NM_GOODS".equals(type) ) {
			result = goodsVO.getNm_goods();
		}

		return result;
	}
	
	@Override
	public List<GoodsVO> listGoodsFavoriteAlliance(GoodsForm goodsForm) {
		return goodsMapper.listGoodsFavoriteAlliance(goodsForm);
	}

	@Override
	public int getGoodsFavoriteAllianceCount(GoodsForm goodsForm) {
		return goodsMapper.getGoodsFavoriteAllianceCount(goodsForm);
	}

	@Override
	public List<GoodsVO> listGoodsFavoriteNoAlliance(GoodsForm goodsForm) {
		return goodsMapper.listGoodsFavoriteNoAlliance(goodsForm);
	}

	@Override
	public int getGoodsFavoriteNoAllianceCount(GoodsForm goodsForm) {
		return goodsMapper.getGoodsFavoriteNoAllianceCount(goodsForm);
	}
	
	@Override
	public GoodsVO getCooconGoodsFavorite(GoodsVO goodsVO) {
		return goodsMapper.getCooconGoodsFavorite(goodsVO);
	}
	
	private void setDenyDesc(List<FinsetInfo> list, String pFilterDesc, FinsetDenyVO finsetDenyVO) {
        String filterDesc = pFilterDesc;
        finsetDenyVO.setDeny_desc(filterDesc);
		logger.info("goodsManagerImpl : finsetDenyVO = " + finsetDenyVO.toString());
		FinsetInfo value = new FinsetInfo();
		value.setErr_desc(filterDesc);

		// namik ADD start
//		value.setNo_person(finsetDenyVO.getNo_person());
//		value.setNo_person(finsetDenyVO.getNo_prepare());
//		value.setCd_fc(finsetDenyVO.getCd_fc());
//		value.setCd_goods(finsetDenyVO.getCd_goods());
		finsetManager.insertFinsetDeny(finsetDenyVO);
		// namik ADD end

		list.add(value);
	}
	
	//나이 체크
	@Override
	public String checkAge(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc, FinsetDenyVO finsetDenyVO) {
        String filterDesc = pFilterDesc;
        if(goodsVO.getAge_loan_from() != null && goodsVO.getAge_loan_to() != null) {
			logger.info("--------나이cfs 시작----------");
			if(txFcTransmitVO.getYmd_birth() != null && !txFcTransmitVO.getYmd_birth().equals("") && txFcTransmitVO.getYmd_birth().length() >= 8){
				//bgn값 나이로 변환
				int personAge = DateUtil.getManAge(txFcTransmitVO.getYmd_birth());
				if(personAge < goodsVO.getAge_loan_from()) {
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_PERSON_AGE", "나이미달");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("나이미달");
				}else if(personAge > goodsVO.getAge_loan_to()) {
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_PERSON_AGE", "나이초과");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("나이초과");
				}
			}else {
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_EXIST_PERSON_BGN", "개인정보생년월일입력없음-나이비교불가");;
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("나이비교불가");
			}
		}
		return filterDesc;
	}

	//연소득 체크
	@Override
	public String checkAmtYearIncome(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc, FinsetDenyVO finsetDenyVO) {
		logger.info("--------연소득cfs 시작----------");
        String filterDesc = pFilterDesc;
        if(StringUtil.isNotEmpty(goodsVO.getAmt_year_income()) && StringUtil.isNotEmpty(goodsVO.getOu_year_income())) {
			if(goodsVO.getOu_year_income().equals("01")) {
				logger.info("연소득  이상일 경우");
				//TODO 다시 봐야함
				if(/*NumberUtil.parseInt(*/txFcTransmitVO.getAmt_year_income()/*)*/ < NumberUtil.parseInt(goodsVO.getAmt_year_income())) {
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_PERSON_YEAR_INCOME", "연소득미달");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("연소득미달");
				}

			} else if(goodsVO.getOu_year_income().equals("02")) {
				logger.info("연소득  이하일 경우");
				if(/*NumberUtil.parseInt(*/txFcTransmitVO.getAmt_year_income()/*)*/ > NumberUtil.parseInt(goodsVO.getAmt_year_income())) {
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_PERSON_YEAR_INCOME", "연소득초과");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("연소득초과");
				}
			}
		}
		return filterDesc;
	}

	//상품판매유무
	@Override
	public String checkGoodsUseYn(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc, FinsetDenyVO finsetDenyVO) {
		logger.info("--------상품판매유무cfs 시작----------");
        String filterDesc = pFilterDesc;
        if( !"Y".equals(goodsVO.getYn_use()) ){
			filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_USE_GOODS_INFO", "판매불가대출상품");
			setDenyDesc(list, filterDesc, finsetDenyVO);
			logger.info("판매불가대출상품");
		}
		return filterDesc;
	}

	//신청금액
	@Override
	public String checkAmtApply(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc, FinsetDenyVO finsetDenyVO) {

		logger.info("--------신청금액cfs 시작----------");
        String filterDesc = pFilterDesc;

		if( StringUtil.isNotEmpty( goodsVO.getAmt_apply_from() )  && StringUtil.isNotEmpty( goodsVO.getAmt_apply_to() ) ){

			if( txFcTransmitVO.getAmt_wanted() < NumberUtil.parseInt( goodsVO.getAmt_apply_from() ) ) { // 최소 금액 미달
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_PERSON_AMT_WANTED", "신청금액미달");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("신청금액미달");
			}else if( txFcTransmitVO.getAmt_wanted() > NumberUtil.parseInt( goodsVO.getAmt_apply_to() ) ){ // 최대 금액 초과
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_PERSON_AMT_WANTED", "신청금액초과");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("신청금액초과");
			}
		}
		return filterDesc;
	}

	//연매출액 체크
	@Override
	public String checkAmtYearSale 	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------연매출액cfs 시작----------");
        String filterDesc = pFilterDesc;

		if( StringUtil.isNotEmpty( goodsVO.getAmt_year_sale() ) ){
			if(goodsVO.getOu_sale_income().equals("01")) {
				logger.info("연매출액 이상일 경우");
				//TODO 다시 봐야함
				/*
				if(txFcTransmitVO.getA....() < NumberUtil.parseInt( goodsVO.getAmt_year_sale() )) {
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_PERSON_YEAR_INCOME", "연매출미달");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("연매출미달");
				}
				*/
			} else if(goodsVO.getOu_sale_income().equals("02")) {
				logger.info("연소득  이하일 경우");
				/*
				if(txFcTransmitVO.getA...() > NumberUtil.parseInt( goodsVO.getAmt_year_sale() )) {
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_PERSON_YEAR_INCOME", "연매출초과");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("연매출초과");
				}
				*/
			}
		}

		return filterDesc;
	}

	//고용형태 체크
	@Override
	public String checkCdEmployType	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc,	FinsetDenyVO finsetDenyVO) {

		int where = -1;
		logger.info("--------고용형태 cfs 시작----------");
        String filterDesc = pFilterDesc;

        if(goodsVO != null &&  StringUtil.isNotEmpty( goodsVO.getCd_employ_type() ) ){  //filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_EMPLOLY_TYPE_TO_APPLY", "접수가능한재직형태아님");
			String[] arrEmpType = StringUtil.tokenizeToStringArray( goodsVO.getCd_employ_type(), "," );

			logger.info("goodsInfo EmployType : " + arrEmpType);

			if(arrEmpType != null && txFcTransmitVO != null && StringUtil.isNotEmpty(txFcTransmitVO.getCd_employee_type()) ){
				where = StringUtil.findStringArrIdx(arrEmpType, txFcTransmitVO.getCd_employee_type());
			}
		}
		if( where < 0 ) {
			filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_EMPLOLY_TYPE_TO_APPLY", "접수가능한재직형태아님");
			setDenyDesc(list, filterDesc, finsetDenyVO);
			logger.info("접수가능한재직형태아님");
		}

		return filterDesc;
	}

	//주거종류 체크
	@Override
	public String checkCdHouseType		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------주거소유형태 cfs 시작----------");
        String filterDesc = pFilterDesc;

		if( StringUtil.isNotEmpty( goodsVO.getCd_house_type() ) ){
			String[] arrHouseType = StringUtil.tokenizeToStringArray( goodsVO.getCd_house_type(), "," );

			logger.info("goodsInfo arrHouseType : " + arrHouseType.toString());

			if( StringUtil.isNotEmpty(txFcTransmitVO.getCd_house_type()) ){
				int where = StringUtil.findStringArrIdx( arrHouseType, txFcTransmitVO.getCd_house_type() );
				if( where < 0 ){
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_HOUSE_TYPE_TO_APPLY", "접수가능한주거종류아님");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("접수가능한주거종류아님");
				}
			}
			/*
			else{
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_PERSON_YEAR_INCOME", "고용형태미선택");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("주거종류미선택");
			}
			*/
		}

		return filterDesc;
	}

	//주거소유형태 체크
	@Override
	public String checkCdLiveType		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------주거소유형태 cfs 시작----------");
        String filterDesc = pFilterDesc;

		if(goodsVO != null && StringUtil.isNotEmpty( goodsVO.getCd_live_type() ) ){
			String[] arrLiveType = StringUtil.tokenizeToStringArray( goodsVO.getCd_live_type(), "," );

			logger.info("goodsInfo arrLiveType : " + arrLiveType);

			if(txFcTransmitVO != null && StringUtil.isNotEmpty(txFcTransmitVO.getCd_live_type_home()) ){
				int where = StringUtil.findStringArrIdx( arrLiveType, txFcTransmitVO.getCd_live_type_home() );
				if( where < 0 ){
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_LIVE_TYPE_TO_APPLY", "접수가능한소유형태아님");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("접수가능한소유형태아님");
				}
			}
		}

		return filterDesc;
	}

	//자금용도 체크
	@Override
	public String checkCdLoanUse		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------자금용도 cfs 시작----------");
		String filterDesc = pFilterDesc;

		String cd_loan_use = null;
		if( StringUtil.isNotEmpty( goodsVO.getCd_loan_use() ) ){
			String[] arrLoanUse = StringUtil.tokenizeToStringArray( goodsVO.getCd_loan_use(), "," );

			logger.info("goodsInfo arrLoanUse : " + arrLoanUse.toString());
			cd_loan_use = txFcTransmitVO.getCd_loan_use();
			if(StringUtil.isNotEmpty(cd_loan_use)){
				int where = StringUtil.findStringArrIdx( arrLoanUse, txFcTransmitVO.getCd_loan_use() );
				if( where < 0 ){
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_LOAN_USE_TO_APPLY", "접수가능한자금용도아님");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("접수가능한자금용도아님");
				}
			}
			/*
			else{
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "", "주거소유형태미선택");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("주거소유형태미선택");
			}
			*/
		}

		return filterDesc;
	}

	//소득형태체크
	@Override
	public String checkCdTypeIncome	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------소득형태체크 cfs 시작----------");

		if( StringUtil.isNotEmpty( goodsVO.getCd_type_income() ) ){
			String[] arrTypeIncome = StringUtil.tokenizeToStringArray( goodsVO.getCd_type_income(), "," );

			logger.info("goodsInfo arrTypeIncome : " + arrTypeIncome.toString());

			/*
			if( StringUtil.isNotEmpty(txFcTransmitVO.getCd_...()) ){
				int where = StringUtil.findStringArrIdx( arrTypeIncome, txFcTransmitVO.getCd_...() );
				if( where < 0 ){
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_TYPE_INCOME_TO_APPLY", "접수가능한소득형태아님");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("접수가능한소득형태아님");
				}
			}
			*/
			/*
			else{
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "", "주거소유형태미선택");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("주거소유형태미선택");
			}
			*/
		}

		return filterDesc;
	}

	//연체여부(최근1년) 체크
	@Override
	public String checkDayDelay12month	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------연체여부(최근1년) cfs 시작----------");

		if( goodsVO.getDay_delay_12month() > 0 ){

			/*
			if( txFcTransmitVO.get...() > NumberUtil.parseInt( goodsVO.getDay_delay_12month() ) ) { // 최소 금액 미달
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_12_MONTH", "최근1년간연체초과");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("최근1년간연체초과");
			}
			*/
		}

		return filterDesc;
	}

	//연체 여부(최근 6개월) 체크
	@Override
	public String checkDayDelay6month	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------연체 여부(최근 6개월) cfs 시작----------");

		if( goodsVO.getDay_delay_6month() > 0 ){

			/*
			if( txFcTransmitVO.get...() > NumberUtil.parseInt( goodsVO.getDay_delay_6month() ) ) { // 최소 금액 미달
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("최근6개월간연체초과");
			}
			*/
		}

		return filterDesc;
	}

	//DTI_GRADE 체크(신용)
	@Override
	public String checkDtiGrade		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------DTI_GRADE cfs 시작----------");

//		if( goodsVO.getDti_grade1 () > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 1  && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade1 () ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}
//		if( goodsVO.getDti_grade2 () > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 2  && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade2 () ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}
//		if( goodsVO.getDti_grade3 () > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 3  && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade3 () ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}
//		if( goodsVO.getDti_grade4 () > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 4  && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade4 () ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}
//		if( goodsVO.getDti_grade5 () > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 5  && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade5 () ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}
//		if( goodsVO.getDti_grade6 () > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 6  && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade6 () ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}
//		if( goodsVO.getDti_grade7 () > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 7  && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade7 () ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}
//		if( goodsVO.getDti_grade8 () > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 8  && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade8 () ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}
//		if( goodsVO.getDti_grade9 () > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 9  && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade9 () ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}
//		if( goodsVO.getDti_grade10() > 0 ){
//			if( /*txFcTransmitVO.getGrade() == 10 && */ txFcTransmitVO.getDti...() > goodsVO.getDti_grade10() ) {
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_DELAY_6_MONTH", "최근6개월간연체초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("최근6개월간연체초과");
//			}
//		}

		return filterDesc;
	}

	//DTI 체크(담보)
	@Override
	public String checkGradeDti		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------DTI cfs 시작----------");
		/*
		if( goodsVO.getGrade_dti() > 0 ){
			if( txFcTransmitVO.getDti...() > goodsVO.getGrade_dti() ) {
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_PAWN_DTI", "담보DTI초과");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("담보DTI초과");
			}
		}
		*/
		return filterDesc;
	}

	//신용등급 (KCB) 체크
	@Override
	public String checkGradeKcb		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------신용등급 (KCB) cfs 시작----------");
		/*
		if( goodsVO.getGrade_kcb() > 0 ){
			if( txFcTransmitVO.getDti...() > goodsVO.getGrade_kcb() ) {
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_KCB_GRADE", "KCB신용등급미달");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("KCB신용등급미달");
			}
		}
		*/
		return filterDesc;
	}

	//LTV 체크
	@Override
	public String checkGradeLtv		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------LTV cfs 시작----------");
		/*
		if( goodsVO.getGrade_ltv() > 0 ){
			if( txFcTransmitVO.getDti...() > goodsVO.getGrade_ltv() ) {
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_LTV_GRADE", "담보LTV미달");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("담보LTV미달");
			}
		}
		*/
		return filterDesc;
	}

	//신용등급 (NICE)
	@Override
	public String checkGradeNice		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------신용등급 (NICE) cfs 시작----------");
		/*
		if( goodsVO.getGrade_nice() > 0 ){
			if( txFcTransmitVO.getDti...() > goodsVO.getGrade_nice() ) {
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_KCB_GRADE", "NICE신용등급미달");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("NICE신용등급미달");
			}
		}
		*/
		return filterDesc;
	}

	//근속 연수 체크
	@Override
	public String checkNoJobYear		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------근속 연수 cfs 시작----------");

		if( goodsVO.getNo_job_year() > 0 ){
			/*
			if( txFcTransmitVO.get...() > NumberUtil.parseInt( goodsVO.getNo_job_year() ) ) { // 근속연수미달
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_JOB_YEAR", "근속연수미달");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("근속연수미달");
			}
			*/
		}

		return filterDesc;
	}

	//신청기간(개월) 체크
	@Override
	public String checkNoMonthApply	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------신청기간(개월) cfs 시작----------");
        String filterDesc = pFilterDesc;

//		if( StringUtil.isNotEmpty( goodsVO.getNo_month_apply_from() )  && StringUtil.isNotEmpty( goodsVO.getNo_month_apply_to() ) ){
//
//			if( NumberUtil.parseInt( txFcTransmitVO.getCd_loan_term() ) < NumberUtil.parseInt( goodsVO.getNo_month_apply_from() ) ) { // 최소 금액 미달
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_PERSON_MONTH_APPLY", "신청기간미달");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("신청기간미달");
//			}else if( NumberUtil.parseInt( txFcTransmitVO.getCd_loan_term() ) > NumberUtil.parseInt( goodsVO.getAmt_apply_to() ) ){ // 최대 금액 초과
//				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_PERSON_MONTH_APPLY", "신청기간초과");
//				setDenyDesc(list, filterDesc, finsetDenyVO);
//				logger.info("신청기간초과");
//			}
//		}

		if( goodsVO.getNo_month_apply_from() > 0  && goodsVO.getNo_month_apply_to() > 0 ){

			if( NumberUtil.parseInt( txFcTransmitVO.getCd_loan_term() ) < goodsVO.getNo_month_apply_from() ) { // 최소 금액 미달
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_PERSON_MONTH_APPLY", "신청기간미달");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("신청기간미달");
			}else if( NumberUtil.parseInt( txFcTransmitVO.getCd_loan_term() ) > goodsVO.getNo_month_apply_to()  ){ // 최대 금액 초과
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_PERSON_MONTH_APPLY", "신청기간초과");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("신청기간초과");
			}
		}

		return filterDesc;
	}

	//입사일자 체크
	@Override
	public String checkYmdStartComp	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------입사일자 cfs 시작----------");
        String filterDesc = pFilterDesc;

		if( StringUtil.isNotEmpty( goodsVO.getYmd_start_comp() ) ){
			if( NumberUtil.parseInt( txFcTransmitVO.getYmd_start_biz() ) > NumberUtil.parseInt( goodsVO.getYmd_start_comp() ) ) { // 입사일자기준미달
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_YMD_START_COMP", "입사일자기준미달");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("입사일자기준미달");
			}
		}

		return filterDesc;
	}

	//4대 보험 가입여부 체크
	@Override
	public String checkYn4insu			(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------4대 보험 가입여부 cfs 시작----------");
		String filterDesc = pFilterDesc;
		if( StringUtil.isNotEmpty( goodsVO.getYn_4insu() ) ){
			if( "Y".equals(goodsVO.getYn_4insu()) && !goodsVO.getYn_4insu().equals(txFcTransmitVO.getYn_4insu()) ){
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_YN_4INSU", "접수가능한4대보험가입대상아님");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("접수가능한4대보험가입대상아님");
			}
		}

		return filterDesc;
	}

	//신용관리정보등재자 체크
	@Override
	public String checkYnBadCredit		(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------신용관리정보등재자 cfs 시작----------");

		if( StringUtil.isNotEmpty( goodsVO.getYn_bad_credit() ) ){
			/*
			if( "Y".equals(goodsVO.getYn_bad_credit()) && !goodsVO.getYn_bad_credit().equals(txFcTransmitVO.getYnBadCredit()) ) ){
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_YN_4INSU", "신용관리정보등재자");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("신용관리정보등재자");
			}
			*/
		}

		return filterDesc;
	}

	//연체 여부(현재) 체크
	@Override
	public String checkYnDelayCurrent	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------연체 여부(현재) cfs 시작----------");

		if( StringUtil.isNotEmpty( goodsVO.getYn_delay_current() ) ){
			/*
			if( "Y".equals(goodsVO.getYn_delay_current()) && !goodsVO.getYn_delay_current().equals(txFcTransmitVO.getYnDelayCurrent()) ) ){
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_YN_DELAY_CURRENT", "접수가능한미연체대상아님");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("접수가능한미연체대상아님");
			}
			*/
		}

		return filterDesc;
	}

	//소득증빙여부 체크
	@Override
	public String checkYnProofIncome	(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {

		logger.info("--------소득증빙여부 cfs 시작----------");
		//goodsVO.getYn_proof_income
		if( StringUtil.isNotEmpty( goodsVO.getYn_proof_income() ) ){
			/*
			if( "Y".equals(goodsVO.getYn_proof_income) && !goodsVO.getYn_proof_income().equals(txFcTransmitVO.getYnProofIncome()) ) ){
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_YN_PROOF_INCOME", "소득증빙자료미존재");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("소득증빙자료미존재");
			}
			*/
		}

		return filterDesc;
	}

	//담보 - 자동차 추가 내용
	//성별 체크
	public String checkCd_sex        (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String pFilterDesc,	FinsetDenyVO finsetDenyVO) {
		logger.info("--------성별 체크			 cfs 시작----------");
		String filterDesc = pFilterDesc;
		if( StringUtil.isNotEmpty( goodsVO.getCd_sex() ) ){
			String[] arrTypeSex = StringUtil.tokenizeToStringArray( goodsVO.getCd_sex(), "," );

			logger.info("goodsInfo arrTypeSex : " + arrTypeSex.toString());

			if( StringUtil.isNotEmpty(txFcTransmitVO.getCd_sex()) ){
				int where = StringUtil.findStringArrIdx( arrTypeSex, txFcTransmitVO.getCd_sex() );
				if( where < 0 ){
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_TYPE_SEX_TO_APPLY", "접수가능한성별아님");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("접수가능한성별아님");
				}
			}
			/*
			else{
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "", "주거소유형태미선택");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("주거소유형태미선택");
			}
			*/
		}
		return filterDesc;
	}

	//현금서비스(건) 체크
	public String checkCash_service  (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {
		logger.info("--------현금서비스(건) 체크	 cfs 시작----------");
		if( goodsVO.getCnt_cash_service() > 0 && goodsVO.getAmt_cash_service() > 0 ){
		/*
			if( NumberUtil.parseInt( txFcTransmitVO.getCnt_cash_service() ) > goodsVO.getCnt_cash_service() ) { // 현금서비스 건수 초과
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_NICE_CNT_CASH_SERVICE", "2차필터-현금서비스건수초과");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("2차필터-현금서비스건수초과");
			}

			if( NumberUtil.parseInt( txFcTransmitVO.getAmt_cash_service() ) > goodsVO.getAmt_cash_service() ) { // 현금서비스 건수 초과
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_NICE_AMT_CASH_SERVICE", "2차필터-현금서비스금액초과");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("2차필터-현금서비스금액초과");
			}

		*/
		}
		return filterDesc;
	}

	//담보소유형태차종류 체크
	public String checkCd_owncar_type(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {
		logger.info("--------담보소유형태차종류 체크	 cfs 시작----------");
		if( StringUtil.isNotEmpty( goodsVO.getCd_owncar_type() ) ){
			String[] arrTypeCdOwnCar = StringUtil.tokenizeToStringArray( goodsVO.getCd_owncar_type(), "," );

			logger.info("goodsInfo arrTypeCdOwnCar : " + arrTypeCdOwnCar.toString());
			/*
			if( StringUtil.isNotEmpty(txFcTransmitVO.getCd_owncar_type()) ){
				int where = StringUtil.findStringArrIdx( arrTypeCdOwnCar, txFcTransmitVO.getCd_owncar_type() );
				if( where < 0 ){
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_TYPE_CAR_OWNER_TO_APPLY", "접수가능한차량소유형태아님");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("접수가능한차량소유형태아님");
				}
			}
			*/
		}
		return filterDesc;
	}

	//담보종류 체크
	public String checkCd_car_type   (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {
		logger.info("--------담보종류 체크			 cfs 시작----------");
		if( StringUtil.isNotEmpty( goodsVO.getCd_car_type() ) ){
			String[] arrTypeCdCar = StringUtil.tokenizeToStringArray( goodsVO.getCd_car_type(), "," );

			logger.info("goodsInfo arrTypeCdCar : " + arrTypeCdCar);
			/*
			if( StringUtil.isNotEmpty(txFcTransmitVO.getCd_owncar_type()) ){
				int where = StringUtil.findStringArrIdx( arrTypeCdCar, txFcTransmitVO.getCd_owncar_type() );
				if( where < 0 ){
					filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "NOT_TYPE_CAR_TO_APPLY", "접수가능한차량종류아님");
					setDenyDesc(list, filterDesc, finsetDenyVO);
					logger.info("접수가능한차량종류아님");
				}
			}
			*/
		}
		return filterDesc;
	}

	//차량가격 체크
	public String checkAmt_carprice  (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {
		logger.info("--------차량가격 체크			 cfs 시작----------");
		if( goodsVO.getAmt_carprice_from() > 0 && goodsVO.getAmt_carprice_to() > 0 ){
		/*
			if( NumberUtil.parseInt( txFcTransmitVO.getAmt_carprice() ) < goodsVO.getAmt_carprice_from() ) { // 최소 금액 미만
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_CAR_PRICE_AMT", "차량가격미달");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("차량가격미달");

			}else if( NumberUtil.parseInt( txFcTransmitVO.getAmt_carprice() ) > goodsVO.getAmt_carprice_to()  ){ // 최대 금액 초과
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_CAR_PRICE_AMT", "차량가격초과");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("차량가격초과");
			}
		*/
		}
		return filterDesc;
	}

	//차량연식 체크
	public String checkNo_caryear    (TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,	FinsetDenyVO finsetDenyVO) {
		logger.info("--------차량연식 체크			 cfs 시작----------");
		if( goodsVO.getNo_caryear_from() > 0 && goodsVO.getNo_caryear_to() > 0 ){
			/*
			if( NumberUtil.parseInt( txFcTransmitVO.getNo_caryear() ) < goodsVO.getNo_caryear_from() ) { // 최소 연식 미만
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "LACK_CAR_YEAR_WANTED", "차량연식미달");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("차량연식미달");

			}else if( NumberUtil.parseInt( txFcTransmitVO.getNo_caryear() ) > goodsVO.getNo_caryear_to()  ){ // 최대 연식 초과
				filterDesc = codeManager.getNvlCodeName("FINSET_FILTER", "EXCEED_CAR_YEAR_WANTED", "차량연식초과");
				setDenyDesc(list, filterDesc, finsetDenyVO);
				logger.info("차량연식초과");
			}
			*/
		}
		return filterDesc;
	}
}
