package com.mttl.vlms.setting.stt012.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * DealerDto
 * 
 *
 */
public class DealerDto extends AbstractDto {

	private static final long serialVersionUID = -943346434188490998L;

	private Integer id;

	private String dealerCode;

	private String dealerName;

	private String address;

	private String phoneNo;

	private String contactName;

	private String contactPosition;

	private String contactPhoneNo;

	private String contactEmail;

	private Date contractStartDate;

	private Date contractEndDate;

	private boolean disabled;

	private String accountInformation;

	private List<String> phoneNoList;

	private List<String> contactPhoneNoList;

	private String contactList;

	private String phList;

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPosition() {
		return contactPosition;
	}

	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
	}

	public String getContactPhoneNo() {
		return contactPhoneNo;
	}

	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getAccountInformation() {
		return accountInformation;
	}

	public void setAccountInformation(String accountInformation) {
		this.accountInformation = accountInformation;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getPhoneNoList() {
		return phoneNoList;
	}

	public void setPhoneNoList(List<String> phoneNoList) {
		this.phoneNoList = phoneNoList;
	}

	public List<String> getContactPhoneNoList() {
		return contactPhoneNoList;
	}

	public void setContactPhoneNoList(List<String> contactPhoneNoList) {
		this.contactPhoneNoList = contactPhoneNoList;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactList() {
		return contactList;
	}

	public void setContactList(String contactList) {
		this.contactList = contactList;
	}

	public String getPhList() {
		return phList;
	}

	public void setPhList(String phList) {
		this.phList = phList;
	}

}
