package com.login_auth.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login_auth.domain.Task;

public interface TaskRepository extends JpaRepository<Task, UUID>{

	List<Task> findByCompleted(boolean completed);
	
	List <Task> findByCompletedFalse() ;
}
