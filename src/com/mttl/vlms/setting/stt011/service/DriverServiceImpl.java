package com.mttl.vlms.setting.stt011.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.selectdto.StateDivisionInfoSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt011.dao.DriverDao;
import com.mttl.vlms.setting.stt011.dto.DriverDto;

/**
 * DriverServiceImpl
 * 
 * 
 *
 */
@Service("DriverService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverDao driverDao;

	@ApplyAspect
	@Override
	public List<DriverDto> findDriverList(DriverDto driverDto) throws SystemException {
		List<DriverDto> result;
		try {
			result = driverDao.searchDriverList(driverDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	/*
	 * @ApplyAspect
	 * 
	 * @Override public int checkDuplicateDriverName(DriverDto driverDto) throws
	 * SystemException { int driverNameCount; try { driverNameCount =
	 * driverDao.checkDuplicateDriverName(driverDto); } catch (DAOException e) {
	 * throw new SystemException(e.getErrorCode(), e.getMessage(), e); } return
	 * driverNameCount; }
	 */

	@ApplyAspect
	@Override
	public int checkDuplicateDriverCode(DriverDto driverDto) throws SystemException {
		int driverCodeCount;
		try {
			driverCodeCount = driverDao.checkDuplicateDriverCode(driverDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return driverCodeCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateStaffId(DriverDto driverDto) throws SystemException {
		int staffIdCount;
		try {
			staffIdCount = driverDao.checkDuplicateStaffId(driverDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return staffIdCount;
	}

	@ApplyAspect
	@Override
	public int checkDriverUsed(Integer driverId) throws SystemException {
		int driverCount;
		try {
			driverCount = driverDao.checkDriverUsed(driverId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return driverCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateDriverNRCNo(DriverDto driverdto) throws SystemException {
		int driverNRCCount;
		try {
			driverNRCCount = driverDao.checkDuplicateDriverNRCNo(driverdto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return driverNRCCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateLicenseNo(DriverDto driverdto) throws SystemException {
		int driverLicenseCount;
		try {
			driverLicenseCount = driverDao.checkDuplicateLicenseNo(driverdto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return driverLicenseCount;
	}

	@ApplyAspect
	@Override
	public void insertDriver(DriverDto driverDto, Integer createdUser) throws SystemException {
		try {
			driverDao.insertDriver(driverDto, createdUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public DriverDto getDriverById(Integer id) throws SystemException {
		DriverDto driverDto;
		try {
			driverDto = driverDao.getDriverById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return driverDto;
	}

	@ApplyAspect
	@Override
	public int updateDriver(DriverDto driverDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = driverDao.updateDriver(driverDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteDriver(DriverDto driverDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = driverDao.deleteDriver(driverDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public List<DriverDto> getDriverList(DriverDto dto) throws SystemException {

		List<DriverDto> driverList;
		try {
			driverList = driverDao.getDriverList(dto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return driverList;
	}

	@Override
	public List<StateDivisionInfoSelectDto> getStateDivisionList() throws SystemException {
		List<StateDivisionInfoSelectDto> stateDivisionList;
		try {
			stateDivisionList = driverDao.getStateDivisionList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return stateDivisionList;
	}

	@ApplyAspect
	@Override
	public String getNextBPStaffId() throws SystemException {

		String staffId;

		try {
			staffId = driverDao.getNextBPStaffId();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return staffId;
	}

	@Override
	public int checkExpireDate(Integer id) throws SystemException {
		Integer driverId;

		try {
			driverId = driverDao.checkExpireDate(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return driverId;
	}

}
