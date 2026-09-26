package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.StudentAssignmentDTO;
import com.example.demo.Service.StudentAssignmentService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


@RestController
@RequestMapping("student-assignments")
@Validated 
public class StudentAssignmentController {
	
    private final StudentAssignmentService studentAssignmentService;

    public StudentAssignmentController(StudentAssignmentService studentService) {
        super();
        this.studentAssignmentService = studentService;
    }
  
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<StudentAssignmentDTO>> getStudentAssignments(
            @PathVariable Long studentId,
            Authentication authentication) {

        return ResponseEntity.ok(
                studentAssignmentService.getStudentAssignments(
                        studentId,
                        authentication)
        );
    }
	
    @PutMapping("/{studentAssignmentId}/delivered")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentAssignmentDTO> setAssignmentDelivered(
            @PathVariable Long studentAssignmentId,
            @RequestParam boolean delivered,
            Authentication authentication) {

        return ResponseEntity.ok(
                studentAssignmentService.setAssignmentDelivered(
                        studentAssignmentId,
                        delivered,
                        authentication
                )
        );
    }
	
    @PutMapping("/{studentAssignmentId}/grade")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<StudentAssignmentDTO> setAssignmentGrade(
            @PathVariable Long studentAssignmentId,
            @RequestParam
            @Min(0)
            @Max(100)
            Integer grade,
            Authentication authentication) {

        return ResponseEntity.ok(
                studentAssignmentService.setAssignmentGrade(
                        studentAssignmentId,
                        grade,
                        authentication
                )
        );
    }
}
	
	
	
	
	

