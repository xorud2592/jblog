package com.ltk.jblog.repository;

import java.util.List;

import com.ltk.jblog.vo.BlogVo;
import com.ltk.jblog.vo.CategoryVo;

public interface CategoryDao {
	public List<CategoryVo> selectAll();
	public List<CategoryVo> selectAll(BlogVo vo);
	public CategoryVo selectByCateNo(int cateNo);
	public int insert(CategoryVo vo);
	public int delete(int cateNo);
}
