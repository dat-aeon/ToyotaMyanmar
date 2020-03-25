package com.mttl.vlms.setting.stt012.dao;

import java.util.List;

import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt012.dto.DealerDto;

/**
 * DealerDao
 * 
 * 
 *
 */
public interface DealerDao {
	List<DealerDto> searchByDealerName(String dealerName) throws DAOException;

	List<DealerSelectDto> getDealerList() throws DAOException;

	int checkDuplicateDealerName(DealerDto dealerDto) throws DAOException;

	int checkDuplicateDealerCode(DealerDto dealerDto) throws DAOException;

	// int checkDealerUsed(Integer dealerId) throws DAOException;

	void insertDealer(DealerDto dealerDto, Integer createdUser) throws DAOException;

	DealerDto getDealerById(Integer dealerId) throws DAOException;

	int updateDealer(DealerDto dealerDto, Integer updatedUser) throws DAOException;

	int deleteDealer(DealerDto dealerDto, Integer updatedUser) throws DAOException;

}
