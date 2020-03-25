package com.mttl.vlms.setting.stt013.service;

import java.util.List;

import com.mttl.vlms.common.selectdto.RoleSelectDto;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.dto.UserInfoSearchReqDto;
import com.mttl.vlms.user.dto.LoginUser;
import com.mttl.vlms.user.dto.User;

/**
 * UserInfoService
 * 
 * 
 *
 */
public interface UserInfoService {

	List<UserInfoDto> getUserList(UserInfoSearchReqDto userInfoSearchReqDto) throws SystemException;

	Integer getUserListCount(UserInfoSearchReqDto userInfoSearchReqDto) throws SystemException;

	// List<UserInfoDto> getUserList(Integer id) throws SystemException;

	void insertUserInfo(UserInfoDto userInfoDto, Integer createdBy) throws SystemException;

	List<UserInfoDto> findByName(String name);

	int checkDuplicateId(UserInfoDto userInfoDto) throws SystemException;

	UserInfoDto getUserInfoById(Integer userInfoId) throws SystemException;

	List<RoleSelectDto> getRoleList() throws SystemException;

	int updateUserInfo(UserInfoDto userInfoDto, Integer updatedUser) throws SystemException;
	
	int updateUser(LoginUser loginUser, User user, Integer updatedUser) throws SystemException;

	int deleteUserInfo(UserInfoDto userInfoDto, Integer userID) throws SystemException;

	int checkLoginIdDuplicateCount(Integer id, String loginId) throws SystemException;

	int checkUserInfoUsed(Integer id) throws SystemException;

	int checkDuplicateUserInfoCode(Integer id, String barcodeId) throws SystemException;

	int checkDuplicateStaffId(Integer id, String staffId) throws SystemException;

	int checkDuplicateNrcNo(Integer id, String nrcNo) throws SystemException;

	// int checkDuplicateNrcNo(UserInfoDto userInfoDto) throws SystemException;
}