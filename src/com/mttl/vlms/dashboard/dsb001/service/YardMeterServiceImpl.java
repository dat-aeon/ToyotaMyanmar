package com.mttl.vlms.dashboard.dsb001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.dashboard.dsb001.dao.YardMeterDao;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt017.dto.YardDto;

/**
 * YardServiceImpl
 * 
 * 
 *
 */
@Service("YardMeterService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class YardMeterServiceImpl implements YardMeterService {

	@Autowired
	YardMeterDao yardDao;

	@ApplyAspect
	@Override
	public int searchYard() throws SystemException {
		int result;
		try {
			result = yardDao.searchYardList();

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<YardDto> findYardList() throws SystemException {
		List<YardDto> result;
		try {
			result = yardDao.findYardList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int findTotalSpace(int id) throws SystemException {
		int result;
		try {
			result = yardDao.findTotalSpace(id);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int findRestSpace(int id) throws SystemException {
		int result;
		try {
			result = yardDao.findRestSpace(id);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int findSpace(int id) throws SystemException {
		int result;
		try {
			result = yardDao.findSpace(id);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int getEtaVehicleNo() throws SystemException {
		int result;
		try {
			result = yardDao.getEtaVehicleNo();

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int getAudioInstallVehicle() throws SystemException {
		int result;
		try {
			result = yardDao.getAudioInstallVehicle();

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int getPdiVehicle() throws SystemException {
		int result;
		try {
			result = yardDao.getPdiVehicle();

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int countLicenseExpireDriver() throws SystemException {
		int result;
		try {
			result = yardDao.countLicenseExpireDriver();

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}
}
