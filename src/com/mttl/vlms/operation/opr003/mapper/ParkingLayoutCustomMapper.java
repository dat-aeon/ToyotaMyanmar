package com.mttl.vlms.operation.opr003.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.operation.opr004.dto.InspectVehicleDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;

/**
 * ParkingLayoutCustomMapper
 * 
 * 
 *
 */
public interface ParkingLayoutCustomMapper {

	List<ZoneDto> getByRowColumnEachZone(@Param("yardFkId") Integer yardFkId);

	Date getParkingInfo(@Param("parkingId") Integer parkingId);

	InspectVehicleDto getParkingSlotInfoByColumnId(@Param("parkingColId") Integer parkingColId);

}
