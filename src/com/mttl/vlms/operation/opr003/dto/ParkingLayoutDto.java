package com.mttl.vlms.operation.opr003.dto;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;
import com.mttl.vlms.setting.stt017.dto.YardDto;
import com.mttl.vlms.setting.stt017.dto.ZoneDto;

/**
 * DriverDto
 * 
 * 
 *
 */
public class ParkingLayoutDto extends AbstractDto {

	private static final long serialVersionUID = -943346434188490998L;

	private List<ZoneDto> zoneDto;

	public List<ZoneDto> getZoneDto() {
		return zoneDto;
	}

	private YardDto yardDto;

	private Integer yardId;

	private String yardName;

	private String address;

	private String description;

	private Integer zoneId;

	private Integer zoneCount;

	public void setZoneDto(List<ZoneDto> zoneDto) {
		this.zoneDto = zoneDto;
	}

	private String zoneName;

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

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String getYardName() {
		return yardName;
	}

	public void setYardName(String yardName) {
		this.yardName = yardName;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public Integer getZoneCount() {
		return zoneCount;
	}

	public void setZoneCount(Integer zoneCount) {
		this.zoneCount = zoneCount;
	}

	public YardDto getYardDto() {
		return yardDto;
	}

	public void setYardDto(YardDto yardDto) {
		this.yardDto = yardDto;
	}

}
