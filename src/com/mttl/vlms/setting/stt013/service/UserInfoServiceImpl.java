package com.mttl.vlms.setting.stt013.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.PasswordCodecHandler;
import com.mttl.vlms.common.selectdto.RoleSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt013.dao.UserInfoDao;
import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.dto.UserInfoSearchReqDto;
import com.mttl.vlms.user.dto.LoginUser;
import com.mttl.vlms.user.dto.User;

/**
 * UserInfoServiceImpl
 * 
 * 
 *
 */
@Service("UserInfoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private PasswordCodecHandler passwordCodecHandler;

	@Override
	@ApplyAspect
	public List<UserInfoDto> getUserList(UserInfoSearchReqDto userInfoSearchReqDto) throws SystemException {
		List<UserInfoDto> userList;
		try {
			userList = userInfoDao.getUserList(userInfoSearchReqDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return userList;
	}

	@Override
	@ApplyAspect
	public Integer getUserListCount(UserInfoSearchReqDto userInfoSearchReqDto) throws SystemException {
		Integer userCount;
		try {
			userCount = userInfoDao.getUserListCount(userInfoSearchReqDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return userCount;
	}

	/*
	 * @ApplyAspect
	 * 
	 * @Override public List<UserInfoDto> getUserList(Integer id) throws
	 * SystemException { List<UserInfoDto> result; try { result =
	 * userInfoDao.getUserList(id); } catch (DAOException e) { throw new
	 * SystemException(e.getErrorCode(), e.getMessage(), e); } return result; }
	 */

	@ApplyAspect
	@Override
	public void insertUserInfo(UserInfoDto userInfoDto, Integer createdBy) throws SystemException {
		try {

			userInfoDto.setPassword(passwordCodecHandler.encode(userInfoDto.getPassword()));
			userInfoDao.insertUserInfo(userInfoDto, createdBy);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@Override
	public List<UserInfoDto> findByName(String name) throws SystemException {
		List<UserInfoDto> result;
		try {
			result = userInfoDao.searchByName(name);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int checkDuplicateId(UserInfoDto userInfoDto) throws SystemException {

		int idCount;
		try {
			idCount = userInfoDao.checkDuplicateId(userInfoDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return idCount;

	}

	@Override
	public List<RoleSelectDto> getRoleList() throws SystemException {
		List<RoleSelectDto> roleList;
		try {
			roleList = userInfoDao.getRoleList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return roleList;

	}

	@Override
	public UserInfoDto getUserInfoById(Integer userInfoId) throws SystemException {
		UserInfoDto userInfoDto;
		try {
			userInfoDto = userInfoDao.getUserInfoById(userInfoId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return userInfoDto;
	}

	@Override
	public int updateUserInfo(UserInfoDto userInfoDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			if (!StringUtils.isEmpty(userInfoDto.getPassword())) {
				userInfoDto.setPassword(passwordCodecHandler.encode(userInfoDto.getPassword()));
			}

			count = userInfoDao.updateUserInfo(userInfoDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int deleteUserInfo(UserInfoDto userInfoDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = userInfoDao.deleteUserInfo(userInfoDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateStaffId(Integer id, String staffId) throws SystemException {
		int idCount;
		try {
			idCount = userInfoDao.checkDuplicateStaffId(id, staffId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return idCount;

	}

	@Override
	public int checkLoginIdDuplicateCount(Integer id, String loginId) throws SystemException {

		int idCount;
		try {
			idCount = userInfoDao.checkLoginIdDuplicateCount(id, loginId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return idCount;

	}

	@Override
	public int checkDuplicateUserInfoCode(Integer id, String barcodeId) throws SystemException {
		int idCount;
		try {
			idCount = userInfoDao.checkDuplicateUserInfoCode(id, barcodeId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return idCount;
	}

	@Override
	public int checkDuplicateNrcNo(Integer id, String nrcNo) throws SystemException {

		int idCount;
		try {
			idCount = userInfoDao.checkDuplicateNrcNo(id, nrcNo);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return idCount;
	}

	@Override
	public int checkUserInfoUsed(Integer id) throws SystemException {
		int userInfoCount;
		try {
			userInfoCount = userInfoDao.checkUserInfoUsed(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return userInfoCount;
	}
	
	@Override
	public int updateUser(LoginUser loginUser, User user, Integer updatedUser) throws SystemException {
		int count;
		try {
			if (!StringUtils.isEmpty(loginUser.getPassword())) {
				loginUser.setPassword(passwordCodecHandler.encode(loginUser.getPassword()));
			}

			count = userInfoDao.updateUser(loginUser, user, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

}