package com.mttl.vlms.setting.stt017.service;

import java.util.List;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;

/**
 * YardService
 * 
 * 
 *
 */
public interface YardService {

	List<YardDto> findByYardName(String name) throws SystemException;

	int checkDuplicateYardName(YardDto yardDto) throws SystemException;

	int checkDuplicateYardCode(YardDto yardDto) throws SystemException;

	int checkYardUsed(Integer yardId) throws SystemException;

	void insertYard(YardDto yardDto, Integer createdUser) throws SystemException;

	YardDto getYardById(Integer yardId) throws SystemException;

	List<ZoneDto> getByRowColumnEachZone(Integer yardId) throws SystemException;

	List<ZoneDto> findByYardFkId(Integer yardFkId) throws SystemException;

	int updateYard(YardDto yardDto, Integer updatedUser, List<ZoneDto> zoneUpdateDtoList) throws SystemException;

	int deleteYard(YardDto yardDto, Integer updatedUser) throws SystemException;

	List<YardDto> getYardList() throws SystemException;

	List<ZoneDto> getZoneListByYardId(int yardId) throws SystemException;

}
