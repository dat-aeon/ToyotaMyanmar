package com.mttl.vlms.setting.stt015.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.common.BeanConverter;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.entity.VehicleInfo;
import com.mttl.vlms.entity.VehicleInfoExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.VehicleInfoMapper;
import com.mttl.vlms.setting.stt015.dto.UploadVehicleFileDto;
import com.mttl.vlms.setting.stt015.mapper.UploadVehicleFileCustomMapper;

/**
 * ChargesDaoImpl
 * 
 * 
 *
 */
@Repository("UploadVehicleFileDao")
@Transactional(propagation = Propagation.REQUIRED)
public class UploadVehicleFileDaoImpl extends BasicDAO implements UploadVehicleFileDao {

	/*
	 * @Autowired private UploadVehicleFileCustomMapper
	 * uploadVehicleFileCustomMapper;
	 */
	@Autowired
	private VehicleInfoMapper uploadVehicleFileMapper;

	@Autowired
	private BeanConverter beanConverter;

	@Autowired
	private UploadVehicleFileCustomMapper uploadVehicleFileCustomMapper;

	@ApplyAspect
	@Override
	public int insertUploadVehicleFile(UploadVehicleFileDto uploadVehicleFileDto, Integer createdUser) throws DAOException {
		int count;
		VehicleInfo vehicleEntity = beanConverter.convert(uploadVehicleFileDto, VehicleInfo.class);
		vehicleEntity.setCreatedDate(Utils.getCurrentTime());
		vehicleEntity.setCreatedUser(createdUser);
		System.out.println("haha");
		try {
			VehicleInfoExample checkChassis = new VehicleInfoExample();
			checkChassis.createCriteria().andChassisNoEqualTo(uploadVehicleFileDto.getChassisNo());

			System.out.println(uploadVehicleFileMapper.selectByExample(checkChassis).size());
			if (uploadVehicleFileMapper.selectByExample(checkChassis).size() < 1) {
				count = uploadVehicleFileMapper.insertSelective(vehicleEntity);

				uploadVehicleFileDto.setProcType("insert");

			} else {
				VehicleInfoExample example = new VehicleInfoExample();
				example.createCriteria().andChassisNoEqualTo(uploadVehicleFileDto.getChassisNo()).andDeleteFlgEqualTo(false);
				count = uploadVehicleFileMapper.updateByExampleSelective(vehicleEntity, example);
				uploadVehicleFileDto.setProcType("update");
			}
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

		return count;
	}

	@ApplyAspect
	@Override
	public String getDealerId(String dealerName) throws DAOException {
		String uploadVehicleFileDto;
		try {
			uploadVehicleFileDto = uploadVehicleFileCustomMapper.getDealerId(dealerName);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return uploadVehicleFileDto;
	}

	@ApplyAspect
	@Override
	public String getPortOfDischargeId(String disChargePortName) throws DAOException {
		String disChargePortId;
		try {
			disChargePortId = uploadVehicleFileCustomMapper.getPortOfDischargeId(disChargePortName);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return disChargePortId;
	}

}
