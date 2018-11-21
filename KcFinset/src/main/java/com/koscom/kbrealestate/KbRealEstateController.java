package com.koscom.kbrealestate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.kbrealestate.model.KbDongAptVO;
import com.koscom.kbrealestate.model.KbMarketPriceInfo;
import com.koscom.kbrealestate.model.KbRealEstateForm;
import com.koscom.kbrealestate.service.KbRealEstateManager;

@Controller
@RequestMapping("/m/kbrealestate")
public class KbRealEstateController {

	private static final Logger logger = LoggerFactory.getLogger(KbRealEstateController.class);
	
	@Autowired
	KbRealEstateManager kbRealEstateManager;
	
	/**
	 * VUE
	 * @param model
	 * @param kbRealEstateForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAddrRegion1.json")
	public String listAddrRegion1(Model model, KbRealEstateForm kbRealEstateForm) throws Exception {
		logger.debug("listAddrRegion1");
		List<String> list = kbRealEstateManager.listAddrRegion1(kbRealEstateForm);
		model.addAttribute("listAddrRegion1", list);
		return "jsonView";
	}
	
	/**
	 * VUE
	 * @param model
	 * @param kbRealEstateForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAddrRegion2.json")
	public String listAddrRegion2(Model model, KbRealEstateForm kbRealEstateForm) throws Exception {
		logger.debug("listAddrRegion2");
		List<String> list = kbRealEstateManager.listAddrRegion2(kbRealEstateForm);
		model.addAttribute("listAddrRegion2", list);
		return "jsonView";
	}
	
	/**
	 * VUE
	 * @param model
	 * @param kbRealEstateForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAddrRegion3.json")
	public String listAddrRegion3(Model model, KbRealEstateForm kbRealEstateForm ) throws Exception {
		logger.debug("listAddrRegion3");
		List<String> list = kbRealEstateManager.listAddrRegion3(kbRealEstateForm);
		model.addAttribute("listAddrRegion3", list);
		return "jsonView";
	}
	
	/**
	 * VUE
	 * @param model
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listSrchApartmentInfo.json")
	public String listSrchApartmentInfo(Model model, KbRealEstateForm realEstateForm) throws Exception {
		logger.info("listSrchApartmentInfo");
		List<KbDongAptVO> list = kbRealEstateManager.listSrchApartmentInfo(realEstateForm);
		model.addAttribute("listSrchApartmentInfo", list);
		return "jsonView";
	}
	
	/**
	 * VUE
	 * @param request
	 * @param response
	 * @param kbRealEstateForm
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/scrapKbMarketPriceList.json")
	public String scrapKbMarketPriceList(KbRealEstateForm kbRealEstateForm, Model model) throws Exception{
		KbMarketPriceInfo kbMarketPriceInfo = kbRealEstateManager.scrapKbMarketPriceList(kbRealEstateForm);
		model.addAttribute("kbMarketPriceOfferingsList", kbMarketPriceInfo.getResp_data());
		model.addAttribute("kbMarketPricePriceList", kbMarketPriceInfo.getResp_data2());
		model.addAttribute("kbMarketPriceComplexList", kbMarketPriceInfo.getResp_data3());
		model.addAttribute("kbMarketPricePyeongList", kbMarketPriceInfo.getResp_data4());
		return "jsonView";
	}
}
