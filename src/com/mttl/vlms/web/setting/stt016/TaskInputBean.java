package com.mttl.vlms.web.setting.stt016;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt016.dto.TaskDto;
import com.mttl.vlms.setting.stt016.service.TaskService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * TaskInputBean
 * 
 * 
 */

@ManagedBean(name = "TaskInputBean")
@ViewScoped
public class TaskInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6038234829522378054L;

	@ManagedProperty(value = "#{TaskService}")
	private TaskService taskService;

	private TaskDto taskDto;

	@PostConstruct
	public void init() {
		taskDto = new TaskDto();
	}

	public String inputTask() {

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		if (taskService.checkDuplicateTaskName(taskDto) > 0) {
			addErrorMessage("Task Name " + taskDto.getTaskName() + " is already exist.");
			keepSetMessage();
			return "taskInput";
		} else {
			taskService.insertTask(taskDto, user.getUserID());
		}

		addInfoMessage(null, "MSG_CMN_002", taskDto.getTaskName());
		keepSetMessage();
		return "taskControlList";
	}

	public TaskDto getTaskDto() {
		return taskDto;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskDto(TaskDto taskDto) {
		this.taskDto = taskDto;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

}
