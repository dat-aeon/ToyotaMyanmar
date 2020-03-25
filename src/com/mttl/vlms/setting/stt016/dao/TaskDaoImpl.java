package com.mttl.vlms.setting.stt016.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.common.BasicDAO;
import com.mttl.vlms.common.BeanConverter;
import com.mttl.vlms.common.Utils;
import com.mttl.vlms.common.selectdto.TaskSelectDto;
import com.mttl.vlms.common.selectmapper.SelectMapper;
import com.mttl.vlms.entity.Task;
import com.mttl.vlms.entity.TaskExample;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.mapper.TaskMapper;
import com.mttl.vlms.setting.stt016.dto.TaskDto;
import com.mttl.vlms.setting.stt016.mapper.TaskCustomMapper;

/**
 * TaskDaoImpl
 * 
 * 
 *
 */
@Repository("TaskDao")
@Transactional(propagation = Propagation.REQUIRED)
public class TaskDaoImpl extends BasicDAO implements TaskDao {

	@Autowired
	TaskCustomMapper taskCustomMapper;

	@Autowired
	TaskMapper taskMapper;

	@Autowired
	private SelectMapper selectMapper;

	@Autowired
	BeanConverter beanConverter;

	/*
	 * @ApplyAspect
	 * 
	 * @Override public int checkDuplicateShortName(TaskDto taskDto) throws
	 * DAOException {
	 * 
	 * int taskCodeCount; try { taskCodeCount =
	 * taskCustomMapper.checkShortName(taskDto); } catch (RuntimeException e) {
	 * throw translate(e.getMessage(), e); } return taskCodeCount; }
	 */

	@ApplyAspect

	@Override
	public int checkDuplicateTaskName(TaskDto taskDto) throws DAOException {
		int taskNameCount;
		try {
			taskNameCount = taskCustomMapper.checkTaskName(taskDto);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return taskNameCount;
	}

	@ApplyAspect
	@Override
	public List<TaskDto> searchByTaskName(String taskName) throws DAOException {
		List<TaskDto> result;
		try {
			result = taskCustomMapper.searchByTaskName(taskName);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public int checkTaskUsed(Integer id) throws DAOException {
		int taskNameCount;
		try {
			taskNameCount = taskCustomMapper.checkTaskUsed(id);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return taskNameCount;
	}

	@ApplyAspect
	@Override
	public void insertTask(TaskDto taskDto, Integer createdUser) throws DAOException {
		try {
			Date createdDate = Utils.getCurrentTime();
			Task task = beanConverter.convert(taskDto, Task.class);
			task.setCreatedDate(createdDate);
			task.setCreatedUser(createdUser);
			taskMapper.insertSelective(task);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public TaskDto getTaskById(Integer taskId) throws DAOException {
		TaskDto taskDto;
		try {
			taskDto = taskCustomMapper.getTaskById(taskId);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return taskDto;
	}

	@ApplyAspect
	@Override
	public int updateTask(TaskDto taskDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			taskDto.setProcessType(taskDto.getProcessType());
			taskDto.setTaskName(taskDto.getTaskName());
			Task task = beanConverter.convert(taskDto, Task.class);
			task.setUpdatedDate(updatedDate);
			task.setUpdatedUser(updatedUser);
			TaskExample example = new TaskExample();
			example.createCriteria().andIdEqualTo(task.getId()).andDeleteFlgEqualTo(false);
			if (null == task.getDescription()) {
				task.setDescription("");
			}
			count = taskMapper.updateByExampleSelective(task, example);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public int deleteTask(TaskDto taskDto, Integer updatedUser) throws DAOException {
		int count;
		try {
			Date updatedDate = Utils.getCurrentTime();
			Task task = beanConverter.convert(taskDto, Task.class);
			task.setDeleteFlg(true);
			task.setUpdatedUser(updatedUser);
			task.setUpdatedDate(updatedDate);
			TaskExample taskExample = new TaskExample();
			taskExample.createCriteria().andIdEqualTo(task.getId()).andDeleteFlgEqualTo(false);
			count = taskMapper.updateByExampleSelective(task, taskExample);
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return count;
	}

	@ApplyAspect
	@Override
	public List<TaskSelectDto> getTaskList() throws DAOException {
		List<TaskSelectDto> taskList;
		try {
			taskList = selectMapper.getTaskList();
		} catch (RuntimeException e) {
			throw translate(e.getMessage(), e);
		}
		return taskList;
	}

}
