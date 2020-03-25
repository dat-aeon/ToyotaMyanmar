package com.mttl.vlms.web.operations.opr001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import com.mttl.vlms.common.CommonConstants;
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
 * 
 * 
 * 
 */
@ManagedBean(name = "InvoiceEditBean")
@ViewScoped
public class InvoiceEditBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8801601108196097027L;

	@ManagedProperty(value = "#{InvoiceService}")
	private InvoiceService invoiceService;

	private InvoiceDto invoiceDtoEdit;

	private List<DealerSelectDto> dealerList;

	private List<ChargesSelectDto> chargesList;

	private List<BankAccountDto> bankAccountList;

	private List<TaskSelectDto> taskList;

	private List<ItemVehicleDto> itemVehicleDtoList;

	private ItemDto itemDto;

	private boolean isEditItem = false;

	@PostConstruct
	public void init() {

		this.itemVehicleDtoList = new ArrayList<>();

		this.invoiceDtoEdit = (InvoiceDto) getSessionParam(CommonConstants.INVOICE_DTO_EDIT);
		this.invoiceDtoEdit.setRemark3(CommonConstants.INVOICE_DEFAULT_REMARK_3);
		removeSessionParam(CommonConstants.INVOICE_DTO_EDIT);

		this.itemDto = new ItemDto();
		this.itemDto.setUnitPrice(BigDecimal.ZERO);
		this.itemDto.getUnitPrice().setScale(2);
		this.itemDto.setAmount(BigDecimal.ZERO);
		this.itemDto.getAmount().setScale(2);
		this.itemDto.setUnit(0);
		this.itemDto.setItemVehicleSearchReqDto(new ItemVehicleSearchReqDto());
		this.itemDto.setDeleteFlg(false);

		this.isEditItem = false;

		this.taskList = this.invoiceService.getTaskList();
		this.dealerList = this.invoiceService.getDealerList();
		this.chargesList = this.invoiceService.getChargesList();
		this.bankAccountList = this.invoiceService.getBankAccountList();
		this.bankAccountList.add(0, getBankHeader());

		if (null != this.invoiceDtoEdit.getDealerId()) {

			DealerSelectDto dealerSelectDto = this.dealerList.stream().filter(x -> this.invoiceDtoEdit.getDealerId().equals(x.getId())).findAny().orElse(null);

			if (null == dealerSelectDto) {
				dealerSelectDto = this.invoiceService.getDealerById(this.invoiceDtoEdit.getDealerId());
			}

			this.invoiceDtoEdit.setDealerDto(dealerSelectDto);
		}

		List<ItemDto> itemDtoList = this.invoiceService.getItemListByInvoiceId(this.invoiceDtoEdit.getId());
		this.invoiceDtoEdit.setItemList(itemDtoList);
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public void redirect() {
		InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(invoiceDtoEdit.getId());
		if (invoiceDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, invoiceDtoEdit.getInvoiceNo());
			redirect("invoiceList.xhtml?faces-redirect=true");
		}
	}

	public String editInvoice() {

		if (invoiceDtoEdit.getDate().compareTo(invoiceDtoEdit.getPaymentDueDate()) > 0) {
			addErrorMessage("Payment Due Date should not be early than Date");
			return null;
		}

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int count = invoiceService.updateInvoice(invoiceDtoEdit, user.getUserID());

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", invoiceDtoEdit.getInvoiceNo());
		} else {
			addInfoMessage(null, "MSG_CMN_003", invoiceDtoEdit.getInvoiceNo());
		}
		keepSetMessage();

		return "invoiceList";
	}

	public void saveItemAction() {

		this.itemVehicleDtoList = new ArrayList<>();

		calculateItemAmount(itemDto);
		if (!isEditItem) {
			itemDto.setInvoiceId(invoiceDtoEdit.getId());
			invoiceDtoEdit.getItemList().add(itemDto);
		}
		newItemInit();
		calculateAllAmount();

	}

	public void deleteItemAction(ItemDto deleteItemDto) {
		if (0 != deleteItemDto.getId()) {
			deleteItemDto.setDeleteFlg(true);
		} else {
			invoiceDtoEdit.getItemList().remove(deleteItemDto);
		}
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

	public void editItemInit(ItemDto editItemDto) {
		isEditItem = true;
		itemDto = editItemDto;

		if (null != itemDto.getItemVehicleDtoList() && !itemDto.getItemVehicleDtoList().isEmpty()) {
			this.itemVehicleDtoList.addAll(itemDto.getItemVehicleDtoList());
		} else {
			this.itemVehicleDtoList = new ArrayList<>();
		}
	}

	private void newItemInit() {
		itemDto = new ItemDto();
		this.itemDto.setUnitPrice(BigDecimal.ZERO);
		this.itemDto.getUnitPrice().setScale(2);
		this.itemDto.setAmount(BigDecimal.ZERO);
		this.itemDto.getAmount().setScale(2);
		this.itemDto.setUnit(0);
		this.itemDto.setItemVehicleSearchReqDto(new ItemVehicleSearchReqDto());
		this.itemDto.setDeleteFlg(false);

		this.isEditItem = false;
	}

	public void calculateAllAmount() {
		subTotalAction();
		taxAmount();
		grandTotalAction();
	}

	public void subTotalAction() {

		BigDecimal subTotal = BigDecimal.ZERO;
		for (ItemDto itemDto : invoiceDtoEdit.getItemList()) {
			if (null != itemDto && !itemDto.getDeleteFlg()) {
				subTotal = subTotal.add(itemDto.getAmount());

			}
		}
		invoiceDtoEdit.setSubTotal(subTotal);
		taxAmount();
	}

	public void taxAmount() {

		BigDecimal taxAmount = BigDecimal.ZERO;

		if (invoiceDtoEdit.getTaxIncluded()) {
			taxAmount = invoiceDtoEdit.getSubTotal().multiply(taxAmount);
			taxAmount = taxAmount.setScale(2, RoundingMode.CEILING);
		}

		invoiceDtoEdit.setTaxAmount(taxAmount);
	}

	public void grandTotalAction() {
		BigDecimal grandTotal = BigDecimal.ZERO;
		grandTotal = invoiceDtoEdit.getSubTotal().add(invoiceDtoEdit.getTaxAmount());
		invoiceDtoEdit.setGrandTotal(grandTotal);
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

	public String onFlowProcess(FlowEvent event) {
		switch (event.getNewStep()) {
			case "charges":
				itemDto.setUnit(itemDto.getItemVehicleDtoList().size());

				break;
		}

		return event.getNewStep();

	}

	public void searchInvoiceVehicleInfo() {
		this.itemVehicleDtoList = invoiceService.getItemVehicleList(itemDto.getItemVehicleSearchReqDto());
	}

	public void changeChargeSelect() {
		ChargesSelectDto chargesSelectDto = chargesList.stream().filter(c -> c.getId().equals(itemDto.getChargesId())).findAny().orElse(null);
		if (null != chargesSelectDto) {
			itemDto.setUnitPrice(chargesSelectDto.getUnitPrice());
			itemDto.setDescription(chargesSelectDto.getTitle());
		}

	}

	//
	//
	// Getter Setter
	//
	//
	public InvoiceService getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public InvoiceDto getInvoiceDtoEdit() {
		return invoiceDtoEdit;
	}

	public void setInvoiceDtoEdit(InvoiceDto invoiceDtoEdit) {
		this.invoiceDtoEdit = invoiceDtoEdit;
	}

	public List<DealerSelectDto> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<DealerSelectDto> dealerList) {
		this.dealerList = dealerList;
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

	public List<TaskSelectDto> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskSelectDto> taskList) {
		this.taskList = taskList;
	}

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

	public boolean isEditItem() {
		return isEditItem;
	}

	public void setEditItem(boolean isEditItem) {
		this.isEditItem = isEditItem;
	}

}
