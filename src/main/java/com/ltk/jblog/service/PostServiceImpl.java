package com.ltk.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltk.jblog.repository.PostDao;
import com.ltk.jblog.vo.PostVo;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostDao postDao;

	@Override
	public List<PostVo> getList() {
		List<PostVo> list = postDao.selectAll();
		return list;
	}

	@Override
	public List<PostVo> getList(int cateNo) {
		List<PostVo> list = postDao.selectAll(cateNo);
		return list;
	}
	
	@Override
	public PostVo getVo(int postNo) {
		PostVo vo = postDao.select(postNo);
		return vo;
	}

	@Override
	public boolean insert(PostVo vo) {
		int insertedCount = postDao.insert(vo);
		return 1 == insertedCount;
	}

}
