package com.mttl.vlms.operation.opr004.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.DriverSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectdto.YardSelectDto;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoDto;

/**
 * vehicleProcessInfoCustomMapper
 * 
 * 
 *
 */
public interface VehicleProcessInfoCustomMapper {
	List<VehicleProcessInfoDto> searchByChassisNo(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("sortField") String sortField,
			@Param("sortOrder") String sortOrder, @Param("filters") Map<String, Object> filters, @Param("vehicleProcessInfoDto") VehicleProcessInfoDto vehicleProcessInfoDto);

	int checkChassisNo(@Param("vehicleProcessInfoDto") VehicleProcessInfoDto vehicleProcessInfoDto);

	int checkVehicleProcessInfoCode(@Param("vehicleProcessInfoDto") VehicleProcessInfoDto vehicleProcessInfoDto);

	int checkVehicleProcessInfoUsed(@Param("vehicleProcessInfoId") Integer vehicleProcessInfoId);

	List<String> getPortLocation(String portDischarge);

	VehicleProcessInfoDto getVehicleProcessInfoById(@Param("chassisNo") String chassisNo);

	/* Start */
	List<TaskSelectDto> getTaskList(Integer processType);

	List<DriverSelectDto> getDriverList(Integer processType);

	List<DealerSelectDto> getDealerList(Integer processType);

	List<YardSelectDto> getYardList(Integer processType);

	List<VehicleProcessInfoDto> searchDetailView(@Param("chassisNo") String chassisNo, @Param("processType") Integer processType);

	List<String> getChassisNoList(@Param("chassisNo") String chassisNo, @Param("processType") Integer processType);

	List<String> getImageList(@Param("vehicleInfoId") Integer vehicleInfoId);

	Integer getCountBySearchTable(@Param("vehicleProcessInfoDto") VehicleProcessInfoDto vehicleProcessInfoSearchDto);

	List<VehicleProcessInfoDto> searchMainCheck(@Param("taskId") Integer taskId, @Param("vehicleId") Integer vehicleId);
}
