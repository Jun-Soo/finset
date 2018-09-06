package com.koscom.board;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.board.model.BoardInfoVO;
import com.koscom.board.service.BoardManager;


@Controller
@RequestMapping("/m/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardManager boardManager;

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
}
