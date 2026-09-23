package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
public ResponseEntity<List<StudentAssignmentDTO>> getStudentAssignments(
        @PathVariable Long studentId) {

    return ResponseEntity.ok(
            studentAssignmentService.getStudentAssignments(studentId)
    );
}
	
@PutMapping("/{studentAssignmentId}/delivered")
public ResponseEntity<StudentAssignmentDTO> setAssignmentDelivered(
        @PathVariable Long studentAssignmentId,
        @RequestParam boolean delivered) {

    return ResponseEntity.ok(
            studentAssignmentService.setAssignmentDelivered(
                    studentAssignmentId,
                    delivered
            )
    );
}
	
@PutMapping("/{studentAssignmentId}/grade")
public ResponseEntity<StudentAssignmentDTO> setAssignmentGrade(
        @PathVariable Long studentAssignmentId,
        @RequestParam
        @Min(0)
        @Max(100)
        Integer grade) {

    return ResponseEntity.ok(
            studentAssignmentService.setAssignmentGrade(
                    studentAssignmentId,
                    grade
            )
    );
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
