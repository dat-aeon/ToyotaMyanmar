package com.mttl.vlms.user.service;

import java.util.Date;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.user.dto.User;

/**
 * UserService
 * 
 *  
 *
 */
public interface UserService {

	User findUser(String userId) throws SystemException;

	int updateRecoveryCode(Integer userId, String recoveryCode, Date recoveryCodeTime) throws SystemException;
}
