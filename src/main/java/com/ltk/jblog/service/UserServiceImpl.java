package com.ltk.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltk.jblog.repository.UserDao;
import com.ltk.jblog.vo.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public boolean join(UserVo vo) {
		int insertedCount = userDao.insert(vo);
		return insertedCount == 1;
	}

	@Override
	public UserVo getUser(String id, String password) {
		UserVo vo = userDao.selectUser(id, password);
		return vo;
	}

	@Override
	public UserVo getUser(String id) {
		UserVo vo = userDao.selectUser(id);
		return vo;
	}

}
