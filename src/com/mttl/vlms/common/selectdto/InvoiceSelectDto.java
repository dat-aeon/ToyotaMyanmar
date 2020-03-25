package com.mttl.vlms.common.selectdto;

import java.util.Date;

import com.mttl.vlms.common.AbstractDto;

/**
 * InvoiceSelectDto
 *
 * 
 *
 */

public class InvoiceSelectDto extends AbstractDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9077015683603305948L;

	private String customerDealerName;

	private String contactName;

	private Date date;

	private Date paymentDueDate;

	private String invoiceNo;

	private String subject;

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

}
