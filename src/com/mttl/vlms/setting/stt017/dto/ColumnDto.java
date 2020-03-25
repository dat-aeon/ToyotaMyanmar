package com.mttl.vlms.setting.stt017.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;
import com.mttl.vlms.operation.opr002.dto.VehicleInfoDto;

/**
 * DriverDto
 * 
 * 
 *
 */

public class ColumnDto extends AbstractDto {

	private static final long serialVersionUID = -943346434188490998L;

	private Integer column_name;

	private Integer id;

	private Integer yardId;

	private Integer zoneId;

	private Integer rowId;

	private Integer status;

	private String name;

	private Boolean deleteFlg;

	private Boolean disabled;

	private Boolean occupiedFlg;

	private VehicleInfoDto vehicleInfoDto;

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

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean getOccupiedFlg() {
		return occupiedFlg;
	}

	public void setOccupiedFlg(Boolean occupiedFlg) {
		this.occupiedFlg = occupiedFlg;
	}

	public VehicleInfoDto getVehicleInfoDto() {
		return vehicleInfoDto;
	}

	public void setVehicleInfoDto(VehicleInfoDto vehicleInfoDto) {
		this.vehicleInfoDto = vehicleInfoDto;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public Integer getColumn_name() {
		return column_name;
	}

	public void setColumn_name(Integer column_name) {
		this.column_name = column_name;
	}

}
