package com.mttl.vlms.setting.stt016.service;

import java.util.List;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt016.dto.TaskDto;

/**
 * TaskService
 * 
 * 
 *
 */
public interface TaskService {

	List<TaskDto> findByTaskName(String taskName) throws SystemException;

	int checkDuplicateTaskName(TaskDto taskDto) throws SystemException;

	int checkTaskUsed(Integer taskId) throws SystemException;

	void insertTask(TaskDto taskDto, Integer createdUser) throws SystemException;

	TaskDto getTaskById(Integer taskId) throws SystemException;

	int updateTask(TaskDto taskDto, Integer updatedUser) throws SystemException;

	int deleteTask(TaskDto taskDto, Integer updatedUser) throws SystemException;

}
