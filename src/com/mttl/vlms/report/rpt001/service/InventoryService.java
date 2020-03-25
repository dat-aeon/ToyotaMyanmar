package com.mttl.vlms.report.rpt001.service;

import java.util.List;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.report.rpt001.dto.InventoryDto;

/**
 * InventoryService
 * 
 * 
 *
 */
public interface InventoryService {

	List<InventoryDto> searchByChassisNo(InventoryDto inventoryDto) throws SystemException;
}
