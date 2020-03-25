package com.mttl.vlms.setting.stt015.service;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt015.dto.UploadVehicleFileDto;

/**
 * UploadVehicleFileService
 * 
 * 
 *
 */
public interface UploadVehicleFileService {

	int insertUploadVehicleFile(UploadVehicleFileDto uploadVehicleFileDto, Integer createdUser) throws SystemException;

	String getDealerId(String dealerName) throws SystemException;

	String getPortOfDischargeId(String portOfDischargeId) throws SystemException;

}
