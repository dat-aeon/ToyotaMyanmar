package com.mttl.vlms.setting.stt013.dto;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

public class UserInfoSearchReqDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3396274718088360576L;
	private Integer limit;
	private Integer offSet;
	private String sortField;
	private String sortOrder;
	private Map<String, Object> filters;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffSet() {
		return offSet;
	}

	public void setOffSet(Integer offSet) {
		this.offSet = offSet;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
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
