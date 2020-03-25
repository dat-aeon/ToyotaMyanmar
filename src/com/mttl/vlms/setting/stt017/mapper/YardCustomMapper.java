package com.mttl.vlms.setting.stt017.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt017.dto.ColumnDto;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;

/**
 * YardCustomMapper
 * 
 * 
 *
 */
public interface YardCustomMapper {
	List<YardDto> searchByYardName(@Param("name") String name);

	List<ZoneDto> searchByYardFkId(@Param("yardId") Integer id);

	List<Integer> searchEachRow(Integer id);

	Integer countRowYardTable(@Param("yardDto") YardDto yardDto);

	List<ZoneDto> getByRowColumnEachZone(@Param("yardFkId") Integer yardFkId);

	Integer maxIdZoneTable();

	Integer minIdRowTable(Integer id);

	int checkYardName(@Param("yardDto") YardDto yardDto);

	int checkYardCode(@Param("yardDto") YardDto yardDto);

	int checkYardUsed(@Param("id") Integer id);

	YardDto getYardById(@Param("id") Integer id);

	ZoneDto getZoneById(@Param("yardId") Integer id);

	List<ColumnDto> getColumnList(@Param("columnId") Integer id);

}
