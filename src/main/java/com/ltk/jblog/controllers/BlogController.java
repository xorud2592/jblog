package com.ltk.jblog.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltk.jblog.service.BlogService;
import com.ltk.jblog.service.CategoryService;
import com.ltk.jblog.service.PostService;
import com.ltk.jblog.vo.BlogVo;
import com.ltk.jblog.vo.CategoryVo;
import com.ltk.jblog.vo.PostVo;
import com.ltk.jblog.vo.UserVo;

@Controller
public class BlogController {
	private static Logger logger = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String myBlog(HttpSession session) {
		UserVo authUser = null;

		if (session != null) {
			authUser = (UserVo) session.getAttribute("authUser");
		}

		return "redirect:/" + authUser.getUserName();
	}

	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public ModelAndView blog(@RequestParam(value="CateNo" , required=false)String cateNo, @RequestParam(value="PostNo" , required=false)String postNo,HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		String url = request.getServletPath().substring(1, request.getServletPath().length());
		BlogVo blogVo = blogService.getBlog(url);

		
		if (blogVo != null) {
			mav.addObject("blogTitle", blogVo.getBlogTitle());
			
			List<CategoryVo> categoryList = categoryService.getList(blogVo);

			if (authUser == null)
				mav.addObject("userName", "");
			else
				mav.addObject("userName", authUser.getUserName());
			
			mav.addObject("url", url);
			mav.addObject("logoFile", blogVo.getLogoFile());
			mav.addObject("categoryList", categoryList);
			
			List<PostVo> postList = null;

			if(cateNo!=null) {
				postList = postService.getList(Integer.parseInt(cateNo));
			} else {
				CategoryVo cateVo = categoryList.get(0);
				postList = postService.getList(cateVo.getCateNo()); 
			}
			
			String postContent = "";
			
			if(postNo!=null) {
				postContent = postService.getVo(Integer.parseInt(postNo)).getPostContent();
			} else if(postList.size() != 0) {
				postContent = postList.get(0).getPostContent();
			} 
			
			mav.addObject("postList",postList);
			mav.addObject("postContent",postContent);
			
			mav.setViewName("/blog/main");

			return mav;
		}

		mav.setViewName("redirect:/");
		return mav;
	}

	@RequestMapping(value = "*/category/{cateNo}", method = RequestMethod.GET) // Path Variable
	public String serachPost(@PathVariable int cateNo, HttpSession session, HttpServletRequest request,
			RedirectAttributes redirect) {
		String url = request.getServletPath().substring(1, request.getServletPath().length());

		String urls[] = url.split("/");

		for (String a : urls) {
			logger.debug("urls" + a);
		}
		
		redirect.addAttribute("CateNo", Integer.toString(cateNo));

		return "redirect:/" + urls[0];
	}
	
	@RequestMapping(value = "*/post/{postNo}", method = RequestMethod.GET) // Path Variable
	public String viewPost(@PathVariable int postNo, HttpSession session, HttpServletRequest request,
			RedirectAttributes redirect) {
		String url = request.getServletPath().substring(1, request.getServletPath().length());

		String urls[] = url.split("/");

		for (String a : urls) {
			logger.debug("urls" + a);
		}
		
		int cateNo =  postService.getVo(postNo).getCateNo();
		
		redirect.addAttribute("CateNo", Integer.toString(cateNo));
		redirect.addAttribute("PostNo", Integer.toString(postNo));
		
		
		return "redirect:/" + urls[0];
	}

	@RequestMapping(value = "*/admin/basic", method = RequestMethod.GET)
	public ModelAndView blogManagement(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		BlogVo blogVo = blogService.getBlog(authUser.getUserNo());

		mav.addObject("blogTitle", blogVo.getBlogTitle());
		mav.addObject("logoFile", blogVo.getLogoFile());

		mav.setViewName("/blog/admin/basic");

		return mav;
	}

	@RequestMapping(value = "*/admin/basic", method = RequestMethod.POST)
	public String blogManagementBasic(@RequestParam(value = "blogTitle") String blogTitle, MultipartFile file,
			HttpSession session, HttpServletRequest request) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		BlogVo blogVo = blogService.getBlog(authUser.getUserNo());

		String saveDir = request.getSession().getServletContext().getRealPath("/images");

		File dir = new File(saveDir);
		if (!dir.exists()) {
			dir.mkdirs();
			logger.debug("디렉토리 생성");
		}
		logger.debug("메소드 동작 확인");

		if (!file.isEmpty()) {
			String orifileName = file.getOriginalFilename();
			logger.debug("파일이름 " + orifileName);
			String ext = orifileName.substring(orifileName.lastIndexOf("."));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
			String reName = sdf.format(System.currentTimeMillis()) + ext;
			try {
				file.transferTo(new File(saveDir + "/" + reName));
				blogVo.setLogoFile(reName);
				logger.debug("들어감 " + (saveDir + "/" + reName));
			} catch (IllegalStateException | IOException e) {

				e.printStackTrace();
			}
		}

		blogVo.setBlogTitle(blogTitle);
		blogService.update(blogVo);

		return "redirect:/" + authUser.getUserName() + "/admin/basic";
	}
}
