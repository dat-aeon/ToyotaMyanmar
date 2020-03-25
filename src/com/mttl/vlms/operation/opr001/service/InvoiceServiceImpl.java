package com.mttl.vlms.operation.opr001.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.common.selectdto.ChargesSelectDto;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.operation.opr001.dao.InvoiceDao;
import com.mttl.vlms.operation.opr001.dto.BankAccountDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceSearchReqDto;
import com.mttl.vlms.operation.opr001.dto.ItemDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleSearchReqDto;

/**
 * InvoiceServiceImpl
 * 
 * 
 *
 */
@Service("InvoiceService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDao invoiceDao;

	@Override
	@ApplyAspect
	public List<DealerSelectDto> getDealerList() throws SystemException {
		List<DealerSelectDto> dealerList;
		try {
			dealerList = invoiceDao.getDealerList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return dealerList;
	}

	@Override
	@ApplyAspect
	public List<ChargesSelectDto> getChargesList() throws SystemException {
		List<ChargesSelectDto> chargesList;
		try {
			chargesList = invoiceDao.getChargesList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return chargesList;
	}

	@Override
	@ApplyAspect
	public List<BankAccountDto> getBankAccountList() throws SystemException {
		List<BankAccountDto> bankAccountList;
		try {
			bankAccountList = invoiceDao.getBankAccountList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return bankAccountList;
	}

	@Override
	@ApplyAspect
	public List<TaskSelectDto> getTaskList() throws SystemException {

		List<TaskSelectDto> taskList;
		try {
			taskList = invoiceDao.getTaskList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return taskList;
	}

	@ApplyAspect
	@Override
	public List<ItemVehicleDto> getItemVehicleList(ItemVehicleSearchReqDto invoiceVehicleSearchDto) throws SystemException {
		List<ItemVehicleDto> result;
		try {
			invoiceVehicleSearchDto.setTaskToDate(Utils.getEndTimeOfDate(invoiceVehicleSearchDto.getTaskToDate()));
			result = invoiceDao.getItemVehicleList(invoiceVehicleSearchDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public void insertInvoice(InvoiceDto dto, Integer createdBy) throws SystemException {
		try {

			if (dto.getType() == 2) {
				dto.setCustomerDealerName(dto.getCustomerDealerName());

			} else {
				dto.setCustomerDealerName(dto.getDealerDto().getDealerName());
				dto.setContactName(dto.getDealerDto().getContactName());
				dto.setDealerId(dto.getDealerDto().getId());
			}

			invoiceDao.insertInvoice(dto, createdBy);

			for (ItemDto item : dto.getItemList()) {
				item.setInvoiceId(dto.getId());
			}

			invoiceDao.insertItem(dto.getItemList(), createdBy);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@Override
	@ApplyAspect
	public List<InvoiceDto> getInvoiceList(InvoiceSearchReqDto invoiceSearchReqDto, InvoiceDto invoiceSearchDto) throws SystemException {
		List<InvoiceDto> invoiceList;
		try {
			invoiceList = invoiceDao.getInvoiceList(invoiceSearchReqDto, invoiceSearchDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return invoiceList;
	}

	@Override
	@ApplyAspect
	public Integer getInvoiceListCount(InvoiceSearchReqDto invoiceSearchReqDto) throws SystemException {
		Integer invoiceCount;
		try {
			invoiceCount = invoiceDao.getInvoiceListCount(invoiceSearchReqDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return invoiceCount;
	}

	@ApplyAspect
	@Override
	public InvoiceDto getInvoiceByInvoiceId(Integer id) throws SystemException {
		InvoiceDto invoiceDto;
		try {
			invoiceDto = invoiceDao.getInvoiceByInvoiceId(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return invoiceDto;
	}

	@ApplyAspect
	@Override
	public List<ItemDto> getItemListByInvoiceId(Integer id) throws SystemException {
		List<ItemDto> itemList;
		try {
			itemList = invoiceDao.getItemListByInvoiceId(id);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return itemList;
	}

	@ApplyAspect
	@Override
	public int updateInvoice(InvoiceDto invoiceDto, Integer updatedBy) throws SystemException {
		int count;
		try {
			count = invoiceDao.updateInvoice(invoiceDto, updatedBy);

			List<ItemDto> newItemList = new ArrayList<ItemDto>();
			List<ItemDto> updateItemList = new ArrayList<ItemDto>();

			for (ItemDto item : invoiceDto.getItemList()) {

				if (0 == item.getId()) {
					newItemList.add(item);
				} else {
					updateItemList.add(item);
				}

			}
			invoiceDao.deleteItem();
			invoiceDao.insertItem(newItemList, updatedBy);
			invoiceDao.updateItem(updateItemList, updatedBy);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteInvoice(InvoiceDto invoiceDto, Integer updatedBy) throws SystemException {
		int count;
		try {
			count = invoiceDao.deleteInvoice(invoiceDto, updatedBy);

			for (ItemDto item : invoiceDto.getItemList()) {
				item.setInvoiceId(invoiceDto.getId());

				// item.setChargesId(item.getChargesSelectDto().getId());
				// item.setUnitPrice(item.getChargesSelectDto().getUnitPrice());

			}
			invoiceDao.deleteItem(invoiceDto.getItemList(), updatedBy);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public String getNextInvoiceNo() throws SystemException {

		String nextInvoiceId = "";

		try {
			nextInvoiceId = invoiceDao.getNextInvoiceNo();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}

		return nextInvoiceId;
	}

	@ApplyAspect
	@Override
	public DealerSelectDto getDealerById(Integer id) throws SystemException {

		DealerSelectDto dealerSelectDto = null;
		try {
			dealerSelectDto = invoiceDao.getDealerById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return dealerSelectDto;

	}

}
