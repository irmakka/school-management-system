package com.example.demo.Controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.StudentLessonDTO;
import com.example.demo.Entity.Grade;
import com.example.demo.Service.StudentLessonService;

import jakarta.validation.constraints.PositiveOrZero;

@RestController
	@RequestMapping("student-lessons")
    @Validated
	public class StudentLessonController {

	    private final StudentLessonService studentLessonService;

	    public StudentLessonController(StudentLessonService studentLessonService) {
	        this.studentLessonService = studentLessonService;
	    }

	    @PostMapping("student/{studentId}/lesson/{lessonId}")
	    public ResponseEntity<StudentLessonDTO> assignLessonToStudent(
	            @PathVariable Long studentId,
	            @PathVariable Long lessonId) {

	        return ResponseEntity.ok(
	                studentLessonService.assignLessonToStudent(studentId, lessonId)
	        );
	    }
	    @PutMapping("{studentLessonId}/grade/{grade}/absent")
	    public ResponseEntity<StudentLessonDTO> setAbsentAndGrade(
	            @PathVariable Long studentLessonId,
	            @PathVariable Grade grade,
	            @RequestParam @PositiveOrZero int absent) {

	        return ResponseEntity.ok(
	                studentLessonService.setAbsentAndGrade(
	                        studentLessonId,
	                        grade,
	                        absent
	                )
	        );
	    }
	       


	    @GetMapping("student/{studentId}")
	    public ResponseEntity<List<StudentLessonDTO>> getStudentLessons(
	            @PathVariable Long studentId) {

	        return ResponseEntity.ok(
	                studentLessonService.getStudentLessons(studentId)
	        );
	    }

	    @GetMapping("student/{studentId}/lesson/{lessonId}")
	    public ResponseEntity<StudentLessonDTO> getStudentLesson(
	            @PathVariable Long studentId,
	            @PathVariable Long lessonId) {

	        return ResponseEntity.ok(
	                studentLessonService.getStudentLesson(studentId, lessonId)
	        );
	    }
	    
	}
	

