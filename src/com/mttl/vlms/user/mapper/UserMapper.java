package com.mttl.vlms.user.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.user.dto.User;

/**
 * UserMapper
 * 
 * 
 *
 */
public interface UserMapper {

	User findUser(@Param("userID") String userID);

	int updateRecoveryCode(@Param("userId") Integer userId, @Param("recoveryCode") String recoveryCode, @Param("recoveryCodeTime") Date recoveryCodeTime);
}
