package com.ltk.jblog.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ltk.jblog.service.BlogService;
import com.ltk.jblog.service.CategoryService;
import com.ltk.jblog.service.PostService;
import com.ltk.jblog.vo.BlogVo;
import com.ltk.jblog.vo.CategoryVo;
import com.ltk.jblog.vo.UserVo;

@Controller
public class CategoryController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "*/admin/category", method = RequestMethod.GET)
	public ModelAndView categoryManagement(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		BlogVo blogVo = blogService.getBlog(authUser.getUserNo());

		List<CategoryVo> list = categoryService.getList();
		
		for(CategoryVo vo: list) {
			vo.setPostSize(postService.getList( vo.getCateNo()).size());
		}
		mav.addObject("blogTitle", blogVo.getBlogTitle());
		mav.addObject("list", list);
		
		
		
		mav.setViewName("/blog/admin/category");

		return mav;
	}
	
	@RequestMapping(value="admin/delete/category/{cateNo}",
			method=RequestMethod.GET)	//	Path Variable
	public String deleteForm(@PathVariable int cateNo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boolean success = categoryService.delete(cateNo);
		logger.debug("Delete Result:" + success);
		return "redirect:/" + authUser.getUserName() + "/admin/category";
	}
	
	
	
	@RequestMapping(value = "*/admin/category", method = RequestMethod.POST)
	public String categoryManagement(@RequestParam(value = "cateName") String cateName, @RequestParam(value = "description") String description, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		CategoryVo categoryVo = new CategoryVo();

		categoryVo.setUserNo(authUser.getUserNo());
		categoryVo.setCateName(cateName);
		categoryVo.setDescription(description);

		categoryService.write(categoryVo);
		
		return "redirect:/" + authUser.getUserName() + "/admin/category";
	}
}
