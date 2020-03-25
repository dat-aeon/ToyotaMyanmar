package com.mttl.vlms.setting.stt012.dao;

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
import com.mttl.vlms.common.selectmapper.SelectMapper;
import com.mttl.vlms.entity.Dealer;
import com.mttl.vlms.entity.DealerExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.DealerMapper;
import com.mttl.vlms.setting.stt012.dto.DealerDto;
import com.mttl.vlms.setting.stt012.mapper.DealerCustomMapper;

/**
 * DealerDaoImpl
 * 
 * 
 *
 */
@Repository("DealerDao")
@Transactional(propagation = Propagation.REQUIRED)
public class DealerDaoImpl extends BasicDAO implements DealerDao {

	@Autowired
	DealerCustomMapper dealerCustomMapper;

	@Autowired
	DealerMapper dealerMapper;

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	BeanConverter beanConverter;

	@ApplyAspect
	@Override
	public List<DealerDto> searchByDealerName(String dealerName) throws DAOException {
		List<DealerDto> result;
		try {
			result = dealerCustomMapper.searchByDealerName(dealerName);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateDealerName(DealerDto dealerDto) throws DAOException {
		int dealerNameCount;
		try {
			dealerNameCount = dealerCustomMapper.checkDealerName(dealerDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return dealerNameCount;
	}

	@ApplyAspect
	@Override
	public int checkDuplicateDealerCode(DealerDto dealerDto) throws DAOException {
		int dealerCodeCount;
		try {
			dealerCodeCount = dealerCustomMapper.checkDealerCode(dealerDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return dealerCodeCount;
	}

	/*
	 * @ApplyAspect
	 * 
	 * @Override public int checkDealerUsed(Integer dealerId) throws
	 * DAOException { int dealerNameCount; try { dealerNameCount =
	 * dealerCustomMapper.checkDealerUsed(dealerId); } catch (RuntimeException
	 * e) { throw translate(e.getMessage(), e); } return dealerNameCount; }
	 */

	@ApplyAspect
	@Override
	public void insertDealer(DealerDto dealerDto, Integer createdUser) throws DAOException {
		try {
			Date createdDate = Utils.getCurrentTime();
			Dealer dealer = beanConverter.convert(dealerDto, Dealer.class);
			dealer.setCreatedDate(createdDate);
			dealer.setCreatedUser(createdUser);
			dealerMapper.insertSelective(dealer);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public DealerDto getDealerById(Integer dealerId) throws DAOException {
		DealerDto dealerDto;
		try {
			dealerDto = dealerCustomMapper.getDealerById(dealerId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return dealerDto;
	}

	@ApplyAspect
	@Override
	public int updateDealer(DealerDto dealerDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			dealerDto.setDealerCode(dealerDto.getDealerCode());
			dealerDto.setDealerName(dealerDto.getDealerName());
			Dealer dealer = beanConverter.convert(dealerDto, Dealer.class);
			dealer.setUpdatedDate(updatedDate);
			dealer.setUpdatedUser(updatedUser);
			DealerExample example = new DealerExample();
			example.createCriteria().andIdEqualTo(dealer.getId()).andDeleteFlgEqualTo(false);
			count = dealerMapper.updateByExampleSelective(dealer, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteDealer(DealerDto dealerDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			Dealer dealer = beanConverter.convert(dealerDto, Dealer.class);
			dealer.setDeleteFlg(true);
			dealer.setUpdatedUser(updatedUser);
			dealer.setUpdatedDate(updatedDate);
			DealerExample dealerExample = new DealerExample();
			dealerExample.createCriteria().andDealerCodeEqualTo(dealer.getDealerCode()).andDeleteFlgEqualTo(false);
			count = dealerMapper.updateByExampleSelective(dealer, dealerExample);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public List<DealerSelectDto> getDealerList() throws DAOException {
		List<DealerSelectDto> dealerList;
		try {
			dealerList = selectMapper.getDealerList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return dealerList;
	}

}
