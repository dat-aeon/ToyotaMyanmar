package com.mttl.vlms.setting.stt017.dto;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * ZoneDto
 * 
 * 
 *
 */

public class ZoneDto extends AbstractDto {

	private static final long serialVersionUID = -943346434188490998L;

	private Integer id;

	private Integer yardId;

	private String name;

	private Boolean disabled;

	private Boolean isExtraZone;

	private int rowCount;

	private int columnCount;

	private List<RowDto> rowDtoList;

	private Boolean deleteFlg;

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

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean getIsExtraZone() {
		return isExtraZone;
	}

	public void setIsExtraZone(Boolean isExtraZone) {
		this.isExtraZone = isExtraZone;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public List<RowDto> getRowDtoList() {
		return rowDtoList;
	}

	public void setRowDtoList(List<RowDto> rowDtoList) {
		this.rowDtoList = rowDtoList;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public Boolean getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

}
