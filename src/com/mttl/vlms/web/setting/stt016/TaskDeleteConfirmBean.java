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
 * TaskDeleteConfirmBean
 * 
 * 
 */
@ManagedBean(name = "TaskDeleteConfirmBean")
@ViewScoped
public class TaskDeleteConfirmBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7173852451546888968L;

	@ManagedProperty(value = "#{TaskService}")
	private TaskService taskService;

	private TaskDto taskDtoDelete;

	@PostConstruct
	public void init() {
		taskDtoDelete = (TaskDto) getSessionParam(CommonConstants.TASK_DTO_DELETE);
	}

	public void redirect() {
		TaskDto task = taskService.getTaskById(taskDtoDelete.getId());
		if (task == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, taskDtoDelete.getId());
			redirect("taskControlList.xhtml?faces-redirect=true");
		}
	}

	public String deleteConfirmTask() {
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int usedCount = taskService.checkTaskUsed(taskDtoDelete.getId());
		/*
		 * if (usedCount > 0) { addErrorMessage(null,
		 * "COUNTRY_DELETE_REJECT_MSG", taskDtoDelete.getTaskName()); } else {
		 */
		int count = taskService.deleteTask(taskDtoDelete, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_007", taskDtoDelete.getTaskName());
		} else {
			addInfoMessage(null, "MSG_CMN_004", taskDtoDelete.getTaskName());
		}
		// }
		keepSetMessage();
		return "taskControlList";
	}

	public TaskDto getTaskDtoDelete() {
		return taskDtoDelete;
	}

	public void setTaskDtoDelete(TaskDto taskDtoDelete) {
		this.taskDtoDelete = taskDtoDelete;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
}
