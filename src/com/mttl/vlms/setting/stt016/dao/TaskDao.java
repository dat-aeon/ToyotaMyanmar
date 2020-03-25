package com.mttl.vlms.setting.stt016.dao;

import java.util.List;

import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt016.dto.TaskDto;

/**
 * TaskDao
 * 
 * 
 *
 */
public interface TaskDao {
	List<TaskDto> searchByTaskName(String TaskName) throws DAOException;

	List<TaskSelectDto> getTaskList() throws DAOException;

	int checkDuplicateTaskName(TaskDto TaskDto) throws DAOException;

	int checkTaskUsed(Integer TaskId) throws DAOException;

	void insertTask(TaskDto TaskDto, Integer createdUser) throws DAOException;

	TaskDto getTaskById(Integer TaskId) throws DAOException;

	int updateTask(TaskDto TaskDto, Integer updatedUser) throws DAOException;

	int deleteTask(TaskDto TaskDto, Integer updatedUser) throws DAOException;

}
