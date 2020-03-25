package com.mttl.vlms.report.rpt001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.report.rpt001.dto.InventoryDto;

/**
 * vehicleInfoCustomMapper
 * 
 * 
 *
 */
public interface InventoryCustomMapper {
	List<InventoryDto> searchByChassisNo(@Param("inventoryDto") InventoryDto inventoryDto);

}
