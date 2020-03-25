package com.mttl.vlms.common.selectdto;

import com.mttl.vlms.common.AbstractDto;

/**
 * BankAccountSelectDto
 *
 * 
 *
 */

public class BankAccountSelectDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8007884524070157536L;

	private String bankName;

	private String accountNo;

	private String swiftCode;

	private String branchName;

	private String bankAddress;

	private String currencyType;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

}
