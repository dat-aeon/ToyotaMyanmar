package com.mttl.vlms.operation.opr002.service;

import java.util.List;

import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoSearchReqDto;

/**
 * PortService
 * 
 * 
 *
 */
public interface VehicleInfoService {

	List<VehicleInfoDto> searchVehicleInfoList(VehicleInfoSearchReqDto vehicleInfoSearchReqDto, VehicleInfoDto vehicleInfoDto) throws SystemException;

	List<VehicleInfoDto> searchAfterUpdate(VehicleInfoDto vehicleInfoDto) throws SystemException;

	int checkDuplicateChassisNo(VehicleInfoDto vehicleInfoDto) throws SystemException;

	int checkDuplicateLicenseNo(VehicleInfoDto vehicleInfoDto) throws SystemException;

	int checkVehicleInfoUsed(Integer vehicleInfoId) throws SystemException;

	List<String> getChassisNoList(String chassisNo);

	VehicleInfoDto getVehicleInfoById(Integer vehicleInfoId) throws SystemException;

	List<VehicleInfoDto> getPortList() throws SystemException;

	List<VehicleInfoDto> getDealerList() throws SystemException;

	List<String> getStatusList() throws SystemException;

	void insertVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer createdUser) throws SystemException;

	int updateVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws SystemException;

	int deleteVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws SystemException;

	void changeUpdate(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws SystemException;

	List<DealerSelectDto> getDealerSelectList() throws SystemException;

}
