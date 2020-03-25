package com.mttl.vlms.setting.stt014.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt014.dao.ChargesDao;
import com.mttl.vlms.setting.stt014.dto.ChargesDto;

/**
 * ChargesServiceImpl
 * 
 * 
 *
 */
@Service("ChargesService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class ChargesServiceImpl implements ChargesService {
	@Autowired
	ChargesDao chargesDao;

	@ApplyAspect
	@Override
	public List<ChargesDto> findByTitle(String title) throws SystemException {
		List<ChargesDto> result;
		try {
			result = chargesDao.searchByTitle(title);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateTitle(ChargesDto chargesDto) throws SystemException {
		int titleCount;
		try {
			titleCount = chargesDao.checkDuplicateTitle(chargesDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return titleCount;
	}

	/*
	 * @ApplyAspect
	 * 
	 * @Override public int checkChargesUsed(Integer id) throws SystemException
	 * { int chargesameCount; try { chargesameCount =
	 * chargesDao.checkChargesUsed(id); } catch (DAOException e) { throw new
	 * SystemException(e.getErrorCode(), e.getMessage(), e); } return
	 * chargesameCount; }
	 */

	@ApplyAspect
	@Override
	public void insertCharges(ChargesDto chargesDto, Integer createdUser) throws SystemException {
		try {
			chargesDao.insertCharges(chargesDto, createdUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public ChargesDto getChargesById(Integer id) throws SystemException {
		ChargesDto chargesDto;
		try {
			chargesDto = chargesDao.getChargesById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return chargesDto;
	}

	@ApplyAspect
	@Override
	public int updateCharges(ChargesDto chargesDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = chargesDao.updateCharges(chargesDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteCharges(ChargesDto chargesDto) throws SystemException {
		int count;
		try {
			count = chargesDao.deleteCharges(chargesDto.getId());
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}
}