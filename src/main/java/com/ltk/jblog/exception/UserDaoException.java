package com.ltk.jblog.exception;

import com.ltk.jblog.vo.UserVo;

public class UserDaoException extends RuntimeException{
	private UserVo memberVo = null;
	
	public UserDaoException() {
		
	}
	
	public UserDaoException(String message) {
		super(message);
	}
	
	public UserDaoException(String message, UserVo memberVo) {
		super(message);
		this.memberVo = memberVo;
	}

	public UserVo getMemberVo() {
		return memberVo;
	}

	public void setMemberVo(UserVo memberVo) {
		this.memberVo = memberVo;
	}
	
	
}
