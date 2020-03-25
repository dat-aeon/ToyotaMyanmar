package com.mttl.vlms.common.selectdto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * LeaveTypeSelectDto
 * 
 *  
 */
public class LeaveTypeSelectDto extends AbstractDto {

	private static final long serialVersionUID = 5617713588327635909L;

	private Integer leaveTypeId;

	private String leaveTypeName;

	private Boolean needAttach;

	public Integer getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(Integer leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public Boolean getNeedAttach() {
		return needAttach;
	}

	public void setNeedAttach(Boolean needAttach) {
		this.needAttach = needAttach;
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
