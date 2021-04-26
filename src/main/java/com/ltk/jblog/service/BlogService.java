package com.ltk.jblog.service;

import com.ltk.jblog.vo.BlogVo;
import com.ltk.jblog.vo.UserVo;

public interface BlogService {
	public boolean join(UserVo vo);

	public BlogVo getBlog(int userNo);

	public BlogVo getBlog(String logoFile);

	public boolean update(BlogVo blogVo);
}
