package com.mttl.vlms.setting.stt017.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt017.dao.YardDao;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;

/**
 * YardServiceImpl
 * 
 * 
 *
 */
@Service("YardService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class YardServiceImpl implements YardService {

	@Autowired
	YardDao yardDao;

	@ApplyAspect
	@Override
	public List<YardDto> findByYardName(String name) throws SystemException {
		List<YardDto> result;
		try {
			result = yardDao.searchByYardName(name);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public List<ZoneDto> getByRowColumnEachZone(Integer yardId) throws SystemException {
		List<ZoneDto> result;
		try {
			result = yardDao.getByRowColumnEachZone(yardId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<ZoneDto> findByYardFkId(Integer yardFkId) throws SystemException {
		List<ZoneDto> result;
		try {
			result = yardDao.searchByYardFkId(yardFkId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateYardName(YardDto yardDto) throws SystemException {
		int yardNameCount;
		try {
			yardNameCount = yardDao.checkDuplicateYardName(yardDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return yardNameCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateYardCode(YardDto yardDto) throws SystemException {
		int yardCodeCount;
		try {
			yardCodeCount = yardDao.checkDuplicateYardCode(yardDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return yardCodeCount;
	}

	@ApplyAspect
	@Override
	public int checkYardUsed(Integer id) throws SystemException {
		int yardCount;
		try {
			yardCount = yardDao.checkYardUsed(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return yardCount;
	}

	@ApplyAspect
	@Override
	public YardDto getYardById(Integer yardId) throws SystemException {
		YardDto yardDto;
		try {
			yardDto = yardDao.getYardById(yardId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return yardDto;
	}

	@ApplyAspect
	@Override
	public int updateYard(YardDto yardDto, Integer updatedUser, List<ZoneDto> zoneUpdateDtoList) throws SystemException {
		int count;
		try {
			count = yardDao.updateYard(yardDto, updatedUser, zoneUpdateDtoList);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteYard(YardDto yardDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = yardDao.deleteYard(yardDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public void insertYard(YardDto yardDto, Integer createdUser) throws SystemException {
		try {
			yardDao.insertYard(yardDto, createdUser);

			for (ZoneDto zoneDto : yardDto.getZoneDtoList()) {
				zoneDto.setYardId(yardDto.getId());
			}
			yardDao.insertZone(yardDto.getZoneDtoList(), createdUser);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public List<YardDto> getYardList() throws SystemException {

		List<YardDto> yardDtoList;

		try {

			yardDtoList = yardDao.getYardList();

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}

		return yardDtoList;
	}

	@ApplyAspect
	@Override
	public List<ZoneDto> getZoneListByYardId(int yardId) throws SystemException {

		List<ZoneDto> zoneDtoList;

		try {

			zoneDtoList = yardDao.getZoneListByYardId(yardId);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}

		return zoneDtoList;
	}
}
