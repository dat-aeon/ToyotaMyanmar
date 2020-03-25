package com.mttl.vlms.operation.opr001.dao;

import java.util.List;

import com.mttl.vlms.common.selectdto.ChargesSelectDto;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.operation.opr001.dto.BankAccountDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceSearchReqDto;
import com.mttl.vlms.operation.opr001.dto.ItemDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleSearchReqDto;

/**
 * InvoiceDao
 * 
 * 
 *
 */
public interface InvoiceDao {

	List<InvoiceDto> getInvoiceList(InvoiceSearchReqDto invoiceSearchReqDto, InvoiceDto invoiceSearchDto) throws DAOException;

	Integer getInvoiceListCount(InvoiceSearchReqDto invoiceSearchReqDto) throws DAOException;

	List<DealerSelectDto> getDealerList() throws DAOException;

	List<ChargesSelectDto> getChargesList() throws DAOException;

	List<BankAccountDto> getBankAccountList() throws DAOException;

	void insertInvoice(InvoiceDto dto, Integer createdBy) throws DAOException;

	void insertItem(List<ItemDto> itemList, Integer createdBy) throws DAOException;

	InvoiceDto getInvoiceByInvoiceId(Integer id) throws DAOException;

	List<ItemDto> getItemListByInvoiceId(Integer id) throws DAOException;

	int updateInvoice(InvoiceDto InvoiceDto, Integer updatedBy) throws DAOException;

	void updateItem(List<ItemDto> itemList, Integer updatedBy) throws DAOException;

	int deleteInvoice(InvoiceDto InvoiceDto, Integer updatedBy) throws DAOException;

	void deleteItem(List<ItemDto> itemList, Integer updatedBy) throws DAOException;

	List<TaskSelectDto> getTaskList() throws DAOException;

	List<ItemVehicleDto> getItemVehicleList(ItemVehicleSearchReqDto invoiceVehicleSearchDto) throws DAOException;

	String getNextInvoiceNo() throws DAOException;

	DealerSelectDto getDealerById(Integer id) throws DAOException;

	void deleteItem() throws DAOException;

}
