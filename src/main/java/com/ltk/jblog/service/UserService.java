package com.ltk.jblog.service;

import com.ltk.jblog.vo.UserVo;

public interface UserService {
	public boolean join(UserVo vo);
	public UserVo getUser(String id, String password);
	public UserVo getUser(String id);
	
}
