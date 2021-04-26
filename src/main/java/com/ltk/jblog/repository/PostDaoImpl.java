package com.ltk.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ltk.jblog.vo.PostVo;

@Repository
public class PostDaoImpl implements PostDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<PostVo> selectAll() {
		List<PostVo> list = sqlSession.selectList("post.selectAll");
		return list;
	}
	
	@Override
	public List<PostVo> selectAll(int cateNo) {
		List<PostVo> list = sqlSession.selectList("post.selectAllBycateNo",cateNo);
		return list;
	}

	@Override
	public PostVo select(int postNo) {
		PostVo vo = sqlSession.selectOne("post.selectByPostNo",postNo);
		return vo;
	}


	@Override
	public int insert(PostVo vo) {
		int insertedCount = sqlSession.insert("post.insert", vo);
		return insertedCount;
	}

}
