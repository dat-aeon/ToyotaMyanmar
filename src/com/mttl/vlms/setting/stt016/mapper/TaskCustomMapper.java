package com.mttl.vlms.setting.stt016.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mttl.vlms.setting.stt016.dto.TaskDto;

/**
 * taskCustomMapper
 * 
 * 
 *
 */
public interface TaskCustomMapper {
	List<TaskDto> searchByTaskName(@Param("taskName") String taskName);

	int checkTaskName(@Param("taskDto") TaskDto taskDto);

	int checkTaskCode(@Param("taskDto") TaskDto taskDto);

	int checkTaskUsed(@Param("taskId") Integer taskId);

	TaskDto getTaskById(@Param("taskId") Integer taskId);

}
