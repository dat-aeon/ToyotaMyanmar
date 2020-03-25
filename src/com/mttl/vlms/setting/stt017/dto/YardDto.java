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
public class YardDto extends AbstractDto {

	private static final long serialVersionUID = -943346434188490998L;

	private Integer id;

	private String name;

	private String address;

	private String description;

	private Integer zoneCount;

	private boolean disabled;

	private List<ZoneDto> zoneDtoList;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getZoneCount() {
		return zoneCount;
	}

	public void setZoneCount(Integer zoneCount) {
		this.zoneCount = zoneCount;
	}

	public List<ZoneDto> getZoneDtoList() {
		return zoneDtoList;
	}

	public void setZoneDtoList(List<ZoneDto> zoneDtoList) {
		this.zoneDtoList = zoneDtoList;
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
