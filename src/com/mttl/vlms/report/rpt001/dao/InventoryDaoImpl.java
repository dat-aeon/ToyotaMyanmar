package com.mttl.vlms.report.rpt001.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.report.rpt001.dto.InventoryDto;
import com.mttl.vlms.report.rpt001.mapper.InventoryCustomMapper;

/**
 * InventoryDaoImpl
 * 
 * 
 *
 */
@Repository("InventoryDao")
@Transactional(propagation = Propagation.REQUIRED)
public class InventoryDaoImpl extends BasicDAO implements InventoryDao {

	@Autowired
	InventoryCustomMapper inventoryCustomMapper;

	@ApplyAspect
	@Override
	public List<InventoryDto> searchByChassisNo(InventoryDto inventoryDto) throws DAOException {
		List<InventoryDto> result;
		try {
			result = inventoryCustomMapper.searchByChassisNo(inventoryDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

}
