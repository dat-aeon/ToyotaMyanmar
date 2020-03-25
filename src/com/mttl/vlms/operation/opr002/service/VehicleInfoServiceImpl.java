package com.mttl.vlms.operation.opr002.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.operation.opr002.dao.VehicleInfoDao;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoSearchReqDto;

/**
 * VehicleInfoServiceImpl
 * 
 * 
 *
 */
@Service("VehicleInfoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class VehicleInfoServiceImpl implements VehicleInfoService {

	@Autowired
	VehicleInfoDao vehicleInfoDao;

	@ApplyAspect
	@Override
	public List<VehicleInfoDto> searchVehicleInfoList(VehicleInfoSearchReqDto vehicleInfoSearchReqDto, VehicleInfoDto vehicleInfoDto) throws SystemException {
		List<VehicleInfoDto> result;
		try {
			result = vehicleInfoDao.searchVehicleInfoList(vehicleInfoSearchReqDto, vehicleInfoDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateChassisNo(VehicleInfoDto vehicleInfoDto) throws SystemException {
		int fullNameCount;
		try {
			fullNameCount = vehicleInfoDao.checkDuplicateChassisNo(vehicleInfoDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return fullNameCount;
	}

	@ApplyAspect
	@Override
	public int checkVehicleInfoUsed(Integer vehicleInfoId) throws SystemException {
		int vehicleInfoCount;
		try {
			vehicleInfoCount = vehicleInfoDao.checkVehicleInfoUsed(vehicleInfoId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return vehicleInfoCount;
	}

	@ApplyAspect
	@Override
	public void insertVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer createdUser) throws SystemException {
		try {
			vehicleInfoDao.insertVehicleInfo(vehicleInfoDto, createdUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public VehicleInfoDto getVehicleInfoById(Integer vehicleInfoId) throws SystemException {
		VehicleInfoDto vehicleInfoDto;
		try {
			vehicleInfoDto = vehicleInfoDao.getVehicleInfoById(vehicleInfoId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return vehicleInfoDto;
	}

	@ApplyAspect
	@Override
	public int updateVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = vehicleInfoDao.updateVehicleInfo(vehicleInfoDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = vehicleInfoDao.deleteVehicleInfo(vehicleInfoDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@Override
	public List<String> getChassisNoList(String chassisNo) {
		List<String> list;
		try {
			list = vehicleInfoDao.getChassisNoList(chassisNo);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return list;
	}

	@Override
	public List<VehicleInfoDto> getPortList() throws SystemException {
		List<VehicleInfoDto> result;
		try {
			result = vehicleInfoDao.getPortList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<VehicleInfoDto> getDealerList() throws SystemException {
		List<VehicleInfoDto> result;
		try {
			result = vehicleInfoDao.getDealerList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public void changeUpdate(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws SystemException {
		try {
			vehicleInfoDao.changeUpdate(vehicleInfoDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}

	}

	@Override
	public List<VehicleInfoDto> searchAfterUpdate(VehicleInfoDto vehicleInfoDto) throws SystemException {
		List<VehicleInfoDto> result;
		try {
			result = vehicleInfoDao.searchAfterUpdate(vehicleInfoDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<String> getStatusList() throws SystemException {
		List<String> result;
		try {
			result = vehicleInfoDao.getStatusList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int checkDuplicateLicenseNo(VehicleInfoDto vehicleInfoDto) throws SystemException {
		int count;
		try {
			count = vehicleInfoDao.checkDuplicateLicenseNo(vehicleInfoDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@Override
	@ApplyAspect
	public List<DealerSelectDto> getDealerSelectList() throws SystemException {
		List<DealerSelectDto> dealerList;
		try {
			dealerList = vehicleInfoDao.getDealerSelectList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return dealerList;
	}

}
