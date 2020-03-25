package com.mttl.vlms.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.user.mapper.SecurityMapper;

/**
 * SecurityDaoImpl
 * 
 * 
 *
 */
@Repository("SecurityDao")
@Transactional(propagation = Propagation.REQUIRED)
public class SecurityDaoImpl extends BasicDAO implements UserDetailsService {
	@Autowired
	private SecurityMapper securityMapper;

	@ApplyAspect
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		UserDetails result = null;
		try {
			result = loadUserByUser(loginId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	private UserDetails loadUserByUser(String loginId) throws DAOException {
		UserDetails result = null;
		try {
			result = securityMapper.loadUserByLoginId(loginId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}
}
