package com.mttl.vlms.setting.stt011.dao;

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
import com.mttl.vlms.common.selectdto.StateDivisionInfoSelectDto;
import com.mttl.vlms.common.selectmapper.SelectMapper;
import com.mttl.vlms.entity.Driver;
import com.mttl.vlms.entity.DriverExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.DriverMapper;
import com.mttl.vlms.setting.stt011.dto.DriverDto;
import com.mttl.vlms.setting.stt011.mapper.DriverCustomMapper;

/**
 * DriverDaoImpl
 * 
 * 
 *
 */
@Repository("DriverDao")
@Transactional(propagation = Propagation.REQUIRED)
public class DriverDaoImpl extends BasicDAO implements DriverDao {

	@Autowired
	DriverCustomMapper driverCustomMapper;

	@Autowired
	DriverMapper driverMapper;

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	BeanConverter beanConverter;

	@ApplyAspect
	@Override
	public List<DriverDto> searchDriverList(DriverDto driverDto) throws DAOException {
		List<DriverDto> result;
		try {
			result = driverCustomMapper.searchDriverList(driverDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	/*
	 * @ApplyAspect
	 * 
	 * @Override public int checkDuplicateDriverName(DriverDto driverDto) throws
	 * DAOException { int driverNameCount; try { driverNameCount =
	 * driverCustomMapper.checkDriverName(driverDto); } catch (RuntimeException
	 * e) { throw translate(e.getMessage(), e); } return driverNameCount; }
	 */

	@ApplyAspect
	@Override
	public int checkDuplicateDriverCode(DriverDto driverDto) throws DAOException {
		int driverCodeCount;
		try {
			driverCodeCount = driverCustomMapper.checkDriverCode(driverDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return driverCodeCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateStaffId(DriverDto driverDto) throws DAOException {
		int staffIdCount;
		try {
			staffIdCount = driverCustomMapper.checkStaffId(driverDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return staffIdCount;
	}

	@ApplyAspect

	@Override
	public int checkDriverUsed(Integer driverId) throws DAOException {
		int driverNameCount;
		try {
			driverNameCount = driverCustomMapper.checkDriverUsed(driverId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return driverNameCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateDriverNRCNo(DriverDto driverDto) throws DAOException {
		int driverNRCCount;
		try {
			driverNRCCount = driverCustomMapper.checkDuplicateDriverNRCNo(driverDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return driverNRCCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateLicenseNo(DriverDto driverDto) throws DAOException {
		int driverLicenseCount;
		try {
			driverLicenseCount = driverCustomMapper.checkDuplicateLicenseNo(driverDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return driverLicenseCount;
	}

	@ApplyAspect
	@Override
	public void insertDriver(DriverDto driverDto, Integer createdUser) throws DAOException {
		try {
			Date createdDate = Utils.getCurrentTime();
			Driver driver = beanConverter.convert(driverDto, Driver.class);
			driver.setCreatedDate(createdDate);
			driver.setCreatedUser(createdUser);
			driverMapper.insertSelective(driver);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public DriverDto getDriverById(Integer driverId) throws DAOException {
		DriverDto driverDto;
		try {
			driverDto = driverCustomMapper.getDriverById(driverId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return driverDto;
	}

	@ApplyAspect
	@Override
	public int updateDriver(DriverDto driverDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			driverDto.setBarcodeId(driverDto.getBarcodeId());
			driverDto.setDriverName(driverDto.getDriverName());
			Driver driver = beanConverter.convert(driverDto, Driver.class);
			driver.setUpdatedDate(updatedDate);
			driver.setUpdatedUser(updatedUser);
			DriverExample example = new DriverExample();
			example.createCriteria().andIdEqualTo(driver.getId()).andDeleteFlgEqualTo(false);
			count = driverMapper.updateByExampleSelective(driver, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteDriver(DriverDto driverDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			Driver driver = beanConverter.convert(driverDto, Driver.class);
			driver.setDeleteFlg(true);
			driver.setUpdatedUser(updatedUser);
			driver.setUpdatedDate(updatedDate);
			DriverExample driverExample = new DriverExample();
			driverExample.createCriteria().andIdEqualTo(driver.getId()).andDeleteFlgEqualTo(false);
			count = driverMapper.updateByExampleSelective(driver, driverExample);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public List<StateDivisionInfoSelectDto> getStateDivisionList() throws DAOException {
		List<StateDivisionInfoSelectDto> stateDivisionList;
		try {
			stateDivisionList = selectMapper.getStateDivisionList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return stateDivisionList;
	}

	@Override
	public List<DriverDto> getDriverList(DriverDto dto) throws DAOException {
		List<DriverDto> driverList;
		try {
			driverList = driverCustomMapper.getDriverList(dto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return driverList;
	}

	@ApplyAspect
	@Override
	public String getNextBPStaffId() throws DAOException {
		String staffId;
		try {
			staffId = driverCustomMapper.getNextBPStaffId();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return staffId;
	}

	@Override
	public int checkExpireDate(Integer id) throws DAOException {
		Integer driverId;

		try {
			driverId = driverCustomMapper.checkExpireDate(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return driverId;
	}

}
