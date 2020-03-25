package com.mttl.vlms.common.selectdto;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * IncomeTaxRespiteSelectDto
 * 
 *  
 *
 */
public class IncomeTaxRespiteSelectDto extends AbstractDto {

	private static final long serialVersionUID = -2461528286900668057L;

	private Integer respiteId;

	private String respiteName;

	private Boolean permit;

	private Integer quantity;

	private Boolean forAll;

	private Date createdDate;

	private Date effectiveDate;

	public Integer getRespiteId() {
		return respiteId;
	}

	public void setRespiteId(Integer respiteId) {
		this.respiteId = respiteId;
	}

	public String getRespiteName() {
		return respiteName;
	}

	public void setRespiteName(String respiteName) {
		this.respiteName = respiteName;
	}

	public Boolean getPermit() {
		return permit;
	}

	public void setPermit(Boolean permit) {
		this.permit = permit;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getForAll() {
		return forAll;
	}

	public void setForAll(Boolean forAll) {
		this.forAll = forAll;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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
