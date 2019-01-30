package com.koscom.news;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.news.model.NewsForm;
import com.koscom.news.model.NewsVO;
import com.koscom.news.service.NewsManager;
import com.koscom.util.FinsetException;
import com.koscom.util.Pagination;


@Controller
@RequestMapping("/m/news")
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private NewsManager newsManager;

	/**
	 * VUE
     * 뉴스 메인 list
     * @param request
     * @param session
     * @param NewsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/listNews.json")
	public String listNews(
	HttpServletRequest request,
	HttpSession session,
	NewsForm newsForm,
	Model model) throws FinsetException, IOException{

		Pagination pagedList = newsForm.setPagedList(newsManager.listApiNews(newsForm),newsManager.listApiNewsCount(newsForm));
		model.addAttribute("pagedList", pagedList);

		return "jsonView";
	}

	/**
	 * VUE
	 * 기사등록 이미지 불러오기(썸네일, 본문)
	 * @param String
	 * @return ResponseEntity<byte[]>
	 */
	@RequestMapping("/getApiNewsImg.json")
	public void getApiNewsImg(HttpServletRequest request, HttpServletResponse response, NewsVO newsVO){

		byte[] img_files = null;
		Map<String, Object> map = null;

		newsVO.setSeq_news(Integer.parseInt(request.getParameter("seq_news").toString()));
		newsVO.setFile_type(request.getParameter("file_type").toString());
		if("02".equals(newsVO.getFile_type())) {
			String seq_file = request.getParameter("seq_file").toString();
			newsVO.setSeq_file(Integer.parseInt(seq_file));
		}
		map = newsManager.getApiNewsImgInfo(newsVO);

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

	/**
	 * VUE
     * 뉴스 상세
     * @param request
     * @param session
     * @param NewsVO
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getNewsInfo.json")
	public String getNewsInfo(
	HttpServletRequest request,
	HttpSession session,
	NewsVO newsVO,
	Model model) throws FinsetException, IOException{

		model.addAttribute("newsInfo", newsManager.getApiNewsInfo(newsVO));

		return "jsonView";
	}

}
