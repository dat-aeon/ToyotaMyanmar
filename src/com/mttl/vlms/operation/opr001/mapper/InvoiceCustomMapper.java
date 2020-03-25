package com.mttl.vlms.operation.opr001.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceSearchReqDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleSearchReqDto;

/**
 * InvoiceCustomMapper
 * 
 * 
 *
 */
public interface InvoiceCustomMapper {

	List<InvoiceDto> getInvoiceList(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder,
			@Param("filters") Map<String, Object> filters, @Param("invoiceSearchDto") InvoiceDto invoiceSearchDto);

	Integer getInvoiceListCount(@Param("invoiceSearchReqDto") InvoiceSearchReqDto invoiceSearchReqDto);

	InvoiceDto getInvoiceByInvoiceId(@Param("id") Integer id);

	List<ItemVehicleDto> getItemVehicleList(ItemVehicleSearchReqDto itemVehicleSearchReqDto);

	List<ItemVehicleDto> getItemVehicleListByItemId(@Param("itemId") Integer itemId);

	String getNextInvoiceNo();

	DealerSelectDto getDealerById(@Param("id") Integer id);

}
