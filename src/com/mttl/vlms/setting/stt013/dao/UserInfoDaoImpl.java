package com.mttl.vlms.setting.stt013.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.common.BeanConverter;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.common.selectdto.RoleSelectDto;
import com.mttl.vlms.common.selectmapper.SelectMapper;
import com.mttl.vlms.entity.UserInfo;
import com.mttl.vlms.entity.UserInfoExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.UserInfoMapper;
import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.dto.UserInfoSearchReqDto;
import com.mttl.vlms.setting.stt013.mapper.UserInfoCustomMapper;
import com.mttl.vlms.user.dto.LoginUser;
import com.mttl.vlms.user.dto.User;

/**
 * UserInfoDaoImpl
 * 
 * 
 *
 */
@Repository("UserInfoDao")
@Transactional(propagation = Propagation.REQUIRED)
public class UserInfoDaoImpl extends BasicDAO implements UserInfoDao {

	@Autowired
	private UserInfoCustomMapper userInfoCustomMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private BeanConverter beanConverter;
	@Autowired
	private SelectMapper selectMapper;

	// public List<UserInfoDto> getUserList(String barcodeId) throws
	// DAOException ///mycorrection {

	@Override
	@ApplyAspect
	public List<UserInfoDto> getUserList(UserInfoSearchReqDto userInfoSearchReqDto) throws DAOException {
		List<UserInfoDto> userList;
		try {
			userList = userInfoCustomMapper.getUserList(userInfoSearchReqDto.getOffSet(), userInfoSearchReqDto.getLimit(), userInfoSearchReqDto.getSortField(),
					userInfoSearchReqDto.getSortOrder(), userInfoSearchReqDto.getFilters());
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return userList;
	}

	@Override
	@ApplyAspect
	public Integer getUserListCount(UserInfoSearchReqDto userInfoSearchReqDto) throws DAOException {
		Integer userCount;
		try {
			userCount = userInfoCustomMapper.getUserListCount(userInfoSearchReqDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return userCount;
	}

	/*
	 * @ApplyAspect
	 * 
	 * @Override public List<UserInfoDto> getUserList(Integer id) throws
	 * DAOException { List<UserInfoDto> result; try { result =
	 * userInfoCustomMapper.getUserList(id); } catch (RuntimeException e) {
	 * throw translate(e.getMessage(), e); } return result; }
	 */

	@ApplyAspect
	@Override
	public void insertUserInfo(UserInfoDto userInfoDto, Integer createdBy) throws DAOException {
		try {
			Date createdDate = Utils.getCurrentTime();
			UserInfo userInfo = beanConverter.convert(userInfoDto, UserInfo.class);
			userInfo.setCreatedDate(createdDate);
			userInfo.setUpdatedDate(createdDate);
			userInfo.setCreatedBy(createdBy);
			userInfo.setUpdatedBy(createdBy);
			userInfoMapper.insertSelective(userInfo);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public int checkDuplicateId(UserInfoDto userInfoDto) throws DAOException {
		int idCount;
		try {
			idCount = userInfoCustomMapper.checkId(userInfoDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return idCount;
	}

	@ApplyAspect
	@Override
	public List<UserInfoDto> searchByName(String name) throws DAOException {
		List<UserInfoDto> result;
		try {
			result = userInfoCustomMapper.searchByName(name);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public List<RoleSelectDto> getRoleList() throws DAOException {
		List<RoleSelectDto> roleList;
		try {
			roleList = selectMapper.getRoleList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return roleList;
	}

	@Override
	public UserInfoDto getUserInfoById(Integer userInfoId) throws DAOException {
		UserInfoDto userInfoDto;
		try {
			userInfoDto = userInfoCustomMapper.getUserInfoById(userInfoId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return userInfoDto;
	}

	@Override
	public int updateUserInfo(UserInfoDto userInfoDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			UserInfo userInfo = beanConverter.convert(userInfoDto, UserInfo.class);
			userInfo.setUpdatedDate(updatedDate);
			userInfo.setUpdatedBy(updatedUser);
			userInfo.setDescription(userInfoDto.getDescription());
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andIdEqualTo(userInfoDto.getId()).andDeleteFlgEqualTo(false);
			count = userInfoMapper.updateByExampleSelective(userInfo, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int checkLoginIdDuplicateCount(Integer id, String loginId) throws DAOException {
		int idCount;
		try {
			idCount = userInfoCustomMapper.checkLoginIdDuplicateCount(id, loginId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return idCount;
	}

	@Override
	public int checkUserInfoUsed(Integer id) throws DAOException {
		int userInfoCount;
		try {
			userInfoCount = userInfoCustomMapper.checkUserInfoUsed(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return userInfoCount;
	}

	@Override
	public int deleteUserInfo(UserInfoDto userInfoDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			UserInfo userInfo = beanConverter.convert(userInfoDto, UserInfo.class);
			userInfo.setDeleteFlg(true);
			userInfo.setUpdatedBy(updatedUser);
			userInfo.setUpdatedDate(updatedDate);
			UserInfoExample userInfoExample = new UserInfoExample();
			userInfoExample.createCriteria().andIdEqualTo(userInfo.getId()).andDeleteFlgEqualTo(false);
			count = userInfoMapper.updateByExampleSelective(userInfo, userInfoExample);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateStaffId(Integer id, String staffId) throws DAOException {
		int idCount;
		try {
			idCount = userInfoCustomMapper.checkStaffId(id, staffId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return idCount;
	}

	@Override
	public int checkDuplicateNrcNo(Integer id, String nrcNo) throws DAOException {
		int idCount;
		try {
			idCount = userInfoCustomMapper.checkDuplicateNrcNo(id, nrcNo);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return idCount;
	}

	@Override
	public int checkDuplicateUserInfoCode(Integer id, String barcodeId) throws DAOException {
		int idCount;
		try {
			idCount = userInfoCustomMapper.checkUserInfoCode(id, barcodeId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return idCount;
	}

	@Override
	public int updateUser(LoginUser loginUser, User user, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			UserInfo userInfo = beanConverter.convert(loginUser, UserInfo.class);
			userInfo.setUpdatedDate(updatedDate);
			userInfo.setUpdatedBy(updatedUser);
			userInfo.setPasswordChangeDate(updatedDate);
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andIdEqualTo(user.getUserID()).andDeleteFlgEqualTo(false);
			count = userInfoMapper.updateByExampleSelective(userInfo, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

}
