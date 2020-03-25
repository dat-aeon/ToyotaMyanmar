package com.mttl.vlms.operation.opr001.service;

import java.util.List;

import com.mttl.vlms.common.selectdto.ChargesSelectDto;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.operation.opr001.dto.BankAccountDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceSearchReqDto;
import com.mttl.vlms.operation.opr001.dto.ItemDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleSearchReqDto;

/**
 * InvoiceService
 * 
 * 
 *
 */
public interface InvoiceService {

	List<InvoiceDto> getInvoiceList(InvoiceSearchReqDto invoiceSearchReqDto, InvoiceDto invoiceDto) throws SystemException;

	Integer getInvoiceListCount(InvoiceSearchReqDto invoiceSearchReqDto) throws SystemException;

	List<DealerSelectDto> getDealerList() throws SystemException;

	List<ChargesSelectDto> getChargesList() throws SystemException;

	List<BankAccountDto> getBankAccountList() throws SystemException;

	void insertInvoice(InvoiceDto dto, Integer createdBy) throws SystemException;

	InvoiceDto getInvoiceByInvoiceId(Integer id) throws SystemException;

	List<ItemDto> getItemListByInvoiceId(Integer id) throws SystemException;

	int updateInvoice(InvoiceDto invoiceDto, Integer updatedBy) throws SystemException;

	int deleteInvoice(InvoiceDto invoiceDto, Integer updatedBy) throws SystemException;

	List<TaskSelectDto> getTaskList() throws SystemException;

	List<ItemVehicleDto> getItemVehicleList(ItemVehicleSearchReqDto invoiceVehicleSearchDto) throws SystemException;

	String getNextInvoiceNo() throws SystemException;

	DealerSelectDto getDealerById(Integer id) throws SystemException;

}
