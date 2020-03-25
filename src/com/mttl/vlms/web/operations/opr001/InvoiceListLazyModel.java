package com.mttl.vlms.web.operations.opr001;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.mttl.vlms.common.Utils;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceSearchReqDto;
import com.mttl.vlms.operation.opr001.service.InvoiceService;

public class InvoiceListLazyModel extends LazyDataModel<InvoiceDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 208689283156964665L;

	private InvoiceSearchReqDto invoiceSearchReqDto;

	private InvoiceService invoiceService;
	private int rowCount;

	private InvoiceDto invoiceSearchDto;

	public InvoiceListLazyModel(int rowCount, InvoiceSearchReqDto invoiceSearchReqDto, InvoiceService invoiceService, InvoiceDto invoiceSearchDto) {
		this.invoiceSearchReqDto = invoiceSearchReqDto;
		this.invoiceService = invoiceService;
		this.rowCount = rowCount;

		this.invoiceSearchDto = invoiceSearchDto;

	}

	@Override
	public Object getRowKey(InvoiceDto invoiceDto) {
		return invoiceDto.getId();
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public List<InvoiceDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<InvoiceDto> invoiceDtoList = new ArrayList<>();

		invoiceSearchReqDto = new InvoiceSearchReqDto();
		invoiceSearchReqDto.setLimit(pageSize);
		invoiceSearchReqDto.setOffSet(first);
		invoiceSearchReqDto.setSortField(sortField);
		invoiceSearchReqDto.setSortOrder(sortOrder.toString());
		invoiceSearchReqDto.setFilters(filters);

		if (invoiceSearchReqDto.getFilters().size() > 0) {
			if (invoiceSearchReqDto.getFilters().containsKey("globalFilter") && Utils.isNullAndEmpty(invoiceSearchReqDto.getFilters().get("globalFilter"))) {
				invoiceSearchReqDto.getFilters().remove("globalFilter");
			}
		}

		invoiceDtoList = invoiceService.getInvoiceList(invoiceSearchReqDto, invoiceSearchDto);
		return invoiceDtoList;

	}

}