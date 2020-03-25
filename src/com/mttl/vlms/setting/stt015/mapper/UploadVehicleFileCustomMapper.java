package com.mttl.vlms.setting.stt015.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt014.dto.ChargesDto;

/**
 * ChargesCustomMapper
 * 
 * 
 *
 */
public interface UploadVehicleFileCustomMapper {

	List<ChargesDto> searchByTitle(@Param("title") String title);

	int checkTitle(@Param("chargesDto") ChargesDto chargesDto);

	// int checkChargesUsed(@Param("id") Integer chargesId);

	ChargesDto getChargesById(@Param("id") Integer chargesId);

	String getDealerId(@Param("dealerName") String dealerName);

	String getPortOfDischargeId(@Param("disChargePortName") String disChargePortName);
}
