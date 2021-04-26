package com.ltk.jblog.service;

import java.util.List;

import com.ltk.jblog.vo.BlogVo;
import com.ltk.jblog.vo.CategoryVo;

public interface CategoryService {
	public List<CategoryVo> getList();
	public List<CategoryVo> getList(BlogVo vo);
	public CategoryVo getVo(int cateNo);
	public boolean write(CategoryVo vo);
	public boolean delete(int cateNo);
}
