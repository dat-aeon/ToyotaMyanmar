package com.mttl.vlms.operation.opr002.dao;

import java.util.List;

import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.VehicleInfoSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoSearchReqDto;

/**
 * VehicleInfoDao
 * 
 * 
 *
 */
public interface VehicleInfoDao {
	List<VehicleInfoDto> searchVehicleInfoList(VehicleInfoSearchReqDto vehicleInfoSearchReqDto, VehicleInfoDto vehicleInfoDto) throws DAOException;

	List<VehicleInfoDto> searchAfterUpdate(VehicleInfoDto vehicleInfoDto) throws DAOException;

	List<VehicleInfoSelectDto> getVehicleInfoList() throws DAOException;

	List<VehicleInfoDto> getPortList() throws DAOException;

	List<VehicleInfoDto> getDealerList() throws DAOException;

	List<String> getStatusList() throws DAOException;

	int checkDuplicateChassisNo(VehicleInfoDto vehicleInfoDto) throws DAOException;

	int checkDuplicateLicenseNo(VehicleInfoDto vehicleInfoDto) throws DAOException;

	int checkVehicleInfoUsed(Integer vehicleInfoId) throws DAOException;

	List<String> getChassisNoList(String chassisNo) throws DAOException;

	VehicleInfoDto getVehicleInfoById(Integer vehicleInfoId) throws DAOException;

	void insertVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer createdUser) throws DAOException;

	int updateVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws DAOException;

	int deleteVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws DAOException;

	void changeUpdate(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws DAOException;

	public List<DealerSelectDto> getDealerSelectList() throws DAOException;

}
