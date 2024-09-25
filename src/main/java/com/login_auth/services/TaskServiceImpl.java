package com.login_auth.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login_auth.domain.Task;
import com.login_auth.dto.TaskDTO;
import com.login_auth.repositories.TaskRepository;


@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskRepository repo;

	@Override
	public List<TaskDTO> getAllTasks() {
		List<Task> tasks = repo.findAll();
		List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();
		for(Task task : tasks) {
			taskDTOs.add(convertToDTO(task));
		}

		return taskDTOs;
	}

	@Override
	public TaskDTO createTask(TaskDTO taskDTO) {
		Task task = convertToEntity(taskDTO);
		Task savedTask = repo.save(task);
		return convertToDTO(savedTask);
	}

	@Override
	public TaskDTO updateTask(UUID id, TaskDTO taskDTO) {
        Task task = repo.getReferenceById(id);
        task.setName(taskDTO.getName());
        task.setDescricao(taskDTO.getDescricao());
        task.setCompleted(taskDTO.isCompleted());
        return convertToDTO(repo.save(task));
    }
	

	@Override
	public void deleteTask(UUID id) {
		repo.deleteById(id);
		
	}

	   // Conversão de Entidade para DTO
    private TaskDTO convertToDTO(Task task) {
        return new TaskDTO(task.getId(), task.getName(), task.getDescricao(), task.isCompleted());
    }
    
    // Conversão de DTO para Entidade
    private Task convertToEntity(TaskDTO taskDTO) {
        return new Task(taskDTO.getId(), taskDTO.getName(), taskDTO.getDescricao(), taskDTO.isCompleted());
    }

	@Override
	public TaskDTO markTaskCompleted(UUID id, boolean completed) {
		Task task = repo.getReferenceById(id);
		
		task.setCompleted(completed);
		
		return convertToDTO(repo.save(task));

	}

	@Override
	public List<Task> getIncompletedTask() {
		
		return repo.findByCompletedFalse();
	}
}
