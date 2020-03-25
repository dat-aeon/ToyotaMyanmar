package com.mttl.vlms.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.user.dao.UserDao;
import com.mttl.vlms.user.dto.User;

/**
 * UserServiceImpl
 * 
 *  
 *
 */
@Service("UserService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@ApplyAspect
	@Override
	public User findUser(String userId) throws SystemException {
		User user;
		try {
			user = userDao.findUser(userId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return user;
	}

	@ApplyAspect
	@Override
	public int updateRecoveryCode(Integer userId, String recoveryCode, Date recoveryCodeTime) throws SystemException {
		int result;
		try {
			result = userDao.updateRecoveryCode(userId, recoveryCode, recoveryCodeTime);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}
}
