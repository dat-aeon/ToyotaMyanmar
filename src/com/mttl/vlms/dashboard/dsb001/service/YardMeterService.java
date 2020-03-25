package com.mttl.vlms.dashboard.dsb001.service;

import java.util.List;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt017.dto.YardDto;

/**
 * YardMeterService
 * 
 * 
 *
 */
public interface YardMeterService {

	int searchYard() throws SystemException;

	List<YardDto> findYardList() throws SystemException;

	int findTotalSpace(int id) throws SystemException;

	int findRestSpace(int id) throws SystemException;

	int findSpace(int id) throws SystemException;

	int getEtaVehicleNo() throws SystemException;

	int getAudioInstallVehicle() throws SystemException;

	int getPdiVehicle() throws SystemException;

	int countLicenseExpireDriver() throws SystemException;
}
