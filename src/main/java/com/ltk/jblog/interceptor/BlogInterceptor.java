package com.ltk.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ltk.jblog.vo.UserVo;

public class BlogInterceptor extends HandlerInterceptorAdapter{
	private static Logger logger = 
			LoggerFactory.getLogger(BlogInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("BlogInterceptor");
		HttpSession session = request.getSession();
		UserVo authUser = null;
		
		if(session !=null) {
			authUser = (UserVo) session.getAttribute("authUser");
		}
		
		if(session ==null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		return true;
	}
	
	
}
