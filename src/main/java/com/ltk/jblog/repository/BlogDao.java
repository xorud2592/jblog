package com.ltk.jblog.repository;

import com.ltk.jblog.vo.BlogVo;

public interface BlogDao {
	public int insert(BlogVo vo);
	public BlogVo selectBlog(int userNo);
	public BlogVo selectBlog(String url);
	public int update(BlogVo blogVo);
}
