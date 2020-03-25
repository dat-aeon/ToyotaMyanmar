package com.mttl.vlms.dashboard.dsb001.dao;

import java.util.List;

import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt017.dto.YardDto;

/**
 * YardDao
 * 
 * 
 *
 */
public interface YardMeterDao {
	// List<YardMeterDto> searchByCountryName(String countryName) throws
	// DAOException;

	int searchYardList() throws DAOException;

	List<YardDto> findYardList() throws DAOException;

	int findTotalSpace(int id) throws DAOException;

	int findRestSpace(int id) throws DAOException;

	int findSpace(int id) throws DAOException;

	int getEtaVehicleNo() throws DAOException;

	int getAudioInstallVehicle() throws DAOException;

	int getPdiVehicle() throws DAOException;

	int countLicenseExpireDriver() throws DAOException;
}
