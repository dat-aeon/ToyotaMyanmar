package com.mttl.vlms.operation.opr002.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;

/**
 * vehicleInfoCustomMapper
 * 
 * 
 *
 */
public interface VehicleInfoCustomMapper {
	List<VehicleInfoDto> searchVehicleInfoList(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("sortField") String sortField,
			@Param("sortOrder") String sortOrder, @Param("filters") Map<String, Object> filters, @Param("vehicleInfoDto") VehicleInfoDto vehicleInfoDto);

	List<VehicleInfoDto> searchAfterUpdate(@Param("vehicleInfoDto") VehicleInfoDto vehicleInfoDto);

	int checkChassisNo(@Param("vehicleInfoDto") VehicleInfoDto vehicleInfoDto);

	int checkDuplicateLicenseNo(@Param("vehicleInfoDto") VehicleInfoDto vehicleInfoDto);

	int checkVehicleInfoUsed(@Param("vehicleInfoId") Integer vehicleInfoId);

	List<String> getChassisNoList(String chassisNo);

	List<String> getStatusList();

	VehicleInfoDto getVehicleInfoById(@Param("vehicleInfoId") Integer vehicleInfoId);

	List<VehicleInfoDto> getPortList();

	List<VehicleInfoDto> getDealerList();

}
