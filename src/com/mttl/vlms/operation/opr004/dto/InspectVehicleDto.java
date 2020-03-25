package com.mttl.vlms.operation.opr004.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * VehicleInfoDto
 * 
 * 
 *
 */
public class InspectVehicleDto extends AbstractDto {

	private static final long serialVersionUID = 1187020691073747192L;

	private Integer inspectVehicleId;

	private Integer vehicleId;

	private Integer taskId;

	private boolean carBody;

	private boolean engine;

	private boolean interior;

	private String inspectRemark;

	private Integer parkingColumnId;

	private Integer parkingStatus;

	private String inspectorBy;

	private Date inspectedDate;

	private String taskName;

	public Integer getInspectVehicleId() {
		return inspectVehicleId;
	}

	public void setInspectVehicleId(Integer inspectVehicleId) {
		this.inspectVehicleId = inspectVehicleId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public boolean isCarBody() {
		return carBody;
	}

	public void setCarBody(boolean carBody) {
		this.carBody = carBody;
	}

	public boolean isEngine() {
		return engine;
	}

	public void setEngine(boolean engine) {
		this.engine = engine;
	}

	public boolean isInterior() {
		return interior;
	}

	public void setInterior(boolean interior) {
		this.interior = interior;
	}

	public String getInspectRemark() {
		return inspectRemark;
	}

	public void setInspectRemark(String inspectRemark) {
		this.inspectRemark = inspectRemark;
	}

	public Integer getParkingColumnId() {
		return parkingColumnId;
	}

	public void setParkingColumnId(Integer parkingColumnId) {
		this.parkingColumnId = parkingColumnId;
	}

	public Integer getParkingStatus() {
		return parkingStatus;
	}

	public void setParkingStatus(Integer parkingStatus) {
		this.parkingStatus = parkingStatus;
	}

	public String getInspectorBy() {
		return inspectorBy;
	}

	public void setInspectorBy(String inspectorBy) {
		this.inspectorBy = inspectorBy;
	}

	public Date getInspectedDate() {
		return inspectedDate;
	}

	public void setInspectedDate(Date inspectedDate) {
		this.inspectedDate = inspectedDate;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
