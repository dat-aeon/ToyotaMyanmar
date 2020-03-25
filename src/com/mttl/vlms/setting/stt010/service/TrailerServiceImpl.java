package com.mttl.vlms.setting.stt010.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt010.dao.TrailerDao;
import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.dto.TrailerSearchReqDto;

/**
 * TrailerServiceImpl
 * 
 * 
 *
 */
@Service("TrailerService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class TrailerServiceImpl implements TrailerService {

	@Autowired
	private TrailerDao trailerDao;

	/*
	 * @ApplyAspect
	 * 
	 * @Override public List<TrailerDto> getTrailerList(String trailerType)
	 * throws SystemException { List<TrailerDto> result; try { result =
	 * trailerDao.getTrailerList(trailerType); } catch (DAOException e) { throw
	 * new SystemException(e.getErrorCode(), e.getMessage(), e); } return
	 * result; }
	 */

	@Override
	@ApplyAspect
	public List<TrailerDto> getTrailerList(TrailerSearchReqDto trailerSearchReqDto) throws SystemException {
		List<TrailerDto> trailerList;
		try {
			trailerList = trailerDao.getTrailerList(trailerSearchReqDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return trailerList;
	}

	@Override
	@ApplyAspect
	public Integer getTrailerListCount(TrailerSearchReqDto trailerSearchReqDto) throws SystemException {
		Integer trailerCount;
		try {
			trailerCount = trailerDao.getTrailerListCount(trailerSearchReqDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return trailerCount;
	}

	@ApplyAspect
	@Override
	public void insertTrailer(TrailerDto trailerDto, Integer createdBy) throws SystemException {
		try {
			trailerDao.insertTrailer(trailerDto, createdBy);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public TrailerDto getTrailerByTrailerId(Integer trailerId) throws SystemException {
		TrailerDto trailerDto;
		try {
			trailerDto = trailerDao.getTrailerByTrailerId(trailerId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return trailerDto;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateLicenseNo(TrailerDto trailerDto) throws SystemException {
		int licenseNoCount;
		try {
			licenseNoCount = trailerDao.checkDuplicateLicenseNo(trailerDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return licenseNoCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateTrailerId(TrailerDto trailerDto) throws SystemException {
		int trailerIdCount;
		try {
			trailerIdCount = trailerDao.checkDuplicateTrailerId(trailerDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return trailerIdCount;
	}

	@ApplyAspect
	@Override
	public int updateTrailer(TrailerDto trailerDto, Integer updatedBy) throws SystemException {
		int count;
		try {
			count = trailerDao.updateTrailer(trailerDto, updatedBy);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteTrailer(TrailerDto trailerDto, Integer updatedBy) throws SystemException {
		int count;
		try {
			count = trailerDao.deleteTrailer(trailerDto, updatedBy);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int disabledTrailer(TrailerDto trailerDto, Integer updatedBy) throws SystemException {
		int count;
		try {
			count = trailerDao.disabledTrailer(trailerDto, updatedBy);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public String getNextTrailerId() throws SystemException {

		String trailerCode;

		try {
			trailerCode = trailerDao.getNextTrailerId();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return trailerCode;
	}

	@Override
	public List<TrailerDto> getMainTrailerList() throws SystemException {
		List<TrailerDto> list;
		try {
			list = trailerDao.getMainTrailerList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return list;
	}

}
