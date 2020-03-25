package com.mttl.vlms.operation.opr004.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.common.BeanConverter;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.DriverSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectdto.YardSelectDto;
import com.mttl.vlms.entity.InspectVehicleAttachmentExample;
import com.mttl.vlms.entity.VehicleInfo;
import com.mttl.vlms.entity.VehicleInfoExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.InspectVehicleAttachmentMapper;
import com.mttl.vlms.mapper.InspectVehicleMapper;
import com.mttl.vlms.mapper.VehicleInfoMapper;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoDto;
import com.mttl.vlms.operation.opr004.dto.VehicleProcessInfoSearchReqDto;
import com.mttl.vlms.operation.opr004.mapper.VehicleProcessInfoCustomMapper;

/**
 * VehicleProcessInfoDaoImpl
 * 
 * 
 *
 */
@Repository("VehicleProcessInfoDao")
@Transactional(propagation = Propagation.REQUIRED)
public class VehicleProcessInfoDaoImpl extends BasicDAO implements VehicleProcessInfoDao {

	@Autowired
	VehicleProcessInfoCustomMapper vehicleProcessInfoCustomMapper;

	@Autowired
	InspectVehicleAttachmentMapper inspectVehicleAttachmentMapper;

	@Autowired
	InspectVehicleMapper inspectVehicleMapper;

	@Autowired
	VehicleInfoMapper vehicleInfoMapper;

	@Autowired
	BeanConverter beanConverter;

	@ApplyAspect
	@Override
	public List<VehicleProcessInfoDto> searchByChassisNo(VehicleProcessInfoSearchReqDto vehicleProcessInfoSearchReqDto, VehicleProcessInfoDto vehicleProcessInfoDto)
			throws DAOException {
		List<VehicleProcessInfoDto> result;
		try {
			result = vehicleProcessInfoCustomMapper.searchByChassisNo(vehicleProcessInfoSearchReqDto.getOffSet(), vehicleProcessInfoSearchReqDto.getLimit(),
					vehicleProcessInfoSearchReqDto.getSortField(), vehicleProcessInfoSearchReqDto.getSortOrder(), vehicleProcessInfoSearchReqDto.getFilters(),
					vehicleProcessInfoDto);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public VehicleProcessInfoDto getVehicleProcessInfoById(String chassisNo) throws DAOException {
		VehicleProcessInfoDto vehicleProcessInfoDto;
		try {
			vehicleProcessInfoDto = vehicleProcessInfoCustomMapper.getVehicleProcessInfoById(chassisNo);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return vehicleProcessInfoDto;
	}

	@ApplyAspect

	@Override
	public int deleteVehicleProcessInfo(VehicleProcessInfoDto vehicleProcessInfoDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			VehicleInfo vehicleProcessInfo = beanConverter.convert(vehicleProcessInfoDto, VehicleInfo.class);
			vehicleProcessInfo.setDeleteFlg(true);
			vehicleProcessInfo.setUpdatedUser(updatedUser);
			vehicleProcessInfo.setUpdatedDate(updatedDate);
			VehicleInfoExample vehicleInfoExample = new VehicleInfoExample();
			vehicleInfoExample.createCriteria().andIdEqualTo(vehicleProcessInfoDto.getVehicleInfoId()).andDeleteFlgEqualTo(false);
			count = vehicleInfoMapper.updateByExampleSelective(vehicleProcessInfo, vehicleInfoExample);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public List<DriverSelectDto> getDriverList(Integer processType) throws DAOException {
		List<DriverSelectDto> driverList;
		try {
			driverList = vehicleProcessInfoCustomMapper.getDriverList(processType);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return driverList;
	}

	@Override
	public List<TaskSelectDto> getTaskList(Integer processType) throws DAOException {
		List<TaskSelectDto> taskList;
		try {
			taskList = vehicleProcessInfoCustomMapper.getTaskList(processType);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return taskList;
	}

	@Override
	public List<DealerSelectDto> getDealerList(Integer processType) throws DAOException {
		List<DealerSelectDto> taskList;
		try {
			taskList = vehicleProcessInfoCustomMapper.getDealerList(processType);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return taskList;
	}

	@Override
	public List<YardSelectDto> getYardList(Integer processType) throws DAOException {
		List<YardSelectDto> taskList;
		try {
			taskList = vehicleProcessInfoCustomMapper.getYardList(processType);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return taskList;
	}

	@Override
	public List<VehicleProcessInfoDto> searchDetailView(String chassisNo, Integer processType) throws DAOException {
		List<VehicleProcessInfoDto> detailViewList;
		try {
			detailViewList = vehicleProcessInfoCustomMapper.searchDetailView(chassisNo, processType);
			System.out.println("lol");
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return detailViewList;
	}

	@Override
	public List<VehicleProcessInfoDto> searchMainCheck(Integer taskId, Integer vehicleId) throws DAOException {
		List<VehicleProcessInfoDto> detailViewList;
		try {
			detailViewList = vehicleProcessInfoCustomMapper.searchMainCheck(taskId, vehicleId);
			System.out.println("lol");
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return detailViewList;
	}

	@Override
	public List<String> getChassisNoList(String chassisNo, Integer processType) throws DAOException {
		List<String> chassisList;
		try {
			chassisList = vehicleProcessInfoCustomMapper.getChassisNoList(chassisNo, processType);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return chassisList;
	}

	@Override
	public List<String> getImageList(Integer vehicleInfoId) throws DAOException {
		List<String> imagesList;
		try {
			imagesList = vehicleProcessInfoCustomMapper.getImageList(vehicleInfoId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return imagesList;
	}

	@Override
	public int getImageCountByInspectVehicleId(Integer inspectVehicleId) throws DAOException {

		int count = 0;
		try {

			InspectVehicleAttachmentExample example = new InspectVehicleAttachmentExample();
			example.createCriteria().andInspectVehicleIdEqualTo(inspectVehicleId);

			count = inspectVehicleAttachmentMapper.countByExample(example);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public Integer getCountBySearchTable(VehicleProcessInfoDto vehicleProcessInfoSearchDto) throws DAOException {
		Integer count = 0;
		try {
			count = vehicleProcessInfoCustomMapper.getCountBySearchTable(vehicleProcessInfoSearchDto);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}
}
