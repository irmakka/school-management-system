package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Assignment;


public interface AssignmentRepository  extends JpaRepository<Assignment, Long>{
	List<Assignment> findByLessonId(Long lessonId);
}
