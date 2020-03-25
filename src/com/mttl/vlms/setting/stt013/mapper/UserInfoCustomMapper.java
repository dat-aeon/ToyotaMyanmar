package com.mttl.vlms.setting.stt013.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt013.dto.UserInfoDto;
import com.mttl.vlms.setting.stt013.dto.UserInfoSearchReqDto;

/**
 * UserInfoInfoCustomMapper
 * 
 * 
 *
 */
public interface UserInfoCustomMapper {

	List<UserInfoDto> getUserList(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder,
			@Param("filters") Map<String, Object> filters);

	Integer getUserListCount(@Param("userInfoSearchReqDto") UserInfoSearchReqDto userInfoSearchReqDto);

	// List<UserInfoDto> getUserList(@Param("id") Integer id);

	List<UserInfoDto> searchByName(@Param("name") String name);

	int checkId(@Param("userInfoDto") UserInfoDto userInfoDto);

	UserInfoDto getUserInfoById(@Param("userInfoId") Integer userInfoId);

	int checkLoginIdDuplicateCount(@Param("id") Integer id, @Param("loginId") String loginId);

	int checkUserInfoUsed(@Param("id") Integer id);

	int checkUserInfoCode(@Param("id") Integer id, @Param("barcodeId") String barcodeId);

	int checkStaffId(@Param("id") Integer id, @Param("staffId") String staffId);

	int checkDuplicateNrcNo(@Param("id") Integer id, @Param("nrcNo") String nrcNo);

	// int checkNrcNo(@Param("userInfoDto") UserInfoDto userInfoDto);

}
