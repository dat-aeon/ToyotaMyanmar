package com.mttl.vlms.setting.stt013.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt013.dto.RoleDto;

/**
 * RoleInfoCustomMapper
 * 
 * 
 *
 */
public interface RoleCustomMapper {

	List<RoleDto> getUserList(@Param("roleId") String RoleId);

}
