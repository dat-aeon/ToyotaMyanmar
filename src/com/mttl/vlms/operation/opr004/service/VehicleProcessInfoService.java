package com.mttl.vlms.operation.opr004.service;

import java.util.List;

import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.DriverSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectdto.YardSelectDto;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoDto;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoSearchReqDto;

/**
 * PortService
 * 
 * 
 *
 */
public interface VehicleProcessInfoService {

	List<VehicleProcessInfoDto> searchByChassisNo(VehicleProcessInfoSearchReqDto vehicleProcessInfoSearchReqDto, VehicleProcessInfoDto vehicleProcessInfoDto)
			throws SystemException;

	VehicleProcessInfoDto getVehicleProcessInfoById(String chassisNo) throws SystemException;

	int deleteVehicleProcessInfo(VehicleProcessInfoDto vehicleProcessInfoDto, Integer updatedUser) throws SystemException;

	/* Start vehicle process information */

	List<DriverSelectDto> getDriverList(Integer processType) throws SystemException;

	List<TaskSelectDto> getTaskList(Integer processType) throws SystemException;

	List<DealerSelectDto> getDealerList(Integer processType) throws SystemException;

	List<YardSelectDto> getYardList(Integer processType) throws SystemException;

	List<VehicleProcessInfoDto> searchDetailView(String chassiNo, Integer processType) throws SystemException;

	List<VehicleProcessInfoDto> searchMainCheck(Integer taskId, Integer vehicleId) throws SystemException;

	List<String> getChassisNoList(String chassiNo, Integer processType) throws SystemException;

	List<String> getImageList(Integer vehicleInfoId) throws SystemException;

	int getImageCountByInspectVehicleId(Integer inspectVehicleId) throws SystemException;

	Integer getCountBySearchTable(VehicleProcessInfoDto vehicleProcessInfoSearchDto) throws SystemException;

}
