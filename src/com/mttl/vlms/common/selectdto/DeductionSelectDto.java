package com.mttl.vlms.common.selectdto;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * DeductionSelectDto
 * 
 *  
 *
 */
public class DeductionSelectDto extends AbstractDto {

	private static final long serialVersionUID = -6504076437465489246L;

	private Integer deductionId;

	private String deductionName;

	private Boolean isExistFlg;

	private Date createdDate;

	private Double amount;

	private Integer paymentType;

	private String paymentTypeName;

	private Integer deductionType;

	public Integer getDeductionId() {
		return deductionId;
	}

	public void setDeductionId(Integer deductionId) {
		this.deductionId = deductionId;
	}

	public String getDeductionName() {
		return deductionName;
	}

	public void setDeductionName(String deductionName) {
		this.deductionName = deductionName;
	}

	public Boolean getIsExistFlg() {
		return isExistFlg;
	}

	public void setIsExistFlg(Boolean isExistFlg) {
		this.isExistFlg = isExistFlg;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public Integer getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(Integer deductionType) {
		this.deductionType = deductionType;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
