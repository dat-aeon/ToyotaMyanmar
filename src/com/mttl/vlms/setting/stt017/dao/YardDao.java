package com.mttl.vlms.setting.stt017.dao;

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
public interface YardDao {

	List<YardDto> searchByYardName(String name) throws DAOException;

	List<ZoneDto> searchByYardFkId(Integer yardFkId) throws DAOException;

	int checkDuplicateYardName(YardDto yardDto) throws DAOException;

	int checkDuplicateYardCode(YardDto yardDto) throws DAOException;

	int checkYardUsed(Integer id) throws DAOException;

	void insertYard(YardDto yardDto, Integer createdUser) throws DAOException;

	void insertZone(List<ZoneDto> zoneDtoList, Integer createdUser) throws DAOException;

	void insertRow(List<RowDto> rowDtoList, Integer createdUser) throws DAOException;

	void insertColumn(List<ColumnDto> columnDtoList, Integer createdUser) throws DAOException;

	YardDto getYardById(Integer yardId) throws DAOException;

	List<ZoneDto> getByRowColumnEachZone(Integer yardId) throws DAOException;

	int updateYard(YardDto yardDto, Integer updatedUser, List<ZoneDto> zoneUpdateDtoList) throws DAOException;

	int updateZone(Integer updatedUser, List<ZoneDto> zoneUpdateDtoList) throws DAOException;

	int updateRow(Integer updatedUser, List<RowDto> zoneUpdateDtoList) throws DAOException;

	int updateColumn(Integer updatedUser, List<ColumnDto> zoneUpdateDtoList) throws DAOException;

	int deleteYard(YardDto yardDto, Integer updatedUser) throws DAOException;

	List<YardDto> getYardList() throws DAOException;

	List<ZoneDto> getZoneListByYardId(int yardId) throws DAOException;

	List<RowDto> getRowListByZoneId(int zoneId) throws DAOException;

	List<ColumnDto> getColumnListByRowId(int rowId) throws DAOException;

	int deleteColumn(Integer updatedUser, List<ColumnDto> zoneUpdateDtoList) throws DAOException;

	int deleteRow(Integer updatedUser, List<RowDto> zoneUpdateDtoList) throws DAOException;

	VehicleInfoDto getVehicleInfoByVehicleId(int vehicleId, int parkingId) throws DAOException;

}
