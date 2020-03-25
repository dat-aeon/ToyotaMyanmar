package com.mttl.vlms.operation.opr004.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.DriverSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectdto.YardSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.operation.opr004.dao.VehicleProcessInfoDao;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoDto;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoSearchReqDto;

/**
 * VehicleProcessInfoServiceImpl
 * 
 * 
 *
 */
@Service("VehicleProcessInfoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class VehicleProcessInfoServiceImpl implements VehicleProcessInfoService {

	@Autowired
	VehicleProcessInfoDao vehicleProcessInfoDao;

	@ApplyAspect
	@Override
	public List<VehicleProcessInfoDto> searchByChassisNo(VehicleProcessInfoSearchReqDto vehicleProcessInfoSearchReqDto, VehicleProcessInfoDto vehicleProcessInfoDto)
			throws SystemException {
		List<VehicleProcessInfoDto> result;
		try {
			result = vehicleProcessInfoDao.searchByChassisNo(vehicleProcessInfoSearchReqDto, vehicleProcessInfoDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public VehicleProcessInfoDto getVehicleProcessInfoById(String chassisNo) throws SystemException {
		VehicleProcessInfoDto vehicleProcessInfoDto;
		try {
			vehicleProcessInfoDto = vehicleProcessInfoDao.getVehicleProcessInfoById(chassisNo);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return vehicleProcessInfoDto;
	}

	@ApplyAspect

	@Override
	public int deleteVehicleProcessInfo(VehicleProcessInfoDto vehicleProcessInfoDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = vehicleProcessInfoDao.deleteVehicleProcessInfo(vehicleProcessInfoDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@Override
	public List<DriverSelectDto> getDriverList(Integer processType) throws SystemException {
		List<DriverSelectDto> driverList;
		try {
			driverList = vehicleProcessInfoDao.getDriverList(processType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return driverList;
	}

	@Override
	public List<TaskSelectDto> getTaskList(Integer processType) throws SystemException {
		List<TaskSelectDto> taskList;
		try {
			taskList = vehicleProcessInfoDao.getTaskList(processType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return taskList;
	}

	@Override
	public List<DealerSelectDto> getDealerList(Integer processType) throws SystemException {
		List<DealerSelectDto> taskList;
		try {
			taskList = vehicleProcessInfoDao.getDealerList(processType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return taskList;
	}

	@Override
	public List<YardSelectDto> getYardList(Integer processType) throws SystemException {
		List<YardSelectDto> taskList;
		try {
			taskList = vehicleProcessInfoDao.getYardList(processType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return taskList;
	}

	@Override
	public List<VehicleProcessInfoDto> searchDetailView(String chassiNo, Integer processType) throws SystemException {
		List<VehicleProcessInfoDto> detailViewList;
		try {
			detailViewList = vehicleProcessInfoDao.searchDetailView(chassiNo, processType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return detailViewList;
	}

	@Override
	public List<VehicleProcessInfoDto> searchMainCheck(Integer taskId, Integer vehicleId) throws SystemException {
		List<VehicleProcessInfoDto> detailViewList;
		try {
			detailViewList = vehicleProcessInfoDao.searchMainCheck(taskId, vehicleId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return detailViewList;
	}

	@Override
	public List<String> getChassisNoList(String chassiNo, Integer processType) throws SystemException {
		List<String> chassisList;
		try {
			chassisList = vehicleProcessInfoDao.getChassisNoList(chassiNo, processType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return chassisList;
	}

	@Override
	public List<String> getImageList(Integer vehicleInfoId) throws SystemException {
		List<String> imageList;
		try {
			imageList = vehicleProcessInfoDao.getImageList(vehicleInfoId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return imageList;
	}

	@Override
	public int getImageCountByInspectVehicleId(Integer inspectVehicleId) throws SystemException {

		int count;
		try {
			count = vehicleProcessInfoDao.getImageCountByInspectVehicleId(inspectVehicleId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;

	}

	@Override
	public Integer getCountBySearchTable(VehicleProcessInfoDto vehicleProcessInfoSearchDto) throws SystemException {
		Integer count;
		try {
			count = vehicleProcessInfoDao.getCountBySearchTable(vehicleProcessInfoSearchDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;

	}
}
