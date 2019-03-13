package com.koscom.deny;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.deny.model.DenyForm;
import com.koscom.deny.model.DenyVO;
import com.koscom.deny.service.DenyManager;
import com.koscom.finset.model.FinsetVO;
import com.koscom.person.model.PersonForm;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Pagination;

@Controller
@RequestMapping("/deny")
public class DenyController<E> {

	private static final Logger logger = LoggerFactory.getLogger(DenyController.class);
	@Autowired
	private DenyManager denyManager;
	@Autowired
	private PersonManager personManager;
	@RequestMapping("/listDenyMain.crz")
	public String listDenyMain(FinsetVO finsetVO, Model model){
		return "/deny/listDenyMain";
	}

	public LinkedHashMap<DenyVO, List<DenyVO>> getListDenyDetail(List<DenyVO> list){
		LinkedHashMap<DenyVO, List<DenyVO>> linkedMap = new LinkedHashMap<DenyVO, List<DenyVO>>();
		for (DenyVO denyVO2 : list) {
			linkedMap.put(denyVO2, denyManager.listDenyDetail(denyVO2));
		}
		return linkedMap;
	}
	@RequestMapping("/listDenyDetail.crz")
	public String listDenyDetail(DenyVO denyVO, Model model){
		List<DenyVO> list = denyManager.listDenyDetail(denyVO);
		LinkedHashMap<DenyVO, List<DenyVO>> linkedMap = new LinkedHashMap<DenyVO, List<DenyVO>>();

		String beforeDt_frt = "";
		//String beforeCd_apply_comp = "";
		String beforeCd_fc = "";
		String beforeCd_goods = "";
		String beforeCd_goods_type = "";
		String beforeNm_goods = "";
		String beforeYn_use = "";
		String beforeDeny_desc = "";

		int i = 0;
		List<DenyVO> list2 = new ArrayList<DenyVO>();
		for (DenyVO denyVO2 : list) {
			//비포에 넣고 
			//현재랑 비교해서 다르면 denyVO3에 넣는다
			if((i > 0) && (!beforeCd_fc.equals(denyVO2.getCd_fc()) || !beforeCd_goods.equals(denyVO2.getCd_goods()))){
				//System.out.println("달라서:" + i + ":" + denyVO2.getNm_goods() + ":" + denyVO2.getCd_apply_comp() + denyVO2.getCd_goods() + ":" + beforeCd_apply_comp + beforeCd_goods);
				System.out.println("달라서:" + i + ":" + denyVO2.getNm_goods() + ":" + denyVO2.getCd_fc() + denyVO2.getCd_goods() + ":" + beforeCd_fc + beforeCd_goods);
				System.out.println("");
				
				//리스트 쌓고
				DenyVO value = new DenyVO();
				value.setDeny_desc(beforeDeny_desc);
				list2.add(value);
				
				//링크드맵 넣고
				DenyVO denyVO3 = new DenyVO();
				denyVO3.setDt_frt(beforeDt_frt);
				denyVO3.setCd_fc(beforeCd_fc);
				denyVO3.setCd_goods(beforeCd_goods);
				denyVO3.setCd_goods_type(beforeCd_goods_type);
				denyVO3.setNm_goods(beforeNm_goods);
				denyVO3.setYn_use(beforeYn_use);
				linkedMap.put(denyVO3, list2);
				
				//넣고 리스트 초기화
				list2 = new ArrayList<DenyVO>();
			}else if(i > 0){
				//리스트 쌓고
				System.out.println("같아서i:" + i + ":" + denyVO2.getCd_fc() + denyVO2.getCd_goods() + ":" + beforeCd_fc + beforeCd_goods);
				DenyVO value = new DenyVO();
				value.setDeny_desc(beforeDeny_desc);
				list2.add(value);
			}

			//맨끝에는 항상 비포가 되고
			beforeDt_frt = denyVO2.getDt_frt();
			beforeCd_fc = denyVO2.getCd_fc();
			beforeCd_goods = denyVO2.getCd_goods();
			beforeCd_goods_type = denyVO2.getCd_goods_type();
			beforeNm_goods = denyVO2.getNm_goods();
			beforeYn_use = denyVO2.getYn_use();
			beforeDeny_desc = denyVO2.getDeny_desc();
			i++;
		}
		System.out.println("마무리" + i + ":" +   beforeCd_fc + beforeCd_goods);
		System.out.println("");
		DenyVO denyVO3 = new DenyVO();
		denyVO3.setDt_frt(beforeDt_frt);
		denyVO3.setCd_fc(beforeCd_fc);
		denyVO3.setCd_goods(beforeCd_goods);
		denyVO3.setCd_goods_type(beforeCd_goods_type);
		denyVO3.setNm_goods(beforeNm_goods);
		denyVO3.setYn_use(beforeYn_use);
		
		DenyVO value = new DenyVO();
		value.setDeny_desc(beforeDeny_desc);
		list2.add(value);
		
		linkedMap.put(denyVO3, list2);

		model.addAttribute("List", list);
		model.addAttribute("linkedMap", linkedMap);
		return "/deny/listDenyDetail";
	}
	
	/**
	 * 고객검색 메인
	 * @param personForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listCustRelMain.crz")
	public String listCustRelMain(PersonForm personForm, Model model) {
		model.addAttribute("personForm", personForm);
		return "/deny/listCustRelMain";
	}
	
	/**
	 * 고객검색 목록
	 * @param personForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listCustRel.crz")
	public String listCustRel(PersonForm personForm, Model model) {
		
		List<PersonVO> list = personManager.listPersonInfo(personForm);
		model.addAttribute("listPerson", list);
		model.addAttribute("personForm", personForm);
		
		return "/deny/listCustRel";
	}
}