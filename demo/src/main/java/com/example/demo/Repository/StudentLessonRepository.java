package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.StudentLesson;

public interface StudentLessonRepository extends JpaRepository<StudentLesson, Long>{
	
	 List<StudentLesson> findByStudentId(Long studentId);

	    List<StudentLesson> findByLessonId(Long lessonId);

	    Optional<StudentLesson> findByStudentIdAndLessonId(
	            Long studentId,
	            Long lessonId
	    );
	    
}
