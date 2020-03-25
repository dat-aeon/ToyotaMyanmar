package com.mttl.vlms.common.selectdto;

import java.math.BigDecimal;

import com.mttl.vlms.common.AbstractDto;

/**
 * ChargesSelectDto
 *
 * 
 *
 */

public class ChargesSelectDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6736776093804968282L;

	private Integer id;

	private String title;

	private BigDecimal unitPrice;

	private String currency;

	private boolean disabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}
