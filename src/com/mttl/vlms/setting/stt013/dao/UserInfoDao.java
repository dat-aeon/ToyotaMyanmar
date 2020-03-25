package com.mttl.vlms.setting.stt013.dao;

import java.util.List;

import com.mttl.vlms.common.selectdto.RoleSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.dto.UserInfoSearchReqDto;
import com.mttl.vlms.user.dto.LoginUser;
import com.mttl.vlms.user.dto.User;

/**
 * UserInfoDao
 * 
 * 
 *
 */
public interface UserInfoDao {

	List<UserInfoDto> searchByName(String name) throws DAOException;

	List<UserInfoDto> getUserList(UserInfoSearchReqDto userInfoSearchReqDto) throws DAOException;

	// List<UserInfoDto> getUserList(Integer id) throws DAOException;

	void insertUserInfo(UserInfoDto userInfoDto, Integer createdBy) throws DAOException;

	int checkDuplicateId(UserInfoDto userInfoDto) throws DAOException;

	List<RoleSelectDto> getRoleList() throws DAOException;

	UserInfoDto getUserInfoById(Integer userInfoId) throws DAOException;

	int updateUserInfo(UserInfoDto userInfoDto, Integer updatedUser) throws DAOException;
	
	int updateUser(LoginUser loginUser, User user, Integer updatedUser) throws DAOException;

	int checkLoginIdDuplicateCount(Integer id, String loginId) throws DAOException;

	int checkUserInfoUsed(Integer id) throws DAOException;

	int deleteUserInfo(UserInfoDto userInfoDto, Integer updatedUser) throws DAOException;

	int checkDuplicateUserInfoCode(Integer id, String barcodeId) throws DAOException;

	int checkDuplicateStaffId(Integer id, String staffId) throws DAOException;

	int checkDuplicateNrcNo(Integer id, String nrcNo) throws DAOException;

	Integer getUserListCount(UserInfoSearchReqDto userInfoSearchReqDto) throws DAOException;

	// int checkDuplicateNrcNo(UserInfoDto userInfoDto) throws DAOException;

}
