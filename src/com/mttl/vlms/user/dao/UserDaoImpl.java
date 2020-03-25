package com.mttl.vlms.user.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.user.mapper.UserMapper;

/**
 * UserDaoImpl
 * 
 * 
 *
 */
@Repository("UserDao")
public class UserDaoImpl extends BasicDAO implements UserDao {
	@Autowired
	private UserMapper userMapper;

	@ApplyAspect
	@Override
	public User findUser(String userId) throws DAOException {
		User result;
		try {
			result = userMapper.findUser(userId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int updateRecoveryCode(Integer userId, String recoveryCode, Date recoveryCodeTime) throws DAOException {
		int result;
		try {
			result = userMapper.updateRecoveryCode(userId, recoveryCode, recoveryCodeTime);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}
}
