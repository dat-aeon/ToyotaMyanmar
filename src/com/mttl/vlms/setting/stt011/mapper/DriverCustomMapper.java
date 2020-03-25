package com.mttl.vlms.setting.stt011.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt011.dto.DriverDto;

/**
 * DriverCustomMapper
 * 
 * 
 *
 */
public interface DriverCustomMapper {

	List<DriverDto> getDriverList(@Param("driverDto") DriverDto dto);

	List<DriverDto> searchDriverList(@Param("driverDto") DriverDto driverDto);

	int checkDriverName(@Param("driverDto") DriverDto driverDto);

	int checkDriverCode(@Param("driverDto") DriverDto driverDto);

	int checkDriverUsed(@Param("id") Integer id);

	int checkDuplicateLicenseNo(@Param("driverDto") DriverDto driverDto);

	String getNextBPStaffId();

	DriverDto getDriverById(@Param("id") Integer id);

	int checkStaffId(@Param("driverDto") DriverDto driverDto);

	int checkDuplicateDriverNRCNo(@Param("driverDto") DriverDto driverDto);

	int checkExpireDate(@Param("id") Integer id);

}
