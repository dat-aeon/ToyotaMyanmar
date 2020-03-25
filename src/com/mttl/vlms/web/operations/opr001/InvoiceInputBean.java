package com.mttl.vlms.web.operations.opr001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.event.FlowEvent;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.common.selectdto.ChargesSelectDto;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.operation.opr001.dto.BankAccountDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.ItemDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleDto;
import com.mttl.vlms.operation.opr001.dto.ItemVehicleSearchReqDto;
import com.mttl.vlms.operation.opr001.service.InvoiceService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * InvoiceListBean
 * 
 * 
 */
@ManagedBean(name = "InvoiceInputBean")
@ViewScoped
public class InvoiceInputBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2464043956838241245L;

	@ManagedProperty(value = "#{APP_CONFIG}")
	private Properties APP_CONFIG;

	@ManagedProperty(value = "#{InvoiceService}")
	private InvoiceService invoiceService;

	private InvoiceDto invoiceDto;

	private List<DealerSelectDto> dearlerList;

	private List<ChargesSelectDto> chargesList;

	private List<BankAccountDto> bankAccountList;

	private ItemDto itemDto;

	// private ItemDto backupItemDto;

	private List<TaskSelectDto> taskList;

	private User loginUser;

	private boolean isEditItem = false;

	private boolean showInvoiceTypeDlg = true;

	private List<ItemVehicleDto> itemVehicleDtoList;

	@PostConstruct
	public void init() {

		itemDto = new ItemDto();
		itemDto.setItemVehicleSearchReqDto(new ItemVehicleSearchReqDto());

		// invoiceVehicleSearchDto = (InvoiceVehicleSearchDto)
		// getSessionParam(CommonConstants.INVOICE_VEHICLE_SEARCH);
		// invoiceVehicleSearchDto = new InvoiceVehicleSearchDto();

		itemVehicleDtoList = new ArrayList<>();

		dearlerList = invoiceService.getDealerList();
		chargesList = invoiceService.getChargesList();
		taskList = invoiceService.getTaskList();

		bankAccountList = invoiceService.getBankAccountList();
		bankAccountList.add(0, getBankHeader());

		if (!Utils.isNullAndEmpty(getSessionParam(CommonConstants.INVOICE_DTO_EDIT))) {
			invoiceDto = (InvoiceDto) getSessionParam(CommonConstants.INVOICE_DTO_EDIT);
			removeSessionParam(CommonConstants.INVOICE_DTO_EDIT);
			this.showInvoiceTypeDlg = false;
		} else {
			invoiceDto = new InvoiceDto();
			invoiceDto.setTaxPercentage(retrieveTaxPercentage());
			invoiceDto.setApprovedBy(retrieveApprovedBy());
			invoiceDto.setPreparedBy(retrievePreparedBy());
			invoiceDto.setInvoiceNo(invoiceService.getNextInvoiceNo());

			invoiceDto.setItemList(new ArrayList<>());
			invoiceDto.setRemark1(CommonConstants.INVOICE_DEFAULT_REMARK_1);
			invoiceDto.setRemark2(CommonConstants.INVOICE_DEFAULT_REMARK_2);
			invoiceDto.setRemark3(CommonConstants.INVOICE_DEFAULT_REMARK_3);
			invoiceDto.setCurrency("MMK");
			invoiceDto.setTaxIncluded(true);

			if (CollectionUtils.isNotEmpty(dearlerList)) {
				invoiceDto.setDealerDto(dearlerList.get(0));
			}
		}

		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);
	}

	// public void onRowEdit(RowEditEvent event) {
	// DataTable dataTable = (DataTable) event.getSource();
	// ItemDto itemDto = (ItemDto) dataTable.getRowData();
	//
	// calculateItemAmount(itemDto);
	// calculateAllAmount();
	// }

	private BigDecimal retrieveTaxPercentage() {

		BigDecimal taxPercnt = BigDecimal.ZERO;

		try {
			String strTaxPerent = APP_CONFIG.getProperty(CommonConstants.COMMERCIAL_TAX_PERCENTAGE);
			if (null != strTaxPerent) {
				taxPercnt = new BigDecimal(strTaxPerent);
			}
		} catch (Exception e) {

		}

		taxPercnt.setScale(2);
		return taxPercnt;
	}

	private String retrieveApprovedBy() {

		String approvedBy = "";

		try {
			approvedBy = APP_CONFIG.getProperty(CommonConstants.INVOICE_APPROVED_BY);
		} catch (Exception e) {

		}
		return approvedBy;
	}

	private String retrievePreparedBy() {

		String preparedBy = "";

		try {
			preparedBy = APP_CONFIG.getProperty(CommonConstants.INVOICE_PREPARED_BY);
		} catch (Exception e) {

		}
		return preparedBy;
	}

	/*
	 * public void onCellEdit(CellEditEvent event) { Object oldValue =
	 * event.getOldValue(); Object newValue = event.getNewValue();
	 * 
	 * if (newValue != null && !newValue.equals(oldValue)) { FacesMessage msg =
	 * new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " +
	 * oldValue + ", New:" + newValue);
	 * FacesContext.getCurrentInstance().addMessage(null, msg); } }
	 */

	public void newItemInit() {
		itemDto = new ItemDto();
		itemDto.setUnitPrice(BigDecimal.ZERO);
		itemDto.getUnitPrice().setScale(2);
		itemDto.setAmount(BigDecimal.ZERO);
		itemDto.getAmount().setScale(2);
		itemDto.setUnit(0);
		itemDto.setItemVehicleSearchReqDto(new ItemVehicleSearchReqDto());

		isEditItem = false;
	}

	public void saveAction() {

		this.itemVehicleDtoList = new ArrayList<>();

		calculateItemAmount(itemDto);
		if (!isEditItem) {
			invoiceDto.getItemList().add(itemDto);
		}
		newItemInit();
		calculateAllAmount();

	}

	/*
	 * public void editAction() {
	 * 
	 * if (invoiceDto.getItemList().contains(itemDto)) { int index =
	 * invoiceDto.getItemList().indexOf(itemDto); itemDto =
	 * invoiceDto.getItemList().get(index);
	 * 
	 * itemDto.setUnit(selectedInvoiceVehicleSearchList.size());
	 * itemDto.setUnitPrice(itemDto.getUnitPrice());
	 * itemDto.setDescription(itemDto.getDescription());
	 * itemDto.setSelectedInvoiceVehicleList(selectedInvoiceVehicleSearchList);
	 * 
	 * calculateAmount(tempItem); subTotalAction(); taxAmount();
	 * grandTotalAction(); }
	 * 
	 * }
	 */

	public void editItemInit(ItemDto editItemDto) {
		isEditItem = true;
		itemDto = editItemDto;
		// backupItemDto = SerializationUtils.clone(editItemDto);
		this.itemVehicleDtoList.addAll(itemDto.getItemVehicleDtoList());
	}

	private BankAccountDto getBankHeader() {
		BankAccountDto bankLeftHeader = new BankAccountDto();
		bankLeftHeader.setCurrencyType("&nbsp;");
		bankLeftHeader.setBankName("Bank Name");
		bankLeftHeader.setAccountNo("Account Number");
		bankLeftHeader.setSwiftCode("SWIFT Code");
		bankLeftHeader.setBranchName("Branch Name");
		bankLeftHeader.setBankAddress("Bank Address");
		return bankLeftHeader;
	}

	public void deleteAction(ItemDto item) {
		invoiceDto.getItemList().remove(item);
		calculateAllAmount();
	}

	public void calculateItemAmount(ItemDto itemDto) {

		if (null != itemDto) {

			BigDecimal amount = BigDecimal.ZERO;
			amount.setScale(2);

			if (null != itemDto.getUnitPrice()) {
				amount = itemDto.getUnitPrice().multiply(new BigDecimal(itemDto.getUnit()));
			}
			itemDto.setAmount(amount);
		}
	}

	public void calculateAllAmount() {
		subTotalAction();
		taxAmount();
		grandTotalAction();
	}

	public void subTotalAction() {

		BigDecimal subTotal = BigDecimal.ZERO;
		subTotal.setScale(2);

		for (ItemDto itemDto : invoiceDto.getItemList()) {
			if (itemDto != null) {
				subTotal = subTotal.add(itemDto.getAmount());
			}
		}

		invoiceDto.setSubTotal(subTotal);
	}

	public void taxAmount() {
		BigDecimal taxAmount = BigDecimal.ZERO;

		if (invoiceDto.getTaxIncluded()) {
			taxAmount = invoiceDto.getSubTotal().multiply(invoiceDto.getTaxPercentage().divide(new BigDecimal(100)));
			taxAmount = taxAmount.setScale(2, RoundingMode.CEILING);
		}

		invoiceDto.setTaxAmount(taxAmount);
	}

	public void grandTotalAction() {
		BigDecimal grandTotal = BigDecimal.ZERO;
		grandTotal.setScale(2);

		grandTotal = invoiceDto.getSubTotal().add(invoiceDto.getTaxAmount());
		invoiceDto.setGrandTotal(grandTotal);
	}

	public void changeChargeSelect() {
		ChargesSelectDto chargesSelectDto = chargesList.stream().filter(c -> c.getId().equals(itemDto.getChargesId())).findAny().orElse(null);
		if (null != chargesSelectDto) {
			itemDto.setUnitPrice(chargesSelectDto.getUnitPrice());
			itemDto.setDescription(chargesSelectDto.getTitle());
		}
	}

	public String invoiceTypeAction(int i) {
		if (i > 1) {
			this.invoiceDto.setType(2);
		} else {
			this.invoiceDto.setType(1);
		}
		return "invoiceInput";
	}

	public void searchItemVehicleInfo() {
		// paginatorVisible = false;
		itemVehicleDtoList = invoiceService.getItemVehicleList(itemDto.getItemVehicleSearchReqDto());
	}

	// public void clearSearch() {
	// this.itemDto.setItemVehicleSearchReqDto(new ItemVehicleSearchReqDto());
	// }

	public void updateDueDate() {
		if (null != invoiceDto && null != invoiceDto.getDate()) {
			invoiceDto.setPaymentDueDate(DateUtils.addWeeks(invoiceDto.getDate(), 2));
		}
	}

	public String previewInvoice() {

		if (invoiceDto.getDate().compareTo(invoiceDto.getPaymentDueDate()) > 0) {
			addErrorMessage("Payment Due Date should not be early than Date");
			return null;
		}

		// invoiceDto.setCustomerDealerName(customerDealerName);
		putSessionParam(CommonConstants.INVOICE_DTO_EDIT, invoiceDto);
		putSessionParam(CommonConstants.INVOICE_IS_FROM_INPUT, true);

		return "invoicePreview";
	}

	public String onFlowProcess(FlowEvent event) {
		switch (event.getNewStep()) {
			case "charges":
				itemDto.setUnit(itemDto.getItemVehicleDtoList().size());

				break;
		}

		return event.getNewStep();

	}

	// public void onDialogClose() {
	// if (isEditItem) {
	//
	// }
	// }

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public InvoiceDto getInvoiceDto() {
		return invoiceDto;
	}

	public void setInvoiceDto(InvoiceDto invoiceDto) {
		this.invoiceDto = invoiceDto;
	}

	public List<DealerSelectDto> getDealerList() {
		return dearlerList;
	}

	public void setDealerList(List<DealerSelectDto> dearlerList) {
		this.dearlerList = dearlerList;
	}

	public List<ChargesSelectDto> getChargesList() {
		return chargesList;
	}

	public void setChargesList(List<ChargesSelectDto> chargesList) {
		this.chargesList = chargesList;
	}

	public List<BankAccountDto> getBankAccountList() {
		return bankAccountList;
	}

	public void setBankAccountList(List<BankAccountDto> bankAccountList) {
		this.bankAccountList = bankAccountList;
	}

	// public boolean isPaginatorVisible() {
	// return paginatorVisible;
	// }
	//
	// public void setPaginatorVisible(boolean paginatorVisible) {
	// this.paginatorVisible = paginatorVisible;
	// }
	//
	// public Integer getFirstRowCount() {
	// return firstRowCount;
	// }
	//
	// public void setFirstRowCount(Integer firstRowCount) {
	// this.firstRowCount = firstRowCount;
	// }

	// public String getCurrency() {
	// return currency;
	// }
	//
	// public void setCurrency(String currency) {
	// this.currency = currency;
	// }

	// public List<ItemDto> getItemList() {
	// return itemList;
	// }
	//
	// public void setItemList(List<ItemDto> itemList) {
	// this.itemList = itemList;
	// }

	public List<TaskSelectDto> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskSelectDto> taskList) {
		this.taskList = taskList;
	}

	// public List<InspectVehicleDto> getInspectVehicleList() {
	// return inspectVehicleList;
	// }
	//
	// public void setInspectVehicleList(List<InspectVehicleDto>
	// inspectVehicleList) {
	// this.inspectVehicleList = inspectVehicleList;
	// }

	// public List<VehicleInfoSelectDto> getVehicleInfoList() {
	// return vehicleInfoList;
	// }
	//
	// public void setVehicleInfoList(List<VehicleInfoSelectDto>
	// vehicleInfoList) {
	// this.vehicleInfoList = vehicleInfoList;
	// }

	public ItemDto getItemDto() {
		return itemDto;
	}

	public void setItemDto(ItemDto itemDto) {
		this.itemDto = itemDto;
	}

	public List<ItemVehicleDto> getItemVehicleDtoList() {
		return itemVehicleDtoList;
	}

	public void setItemVehicleDtoList(List<ItemVehicleDto> itemVehicleDtoList) {
		this.itemVehicleDtoList = itemVehicleDtoList;
	}

	public Properties getAPP_CONFIG() {
		return APP_CONFIG;
	}

	public void setAPP_CONFIG(Properties aPP_CONFIG) {
		APP_CONFIG = aPP_CONFIG;
	}

	public boolean isShowInvoiceTypeDlg() {
		return showInvoiceTypeDlg;
	}

	public void setShowInvoiceTypeDlg(boolean showInvoiceTypeDlg) {
		this.showInvoiceTypeDlg = showInvoiceTypeDlg;
	}

}
