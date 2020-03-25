package com.mttl.vlms.user.mapper;

import com.mttl.vlms.user.dto.LoginUser;

/**
 * SecurityMapper
 * 
 * 
 *
 */
public interface SecurityMapper {
	LoginUser loadUserByLoginId(String loginId);
}