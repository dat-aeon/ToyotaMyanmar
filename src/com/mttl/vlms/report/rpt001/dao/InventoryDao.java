package com.mttl.vlms.report.rpt001.dao;

import java.util.List;

import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.report.rpt001.dto.InventoryDto;

/**
 * InventoryDao
 * 
 * 
 *
 */
public interface InventoryDao {
	List<InventoryDto> searchByChassisNo(InventoryDto inventoryDto) throws DAOException;

}
