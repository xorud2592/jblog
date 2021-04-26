package com.ltk.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltk.jblog.repository.CategoryDao;
import com.ltk.jblog.vo.BlogVo;
import com.ltk.jblog.vo.CategoryVo;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryDaoImpl;
	
	@Override
	public List<CategoryVo> getList() {
		List<CategoryVo> list = categoryDaoImpl.selectAll();
		return list;
	}
	
	@Override
	public List<CategoryVo> getList(BlogVo vo) {
		List<CategoryVo> list = categoryDaoImpl.selectAll(vo);
		return list;
	}
	
	@Override
	public CategoryVo getVo(int cateNo) {
		CategoryVo vo = categoryDaoImpl.selectByCateNo(cateNo);
		return vo;
	}

	@Override
	public boolean write(CategoryVo vo) {
		int insertedCount = categoryDaoImpl.insert(vo);
		return 1 == insertedCount;
	}

	@Override
	public boolean delete(int cateNo) {
		int deletedCount = categoryDaoImpl.delete(cateNo);
		return 1 == deletedCount;
	}
}
