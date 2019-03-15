package com.koscom.kcb;

import com.koscom.util.FinsetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.kcb.model.Kcb_300;
import com.koscom.kcb.service.KcbManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/kcb/*")
public class KcbController {
    private Logger logger = LoggerFactory.getLogger(KcbController.class);
    @Autowired
    private KcbManager kcbManager;
    @RequestMapping("/listKcbMain.crz")
    public String listKcbMain(KcbCreditInfoVO kcbCreditInfoVO, Model model) {
        return "/kcb/listKcbMain";
    }
    /**
     * KCB 신용조회 실행
     * @param info
     * @param model
     * @return
     */
    @RequestMapping("procKcbCb.json")
    public String procKcbCbJson(KcbCreditInfoVO kcbCreditInfoVO , Model model) throws UnsupportedEncodingException,FinsetException,IOException {
        logger.info("[KCB 신용조회]START == \n"+kcbCreditInfoVO);
        logger.debug("kcbCreditInfoVO.getCd_regist():" + kcbCreditInfoVO.getCd_regist());
        logger.debug("kcbCreditInfoVO.getNmIf():"+ kcbCreditInfoVO.getNmIf());
        logger.debug("kcbCreditInfoVO.getNoPerson():" + kcbCreditInfoVO.getNoPerson());
        logger.debug("kcbCreditInfoVO.getNmCust():" + kcbCreditInfoVO.getNmCust());
        logger.debug("kcbCreditInfoVO.getBgn():" + kcbCreditInfoVO.getBgn());
        logger.debug("kcbCreditInfoVO.getDi():" + kcbCreditInfoVO.getDi());
        logger.debug("kcbCreditInfoVO.getCp():" + kcbCreditInfoVO.getCp());
        logger.debug("kcbCreditInfoVO.getCd_per_corp():" + kcbCreditInfoVO.getCd_per_corp());
//		model.addAttribute("returnData", kcbManager.procKcbCb(info));
        ReturnClass returnClass = kcbManager.getKcbCbInfo(kcbCreditInfoVO);
        model.addAttribute("returnData", returnClass);

        logger.debug("####################################################");
        logger.debug("returnClass.getCd_result():" + returnClass.getCd_result());
        logger.debug("returnClass.getDes_message():" + returnClass.getDes_message());
        logger.debug("returnClass.getMessage():" + returnClass.getMessage());
        logger.debug("returnClass.getReturnObj():" + returnClass.getReturnObj());
        logger.debug("####################################################");
        if(returnClass != null && returnClass.getCd_result().equals(Constant.SUCCESS)){
            if(kcbCreditInfoVO.getNmIf() != null && kcbCreditInfoVO.getNmIf().equals("300")){
                Object retObject = returnClass.getReturnObj();
                if(retObject != null && retObject instanceof Kcb_300){
                    Kcb_300 kcb_300 = (Kcb_300)retObject;
                    model.addAttribute("Kcb_Segment029", kcb_300.getListKcb_Segment029());
                    model.addAttribute("Kcb_Segment030", kcb_300.getListKcb_Segment030());
                    model.addAttribute("Kcb_Segment034", kcb_300.getListKcb_Segment034());
                    model.addAttribute("Kcb_Segment035", kcb_300.getListKcb_Segment035());
                    model.addAttribute("Kcb_Segment041", kcb_300.getListKcb_Segment041());
                    model.addAttribute("Kcb_Segment045", kcb_300.getListKcb_Segment045());
                    model.addAttribute("Kcb_Segment046", kcb_300.getListKcb_Segment046());
                    model.addAttribute("Kcb_Segment061", kcb_300.getListKcb_Segment061());
                    model.addAttribute("Kcb_Segment062", kcb_300.getListKcb_Segment062());
                    model.addAttribute("Kcb_Segment065", kcb_300.getListKcb_Segment065());
                    model.addAttribute("Kcb_Segment103", kcb_300.getListKcb_Segment103());
                    model.addAttribute("Kcb_Segment105", kcb_300.getListKcb_Segment105());
                }
                return "/kcb/listKcb";
            }
        }
        return "jsonView";
    }
    @RequestMapping("procKcbCb600.json")
    public String procKcbCbJson600(KcbCreditInfoVO kcbCreditInfoVO, Model model) throws UnsupportedEncodingException,FinsetException,IOException {
    	
        logger.info("[KCB 신용조회]START == \n"+kcbCreditInfoVO);
        logger.debug("kcbCreditInfoVO.getCd_regist():" + kcbCreditInfoVO.getCd_regist());
        logger.debug("kcbCreditInfoVO.getNmIf():"+ kcbCreditInfoVO.getNmIf());
        logger.debug("kcbCreditInfoVO.getNoPerson():" + kcbCreditInfoVO.getNoPerson());
        logger.debug("kcbCreditInfoVO.getNmCust():" + kcbCreditInfoVO.getNmCust());
        logger.debug("kcbCreditInfoVO.getBgn():" + kcbCreditInfoVO.getBgn());
        logger.debug("kcbCreditInfoVO.getDi():" + kcbCreditInfoVO.getDi());
        logger.debug("kcbCreditI	nfoVO.getCp():" + kcbCreditInfoVO.getCp());
        //1	7008022	이종술	19700802	2	010 111 2222	999788	MC0GCCqGSIb3DQIJAyEArXG0UaqvQUOE3R7KzX9exxq2w0dLi7gJ8ghGUa/ktvM=	P18760000000
        //2	7004131	손도희	19700413	1	010 111 2222	999893	MC0GCCqGSIb3DQIJAyEAmbyd8GBoK8dpfEbNV2GjXqjpQHv7Sxk8r/Pb8cfFjns=	P18760000000
        //3	5311121	현승호	19531112	1	010 111 2222	000244	MC0GCCqGSIb3DQIJAyEAc6LN4Re/Oera57xsQmtczam3Vhpz8NZJzxODJQRv/fQ=	P18760000000
        //4	5808061	전성열	19580806	1	010 111 2222	000471	MC0GCCqGSIb3DQIJAyEABbtfwWD97d1miIFX4waLIckAoz8wtzr3Reo++7vs+4w=	P18760000000
        //5	7005112	우송회	19700511	2	010 111 2222	999868	MC0GCCqGSIb3DQIJAyEA0TfTrlcFKIElAjm6CeZIDViBM7v21Zl8LD8xP64SxJQ=	P18760000000

//		kcbCreditInfoVO.setCd_regist("01");		//1: 발생 신규 고객ID 등록&업데이트,   5 : 고객 ID 삭제
//		kcbCreditInfoVO.setNmIf("600");
//		kcbCreditInfoVO.setNoPerson("P000000029");
//		kcbCreditInfoVO.setHp("0101112222");

//		kcbCreditInfoVO.setNmCust("이종술");	kcbCreditInfoVO.setBgn("197008022");	kcbCreditInfoVO.setDi("MC0GCCqGSIb3DQIJAyEArXG0UaqvQUOE3R7KzX9exxq2w0dLi7gJ8ghGUa/ktvM=");
//		kcbCreditInfoVO.setNmCust("손도희");	kcbCreditInfoVO.setBgn("197004131");	kcbCreditInfoVO.setDi("MC0GCCqGSIb3DQIJAyEAmbyd8GBoK8dpfEbNV2GjXqjpQHv7Sxk8r/Pb8cfFjns=");
//		kcbCreditInfoVO.setNmCust("현승호");	kcbCreditInfoVO.setBgn("195311121");	kcbCreditInfoVO.setDi("MC0GCCqGSIb3DQIJAyEAc6LN4Re/Oera57xsQmtczam3Vhpz8NZJzxODJQRv/fQ=");
//		kcbCreditInfoVO.setNmCust("전성열");	kcbCreditInfoVO.setBgn("195808061");	kcbCreditInfoVO.setDi("MC0GCCqGSIb3DQIJAyEABbtfwWD97d1miIFX4waLIckAoz8wtzr3Reo++7vs+4w=");
//		kcbCreditInfoVO.setNmCust("우송회");	kcbCreditInfoVO.setBgn("197005112");	kcbCreditInfoVO.setDi("MC0GCCqGSIb3DQIJAyEA0TfTrlcFKIElAjm6CeZIDViBM7v21Zl8LD8xP64SxJQ=");		
//		kcbCreditInfoVO.setCp("P18760000000");	//(No.10)에서 ‘01’인 경우에만 입력 : DI값 (64byte) + CP코드(12byte)	고정
        model.addAttribute("returnData", kcbManager.procKcbCb(kcbCreditInfoVO));
        return "jsonView";
    }
}
