package com.mttl.vlms.dashboard.dsb001.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.dashboard.dsb001.mapper.DashboardMapper;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt017.dto.YardDto;

/**
 * CountryDaoImpl
 * 
 * 
 *
 */
@Repository("YardMeterDao")
@Transactional(propagation = Propagation.REQUIRED)
public class YardMeterDaoImpl extends BasicDAO implements YardMeterDao {

	@Autowired
	DashboardMapper yardMeterCustomMapper;

	@ApplyAspect
	@Override
	public int searchYardList() throws DAOException {
		int yardcount;
		/* List<Yard> yardList; */
		try {
			yardcount = yardMeterCustomMapper.searchYard();

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return yardcount;
	}

	@Override
	public List<YardDto> findYardList() throws DAOException {
		List<YardDto> result;
		try {
			result = yardMeterCustomMapper.findYardList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int findTotalSpace(int id) throws DAOException {
		int count;
		try {
			count = yardMeterCustomMapper.findTotalSpace(id);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int findRestSpace(int id) throws DAOException {
		int count;
		try {
			count = yardMeterCustomMapper.findRestSpace(id);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int findSpace(int id) throws DAOException {
		int count;
		try {
			count = yardMeterCustomMapper.findSpace(id);

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int getEtaVehicleNo() throws DAOException {
		int count;
		try {
			count = yardMeterCustomMapper.getEtaVehicleNo();

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int getAudioInstallVehicle() throws DAOException {
		int count;
		try {
			count = yardMeterCustomMapper.getAudioInstallVehicle();

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int getPdiVehicle() throws DAOException {
		int count;
		try {
			count = yardMeterCustomMapper.getPdiVehicle();

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int countLicenseExpireDriver() throws DAOException {
		int count;
		try {
			count = yardMeterCustomMapper.countLicenseExpireDriver();

		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

}
