package com.mttl.vlms.operation.opr004.dao;

import java.util.List;

import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.DriverSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectdto.YardSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoDto;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoSearchReqDto;

/**
 * VehicleProcessInfoDao
 * 
 * 
 *
 */
public interface VehicleProcessInfoDao {
	List<VehicleProcessInfoDto> searchByChassisNo(VehicleProcessInfoSearchReqDto vehicleProcessInfoSearchReqDto, VehicleProcessInfoDto vehicleProcessInfoDto) throws DAOException;

	VehicleProcessInfoDto getVehicleProcessInfoById(String chassisNo) throws DAOException;

	int deleteVehicleProcessInfo(VehicleProcessInfoDto vehicleProcessInfoDto, Integer updatedUser) throws DAOException;

	/* start vehicle process info */

	List<DriverSelectDto> getDriverList(Integer processType) throws DAOException;

	List<TaskSelectDto> getTaskList(Integer processType) throws DAOException;

	List<DealerSelectDto> getDealerList(Integer processType) throws DAOException;

	List<YardSelectDto> getYardList(Integer processType) throws DAOException;

	List<VehicleProcessInfoDto> searchDetailView(String chassisNo, Integer processType) throws DAOException;

	List<VehicleProcessInfoDto> searchMainCheck(Integer taskId, Integer vehicleId) throws DAOException;

	List<String> getChassisNoList(String chassiNo, Integer processType) throws DAOException;

	List<String> getImageList(Integer vehicleInfoId) throws DAOException;

	int getImageCountByInspectVehicleId(Integer inspectVehicleId) throws DAOException;

	Integer getCountBySearchTable(VehicleProcessInfoDto vehicleProcessInfoSearchDto) throws DAOException;

}
