package com.mttl.vlms.setting.stt010.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt010.dto.TrailerDto;
import com.mttl.vlms.setting.stt010.dto.TrailerSearchReqDto;

/**
 * TrailerCustomMapper
 * 
 * 
 *
 */
public interface TrailerCustomMapper {

	List<TrailerDto> getMainTrailerList();

	List<TrailerDto> getTrailerList(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder,
			@Param("filters") Map<String, Object> filters);

	Integer getTrailerListCount(@Param("trailerSearchReqDto") TrailerSearchReqDto trailerSearchReqDto);

	int checkLicenseNo(@Param("trailerDto") TrailerDto trailerDto);

	int checkTrailerId(@Param("trailerDto") TrailerDto trailerDto);

	List<Integer> getEmployeeUsedList(@Param("id") Integer id);

	TrailerDto getTrailerByTrailerId(@Param("id") Integer id);

	String getNextTrailerId();
}
