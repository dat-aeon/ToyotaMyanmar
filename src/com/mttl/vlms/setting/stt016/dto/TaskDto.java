package com.mttl.vlms.setting.stt016.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;
import com.mttl.vlms.web.common.MyBean;
import com.mttl.vlms.web.common.MyBean.ProcessTypeList;

/**
 * TaskDto
 * 
 * 
 *
 */
public class TaskDto extends AbstractDto {

	private static final long serialVersionUID = 1187020691073747192L;

	private Integer id;

	private Integer processType;

	private String taskName;

	private String task;

	private String description;

	private boolean disabled;

	private ProcessTypeList newProcessType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProcessType() {
		return processType;
	}

	public void setProcessType(Integer processType) {
		this.processType = processType;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProcessTypeList getNewProcessType() {
		if (processType == 0)
			newProcessType = MyBean.ProcessTypeList.YARD_IN;
		if (processType == 1)
			newProcessType = MyBean.ProcessTypeList.YARD_OUT;
		if (processType == 2)
			newProcessType = MyBean.ProcessTypeList.DAILYOPERATION;
		if (processType == 3)
			newProcessType = MyBean.ProcessTypeList.OTHER;
		return newProcessType;

	}

	public void setNewProcessType(ProcessTypeList newProcessType) {
		this.newProcessType = newProcessType;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
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
