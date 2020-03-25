package com.mttl.vlms.dashboard.dsb001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt017.dto.YardDto;

/**
 * CountryCustomMapper
 * 
 * 
 *
 */
public interface DashboardMapper {

	int searchYard();

	List<YardDto> findYardList();

	int findTotalSpace(@Param("id") int id);

	int findRestSpace(@Param("id") int id);

	int findSpace(@Param("id") int id);

	int getEtaVehicleNo();

	int getAudioInstallVehicle();

	int getPdiVehicle();

	int countLicenseExpireDriver();

}
