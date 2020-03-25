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
import com.mttl.vlms.entity.Role;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.RoleMapper;
import com.mttl.vlms.setting.stt013.dto.RoleDto;
import com.mttl.vlms.setting.stt013.mapper.RoleCustomMapper;

/**
 * RoleDaoImpl
 * 
 * 
 *
 */
@Repository("RoleDao")
@Transactional(propagation = Propagation.REQUIRED)
public class RoleDaoImpl extends BasicDAO implements RoleDao {

	@Autowired
	private RoleCustomMapper roleCustomMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private BeanConverter beanConverter;

	@ApplyAspect
	@Override
	public List<RoleDto> getUserList(String roleId) throws DAOException {
		List<RoleDto> result;
		try {
			result = roleCustomMapper.getUserList(roleId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public void insertRole(RoleDto roleDto, Integer createdBy) throws DAOException {
		try {
			Date createdDate = Utils.getCurrentTime();
			Role role = beanConverter.convert(roleDto, Role.class);
			role.setCreatedDate(createdDate);
			role.setUpdatedDate(createdDate);
			role.setCreatedBy(createdBy);
			role.setUpdatedBy(createdBy);
			roleMapper.insertSelective(role);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@Override
	public int checkDuplicateRoleId(RoleDto RoleDto) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
