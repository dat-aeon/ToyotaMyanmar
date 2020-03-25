package com.mttl.vlms.setting.stt009.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt009.dto.PortDto;

/**
 * portCustomMapper
 * 
 * 
 *
 */
public interface PortCustomMapper {
	List<PortDto> searchByPortName(@Param("fullName") String portName);

	int checkPortName(@Param("portDto") PortDto portDto);

	int checkPortCode(@Param("portDto") PortDto portDto);

	int checkPortUsed(@Param("portId") Integer portId);

	PortDto getPortById(@Param("portId") Integer portId);

	int checkDuplicateShortName(@Param("id") Integer id, @Param("shortName") String shortName);

}
