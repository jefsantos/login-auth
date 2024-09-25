package com.login_auth.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.login_auth.domain.Task;
import com.login_auth.dto.TaskDTO;

@Service
public interface TaskService {
	List<TaskDTO> getAllTasks();

	TaskDTO createTask(TaskDTO taskDTO);

	TaskDTO updateTask(UUID id, TaskDTO taskDTO);

	void deleteTask(UUID id);
	
	TaskDTO markTaskCompleted(UUID id, boolean completed);
	
	List<Task> getIncompletedTask ();
}