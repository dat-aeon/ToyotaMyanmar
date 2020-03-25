package com.mttl.vlms.setting.stt014.dao;

import java.util.List;

import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt014.dto.ChargesDto;

/**
 * ChargesDao
 * 
 * 
 *
 */
public interface ChargesDao {

	List<ChargesDto> searchByTitle(String Title) throws DAOException;

	int checkDuplicateTitle(ChargesDto chargesDto) throws DAOException;

	// int checkChargesUsed(Integer id) throws DAOException;

	void insertCharges(ChargesDto chargesDto, Integer createdUser) throws DAOException;

	ChargesDto getChargesById(Integer id) throws DAOException;

	int updateCharges(ChargesDto chargesDto, Integer updatedUser) throws DAOException;

	int deleteCharges(Integer id) throws DAOException;
}
