package com.mttl.vlms.setting.stt009.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt009.dao.PortDao;
import com.mttl.vlms.setting.stt009.dto.PortDto;

/**
 * PortServiceImpl
 * 
 * 
 *
 */
@Service("PortService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class PortServiceImpl implements PortService {

	@Autowired
	PortDao portDao;

	@ApplyAspect
	@Override
	public List<PortDto> findByPortName(String portName) throws SystemException {
		List<PortDto> result;
		try {
			result = portDao.searchByPortName(portName);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	/*
	 * @ApplyAspect
	 * 
	 * @Override public int checkDuplicateShortName(PortDto portDto) throws
	 * SystemException { int shortNameCount; try { shortNameCount =
	 * portDao.checkDuplicateShortName(portDto); } catch (DAOException e) {
	 * throw new SystemException(e.getErrorCode(), e.getMessage(), e); } return
	 * shortNameCount; }
	 */
	@ApplyAspect
	@Override
	public int checkDuplicateFullName(PortDto portDto) throws SystemException {
		int fullNameCount;
		try {
			fullNameCount = portDao.checkDuplicateFullName(portDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return fullNameCount;
	}

	@ApplyAspect
	@Override
	public int checkPortUsed(Integer portId) throws SystemException {
		int portCount;
		try {
			portCount = portDao.checkPortUsed(portId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return portCount;
	}

	@ApplyAspect
	@Override
	public void insertPort(PortDto portDto, Integer createdUser) throws SystemException {
		try {
			portDao.insertPort(portDto, createdUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public PortDto getPortById(Integer portId) throws SystemException {
		PortDto portDto;
		try {
			portDto = portDao.getPortById(portId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return portDto;
	}

	@ApplyAspect
	@Override
	public int updatePort(PortDto portDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = portDao.updatePort(portDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deletePort(PortDto portDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = portDao.deletePort(portDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int checkDuplicateShortName(Integer id, String shortName) throws SystemException {
		int idCount;
		try {
			idCount = portDao.checkDuplicateShortName(id, shortName);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return idCount;
	}

}
