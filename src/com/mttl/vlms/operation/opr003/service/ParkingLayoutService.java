package com.mttl.vlms.operation.opr003.service;

import java.util.List;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;

/**
 * ParkingLayoutService
 * 
 * 
 *
 */
public interface ParkingLayoutService {

	List<YardDto> getYardList() throws SystemException;

	List<ZoneDto> getByRowColumnEachZone(Integer yardId) throws SystemException;

	List<ZoneDto> getZoneListByYardId(int yardId) throws SystemException;

}
