package com.mttl.vlms.operation.opr003.dao;

import java.util.List;

import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;
import com.mttl.vlms.setting.stt017.dto.ColumnDto;
import com.mttl.vlms.setting.stt017.dto.RowDto;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;

/**
 * YardDao
 * 
 * 
 *
 */
public interface ParkingLayoutDao {

	List<YardDto> getYardList() throws DAOException;

	List<ZoneDto> getByRowColumnEachZone(Integer yardId) throws DAOException;

	List<ZoneDto> getZoneListByYardId(int yardId) throws DAOException;

	List<RowDto> getRowListByZoneId(int zoneId) throws DAOException;

	List<ColumnDto> getColumnListByRowId(int rowId) throws DAOException;

	VehicleInfoDto getVehicleInfoByVehicleId(int vehicleId, int parkingId) throws DAOException;

}
