package com.ltk.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ltk.jblog.exception.BlogDaoException;
import com.ltk.jblog.vo.BlogVo;

@Repository
public class BlogDaoImpl implements BlogDao {

	private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(BlogVo vo) {
		int insertedCount = 0;
		try {
			logger.debug("blogvo값 :" + vo );
			insertedCount = sqlSession.insert("blog.insert", vo);
		} catch (BlogDaoException e) {
			logger.error("예외 발생:" + e.getMessage());

			throw new BlogDaoException("블로그 생성 중 오류 발생!", vo);
		}
		return insertedCount;
	}

	@Override
	public BlogVo selectBlog(int userNo) {
		BlogVo vo = sqlSession.selectOne("blog.selectBlogByUserNo", userNo);
		return vo;
	}

	@Override
	public BlogVo selectBlog(String url) {
		BlogVo vo = sqlSession.selectOne("blog.selectBlogByUrl", url);
		return vo;
	}

	@Override
	public int update(BlogVo blogVo) {
		int updateCount = sqlSession.update("blog.updateBlogByTitleLogo", blogVo);
		return updateCount;
	}
}
