package com.ltk.jblog.repository;

import java.util.List;

import com.ltk.jblog.vo.PostVo;

public interface PostDao {
	public List<PostVo> selectAll();
	public List<PostVo> selectAll(int cateNo);
	public PostVo select(int postNo);
	public int insert(PostVo vo);
}
