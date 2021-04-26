package com.ltk.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ltk.jblog.vo.BlogVo;
import com.ltk.jblog.vo.CategoryVo;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<CategoryVo> selectAll() {
		List<CategoryVo> list = sqlSession.selectList("category.selectAll");
		return list;
	}
	
	@Override
	public List<CategoryVo> selectAll(BlogVo vo) {
		List<CategoryVo> list = sqlSession.selectList("category.selectAllByBlog",vo);
		return list;
	}
	
	@Override
	public CategoryVo selectByCateNo(int cateNo) {
		CategoryVo vo = sqlSession.selectOne("category.selectByCateNo",cateNo);
		return vo;
	}
	
	@Override
	public int insert(CategoryVo vo) {
		int insertedCount = sqlSession.insert("category.insert", vo);
		return insertedCount;
	}

	@Override
	public int delete(int cateNo) {
		int deletedCount = sqlSession.delete("category.delete", cateNo);
		return deletedCount;
	}

}
