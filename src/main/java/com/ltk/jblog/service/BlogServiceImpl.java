package com.ltk.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltk.jblog.repository.BlogDao;
import com.ltk.jblog.vo.BlogVo;
import com.ltk.jblog.vo.UserVo;

@Service("blogService")
public class BlogServiceImpl implements BlogService{
	@Autowired
	BlogDao blogDao;
	
	@Override
	public boolean join(UserVo vo) {
		BlogVo blogVo = new BlogVo();
		blogVo.setUserNo(vo.getUserNo());
		blogVo.setBlogTitle(vo.getUserName()+"의 블로그 입니다.");
		blogVo.setLogoFile("");
		
		int insertedCount = blogDao.insert(blogVo);
		return insertedCount == 1;
	}

	@Override
	public BlogVo getBlog(int userNo) {
		BlogVo vo = blogDao.selectBlog(userNo);
		return vo;
	}

	@Override
	public BlogVo getBlog(String url) {
		BlogVo vo = blogDao.selectBlog(url);
		return vo;
	}

	@Override
	public boolean update(BlogVo blogVo) {
		int updatedCount = blogDao.update(blogVo);
		return 1 == updatedCount;
	}
}
