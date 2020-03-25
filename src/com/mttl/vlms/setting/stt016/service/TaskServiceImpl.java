package com.mttl.vlms.setting.stt016.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt016.dao.TaskDao;
import com.mttl.vlms.setting.stt016.dto.TaskDto;

/**
 * TaskServiceImpl
 * 
 * 
 *
 */
@Service("TaskService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskDao taskDao;

	@ApplyAspect
	@Override
	public List<TaskDto> findByTaskName(String taskName) throws SystemException {
		List<TaskDto> result;
		try {
			result = taskDao.searchByTaskName(taskName);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect

	@Override
	public int checkDuplicateTaskName(TaskDto taskDto) throws SystemException {
		int fullNameCount;
		try {
			fullNameCount = taskDao.checkDuplicateTaskName(taskDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return fullNameCount;
	}

	@ApplyAspect
	@Override
	public int checkTaskUsed(Integer taskId) throws SystemException {
		int taskCount;
		try {
			taskCount = taskDao.checkTaskUsed(taskId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return taskCount;
	}

	@ApplyAspect
	@Override
	public void insertTask(TaskDto taskDto, Integer createdUser) throws SystemException {
		try {
			taskDao.insertTask(taskDto, createdUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public TaskDto getTaskById(Integer taskId) throws SystemException {
		TaskDto taskDto;
		try {
			taskDto = taskDao.getTaskById(taskId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return taskDto;
	}

	@ApplyAspect
	@Override
	public int updateTask(TaskDto taskDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = taskDao.updateTask(taskDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteTask(TaskDto taskDto, Integer updatedUser) throws SystemException {
		int count;
		try {
			count = taskDao.deleteTask(taskDto, updatedUser);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return count;
	}

}
