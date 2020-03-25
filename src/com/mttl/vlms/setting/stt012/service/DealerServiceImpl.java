package com.mttl.vlms.setting.stt012.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt012.dao.DealerDao;
import com.mttl.vlms.setting.stt012.dto.DealerDto;

/**
 * DealerServiceImpl
 * 
 * 
 *
 */
@Service("DealerService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class DealerServiceImpl implements DealerService {

	@Autowired
	DealerDao dealerDao;

	@ApplyAspect
	@Override
	public List<DealerDto> findByDealerName(String dealerName) throws SystemException {
		List<DealerDto> result;
		try {
			result = dealerDao.searchByDealerName(dealerName);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateDealerName(DealerDto dealerDto) throws SystemException {
		int dealerNameCount;
		try {
			dealerNameCount = dealerDao.checkDuplicateDealerName(dealerDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return dealerNameCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateDealerCode(DealerDto dealerDto) throws SystemException {
		int dealerCodeCount;
		try {
			dealerCodeCount = dealerDao.checkDuplicateDealerCode(dealerDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return dealerCodeCount;
	}

	/*
	 * @ApplyAspect
	 * 
	 * @Override public int checkDealerUsed(Integer dealerId) throws
	 * SystemException { int dealerCount; try { dealerCount =
	 * dealerDao.checkDealerUsed(dealerId); } catch (DAOException e) { throw new
	 * SystemException(e.getErrorCode(), e.getMessage(), e); } return
	 * dealerCount; }
	 */

	@ApplyAspect
	@Override
	public void insertDealer(DealerDto dealerDto, Integer createdUser) throws SystemException {
		try {
			dealerDao.insertDealer(dealerDto, createdUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public DealerDto getDealerById(Integer id) throws SystemException {
		DealerDto dealerDto;
		try {
			dealerDto = dealerDao.getDealerById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return dealerDto;
	}

	@ApplyAspect
	@Override
	public int updateDealer(DealerDto dealerDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = dealerDao.updateDealer(dealerDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteDealer(DealerDto dealerDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = dealerDao.deleteDealer(dealerDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@Override
	public List<DealerSelectDto> getDealerList() throws SystemException {
		List<DealerSelectDto> dealerList;
		try {
			dealerList = dealerDao.getDealerList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return dealerList;
	}

}
