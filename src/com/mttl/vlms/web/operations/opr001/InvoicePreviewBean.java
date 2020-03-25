package com.mttl.vlms.web.operations.opr001;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.operation.opr001.dto.BankAccountDto;
import com.mttl.vlms.operation.opr001.dto.InvoiceDto;
import com.mttl.vlms.operation.opr001.service.InvoiceService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * InvoiceListBean
 * 
 * 
 *
 */
@ManagedBean(name = "InvoicePreviewBean")
@ViewScoped
public class InvoicePreviewBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8595693451616225040L;

	@ManagedProperty(value = "#{InvoiceService}")
	private InvoiceService invoiceService;

	private InvoiceDto invoiceDto;

	private List<BankAccountDto> bankAccountList;

	private User loginUser;

	private boolean isFromInput;

	@PostConstruct
	public void init() {

		invoiceDto = (InvoiceDto) getSessionParam(CommonConstants.INVOICE_DTO_EDIT);
		setFromInput(BooleanUtils.isTrue((Boolean) getSessionParam(CommonConstants.INVOICE_IS_FROM_INPUT)));

		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);

		bankAccountList = invoiceService.getBankAccountList();
		bankAccountList.add(0, getBankHeader());

	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public String back() {

		putSessionParam(CommonConstants.INVOICE_DTO_EDIT, invoiceDto);

		if (isFromInput) {
			return "invoiceInput";
		} else {
			return "invoiceEdit";
		}
	}

	public String inputInvoice() {

		invoiceService.insertInvoice(invoiceDto, loginUser.getUserID());
		addInfoMessage(null, "MSG_CMN_002", invoiceDto.getInvoiceNo());
		keepSetMessage();

		removeSessionParam(CommonConstants.INVOICE_DTO_EDIT);
		return "invoiceList";
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

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public InvoiceDto getInvoiceDto() {
		return invoiceDto;
	}

	public void setInvoiceDto(InvoiceDto invoiceDto) {
		this.invoiceDto = invoiceDto;
	}

	public List<BankAccountDto> getBankAccountList() {
		return bankAccountList;
	}

	public void setBankAccountList(List<BankAccountDto> bankAccountList) {
		this.bankAccountList = bankAccountList;
	}

	public boolean isFromInput() {
		return isFromInput;
	}

	public void setFromInput(boolean isFromInput) {
		this.isFromInput = isFromInput;
	}

}
