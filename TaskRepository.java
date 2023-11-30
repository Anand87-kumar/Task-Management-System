package com.anand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anand.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

