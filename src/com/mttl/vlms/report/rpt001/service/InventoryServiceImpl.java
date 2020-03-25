package com.mttl.vlms.report.rpt001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.report.rpt001.dao.InventoryDao;
import com.mttl.vlms.report.rpt001.dto.InventoryDto;

/**
 * InventoryServiceImpl
 * 
 *
 */
@Service("InventoryService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryDao inventoryDao;

	@ApplyAspect
	@Override
	public List<InventoryDto> searchByChassisNo(InventoryDto inventoryDto) throws SystemException {
		List<InventoryDto> result;
		try {
			result = inventoryDao.searchByChassisNo(inventoryDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

}
