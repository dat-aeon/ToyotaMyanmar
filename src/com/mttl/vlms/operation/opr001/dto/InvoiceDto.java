package com.mttl.vlms.operation.opr001.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectdto.VehicleInfoSelectDto;
import com.mttl.vlms.operation.opr004.dto.InspectVehicleDto;

/**
 * InvoiceDto
 * 
 * 
 *
 */
public class InvoiceDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7657145894803859209L;

	private Integer id;

	private DealerSelectDto dealerDto;

	private List<BankAccountDto> bankAccountList;

	private List<ItemDto> itemList;

	private BigDecimal subTotal;

	private BigDecimal taxAmount;

	private BigDecimal taxPercentage;

	private Boolean taxIncluded;

	private BigDecimal grandTotal;

	private Date date;

	private Date paymentDueDate;

	private String invoiceNo;

	private Date invoiceDateFrom;

	private Date invoiceDateTo;

	private Date paymentDueDateFrom;

	private Date paymentDueDateTo;

	private boolean deleteFlg;

	private String subject;

	private String remark1;

	private String remark2;

	private String remark3;

	private String currency;

	private int type;

	private String customerDealerName;

	private String contactName;

	private Integer dealerId;

	private TaskSelectDto taskSelectDto;

	private List<InspectVehicleDto> inspectVehicleDto;

	private List<VehicleInfoSelectDto> vehicleInfoSelectDto;

	public List<ItemVehicleSearchReqDto> invoiceVehicleSearchList;

	private String preparedBy;

	private String approvedBy;

	public DealerSelectDto getDealerDto() {
		return dealerDto;
	}

	public void setDealerDto(DealerSelectDto dealerDto) {
		this.dealerDto = dealerDto;
	}

	public List<ItemDto> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemDto> itemList) {
		this.itemList = itemList;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public Boolean getTaxIncluded() {
		return taxIncluded;
	}

	public void setTaxIncluded(Boolean taxIncluded) {
		this.taxIncluded = taxIncluded;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public List<BankAccountDto> getBankAccountList() {
		return bankAccountList;
	}

	public void setBankAccountList(List<BankAccountDto> bankAccountList) {
		this.bankAccountList = bankAccountList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCustomerDealerName() {
		return customerDealerName;
	}

	public void setCustomerDealerName(String customerDealerName) {
		this.customerDealerName = customerDealerName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public TaskSelectDto getTaskSelectDto() {
		return taskSelectDto;
	}

	public void setTaskSelectDto(TaskSelectDto taskSelectDto) {
		this.taskSelectDto = taskSelectDto;
	}

	public List<InspectVehicleDto> getInspectVehicleDto() {
		return inspectVehicleDto;
	}

	public void setInspectVehicleDto(List<InspectVehicleDto> inspectVehicleDto) {
		this.inspectVehicleDto = inspectVehicleDto;
	}

	public List<VehicleInfoSelectDto> getVehicleInfoSelectDto() {
		return vehicleInfoSelectDto;
	}

	public void setVehicleInfoSelectDto(List<VehicleInfoSelectDto> vehicleInfoSelectDto) {
		this.vehicleInfoSelectDto = vehicleInfoSelectDto;
	}

	public List<ItemVehicleSearchReqDto> getInvoiceVehicleSearchList() {
		return invoiceVehicleSearchList;
	}

	public void setInvoiceVehicleSearchList(List<ItemVehicleSearchReqDto> invoiceVehicleSearchList) {
		this.invoiceVehicleSearchList = invoiceVehicleSearchList;
	}

	public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public Date getInvoiceDateFrom() {
		return invoiceDateFrom;
	}

	public void setInvoiceDateFrom(Date invoiceDateFrom) {
		this.invoiceDateFrom = invoiceDateFrom;
	}

	public Date getInvoiceDateTo() {
		return invoiceDateTo;
	}

	public void setInvoiceDateTo(Date invoiceDateTo) {
		this.invoiceDateTo = invoiceDateTo;
	}

	public Date getPaymentDueDateFrom() {
		return paymentDueDateFrom;
	}

	public void setPaymentDueDateFrom(Date paymentDueDateFrom) {
		this.paymentDueDateFrom = paymentDueDateFrom;
	}

	public Date getPaymentDueDateTo() {
		return paymentDueDateTo;
	}

	public void setPaymentDueDateTo(Date paymentDueDateTo) {
		this.paymentDueDateTo = paymentDueDateTo;
	}

	public boolean isDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

}
