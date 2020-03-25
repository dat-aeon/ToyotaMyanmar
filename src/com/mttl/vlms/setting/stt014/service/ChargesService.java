package com.mttl.vlms.setting.stt014.service;

import java.util.List;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt014.dto.ChargesDto;

/**
 * ChargesService
 * 
 * 
 *
 */
public interface ChargesService {

	List<ChargesDto> findByTitle(String title) throws SystemException;

	int checkDuplicateTitle(ChargesDto chargesDto) throws SystemException;

	// This method is used for checking Charges is used in employee.
	// int checkChargesUsed(Integer id) throws SystemException;

	void insertCharges(ChargesDto chargesDto, Integer createdUser) throws SystemException;

	ChargesDto getChargesById(Integer id) throws SystemException;

	int updateCharges(ChargesDto chargesDto, Integer updatedUser) throws SystemException;

	int deleteCharges(ChargesDto chargesDto) throws SystemException;
}
