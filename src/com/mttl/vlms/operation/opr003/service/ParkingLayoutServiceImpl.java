package com.mttl.vlms.operation.opr003.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.operation.opr003.dao.ParkingLayoutDao;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;

/**
 * YardServiceImpl
 * 
 * 
 *
 */
@Service("ParkingLayoutService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class ParkingLayoutServiceImpl implements ParkingLayoutService {

	@Autowired
	ParkingLayoutDao parkingLayoutDao;

	@Override
	public List<YardDto> getYardList() throws SystemException {
		List<YardDto> yardList;
		try {
			yardList = parkingLayoutDao.getYardList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return yardList;
	}

	@ApplyAspect
	@Override
	public List<ZoneDto> getByRowColumnEachZone(Integer yardId) throws SystemException {
		List<ZoneDto> result;
		try {
			result = parkingLayoutDao.getByRowColumnEachZone(yardId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public List<ZoneDto> getZoneListByYardId(int yardId) throws SystemException {

		List<ZoneDto> zoneDtoList;

		try {

			zoneDtoList = parkingLayoutDao.getZoneListByYardId(yardId);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}

		return zoneDtoList;
	}

}
