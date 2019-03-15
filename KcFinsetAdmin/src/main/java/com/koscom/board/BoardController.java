package com.koscom.board;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.koscom.board.model.BoardForm;
import com.koscom.board.model.BoardInfoVO;
import com.koscom.board.service.BoardManager;
import com.koscom.domain.BoardComment;
import com.koscom.domain.BoardInfo;
import com.koscom.domain.BoardManage;
import com.koscom.util.FinsetException;
import com.koscom.util.LogUtil;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardManager boardManager;

	/**
	 * 게시판 관리 메인
	 * @param boardForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listBoardManageMain.crz")
	public String listBoardManageMain(BoardForm boardForm, Model model){

		model.addAttribute("listBoardManage", boardManager.listBoardManage(boardForm));
		return "/board/listBoardManageMain";
	}

	/**
	 * 게시판 관리 리스트
	 * @param boardForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listBoardManage.crz")
	public String listBoardManage(BoardForm boardForm, Model model){

		model.addAttribute("listBoardManage", boardManager.listBoardManage(boardForm));
		return "/board/listBoardManage";
	}

	/**
	 * 게시판 생성, 수정
	 * @param request
	 * @param agencyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/procBoardManage.json")
	public String procBoardManage(HttpServletRequest request, BoardManage boardManage, Model model) {
		SessionUtil session = new SessionUtil(request);

		boardManage.setId_frt(session.getUserId());
		boardManage.setId_lst(session.getUserId());

		ReturnClass returnClass = boardManager.procBoardManage(boardManage);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 게시판 정보 조회
	 * @param boardManage
	 * @param model
	 * @return
	 */
	@RequestMapping("/getBoardManage.crz")
	public String getBoardManage(BoardManage boardManage, Model model) {

		BoardManage boardManageInfo = boardManager.getBoardManage(boardManage);
		model.addAttribute("boardManage", boardManageInfo);

		return "/board/formBoardManage";
	}

	/**
	 * 게시판 조회(게시판별 리스트) 메인
	 * @param boardForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listBoardInfoMain.crz")
	public String listBoardInfoMain(BoardForm boardForm, Model model) {

		//공지사항 게시판
		boardForm.setId_board("notice");
		Pagination pagedList = (Pagination) boardForm.setPagedList(boardManager.listBoardInfo(boardForm), boardManager.listBoardInfoCount(boardForm));
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("boardForm", boardForm);

		return "/board/listBoardInfoMain";
	}

	/**
	 * 게시판 조회(게시판별 리스트)
	 * @param boardForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listBoardInfo.crz")
	public String listBoardInfo(BoardForm boardForm, Model model) {
		Pagination pagedList = (Pagination) boardForm.setPagedList(boardManager.listBoardInfo(boardForm), boardManager.listBoardInfoCount(boardForm));
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("boardForm", boardForm);

		return "/board/listBoardInfo";
	}

	/**
	 * 게시판 글쓰기 폼
	 * @param boardInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/formBoardInfo.crz")
	public String formBoardInfo(BoardInfo boardInfo, Model model, HttpServletResponse response){

		BoardInfoVO boardVO = boardManager.getBoardInfo(boardInfo);
		
		model.addAttribute("boardInfo", boardInfo);
		model.addAttribute("boardVO", boardVO);

		return "/board/formBoardInfo";
	}

	/**
	 * 게시글 등록
	 * @param boardVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/createBoardInfo.json")
	public String createBoardInfo(HttpServletRequest request, BoardInfoVO boardVO, Model model) throws FinsetException, IOException {
		SessionUtil session = new SessionUtil(request);
		ReturnClass returnClass = null;
		boardVO.setId_frt(session.getUserId());
		
		MultipartFile file1 = boardVO.getImgfiles1();
		MultipartFile file2 = boardVO.getImgfiles2();
		
		try {
			if(file1 != null) {
				byte[] fileArray = file1.getBytes();
				int fileSize = fileArray.length;
				String fileName = file1.getOriginalFilename();
				logger.info("fileName1->"+fileName);
				logger.info("fileArray1->"+fileArray);
				logger.info("fileSize1->"+fileSize);

				if(fileArray != null && fileSize > 0){
					boardVO.setImg_files1(fileArray);
					boardVO.setNm_img_files1(fileName);
				}
				boardVO.setImgfiles1(null);
			}
			
			if(file2 != null) {
				byte[] fileArray = file2.getBytes();
				int fileSize = fileArray.length;
				String fileName = file2.getOriginalFilename();
				logger.info("fileName2->"+fileName);
				logger.info("fileArray2->"+fileArray);
				logger.info("fileSize2->"+fileSize);

				if(fileArray != null && fileSize > 0){
					boardVO.setImg_files2(fileArray);
					boardVO.setNm_img_files2(fileName);
				}
				boardVO.setImgfiles2(null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
            LogUtil.error(logger,e);
		}
		
		returnClass = boardManager.createBoardInfo(boardVO);
		model.addAttribute("result", returnClass.getReturnObj());

		return "/comm/ajaxResult";
	}

	/**
	 * 게시글 수정
	 * @param request
	 * @param boardInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyBoardInfo.json")
	public String modifyBoardInfo(HttpServletRequest request, BoardInfoVO boardVO, Model model) throws FinsetException, IOException {

		SessionUtil session = new SessionUtil(request);
		ReturnClass returnClass = null;
		boardVO.setId_frt(session.getUserId());
			//본인만 수정가능
			if(boardVO.getId_frt().equals(session.getUserId())){
				
				MultipartFile file1 = boardVO.getImgfiles1();
				MultipartFile file2 = boardVO.getImgfiles2();
				try {
					if(file1 != null) {
						byte[] fileArray = file1.getBytes();
						int fileSize = fileArray.length;
						String fileName = file1.getOriginalFilename();
						logger.info("fileName1->"+fileName);
						logger.info("fileArray1->"+fileArray);
						logger.info("fileSize1->"+fileSize);

						if(fileArray != null && fileSize > 0){
							boardVO.setImg_files1(fileArray);
							boardVO.setNm_img_files1(fileName);
						}
						boardVO.setImgfiles1(null);
					}
					
					if(file2 != null) {
						byte[] fileArray = file2.getBytes();
						int fileSize = fileArray.length;
						String fileName = file2.getOriginalFilename();
						logger.info("fileName2->"+fileName);
						logger.info("fileArray2->"+fileArray);
						logger.info("fileSize2->"+fileSize);

						if(fileArray != null && fileSize > 0){
							boardVO.setImg_files2(fileArray);
							boardVO.setNm_img_files2(fileName);
						}
						boardVO.setImgfiles2(null);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
		            LogUtil.error(logger,e);
				}
			
				
				boardVO.setId_lst(session.getUserId());
				returnClass = boardManager.modifyBoardInfo(boardVO);
				model.addAttribute("result", returnClass.getReturnObj());
			}else {
				model.addAttribute("result", "");
			}
		return "/comm/ajaxResult";
	}

	/**
	 * 게시글 보기
	 * @param request
	 * @param boardInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewBoardInfo.crz")
	public String viewBoardInfo(BoardInfo boardInfo, Model model){

		//조회수 업데이트
		boardManager.updateBoardInfoHit(boardInfo.getSeq());

		//게시글 정보
		BoardInfoVO boardInfoVO = boardManager.getBoardInfo(boardInfo);
		model.addAttribute("boardVO", boardInfoVO);
		
		//이미지 정보
		if("event".equals(boardInfoVO.getId_board())) {
			boardInfoVO.setFile_type("02");
			BoardInfoVO boardImgInfo = boardManager.getBoardFileInfo(boardInfoVO);
			model.addAttribute("boardImgInfo", boardImgInfo);
		}
		
		//댓글 리스트
		List<BoardComment> boardComment = boardManager.listBoardComment(boardInfo.getSeq());
		model.addAttribute("listBoardComment", boardComment);

		return "/board/viewBoardInfo";
	}

	/**
	 * 게시글 삭제
	 * @param boardInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/delBoardInfo.json")
	public String delBoardInfo(HttpServletRequest request, BoardInfo boardInfo, Model model){
		SessionUtil session = new SessionUtil(request);

		//본인글만 삭제가능
		if(boardInfo.getId_frt().equals(session.getUserId())){
			boardInfo.setId_lst(session.getUserId());
			ReturnClass returnClass = boardManager.delBoardInfo(boardInfo);
			model.addAttribute("returnData", returnClass);
		}else model.addAttribute("returnData", "");

		return "jsonView";
	}

	/**
	 * 댓글 등록
	 * @param request
	 * @param boardComment
	 * @param model
	 * @return
	 */
	@RequestMapping("/createBoardComment.json")
	public String createBoardComment(HttpServletRequest request, BoardComment boardComment, Model model){
		SessionUtil session = new SessionUtil(request);

		boardComment.setId_frt(session.getUserId());
		ReturnClass returnClass = boardManager.createBoardComment(boardComment);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 댓글 정보 조회
	 * @param request
	 * @param boardComment
	 * @param model
	 * @return
	 */
	@RequestMapping("/getBoardComment.crz")
	public String getBoardComment(BoardComment boardComment, Model model){

		model.addAttribute("boardComment", boardManager.getBoardComment(boardComment.getSeq()));

		return "/board/formBoardComment";
	}

	/**
	 * 댓글 수정
	 * @param request
	 * @param boardComment
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyBoardComment.json")
	public String modifyBoardComment(HttpServletRequest request, BoardComment boardComment, Model model){
		SessionUtil session = new SessionUtil(request);

		boardComment.setId_lst(session.getUserId());
		ReturnClass returnClass = boardManager.modifyBoardComment(boardComment);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 댓글 삭제
	 * @param request
	 * @param boardComment
	 * @param model
	 * @return
	 */
	@RequestMapping("/delBoardComment.json")
	public String delBoardComment(HttpServletRequest request, BoardComment boardComment, Model model){
		SessionUtil session = new SessionUtil(request);

		boardComment.setId_lst(session.getUserId());
		ReturnClass returnClass = boardManager.delBoardComment(boardComment.getSeq());
		returnClass.setReturnObj(boardComment.getSeq_board());
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}
	
	/* srchou */
	/**
	 * BLOB 이미지 변환
	 * @param String
	 * @return ResponseEntity<byte[]>
	 */
	@RequestMapping("/getBoardImg.crz")
	public void getBoardImg(HttpServletRequest request, HttpServletResponse response, BoardInfoVO boardInfoVO){
		
		byte[] img_files = null;
		Map<String, Object> map = null;
		
		boardInfoVO.setSeq(request.getParameter("seq").toString());
		boardInfoVO.setFile_type(request.getParameter("file_type").toString());
		boardInfoVO.setId_board(request.getParameter("id_board").toString());
		map = boardManager.getBoardImg(boardInfoVO);

		if(map != null) {
			img_files =  (byte[]) map.get("img_files");
			
			logger.info("blob data chekc : " +img_files);
			response.setContentType("image/jpeg");
			InputStream inputS = new ByteArrayInputStream(img_files);
			try {
				IOUtils.copy(inputS, response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	/**
//	 * BLOB 이미지 DOWN
//	 * @param String
//	 * @return ResponseEntity<byte[]>
//	 */
//	@RequestMapping("/downBoardImg.crz")
//    public void downFinCorpIcon(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		String seq = request.getParameter("seq");
//		
//		byte[] img_files = null;
//		String nm_img_files = null;
//		img_files = (byte[])map.get("img_files");
//		nm_img_files = (String)map.get("nm_img_files");
//		
//		OutputStream outputS = null;
//		outputS = response.getOutputStream();
//		
//		response.setContentType("application/octet-stream");
//		response.setHeader("Content-disposition", "attachment;filename="+nm_img_files);
//		response.setContentLength(img_files.length);
//		try {
//			outputS.write(img_files);
//			outputS.flush();
//			outputS.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//       
//	}

}
