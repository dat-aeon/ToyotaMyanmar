package com.mttl.vlms.operation.opr002.dao;

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
import com.mttl.vlms.common.selectdto.VehicleInfoSelectDto;
import com.mttl.vlms.common.selectmapper.SelectMapper;
import com.mttl.vlms.entity.VehicleInfo;
import com.mttl.vlms.entity.VehicleInfoExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.VehicleInfoMapper;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoSearchReqDto;
import com.mttl.vlms.operation.opr002.mapper.VehicleInfoCustomMapper;

/**
 * VehicleInfoDaoImpl
 * 
 * 
 *
 */
@Repository("VehicleInfoDao")
@Transactional(propagation = Propagation.REQUIRED)
public class VehicleInfoDaoImpl extends BasicDAO implements VehicleInfoDao {

	@Autowired
	VehicleInfoCustomMapper vehicleInfoCustomMapper;

	@Autowired
	VehicleInfoMapper vehicleInfoMapper;

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	BeanConverter beanConverter;

	@ApplyAspect
	@Override
	public int checkDuplicateChassisNo(VehicleInfoDto vehicleInfoDto) throws DAOException {
		int chassisNoCount;
		try {
			chassisNoCount = vehicleInfoCustomMapper.checkChassisNo(vehicleInfoDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return chassisNoCount;
	}

	@ApplyAspect
	@Override
	public List<VehicleInfoDto> searchVehicleInfoList(VehicleInfoSearchReqDto vehicleInfoSearchReqDto, VehicleInfoDto vehicleInfoDto) throws DAOException {
		List<VehicleInfoDto> result;
		try {
			result = vehicleInfoCustomMapper.searchVehicleInfoList(vehicleInfoSearchReqDto.getOffSet(), vehicleInfoSearchReqDto.getLimit(), vehicleInfoSearchReqDto.getSortField(),
					vehicleInfoSearchReqDto.getSortOrder(), vehicleInfoSearchReqDto.getFilters(), vehicleInfoDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkVehicleInfoUsed(Integer id) throws DAOException {
		int vehicleInfoIdCount;
		try {
			vehicleInfoIdCount = vehicleInfoCustomMapper.checkVehicleInfoUsed(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return vehicleInfoIdCount;
	}

	@ApplyAspect
	@Override
	public void insertVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer createdUser) throws DAOException {
		try {
			Date createdDate = Utils.getCurrentTime();
			VehicleInfo vehicleInfo = beanConverter.convert(vehicleInfoDto, VehicleInfo.class);
			vehicleInfo.setCreatedDate(createdDate);
			vehicleInfo.setCreatedUser(createdUser);
			vehicleInfoMapper.insertSelective(vehicleInfo);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public VehicleInfoDto getVehicleInfoById(Integer vehicleInfoId) throws DAOException {
		VehicleInfoDto vehicleInfoDto;
		try {
			vehicleInfoDto = vehicleInfoCustomMapper.getVehicleInfoById(vehicleInfoId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return vehicleInfoDto;
	}

	@ApplyAspect
	@Override
	public int updateVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			vehicleInfoDto.setChassisNo(vehicleInfoDto.getChassisNo());
			vehicleInfoDto.setModel(vehicleInfoDto.getModel());
			VehicleInfo vehicleInfo = beanConverter.convert(vehicleInfoDto, VehicleInfo.class);
			vehicleInfo.setUpdatedDate(updatedDate);
			vehicleInfo.setUpdatedUser(updatedUser);
			VehicleInfoExample example = new VehicleInfoExample();
			example.createCriteria().andIdEqualTo(vehicleInfo.getId()).andDeleteFlgEqualTo(false);

			if (null == vehicleInfo.getOrderBy()) {
				vehicleInfo.setOrderBy("");
			}
			if (null == vehicleInfo.getFirstVesselName()) {
				vehicleInfo.setFirstVesselName("");
			}
			if (null == vehicleInfo.getSecondVesselName()) {
				vehicleInfo.setSecondVesselName("");
			}
			if (null == vehicleInfo.getCif()) {
				vehicleInfo.setCif(null);
			}
			if (null == vehicleInfo.getEtdOrigin()) {
				vehicleInfo.setEtdOrigin("");
			}
			if (null == vehicleInfo.getAgentName()) {
				vehicleInfo.setAgentName("");
			}
			if (null == vehicleInfo.getStockOf()) {
				vehicleInfo.setStockOf("");
			}
			if (null == vehicleInfo.getConsigneeOnBl()) {
				vehicleInfo.setConsigneeOnBl("");
			}
			if (null == vehicleInfo.getPortCarriedOutRemark()) {
				vehicleInfo.setPortCarriedOutRemark("");
			}
			if (null == vehicleInfo.getRemark()) {
				vehicleInfo.setRemark("");
			}

			count = vehicleInfoMapper.updateByExampleSelective(vehicleInfo, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteVehicleInfo(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			VehicleInfo vehicleInfo = beanConverter.convert(vehicleInfoDto, VehicleInfo.class);
			vehicleInfo.setDeleteFlg(true);
			vehicleInfo.setUpdatedUser(updatedUser);
			vehicleInfo.setUpdatedDate(updatedDate);
			VehicleInfoExample vehicleInfoExample = new VehicleInfoExample();
			vehicleInfoExample.createCriteria().andIdEqualTo(vehicleInfo.getId()).andDeleteFlgEqualTo(false);
			count = vehicleInfoMapper.updateByExampleSelective(vehicleInfo, vehicleInfoExample);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public List<VehicleInfoSelectDto> getVehicleInfoList() throws DAOException {
		List<VehicleInfoSelectDto> vehicleInfoList;
		try {
			vehicleInfoList = selectMapper.getVehicleInfoList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return vehicleInfoList;
	}

	@Override
	public List<String> getChassisNoList(String chassisNo) throws DAOException {
		List<String> list;
		try {
			list = vehicleInfoCustomMapper.getChassisNoList(chassisNo);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public List<VehicleInfoDto> getPortList() throws DAOException {
		List<VehicleInfoDto> result;
		try {
			result = vehicleInfoCustomMapper.getPortList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<VehicleInfoDto> getDealerList() throws DAOException {
		List<VehicleInfoDto> result;
		try {
			result = vehicleInfoCustomMapper.getDealerList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public void changeUpdate(VehicleInfoDto vehicleInfoDto, Integer updatedUser) throws DAOException {
		try {
			Date updatedDate = Utils.getCurrentTime();
			vehicleInfoDto.setChassisNo(vehicleInfoDto.getChassisNo());
			vehicleInfoDto.setModel(vehicleInfoDto.getModel());
			VehicleInfo vehicleInfo = beanConverter.convert(vehicleInfoDto, VehicleInfo.class);
			vehicleInfo.setUpdatedDate(updatedDate);
			vehicleInfo.setUpdatedUser(updatedUser);
			VehicleInfoExample example = new VehicleInfoExample();
			example.createCriteria().andIdEqualTo(vehicleInfo.getId()).andDeleteFlgEqualTo(false);
			if (vehicleInfo.getDischargePortId() == null) {
				vehicleInfo.setDischargePortId(null);
			}
			if (vehicleInfo.getDealerId() == null) {
				vehicleInfo.setDealerId(null);
			}
			if (null == vehicleInfo.getOrderBy()) {
				vehicleInfo.setOrderBy("");
			}
			if (null == vehicleInfo.getFirstVesselName()) {
				vehicleInfo.setFirstVesselName("");
			}
			if (null == vehicleInfo.getSecondVesselName()) {
				vehicleInfo.setSecondVesselName("");
			}
			if (null == vehicleInfo.getCif()) {
				vehicleInfo.setCif(null);
			}
			if (null == vehicleInfo.getEtdOrigin()) {
				vehicleInfo.setEtdOrigin("");
			}
			if (null == vehicleInfo.getAgentName()) {
				vehicleInfo.setAgentName("");
			}
			if (null == vehicleInfo.getStockOf()) {
				vehicleInfo.setStockOf("");
			}
			if (null == vehicleInfo.getConsigneeOnBl()) {
				vehicleInfo.setConsigneeOnBl("");
			}
			if (null == vehicleInfo.getPortCarriedOutRemark()) {
				vehicleInfo.setPortCarriedOutRemark("");
			}
			if (null == vehicleInfo.getRemark()) {
				vehicleInfo.setRemark("");
			}
			vehicleInfoMapper.updateByExampleSelective(vehicleInfo, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}

	}

	@Override
	public List<VehicleInfoDto> searchAfterUpdate(VehicleInfoDto vehicleInfoDto) throws DAOException {
		List<VehicleInfoDto> result;
		try {
			result = vehicleInfoCustomMapper.searchAfterUpdate(vehicleInfoDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<String> getStatusList() throws DAOException {
		List<String> list;
		try {
			list = vehicleInfoCustomMapper.getStatusList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public int checkDuplicateLicenseNo(VehicleInfoDto vehicleInfoDto) throws DAOException {
		int count;
		try {
			count = vehicleInfoCustomMapper.checkDuplicateLicenseNo(vehicleInfoDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@Override
	@ApplyAspect
	public List<DealerSelectDto> getDealerSelectList() throws DAOException {
		List<DealerSelectDto> dealerList;
		try {
			dealerList = selectMapper.getDealerList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return dealerList;
	}
}
