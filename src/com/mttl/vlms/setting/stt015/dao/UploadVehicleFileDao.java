package com.mttl.vlms.setting.stt015.dao;

import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt015.dto.UploadVehicleFileDto;

/**
 * ChargesDao
 * 
 * 
 *
 */
public interface UploadVehicleFileDao {

	int insertUploadVehicleFile(UploadVehicleFileDto chargesDto, Integer createdUser) throws DAOException;

	String getDealerId(String dealerName) throws DAOException;

	String getPortOfDischargeId(String portOfDischargeId) throws DAOException;

}
