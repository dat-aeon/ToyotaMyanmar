package com.mttl.vlms.setting.stt011.service;

import java.util.List;

import com.mttl.vlms.common.selectdto.StateDivisionInfoSelectDto;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt011.dto.DriverDto;

/**
 * DriverService
 * 
 * 
 *
 */
public interface DriverService {

	List<DriverDto> getDriverList(DriverDto dto) throws SystemException;

	List<StateDivisionInfoSelectDto> getStateDivisionList() throws SystemException;

	List<DriverDto> findDriverList(DriverDto driverDto) throws SystemException;

	int checkDuplicateDriverCode(DriverDto driverDto) throws SystemException;

	int checkDuplicateStaffId(DriverDto driverDto) throws SystemException;

	int checkDriverUsed(Integer id) throws SystemException;

	int checkDuplicateDriverNRCNo(DriverDto driverDto) throws SystemException;

	int checkDuplicateLicenseNo(DriverDto driverDto) throws SystemException;

	void insertDriver(DriverDto driverDto, Integer createdUser) throws SystemException;

	DriverDto getDriverById(Integer id) throws SystemException;

	int updateDriver(DriverDto driverDto, Integer updatedUser) throws SystemException;

	int deleteDriver(DriverDto driverDto, Integer updatedUser) throws SystemException;

	int checkExpireDate(Integer id) throws SystemException;

	String getNextBPStaffId() throws SystemException;

}
