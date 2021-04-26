package com.ltk.jblog.exception;

import com.ltk.jblog.vo.BlogVo;

public class BlogDaoException extends RuntimeException{
	private BlogVo blogVo = null;
	
	public BlogDaoException() {
		
	}
	
	public BlogDaoException(String message) {
		super(message);
	}
	
	public BlogDaoException(String message, BlogVo blogVo) {
		super(message);
		this.blogVo = blogVo;
	}

	public BlogVo getBlogVo() {
		return blogVo;
	}

	public void setBlogVo(BlogVo blogVo) {
		this.blogVo = blogVo;
	}
}
