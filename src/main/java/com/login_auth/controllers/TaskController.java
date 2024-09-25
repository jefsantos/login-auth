package com.login_auth.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login_auth.domain.Task;
import com.login_auth.dto.TaskDTO;
import com.login_auth.record.CompletedRecord;
import com.login_auth.repositories.TaskRepository;
import com.login_auth.services.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	TaskService service;


	@GetMapping
	public ResponseEntity<?> getAllTasks() {
		List<TaskDTO> listTasks = service.getAllTasks();
		if (listTasks.isEmpty()) {
			ResponseEntity.status(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listTasks, HttpStatus.ACCEPTED);

	}
	
	@PostMapping
	public ResponseEntity<?> creatTask(@RequestBody TaskDTO taskDto){
		TaskDTO createdTask = service.createTask(taskDto);
		
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);	
		
	}
	
	
	@PatchMapping("/{id}/completed")
	public ResponseEntity<?> updateTask (@PathVariable UUID id, @RequestBody CompletedRecord  completedRecord){
		Boolean completed = completedRecord.completed();
		TaskDTO updatedTask= service.markTaskCompleted(id, completed);
		
		return new ResponseEntity<>(updatedTask, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/test-aws")
	public String returnString () {
		return "SISTEMA ON NA AMAZON";
	}
	
	
	@GetMapping("/list-false")
	public ResponseEntity<?> returnTaskFalse(){
		 List<Task> taskFalse = service.getIncompletedTask();
		 
		 return new  ResponseEntity<>(taskFalse, HttpStatus.CREATED);
		
	}
	
}



