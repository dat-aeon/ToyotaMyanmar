package com.mttl.vlms.operation.opr001.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * ItemVehicleSearchReqDto
 * 
 * 
 *
 */
public class ItemVehicleSearchReqDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7053206879470410480L;

	private Integer dealerId;

	private Integer taskId;

	private Date taskFromDate;

	private Date taskToDate;

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Date getTaskFromDate() {
		return taskFromDate;
	}

	public void setTaskFromDate(Date taskFromDate) {
		this.taskFromDate = taskFromDate;
	}

	public Date getTaskToDate() {
		return taskToDate;
	}

	public void setTaskToDate(Date taskToDate) {
		this.taskToDate = taskToDate;
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
