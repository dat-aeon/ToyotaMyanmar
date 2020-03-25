package com.mttl.vlms.setting.stt011.dao;

import java.util.List;

import com.mttl.vlms.common.selectdto.StateDivisionInfoSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt011.dto.DriverDto;

/**
 * DriverDao
 * 
 * 
 *
 */
public interface DriverDao {

	List<DriverDto> getDriverList(DriverDto dto) throws DAOException;

	List<StateDivisionInfoSelectDto> getStateDivisionList() throws DAOException;

	List<DriverDto> searchDriverList(DriverDto driverDto) throws DAOException;

	int checkDuplicateDriverCode(DriverDto driverDto) throws DAOException;

	int checkDriverUsed(Integer driverId) throws DAOException;

	int checkDuplicateDriverNRCNo(DriverDto driverDto) throws DAOException;

	int checkDuplicateLicenseNo(DriverDto driverDto) throws DAOException;

	void insertDriver(DriverDto driverDto, Integer createdUser) throws DAOException;

	DriverDto getDriverById(Integer driverId) throws DAOException;

	int updateDriver(DriverDto driverDto, Integer updatedUser) throws DAOException;

	int deleteDriver(DriverDto driverDto, Integer updatedUser) throws DAOException;

	int checkDuplicateStaffId(DriverDto driverDto) throws DAOException;

	int checkExpireDate(Integer id) throws DAOException;

	String getNextBPStaffId() throws DAOException;

}
