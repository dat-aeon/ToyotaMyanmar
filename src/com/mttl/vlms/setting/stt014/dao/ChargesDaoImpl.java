package com.mttl.vlms.setting.stt014.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.common.BeanConverter;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.entity.Charges;
import com.mttl.vlms.entity.ChargesExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.ChargesMapper;
import com.mttl.vlms.setting.stt014.dto.ChargesDto;
import com.mttl.vlms.setting.stt014.mapper.ChargesCustomMapper;

/**
 * ChargesDaoImpl
 * 
 * 
 *
 */
@Repository("ChargesDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ChargesDaoImpl extends BasicDAO implements ChargesDao {

	@Autowired
	private ChargesCustomMapper chargesCustomMapper;

	@Autowired
	private ChargesMapper chargesMapper;

	@Autowired
	private BeanConverter beanConverter;

	@ApplyAspect
	@Override
	public List<ChargesDto> searchByTitle(String title) throws DAOException {
		List<ChargesDto> result;
		try {
			result = chargesCustomMapper.searchByTitle(title);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateTitle(ChargesDto chargesDto) throws DAOException {
		int titleCount;
		try {
			titleCount = chargesCustomMapper.checkTitle(chargesDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return titleCount;
	}

	/*
	 * @ApplyAspect
	 * 
	 * @Override public int checkChargesUsed(Integer id) throws DAOException {
	 * int titleCount; try { titleCount =
	 * chargesCustomMapper.checkChargesUsed(id); } catch (RuntimeException e) {
	 * throw translate(e.getMessage(), e); } return titleCount; }
	 */

	@ApplyAspect
	@Override
	public void insertCharges(ChargesDto chargesDto, Integer createdUser) throws DAOException {
		Charges chargesEntity = beanConverter.convert(chargesDto, Charges.class);
		chargesEntity.setCreatedDate(Utils.getCurrentTime());
		chargesEntity.setCreatedUser(createdUser);
		try {
			chargesMapper.insertSelective(chargesEntity);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public ChargesDto getChargesById(Integer id) throws DAOException {
		ChargesDto chargesDto;
		try {
			chargesDto = chargesCustomMapper.getChargesById(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return chargesDto;
	}

	@ApplyAspect
	@Override
	public int updateCharges(ChargesDto chargesDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Charges chargesEntity = beanConverter.convert(chargesDto, Charges.class);
			chargesEntity.setUpdatedDate(Utils.getCurrentTime());
			chargesEntity.setUpdatedUser(updatedUser);
			ChargesExample example = new ChargesExample();
			example.createCriteria().andIdEqualTo(chargesEntity.getId());
			if (chargesEntity.getTitle() == null) {
				chargesEntity.setTitle("");
			}
			count = chargesMapper.updateByExampleSelective(chargesEntity, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteCharges(Integer id) throws DAOException {
		int count;
		try {
			count = chargesMapper.deleteByPrimaryKey(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

}
