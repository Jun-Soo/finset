package com.koscom.person;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyVO;
import com.koscom.apply.service.ApplyManager;
import com.koscom.counsel.service.CounselManager;
import com.koscom.person.model.PersonForm;
import com.koscom.person.model.PersonLoginHistForm;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonManager personManager;

	@Autowired
	private CounselManager counselManager;
	
	@Autowired
	private ApplyManager applyManager;

	
	/**
	 * 고객 리스트 메인
	 * @param personForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listPersonMain.crz")
	public String listPersonMain(PersonForm personForm, Model model) {
		
		logger.debug("고객리스트 메인");
		
		return "/person/listPersonMain";
	}
	
	/**
	 * 고객 리스트 조회
	 * @param personForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listPersonInfo.crz")
	public String listPersonInfo(PersonForm personForm, Model model) {
		
		
		Pagination pagedList = (Pagination) personForm.setPagedList(personManager.listPersonInfoPg(personForm), personManager.listPersonCount(personForm));
		model.addAttribute("pagedList", pagedList);
		
		return "/person/listPersonInfo";
	}	
	
	/**
	 * 고객정보 조회 메인
	 * @param personForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/formPersonMain.crz")
	public String formPersonMain(PersonForm personForm, Model model) {
		PersonVO personVO = personManager.getPersonInfo(personForm.getNo_person());
		model.addAttribute("person", personVO);
//		접속로그
		personForm.setPage(personForm.getPage());
		PersonLoginHistForm personLoginHistForm = new PersonLoginHistForm();
		personLoginHistForm.setPage(personLoginHistForm.getPage());
		personLoginHistForm.setSel_detail("PH.no_person");
		personLoginHistForm.setTxt_detail(personForm.getNo_person());
		Pagination pagedList1 = (Pagination) personLoginHistForm.setPagedList(personManager.listPersonLoginHist(personLoginHistForm), personManager.listPersonLoginHistCount(personLoginHistForm));
		model.addAttribute("pagedList1", pagedList1);
//		활동로그
		Pagination pagedList2 = (Pagination) personForm.setPagedList(personManager.listPersonActiveHistInfoPg(personForm), personManager.listPersonActiveHistCount(personForm));
		model.addAttribute("pagedList2", pagedList2);
//		실행이력
		ApplyForm applyForm = new ApplyForm();
		applyForm.setPage(applyForm.getPage());
		applyForm.setSel_detail("AP.no_person");
		applyForm.setTxt_detail(personForm.getNo_person());
		applyForm.setCd_apply_doc_box1("10");
		applyForm.setCd_apply_doc_box2("50");
		applyForm.setCd_apply_doc_box3("60");
		Pagination pagedList3 = (Pagination) applyForm.setPagedList(applyManager.listPastInfoPg(applyForm), applyManager.listPastPgCount(applyForm));
		model.addAttribute("pagedList3", pagedList3);
//		조회이력
		ApplyForm applyForm2 = new ApplyForm();
		applyForm2.setPage(applyForm2.getPage());
		applyForm2.setNo_person(personForm.getNo_person());
		
		List<ApplyVO> pagedList4 = null;
		pagedList4 = applyManager.listLookupInfoPg(applyForm2);
//		for (ApplyVO applyVO : pagedList4) {
//		}
		model.addAttribute("pagedList4", pagedList4);
//		상담이력
		
// TODO  우선 주석처리 상담톡 때문에 리스트 안나오게  2017/08/31
//		CounselForm counselForm = new CounselForm();
//		counselForm.setNo_person(personForm.getNo_person());
//		counselForm.setPage(counselForm.getPage());
//		Pagination pagedList5 = (Pagination) counselForm.setPagedList(counselManager.listCounselHist(counselForm), counselManager.listCounselHistCount(counselForm));
//		model.addAttribute("pagedList5", pagedList5);
//		personForm.setYn_modal("Y");
//		model.addAttribute("personForm", personForm);
//		model.addAttribute("no_person", personForm.getNo_person());
		
		return "/person/formPersonMain";
	}
	
	/**
	 * 조회이력 리스트
	 * @param request
	 * @param personVO
	 * @param model
	 * @return
	 */
	
	
	@RequestMapping("/listPastMain.crz") //2017.06.20 ADD
	public String listPastMain(HttpServletRequest request, ApplyForm applyForm, Model model, HttpSession session) {
		logger.info("applyForm no_person : "+applyForm.getNo_person());
		
		PersonForm personForm = new PersonForm();
		applyForm.setNo_person(personForm.getNo_person());

		Pagination pagedList4 = (Pagination) applyForm.setPagedList(applyManager.listPastInfoPg(applyForm), applyManager.listPastPgCount(applyForm));
		model.addAttribute("pagedList4", pagedList4);
		
		return "/person/listLookupHist";
	}
	
	
	/**
	 * 사용여부 수정
	 * @param request
	 * @param personVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyYnUse.json")
	public String modifyYnUse(HttpServletRequest request, PersonVO personVO, Model model) {
		logger.info("personVO ==========================");
		logger.info(personVO.toString());
		
		PersonVO vo = new PersonVO();
		vo = personManager.getPersonInfo(personVO.getNo_person());
		logger.info("vo.getYn_use() : "+vo.getYn_use());
		logger.info("personVO.getYn_use() : "+personVO.getYn_use());
		if(vo.getYn_use().equals(personVO.getYn_use())){
			model.addAttribute("message", "사용여부 값이 동일 합니다.");
			model.addAttribute("result", "05");
			return "jsonView";
		}
		
		SessionUtil session = new SessionUtil(request);
		personVO.setId_lst(session.getUserId());
		ReturnClass returnClass = personManager.modifyYnUse(personVO); 
		model.addAttribute("returnData", returnClass);
		return "jsonView";
	}
	
	
	/**
	 * 자동완성
	 * @param form
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listPersonInfoAuto.json")
	public String listPersonInfoAuto(PersonForm form, Model model) throws Exception {
		logger.info("input txt_detail=[" + form.getTxt_detail() + "]");
		ReturnClass rc = personManager.listPersonInfoJson(form);
		model.addAttribute("data", (JSONArray)rc.getReturnObj());
		logger.info("output=[" + (JSONArray)rc.getReturnObj() + "]" );
		return "jsonView";
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
		
		return "/person/listCustRel";
	}
	
	/**
	 * 고객 탈퇴 관리 메인
	 * @return
	 */
	@RequestMapping("/listPersonQuitMain.crz")
	public String listPersonQuitMain(){
		
		logger.debug("고객 탈퇴 리스트 메인");
		
		return "/person/listPersonQuitMain";
	}
	
	/**
	 * 고객 탈퇴 관리 리스트
	 * @param personForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listPersonQuitInfo.crz")
	public String listPersonQuitInfo(PersonForm personForm, Model model){
		
		Pagination pagedList = (Pagination) personForm.setPagedList(personManager.listPersonQuitInfoPg(personForm), personManager.listPersonQuitCount(personForm));
		model.addAttribute("pagedList", pagedList);
		
		return "/person/listPersonQuitInfo";
	}
	
	/**
	 * 회원 접속 이력 리스트 메인
	 * @param personForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listPersonLoginHistMain.crz")
	public String listPersonLoginHistMain(){
		return "/person/listPersonLoginHistMain";
	}
	
	/**
	 * 회원 접속 이력 리스트
	 * @param personForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listPersonLoginHist.crz")
	public String listPersonLoginHist(Model model, PersonLoginHistForm personLoginHistForm){
		Pagination pagedList = (Pagination) personLoginHistForm.setPagedList(personManager.listPersonLoginHist(personLoginHistForm), personManager.listPersonLoginHistCount(personLoginHistForm));
		model.addAttribute("pagedList", pagedList);
		
		return "/person/listPersonLoginHist";
	}
	
//	/**
//	 * 프로필 사진 등록/수정 
//	 * @param personNiceVO
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("/profileImg.json")
//	public String profileImg(HttpServletRequest request, PersonVO personVO, Model model) {
////		SessionUtil session = new SessionUtil(request);
//		
//		logger.info("============personVO확인=============");
//		logger.info(personVO.toString());
//		
//		MultipartFile file1 = personVO.getFile1();
//		try {
//			if(file1 != null){
//			
//				byte[] fileArray = file1.getBytes();
//				int fileSize = fileArray.length;
//				String fileName = file1.getOriginalFilename();
//				
//				
//				if(fileArray != null && fileSize > 0){
//					personVO.setFileArray(fileArray);
//					personVO.setFileName(fileName);
//					personVO.setFileSize(fileSize);
//					logger.info("fileName->"+fileName);
//					logger.info("fileSize->"+fileSize);
//					
//				}
//			}
//			personVO.setFile1(null);
//
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		ReturnClass returnClass = personManager.profileImg(personVO);
//		model.addAttribute("returnData", returnClass);
//		return "jsonView";
//	}
	
}