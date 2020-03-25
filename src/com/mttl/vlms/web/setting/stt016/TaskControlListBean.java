package com.mttl.vlms.web.setting.stt016;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt016.dto.TaskDto;
import com.mttl.vlms.setting.stt016.service.TaskService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * TaskControlListBean
 * 
 * 
 *
 */
@ManagedBean(name = "TaskControlListBean")
@ViewScoped

public class TaskControlListBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -6598961226154170581L;
	@ManagedProperty(value = "#{TaskService}")
	private TaskService taskService;
	private TaskDto taskSearchDto;
	private List<TaskDto> taskList;
	private List<TaskDto> filterTaskList;
	private boolean paginatorVisible;
	/** for paging page */
	private Integer firstRowCount;

	@PostConstruct
	public void init() {
		taskSearchDto = new TaskDto();
		firstRowCount = (Integer) getSessionParam(CommonConstants.FIRST_ROW_COUNT);
		if (firstRowCount == null) {
			firstRowCount = 0;
		}
		searchTask();
		removeSessionParam(CommonConstants.FIRST_ROW_COUNT, CommonConstants.PROCESS_TYPE, CommonConstants.TASK_NAME, CommonConstants.TASK, CommonConstants.TASK_DESCRIPTION,
				CommonConstants.TASK_DTO_DELETE, CommonConstants.TASK_DTO_EDIT);
	}

	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		int filterInt = getInteger(filterText);

		TaskDto task = (TaskDto) value;
		return task.getTaskName().toLowerCase().contains(filterText) || task.getTask().toLowerCase().contains(filterText)
				|| task.getDescription().toLowerCase().contains(filterText) || (task.isDisabled() ? "enable" : "disable").toString().toLowerCase().contains(filterText);
	}

	private int getInteger(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	public void showMessage() {
		String messageCode = (String) getSessionParam(CommonConstants.MESSAGE_CODE);
		String messageParameter = (String) getSessionParam(CommonConstants.MESSAGE_PARAMETER);
		removeSessionParam(CommonConstants.MESSAGE_CODE, CommonConstants.MESSAGE_PARAMETER);
		if (messageCode != null && messageParameter != null) {
			addErrorMessage(null, messageCode, messageParameter);
		}
	}

	public void searchTask() {

		taskList = taskService.findByTaskName(taskSearchDto.getTaskName());

		if (taskList.size() > 10) {
			paginatorVisible = true;
		}
	}

	public String editTask(TaskDto taskDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmTask:tblTask");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.TASK_DTO_EDIT, taskDto);
		putSessionParam(CommonConstants.PROCESS_TYPE, this.taskSearchDto.getProcessType());
		putSessionParam(CommonConstants.TASK_NAME, this.taskSearchDto.getTaskName());
		putSessionParam(CommonConstants.TASK, this.taskSearchDto.getTask());
		putSessionParam(CommonConstants.TASK_DESCRIPTION, this.taskSearchDto.getDescription());
		return "taskEdit";
	}

	public String deleteConfirmTask(TaskDto taskDto) {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmTask:tblTask");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);
		putSessionParam(CommonConstants.TASK_DTO_DELETE, taskDto);
		putSessionParam(CommonConstants.PROCESS_TYPE, this.taskSearchDto.getProcessType());
		putSessionParam(CommonConstants.TASK_NAME, this.taskSearchDto.getTaskName());
		return "taskDeleteConfirm";
	}

	public void disabledTask(TaskDto taskDtoEdit) {

		taskDtoEdit.setDisabled(!taskDtoEdit.isDisabled());

		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmTask:tblTask");
		firstRowCount = dataTable.getFirst();
		putSessionParam(CommonConstants.FIRST_ROW_COUNT, firstRowCount);

		User user = (User) getSessionParam(CommonConstants.LOGIN_USER);

		int count = taskService.updateTask(taskDtoEdit, user.getUserID());

		System.out.println(count);

		if (count == 0) {
			addErrorMessage(null, "MSG_CMN_006", taskDtoEdit.getId());
		} else {
			addInfoMessage(null, "MSG_CMN_003", taskDtoEdit.getTaskName());
		}
		searchTask();

	}

	public TaskDto getTaskSearchDto() {
		return taskSearchDto;
	}

	public List<TaskDto> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskDto> taskList) {
		this.taskList = taskList;
	}

	public boolean isPaginatorVisible() {
		return paginatorVisible;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public Integer getFirstRowCount() {
		return firstRowCount;
	}

	public List<TaskDto> getFilterTaskList() {
		return filterTaskList;
	}

	public void setFilterTaskList(List<TaskDto> filterTaskList) {
		this.filterTaskList = filterTaskList;
	}

}