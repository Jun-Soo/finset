package com.koscom.finset.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.koscom.domain.FinsetInfo;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finset.model.FinsetVO;
import com.koscom.util.NumberUtil;

import junit.framework.TestCase;

public class FinsetManager extends TestCase {
	public void testIMPL(){
		LinkedHashMap<FinsetVO, List<FinsetInfo>> linkedMap =  new LinkedHashMap<FinsetVO, List<FinsetInfo>>();
		TxFcReceiveVO txFcReceiveVO = new TxFcReceiveVO();
		LinkedList<FinsetVO> listGoods = new LinkedList<FinsetVO>();
		
		FinsetVO tempFinsetVO = new FinsetVO();
		tempFinsetVO.setCd_fc("S1208111421");
		tempFinsetVO.setCd_goods("g1");
		tempFinsetVO.setYear_term(1);
		tempFinsetVO.setAmt_limit("10000");
		tempFinsetVO.setRto_loan(11.22);
		listGoods.add(tempFinsetVO);
		tempFinsetVO = new FinsetVO();
		tempFinsetVO.setCd_fc("S1208111421");
		tempFinsetVO.setCd_goods("g1");
		tempFinsetVO.setYear_term(2);
		tempFinsetVO.setAmt_limit("20000");
		tempFinsetVO.setRto_loan(22.22);
		listGoods.add(tempFinsetVO);
		tempFinsetVO = new FinsetVO();
		tempFinsetVO.setCd_fc("S1208111421");
		tempFinsetVO.setCd_goods("g1");
		tempFinsetVO.setYear_term(3);
		tempFinsetVO.setAmt_limit("30000");
		tempFinsetVO.setRto_loan(33.22);
		listGoods.add(tempFinsetVO);
		tempFinsetVO = new FinsetVO();
		tempFinsetVO.setCd_fc("S1208111421");
		tempFinsetVO.setCd_goods("g1");
		tempFinsetVO.setYear_term(4);
		tempFinsetVO.setAmt_limit("40000");
		tempFinsetVO.setRto_loan(44.22);
		listGoods.add(tempFinsetVO);
		tempFinsetVO = new FinsetVO();
		tempFinsetVO.setCd_fc("S1208111421");
		tempFinsetVO.setCd_goods("g1");
		tempFinsetVO.setYear_term(5);
		tempFinsetVO.setAmt_limit("50000");
		tempFinsetVO.setRto_loan(55.22);
		listGoods.add(tempFinsetVO);
		
		tempFinsetVO = new FinsetVO();
		tempFinsetVO.setCd_fc("B2018168693");
		tempFinsetVO.setCd_goods("g2222");
		tempFinsetVO.setYear_term(1);
		tempFinsetVO.setAmt_limit("4000");
		tempFinsetVO.setRto_loan(111.22);
		listGoods.add(tempFinsetVO);
		tempFinsetVO.setCd_fc("B2018168693");
		tempFinsetVO.setCd_goods("g2222");
		tempFinsetVO.setYear_term(2);
		tempFinsetVO.setAmt_limit("4000");
		tempFinsetVO.setRto_loan(222.22);
		listGoods.add(tempFinsetVO);
		txFcReceiveVO.setListGoods(listGoods);

		
		String oldCdApplyComp = "";
		String oldCdGoods = "";
		int iCnt = 0;
		List<FinsetInfo> listFinsetInfo = new ArrayList<FinsetInfo>();
		FinsetVO makedFinsetVO = new FinsetVO();
		for (FinsetVO finsetVO : txFcReceiveVO.getListGoods()) {
//			System.out.println("apply_comp:" + FinsetVO.getCd_fc()+ ":" + "Cd_goods:" + FinsetVO.getCd_goods()+ ":" + "Year_term:" + FinsetVO.getYear_term()+ ":" + "Rto_loan:" + FinsetVO.getRto_loan()+ ":" + "Amt_limit:" + FinsetVO.getAmt_limit());
			FinsetInfo value = new FinsetInfo();
			value.setYear_term(finsetVO.getYear_term());
			value.setAmt_limit(finsetVO.getAmt_limit());
			value.setRto_loan(NumberUtil.formatNumberByPointForDouble(finsetVO.getRto_loan() * 100, 3));
			listFinsetInfo.add(value);

			//컷오프 
			//	이프(컷오프 안됐으면)

			if(iCnt > 0 && !oldCdApplyComp.equals(finsetVO.getCd_fc()) && !oldCdGoods.equals(finsetVO.getCd_goods())){
//				System.out.println("담기iCnt:" + iCnt + ":" + oldCdApplyComp + ":" + oldCdGoods + FinsetVO.getCd_fc() + ":" + FinsetVO.getCd_goods());
				makedFinsetVO.setCd_fc(oldCdApplyComp);
				makedFinsetVO.setCd_goods(oldCdGoods);
				linkedMap.put(makedFinsetVO, listFinsetInfo);
				
				makedFinsetVO = new FinsetVO();
				listFinsetInfo = new ArrayList<FinsetInfo>();	//화면 표시를 위한 list
			}
			oldCdApplyComp = finsetVO.getCd_fc();
			oldCdGoods = finsetVO.getCd_goods();
			iCnt++;
		}
		
		if(txFcReceiveVO != null && txFcReceiveVO.getListGoods() != null && txFcReceiveVO.getListGoods().size() == iCnt){
			makedFinsetVO.setCd_fc(oldCdApplyComp);
			makedFinsetVO.setCd_goods(oldCdGoods);
			linkedMap.put(makedFinsetVO, listFinsetInfo);
		}
		
		System.out.println("[LinkedHashMap]");
		Set<FinsetVO> linkedSet = linkedMap.keySet();
		Iterator<FinsetVO> linkedIter = linkedSet.iterator();
		for (FinsetVO FinsetVO : linkedSet) {
//			System.out.println("key:"+FinsetVO + ":value:"+linkedMap.get(FinsetVO));
			for (FinsetInfo FinsetInfo :  linkedMap.get(linkedIter.next())) {
				System.out.println("금융사코드:" + FinsetVO.getCd_fc()+ ":상품코드:" + FinsetVO.getCd_fc()
				+ ":년도:" + FinsetInfo.getYear_term() + ":금리:" + FinsetInfo.getRto_loan() + ": 한도금액:" + FinsetInfo.getAmt_limit()
				);
			} 
		}
	}
}
