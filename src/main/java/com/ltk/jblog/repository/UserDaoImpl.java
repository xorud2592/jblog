package com.ltk.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ltk.jblog.exception.UserDaoException;
import com.ltk.jblog.vo.UserVo;

@Repository
public class UserDaoImpl implements UserDao {
	private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(UserVo vo) {
		int insertedCount = 0;
		try {
			insertedCount = sqlSession.insert("users.insert", vo);
		} catch (UserDaoException e) {
			logger.error("예외 발생:" + e.getMessage());
			
			throw new UserDaoException("회원 가입 중 오류 발생!", vo);
		}
		return insertedCount;
	}

	@Override
	public UserVo selectUser(String id, String password) {
		Map<String, String> userMap = new HashMap<>();
		userMap.put("id", id);
		userMap.put("password", password);
		
		UserVo vo = sqlSession.selectOne("users.selectUserByEmailAndPassword", userMap);
		return vo;
	}

	@Override
	public UserVo selectUser(String id) {
		UserVo vo = sqlSession.selectOne("users.selectUserById", id);
		return vo;
	}

}
