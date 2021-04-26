package com.ltk.jblog.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ltk.jblog.service.BlogService;
import com.ltk.jblog.service.CategoryService;
import com.ltk.jblog.service.PostService;
import com.ltk.jblog.vo.BlogVo;
import com.ltk.jblog.vo.CategoryVo;
import com.ltk.jblog.vo.PostVo;
import com.ltk.jblog.vo.UserVo;

@Controller
public class PostController {
	private static Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "*/admin/write", method = RequestMethod.GET)
	public ModelAndView categoryManagement(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		BlogVo blogVo = blogService.getBlog(authUser.getUserNo());

		List<CategoryVo> list = categoryService.getList();
		
		mav.addObject("blogTitle", blogVo.getBlogTitle());
		mav.addObject("list", list);
		
		mav.setViewName("/blog/admin/post");

		return mav;
	}
	
	@RequestMapping(value = "*/admin/write", method = RequestMethod.POST)
	public String categoryManagement(@RequestParam(value = "postTitle") String postTitle, @RequestParam(value = "cateNo") int cateNo, @RequestParam(value = "postContent") String postContent, HttpSession session) {
		
		logger.debug("postTitle : "+postTitle+"\n postContent:"+postContent + " cateNo: " +cateNo);
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		PostVo postVo = new PostVo();

		postVo.setCateNo(cateNo);
		postVo.setPostTitle(postTitle);
		postVo.setPostContent(postContent);

		postService.insert(postVo);
		
		return "redirect:/" + authUser.getUserName() + "/admin/write";
	}
}
