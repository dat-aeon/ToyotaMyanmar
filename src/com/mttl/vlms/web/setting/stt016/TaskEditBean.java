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
 * TaskEditBean
 * 
 * 
 */
@ManagedBean(name = "TaskEditBean")
@ViewScoped
public class TaskEditBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 6273397299924214278L;

	@ManagedProperty(value = "#{TaskService}")
	private TaskService taskService;

	private TaskDto taskDtoEdit;

	@PostConstruct
	public void init() {
		taskDtoEdit = (TaskDto) getSessionParam(CommonConstants.TASK_DTO_EDIT);
	}

	public void redirect() {
		TaskDto taskDto = taskService.getTaskById(taskDtoEdit.getId());
		if (taskDto == null) {
			putSessionParam(CommonConstants.MESSAGE_CODE, "MSG_CMN_011");
			putSessionParam(CommonConstants.MESSAGE_PARAMETER, taskDtoEdit.getId());
			redirect("taskControlList.xhtml?faces-redirect=true");
		}
	}

	public String editTask() {

		if (taskService.checkDuplicateTaskName(taskDtoEdit) > 0) {
			addErrorMessage(null, "MSG_CMN_010", taskDtoEdit.getTaskName());
			return null;
		}

		TaskDto taskDto = taskService.getTaskById(taskDtoEdit.getId());
		if (taskDto == null) {
			addErrorMessage(null, "MSG_CMN_011", taskDtoEdit.getTaskName());
			keepSetMessage();
			return "taskControlList";
		}
		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);
		int count = taskService.updateTask(taskDtoEdit, user.getUserID());
		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", taskDto.getTaskName());
		} else {
			addInfoMessage(null, "MSG_CMN_003", taskDto.getTaskName());
		}
		keepSetMessage();
		return "taskControlList";
	}

	public TaskDto getTaskDtoEdit() {
		return taskDtoEdit;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
}
