package com.mttl.vlms.web.operations.opr001;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.common.selectdto.ChargesSelectDto;
import com.mttl.vlms.common.selectdto.DealerSelectDto;
import com.mttl.vlms.operation.opr001.dto.BankAccountDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.dto.ItemDto;
import com.mttl.vlms.operation.opr001.service.InvoiceService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * InvoiceDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "InvoiceDeleteConfirmBean")
@ViewScoped
public class InvoiceDeleteConfirmBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5098804254149639383L;

	@ManagedProperty(value = "#{InvoiceService}")
	private InvoiceService invoiceService;

	private InvoiceDto invoiceDeleteDto;

	private List<DealerSelectDto> dealerList;

	private List<ChargesSelectDto> chargesList;

	private List<BankAccountDto> bankAccountList;

	private Integer firstRowCount;

	private boolean paginatorVisible;

	@PostConstruct
	public void init() {

		invoiceDeleteDto = (InvoiceDto) getSessionParam(CommonConstants.INVOICE_DTO_DELETE);

		setDealerList(invoiceService.getDealerList());
		setChargesList(invoiceService.getChargesList());

		invoiceDeleteDto.setRemark1(CommonConstants.INVOICE_DEFAULT_REMARK_1);
		invoiceDeleteDto.setRemark2(CommonConstants.INVOICE_DEFAULT_REMARK_2);
		invoiceDeleteDto.setRemark3(CommonConstants.INVOICE_DEFAULT_REMARK_3);

		setBankAccountList(invoiceService.getBankAccountList());

		BankAccountDto bankLeftHeader = new BankAccountDto();
		bankLeftHeader.setCurrencyType("&nbsp;");
		bankLeftHeader.setBankName("Bank Name");
		bankLeftHeader.setAccountNo("Account Number");
		bankLeftHeader.setSwiftCode("SWIFT Code");
		bankLeftHeader.setBranchName("Branch Name");
		bankLeftHeader.setBankAddress("Bank Address");
		bankAccountList.add(0, bankLeftHeader);

		if (null != invoiceDeleteDto.getDealerId()) {
			invoiceDeleteDto.setType(1);

			DealerSelectDto dealerSelectDto = dealerList.stream().filter(x -> invoiceDeleteDto.getDealerId().equals(x.getId())).findAny().orElse(null);
			invoiceDeleteDto.setDealerDto(dealerSelectDto);

		} else {
			invoiceDeleteDto.setType(2);
		}

		List<ItemDto> itemDtoList = invoiceService.getItemListByInvoiceId(invoiceDeleteDto.getId());

		for (ItemDto itemDto : itemDtoList) {
			ChargesSelectDto chargesSelectDto = new ChargesSelectDto();
			chargesSelectDto = chargesList.stream().filter(x -> itemDto.getChargesId() == x.getId()).findAny().orElse(null);
			itemDto.setChargesSelectDto(chargesSelectDto);

		}

		invoiceDeleteDto.setItemList(itemDtoList);
	}

	public void redirect() {
		InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(invoiceDeleteDto.getId());
		if (invoiceDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, invoiceDeleteDto.getId());
			redirect("invoiceList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmInvoice() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = invoiceService.deleteInvoice(invoiceDeleteDto, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_007", invoiceDeleteDto.getInvoiceNo());
		} else {
			addInfoMessage(null, "MSG_CMN_004", invoiceDeleteDto.getInvoiceNo());

		}
		keepSetMessage();
		return "invoiceList";
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public InvoiceDto getInvoiceDeleteDto() {
		return invoiceDeleteDto;
	}

	public void setInvoiceDeleteDto(InvoiceDto invoiceDeleteDto) {
		this.invoiceDeleteDto = invoiceDeleteDto;
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

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setPaginatorVisible(boolean paginatorVisible) {
		this.paginatorVisible = paginatorVisible;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public void setFirstRowCount(Integer firstRowCount) {
		this.firstRowCount = firstRowCount;
	}
}
