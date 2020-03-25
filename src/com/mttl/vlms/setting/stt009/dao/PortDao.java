package com.mttl.vlms.setting.stt009.dao;

import java.util.List;

import com.mttl.vlms.common.selectdto.PortSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt009.dto.PortDto;

/**
 * PortDao
 * 
 * 
 *
 */
public interface PortDao {
	List<PortDto> searchByPortName(String portName) throws DAOException;

	List<PortSelectDto> getPortList() throws DAOException;

	/* int checkDuplicateShortName(PortDto portDto) throws DAOException; */

	int checkDuplicateFullName(PortDto portDto) throws DAOException;

	int checkPortUsed(Integer portId) throws DAOException;

	void insertPort(PortDto portDto, Integer createdUser) throws DAOException;

	PortDto getPortById(Integer portId) throws DAOException;

	int updatePort(PortDto portDto, Integer updatedUser) throws DAOException;

	int deletePort(PortDto portDto, Integer updatedUser) throws DAOException;

	int checkDuplicateShortName(Integer id, String shortName) throws DAOException;

}
