package com.ltk.jblog.service;

import java.util.List;

import com.ltk.jblog.vo.PostVo;

public interface PostService {
	public List<PostVo> getList();
	public List<PostVo> getList(int cateNo);
	public PostVo getVo(int postNo);
	public boolean insert(PostVo vo);
}
