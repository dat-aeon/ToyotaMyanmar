package com.mttl.vlms.setting.stt015.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt015.dao.UploadVehicleFileDao;
import com.mttl.vlms.setting.stt015.dto.UploadVehicleFileDto;

/**
 * ChargesServiceImpl
 * 
 * 
 *
 */
@Service("UploadVehicleFileService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class UploadVehicleFileServiceImpl implements UploadVehicleFileService {
	@Autowired
	UploadVehicleFileDao uploadVehicleFileDao;

	@ApplyAspect
	@Override
	public int insertUploadVehicleFile(UploadVehicleFileDto uploadVehicleFileDto, Integer createdUser) throws SystemException {
		int count;
		try {
			count = uploadVehicleFileDao.insertUploadVehicleFile(uploadVehicleFileDto, createdUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}

		return count;
	}

	@ApplyAspect
	@Override
	public String getDealerId(String dealerName) throws SystemException {
		String uploadVehicleFileDto;
		try {
			uploadVehicleFileDto = uploadVehicleFileDao.getDealerId(dealerName);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return uploadVehicleFileDto;
	}

	@ApplyAspect
	@Override
	public String getPortOfDischargeId(String disChargePortName) throws SystemException {
		String disChargePortId;
		try {
			disChargePortId = uploadVehicleFileDao.getPortOfDischargeId(disChargePortName);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return disChargePortId;
	}

}