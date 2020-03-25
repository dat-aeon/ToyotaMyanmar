package com.mttl.vlms.setting.stt012.service;

import java.util.List;

import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt012.dto.DealerDto;

/**
 * DealerService
 * 
 * 
 *
 */
public interface DealerService {

	List<DealerDto> findByDealerName(String dealerName) throws SystemException;

	int checkDuplicateDealerName(DealerDto dealerDto) throws SystemException;

	int checkDuplicateDealerCode(DealerDto dealerDto) throws SystemException;

	// int checkDealerUsed(Integer dealerId) throws SystemException;

	void insertDealer(DealerDto dealerDto, Integer createdUser) throws SystemException;

	DealerDto getDealerById(Integer id) throws SystemException;

	int updateDealer(DealerDto dealerDto, Integer updatedUser) throws SystemException;

	int deleteDealer(DealerDto dealerDto, Integer updatedUser) throws SystemException;

	List<DealerSelectDto> getDealerList() throws SystemException;

}
