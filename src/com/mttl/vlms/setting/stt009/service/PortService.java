package com.mttl.vlms.setting.stt009.service;

import java.util.List;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt009.dto.PortDto;

/**
 * PortService
 * 
 * 
 *
 */
public interface PortService {

	List<PortDto> findByPortName(String portName) throws SystemException;

	/* int checkDuplicateShortName(PortDto portDto) throws SystemException; */

	int checkDuplicateFullName(PortDto portDto) throws SystemException;

	int checkPortUsed(Integer portId) throws SystemException;

	void insertPort(PortDto portDto, Integer createdUser) throws SystemException;

	PortDto getPortById(Integer portId) throws SystemException;

	int updatePort(PortDto portDto, Integer updatedUser) throws SystemException;

	int deletePort(PortDto portDto, Integer updatedUser) throws SystemException;

	int checkDuplicateShortName(Integer id, String shortName) throws SystemException;

}
