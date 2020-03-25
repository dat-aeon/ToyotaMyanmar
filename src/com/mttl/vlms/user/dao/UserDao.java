package com.mttl.vlms.user.dao;

import java.util.Date;

import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.user.dto.User;

/**
 * UserDao
 * 
 *  
 *
 */
public interface UserDao {

	User findUser(String userId) throws DAOException;

	int updateRecoveryCode(Integer userId, String recoveryCode, Date recoveryCodeTime) throws DAOException;
}
