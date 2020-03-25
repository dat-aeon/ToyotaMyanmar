package com.mttl.vlms.setting.stt017.dto;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * DriverDto
 * 
 * 
 *
 */

public class RowDto extends AbstractDto {

	private static final long serialVersionUID = -943346434188490998L;

	private Integer id;

	private Integer yardId;

	private Integer zoneId;

	private String name;

	private Boolean disabled;

	private Integer columnCount;

	private List<ColumnDto> columnDtoList;

	public Integer getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(Integer columnCount) {
		this.columnCount = columnCount;
	}

	public List<ColumnDto> getColumnDtoList() {
		return columnDtoList;
	}

	public void setColumnDtoList(List<ColumnDto> columnDtoList) {
		this.columnDtoList = columnDtoList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYardId() {
		return yardId;
	}

	public void setYardId(Integer yardId) {
		this.yardId = yardId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
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
